package com.alipay.mobile.common.logging.render;

import android.os.Build;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.monitor.Performance;
import com.alipay.mobile.common.logging.helper.DeviceHWRenderHelper;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;
import java.util.Map;

public class PerformanceRender extends BaseRender {
    public PerformanceRender(LogContext logContext) {
        super(logContext);
    }

    public String render(String str, Performance performance) {
        return render(str, performance.getSubType(), performance.getParam1(), performance.getParam2(), performance.getParam3(), performance.getExtPramas());
    }

    public String render(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("D-MM");
        LoggingUtil.appendParam(sb, LoggingUtil.getNowTime());
        LoggingUtil.appendParam(sb, this.logContext.getProductId());
        LoggingUtil.appendParam(sb, this.logContext.getProductVersion());
        LoggingUtil.appendParam(sb, "2");
        LoggingUtil.appendParam(sb, this.logContext.getClientId());
        LoggingUtil.appendParam(sb, this.logContext.getSessionId());
        LoggingUtil.appendParam(sb, this.logContext.getUserId());
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam(LogContext.LOCAL_STORAGE_ACTIONID));
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam("actionToken"));
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam(LogContext.LOCAL_STORAGE_ACTIONDESC));
        LoggingUtil.appendParam(sb, str);
        LoggingUtil.appendParam(sb, str2);
        LoggingUtil.appendParam(sb, str3);
        LoggingUtil.appendParam(sb, str4);
        LoggingUtil.appendParam(sb, str5);
        LoggingUtil.appendExtParam(sb, map);
        LoggingUtil.appendParam(sb, "android");
        LoggingUtil.appendParam(sb, Build.VERSION.RELEASE);
        LoggingUtil.appendParam(sb, NetUtil.getNetworkType(this.logContext.getApplicationContext()));
        LoggingUtil.appendParam(sb, Build.MODEL);
        LoggingUtil.appendParam(sb, this.logContext.getReleaseCode());
        LoggingUtil.appendParam(sb, this.logContext.getChannelId());
        LoggingUtil.appendParam(sb, this.logContext.getDeviceId());
        LoggingUtil.appendParam(sb, this.logContext.getLanguage());
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getNumCoresOfCPU()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getCPUMaxFreqMHz()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getTotalMem(this.logContext.getApplicationContext())));
        LoggingUtil.appendParam(sb, this.logContext.getHotpatchVersion());
        sb.append(Consts.c);
        return sb.toString();
    }
}
