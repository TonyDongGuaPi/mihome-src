package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
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
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.util.fresco.FrescoUtils;

public class PayActivityWebDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7220a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7220a = builder;
            builder.ivClose = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_close, "field 'ivClose'", ImageView.class);
            builder.ivActivityImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.iv_activity_image, "field 'ivActivityImage'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7220a;
            if (builder != null) {
                this.f7220a = null;
                builder.ivClose = null;
                builder.ivActivityImage = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public PayActivityWebDialog(Context context) {
        super(context);
    }

    public PayActivityWebDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7218a;
        private String b;
        @BindView(2131493530)
        SimpleDraweeView ivActivityImage;
        @BindView(2131493536)
        ImageView ivClose;

        public Builder(Context context) {
            this.f7218a = context;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public PayActivityWebDialog a() {
            final PayActivityWebDialog payActivityWebDialog = new PayActivityWebDialog(this.f7218a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7218a.getSystemService("layout_inflater")).inflate(R.layout.shop_pay_activity_web_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            this.ivClose.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    payActivityWebDialog.dismiss();
                }
            });
            if (this.b != null && !TextUtils.isEmpty(this.b)) {
                FrescoUtils.a(this.b, this.ivActivityImage);
            }
            payActivityWebDialog.setCanceledOnTouchOutside(true);
            payActivityWebDialog.setContentView(inflate);
            return payActivityWebDialog;
        }
    }
}
