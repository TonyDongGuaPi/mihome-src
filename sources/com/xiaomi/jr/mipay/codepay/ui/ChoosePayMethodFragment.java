package com.xiaomi.jr.mipay.codepay.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.component.ArrayAdapter;
import com.xiaomi.jr.mipay.codepay.data.PayType;
import com.xiaomi.jr.mipay.codepay.util.CodePayConstants;
import java.util.ArrayList;

public class ChoosePayMethodFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10916a = "mipay.payMethod";
    private static final String b = "ChoosePayMethodFragment";
    private static final int c = 10;
    private static final int d = 4;
    private ListView e;
    private PayMethodListAdapter f;
    /* access modifiers changed from: private */
    public ArrayList<PayType> g;
    /* access modifiers changed from: private */
    public PayType h;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.g = (ArrayList) arguments.getSerializable(CodePayConstants.j);
            this.h = (PayType) arguments.getSerializable(CodePayConstants.k);
        }
        if (this.g == null || this.g.isEmpty()) {
            MifiLog.e(b, "arguments is invalid");
            getActivity().finish();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.jr_mipay_code_pay_choose_pay_method, viewGroup, false);
        this.e = (ListView) inflate.findViewById(16908298);
        return inflate;
    }

    private int a() {
        if (this.h == null) {
            return 0;
        }
        for (int i = 0; i < this.g.size(); i++) {
            if (this.h.mPayTypeId == this.g.get(i).mPayTypeId) {
                return i;
            }
        }
        return 0;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        b();
        this.e.setChoiceMode(1);
        this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (j > -1) {
                    PayType unused = ChoosePayMethodFragment.this.h = (PayType) ChoosePayMethodFragment.this.g.get(i);
                    Intent intent = new Intent();
                    intent.putExtra(CodePayConstants.k, ChoosePayMethodFragment.this.h);
                    ChoosePayMethodFragment.this.a(-1, intent);
                    ChoosePayMethodFragment.this.d();
                }
            }
        });
        this.f = new PayMethodListAdapter(getActivity());
        this.e.setAdapter(this.f);
        this.f.a(this.g);
        this.e.setItemChecked(a(), true);
    }

    private void b() {
        View view = getView();
        if (view != null && this.g.size() > 10) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.height = getResources().getDimensionPixelSize(R.dimen.jr_mipay_dialog_max_height);
            view.setLayoutParams(layoutParams);
        }
    }

    private class PayMethodListAdapter extends ArrayAdapter<PayType> {
        private LayoutInflater d;

        public PayMethodListAdapter(Context context) {
            super(context);
            this.d = LayoutInflater.from(context);
        }

        public View a(Context context, int i, PayType payType, ViewGroup viewGroup) {
            return this.d.inflate(R.layout.jr_mipay_code_pay_method_item, viewGroup, false);
        }

        public void a(View view, int i, PayType payType) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(payType.mSummary)) {
                sb.append(payType.mSummary);
            } else if (TextUtils.equals(PayType.PAY_TYPE_BANLANCE, payType.mPayType)) {
                double d2 = (double) payType.mBalance;
                Double.isNaN(d2);
                String format = String.format("%.2f", new Object[]{Double.valueOf(d2 / 100.0d)});
                sb.append(ChoosePayMethodFragment.this.getString(R.string.jr_mipay_balance));
                sb.append(" (");
                sb.append(format);
                sb.append(ChoosePayMethodFragment.this.getString(R.string.jr_mipay_code_pay_money_unit));
                sb.append(Operators.BRACKET_END);
            } else if (TextUtils.equals("BANKCARD", payType.mPayType)) {
                if (2 == payType.mCardType) {
                    sb.append(payType.mBankName);
                    sb.append(' ');
                    sb.append(ChoosePayMethodFragment.this.getString(R.string.jr_mipay_code_pay_card_type_credit));
                    sb.append(' ');
                    sb.append(Operators.BRACKET_START);
                    sb.append(payType.mTailNum);
                    sb.append(Operators.BRACKET_END);
                } else if (1 == payType.mCardType) {
                    sb.append(payType.mBankName);
                    sb.append(' ');
                    sb.append(ChoosePayMethodFragment.this.getString(R.string.jr_mipay_code_pay_card_type_debit));
                    sb.append(' ');
                    sb.append(Operators.BRACKET_START);
                    sb.append(payType.mTailNum);
                    sb.append(Operators.BRACKET_END);
                }
            } else if (TextUtils.equals("BINDCARD", payType.mPayType)) {
                sb.append(ChoosePayMethodFragment.this.getString(R.string.jr_mipay_code_pay_bind_new_card));
            } else if (TextUtils.equals(PayType.PAY_TYPE_INSTALLMENT, payType.mPayType)) {
                sb.append(payType.mSummary);
            }
            view.setEnabled(payType.mAvailable);
            ((RadioButton) view).setText(sb.toString());
        }

        public boolean isEnabled(int i) {
            return ((PayType) getItem(i)).mAvailable;
        }
    }
}
