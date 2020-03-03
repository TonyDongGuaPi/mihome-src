package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.share.internal.ShareConstants;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SimpleCheckedAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.ParamKey;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.model.UserInfoPermission;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.PermissionImageView;
import com.mi.global.bbs.view.dialog.SelectDateDialog;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.util.ResourceUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaomi.smarthome.library.common.widget.crop.CropImageActivity;
import com.xiaomi.youpin.share.ShareObject;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class EditUserInfoActivity extends BaseActivity {
    private static final int REQUEST_CROP = 47;
    private static final int REQUEST_IMAGE = 17;
    private static final int SAVE_ID = 37;
    private static final String USER_PROFILE_NAME = "user_icon";
    @BindView(2131492907)
    TextView activityEditBirthday;
    @BindView(2131492908)
    PermissionImageView activityEditBirthdayPermission;
    @BindView(2131492909)
    TextView activityEditEducation;
    @BindView(2131492910)
    PermissionImageView activityEditEducationPermission;
    @BindView(2131492911)
    FontEditText activityEditEmail;
    @BindView(2131492912)
    CheckedTextView activityEditFemale;
    @BindView(2131492913)
    PermissionImageView activityEditGenderPermission;
    @BindView(2131492914)
    ImageView activityEditIcon;
    @BindView(2131492916)
    FontEditText activityEditInterest;
    @BindView(2131492917)
    PermissionImageView activityEditInterestPermission;
    @BindView(2131492918)
    FontEditText activityEditJob;
    @BindView(2131492919)
    PermissionImageView activityEditJobPermission;
    @BindView(2131492920)
    FontEditText activityEditLivingCity;
    @BindView(2131492921)
    PermissionImageView activityEditLivingCityPermission;
    @BindView(2131492922)
    CheckedTextView activityEditMale;
    @BindView(2131492923)
    FontEditText activityEditNickName;
    @BindView(2131492924)
    TextView activityEditNickNameEditable;
    @BindView(2131492925)
    FontEditText activityEditRealName;
    @BindView(2131492926)
    PermissionImageView activityEditRealNamePermission;
    /* access modifiers changed from: private */
    public UserInfoModel.DataBean editUserInfo;
    /* access modifiers changed from: private */
    public String[] educationArray;
    private Uri imageUri;
    private UserInfoPermission infoPermission;
    private UserInfoModel.DataBean userInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.infoPermission = createInitModel();
        setTitleAndBack(R.string.edit_profile, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_edit_user_info);
        ButterKnife.bind((Activity) this);
        this.editUserInfo = new UserInfoModel.DataBean();
        this.educationArray = getResources().getStringArray(R.array.education);
        this.userInfo = (UserInfoModel.DataBean) getIntent().getParcelableExtra("user");
        bindData();
        getPermissionData();
        addSaveMenu();
        String imageCacheFileName = FileUtils.getImageCacheFileName(USER_PROFILE_NAME);
        if (!TextUtils.isEmpty(imageCacheFileName)) {
            this.imageUri = Uri.fromFile(new File(imageCacheFileName));
        }
    }

    private void addSaveMenu() {
        FontTextView fontTextView = new FontTextView(this);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.settings_margin_lr);
        fontTextView.setPadding(dimensionPixelOffset, 0, dimensionPixelOffset, 0);
        fontTextView.setGravity(17);
        fontTextView.setTextColor(getResources().getColor(R.color.activity_black_color));
        fontTextView.setTextSize(1, 16.0f);
        fontTextView.setText(getString(R.string.save));
        fontTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImeUtils.hideIme(EditUserInfoActivity.this.activityEditRealName);
                EditUserInfoActivity.this.saveUserInfo();
            }
        });
        this.menuLayout.addView(fontTextView);
    }

    private void getPermissionData() {
        ApiClient.getPermission(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                EditUserInfoActivity.this.handlePermissionData(jsonObject.toString());
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    private void bindData() {
        try {
            this.userInfo = (UserInfoModel.DataBean) getIntent().getParcelableExtra("user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.userInfo != null) {
            ImageLoader.showHeadIcon(this.activityEditIcon, this.userInfo.icon);
            this.activityEditNickName.setText(this.userInfo.username);
            if (!TextUtils.isEmpty(this.userInfo.username)) {
                boolean z = TextHelper.isNumeric(this.userInfo.username) && this.userInfo.uid.equals(this.userInfo.username);
                this.activityEditNickName.setEnabled(z);
                this.activityEditNickNameEditable.setVisibility(z ? 4 : 0);
            }
            this.activityEditRealName.setText(this.userInfo.realname);
            this.activityEditRealName.setSelection(this.activityEditRealName.getText().length());
            this.activityEditEmail.setText(this.userInfo.email);
            handleGenderData();
            String str = this.userInfo.education;
            if (!TextUtils.isEmpty(str)) {
                this.activityEditEducation.setText(str);
            } else {
                this.activityEditEducation.setText(R.string.select);
            }
            if (!TextUtils.isEmpty(this.userInfo.birthmonth) && !this.userInfo.birthmonth.equals("0")) {
                this.activityEditBirthday.setText(getString(R.string.birthday_, new Object[]{this.userInfo.birthmonth, this.userInfo.birthday}));
            }
            this.activityEditLivingCity.setText(this.userInfo.livingcity);
            this.activityEditJob.setText(this.userInfo.occupation);
            this.activityEditInterest.setText(this.userInfo.interest);
        }
    }

    private void handleGenderData() {
        String str = this.userInfo.gender;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("1")) {
                this.activityEditMale.setChecked(true);
                this.activityEditFemale.setChecked(false);
            } else if (str.equals("2")) {
                this.activityEditMale.setChecked(false);
                this.activityEditFemale.setChecked(true);
            }
        }
        this.activityEditMale.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                EditUserInfoActivity.this.editUserInfo.gender = "1";
                EditUserInfoActivity.this.activityEditFemale.setChecked(false);
            }
        });
        this.activityEditFemale.setOnCheckedChangeListener(new CheckedTextView.OnCheckedChangeListener() {
            public void onChecked(CheckedTextView checkedTextView, boolean z) {
                EditUserInfoActivity.this.editUserInfo.gender = "2";
                EditUserInfoActivity.this.activityEditMale.setChecked(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void saveUserInfo() {
        if (this.userInfo != null) {
            if (!checkIfHasChange()) {
                finish();
                return;
            }
            if (this.activityEditNickName.isEnabled()) {
                String obj = this.activityEditNickName.getText().toString();
                if (!TextUtils.isEmpty(obj) && !obj.equals(this.userInfo.realname)) {
                    this.editUserInfo.username = obj;
                }
                if (TextUtils.isEmpty(obj) || obj.length() < 2) {
                    toast(Integer.valueOf(R.string.nickname_to_short));
                    return;
                }
            }
            String obj2 = this.activityEditEmail.getText().toString();
            if (!obj2.equals(this.userInfo.email)) {
                this.editUserInfo.email = obj2;
                if (!TextHelper.isEmail(obj2)) {
                    toast(Integer.valueOf(R.string.invalid_email));
                    return;
                }
            }
            String obj3 = this.activityEditRealName.getText().toString();
            if (!obj3.equals(this.userInfo.realname)) {
                this.editUserInfo.realname = obj3;
            }
            String obj4 = this.activityEditLivingCity.getText().toString();
            if (!obj4.equals(this.userInfo.livingcity)) {
                this.editUserInfo.livingcity = obj4;
            }
            String obj5 = this.activityEditJob.getText().toString();
            if (!obj5.equals(this.userInfo.occupation)) {
                this.editUserInfo.occupation = obj5;
            }
            String obj6 = this.activityEditInterest.getText().toString();
            if (!obj6.equals(this.userInfo.interest)) {
                this.editUserInfo.interest = obj6;
            }
            String charSequence = this.activityEditEducation.getText().toString();
            if (!charSequence.equals(this.userInfo.education)) {
                this.editUserInfo.education = charSequence;
            }
            showProDialog(getString(R.string.bbs_loading));
            ApiClient.editUserProfile(this.editUserInfo, getPermissionModel(), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        DataManager.init().userInfoChange(true);
                        EditUserInfoActivity.this.finish();
                    } else {
                        EditUserInfoActivity.this.toast(baseResult.getErrmsg());
                    }
                    EditUserInfoActivity.this.dismissProDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    EditUserInfoActivity.this.dismissProDialog();
                }
            });
        }
    }

    @OnClick({2131492914, 2131492915, 2131492907, 2131492909})
    public void onClick(View view) {
        if (view.getId() == R.id.activity_edit_icon || view.getId() == R.id.activity_edit_icon_bt) {
            pickPicture();
        } else if (view.getId() == R.id.activity_edit_birthday) {
            showDataPicker();
        } else if (view.getId() == R.id.activity_edit_education) {
            showSelectDialog();
        }
    }

    private void showDataPicker() {
        int i;
        int i2;
        if (this.userInfo == null || TextUtils.isEmpty(this.userInfo.birthmonth) || this.userInfo.birthmonth.equals("0")) {
            Calendar instance = Calendar.getInstance();
            int i3 = instance.get(2);
            i = instance.get(5);
            i2 = i3;
        } else {
            i2 = Integer.parseInt(this.userInfo.birthmonth) - 1;
            i = Integer.parseInt(this.userInfo.birthday);
            if (i2 < 0) {
                i2 = 0;
            }
        }
        SelectDateDialog selectDateDialog = new SelectDateDialog(this, i2, i);
        selectDateDialog.setOnSelectCompletedListener(new SelectDateDialog.OnSelectCompletedListener() {
            public void onSelectCompleted(int i, int i2) {
                String valueOf = String.valueOf(i + 1);
                String valueOf2 = String.valueOf(i2 + 1);
                EditUserInfoActivity.this.editUserInfo.birthmonth = valueOf;
                EditUserInfoActivity.this.editUserInfo.birthday = valueOf2;
                EditUserInfoActivity.this.activityEditBirthday.setText(EditUserInfoActivity.this.getString(R.string.birthday_, new Object[]{valueOf, valueOf2}));
            }
        });
        selectDateDialog.show();
    }

    private void showSelectDialog() {
        SimpleCheckedAdapter simpleCheckedAdapter = new SimpleCheckedAdapter(this, this.educationArray, getEducationIndex(this.activityEditEducation.getText().toString()));
        final AlertDialog showListDialog = DialogFactory.showListDialog(this, simpleCheckedAdapter);
        simpleCheckedAdapter.setOnItemClickListener(new SimpleCheckedAdapter.OnItemClickListener() {
            public void onClick(int i) {
                String str = EditUserInfoActivity.this.educationArray[i];
                EditUserInfoActivity.this.editUserInfo.education = str;
                EditUserInfoActivity.this.activityEditEducation.setText(str);
                showListDialog.dismiss();
            }
        });
    }

    private int getEducationIndex(String str) {
        for (int i = 0; i < this.educationArray.length; i++) {
            if (str.equals(this.educationArray[i])) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public void handlePermissionData(String str) {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            if (optJSONObject != null) {
                traversePermission(optJSONObject.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_PRIVACY));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void traversePermission(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    if (next.equalsIgnoreCase(ParamKey.realname)) {
                        int i = jSONObject.getInt(next);
                        this.activityEditRealNamePermission.setPermissionState(i);
                        this.infoPermission.realname = i;
                    } else if (next.equalsIgnoreCase("gender")) {
                        int i2 = jSONObject.getInt(next);
                        this.activityEditGenderPermission.setPermissionState(i2);
                        this.infoPermission.gender = i2;
                    } else if (next.equalsIgnoreCase(ParamKey.livingcity)) {
                        int i3 = jSONObject.getInt(next);
                        this.activityEditLivingCityPermission.setPermissionState(i3);
                        this.infoPermission.livingcity = i3;
                    } else if (next.equalsIgnoreCase(ParamKey.occupation)) {
                        int i4 = jSONObject.getInt(next);
                        this.activityEditJobPermission.setPermissionState(i4);
                        this.infoPermission.occupation = i4;
                    } else if (next.equalsIgnoreCase(ParamKey.birthday)) {
                        int i5 = jSONObject.getInt(next);
                        this.activityEditBirthdayPermission.setPermissionState(i5);
                        this.infoPermission.birthday = i5;
                    } else if (next.equalsIgnoreCase(ParamKey.education)) {
                        int i6 = jSONObject.getInt(next);
                        this.activityEditEducationPermission.setPermissionState(i6);
                        this.infoPermission.education = i6;
                    } else if (next.equalsIgnoreCase(ParamKey.interest)) {
                        int i7 = jSONObject.getInt(next);
                        this.activityEditInterestPermission.setPermissionState(i7);
                        this.infoPermission.interest = i7;
                    }
                }
            }
        }
    }

    private UserInfoPermission getPermissionModel() {
        UserInfoPermission userInfoPermission = new UserInfoPermission();
        userInfoPermission.realname = this.activityEditRealNamePermission.getPermissionState();
        userInfoPermission.birthday = this.activityEditBirthdayPermission.getPermissionState();
        userInfoPermission.gender = this.activityEditGenderPermission.getPermissionState();
        userInfoPermission.livingcity = this.activityEditLivingCityPermission.getPermissionState();
        userInfoPermission.education = this.activityEditEducationPermission.getPermissionState();
        userInfoPermission.occupation = this.activityEditJobPermission.getPermissionState();
        userInfoPermission.interest = this.activityEditInterestPermission.getPermissionState();
        return userInfoPermission;
    }

    private UserInfoPermission createInitModel() {
        UserInfoPermission userInfoPermission = new UserInfoPermission();
        userInfoPermission.realname = -1;
        userInfoPermission.birthday = -1;
        userInfoPermission.gender = -1;
        userInfoPermission.livingcity = -1;
        userInfoPermission.education = -1;
        userInfoPermission.occupation = -1;
        userInfoPermission.interest = -1;
        return userInfoPermission;
    }

    private void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file, R.string.str_permission_use_camera), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) EditUserInfoActivity.this, 17);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 17 && i2 == -1 && (stringArrayListExtra = intent.getStringArrayListExtra("select_result")) != null && stringArrayListExtra.size() > 0 && this.imageUri != null) {
            startCropIntent(stringArrayListExtra.get(0));
        }
        if (i == 47 && i2 == -1 && this.imageUri != null) {
            uploadImage(this.imageUri.getPath());
        }
    }

    private void startCropIntent(String str) {
        Uri uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), new File(str));
            intent.addFlags(1);
        } else {
            uri = Uri.fromFile(new File(str));
        }
        intent.setDataAndType(uri, ShareObject.d);
        intent.putExtra("crop", "true");
        intent.putExtra(CropImageActivity.ASPECT_X, 1);
        intent.putExtra(CropImageActivity.ASPECT_Y, 1);
        intent.putExtra(CropImageActivity.OUTPUT_X, 120);
        intent.putExtra(CropImageActivity.OUTPUT_Y, 120);
        intent.putExtra("scale", true);
        intent.putExtra(AgentOptions.k, this.imageUri);
        intent.putExtra(CropImageActivity.RETURN_DATA, false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 47);
        } else {
            uploadImage(str);
        }
    }

    private void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                EditUserInfoActivity.this.handleUploadResult(uploadResultModel);
                EditUserInfoActivity.this.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                EditUserInfoActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(UploadResultModel uploadResultModel) {
        UploadResultModel.DataBean data;
        if (uploadResultModel != null && (data = uploadResultModel.getData()) != null && !TextUtils.isEmpty(data.getUrl())) {
            this.editUserInfo.icon = data.getUrl();
            ImageLoader.showHeadIcon(this.activityEditIcon, data.getUrl());
        }
    }

    public void onBackPressed() {
        ImeUtils.hideIme(this.activityEditRealName);
        if (checkIfHasChange()) {
            DialogFactory.showAlert((Context) this, R.string.discard_change, R.string.bbs_dialog_ask_ok, R.string.bbs_cancel, (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
                public void onOkClick(View view) {
                    EditUserInfoActivity.this.finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    public boolean checkIfHasChange() {
        if (this.userInfo == null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.editUserInfo.icon)) {
            return true;
        }
        if ((this.activityEditNickName.isEnabled() && !this.activityEditNickName.getText().toString().equals(this.userInfo.username)) || !this.activityEditEmail.getText().toString().equals(this.userInfo.email) || !this.activityEditRealName.getText().toString().equals(this.userInfo.realname) || !this.activityEditLivingCity.getText().toString().equals(this.userInfo.livingcity) || !this.activityEditJob.getText().toString().equals(this.userInfo.occupation) || !this.activityEditEducation.getText().toString().equals(this.userInfo.education) || !this.activityEditInterest.getText().toString().equals(this.userInfo.interest) || !TextUtils.isEmpty(this.editUserInfo.gender)) {
            return true;
        }
        if (!TextUtils.isEmpty(this.editUserInfo.birthmonth) && !this.editUserInfo.birthmonth.equals(this.userInfo.birthmonth)) {
            return true;
        }
        if (!TextUtils.isEmpty(this.editUserInfo.birthday) && !this.editUserInfo.birthday.equals(this.userInfo.birthday)) {
            return true;
        }
        UserInfoPermission permissionModel = getPermissionModel();
        if (this.infoPermission.realname != -1 && this.infoPermission.realname != permissionModel.realname) {
            return true;
        }
        if (this.infoPermission.gender != -1 && this.infoPermission.gender != permissionModel.gender) {
            return true;
        }
        if (this.infoPermission.education != -1 && this.infoPermission.education != permissionModel.education) {
            return true;
        }
        if (this.infoPermission.livingcity != -1 && this.infoPermission.livingcity != permissionModel.livingcity) {
            return true;
        }
        if (this.infoPermission.occupation != -1 && this.infoPermission.occupation != permissionModel.occupation) {
            return true;
        }
        if (this.infoPermission.birthday != -1 && this.infoPermission.birthday != permissionModel.birthday) {
            return true;
        }
        if (this.infoPermission.interest == -1 || this.infoPermission.interest == permissionModel.interest) {
            return false;
        }
        return true;
    }
}
