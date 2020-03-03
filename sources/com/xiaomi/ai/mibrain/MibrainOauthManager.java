package com.xiaomi.ai.mibrain;

public class MibrainOauthManager {
    public static int MIBRAIN_OAUTH_PT_DEV = 0;
    public static int MIBRAIN_OAUTH_PT_SHUIDI = 1;
    public static int MIBRAIN_OAUTH_UPDATE_TOKEN_BY_CODE = 3;
    public static int MIBRAIN_OAUTH_UPDATE_TOKEN_BY_REFRESHTOKEN = 2;
    private String mClientId;
    private String mClientSecret;
    private MibrainOauthManagerCallbacks mMibrainOauthManagerCallbacks;
    private long mNativeInstance;
    private String mRedirectUrl;
    private String mScopeData;
    private updateTokenCallback updateCallback;

    public static class AccessTokenResult {
        public String accessToken;
        public int errorCode;
        public boolean isfromCode;
    }

    public interface updateTokenCallback {
        void onUpdatedToken(int i, String str);
    }

    private native String genAnonymousAuthorization(long j, String str, String str2, String str3);

    private native void getAccessToken(long j, AccessTokenResult accessTokenResult, int i);

    private native int getErrorCode(long j);

    private native int init(long j, String str, String str2, String str3, String str4);

    private native long newInstance();

    private native void releaseInstance(long j);

    private native int setAnonymousAuth(long j, String str, String str2, String str3, int i);

    private native void setEnv(long j, int i);

    private native void setPt(long j, int i);

    private native void setTimeout(long j, int i);

    private native void unInit(long j);

    private native int updateAnonymousArgs(long j, String str, String str2, String str3, String str4);

    /* access modifiers changed from: protected */
    public String getOauthData(String str) {
        return this.mMibrainOauthManagerCallbacks.getOauthData(str);
    }

    /* access modifiers changed from: protected */
    public boolean putOauthData(String str, String str2) {
        return this.mMibrainOauthManagerCallbacks.putOauthData(str, str2);
    }

    /* access modifiers changed from: protected */
    public String getOauthCode() {
        return this.mMibrainOauthManagerCallbacks.getOauthCode();
    }

    /* access modifiers changed from: protected */
    public void updateToken(int i, String str) {
        if (this.updateCallback != null) {
            this.updateCallback.onUpdatedToken(i, str);
        }
    }

    public void setUpdateTokenCallback(updateTokenCallback updatetokencallback) {
        this.updateCallback = updatetokencallback;
    }

    static {
        Mibrainsdk.loadMibrainLibs();
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    public String getcopeData() {
        return this.mScopeData;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public MibrainOauthManager(MibrainOauthManagerCallbacks mibrainOauthManagerCallbacks, String str, String str2, String str3, String str4) {
        this.mRedirectUrl = str;
        this.mClientSecret = str2;
        this.mClientId = str3;
        this.mScopeData = str4;
        this.mMibrainOauthManagerCallbacks = mibrainOauthManagerCallbacks;
    }

    public void init() {
        this.mNativeInstance = newInstance();
        if (this.mNativeInstance != 0) {
            if (init(this.mNativeInstance, this.mRedirectUrl, this.mClientSecret, this.mClientId, this.mScopeData) != 0) {
                throw new IllegalArgumentException("init failed,please check params");
            }
            return;
        }
        throw new RuntimeException("newInstance failed ");
    }

    public int getErrorCode() {
        if (this.mNativeInstance != 0) {
            return getErrorCode(this.mNativeInstance);
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public AccessTokenResult getAccessToken(boolean z) {
        if (this.mNativeInstance != 0) {
            AccessTokenResult accessTokenResult = new AccessTokenResult();
            getAccessToken(this.mNativeInstance, accessTokenResult, z ? 1 : 0);
            return accessTokenResult;
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public void setOauthEnv(int i) {
        if (this.mNativeInstance != 0) {
            setEnv(this.mNativeInstance, i);
            return;
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public void setOauthPt(int i) {
        if (this.mNativeInstance != 0) {
            setPt(this.mNativeInstance, i);
            return;
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public void setNetTimeOut(int i) {
        if (this.mNativeInstance != 0) {
            setTimeout(this.mNativeInstance, i);
            return;
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public void release() {
        if (this.mNativeInstance != 0) {
            unInit(this.mNativeInstance);
            releaseInstance(this.mNativeInstance);
            this.mNativeInstance = 0;
            return;
        }
        throw new IllegalStateException("MibrainOauthManager not init");
    }

    public int setAnonymousAuth(String str, String str2, String str3, int i) {
        if (this.mNativeInstance != 0) {
            return setAnonymousAuth(this.mNativeInstance, str, str2, str3, i);
        }
        throw new RuntimeException("newInstance failed ");
    }

    public int updateAnonymousArgs(String str, String str2, String str3, String str4, String str5, String str6) {
        if (str == null || str2 == null || str3 == null || str5 == null || str6 == null) {
            return -1;
        }
        String format = String.format("%s;%s;%s", new Object[]{str4, str5.toLowerCase(), str6.toLowerCase()});
        if (this.mNativeInstance != 0) {
            return updateAnonymousArgs(this.mNativeInstance, str, str2, str3, format);
        }
        throw new RuntimeException("newInstance failed ");
    }

    public String genAnonymousAuthorization(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            return null;
        }
        if (this.mNativeInstance != 0) {
            return genAnonymousAuthorization(this.mNativeInstance, str, str2, str3);
        }
        throw new RuntimeException("newInstance failed ");
    }
}
