package com.megvii.livenessdetection;

import org.json.JSONException;
import org.json.JSONObject;

public final class DetectionConfig {
    public final float eyeOpenThreshold;
    public final float gaussianBlur;
    public final float integrity;
    public final int maxBrightness;
    public final int minBrightness;
    public final float minFaceSize;
    public final float motionBlur;
    public final float mouthOpenThreshold;
    public final float pitchAngle;
    public final long timeout;
    public final float yawAngle;

    /* synthetic */ DetectionConfig(Builder builder, byte b) {
        this(builder);
    }

    @Deprecated
    public final int getMinBrightness() {
        return this.minBrightness;
    }

    @Deprecated
    public final int getMaxBrightness() {
        return this.maxBrightness;
    }

    @Deprecated
    public final float getMotionBlur() {
        return this.motionBlur;
    }

    @Deprecated
    public final float getGaussianBlur() {
        return this.gaussianBlur;
    }

    @Deprecated
    public final long getTimeout() {
        return this.timeout;
    }

    @Deprecated
    public final float getYawAngle() {
        return this.yawAngle;
    }

    @Deprecated
    public final float getPitchAngle() {
        return this.pitchAngle;
    }

    @Deprecated
    public final float getMinFaceSize() {
        return this.minFaceSize;
    }

    @Deprecated
    public final float getEyeOpenThreshold() {
        return this.eyeOpenThreshold;
    }

    @Deprecated
    public final float getMouthOpenThreshold() {
        return this.mouthOpenThreshold;
    }

    private DetectionConfig(Builder builder) {
        this.gaussianBlur = builder.f;
        this.motionBlur = builder.e;
        this.pitchAngle = builder.b;
        this.yawAngle = builder.f6669a;
        this.minBrightness = builder.c;
        this.maxBrightness = builder.d;
        this.minFaceSize = builder.g;
        this.timeout = (long) builder.h;
        this.eyeOpenThreshold = builder.i;
        this.mouthOpenThreshold = builder.j;
        this.integrity = builder.k;
    }

    public final String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("gaussianBlur", (double) this.gaussianBlur);
            jSONObject.put("motionBlur", (double) this.motionBlur);
            jSONObject.put("pitchAngle", (double) this.pitchAngle);
            jSONObject.put("yawAngle", (double) this.yawAngle);
            jSONObject.put("minBrightness", this.minBrightness);
            jSONObject.put("maxBrightness", this.maxBrightness);
            jSONObject.put("minFaceSize", (double) this.minFaceSize);
            jSONObject.put("timeout", this.timeout);
            jSONObject.put("eyeOpenThreshold", (double) this.eyeOpenThreshold);
            jSONObject.put("mouthOpenThreshold", (double) this.mouthOpenThreshold);
            jSONObject.put("integrity", (double) this.integrity);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public float f6669a = 0.17f;
        /* access modifiers changed from: private */
        public float b = 0.17f;
        /* access modifiers changed from: private */
        public int c = 80;
        /* access modifiers changed from: private */
        public int d = 170;
        /* access modifiers changed from: private */
        public float e = 0.1f;
        /* access modifiers changed from: private */
        public float f = 0.08f;
        /* access modifiers changed from: private */
        public float g = 150.0f;
        /* access modifiers changed from: private */
        public int h = 10000;
        /* access modifiers changed from: private */
        public float i = 0.3f;
        /* access modifiers changed from: private */
        public float j = 0.4f;
        /* access modifiers changed from: private */
        public float k = 0.9f;

        public final Builder setMinFaceSize(int i2) {
            this.g = (float) i2;
            return this;
        }

        public final Builder setDetectionTimeout(int i2) {
            this.h = i2;
            return this;
        }

        public final Builder setMaxAngle(float f2, float f3, float f4) {
            this.b = f2;
            this.f6669a = f3;
            return this;
        }

        public final Builder setBrightness(int i2, int i3) {
            this.c = i2;
            this.d = i3;
            return this;
        }

        public final Builder setMouthHwratio(float f2) {
            this.j = f2;
            return this;
        }

        public final Builder setEyeHwratio(float f2) {
            this.i = f2;
            return this;
        }

        public final Builder setBlur(float f2, float f3) {
            this.f = f2;
            this.e = f3;
            return this;
        }

        public final Builder setIntegrity(float f2) {
            this.k = f2;
            return this;
        }

        public final DetectionConfig build() {
            return new DetectionConfig(this, (byte) 0);
        }
    }
}
