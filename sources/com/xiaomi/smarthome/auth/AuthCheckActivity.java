package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.model.AuthCallBackInfo;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthSlaveListActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;

public class AuthCheckActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13777a = "AuthCheckActivity";
    String appId;
    private String b;
    private String c;
    private String d = "-1";
    /* access modifiers changed from: private */
    public IAuthCallBack e;
    /* access modifiers changed from: private */
    public int f = -1;
    /* access modifiers changed from: private */
    public Bundle g;
    /* access modifiers changed from: private */
    public boolean h = false;
    private BroadcastReceiver i = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), HomeManager.t) && TextUtils.equals(HomeManager.x, intent.getStringExtra(HomeManager.v))) {
                if (intent.getIntExtra("result_code", -1) == ErrorCode.SUCCESS.getCode()) {
                    boolean unused = AuthCheckActivity.this.j = true;
                    if (AuthCheckActivity.this.k && AuthCheckActivity.this.j) {
                        AuthCheckActivity.this.j();
                        return;
                    }
                    return;
                }
                AuthCheckActivity.this.k();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    private MLAlertDialog l;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void a(int i) {
            Miio.b("AuthManager", "onRefreshClientDeviceSuccesstype   " + i);
            if (i == 3) {
                boolean unused = AuthCheckActivity.this.k = true;
                if (AuthCheckActivity.this.k && AuthCheckActivity.this.j) {
                    AuthCheckActivity.this.j();
                }
            }
        }

        public void b(int i) {
            AuthCheckActivity.this.k();
        }
    };
    @BindView(2131427734)
    TextView mAppDescTV;
    @BindView(2131427735)
    SimpleDraweeView mAppIconIV;
    @BindView(2131427736)
    TextView mAppNameTV;
    View.OnClickListener mConfigClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int access$1000 = AuthCheckActivity.this.f;
            if (access$1000 == 2) {
                Intent intent = new Intent(SHApplication.getAppContext(), DeviceAuthSlaveListActivity.class);
                AuthCheckActivity.this.g.putBoolean(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, true);
                intent.putExtras(AuthCheckActivity.this.g);
                AuthCheckActivity.this.startActivity(intent);
                AuthCheckActivity.this.finish();
            } else if (access$1000 == 4) {
                AuthCheckActivity.this.g();
            } else if (access$1000 == 6) {
                String string = AuthCheckActivity.this.g.getString("device_id");
                if (TextUtils.isEmpty(string) || SmartHomeDeviceManager.a().b(string) == null || !SmartHomeDeviceManager.a().b(string).isOnline) {
                    Intent intent2 = new Intent(SHApplication.getAppContext(), SmartConfigMainActivity.class);
                    intent2.putExtra("did", AuthCheckActivity.this.g.getString("device_id"));
                    intent2.putExtra("strategy_id", 10);
                    if (AuthCheckActivity.this.g.getString(AuthConstants.f) != null) {
                        intent2.putExtra("bind_key", AuthCheckActivity.this.g.getString(AuthConstants.f));
                    } else {
                        intent2.putExtra("token", AuthCheckActivity.this.g.getString(AuthConstants.e));
                        intent2.putExtra("timestamp", AuthCheckActivity.this.g.getLong(AuthConstants.j));
                        intent2.putExtra("sn", AuthCheckActivity.this.g.getString(AuthConstants.k));
                    }
                    AuthCheckActivity.this.startActivity(intent2);
                    AuthCheckActivity.this.finish();
                    return;
                }
                Intent intent3 = new Intent(SHApplication.getAppContext(), DeviceAuthSlaveListActivity.class);
                AuthCheckActivity.this.g.putString("device_id", string);
                AuthCheckActivity.this.g.putBoolean(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, true);
                AuthManager.h().a(2);
                intent3.putExtras(AuthCheckActivity.this.g);
                AuthCheckActivity.this.startActivity(intent3);
                AuthCheckActivity.this.finish();
            }
        }
    };
    @BindView(2131427737)
    TextView mConfigTV;
    XQProgressDialog mProcessDialog;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131430969)
    ImageView mTitleReturn;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auth_check_layout);
        ButterKnife.bind((Activity) this);
        this.mTitle.setText(R.string.outer_auth_request);
        this.mTitleReturn.setVisibility(8);
        Intent intent = getIntent();
        intent.setExtrasClassLoader(IAuthCallBack.class.getClassLoader());
        if (intent != null) {
            AuthManager.c = intent.getIntExtra(AuthConstants.h, -1);
        }
        if (AuthManager.c >= 7) {
            AuthManager.h().l();
            a(intent);
        }
        HomeKeyManager.a().b();
        HomeKeyManager.a().a(true);
        HomeKeyManager.a().a((BaseActivity) this);
        this.e = AuthManager.h().e();
        this.g = AuthManager.h().f();
        if (this.g != null) {
            this.d = this.g.getString("extra_user_id", "-1");
        }
        SmartHomeDeviceManager.a().a(this.listener);
        if (a(this.g)) {
            StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                public void a() {
                }

                public void c() {
                }

                public void b() {
                    AuthCheckActivity.this.h();
                }

                public void d() {
                    AuthCheckActivity.this.h();
                }

                public void e() {
                    Log.e("AuthManager", "onAllFinished");
                    if (AuthCheckActivity.this.isValid()) {
                        AuthCheckActivity.this.d();
                    }
                    CoreApi.a().a((Context) AuthCheckActivity.this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            Log.e("AuthManager", "onCoreReady");
                            if (AuthCheckActivity.this.isValid()) {
                                AuthCheckActivity.this.a();
                            }
                        }
                    });
                }
            });
        } else {
            h();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HomeManager.t);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.i, intentFilter);
    }

    private void a(Intent intent) {
        Bundle extras;
        if (intent != null && (extras = intent.getExtras()) != null) {
            AuthManager.h().a(((AuthCallBackInfo) extras.getParcelable(AuthConstants.l)).mAuthCallBack);
            AuthManager.h().a(extras);
            AuthManager.h().a(extras.getInt(AuthService.REQUEST_CODE_AUTH));
        }
    }

    private boolean a(Bundle bundle) {
        if (bundle != null) {
            try {
                this.appId = bundle.getString(AuthConstants.f13798a);
                this.b = bundle.getString(AuthConstants.b);
                this.c = bundle.getString(AuthConstants.c);
                this.f = bundle.getInt(AuthService.REQUEST_CODE_AUTH);
                Log.e("AuthManager", "mRequestCode" + this.f);
                AuthManager.c = bundle.getInt(AuthConstants.h, -1);
                AuthManager.d = bundle.getString("sdk_version_name", "-1");
                Miio.b("AuthManager", "mAppSign  " + this.c);
                Miio.b("AuthManager", "mPackageName  " + this.b);
                Miio.b("AuthManager", "appId  " + this.appId);
                StringBuilder sb = new StringBuilder();
                sb.append("callback == null");
                sb.append(AuthManager.h().e() == null);
                sb.append(AuthManager.h().e());
                Log.e("AuthManager", sb.toString());
                if (AuthManager.c <= 8) {
                    if (AuthManager.c >= 4) {
                        if (TextUtils.isEmpty(this.appId) && this.e != null) {
                            this.e.onFail(-104, AuthCode.a(-104));
                            return false;
                        } else if (!AuthManager.h().e(this.b) && this.e != null) {
                            this.e.onFail(-100, AuthCode.a(-100));
                            return false;
                        } else if (!TextUtils.isEmpty(this.c) || this.e == null) {
                            if (this.f != -1) {
                                if (this.f != 4) {
                                    if (this.f == 2) {
                                        if (TextUtils.isEmpty(this.g.getString("device_id"))) {
                                            this.e.onFail(AuthCode.l, AuthCode.a(AuthCode.l));
                                            return false;
                                        }
                                        Miio.b("AuthManager", "did  " + this.g.getString("device_id"));
                                    } else if (this.f == 6 && TextUtils.isEmpty(this.g.getString("device_id"))) {
                                        this.e.onFail(AuthCode.l, AuthCode.a(AuthCode.m));
                                        return false;
                                    }
                                    AuthManager.h().c(this.c);
                                    AuthManager.h().b(this.b);
                                    AuthManager.h().a(this.appId);
                                }
                            }
                            if (this.e != null) {
                                this.e.onFail(-108, AuthCode.a(-108));
                                return false;
                            }
                            AuthManager.h().c(this.c);
                            AuthManager.h().b(this.b);
                            AuthManager.h().a(this.appId);
                        } else {
                            this.e.onFail(-105, AuthCode.a(-105));
                            return false;
                        }
                    }
                }
                this.e.onFail(AuthCode.q, AuthCode.a(AuthCode.q));
                return false;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return true;
            }
        } else if (this.e != null) {
            this.e.onFail(-101, AuthCode.a(-101));
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.h) {
            if (isValid()) {
                d();
                a();
            }
            this.h = false;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!CoreApi.a().q()) {
            e();
            new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AuthCheckActivity.this.f();
                    boolean unused = AuthCheckActivity.this.h = true;
                    dialogInterface.dismiss();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    AuthCheckActivity.this.h();
                }
            }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AuthCheckActivity.this.h();
                }
            }).b((int) R.string.loing_helper_title).d();
            return;
        }
        b();
    }

    private void b() {
        if (!NetworkUtils.c()) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (AuthCheckActivity.this.e != null) {
                        try {
                            AuthCheckActivity.this.e.onFail(AuthCode.s, AuthCode.a(AuthCode.s));
                            AuthCheckActivity.this.h();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    AuthCheckActivity.this.h();
                }
            }, 1000);
            return;
        }
        Miio.b("AuthManager", "initData");
        AuthManager.h().a((AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                Log.e("AuthManager", "updateAuthDefaultInfo  onSuccess");
                if (!AuthManager.h().b(AuthCheckActivity.this.f)) {
                    try {
                        AuthCheckActivity.this.e.onFail(AuthCode.p, AuthCode.a(AuthCode.p));
                        AuthCheckActivity.this.h();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                SmartHomeDeviceManager.a().p();
                HomeManager.a().b();
            }

            public void onFailure(final Error error) {
                AuthCheckActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (AuthCheckActivity.this.e != null) {
                            try {
                                if (error != null) {
                                    if (!TextUtils.isEmpty(error.b())) {
                                        String b2 = error.b();
                                        Log.e("AuthManager", "error msg" + b2);
                                        if (b2.equalsIgnoreCase("unknown app")) {
                                            AuthCheckActivity.this.e.onFail(-104, AuthCode.a(-104));
                                        } else if (b2.equalsIgnoreCase("invalid package name")) {
                                            AuthCheckActivity.this.e.onFail(-100, AuthCode.a(-100));
                                        } else if (b2.equalsIgnoreCase("invalid package sign")) {
                                            AuthCheckActivity.this.e.onFail(-105, AuthCode.a(-105));
                                        } else {
                                            AuthCheckActivity.this.e.onFail(-107, AuthCode.a(-107));
                                        }
                                    }
                                }
                                AuthCheckActivity.this.e.onFail(-107, AuthCode.a(-107));
                                Log.e("AuthManager", "error == null");
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        AuthCheckActivity.this.h();
                    }
                }, 1000);
            }
        }, true);
    }

    private void c() {
        this.mAppIconIV.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(200).setPlaceholderImage(getResources().getDrawable(R.drawable.ic_launcher)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        this.mAppIconIV.setBackgroundResource(R.color.transparent);
        AuthInfo a2 = AuthManager.h().a();
        if (a2 == null) {
            this.mAppIconIV.setImageURI(Uri.parse(""));
            this.mAppNameTV.setText("");
            this.mAppDescTV.setText("");
            this.mConfigTV.setText(R.string.auth_check_config);
        } else {
            this.mAppIconIV.setImageURI(Uri.parse(a2.c));
            this.mAppNameTV.setText(a2.f13800a);
            this.mAppDescTV.setText(a2.b);
            if (TextUtils.isEmpty(a2.e)) {
                this.mConfigTV.setText(R.string.auth_check_config);
            } else {
                this.mConfigTV.setText(a2.e);
            }
        }
        if (TextUtils.equals("-1", this.d) || TextUtils.equals(CoreApi.a().s(), this.d)) {
            this.mConfigTV.setOnClickListener(this.mConfigClickListener);
        } else {
            i();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.show();
    }

    private void e() {
        if (this.mProcessDialog != null) {
            this.mProcessDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        MultiHomeDeviceManager.a().b();
        SmartHomeDeviceManager.a().v();
        SceneManager.x().A();
        SmartHomeDeviceManager.a().p();
        HomeManager.a().b();
        sendBroadcast(new Intent(WifiScanHomelog.c));
        LoginApi.a().a((Context) this, 3, (LoginApi.LoginCallback) null);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.f == 4) {
            Intent intent = new Intent(this, ThirdAuthMainActivity.class);
            intent.putExtras(this.g);
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
    }

    public void onBackPressed() {
        if (this.e != null) {
            try {
                this.e.onFail(-106, AuthCode.a(-106));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        h();
    }

    /* access modifiers changed from: private */
    public void h() {
        AuthManager.h().l();
        finish();
    }

    private void i() {
        Object text = this.mAppNameTV.getText();
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        if (this.l == null) {
            MLAlertDialog.Builder a2 = builder.a((int) R.string.account_error_title);
            Object[] objArr = new Object[1];
            objArr[0] = text == null ? "" : text;
            MLAlertDialog.Builder b2 = a2.b((CharSequence) getString(R.string.account_error_msg, objArr));
            Object[] objArr2 = new Object[1];
            if (text == null) {
                text = "";
            }
            objArr2[0] = text;
            this.l = b2.c((CharSequence) getString(R.string.account_error_btn, objArr2), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AuthCheckActivity.this.h();
                }
            }).d();
        } else if (!this.l.isShowing()) {
            this.l.show();
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        e();
        c();
    }

    /* access modifiers changed from: private */
    public void k() {
        if (isValid()) {
            e();
        }
        if (this.e != null) {
            try {
                this.e.onFail(AuthCode.s, AuthCode.a(AuthCode.s));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        h();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c(this.listener);
        HomeKeyManager.a().b((BaseActivity) this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.i);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a("AuthManager", "onSaveInstanceState" + bundle.getParcelable(AuthConstants.l));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        LogUtil.a("AuthManager", "onRestoreInstanceState" + bundle.getParcelable(AuthConstants.l));
    }
}
