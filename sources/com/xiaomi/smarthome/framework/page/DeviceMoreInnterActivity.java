package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.TimeZoneApi;
import com.xiaomi.smarthome.framework.api.model.MyTimeZone;
import com.xiaomi.smarthome.framework.page.verify.SecuritySettingActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.TimeZone;

public class DeviceMoreInnterActivity extends BaseActivity {
    protected Device mDevice;
    TextView mTVtimezone;
    View mTimezone;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("did");
        this.mDevice = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.mDevice == null) {
            this.mDevice = SmartHomeDeviceManager.a().l(stringExtra);
            if (this.mDevice == null) {
                finish();
                return;
            }
        }
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.device_more_inner_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceMoreInnterActivity.this.onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miui_earphone_more_setting);
        View findViewById = findViewById(R.id.security_setting);
        if (!visableSecurity(intent) || !this.mDevice.isOwner() || this.mDevice.isSubDevice()) {
            findViewById.setVisibility(8);
        } else {
            if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
                ((TextView) findViewById(R.id.security_setting_title)).setText(R.string.ble_secure_pin_device_more_title);
            }
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SecuritySettingActivity.startActivity(DeviceMoreInnterActivity.this, DeviceMoreInnterActivity.this.mDevice.did, "");
                }
            });
        }
        View findViewById2 = findViewById(R.id.network_info);
        if (!visableNetwork(this.mDevice)) {
            findViewById2.setVisibility(8);
        }
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeviceMoreInnterActivity.this, DeviceNetworkInfoActivity.class);
                intent.putExtra("did", DeviceMoreInnterActivity.this.mDevice.did);
                DeviceMoreInnterActivity.this.startActivity(intent);
            }
        });
        this.mTimezone = findViewById(R.id.timezone);
        this.mTVtimezone = (TextView) findViewById(R.id.text_timezone);
        if (!visableTimezone(intent)) {
            this.mTimezone.setVisibility(8);
        } else {
            this.mTimezone.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(DeviceMoreInnterActivity.this, TimezoneActivity.class);
                    intent.putExtra("extra_device_did", DeviceMoreInnterActivity.this.mDevice.did);
                    DeviceMoreInnterActivity.this.startActivityForResult(intent, 3);
                }
            });
            RemoteFamilyApi.a().c(this.mDevice.did, (AsyncCallback<MyTimeZone, Error>) new AsyncCallback<MyTimeZone, Error>() {
                /* renamed from: a */
                public void onSuccess(MyTimeZone myTimeZone) {
                    if (myTimeZone != null) {
                        DeviceMoreInnterActivity.this.mTVtimezone.setText(myTimeZone.a() + " " + myTimeZone.b());
                    }
                }

                public void onFailure(Error error) {
                    Log.i("zc", "onFailure");
                }
            });
        }
        View findViewById3 = findViewById(R.id.feedback);
        if (!visableFeedback(this.mDevice)) {
            findViewById3.setVisibility(8);
        }
        findViewById3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeviceMoreInnterActivity.this, FeedbackActivity.class);
                intent.putExtra("extra_device_did", DeviceMoreInnterActivity.this.mDevice.did);
                DeviceMoreInnterActivity.this.startActivity(intent);
            }
        });
        findViewById3.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (DeviceMoreInnterActivity.this.mDevice.did == null || ServerCompact.g((Context) DeviceMoreInnterActivity.this)) {
                    return true;
                }
                new MLAlertDialog.Builder(DeviceMoreInnterActivity.this).a((CharSequence) DeviceMoreInnterActivity.this.mDevice.did).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).d();
                return true;
            }
        });
        View findViewById4 = findViewById(R.id.short_cut);
        findViewById4.setVisibility(8);
        if (visableShortcut(intent)) {
            findViewById4.setVisibility(0);
            findViewById4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    final XQProgressDialog a2 = XQProgressDialog.a(DeviceMoreInnterActivity.this, (CharSequence) null, DeviceMoreInnterActivity.this.getString(R.string.creating));
                    DeviceShortcutUtils.a(false, DeviceMoreInnterActivity.this.mDevice, (Intent) null, "device_more", (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                        public void a(Void voidR) {
                            a2.dismiss();
                        }

                        public void a(int i) {
                            if (i == -1) {
                                if (Build.VERSION.SDK_INT < 23 || !DeviceMoreInnterActivity.this.shouldShowRequestPermissionRationale("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                                    ToastUtil.a((int) R.string.permission_tips_denied_msg);
                                } else {
                                    DeviceMoreInnterActivity.this.requestPermissions(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, 1);
                                }
                            }
                            a2.dismiss();
                        }

                        public void a(int i, Object obj) {
                            a2.dismiss();
                        }
                    });
                }
            });
        }
    }

    public static boolean visableShortcut(Intent intent) {
        return intent.getBooleanExtra(DeviceMoreActivity.ARGS_SHORTCUT_ENABLE, true);
    }

    public static boolean visableSecurity(Intent intent) {
        return intent.getBooleanExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, false);
    }

    public static boolean visableTimezone(Intent intent) {
        return intent.getBooleanExtra(DeviceMoreActivity.ARGS_TIMEZONE_ENABLE, true);
    }

    public static boolean visableNetwork(Device device) {
        return !device.isSubDevice() && !device.isVirtualDevice() && (device instanceof MiioDeviceV2) && !TextUtils.isEmpty(device.bssid) && !TextUtils.isEmpty(device.ssid);
    }

    public static boolean visableFeedback(Device device) {
        return !device.model.startsWith("yeelink.light");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3 && i2 == 4 && intent != null) {
            TimeZone timeZone = (TimeZone) intent.getSerializableExtra(TimezoneActivity.KEY_TIMEZONE);
            String a2 = TimeZoneApi.a(timeZone.getID(), this);
            String displayName = timeZone.getDisplayName(false, 0);
            TextView textView = this.mTVtimezone;
            textView.setText(a2 + " " + displayName);
        }
        if (intent != null && intent.getBooleanExtra(Constants.Event.FINISH, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
