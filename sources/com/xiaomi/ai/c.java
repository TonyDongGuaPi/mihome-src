package com.xiaomi.ai;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.libra.Color;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.ai.SpeechEngine;
import com.xiaomi.ai.VoiceprintRecognizeRequest;
import com.xiaomi.ai.VoiceprintRegisterRequest;
import com.xiaomi.ai.m;
import com.xiaomi.ai.mibrain.GetAnonymousAuthorizationInterface;
import com.xiaomi.ai.mibrain.MibrainOauthManager;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.mibrain.MibrainRequestListener;
import com.xiaomi.ai.mibrain.MibrainRequestParams;
import com.xiaomi.ai.mibrain.Mibrainsdk;
import com.xiaomi.ai.streamplayer.k;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.UploadHelper;
import com.xiaomi.ai.utils.g;
import com.xiaomi.ai.utils.h;
import com.xiaomi.ai.utils.n;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class c extends SpeechEngine {
    private static final String Y = "MiSpeechSDK:MiAiEngine";
    private static final int ac = 4;
    private static final int ad = 10;
    private static final int ae = 5;
    GetAnonymousAuthorizationInterface X = new h(this);
    private SpannableStringBuilder Z;
    /* access modifiers changed from: private */
    public int aa = 0;
    private String ab = null;
    /* access modifiers changed from: private */
    public k af;
    /* access modifiers changed from: private */
    public volatile boolean ag;
    /* access modifiers changed from: private */
    public a ah;
    /* access modifiers changed from: private */
    public AudioManager ai;
    /* access modifiers changed from: private */
    public MibrainRequest aj;
    /* access modifiers changed from: private */
    public e ak;
    /* access modifiers changed from: private */
    public final Object al = new Object();
    private final Object am = new Object();
    /* access modifiers changed from: private */
    public String an;
    /* access modifiers changed from: private */
    public volatile SpeechEngine.ParamBuilder ao = null;
    /* access modifiers changed from: private */
    public m ap = null;
    /* access modifiers changed from: private */
    public int aq = 0;
    /* access modifiers changed from: private */
    public String ar;
    /* access modifiers changed from: private */
    public String as;
    private String at;
    /* access modifiers changed from: private */
    public int au;
    private ExecutorService av;
    /* access modifiers changed from: private */
    public AtomicBoolean aw;
    /* access modifiers changed from: private */
    public k.a ax = new j(this);

    private class a extends Thread {
        private MibrainRequest b;
        private boolean c;
        private SpeechEngine.ParamBuilder d;

        a(MibrainRequest mibrainRequest, SpeechEngine.ParamBuilder paramBuilder) {
            this.b = mibrainRequest;
            this.d = paramBuilder;
        }

        private void a(b bVar) {
            if (bVar != null && bVar.f9916a != null) {
                float a2 = n.a(bVar.f9916a, bVar.f9916a.length);
                if (this.d.f9904a) {
                    c.this.a(bVar.f9916a);
                    c.this.a(a2);
                }
                if (this.d.m) {
                    c.this.c(bVar.f9916a);
                    c.this.c(a2);
                }
                if (this.d.i) {
                    c.this.b(bVar.f9916a);
                    c.this.b(a2);
                }
            }
        }

        private void a(e eVar) {
            while (eVar.c()) {
                a(eVar.b());
            }
            eVar.a(new b(new byte[0], 0, 0, true));
            eVar.b.clear();
        }

        private boolean a() {
            if (this.d.b != null && this.d.b.l == AsrRequest.DataInputMode.DATA_INPUT_MODE_RECORDER) {
                return true;
            }
            if (this.d.h == null || this.d.h.c != VoiceprintRegisterRequest.DataInputMode.DATA_INPUT_MODE_RECORDER) {
                return this.d.j != null && this.d.j.b == VoiceprintRecognizeRequest.DataInputMode.DATA_INPUT_MODE_RECORDER;
            }
            return true;
        }

        /* JADX INFO: finally extract failed */
        private void b(e eVar) {
            synchronized (c.class) {
                if (!isInterrupted()) {
                    c.this.a(h.a("recordingToBuffer", Color.g));
                    int minBufferSize = AudioRecord.getMinBufferSize(RecordDevice.PCM_FREQUENCE_16K, 16, 2);
                    AudioRecord audioRecord = new AudioRecord(1, RecordDevice.PCM_FREQUENCE_16K, 16, 2, minBufferSize);
                    if (audioRecord.getState() != 1) {
                        synchronized (c.this.al) {
                            if (!c()) {
                                c.this.a((CharSequence) "recorder init failed");
                                SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                                speechError.U = 5;
                                speechError.T = 2;
                                speechError.V = "Fail to init recorder";
                                c.this.a(this.d, speechError);
                                return;
                            }
                            return;
                        }
                    }
                    try {
                        audioRecord.startRecording();
                        short[] sArr = new short[minBufferSize];
                        while (c.this.ag && !isInterrupted() && eVar.c()) {
                            try {
                                int read = audioRecord.read(sArr, 0, minBufferSize);
                                if (read <= 0) {
                                    boolean unused = c.this.ag = false;
                                    Log.h(c.Y, "audio read size =0");
                                    try {
                                        audioRecord.stop();
                                    } catch (IllegalStateException e) {
                                        c.this.a((CharSequence) "IllegalStateException: ", (Throwable) e);
                                    }
                                    audioRecord.release();
                                    return;
                                }
                                byte[] a2 = com.xiaomi.ai.utils.e.a(sArr, read);
                                b bVar = new b(a2, 0, a2.length, false);
                                eVar.a(bVar);
                                a(bVar);
                            } catch (Exception e2) {
                                try {
                                    Log.g(c.Y, "InterruptedException at recordingToBuffer: ", e2);
                                    try {
                                        audioRecord.stop();
                                    } catch (IllegalStateException e3) {
                                        c.this.a((CharSequence) "IllegalStateException: ", (Throwable) e3);
                                    }
                                } catch (Throwable th) {
                                    try {
                                        audioRecord.stop();
                                    } catch (IllegalStateException e4) {
                                        c.this.a((CharSequence) "IllegalStateException: ", (Throwable) e4);
                                    }
                                    audioRecord.release();
                                    throw th;
                                }
                            }
                        }
                        eVar.a(new b(new byte[0], 0, 0, true));
                        try {
                            audioRecord.stop();
                        } catch (IllegalStateException e5) {
                            c.this.a((CharSequence) "IllegalStateException: ", (Throwable) e5);
                        }
                        audioRecord.release();
                    } catch (IllegalStateException e6) {
                        synchronized (c.this.al) {
                            if (!c()) {
                                c.this.a((CharSequence) "IllegalStateException: ", (Throwable) e6);
                                audioRecord.release();
                                SpeechError speechError2 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                                speechError2.V = e6.toString();
                                speechError2.U = 11;
                                speechError2.T = 2;
                                c.this.a(this.d, speechError2);
                            }
                        }
                    }
                }
            }
        }

        private boolean b() {
            if (this.d.b != null && this.d.b.l == AsrRequest.DataInputMode.DATA_INPUT_MODE_BUFFER) {
                return true;
            }
            if (this.d.h == null || this.d.h.c != VoiceprintRegisterRequest.DataInputMode.DATA_INPUT_MODE_BUFFER) {
                return this.d.j != null && this.d.j.b == VoiceprintRecognizeRequest.DataInputMode.DATA_INPUT_MODE_BUFFER;
            }
            return true;
        }

        private boolean c() {
            return (c.this.ao == this.d && c.this.aj == this.b) ? false : true;
        }

        private boolean d() {
            synchronized (c.this.al) {
                if (c()) {
                    return true;
                }
                c.this.q();
                return false;
            }
        }

        private boolean e() {
            synchronized (c.this.al) {
                if (c()) {
                    return true;
                }
                c.this.r();
                return false;
            }
        }

        /* access modifiers changed from: private */
        public void f() {
            synchronized (c.this.al) {
                boolean z = true;
                if (this.c || c()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("call failed isNotifyEnd ");
                    sb.append(this.c);
                    sb.append("mMibrainRequest==request");
                    if (this.b != c.this.aj) {
                        z = false;
                    }
                    sb.append(z);
                    Log.g(c.Y, sb.toString());
                } else {
                    c.this.u();
                    this.c = true;
                }
            }
        }

        private boolean g() {
            synchronized (c.this.al) {
                if (c()) {
                    return true;
                }
                c.this.t();
                return false;
            }
        }

        private void h() {
            synchronized (c.this.al) {
                boolean z = true;
                if (this.c || c()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("call failed isNotifyEnd ");
                    sb.append(this.c);
                    sb.append("mMibrainRequest==request");
                    if (this.b != c.this.aj) {
                        z = false;
                    }
                    sb.append(z);
                    Log.g(c.Y, sb.toString());
                } else {
                    c.this.w();
                    this.c = true;
                }
            }
        }

        private boolean i() {
            synchronized (c.this.al) {
                if (c()) {
                    return true;
                }
                c.this.s();
                return false;
            }
        }

        private void j() {
            synchronized (c.this.al) {
                boolean z = true;
                if (this.c || c()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("call failed isNotifyEnd ");
                    sb.append(this.c);
                    sb.append("mMibrainRequest==request");
                    if (this.b != c.this.aj) {
                        z = false;
                    }
                    sb.append(z);
                    Log.h(c.Y, sb.toString());
                } else {
                    c.this.v();
                    this.c = true;
                }
            }
        }

        public void run() {
            e i;
            if (isInterrupted() || (i = c.this.ak) == null) {
                return;
            }
            if (this.d.f9904a && d()) {
                return;
            }
            if (this.d.f9904a && e()) {
                return;
            }
            if (this.d.m && g()) {
                return;
            }
            if (!this.d.i || !i()) {
                try {
                    if (a()) {
                        i.d();
                    }
                    if (a()) {
                        b(i);
                    } else if (b()) {
                        a(i);
                    } else {
                        throw new IllegalArgumentException("date input mode error");
                    }
                    if (this.d.f9904a) {
                        f();
                    }
                    if (this.d.m) {
                        h();
                    }
                    if (this.d.i) {
                        j();
                    }
                } finally {
                    if (!b()) {
                        i.e();
                    }
                }
            }
        }
    }

    private class b {

        /* renamed from: a  reason: collision with root package name */
        byte[] f9916a;
        boolean b;
        boolean c;
        C0082c d;
        String e;

        b(C0082c cVar) {
            this.d = cVar;
        }

        b(String str, C0082c cVar) {
            this.e = str;
            this.d = cVar;
        }

        b(byte[] bArr, int i, int i2, boolean z) {
            this.f9916a = bArr;
            if (bArr == null) {
                throw new NullPointerException("addBuffer params error, buffer is null ");
            } else if (i2 < 0 || i < 0) {
                throw new IllegalArgumentException("addBuffer params error,offset < 0 or len <0");
            } else if (i2 + i <= bArr.length) {
                this.f9916a = new byte[i2];
                System.arraycopy(bArr, i, this.f9916a, 0, i2);
                this.b = z;
                this.d = C0082c.DATA;
            } else {
                throw new IllegalArgumentException("addBuffer params error,offset + len > bytes.length");
            }
        }

        b(byte[] bArr, int i, int i2, boolean z, boolean z2) {
            this.f9916a = bArr;
            if (bArr == null) {
                throw new NullPointerException("addBuffer params error, buffer is null ");
            } else if (i2 < 0 || i < 0) {
                throw new IllegalArgumentException("addBuffer params error,offset < 0 or len <0");
            } else if (i2 + i <= bArr.length) {
                this.f9916a = new byte[i2];
                System.arraycopy(bArr, i, this.f9916a, 0, i2);
                this.b = z;
                this.c = z2;
                this.d = C0082c.DATA;
            } else {
                throw new IllegalArgumentException("addBuffer params error,offset + len > bytes.length");
            }
        }
    }

    /* renamed from: com.xiaomi.ai.c$c  reason: collision with other inner class name */
    private enum C0082c {
        DATA,
        FLAG_ABANDON_START,
        FLAG_ABANDON_END,
        NLP_DATA,
        VAD_BEGIN
    }

    private class d implements MibrainRequestListener {
        private MibrainRequest b;
        private a c;
        private boolean d = false;
        private SpeechEngine.ParamBuilder e;
        /* access modifiers changed from: private */
        public volatile SpeechEngine.ProcessStage f;
        private String g;

        d(SpeechEngine.ParamBuilder paramBuilder, MibrainRequest mibrainRequest) {
            this.b = mibrainRequest;
            this.e = paramBuilder;
            this.f = SpeechEngine.ProcessStage.STAGE_REQUESTING;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x015b, code lost:
            if ("TRANSACTION_BEGIN".equals(r2) == false) goto L_0x015d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x015d, code lost:
            r5 = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.xiaomi.ai.SpeechError a(org.json.JSONObject r7) {
            /*
                r6 = this;
                com.xiaomi.ai.SpeechEngine$ParamBuilder r0 = r6.e
                boolean r0 = r0.p
                r1 = 0
                if (r0 == 0) goto L_0x000e
                com.xiaomi.ai.SpeechEngine$ParamBuilder r0 = r6.e
                com.xiaomi.ai.EventRequest r0 = r0.q
                if (r0 == 0) goto L_0x000e
                return r1
            L_0x000e:
                r0 = 2
                java.lang.String r2 = "meta"
                org.json.JSONObject r2 = r7.getJSONObject(r2)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r3 = "type"
                java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r3 = "ERROR"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                r4 = 1
                if (r3 == 0) goto L_0x0044
                java.lang.String r1 = "response"
                org.json.JSONObject r7 = r7.getJSONObject(r1)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r1 = "error_code"
                int r1 = r7.getInt(r1)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r2 = "error_msg"
                java.lang.String r7 = r7.getString(r2)     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechError r2 = new com.xiaomi.ai.SpeechError     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r6.f     // Catch:{ JSONException -> 0x019f }
                r2.<init>(r3)     // Catch:{ JSONException -> 0x019f }
                r2.T = r4     // Catch:{ JSONException -> 0x019f }
                r2.U = r1     // Catch:{ JSONException -> 0x019f }
                r2.V = r7     // Catch:{ JSONException -> 0x019f }
                return r2
            L_0x0044:
                java.lang.String r3 = "TRANSACTION_BEGIN"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_FINAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_QUERY"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_DEL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_REG"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_REG_PARTIAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_VOR_REG_FINAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_ASR_PARTIAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_ASR_FINAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_NLP"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_TTS_BEGIN"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_TTS_END"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "TRANSACTION_END"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r3 = "RESULT_FILE_STORED"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x00cb
                java.lang.String r2 = "MiSpeechSDK:MiAiEngine"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x019f }
                r3.<init>()     // Catch:{ JSONException -> 0x019f }
                java.lang.String r4 = "unkown type result = "
                r3.append(r4)     // Catch:{ JSONException -> 0x019f }
                r3.append(r7)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r7 = r3.toString()     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.utils.Log.h(r2, r7)     // Catch:{ JSONException -> 0x019f }
                return r1
            L_0x00cb:
                int[] r3 = com.xiaomi.ai.k.f9927a     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r5 = r6.f     // Catch:{ JSONException -> 0x019f }
                int r5 = r5.ordinal()     // Catch:{ JSONException -> 0x019f }
                r3 = r3[r5]     // Catch:{ JSONException -> 0x019f }
                r5 = 0
                switch(r3) {
                    case 1: goto L_0x0155;
                    case 2: goto L_0x013c;
                    case 3: goto L_0x012a;
                    case 4: goto L_0x0121;
                    case 5: goto L_0x0108;
                    case 6: goto L_0x00ef;
                    case 7: goto L_0x00e5;
                    case 8: goto L_0x00db;
                    default: goto L_0x00d9;
                }     // Catch:{ JSONException -> 0x019f }
            L_0x00d9:
                goto L_0x015e
            L_0x00db:
                java.lang.String r3 = "RESULT_VOR_QUERY"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x00e5:
                java.lang.String r3 = "RESULT_VOR_DEL"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x00ef:
                java.lang.String r3 = "RESULT_TTS_BEGIN"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "RESULT_TTS_END"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "TRANSACTION_END"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x0108:
                java.lang.String r3 = "RESULT_VOR_REG_FINAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "RESULT_VOR_REG_PARTIAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "TRANSACTION_END"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x0121:
                java.lang.String r3 = "RESULT_VOR_FINAL"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x012a:
                java.lang.String r3 = "RESULT_NLP"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                r2 = r2 ^ r4
                boolean r3 = r6.d     // Catch:{ JSONException -> 0x019f }
                if (r3 == 0) goto L_0x013a
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_TTS     // Catch:{ JSONException -> 0x019f }
                r6.f = r2     // Catch:{ JSONException -> 0x019f }
                goto L_0x015e
            L_0x013a:
                r5 = r2
                goto L_0x015e
            L_0x013c:
                java.lang.String r3 = "RESULT_ASR_PARTIAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "RESULT_ASR_FINAL"
                boolean r3 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r3 != 0) goto L_0x015e
                java.lang.String r3 = "RESULT_FILE_STORED"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
                goto L_0x015d
            L_0x0155:
                java.lang.String r3 = "TRANSACTION_BEGIN"
                boolean r2 = r3.equals(r2)     // Catch:{ JSONException -> 0x019f }
                if (r2 != 0) goto L_0x015e
            L_0x015d:
                r5 = 1
            L_0x015e:
                if (r5 == 0) goto L_0x0171
                com.xiaomi.ai.SpeechError r1 = new com.xiaomi.ai.SpeechError     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r7 = r6.f     // Catch:{ JSONException -> 0x019f }
                r1.<init>(r7)     // Catch:{ JSONException -> 0x019f }
                r1.T = r0     // Catch:{ JSONException -> 0x019f }
                r7 = 3
                r1.U = r7     // Catch:{ JSONException -> 0x019f }
                java.lang.String r7 = "Internal state error"
            L_0x016e:
                r1.V = r7     // Catch:{ JSONException -> 0x019f }
                goto L_0x01b8
            L_0x0171:
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = r6.f     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_NLP     // Catch:{ JSONException -> 0x019f }
                if (r2 != r3) goto L_0x01b8
                java.lang.String r2 = "response"
                org.json.JSONObject r7 = r7.getJSONObject(r2)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r2 = "status"
                org.json.JSONObject r7 = r7.getJSONObject(r2)     // Catch:{ JSONException -> 0x019f }
                java.lang.String r2 = "code"
                int r2 = r7.getInt(r2)     // Catch:{ JSONException -> 0x019f }
                r3 = 200(0xc8, float:2.8E-43)
                if (r2 == r3) goto L_0x01b8
                com.xiaomi.ai.SpeechError r1 = new com.xiaomi.ai.SpeechError     // Catch:{ JSONException -> 0x019f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r6.f     // Catch:{ JSONException -> 0x019f }
                r1.<init>(r3)     // Catch:{ JSONException -> 0x019f }
                r1.T = r4     // Catch:{ JSONException -> 0x019f }
                r1.U = r2     // Catch:{ JSONException -> 0x019f }
                java.lang.String r2 = "error_details"
                java.lang.String r7 = r7.optString(r2)     // Catch:{ JSONException -> 0x019f }
                goto L_0x016e
            L_0x019f:
                r7 = move-exception
                com.xiaomi.ai.c r1 = com.xiaomi.ai.c.this
                java.lang.String r2 = "JSONException: "
                r1.a((java.lang.CharSequence) r2, (java.lang.Throwable) r7)
                com.xiaomi.ai.SpeechError r1 = new com.xiaomi.ai.SpeechError
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = r6.f
                r1.<init>(r2)
                r1.T = r0
                r1.U = r0
                java.lang.String r7 = r7.toString()
                r1.V = r7
            L_0x01b8:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.c.d.a(org.json.JSONObject):com.xiaomi.ai.SpeechError");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: com.xiaomi.ai.SpeechResult} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: com.xiaomi.ai.api.common.Instruction[]} */
        /* JADX WARNING: type inference failed for: r6v0 */
        /* JADX WARNING: type inference failed for: r5v1 */
        /* JADX WARNING: type inference failed for: r5v3 */
        /* JADX WARNING: type inference failed for: r6v3 */
        /* JADX WARNING: type inference failed for: r5v5, types: [int] */
        /* JADX WARNING: type inference failed for: r5v9 */
        /* JADX WARNING: type inference failed for: r6v7 */
        /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
            r10 = r8.f9918a;
            r2 = "RESULT_FILE_STORED stage: " + r8.f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
            r10 = r8.f9918a;
            r2 = "RESULT_NLP stage: " + r8.f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:334:?, code lost:
            com.xiaomi.ai.c.a(r8.f9918a, (java.lang.CharSequence) "RESULT_TTS_END stage: " + r8.f);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:335:0x0536, code lost:
            if ("RESULT_TTS_END".equals(r3) != false) goto L_0x053e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:337:0x053c, code lost:
            if (r8.f != com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_TTS) goto L_0x053f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:338:0x053e, code lost:
            r5 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:339:0x053f, code lost:
            r10 = com.xiaomi.ai.c.e(r8.f9918a);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:340:0x0545, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:343:0x054a, code lost:
            if (b(r9) == false) goto L_0x054e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:344:0x054c, code lost:
            monitor-exit(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:345:0x054d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:346:0x054e, code lost:
            r8.f9918a.g(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:347:0x0553, code lost:
            monitor-exit(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            r10 = r8.f9918a;
            r2 = "TRANSACTION_BEGIN stage: " + r8.f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:395:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
            r10 = r8.f9918a;
            r2 = "TRESULT_ASR_FINAL stage: " + r8.f;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(com.xiaomi.ai.mibrain.MibrainRequest r9, java.lang.String r10) {
            /*
                r8 = this;
                boolean r0 = r8.b(r9)
                if (r0 == 0) goto L_0x0007
                return
            L_0x0007:
                r0 = 1
                r1 = 2
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x055b }
                r2.<init>(r10)     // Catch:{ Exception -> 0x055b }
                r8.a((com.xiaomi.ai.mibrain.MibrainRequest) r9, (org.json.JSONObject) r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechError r3 = r8.a((org.json.JSONObject) r2)     // Catch:{ Exception -> 0x055b }
                if (r3 == 0) goto L_0x004c
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = "Text received: ERROR!!!"
                java.lang.CharSequence r4 = com.xiaomi.ai.utils.h.a(r4)     // Catch:{ Exception -> 0x055b }
                r2.a((java.lang.CharSequence) r4)     // Catch:{ Exception -> 0x055b }
                java.lang.String r10 = com.xiaomi.ai.utils.a.a((java.lang.String) r10)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                r4 = -65536(0xffffffffffff0000, float:NaN)
                java.lang.CharSequence r10 = com.xiaomi.ai.utils.h.a(r10, r4)     // Catch:{ Exception -> 0x055b }
                r2.a((java.lang.CharSequence) r10)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r2 = r8.b(r9)     // Catch:{ all -> 0x0049 }
                if (r2 == 0) goto L_0x0040
                monitor-exit(r10)     // Catch:{ all -> 0x0049 }
                return
            L_0x0040:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0049 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r4 = r8.e     // Catch:{ all -> 0x0049 }
                r2.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r4, (com.xiaomi.ai.SpeechError) r3)     // Catch:{ all -> 0x0049 }
                monitor-exit(r10)     // Catch:{ all -> 0x0049 }
                return
            L_0x0049:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0049 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x004c:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = "Text received: "
                java.lang.CharSequence r4 = com.xiaomi.ai.utils.h.a(r4)     // Catch:{ Exception -> 0x055b }
                r3.a((java.lang.CharSequence) r4)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.String r10 = com.xiaomi.ai.utils.a.a((java.lang.String) r10)     // Catch:{ Exception -> 0x055b }
                r3.a((java.lang.CharSequence) r10)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                if (r10 != 0) goto L_0x006f
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ResultParser r3 = new com.xiaomi.ai.SpeechEngine$ResultParser     // Catch:{ Exception -> 0x055b }
                r3.<init>()     // Catch:{ Exception -> 0x055b }
                r10.t = r3     // Catch:{ Exception -> 0x055b }
            L_0x006f:
                java.lang.String r10 = "meta"
                org.json.JSONObject r10 = r2.getJSONObject(r10)     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "type"
                java.lang.String r3 = r10.getString(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = "TRANSACTION_BEGIN"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x00bc
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r2 = r8.b(r9)     // Catch:{ all -> 0x00b9 }
                if (r2 == 0) goto L_0x0092
                monitor-exit(r10)     // Catch:{ all -> 0x00b9 }
                return
            L_0x0092:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x00b9 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x00b9 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x00b9 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = r2.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x00b9 }
                r8.f = r2     // Catch:{ all -> 0x00b9 }
                monitor-exit(r10)     // Catch:{ all -> 0x00b9 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x055b }
                r2.<init>()     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "TRANSACTION_BEGIN stage: "
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r8.f     // Catch:{ Exception -> 0x055b }
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x055b }
            L_0x00b4:
                r10.a((java.lang.CharSequence) r2)     // Catch:{ Exception -> 0x055b }
                goto L_0x058b
            L_0x00b9:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00b9 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x00bc:
                java.lang.String r4 = "RESULT_ASR_PARTIAL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                r5 = 12
                if (r4 == 0) goto L_0x0100
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.b(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = r10.f9906a     // Catch:{ all -> 0x00fd }
                if (r3 != 0) goto L_0x00ed
                com.xiaomi.ai.SpeechError r10 = new com.xiaomi.ai.SpeechError     // Catch:{ all -> 0x00fd }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_ASR     // Catch:{ all -> 0x00fd }
                r10.<init>(r3)     // Catch:{ all -> 0x00fd }
                r10.U = r5     // Catch:{ all -> 0x00fd }
                r10.T = r1     // Catch:{ all -> 0x00fd }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x00fd }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r4 = r8.e     // Catch:{ all -> 0x00fd }
                r3.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r4, (com.xiaomi.ai.SpeechError) r10)     // Catch:{ all -> 0x00fd }
                monitor-exit(r2)     // Catch:{ all -> 0x00fd }
                return
            L_0x00ed:
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x00fd }
                if (r3 == 0) goto L_0x00f5
                monitor-exit(r2)     // Catch:{ all -> 0x00fd }
                return
            L_0x00f5:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x00fd }
                r3.f((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x00fd }
                monitor-exit(r2)     // Catch:{ all -> 0x00fd }
                goto L_0x058b
            L_0x00fd:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00fd }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x0100:
                java.lang.String r4 = "RESULT_ASR_FINAL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x016b
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.b(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = r10.f9906a     // Catch:{ all -> 0x0168 }
                if (r3 != 0) goto L_0x012f
                com.xiaomi.ai.SpeechError r10 = new com.xiaomi.ai.SpeechError     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_ASR     // Catch:{ all -> 0x0168 }
                r10.<init>(r3)     // Catch:{ all -> 0x0168 }
                r10.U = r5     // Catch:{ all -> 0x0168 }
                r10.T = r1     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r4 = r8.e     // Catch:{ all -> 0x0168 }
                r3.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r4, (com.xiaomi.ai.SpeechError) r10)     // Catch:{ all -> 0x0168 }
                monitor-exit(r2)     // Catch:{ all -> 0x0168 }
                return
            L_0x012f:
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x0168 }
                if (r3 == 0) goto L_0x0137
                monitor-exit(r2)     // Catch:{ all -> 0x0168 }
                return
            L_0x0137:
                java.lang.String r3 = r10.f9906a     // Catch:{ all -> 0x0168 }
                boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0168 }
                r8.d = r3     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0168 }
                r3.a((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x0168 }
                r8.f = r10     // Catch:{ all -> 0x0168 }
                monitor-exit(r2)     // Catch:{ all -> 0x0168 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x055b }
                r2.<init>()     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "TRESULT_ASR_FINAL stage: "
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r8.f     // Catch:{ Exception -> 0x055b }
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x055b }
                goto L_0x00b4
            L_0x0168:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0168 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x016b:
                java.lang.String r4 = "RESULT_FILE_STORED"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                r6 = 0
                if (r4 == 0) goto L_0x01d0
                java.lang.String r3 = "request_id"
                java.lang.String r10 = r10.optString(r3, r6)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r3 = r3.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r3)     // Catch:{ Exception -> 0x055b }
                if (r10 != 0) goto L_0x0199
                com.xiaomi.ai.SpeechError r10 = new com.xiaomi.ai.SpeechError     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_ASR     // Catch:{ all -> 0x0197 }
                r10.<init>(r2)     // Catch:{ all -> 0x0197 }
                r10.U = r5     // Catch:{ all -> 0x0197 }
                r10.T = r1     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r4 = r8.e     // Catch:{ all -> 0x0197 }
                r2.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r4, (com.xiaomi.ai.SpeechError) r10)     // Catch:{ all -> 0x0197 }
                monitor-exit(r3)     // Catch:{ all -> 0x0197 }
                return
            L_0x0197:
                r10 = move-exception
                goto L_0x01ce
            L_0x0199:
                boolean r4 = r8.b(r9)     // Catch:{ all -> 0x0197 }
                if (r4 == 0) goto L_0x01a1
                monitor-exit(r3)     // Catch:{ all -> 0x0197 }
                return
            L_0x01a1:
                com.xiaomi.ai.c r4 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0197 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0197 }
                r4.c(r10, r2)     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r2 = r8.e     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r2, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x0197 }
                r8.f = r10     // Catch:{ all -> 0x0197 }
                monitor-exit(r3)     // Catch:{ all -> 0x0197 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x055b }
                r2.<init>()     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "RESULT_FILE_STORED stage: "
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r8.f     // Catch:{ Exception -> 0x055b }
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x055b }
                goto L_0x00b4
            L_0x01ce:
                monitor-exit(r3)     // Catch:{ all -> 0x0197 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x01d0:
                java.lang.String r4 = "RESULT_NLP"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                r5 = 0
                if (r4 == 0) goto L_0x0255
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.a(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = r10.e     // Catch:{ Exception -> 0x055b }
                java.lang.String unused = r3.an = r4     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "response"
                org.json.JSONObject r3 = r2.getJSONObject(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = "instructions"
                boolean r3 = r3.has(r4)     // Catch:{ Exception -> 0x055b }
                if (r3 == 0) goto L_0x01fe
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r3 = r3.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.api.common.Instruction[] r6 = r3.g(r2)     // Catch:{ Exception -> 0x055b }
            L_0x01fe:
                if (r6 == 0) goto L_0x0202
                r2 = 1
                goto L_0x0203
            L_0x0202:
                r2 = 0
            L_0x0203:
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.NlpRequest r3 = r3.d     // Catch:{ Exception -> 0x055b }
                if (r3 == 0) goto L_0x0212
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.NlpRequest r3 = r3.d     // Catch:{ Exception -> 0x055b }
                boolean r3 = r3.q     // Catch:{ Exception -> 0x055b }
                if (r3 == 0) goto L_0x0212
                r2 = 0
            L_0x0212:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r3 = r3.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r3)     // Catch:{ Exception -> 0x055b }
                boolean r4 = r8.b(r9)     // Catch:{ all -> 0x0252 }
                if (r4 == 0) goto L_0x0221
                monitor-exit(r3)     // Catch:{ all -> 0x0252 }
                return
            L_0x0221:
                if (r2 == 0) goto L_0x0229
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0252 }
                r2.a((com.xiaomi.ai.api.common.Instruction[]) r6, (com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x0252 }
                goto L_0x022e
            L_0x0229:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0252 }
                r2.g((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x0252 }
            L_0x022e:
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0252 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r2 = r8.e     // Catch:{ all -> 0x0252 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x0252 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r2, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x0252 }
                r8.f = r10     // Catch:{ all -> 0x0252 }
                monitor-exit(r3)     // Catch:{ all -> 0x0252 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x055b }
                r2.<init>()     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "RESULT_NLP stage: "
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r8.f     // Catch:{ Exception -> 0x055b }
                r2.append(r3)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x055b }
                goto L_0x00b4
            L_0x0252:
                r10 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0252 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x0255:
                java.lang.String r4 = "RESULT_TTS_BEGIN"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x0318
            L_0x025d:
                r10 = 10
                if (r5 >= r10) goto L_0x0286
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                android.media.AudioManager r10 = r10.ai     // Catch:{ Exception -> 0x055b }
                boolean r10 = r10.isBluetoothScoOn()     // Catch:{ Exception -> 0x055b }
                if (r10 == 0) goto L_0x0286
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0283 }
                java.lang.Object r3 = r3.al     // Catch:{ all -> 0x0283 }
                r6 = 100
                r3.wait(r6)     // Catch:{ all -> 0x0283 }
                monitor-exit(r10)     // Catch:{ all -> 0x0283 }
                int r5 = r5 + 1
                goto L_0x025d
            L_0x0283:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0283 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x0286:
                java.lang.String r10 = "meta"
                org.json.JSONObject r10 = r2.getJSONObject(r10)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = "tts"
                org.json.JSONObject r10 = r10.getJSONObject(r2)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = "codec"
                java.lang.String r10 = r10.optString(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x0315 }
                if (r3 == 0) goto L_0x02a7
                monitor-exit(r2)     // Catch:{ all -> 0x0315 }
                return
            L_0x02a7:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                java.util.concurrent.atomic.AtomicBoolean r3 = r3.aw     // Catch:{ all -> 0x0315 }
                boolean r3 = r3.get()     // Catch:{ all -> 0x0315 }
                if (r3 == 0) goto L_0x030d
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k r4 = new com.xiaomi.ai.streamplayer.k     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r5 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                int r5 = r5.M     // Catch:{ all -> 0x0315 }
                r4.<init>(r5)     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k unused = r3.af = r4     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k r3 = r3.af     // Catch:{ all -> 0x0315 }
                r3.a((java.lang.String) r10)     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k r10 = r10.af     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k$a r3 = r3.ax     // Catch:{ all -> 0x0315 }
                r10.a((com.xiaomi.ai.streamplayer.k.a) r3)     // Catch:{ all -> 0x0315 }
                java.lang.String r10 = "MiSpeechSDK:MiAiEngine"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0315 }
                r3.<init>()     // Catch:{ all -> 0x0315 }
                java.lang.String r4 = "setTTSPlayMode"
                r3.append(r4)     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r4 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                int r4 = r4.au     // Catch:{ all -> 0x0315 }
                r3.append(r4)     // Catch:{ all -> 0x0315 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.utils.Log.f(r10, r3)     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k r10 = r10.af     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                int r3 = r3.au     // Catch:{ all -> 0x0315 }
                r10.a((int) r3)     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                com.xiaomi.ai.streamplayer.k r10 = r10.af     // Catch:{ all -> 0x0315 }
                r10.a()     // Catch:{ all -> 0x0315 }
            L_0x030d:
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0315 }
                r10.y()     // Catch:{ all -> 0x0315 }
                monitor-exit(r2)     // Catch:{ all -> 0x0315 }
                goto L_0x058b
            L_0x0315:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0315 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x0318:
                java.lang.String r4 = "RESULT_TTS_END"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 != 0) goto L_0x04eb
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r7 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_TTS     // Catch:{ Exception -> 0x055b }
                if (r4 != r7) goto L_0x0330
                java.lang.String r4 = "TRANSACTION_END"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x0330
                goto L_0x04eb
            L_0x0330:
                java.lang.String r4 = "RESULT_TTS_URL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x035c
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x0359 }
                if (r3 == 0) goto L_0x0347
                monitor-exit(r10)     // Catch:{ all -> 0x0359 }
                return
            L_0x0347:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0359 }
                java.lang.String r4 = "response"
                org.json.JSONObject r2 = r2.getJSONObject(r4)     // Catch:{ all -> 0x0359 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0359 }
                r3.i(r2)     // Catch:{ all -> 0x0359 }
                monitor-exit(r10)     // Catch:{ all -> 0x0359 }
                goto L_0x058b
            L_0x0359:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0359 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x035c:
                java.lang.String r4 = "RESULT_VOR_REG_FINAL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x0392
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.d(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x038f }
                if (r3 == 0) goto L_0x037b
                monitor-exit(r2)     // Catch:{ all -> 0x038f }
                return
            L_0x037b:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x038f }
                r3.b((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x038f }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x038f }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x038f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x038f }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x038f }
                r8.f = r10     // Catch:{ all -> 0x038f }
                monitor-exit(r2)     // Catch:{ all -> 0x038f }
                goto L_0x058b
            L_0x038f:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x038f }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x0392:
                java.lang.String r4 = "RESULT_VOR_FINAL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x03c8
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.c(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x03c5 }
                if (r3 == 0) goto L_0x03b1
                monitor-exit(r2)     // Catch:{ all -> 0x03c5 }
                return
            L_0x03b1:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x03c5 }
                r3.e((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x03c5 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x03c5 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x03c5 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x03c5 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x03c5 }
                r8.f = r10     // Catch:{ all -> 0x03c5 }
                monitor-exit(r2)     // Catch:{ all -> 0x03c5 }
                goto L_0x058b
            L_0x03c5:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x03c5 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x03c8:
                java.lang.String r4 = "RESULT_VOR_REG_PARTIAL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x03fe
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.d(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x03fb }
                if (r3 == 0) goto L_0x03e7
                monitor-exit(r2)     // Catch:{ all -> 0x03fb }
                return
            L_0x03e7:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x03fb }
                r3.b((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x03fb }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x03fb }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x03fb }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x03fb }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x03fb }
                r8.f = r10     // Catch:{ all -> 0x03fb }
                monitor-exit(r2)     // Catch:{ all -> 0x03fb }
                goto L_0x058b
            L_0x03fb:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x03fb }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x03fe:
                java.lang.String r4 = "RESULT_VOR_DEL"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x0434
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.e(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x0431 }
                if (r3 == 0) goto L_0x041d
                monitor-exit(r2)     // Catch:{ all -> 0x0431 }
                return
            L_0x041d:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0431 }
                r3.c((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x0431 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0431 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x0431 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x0431 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x0431 }
                r8.f = r10     // Catch:{ all -> 0x0431 }
                monitor-exit(r2)     // Catch:{ all -> 0x0431 }
                goto L_0x058b
            L_0x0431:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0431 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x0434:
                java.lang.String r4 = "RESULT_VOR_QUERY"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x046a
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechResult r10 = r10.f(r2)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x0467 }
                if (r3 == 0) goto L_0x0453
                monitor-exit(r2)     // Catch:{ all -> 0x0467 }
                return
            L_0x0453:
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0467 }
                r3.d((com.xiaomi.ai.SpeechResult) r10)     // Catch:{ all -> 0x0467 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0467 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r3 = r8.e     // Catch:{ all -> 0x0467 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ all -> 0x0467 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r10.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r3, (com.xiaomi.ai.SpeechEngine.ProcessStage) r4)     // Catch:{ all -> 0x0467 }
                r8.f = r10     // Catch:{ all -> 0x0467 }
                monitor-exit(r2)     // Catch:{ all -> 0x0467 }
                goto L_0x058b
            L_0x0467:
                r10 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0467 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x046a:
                java.lang.String r4 = "SERVICE_EVENT"
                boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r4 == 0) goto L_0x04a9
                java.lang.String r3 = "event_level"
                boolean r3 = r10.has(r3)     // Catch:{ Exception -> 0x055b }
                if (r3 == 0) goto L_0x0482
                java.lang.String r3 = "event_level"
                java.lang.String r10 = r10.getString(r3)     // Catch:{ Exception -> 0x055b }
                r8.g = r10     // Catch:{ Exception -> 0x055b }
            L_0x0482:
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r3 = r8.b(r9)     // Catch:{ all -> 0x04a6 }
                if (r3 == 0) goto L_0x0491
                monitor-exit(r10)     // Catch:{ all -> 0x04a6 }
                return
            L_0x0491:
                com.xiaomi.ai.ServiceEvent r3 = new com.xiaomi.ai.ServiceEvent     // Catch:{ all -> 0x04a6 }
                java.lang.String r4 = r8.g     // Catch:{ all -> 0x04a6 }
                java.lang.String r5 = "response"
                org.json.JSONObject r2 = r2.getJSONObject(r5)     // Catch:{ all -> 0x04a6 }
                r3.<init>(r4, r2)     // Catch:{ all -> 0x04a6 }
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x04a6 }
                r2.a((com.xiaomi.ai.ServiceEvent) r3)     // Catch:{ all -> 0x04a6 }
                monitor-exit(r10)     // Catch:{ all -> 0x04a6 }
                goto L_0x058b
            L_0x04a6:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x04a6 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x04a9:
                java.lang.String r10 = "INSTRUCTION"
                boolean r10 = r10.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r10 == 0) goto L_0x058b
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.q r10 = r10.t     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.api.common.Instruction[] r10 = r10.g(r2)     // Catch:{ Exception -> 0x055b }
                java.lang.String r3 = "response"
                org.json.JSONObject r3 = r2.getJSONObject(r3)     // Catch:{ JSONException -> 0x04d1 }
                java.lang.String r4 = "answer"
                boolean r3 = r3.has(r4)     // Catch:{ JSONException -> 0x04d1 }
                if (r3 == 0) goto L_0x04d5
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ JSONException -> 0x04d1 }
                com.xiaomi.ai.q r3 = r3.t     // Catch:{ JSONException -> 0x04d1 }
                com.xiaomi.ai.SpeechResult r2 = r3.a(r2)     // Catch:{ JSONException -> 0x04d1 }
                r6 = r2
                goto L_0x04d5
            L_0x04d1:
                r2 = move-exception
                r2.printStackTrace()     // Catch:{ Exception -> 0x055b }
            L_0x04d5:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r2 = r2.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r2)     // Catch:{ Exception -> 0x055b }
                if (r10 == 0) goto L_0x04e6
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x04e4 }
                r3.a((com.xiaomi.ai.api.common.Instruction[]) r10, (com.xiaomi.ai.SpeechResult) r6)     // Catch:{ all -> 0x04e4 }
                goto L_0x04e6
            L_0x04e4:
                r10 = move-exception
                goto L_0x04e9
            L_0x04e6:
                monitor-exit(r2)     // Catch:{ all -> 0x04e4 }
                goto L_0x058b
            L_0x04e9:
                monitor-exit(r2)     // Catch:{ all -> 0x04e4 }
                throw r10     // Catch:{ Exception -> 0x055b }
            L_0x04eb:
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r2 = r8.b(r9)     // Catch:{ all -> 0x0558 }
                if (r2 == 0) goto L_0x04fa
                monitor-exit(r10)     // Catch:{ all -> 0x0558 }
                return
            L_0x04fa:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.streamplayer.k r2 = r2.af     // Catch:{ all -> 0x0558 }
                if (r2 == 0) goto L_0x050b
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.streamplayer.k r2 = r2.af     // Catch:{ all -> 0x0558 }
                r2.b()     // Catch:{ all -> 0x0558 }
            L_0x050b:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r4 = r8.e     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r6 = r8.f     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = r2.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r4, (com.xiaomi.ai.SpeechEngine.ProcessStage) r6)     // Catch:{ all -> 0x0558 }
                r8.f = r2     // Catch:{ all -> 0x0558 }
                monitor-exit(r10)     // Catch:{ all -> 0x0558 }
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x055b }
                r2.<init>()     // Catch:{ Exception -> 0x055b }
                java.lang.String r4 = "RESULT_TTS_END stage: "
                r2.append(r4)     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r8.f     // Catch:{ Exception -> 0x055b }
                r2.append(r4)     // Catch:{ Exception -> 0x055b }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x055b }
                r10.a((java.lang.CharSequence) r2)     // Catch:{ Exception -> 0x055b }
                java.lang.String r10 = "RESULT_TTS_END"
                boolean r10 = r10.equals(r3)     // Catch:{ Exception -> 0x055b }
                if (r10 != 0) goto L_0x053e
                com.xiaomi.ai.SpeechEngine$ProcessStage r10 = r8.f     // Catch:{ Exception -> 0x055b }
                com.xiaomi.ai.SpeechEngine$ProcessStage r2 = com.xiaomi.ai.SpeechEngine.ProcessStage.STAGE_TTS     // Catch:{ Exception -> 0x055b }
                if (r10 != r2) goto L_0x053f
            L_0x053e:
                r5 = 1
            L_0x053f:
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this     // Catch:{ Exception -> 0x055b }
                java.lang.Object r10 = r10.al     // Catch:{ Exception -> 0x055b }
                monitor-enter(r10)     // Catch:{ Exception -> 0x055b }
                boolean r2 = r8.b(r9)     // Catch:{ all -> 0x0555 }
                if (r2 == 0) goto L_0x054e
                monitor-exit(r10)     // Catch:{ all -> 0x0555 }
                return
            L_0x054e:
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x0555 }
                r2.g((boolean) r5)     // Catch:{ all -> 0x0555 }
                monitor-exit(r10)     // Catch:{ all -> 0x0555 }
                goto L_0x058b
            L_0x0555:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0555 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x0558:
                r2 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x0558 }
                throw r2     // Catch:{ Exception -> 0x055b }
            L_0x055b:
                r10 = move-exception
                com.xiaomi.ai.c r2 = com.xiaomi.ai.c.this
                java.lang.String r3 = "Exception in onTextMessage: "
                r2.a((java.lang.CharSequence) r3, (java.lang.Throwable) r10)
                com.xiaomi.ai.SpeechError r2 = new com.xiaomi.ai.SpeechError
                com.xiaomi.ai.SpeechEngine$ProcessStage r3 = r8.f
                r2.<init>(r3)
                r2.T = r1
                r2.U = r0
                java.lang.String r10 = r10.toString()
                r2.V = r10
                com.xiaomi.ai.c r10 = com.xiaomi.ai.c.this
                java.lang.Object r10 = r10.al
                monitor-enter(r10)
                boolean r9 = r8.b(r9)     // Catch:{ all -> 0x058c }
                if (r9 == 0) goto L_0x0583
                monitor-exit(r10)     // Catch:{ all -> 0x058c }
                return
            L_0x0583:
                com.xiaomi.ai.c r9 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x058c }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r0 = r8.e     // Catch:{ all -> 0x058c }
                r9.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r0, (com.xiaomi.ai.SpeechError) r2)     // Catch:{ all -> 0x058c }
                monitor-exit(r10)     // Catch:{ all -> 0x058c }
            L_0x058b:
                return
            L_0x058c:
                r9 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x058c }
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.c.d.a(com.xiaomi.ai.mibrain.MibrainRequest, java.lang.String):void");
        }

        private void a(MibrainRequest mibrainRequest, JSONObject jSONObject) {
            try {
                a aVar = this.c;
                if (aVar != null) {
                    String string = jSONObject.getJSONObject("meta").getString("type");
                    if ("RESULT_ASR_VAD".equals(string)) {
                        if (jSONObject.getJSONObject(CBConstant.RESPONSE).getBoolean("is_final")) {
                            synchronized (c.this.al) {
                                if (!b(mibrainRequest)) {
                                    aVar.f();
                                }
                            }
                        }
                    } else if ("RESULT_ASR_FINAL".equals(string) || "RESULT_FILE_STORED".equals(string)) {
                        synchronized (c.this.al) {
                            if (!b(mibrainRequest)) {
                                aVar.f();
                            }
                        }
                    }
                }
            } catch (JSONException e2) {
                c.this.a((CharSequence) "JSONException: ", (Throwable) e2);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
            if (r3 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
            r3.a(r4, 0, r4.length);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(com.xiaomi.ai.mibrain.MibrainRequest r3, byte[] r4) {
            /*
                r2 = this;
                com.xiaomi.ai.c r0 = com.xiaomi.ai.c.this
                java.lang.Object r0 = r0.al
                monitor-enter(r0)
                boolean r3 = r2.b(r3)     // Catch:{ all -> 0x003a }
                if (r3 != 0) goto L_0x0038
                com.xiaomi.ai.c r3 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x003a }
                com.xiaomi.ai.streamplayer.k r3 = r3.af     // Catch:{ all -> 0x003a }
                if (r4 != 0) goto L_0x002f
                com.xiaomi.ai.SpeechError r3 = new com.xiaomi.ai.SpeechError     // Catch:{ all -> 0x003a }
                com.xiaomi.ai.SpeechEngine$ProcessStage r4 = r2.f     // Catch:{ all -> 0x003a }
                r3.<init>(r4)     // Catch:{ all -> 0x003a }
                r4 = 2
                r3.T = r4     // Catch:{ all -> 0x003a }
                r4 = 6
                r3.U = r4     // Catch:{ all -> 0x003a }
                java.lang.String r4 = "resolveBinaryResult binary is null"
                r3.V = r4     // Catch:{ all -> 0x003a }
                com.xiaomi.ai.c r4 = com.xiaomi.ai.c.this     // Catch:{ all -> 0x003a }
                com.xiaomi.ai.SpeechEngine$ParamBuilder r1 = r2.e     // Catch:{ all -> 0x003a }
                r4.a((com.xiaomi.ai.SpeechEngine.ParamBuilder) r1, (com.xiaomi.ai.SpeechError) r3)     // Catch:{ all -> 0x003a }
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                return
            L_0x002f:
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                if (r3 == 0) goto L_0x0037
                r0 = 0
                int r1 = r4.length
                r3.a(r4, r0, r1)
            L_0x0037:
                return
            L_0x0038:
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                return
            L_0x003a:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x003a }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.c.d.a(com.xiaomi.ai.mibrain.MibrainRequest, byte[]):void");
        }

        private boolean a(MibrainRequest mibrainRequest) {
            if (mibrainRequest == this.b) {
                return true;
            }
            SpeechError speechError = new SpeechError(this.f);
            speechError.T = 2;
            speechError.U = -1;
            speechError.V = "FATAL ERROR isResponseCorrect check failed !!";
            c.this.a(this.e, speechError);
            return false;
        }

        private boolean b(MibrainRequest mibrainRequest) {
            boolean z;
            synchronized (c.this.al) {
                if (!c.this.p()) {
                    if (c.this.aj == mibrainRequest) {
                        z = false;
                    }
                }
                z = true;
            }
            return z;
        }

        public String a() {
            return this.g;
        }

        public void a(a aVar) {
            this.c = aVar;
        }

        public void onEventTrack(String str) {
            if (c.this.G != null) {
                c.this.G.a(str);
            } else {
                Log.h(c.Y, "no Need onEventTrack callback ,skip");
            }
        }

        public void onInstruction(MibrainRequest mibrainRequest, String str) {
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public String onNeedUpdateToken() {
            if (c.this.N != null) {
                Log.h(c.Y, "Need UpdateToken");
                return c.this.N.a();
            }
            Log.h(c.Y, "no Need UpdateToken callback ,skip");
            return null;
        }

        public void onRequestASRResult(MibrainRequest mibrainRequest, String str, boolean z) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestError(MibrainRequest mibrainRequest, String str, int i, int i2) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            synchronized (c.this.al) {
                if (!b(mibrainRequest)) {
                    SpeechError speechError = new SpeechError(this.f);
                    if (i == 1) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            JSONObject jSONObject2 = jSONObject.getJSONObject(CBConstant.RESPONSE);
                            speechError.U = jSONObject2.getInt("error_code");
                            speechError.V = jSONObject2.getString("error_msg");
                            speechError.W = jSONObject.getJSONObject("meta").getString("request_id");
                        } catch (JSONException e2) {
                            speechError.T = 2;
                            speechError.U = -9;
                            str = e2.getMessage();
                        }
                    } else {
                        speechError.T = i;
                        speechError.U = i2;
                        speechError.V = str;
                    }
                    c.this.a(this.e, speechError);
                }
            }
        }

        public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (b(mibrainRequest)) {
                return null;
            }
            m a2 = c.this.ap;
            if (a2 != null) {
                m.a a3 = a2.a(z);
                if (a3 != null && a3.f9930a != null) {
                    return a3.f9930a;
                }
                synchronized (c.this.al) {
                    if (b(mibrainRequest)) {
                        return null;
                    }
                    SpeechError speechError = new SpeechError(this.f);
                    speechError.T = 2;
                    speechError.U = -23;
                    StringBuilder sb = new StringBuilder();
                    sb.append("oauth getToken failed code= ");
                    sb.append(a3 == null ? "" : Integer.valueOf(a3.b));
                    speechError.V = sb.toString();
                    c.this.a(this.e, speechError);
                }
            } else {
                Log.h(c.Y, "onRequestGetToken aiOauthHelper = null ");
            }
            return null;
        }

        public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
        }

        public void onRequestSessionStart(MibrainRequest mibrainRequest) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
        }

        public void onRequestTTSResult(MibrainRequest mibrainRequest, String str, byte[] bArr, boolean z) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            } else {
                a(mibrainRequest, bArr);
            }
        }

        public void onRequestVoiceprintDeleteResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintQueryResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintRecognizedResult(MibrainRequest mibrainRequest, String str) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }

        public void onRequestVoiceprintRegisteredResult(MibrainRequest mibrainRequest, String str, boolean z) {
            if (!a(mibrainRequest)) {
                Log.h(c.Y, " isResponseCorrect = false ,mibrainRequest = " + mibrainRequest + " this.request = " + this.b);
            }
            if (!TextUtils.isEmpty(str)) {
                a(mibrainRequest, str);
            }
        }
    }

    private class e {
        /* access modifiers changed from: private */
        public BlockingQueue<b> b = new LinkedBlockingDeque();
        private MibrainRequest c;
        private SpeechEngine.ParamBuilder d;
        private boolean e;
        private Thread f;
        private d g;
        private Runnable h = new l(this);

        public e(SpeechEngine.ParamBuilder paramBuilder) {
            this.d = paramBuilder;
        }

        /* access modifiers changed from: private */
        public void a(b bVar) {
            this.b.add(bVar);
        }

        /* access modifiers changed from: private */
        public void a(d dVar) {
            this.g = dVar;
        }

        /* access modifiers changed from: private */
        public void a(MibrainRequest mibrainRequest) {
            this.c = mibrainRequest;
        }

        private boolean a() {
            return (this.d == c.this.ao && c.this.aj == this.c) ? false : true;
        }

        /* access modifiers changed from: private */
        public b b() {
            try {
                b take = this.b.take();
                if (take.d == C0082c.DATA) {
                    if (take.f9916a != null && !this.e) {
                        int addAudioData = this.c.addAudioData(take.f9916a, take.c, take.b);
                        if (take.b) {
                            Log.g(c.Y, "add end flag");
                        }
                        if (addAudioData == -5) {
                            this.e = true;
                            return null;
                        } else if (addAudioData < 0) {
                            SpeechError speechError = new SpeechError(this.g.f);
                            speechError.V = "sdk addAudioData failed ";
                            speechError.U = addAudioData;
                            speechError.T = 2;
                            synchronized (c.this.al) {
                                if (!a()) {
                                    c.this.a(this.d, speechError);
                                }
                            }
                            this.e = true;
                            return null;
                        }
                    } else if (take.b) {
                        Log.g(c.Y, "add end flag");
                        int addAudioData2 = this.c.addAudioData(new byte[0], true);
                        if (addAudioData2 == -5) {
                            this.e = true;
                            return null;
                        } else if (addAudioData2 < 0) {
                            SpeechError speechError2 = new SpeechError(this.g.f);
                            speechError2.V = "sdk addAudioData failed ";
                            speechError2.U = addAudioData2;
                            speechError2.T = 2;
                            synchronized (c.this.al) {
                                if (!a()) {
                                    c.this.a(this.d, speechError2);
                                }
                            }
                            this.e = true;
                            return null;
                        }
                    }
                } else if (take.d == C0082c.NLP_DATA) {
                    if (take.e != null && !this.e) {
                        int addNlpData = this.c.addNlpData(take.e);
                        if (addNlpData == -5) {
                            this.e = true;
                            return null;
                        } else if (addNlpData < 0) {
                            SpeechError speechError3 = new SpeechError(this.g.f);
                            speechError3.V = "sdk addNlpData failed ";
                            speechError3.U = addNlpData;
                            speechError3.T = 2;
                            synchronized (c.this.al) {
                                if (!a()) {
                                    c.this.a(this.d, speechError3);
                                }
                            }
                            this.e = true;
                            return null;
                        }
                    }
                } else if (take.d == C0082c.VAD_BEGIN && !this.e) {
                    int addRawData = this.c.addRawData(UploadHelper.b().getBytes(), 4, false, false);
                    if (addRawData == -5) {
                        this.e = true;
                        return null;
                    } else if (addRawData < 0) {
                        SpeechError speechError4 = new SpeechError(this.g.f);
                        speechError4.V = "add VAD_BEGIN failed";
                        speechError4.U = addRawData;
                        speechError4.T = 2;
                        synchronized (c.this.al) {
                            if (!a()) {
                                c.this.a(this.d, speechError4);
                            }
                        }
                        this.e = true;
                        return null;
                    }
                }
                if (!take.b) {
                    return take;
                }
                this.e = true;
                return null;
            } catch (InterruptedException unused) {
                return null;
            }
        }

        /* access modifiers changed from: private */
        public boolean c() {
            return !Thread.currentThread().isInterrupted() && this.d == c.this.ao && this.c.getErrorCode() == 0 && this.c != null && this.c == c.this.aj && !this.e;
        }

        /* access modifiers changed from: private */
        public void d() {
            this.f = new Thread(this.h);
            this.f.start();
            Log.h(c.Y, "RecorderCacheQueueHelper start ");
        }

        /* access modifiers changed from: private */
        public void e() {
            this.b.add(new b(new byte[0], 0, 0, true));
        }

        /* access modifiers changed from: private */
        public void f() {
            this.b.add(new b(new byte[0], 0, 0, true));
            this.e = true;
            if (this.f != null) {
                this.f.interrupt();
            }
        }
    }

    c(Context context, String str, String str2) {
        super(context, str, str2);
        this.ai = (AudioManager) context.getSystemService("audio");
        this.au = 1;
        this.av = Executors.newSingleThreadExecutor();
        this.aw = new AtomicBoolean(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = com.xiaomi.ai.utils.n.b(r1.v);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String B() {
        /*
            r1 = this;
            android.content.Context r0 = r1.v
            if (r0 != 0) goto L_0x0007
            java.lang.String r0 = ""
            return r0
        L_0x0007:
            android.content.Context r0 = r1.v
            java.lang.String r0 = com.xiaomi.ai.utils.n.b((android.content.Context) r0)
            if (r0 != 0) goto L_0x0012
            java.lang.String r0 = ""
            return r0
        L_0x0012:
            java.lang.String r0 = com.xiaomi.ai.utils.n.b((java.lang.String) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.c.B():java.lang.String");
    }

    private String a(boolean z, String str) {
        m.a a2;
        if (str == null) {
            return null;
        }
        try {
            m mVar = this.ap;
            if (!(mVar == null || (a2 = mVar.a(z)) == null || a2.f9930a == null)) {
                return a2.f9930a;
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void a(CharSequence charSequence) {
        a(charSequence, (Throwable) null);
    }

    /* access modifiers changed from: private */
    public void a(CharSequence charSequence, Throwable th) {
        synchronized (this.am) {
            if (th != null) {
                try {
                    Log.h(Y, charSequence.toString(), th);
                    SpannableStringBuilder spannableStringBuilder = this.Z;
                    spannableStringBuilder.append(charSequence + th.toString());
                } catch (Throwable th2) {
                    throw th2;
                }
            } else {
                Log.f(Y, charSequence.toString());
                this.Z.append(charSequence);
            }
            this.Z.append("\n");
        }
    }

    private void c(SpeechEngine.ParamBuilder paramBuilder) {
        if (this.aq == d && this.W == 0 && this.D == null) {
            throw new IllegalStateException("please call setMiAIOauthCallbacks first !!");
        } else if (this.aq == f && this.W == 0 && this.D == null) {
            throw new IllegalStateException("please call setMiAOOauthCallbacks first !!");
        } else if (this.aq == c && this.I == null) {
            throw new IllegalStateException("please call updateMiotAuth first !!");
        } else if (this.aq == e && this.I == null) {
            throw new IllegalStateException("please call updateTPAuth first !!");
        } else {
            l();
            m();
            n();
            if (paramBuilder.e && !paramBuilder.f9904a && !paramBuilder.c && paramBuilder.f.j != null) {
                paramBuilder.g = true;
                paramBuilder.d = paramBuilder.f.j;
            }
            if (!paramBuilder.e && paramBuilder.f9904a && !paramBuilder.c && paramBuilder.b.w != null) {
                paramBuilder.g = true;
                paramBuilder.d = paramBuilder.b.w;
            }
            synchronized (this.al) {
                if (paramBuilder.f9904a || paramBuilder.m || paramBuilder.i) {
                    this.ag = true;
                }
                this.ak = new e(paramBuilder);
                this.ao = paramBuilder;
            }
            new Thread(new i(this, paramBuilder)).start();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x03a5  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x03a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.ai.mibrain.MibrainRequestParams d(com.xiaomi.ai.SpeechEngine.ParamBuilder r11) {
        /*
            r10 = this;
            android.content.Context r0 = r10.v
            java.lang.String r1 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder r1 = new com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder
            r1.<init>()
            java.lang.String r2 = r10.H
            com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder r2 = r1.setAppId(r2)
            java.lang.String r3 = r10.I
            com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder r2 = r2.setToken(r3)
            java.lang.String r3 = r10.ar
            r2.setApiKey(r3)
            int r2 = r10.W
            r3 = 0
            if (r2 != 0) goto L_0x0038
            int r2 = r10.aq
            int r4 = d
            if (r2 == r4) goto L_0x0035
            int r2 = r10.aq
            int r4 = f
            if (r2 != r4) goto L_0x0038
        L_0x0035:
            r1.setToken(r3)
        L_0x0038:
            java.lang.String r2 = r10.P
            r1.setProtocolVersion(r2)
            int r2 = r11.u
            r1.setEventLevel(r2)
            java.lang.String r2 = r11.v
            r1.setApiKey(r2)
            int r2 = r10.R
            r1.setConnectTimeOut(r2)
            int r2 = r10.S
            r1.setReceiverTimeOut(r2)
            int r2 = r10.T
            r1.setUploadTimeout(r2)
            int r2 = r10.U
            r1.setActiveTimeout(r2)
            int r2 = r10.aa
            r1.setEnv(r2)
            int r2 = r10.aq
            r1.setAuthMode(r2)
            boolean r2 = r11.g
            r1.setNeedExtraNLP(r2)
            int r2 = r10.Q
            r1.setStartEventTrack(r2)
            int r2 = r10.V
            r1.setEventTrackReceiver(r2)
            int r2 = r10.W
            r1.setUseExternalOAuthToken(r2)
            boolean r2 = r11.w
            r4 = 1
            if (r2 == 0) goto L_0x0086
            java.lang.String r2 = r11.x
            r1.setWakeupInfo(r2)
            r1.setNeedWakeupData(r4)
        L_0x0086:
            boolean r2 = r11.f9904a
            if (r2 == 0) goto L_0x0110
            r1.setChannels(r4)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            if (r2 == 0) goto L_0x009a
            com.xiaomi.ai.AsrRequest r2 = r11.b
            boolean r2 = r2.j
            if (r2 == 0) goto L_0x009a
            r2 = 8
            goto L_0x009b
        L_0x009a:
            r2 = 4
        L_0x009b:
            r1.setMaxEmptyAudioTime(r2)
            r2 = 16
            r1.setAudioBitNum(r2)
            r2 = 16000(0x3e80, float:2.2421E-41)
            r1.setSampleRate(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            java.lang.String r2 = r2.n
            r1.setAsrLang(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            if (r2 == 0) goto L_0x00ba
            com.xiaomi.ai.AsrRequest r2 = r11.b
            boolean r2 = r2.j
            if (r2 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r4 = 0
        L_0x00bb:
            r1.setIsForSai(r4)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            int r2 = r2.o
            r1.setPreAsrTrack(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            boolean r2 = r2.k
            r1.setAsrRemoveEndPunc(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            short r2 = r2.p
            r1.setCodecMode(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            int r2 = r2.r
            r1.setOpusBitRates(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            int r2 = r2.q
            r1.setOpusFrameSize(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            java.lang.String r2 = r2.s
            r1.setASRDialect(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            boolean r2 = r2.u
            r1.setASRDisableTimeout(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            int r2 = r2.v
            r1.setASRAudioMaxTime(r2)
            com.xiaomi.ai.AsrRequest r2 = r11.b
            if (r2 == 0) goto L_0x0103
            com.xiaomi.ai.AsrRequest r2 = r11.b
            java.lang.String r2 = r2.a()
            r1.setASRVendor(r2)
        L_0x0103:
            com.xiaomi.ai.AsrRequest r2 = r11.b
            java.lang.String r2 = r2.t
            if (r2 == 0) goto L_0x0110
            com.xiaomi.ai.AsrRequest r2 = r11.b
            java.lang.String r2 = r2.t
            r1.setRequestId(r2)
        L_0x0110:
            boolean r2 = r11.e
            if (r2 == 0) goto L_0x015d
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r4 = "codec"
            java.lang.String r5 = "mp3"
            r2.put(r4, r5)
            java.lang.String r2 = "mp3"
            r1.setTTSCodec(r2)
            com.xiaomi.ai.TtsRequest r2 = r11.f
            if (r2 == 0) goto L_0x015d
            com.xiaomi.ai.TtsRequest r2 = r11.f
            java.lang.String r2 = r2.b()
            r1.setTTSVendor(r2)
            com.xiaomi.ai.TtsRequest r2 = r11.f
            int r2 = r2.b
            r1.setTTSTimeout(r2)
            com.xiaomi.ai.TtsRequest r2 = r11.f
            java.lang.String r2 = r2.i
            r1.setTTSExtendVendor(r2)
            android.content.Context r2 = r10.v
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r4 = "com.zmifi.phoneai"
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0156
            java.lang.String r4 = "com.xiaomi.translator"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x015d
        L_0x0156:
            com.xiaomi.ai.TtsRequest r2 = r11.f
            java.lang.String r2 = r2.h
            r1.setTTSLang(r2)
        L_0x015d:
            com.xiaomi.ai.AsrRequest r2 = r11.b
            if (r2 == 0) goto L_0x0168
            com.xiaomi.ai.AsrRequest r2 = r11.b
            boolean r2 = r2.i
            r1.setUseInternalVadFlag(r2)
        L_0x0168:
            com.xiaomi.ai.AsrRequest r2 = r11.b
            if (r2 == 0) goto L_0x017c
            com.xiaomi.ai.AsrRequest r2 = r11.b
            com.xiaomi.ai.AsrRequest$VadMode r2 = r2.m
            com.xiaomi.ai.AsrRequest$VadMode r4 = com.xiaomi.ai.AsrRequest.VadMode.VAD_MODE_LOCAL
            if (r2 != r4) goto L_0x0177
            com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder$VadMode r2 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.VadMode.VAD_MODE_LOCAL
            goto L_0x0179
        L_0x0177:
            com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder$VadMode r2 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.VadMode.VAD_MODE_CLOUD
        L_0x0179:
            r1.setVadTypeLocal(r2)
        L_0x017c:
            android.content.Context r2 = r10.v
            java.lang.String r2 = com.xiaomi.ai.utils.n.b((android.content.Context) r2)
            java.lang.String r4 = com.xiaomi.ai.utils.n.b((java.lang.String) r2)
            r1.setScopeData(r4)
            r1.setDeviceId(r2)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            if (r0 == 0) goto L_0x01a3
            java.lang.String r4 = "network"
            int r0 = r0.getType()
            if (r0 != 0) goto L_0x019e
            java.lang.String r0 = "mobile"
            goto L_0x01a0
        L_0x019e:
            java.lang.String r0 = "wifi"
        L_0x01a0:
            r2.put(r4, r0)
        L_0x01a3:
            java.lang.String r0 = r10.L
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x01b2
            java.lang.String r0 = "model"
            java.lang.String r4 = r10.L
            r2.put(r0, r4)
        L_0x01b2:
            java.lang.String r0 = "ip"
            java.lang.String r4 = ""
            r2.put(r0, r4)
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x01c1
            boolean r0 = r11.g
            if (r0 == 0) goto L_0x023e
        L_0x01c1:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            if (r0 == 0) goto L_0x023e
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.lang.Float r0 = r0.e
            if (r0 == 0) goto L_0x01e4
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.lang.Float r0 = r0.f
            if (r0 == 0) goto L_0x01e4
            java.lang.String r0 = "latitude"
            com.xiaomi.ai.NlpRequest r4 = r11.d
            java.lang.Float r4 = r4.e
            r2.put(r0, r4)
            java.lang.String r0 = "longitude"
            com.xiaomi.ai.NlpRequest r4 = r11.d
            java.lang.Float r4 = r4.f
            r2.put(r0, r4)
            goto L_0x020c
        L_0x01e4:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            boolean r0 = r0.o
            if (r0 == 0) goto L_0x020c
            android.content.Context r0 = r10.v
            android.location.Location r0 = com.xiaomi.ai.utils.b.a(r0)
            if (r0 == 0) goto L_0x0205
            java.lang.String r4 = "latitude"
            double r5 = r0.getLatitude()
            r2.put(r4, r5)
            java.lang.String r4 = "longitude"
            double r5 = r0.getLongitude()
            r2.put(r4, r5)
            goto L_0x020c
        L_0x0205:
            java.lang.String r0 = "MiSpeechSDK:MiAiEngine"
            java.lang.String r4 = "location is null"
            com.xiaomi.ai.utils.Log.h(r0, r4)
        L_0x020c:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.lang.String r0 = r0.k
            if (r0 == 0) goto L_0x021a
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.lang.String r0 = r0.k
        L_0x0216:
            r1.setCustomNLPVersion(r0)
            goto L_0x021d
        L_0x021a:
            java.lang.String r0 = "2.2"
            goto L_0x0216
        L_0x021d:
            java.util.TimeZone r0 = java.util.TimeZone.getDefault()
            java.lang.String r0 = r0.getID()
            java.lang.String r4 = "time_zone"
            r2.put(r4, r0)
            java.util.TimeZone r0 = java.util.TimeZone.getDefault()
            java.lang.String r0 = r0.getDisplayName()
            java.lang.String r4 = "time_zone_display_name"
            r2.put(r4, r0)
            com.xiaomi.ai.NlpRequest r0 = r11.d
            int r0 = r0.p
            r1.setIsPendingSend(r0)
        L_0x023e:
            java.lang.String r0 = r10.s
            if (r0 == 0) goto L_0x0248
            java.lang.String r0 = r10.s
        L_0x0244:
            r1.setUserAgent(r0)
            goto L_0x024f
        L_0x0248:
            java.lang.String r0 = "http.agent"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            goto L_0x0244
        L_0x024f:
            java.lang.String r0 = r2.toString()
            r1.setDevice(r0)
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x0281
            com.xiaomi.ai.TtsRequest r0 = r11.f
            if (r0 == 0) goto L_0x0281
            com.xiaomi.ai.TtsRequest r0 = r11.f
            java.lang.String r0 = r0.f9908a
            r1.setTTSText(r0)
            com.xiaomi.ai.TtsRequest r0 = r11.f
            int r0 = r0.f
            r1.setTTSTone(r0)
            com.xiaomi.ai.TtsRequest r0 = r11.f
            int r0 = r0.e
            r1.setTTSVolume(r0)
            com.xiaomi.ai.TtsRequest r0 = r11.f
            int r0 = r0.d
            r1.setTTSSpeed(r0)
            com.xiaomi.ai.TtsRequest r0 = r11.f
            boolean r0 = r0.g
            r1.setTTSAsFileUrl(r0)
        L_0x0281:
            java.lang.String r0 = r10.ab
            r2 = 2
            if (r0 == 0) goto L_0x02a2
            int r0 = r10.aa
            if (r0 != r2) goto L_0x029a
            java.lang.String r0 = r10.ab
            r1.setCustomNLPApi(r0)
            java.lang.String r0 = r10.H
            r1.setCustomNLPAppId(r0)
            java.lang.String r0 = r10.I
            r1.setCustomNLPAppToken(r0)
            goto L_0x02a2
        L_0x029a:
            java.lang.String r11 = "MiSpeechSDK:MiAiEngine"
            java.lang.String r0 = "only STAGING env  can setCustomNLPApi !!!!"
            com.xiaomi.ai.utils.Log.h(r11, r0)
            return r3
        L_0x02a2:
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x02aa
            boolean r0 = r11.g
            if (r0 == 0) goto L_0x03f1
        L_0x02aa:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            if (r0 == 0) goto L_0x03f1
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.lang.String r0 = r0.a()
            r1.setNLPText(r0)
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.xiaomi.ai.NlpRequest r4 = r11.d
            boolean r4 = r4.c
            if (r4 == 0) goto L_0x02c6
            java.lang.String r4 = ""
            r10.an = r4
        L_0x02c6:
            java.lang.String r4 = "is_new"
            com.xiaomi.ai.NlpRequest r5 = r11.d
            boolean r5 = r5.c
            r0.put(r4, r5)
            com.xiaomi.ai.NlpRequest r4 = r11.d
            java.lang.String r4 = r4.d
            if (r4 == 0) goto L_0x02df
            java.lang.String r4 = "id"
            com.xiaomi.ai.NlpRequest r5 = r11.d
            java.lang.String r5 = r5.d
        L_0x02db:
            r0.put(r4, r5)
            goto L_0x02e4
        L_0x02df:
            java.lang.String r4 = "id"
            java.lang.String r5 = r10.an
            goto L_0x02db
        L_0x02e4:
            java.lang.String r0 = r0.toString()
            r1.setNLPSession(r0)
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.m
            if (r0 == 0) goto L_0x02fc
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.m
            java.lang.String r0 = r0.toString()
            r1.setDevice(r0)
        L_0x02fc:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.l
            if (r0 == 0) goto L_0x030d
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.l
            java.lang.String r0 = r0.toString()
            r1.setNLPSpeechData(r0)
        L_0x030d:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            com.xiaomi.ai.NlpRequest$UserInfo r0 = r0.j
            if (r0 == 0) goto L_0x032e
            com.xiaomi.ai.NlpRequest r0 = r11.d
            com.xiaomi.ai.NlpRequest$UserInfo r0 = r0.j
            java.lang.String r0 = r0.b
            r1.setNLPUserInfoIdType(r0)
            com.xiaomi.ai.NlpRequest r0 = r11.d
            com.xiaomi.ai.NlpRequest$UserInfo r0 = r0.j
            java.lang.String r0 = r0.f9900a
            r1.setNLPUserInfoId(r0)
            com.xiaomi.ai.NlpRequest r0 = r11.d
            com.xiaomi.ai.NlpRequest$UserInfo r0 = r0.j
            java.lang.String r0 = r0.c
            r1.setNLPUserInfoExtend(r0)
        L_0x032e:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r0.g
            if (r0 != 0) goto L_0x033a
            com.xiaomi.ai.NlpRequest r0 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r0 = r0.h
            if (r0 == 0) goto L_0x03e0
        L_0x033a:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            com.xiaomi.ai.NlpRequest r4 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r4 = r4.g
            if (r4 == 0) goto L_0x03af
            com.xiaomi.ai.NlpRequest r4 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r4 = r4.g
            java.util.Set r4 = r4.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0351:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x03af
            java.lang.Object r5 = r4.next()
            java.lang.String r5 = (java.lang.String) r5
            com.xiaomi.ai.NlpRequest r6 = r11.d     // Catch:{ JSONException -> 0x038a }
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r6 = r6.g     // Catch:{ JSONException -> 0x038a }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ JSONException -> 0x038a }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ JSONException -> 0x038a }
            java.lang.String r6 = r6.trim()     // Catch:{ JSONException -> 0x038a }
            java.lang.String r7 = "["
            boolean r7 = r6.startsWith(r7)     // Catch:{ JSONException -> 0x038a }
            if (r7 == 0) goto L_0x0383
            java.lang.String r7 = "]"
            boolean r7 = r6.endsWith(r7)     // Catch:{ JSONException -> 0x038a }
            if (r7 == 0) goto L_0x0383
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ JSONException -> 0x038a }
            r7.<init>(r6)     // Catch:{ JSONException -> 0x038a }
            r6 = r7
            r7 = r3
            goto L_0x03a3
        L_0x0383:
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ JSONException -> 0x038a }
            r7.<init>(r6)     // Catch:{ JSONException -> 0x038a }
            r6 = r3
            goto L_0x03a3
        L_0x038a:
            r6 = move-exception
            java.lang.String r7 = "MiSpeechSDK:MiAiEngine"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "JSONException when set context for key: "
            r8.append(r9)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            com.xiaomi.ai.utils.Log.h(r7, r8, r6)
            r6 = r3
            r7 = r6
        L_0x03a3:
            if (r6 == 0) goto L_0x03a9
            r0.put(r5, r6)
            goto L_0x0351
        L_0x03a9:
            if (r7 == 0) goto L_0x0351
            r0.put(r5, r7)
            goto L_0x0351
        L_0x03af:
            com.xiaomi.ai.NlpRequest r3 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r3 = r3.h
            if (r3 == 0) goto L_0x03d9
            com.xiaomi.ai.NlpRequest r3 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r3 = r3.h
            java.util.Set r3 = r3.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x03c1:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x03d9
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            com.xiaomi.ai.NlpRequest r5 = r11.d
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r5 = r5.h
            java.lang.Object r5 = r5.get(r4)
            r0.put(r4, r5)
            goto L_0x03c1
        L_0x03d9:
            java.lang.String r0 = r0.toString()
            r1.setNLPContext(r0)
        L_0x03e0:
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.n
            if (r0 == 0) goto L_0x03f1
            com.xiaomi.ai.NlpRequest r0 = r11.d
            org.json.JSONObject r0 = r0.n
            java.lang.String r0 = r0.toString()
            r1.setNLPContext(r0)
        L_0x03f1:
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            if (r0 == 0) goto L_0x0423
            boolean r0 = r11.i
            if (r0 == 0) goto L_0x0423
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            java.lang.String r0 = r0.f9912a
            r1.setVoiceprintUserId(r0)
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            java.lang.String r0 = r0.b
            r1.setVoiceprintFamilyId(r0)
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            java.lang.String r0 = r0.d
            r1.setVoiceprintQueries(r0)
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            java.lang.String r0 = r0.e
            r1.setVoiceprintVendor(r0)
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            int r0 = r0.f
            r1.setVoiceId(r0)
            com.xiaomi.ai.VoiceprintRegisterRequest r0 = r11.h
            int r0 = r0.g
            r1.setVoiceprintDisableTextDependent(r0)
        L_0x0423:
            com.xiaomi.ai.VoiceprintDeleteRequest r0 = r11.l
            if (r0 == 0) goto L_0x0444
            boolean r0 = r11.n
            if (r0 == 0) goto L_0x0444
            com.xiaomi.ai.VoiceprintDeleteRequest r0 = r11.l
            org.json.JSONArray r0 = r0.b
            java.lang.String r0 = r0.toString()
            r1.setVoiceprintDeleteIds(r0)
            com.xiaomi.ai.VoiceprintDeleteRequest r0 = r11.l
            java.lang.String r0 = r0.f9909a
            r1.setVoiceprintFamilyId(r0)
            com.xiaomi.ai.VoiceprintDeleteRequest r0 = r11.l
            java.lang.String r0 = r0.c
            r1.setVoiceprintVendor(r0)
        L_0x0444:
            com.xiaomi.ai.VoiceprintQueryRequest r0 = r11.k
            if (r0 == 0) goto L_0x045a
            boolean r0 = r11.o
            if (r0 == 0) goto L_0x045a
            com.xiaomi.ai.VoiceprintQueryRequest r0 = r11.k
            java.lang.String r0 = r0.f9910a
            r1.setVoiceprintFamilyId(r0)
            com.xiaomi.ai.VoiceprintQueryRequest r0 = r11.k
            java.lang.String r0 = r0.b
            r1.setVoiceprintVendor(r0)
        L_0x045a:
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            if (r0 == 0) goto L_0x0485
            boolean r0 = r11.m
            if (r0 == 0) goto L_0x0485
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            java.lang.String r0 = r0.c
            r1.setVoiceprintQueries(r0)
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            java.lang.String r0 = r0.f9911a
            r1.setVoiceprintFamilyId(r0)
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            java.lang.String r0 = r0.d
            r1.setVoiceprintUserId(r0)
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            java.lang.String r0 = r0.e
            r1.setVoiceprintVendor(r0)
            com.xiaomi.ai.VoiceprintRecognizeRequest r0 = r11.j
            int r0 = r0.f
            r1.setVoiceprintDisableTextDependent(r0)
        L_0x0485:
            boolean r0 = r11.p
            if (r0 == 0) goto L_0x04df
            com.xiaomi.ai.EventRequest r0 = r11.q
            if (r0 == 0) goto L_0x04df
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.f9897a
            r1.setEventNameSpace(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.b
            r1.setEventName(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.c
            r1.setEventPayload(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            boolean r0 = r0.h
            if (r0 != 0) goto L_0x04b9
            java.lang.String r0 = r10.an
            if (r0 == 0) goto L_0x04b9
            java.lang.String r0 = r10.an
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x04b9
            java.lang.String r0 = r10.an
            r1.setEventSessionId(r0)
        L_0x04b9:
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.d
            r1.setEventContextNameSpace(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.e
            r1.setEventContextName(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            java.lang.String r0 = r0.f
            r1.setEventContextPayload(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            org.json.JSONObject r0 = r0.i
            if (r0 == 0) goto L_0x04df
            com.xiaomi.ai.EventRequest r0 = r11.q
            org.json.JSONObject r0 = r0.i
            java.lang.String r0 = r0.toString()
            r1.setDevice(r0)
        L_0x04df:
            boolean r0 = r11.f9904a
            if (r0 == 0) goto L_0x0506
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x0506
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x0506
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_ASR
        L_0x0501:
            r1.setRequestCmd(r11)
            goto L_0x0687
        L_0x0506:
            boolean r0 = r11.f9904a
            if (r0 == 0) goto L_0x0525
            boolean r0 = r11.c
            if (r0 == 0) goto L_0x0525
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x0525
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x0525
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x0525
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x0525
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x0525
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_ASR_NLP
            goto L_0x0501
        L_0x0525:
            boolean r0 = r11.f9904a
            if (r0 == 0) goto L_0x0544
            boolean r0 = r11.c
            if (r0 == 0) goto L_0x0544
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x0544
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x0544
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x0544
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x0544
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x0544
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_ASR_NLP_TTS
            goto L_0x0501
        L_0x0544:
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x0563
            boolean r0 = r11.c
            if (r0 == 0) goto L_0x0563
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x0563
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x0563
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x0563
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x0563
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x0563
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_NLP
            goto L_0x0501
        L_0x0563:
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x0583
            boolean r0 = r11.c
            if (r0 == 0) goto L_0x0583
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x0583
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x0583
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x0583
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x0583
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x0583
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_NLP_TTS
            goto L_0x0501
        L_0x0583:
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x05a3
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x05a3
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x05a3
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x05a3
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x05a3
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x05a3
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x05a3
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_TTS
            goto L_0x0501
        L_0x05a3:
            boolean r0 = r11.i
            if (r0 == 0) goto L_0x05cd
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x05cd
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x05cd
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x05cd
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x05cd
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x05cd
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x05cd
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_VOR_REG
        L_0x05c1:
            r1.setRequestCmd(r11)
            int r11 = r10.S
            int r11 = r11 * 2
            r1.setReceiverTimeOut(r11)
            goto L_0x0687
        L_0x05cd:
            boolean r0 = r11.m
            if (r0 == 0) goto L_0x05ec
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x05ec
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x05ec
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x05ec
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x05ec
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x05ec
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x05ec
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_VOR
            goto L_0x05c1
        L_0x05ec:
            boolean r0 = r11.n
            if (r0 == 0) goto L_0x060c
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x060c
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x060c
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x060c
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x060c
            boolean r0 = r11.o
            if (r0 != 0) goto L_0x060c
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x060c
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_VOR_DELETE
            goto L_0x0501
        L_0x060c:
            boolean r0 = r11.o
            if (r0 == 0) goto L_0x062c
            boolean r0 = r11.f9904a
            if (r0 != 0) goto L_0x062c
            boolean r0 = r11.c
            if (r0 != 0) goto L_0x062c
            boolean r0 = r11.e
            if (r0 != 0) goto L_0x062c
            boolean r0 = r11.m
            if (r0 != 0) goto L_0x062c
            boolean r0 = r11.n
            if (r0 != 0) goto L_0x062c
            boolean r0 = r11.i
            if (r0 != 0) goto L_0x062c
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_VOR_QUERY
            goto L_0x0501
        L_0x062c:
            boolean r0 = r11.p
            if (r0 == 0) goto L_0x0696
            com.xiaomi.ai.EventRequest r0 = r11.q
            boolean r0 = r0.g
            if (r0 == 0) goto L_0x0683
            int r0 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_EVENT_TTS
            r1.setRequestCmd(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            if (r0 == 0) goto L_0x0687
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            java.lang.String r0 = r0.b()
            r1.setTTSVendor(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            int r0 = r0.b
            r1.setTTSTimeout(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            java.lang.String r0 = r0.i
            r1.setTTSExtendVendor(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            int r0 = r0.f
            r1.setTTSTone(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            int r0 = r0.e
            r1.setTTSVolume(r0)
            com.xiaomi.ai.EventRequest r0 = r11.q
            com.xiaomi.ai.TtsRequest r0 = r0.j
            int r0 = r0.d
            r1.setTTSSpeed(r0)
            com.xiaomi.ai.EventRequest r11 = r11.q
            com.xiaomi.ai.TtsRequest r11 = r11.j
            boolean r11 = r11.g
            r1.setTTSAsFileUrl(r11)
            goto L_0x0687
        L_0x0683:
            int r11 = com.xiaomi.ai.mibrain.MibrainRequestParamsBuilder.MI_CMD_EVENT
            goto L_0x0501
        L_0x0687:
            com.xiaomi.ai.mibrain.MibrainRequestParams r11 = r1.buid()     // Catch:{ MibrainException -> 0x068c }
            return r11
        L_0x068c:
            r11 = move-exception
            r11.printStackTrace()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r11)
            throw r0
        L_0x0696:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "needAsr:"
            r1.append(r2)
            boolean r2 = r11.f9904a
            r1.append(r2)
            java.lang.String r2 = ",needNlp:"
            r1.append(r2)
            boolean r2 = r11.c
            r1.append(r2)
            java.lang.String r2 = ",needTts:"
            r1.append(r2)
            boolean r2 = r11.e
            r1.append(r2)
            java.lang.String r2 = ",needVpReg:"
            r1.append(r2)
            boolean r2 = r11.i
            r1.append(r2)
            java.lang.String r2 = ",needVpDel:"
            r1.append(r2)
            boolean r2 = r11.n
            r1.append(r2)
            java.lang.String r2 = ",needVpRec:"
            r1.append(r2)
            boolean r2 = r11.m
            r1.append(r2)
            java.lang.String r2 = ",needVpQry:"
            r1.append(r2)
            boolean r2 = r11.o
            r1.append(r2)
            java.lang.String r2 = ",needEvent:"
            r1.append(r2)
            boolean r11 = r11.p
            r1.append(r11)
            java.lang.String r11 = "  combine Illegally "
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.c.d(com.xiaomi.ai.SpeechEngine$ParamBuilder):com.xiaomi.ai.mibrain.MibrainRequestParams");
    }

    private String h(boolean z) {
        m.a a2;
        try {
            m mVar = this.ap;
            if (mVar == null || (a2 = mVar.a(z)) == null || a2.f9930a == null) {
                return null;
            }
            return a2.f9930a;
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public CharSequence A() {
        return this.Z;
    }

    public int a(NlpRequest nlpRequest) {
        nlpRequest.a(0);
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.c();
        paramBuilder.a(this.ar);
        paramBuilder.a(nlpRequest);
        try {
            MibrainRequestParams d2 = d(paramBuilder);
            if (this.aj == null) {
                Log.h(Y, "NULL ERROR");
            }
            if (this.aj == null) {
                return -1;
            }
            this.aj.updateRequestParams(d2);
            return this.aj.sendPendingData();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr) {
        return a(sendWakeupDataStatusInterface, aSRRecordInfo, bArr, (String) null);
    }

    public int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr, String str) {
        if (sendWakeupDataStatusInterface == null) {
            return -1;
        }
        e eVar = new e(this, sendWakeupDataStatusInterface, str, aSRRecordInfo, bArr);
        if (this.av != null) {
            this.av.execute(eVar);
            return 0;
        }
        sendWakeupDataStatusInterface.b("ThreadPool error");
        return -1;
    }

    public int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr) {
        return a(sendWakeupDataStatusInterface, wakeupDataInfo, bArr, (String) null);
    }

    public int a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr, String str) {
        if (sendWakeupDataStatusInterface == null) {
            return -1;
        }
        d dVar = new d(this, sendWakeupDataStatusInterface, str, wakeupDataInfo, bArr);
        if (this.av != null) {
            this.av.execute(dVar);
            return 0;
        }
        sendWakeupDataStatusInterface.b("ThreadPool error");
        return -1;
    }

    public UploadHelper a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface) {
        UploadHelper uploadHelper = new UploadHelper();
        if (uploadHelper.a(sendWakeupDataStatusInterface, this.ap, this.aa, this.aq, this.H, this.I, this.ar, n.b(n.b(this.v))) != 0) {
            return null;
        }
        return uploadHelper;
    }

    public UploadHelper a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str) {
        UploadHelper uploadHelper = new UploadHelper();
        if (uploadHelper.a(sendWakeupDataStatusInterface, this.ap, this.aa, this.aq, this.H, this.I, this.ar, n.b(n.b(this.v)), str) != 0) {
            return null;
        }
        return uploadHelper;
    }

    public String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        String d2 = d();
        if (d2 == null) {
            h(true);
            d2 = d();
            if (d2 == null) {
                return null;
            }
        }
        return MibrainRequest.getResourceDetails(str, str2, d2, this.aa);
    }

    public void a(int i, String str) {
        a(i, str, this.I);
    }

    public void a(int i, String str, String str2) {
        if (this.ap != null) {
            this.ap.b(i);
        }
        this.aa = i;
        this.H = str;
        this.I = str2;
    }

    public void a(Context context, String str, int i) {
        super.a(context, str, i);
        int i2 = 1;
        Log.setJNILogHook(true);
        Mibrainsdk.setLogLevel(Mibrainsdk.MIBRAIN_DEBUG_LEVEL_WARNING);
        if (i == d || i == f || i == g || i == h) {
            this.ap = new m(context, str, this.I, this.H);
            this.ap.b();
            m mVar = this.ap;
            if (i != d) {
                i2 = 0;
            }
            mVar.a(i2);
            if (i == g || i == h) {
                this.ap.a(str, n.b(context), new g(context).d(), i);
                this.ar = str;
            }
        }
        this.aq = i;
    }

    public void a(HTTPCallback hTTPCallback) {
        this.O.a(hTTPCallback);
    }

    public void a(HTTPCallback hTTPCallback, TrackInfo trackInfo, String str) {
        if (this.O != null) {
            this.O.a(this.aa);
            this.O.a(hTTPCallback, trackInfo, str, d(), this.aq);
        }
    }

    public void a(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.a(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        } else if (this.aq == d) {
            this.ap.a(miAiOauthCallbacks);
        } else {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_MIAO");
        }
    }

    public void a(SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str, String str2, String str3, int i, int i2, String str4, byte[] bArr) {
        SendWakeupDataStatusInterface sendWakeupDataStatusInterface2 = sendWakeupDataStatusInterface;
        g gVar = new g(this, i, str3, str, str2, i2, str4, bArr, new f(this, sendWakeupDataStatusInterface));
        if (this.av != null) {
            this.av.execute(gVar);
        } else if (sendWakeupDataStatusInterface2 != null) {
            sendWakeupDataStatusInterface.b("ThreadPool error");
        }
    }

    public void a(TrackInfo trackInfo, String str) {
        if (this.O != null) {
            this.O.a(this.aa);
            this.O.a(this.O.a(), trackInfo, str, d(), this.aq);
        }
    }

    public void a(MibrainOauthManager.updateTokenCallback updatetokencallback) {
        if (this.ap != null) {
            this.ap.a(updatetokencallback);
        }
    }

    public void a(JSONObject jSONObject) {
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(jSONObject.toString(), C0082c.NLP_DATA));
            return;
        }
        throw new IllegalStateException("you should call doStartIntegrally first !!");
    }

    public void a(byte[] bArr, int i, int i2) {
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(bArr, i, i2, false));
            return;
        }
        throw new IllegalStateException("you should call doStartIntegrally first !!");
    }

    public boolean a(String str, String str2, long j) {
        if (this.ap != null) {
            return this.ap.a(this.aq, this.H, str, str2, j);
        }
        Log.h(Y, "setAuthorizationTokens: mOauthHelper is null\n");
        return false;
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = str;
        if (this.aq != h) {
            Log.h("ERROR", "wrong mode " + this.aq);
            return false;
        }
        m mVar = this.ap;
        if (mVar == null || mVar.a(str, str2, str3, str4, str5, str6) != 0) {
            return false;
        }
        this.at = str7;
        this.H = str7;
        this.I = str2;
        this.ar = str3;
        return true;
    }

    /* access modifiers changed from: protected */
    public void b(AsrRequest asrRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.b();
        paramBuilder.a(this.ar);
        paramBuilder.a(asrRequest);
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        c(paramBuilder);
    }

    public void b(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.a(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        } else if (this.aq == f) {
            this.ap.a(miAiOauthCallbacks);
        } else {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_MIAO");
        }
    }

    /* access modifiers changed from: protected */
    public void b(SpeechEngine.ParamBuilder paramBuilder) {
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        paramBuilder.a(this.ar);
        c(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void b(TtsRequest ttsRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.d();
        paramBuilder.a(this.ar);
        paramBuilder.a(ttsRequest);
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        c(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void b(VoiceprintDeleteRequest voiceprintDeleteRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.n = true;
        paramBuilder.a(this.ar);
        paramBuilder.l = voiceprintDeleteRequest;
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        paramBuilder.a(this.ar);
        c(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void b(VoiceprintQueryRequest voiceprintQueryRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.o = true;
        paramBuilder.a(this.ar);
        paramBuilder.k = voiceprintQueryRequest;
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        paramBuilder.a(this.ar);
        c(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void b(VoiceprintRecognizeRequest voiceprintRecognizeRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.m = true;
        paramBuilder.a(this.ar);
        paramBuilder.j = voiceprintRecognizeRequest;
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        c(paramBuilder);
    }

    /* access modifiers changed from: protected */
    public void b(VoiceprintRegisterRequest voiceprintRegisterRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.i = true;
        paramBuilder.a(this.ar);
        paramBuilder.h = voiceprintRegisterRequest;
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        c(paramBuilder);
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            b(true);
            this.I = str;
            return;
        }
        throw new IllegalArgumentException("OAuth token  is empty");
    }

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("Miot token or session  is empty");
        }
        this.I = String.format(Locale.getDefault(), "session_id:%s,token:%s", new Object[]{str2, str});
    }

    public String c(boolean z) {
        String a2;
        String str;
        String str2;
        Object[] objArr;
        if (this.aq != c) {
            if (this.aq == d) {
                String h = h(z);
                if (h == null) {
                    return null;
                }
                str2 = "DO-TOKEN-V1 app_id:%s,scope_data:%s,access_token:%s";
                objArr = new Object[]{this.H, B(), h};
            } else if (this.aq == f) {
                String h2 = h(z);
                if (h2 == null) {
                    return null;
                }
                str2 = "AO-TOKEN-V1 dev_app_id:%s,access_token:%s";
                objArr = new Object[]{this.H, h2};
            } else {
                if (this.aq == g) {
                    a2 = a(z, this.H);
                    if (a2 == null) {
                        str = "anon";
                    }
                    return this.ap.a(this.H, this.ar, a2);
                } else if (this.aq == h) {
                    a2 = a(z, this.H);
                    if (a2 == null) {
                        str = "anon proxy";
                    }
                    return this.ap.a(this.H, this.ar, a2);
                } else if (this.aq != e || this.H == null || this.I == null) {
                    return null;
                } else {
                    return String.format("TP-TOKEN-V1 app_id:%s,access_token:%s", new Object[]{this.H, this.I});
                }
                Log.f(str, "failed");
                return null;
            }
            return String.format(str2, objArr);
        } else if (this.H == null || this.I == null) {
            return null;
        } else {
            return String.format("MIOT-TOKEN-V1 app_id:%s,%s", new Object[]{this.H, this.I});
        }
    }

    public void c(MiAiOauthCallbacks miAiOauthCallbacks) {
        super.a(miAiOauthCallbacks);
        if (miAiOauthCallbacks == null) {
            throw new NullPointerException("aiOauthListener is null");
        } else if (this.aq == g || this.aq == h) {
            this.ap.a(miAiOauthCallbacks);
        } else {
            throw new IllegalArgumentException("setMiAOOauthCallbacks is only for ENGINE_AUTH_ANONYMOUS");
        }
    }

    /* access modifiers changed from: protected */
    public void c(NlpRequest nlpRequest) {
        SpeechEngine.ParamBuilder paramBuilder = new SpeechEngine.ParamBuilder();
        paramBuilder.c();
        paramBuilder.a(this.ar);
        paramBuilder.a(nlpRequest);
        synchronized (this.am) {
            this.Z = new SpannableStringBuilder();
        }
        c(paramBuilder);
    }

    public void c(String str) {
        try {
            m mVar = this.ap;
            if (mVar == null) {
                return;
            }
            if (mVar.a(str) == null) {
                Log.h(Y, "oAuth is null " + str);
                return;
            }
            this.ap.c(str);
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public boolean c() {
        String str;
        StringBuilder sb;
        m mVar = this.ap;
        if (mVar == null) {
            return false;
        }
        if (this.aq == g || this.aq == h) {
            if (mVar.a(this.H) != null) {
                return true;
            }
            str = Y;
            sb = new StringBuilder();
        } else if (mVar.a() != null) {
            return true;
        } else {
            str = Y;
            sb = new StringBuilder();
        }
        sb.append("NO AUTH DATA, mode ");
        sb.append(this.aq);
        Log.f(str, sb.toString());
        return false;
    }

    public String d() {
        return c(false);
    }

    public void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.I = str;
            return;
        }
        throw new IllegalArgumentException(" token  is empty");
    }

    public void e() {
        try {
            m mVar = this.ap;
            if (mVar == null) {
                return;
            }
            if (mVar.a() == null) {
                Log.h(Y, "oAuth is null");
            } else {
                this.ap.d();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void e(boolean z) {
        synchronized (this.al) {
            this.aw.set(z);
        }
    }

    public void f() {
        try {
            m mVar = this.ap;
            if (mVar != null) {
                mVar.e();
            }
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void f(int i) {
        Log.f(Y, "setTTSMode " + i);
        this.au = i;
    }

    public void f(String str) {
        this.ab = str;
    }

    public int g() {
        Log.f(Y, "getTTSMode " + this.au);
        return this.au;
    }

    public void h() {
        super.h();
        synchronized (this.al) {
            if (this.ap != null) {
                this.ap.c();
                this.ap = null;
            }
            if (this.ak != null) {
                this.ak.f();
                this.ak = null;
            }
            f(false);
        }
    }

    public void h(int i) {
        a(i, this.H, this.I);
    }

    public void i() {
        synchronized (this.al) {
            super.i();
            if (this.af != null) {
                this.af.c();
                this.af = null;
            }
            if (this.ak != null) {
                this.ak.f();
                this.ak = null;
            }
            if (this.ah != null) {
                this.ah.interrupt();
                this.ah = null;
            }
            if (this.aj != null) {
                this.aj.asyncStopRequest();
                this.aj = null;
            }
            this.ao = null;
            f(false);
        }
    }

    public void j() {
        synchronized (this.al) {
            if (this.af != null) {
                this.af.c();
                this.af = null;
            }
        }
    }

    public void k() {
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(C0082c.VAD_BEGIN));
            return;
        }
        throw new IllegalStateException("you should call doStartIntegrally first 1!!");
    }

    public void l() {
        this.ag = false;
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }

    public void m() {
        this.ag = false;
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }

    public void n() {
        this.ag = false;
        e eVar = this.ak;
        if (eVar != null) {
            eVar.a(new b(new byte[0], 0, 0, true));
        }
    }
}
