package com.mibi.common.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import com.mibi.common.R;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.StepActivity;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.SimpleDialogFragment;
import miuipub.app.ProgressDialog;

public abstract class BaseProcessFragment extends BaseFragment {
    public static final int u = 10000;
    protected String t;
    private ProgressDialog v;
    private DialogInterface.OnClickListener w = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            BaseProcessFragment.this.c(10000);
            BaseProcessFragment.this.c(BaseProcessFragment.this.t);
        }
    };

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.t = bundle.getString("processId");
    }

    public void a(Class<? extends StepFragment> cls, Bundle bundle, int i, String str, Class<? extends StepActivity> cls2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        bundle2.putString("processId", this.t);
        super.a(cls, bundle2, i, str, cls2);
    }

    public void K() {
        g(true);
    }

    public void g(boolean z) {
        a(this.t, z);
    }

    /* access modifiers changed from: protected */
    public void L() {
        if (!A_()) {
            SimpleDialogFragment a2 = new SimpleDialogFragment.SimpleDialogFragmentBuilder(1).a(getString(R.string.mibi_process_expired)).a(false).a();
            a2.a(17039370, this.w);
            a2.show(getFragmentManager(), "process error");
        }
    }

    /* access modifiers changed from: protected */
    public void d(String str) {
        if (!A_()) {
            SimpleDialogFragment a2 = new SimpleDialogFragment.SimpleDialogFragmentBuilder(1).a(str).a(true).a();
            a2.a(17039370, this.w);
            a2.b(17039360, (DialogInterface.OnClickListener) null);
            a2.show(getFragmentManager(), "process error");
        }
    }

    /* access modifiers changed from: protected */
    public void b(String str, boolean z) {
        a(str, z, (DialogInterface.OnCancelListener) null);
    }

    /* access modifiers changed from: protected */
    public void a(String str, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        if (isAdded()) {
            if (this.v == null) {
                this.v = new ProgressDialog(getActivity());
            }
            this.v.a((CharSequence) str);
            this.v.setCanceledOnTouchOutside(false);
            this.v.setCancelable(z);
            if (z) {
                this.v.setOnCancelListener(onCancelListener);
            }
            this.v.show();
        }
    }

    /* access modifiers changed from: protected */
    public void M() {
        if (this.v != null) {
            this.v.dismiss();
            this.v = null;
        }
    }
}
