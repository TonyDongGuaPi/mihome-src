package com.mi.global.shop.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.widget.CustomTextView;

public class EMIAgreementDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f7207a;

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7209a;
        private View b;
        private View c;

        @UiThread
        public Builder_ViewBinding(final Builder builder, View view) {
            this.f7209a = builder;
            builder.tvText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_text, "field 'tvText'", CustomTextView.class);
            View findRequiredView = Utils.findRequiredView(view, R.id.btn_change_plan, "method 'changePlan'");
            this.b = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    builder.changePlan();
                }
            });
            View findRequiredView2 = Utils.findRequiredView(view, R.id.btn_agree_continue, "method 'agreeContinue'");
            this.c = findRequiredView2;
            findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    builder.agreeContinue();
                }
            });
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7209a;
            if (builder != null) {
                this.f7209a = null;
                builder.tvText = null;
                this.b.setOnClickListener((View.OnClickListener) null);
                this.b = null;
                this.c.setOnClickListener((View.OnClickListener) null);
                this.c = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public EMIAgreementDialog(Context context, int i) {
        super(context, i);
        this.f7207a = context;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7208a;
        private String b;
        private EMIAgreementDialog c;
        private OnclickListener d;
        @BindView(2131494270)
        CustomTextView tvText;

        public interface OnclickListener {
            void b();
        }

        public Builder(Context context, OnclickListener onclickListener) {
            this.f7208a = context;
            this.d = onclickListener;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public EMIAgreementDialog a() {
            this.c = new EMIAgreementDialog(this.f7208a, R.style.BackgroundLightDialog);
            View inflate = ((LayoutInflater) this.f7208a.getSystemService("layout_inflater")).inflate(R.layout.shop_emi_agreement_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            if (this.b != null) {
                this.tvText.setText(Html.fromHtml(this.b));
            }
            this.c.setCanceledOnTouchOutside(true);
            this.c.setCancelable(false);
            Rect rect = new Rect();
            ((Activity) this.f7208a).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            this.c.setContentView(inflate, new ViewGroup.LayoutParams(rect.width(), rect.height()));
            return this.c;
        }

        @OnClick({2131493022})
        public void changePlan() {
            this.c.b();
            if (this.f7208a instanceof FragmentActivity) {
                ((FragmentActivity) this.f7208a).getSupportFragmentManager().popBackStack();
            }
        }

        @OnClick({2131493017})
        public void agreeContinue() {
            if (this.d != null) {
                this.d.b();
            }
        }
    }

    public void a() {
        if (BaseActivity.isActivityAlive(this.f7207a)) {
            show();
        }
    }

    public void b() {
        if (BaseActivity.isActivityAlive(this.f7207a)) {
            dismiss();
        }
    }
}
