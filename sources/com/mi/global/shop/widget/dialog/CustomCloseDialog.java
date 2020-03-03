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
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;

public class CustomCloseDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7200a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7200a = builder;
            builder.tvTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", CustomTextView.class);
            builder.tvContent = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'tvContent'", CustomTextView.class);
            builder.btnGotIt = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_got_it, "field 'btnGotIt'", CustomButtonView.class);
            builder.btnClose = (ImageView) Utils.findRequiredViewAsType(view, R.id.btn_close, "field 'btnClose'", ImageView.class);
            builder.llBottom = Utils.findRequiredView(view, R.id.ll_bootom, "field 'llBottom'");
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7200a;
            if (builder != null) {
                this.f7200a = null;
                builder.tvTitle = null;
                builder.tvContent = null;
                builder.btnGotIt = null;
                builder.btnClose = null;
                builder.llBottom = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CustomCloseDialog(Context context) {
        super(context);
    }

    public CustomCloseDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7196a;
        private boolean b = true;
        @BindView(2131493023)
        ImageView btnClose;
        @BindView(2131493027)
        CustomButtonView btnGotIt;
        private boolean c = true;
        private String d;
        private String e;
        private String f;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener g;
        @BindView(2131493665)
        View llBottom;
        @BindView(2131494190)
        CustomTextView tvContent;
        @BindView(2131494276)
        CustomTextView tvTitle;

        public Builder(Context context) {
            this.f7196a = context;
        }

        public Builder a(String str) {
            this.d = str;
            return this;
        }

        public Builder b(String str) {
            this.e = str;
            return this;
        }

        public Builder a(String str, DialogInterface.OnClickListener onClickListener) {
            this.f = str;
            this.g = onClickListener;
            return this;
        }

        public Builder a(Boolean bool) {
            this.b = bool.booleanValue();
            return this;
        }

        public Builder b(Boolean bool) {
            this.c = bool.booleanValue();
            return this;
        }

        public CustomCloseDialog a() {
            final CustomCloseDialog customCloseDialog = new CustomCloseDialog(this.f7196a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7196a.getSystemService("layout_inflater")).inflate(R.layout.shop_custom_close_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            customCloseDialog.setCanceledOnTouchOutside(this.b);
            if (!TextUtils.isEmpty(this.d)) {
                this.tvTitle.setText(this.d);
            } else {
                this.tvTitle.setVisibility(8);
            }
            if (!TextUtils.isEmpty(this.e)) {
                this.tvContent.setText(this.e);
            }
            if (this.f != null) {
                this.btnGotIt.setText(this.f);
            }
            if (!this.c) {
                this.llBottom.setVisibility(8);
            }
            this.btnGotIt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    customCloseDialog.dismiss();
                }
            });
            this.btnClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    customCloseDialog.dismiss();
                }
            });
            if (this.g != null) {
                this.btnGotIt.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.g.onClick(customCloseDialog, -1);
                        customCloseDialog.dismiss();
                    }
                });
            }
            customCloseDialog.setContentView(inflate);
            return customCloseDialog;
        }
    }
}
