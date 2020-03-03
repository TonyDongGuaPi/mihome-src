package com.alipay.mobile.common.logging.render;

import android.os.Build;
import android.text.TextUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.alipay.mobile.common.logging.LogContextImpl;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.behavor.Behavor;
import com.alipay.mobile.common.logging.helper.DeviceHWRenderHelper;
import com.alipay.mobile.common.logging.util.LoggingUtil;
import com.alipay.mobile.common.logging.util.NetUtil;

public class BehavorRender extends BaseRender {
    public BehavorRender(LogContext logContext) {
        super(logContext);
    }

    public String render(String str, Behavor behavor) {
        StringBuilder sb = new StringBuilder();
        sb.append("D-VM");
        LoggingUtil.appendParam(sb, LoggingUtil.getNowTime());
        LoggingUtil.appendParam(sb, this.logContext.getProductId());
        LoggingUtil.appendParam(sb, this.logContext.getProductVersion());
        LoggingUtil.appendParam(sb, "2");
        LoggingUtil.appendParam(sb, this.logContext.getClientId());
        LoggingUtil.appendParam(sb, this.logContext.getSessionId());
        LoggingUtil.appendParam(sb, this.logContext.getUserId());
        if (!TextUtils.isEmpty(str)) {
            LoggingUtil.appendParam(sb, str);
        } else {
            LoggingUtil.appendParam(sb, "event");
        }
        LoggingUtil.appendParam(sb, behavor.getStatus());
        LoggingUtil.appendParam(sb, behavor.getStatusMsg());
        if (behavor.getAppID() != null) {
            LoggingUtil.appendParam(sb, behavor.getAppID());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getStorageParam("appID"));
        }
        LoggingUtil.appendParam(sb, behavor.getAppVersion());
        LoggingUtil.appendParam(sb, behavor.getViewID());
        LoggingUtil.appendParam(sb, behavor.getRefViewID());
        LoggingUtil.appendParam(sb, behavor.getSeedID());
        LoggingUtil.appendParam(sb, behavor.getUrl());
        LoggingUtil.appendParam(sb, behavor.getBehaviourPro());
        LoggingUtil.appendParam(sb, behavor.getLogPro());
        LoggingUtil.appendParam(sb, behavor.getParam1());
        LoggingUtil.appendParam(sb, behavor.getParam2());
        LoggingUtil.appendParam(sb, behavor.getParam3());
        if (behavor.getLegacyParam() != null) {
            LoggingUtil.appendParam(sb, behavor.getLegacyParam());
        } else {
            LoggingUtil.appendJsonExtParam(sb, behavor.getExtParams());
        }
        LoggingUtil.appendParam(sb, this.logContext.getSourceId());
        LoggingUtil.appendParam(sb, this.logContext.getContextParam(LogContextImpl.STORAGE_PAGESERIAL));
        LoggingUtil.appendParam(sb, this.logContext.getDeviceId());
        LoggingUtil.appendParam(sb, behavor.getUserCaseID());
        LoggingUtil.appendParam(sb, (String) null);
        if (behavor.getRefViewID() != null) {
            LoggingUtil.appendParam(sb, behavor.getRefViewID());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getContextParam(LogContextImpl.STORAGE_REFVIEWID));
        }
        if (behavor.getViewID() != null) {
            LoggingUtil.appendParam(sb, behavor.getViewID());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getContextParam(LogContextImpl.STORAGE_VIEWID));
        }
        if (behavor.getTrackId() != null) {
            LoggingUtil.appendParam(sb, behavor.getTrackId());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getStorageParam(LogContext.LOCAL_STORAGE_ACTIONID));
        }
        if (behavor.getTrackToken() != null) {
            LoggingUtil.appendParam(sb, behavor.getTrackToken());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getStorageParam("actionToken"));
        }
        if (behavor.getTrackDesc() != null) {
            LoggingUtil.appendParam(sb, behavor.getTrackDesc());
        } else {
            LoggingUtil.appendParam(sb, this.logContext.getStorageParam(LogContext.LOCAL_STORAGE_ACTIONDESC));
        }
        LoggingUtil.appendParam(sb, Build.MODEL);
        LoggingUtil.appendParam(sb, Build.VERSION.RELEASE);
        LoggingUtil.appendParam(sb, NetUtil.getNetworkType(this.logContext.getApplicationContext()));
        LoggingUtil.appendParam(sb, this.logContext.getReleaseCode());
        LoggingUtil.appendParam(sb, this.logContext.getChannelId());
        LoggingUtil.appendParam(sb, this.logContext.getLanguage());
        LoggingUtil.appendParam(sb, this.logContext.getHotpatchVersion());
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getNumCoresOfCPU()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getCPUMaxFreqMHz()));
        LoggingUtil.appendParam(sb, String.valueOf(DeviceHWRenderHelper.getTotalMem(this.logContext.getApplicationContext())));
        sb.append(Consts.c);
        return sb.toString();
    }
}
