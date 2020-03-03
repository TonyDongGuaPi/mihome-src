package com.mi.global.bbs.ui.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.SimpleSymmetryTextView;

public class UserProfileFragment_ViewBinding implements Unbinder {
    private UserProfileFragment target;

    @UiThread
    public UserProfileFragment_ViewBinding(UserProfileFragment userProfileFragment, View view) {
        this.target = userProfileFragment;
        userProfileFragment.userProfileId = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_id, "field 'userProfileId'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileRegisterDate = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_register_date, "field 'userProfileRegisterDate'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileVisitTime = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_visit_time, "field 'userProfileVisitTime'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileStatus = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_status, "field 'userProfileStatus'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileName = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_name, "field 'userProfileName'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileGender = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_gender, "field 'userProfileGender'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileCity = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_city, "field 'userProfileCity'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileBirth = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_birth, "field 'userProfileBirth'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileDegree = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_degree, "field 'userProfileDegree'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileJob = (SimpleSymmetryTextView) Utils.findRequiredViewAsType(view, R.id.user_profile_job, "field 'userProfileJob'", SimpleSymmetryTextView.class);
        userProfileFragment.userProfileInterest = (TextView) Utils.findRequiredViewAsType(view, R.id.user_profile_interest, "field 'userProfileInterest'", TextView.class);
        userProfileFragment.userProfileRoot = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.user_profile_root, "field 'userProfileRoot'", NestedScrollView.class);
    }

    @CallSuper
    public void unbind() {
        UserProfileFragment userProfileFragment = this.target;
        if (userProfileFragment != null) {
            this.target = null;
            userProfileFragment.userProfileId = null;
            userProfileFragment.userProfileRegisterDate = null;
            userProfileFragment.userProfileVisitTime = null;
            userProfileFragment.userProfileStatus = null;
            userProfileFragment.userProfileName = null;
            userProfileFragment.userProfileGender = null;
            userProfileFragment.userProfileCity = null;
            userProfileFragment.userProfileBirth = null;
            userProfileFragment.userProfileDegree = null;
            userProfileFragment.userProfileJob = null;
            userProfileFragment.userProfileInterest = null;
            userProfileFragment.userProfileRoot = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
