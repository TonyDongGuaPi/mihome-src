package com.xiaomi.smarthome.device;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RemoteRouterMitvApi;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthomedevice.R;
import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import java.text.DecimalFormat;

public class RouterDeviceLoginHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14954a = "RouterDeviceLoginHelper";
    public static final DecimalFormat b = new DecimalFormat("0.#");
    Dialog c = null;
    Context d;
    RouterDevice e;
    AsyncResponseCallback<Void> f;
    MLAlertDialog g = null;
    String h = "";
    View i;
    EditText j;
    ToggleButton k;
    View l;

    public void a(Context context) {
        a();
        this.c = XQProgressDialog.a(context, "", context.getString(R.string.camera_waiting));
    }

    public void a() {
        if (this.c != null) {
            this.c.dismiss();
            this.c = null;
        }
    }

    public void a(Context context, final RouterDevice routerDevice, final AsyncResponseCallback<Void> asyncResponseCallback) {
        this.d = context;
        this.e = routerDevice;
        this.f = asyncResponseCallback;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
        this.i = LayoutInflater.from(context).inflate(R.layout.router_login_account_view, (ViewGroup) null);
        this.j = (EditText) this.i.findViewById(R.id.login_other_account_password_editor);
        this.k = (ToggleButton) this.i.findViewById(R.id.login_other_account_password_toggle);
        this.l = this.i.findViewById(R.id.login_other_account_login_button);
        this.l.setEnabled(false);
        this.k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int selectionStart = RouterDeviceLoginHelper.this.j.getSelectionStart();
                if (z) {
                    RouterDeviceLoginHelper.this.j.setInputType(144);
                } else {
                    RouterDeviceLoginHelper.this.j.setInputType(129);
                }
                RouterDeviceLoginHelper.this.j.setSelection(selectionStart);
            }
        });
        this.j.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(RouterDeviceLoginHelper.this.j.getText().toString())) {
                    RouterDeviceLoginHelper.this.l.setEnabled(false);
                } else {
                    RouterDeviceLoginHelper.this.l.setEnabled(true);
                }
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RouterDeviceLoginHelper.this.b();
            }
        });
        builder.a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                RouterDeviceLoginHelper.this.a();
                routerDevice.setOwner(false);
                RouterDeviceLoginHelper.this.g = null;
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_PERMISSION_DENIED.getCode());
                }
            }
        });
        builder.b(this.i);
        this.g = builder.d();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a(this.d);
        a(this.j.getEditableText().toString(), new AsyncResponseCallback<RemoteRouterMitvApi.RouterToken>() {
            public void a(final RemoteRouterMitvApi.RouterToken routerToken) {
                RouterDeviceLoginHelper.b(routerToken.f16406a, new AsyncResponseCallback<String>() {
                    public void a(String str) {
                        RouterRemoteApi.a().a(CommonApplication.getAppContext(), RouterDeviceLoginHelper.this.e.did, routerToken.b, str, new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                RouterDeviceLoginHelper.this.a();
                                SmartHomeDeviceManager.a().s();
                                Intent intent = new Intent("com.xiaomi.router");
                                intent.putExtra(HomeManager.v, LoginBindBaseWebActivity.SOURCE_BIND);
                                intent.putExtra("userId", CoreApi.a().s());
                                intent.putExtra("sn", RouterDeviceLoginHelper.this.e.token);
                                intent.putExtra("id", RouterDeviceLoginHelper.this.e.did);
                                CommonApplication.getAppContext().sendBroadcast(intent);
                                RouterDeviceLoginHelper.this.e.setOwner(true);
                                RouterDeviceLoginHelper.this.e.userId = CoreApi.a().s();
                                RouterDevice routerDevice = RouterDeviceLoginHelper.this.e;
                                routerDevice.did = "miwifi." + RouterDeviceLoginHelper.this.e.did;
                                MiioManager.a().a((Device) RouterDeviceLoginHelper.this.e);
                                if (RouterDeviceLoginHelper.this.g != null) {
                                    RouterDeviceLoginHelper.this.g.dismiss();
                                }
                                RouterDeviceLoginHelper.this.g = null;
                                if (RouterDeviceLoginHelper.this.f != null) {
                                    RouterDeviceLoginHelper.this.f.a(null);
                                }
                            }

                            public void onFailure(Error error) {
                                RouterDeviceLoginHelper.this.a();
                                Toast.makeText(RouterDeviceLoginHelper.this.d, R.string.router_login_account_bind_error, 0).show();
                            }
                        });
                    }

                    public void a(int i) {
                        RouterDeviceLoginHelper.this.a();
                        Toast.makeText(RouterDeviceLoginHelper.this.d, R.string.router_login_account_get_secret_error, 0).show();
                    }

                    public void a(int i, Object obj) {
                        RouterDeviceLoginHelper.this.a();
                        Toast.makeText(RouterDeviceLoginHelper.this.d, R.string.router_login_account_get_secret_error, 0).show();
                    }
                });
            }

            public void a(int i) {
                RouterDeviceLoginHelper.this.a();
                Toast.makeText(RouterDeviceLoginHelper.this.d, R.string.router_login_account_passwd_error, 0).show();
            }

            public void a(int i, Object obj) {
                RouterDeviceLoginHelper.this.a();
                Toast.makeText(RouterDeviceLoginHelper.this.d, R.string.router_login_account_passwd_error, 0).show();
            }
        });
    }

    public static void a(String str, AsyncResponseCallback<RemoteRouterMitvApi.RouterToken> asyncResponseCallback) {
        RemoteRouterMitvApi.a().a(CommonApplication.getAppContext(), str, asyncResponseCallback);
    }

    public static void b(String str, AsyncResponseCallback<String> asyncResponseCallback) {
        RemoteRouterMitvApi.a().b(CommonApplication.getAppContext(), str, asyncResponseCallback);
    }
}
