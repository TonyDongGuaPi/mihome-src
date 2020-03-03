package com.miui.tsmclient.net;

import android.content.Context;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.common.net.host.AuthHost;
import com.miui.tsmclient.common.net.host.Host;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.EnvironmentConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AuthRequest {
    public static final boolean STAGING = EnvironmentConfig.isStaging();
    /* access modifiers changed from: private */
    public Map<String, String> mCookies;
    /* access modifiers changed from: private */
    public Host mHost;
    /* access modifiers changed from: private */
    public Map<String, String> mParams;
    /* access modifiers changed from: private */
    public RespContentType mRespContentType;
    /* access modifiers changed from: private */
    public String mUrl;

    public enum RespContentType {
        json,
        protobuf
    }

    private AuthRequest() {
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public Map<String, String> getCookies() {
        return this.mCookies;
    }

    public String getRequestFullUrl() {
        if (this.mUrl == null) {
            this.mUrl = "";
        }
        return String.format(getServer() + this.mUrl, new Object[]{this.mHost.getAuthType()});
    }

    public RespContentType getRespContentType() {
        return this.mRespContentType;
    }

    private String getServer() {
        if (STAGING) {
            return this.mHost.getStagingHost();
        }
        return this.mHost.getOnlineHost();
    }

    public void addParams(String str, String str2) {
        this.mParams.put(str, str2);
    }

    public static class AuthRequestBuilder {
        private Map<String, String> mCookies = new HashMap();
        private Host mHost;
        private Map<String, String> mParams = new HashMap();
        private RespContentType mRespContentType;
        private String mUrl;

        private AuthRequestBuilder() {
        }

        public static AuthRequestBuilder newBuilder(AccountInfo accountInfo, String str, RespContentType respContentType) {
            return newBuilder(accountInfo, new AuthHost(), str, respContentType);
        }

        public static AuthRequestBuilder newBuilder(AccountInfo accountInfo, Host host, String str, RespContentType respContentType) {
            AuthRequestBuilder authRequestBuilder = new AuthRequestBuilder();
            if (accountInfo != null) {
                authRequestBuilder.mCookies.put("userId", accountInfo.getUserId());
                authRequestBuilder.mCookies.put("serviceToken", accountInfo.getServiceToken());
                authRequestBuilder.mParams.put("userId", accountInfo.getUserId());
                authRequestBuilder.mParams.put(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null));
                authRequestBuilder.mParams.put(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion());
                authRequestBuilder.mParams.put(TSMAuthContants.PARAM_ANDROID_VERSION, DeviceUtils.getAndroidVersion());
                Context context = EnvironmentConfig.getContext();
                if (context != null) {
                    authRequestBuilder.mParams.put(TSMAuthContants.PARAM_TSMCLIENT_VERSION_CODE, String.valueOf(DeviceUtils.getAppVersionCode(context, "com.miui.tsmclient")));
                    authRequestBuilder.mParams.put(TSMAuthContants.PARAM_TSMCLIENT_VERSION_NAME, String.valueOf(DeviceUtils.getAppVersionName(context, "com.miui.tsmclient")));
                    authRequestBuilder.mParams.put(TSMAuthContants.PARAM_APP_VERSION_NAME, String.valueOf(DeviceUtils.getAppVersionCode(context)));
                    authRequestBuilder.mParams.put(TSMAuthContants.PARAM_APP_VERSION_CODE, String.valueOf(DeviceUtils.getAppVersionName(context)));
                }
            }
            authRequestBuilder.mHost = host;
            authRequestBuilder.mUrl = str;
            authRequestBuilder.mRespContentType = respContentType;
            authRequestBuilder.mParams.put(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString());
            return authRequestBuilder;
        }

        public AuthRequestBuilder addParams(String str, String str2) {
            this.mParams.put(str, str2);
            return this;
        }

        public AuthRequest create() {
            AuthRequest authRequest = new AuthRequest();
            Map unused = authRequest.mParams = this.mParams;
            Map unused2 = authRequest.mCookies = this.mCookies;
            Host unused3 = authRequest.mHost = this.mHost;
            String unused4 = authRequest.mUrl = this.mUrl;
            RespContentType unused5 = authRequest.mRespContentType = this.mRespContentType;
            return authRequest;
        }
    }
}
