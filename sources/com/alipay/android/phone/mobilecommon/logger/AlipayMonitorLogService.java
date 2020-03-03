package com.alipay.android.phone.mobilecommon.logger;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.EventCategory;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.api.behavor.Behavor;
import com.alipay.mobile.security.bio.log.BehaviourIdEnum;
import com.alipay.mobile.security.bio.log.VerifyBehavior;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.ReflectUtils;

public class AlipayMonitorLogService extends MonitorLogService {

    /* renamed from: a  reason: collision with root package name */
    private boolean f819a = false;

    public void install(Context context) {
        BioLog.i("AlipayMonitorLogService init:" + this.f819a + "|" + sInitialized);
        if (!this.f819a) {
            BioLog.i("LoggerFactory init");
            super.install(context);
            LoggerFactory.init(context);
            BioLog.setLogger(new a());
            this.f819a = true;
        }
    }

    public void logBehavior(BehaviourIdEnum behaviourIdEnum, VerifyBehavior verifyBehavior) {
        if (verifyBehavior == null) {
            BioLog.w("verifyBehavior is null");
            return;
        }
        Behavor behavor = new Behavor();
        behavor.setBehaviourPro(verifyBehavior.getBizType());
        a(behavor, verifyBehavior.getLoggerLevel());
        behavor.setUserCaseID(verifyBehavior.getUserCaseID());
        if (!TextUtils.isEmpty(verifyBehavior.getAppID())) {
            behavor.setAppID(verifyBehavior.getAppID());
        }
        behavor.setSeedID(verifyBehavior.getSeedID());
        behavor.setParam1(verifyBehavior.getParam1());
        behavor.setParam2(verifyBehavior.getParam2());
        behavor.setParam3(verifyBehavior.getParam3());
        if (verifyBehavior.getExtParams() != null) {
            behavor.getExtParams().putAll(verifyBehavior.getExtParams());
        }
        behavor.getExtParams().put("integration", "alipaycloud_china_sdk");
        behavor.addExtParam("verison", "3.0.0");
        behavor.addExtParam("mpaas", "0");
        LoggerFactory.getBehavorLogger().event(behaviourIdEnum == null ? "" : behaviourIdEnum.getDes(), behavor);
    }

    private static void a(Behavor behavor, int i) {
        if (behavor != null) {
            try {
                ReflectUtils.invokeMethod(behavor, "setLoggerLevel", new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(i)});
            } catch (Throwable unused) {
            }
        }
    }

    public void trigUpload() {
        super.trigUpload();
        try {
            LoggerFactory.getLogContext().flush(true);
            LoggerFactory.getLogContext().appendLogEvent(new LogEvent(EventCategory.CATEGORY_UPLOAD, (String) null, LogEvent.Level.ERROR, "gotoBackground"));
        } catch (Exception unused) {
        }
    }
}
