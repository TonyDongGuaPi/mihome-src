package com.alipay.android.phone.mobilecommon.logger;

import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.security.bio.utils.Logger;

public class a extends Logger {
    public int verbose(String str, String str2) {
        LoggerFactory.getTraceLogger().verbose(str, str2);
        return 0;
    }

    public int debug(String str, String str2) {
        LoggerFactory.getTraceLogger().debug(str, str2);
        return 0;
    }

    public int info(String str, String str2) {
        LoggerFactory.getTraceLogger().info(str, str2);
        return 0;
    }

    public int warn(String str, String str2) {
        LoggerFactory.getTraceLogger().warn(str, str2);
        return 0;
    }

    public int error(String str, String str2) {
        LoggerFactory.getTraceLogger().error(str, str2);
        return 0;
    }

    /* access modifiers changed from: protected */
    public String a(Throwable th) {
        return LoggingUtil.throwableToString(th);
    }
}
