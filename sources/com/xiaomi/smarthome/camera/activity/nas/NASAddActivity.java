package com.xiaomi.smarthome.camera.activity.nas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.app.AppCode;
import com.mijia.app.AppConstant;
import com.mijia.camera.nas.NASInfo;
import com.mijia.camera.nas.NASNode;
import com.mijia.camera.nas.NASServer;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.Iterator;
import java.util.List;

public class NASAddActivity extends CameraBaseActivity {
    private static final String TAG = "NASAddActivity";
    private EditText mNASName;
    private EditText mNASPassword;
    /* access modifiers changed from: private */
    public NASServer mNASServer;
    private EditText mNASUserName;
    /* access modifiers changed from: private */
    public int mPollCnt = 3;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    static /* synthetic */ int access$510(NASAddActivity nASAddActivity) {
        int i = nASAddActivity.mPollCnt;
        nASAddActivity.mPollCnt = i - 1;
        return i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_activity_device_smb_add);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mNASServer = (NASServer) intent.getParcelableExtra("data");
        if (this.mNASServer == null) {
            finish();
            return;
        }
        initViews();
        initProgressDialog();
    }

    private void initViews() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.smb_add_title);
        findViewById(R.id.title_bar_more).setVisibility(8);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASAddActivity.this.finish();
            }
        });
        this.mNASName = (EditText) findViewById(R.id.smb_name_et);
        this.mNASName.setText(this.mNASServer.a());
        this.mNASUserName = (EditText) findViewById(R.id.smb_username_et);
        this.mNASPassword = (EditText) findViewById(R.id.smb_password_et);
        findViewById(R.id.complete_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASAddActivity.this.onComplete();
            }
        });
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
        this.mNASServer.b(this.mNASServer.c());
        this.mNASServer.d(obj2);
        this.mNASServer.e(obj3);
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().a(new NASNode(this.mNASServer, ""), (Callback<List<NASNode>>) new Callback<List<NASNode>>() {
            public void onSuccess(List<NASNode> list) {
                if (!NASAddActivity.this.isFinishing()) {
                    boolean z = false;
                    if (list != null && !list.isEmpty()) {
                        Iterator<NASNode> it = list.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().b().equals(AppConstant.f)) {
                                    z = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    if (z) {
                        NASAddActivity.this.mNASServer.c(AppConstant.f);
                        NASInfo nASInfo = new NASInfo(NASAddActivity.this.mNASServer);
                        nASInfo.a(300);
                        nASInfo.b(AppCode.o);
                        nASInfo.c(1);
                        NASAddActivity.this.mCameraDevice.l().a(nASInfo, (Callback<Object>) new Callback<Object>() {
                            public void onSuccess(Object obj) {
                                if (!NASAddActivity.this.isFinishing()) {
                                    NASAddActivity.this.startPolling(AppConstant.f);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!NASAddActivity.this.isFinishing()) {
                                    NASAddActivity.this.mXQProgressDialog.dismiss();
                                    ToastUtil.a((Context) NASAddActivity.this, (int) R.string.smb_tip_set_fail);
                                }
                            }
                        });
                        return;
                    }
                    NASAddActivity.this.mXQProgressDialog.dismiss();
                    Intent intent = new Intent();
                    intent.putExtra("data", new NASInfo(NASAddActivity.this.mNASServer));
                    intent.setClass(NASAddActivity.this, NASDirListActivity.class);
                    NASAddActivity.this.startActivity(intent);
                    NASAddActivity.this.finish();
                }
            }

            public void onFailure(int i, String str) {
                if (!NASAddActivity.this.isFinishing()) {
                    NASAddActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASAddActivity.this, (int) R.string.smb_tip_set_fail);
                }
            }
        });
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void startPolling(final String str) {
        this.mXQProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                NASAddActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
            }
        });
        this.mPollCnt = 3;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (NASAddActivity.this.isValid()) {
                    NASAddActivity.access$510(NASAddActivity.this);
                    if (NASAddActivity.this.mPollCnt < 0) {
                        NASAddActivity.this.mXQProgressDialog.dismiss();
                        ToastUtil.a((Context) NASAddActivity.this, (int) R.string.smb_tip_set_fail);
                        return;
                    }
                    NASAddActivity.this.mCameraDevice.l().b(new Callback<NASInfo>() {
                        public void onSuccess(NASInfo nASInfo) {
                            if (nASInfo == null) {
                                NASAddActivity.this.mHandler.postDelayed(this, 2000);
                            } else if (TextUtils.equals(str, nASInfo.b())) {
                                NASAddActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        NASAddActivity.this.mXQProgressDialog.dismiss();
                                        ToastUtil.a((Context) NASAddActivity.this, (int) R.string.smb_tip_set_success);
                                        LocalBroadcastManager.getInstance(NASAddActivity.this).sendBroadcast(new Intent("go_smbinfo_clear_top_activity"));
                                        Intent intent = new Intent();
                                        intent.setClass(NASAddActivity.this, NASInfoActivity.class);
                                        NASAddActivity.this.startActivity(intent);
                                        NASAddActivity.this.finish();
                                    }
                                });
                            } else {
                                NASAddActivity.this.mHandler.postDelayed(this, 2000);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (NASAddActivity.this.isValid()) {
                                NASAddActivity.this.mHandler.postDelayed(this, 2000);
                            }
                        }
                    });
                }
            }
        }, 2000);
    }
}
