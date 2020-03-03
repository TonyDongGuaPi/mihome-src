package com.xiaomi.smarthome.camera.activity.alarm;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.AlarmSelectView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;

public class AlarmSelectActivity extends CameraBaseActivity implements View.OnClickListener, AlarmSelectView.IAlarmChangeListener {
    AlarmSelectView mAlarmSelectView;
    Bitmap mBitmap;
    Button mCancelBtn;
    XQProgressDialog mProgressDialog;
    Button mSelectBtn;
    View mSelectView;
    MLAlertDialog mlAlertDialog;

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_setting_alarm_select);
        initView();
        String b = FileUtil.b(this.mCameraDevice.getDid());
        if (new File(b).exists()) {
            this.mBitmap = BitmapFactory.decodeFile(b);
        }
        if (this.mBitmap != null) {
            this.mAlarmSelectView.setBackgroundDrawable(new BitmapDrawable(this.mBitmap));
        }
        loadData();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mBitmap != null && !this.mBitmap.isRecycled()) {
            try {
                this.mBitmap.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {
        ((TextView) findViewById(R.id.select_all_title)).setText(R.string.setting_alarm_area);
        this.mSelectView = findViewById(R.id.select_all_title_bar);
        this.mSelectBtn = (Button) findViewById(R.id.btn_select_all);
        this.mCancelBtn = (Button) findViewById(R.id.select_all_cancel);
        this.mSelectBtn.setVisibility(8);
        this.mSelectBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        this.mProgressDialog = new XQProgressDialog(this);
        this.mAlarmSelectView = (AlarmSelectView) findViewById(R.id.alarm_select_view);
        this.mAlarmSelectView.setChangeListener(this);
        this.mProgressDialog.setMessage(getString(R.string.loading_data));
        this.mProgressDialog.show();
    }

    private void loadData() {
        this.mCameraDevice.i().b((Callback<int[][]>) new Callback<int[][]>() {
            public void onSuccess(int[][] iArr) {
                if (!AlarmSelectActivity.this.isFinishing()) {
                    if (AlarmSelectActivity.this.mProgressDialog.isShowing()) {
                        AlarmSelectActivity.this.mProgressDialog.dismiss();
                    }
                    AlarmSelectActivity.this.mAlarmSelectView.setSelectStatus(iArr);
                }
            }

            public void onFailure(int i, String str) {
                if (!AlarmSelectActivity.this.isFinishing()) {
                    if (AlarmSelectActivity.this.mProgressDialog.isShowing()) {
                        AlarmSelectActivity.this.mProgressDialog.dismiss();
                    }
                    ToastUtil.a((Context) AlarmSelectActivity.this, (CharSequence) AlarmSelectActivity.this.getString(R.string.tip_no_info));
                }
            }
        }, true);
    }

    private void showSelect() {
        if (this.mSelectBtn.getVisibility() != 0) {
            this.mSelectBtn.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void hideSelect() {
        if (this.mSelectBtn.getVisibility() != 8) {
            this.mSelectBtn.setVisibility(8);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_select_all) {
            if (id == R.id.select_all_cancel) {
                if (this.mSelectBtn.getVisibility() != 0 || !this.mSelectBtn.isEnabled()) {
                    finish();
                    return;
                }
                initAlertDlg();
                if (!this.mlAlertDialog.isShowing()) {
                    this.mlAlertDialog.show();
                }
            }
        } else if (!this.mAlarmSelectView.isChange()) {
            ToastUtil.a((Context) this, (int) R.string.alarm_select_need_chose);
        } else {
            this.mProgressDialog.setMessage(getString(R.string.alarm_select_set_ing));
            if (this.mAlarmSelectView.isCloseAll()) {
                ToastUtil.a((Context) this, (int) R.string.setting_alarm_none);
                this.mAlarmSelectView.reset();
                return;
            }
            this.mProgressDialog.show();
            this.mCameraDevice.i().a((Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    if (!AlarmSelectActivity.this.isFinishing()) {
                        if (AlarmSelectActivity.this.mProgressDialog.isShowing()) {
                            AlarmSelectActivity.this.mProgressDialog.dismiss();
                        }
                        ToastUtil.a((Context) AlarmSelectActivity.this, (int) R.string.settings_set_success);
                        AlarmSelectActivity.this.mAlarmSelectView.invalidate();
                        AlarmSelectActivity.this.hideSelect();
                        AlarmSelectActivity.this.finish();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmSelectActivity.this.isFinishing()) {
                        if (AlarmSelectActivity.this.mProgressDialog.isShowing()) {
                            AlarmSelectActivity.this.mProgressDialog.dismiss();
                        }
                        ToastUtil.a((Context) AlarmSelectActivity.this, (int) R.string.smb_tip_set_fail);
                    }
                }
            }, this.mAlarmSelectView.getCurrentItems());
        }
    }

    public void onBackPressed() {
        if (this.mSelectBtn.getVisibility() != 0 || !this.mSelectBtn.isEnabled()) {
            super.onBackPressed();
            return;
        }
        initAlertDlg();
        if (!this.mlAlertDialog.isShowing()) {
            this.mlAlertDialog.show();
        }
    }

    public void onChange() {
        if (this.mAlarmSelectView.isChange()) {
            this.mSelectBtn.setEnabled(true);
            showSelect();
            return;
        }
        this.mSelectBtn.setEnabled(false);
    }

    private void initAlertDlg() {
        if (this.mlAlertDialog == null) {
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            builder.a((int) R.string.common_give_up);
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
            builder.b((int) R.string.alarm_select_give_up);
            builder.a((int) R.string.common_give_up, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AlarmSelectActivity.this.finish();
                }
            });
            this.mlAlertDialog = builder.b();
        }
    }
}
