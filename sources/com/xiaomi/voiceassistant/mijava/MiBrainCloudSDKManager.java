package com.xiaomi.voiceassistant.mijava;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.utils.TbsLog;
import com.xiaomi.ai.AsrListener;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.ai.SpeechEngine;
import com.xiaomi.ai.SpeechError;
import com.xiaomi.ai.SpeechResult;
import com.xiaomi.ai.TtsRequest;
import java.io.ByteArrayOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class MiBrainCloudSDKManager implements AsrListener {
    private static final int A = 2;
    private static final int B = 3;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = 8;
    public static final int i = 9;
    private static final String k = "2882303761517406057";
    private static final String l = "5571740645057";
    private static final String m = "2882303761517406028";
    private static final String n = "5251740626028";
    private static final String o = "MiBrainCloudSDKManager";
    private static MiBrainCloudSDKManager r = null;
    private static final int u = 10;
    private static final int v = 11;
    private static final int w = 12;
    private static final int x = 13;
    private static final int y = 0;
    private static final int z = 1;
    /* access modifiers changed from: private */
    public JSONObject C;
    /* access modifiers changed from: private */
    public Handler D = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Log.d(MiBrainCloudSDKManager.o, "msg.what" + message.what);
            int i = message.what;
            if (i != 4) {
                switch (i) {
                    case 10:
                        String str = (String) message.obj;
                        if (MiBrainCloudSDKManager.this.p == null) {
                            return;
                        }
                        if (MiBrainCloudSDKManager.this.b(str) == null) {
                            MiBrainCloudSDKManager.this.p.a((String) message.obj);
                            return;
                        } else {
                            MiBrainCloudSDKManager.this.p.a(MiBrainCloudSDKManager.this.b(str));
                            return;
                        }
                    case 11:
                        if (MiBrainCloudSDKManager.this.p != null) {
                            MiBrainCloudSDKManager.this.p.a((AiServiceError) message.obj);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                int unused = MiBrainCloudSDKManager.this.t = 2;
            }
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final ByteArrayOutputStream f23138a = new ByteArrayOutputStream();
    SpeechEngine j;
    /* access modifiers changed from: private */
    public NLPResultCallBack p;
    private AsrListener q;
    /* access modifiers changed from: private */
    public NLProcessor s;
    /* access modifiers changed from: private */
    public int t = 0;

    private MiBrainCloudSDKManager(Context context, String str, String str2, String str3) {
        this.s = new NLProcessor(context);
        this.s.a(str, str2, str3);
        this.j = SpeechEngine.a(context, 1, k, l);
        this.j.a((AsrListener) this);
        this.j.a(n(), e(), f());
    }

    private int n() {
        if (NLProcessor.f23141a == 0) {
            return 0;
        }
        if (NLProcessor.f23141a == 1) {
            return 1;
        }
        if (NLProcessor.f23141a == 2) {
            return 2;
        }
        return 0;
    }

    static String e() {
        return (NLProcessor.f23141a == 0 || NLProcessor.f23141a == 1 || NLProcessor.f23141a != 2) ? k : m;
    }

    static String f() {
        return (NLProcessor.f23141a == 0 || NLProcessor.f23141a == 1 || NLProcessor.f23141a != 2) ? l : n;
    }

    public static MiBrainCloudSDKManager a(Context context, String str, String str2, String str3, boolean z2) {
        if (r == null) {
            r = new MiBrainCloudSDKManager(context, str, str2, str3);
        }
        if (z2) {
            r.s.a(str, str2, str3);
        }
        return r;
    }

    public static MiBrainCloudSDKManager g() {
        return r;
    }

    public void a(NLPResultCallBack nLPResultCallBack) {
        this.p = nLPResultCallBack;
    }

    public void a(AsrListener asrListener) {
        this.q = asrListener;
    }

    public void a(String str) {
        new NLPTask().execute(new String[]{str, "fromClick"});
    }

    public int h() {
        return this.t;
    }

    public void i() {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        AsrRequest asrRequest = new AsrRequest();
        asrRequest.b(true);
        paramBuilder.b().a(asrRequest);
        this.j.a(paramBuilder);
        this.t = 1;
        this.f23138a.reset();
    }

    public void j() {
        if (this.j != null) {
            this.j.i();
        }
    }

    public void k() {
        this.t = 2;
        this.j.l();
        Log.d(o, "stopListening");
    }

    public void l() {
        this.t = 0;
        this.j.i();
        Log.d(o, "cancelListening");
    }

    private class NLPTask extends AsyncTask<String, Void, String> {
        private NLPTask() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String doInBackground(String... strArr) {
            try {
                return MiBrainCloudSDKManager.this.s.a(strArr[0], MiBrainCloudSDKManager.this.C).toString();
            } catch (Exception e) {
                MiBrainCloudSDKManager.this.D.obtainMessage(11, new AiServiceError(999, "NLPTaskErro", AiServiceError.c)).sendToTarget();
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(String str) {
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    NLProcessor.b = jSONObject.optString("session_id");
                    JSONObject unused = MiBrainCloudSDKManager.this.C = jSONObject.getJSONArray("answer").getJSONObject(0).getJSONObject("post_back");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MiBrainCloudSDKManager.this.D.obtainMessage(10, str).sendToTarget();
            }
        }
    }

    public void b() {
        if (this.q != null) {
            this.q.b();
        }
    }

    public void a(float f2) {
        if (this.q != null) {
            this.q.a(f2);
        }
    }

    public void a(byte[] bArr) {
        if (this.q != null) {
            this.q.a(bArr);
        }
    }

    public void a(SpeechError speechError) {
        if (this.q != null) {
            this.q.a(speechError);
        }
    }

    public void a(SpeechResult speechResult) {
        if (this.q != null) {
            this.q.a(speechResult);
        }
        new NLPTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{speechResult.f()});
    }

    public void b(SpeechResult speechResult) {
        if (this.q != null) {
            this.q.b(speechResult);
        }
    }

    public void d() {
        if (this.q != null) {
            this.q.d();
        }
    }

    public void a(String str, String str2) {
        if (this.q != null) {
            this.q.a(str, str2);
        }
    }

    public void a() {
        if (this.q != null) {
            this.q.a();
            Log.d(o, "ASR onReadyForSpeech");
            this.t = 0;
        }
    }

    public void c() {
        if (this.q != null) {
            this.q.c();
            Log.d(o, "AudioTask # onEndOfSpeech");
            this.D.obtainMessage(4).sendToTarget();
        }
    }

    public AiServiceError b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getJSONObject("status").getInt("code");
            if (i2 != 200) {
                return new AiServiceError(i2, jSONObject.getJSONObject("status").getString("error_details"), AiServiceError.b);
            }
            if (d(str) != null) {
                return d(str);
            }
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private AiServiceError d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.getJSONArray("answer").getJSONObject(0).getString("domain").equalsIgnoreCase("smartMiot")) {
                return new AiServiceError(TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "对不起，我没听懂", AiServiceError.d);
            }
            if (jSONObject.getJSONArray("answer").getJSONObject(0).getJSONObject("intention").getDouble("score") <= 0.0d) {
                return new AiServiceError(TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "对不起，我没听懂", AiServiceError.d);
            }
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void c(String str) {
        if (this.j != null && !TextUtils.isEmpty(str)) {
            TtsRequest ttsRequest = new TtsRequest();
            ttsRequest.c(str);
            this.j.a(ttsRequest);
        }
    }

    public void m() {
        if (r != null) {
            r.k();
            r = null;
        }
    }
}
