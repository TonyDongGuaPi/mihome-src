package com.xiaomi.jr.verification.livenessdetection.detector;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.preference.PreferenceManager;
import android.util.Base64;
import com.megvii.licensemanager.ILicenseManager;
import com.megvii.licensemanager.Manager;
import com.megvii.livenessdetection.DetectionConfig;
import com.megvii.livenessdetection.DetectionFrame;
import com.megvii.livenessdetection.Detector;
import com.megvii.livenessdetection.LivenessLicenseManager;
import com.megvii.livenessdetection.bean.FaceIDDataStruct;
import com.megvii.livenessdetection.bean.FaceInfo;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.verification.Constants;
import com.xiaomi.jr.verification.livenessdetection.utils.Util;
import java.util.UUID;

public class FaceplusplusDetector extends Detector {
    private static final String f = "FaceplusplusDetector";
    private static final int g = 300;
    private Detector h;
    /* access modifiers changed from: private */
    public DetectionFrame i = new DetectionFrame();
    /* access modifiers changed from: private */
    public boolean j = true;
    private FaceIDDataStruct k;
    private Detector.DetectionListener l = new Detector.DetectionListener() {
        public Detector.DetectionType onDetectionSuccess(DetectionFrame detectionFrame) {
            if (FaceplusplusDetector.this.j && FaceplusplusDetector.this.e != null) {
                FaceplusplusDetector.this.e.onDetectionSuccess();
            }
            boolean unused = FaceplusplusDetector.this.j = false;
            Detector.b("detection success: hasFace=" + detectionFrame.hasFace());
            return Detector.DetectionType.MOUTH;
        }

        public void onDetectionFailed(Detector.DetectionFailedType detectionFailedType) {
            if (FaceplusplusDetector.this.j && FaceplusplusDetector.this.e != null) {
                FaceplusplusDetector.this.e.onDetectionFailed(FaceplusplusDetector.this.a(detectionFailedType));
            }
            Detector.b("detection fail: type=" + detectionFailedType.name());
            boolean unused = FaceplusplusDetector.this.j = false;
        }

        public void onFrameDetected(long j, DetectionFrame detectionFrame) {
            StringBuilder sb = new StringBuilder();
            sb.append("frame detected: available=");
            sb.append(FaceplusplusDetector.this.j);
            sb.append(", listener=");
            sb.append(FaceplusplusDetector.this.e != null);
            Detector.b(sb.toString());
            if (FaceplusplusDetector.this.j && FaceplusplusDetector.this.e != null) {
                FaceplusplusDetector.this.a(detectionFrame);
                FaceplusplusDetector.this.e.onFrameDetected(j, FaceplusplusDetector.this.i);
            }
        }
    };

    private static String a(int i2) {
        switch (i2) {
            case 0:
                return "ok";
            case 1:
                return "invalid model";
            case 2:
                return "shared lib load fail";
            case 3:
                return "native init fail";
            case 4:
                return "bad cipher";
            case 5:
                return "expire";
            default:
                return null;
        }
    }

    public FaceplusplusDetector(Activity activity, long j2) {
        this.b = activity;
        DetectionConfig.Builder builder = new DetectionConfig.Builder();
        builder.setDetectionTimeout((int) j2);
        this.h = new Detector(activity, builder.build());
        this.h.setDetectionListener(this.l);
    }

    public boolean a() {
        Manager manager = new Manager(this.b);
        LivenessLicenseManager livenessLicenseManager = new LivenessLicenseManager(this.b);
        manager.a((ILicenseManager) livenessLicenseManager);
        manager.c(a((Context) this.b));
        if (livenessLicenseManager.checkCachedLicense() <= 0 || this.b == null) {
            return false;
        }
        byte[] a2 = Util.a(this.b, "faceplusplus_model");
        if (this.h == null || this.h.init(this.b, a2, (String) null, (String) null) != 0) {
            return false;
        }
        return true;
    }

    public void b() {
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
    }

    public boolean a(byte[] bArr, int i2, int i3) {
        return this.h != null && this.h.doDetection(bArr, i2, i3, 270);
    }

    public void a(DetectionType detectionType) {
        if (this.h != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("changeDetectionType: ");
            sb.append(detectionType != null ? detectionType.ordinal() : -1);
            MifiLog.b(f, sb.toString());
            this.h.changeDetectionType(b(detectionType));
            this.h.resetAction();
            this.j = true;
        }
    }

    public void a(long j2) {
        throw new UnsupportedOperationException();
    }

    public byte[] c() {
        e();
        if (this.k == null || this.k.images == null) {
            return null;
        }
        return this.k.images.get("image_best");
    }

    public byte[] d() {
        e();
        if (this.k == null || this.k.images == null) {
            return null;
        }
        return this.k.images.get("image_env");
    }

    public String a(String str) {
        if (!Constants.h.equals(str)) {
            return null;
        }
        e();
        if (this.k != null) {
            return this.k.delta;
        }
        return null;
    }

    private void e() {
        if (this.k == null) {
            this.k = this.h.getFaceIDDataStruct(300);
        }
    }

    /* access modifiers changed from: private */
    public void a(DetectionFrame detectionFrame) {
        this.i.a();
        FaceInfo faceInfo = detectionFrame.getFaceInfo();
        RectF facePos = detectionFrame.getFacePos();
        if (faceInfo != null && facePos != null) {
            this.i.f11054a = faceInfo.smoothQuality;
            this.i.b.left = (int) (((float) this.c) * (1.0f - facePos.right));
            this.i.b.top = (int) (((float) this.d) * facePos.top);
            this.i.b.right = (int) (((float) this.c) * (1.0f - facePos.left));
            this.i.b.bottom = (int) (((float) this.d) * facePos.bottom);
        }
    }

    private Detector.DetectionType b(DetectionType detectionType) {
        Detector.DetectionType detectionType2 = Detector.DetectionType.NONE;
        if (detectionType == DetectionType.BLINK) {
            return Detector.DetectionType.BLINK;
        }
        if (detectionType == DetectionType.MOUTH) {
            return Detector.DetectionType.MOUTH;
        }
        if (detectionType == DetectionType.POS_PITCH_UP) {
            return Detector.DetectionType.POS_PITCH_UP;
        }
        if (detectionType == DetectionType.POS_YAW_LEFT) {
            return Detector.DetectionType.POS_YAW_LEFT;
        }
        return detectionType == DetectionType.POS_YAW_RIGHT ? Detector.DetectionType.POS_YAW_RIGHT : detectionType2;
    }

    /* access modifiers changed from: private */
    public DetectionFailedType a(Detector.DetectionFailedType detectionFailedType) {
        if (detectionFailedType == Detector.DetectionFailedType.ACTIONBLEND) {
            return DetectionFailedType.ACTIONBLEND;
        }
        if (detectionFailedType == Detector.DetectionFailedType.NOTVIDEO) {
            return DetectionFailedType.NOTVIDEO;
        }
        if (detectionFailedType == Detector.DetectionFailedType.TIMEOUT) {
            return DetectionFailedType.TIMEOUT;
        }
        if (detectionFailedType == Detector.DetectionFailedType.MASK) {
            return DetectionFailedType.MASK;
        }
        if (detectionFailedType == Detector.DetectionFailedType.FACENOTCONTINUOUS || detectionFailedType == Detector.DetectionFailedType.TOOMANYFACELOST || detectionFailedType == Detector.DetectionFailedType.FACELOSTNOTCONTINUOUS) {
            return DetectionFailedType.ACTIONTOOFAST;
        }
        return null;
    }

    private static String a(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String string = defaultSharedPreferences.getString("uuid", (String) null);
        if (string != null && string.trim().length() != 0) {
            return string;
        }
        String encodeToString = Base64.encodeToString(UUID.randomUUID().toString().getBytes(), 0);
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        edit.putString("uuid", encodeToString);
        edit.apply();
        return encodeToString;
    }
}
