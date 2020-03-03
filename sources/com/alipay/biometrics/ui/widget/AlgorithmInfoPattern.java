package com.alipay.biometrics.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.android.phone.mobilecommon.a.a.a;
import com.alipay.mobile.security.bio.utils.DeviceUtil;

public class AlgorithmInfoPattern extends RelativeLayout {
    private TextView mTxtCode = null;
    private TextView mTxtDistance = null;
    private TextView mTxtFaceLight = null;
    private TextView mTxtFacePitch = null;
    private TextView mTxtFaceQuality = null;
    private TextView mTxtFaceSize = null;
    private TextView mTxtFaceYaw = null;
    private TextView mTxtGaussian = null;
    private TextView mTxtHasFace = null;
    private TextView mTxtIntegrity = null;
    private TextView mTxtLeftEyeOcclusion = null;
    private TextView mTxtLiveScore = null;
    private TextView mTxtMotion = null;
    private TextView mTxtRectWidth = null;
    private TextView mTxtRightEyeOcclusion = null;
    private TextView mTxtVersionName = null;

    public AlgorithmInfoPattern(Context context) {
        super(context);
        initViews();
    }

    public AlgorithmInfoPattern(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public AlgorithmInfoPattern(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        View inflate = LayoutInflater.from(getContext()).inflate(a.d.bio_algorithm_info, this, true);
        this.mTxtHasFace = (TextView) inflate.findViewById(a.c.face_circle_has_face);
        this.mTxtFaceQuality = (TextView) inflate.findViewById(a.c.face_circle_face_quality);
        this.mTxtLiveScore = (TextView) inflate.findViewById(a.c.face_circle_face_live_score);
        this.mTxtFaceLight = (TextView) inflate.findViewById(a.c.face_circle_face_light);
        this.mTxtFaceSize = (TextView) inflate.findViewById(a.c.face_circle_face_size);
        this.mTxtFacePitch = (TextView) inflate.findViewById(a.c.face_circle_face_pitch);
        this.mTxtFaceYaw = (TextView) inflate.findViewById(a.c.face_circle_face_yaw);
        this.mTxtGaussian = (TextView) findViewById(a.c.face_circle_face_gaussian);
        this.mTxtMotion = (TextView) findViewById(a.c.face_circle_face_motion);
        this.mTxtIntegrity = (TextView) findViewById(a.c.face_circle_face_integrity);
        this.mTxtLeftEyeOcclusion = (TextView) findViewById(a.c.face_circle_face_left_eye_occlusion);
        this.mTxtRightEyeOcclusion = (TextView) findViewById(a.c.face_circle_face_right_eye_occlusion);
        this.mTxtDistance = (TextView) findViewById(a.c.face_circle_face_distance);
        this.mTxtRectWidth = (TextView) findViewById(a.c.face_circle_face_rectWidth);
        this.mTxtVersionName = (TextView) findViewById(a.c.smile_version_name);
        this.mTxtCode = (TextView) findViewById(a.c.smile_machine_code);
        this.mTxtVersionName.setText(DeviceUtil.getVersionName(getContext()));
        this.mTxtCode.setText(DeviceUtil.getUtdid(getContext()));
    }

    private void reset() {
        this.mTxtHasFace.setText("false");
        this.mTxtFaceQuality.setText("0");
        this.mTxtLiveScore.setText("0");
        this.mTxtFaceLight.setText("0");
        this.mTxtFaceSize.setText("[0,0,0,0]");
        this.mTxtFacePitch.setText("0");
        this.mTxtFaceYaw.setText("0");
        this.mTxtGaussian.setText("0");
        this.mTxtMotion.setText("0");
        this.mTxtIntegrity.setText("0");
        this.mTxtLeftEyeOcclusion.setText("0");
        this.mTxtRightEyeOcclusion.setText("0");
        this.mTxtDistance.setText("0");
        this.mTxtRectWidth.setText("0");
    }

    public void showInfo(AlgorithmInfo algorithmInfo) {
        if (algorithmInfo == null) {
            reset();
            return;
        }
        TextView textView = this.mTxtHasFace;
        textView.setText("" + algorithmInfo.isHasFace());
        if (algorithmInfo.isHasFace()) {
            TextView textView2 = this.mTxtFaceQuality;
            textView2.setText("" + algorithmInfo.getQuality());
            TextView textView3 = this.mTxtFaceLight;
            textView3.setText("" + algorithmInfo.getBrightness());
            if (algorithmInfo.getFaceRegion() != null) {
                float f = algorithmInfo.getFaceRegion().left;
                float f2 = algorithmInfo.getFaceRegion().top;
                TextView textView4 = this.mTxtFaceSize;
                textView4.setText("x:" + f + " y:" + f2 + " width:" + (algorithmInfo.getFaceRegion().right - f) + " height:" + (algorithmInfo.getFaceRegion().bottom - f2));
            }
            TextView textView5 = this.mTxtFacePitch;
            textView5.setText("" + algorithmInfo.getPitch());
            TextView textView6 = this.mTxtFaceYaw;
            textView6.setText("" + algorithmInfo.getYaw());
            TextView textView7 = this.mTxtGaussian;
            textView7.setText("" + algorithmInfo.getGaussian());
            TextView textView8 = this.mTxtMotion;
            textView8.setText("" + algorithmInfo.getMotion());
            TextView textView9 = this.mTxtIntegrity;
            textView9.setText("" + algorithmInfo.getIntegrity());
            TextView textView10 = this.mTxtLeftEyeOcclusion;
            textView10.setText("" + algorithmInfo.getLeftEyeBlinkRatio());
            TextView textView11 = this.mTxtRightEyeOcclusion;
            textView11.setText("" + algorithmInfo.getRightEyeBlinkRatio());
            TextView textView12 = this.mTxtDistance;
            textView12.setText("" + algorithmInfo.getDistance());
            this.mTxtLiveScore.setText("-1");
            return;
        }
        reset();
    }
}
