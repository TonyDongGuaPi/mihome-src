package cn.fraudmetrix.octopus.aspirit.d.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.b.e;
import cn.fraudmetrix.octopus.aspirit.b.f;
import cn.fraudmetrix.octopus.aspirit.c.c;
import cn.fraudmetrix.octopus.aspirit.e.a;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.d;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import com.alibaba.fastjson.JSON;
import com.unionpay.tsmservice.data.ResultCode;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class a implements f {

    /* renamed from: a  reason: collision with root package name */
    private OkHttpClient f632a = null;
    /* access modifiers changed from: private */
    public Handler b;

    /* renamed from: cn.fraudmetrix.octopus.aspirit.d.a.a$a  reason: collision with other inner class name */
    abstract class C0020a<IN> implements e<Response, IN>, Callback {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public cn.fraudmetrix.octopus.aspirit.b.b f635a;
        private cn.fraudmetrix.octopus.aspirit.b.a c;

        public C0020a(a aVar, cn.fraudmetrix.octopus.aspirit.b.b bVar) {
            this(bVar, (cn.fraudmetrix.octopus.aspirit.b.a) null);
        }

        public C0020a(cn.fraudmetrix.octopus.aspirit.b.b bVar, cn.fraudmetrix.octopus.aspirit.b.a aVar) {
            this.f635a = (cn.fraudmetrix.octopus.aspirit.b.b) a.C0021a.a((Class<?>) cn.fraudmetrix.octopus.aspirit.b.b.class, (Object) bVar);
            this.c = (cn.fraudmetrix.octopus.aspirit.b.a) a.C0021a.a((Class<?>) cn.fraudmetrix.octopus.aspirit.b.a.class, (Object) aVar);
        }

        /* access modifiers changed from: private */
        public void a(Call call, Object obj) {
            try {
                this.f635a.b(obj);
                this.f635a.b();
                this.c.a(b(call), "0", (String) null);
            } catch (Exception e) {
                e.printStackTrace();
                a(call, (Throwable) e);
            }
        }

        private void a(Call call, final Throwable th) {
            this.c.a(b(call), ResultCode.ERROR_INTERFACE_GET_APP_DETAIL, th != null ? th.getLocalizedMessage() : "");
            a.this.b.post(new Runnable() {
                public void run() {
                    C0020a.this.f635a.a(th);
                    C0020a.this.f635a.b();
                }
            });
        }

        private String b(Call call) {
            return call.request().url().uri().toString();
        }

        public void a(Call call) {
            a.this.b.post(new Runnable() {
                public void run() {
                    C0020a.this.f635a.a();
                }
            });
            this.c.a(b(call), (String) null);
        }

        public void onFailure(Call call, IOException iOException) {
            a(call, (Throwable) iOException);
        }

        public void onResponse(final Call call, Response response) {
            d.a((Object) response, "response is null");
            if (!response.isSuccessful()) {
                a(call, (Throwable) new Exception(response.code() + ""));
                return;
            }
            try {
                final Object a2 = this.f635a.a(a(response));
                a.this.b.post(new Runnable() {
                    public void run() {
                        C0020a.this.a(call, a2);
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
                a(call, th);
            }
        }
    }

    class b extends C0020a<String> {
        public b(cn.fraudmetrix.octopus.aspirit.b.b bVar, cn.fraudmetrix.octopus.aspirit.b.a aVar) {
            super(bVar, aVar);
        }

        public String a(Response response) {
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                RuntimeException runtimeException = new RuntimeException(e.getMessage());
                runtimeException.initCause(runtimeException);
                throw runtimeException;
            }
        }
    }

    private String a(@NonNull String str) {
        d.a(str, "path is empty");
        return String.format("%s%s?partner_code=%s&partner_key=%s", new Object[]{OctopusManager.b().g(), str, OctopusManager.b().d(), OctopusManager.b().e()});
    }

    private String a(Map<String, Object> map) {
        if (map == null) {
            return "{}";
        }
        if (!map.containsKey(OctopusConstants.v) || ((Boolean) map.get(OctopusConstants.v)).booleanValue()) {
            map.put("app_version", OctopusManager.b().h());
            map.put("device_type", com.alipay.android.phone.a.a.a.f813a);
            map.put("mohe_code", OctopusManager.b().d());
            map.put("mohe_key", OctopusManager.b().e());
        }
        if (map.containsKey(OctopusConstants.w) && ((Boolean) map.get(OctopusConstants.w)).booleanValue()) {
            OctopusManager b2 = OctopusManager.b();
            if (b2.f644a != null) {
                if (b2.f644a.identityCode != null) {
                    map.put(OctopusConstants.F, b2.f644a.identityCode);
                }
                String str = b2.f644a.realName;
                if (!TextUtils.isEmpty(str)) {
                    map.put(OctopusConstants.D, j.b(str));
                }
                if (b2.f644a.mobile != null) {
                    map.put(OctopusConstants.E, b2.f644a.mobile);
                }
            }
        }
        map.remove(OctopusConstants.w);
        map.remove(OctopusConstants.v);
        return JSON.toJSONString(map);
    }

    private Call a(@NonNull String str, String str2) {
        d.a(str, "url is empty");
        if (TextUtils.isEmpty(str2)) {
            str2 = "{}";
        }
        return a().newCall(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str2)).build());
    }

    private OkHttpClient a() {
        if (this.f632a == null) {
            this.f632a = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).addInterceptor(b()).build();
            this.b = new Handler(Looper.getMainLooper());
        }
        return this.f632a;
    }

    private Interceptor b() {
        return new Interceptor() {
            public Response intercept(@NonNull Interceptor.Chain chain) {
                return chain.proceed(chain.request().newBuilder().header("Accept-Encoding", "compress, gzip").header("PARAMS-PARTNER-CODE", OctopusManager.b().d()).header("PARAMS-EVENT-ID", "2222").header("Device-Type", com.alipay.android.phone.a.a.a.f813a).header("App-Version", OctopusManager.b().h()).build());
            }
        };
    }

    public void a(@NonNull String str, String str2, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        d(a(str), str2, bVar);
    }

    public void a(@NonNull String str, Map<String, Object> map, cn.fraudmetrix.octopus.aspirit.b.b<InputStream, ?> bVar) {
        d.a(str, "url is empty");
        HttpUrl.Builder newBuilder = HttpUrl.parse(str).newBuilder();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                newBuilder.addQueryParameter((String) next.getKey(), next.getValue().toString());
            }
        }
        Call newCall = a().newCall(new Request.Builder().url(newBuilder.build()).build());
        AnonymousClass2 r4 = new C0020a<InputStream>(bVar) {
            public InputStream a(Response response) {
                return response.body().byteStream();
            }
        };
        r4.a(newCall);
        newCall.enqueue(r4);
    }

    public void b(@NonNull String str, String str2, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        Call a2 = a(str, str2);
        b bVar2 = new b(bVar, c.d());
        bVar2.a(a2);
        a2.enqueue(bVar2);
    }

    public void b(@NonNull String str, Map<String, Object> map, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        c(str, a(map), bVar);
    }

    public void c(@NonNull String str, String str2, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        b(a(str), str2, bVar);
    }

    public void c(@NonNull String str, Map<String, Object> map, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        a(str, a(map), bVar);
    }

    public void d(@NonNull String str, String str2, cn.fraudmetrix.octopus.aspirit.b.b<String, ?> bVar) {
        Call a2 = a(str, str2);
        b bVar2 = new b(bVar, c.d());
        try {
            bVar2.a(a2);
            bVar2.onResponse(a2, a2.execute());
        } catch (IOException e) {
            e.printStackTrace();
            bVar2.onFailure(a2, e);
        }
    }
}
