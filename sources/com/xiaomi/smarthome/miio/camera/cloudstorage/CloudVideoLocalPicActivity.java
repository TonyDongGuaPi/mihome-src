package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.fastvideo.VideoViewGl;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class CloudVideoLocalPicActivity extends CloudVideoBaseActivity {
    private String filePath;
    private ImageView ivHeaderLeftBack;
    private ImageView ivHeaderRightSetting;
    private RelativeLayout rlTitleBar;
    private TextView tvTitleBarTitle;
    private VideoViewGl vvPic;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_local_pic);
        initViews();
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        TitleBarUtil.b((Activity) this);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setVisibility(8);
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setImageResource(R.drawable.std_tittlebar_main_device_back_white);
        this.ivHeaderLeftBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CloudVideoLocalPicActivity.this.onBackPressed();
            }
        });
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        this.tvTitleBarTitle.setVisibility(0);
        this.tvTitleBarTitle.setTextColor(-1);
        this.vvPic = (VideoViewGl) findViewById(R.id.vvPic);
        if (this.vvPic.getSurfaceView() != null) {
            this.vvPic.getSurfaceView().setMiniScale(true);
        }
        this.filePath = getIntent().getStringExtra(TbsReaderView.KEY_FILE_PATH);
        if (!TextUtils.isEmpty(this.filePath)) {
            String fileName = getFileName(this.filePath);
            if (!TextUtils.isEmpty(fileName)) {
                this.tvTitleBarTitle.setText(fileName);
            }
            this.vvPic.setFirstBitmap(BitmapFactory.decodeFile(this.filePath));
        }
    }

    private String getFileName(String str) {
        int lastIndexOf;
        try {
            if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf("/")) < 0 || lastIndexOf >= str.length()) {
                return null;
            }
            return str.substring(str.lastIndexOf("/") + 1);
        } catch (Exception unused) {
            return null;
        }
    }
}
