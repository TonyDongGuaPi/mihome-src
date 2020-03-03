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
import com.mijia.debug.SDKLog;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.AlarmSelectView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmSelectV2Activity extends CameraBaseActivity implements View.OnClickListener, AlarmSelectView.IAlarmChangeListener {
    private static final String TAG = "AlarmSelectV2Activity";
    AlarmSelectView mAlarmSelectView;
    Bitmap mBitmap;
    Button mCancelBtn;
    XQProgressDialog mProgressDialog;
    Button mSelectBtn;
    View mSelectView;
    MLAlertDialog mlAlertDialog;
    int[] sensitive;

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_setting_alarm_select_v2);
        initView();
        String b = FileUtil.b(this.mCameraDevice.getDid());
        if (new File(b).exists()) {
            this.mBitmap = BitmapFactory.decodeFile(b);
        }
        if (this.mBitmap != null) {
            this.mAlarmSelectView.setBackgroundDrawable(new BitmapDrawable(this.mBitmap));
        }
        this.sensitive = getIntent().getIntArrayExtra("sensitive");
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
        TitleBarUtil.a(findViewById(R.id.title_bar_container));
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
    }

    private void loadData() {
        try {
            if (this.sensitive != null && this.sensitive.length > 0) {
                this.mAlarmSelectView.setSelectStatus(dimension1ToDimension2(this.sensitive));
            }
        } catch (Exception unused) {
        }
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
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("did", this.mCameraDevice.getDid());
                hashMap.put("region", Locale.getDefault().getCountry());
                JSONArray jSONArray = new JSONArray();
                for (int put : dimension2ToDimension1(this.mAlarmSelectView.getCurrentItems())) {
                    jSONArray.put(put);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sensitive", jSONArray);
                hashMap.put("sensitive", jSONObject.toString());
                this.mProgressDialog.show();
                AlarmNetUtils.a().g(this.mCameraDevice.getModel(), hashMap.toString(), new Callback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject) {
                        if (!AlarmSelectV2Activity.this.isFinishing()) {
                            AlarmSelectV2Activity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (AlarmSelectV2Activity.this.mProgressDialog.isShowing()) {
                                        AlarmSelectV2Activity.this.mProgressDialog.dismiss();
                                    }
                                    ToastUtil.a((Context) AlarmSelectV2Activity.this, (int) R.string.settings_set_success);
                                    AlarmSelectV2Activity.this.mAlarmSelectView.invalidate();
                                    AlarmSelectV2Activity.this.hideSelect();
                                    AlarmSelectV2Activity.this.finish();
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!AlarmSelectV2Activity.this.isFinishing()) {
                            SDKLog.d(AlarmSelectV2Activity.TAG, "i:" + i + " s:" + str);
                            AlarmSelectV2Activity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    if (AlarmSelectV2Activity.this.mProgressDialog.isShowing()) {
                                        AlarmSelectV2Activity.this.mProgressDialog.dismiss();
                                    }
                                    ToastUtil.a((Context) AlarmSelectV2Activity.this, (int) R.string.smb_tip_set_fail);
                                }
                            });
                        }
                    }
                });
            } catch (JSONException unused) {
            }
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
                    AlarmSelectV2Activity.this.finish();
                }
            });
            this.mlAlertDialog = builder.b();
        }
    }

    private int[][] dimension1ToDimension2(int[] iArr) {
        int[][] iArr2 = (int[][]) Array.newInstance(int.class, new int[]{4, 8});
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < 8) {
                int i5 = i3 + 1;
                iArr2[i][i4] = iArr[i3];
                i4++;
                i3 = i5;
            }
            i++;
            i2 = i3;
        }
        return iArr2;
    }

    private int[] dimension2ToDimension1(int[][] iArr) {
        int[] iArr2 = new int[32];
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < 8) {
                iArr2[i3] = iArr[i][i4];
                i4++;
                i3++;
            }
            i++;
            i2 = i3;
        }
        return iArr2;
    }
}
