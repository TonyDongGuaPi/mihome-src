package com.xiaomi.smarthome.audiorecord;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.RecordButton;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.internal.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioRecordActivity extends BaseActivity {
    private static final int f = 1;
    private static final int g = 2;
    private static final int j = 1;
    private static final JoinPoint.StaticPart k = null;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RecordButton f13756a;
    /* access modifiers changed from: private */
    public TextView b;
    /* access modifiers changed from: private */
    public TextView c;
    /* access modifiers changed from: private */
    public OkHttpClient d;
    /* access modifiers changed from: private */
    public TextView e;
    /* access modifiers changed from: private */
    public Handler h = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    if (!TextUtils.isEmpty(str)) {
                        TextView access$000 = AudioRecordActivity.this.e;
                        access$000.setText(AudioRecordActivity.this.e.getText() + str + "\n\n");
                        return;
                    }
                    return;
                case 2:
                    AudioRecordActivity.this.c();
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public String i = null;
    XQProgressDialog mProcessDialog;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return AudioRecordActivity.build_aroundBody0((AudioRecordActivity) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    static {
        d();
    }

    private static void d() {
        Factory factory = new Factory("AudioRecordActivity.java", AudioRecordActivity.class);
        k = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 148);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_audio_record);
        a();
        b();
    }

    private void a() {
        this.f13756a = (RecordButton) findViewById(R.id.record_btn);
        this.b = (TextView) findViewById(R.id.send_tv);
        this.c = (TextView) findViewById(R.id.cancel_tv);
        this.e = (TextView) findViewById(R.id.log_tv);
        this.e.setMovementMethod(new ScrollingMovementMethod());
        this.f13756a.setRecordListener(new RecordButton.RecordListener() {
            public void a(String str, int i) {
                AudioRecordActivity.this.f13756a.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
                AudioRecordActivity.this.f13756a.invalidate();
                AudioRecordActivity.this.b(str);
            }

            public void a() {
                AudioRecordActivity.this.b.setVisibility(8);
                AudioRecordActivity.this.c.setVisibility(8);
                AudioRecordActivity.this.f13756a.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
                AudioRecordActivity.this.f13756a.invalidate();
            }

            public void b() {
                AudioRecordActivity.this.b.setVisibility(0);
                AudioRecordActivity.this.c.setVisibility(8);
                AudioRecordActivity.this.f13756a.setBackgroundResource(R.drawable.kuailian_progress_filled);
                AudioRecordActivity.this.f13756a.invalidate();
            }

            public void a(boolean z) {
                if (z) {
                    AudioRecordActivity.this.b.setVisibility(0);
                    AudioRecordActivity.this.c.setVisibility(8);
                    return;
                }
                AudioRecordActivity.this.b.setVisibility(8);
                AudioRecordActivity.this.c.setVisibility(0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void b() {
        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).cipherSuites(CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA).supportsTlsExtensions(true).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(build);
        OkHttpClient.Builder connectionSpecs = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(60, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).connectionSpecs(arrayList);
        JoinPoint a2 = Factory.a(k, (Object) this, (Object) connectionSpecs);
        this.d = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, connectionSpecs, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient build_aroundBody0(AudioRecordActivity audioRecordActivity, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    private static class VoiceUploadUrl {

        /* renamed from: a  reason: collision with root package name */
        String f13767a;
        String b;

        private VoiceUploadUrl() {
        }

        public static VoiceUploadUrl a(String str, JSONObject jSONObject) {
            VoiceUploadUrl voiceUploadUrl = new VoiceUploadUrl();
            if (jSONObject.isNull(str)) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject.isNull("url")) {
                return null;
            }
            if (!optJSONObject.isNull("obj_name")) {
                voiceUploadUrl.b = optJSONObject.optString("obj_name");
            }
            voiceUploadUrl.f13767a = optJSONObject.optString("url");
            return voiceUploadUrl;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.h.sendMessage(this.h.obtainMessage(1, str));
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        try {
            this.e.setText("");
            this.i = null;
            this.mProcessDialog = new XQProgressDialog(this);
            this.mProcessDialog.setCancelable(true);
            this.mProcessDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AudioRecordActivity.this.h.removeCallbacksAndMessages((Object) null);
                }
            });
            this.mProcessDialog.setMessage("Pls wait, uploading");
            this.mProcessDialog.show();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constants.Name.SUFFIX, "1");
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a((Context) this, new NetRequest.Builder().a("POST").b("/voicectrl/gen_voice_upload_url").b((List<KeyValuePair>) arrayList).a(), new JsonParser<VoiceUploadUrl>() {
                /* renamed from: a */
                public VoiceUploadUrl parse(JSONObject jSONObject) throws JSONException {
                    return VoiceUploadUrl.a("1", jSONObject);
                }
            }, Crypto.RC4, new AsyncCallback<VoiceUploadUrl, Error>() {
                /* renamed from: a */
                public void onSuccess(final VoiceUploadUrl voiceUploadUrl) {
                    if (AudioRecordActivity.this.isValid()) {
                        AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                        audioRecordActivity.a("gen_voice_upload_url 调用成功 url=" + voiceUploadUrl.f13767a + ",object_name=" + voiceUploadUrl.b);
                        Request build = new Request.Builder().url(voiceUploadUrl.f13767a).put(RequestBody.create(MediaType.parse(""), new File(str))).build();
                        if (voiceUploadUrl.f13767a.startsWith("https")) {
                            new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).cipherSuites(CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA).supportsTlsExtensions(true).build();
                        }
                        AudioRecordActivity.this.d.newCall(build).enqueue(new Callback() {
                            public void onFailure(Call call, IOException iOException) {
                                if (AudioRecordActivity.this.isValid()) {
                                    AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                                    ToastUtil.a(audioRecordActivity, "upload fail " + iOException.getMessage());
                                    AudioRecordActivity audioRecordActivity2 = AudioRecordActivity.this;
                                    audioRecordActivity2.a("上传文件失败 " + iOException.getMessage());
                                    AudioRecordActivity.this.mProcessDialog.dismiss();
                                    if (iOException != null) {
                                        iOException.printStackTrace();
                                    }
                                }
                            }

                            public void onResponse(Call call, Response response) throws IOException {
                                if (AudioRecordActivity.this.isValid()) {
                                    if (!response.isSuccessful()) {
                                        AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                                        audioRecordActivity.a("上传文件失败 " + response.code() + " " + response.message());
                                        AudioRecordActivity audioRecordActivity2 = AudioRecordActivity.this;
                                        ToastUtil.a(audioRecordActivity2, "upload fail " + response.code() + " " + response.message());
                                        return;
                                    }
                                    AudioRecordActivity audioRecordActivity3 = AudioRecordActivity.this;
                                    audioRecordActivity3.a("上传文件成功 " + response.code() + " " + response.message());
                                    ToastUtil.a(AudioRecordActivity.this, "upload success");
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put("voice_src", "2");
                                        jSONObject.put("voice_url", voiceUploadUrl.b);
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
                                        CoreApi.a().a((Context) AudioRecordActivity.this, new NetRequest.Builder().a("POST").b("/voicectrl/query").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                                            /* renamed from: a */
                                            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                                                return jSONObject;
                                            }
                                        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(JSONObject jSONObject) {
                                                AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                                                audioRecordActivity.a("发起语⾳音控制成功 " + jSONObject.toString());
                                                try {
                                                    if (jSONObject.isNull("sid")) {
                                                        AudioRecordActivity.this.mProcessDialog.dismiss();
                                                        return;
                                                    }
                                                    String unused = AudioRecordActivity.this.i = jSONObject.optString("sid");
                                                    AudioRecordActivity.this.h.sendEmptyMessage(2);
                                                } catch (Exception e) {
                                                    AudioRecordActivity.this.mProcessDialog.dismiss();
                                                    e.printStackTrace();
                                                }
                                            }

                                            public void onFailure(Error error) {
                                                String str;
                                                AudioRecordActivity.this.mProcessDialog.dismiss();
                                                AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("发起语⾳音控制失败 ");
                                                if (error == null) {
                                                    str = "null";
                                                } else {
                                                    str = error.a() + " " + error.b();
                                                }
                                                sb.append(str);
                                                audioRecordActivity.a(sb.toString());
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }

                public void onFailure(Error error) {
                    String str;
                    String str2;
                    if (AudioRecordActivity.this.isValid()) {
                        AudioRecordActivity.this.mProcessDialog.dismiss();
                        AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append("上传文件失败 ");
                        if (error == null) {
                            str = "null";
                        } else {
                            str = error.a() + " " + error.b();
                        }
                        sb.append(str);
                        audioRecordActivity.a(sb.toString());
                        AudioRecordActivity audioRecordActivity2 = AudioRecordActivity.this;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("upload onFail ");
                        if (error == null) {
                            str2 = "null";
                        } else {
                            str2 = error.a() + " " + error.b();
                        }
                        sb2.append(str2);
                        ToastUtil.a(audioRecordActivity2, sb2.toString());
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mProcessDialog.dismiss();
            a("上传文件失败 Exception　" + e2.getMessage());
            ToastUtil.a(this, "Exception " + e2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (TextUtils.isEmpty(this.i)) {
            a("queryVoiceCtrlReply sid is empty");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", "sid");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a((Context) this, new NetRequest.Builder().a("POST").b("/voicectrl/reply").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (AudioRecordActivity.this.isValid()) {
                    AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("获取语⾳音控制结果成功 ");
                    sb.append(jSONObject == null ? "" : jSONObject.toString());
                    audioRecordActivity.a(sb.toString());
                    if (jSONObject.isNull("status")) {
                        AudioRecordActivity.this.mProcessDialog.dismiss();
                    } else if (jSONObject.optInt("status") == 1) {
                        AudioRecordActivity.this.h.sendEmptyMessageDelayed(2, 3000);
                    } else {
                        AudioRecordActivity.this.mProcessDialog.dismiss();
                    }
                }
            }

            public void onFailure(Error error) {
                String str;
                if (AudioRecordActivity.this.isValid()) {
                    AudioRecordActivity audioRecordActivity = AudioRecordActivity.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append("获取语⾳音控制结果失败 ");
                    if (error == null) {
                        str = "null";
                    } else {
                        str = error.a() + " " + error.b();
                    }
                    sb.append(str);
                    audioRecordActivity.a(sb.toString());
                    AudioRecordActivity.this.mProcessDialog.dismiss();
                }
            }
        });
    }
}
