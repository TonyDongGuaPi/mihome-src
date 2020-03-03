package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.Gender;
import com.xiaomi.accountsdk.account.data.IdentityAuthReason;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.data.XiaomiUserCoreInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.AccountChangedBroadcastHelper;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.internal.util.SysHelper;
import com.xiaomi.passport.ui.settings.GetIdentityAuthUrlTask;
import com.xiaomi.passport.ui.settings.QueryUserInfoTask;
import com.xiaomi.passport.ui.settings.UploadMiUserProfileTask;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class AccountSettingsFragment extends Fragment {
    private static final long ONE_DAY_IN_MILLIS = 86400000;
    private static final int REQUEST_BIND_RECOVERY_EMAIL = 17;
    private static final int REQUEST_CODE_CHANGE_PASSWORD = 18;
    private static final int REQUEST_PASSPORT_IDENTITY = 16;
    private static final String TAG = "AccountSettingsFragment";
    /* access modifiers changed from: private */
    public Account mAccount;
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public GetIdentityAuthUrlTask mGetIdentityUrlTask;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public IdentityAuthReason mIdentityAuthReason;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserAvatar;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserEmail;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserGender;
    private AccountPreferenceView mPrefUserID;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserName;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserPassword;
    /* access modifiers changed from: private */
    public AccountPreferenceView mPrefUserPhone;
    private View.OnClickListener mPreferenceItemCallback = new View.OnClickListener() {
        public void onClick(View view) {
            try {
                if (view == AccountSettingsFragment.this.mPrefUserName) {
                    AccountSettingsFragment.this.showUserNameUpdateDialog();
                } else if (view == AccountSettingsFragment.this.mPrefUserGender) {
                    AccountSettingsFragment.this.showUserGenderUpdateDialog();
                } else if (view == AccountSettingsFragment.this.mPrefUserAvatar) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccountSettingsFragment.this.mActivity);
                    builder.setTitle(R.string.user_avatar_update_title);
                    builder.setSingleChoiceItems((CharSequence[]) new String[]{AccountSettingsFragment.this.getString(R.string.account_user_avatar_from_camera), AccountSettingsFragment.this.getString(R.string.account_user_avatar_from_album)}, 0, AccountSettingsFragment.this.mUpdateAvatarOnClickListener);
                    builder.show();
                } else if (view == AccountSettingsFragment.this.mPrefUserPhone) {
                    AccountSettingsFragment.this.startActivity(SysHelper.newViewPhoneInfoIntent(AccountSettingsFragment.this.getActivity(), AccountSettingsFragment.TAG));
                } else if (view == AccountSettingsFragment.this.mPrefUserEmail) {
                    AccountSettingsFragment.this.updateEmailAddress();
                } else if (view == AccountSettingsFragment.this.mPrefUserPassword) {
                    AccountSettingsFragment.this.startChangePasswordActivity();
                }
            } catch (ActivityNotFoundException e) {
                AccountLog.e(AccountSettingsFragment.TAG, "activity not found", e);
                Toast.makeText(AccountSettingsFragment.this.getActivity(), R.string.activity_not_found_notice, 1).show();
            }
        }
    };
    private QueryUserInfoTask mQueryUserInfoTask;
    private AccountManagerCallback<Boolean> mSignOutCallback = new AccountManagerCallback<Boolean>() {
        public void run(AccountManagerFuture<Boolean> accountManagerFuture) {
            boolean z;
            Activity activity;
            try {
                z = accountManagerFuture.getResult().booleanValue();
            } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                AccountLog.e(AccountSettingsFragment.TAG, "sign out failed", e);
                z = false;
            }
            if (z && (activity = AccountSettingsFragment.this.getActivity()) != null) {
                AccountChangedBroadcastHelper.sendBroadcast(activity, AccountSettingsFragment.this.mAccount, AccountChangedBroadcastHelper.UpdateType.POST_REMOVE);
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public DialogInterface.OnClickListener mUpdateAvatarOnClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case 0:
                    AccountSettingsFragment.this.startUpdateAvatarActivity(UserAvatarUpdateActivity.CAMERA);
                    break;
                case 1:
                    AccountSettingsFragment.this.startUpdateAvatarActivity("gallery");
                    break;
            }
            dialogInterface.dismiss();
        }
    };
    private HashMap<UploadProfileType, UploadMiUserProfileTask> mUploadTask = new HashMap<>();
    private Bitmap mUserAvatarBitmap;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(this.mActivity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.account_settings_layout, viewGroup, false);
        this.mPrefUserAvatar = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_avatar);
        this.mPrefUserName = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_user_name);
        this.mPrefUserID = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_user_id);
        this.mPrefUserGender = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_user_gender);
        this.mPrefUserPhone = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_user_phone);
        this.mPrefUserEmail = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_user_email);
        this.mPrefUserPassword = (AccountPreferenceView) inflate.findViewById(R.id.pref_account_password);
        this.mPrefUserAvatar.setOnClickListener(this.mPreferenceItemCallback);
        this.mPrefUserName.setOnClickListener(this.mPreferenceItemCallback);
        this.mPrefUserID.setRightArrowVisible(false);
        this.mPrefUserGender.setOnClickListener(this.mPreferenceItemCallback);
        this.mPrefUserPhone.setOnClickListener(this.mPreferenceItemCallback);
        this.mPrefUserEmail.setOnClickListener(this.mPreferenceItemCallback);
        this.mPrefUserPassword.setOnClickListener(this.mPreferenceItemCallback);
        ((Button) inflate.findViewById(R.id.logout_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AccountSettingsFragment.this.signOut();
            }
        });
        inflate.findViewById(R.id.profile_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AccountSettingsFragment.this.getActivity().onBackPressed();
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void signOut() {
        Activity activity = getActivity();
        AccountChangedBroadcastHelper.sendBroadcast(activity, this.mAccount, AccountChangedBroadcastHelper.UpdateType.PRE_REMOVE);
        MiAccountManager.get(activity).removeXiaomiAccount(this.mSignOutCallback, this.mHandler);
    }

    /* access modifiers changed from: private */
    public void startUpdateAvatarActivity(String str) {
        Intent intent = new Intent(getActivity(), UserAvatarUpdateActivity.class);
        intent.setPackage(getActivity().getPackageName());
        intent.putExtra(UserAvatarUpdateActivity.EXTRA_UPDATE_AVATAR_TYPE, str);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void startChangePasswordActivity() {
        Intent newIntent = ChangePasswordActivity.newIntent(getActivity());
        getActivity().overridePendingTransition(0, 0);
        startActivityForResult(newIntent, 18);
    }

    public void onResume() {
        super.onResume();
        queryUserInfoOnline();
    }

    private void queryUserInfoOnline() {
        if (this.mQueryUserInfoTask == null || AsyncTask.Status.RUNNING != this.mQueryUserInfoTask.getStatus()) {
            this.mQueryUserInfoTask = new QueryUserInfoTask(this.mActivity.getApplicationContext(), new UpdateOnlineUserInfoCallback(this));
            this.mQueryUserInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void onStart() {
        super.onStart();
        this.mActivity = getActivity();
    }

    public void onDestroy() {
        cancelUploadProfileTask();
        if (this.mUserAvatarBitmap != null) {
            this.mUserAvatarBitmap.recycle();
            this.mUserAvatarBitmap = null;
        }
        if (this.mGetIdentityUrlTask != null) {
            this.mGetIdentityUrlTask.cancel(true);
            this.mGetIdentityUrlTask = null;
        }
        this.mActivity = null;
        super.onDestroy();
    }

    private void cancelUploadProfileTask() {
        if (this.mUploadTask != null) {
            Iterator<UploadProfileType> it = this.mUploadTask.keySet().iterator();
            while (it.hasNext()) {
                UploadMiUserProfileTask uploadMiUserProfileTask = this.mUploadTask.get(it.next());
                if (uploadMiUserProfileTask != null) {
                    uploadMiUserProfileTask.cancel(true);
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshUserInfo() {
        String str;
        if (this.mAccount == null) {
            getActivity().finish();
        } else if (this.mActivity != null) {
            AccountManager accountManager = AccountManager.get(this.mActivity);
            String userData = accountManager.getUserData(this.mAccount, "acc_user_name");
            if (TextUtils.isEmpty(userData)) {
                userData = getString(R.string.account_none_user_name);
            }
            this.mPrefUserName.setValue(userData);
            this.mPrefUserID.setValue(this.mAccount.name);
            String userData2 = accountManager.getUserData(this.mAccount, Constants.ACCOUNT_USER_GENDER);
            if (TextUtils.isEmpty(userData2)) {
                str = getString(R.string.account_no_set);
            } else {
                str = getResources().getStringArray(R.array.account_user_gender_name)[!userData2.equals(Gender.MALE.getType())];
            }
            this.mPrefUserGender.setValue(str);
            String userData3 = accountManager.getUserData(this.mAccount, "acc_avatar_file_name");
            if (this.mUserAvatarBitmap != null) {
                this.mUserAvatarBitmap.recycle();
            }
            this.mUserAvatarBitmap = SysHelper.createPhoto(getActivity(), userData3);
            if (this.mUserAvatarBitmap != null) {
                this.mPrefUserAvatar.setImageBitmap(this.mUserAvatarBitmap);
            }
            String userData4 = accountManager.getUserData(this.mAccount, "acc_user_email");
            AccountPreferenceView accountPreferenceView = this.mPrefUserEmail;
            if (TextUtils.isEmpty(userData4)) {
                userData4 = getString(R.string.account_none_bind_info);
            }
            accountPreferenceView.setValue(userData4);
            String userData5 = accountManager.getUserData(this.mAccount, "acc_user_phone");
            AccountPreferenceView accountPreferenceView2 = this.mPrefUserPhone;
            if (TextUtils.isEmpty(userData5)) {
                userData5 = getString(R.string.account_none_bind_info);
            }
            accountPreferenceView2.setValue(userData5);
        }
    }

    private class AccountBroadCastReceiver extends BroadcastReceiver {
        private AccountBroadCastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            intent.getAction();
        }
    }

    private class UpdateOnlineUserInfoCallback implements QueryUserInfoTask.QueryUserInfoCallback {
        private final WeakReference<AccountSettingsFragment> mFragmentWeakRef;

        UpdateOnlineUserInfoCallback(AccountSettingsFragment accountSettingsFragment) {
            this.mFragmentWeakRef = new WeakReference<>(accountSettingsFragment);
        }

        public void onResult(XiaomiUserCoreInfo xiaomiUserCoreInfo) {
            AccountSettingsFragment accountSettingsFragment = (AccountSettingsFragment) this.mFragmentWeakRef.get();
            if (accountSettingsFragment != null) {
                accountSettingsFragment.refreshUserInfo();
            }
        }
    }

    /* access modifiers changed from: private */
    public void showUserNameUpdateDialog() {
        final EditText editText = new EditText(getActivity());
        editText.setText(this.mPrefUserName.getValue());
        editText.setSelection(editText.getText().length());
        final AlertDialog show = new AlertDialog.Builder(getActivity()).setTitle(R.string.account_user_name_dialog_title).setView((View) editText).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).show();
        ((ViewGroup.MarginLayoutParams) editText.getLayoutParams()).setMarginStart((int) getResources().getDimension(R.dimen.preference_left_margin));
        show.getButton(-1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String obj = editText.getText().toString();
                String access$1600 = AccountSettingsFragment.this.getUserNameInvalidReason(obj);
                if (!TextUtils.isEmpty(access$1600)) {
                    editText.setError(access$1600);
                    return;
                }
                show.dismiss();
                AccountSettingsFragment.this.startUploadUserProfileTask(UploadProfileType.TYPE_USER_NAME, obj, (Calendar) null, (Gender) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startUploadUserProfileTask(UploadProfileType uploadProfileType, String str, Calendar calendar, Gender gender) {
        if (uploadProfileType != null) {
            UploadMiUserProfileTask uploadMiUserProfileTask = this.mUploadTask.get(uploadProfileType);
            if (uploadMiUserProfileTask != null) {
                uploadMiUserProfileTask.cancel(true);
            }
            new UploadMiUserProfileTask(getActivity().getApplicationContext(), str, gender, new setUserProfileValueAfterTask()).executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }

    private class setUserProfileValueAfterTask implements UploadMiUserProfileTask.IUploadUserProfile {
        private setUserProfileValueAfterTask() {
        }

        public void onFinishUploading(String str, Gender gender) {
            Activity activity = AccountSettingsFragment.this.getActivity();
            if (!TextUtils.isEmpty(str)) {
                AccountSettingsFragment.this.mPrefUserName.setValue(str);
                UserInfoSaver.saveByType(activity, AccountSettingsFragment.this.mAccount, "acc_user_name", str);
            } else if (gender != null) {
                AccountSettingsFragment.this.mPrefUserGender.setValue(AccountSettingsFragment.this.getResources().getStringArray(R.array.account_user_gender_name)[gender == Gender.MALE ? (char) 0 : 1]);
                UserInfoSaver.saveByType(activity, AccountSettingsFragment.this.mAccount, Constants.ACCOUNT_USER_GENDER, gender.getType());
            }
        }
    }

    /* access modifiers changed from: private */
    public String getUserNameInvalidReason(String str) {
        if (TextUtils.isEmpty(str)) {
            return getString(R.string.account_empty_user_name);
        }
        if (str.length() < 2) {
            return getString(R.string.account_error_shorter_user_name);
        }
        if (str.length() > 20) {
            return getString(R.string.account_error_longer_user_name);
        }
        if (str.matches("\\s+")) {
            return getString(R.string.account_error_all_space_user_name);
        }
        if (str.contains("<") || str.contains(">") || str.contains("/")) {
            return getString(R.string.account_error_invalid_user_name);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void showUserGenderUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.account_user_gender);
        String[] stringArray = getResources().getStringArray(R.array.account_user_gender_name);
        boolean equals = this.mPrefUserGender.getValue().toString().equals(stringArray[1]);
        builder.setSingleChoiceItems((CharSequence[]) stringArray, equals ? 1 : 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AccountSettingsFragment.this.startUploadUserProfileTask(UploadProfileType.TYPE_GENDER, (String) null, (Calendar) null, i == 0 ? Gender.MALE : Gender.FEMALE);
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public void onActivityResultOfFragment(int i, int i2, Intent intent) {
        NotificationAuthResult notificationAuthResult;
        AccountLog.d(TAG, "onActivityResult() requestCode:" + i + " resultCode:" + i2);
        switch (i) {
            case 16:
                if (i2 == -1 && (notificationAuthResult = (NotificationAuthResult) intent.getParcelableExtra("notification_auth_end")) != null) {
                    AccountManager.get(getActivity()).setUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN, notificationAuthResult.serviceToken);
                    onPassIdentityAuth(this.mIdentityAuthReason);
                    return;
                }
                return;
            case 17:
                if (i2 == 9999) {
                    doIdentityAuth(IdentityAuthReason.SEND_EMAIL_ACTIVATE_MESSAGE);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: com.xiaomi.passport.ui.settings.AccountSettingsFragment$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason = new int[IdentityAuthReason.values().length];

        static {
            try {
                $SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason[IdentityAuthReason.SEND_EMAIL_ACTIVATE_MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void onPassIdentityAuth(IdentityAuthReason identityAuthReason) {
        if (identityAuthReason != null && AnonymousClass10.$SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason[identityAuthReason.ordinal()] == 1) {
            startBindSafeEmailActivity(false, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void doIdentityAuth(IdentityAuthReason identityAuthReason) {
        if (this.mGetIdentityUrlTask == null) {
            this.mIdentityAuthReason = identityAuthReason;
            this.mGetIdentityUrlTask = new GetIdentityAuthUrlTask(getActivity(), AccountManager.get(getActivity()).getUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN), identityAuthReason, new GetIdentityAuthUrlTask.GetIdentityAuthUrlCallback() {
                public void onFail(int i) {
                    GetIdentityAuthUrlTask unused = AccountSettingsFragment.this.mGetIdentityUrlTask = null;
                    Toast.makeText(AccountSettingsFragment.this.getActivity(), i, 1).show();
                }

                public void onNeedNotification(String str) {
                    GetIdentityAuthUrlTask unused = AccountSettingsFragment.this.mGetIdentityUrlTask = null;
                    Intent newNotificationIntent = AuthenticatorUtil.newNotificationIntent(AccountSettingsFragment.this.getActivity(), (Parcelable) null, str, "passportapi", true, (Bundle) null);
                    newNotificationIntent.putExtra("userId", AccountSettingsFragment.this.mAccount.name);
                    newNotificationIntent.putExtra("passToken", AuthenticatorUtil.getPassToken(AccountSettingsFragment.this.getActivity().getApplicationContext(), AccountSettingsFragment.this.mAccount));
                    AccountSettingsFragment.this.getActivity().overridePendingTransition(0, 0);
                    AccountSettingsFragment.this.getActivity().startActivityForResult(newNotificationIntent, 16);
                }

                public void onSuccess() {
                    GetIdentityAuthUrlTask unused = AccountSettingsFragment.this.mGetIdentityUrlTask = null;
                    AccountSettingsFragment.this.onPassIdentityAuth(AccountSettingsFragment.this.mIdentityAuthReason);
                }
            });
            this.mGetIdentityUrlTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void updateEmailAddress() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(this.mAccount.name, 0);
        String string = sharedPreferences.getString(Constants.SP_UNACTIVATED_EMAIL_ADDRESS, (String) null);
        long j = sharedPreferences.getLong(Constants.SP_UNACTIVATED_EMAIL_TIME_STAMP, 0);
        String userData = AccountManager.get(getActivity()).getUserData(this.mAccount, "acc_user_email");
        if (System.currentTimeMillis() - j > 86400000) {
            sharedPreferences.edit().clear().apply();
        } else if (isStillUnactivatedEmail(userData, string)) {
            startBindSafeEmailActivity(true, string);
            return;
        }
        if (TextUtils.isEmpty(userData)) {
            doIdentityAuth(IdentityAuthReason.SEND_EMAIL_ACTIVATE_MESSAGE);
            return;
        }
        showConfirmDialog(R.string.update_email_address_dialog_title, R.string.update_email_address_dialog_message, 17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AccountSettingsFragment.this.doIdentityAuth(IdentityAuthReason.SEND_EMAIL_ACTIVATE_MESSAGE);
            }
        }, 17039360, (DialogInterface.OnClickListener) null);
    }

    private boolean isStillUnactivatedEmail(String str, String str2) {
        return !TextUtils.isEmpty(str2) && (TextUtils.isEmpty(str) || !str2.equals(str));
    }

    private void startBindSafeEmailActivity(boolean z, String str) {
        Intent intent = new Intent(getActivity(), BindSafeEmailActivity.class);
        intent.putExtra(Constants.EXTRA_HAS_UNACTIVATED_EMAIL, z);
        intent.putExtra(Constants.SP_UNACTIVATED_EMAIL_ADDRESS, str);
        getActivity().startActivityForResult(intent, 17);
    }

    private void showConfirmDialog(int i, int i2, int i3, DialogInterface.OnClickListener onClickListener, int i4, DialogInterface.OnClickListener onClickListener2) {
        new AlertDialog.Builder(getActivity()).setTitle(i).setMessage(i2).setPositiveButton(i3, onClickListener).setNegativeButton(i4, onClickListener2).create().show();
    }
}
