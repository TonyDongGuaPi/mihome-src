package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.MiuiActivatorInfo;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.internal.util.SysHelper;
import com.xiaomi.passport.ui.settings.BindPhoneActivity;
import com.xiaomi.passport.ui.settings.GetUserBindIdAndLimitTask;
import com.xiaomi.passport.ui.settings.utils.AccountSmsVerifyCodeReceiver;
import com.xiaomi.passport.ui.settings.utils.PhoneNumUtil;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputBindedPhoneFragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_CODE_FOREIGN_LOGIN = 10001;
    private static final String TAG = "InputBindedPhoneFragmen";
    private Account mAccount;
    private TextView mAreaCodeView;
    private Button mBtnNext;
    /* access modifiers changed from: private */
    public CaptchaView mCaptchaView;
    private String mCheckedPhone;
    private PhoneNumUtil.CountryPhoneNumData mCountryPhoneNumData;
    /* access modifiers changed from: private */
    public TextView mErrorStatusView;
    /* access modifiers changed from: private */
    public AsyncTask<Void, Void, Pair<Integer, GetUserBindIdAndLimitTask.UserBindIdAndLimitResult>> mGetUserBindIdAndLimitTask;
    private EditText mPhoneView;
    final TextWatcher mTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            InputBindedPhoneFragment.this.updateShowErrorStatusView(false, (String) null);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PhoneNumUtil.initializeCountryPhoneData(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.input_bind_phone_address, viewGroup, false);
        this.mAreaCodeView = (TextView) inflate.findViewById(R.id.tv_area_code);
        this.mPhoneView = (EditText) inflate.findViewById(R.id.ev_phone);
        this.mPhoneView.addTextChangedListener(this.mTextWatcher);
        this.mErrorStatusView = (TextView) inflate.findViewById(R.id.error_status);
        this.mBtnNext = (Button) inflate.findViewById(R.id.btn_phone_next);
        this.mAreaCodeView.setOnClickListener(this);
        this.mBtnNext.setOnClickListener(this);
        this.mCaptchaView = (CaptchaView) inflate.findViewById(R.id.captcha_layout);
        refreshViewStateByISO(Locale.getDefault().getCountry());
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(getActivity());
        if (this.mAccount == null) {
            AccountLog.i(TAG, "no xiaomi account");
            getActivity().finish();
        }
    }

    public void onDestroy() {
        if (this.mGetUserBindIdAndLimitTask != null) {
            this.mGetUserBindIdAndLimitTask.cancel(true);
            this.mGetUserBindIdAndLimitTask = null;
        }
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && -1 == i2) {
            refreshViewStateByISO(intent.getStringExtra(AreaCodePickerFragment.KEY_COUNTRY_ISO));
        }
    }

    private void refreshViewStateByISO(String str) {
        if (TextUtils.isEmpty(str)) {
            AccountLog.d(TAG, "region info is null, and set China as the default area iso");
            str = "CN";
        }
        this.mCountryPhoneNumData = PhoneNumUtil.getCounrtyPhoneDataFromIso(str);
        if (this.mAreaCodeView != null) {
            TextView textView = this.mAreaCodeView;
            textView.setText(this.mCountryPhoneNumData.countryName + "(+" + this.mCountryPhoneNumData.countryCode + Operators.BRACKET_END_STR);
        }
    }

    private String getTheInputPhoneNumber() {
        String obj = this.mPhoneView.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mPhoneView.setError(getString(R.string.passport_error_empty_phone_num));
            return null;
        }
        if (this.mCountryPhoneNumData != null) {
            obj = PhoneNumUtil.checkNumber(obj, this.mCountryPhoneNumData);
            if (TextUtils.isEmpty(obj)) {
                this.mPhoneView.setError(getString(R.string.passport_wrong_phone_number_format));
                return null;
            }
        }
        if (!TextUtils.equals(AccountManager.get(getActivity()).getUserData(this.mAccount, "acc_user_phone"), obj)) {
            return obj;
        }
        this.mPhoneView.setError(getString(R.string.failed_dup_secure_phone_number));
        return null;
    }

    public void onClick(View view) {
        if (view == this.mAreaCodeView) {
            Intent intent = new Intent(getActivity(), AreaCodePickerActivity.class);
            intent.setPackage(getActivity().getPackageName());
            startActivityForResult(intent, 10001);
        } else if (view == this.mBtnNext) {
            String theInputPhoneNumber = getTheInputPhoneNumber();
            if (TextUtils.isEmpty(theInputPhoneNumber)) {
                return;
            }
            if (TextUtils.equals(this.mCheckedPhone, theInputPhoneNumber)) {
                sendModifyPhoneTicket(theInputPhoneNumber);
            } else {
                getUserBindIdAndLimit(theInputPhoneNumber);
            }
        }
    }

    private void getUserBindIdAndLimit(final String str) {
        if (this.mGetUserBindIdAndLimitTask == null) {
            this.mGetUserBindIdAndLimitTask = new GetUserBindIdAndLimitTask(getActivity(), str, new GetUserBindIdAndLimitTask.GetUserBindIdAndLimitCallBack() {
                public void onFail(int i) {
                    AsyncTask unused = InputBindedPhoneFragment.this.mGetUserBindIdAndLimitTask = null;
                    InputBindedPhoneFragment.this.updateShowErrorStatusView(true, InputBindedPhoneFragment.this.getString(i));
                }

                public void onSuccess(GetUserBindIdAndLimitTask.UserBindIdAndLimitResult userBindIdAndLimitResult) {
                    AsyncTask unused = InputBindedPhoneFragment.this.mGetUserBindIdAndLimitTask = null;
                    InputBindedPhoneFragment.this.mErrorStatusView.setVisibility(8);
                    try {
                        int times = userBindIdAndLimitResult.getTimes();
                        long time = userBindIdAndLimitResult.getTime();
                        String userId = userBindIdAndLimitResult.getUserId();
                        if (times == 0) {
                            InputBindedPhoneFragment.this.updateShowErrorStatusView(true, InputBindedPhoneFragment.this.getString(R.string.get_phone_bind_exceed_limit));
                        } else if (TextUtils.isEmpty(userId)) {
                            InputBindedPhoneFragment.this.sendModifyPhoneTicket(str);
                        } else {
                            InputBindedPhoneFragment.this.confirmUnbundledPhone(str, time, userId);
                        }
                    } catch (Exception e) {
                        AccountLog.e(InputBindedPhoneFragment.TAG, "GetUserBindIdAndLimitException", e);
                    }
                }
            });
            this.mGetUserBindIdAndLimitTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void confirmUnbundledPhone(final String str, long j, String str2) {
        Date date = new Date();
        date.setTime(j);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(date);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirm_bundled_phone_dialog_title);
        builder.setMessage((CharSequence) String.format(getString(R.string.confirm_unbundled_phone_dialog_message), new Object[]{format, str2, str2}));
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                InputBindedPhoneFragment.this.sendModifyPhoneTicket(str);
            }
        });
        builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    private void modifySafePhone(String str, MiuiActivatorInfo miuiActivatorInfo) {
        ((BindPhoneActivity) getActivity()).modifySafePhone(str, miuiActivatorInfo, (String) null, new BindPhoneActivity.ModifyPhoneCallback() {
            public void onNeedSMSCode(String str) {
                InputBindedPhoneFragment.this.sendModifyPhoneTicket(str);
            }

            public void onError(int i) {
                InputBindedPhoneFragment.this.updateShowErrorStatusView(true, InputBindedPhoneFragment.this.getString(i));
            }

            public void onSuccess() {
                AccountLog.i(InputBindedPhoneFragment.TAG, "modify phone success");
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendModifyPhoneTicket(final String str) {
        String str2;
        if (this.mCaptchaView.getVisibility() == 0) {
            str2 = this.mCaptchaView.getCaptchaCode();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
        } else {
            str2 = null;
        }
        ((BindPhoneActivity) getActivity()).sendModifySafePhoneTicket(str, str2, this.mCaptchaView.getCaptchaIck(), new BindPhoneActivity.SendTicketCallback() {
            public void onNeedCaptchaCode(String str) {
                if (InputBindedPhoneFragment.this.mCaptchaView.getVisibility() == 0) {
                    InputBindedPhoneFragment.this.updateShowErrorStatusView(true, InputBindedPhoneFragment.this.getString(R.string.passport_wrong_captcha));
                }
                InputBindedPhoneFragment.this.mCaptchaView.setVisibility(0);
                InputBindedPhoneFragment.this.mCaptchaView.downloadCaptcha(str, Constants.URL_ANTI_SPAM_GET_VOICE_CAPTCHA_CODE);
            }

            public void onError(int i) {
                InputBindedPhoneFragment.this.updateShowErrorStatusView(true, InputBindedPhoneFragment.this.getString(i));
            }

            public void onSuccess() {
                if (!AccountSmsVerifyCodeReceiver.tryRequestSmsPermission(InputBindedPhoneFragment.this.getActivity())) {
                    InputBindedPhoneFragment.this.switchToInputVerifyCodeFragment(str);
                }
            }
        });
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (51 == i) {
            switchToInputVerifyCodeFragment(getTheInputPhoneNumber());
        }
    }

    /* access modifiers changed from: private */
    public void switchToInputVerifyCodeFragment(String str) {
        SysHelper.replaceToFragment(getActivity(), InputBindedVerifyCodeFragment.getInstance(str, getArguments()), false, ((ViewGroup) getView().getParent()).getId());
    }

    /* access modifiers changed from: private */
    public void updateShowErrorStatusView(boolean z, String str) {
        int i;
        if (z) {
            this.mErrorStatusView.setVisibility(0);
            this.mErrorStatusView.setText(str);
            i = R.dimen.passport_buttons_margin_v;
        } else {
            this.mErrorStatusView.setVisibility(8);
            i = R.dimen.passport_reg_content_bottom_margin;
        }
        setNextBtnMarginTop(getResources().getDimensionPixelSize(i));
    }

    private void setNextBtnMarginTop(int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, i, 0, 0);
        this.mBtnNext.setLayoutParams(layoutParams);
    }
}
