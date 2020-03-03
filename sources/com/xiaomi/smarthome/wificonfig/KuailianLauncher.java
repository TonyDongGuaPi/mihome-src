package com.xiaomi.smarthome.wificonfig;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class KuailianLauncher extends Activity {
    public static final int LOGIN_REQUEST = 1;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f22896a = 1;
    private int b = 50;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == KuailianLauncher.this.f22896a) {
                OpenApi.a(KuailianLauncher.this.mInfo.c, KuailianLauncher.this.mInfo.d, true, 0);
                KuailianLauncher.this.finish();
            }
        }
    };
    Info mInfo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.scene_launcher_layout);
        if (ApiConst.o.equals(getIntent().getAction())) {
            overridePendingTransition(R.anim.v5_dialog_enter, R.anim.v5_dialog_exit);
        }
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                KuailianLauncher.this.finish();
            }

            public void d() {
                KuailianLauncher.this.finish();
            }

            public void e() {
                KuailianLauncher.this.doWork();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public void doWork() {
        if (ApiConst.o.equals(getIntent().getAction())) {
            openKuailianPage();
        }
    }

    /* access modifiers changed from: package-private */
    public void openKuailianPage() {
        Object obj;
        Intent intent = getIntent();
        String str = "";
        Bundle extras = getIntent().getExtras();
        if (!(extras == null || (obj = extras.get("user_id")) == null)) {
            str = obj.toString();
            if ("0".equals(str)) {
                str = "";
            }
        }
        String stringExtra = intent.getStringExtra("bssid");
        if (!TextUtils.isEmpty(stringExtra)) {
            stringExtra = stringExtra.toLowerCase();
        }
        String stringExtra2 = intent.getStringExtra(ApiConst.f);
        String stringExtra3 = intent.getStringExtra("password");
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str)) {
            if (!CoreApi.a().q()) {
                goMainActivity();
                return;
            } else if (TextUtils.isEmpty(str) || !CoreApi.a().q() || CoreApi.a().s().equals(str)) {
                Intent intent2 = new Intent(this, ChooseDeviceActivity.class);
                intent2.putExtra("model", stringExtra2);
                intent2.putExtra("bssid", stringExtra);
                if (stringExtra3 != null) {
                    intent2.putExtra("password", stringExtra3);
                }
                startActivity(intent2);
                finish();
            } else {
                new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.open_device_account_erro)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginManager.a().logout(new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                KuailianLauncher.this.goMainActivity();
                            }

                            public void onFailure(Error error) {
                                KuailianLauncher.this.openDevicePageError(KuailianLauncher.this.getString(R.string.open_device_logout_error));
                            }
                        });
                    }
                }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        KuailianLauncher.this.backToLauncher();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        KuailianLauncher.this.backToLauncher();
                    }
                }).d();
                return;
            }
        }
        openDevicePageError(getString(R.string.open_device_not_found_erro_1));
    }

    /* access modifiers changed from: package-private */
    public void openDevicePageError(String str) {
        new MLAlertDialog.Builder(this).b((CharSequence) str).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                KuailianLauncher.this.backToLauncher();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                KuailianLauncher.this.backToLauncher();
            }
        }).d();
    }

    /* access modifiers changed from: package-private */
    public void backToLauncher() {
        finish();
        overridePendingTransition(R.anim.v5_dialog_enter, R.anim.v5_dialog_exit);
    }

    /* access modifiers changed from: package-private */
    public void goMainActivity() {
        Intent intent = new Intent(this, SmartHomeMainActivity.class);
        intent.putExtras(getIntent());
        intent.setFlags(268468224);
        intent.setAction(ApiConst.f16684a);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        finish();
    }

    private class Info {

        /* renamed from: a  reason: collision with root package name */
        String f22905a;
        /* access modifiers changed from: private */
        public Class<?> c;
        /* access modifiers changed from: private */
        public Bundle d;

        private Info() {
        }
    }
}
