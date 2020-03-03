package com.miuipub.internal.webkit;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.miuipub.internal.hybrid.provider.AbsWebView;
import miuipub.hybrid.R;

public class DefaultDeviceAccountLogin extends DeviceAccountLogin {
    private static final String c = "dialog";
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 500;
    /* access modifiers changed from: private */
    public AbsWebView g;
    private Handler h = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                DefaultDeviceAccountLogin.this.e();
            } else if (message.what == 1) {
                DefaultDeviceAccountLogin.this.f();
                DefaultDeviceAccountLogin.this.g.a(0);
            }
        }
    };
    private ProgressDialogFragment i;

    public DefaultDeviceAccountLogin(Activity activity, AbsWebView absWebView) {
        super(activity);
        this.g = absWebView;
    }

    /* access modifiers changed from: private */
    public void e() {
        f();
        this.i = new ProgressDialogFragment();
        try {
            this.i.show(this.f8314a.getFragmentManager(), c);
        } catch (IllegalStateException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.h.removeMessages(0);
        if (this.i != null && this.i.isAdded()) {
            this.i.dismissAllowingStateLoss();
        }
        this.i = null;
    }

    public void a() {
        this.h.sendEmptyMessageDelayed(0, 500);
    }

    public void a(String str) {
        this.g.a(str);
    }

    public void b() {
        f();
        this.g.a(0);
        Toast.makeText(this.f8314a, R.string.web_sso_login_fail, 0).show();
    }

    public void c() {
        f();
        this.g.a(0);
    }

    public void d() {
        this.h.sendEmptyMessageDelayed(1, 500);
    }

    public static class ProgressDialogFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle bundle) {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.web_sso_login_message));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            return progressDialog;
        }
    }
}
