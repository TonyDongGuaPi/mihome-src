package com.xiaomi.passport.ui.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.AuthenticatorUtil;

public class UserAvatarUpdateActivity extends AppCompatActivity {
    public static final String CAMERA = "camera";
    public static final String EXTRA_UPDATE_AVATAR_TYPE = "update_avatar_type";
    public static final String GALLERY = "gallery";
    private static final String TAG = "UserAvatarUpdateActivity";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
            return;
        }
        UserAvatarUpdateFragment userAvatarUpdateFragment = new UserAvatarUpdateFragment();
        userAvatarUpdateFragment.setArguments(getIntent().getExtras());
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, userAvatarUpdateFragment);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (AuthenticatorUtil.getXiaomiAccount(this) == null) {
            AccountLog.w(TAG, "no xiaomi account");
            finish();
        }
    }
}
