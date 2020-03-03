package com.xiaomi.ai;

import android.content.Context;
import android.media.AudioTrack;
import android.text.TextUtils;
import com.google.android.gms.actions.SearchIntents;
import com.payu.custombrowser.util.CBConstant;
import com.taobao.weex.common.Constants;
import com.xiaomi.ai.api.common.APIUtils;
import com.xiaomi.ai.api.common.Instruction;
import com.xiaomi.ai.mibrain.MibrainOauthManager;
import com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder;
import com.xiaomi.ai.utils.DeviceUtils;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.UploadHelper;
import com.xiaomi.ai.utils.k;
import com.xiaomi.smarthome.light.group.LightGroupMemberUpdateActivity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class SpeechEngine {
    private static final String X = "MiSpeechSDK:SpeechEngine";

    /* renamed from: a  reason: collision with root package name */
    public static final int f9903a = 1;
    public static final int b = MibrainRequestParamsBuilder.MIBRAIN_AUTH_APPIDTOKEN;
    public static final int c = MibrainRequestParamsBuilder.MIBRAIN_AUTH_MIOTTOKEN;
    public static final int d = MibrainRequestParamsBuilder.MIBRAIN_AUTH_MIAITOKEN;
    public static final int e = MibrainRequestParamsBuilder.MIBRAIN_AUTH_TPTOKEN;
    public static final int f = MibrainRequestParamsBuilder.MIBRAIN_AUTH_MIAOTOKEN;
    public static final int g = MibrainRequestParamsBuilder.MIBRAIN_AUTH_ANONYMOUS;
    public static final int h = MibrainRequestParamsBuilder.MIBRAIN_AUTH_ANONYMOUS_PROXY;
    public static final int i = 128;
    public static final int j = 0;
    public static final int k = 2;
    public static final int l = 1;
    public static final int m = 0;
    public static final int n = 3;
    public static final int o = 1;
    public static final int p = 2;
    public static final int q = 1;
    public static final int r = 2;
    protected VoiceprintRegisterListener A;
    protected VoiceprintDeleteListener B;
    protected VoiceprintQueryListener C;
    protected MiAiOauthCallbacks D;
    protected ServiceEventListener E;
    protected InstructionListener F;
    protected EventTrackListener G;
    protected String H;
    protected String I;
    protected String J;
    protected int K;
    protected String L;
    protected int M = 3;
    protected NeedUpdateTokenCallback N;
    protected k O;
    protected String P = "1.0.5";
    protected int Q = 1;
    protected int R = 5;
    protected int S = 10;
    protected int T = 4;
    protected int U = 10;
    protected int V = 0;
    protected int W = 0;
    private boolean Y;
    private BluetoothManager Z;
    protected String s;
    protected q t;
    protected SpeechError u;
    protected Context v;
    protected AsrListener w;
    protected NlpListener x;
    protected TtsListener y;
    protected VoiceprintRecognizeListener z;

    public static class ParamBuilder {
        public static final int r = 0;
        public static final int s = 1;
        public static final int t = 2;

        /* renamed from: a  reason: collision with root package name */
        boolean f9904a;
        AsrRequest b;
        boolean c;
        NlpRequest d;
        boolean e;
        TtsRequest f;
        boolean g;
        VoiceprintRegisterRequest h;
        boolean i;
        VoiceprintRecognizeRequest j;
        VoiceprintQueryRequest k;
        VoiceprintDeleteRequest l;
        boolean m;
        boolean n;
        boolean o;
        boolean p;
        EventRequest q;
        int u = 1;
        String v;
        boolean w;
        String x;
        byte[] y;

        public ParamBuilder a() {
            a(true);
            return this;
        }

        public ParamBuilder a(int i2) {
            this.u = i2;
            return this;
        }

        public ParamBuilder a(AsrRequest asrRequest) {
            this.b = asrRequest;
            return this;
        }

        public ParamBuilder a(EventRequest eventRequest) {
            this.q = eventRequest;
            return this;
        }

        public ParamBuilder a(NlpRequest nlpRequest) {
            this.d = nlpRequest;
            return this;
        }

        public ParamBuilder a(TtsRequest ttsRequest) {
            this.f = ttsRequest;
            return this;
        }

        public ParamBuilder a(UploadHelper.WakeupDataInfo wakeupDataInfo) {
            this.x = UploadHelper.a(wakeupDataInfo);
            return this;
        }

        public ParamBuilder a(String str) {
            this.v = str;
            return this;
        }

        public ParamBuilder a(boolean z) {
            this.w = z;
            return this;
        }

        public ParamBuilder a(byte[] bArr) {
            this.y = bArr;
            return this;
        }

        public ParamBuilder b() {
            b(true);
            return this;
        }

        public ParamBuilder b(boolean z) {
            this.f9904a = z;
            return this;
        }

        public ParamBuilder c() {
            c(true);
            return this;
        }

        public ParamBuilder c(boolean z) {
            this.c = z;
            return this;
        }

        public ParamBuilder d() {
            d(true);
            return this;
        }

        public ParamBuilder d(boolean z) {
            this.e = z;
            return this;
        }

        public ParamBuilder e() {
            this.p = true;
            return this;
        }

        public ParamBuilder e(boolean z) {
            this.p = z;
            return this;
        }
    }

    public enum ProcessStage {
        STAGE_REQUESTING,
        STAGE_ASR,
        STAGE_NLP,
        STAGE_TTS,
        STAGE_VP_REG,
        STAGE_VP,
        STAGE_VP_DEL,
        STAGE_VP_QRY,
        STAGE_EVENT,
        STAGE_IDLE
    }

    public static class ResultParser extends q {
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0077  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0079  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0081  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0090  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x009a  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00a2  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00a6  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00b1  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00bb  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00c2  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00da  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00dc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.xiaomi.ai.SpeechResult a(org.json.JSONObject r22) {
            /*
                r21 = this;
                r0 = r22
                com.xiaomi.ai.SpeechResult r1 = new com.xiaomi.ai.SpeechResult
                r1.<init>()
                java.lang.String r2 = "response"
                org.json.JSONObject r2 = r0.getJSONObject(r2)
                java.lang.String r3 = "version"
                double r3 = r2.getDouble(r3)
                java.lang.String r5 = "answer"
                java.lang.Object r5 = r2.get(r5)
                boolean r6 = r5 instanceof org.json.JSONArray
                r7 = 0
                if (r6 == 0) goto L_0x0025
                org.json.JSONArray r5 = (org.json.JSONArray) r5
                org.json.JSONObject r5 = r5.getJSONObject(r7)
                goto L_0x002d
            L_0x0025:
                boolean r6 = r5 instanceof org.json.JSONObject
                if (r6 == 0) goto L_0x002c
                org.json.JSONObject r5 = (org.json.JSONObject) r5
                goto L_0x002d
            L_0x002c:
                r5 = 0
            L_0x002d:
                java.lang.String r6 = "intention"
                org.json.JSONObject r6 = r5.getJSONObject(r6)
                java.lang.String r9 = "content"
                org.json.JSONObject r9 = r5.optJSONObject(r9)
                java.lang.String r10 = "session_id"
                java.lang.String r10 = r2.optString(r10)
                java.lang.String r11 = "action"
                java.lang.String r11 = r5.optString(r11)
                java.lang.String r12 = "domain"
                java.lang.String r12 = r5.optString(r12)
                java.lang.String r13 = r5.toString()
                java.lang.String r14 = "text"
                java.lang.String r5 = r5.optString(r14)
                java.lang.String r14 = "query"
                java.lang.String r14 = r6.optString(r14)
                r15 = 4612136378390124954(0x400199999999999a, double:2.2)
                int r17 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
                if (r17 < 0) goto L_0x006f
                if (r9 != 0) goto L_0x0068
            L_0x0066:
                r3 = 0
                goto L_0x0075
            L_0x0068:
                java.lang.String r3 = "to_speak"
            L_0x006a:
                java.lang.String r3 = r9.optString(r3)
                goto L_0x0075
            L_0x006f:
                if (r9 != 0) goto L_0x0072
                goto L_0x0066
            L_0x0072:
                java.lang.String r3 = "toSpeak"
                goto L_0x006a
            L_0x0075:
                if (r9 != 0) goto L_0x0079
                r4 = 0
                goto L_0x007f
            L_0x0079:
                java.lang.String r4 = "to_display"
                org.json.JSONObject r4 = r9.optJSONObject(r4)
            L_0x007f:
                if (r4 != 0) goto L_0x0083
                r15 = 0
                goto L_0x0089
            L_0x0083:
                java.lang.String r15 = "text"
                java.lang.String r15 = r4.optString(r15)
            L_0x0089:
                r8 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r4 != 0) goto L_0x0090
                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                goto L_0x0096
            L_0x0090:
                java.lang.String r7 = "duration"
                int r4 = r4.optInt(r7, r8)
            L_0x0096:
                if (r9 != 0) goto L_0x009a
                r7 = 0
                goto L_0x00a0
            L_0x009a:
                java.lang.String r7 = "unknown_domain_action"
                int r7 = r9.optInt(r7)
            L_0x00a0:
                if (r9 != 0) goto L_0x00a6
                r18 = r7
                r7 = 0
                goto L_0x00af
            L_0x00a6:
                java.lang.String r8 = "open_mic"
                r18 = r7
                r7 = 0
                boolean r7 = r9.optBoolean(r8, r7)
            L_0x00af:
                if (r9 == 0) goto L_0x00b8
                java.lang.String r8 = "directive"
                java.lang.String r8 = r9.optString(r8)
                goto L_0x00b9
            L_0x00b8:
                r8 = 0
            L_0x00b9:
                if (r9 != 0) goto L_0x00c2
                r20 = r7
                r19 = r8
                r8 = -2147483648(0xffffffff80000000, float:-0.0)
                goto L_0x00ce
            L_0x00c2:
                r19 = r8
                java.lang.String r8 = "vad_idle"
                r20 = r7
                r7 = -2147483648(0xffffffff80000000, float:-0.0)
                int r8 = r9.optInt(r8, r7)
            L_0x00ce:
                r1.b = r5
                r1.f9906a = r14
                java.lang.String r5 = r6.toString()
                r1.d = r5
                if (r9 != 0) goto L_0x00dc
                r5 = 0
                goto L_0x00e0
            L_0x00dc:
                java.lang.String r5 = r9.toString()
            L_0x00e0:
                r1.h = r5
                java.lang.String r5 = "meta"
                org.json.JSONObject r0 = r0.getJSONObject(r5)
                java.lang.String r5 = "request_id"
                java.lang.String r0 = r0.getString(r5)
                r1.f = r0
                r1.e = r10
                r1.i = r12
                r1.j = r11
                java.lang.String r0 = r2.toString()
                r1.g = r0
                r1.c = r13
                r1.k = r3
                r1.l = r15
                r1.w = r4
                r7 = r20
                r1.n = r7
                r0 = r19
                r1.m = r0
                r7 = r18
                r1.o = r7
                r1.v = r8
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.SpeechEngine.ResultParser.a(org.json.JSONObject):com.xiaomi.ai.SpeechResult");
        }

        public SpeechResult b(JSONObject jSONObject) {
            SpeechResult speechResult = new SpeechResult();
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            JSONArray jSONArray = jSONObject2.getJSONArray("queries");
            if (jSONArray != null && jSONArray.length() > 0) {
                speechResult.f9906a = jSONObject2.getJSONArray("queries").getJSONObject(0).getString(SearchIntents.EXTRA_QUERY);
                speechResult.g = jSONObject2.toString();
            }
            speechResult.f = jSONObject.getJSONObject("meta").getString("request_id");
            return speechResult;
        }

        public SpeechResult c(JSONObject jSONObject) {
            SpeechResult speechResult = new SpeechResult();
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            speechResult.y = jSONObject2;
            JSONArray jSONArray = jSONObject2.getJSONArray("result");
            if (jSONArray != null && jSONArray.length() > 0) {
                speechResult.r = jSONArray.getJSONObject(0).getString(Constants.Name.ROLE);
            }
            speechResult.u = jSONObject2.optString("error_msg");
            speechResult.f = jSONObject.getJSONObject("meta").getString("request_id");
            speechResult.x = jSONObject2.optJSONArray("result");
            return speechResult;
        }

        public SpeechResult d(JSONObject jSONObject) {
            SpeechResult speechResult = new SpeechResult();
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            speechResult.y = jSONObject2;
            speechResult.p = jSONObject2.optBoolean("is_success", false);
            speechResult.q = "RESULT_VOR_REG_FINAL".equals(jSONObject.getJSONObject("meta").getString("type"));
            speechResult.u = jSONObject2.optString("error_msg");
            speechResult.f = jSONObject.optJSONObject("meta").getString("request_id");
            speechResult.x = jSONObject2.optJSONArray("result");
            return speechResult;
        }

        public SpeechResult e(JSONObject jSONObject) {
            SpeechResult speechResult = new SpeechResult();
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            speechResult.y = jSONObject2;
            speechResult.s = jSONObject2.optBoolean("is_success", false);
            speechResult.u = jSONObject2.optString("error_msg");
            speechResult.f = jSONObject.getJSONObject("meta").getString("request_id");
            speechResult.x = jSONObject2.optJSONArray("result");
            return speechResult;
        }

        public SpeechResult f(JSONObject jSONObject) {
            SpeechResult speechResult = new SpeechResult();
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            speechResult.y = jSONObject2;
            JSONArray jSONArray = jSONObject2.getJSONArray("result");
            speechResult.x = jSONArray;
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null && jSONArray.length() > 0) {
                JSONArray optJSONArray = jSONArray.getJSONObject(0).optJSONArray(LightGroupMemberUpdateActivity.KEY_MEMBER);
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(optJSONArray.getString(i));
                    }
                }
            }
            speechResult.u = jSONObject2.optString("error_msg");
            speechResult.t = arrayList;
            speechResult.f = jSONObject.getJSONObject("meta").getString("request_id");
            return speechResult;
        }

        public Instruction[] g(JSONObject jSONObject) {
            String str;
            String str2;
            if (!jSONObject.has(CBConstant.RESPONSE)) {
                return null;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
            if (!jSONObject2.has("instructions")) {
                return null;
            }
            JSONArray jSONArray = jSONObject2.getJSONArray("instructions");
            if (jSONArray != null) {
                ArrayList arrayList = new ArrayList(jSONArray.length());
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        arrayList.add(APIUtils.readInstruction(jSONArray.get(i).toString()));
                    } catch (Exception e) {
                        Log.h(SpeechEngine.X, "getInstruction: failed to readInstruction, " + jSONArray.get(i).toString());
                        e.printStackTrace();
                    }
                }
                if (!arrayList.isEmpty()) {
                    return (Instruction[]) arrayList.toArray(new Instruction[arrayList.size()]);
                }
                str = SpeechEngine.X;
                str2 = "getInstruction: instruction array is empty";
            } else {
                str = SpeechEngine.X;
                str2 = "getInstruction: no instructions";
            }
            Log.h(str, str2);
            return null;
        }
    }

    SpeechEngine(Context context, String str, String str2) {
        this.v = context.getApplicationContext();
        this.H = str;
        this.I = str2;
        this.O = new k(this.H, DeviceUtils.b(this.v));
    }

    public static SpeechEngine a(Context context, int i2, String str) {
        c cVar = i2 != 1 ? null : new c(context, str, (String) null);
        if (cVar != null) {
            cVar.a(context, (String) null, c);
        }
        return cVar;
    }

    @Deprecated
    public static SpeechEngine a(Context context, int i2, String str, String str2) {
        c cVar = i2 != 1 ? null : new c(context, str, str2);
        if (cVar != null) {
            cVar.a(context, (String) null, b);
        }
        return cVar;
    }

    public static SpeechEngine a(Context context, int i2, String str, String str2, String str3) {
        c cVar = i2 != 1 ? null : new c(context, str, str2);
        if (cVar != null) {
            cVar.a(context, str3, d);
        }
        return cVar;
    }

    public static SpeechEngine a(Context context, int i2, String str, String str2, String str3, int i3) {
        int i4;
        Log.f("SpeechEngine", "use anonymous mode " + i3);
        c cVar = i2 != 1 ? null : new c(context, str, str2);
        if (cVar != null) {
            if (i3 == 1) {
                i4 = g;
            } else if (i3 != 2) {
                return null;
            } else {
                if (context.getPackageName().equals("com.xiaomi.mibrain.speech") || context.getPackageName().equals("com.xiaomi.mibrain.demo")) {
                    i4 = h;
                } else {
                    Log.h("ERROR", "illegal call");
                    return null;
                }
            }
            cVar.a(context, str3, i4);
            cVar.c((MiAiOauthCallbacks) new o());
        }
        return cVar;
    }

    public static SpeechEngine a(Context context, int i2, String str, String str2, String str3, boolean z2) {
        int i3;
        c cVar = i2 != 1 ? null : new c(context, str, str2);
        if (z2) {
            if (cVar != null) {
                i3 = f;
            }
            return cVar;
        }
        if (cVar != null) {
            i3 = d;
        }
        return cVar;
        cVar.a(context, str3, i3);
        return cVar;
    }

    public static SpeechEngine a(Context context, int i2, String str, boolean z2) {
        c cVar = i2 != 1 ? null : new c(context, str, (String) null);
        if (cVar != null) {
            cVar.a(context, (String) null, z2 ? e : c);
        }
        return cVar;
    }

    public static String a() {
        return BuildConfig.f;
    }

    public static int b() {
        return BuildConfig.e;
    }

    public abstract int a(NlpRequest nlpRequest);

    public abstract int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr);

    public abstract int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr, String str);

    public abstract int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr);

    public abstract int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr, String str);

    /* access modifiers changed from: protected */
    public final ProcessStage a(ParamBuilder paramBuilder, ProcessStage processStage) {
        if (processStage == ProcessStage.STAGE_REQUESTING) {
            return paramBuilder.f9904a ? ProcessStage.STAGE_ASR : paramBuilder.c ? ProcessStage.STAGE_NLP : paramBuilder.e ? ProcessStage.STAGE_TTS : paramBuilder.m ? ProcessStage.STAGE_VP : paramBuilder.i ? ProcessStage.STAGE_VP_REG : paramBuilder.n ? ProcessStage.STAGE_VP_DEL : ProcessStage.STAGE_VP_QRY;
        }
        if (processStage == ProcessStage.STAGE_ASR) {
            if (paramBuilder.c) {
                return ProcessStage.STAGE_NLP;
            }
            if (paramBuilder.e) {
                return ProcessStage.STAGE_TTS;
            }
        } else if (processStage == ProcessStage.STAGE_NLP) {
            if (paramBuilder.e) {
                return ProcessStage.STAGE_TTS;
            }
        } else if (processStage == ProcessStage.STAGE_VP_REG) {
            return ProcessStage.STAGE_VP_REG;
        }
        return ProcessStage.STAGE_IDLE;
    }

    public abstract UploadHelper a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface);

    public abstract UploadHelper a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str);

    public abstract String a(String str, String str2);

    /* access modifiers changed from: protected */
    public void a(float f2) {
        if (this.w != null) {
            this.w.a(f2);
        }
    }

    public void a(int i2) {
        this.V = i2;
    }

    public void a(int i2, String str) {
    }

    public void a(int i2, String str, String str2) {
    }

    public void a(Context context, String str, int i2) {
        Log.a(context);
        this.J = str;
        this.K = i2;
    }

    /* access modifiers changed from: protected */
    public void a(AudioTrack audioTrack) {
        if (this.y != null) {
            this.y.a(audioTrack);
        }
    }

    public void a(AsrListener asrListener) {
        this.w = asrListener;
    }

    public final void a(AsrRequest asrRequest) {
        i();
        b(asrRequest);
    }

    public void a(EventTrackListener eventTrackListener) {
        this.G = eventTrackListener;
    }

    public abstract void a(HTTPCallback hTTPCallback);

    public abstract void a(HTTPCallback hTTPCallback, TrackInfo trackInfo, String str);

    public void a(InstructionListener instructionListener) {
        this.F = instructionListener;
    }

    public void a(MiAiOauthCallbacks miAiOauthCallbacks) {
        this.D = miAiOauthCallbacks;
    }

    public void a(NeedUpdateTokenCallback needUpdateTokenCallback) {
        this.N = needUpdateTokenCallback;
    }

    public void a(NlpListener nlpListener) {
        this.x = nlpListener;
    }

    /* access modifiers changed from: protected */
    public void a(PCMInfo pCMInfo) {
        if (this.y != null) {
            this.y.a(pCMInfo);
        }
    }

    public abstract void a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str, String str2, String str3, int i2, int i3, String str4, byte[] bArr);

    /* access modifiers changed from: protected */
    public void a(ServiceEvent serviceEvent) {
        if (this.E != null) {
            this.E.a(serviceEvent);
        }
    }

    public void a(ServiceEventListener serviceEventListener) {
        this.E = serviceEventListener;
    }

    public final void a(ParamBuilder paramBuilder) {
        i();
        b(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void a(ParamBuilder paramBuilder, SpeechError speechError) {
        Log.h(X, "notifyError: " + speechError + "state" + speechError.S);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", speechError.U);
            jSONObject.put("subcode", "-512");
            jSONObject.put("msg", speechError.V);
            jSONObject.put("request_id", "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        if (!TextUtils.isEmpty(jSONObject2)) {
            speechError.V = jSONObject2;
        }
        this.u = speechError;
        if (speechError.S == ProcessStage.STAGE_REQUESTING) {
            speechError.S = a(paramBuilder, speechError.S);
        }
        switch (p.f9932a[speechError.S.ordinal()]) {
            case 1:
                a(speechError);
                return;
            case 2:
                f(speechError);
                return;
            case 3:
                g(speechError);
                return;
            case 4:
                c(speechError);
                return;
            case 5:
                b(speechError);
                return;
            case 6:
                d(speechError);
                return;
            case 7:
                e(speechError);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void a(SpeechError speechError) {
        if (this.w != null) {
            this.w.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void a(SpeechResult speechResult) {
        if (this.w != null) {
            this.w.a(speechResult);
        }
    }

    public abstract void a(TrackInfo trackInfo, String str);

    public void a(TtsListener ttsListener) {
        this.y = ttsListener;
    }

    public final void a(TtsRequest ttsRequest) {
        i();
        b(ttsRequest);
    }

    public void a(VoiceprintDeleteListener voiceprintDeleteListener) {
        this.B = voiceprintDeleteListener;
    }

    public final void a(VoiceprintDeleteRequest voiceprintDeleteRequest) {
        i();
        b(voiceprintDeleteRequest);
    }

    public void a(VoiceprintQueryListener voiceprintQueryListener) {
        this.C = voiceprintQueryListener;
    }

    public final void a(VoiceprintQueryRequest voiceprintQueryRequest) {
        i();
        b(voiceprintQueryRequest);
    }

    public void a(VoiceprintRecognizeListener voiceprintRecognizeListener) {
        this.z = voiceprintRecognizeListener;
    }

    public final void a(VoiceprintRecognizeRequest voiceprintRecognizeRequest) {
        i();
        b(voiceprintRecognizeRequest);
    }

    public void a(VoiceprintRegisterListener voiceprintRegisterListener) {
        this.A = voiceprintRegisterListener;
    }

    public final void a(VoiceprintRegisterRequest voiceprintRegisterRequest) {
        i();
        b(voiceprintRegisterRequest);
    }

    public abstract void a(MibrainOauthManager.updateTokenCallback updatetokencallback);

    public void a(String str) {
        this.P = str;
    }

    public abstract void a(JSONObject jSONObject);

    public void a(boolean z2) {
        this.Q = z2 ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr) {
        if (bArr != null) {
            this.w.a(bArr);
        }
    }

    public abstract void a(byte[] bArr, int i2, int i3);

    /* access modifiers changed from: protected */
    public void a(Instruction[] instructionArr, SpeechResult speechResult) {
        if (this.F != null) {
            this.F.a(instructionArr, speechResult);
        }
    }

    public abstract boolean a(String str, String str2, long j2);

    public abstract boolean a(String str, String str2, String str3, String str4, String str5, String str6);

    /* access modifiers changed from: protected */
    public void b(float f2) {
        if (this.A != null) {
            this.A.a(f2);
        }
    }

    public void b(int i2) {
        this.R = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void b(AsrRequest asrRequest);

    public void b(MiAiOauthCallbacks miAiOauthCallbacks) {
        this.D = miAiOauthCallbacks;
    }

    public final void b(NlpRequest nlpRequest) {
        i();
        c(nlpRequest);
    }

    /* access modifiers changed from: protected */
    public abstract void b(ParamBuilder paramBuilder);

    /* access modifiers changed from: protected */
    public void b(SpeechError speechError) {
        if (this.A != null) {
            this.A.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void b(SpeechResult speechResult) {
        if (this.A != null) {
            this.A.a(speechResult);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b(TtsRequest ttsRequest);

    /* access modifiers changed from: protected */
    public abstract void b(VoiceprintDeleteRequest voiceprintDeleteRequest);

    /* access modifiers changed from: protected */
    public abstract void b(VoiceprintQueryRequest voiceprintQueryRequest);

    /* access modifiers changed from: protected */
    public abstract void b(VoiceprintRecognizeRequest voiceprintRecognizeRequest);

    /* access modifiers changed from: protected */
    public abstract void b(VoiceprintRegisterRequest voiceprintRegisterRequest);

    public abstract void b(String str);

    public void b(String str, String str2) {
    }

    public void b(boolean z2) {
        this.W = z2 ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public void b(byte[] bArr) {
        if (bArr != null && this.A != null) {
            this.A.a(bArr);
        }
    }

    public abstract String c(boolean z2);

    /* access modifiers changed from: protected */
    public void c(float f2) {
        if (this.z != null) {
            this.z.a(f2);
        }
    }

    public void c(int i2) {
        this.S = i2;
    }

    public void c(MiAiOauthCallbacks miAiOauthCallbacks) {
        this.D = miAiOauthCallbacks;
    }

    /* access modifiers changed from: protected */
    public abstract void c(NlpRequest nlpRequest);

    /* access modifiers changed from: protected */
    public void c(SpeechError speechError) {
        if (this.z != null) {
            this.z.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void c(SpeechResult speechResult) {
        if (this.B != null) {
            this.B.a(speechResult);
        }
    }

    public abstract void c(String str);

    /* access modifiers changed from: protected */
    public void c(String str, String str2) {
        if (this.w != null) {
            this.w.a(str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void c(byte[] bArr) {
        if (bArr != null && this.z != null) {
            this.z.a(bArr);
        }
    }

    public abstract boolean c();

    public abstract String d();

    public void d(int i2) {
        this.T = i2;
    }

    /* access modifiers changed from: protected */
    public void d(SpeechError speechError) {
        if (this.B != null) {
            this.B.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void d(SpeechResult speechResult) {
        if (this.C != null) {
            this.C.a(speechResult);
        }
    }

    public void d(String str) {
    }

    public void d(boolean z2) {
        this.Y = z2;
        if (z2) {
            if (this.Z == null) {
                this.Z = BluetoothManager.a(this.v);
                this.Z.c();
            }
        } else if (this.Z != null) {
            this.Z.d();
        }
    }

    public abstract void e();

    public void e(int i2) {
        this.U = i2;
    }

    /* access modifiers changed from: protected */
    public void e(SpeechError speechError) {
        if (this.C != null) {
            this.C.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void e(SpeechResult speechResult) {
        if (this.z != null) {
            this.z.a(speechResult);
        }
    }

    public void e(String str) {
        this.L = str;
    }

    public abstract void e(boolean z2);

    public abstract void f();

    public abstract void f(int i2);

    /* access modifiers changed from: protected */
    public void f(SpeechError speechError) {
        if (this.x != null) {
            this.x.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void f(SpeechResult speechResult) {
        if (this.w != null) {
            this.w.b(speechResult);
        }
    }

    public void f(String str) {
    }

    /* access modifiers changed from: protected */
    public void f(boolean z2) {
        if (this.Y && this.Z != null) {
            this.Z.a(z2);
        }
    }

    public abstract int g();

    public void g(int i2) {
        this.M = i2;
    }

    /* access modifiers changed from: protected */
    public void g(SpeechError speechError) {
        if (this.y != null) {
            this.y.a(speechError);
        }
    }

    /* access modifiers changed from: protected */
    public void g(SpeechResult speechResult) {
        if (this.x != null) {
            this.x.a(speechResult);
        }
    }

    public void g(String str) {
        Log.h("SET UA", str);
        this.s = str;
    }

    /* access modifiers changed from: protected */
    public void g(boolean z2) {
        if (this.y != null) {
            this.y.a(z2);
        }
    }

    public void h() {
        if (this.Z != null) {
            this.Z.d();
        }
    }

    public void h(int i2) {
    }

    /* access modifiers changed from: protected */
    public void h(String str) {
        if (this.G != null) {
            this.G.a(str);
        }
    }

    public void i() {
        this.u = null;
    }

    /* access modifiers changed from: protected */
    public void i(String str) {
        if (this.y != null) {
            this.y.a(str);
        }
    }

    public abstract void j();

    public abstract void k();

    public void l() {
    }

    public abstract void m();

    public abstract void n();

    public SpeechError o() {
        return this.u;
    }

    public boolean p() {
        return this.u != null;
    }

    /* access modifiers changed from: protected */
    public void q() {
        if (this.w != null) {
            this.w.a();
        }
    }

    /* access modifiers changed from: protected */
    public void r() {
        Log.h(X, "mIsScoEnable" + this.Y);
        if (this.Y && this.Z != null) {
            this.Z.a(true);
        }
        if (this.w != null) {
            this.w.b();
        }
    }

    /* access modifiers changed from: protected */
    public void s() {
        if (this.Y && this.Z != null) {
            this.Z.a(true);
        }
        if (this.A != null) {
            this.A.a();
        }
    }

    /* access modifiers changed from: protected */
    public void t() {
        if (this.Y && this.Z != null) {
            this.Z.a(true);
        }
        if (this.z != null) {
            this.z.a();
        }
    }

    /* access modifiers changed from: protected */
    public void u() {
        Log.h(X, "notifyAsrEndOfSpeech:=" + this.Y);
        if (this.Y && this.Z != null) {
            this.Z.a(false);
        }
        if (this.w != null) {
            this.w.c();
        }
    }

    /* access modifiers changed from: protected */
    public void v() {
        if (this.Y && this.Z != null) {
            this.Z.a(false);
        }
        if (this.A != null) {
            this.A.b();
        }
    }

    /* access modifiers changed from: protected */
    public void w() {
        if (this.Y && this.Z != null) {
            this.Z.a(false);
        }
        if (this.z != null) {
            this.z.b();
        }
    }

    /* access modifiers changed from: protected */
    public void x() {
        if (this.w != null) {
            this.w.d();
        }
    }

    /* access modifiers changed from: protected */
    public void y() {
        if (this.y != null) {
            this.y.a();
        }
    }

    /* access modifiers changed from: protected */
    public void z() {
        if (this.y != null) {
            this.y.b();
        }
    }
}
