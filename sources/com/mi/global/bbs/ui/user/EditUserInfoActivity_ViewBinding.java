package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.PermissionImageView;

public class EditUserInfoActivity_ViewBinding implements Unbinder {
    private EditUserInfoActivity target;
    private View view2131492907;
    private View view2131492909;
    private View view2131492914;
    private View view2131492915;

    @UiThread
    public EditUserInfoActivity_ViewBinding(EditUserInfoActivity editUserInfoActivity) {
        this(editUserInfoActivity, editUserInfoActivity.getWindow().getDecorView());
    }

    @UiThread
    public EditUserInfoActivity_ViewBinding(final EditUserInfoActivity editUserInfoActivity, View view) {
        this.target = editUserInfoActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.activity_edit_icon, "field 'activityEditIcon' and method 'onClick'");
        editUserInfoActivity.activityEditIcon = (ImageView) Utils.castView(findRequiredView, R.id.activity_edit_icon, "field 'activityEditIcon'", ImageView.class);
        this.view2131492914 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserInfoActivity.onClick(view);
            }
        });
        editUserInfoActivity.activityEditRealName = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_real_name, "field 'activityEditRealName'", FontEditText.class);
        editUserInfoActivity.activityEditRealNamePermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_real_name_permission, "field 'activityEditRealNamePermission'", PermissionImageView.class);
        editUserInfoActivity.activityEditMale = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.activity_edit_male, "field 'activityEditMale'", CheckedTextView.class);
        editUserInfoActivity.activityEditFemale = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.activity_edit_female, "field 'activityEditFemale'", CheckedTextView.class);
        editUserInfoActivity.activityEditGenderPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_gender_permission, "field 'activityEditGenderPermission'", PermissionImageView.class);
        editUserInfoActivity.activityEditLivingCity = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_living_city, "field 'activityEditLivingCity'", FontEditText.class);
        editUserInfoActivity.activityEditLivingCityPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_living_city_permission, "field 'activityEditLivingCityPermission'", PermissionImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.activity_edit_birthday, "field 'activityEditBirthday' and method 'onClick'");
        editUserInfoActivity.activityEditBirthday = (TextView) Utils.castView(findRequiredView2, R.id.activity_edit_birthday, "field 'activityEditBirthday'", TextView.class);
        this.view2131492907 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserInfoActivity.onClick(view);
            }
        });
        editUserInfoActivity.activityEditBirthdayPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_birthday_permission, "field 'activityEditBirthdayPermission'", PermissionImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.activity_edit_education, "field 'activityEditEducation' and method 'onClick'");
        editUserInfoActivity.activityEditEducation = (TextView) Utils.castView(findRequiredView3, R.id.activity_edit_education, "field 'activityEditEducation'", TextView.class);
        this.view2131492909 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserInfoActivity.onClick(view);
            }
        });
        editUserInfoActivity.activityEditEducationPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_education_permission, "field 'activityEditEducationPermission'", PermissionImageView.class);
        editUserInfoActivity.activityEditJob = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_job, "field 'activityEditJob'", FontEditText.class);
        editUserInfoActivity.activityEditJobPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_job_permission, "field 'activityEditJobPermission'", PermissionImageView.class);
        editUserInfoActivity.activityEditInterest = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_interest, "field 'activityEditInterest'", FontEditText.class);
        editUserInfoActivity.activityEditInterestPermission = (PermissionImageView) Utils.findRequiredViewAsType(view, R.id.activity_edit_interest_permission, "field 'activityEditInterestPermission'", PermissionImageView.class);
        editUserInfoActivity.activityEditNickName = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_nick_name, "field 'activityEditNickName'", FontEditText.class);
        editUserInfoActivity.activityEditEmail = (FontEditText) Utils.findRequiredViewAsType(view, R.id.activity_edit_email, "field 'activityEditEmail'", FontEditText.class);
        editUserInfoActivity.activityEditNickNameEditable = (TextView) Utils.findRequiredViewAsType(view, R.id.activity_edit_nick_name_editable, "field 'activityEditNickNameEditable'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.activity_edit_icon_bt, "method 'onClick'");
        this.view2131492915 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editUserInfoActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        EditUserInfoActivity editUserInfoActivity = this.target;
        if (editUserInfoActivity != null) {
            this.target = null;
            editUserInfoActivity.activityEditIcon = null;
            editUserInfoActivity.activityEditRealName = null;
            editUserInfoActivity.activityEditRealNamePermission = null;
            editUserInfoActivity.activityEditMale = null;
            editUserInfoActivity.activityEditFemale = null;
            editUserInfoActivity.activityEditGenderPermission = null;
            editUserInfoActivity.activityEditLivingCity = null;
            editUserInfoActivity.activityEditLivingCityPermission = null;
            editUserInfoActivity.activityEditBirthday = null;
            editUserInfoActivity.activityEditBirthdayPermission = null;
            editUserInfoActivity.activityEditEducation = null;
            editUserInfoActivity.activityEditEducationPermission = null;
            editUserInfoActivity.activityEditJob = null;
            editUserInfoActivity.activityEditJobPermission = null;
            editUserInfoActivity.activityEditInterest = null;
            editUserInfoActivity.activityEditInterestPermission = null;
            editUserInfoActivity.activityEditNickName = null;
            editUserInfoActivity.activityEditEmail = null;
            editUserInfoActivity.activityEditNickNameEditable = null;
            this.view2131492914.setOnClickListener((View.OnClickListener) null);
            this.view2131492914 = null;
            this.view2131492907.setOnClickListener((View.OnClickListener) null);
            this.view2131492907 = null;
            this.view2131492909.setOnClickListener((View.OnClickListener) null);
            this.view2131492909 = null;
            this.view2131492915.setOnClickListener((View.OnClickListener) null);
            this.view2131492915 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
