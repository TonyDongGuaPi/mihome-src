package com.xiaomi.smarthome.aitraining;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import com.alipay.sdk.util.i;
import com.taobao.weex.common.Constants;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.File;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONObject;

public class UploadFileHelper {

    /* renamed from: a  reason: collision with root package name */
    public static String f13686a = "https://i.ai.mi.com/file";
    public static final String b = "UploadFileManagerNew";
    private static final JoinPoint.StaticPart f = null;
    /* access modifiers changed from: private */
    public String c = "";
    private Context d;
    private OkHttpClient e;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return UploadFileHelper.a((UploadFileHelper) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    public interface ProgressCallback {
        void a(long j, long j2);
    }

    private static void a() {
        Factory factory = new Factory("UploadFileHelper.java", UploadFileHelper.class);
        f = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 205);
    }

    static {
        a();
    }

    public UploadFileHelper() {
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(new AddCookiesInterceptor());
        JoinPoint a2 = Factory.a(f, (Object) this, (Object) addInterceptor);
        this.e = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, addInterceptor, a2}).linkClosureAndJoinPoint(4112));
    }

    public void a(final WebView webView, Context context, File file, long j, String[] strArr, String[] strArr2) {
        this.d = context;
        if (!a(strArr, strArr2)) {
            LogUtil.b(b, "NO Ready!@!!1");
            return;
        }
        final XQProgressHorizontalDialog xQProgressHorizontalDialog = new XQProgressHorizontalDialog(context);
        xQProgressHorizontalDialog.setCancelable(false);
        xQProgressHorizontalDialog.show();
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(MultipartBody.create(MediaType.parse("audio/mp3"), file), new ProgressCallback() {
            public void a(long j, long j2) {
                final long j3 = j2;
                final long j4 = j;
                webView.post(new Runnable() {
                    public void run() {
                        xQProgressHorizontalDialog.a((int) j3, (int) (((float) j4) * 0.99f));
                    }
                });
            }
        });
        MultipartBody.create(MediaType.parse("text/plain"), XmPluginHostApi.instance().getAccountId());
        MultipartBody.create(MediaType.parse("text/plain"), strArr2[2]);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart(Constants.Name.PREFIX, XmPluginHostApi.instance().getAccountId());
        builder.addFormDataPart("i.ai.mi.com_ph", strArr2[2]);
        builder.addFormDataPart("file", file.getName(), progressRequestBody);
        final WebView webView2 = webView;
        final long j2 = j;
        final File file2 = file;
        this.e.newCall(new Request.Builder().url(f13686a).post(builder.build()).build()).enqueue(new Callback() {
            public void onFailure(Call call, final IOException iOException) {
                webView2.post(new Runnable() {
                    public void run() {
                        xQProgressHorizontalDialog.dismiss();
                        LogUtil.b(UploadFileHelper.b, Log.getStackTraceString(iOException));
                    }
                });
            }

            public void onResponse(Call call, final Response response) throws IOException {
                webView2.post(new Runnable() {
                    public void run() {
                        xQProgressHorizontalDialog.dismiss();
                        try {
                            ResponseBody body = response.body();
                            if (body != null) {
                                String string = body.string();
                                JSONObject jSONObject = new JSONObject(string);
                                jSONObject.put("fileDuration", j2);
                                string = jSONObject.toString();
                                String str = "saveAudio('', JSON.stringify(" + string + "))";
                                webView2.loadUrl("javascript:" + str);
                                LogUtil.c(UploadFileHelper.b, str);
                                file2.delete();
                                response.close();
                            }
                        } catch (Exception e) {
                            LogUtil.b(UploadFileHelper.b, Log.getStackTraceString(e));
                        } catch (Throwable th) {
                            LogUtil.b(UploadFileHelper.b, Log.getStackTraceString(th));
                        }
                    }
                });
            }
        });
    }

    private boolean a(String[] strArr, String[] strArr2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            sb.append("=");
            sb.append(strArr2[i]);
            sb.append(i.b);
        }
        this.c = sb.toString();
        return true;
    }

    public class ProgressRequestBody extends RequestBody {
        private final RequestBody b;
        /* access modifiers changed from: private */
        public ProgressCallback c;
        private BufferedSink d;

        public ProgressRequestBody(RequestBody requestBody, ProgressCallback progressCallback) {
            this.b = requestBody;
            this.c = progressCallback;
        }

        public long contentLength() throws IOException {
            return this.b.contentLength();
        }

        public MediaType contentType() {
            return this.b.contentType();
        }

        public void writeTo(BufferedSink bufferedSink) throws IOException {
            if (this.d == null) {
                this.d = Okio.buffer(a((Sink) bufferedSink));
            }
            this.b.writeTo(this.d);
            this.d.flush();
        }

        private Sink a(Sink sink) {
            return new ForwardingSink(sink) {

                /* renamed from: a  reason: collision with root package name */
                long f13694a = 0;
                long b = 0;

                public void write(Buffer buffer, long j) throws IOException {
                    super.write(buffer, j);
                    if (ProgressRequestBody.this.c != null) {
                        if (this.b == 0) {
                            this.b = ProgressRequestBody.this.contentLength();
                        }
                        this.f13694a += j;
                        ProgressRequestBody.this.c.a(this.f13694a, this.b);
                    }
                }
            };
        }
    }

    public class AddCookiesInterceptor implements Interceptor {
        private List<String> b;

        public AddCookiesInterceptor() {
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder newBuilder = chain.request().newBuilder();
            newBuilder.addHeader("Cookie", UploadFileHelper.this.c);
            if (this.b != null) {
                for (String str : this.b) {
                    newBuilder.addHeader("Cookie", str.toString());
                }
            }
            Response proceed = chain.proceed(newBuilder.build());
            this.b = proceed.headers("Set-Cookie");
            return proceed;
        }
    }

    static final OkHttpClient a(UploadFileHelper uploadFileHelper, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }
}
