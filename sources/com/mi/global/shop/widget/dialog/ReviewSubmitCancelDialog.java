package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;

public class ReviewSubmitCancelDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7224a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7224a = builder;
            builder.btnCancel = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_cancel, "field 'btnCancel'", CustomButtonView.class);
            builder.btnLeave = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_leave, "field 'btnLeave'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7224a;
            if (builder != null) {
                this.f7224a = null;
                builder.btnCancel = null;
                builder.btnLeave = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ReviewSubmitCancelDialog(Context context) {
        super(context);
    }

    public ReviewSubmitCancelDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7221a;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener b;
        @BindView(2131493021)
        CustomButtonView btnCancel;
        @BindView(2131493030)
        CustomButtonView btnLeave;

        public Builder(Context context) {
            this.f7221a = context;
        }

        public Builder a(DialogInterface.OnClickListener onClickListener) {
            this.b = onClickListener;
            return this;
        }

        public ReviewSubmitCancelDialog a() {
            final ReviewSubmitCancelDialog reviewSubmitCancelDialog = new ReviewSubmitCancelDialog(this.f7221a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7221a.getSystemService("layout_inflater")).inflate(R.layout.shop_review_submit_cancel_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            reviewSubmitCancelDialog.setCanceledOnTouchOutside(true);
            this.btnCancel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    reviewSubmitCancelDialog.dismiss();
                }
            });
            if (this.b != null) {
                this.btnLeave.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.b.onClick(reviewSubmitCancelDialog, -2);
                    }
                });
            }
            reviewSubmitCancelDialog.setContentView(inflate);
            return reviewSubmitCancelDialog;
        }
    }
}
