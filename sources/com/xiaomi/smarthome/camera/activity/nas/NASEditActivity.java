package com.xiaomi.smarthome.camera.activity.nas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.camera.nas.NASInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class NASEditActivity extends CameraBaseActivity {
    private NASInfo mNASInfo;
    private EditText mNASName;
    private EditText mNASPassword;
    private EditText mNASUserName;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_activity_device_smb_edit);
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
        initViews();
        initProgressDialog();
    }

    private void initViews() {
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(this.mNASInfo.a().a());
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASEditActivity.this.finish();
            }
        });
        this.mNASName = (EditText) findViewById(R.id.smb_name_et);
        this.mNASName.setText(this.mNASInfo.a().a());
        this.mNASUserName = (EditText) findViewById(R.id.smb_username_et);
        this.mNASUserName.setText(this.mNASInfo.a().g());
        this.mNASPassword = (EditText) findViewById(R.id.smb_password_et);
        this.mNASPassword.setText(this.mNASInfo.a().h());
        findViewById(R.id.complete_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASEditActivity.this.onComplete();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mXQProgressDialog != null && this.mXQProgressDialog.isShowing()) {
            this.mXQProgressDialog.dismiss();
        }
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void onComplete() {
        String obj = this.mNASName.getText().toString();
        String obj2 = this.mNASUserName.getText().toString();
        String obj3 = this.mNASPassword.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toast.makeText(this, R.string.smb_warning_name_empty, 0).show();
            return;
        }
        this.mNASInfo.a().e(obj3);
        this.mNASInfo.a().d(obj2);
        if (!(this.mNASInfo == null || this.mNASInfo.a() == null)) {
            this.mNASInfo.a().b(this.mNASInfo.a().c());
        }
        this.mNASInfo.a().a(obj);
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().a(this.mNASInfo, (Callback<Object>) new Callback<Object>() {
            public void onSuccess(Object obj) {
                if (!NASEditActivity.this.isFinishing()) {
                    NASEditActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASEditActivity.this, (int) R.string.smb_tip_set_success);
                    NASEditActivity.this.setResult(-1);
                    NASEditActivity.this.finish();
                }
            }

            public void onFailure(int i, String str) {
                if (!NASEditActivity.this.isFinishing()) {
                    NASEditActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASEditActivity.this, (int) R.string.smb_tip_set_fail);
                }
            }
        });
    }
}
