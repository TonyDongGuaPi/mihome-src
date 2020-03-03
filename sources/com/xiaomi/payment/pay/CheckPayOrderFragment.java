package com.xiaomi.payment.pay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.pay.contract.CheckPayOrderContract;
import com.xiaomi.payment.pay.presenter.CheckPayOrderPresenter;
import com.xiaomi.payment.platform.R;
import miuipub.app.ProgressDialog;

public class CheckPayOrderFragment extends BaseFragment implements AutoSave, CheckPayOrderContract.View {
    private static final String t = "CheckPayOrderFragment";
    private static final int u = 1;
    @AutoSave.AutoSavable
    private long v;
    private boolean w;
    private ProgressDialog x;
    private boolean y = false;

    public void a(int i, boolean z) {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        if (bundle == null) {
            ((CheckPayOrderPresenter) H_()).g();
        }
        K();
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.w = bundle.getBoolean("payment_is_no_account");
    }

    public IPresenter onCreatePresenter() {
        return new CheckPayOrderPresenter();
    }

    public void a(String str, Bundle bundle) {
        L();
        this.v = bundle.getLong("price");
        a((Class) bundle.getSerializable("fragment"), bundle, 1, str, (Class) bundle.getSerializable("activity"));
    }

    public void a(int i, String str, Throwable th) {
        Activity activity = getActivity();
        Toast.makeText(activity, str + ":" + i, 0).show();
        b(i, str, (Bundle) null);
        c(i, str, (Bundle) null);
        E();
    }

    public void a(int i, String str, Bundle bundle) {
        Activity activity = getActivity();
        Toast.makeText(activity, str + ":" + i, 0).show();
        b(i, str, bundle);
        c(i, str, bundle);
        E();
    }

    public void a(int i, String str) {
        PrivacyManager.a(p(), false);
        MibiPrivacyUtils.a(getActivity(), new MibiPrivacyUtils.PrivacyCallBack() {
            public void a() {
                CheckPayOrderFragment.this.g((Bundle) null);
                CheckPayOrderFragment.this.h((Bundle) null);
                CheckPayOrderFragment.this.E();
            }
        });
    }

    private void K() {
        if (isAdded()) {
            if (this.x == null) {
                this.x = new ProgressDialog(getActivity());
                this.x.a((CharSequence) getString(R.string.mibi_progress_creating));
                this.x.setCanceledOnTouchOutside(false);
                this.x.setCancelable(true);
                this.x.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        CheckPayOrderFragment.this.b(4, "cancelled by user", (Bundle) null);
                        CheckPayOrderFragment.this.c(0, "cancelled by user", (Bundle) null);
                        CheckPayOrderFragment.this.E();
                    }
                });
            }
            this.x.show();
        }
    }

    private void L() {
        if (this.x != null) {
            this.x.dismiss();
            this.x = null;
        }
    }

    public void a(int i, Bundle bundle) {
        super.a(i, bundle);
        if (i == 10000) {
            b(4, "cancelled by user", (Bundle) null);
            c(0, "cancelled by user", (Bundle) null);
        }
        E();
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        Log.v(t, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        if (i == 1) {
            if (i2 == 0) {
                b(4, "cancelled by user", (Bundle) null);
                c(0, "cancelled by user", (Bundle) null);
                E();
            } else if (i2 == 1101) {
                g(bundle);
                h(bundle);
                E();
            } else if (i2 == 1100) {
                e(bundle);
                f(bundle);
                E();
            } else if (i2 == 1102 || i2 == 2001) {
                ((CheckPayOrderPresenter) H_()).n();
            } else if (i2 == 1105) {
                e(bundle);
                f(bundle);
                E();
            } else if (i2 == 1106) {
                g(bundle);
                h(bundle);
                E();
            } else {
                b(4, "cancelled by user", (Bundle) null);
                c(0, "cancelled by user", (Bundle) null);
                E();
            }
        }
    }

    private void e(Bundle bundle) {
        if (!this.y) {
            this.y = true;
            if (this.b.j()) {
                Bundle bundle2 = new Bundle();
                if (bundle != null) {
                    bundle2.putString("payment_payment_result", bundle.getString("result"));
                }
                this.b.a(bundle2);
                return;
            }
            g(true);
        }
    }

    private void f(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("code", -1);
        String str = "";
        if (bundle != null) {
            str = bundle.getString("result");
        }
        bundle2.putString("result", str);
        b(-1, bundle2);
    }

    /* access modifiers changed from: private */
    public void g(Bundle bundle) {
        String str = "";
        Bundle bundle2 = new Bundle();
        int i = 4;
        if (bundle != null) {
            i = bundle.getInt(MibiConstants.da, 4);
            str = bundle.getString(MibiConstants.db);
            bundle2.putString("payment_payment_result", bundle.getString("result"));
        }
        b(i, str, bundle2);
    }

    /* access modifiers changed from: private */
    public void b(int i, String str, Bundle bundle) {
        if (!this.y) {
            this.y = true;
            Session p = p();
            if (p.j()) {
                p.a(i, str, bundle);
            } else {
                g(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void h(Bundle bundle) {
        String str = "";
        int i = 17;
        if (bundle != null) {
            i = bundle.getInt(MibiConstants.da, 17);
            str = bundle.getString(MibiConstants.db);
        }
        c(i, str, bundle);
    }

    /* access modifiers changed from: private */
    public void c(int i, String str, Bundle bundle) {
        String str2 = "";
        if (bundle != null) {
            str2 = bundle.getString("result");
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("code", i);
        bundle2.putString("message", str);
        bundle2.putString("result", str2);
        b(i, bundle2);
    }

    private void g(boolean z) {
        if (z) {
            if (!this.w) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setPackage(getActivity().getPackageName());
                intent.setData(Uri.parse("https://app.mibi.xiaomi.com?mibi.billRecord"));
                PendingIntent activity = PendingIntent.getActivity(getActivity(), Utils.a(), intent, 134217728);
                Utils.a(getActivity(), getActivity().getResources().getString(R.string.mibi_mili_center), getActivity().getResources().getString(R.string.mibi_progress_success_pay_title, new Object[]{Utils.a(this.v)}), activity, (PendingIntent) null);
            }
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.mibi_progress_success_pay_title, new Object[]{Utils.a(this.v)}), 0).show();
            return;
        }
        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.mibi_progress_error_pay_title), 0).show();
    }

    public void o() {
        L();
        super.o();
    }
}
