package com.xiaomi.smarthome.camera.activity.nas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.mijia.camera.nas.NASInfo;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;

public class NASSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public NASInfo mNASInfo;
    private SettingsItemView mSivChangeSetting;
    private SettingsItemView mSivChangeStorageDir;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_activity_device_nas_setting);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mNASInfo = (NASInfo) intent.getParcelableExtra("data");
        if (this.mNASInfo == null) {
            finish();
            return;
        }
        initProgressDialog();
        initViews();
    }

    /* access modifiers changed from: protected */
    public void initViews() {
        this.mSivChangeSetting = (SettingsItemView) findViewById(R.id.sivChangeSetting);
        this.mSivChangeStorageDir = (SettingsItemView) findViewById(R.id.sivChangeStorageDir);
        this.mSivChangeSetting.setOnClickListener(this);
        this.mSivChangeStorageDir.setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        refreshViews();
    }

    /* access modifiers changed from: protected */
    public void refreshViews() {
        if (this.mNASInfo != null && this.mNASInfo.a() != null) {
            this.mSivChangeSetting.setInfo(this.mNASInfo.a().a());
            this.mSivChangeStorageDir.setInfo(this.mNASInfo.a().e());
        }
    }

    public void onResume() {
        super.onResume();
        loadNASServerInfo();
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.title_bar_return) {
            switch (id) {
                case R.id.sivChangeSetting:
                    Intent intent = new Intent(this, NASEditActivity.class);
                    intent.putExtra("data", this.mNASInfo);
                    startActivity(intent);
                    return;
                case R.id.sivChangeStorageDir:
                    Intent intent2 = new Intent(this, NASDirListActivity.class);
                    intent2.putExtra("data", this.mNASInfo);
                    startActivity(intent2);
                    return;
                default:
                    return;
            }
        } else {
            onBackPressed();
        }
    }

    private void loadNASServerInfo() {
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().b(new Callback<NASInfo>() {
            public void onSuccess(NASInfo nASInfo) {
                if (!NASSettingActivity.this.isFinishing()) {
                    NASInfo unused = NASSettingActivity.this.mNASInfo = NASSettingActivity.this.mCameraDevice.l().a();
                    if (NASSettingActivity.this.mXQProgressDialog.isShowing()) {
                        NASSettingActivity.this.mXQProgressDialog.dismiss();
                    }
                    NASSettingActivity.this.refreshViews();
                    if (nASInfo.f() == 0) {
                        NASSettingActivity.this.startActivity(new Intent(NASSettingActivity.this, NASDiscoveryActivity.class));
                        NASSettingActivity.this.finish();
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!NASSettingActivity.this.isFinishing()) {
                    SDKLog.d(Tag.b, " loadNAS fail " + i + " : " + str);
                    if (NASSettingActivity.this.mXQProgressDialog.isShowing()) {
                        NASSettingActivity.this.mXQProgressDialog.dismiss();
                    }
                }
            }
        });
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }
}
