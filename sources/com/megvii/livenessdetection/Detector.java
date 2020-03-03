package com.megvii.livenessdetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Base64;
import com.megvii.livenessdetection.DetectionConfig;
import com.megvii.livenessdetection.DetectionFrame;
import com.megvii.livenessdetection.bean.FaceIDDataStruct;
import com.megvii.livenessdetection.bean.FaceInfo;
import com.megvii.livenessdetection.impl.b;
import com.megvii.livenessdetection.obf.d;
import com.megvii.livenessdetection.obf.e;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.common.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detector {
    public static final int DETECTOR_INIT_FAILED_BADCIPHER = 4;
    public static final int DETECTOR_INIT_FAILED_EXPIRE = 5;
    public static final int DETECTOR_INIT_FAILED_INVALIDMODEL = 1;
    public static final int DETECTOR_INIT_FAILED_NATIVEINITFAILED = 3;
    public static final int DETECTOR_INIT_FAILED_SHAREDLIBLOADFAILED = 2;
    public static final int DETECTOR_INIT_OK = 0;
    private static boolean d = false;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public DetectionConfig f6671a = null;
    /* access modifiers changed from: private */
    public long b = 0;
    private long c = 10;
    private Context e;
    /* access modifiers changed from: private */
    public e f;
    /* access modifiers changed from: private */
    public BlockingQueue<b> g;
    private a h;
    /* access modifiers changed from: private */
    public DetectionListener i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public Handler k;
    /* access modifiers changed from: private */
    public boolean l = true;
    /* access modifiers changed from: private */
    public com.megvii.livenessdetection.obf.a m;
    private Map<String, DetectionFrame> n;
    private boolean o = true;
    /* access modifiers changed from: private */
    public b p = null;
    /* access modifiers changed from: private */
    public b q = null;
    /* access modifiers changed from: private */
    public long r = -1;
    /* access modifiers changed from: private */
    public DetectionType s = DetectionType.NONE;
    /* access modifiers changed from: private */
    public ArrayList<DetectionFrame> t;

    public enum DetectionFailedType {
        ACTIONBLEND,
        NOTVIDEO,
        TIMEOUT,
        MASK,
        FACENOTCONTINUOUS,
        TOOMANYFACELOST,
        FACELOSTNOTCONTINUOUS
    }

    public interface DetectionListener {
        void onDetectionFailed(DetectionFailedType detectionFailedType);

        DetectionType onDetectionSuccess(DetectionFrame detectionFrame);

        void onFrameDetected(long j, DetectionFrame detectionFrame);
    }

    /* access modifiers changed from: private */
    public native String nativeDetection(long j2, int i2, byte[] bArr, int i3, int i4, int i5);

    private native String nativeEncode(long j2, byte[] bArr);

    private native String nativeFaceQuality(long j2, byte[] bArr, int i2, int i3);

    private static native String nativeGetVersion();

    private native long nativeRawInit(Context context, byte[] bArr, String str, String str2, String str3);

    private native void nativeRelease(long j2);

    /* access modifiers changed from: private */
    public native void nativeReset(long j2);

    /* access modifiers changed from: private */
    public native void waitNormal(long j2);

    public Detector(Context context, DetectionConfig detectionConfig) {
        if (detectionConfig == null) {
            this.f6671a = new DetectionConfig.Builder().build();
        }
        this.e = context.getApplicationContext();
        this.f6671a = detectionConfig;
        this.b = 0;
        this.j = false;
        this.l = true;
        this.m = new com.megvii.livenessdetection.obf.a();
        this.f = new e(this.e);
        this.n = new HashMap();
    }

    public synchronized boolean init(Context context, String str) {
        return a(context, str, (byte[]) null, (String) null, (String) null) == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0021 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0023 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean init(android.content.Context r9, byte[] r10, java.lang.String r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = 0
            r1 = 1
            if (r11 == 0) goto L_0x0012
            java.lang.String r2 = "W6VLf6PitAIkKiFuVXBeTe54CSc8jB"
            boolean r11 = r11.equals(r2)     // Catch:{ all -> 0x0010 }
            if (r11 != 0) goto L_0x000e
            goto L_0x0012
        L_0x000e:
            r11 = 0
            goto L_0x0013
        L_0x0010:
            r9 = move-exception
            goto L_0x0025
        L_0x0012:
            r11 = 1
        L_0x0013:
            r8.o = r11     // Catch:{ all -> 0x0010 }
            r4 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            r5 = r10
            int r9 = r2.a((android.content.Context) r3, (java.lang.String) r4, (byte[]) r5, (java.lang.String) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0010 }
            if (r9 != 0) goto L_0x0023
            monitor-exit(r8)
            return r1
        L_0x0023:
            monitor-exit(r8)
            return r0
        L_0x0025:
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenessdetection.Detector.init(android.content.Context, byte[], java.lang.String):boolean");
    }

    public synchronized int init(Context context, byte[] bArr, String str, String str2) {
        boolean z;
        if (str2 != null) {
            try {
                if (str2.equals("W6VLf6PitAIkKiFuVXBeTe54CSc8jB")) {
                    z = false;
                    this.o = z;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        z = true;
        this.o = z;
        return a(context, (String) null, bArr, str, (String) null);
    }

    public synchronized int init(Context context, byte[] bArr, String str, String str2, String str3) {
        return a(context, (String) null, bArr, str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dd, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int a(android.content.Context r9, java.lang.String r10, byte[] r11, java.lang.String r12, java.lang.String r13) {
        /*
            r8 = this;
            monitor-enter(r8)
            r8.e = r9     // Catch:{ all -> 0x00de }
            r0 = 1
            if (r10 != 0) goto L_0x000a
            if (r11 != 0) goto L_0x000a
            monitor-exit(r8)
            return r0
        L_0x000a:
            if (r11 != 0) goto L_0x0010
            byte[] r11 = com.megvii.livenessdetection.obf.b.a((java.lang.String) r10)     // Catch:{ all -> 0x00de }
        L_0x0010:
            r2 = r11
            if (r2 == 0) goto L_0x00dc
            java.lang.String r10 = "b3c61531d3a785d8af140218304940e5b24834d3"
            java.lang.String r11 = com.megvii.livenessdetection.obf.b.a((byte[]) r2)     // Catch:{ all -> 0x00de }
            boolean r10 = r10.equalsIgnoreCase(r11)     // Catch:{ all -> 0x00de }
            if (r10 != 0) goto L_0x0021
            goto L_0x00dc
        L_0x0021:
            boolean r10 = d     // Catch:{ all -> 0x00de }
            if (r10 != 0) goto L_0x0043
            android.content.Context r10 = r9.getApplicationContext()     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.obf.c r10 = com.megvii.livenessdetection.obf.c.a((android.content.Context) r10)     // Catch:{ all -> 0x00de }
            java.lang.String r11 = "livenessdetection"
            java.lang.String r0 = "v2.4.5"
            boolean r10 = r10.a((java.lang.String) r11, (java.lang.String) r0)     // Catch:{ all -> 0x00de }
            if (r10 != 0) goto L_0x0043
            if (r13 == 0) goto L_0x0040
            boolean r10 = com.megvii.livenessdetection.obf.b.b(r13)     // Catch:{ all -> 0x00de }
            if (r10 != 0) goto L_0x0043
        L_0x0040:
            r9 = 2
            monitor-exit(r8)
            return r9
        L_0x0043:
            com.megvii.livenessdetection.LivenessLicenseManager r10 = new com.megvii.livenessdetection.LivenessLicenseManager     // Catch:{ all -> 0x00de }
            android.content.Context r11 = r8.e     // Catch:{ all -> 0x00de }
            android.content.Context r11 = r11.getApplicationContext()     // Catch:{ all -> 0x00de }
            r10.<init>(r11)     // Catch:{ all -> 0x00de }
            long r10 = r10.checkCachedLicense()     // Catch:{ all -> 0x00de }
            r6 = 0
            int r13 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r13 != 0) goto L_0x005b
            r9 = 4
            monitor-exit(r8)
            return r9
        L_0x005b:
            com.megvii.livenessdetection.obf.e r10 = r8.f     // Catch:{ Exception -> 0x006d }
            java.lang.String r11 = "889109d126886bd98bc8f6a70d138545"
            java.lang.String r10 = r10.a(r11)     // Catch:{ Exception -> 0x006d }
            if (r10 == 0) goto L_0x0071
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ Exception -> 0x006d }
            long r10 = (long) r10     // Catch:{ Exception -> 0x006d }
            r8.c = r10     // Catch:{ Exception -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r10 = 10
            r8.c = r10     // Catch:{ all -> 0x00de }
        L_0x0071:
            r8.release()     // Catch:{ all -> 0x00de }
            java.util.concurrent.LinkedBlockingDeque r10 = new java.util.concurrent.LinkedBlockingDeque     // Catch:{ all -> 0x00de }
            r11 = 3
            r10.<init>(r11)     // Catch:{ all -> 0x00de }
            r8.g = r10     // Catch:{ all -> 0x00de }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x00de }
            r10.<init>()     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.obf.e r13 = r8.f     // Catch:{ all -> 0x00de }
            java.lang.String r0 = "cb072839e1e240a23baae123ca6cf165"
            java.lang.String r13 = r13.a(r0)     // Catch:{ all -> 0x00de }
            r10.append(r13)     // Catch:{ all -> 0x00de }
            java.lang.String r13 = ":"
            r10.append(r13)     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.obf.e r13 = r8.f     // Catch:{ all -> 0x00de }
            java.lang.String r0 = "e2380b201325a8f252636350338aeae8"
            java.lang.String r13 = r13.a(r0)     // Catch:{ all -> 0x00de }
            r10.append(r13)     // Catch:{ all -> 0x00de }
            java.lang.String r4 = r10.toString()     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.DetectionConfig r10 = r8.f6671a     // Catch:{ all -> 0x00de }
            java.lang.String r5 = r10.toJsonString()     // Catch:{ all -> 0x00de }
            r0 = r8
            r1 = r9
            r3 = r12
            long r9 = r0.nativeRawInit(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00de }
            r8.b = r9     // Catch:{ all -> 0x00de }
            long r9 = r8.b     // Catch:{ all -> 0x00de }
            int r12 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r12 != 0) goto L_0x00b7
            monitor-exit(r8)
            return r11
        L_0x00b7:
            com.megvii.livenessdetection.Detector$a r9 = new com.megvii.livenessdetection.Detector$a     // Catch:{ all -> 0x00de }
            r9.<init>()     // Catch:{ all -> 0x00de }
            r8.h = r9     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.Detector$a r9 = r8.h     // Catch:{ all -> 0x00de }
            r9.start()     // Catch:{ all -> 0x00de }
            com.megvii.livenessdetection.Detector$DetectionType r9 = com.megvii.livenessdetection.Detector.DetectionType.NONE     // Catch:{ all -> 0x00de }
            r8.s = r9     // Catch:{ all -> 0x00de }
            android.os.Handler r9 = new android.os.Handler     // Catch:{ all -> 0x00de }
            android.os.Looper r10 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x00de }
            r9.<init>(r10)     // Catch:{ all -> 0x00de }
            r8.k = r9     // Catch:{ all -> 0x00de }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x00de }
            r9.<init>()     // Catch:{ all -> 0x00de }
            r8.t = r9     // Catch:{ all -> 0x00de }
            r9 = 0
            monitor-exit(r8)
            return r9
        L_0x00dc:
            monitor-exit(r8)
            return r0
        L_0x00de:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenessdetection.Detector.a(android.content.Context, java.lang.String, byte[], java.lang.String, java.lang.String):int");
    }

    public synchronized void release() {
        if (this.h != null) {
            this.h.interrupt();
            try {
                this.h.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.h = null;
        }
        if (this.g != null) {
            this.g.clear();
            this.g = null;
        }
        if (this.t != null) {
            this.t.clear();
            this.t = null;
        }
        if (this.b != 0) {
            nativeRelease(this.b);
        }
        this.b = 0;
    }

    public void enableDebug(boolean z) {
        if (z) {
            d.a();
        } else {
            d.b();
        }
    }

    public String getLog() {
        if (this.m == null) {
            return null;
        }
        return this.m.toString();
    }

    public DetectionType getCurDetectType() {
        return this.s;
    }

    public boolean doDetection(byte[] bArr, int i2, int i3, int i4) {
        if (this.b == 0 || this.i == null || this.s == DetectionType.DONE || this.s == null || this.j) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.b != 0);
            objArr[1] = Boolean.valueOf(this.i == null);
            d.b(String.format("detector inited:%b, detectionlistener is null:%b", objArr));
            return false;
        }
        try {
            return this.g.offer(new b(bArr, i2, i3, i4, this.s));
        } catch (Exception unused) {
            return false;
        }
    }

    public synchronized void setDetectionListener(DetectionListener detectionListener) {
        this.i = detectionListener;
    }

    static {
        try {
            System.loadLibrary("livenessdetection_v2.4.5");
            d = true;
        } catch (UnsatisfiedLinkError unused) {
            d.b("static load library error ");
            d = false;
        }
    }

    public enum DetectionType {
        NONE(0),
        BLINK(1),
        MOUTH(2),
        POS_YAW(3),
        POS_PITCH(4),
        POS_YAW_LEFT(7),
        POS_YAW_RIGHT(8),
        DONE(6),
        POS_PITCH_UP(9),
        POS_PITCH_DOWN(10),
        AIMLESS(-1);
        
        /* access modifiers changed from: private */
        public int mInterVal;

        private DetectionType(int i) {
            this.mInterVal = -1;
            this.mInterVal = i;
        }
    }

    public synchronized void changeDetectionType(DetectionType detectionType) {
        if (this.b != 0) {
            if (detectionType != null) {
                this.j = false;
                this.s = detectionType;
                nativeReset(this.b);
                this.r = System.currentTimeMillis();
                this.l = true;
                this.m.a(detectionType);
                return;
            }
            throw new RuntimeException("DetectionType could not be null");
        }
    }

    public synchronized void reset() {
        if (this.b != 0) {
            this.p = null;
            this.q = null;
            this.t = new ArrayList<>();
            this.j = false;
            changeDetectionType(DetectionType.NONE);
            this.l = true;
            this.m.a();
            this.n.clear();
        }
    }

    public synchronized void resetAction() {
        if (this.b != 0) {
            this.j = false;
            this.l = true;
            changeDetectionType(this.s);
        }
    }

    public synchronized ArrayList<DetectionFrame> getValidFrame() {
        if (this.t == null) {
            return null;
        }
        ArrayList<DetectionFrame> arrayList = new ArrayList<>(this.t);
        arrayList.add(0, this.p);
        return arrayList;
    }

    class a extends Thread {
        private com.megvii.livenessdetection.obf.b b = new com.megvii.livenessdetection.obf.b();

        public a() {
            this.b.a(true);
        }

        public final void run() {
            while (true) {
                try {
                    final b bVar = (b) Detector.this.g.take();
                    if (bVar != null) {
                        if (Detector.this.b != 0) {
                            if (Detector.this.s != DetectionType.DONE) {
                                if (System.currentTimeMillis() <= Detector.this.r + Detector.this.f6671a.timeout || Detector.this.s == DetectionType.NONE || Detector.this.s == DetectionType.AIMLESS) {
                                    byte[] yUVData = bVar.getYUVData();
                                    int imageWidth = bVar.getImageWidth();
                                    int imageHeight = bVar.getImageHeight();
                                    int rotation = bVar.getRotation();
                                    DetectionType c = Detector.this.s;
                                    final DetectionListener g = Detector.this.i;
                                    if (!(c == null || Detector.this.b == 0 || g == null)) {
                                        if (!Detector.this.j) {
                                            if (Detector.this.l) {
                                                boolean unused = Detector.this.l = false;
                                                Detector.this.waitNormal(Detector.this.b);
                                            }
                                            String a2 = Detector.this.nativeDetection(Detector.this.b, c.mInterVal, yUVData, imageWidth, imageHeight, rotation);
                                            try {
                                                JSONObject jSONObject = new JSONObject(a2);
                                                if (!Detector.this.j) {
                                                    if (c == bVar.a()) {
                                                        bVar.a(a2, Detector.this.f6671a, this.b);
                                                        if (c != DetectionType.NONE) {
                                                            if (c != DetectionType.AIMLESS) {
                                                                if (bVar.hasFace()) {
                                                                    b unused2 = Detector.this.q = bVar;
                                                                    Detector.this.a((DetectionFrame) bVar);
                                                                }
                                                                switch (jSONObject.getInt("result")) {
                                                                    case 1:
                                                                        if (bVar != null && bVar.hasFace() && bVar.getFaceInfo().faceTooLarge) {
                                                                            Detector.this.nativeReset(Detector.this.b);
                                                                            break;
                                                                        } else {
                                                                            Detector.this.t.add(Detector.this.q);
                                                                            boolean unused3 = Detector.this.j = true;
                                                                            bVar.setFrameType(DetectionFrame.FrameType.NONE);
                                                                            Detector.this.k.post(new Runnable() {
                                                                                public final void run() {
                                                                                    g.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), bVar);
                                                                                    DetectionType onDetectionSuccess = g.onDetectionSuccess(bVar);
                                                                                    if (onDetectionSuccess == null || onDetectionSuccess == DetectionType.DONE) {
                                                                                        DetectionType unused = Detector.this.s = DetectionType.DONE;
                                                                                        Detector.this.g.clear();
                                                                                        if (Detector.this.m != null) {
                                                                                            Detector.this.m.a(Detector.this.s);
                                                                                            Detector.j(Detector.this);
                                                                                            return;
                                                                                        }
                                                                                        return;
                                                                                    }
                                                                                    Detector.this.changeDetectionType(onDetectionSuccess);
                                                                                }
                                                                            });
                                                                            break;
                                                                        }
                                                                    case 2:
                                                                        bVar.setFrameType(DetectionFrame.FrameType.NONE);
                                                                        a(bVar);
                                                                        Detector.this.k.post(new Runnable() {
                                                                            public final void run() {
                                                                                g.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), bVar);
                                                                            }
                                                                        });
                                                                        break;
                                                                    case 3:
                                                                        Detector.this.k.post(new Runnable() {
                                                                            public final void run() {
                                                                                g.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), bVar);
                                                                            }
                                                                        });
                                                                        break;
                                                                    case 4:
                                                                        a(DetectionFailedType.NOTVIDEO, g, bVar);
                                                                        break;
                                                                    case 5:
                                                                        a(DetectionFailedType.ACTIONBLEND, g, bVar);
                                                                        break;
                                                                    case 6:
                                                                        d.a("LivenessDetection", "wait for normal success");
                                                                        bVar.setFrameType(DetectionFrame.FrameType.WAITINGNORMAL);
                                                                        a(bVar);
                                                                        Detector.this.k.post(new Runnable() {
                                                                            public final void run() {
                                                                                g.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), bVar);
                                                                            }
                                                                        });
                                                                        break;
                                                                    case 7:
                                                                        d.a("LivenessDetection", "is waiting for normal");
                                                                        bVar.setFrameType(DetectionFrame.FrameType.WAITINGNORMAL);
                                                                        a(bVar);
                                                                        Detector.this.k.post(new Runnable() {
                                                                            public final void run() {
                                                                                g.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), bVar);
                                                                            }
                                                                        });
                                                                        break;
                                                                    case 8:
                                                                        a(DetectionFailedType.MASK, g, bVar);
                                                                        break;
                                                                    case 9:
                                                                        a(DetectionFailedType.FACENOTCONTINUOUS, g, bVar);
                                                                        break;
                                                                    case 10:
                                                                        a(DetectionFailedType.TOOMANYFACELOST, g, bVar);
                                                                        break;
                                                                    case 11:
                                                                        a(DetectionFailedType.FACELOSTNOTCONTINUOUS, g, bVar);
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                        bVar.setFrameType(DetectionFrame.FrameType.NONE);
                                                        Detector.this.k.post(new Runnable() {
                                                            public final void run() {
                                                                g.onFrameDetected(Detector.this.f6671a.timeout, bVar);
                                                            }
                                                        });
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                } else if (!Detector.this.j) {
                                    a(DetectionFailedType.TIMEOUT, Detector.this.i, bVar);
                                }
                            }
                        }
                    }
                } catch (InterruptedException unused4) {
                    return;
                }
            }
        }

        private void a(final DetectionFailedType detectionFailedType, final DetectionListener detectionListener, final DetectionFrame detectionFrame) {
            Detector.this.m.a(detectionFailedType);
            if (!(Detector.this.m == null || Detector.this.f == null)) {
                Detector.j(Detector.this);
            }
            boolean unused = Detector.this.j = true;
            Detector.this.k.post(new Runnable() {
                public final void run() {
                    detectionListener.onFrameDetected((Detector.this.r + Detector.this.f6671a.timeout) - System.currentTimeMillis(), detectionFrame);
                    detectionListener.onDetectionFailed(detectionFailedType);
                }
            });
        }

        private void a(b bVar) {
            if (Detector.this.p == null) {
                b unused = Detector.this.p = bVar;
            }
            if (bVar.a(Detector.this.p)) {
                b unused2 = Detector.this.p = bVar;
            }
        }
    }

    public synchronized DetectionFrame faceQualityDetection(Bitmap bitmap) {
        com.megvii.livenessdetection.impl.a aVar = new com.megvii.livenessdetection.impl.a(bitmap);
        byte[] a2 = aVar.a();
        int imageWidth = aVar.getImageWidth();
        int imageHeight = aVar.getImageHeight();
        if (!(a2 == null || imageWidth == -1)) {
            if (imageHeight != -1) {
                aVar.a(nativeFaceQuality(this.b, a2, imageWidth, imageHeight), this.f6671a, new com.megvii.livenessdetection.obf.b());
                return aVar;
            }
        }
        return null;
    }

    public static String getVersion() {
        try {
            return nativeGetVersion();
        } catch (UnsatisfiedLinkError unused) {
            d.a("dynamic library does not load successfully, try to internalInit it with detector.init method");
            return "Could not read message from native method";
        }
    }

    public FaceIDDataStruct getFaceIDDataStruct(int i2) {
        JSONObject jSONObject = new JSONObject();
        FaceIDDataStruct faceIDDataStruct = new FaceIDDataStruct();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        b bVar = this.p;
        try {
            jSONObject2.put("image_best", a((DetectionFrame) bVar, i2, "image_best", faceIDDataStruct, true));
            if (this.t != null) {
                int i3 = 0;
                while (i3 < this.t.size()) {
                    StringBuilder sb = new StringBuilder("image_action");
                    int i4 = i3 + 1;
                    sb.append(i4);
                    String sb2 = sb.toString();
                    jSONObject2.put(sb2, a(this.t.get(i3), i2, "image_action" + i4, faceIDDataStruct, true));
                    i3 = i4;
                }
            }
            if (this.n != null) {
                for (Map.Entry next : this.n.entrySet()) {
                    JSONObject b2 = b((DetectionFrame) next.getValue());
                    if (b2 != null) {
                        jSONObject3.put((String) next.getKey(), b2);
                    }
                }
            }
            jSONObject2.put("image_env", a((DetectionFrame) bVar, i2, "image_env", faceIDDataStruct, false));
            jSONObject.put("images", jSONObject2);
            jSONObject.put("snapshot", jSONObject3);
            jSONObject.put(Constants.Value.DATETIME, new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date()));
            jSONObject.put(DTransferConstants.l, getVersion());
            jSONObject.put("bid", this.e.getPackageName());
            String a2 = this.f.a("cb072839e1e240a23ccc123ca6cf165");
            if (a2 != null) {
                jSONObject.put("uuid", a2);
            }
            String a3 = this.f.a("cb072839e1e240a23baae123ca6cf165");
            if (a3 != null && a3.length() >= 16) {
                jSONObject.put("sid", a3.substring(0, 16));
            }
            jSONObject.put("user_info", com.megvii.livenessdetection.obf.b.a());
            jSONObject.put("log", getLog());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        faceIDDataStruct.delta = nativeEncode(this.b, jSONObject.toString().getBytes());
        return faceIDDataStruct;
    }

    /* access modifiers changed from: package-private */
    public final void a(DetectionFrame detectionFrame) {
        DetectionFrame detectionFrame2;
        DetectionFrame detectionFrame3;
        DetectionFrame detectionFrame4;
        DetectionFrame detectionFrame5;
        if (detectionFrame != null && detectionFrame.hasFace()) {
            if (((double) Math.abs(detectionFrame.getFaceInfo().yaw)) >= 0.167d && ((detectionFrame5 = this.n.get("yaw")) == null || detectionFrame5.getFaceInfo() == null || detectionFrame5.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
                this.n.put("yaw", detectionFrame);
            }
            if (((double) Math.abs(detectionFrame.getFaceInfo().pitch)) >= 0.111d && ((detectionFrame4 = this.n.get("pitch")) == null || detectionFrame4.getFaceInfo() == null || detectionFrame4.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
                this.n.put("pitch", detectionFrame);
            }
            if (Math.abs(detectionFrame.getFaceInfo().mouthHWRatio) >= 0.2f && ((detectionFrame3 = this.n.get("mouth")) == null || detectionFrame3.getFaceInfo() == null || detectionFrame3.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
                this.n.put("mouth", detectionFrame);
            }
            if (Math.abs(detectionFrame.getFaceInfo().leftEyeHWRatio) <= 0.3f && Math.abs(detectionFrame.getFaceInfo().rightEyeHWRatio) <= 0.3f && ((detectionFrame2 = this.n.get("eye")) == null || detectionFrame2.getFaceInfo() == null || detectionFrame2.getFaceInfo().smoothQuality < detectionFrame.getFaceInfo().smoothQuality)) {
                this.n.put("eye", detectionFrame);
            }
            if (this.o) {
                DetectionFrame detectionFrame6 = this.n.get("max_pitch");
                if (detectionFrame6 == null || detectionFrame6.getFaceInfo() == null || Math.abs(detectionFrame6.getFaceInfo().pitch) < Math.abs(detectionFrame.getFaceInfo().pitch)) {
                    if (((double) Math.abs(detectionFrame.getFaceInfo().pitch)) > 0.2d) {
                        RectF rectF = detectionFrame.getFaceInfo().position;
                        float width = rectF.width();
                        float height = rectF.height();
                        float f2 = width / 10.0f;
                        rectF.left -= f2;
                        rectF.right += f2;
                        float f3 = height / 10.0f;
                        rectF.top -= f3;
                        rectF.bottom += f3;
                    }
                    this.n.put("max_pitch", detectionFrame);
                }
                DetectionFrame detectionFrame7 = this.n.get("max_yaw");
                if (detectionFrame7 == null || detectionFrame7.getFaceInfo() == null || Math.abs(detectionFrame7.getFaceInfo().yaw) < Math.abs(detectionFrame.getFaceInfo().yaw)) {
                    if (((double) Math.abs(detectionFrame.getFaceInfo().yaw)) > 0.2d) {
                        RectF rectF2 = detectionFrame.getFaceInfo().position;
                        float width2 = rectF2.width();
                        float height2 = rectF2.height();
                        float f4 = width2 / 10.0f;
                        rectF2.left -= f4;
                        rectF2.right += f4;
                        float f5 = height2 / 10.0f;
                        rectF2.top -= f5;
                        rectF2.bottom += f5;
                    }
                    this.n.put("max_yaw", detectionFrame);
                }
            }
        }
    }

    public FaceIDDataStruct getFaceIDDataStruct() {
        return getFaceIDDataStruct(-1);
    }

    private static JSONObject b(DetectionFrame detectionFrame) {
        if (detectionFrame == null || !detectionFrame.hasFace()) {
            return null;
        }
        Rect rect = new Rect();
        byte[] imageData = detectionFrame.getImageData(rect, true, 90, 150, false, false, 0);
        if (imageData == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("image", Base64.encodeToString(imageData, 2));
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(rect.left);
            jSONArray.put(rect.top);
            jSONArray.put(rect.right);
            jSONArray.put(rect.bottom);
            jSONObject.put(CameraPropertyBase.u, jSONArray);
            jSONObject.put("smooth_quality", (double) detectionFrame.getFaceInfo().smoothQuality);
            jSONObject.put(Constants.Name.QUALITY, (double) detectionFrame.getFaceInfo().faceQuality);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static JSONObject a(DetectionFrame detectionFrame, int i2, String str, FaceIDDataStruct faceIDDataStruct, boolean z) {
        byte[] bArr;
        if (detectionFrame == null || !detectionFrame.hasFace()) {
            return null;
        }
        Rect rect = new Rect();
        if (!z) {
            FaceInfo faceInfo = detectionFrame.getFaceInfo();
            bArr = detectionFrame.getImageData(rect, false, 70, (int) (150.0f / Math.min(faceInfo.position.width(), faceInfo.position.height())), false, false, 0);
        } else {
            bArr = detectionFrame.getImageData(rect, true, 70, i2, false, false, 0);
        }
        if (bArr == null) {
            return null;
        }
        faceIDDataStruct.images.put(str, bArr);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.Name.QUALITY, (double) detectionFrame.getFaceInfo().faceQuality);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(rect.left);
            jSONArray.put(rect.top);
            jSONArray.put(rect.right);
            jSONArray.put(rect.bottom);
            jSONObject.put(CameraPropertyBase.u, jSONArray);
            jSONObject.put(com.mobikwik.sdk.lib.Constants.LABEL_CHECKSUM, com.megvii.livenessdetection.obf.b.a(bArr));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    static /* synthetic */ void j(Detector detector) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        if (detector.m != null) {
            try {
                jSONArray = new JSONArray(detector.f.b("8cd0604ba33e2ba7f38a56f0aec08a54"));
            } catch (Exception unused) {
                jSONArray = new JSONArray();
            }
            jSONArray.put(detector.m.toString());
            if (((long) jSONArray.length()) > detector.c) {
                jSONArray2 = new JSONArray();
                for (int i2 = 1; i2 < jSONArray.length(); i2++) {
                    try {
                        jSONArray2.put(jSONArray.get(i2));
                    } catch (JSONException unused2) {
                    }
                }
            } else {
                jSONArray2 = jSONArray;
            }
            detector.f.a("8cd0604ba33e2ba7f38a56f0aec08a54", jSONArray2.toString());
        }
    }
}
