package com.alipay.mobile.common.logging.render;

import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;
import java.util.Map;

public class DataflowRender extends BaseRender {
    public DataflowRender(LogContext logContext) {
        super(logContext);
    }

    public String render(String str, String str2, long j, String str3, Map<String, String> map, long j2, long j3, long j4) {
        StringBuilder sb = new StringBuilder();
        sb.append("DF");
        LoggingUtil.appendParam(sb, LoggingUtil.getNowTime());
        LoggingUtil.appendParam(sb, this.logContext.getProductId());
        LoggingUtil.appendParam(sb, this.logContext.getProductVersion());
        LoggingUtil.appendParam(sb, this.logContext.getUserId());
        LoggingUtil.appendParam(sb, this.logContext.getDeviceId());
        LoggingUtil.appendParam(sb, NetUtil.getNetworkType(this.logContext.getApplicationContext()));
        LoggingUtil.appendParam(sb, str);
        LoggingUtil.appendParam(sb, str2);
        LoggingUtil.appendParam(sb, String.valueOf(j));
        LoggingUtil.appendParam(sb, str3);
        LoggingUtil.appendExtParam(sb, map);
        LoggingUtil.appendParam(sb, String.valueOf(j2));
        LoggingUtil.appendParam(sb, String.valueOf(j3));
        LoggingUtil.appendParam(sb, String.valueOf(j4));
        sb.append(Consts.c);
        return sb.toString();
    }
}
