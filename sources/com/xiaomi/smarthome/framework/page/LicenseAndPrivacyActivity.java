package com.xiaomi.smarthome.framework.page;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.NetworkUtil;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.BleMeshDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManager;
import com.xiaomi.smarthome.miio.page.usrexpplan.UsrExpPlanActivity;

public class LicenseAndPrivacyActivity extends BaseActivity {
    public static final String ARGS_LICENSE_TITLE = "licenseContenttitle";
    public static final String ARGS_PRIVACY_TITLE = "privacyContenttitle";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Device f16867a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Spanned spanned;
        super.onCreate(bundle);
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        setContentView(R.layout.activity_license_and_privacy_2);
        String stringExtra = getIntent().getStringExtra("did");
        this.f16867a = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.f16867a == null) {
            this.f16867a = SmartHomeDeviceManager.a().l(stringExtra);
            if (this.f16867a == null) {
                finish();
                return;
            }
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LicenseAndPrivacyActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.law_infos);
        final Intent intent = new Intent(this, PluginLicenseActivity.class);
        final String stringExtra2 = getIntent().getStringExtra(DeviceMoreActivity.ARGS_LICENSE_URI);
        final String stringExtra3 = getIntent().getStringExtra(DeviceMoreActivity.ARGS_PRIVACY_URI);
        final String stringExtra4 = getIntent().getStringExtra(DeviceMoreActivity.ARGS_LICENSE_HTML);
        final String stringExtra5 = getIntent().getStringExtra(DeviceMoreActivity.ARGS_PRIVACY_HTML);
        final String stringExtra6 = getIntent().getStringExtra(ARGS_LICENSE_TITLE);
        final String stringExtra7 = getIntent().getStringExtra(ARGS_PRIVACY_TITLE);
        final Spanned spanned2 = (Spanned) getIntent().getCharSequenceExtra(DeviceMoreActivity.ARGS_LICENSE_CONTENT);
        final Spanned spanned3 = (Spanned) getIntent().getCharSequenceExtra(DeviceMoreActivity.ARGS_PRIVACY_CONTENT);
        final int intExtra = getIntent().getIntExtra(DeviceMoreActivity.ARGS_LICENSE_HTML_RES, -1);
        final int intExtra2 = getIntent().getIntExtra(DeviceMoreActivity.ARGS_PRIVACY_HTML_RES, -1);
        if (stringExtra4 != null && stringExtra5 != null) {
            findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra6) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_license) : stringExtra6);
                    intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT, stringExtra4);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
            findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                    intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT, stringExtra5);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
        } else if (stringExtra2 != null && stringExtra3 != null) {
            findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra6) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_license) : stringExtra6);
                    intent.putExtra(PluginLicenseActivity.LICENSE_URI, stringExtra2);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
            findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                    intent.putExtra(PluginLicenseActivity.LICENSE_URI, stringExtra3);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
        } else if (spanned2 != null && spanned3 != null) {
            findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (spanned2 != null) {
                        intent.putExtra("title", TextUtils.isEmpty(stringExtra6) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_license) : stringExtra6);
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, spanned2);
                        LicenseAndPrivacyActivity.this.startActivity(intent);
                    }
                }
            });
            findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (spanned3 != null) {
                        intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, spanned3);
                        LicenseAndPrivacyActivity.this.startActivity(intent);
                    }
                }
            });
        } else if (intExtra <= 0 || intExtra2 <= 0) {
            boolean booleanExtra = getIntent().getBooleanExtra(DeviceMoreActivity.ARGS_USE_DEFAULT_LICENSE, false);
            if (booleanExtra) {
                findViewById(R.id.license).setVisibility(8);
            } else {
                findViewById(R.id.license).setVisibility(0);
                final Spanned spanned4 = (Spanned) getIntent().getCharSequenceExtra(DeviceMoreActivity.ARGS_LICENSE_CONTENT);
                findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (spanned4 != null) {
                            intent.putExtra("title", TextUtils.isEmpty(stringExtra6) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_license) : stringExtra6);
                            intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, spanned4);
                            LicenseAndPrivacyActivity.this.startActivity(intent);
                        }
                    }
                });
            }
            if (booleanExtra) {
                spanned = Html.fromHtml(getString(R.string.user_privacy_new));
            } else {
                spanned = (Spanned) getIntent().getCharSequenceExtra(DeviceMoreActivity.ARGS_PRIVACY_CONTENT);
            }
            final Spanned spanned5 = spanned;
            findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (stringExtra3 != null) {
                        intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                        intent.putExtra(PluginLicenseActivity.LICENSE_URI, stringExtra3);
                        LicenseAndPrivacyActivity.this.startActivity(intent);
                    } else if (spanned5 != null) {
                        intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                        intent.putExtra(PluginLicenseActivity.LICENSE_CONTENT, spanned5);
                        LicenseAndPrivacyActivity.this.startActivity(intent);
                    }
                }
            });
        } else {
            findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra6) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_license) : stringExtra6);
                    intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, intExtra);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
            findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    intent.putExtra("title", TextUtils.isEmpty(stringExtra7) ? LicenseAndPrivacyActivity.this.getString(R.string.device_more_activity_privacy) : stringExtra7);
                    intent.putExtra(PluginLicenseActivity.LICENSE_HTML_CONTENT_RES, intExtra2);
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
        }
        View findViewById = findViewById(R.id.usr_exp_plan_container);
        if (findViewById != null && getIntent().getBooleanExtra("enable_privacy_setting", false)) {
            findViewById.setVisibility(0);
            SwitchButton switchButton = (SwitchButton) findViewById(R.id.right_arrow);
            switchButton.setChecked(AppUsrExpPlanUtil.a(getApplicationContext(), this.f16867a.did));
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    AppUsrExpPlanUtil.a(LicenseAndPrivacyActivity.this.getApplicationContext(), LicenseAndPrivacyActivity.this.f16867a.did, z);
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(LicenseAndPrivacyActivity.this, UsrExpPlanActivity.class);
                    intent.putExtras(LicenseAndPrivacyActivity.this.getIntent());
                    LicenseAndPrivacyActivity.this.startActivity(intent);
                }
            });
        }
        findViewById(R.id.cancel_license_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LicenseAndPrivacyActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        new MLAlertDialog.Builder(this).a((int) R.string.reassure_dialog_title).b((int) R.string.reassure_dialog_content_new).a((int) R.string.reassure_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                LicenseAndPrivacyActivity.this.b();
                TypeListMsgCacheManager.a().a(LicenseAndPrivacyActivity.this.f16867a.did);
            }
        }).b((int) R.string.reassure_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).b().show();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (getIntent().getBooleanExtra(DeviceMoreActivity.ARGS_NEED_DELETE_SERVER_DATA, true)) {
            final XQProgressDialog xQProgressDialog = new XQProgressDialog(this);
            xQProgressDialog.setMessage(getString(R.string.smarthome_deleting));
            xQProgressDialog.setCancelable(false);
            xQProgressDialog.show();
            DeviceApi.getInstance().setUserLicenseConfig(this, this.f16867a.did, "remove", new AsyncCallback<String, Error>() {
                /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
                /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
                /* renamed from: a */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onSuccess(java.lang.String r4) {
                    /*
                        r3 = this;
                        boolean r0 = android.text.TextUtils.isEmpty(r4)
                        r1 = 0
                        if (r0 != 0) goto L_0x0036
                        org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0030 }
                        r0.<init>(r4)     // Catch:{ JSONException -> 0x0030 }
                        java.lang.String r4 = "code"
                        int r4 = r0.optInt(r4)     // Catch:{ JSONException -> 0x0030 }
                        java.lang.String r2 = "result"
                        java.lang.String r0 = r0.optString(r2)     // Catch:{ JSONException -> 0x0030 }
                        if (r4 != 0) goto L_0x0036
                        java.lang.String r4 = "ok"
                        boolean r4 = r4.equals(r0)     // Catch:{ JSONException -> 0x0030 }
                        if (r4 == 0) goto L_0x0036
                        r4 = 1
                        com.xiaomi.smarthome.library.common.dialog.XQProgressDialog r0 = r0     // Catch:{ JSONException -> 0x002e }
                        r0.dismiss()     // Catch:{ JSONException -> 0x002e }
                        com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity r0 = com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity.this     // Catch:{ JSONException -> 0x002e }
                        r0.c()     // Catch:{ JSONException -> 0x002e }
                        goto L_0x0037
                    L_0x002e:
                        r0 = move-exception
                        goto L_0x0032
                    L_0x0030:
                        r0 = move-exception
                        r4 = 0
                    L_0x0032:
                        r0.printStackTrace()
                        goto L_0x0037
                    L_0x0036:
                        r4 = 0
                    L_0x0037:
                        if (r4 != 0) goto L_0x004a
                        com.xiaomi.smarthome.library.common.dialog.XQProgressDialog r4 = r0
                        r4.dismiss()
                        com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity r4 = com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity.this
                        r0 = 2131493489(0x7f0c0271, float:1.861046E38)
                        android.widget.Toast r4 = android.widget.Toast.makeText(r4, r0, r1)
                        r4.show()
                    L_0x004a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity.AnonymousClass17.onSuccess(java.lang.String):void");
                }

                public void onFailure(Error error) {
                    xQProgressDialog.dismiss();
                    if (error.a() == -7) {
                        LicenseAndPrivacyActivity.this.c();
                    } else if (!NetworkUtil.d(LicenseAndPrivacyActivity.this)) {
                        Toast.makeText(LicenseAndPrivacyActivity.this, R.string.network_disable, 0).show();
                    } else {
                        Toast.makeText(LicenseAndPrivacyActivity.this, R.string.bind_error, 0).show();
                    }
                }
            });
            return;
        }
        c();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f16867a instanceof BleDevice) {
            BLEDeviceManager.a((BleDevice) this.f16867a);
        } else if (this.f16867a instanceof BleMeshDevice) {
            BLEDeviceManager.a(this.f16867a);
        }
        Toast.makeText(this, R.string.bind_success, 0).show();
        SmartHomeDeviceManager.a().c(this.f16867a);
        Intent intent = new Intent();
        intent.putExtra("result_data", DeviceMoreActivity.ARGS_RESULT_REMOVE_LICENSE);
        intent.putExtra(Constants.Event.FINISH, true);
        setResult(-1, intent);
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }
}
