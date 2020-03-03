package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.File;

public class RoteSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    Bitmap mBitMap = null;
    private boolean mIsRote = false;
    ImageView mRoteView;
    Button mSelectView;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_rote_setting);
        TitleBarUtil.a(findViewById(R.id.select_all_title_bar));
        ((TextView) findViewById(R.id.select_all_title)).setText(R.string.rote_title);
        initView();
    }

    private void initView() {
        this.mRoteView = (ImageView) findViewById(R.id.rote_bg);
        findViewById(R.id.rote_select).setOnClickListener(this);
        findViewById(R.id.select_all_cancel).setOnClickListener(this);
        this.mSelectView = (Button) findViewById(R.id.btn_select_all);
        this.mSelectView.setOnClickListener(this);
        this.mSelectView.setVisibility(0);
        String b = FileUtil.b(this.mCameraDevice.getDid());
        if (b != null) {
            if (new File(b).exists()) {
                this.mBitMap = BitmapFactory.decodeFile(b);
            }
            if (this.mBitMap == null) {
                this.mBitMap = BitmapFactory.decodeResource(getResources(), R.drawable.camera_default_rote);
            }
            this.mRoteView.setImageBitmap(this.mBitMap);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_select_all) {
            if (id != R.id.rote_select) {
                if (id == R.id.select_all_cancel) {
                    onBackPressed();
                }
            } else if (this.mIsRote) {
                this.mRoteView.setRotation(0.0f);
                this.mIsRote = false;
            } else {
                this.mRoteView.setRotation(180.0f);
                this.mIsRote = true;
            }
        } else if (this.mIsRote) {
            save();
        } else {
            ToastUtil.a((Context) this, (int) R.string.smb_tip_set_success);
            finish();
        }
    }

    private void save() {
        this.mCameraDevice.f().a(new String[]{CameraPropertyBase.i}, (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                RoteSettingActivity.this.mCameraDevice.f().a(CameraPropertyBase.i, !RoteSettingActivity.this.mCameraDevice.f().b(CameraPropertyBase.i), (Callback<Void>) new Callback<Void>() {
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x0085  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onSuccess(java.lang.Void r9) {
                        /*
                            r8 = this;
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this
                            boolean r9 = r9.isFinishing()
                            if (r9 == 0) goto L_0x000b
                            return
                        L_0x000b:
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this
                            r0 = 2131499269(0x7f0c1905, float:1.8622183E38)
                            com.xiaomi.smarthome.library.common.util.ToastUtil.a((android.content.Context) r9, (int) r0)
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this
                            android.graphics.Bitmap r9 = r9.mBitMap
                            if (r9 == 0) goto L_0x0088
                            r9 = 0
                            android.graphics.Matrix r5 = new android.graphics.Matrix     // Catch:{ Exception -> 0x007c }
                            r5.<init>()     // Catch:{ Exception -> 0x007c }
                            r0 = 1127481344(0x43340000, float:180.0)
                            r5.setRotate(r0)     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r0 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r0 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007c }
                            android.graphics.Bitmap r0 = r0.mBitMap     // Catch:{ Exception -> 0x007c }
                            r1 = 0
                            r2 = 0
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r3 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r3 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007c }
                            android.graphics.Bitmap r3 = r3.mBitMap     // Catch:{ Exception -> 0x007c }
                            int r3 = r3.getWidth()     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r4 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r4 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007c }
                            android.graphics.Bitmap r4 = r4.mBitMap     // Catch:{ Exception -> 0x007c }
                            int r4 = r4.getHeight()     // Catch:{ Exception -> 0x007c }
                            r6 = 1
                            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x007c }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r1 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007a }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r1 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007a }
                            android.graphics.Bitmap r1 = r1.mBitMap     // Catch:{ Exception -> 0x007a }
                            r1.recycle()     // Catch:{ Exception -> 0x007a }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r1 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007a }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r1 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007a }
                            r1.mBitMap = r9     // Catch:{ Exception -> 0x007a }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this     // Catch:{ Exception -> 0x007a }
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this     // Catch:{ Exception -> 0x007a }
                            com.mijia.camera.MijiaCameraDevice r9 = r9.mCameraDevice     // Catch:{ Exception -> 0x007a }
                            java.lang.String r9 = r9.getDid()     // Catch:{ Exception -> 0x007a }
                            java.lang.String r9 = com.Utils.FileUtil.b(r9)     // Catch:{ Exception -> 0x007a }
                            if (r9 == 0) goto L_0x0083
                            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x007a }
                            r1.<init>(r9)     // Catch:{ Exception -> 0x007a }
                            android.graphics.Bitmap$CompressFormat r9 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x007a }
                            r2 = 100
                            r0.compress(r9, r2, r1)     // Catch:{ Exception -> 0x007a }
                            r1.close()     // Catch:{ Exception -> 0x007a }
                            goto L_0x0083
                        L_0x007a:
                            r9 = move-exception
                            goto L_0x0080
                        L_0x007c:
                            r0 = move-exception
                            r7 = r0
                            r0 = r9
                            r9 = r7
                        L_0x0080:
                            r9.printStackTrace()
                        L_0x0083:
                            if (r0 == 0) goto L_0x0088
                            r0.recycle()
                        L_0x0088:
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity$1 r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.this
                            com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity r9 = com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.this
                            r9.finish()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.setting.RoteSettingActivity.AnonymousClass1.AnonymousClass1.onSuccess(java.lang.Void):void");
                    }

                    public void onFailure(int i, String str) {
                        if (!RoteSettingActivity.this.isFinishing()) {
                            ToastUtil.a((Context) RoteSettingActivity.this, (int) R.string.smb_tip_set_fail);
                        }
                    }
                });
            }

            public void onFailure(int i, String str) {
                if (!RoteSettingActivity.this.isFinishing()) {
                    ToastUtil.a((Context) RoteSettingActivity.this, (int) R.string.smb_tip_set_fail);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mBitMap != null && !this.mBitMap.isRecycled()) {
            this.mBitMap.recycle();
        }
    }
}
