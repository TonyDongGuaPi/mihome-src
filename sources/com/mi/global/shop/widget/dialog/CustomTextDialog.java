package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
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
import com.mi.global.shop.widget.CustomTextView;

public class CustomTextDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7203a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7203a = builder;
            builder.tvText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_text, "field 'tvText'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7203a;
            if (builder != null) {
                this.f7203a = null;
                builder.tvText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CustomTextDialog(Context context) {
        super(context);
    }

    public CustomTextDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7202a;
        private String b;
        @BindView(2131494270)
        CustomTextView tvText;

        public Builder(Context context) {
            this.f7202a = context;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public CustomTextDialog a() {
            CustomTextDialog customTextDialog = new CustomTextDialog(this.f7202a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7202a.getSystemService("layout_inflater")).inflate(R.layout.shop_custom_text_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            if (this.b != null) {
                this.tvText.setText(this.b);
            }
            customTextDialog.setCanceledOnTouchOutside(true);
            customTextDialog.setContentView(inflate);
            return customTextDialog;
        }
    }
}
