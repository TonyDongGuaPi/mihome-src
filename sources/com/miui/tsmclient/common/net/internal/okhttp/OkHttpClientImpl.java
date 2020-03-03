package com.miui.tsmclient.common.net.internal.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.AccountManagerFactory;
import com.miui.tsmclient.account.IAccountManager;
import com.miui.tsmclient.common.net.ErrorInfo;
import com.miui.tsmclient.common.net.IHttpClient;
import com.miui.tsmclient.common.net.RequestCallback;
import com.miui.tsmclient.common.net.host.Host;
import com.miui.tsmclient.common.net.request.BaseRequest;
import com.miui.tsmclient.util.EnvironmentConfig;
import com.miui.tsmclient.util.IOUtils;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.SecureRequest;
import com.xiaomi.accountsdk.utils.AESCoder;
import com.xiaomi.accountsdk.utils.CryptCoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpClientImpl implements IHttpClient {
    private static final int DEFAULT_IO_TIMEOUT_MILLISECOND = 60000;
    private static final int DEFAULT_TIMEOUT_MILLISECOND = 10000;
    private IAccountManager mAccountManager;
    /* access modifiers changed from: private */
    public ThreadLocalAuthInfo mAuthInfo = new ThreadLocalAuthInfo();
    private OkHttpClient mClient;
    private Context mContext;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());

    private class ThreadLocalAuthInfo extends ThreadLocal<AuthInfo> {
        private ThreadLocalAuthInfo() {
        }

        /* access modifiers changed from: protected */
        public AuthInfo initialValue() {
            return new AuthInfo();
        }

        public void setAccountInfo(AccountInfo accountInfo) {
            AuthInfo authInfo = (AuthInfo) get();
            AccountInfo unused = authInfo.mAccountInfo = accountInfo;
            if (accountInfo == null || TextUtils.isEmpty(accountInfo.getSecurity())) {
                CryptCoder unused2 = authInfo.mCoder = null;
            } else {
                CryptCoder unused3 = authInfo.mCoder = new AESCoder(accountInfo.getSecurity());
            }
        }

        public AccountInfo getAccountInfo() {
            return ((AuthInfo) get()).mAccountInfo;
        }

        public CryptCoder getCoder() {
            return ((AuthInfo) get()).mCoder;
        }
    }

    private static final class AuthInfo {
        /* access modifiers changed from: private */
        public AccountInfo mAccountInfo;
        /* access modifiers changed from: private */
        public CryptCoder mCoder;

        private AuthInfo() {
        }
    }

    public OkHttpClientImpl(Context context) {
        this.mContext = context;
        this.mAccountManager = AccountManagerFactory.createAccountManager();
        this.mClient = new OkHttpClient.Builder().cache(new Cache(context.getCacheDir(), 314572800)).addInterceptor(new TimeoutInterceptor()).addInterceptor(new RetryInterceptor(2)).addInterceptor(new CryptInterceptor()).addNetworkInterceptor(new CookieInterceptor()).addNetworkInterceptor(new PersistenceInterceptor()).build();
    }

    public <T> void enqueue(final BaseRequest<T> baseRequest) {
        this.mClient.newCall(getOkRequest(baseRequest)).enqueue(new Callback() {
            public void onFailure(Call call, final IOException iOException) {
                LogUtils.v("Callback onFailure:" + baseRequest.getUrl());
                final RequestCallback callback = baseRequest.getCallback();
                if (callback != null) {
                    OkHttpClientImpl.this.mHandler.post(new Runnable() {
                        public void run() {
                            callback.onFailed(new ErrorInfo(-1, iOException.getMessage()));
                        }
                    });
                }
            }

            public void onResponse(Call call, final Response response) throws IOException {
                LogUtils.v("Callback onResponse:" + baseRequest.getUrl() + "\n" + baseRequest.getResponse());
                final RequestCallback callback = baseRequest.getCallback();
                if (callback != null) {
                    OkHttpClientImpl.this.mHandler.post(new Runnable() {
                        public void run() {
                            if (!response.isSuccessful() || baseRequest.getResult() == null) {
                                callback.onFailed(new ErrorInfo(-1, response.message()));
                            } else {
                                callback.onResponse(baseRequest.getResult());
                            }
                        }
                    });
                }
            }
        });
    }

    public <T> com.miui.tsmclient.common.net.Response<T> execute(BaseRequest<T> baseRequest) throws IOException {
        Response execute = this.mClient.newCall(getOkRequest(baseRequest)).execute();
        if (execute != null) {
            try {
                execute.close();
            } catch (Exception e) {
                LogUtils.e("close response failed on execute", e);
            }
        }
        return baseRequest.getResponse();
    }

    public void cancel(BaseRequest baseRequest) {
        Dispatcher dispatcher = this.mClient.dispatcher();
        for (Call next : dispatcher.queuedCalls()) {
            if (baseRequest == next.request().tag()) {
                next.cancel();
            }
        }
        for (Call next2 : dispatcher.runningCalls()) {
            if (baseRequest == next2.request().tag()) {
                next2.cancel();
            }
        }
    }

    /* access modifiers changed from: private */
    public static BaseRequest<?> getBaseRequest(Request request) throws IOException {
        if (request != null) {
            Object tag = request.tag();
            if (tag instanceof BaseRequest) {
                return (BaseRequest) tag;
            }
        }
        throw new IOException("getBaseRequest failed");
    }

    /* access modifiers changed from: private */
    public static boolean isAuthRequired(Request request) throws IOException {
        return getBaseRequest(request).isAuth();
    }

    /* access modifiers changed from: private */
    public static boolean isCryptRequired(Request request) throws IOException {
        return getBaseRequest(request).isCrypt();
    }

    private Request getOkRequest(BaseRequest baseRequest) {
        return new Request.Builder().url(baseRequest.getUrl()).tag(baseRequest).build();
    }

    /* access modifiers changed from: private */
    public void doPreRetry() {
        AccountInfo accountInfo = this.mAuthInfo.getAccountInfo();
        this.mAccountManager.resetAccount(this.mContext, accountInfo == null ? null : accountInfo.getAuthToken());
        this.mAuthInfo.setAccountInfo((AccountInfo) null);
    }

    /* access modifiers changed from: private */
    public void loadAccountInfo(Host host) throws IOException {
        if (this.mAuthInfo.getAccountInfo() == null) {
            AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(this.mContext, host.getServiceId());
            if (loadAccountInfo != null) {
                this.mAuthInfo.setAccountInfo(loadAccountInfo);
                return;
            }
            throw new IOException("Getting account info failed");
        }
    }

    private static class TimeoutInterceptor implements Interceptor {
        private TimeoutInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            BaseRequest access$900 = OkHttpClientImpl.getBaseRequest(request);
            int connectTimeout = access$900.getConnectTimeout() >= 0 ? access$900.getConnectTimeout() : 10000;
            int i = 60000;
            int readTimeout = access$900.getReadTimeout() >= 0 ? access$900.getReadTimeout() : 60000;
            if (access$900.getWriteTimeout() >= 0) {
                i = access$900.getWriteTimeout();
            }
            return chain.withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS).withReadTimeout(readTimeout, TimeUnit.MILLISECONDS).withWriteTimeout(i, TimeUnit.MILLISECONDS).proceed(request);
        }
    }

    private class RetryInterceptor implements Interceptor {
        private final int maxRetry;

        RetryInterceptor(int i) {
            this.maxRetry = i;
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            int i = 1;
            if (OkHttpClientImpl.isAuthRequired(request)) {
                while (needRetry(i, proceed)) {
                    try {
                        proceed.close();
                    } catch (Exception e) {
                        LogUtils.e("close HTTP_UNAUTHORIZED response failed on RetryInterceptor", e);
                    }
                    i++;
                    OkHttpClientImpl.this.doPreRetry();
                    proceed = chain.proceed(request);
                }
            }
            LogUtils.t("RetryInterceptor requestCount:" + i);
            return proceed;
        }

        private boolean needRetry(int i, Response response) {
            return response != null && response.code() == 401 && i < this.maxRetry;
        }
    }

    private class CryptInterceptor implements Interceptor {
        private CryptInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request;
            String str;
            Request request2 = chain.request();
            BaseRequest access$900 = OkHttpClientImpl.getBaseRequest(request2);
            boolean access$1000 = OkHttpClientImpl.isAuthRequired(request2);
            boolean access$1200 = OkHttpClientImpl.isCryptRequired(request2);
            access$900.addExtraParams();
            Map<String, String> params = access$900.getParams();
            if (access$1000) {
                OkHttpClientImpl.this.loadAccountInfo(access$900.getHost());
                AccountInfo accountInfo = OkHttpClientImpl.this.mAuthInfo.getAccountInfo();
                String userId = accountInfo.getUserId();
                if (!TextUtils.isEmpty(userId)) {
                    params.put("userId", userId);
                    String ph = accountInfo.getPh();
                    if (!TextUtils.isEmpty(ph)) {
                        params.put(access$900.getHost().getServiceId() + "_ph", ph);
                    } else {
                        LogUtils.i("CryptInterceptor: ph is null");
                    }
                    if (!TextUtils.isEmpty(EnvironmentConfig.getClientId())) {
                        params.put("clientId", EnvironmentConfig.getClientId());
                        params.put("token", accountInfo.getAuthToken());
                    }
                    if (access$1200) {
                        try {
                            params = SecureRequest.encryptParams(access$900.getMethod() == 0 ? "GET" : "POST", access$900.getUrl(), params, accountInfo.getSecurity(), OkHttpClientImpl.this.mAuthInfo.getCoder());
                        } catch (CipherException e) {
                            throw new IOException(e);
                        }
                    }
                } else {
                    LogUtils.e("CryptInterceptor: userId is null");
                    throw new IOException("userId is null");
                }
            }
            if (access$900.getMethod() == 0) {
                HttpUrl.Builder newBuilder = request2.url().newBuilder();
                for (Map.Entry next : params.entrySet()) {
                    newBuilder.addQueryParameter((String) next.getKey(), (String) next.getValue());
                }
                request = request2.newBuilder().url(newBuilder.build()).build();
            } else {
                FormBody.Builder builder = new FormBody.Builder();
                for (Map.Entry next2 : params.entrySet()) {
                    builder.add((String) next2.getKey(), (String) next2.getValue());
                }
                request = request2.newBuilder().post(builder.build()).build();
            }
            Response proceed = chain.proceed(request);
            StringBuilder sb = new StringBuilder();
            sb.append(request.method());
            sb.append(": ");
            sb.append(access$900.getUrl());
            sb.append("\nResponseStatus: ");
            if (proceed == null) {
                str = null;
            } else {
                str = proceed.toString();
            }
            sb.append(str);
            LogUtils.t(sb.toString());
            return processResponse(request, proceed);
        }

        private Response processResponse(Request request, Response response) throws IOException {
            Throwable th;
            InputStream inputStream;
            if (response == null || !response.isSuccessful()) {
                return response;
            }
            BaseRequest access$900 = OkHttpClientImpl.getBaseRequest(request);
            ResponseBody body = response.body();
            if (body == null) {
                return response;
            }
            if (access$900.isStringResponse()) {
                String string = body.string();
                if (OkHttpClientImpl.isCryptRequired(request)) {
                    try {
                        string = ((AuthInfo) OkHttpClientImpl.this.mAuthInfo.get()).mCoder.decrypt(string);
                    } catch (CipherException e) {
                        LogUtils.e("error occurred on CryptInterceptor", e);
                        throw new IOException(e);
                    }
                }
                LogUtils.t("bodyStr: " + string);
                LogUtils.t("----------");
                access$900.setResponse(string);
            } else {
                try {
                    LogUtils.t("start parsing inputStream");
                    inputStream = body.byteStream();
                    if (inputStream != null) {
                        try {
                            access$900.setResponse(inputStream, body.contentLength());
                            IOUtils.closeQuietly(inputStream);
                        } catch (Throwable th2) {
                            th = th2;
                            IOUtils.closeQuietly(inputStream);
                            throw th;
                        }
                    } else {
                        LogUtils.e("byteStream is null");
                        throw new IOException("byteStream failed");
                    }
                } catch (Throwable th3) {
                    inputStream = null;
                    th = th3;
                    IOUtils.closeQuietly(inputStream);
                    throw th;
                }
            }
            return response;
        }
    }

    private class CookieInterceptor implements Interceptor {
        private CookieInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (OkHttpClientImpl.isAuthRequired(request)) {
                BaseRequest access$900 = OkHttpClientImpl.getBaseRequest(request);
                AccountInfo access$200 = ((AuthInfo) OkHttpClientImpl.this.mAuthInfo.get()).mAccountInfo;
                access$900.addCookie("userId", access$200.getUserId());
                access$900.addCookie("serviceToken", access$200.getServiceToken());
                access$900.addCookie(access$900.getHost().getServiceId() + "_ph", access$200.getPh());
                request = request.newBuilder().header("Cookie", StringUtils.join(access$900.getCookies(), "; ")).build();
            }
            return chain.proceed(request);
        }
    }

    private class PersistenceInterceptor implements Interceptor {
        private PersistenceInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            BaseRequest access$900 = OkHttpClientImpl.getBaseRequest(request);
            Response proceed = chain.proceed(request);
            return (proceed == null || !access$900.isPersistence()) ? proceed : proceed.newBuilder().header("Cache-Control", "immutable").build();
        }
    }
}
