package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.SplashUtil;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.AppSettingsItem;
import com.mi.global.bbs.view.dialog.ShareDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ResourceBundle;

public class AppSettingsActivity extends BaseActivity {
    private static final String TAG = "AppSettingsActivity";
    private Handler mHandler;
    @BindView(2131493954)
    AppSettingsItem settingsClear;
    @BindView(2131493958)
    TextView settingsLog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.Settings, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_app_settings);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        GoogleTrackerUtil.sendRecordPage(TAG);
        LoginManager.getInstance().addLoginListener(this);
        showButtonByLogState();
    }

    private void createMainHandlerIfNull() {
        if (this.mHandler == null) {
            this.mHandler = new Handler(getMainLooper());
        }
    }

    /* access modifiers changed from: private */
    public void showButtonByLogState() {
        this.settingsLog.setVisibility((!LoginManager.getInstance().hasLogin() || TextUtils.isEmpty(LoginManager.getInstance().getUserId())) ? 8 : 0);
    }

    @OnClick({2131493959, 2131493960, 2131493961, 2131493955, 2131493954, 2131493958})
    public void onClick(View view) {
        if (!ConnectionHelper.isOpenNetwork(this)) {
            toast(Integer.valueOf(R.string.bbs_network_unavaliable));
        } else if (view.getId() == R.id.settings_notifications) {
            if (LoginManager.getInstance().hasLogin()) {
                startActivity(new Intent(this, NoticeSettingsActivity.class));
            } else {
                gotoAccount();
            }
        } else if (view.getId() == R.id.settings_rate) {
            rateUs();
        } else if (view.getId() == R.id.settings_share) {
            share();
        } else if (view.getId() == R.id.settings_feedback) {
            if (LoginManager.getInstance().hasLogin()) {
                startActivity(new Intent(this, FeedbackActivity.class));
            } else {
                gotoAccount();
            }
        } else if (view.getId() == R.id.settings_clear) {
            clearAppCache();
        } else if (view.getId() == R.id.settings_log) {
            logout();
        }
    }

    private void share() {
        String str = "https://play.google.com/store/apps/details?id=" + getPackageName();
        new ShareDialog(this).setShareTitle(getString(R.string.share_app_title)).setShareText(getString(R.string.share_app_des)).setShareUrl(str).setCallbackManager(this.callbackManager).show();
    }

    private void clearAppCache() {
        showProDialog(getResources().getString(R.string.clearing));
        Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                ResourceBundle.clearCache();
                Glide.b((Context) AppSettingsActivity.this).h();
                SplashUtil.clearSplash();
                observableEmitter.onNext("");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(@NonNull String str) throws Exception {
                Glide.b((Context) AppSettingsActivity.this).g();
                Utils.Preference.removePref(AppSettingsActivity.this, "userInfo");
                AppSettingsActivity.this.toast(Integer.valueOf(R.string.cache_cleared_successfully));
                AppSettingsActivity.this.dismissProDialog();
            }
        });
    }

    private void rateUs() {
        String packageName = getPackageName();
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if ((i == 1 || i == 2) && i2 != 0 && i2 == -1) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent();
                    intent.putExtra("changeRegion", 1);
                    AppSettingsActivity.this.setResult(-1, intent);
                    AppSettingsActivity.this.finish();
                }
            });
        }
    }

    public void logout() {
        DialogFactory.showAlert(this, getString(R.string.log_out_hint), new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                AppSettingsActivity.this.goToLogOut();
            }
        });
    }

    public void onLogout() {
        super.onLogout();
        createMainHandlerIfNull();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                Utils.Preference.removePref(BBSApplication.getInstance(), "userInfo");
                AppSettingsActivity.this.toast(Integer.valueOf(R.string.log_out_success));
                AppSettingsActivity.this.finish();
            }
        }, 800);
    }

    public void onLogin(String str, String str2, String str3) {
        super.onLogin(str, str2, str3);
        createMainHandlerIfNull();
        this.mHandler.post(new Runnable() {
            public void run() {
                AppSettingsActivity.this.showButtonByLogState();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
        LoginManager.getInstance().removeLoginListener(this);
    }
}
