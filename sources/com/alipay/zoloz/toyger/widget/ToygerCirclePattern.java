package com.alipay.zoloz.toyger.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.biometrics.ui.widget.AlgorithmInfoPattern;
import com.alipay.biometrics.ui.widget.TitleBar;
import com.alipay.biometrics.ui.widget.WaveView;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.StringUtil;
import com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting;
import com.alipay.zoloz.hardware.camera.widget.CameraSurfaceView;
import com.alipay.zoloz.toyger.R;

public class ToygerCirclePattern extends RelativeLayout {
    private AlgorithmInfoPattern mAlgorithmInfoPattern;
    /* access modifiers changed from: private */
    public int mAnimateValue;
    private ImageView mBottomImage;
    private TextView mBottomLeftTip;
    private TextView mBottomRightTip;
    private TextView mBottomTextView;
    public CameraSurfaceView mCameraSurfaceView;
    public CircleUploadPattern mCircleUploadPattern;
    public ViewStub mCircleUploadPatternViewStub;
    private ImageView mGuassianBackground;
    private ImageView mImgBack;
    public boolean mIsShowProcess;
    public Handler mMainHandler;
    private RoundProgressBar mOuterBakRoundProgressBar;
    public View mParentView;
    /* access modifiers changed from: private */
    public RoundProgressBar mRoundProgressBar;
    private RoundProgressBar mRoundProgressBarInner;
    private TitleBar mTitleBar;
    private TextView mTopTipView;
    public ValueAnimator mValueAnimator;
    private WaveView mWaveView;

    public ToygerCirclePattern(Context context) {
        super(context);
        initViews();
    }

    public ToygerCirclePattern(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public ToygerCirclePattern(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    public void initViews() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.toyger_circle_pattern, this, true);
        this.mParentView = inflate;
        this.mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.toyger_circle_surfaceview);
        this.mBottomTextView = (TextView) findViewById(R.id.face_eye_circle_bottom_tip);
        this.mCircleUploadPatternViewStub = (ViewStub) inflate.findViewById(R.id.face_eye_upload_info_stub);
        this.mTitleBar = (TitleBar) inflate.findViewById(R.id.face_eye_circle_titlebar);
        this.mAlgorithmInfoPattern = (AlgorithmInfoPattern) inflate.findViewById(R.id.face_circle_algothm_info);
        this.mRoundProgressBarInner = (RoundProgressBar) findViewById(R.id.toyger_circle_round_inner);
        this.mTopTipView = (TextView) findViewById(R.id.face_eye_circle_top_tip);
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mRoundProgressBar = (RoundProgressBar) findViewById(R.id.toyger_circle_round_processbar);
        this.mOuterBakRoundProgressBar = (RoundProgressBar) inflate.findViewById(R.id.toyger_circle_round_outer_bak);
        this.mWaveView = (WaveView) inflate.findViewById(R.id.face_eye_circle_wave);
        this.mGuassianBackground = (ImageView) inflate.findViewById(R.id.face_eye_circle_guassian_background);
        this.mBottomImage = (ImageView) inflate.findViewById(R.id.face_eye_circle_bottom_image);
        this.mBottomLeftTip = (TextView) inflate.findViewById(R.id.face_eye_circle_bottom_left_protocol);
        this.mBottomLeftTip.setOnClickListener(new g(this));
        this.mBottomRightTip = (TextView) inflate.findViewById(R.id.face_eye_circle_bottom_right);
        this.mBottomRightTip.setOnClickListener(new h(this));
    }

    public void init(DeviceSetting[] deviceSettingArr) {
        this.mCameraSurfaceView.init(deviceSettingArr);
    }

    public CircleUploadPattern getCircleUploadPattern() {
        if (this.mCircleUploadPattern == null) {
            this.mCircleUploadPatternViewStub.inflate();
            this.mCircleUploadPattern = (CircleUploadPattern) this.mParentView.findViewById(R.id.toyger_circle_pattern_upload_info);
        }
        return this.mCircleUploadPattern;
    }

    public CameraSurfaceView getCameraSurfaceView() {
        return this.mCameraSurfaceView;
    }

    public void onPreviewChanged(double d, double d2) {
        if (this.mCameraSurfaceView == null) {
            return;
        }
        if (d < d2) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mCameraSurfaceView.getLayoutParams();
            BioLog.i("setPreviewChanged parent : W:" + layoutParams.width + " H:" + layoutParams.height);
            BioLog.i("setPreviewChanged preview: w:" + d + " h:" + d2);
            double d3 = (double) layoutParams.width;
            Double.isNaN(d3);
            int i = (int) ((d3 / (d * 1.0d)) * d2);
            layoutParams.height = i;
            this.mCameraSurfaceView.setLayoutParams(layoutParams);
            this.mCameraSurfaceView.setBackgroundColor(0);
            ((FrameLayout.LayoutParams) this.mGuassianBackground.getLayoutParams()).height = i;
            this.mGuassianBackground.setLayoutParams(layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mCameraSurfaceView.getLayoutParams();
        BioLog.i("setPreviewChanged parent : W:" + layoutParams2.width + " H:" + layoutParams2.height);
        BioLog.i("setPreviewChanged preview: w:" + d + " h:" + d2);
        double d4 = (double) layoutParams2.height;
        Double.isNaN(d4);
        int i2 = (int) ((d4 / (d2 * 1.0d)) * d);
        layoutParams2.width = i2;
        this.mCameraSurfaceView.setLayoutParams(layoutParams2);
        this.mCameraSurfaceView.setBackgroundColor(0);
        ((FrameLayout.LayoutParams) this.mGuassianBackground.getLayoutParams()).width = i2;
        this.mGuassianBackground.setLayoutParams(layoutParams2);
    }

    public void showProcessBar(float f) {
        showAnimator((int) (f * 3600.0f), 500, false);
    }

    public void showProcessBar(float f, int i, boolean z) {
        this.mIsShowProcess = !z;
        showAnimator((int) (f * 3600.0f), i, false);
    }

    public TitleBar getTitleBar() {
        return this.mTitleBar;
    }

    public ImageView getBackButton() {
        return this.mImgBack;
    }

    public TextView getTopTip() {
        return this.mTopTipView;
    }

    public void pause() {
        if (this.mCircleUploadPattern != null) {
            this.mCircleUploadPattern.stopProcess();
        }
    }

    public void setCameraVisible(boolean z) {
        if (z) {
            this.mCameraSurfaceView.setVisibility(0);
        } else {
            this.mCameraSurfaceView.setVisibility(8);
        }
    }

    private void showAnimator(int i, int i2, boolean z) {
        if (!this.mIsShowProcess) {
            this.mIsShowProcess = true;
            long j = (long) i2;
            this.mMainHandler.postDelayed(new i(this), j);
            int progress = this.mRoundProgressBar.getProgress();
            if (this.mValueAnimator != null) {
                this.mValueAnimator.cancel();
            }
            this.mValueAnimator = ValueAnimator.ofInt(new int[]{progress, i});
            this.mValueAnimator.setDuration(j);
            if (z) {
                this.mValueAnimator.setInterpolator(new LinearInterpolator());
            } else {
                this.mValueAnimator.setInterpolator(new AccelerateInterpolator());
            }
            this.mValueAnimator.addUpdateListener(new j(this, i));
            this.mValueAnimator.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    private String getString(String str, String str2) {
        return StringUtil.isNullorEmpty(str) ? str2 : str;
    }

    public WaveView getWaveView() {
        return this.mWaveView;
    }

    public RoundProgressBar getRoundProgressBarInner() {
        return this.mRoundProgressBarInner;
    }

    public RoundProgressBar getRoundProgressBar() {
        return this.mRoundProgressBar;
    }

    public RoundProgressBar getOuterBakRoundProgressBar() {
        return this.mOuterBakRoundProgressBar;
    }

    public ImageView getGuassianBackground() {
        return this.mGuassianBackground;
    }

    public ImageView getBottomImage() {
        return this.mBottomImage;
    }

    public TextView getBottomTextView() {
        return this.mBottomTextView;
    }

    public AlgorithmInfoPattern getAlgorithmInfoPattern() {
        return this.mAlgorithmInfoPattern;
    }

    public void destroy() {
        this.mCircleUploadPattern = null;
        this.mCircleUploadPatternViewStub = null;
        this.mMainHandler = null;
        this.mParentView = null;
    }
}
