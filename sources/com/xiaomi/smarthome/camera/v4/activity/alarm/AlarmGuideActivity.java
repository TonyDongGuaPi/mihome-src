package com.xiaomi.smarthome.camera.v4.activity.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class AlarmGuideActivity extends CameraBaseActivity implements View.OnClickListener {
    private TextView mNextTxt;
    private TextView mTitleNameTxt;
    private boolean useNewAlarmVideo;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alarm_guide);
        initView();
        this.useNewAlarmVideo = getIntent().getBooleanExtra("useNewAlarmVideo", false);
    }

    private void initView() {
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mTitleNameTxt = (TextView) findViewById(R.id.title_bar_title);
        this.mTitleNameTxt.setText(getResources().getString(R.string.housekeeping_v4));
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mNextTxt = (TextView) findViewById(R.id.text_three_menu_next);
        this.mNextTxt.setOnClickListener(this);
        this.mNextTxt.setVisibility(0);
        initHeadImage();
    }

    private void initHeadImage() {
        ImageView imageView = (ImageView) findViewById(R.id.img_housekeeping_guide_head_img);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        imageView.getLayoutParams().width = width;
        imageView.getLayoutParams().height = (int) (((float) (width * 493)) / 720.0f);
        imageView.postInvalidate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.text_three_menu_next) {
            this.mCameraDevice.e().h(false);
            if (!this.mCameraDevice.f().c()) {
                ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.camera_dromanting));
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("jump", true);
            if (!AlarmNetUtils.b() || !AlarmNetUtils.e()) {
                intent.setClass(this, AlarmSettingActivity.class);
                startActivity(intent);
            } else {
                if (this.useNewAlarmVideo) {
                    intent.setClass(this, AlarmSettingV2Activity.class);
                } else {
                    intent.setClass(this, AlarmSettingV2Activity.class);
                }
                startActivity(intent);
            }
            finish();
        } else if (id == R.id.title_bar_return) {
            finish();
        }
    }
}
