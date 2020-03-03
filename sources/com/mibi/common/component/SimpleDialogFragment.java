package com.mibi.common.component;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import miuipub.app.ProgressDialog;

public class SimpleDialogFragment extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7490a = "msg_res_id";
    public static final String b = "cancelable";
    public static final String c = "title";
    public static final String d = "type";
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = -1;
    private static final String h = "SimpleDialogFragment";
    private String i;
    private boolean j = true;
    private String k;
    private int l;
    private int m = -1;
    private int n = -1;
    private String o;
    private String p;
    private DialogInterface.OnClickListener q;
    private DialogInterface.OnClickListener r;
    private DialogInterface.OnDismissListener s;
    private DialogInterface.OnCancelListener t;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a(getArguments())) {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.l = bundle.getInt("type");
        this.i = bundle.getString("msg_res_id");
        this.k = bundle.getString("title");
        this.j = bundle.getBoolean("cancelable", true);
        return true;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        switch (this.l) {
            case 1:
                AlertDialog.Builder title = new AlertDialog.Builder(getActivity()).setMessage(this.i).setCancelable(this.j).setTitle(this.k);
                Log.v(h, "SimpleDialogFragment.onCreateDialog, mTitle=" + this.k + ", mMessage=" + this.i + ",positiveText=" + this.o + ", positiveTextRes=" + this.m + ",negativeText=" + this.p + ",negativeTextRes=" + this.n);
                if (!TextUtils.isEmpty(this.o)) {
                    title.setPositiveButton(this.o, this.q);
                } else if (this.m != -1) {
                    title.setPositiveButton(this.m, this.q);
                }
                if (!TextUtils.isEmpty(this.p)) {
                    title.setNegativeButton(this.p, this.r);
                } else if (this.n != -1) {
                    title.setNegativeButton(this.n, this.r);
                }
                return title.create();
            case 2:
                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.a((CharSequence) this.i);
                progressDialog.setCancelable(this.j);
                return progressDialog;
            default:
                throw new IllegalStateException("unknown dialog type:" + this.l);
        }
    }

    public void a(DialogInterface.OnDismissListener onDismissListener) {
        this.s = onDismissListener;
    }

    public void a(DialogInterface.OnCancelListener onCancelListener) {
        this.t = onCancelListener;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.s != null) {
            this.s.onDismiss(dialogInterface);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        if (this.t != null) {
            this.t.onCancel(dialogInterface);
        }
    }

    public void a(int i2, DialogInterface.OnClickListener onClickListener) {
        this.m = i2;
        this.o = null;
        this.q = onClickListener;
    }

    public void a(String str, DialogInterface.OnClickListener onClickListener) {
        this.o = str;
        this.m = -1;
        this.q = onClickListener;
    }

    public void b(int i2, DialogInterface.OnClickListener onClickListener) {
        this.n = i2;
        this.p = null;
        this.r = onClickListener;
    }

    public void b(String str, DialogInterface.OnClickListener onClickListener) {
        this.p = str;
        this.n = -1;
        this.r = onClickListener;
    }

    public static class SimpleDialogFragmentBuilder {

        /* renamed from: a  reason: collision with root package name */
        private String f7491a;
        private String b;
        private boolean c = true;
        private boolean d;
        private int e;

        public SimpleDialogFragmentBuilder(int i) {
            this.e = i;
        }

        public SimpleDialogFragmentBuilder a(String str) {
            this.b = str;
            return this;
        }

        public SimpleDialogFragmentBuilder a(boolean z) {
            this.c = z;
            return this;
        }

        public SimpleDialogFragmentBuilder b(String str) {
            this.f7491a = str;
            return this;
        }

        public SimpleDialogFragment a() {
            if (!this.d) {
                this.d = true;
                SimpleDialogFragment simpleDialogFragment = new SimpleDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", this.f7491a);
                bundle.putString("msg_res_id", this.b);
                bundle.putBoolean("cancelable", this.c);
                bundle.putInt("type", this.e);
                simpleDialogFragment.setArguments(bundle);
                return simpleDialogFragment;
            }
            throw new IllegalStateException("dialog has been created");
        }
    }
}
