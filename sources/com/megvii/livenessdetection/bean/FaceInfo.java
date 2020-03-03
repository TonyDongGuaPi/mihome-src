package com.megvii.livenessdetection.bean;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import org.json.JSONArray;
import org.json.JSONObject;

public class FaceInfo {
    public float brightness = 0.0f;
    public boolean eyeBlink = false;
    public float eyeLeftDet = 0.0f;
    public float eyeLeftOcclusion = 0.0f;
    public float eyeRightDet = 0.0f;
    public float eyeRightOcclusion = 0.0f;
    public float faceQuality = 0.0f;
    public Rect faceSize;
    public boolean faceTooLarge = false;
    public float gaussianBlur = 0.0f;
    public float integrity = 0.0f;
    public PointF[] landmark;
    public float leftEyeHWRatio = 0.0f;
    public float motionBlur = 0.0f;
    public float mouthDet = 0.0f;
    public float mouthHWRatio = 0.0f;
    public float mouthOcclusion = 0.0f;
    public boolean mouthOpen = false;
    public boolean notVideo = false;
    public float pitch = 0.0f;
    public boolean pitch3d = false;
    public RectF position;
    public float rightEyeHWRatio = 0.0f;
    public float smoothPitch = 0.0f;
    public float smoothQuality = 0.0f;
    public float smoothYaw = 0.0f;
    public float wearGlass = 0.0f;
    public float yaw = 0.0f;

    public String toString() {
        return "FaceInfo{faceSize=" + this.faceSize.toShortString() + ", position=" + this.position.toShortString() + ", yaw=" + this.yaw + ", pitch=" + this.pitch + ", gaussianBlur=" + this.gaussianBlur + ", motionBlur=" + this.motionBlur + ", brightness=" + this.brightness + ", wearGlass=" + this.wearGlass + ", faceQuality=" + this.faceQuality + ", leftEyeHWRatio=" + this.leftEyeHWRatio + ", rightEyeHWRatio=" + this.rightEyeHWRatio + ", mouthHWRatio=" + this.mouthHWRatio + Operators.BLOCK_END;
    }

    public static class FaceInfoFactory {
        public static FaceInfo createFaceInfo(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("has_face")) {
                    if (jSONObject.getBoolean("has_face")) {
                        FaceInfo faceInfo = new FaceInfo();
                        JSONObject jSONObject2 = jSONObject.getJSONObject("pos");
                        faceInfo.pitch = (float) jSONObject2.getDouble("pitch");
                        faceInfo.yaw = (float) jSONObject2.getDouble("yaw");
                        JSONArray jSONArray = jSONObject.getJSONArray("facerect");
                        RectF rectF = new RectF();
                        rectF.left = (float) jSONArray.getDouble(0);
                        rectF.top = (float) jSONArray.getDouble(1);
                        rectF.right = (float) jSONArray.getDouble(2);
                        rectF.bottom = (float) jSONArray.getDouble(3);
                        faceInfo.position = rectF;
                        faceInfo.brightness = (float) jSONObject.getDouble(LightGroupManager.f);
                        faceInfo.motionBlur = (float) jSONObject.getJSONObject("blurness").getDouble("motion");
                        faceInfo.gaussianBlur = (float) jSONObject.getJSONObject("blurness").getDouble("gaussian");
                        faceInfo.wearGlass = (float) jSONObject.getDouble("wearglass");
                        faceInfo.pitch3d = jSONObject.getBoolean("pitch3d");
                        jSONObject.getDouble("eye_hwratio");
                        faceInfo.mouthHWRatio = (float) jSONObject.getDouble("mouth_hwratio");
                        faceInfo.leftEyeHWRatio = (float) jSONObject.getDouble("eye_left_hwratio");
                        faceInfo.rightEyeHWRatio = (float) jSONObject.getDouble("eye_right_hwratio");
                        faceInfo.integrity = (float) jSONObject.getDouble("integrity");
                        Rect rect = new Rect();
                        rect.left = 0;
                        rect.top = 0;
                        rect.right = (int) jSONObject.getDouble("real_width");
                        rect.bottom = (int) jSONObject.getDouble("real_height");
                        faceInfo.faceSize = rect;
                        faceInfo.smoothYaw = (float) jSONObject.getDouble("smooth_yaw");
                        faceInfo.smoothPitch = (float) jSONObject.getDouble("smooth_pitch");
                        faceInfo.notVideo = jSONObject.getBoolean("not_video");
                        faceInfo.eyeBlink = jSONObject.getBoolean("eye_blink");
                        faceInfo.mouthOpen = jSONObject.getBoolean("mouth_open");
                        faceInfo.eyeLeftDet = (float) jSONObject.getDouble("eye_left_det");
                        faceInfo.eyeRightDet = (float) jSONObject.getDouble("eye_right_det");
                        faceInfo.mouthDet = (float) jSONObject.getDouble("mouth_det");
                        faceInfo.faceQuality = (float) jSONObject.getDouble(Constants.Name.QUALITY);
                        faceInfo.eyeLeftOcclusion = (float) jSONObject.getDouble("eye_left_occlusion");
                        faceInfo.eyeRightOcclusion = (float) jSONObject.getDouble("eye_right_occlusion");
                        faceInfo.mouthOcclusion = (float) jSONObject.getDouble("mouth_occlusion");
                        JSONArray jSONArray2 = jSONObject.getJSONArray("landmark");
                        if (jSONArray2.length() % 2 == 0) {
                            faceInfo.landmark = new PointF[(jSONArray2.length() / 2)];
                            for (int i = 0; i < jSONArray2.length() / 2; i++) {
                                PointF pointF = new PointF();
                                int i2 = i << 1;
                                pointF.x = (float) jSONArray2.getDouble(i2);
                                pointF.y = (float) jSONArray2.getDouble(i2 + 1);
                                faceInfo.landmark[i] = pointF;
                            }
                        }
                        faceInfo.faceTooLarge = jSONObject.getBoolean("face_too_large");
                        return faceInfo;
                    }
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
