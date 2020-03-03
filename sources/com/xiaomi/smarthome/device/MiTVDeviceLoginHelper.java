package com.xiaomi.smarthome.device;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthomedevice.R;
import com.xiaomi.stat.d.q;
import org.json.JSONObject;

public class MiTVDeviceLoginHelper {

    /* renamed from: a  reason: collision with root package name */
    Dialog f14885a = null;
    Context b;
    View c;
    View d;
    View e;
    EditText f;
    EditText g;
    MiTVDevice h;
    AsyncResponseCallback<Void> i;
    MLAlertDialog j = null;
    String k = "";
    EditText l;
    ToggleButton m;
    View n;
    boolean o = false;

    public void a(Context context) {
        a();
        this.f14885a = XQProgressDialog.a(context, "", context.getString(R.string.camera_waiting));
    }

    public void a() {
        if (this.f14885a != null) {
            this.f14885a.dismiss();
            this.f14885a = null;
        }
    }

    public void a(Context context, final MiTVDevice miTVDevice, final AsyncResponseCallback<Void> asyncResponseCallback) {
        String str;
        this.b = context;
        this.h = miTVDevice;
        this.i = asyncResponseCallback;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
        this.c = LayoutInflater.from(context).inflate(R.layout.mitv_login_account_view, (ViewGroup) null);
        this.d = this.c.findViewById(R.id.login_captchacode);
        this.e = this.c.findViewById(R.id.login_vericode);
        this.f = (EditText) this.d.findViewById(R.id.login_captchacode_edit);
        this.g = (EditText) this.e.findViewById(R.id.login_vericode_edit);
        TextView textView = (TextView) this.c.findViewById(R.id.account_name);
        if (miTVDevice.model.contains(q.f23064a)) {
            str = context.getString(R.string.mitv_login_account_tv_info);
        } else {
            str = context.getString(R.string.mitv_login_account_box_info);
        }
        ((TextView) this.c.findViewById(R.id.title)).setText(context.getString(R.string.mitv_login_account_title, new Object[]{CoreApi.a().s()}));
        textView.setText(str);
        this.l = (EditText) this.c.findViewById(R.id.login_other_account_password_editor);
        this.m = (ToggleButton) this.c.findViewById(R.id.login_other_account_password_toggle);
        this.n = this.c.findViewById(R.id.login_other_account_login_button);
        this.n.setEnabled(false);
        this.m.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int selectionStart = MiTVDeviceLoginHelper.this.l.getSelectionStart();
                if (z) {
                    MiTVDeviceLoginHelper.this.l.setInputType(144);
                } else {
                    MiTVDeviceLoginHelper.this.l.setInputType(129);
                }
                MiTVDeviceLoginHelper.this.l.setSelection(selectionStart);
            }
        });
        this.l.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(MiTVDeviceLoginHelper.this.l.getText().toString())) {
                    MiTVDeviceLoginHelper.this.n.setEnabled(false);
                } else {
                    MiTVDeviceLoginHelper.this.n.setEnabled(true);
                }
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiTVDeviceLoginHelper.this.a(false);
            }
        });
        builder.a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                MiTVDeviceLoginHelper.this.a();
                miTVDevice.setOwner(false);
                MiTVDeviceLoginHelper.this.j = null;
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_PERMISSION_DENIED.getCode());
                }
            }
        });
        builder.b(this.c);
        this.j = builder.d();
    }

    public void a(boolean z) {
        this.o = z;
        a(this.b);
        this.h.a(CoreApi.a().s(), this.l.getText().toString(), this.k, this.f.getText().toString(), this.g.getText().toString(), new AsyncResponseCallback<JSONObject>() {
            public void a(JSONObject jSONObject) {
                int i;
                MiTVDeviceLoginHelper.this.a();
                if (jSONObject != null) {
                    String str = null;
                    if ("success".equals(jSONObject.optString("result"))) {
                        MiTVDeviceLoginHelper.this.h.setOwner(true);
                        MiTVDeviceLoginHelper.this.h.userId = CoreApi.a().s();
                        SmartHomeDeviceManager.a().b((Device) MiTVDeviceLoginHelper.this.h);
                        MiioManager.a().a((Device) MiTVDeviceLoginHelper.this.h);
                        if (MiTVDeviceLoginHelper.this.j != null) {
                            MiTVDeviceLoginHelper.this.j.dismiss();
                        }
                        MiTVDeviceLoginHelper.this.j = null;
                        if (MiTVDeviceLoginHelper.this.i != null) {
                            MiTVDeviceLoginHelper.this.i.a(null);
                            return;
                        }
                        return;
                    }
                    switch (jSONObject.optInt("message")) {
                        case 1:
                            i = R.string.mitv_login_account_error_passwd;
                            break;
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                            i = R.string.mitv_login_account_error_server;
                            break;
                        case 18:
                            i = R.string.mitv_login_account_error_invalid_user;
                            break;
                        case 19:
                            i = R.string.mitv_login_account_error_already_login;
                            break;
                        case 20:
                            int optInt = jSONObject.optInt("version");
                            jSONObject.optJSONObject("userData");
                            if (optInt == 1) {
                                MiTVDeviceLoginHelper.this.e.setVisibility(0);
                                break;
                            }
                            break;
                        case 22:
                        case 23:
                            int optInt2 = jSONObject.optInt("version");
                            JSONObject optJSONObject = jSONObject.optJSONObject("userData");
                            if (optJSONObject != null) {
                                str = optJSONObject.optString("captchaurl");
                            }
                            if (optInt2 == 1 && !TextUtils.isEmpty(str)) {
                                MiTVDeviceLoginHelper.this.k = "";
                                MiTVDeviceLoginHelper.this.h.a(str, new AsyncResponseCallback<MiTVDevice.Captchacode>() {
                                    public void a(MiTVDevice.Captchacode captchacode) {
                                        MiTVDeviceLoginHelper.this.d.setVisibility(0);
                                        ImageView imageView = (ImageView) MiTVDeviceLoginHelper.this.d.findViewById(R.id.login_captchacode_image);
                                        imageView.setImageBitmap(captchacode.f14884a);
                                        MiTVDeviceLoginHelper.this.k = captchacode.b;
                                        imageView.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View view) {
                                                MiTVDeviceLoginHelper.this.a(true);
                                            }
                                        });
                                    }

                                    public void a(int i) {
                                        Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.mitv_login_account_captchacode_error_get, 0).show();
                                    }

                                    public void a(int i, Object obj) {
                                        Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.mitv_login_account_captchacode_error_get, 0).show();
                                    }
                                });
                                break;
                            }
                        case 30:
                            i = R.string.mitv_login_account_error_decode;
                            break;
                        case 31:
                            i = R.string.mitv_login_account_error_format;
                            break;
                    }
                    i = 0;
                    if (i > 0 && !MiTVDeviceLoginHelper.this.o) {
                        Toast.makeText(MiTVDeviceLoginHelper.this.b, i, 0).show();
                    }
                    if (MiTVDeviceLoginHelper.this.i != null) {
                        MiTVDeviceLoginHelper.this.i.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                }
            }

            public void a(int i) {
                MiTVDeviceLoginHelper.this.j = null;
                MiTVDeviceLoginHelper.this.a();
                Miio.g("binding network onFailure" + MiTVDeviceLoginHelper.this.h.did);
                MiTVDeviceLoginHelper.this.h.setOwner(false);
                if (i == ErrorCode.ERROR_NO_METHOD.getCode()) {
                    Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.button_bind_failed_old_version, 0).show();
                } else {
                    Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.button_bind_failed, 0).show();
                }
                if (MiTVDeviceLoginHelper.this.i != null) {
                    MiTVDeviceLoginHelper.this.i.a(i);
                }
            }

            public void a(int i, Object obj) {
                MiTVDeviceLoginHelper.this.j = null;
                MiTVDeviceLoginHelper.this.a();
                Miio.g("binding network onFailure" + MiTVDeviceLoginHelper.this.h.did);
                MiTVDeviceLoginHelper.this.h.setOwner(false);
                if (i == ErrorCode.ERROR_NO_METHOD.getCode()) {
                    Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.button_bind_failed_old_version, 0).show();
                } else {
                    Toast.makeText(MiTVDeviceLoginHelper.this.b, R.string.button_bind_failed, 0).show();
                }
                if (MiTVDeviceLoginHelper.this.i != null) {
                    MiTVDeviceLoginHelper.this.i.a(i);
                }
            }
        });
    }
}
