package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;

public class EMIConfirmDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f7212a;

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7217a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7217a = builder;
            builder.tvTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", CustomTextView.class);
            builder.tvContent = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'tvContent'", CustomTextView.class);
            builder.btnNo = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_no, "field 'btnNo'", CustomButtonView.class);
            builder.btnYes = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_yes, "field 'btnYes'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7217a;
            if (builder != null) {
                this.f7217a = null;
                builder.tvTitle = null;
                builder.tvContent = null;
                builder.btnNo = null;
                builder.btnYes = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public EMIConfirmDialog(Context context) {
        super(context);
    }

    public EMIConfirmDialog(Context context, int i) {
        super(context, i);
        this.f7212a = context;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7213a;
        private boolean b = true;
        @BindView(2131493033)
        CustomButtonView btnNo;
        @BindView(2131493042)
        CustomButtonView btnYes;
        private String c;
        private String d;
        private String e;
        private String f;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener g;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener h;
        @BindView(2131494190)
        CustomTextView tvContent;
        @BindView(2131494276)
        CustomTextView tvTitle;

        public Builder(Context context) {
            this.f7213a = context;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder a(String str, DialogInterface.OnClickListener onClickListener) {
            this.f = str;
            this.g = onClickListener;
            return this;
        }

        public Builder b(String str, DialogInterface.OnClickListener onClickListener) {
            this.e = str;
            this.h = onClickListener;
            return this;
        }

        public Builder a(Boolean bool) {
            this.b = bool.booleanValue();
            return this;
        }

        public EMIConfirmDialog a() {
            final EMIConfirmDialog eMIConfirmDialog = new EMIConfirmDialog(this.f7213a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7213a.getSystemService("layout_inflater")).inflate(R.layout.shop_emi_confirm_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            eMIConfirmDialog.setCanceledOnTouchOutside(this.b);
            if (TextUtils.isEmpty(this.c)) {
                this.tvTitle.setVisibility(8);
            } else {
                this.tvTitle.setVisibility(0);
                this.tvTitle.setText(this.c);
            }
            if (TextUtils.isEmpty(this.d)) {
                this.tvContent.setVisibility(8);
            } else {
                this.tvContent.setVisibility(0);
                this.tvContent.setText(this.d);
            }
            if (this.e != null) {
                this.btnNo.setText(this.e);
            }
            if (this.f != null) {
                this.btnYes.setText(this.f);
            }
            this.btnNo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    eMIConfirmDialog.dismiss();
                }
            });
            if (this.g != null) {
                this.btnYes.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.g.onClick(eMIConfirmDialog, -1);
                    }
                });
            }
            if (this.h != null) {
                this.btnNo.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.h.onClick(eMIConfirmDialog, -2);
                    }
                });
            }
            eMIConfirmDialog.setContentView(inflate);
            return eMIConfirmDialog;
        }
    }

    public void a() {
        if (BaseActivity.isActivityAlive(this.f7212a)) {
            show();
        }
    }

    public void b() {
        if (BaseActivity.isActivityAlive(this.f7212a)) {
            dismiss();
        }
    }
}
