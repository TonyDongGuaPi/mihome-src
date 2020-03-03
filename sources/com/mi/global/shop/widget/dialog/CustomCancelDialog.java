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
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;

public class CustomCancelDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7195a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7195a = builder;
            builder.tvTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", CustomTextView.class);
            builder.tvContent = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'tvContent'", CustomTextView.class);
            builder.btnNo = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_no, "field 'btnNo'", CustomButtonView.class);
            builder.btnYes = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_yes, "field 'btnYes'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7195a;
            if (builder != null) {
                this.f7195a = null;
                builder.tvTitle = null;
                builder.tvContent = null;
                builder.btnNo = null;
                builder.btnYes = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CustomCancelDialog(Context context) {
        super(context);
    }

    public CustomCancelDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7191a;
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
        private int i = 0;
        private int j = 0;
        @BindView(2131494190)
        CustomTextView tvContent;
        @BindView(2131494276)
        CustomTextView tvTitle;

        public Builder(Context context) {
            this.f7191a = context;
        }

        public Builder a(String str) {
            this.c = str;
            return this;
        }

        public Builder a(int i2) {
            this.i = i2;
            return this;
        }

        public Builder b(int i2) {
            this.j = i2;
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

        public CustomCancelDialog a() {
            final CustomCancelDialog customCancelDialog = new CustomCancelDialog(this.f7191a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7191a.getSystemService("layout_inflater")).inflate(R.layout.shop_custom_cancel_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            customCancelDialog.setCanceledOnTouchOutside(this.b);
            if (TextUtils.isEmpty(this.c)) {
                this.tvTitle.setVisibility(8);
            } else {
                this.tvTitle.setVisibility(0);
                this.tvTitle.setText(this.c);
                this.tvTitle.setGravity(this.i);
            }
            if (TextUtils.isEmpty(this.d)) {
                this.tvContent.setVisibility(8);
            } else {
                this.tvContent.setVisibility(0);
                this.tvContent.setText(this.d);
                this.tvContent.setGravity(this.j);
            }
            if (this.e != null) {
                this.btnNo.setText(this.e);
            }
            if (this.f != null) {
                this.btnYes.setText(this.f);
            }
            this.btnNo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    customCancelDialog.dismiss();
                }
            });
            if (this.g != null) {
                this.btnYes.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.g.onClick(customCancelDialog, -1);
                        customCancelDialog.dismiss();
                    }
                });
            }
            if (this.h != null) {
                this.btnNo.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.h.onClick(customCancelDialog, -2);
                    }
                });
            }
            customCancelDialog.setContentView(inflate);
            return customCancelDialog;
        }
    }
}
