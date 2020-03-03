package com.mics.network;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GoHttp {
    private static final HashMap<String, GoCallback> e = new HashMap<>();
    private static final Gson f = new Gson();

    /* renamed from: a  reason: collision with root package name */
    private OkHttpClient f7763a;
    private OkHttpClient b;
    private String c;
    private final GoHandler d = new GoHandler(Looper.getMainLooper());
    private GoInterceptor g;

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static final GoHttp f7766a = new GoHttp();

        private Holder() {
        }
    }

    protected GoHttp() {
        this.d.a(e);
        this.d.a(f);
    }

    public static GoHttp a() {
        return Holder.f7766a;
    }

    public static GoHttp b() {
        return a(new OkHttpClient.Builder());
    }

    public static GoHttp a(Context context, String str, String str2) {
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(str2);
        } catch (IOException e2) {
            e2.printStackTrace();
            inputStream = null;
        }
        return a(str, inputStream);
    }

    public static GoHttp a(String str, InputStream inputStream) {
        a().c = str;
        try {
            X509TrustManager a2 = a().a(inputStream);
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new TrustManager[]{a2}, (SecureRandom) null);
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            a().f7763a = new OkHttpClient.Builder().build();
            return a(new OkHttpClient.Builder().sslSocketFactory(socketFactory, a2));
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static GoHttp a(OkHttpClient.Builder builder) {
        a().b = builder.build();
        return a();
    }

    public static GoHttp a(OkHttpClient okHttpClient) {
        a().b = okHttpClient;
        return a();
    }

    public static void a(GoInterceptor goInterceptor) {
        a().g = goInterceptor;
    }

    public static OkHttpClient a(Request request) {
        return a().d(request);
    }

    private X509TrustManager a(InputStream inputStream) throws GeneralSecurityException {
        Collection<? extends Certificate> generateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(inputStream);
        if (!generateCertificates.isEmpty()) {
            char[] charArray = "password".toCharArray();
            KeyStore a2 = a(charArray);
            int i = 0;
            for (Certificate certificateEntry : generateCertificates) {
                int i2 = i + 1;
                a2.setCertificateEntry(Integer.toString(i), certificateEntry);
                i = i2;
            }
            KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm()).init(a2, charArray);
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init(a2);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        throw new IllegalArgumentException("expected non-empty set of trusted certificates");
    }

    private KeyStore a(char[] cArr) throws GeneralSecurityException {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load((InputStream) null, cArr);
            return instance;
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public static void a(String str, GoCallback goCallback) {
        a(str, str + System.currentTimeMillis(), goCallback);
    }

    public static void a(String str, HashMap<String, String> hashMap, GoCallback goCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry next : hashMap.entrySet()) {
            builder.add((String) next.getKey(), (String) next.getValue());
        }
        a(str, builder, goCallback);
    }

    public static void a(String str, FormBody.Builder builder, GoCallback goCallback) {
        Request.Builder builder2 = new Request.Builder();
        if (builder != null) {
            builder2.post(builder.build());
        }
        builder2.url(str);
        a().b(builder2.build(), str + System.currentTimeMillis(), goCallback);
    }

    public static void a(String str, String str2, GoCallback goCallback) {
        a().b(str, str2, goCallback);
    }

    public static void a(Request request, String str, GoCallback goCallback) {
        a().b(request, str, goCallback);
    }

    public static String b(Request request) {
        return c(request);
    }

    public static String a(String str) {
        return b(new Request.Builder().url(str).build());
    }

    private void b(String str, String str2, GoCallback goCallback) {
        b(new Request.Builder().url(str).build(), str2, goCallback);
    }

    private void b(Request request, String str, GoCallback goCallback) {
        if (!(goCallback == null || str == null)) {
            e.put(str, goCallback);
        }
        Call newCall = a().d(request).newCall(request);
        CallbackImpl callbackImpl = new CallbackImpl(str);
        callbackImpl.a(this.d);
        newCall.enqueue(callbackImpl);
    }

    private static String c(Request request) {
        try {
            Response execute = a().d(request).newCall(request).execute();
            ResponseBody body = execute.body();
            if (execute.isSuccessful() && body != null) {
                return body.string();
            }
            if (body == null) {
                return "请求失败";
            }
            body.close();
            return execute.toString();
        } catch (IOException e2) {
            return e2.getMessage();
        }
    }

    private OkHttpClient d(Request request) {
        URL url = request.url().url();
        if (a().f7763a == null || !request.isHttps() || url.toString().contains(this.c)) {
            return a().b;
        }
        return a().f7763a;
    }

    private static class CallbackImpl implements Callback {

        /* renamed from: a  reason: collision with root package name */
        private String f7764a;
        private GoHandler b;

        CallbackImpl(String str) {
            this.f7764a = str;
        }

        public void a(GoHandler goHandler) {
            this.b = goHandler;
        }

        public void onFailure(Call call, IOException iOException) {
            this.b.sendMessage(this.b.obtainMessage(-1, new ResponseInfo(this.f7764a, iOException.getMessage())));
        }

        public void onResponse(Call call, Response response) throws IOException {
            this.b.sendMessage(this.b.obtainMessage(200, new ResponseInfo(this.f7764a, response.body().string())));
        }
    }

    private static class ResponseInfo {

        /* renamed from: a  reason: collision with root package name */
        private String f7767a;
        private String b;

        ResponseInfo(String str, String str2) {
            this.f7767a = str;
            this.b = str2;
        }

        public String a() {
            return this.f7767a;
        }

        public String b() {
            return this.b;
        }
    }

    private static class GoHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        HashMap<String, GoCallback> f7765a;
        Gson b;

        GoHandler(Looper looper) {
            super(looper);
        }

        public void a(HashMap<String, GoCallback> hashMap) {
            this.f7765a = hashMap;
        }

        public void a(Gson gson) {
            this.b = gson;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            ResponseInfo responseInfo = (ResponseInfo) message.obj;
            String a2 = responseInfo.a();
            String b2 = responseInfo.b();
            GoCallback goCallback = this.f7765a.get(a2);
            if (goCallback != null) {
                Class a3 = GoHttp.b((Object) goCallback);
                Object obj = null;
                if (a3 != null) {
                    try {
                        obj = this.b.fromJson(b2, a3);
                        if (obj == null) {
                            throw new RuntimeException("Parse Json Exception.");
                        }
                    } catch (Exception e) {
                        obj = new GoFailure();
                        GoFailure goFailure = (GoFailure) obj;
                        goFailure.a(i);
                        String message2 = i == 200 ? e.getMessage() : "请检查网络连接是否正常";
                        goFailure.a(b2);
                        goFailure.b(message2);
                        i = -1;
                    }
                }
                if (i != 200 || GoHttp.c(b2, obj)) {
                    goCallback.a(b2, GoHttp.d(b2, obj));
                } else {
                    goCallback.a(b2, obj);
                }
                this.f7765a.remove(a2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(String str, Object obj) {
        if (a().g != null) {
            return a().g.a(str, obj);
        }
        return obj instanceof GoFailure;
    }

    /* access modifiers changed from: private */
    public static GoFailure d(String str, Object obj) {
        if (a().g != null) {
            return a().g.b(str, obj);
        }
        if (obj instanceof GoFailure) {
            return (GoFailure) obj;
        }
        return new GoFailure();
    }

    /* access modifiers changed from: private */
    public static Class b(Object obj) {
        Type type = obj.getClass().getGenericInterfaces()[0];
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return null;
    }
}
