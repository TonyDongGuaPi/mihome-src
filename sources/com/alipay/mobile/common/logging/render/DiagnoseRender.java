package com.alipay.mobile.common.logging.render;

import android.os.Build;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.helper.DeviceHWRenderHelper;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;
import java.util.HashMap;
import java.util.Map;

public class DiagnoseRender extends BaseRender {
    public DiagnoseRender(LogContext logContext) {
        super(logContext);
    }

    public String render(String str, String str2, Throwable th, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("D-EM");
        LoggingUtil.appendParam(sb, LoggingUtil.getNowTime());
        LoggingUtil.appendParam(sb, this.logContext.getProductId());
        LoggingUtil.appendParam(sb, this.logContext.getProductVersion());
        LoggingUtil.appendParam(sb, "2");
        LoggingUtil.appendParam(sb, this.logContext.getClientId());
        LoggingUtil.appendParam(sb, this.logContext.getUserId());
        LoggingUtil.appendParam(sb, NetUtil.getNetworkType(this.logContext.getApplicationContext()));
        LoggingUtil.appendParam(sb, Build.MODEL);
        LoggingUtil.appendParam(sb, Build.VERSION.RELEASE);
        LoggingUtil.appendParam(sb, this.logContext.getReleaseCode());
        LoggingUtil.appendParam(sb, this.logContext.getChannelId());
        LoggingUtil.appendParam(sb, this.logContext.getReleaseType());
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam("appID"));
        LoggingUtil.appendParam(sb, str);
        LoggingUtil.appendParam(sb, str2);
        if (th != null) {
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("stackFrame", LoggingUtil.throwableToString(th));
        }
        LoggingUtil.appendExtParam(sb, map);
        LoggingUtil.appendParam(sb, this.logContext.getLanguage());
        LoggingUtil.appendParam(sb, this.logContext.getHotpatchVersion());
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getNumCoresOfCPU()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getCPUMaxFreqMHz()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getTotalMem(this.logContext.getApplicationContext())));
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getApkUniqueId());
        sb.append(Consts.c);
        return sb.toString();
    }
}
