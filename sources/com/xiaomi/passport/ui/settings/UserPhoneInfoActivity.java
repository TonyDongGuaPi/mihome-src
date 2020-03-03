package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.IdentityAuthReason;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.stat.AccountStatInterface;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.settings.GetIdentityAuthUrlTask;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;

public class UserPhoneInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PASSPORT_PHONE_IDENTITY = 10001;
    private static final int REQUEST_UPDATE_SAFE_PHONE = 10002;
    private static final String TAG = "UserPhoneInfoActivity";
    /* access modifiers changed from: private */
    public Account mAccount;
    /* access modifiers changed from: private */
    public GetIdentityAuthUrlTask mGetPhoneIdentityAuthTask;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
            return;
        }
        setContentView(R.layout.user_phone_info);
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(this);
        if (this.mAccount == null) {
            AccountLog.i(TAG, "no xiaomi account");
            finish();
            return;
        }
        initIconInfo(findViewById(R.id.use_sign_in), R.drawable.icon_sign_in, R.string.sign_in);
        initIconInfo(findViewById(R.id.use_get_back_pwd), R.drawable.icon_get_back_pwd, R.string.get_back_pwd);
        initIconInfo(findViewById(R.id.use_identity), R.drawable.icon_identity, R.string.identity);
        refreshUpdatePhoneInfo();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AccountStatInterface.getInstance().statPageStart(TAG);
    }

    public void onPause() {
        AccountStatInterface.getInstance().statPageEnd(TAG);
        super.onPause();
    }

    public void onDestroy() {
        if (this.mGetPhoneIdentityAuthTask != null) {
            this.mGetPhoneIdentityAuthTask.cancel(true);
            this.mGetPhoneIdentityAuthTask = null;
        }
        super.onDestroy();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.action_btn) {
            doPhoneIdentityAuth();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        NotificationAuthResult notificationAuthResult;
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 10001:
                if (i2 == -1 && (notificationAuthResult = (NotificationAuthResult) intent.getParcelableExtra("notification_auth_end")) != null) {
                    AccountManager.get(this).setUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN, notificationAuthResult.serviceToken);
                    startUpdateSafePhone();
                    return;
                }
                return;
            case 10002:
                if (i2 == -1) {
                    refreshUpdatePhoneInfo();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void refreshUpdatePhoneInfo() {
        String userData = AccountManager.get(getApplicationContext()).getUserData(this.mAccount, "acc_user_phone");
        boolean isEmpty = TextUtils.isEmpty(userData);
        ImageView imageView = (ImageView) findViewById(R.id.icon_phone);
        int i = 0;
        if (imageView != null) {
            imageView.setVisibility(isEmpty ? 8 : 0);
        }
        TextView textView = (TextView) findViewById(R.id.phone_num);
        if (textView != null) {
            if (isEmpty) {
                userData = getString(R.string.no_phone);
            }
            textView.setText(userData);
        }
        TextView textView2 = (TextView) findViewById(R.id.update_phone_notice);
        if (textView2 != null) {
            if (isEmpty) {
                i = 8;
            }
            textView2.setVisibility(i);
        }
        Button button = (Button) findViewById(R.id.action_btn);
        if (button != null) {
            button.setText(isEmpty ? R.string.action_add_phone : R.string.action_update_phone);
            button.setOnClickListener(this);
        }
    }

    private void initIconInfo(View view, int i, int i2) {
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            if (imageView != null) {
                imageView.setImageDrawable(getResources().getDrawable(i));
            }
            TextView textView = (TextView) view.findViewById(R.id.icon_desc);
            if (textView != null) {
                textView.setText(i2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startUpdateSafePhone() {
        Intent intent = new Intent(this, BindPhoneActivity.class);
        intent.setPackage(getPackageName());
        startActivityForResult(intent, 10002);
    }

    private void doPhoneIdentityAuth() {
        if (this.mGetPhoneIdentityAuthTask == null) {
            this.mGetPhoneIdentityAuthTask = new GetIdentityAuthUrlTask(this, AccountManager.get(this).getUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN), IdentityAuthReason.MODIFY_SAFE_PHONE, new GetIdentityAuthUrlTask.GetIdentityAuthUrlCallback() {
                public void onNeedNotification(String str) {
                    GetIdentityAuthUrlTask unused = UserPhoneInfoActivity.this.mGetPhoneIdentityAuthTask = null;
                    Intent newNotificationIntent = AuthenticatorUtil.newNotificationIntent(UserPhoneInfoActivity.this, (Parcelable) null, str, "passportapi", true, (Bundle) null);
                    newNotificationIntent.putExtra("userId", UserPhoneInfoActivity.this.mAccount.name);
                    newNotificationIntent.putExtra("passToken", AuthenticatorUtil.getPassToken(UserPhoneInfoActivity.this.getApplicationContext(), UserPhoneInfoActivity.this.mAccount));
                    UserPhoneInfoActivity.this.startActivityForResult(newNotificationIntent, 10001);
                }

                public void onSuccess() {
                    GetIdentityAuthUrlTask unused = UserPhoneInfoActivity.this.mGetPhoneIdentityAuthTask = null;
                    UserPhoneInfoActivity.this.startUpdateSafePhone();
                }

                public void onFail(int i) {
                    GetIdentityAuthUrlTask unused = UserPhoneInfoActivity.this.mGetPhoneIdentityAuthTask = null;
                    Toast.makeText(UserPhoneInfoActivity.this, i, 1).show();
                }
            });
            this.mGetPhoneIdentityAuthTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }
}
