package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;

public class WebViewDebugDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7231a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7231a = builder;
            builder.etUrl = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.et_url, "field 'etUrl'", CustomEditTextView.class);
            builder.etCookie = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.et_cookie, "field 'etCookie'", CustomEditTextView.class);
            builder.btnSubmit = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_submit, "field 'btnSubmit'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7231a;
            if (builder != null) {
                this.f7231a = null;
                builder.etUrl = null;
                builder.etCookie = null;
                builder.btnSubmit = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public WebViewDebugDialog(Context context) {
        super(context);
    }

    public WebViewDebugDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Context f7229a;
        @BindView(2131493038)
        CustomButtonView btnSubmit;
        @BindView(2131493321)
        CustomEditTextView etCookie;
        @BindView(2131493326)
        CustomEditTextView etUrl;

        public Builder(Context context) {
            this.f7229a = context;
        }

        public WebViewDebugDialog a() {
            WebViewDebugDialog webViewDebugDialog = new WebViewDebugDialog(this.f7229a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7229a.getSystemService("layout_inflater")).inflate(R.layout.shop_webview_debug_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            webViewDebugDialog.setCanceledOnTouchOutside(true);
            this.btnSubmit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(Builder.this.f7229a, WebActivity.class);
                    intent.putExtra("url", Builder.this.etUrl.getText().toString().trim());
                    intent.putExtra("debug_cookie", Builder.this.etCookie.getText().toString().toString());
                    intent.putExtra("debug_model", 10);
                    Builder.this.f7229a.startActivity(intent);
                }
            });
            webViewDebugDialog.setContentView(inflate);
            return webViewDebugDialog;
        }
    }
}
