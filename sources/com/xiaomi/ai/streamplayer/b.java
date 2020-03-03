package com.xiaomi.ai.streamplayer;

import com.tiqiaa.icontrol.util.TiqiaaService;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTAudioModule;
import java.util.HashMap;

class b extends i {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9935a = "MiSpeechSDK:FrameParser";
    private static final String e = "Layer I";
    private static final String f = "Layer II";
    private static final String g = "Layer III";
    private static final String h = "MPEG2.5";
    private static final String i = "MPEG2";
    private static final String j = "MPEG1";
    private static final String k = "MPEG_UNKNOW";
    private static final String l = "Check bit 1";
    private static final String m = "Check bit 0";
    private static final String n = "Layer unkown";
    private static final char o = '1';
    private static final char p = '0';
    private static final int q = 1;
    private static final int r = 2;
    private static final int s = 0;
    private static final HashMap<String, Integer> t = new HashMap<>();
    private static final HashMap<String, Integer> u = new HashMap<>();
    private Thread v = new c(this);

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        String f9936a;
        String b;
        int c;
        int d;
        int e;
        int f;
        int g;

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.f9936a != b.n && this.b != b.k && this.f > 0 && this.c > 0 && this.d > 0 && this.g > 0 && this.e > 0;
        }

        public String toString() {
            return "layer=" + this.f9936a + ";mpeg=" + this.b + ";sampleRate=" + this.c + ";bitrate=" + this.d + ";sampleRateCount=" + this.e + ";frameSize=" + this.f + "channel=" + this.g;
        }
    }

    static {
        t.put(a(g, i, new char[]{p, p, o, o}, 0, 4), 128000);
        t.put(a(g, i, new char[]{p, o, p, p}, 0, 4), Integer.valueOf(RecordDevice.PCM_FREQUENCE_16K));
        t.put(a(g, i, new char[]{p, p, o, p}, 0, 4), Integer.valueOf(AsrRequest.d));
        u.put(a(g, j, (char[]) null, 0, 0), 1152);
        u.put(a(g, i, (char[]) null, 0, 0), 576);
        u.put(a(g, h, (char[]) null, 0, 0), 576);
    }

    b() {
    }

    private static String a(byte b) {
        String binaryString = Integer.toBinaryString(b | 256);
        int length = binaryString.length();
        return binaryString.substring(length - 8, length);
    }

    public static String a(String str, String str2, char[] cArr, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            sb.append(cArr[i4]);
        }
        return sb.toString();
    }

    private static boolean a(char[] cArr) {
        for (int i2 = 21; i2 < 31; i2++) {
            if (cArr[i2] != '1') {
                return false;
            }
        }
        return true;
    }

    private static char[] a(byte[] bArr) {
        char[] b = b(bArr);
        char[] cArr = new char[b.length];
        for (int i2 = 0; i2 < b.length; i2++) {
            cArr[(b.length - i2) - 1] = b[i2];
        }
        return cArr;
    }

    private static int b(char[] cArr) {
        if (cArr[7] == '0' && cArr[6] == '0') {
            return 2;
        }
        return (cArr[7] == '1' && cArr[6] == '1') ? 1 : 0;
    }

    private static char[] b(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append(String.format("%s", new Object[]{a(bArr[i2])}));
        }
        return sb.toString().toCharArray();
    }

    private static int c(char[] cArr) {
        Integer num = u.get(a(g(cArr), i(cArr), (char[]) null, 0, 0));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    private static int d(char[] cArr) {
        Integer num = t.get(a(g(cArr), i(cArr), cArr, 12, 4));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0071 A[Catch:{ IOException -> 0x00ee, all -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1 A[Catch:{ IOException -> 0x00ee, all -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r13 = this;
            java.io.PipedInputStream r0 = r13.c
            r1 = 4
            byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x00ee }
            com.xiaomi.ai.streamplayer.j r3 = r13.b     // Catch:{ IOException -> 0x00ee }
            if (r3 == 0) goto L_0x000e
            com.xiaomi.ai.streamplayer.j r3 = r13.b     // Catch:{ IOException -> 0x00ee }
            r3.a(r13)     // Catch:{ IOException -> 0x00ee }
        L_0x000e:
            byte[] r3 = new byte[r1]     // Catch:{ IOException -> 0x00ee }
            r4 = 0
            r5 = 0
        L_0x0012:
            boolean r6 = r13.d     // Catch:{ IOException -> 0x00ee }
            if (r6 != 0) goto L_0x00e2
            r6 = 1
            if (r5 != 0) goto L_0x0044
            r7 = 0
            r8 = 0
        L_0x001b:
            int r9 = 4 - r7
            int r9 = r0.read(r3, r4, r9)     // Catch:{ IOException -> 0x00ee }
            if (r9 <= 0) goto L_0x002a
            int r7 = r7 + r9
            java.lang.System.arraycopy(r3, r4, r2, r8, r9)     // Catch:{ IOException -> 0x00ee }
            int r8 = r8 + r9
            if (r7 != r1) goto L_0x001b
        L_0x002a:
            if (r9 > 0) goto L_0x0062
            java.lang.String r0 = "MiSpeechSDK:FrameParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ee }
            r1.<init>()     // Catch:{ IOException -> 0x00ee }
            java.lang.String r2 = "doParserFrame:read end ret="
            r1.append(r2)     // Catch:{ IOException -> 0x00ee }
            r1.append(r9)     // Catch:{ IOException -> 0x00ee }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00ee }
        L_0x003f:
            com.xiaomi.ai.utils.Log.a(r0, r1)     // Catch:{ IOException -> 0x00ee }
            goto L_0x00e2
        L_0x0044:
            r7 = 3
            java.lang.System.arraycopy(r2, r6, r2, r4, r7)     // Catch:{ IOException -> 0x00ee }
            int r7 = r0.read(r2, r7, r6)     // Catch:{ IOException -> 0x00ee }
            if (r7 > 0) goto L_0x0062
            java.lang.String r0 = "MiSpeechSDK:FrameParser"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00ee }
            r1.<init>()     // Catch:{ IOException -> 0x00ee }
            java.lang.String r2 = "doParserFrame:read end ret="
            r1.append(r2)     // Catch:{ IOException -> 0x00ee }
            r1.append(r7)     // Catch:{ IOException -> 0x00ee }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00ee }
            goto L_0x003f
        L_0x0062:
            char[] r7 = a((byte[]) r2)     // Catch:{ IOException -> 0x00ee }
            com.xiaomi.ai.streamplayer.b$a r8 = new com.xiaomi.ai.streamplayer.b$a     // Catch:{ IOException -> 0x00ee }
            r8.<init>()     // Catch:{ IOException -> 0x00ee }
            boolean r9 = a((char[]) r7)     // Catch:{ IOException -> 0x00ee }
            if (r9 == 0) goto L_0x009b
            java.lang.String r9 = g(r7)     // Catch:{ IOException -> 0x00ee }
            r8.f9936a = r9     // Catch:{ IOException -> 0x00ee }
            java.lang.String r9 = i(r7)     // Catch:{ IOException -> 0x00ee }
            r8.b = r9     // Catch:{ IOException -> 0x00ee }
            int r9 = d(r7)     // Catch:{ IOException -> 0x00ee }
            r8.d = r9     // Catch:{ IOException -> 0x00ee }
            int r9 = f(r7)     // Catch:{ IOException -> 0x00ee }
            r8.c = r9     // Catch:{ IOException -> 0x00ee }
            int r9 = c(r7)     // Catch:{ IOException -> 0x00ee }
            r8.e = r9     // Catch:{ IOException -> 0x00ee }
            int r9 = e(r7)     // Catch:{ IOException -> 0x00ee }
            r8.f = r9     // Catch:{ IOException -> 0x00ee }
            int r7 = b((char[]) r7)     // Catch:{ IOException -> 0x00ee }
            r8.g = r7     // Catch:{ IOException -> 0x00ee }
        L_0x009b:
            boolean r7 = r8.a()     // Catch:{ IOException -> 0x00ee }
            if (r7 == 0) goto L_0x00df
            int r6 = r8.f     // Catch:{ IOException -> 0x00ee }
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x00ee }
            int r7 = r6.length     // Catch:{ IOException -> 0x00ee }
            int r7 = r7 - r1
            int r9 = r6.length     // Catch:{ IOException -> 0x00ee }
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x00ee }
            java.lang.System.arraycopy(r2, r4, r6, r4, r1)     // Catch:{ IOException -> 0x00ee }
            r10 = 0
            r11 = 4
        L_0x00af:
            if (r7 == r10) goto L_0x00d2
            int r12 = r7 - r10
            int r12 = r0.read(r9, r4, r12)     // Catch:{ IOException -> 0x00ee }
            if (r12 >= 0) goto L_0x00cc
            java.lang.String r9 = "MiSpeechSDK:FrameParser"
            java.lang.String r11 = "doParserFrame:read end frame data miss "
            com.xiaomi.ai.utils.Log.a(r9, r11)     // Catch:{ IOException -> 0x00ee }
            com.xiaomi.ai.streamplayer.j r9 = r13.b     // Catch:{ IOException -> 0x00ee }
            if (r9 == 0) goto L_0x00d2
            com.xiaomi.ai.streamplayer.j r9 = r13.b     // Catch:{ IOException -> 0x00ee }
            java.lang.String r11 = "frame  misss "
            r9.a(r13, r11)     // Catch:{ IOException -> 0x00ee }
            goto L_0x00d2
        L_0x00cc:
            int r10 = r10 + r12
            java.lang.System.arraycopy(r9, r4, r6, r11, r12)     // Catch:{ IOException -> 0x00ee }
            int r11 = r11 + r12
            goto L_0x00af
        L_0x00d2:
            if (r7 != r10) goto L_0x0012
            com.xiaomi.ai.streamplayer.j r7 = r13.b     // Catch:{ IOException -> 0x00ee }
            if (r7 == 0) goto L_0x0012
            com.xiaomi.ai.streamplayer.j r7 = r13.b     // Catch:{ IOException -> 0x00ee }
            r7.a(r13, r8, r6)     // Catch:{ IOException -> 0x00ee }
            goto L_0x0012
        L_0x00df:
            r5 = 1
            goto L_0x0012
        L_0x00e2:
            com.xiaomi.ai.streamplayer.j r0 = r13.b
            if (r0 == 0) goto L_0x0114
        L_0x00e6:
            com.xiaomi.ai.streamplayer.j r0 = r13.b
            r0.b(r13)
            goto L_0x0114
        L_0x00ec:
            r0 = move-exception
            goto L_0x0115
        L_0x00ee:
            r0 = move-exception
            boolean r1 = r0 instanceof java.io.InterruptedIOException     // Catch:{ all -> 0x00ec }
            if (r1 == 0) goto L_0x00fb
            java.lang.String r0 = "MiSpeechSDK:FrameParser"
            java.lang.String r1 = "InterruptedIOException"
            com.xiaomi.ai.utils.Log.a(r0, r1)     // Catch:{ all -> 0x00ec }
            goto L_0x010f
        L_0x00fb:
            java.lang.String r1 = "MiSpeechSDK:FrameParser"
            java.lang.String r2 = "doParserFrame"
            com.xiaomi.ai.utils.Log.a(r1, r2, r0)     // Catch:{ all -> 0x00ec }
            com.xiaomi.ai.streamplayer.j r1 = r13.b     // Catch:{ all -> 0x00ec }
            if (r1 == 0) goto L_0x010f
            com.xiaomi.ai.streamplayer.j r1 = r13.b     // Catch:{ all -> 0x00ec }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00ec }
            r1.a(r13, r0)     // Catch:{ all -> 0x00ec }
        L_0x010f:
            com.xiaomi.ai.streamplayer.j r0 = r13.b
            if (r0 == 0) goto L_0x0114
            goto L_0x00e6
        L_0x0114:
            return
        L_0x0115:
            com.xiaomi.ai.streamplayer.j r1 = r13.b
            if (r1 == 0) goto L_0x011e
            com.xiaomi.ai.streamplayer.j r1 = r13.b
            r1.b(r13)
        L_0x011e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.streamplayer.b.d():void");
    }

    private static int e(char[] cArr) {
        int i2 = 0;
        if (g(cArr) == e) {
            int floor = (int) Math.floor((double) (((((float) c(cArr)) / 8.0f) * ((float) d(cArr))) / ((float) f(cArr))));
            if (cArr[9] == '1') {
                i2 = 4;
            }
            return floor + i2;
        }
        int floor2 = (int) Math.floor((double) (((((float) c(cArr)) / 8.0f) * ((float) d(cArr))) / ((float) f(cArr))));
        if (cArr[9] == '1') {
            i2 = 1;
        }
        return floor2 + i2;
    }

    private static int f(char[] cArr) {
        if (cArr[20] == '0' && cArr[19] == '0') {
            if (cArr[11] == '0' && cArr[10] == '0') {
                return 11025;
            }
            return (cArr[11] == '0' && cArr[10] == '1') ? TiqiaaService.BaseCallBack.ERROR_CODE_NO_NET : (cArr[11] == '1' && cArr[10] == '0') ? 8000 : -1;
        } else if (cArr[20] != '1' || cArr[19] != '0') {
            if (cArr[20] == '1' && cArr[19] == '1') {
                if (cArr[11] == '0' && cArr[10] == '0') {
                    return 44100;
                }
                if (cArr[11] == '0' && cArr[10] == '1') {
                    return MIOTAudioModule.SAMPLING_RATE;
                }
                if (cArr[11] == '1' && cArr[10] == '0') {
                    return AsrRequest.d;
                }
                return -1;
            }
            return -1;
        } else if (cArr[11] == '0' && cArr[10] == '0') {
            return 22050;
        } else {
            if (cArr[11] == '0' && cArr[10] == '1') {
                return 24000;
            }
            if (cArr[11] == '1' && cArr[10] == '0') {
                return RecordDevice.PCM_FREQUENCE_16K;
            }
            return -1;
        }
    }

    private static String g(char[] cArr) {
        return (cArr[18] == '0' && cArr[17] == '1') ? g : (cArr[18] == '1' && cArr[17] == '0') ? f : (cArr[18] == '1' && cArr[17] == '1') ? e : n;
    }

    private static String h(char[] cArr) {
        return cArr[16] == '0' ? m : l;
    }

    private static String i(char[] cArr) {
        return (cArr[20] == '0' && cArr[19] == '0') ? h : (cArr[20] == '1' && cArr[19] == '0') ? i : (cArr[20] == '1' && cArr[19] == '1') ? j : k;
    }

    public void a() {
        super.a();
        this.v.start();
    }

    public void b() {
        super.b();
        this.d = true;
        this.v.interrupt();
    }
}
