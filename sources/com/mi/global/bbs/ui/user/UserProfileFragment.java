package com.mi.global.bbs.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.view.SimpleSymmetryTextView;

public class UserProfileFragment extends BaseFragment {
    private UserInfoModel.DataBean userInfo;
    @BindView(2131494226)
    SimpleSymmetryTextView userProfileBirth;
    @BindView(2131494227)
    SimpleSymmetryTextView userProfileCity;
    @BindView(2131494228)
    SimpleSymmetryTextView userProfileDegree;
    @BindView(2131494229)
    SimpleSymmetryTextView userProfileGender;
    @BindView(2131494230)
    SimpleSymmetryTextView userProfileId;
    @BindView(2131494231)
    TextView userProfileInterest;
    @BindView(2131494232)
    SimpleSymmetryTextView userProfileJob;
    @BindView(2131494233)
    SimpleSymmetryTextView userProfileName;
    @BindView(2131494234)
    SimpleSymmetryTextView userProfileRegisterDate;
    @BindView(2131494235)
    NestedScrollView userProfileRoot;
    @BindView(2131494236)
    SimpleSymmetryTextView userProfileStatus;
    @BindView(2131494237)
    SimpleSymmetryTextView userProfileVisitTime;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_fragment_user_profile, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        if (this.userInfo != null) {
            inflaterUi(this.userInfo);
        }
        return inflate;
    }

    public void setPadding(int i) {
        if (this.userProfileRoot != null) {
            this.userProfileRoot.setPadding(0, 0, 0, i);
        }
    }

    public void inflaterUi(UserInfoModel.DataBean dataBean) {
        if (getActivity() != null) {
            String userId = LoginManager.getInstance().getUserId();
            if (TextUtils.isEmpty(userId) || !userId.equals(dataBean.uid)) {
                this.userProfileId.setRightText(dataBean.uid);
                this.userProfileRegisterDate.setRightText(dataBean.regdate);
                this.userProfileVisitTime.setRightText(dataBean.lastvisit);
                this.userProfileStatus.setRightText(getResources().getStringArray(R.array.online)[dataBean.online]);
                this.userProfileName.setRightText(dataBean.realname);
                String str = dataBean.gender;
                if (!TextUtils.isEmpty(str) && !str.equals("null")) {
                    this.userProfileGender.setRightText(getResources().getStringArray(R.array.gender)[Integer.valueOf(str).intValue()]);
                }
                this.userProfileCity.setRightText(dataBean.livingcity);
                if (!TextUtils.isEmpty(dataBean.birthmonth) && !dataBean.birthmonth.equals("0")) {
                    this.userProfileBirth.setRightText(getString(R.string.birthday_, dataBean.birthmonth, dataBean.birthday));
                }
                this.userProfileDegree.setRightText(dataBean.education);
                this.userProfileJob.setRightText(dataBean.occupation);
                this.userProfileInterest.setText(dataBean.interest);
                return;
            }
            this.userProfileId.setRightText(dataBean.uid);
            this.userProfileRegisterDate.setRightText(dataBean.regdate);
            this.userProfileVisitTime.setRightText(dataBean.lastvisit);
            this.userProfileStatus.setRightText(getResources().getStringArray(R.array.online)[dataBean.online]);
            this.userProfileName.setRightDefaultText(dataBean.realname);
            String str2 = dataBean.gender;
            if (TextUtils.isEmpty(str2) || str2.equals("null")) {
                this.userProfileGender.setRightDefaultText("");
            } else {
                this.userProfileGender.setRightText(getResources().getStringArray(R.array.gender)[Integer.valueOf(str2).intValue()]);
            }
            this.userProfileCity.setRightDefaultText(dataBean.livingcity);
            if (TextUtils.isEmpty(dataBean.birthmonth) || dataBean.birthmonth.equals("0")) {
                this.userProfileBirth.setRightDefaultText("");
            } else {
                this.userProfileBirth.setRightText(getString(R.string.birthday_, dataBean.birthmonth, dataBean.birthday));
            }
            this.userProfileDegree.setRightDefaultText(dataBean.education);
            this.userProfileJob.setRightDefaultText(dataBean.occupation);
            this.userProfileInterest.setText(dataBean.interest);
            TextHelper.setText(this.userProfileInterest, dataBean.interest, getString(R.string.to_be_completed));
        }
    }

    public void setUserInfo(UserInfoModel.DataBean dataBean) {
        this.userInfo = dataBean;
    }
}
