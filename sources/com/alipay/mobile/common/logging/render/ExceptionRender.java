package com.alipay.mobile.common.logging.render;

import android.os.Build;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.LogContextImpl;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.monitor.ExceptionID;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;
import com.facebook.internal.AnalyticsEvents;

public class ExceptionRender extends BaseRender {
    public ExceptionRender(LogContext logContext) {
        super(logContext);
    }

    public String render(ExceptionID exceptionID, Throwable th, String str) {
        return render(exceptionID, LoggingUtil.throwableToString(th), str, false, LoggerFactory.getProcessInfo().getProcessAlias(), Thread.currentThread().getName(), false);
    }

    public String render(ExceptionID exceptionID, String str, String str2, boolean z, String str3, String str4, boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append("e");
        LoggingUtil.appendParam(sb, LoggingUtil.getNowTime());
        LoggingUtil.appendParam(sb, this.logContext.getProductId());
        LoggingUtil.appendParam(sb, this.logContext.getProductVersion());
        LoggingUtil.appendParam(sb, "4");
        LoggingUtil.appendParam(sb, this.logContext.getClientId());
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getUserId());
        LoggingUtil.appendParam(sb, LogCategory.CATEGORY_EXCEPTION);
        LoggingUtil.appendParam(sb, this.logContext.getClientStatus(z));
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam("appID"));
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, exceptionID.getDes());
        LoggingUtil.appendParam(sb, str);
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getChannelId());
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getContextParam(LogContextImpl.STORAGE_REFVIEWID));
        LoggingUtil.appendParam(sb, this.logContext.getContextParam(LogContextImpl.STORAGE_VIEWID));
        LoggingUtil.appendParam(sb, (String) null);
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam(LogContext.LOCAL_STORAGE_ACTIONID));
        LoggingUtil.appendParam(sb, this.logContext.getStorageParam("actionToken"));
        LoggingUtil.appendParam(sb, Build.MODEL);
        LoggingUtil.appendParam(sb, Build.VERSION.RELEASE);
        LoggingUtil.appendParam(sb, NetUtil.getNetworkType(this.logContext.getApplicationContext()));
        LoggingUtil.appendParam(sb, str2);
        LoggingUtil.appendParam(sb, this.logContext.getReleaseCode());
        LoggingUtil.appendParam(sb, this.logContext.getDeviceId());
        LoggingUtil.appendParam(sb, this.logContext.getLanguage());
        LoggingUtil.appendParam(sb, this.logContext.getHotpatchVersion());
        LoggingUtil.appendParam(sb, str3);
        LoggingUtil.appendParam(sb, str4);
        LoggingUtil.appendParam(sb, z2 ? AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE : "java");
        LoggingUtil.appendParam(sb, this.logContext.getApkUniqueId());
        sb.append(Consts.c);
        return sb.toString();
    }
}
