package com.xiaomi.smarthome.miio.camera.face.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.LocalLicenseUtil;
import com.xiaomi.smarthome.camera.view.widget.XmLoadingDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoWebActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfos;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class FaceManagerGuideActivity extends FaceManagerBaseActivity implements View.OnClickListener {
    public static final String KEY_EXTRA_FACE_TRY = "key_extra_try_face";
    public static final String KEY_EXTRA_IS_USING_FREE_FACE = "key_extra_is_using_free_service";
    public static final String KEY_EXTRA_face_buy_cloud_title = "KEY_EXTRA_face_buy_cloud_title";
    public static final String KEY_EXTRA_face_buy_cloud_url = "KEY_EXTRA_face_buy_cloud_url";
    public static final String KEY_EXTRA_face_from_camera = "KEY_EXTRA_face_from_camera";
    public static final String KEY_EXTRA_face_from_lowpower = "KEY_EXTRA_face_from_lowpower";
    public static final String KEY_EXTRA_face_is_vip_user = "KEY_EXTRA_face_is_vip_user";
    public static final String KEY_EXTRA_face_tips_info = "KEY_EXTRA_face_tips_info";
    public static final String TAG = "FaceManagerGuideActivity";
    /* access modifiers changed from: private */
    public boolean alreadyHasFaceInfo = false;
    /* access modifiers changed from: private */
    public String buy_cloud_title;
    /* access modifiers changed from: private */
    public String buy_cloud_url;
    private LinearLayout camera_privacy_container;
    private CheckBox cb_agreement;
    /* access modifiers changed from: private */
    public boolean from_lowpower = false;
    private boolean isFromCamera = false;
    /* access modifiers changed from: private */
    public boolean isUsingFreeFaceService;
    private boolean is_vip_user = false;
    private LinearLayout ll_need_buy_cloud;
    private TextView lowpower_privacy;
    private String[] permitArray = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private boolean privacy_agreement = true;
    private FrameLayout privacy_container;
    private XmLoadingDialog progressDialog;
    private boolean try_face_use;
    private LinearLayout try_vip_container;
    private TextView tv_face_tips_info;
    private TextView tv_privacy_agreement;
    private TextView tv_to_buy_cloud;
    /* access modifiers changed from: private */
    public TextView tv_to_use;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_face_manager_first);
        Bundle extras = getIntent().getExtras();
        String str = null;
        if (extras != null) {
            this.from_lowpower = extras.getBoolean(KEY_EXTRA_face_from_lowpower, false);
            this.is_vip_user = extras.getBoolean(KEY_EXTRA_face_is_vip_user, false);
            String string = extras.getString(KEY_EXTRA_face_tips_info, (String) null);
            this.buy_cloud_url = extras.getString(KEY_EXTRA_face_buy_cloud_url, (String) null);
            this.buy_cloud_title = extras.getString(KEY_EXTRA_face_buy_cloud_title, (String) null);
            this.try_face_use = extras.getBoolean(KEY_EXTRA_FACE_TRY, false);
            this.isFromCamera = extras.getBoolean(KEY_EXTRA_face_from_camera, false);
            this.isUsingFreeFaceService = extras.getBoolean(KEY_EXTRA_IS_USING_FREE_FACE, false);
            str = string;
        }
        initViews();
        initFaceDatas();
        if (!TextUtils.isEmpty(str)) {
            this.tv_face_tips_info.setText(str);
        }
    }

    private void initFaceDatas() {
        mFaceManager.getFigures(new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                FigureInfos figureInfos = (FigureInfos) obj2;
                if (figureInfos == null || figureInfos.figureInfos == null || figureInfos.figureInfos.size() <= 0) {
                    boolean unused = FaceManagerGuideActivity.this.alreadyHasFaceInfo = false;
                } else {
                    boolean unused2 = FaceManagerGuideActivity.this.alreadyHasFaceInfo = true;
                }
            }

            public void onFailure(int i, String str) {
                boolean unused = FaceManagerGuideActivity.this.alreadyHasFaceInfo = false;
            }
        });
    }

    private void initViews() {
        this.tv_privacy_agreement = (TextView) findViewById(R.id.tv_privacy_agreement);
        this.tv_face_tips_info = (TextView) findViewById(R.id.tv_face_tips_info);
        this.cb_agreement = (CheckBox) findViewById(R.id.cb_privacy_agreement);
        this.tv_to_use = (TextView) findViewById(R.id.tv_to_use);
        this.tv_to_use.setOnClickListener(this);
        this.tv_to_buy_cloud = (TextView) findViewById(R.id.tv_to_buy_cloud);
        this.ll_need_buy_cloud = (LinearLayout) findViewById(R.id.ll_need_buy_cloud);
        this.try_vip_container = (LinearLayout) findViewById(R.id.try_vip_container);
        this.privacy_container = (FrameLayout) findViewById(R.id.privacy_container);
        this.camera_privacy_container = (LinearLayout) findViewById(R.id.camera_privacy_container);
        this.lowpower_privacy = (TextView) findViewById(R.id.low_power_privacy_agreement);
        this.camera_privacy_container.setVisibility(8);
        this.lowpower_privacy.setVisibility(0);
        if (this.from_lowpower && this.try_face_use && !this.is_vip_user) {
            this.try_vip_container.setVisibility(0);
            this.try_vip_container.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FaceManagerGuideActivity.this.markUserFreeFaceService();
                }
            });
            this.tv_to_use.setVisibility(8);
            this.ll_need_buy_cloud.setVisibility(8);
        } else if ((this.isFromCamera || this.from_lowpower) && !this.is_vip_user && !this.isUsingFreeFaceService) {
            this.tv_to_use.setVisibility(4);
            this.privacy_container.setVisibility(4);
            this.try_vip_container.setVisibility(8);
            this.ll_need_buy_cloud.setVisibility(0);
            Intent intent = new Intent();
            intent.putExtra("showed_cloud_url", "1");
            setResult(-1, intent);
            this.tv_to_buy_cloud.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FaceManagerGuideActivity.this.from_lowpower) {
                        Intent intent = new Intent(FaceManagerGuideActivity.this, CloudVideoWebActivity.class);
                        intent.putExtra("title", FaceManagerGuideActivity.this.buy_cloud_title);
                        intent.putExtra("url", FaceManagerGuideActivity.this.buy_cloud_url);
                        intent.putExtra("did", FaceManagerBaseActivity.mFaceManager.getDeviceId());
                        FaceManagerGuideActivity.this.startActivity(intent);
                        FaceManagerGuideActivity.this.finish();
                        return;
                    }
                    CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(FaceManagerGuideActivity.this, FaceManagerBaseActivity.mFaceManager.getDeviceId());
                }
            });
        } else {
            this.tv_to_use.setVisibility(0);
            this.try_vip_container.setVisibility(8);
            this.ll_need_buy_cloud.setVisibility(8);
        }
        this.cb_agreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    FaceManagerGuideActivity.this.tv_to_use.setBackground(FaceManagerGuideActivity.this.getResources().getDrawable(R.drawable.bg_wide_button_press_shape));
                    FaceManagerGuideActivity.this.tv_to_use.setEnabled(false);
                    return;
                }
                FaceManagerGuideActivity.this.tv_to_use.setBackground(FaceManagerGuideActivity.this.getResources().getDrawable(R.drawable.bg_wide_button_normal_shape));
                FaceManagerGuideActivity.this.tv_to_use.setEnabled(true);
            }
        });
        this.cb_agreement.setChecked(true);
        AnonymousClass5 r0 = new ClickableSpan() {
            public void onClick(View view) {
                if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equalsIgnoreCase(FaceManagerBaseActivity.mFaceManager.getModel())) {
                    LocalLicenseUtil.jumpToV3UpgradePrivacyPage(FaceManagerGuideActivity.this);
                    return;
                }
                LocalLicenseUtil.jumpToV3PrivacyPage(FaceManagerGuideActivity.this, !"mijia.camera.v3".equals(FaceManagerBaseActivity.mFaceManager.getModel()));
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#32BAC0"));
            }
        };
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(getString(R.string.privacy_agreement));
        spannableStringBuilder.setSpan(r0, 0, getString(R.string.privacy_agreement).length(), 33);
        this.tv_privacy_agreement.setText(spannableStringBuilder);
        this.tv_privacy_agreement.setMovementMethod(LinkMovementMethod.getInstance());
        this.tv_privacy_agreement.setLineSpacing(0.0f, 1.5f);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void markUserFreeFaceService() {
        showLoadingDialog();
        mFaceManager.markUseFreeFaceService(this, new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                FaceManagerGuideActivity.this.hideLoadingDialog();
                boolean unused = FaceManagerGuideActivity.this.isUsingFreeFaceService = true;
                FaceManagerGuideActivity.this.gotoUseFaceService();
            }

            public void onFailure(int i, String str) {
                FaceManagerGuideActivity.this.hideLoadingDialog();
                ToastUtil.a((int) R.string.ble_combo_network_title_error);
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.title_bar_return) {
            finish();
        } else if (id != R.id.tv_privacy_agreement) {
            if (id == R.id.tv_to_use) {
                gotoUseFaceService();
            }
        } else if (this.privacy_agreement) {
            this.privacy_agreement = false;
            this.tv_privacy_agreement.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_unselected, 0, 0, 0);
            this.tv_to_use.setBackgroundColor(Color.parseColor("#6632BAC0"));
            this.tv_to_use.setEnabled(false);
        } else {
            this.privacy_agreement = true;
            this.tv_privacy_agreement.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_selected, 0, 0, 0);
            this.tv_to_use.setBackgroundColor(getResources().getColor(R.color.leave_msg_playing));
            this.tv_to_use.setEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void gotoUseFaceService() {
        startActivity(new Intent(this, this.alreadyHasFaceInfo ? FaceManagerActivity.class : FaceEmptyActivity.class));
        if (this.isUsingFreeFaceService) {
            FaceUtils.setNeedFaceGuideForUsingFreeFaceService(mFaceManager.getDeviceId() + mFaceManager.getDevice().deviceStat().userId, false);
            if (this.is_vip_user) {
                FaceUtils.setNeedFaceGuide(mFaceManager.getDeviceId() + mFaceManager.getDevice().deviceStat().userId, false);
            }
        } else {
            FaceUtils.setNeedFaceGuide(mFaceManager.getDeviceId() + mFaceManager.getDevice().deviceStat().userId, false);
        }
        finish();
    }

    public void showLoadingDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        this.progressDialog = XmLoadingDialog.show(this, getString(R.string.plugin_loading));
        this.progressDialog.show();
    }

    public void hideLoadingDialog() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }
}
