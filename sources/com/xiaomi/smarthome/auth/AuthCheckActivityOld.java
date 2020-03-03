package com.xiaomi.smarthome.auth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;

public class AuthCheckActivityOld extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13790a = "AuthCheckActivity";
    String appId;
    private String b;
    private String c;
    private IAuthCallBack d;
    private int e = -1;
    private Bundle f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.d = AuthManager.h().e();
        this.f = intent.getExtras();
        if (a(this.f)) {
            StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                public void a() {
                }

                public void c() {
                }

                public void b() {
                    AuthCheckActivityOld.this.finish();
                }

                public void d() {
                    AuthCheckActivityOld.this.finish();
                }

                public void e() {
                    CoreApi.a().a((Context) AuthCheckActivityOld.this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            AuthCheckActivityOld.this.a();
                        }
                    });
                }
            });
        } else {
            finish();
        }
    }

    private boolean a(Bundle bundle) {
        boolean z = false;
        boolean z2 = true;
        if (bundle != null) {
            try {
                this.appId = bundle.getString(AuthConstants.f13798a);
                this.b = bundle.getString(AuthConstants.b);
                this.c = bundle.getString(AuthConstants.c);
                this.e = bundle.getInt(AuthService.REQUEST_CODE_AUTH);
                if (TextUtils.isEmpty(this.appId) && this.d != null) {
                    this.d.onFail(-104, AuthCode.a(-104));
                    z2 = false;
                }
                if (!AuthManager.h().e(this.b) && this.d != null) {
                    this.d.onFail(-100, AuthCode.a(-100));
                    z2 = false;
                }
                if (!TextUtils.isEmpty(this.c) || this.d == null) {
                    z = z2;
                } else {
                    this.d.onFail(-105, AuthCode.a(-105));
                }
                try {
                    AuthManager.h().c(this.c);
                    AuthManager.h().b(this.b);
                    AuthManager.h().a(this.appId);
                    return z;
                } catch (RemoteException e2) {
                    e = e2;
                    z2 = z;
                }
            } catch (RemoteException e3) {
                e = e3;
                e.printStackTrace();
                return z2;
            }
        } else if (this.d == null) {
            return true;
        } else {
            this.d.onFail(-101, AuthCode.a(-101));
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!CoreApi.a().q()) {
            new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AuthCheckActivityOld.this.b();
                    dialogInterface.dismiss();
                    AuthCheckActivityOld.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    AuthCheckActivityOld.this.finish();
                }
            }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    AuthCheckActivityOld.this.finish();
                }
            }).b((int) R.string.loing_helper_title).d();
        } else {
            c();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        MultiHomeDeviceManager.a().b();
        SmartHomeDeviceManager.a().v();
        SceneManager.x().A();
        SmartHomeDeviceManager.a().p();
        sendBroadcast(new Intent(WifiScanHomelog.c));
        LoginApi.a().a((Context) this, 3, (LoginApi.LoginCallback) null);
    }

    private void c() {
        if (this.e == 4) {
            Intent intent = new Intent(this, ThirdAuthMainActivity.class);
            intent.putExtras(this.f);
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
