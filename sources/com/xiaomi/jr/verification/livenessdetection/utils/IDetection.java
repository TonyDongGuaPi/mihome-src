package com.xiaomi.jr.verification.livenessdetection.utils;

import android.content.Context;
import com.xiaomi.jr.verification.R;
import com.xiaomi.jr.verification.livenessdetection.LivenessDetectionActivity;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionFailedType;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IDetection {
    private static Map<DetectionType, String> c;
    private static Map<DetectionType, String> d;
    private static Map<DetectionFailedType, String> e;
    private static Map<LivenessDetectionActivity.FinishType, String> f;

    /* renamed from: a  reason: collision with root package name */
    public int f11056a = 3;
    public List<DetectionType> b;

    public IDetection(Context context) {
        if (c == null) {
            c = new HashMap();
            c.put(DetectionType.BLINK, context.getString(R.string.liveness_blink));
            c.put(DetectionType.MOUTH, context.getString(R.string.liveness_mouth));
            c.put(DetectionType.POS_PITCH_UP, context.getString(R.string.liveness_pos_pitch_up));
            c.put(DetectionType.POS_YAW_LEFT, context.getString(R.string.liveness_pos_yaw_left));
            c.put(DetectionType.POS_YAW_RIGHT, context.getString(R.string.liveness_pos_yaw_right));
        }
        if (d == null) {
            d = new HashMap();
            d.put(DetectionType.BLINK, context.getString(R.string.stat_action_blink));
            d.put(DetectionType.MOUTH, context.getString(R.string.stat_action_mouth));
            d.put(DetectionType.POS_PITCH_UP, context.getString(R.string.stat_action_pos_pitch_up));
            d.put(DetectionType.POS_YAW_LEFT, context.getString(R.string.stat_action_pos_yaw_left));
            d.put(DetectionType.POS_YAW_RIGHT, context.getString(R.string.stat_action_pos_yaw_right));
        }
        if (e == null) {
            e = new HashMap();
            e.put(DetectionFailedType.ACTIONBLEND, context.getString(R.string.liveness_actionblend_advice));
            e.put(DetectionFailedType.TIMEOUT, context.getString(R.string.liveness_timeout_advice));
            e.put(DetectionFailedType.ACTIONTOOFAST, context.getString(R.string.liveness_actiontoofast_advice));
            e.put(DetectionFailedType.FACE_MOVE_OUT, context.getString(R.string.liveness_face_move_out_advice));
            e.put(DetectionFailedType.WRONG_ACTION, context.getString(R.string.liveness_wrong_action_advice));
        }
        if (f == null) {
            f = new HashMap();
            f.put(LivenessDetectionActivity.FinishType.PREPARE_FAIL, context.getString(R.string.liveness_prepare_fail_summary));
        }
    }

    public void a() {
        ArrayList arrayList = new ArrayList(c.keySet());
        Collections.shuffle(arrayList);
        this.b = arrayList.subList(0, this.f11056a);
    }

    public String a(DetectionType detectionType) {
        return c.get(detectionType);
    }

    public String b(DetectionType detectionType) {
        return d.get(detectionType);
    }

    public String a(DetectionFailedType detectionFailedType) {
        return e.get(detectionFailedType);
    }

    public String a(LivenessDetectionActivity.FinishType finishType) {
        return f.get(finishType);
    }
}
