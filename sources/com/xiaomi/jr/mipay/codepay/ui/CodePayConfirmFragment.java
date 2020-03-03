package com.xiaomi.jr.mipay.codepay.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.miui.supportlite.app.AlertDialog;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkConstants;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.component.AgreementCheckBox;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayConfirmContract;
import com.xiaomi.jr.mipay.codepay.presenter.CodePayConfirmPresenter;
import com.xiaomi.jr.mipay.codepay.presenter.Presenter;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import com.xiaomi.jr.mipay.codepay.util.CodePayUtils;
import com.xiaomi.jr.mipay.codepay.validate.FooterViewType;
import com.xiaomi.jr.mipay.pay.verifier.component.PasswordEditText;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboard;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardManager;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboardView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CodePayConfirmFragment extends BaseFragment implements CodePayConfirmContract.View {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10922a = "mipay.codepayConfirm";
    private TextView b;
    private TextView c;
    private TextView d;
    private View e;
    private TextView f;
    private TextView g;
    private View h;
    private TextView i;
    private AgreementCheckBox j;
    private ViewGroup k;
    private PasswordEditText l;
    private SafeKeyboardView m;
    private TextView n;
    private View o;
    private boolean p = true;
    private CodePayConfirmPresenter q = new CodePayConfirmPresenter(this);

    public Presenter c() {
        return this.q;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jr_mipay_code_pay_confirm_fragment, viewGroup, false);
        this.b = (TextView) inflate.findViewById(R.id.header);
        this.c = (TextView) inflate.findViewById(R.id.trade_info);
        this.d = (TextView) inflate.findViewById(R.id.trade_amount);
        this.e = inflate.findViewById(R.id.trade_method_row);
        this.f = (TextView) inflate.findViewById(R.id.trade_method_txt);
        this.g = (TextView) inflate.findViewById(R.id.trade_method_summary_txt);
        this.h = inflate.findViewById(R.id.extra_info);
        this.i = (TextView) inflate.findViewById(R.id.pay_tip_text);
        this.j = (AgreementCheckBox) inflate.findViewById(R.id.agreement);
        this.k = (ViewGroup) inflate.findViewById(R.id.footer);
        this.l = (PasswordEditText) inflate.findViewById(R.id.check_password_edit);
        this.o = inflate.findViewById(R.id.loading);
        this.n = (TextView) inflate.findViewById(R.id.confirm);
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.k.setEnabled(this.j.isChecked());
        this.l.setPassInputListener(new PasswordEditText.PasswordInputListener() {
            public final void onPassInputFinish(boolean z) {
                CodePayConfirmFragment.this.b(z);
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayConfirmFragment.this.b(view);
            }
        });
        this.j.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CodePayConfirmFragment.this.a(compoundButton, z);
            }
        });
        this.j.setOnAgreementClickedListener(new AgreementCheckBox.OnAgreementClickedListener() {
            public final void onClicked(String str, String str2) {
                CodePayConfirmFragment.this.b(str, str2);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CodePayConfirmFragment.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z) {
        if (z) {
            this.q.a(this.l.getPassword());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        this.q.a((String) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        this.k.setEnabled(z);
        if (!z) {
            new AlertDialog.Builder(getActivity()).a(R.string.jr_mipay_agreement_dialog_title).b(R.string.jr_mipay_agreement_dialog_message).a(R.string.jr_mipay_button_agree, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CodePayConfirmFragment.this.d(dialogInterface, i);
                }
            }).b(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CodePayConfirmFragment.this.c(dialogInterface, i);
                }
            }).a().show(getFragmentManager(), "agreement");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(DialogInterface dialogInterface, int i2) {
        this.j.setChecked(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putString("url", str2);
        DeeplinkUtils.openDeeplink(this, str, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        List<PayType> b2;
        if (this.p && (b2 = ((CodePayConfirmContract.Presenter) c()).b()) != null && b2.size() >= 2) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CodePayConstants.k, ((CodePayConfirmContract.Presenter) c()).a());
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(b2);
            bundle.putSerializable(CodePayConstants.j, arrayList);
            bundle.putInt(DeeplinkConstants.KEY_REQUEST_CODE, 1001);
            CodePayUtils.a(this, ChoosePayMethodFragment.f10916a, bundle);
        }
    }

    public void a(String str) {
        this.b.setText(str);
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void a(FooterViewType footerViewType) {
        switch (footerViewType) {
            case PASSWORD:
                a();
                return;
            case LOADING:
                this.l.setVisibility(8);
                this.n.setVisibility(8);
                this.o.setVisibility(0);
                return;
            case BUTTON:
                this.l.setVisibility(8);
                this.n.setVisibility(0);
                this.o.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void a() {
        this.l.setVisibility(0);
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.l.clearPassword();
        this.l.requestFocus();
        if (this.m == null) {
            this.m = SafeKeyboardManager.a((Activity) getActivity(), SafeKeyboard.c);
            SafeKeyboardManager.a(this.m);
            SafeKeyboardManager.a((View) this.l, this.m);
        }
        this.l.requestFocus();
    }

    public void a(String str, long j2) {
        this.d.setText(getString(R.string.jr_mipay_order_denom_value, a(j2)));
        this.c.setText(str);
    }

    public void a(PayType payType) {
        this.f.setText(payType.mSummary);
        if (TextUtils.isEmpty(payType.mSubSummary)) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
            this.g.setText(payType.mSubSummary);
        }
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(payType.mTips);
        if (payType.mAgreements == null || payType.mAgreements.isEmpty()) {
            z = false;
        }
        if (z || z2) {
            this.h.setVisibility(0);
            if (z2) {
                this.i.setVisibility(0);
                this.i.setText(payType.mTips);
            } else {
                this.i.setVisibility(8);
            }
            if (z) {
                this.j.setVisibility(0);
                this.j.setAgreement(payType.mAgreements);
                return;
            }
            this.j.setVisibility(8);
            return;
        }
        this.h.setVisibility(8);
    }

    public void a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(CodePayConstants.f, str2);
        bundle.putString("processId", str);
        bundle.putInt(DeeplinkConstants.KEY_REQUEST_CODE, 1002);
        CodePayUtils.a(this, CodePayCheckSmsFragment.f10918a, bundle);
    }

    public void a(boolean z, String str, String str2) {
        Utils.a((DialogFragment) new AlertDialog.Builder(getActivity()).a((CharSequence) str).b((CharSequence) str2).a((CharSequence) z ? getString(R.string.jr_mipay_pass_err_reinput) : null, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CodePayConfirmFragment.this.b(dialogInterface, i);
            }
        }).b((CharSequence) null, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CodePayConfirmFragment.this.a(dialogInterface, i);
            }
        }).a(false).a(), getFragmentManager(), "passErr");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        a();
    }

    public void a(int i2, String str) {
        Context applicationContext = getActivity().getApplicationContext();
        Utils.b(applicationContext, Operators.ARRAY_START_STR + i2 + Operators.ARRAY_END_STR + str);
    }

    private static String a(long j2) {
        if (j2 % 100 == 0) {
            return String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(j2 / 100)});
        } else if (j2 % 10 == 0) {
            Locale locale = Locale.getDefault();
            double d2 = (double) j2;
            Double.isNaN(d2);
            return String.format(locale, "%.1f", new Object[]{Double.valueOf(d2 / 100.0d)});
        } else {
            Locale locale2 = Locale.getDefault();
            double d3 = (double) j2;
            Double.isNaN(d3);
            return String.format(locale2, "%.2f", new Object[]{Double.valueOf(d3 / 100.0d)});
        }
    }
}
