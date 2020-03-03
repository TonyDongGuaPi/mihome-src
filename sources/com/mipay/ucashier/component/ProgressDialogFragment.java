package com.mipay.ucashier.component;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.mipay.ucashier.R;

public class ProgressDialogFragment extends DialogFragment {
    public static final String ARG_CANCELABLE = "cancelable";
    public static final String ARG_MESSAGE = "msg_res_id";

    /* renamed from: a  reason: collision with root package name */
    private boolean f8179a = true;
    private String b;

    public static class ProgressDialogFragmentBuilder {

        /* renamed from: a  reason: collision with root package name */
        private String f8180a;
        private boolean b = true;
        private boolean c;

        public ProgressDialogFragment create() {
            if (!this.c) {
                this.c = true;
                ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("msg_res_id", this.f8180a);
                bundle.putBoolean("cancelable", this.b);
                progressDialogFragment.setArguments(bundle);
                return progressDialogFragment;
            }
            throw new IllegalStateException("dialog has been created");
        }

        public ProgressDialogFragmentBuilder setCancelable(boolean z) {
            this.b = z;
            return this;
        }

        public ProgressDialogFragmentBuilder setMessage(String str) {
            this.f8180a = str;
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        this.b = bundle.getString("msg_res_id");
        this.f8179a = bundle.getBoolean("cancelable", true);
        return true;
    }

    public boolean isShowing() {
        Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().setCancelable(this.f8179a);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a(getArguments())) {
            throw new IllegalArgumentException();
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = new Dialog(getActivity(), R.style.UCashier_Theme_Dialog);
        View inflate = dialog.getLayoutInflater().inflate(R.layout.ucashier_progress, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.message)).setText(this.b);
        dialog.setContentView(inflate);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        return dialog;
    }
}
