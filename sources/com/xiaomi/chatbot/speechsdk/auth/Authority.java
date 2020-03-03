package com.xiaomi.chatbot.speechsdk.auth;

import com.google.gson.annotations.SerializedName;
import com.xiaomi.chatbot.speechsdk.ErrorListener;
import com.xiaomi.chatbot.speechsdk.common.DeviceUuidFactory;
import com.xiaomi.chatbot.speechsdk.common.SpeechApp;
import com.xiaomi.chatbot.speechsdk.common.SpeechLog;
import com.xiaomi.chatbot.speechsdk.common.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Authority {
    private static final int INTERVAL = 1000;
    private static final String TAG = "Authority";
    private static final int TEN_INTERVAL = 10;
    private static final String authUrl = SpeechApp.getServerUrl();
    private static final String initPath = (authUrl + "/token/auth");
    private static Authority instance = null;
    private static final String refreshPath = (authUrl + "/token/refresh");
    private static final String refreshTokenFile = "refresh_token";
    /* access modifiers changed from: private */
    public static Token token = null;
    private String appId;
    private String deviceId;
    /* access modifiers changed from: private */
    public ErrorListener errorListener;

    public static Authority getInstance(String str, String str2, ErrorListener errorListener2) {
        if (instance == null) {
            instance = new Authority(str, str2, errorListener2);
        }
        return instance;
    }

    public static Authority getInstance() {
        return instance;
    }

    private class Token {
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("app_id")
        private String appId;
        /* access modifiers changed from: private */
        @SerializedName("expires_in")
        public Integer expiresIn;
        @SerializedName("refresh_token")
        private String refreshToken;
        private Long timestamp;

        private Token() {
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public void setAccessToken(String str) {
            this.accessToken = str;
        }

        public String getRefreshToken() {
            return this.refreshToken;
        }

        public void setRefreshToken(String str) {
            this.refreshToken = str;
        }

        public Integer getExpiresIn() {
            return this.expiresIn;
        }

        public void setExpiresIn(Integer num) {
            this.expiresIn = num;
        }

        public long getTimestamp() {
            return this.timestamp.longValue();
        }

        public void setTimestamp() {
            this.timestamp = Long.valueOf(System.currentTimeMillis());
        }

        public String toString() {
            return Utils.GSON.toJson((Object) this);
        }

        public String getAppId() {
            return this.appId;
        }
    }

    private class Response {
        private Integer code;
        private String description;
        /* access modifiers changed from: private */
        public Token result;

        private Response() {
        }

        public Integer getCode() {
            return this.code;
        }

        public void setCode(Integer num) {
            this.code = num;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public Token getToken() {
            return this.result;
        }

        public void setToken(Token token) {
            this.result = token;
        }

        public String toString() {
            return Utils.GSON.toJson((Object) this);
        }
    }

    private static class AuthCaller {
        private static final OkHttpClient client = new OkHttpClient();

        private AuthCaller() {
        }

        public static Response post(String str, Map<String, String> map) {
            FormBody.Builder builder = new FormBody.Builder();
            for (String next : map.keySet()) {
                builder.addEncoded(next, map.get(next));
            }
            Request build = new Request.Builder().addHeader("Content-Encoding", "UTF-8").addHeader("Content-Type", "application/json").url(str).post(builder.build()).build();
            SpeechLog.i(Authority.TAG, String.format("post %s", new Object[]{str}));
            try {
                okhttp3.Response execute = client.newCall(build).execute();
                if (!execute.isSuccessful()) {
                    SpeechLog.e(Authority.TAG, String.format("call %s failed! %s", new Object[]{build.toString(), execute.toString()}));
                    return null;
                }
                Response response = (Response) Utils.GSON.fromJson(execute.body().string(), Response.class);
                response.getToken().setTimestamp();
                SpeechLog.i(Authority.TAG, String.format("call %s, response=%s", new Object[]{build.toString(), execute.toString()}));
                return response;
            } catch (IOException e) {
                SpeechLog.printException(Authority.TAG, e);
                return null;
            }
        }
    }

    private Authority(String str, String str2, ErrorListener errorListener2) {
        if (str != null) {
            this.deviceId = str;
        } else {
            this.deviceId = new DeviceUuidFactory(SpeechApp.getContext()).getId();
        }
        this.appId = str2;
        this.errorListener = errorListener2;
        errorListener2.setError(false);
    }

    public void setup() {
        AnonymousClass1 r0 = new Thread() {
            public void run() {
                try {
                    Token unused = Authority.token = (Token) Utils.GSON.fromJson(Authority.this.getRefreshTokenFormFile(true), Token.class);
                    if (Authority.token != null) {
                        Authority.this.errorListener.setError(false);
                        if (Authority.this.checkToken()) {
                            Authority.this.errorListener.setError(true);
                            try {
                                SpeechLog.i(Authority.TAG, "refresh token");
                                Authority.this.callRefresh();
                            } catch (Exception e) {
                                SpeechLog.i(Authority.TAG, "e init token" + e.getMessage());
                                Authority.this.callInit();
                            }
                        }
                    } else {
                        SpeechLog.i(Authority.TAG, "init token");
                        Authority.this.callInit();
                    }
                    SpeechLog.i(Authority.TAG, Authority.token == null ? "null" : Authority.token.getAccessToken());
                } catch (Exception e2) {
                    Authority.this.errorListener.setError(true);
                    SpeechLog.printException(Authority.TAG, e2);
                    e2.printStackTrace();
                }
            }
        };
        r0.start();
        try {
            r0.join();
        } catch (InterruptedException e) {
            SpeechLog.printException(TAG, e);
        }
        new Thread() {
            public void run() {
                while (true) {
                    Utils.SleepCatchException(1000);
                    if (Authority.token == null || Authority.this.checkToken()) {
                        Authority.this.errorListener.setError(true);
                        try {
                            Authority.this.callRefresh();
                        } catch (Exception e) {
                            SpeechLog.printException(Authority.TAG, e);
                        }
                    }
                }
            }
        }.start();
    }

    /* access modifiers changed from: private */
    public boolean checkToken() {
        return ((System.currentTimeMillis() - token.getTimestamp()) / 1000) - ((long) token.expiresIn.intValue()) > 10;
    }

    /* access modifiers changed from: private */
    public void callInit() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("device_id", this.deviceId);
        hashMap.put("app_id", this.appId);
        updateToken(initPath, hashMap);
    }

    /* access modifiers changed from: private */
    public void callRefresh() throws Exception {
        if (token == null) {
            token = (Token) Utils.GSON.fromJson(getRefreshTokenFormFile(false), Token.class);
            if (token == null) {
                this.errorListener.setError(true);
                return;
            }
            this.errorListener.setError(false);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("device_id", this.deviceId);
        hashMap.put("app_id", this.appId);
        hashMap.put("token", token.getRefreshToken());
        SpeechLog.d(TAG, "call refresh. updata");
        updateToken(refreshPath, hashMap);
    }

    private void updateToken(String str, Map<String, String> map) throws Exception {
        Response post = AuthCaller.post(str, map);
        if (post == null || post.result == null) {
            this.errorListener.setError(true);
            SpeechLog.e(TAG, String.format("call url=%s", new Object[]{str}));
            throw new RuntimeException("Fatal error! Failed on initializing Auth!");
        }
        this.errorListener.setError(false);
        post.getToken().setTimestamp();
        token = post.getToken();
        if (token == null) {
            this.errorListener.setError(true);
        } else {
            this.errorListener.setError(false);
        }
        SpeechLog.i(TAG, String.format("call url=%s,reponse=%s", new Object[]{str, post}));
        updateRefreshToken(token);
    }

    /* access modifiers changed from: private */
    public String getRefreshTokenFormFile(boolean z) {
        return Utils.readAppFile(SpeechApp.getContext(), "refresh_token", z);
    }

    private void updateRefreshToken(Token token2) {
        Utils.writeAppFile(SpeechApp.getContext(), "refresh_token", token2.toString());
    }

    public String getToken() {
        if (token != null) {
            this.errorListener.setError(false);
            return token.getAccessToken();
        }
        this.errorListener.setError(true);
        return null;
    }

    public String getAuthHeader() {
        if (token != null) {
            this.errorListener.setError(false);
            return String.format("TP-TOKEN-V1 app_id:%s,access_token:%s", new Object[]{token.getAppId(), token.getAccessToken()});
        }
        this.errorListener.setError(true);
        return null;
    }

    public String getDeviceId() {
        return this.deviceId;
    }
}
