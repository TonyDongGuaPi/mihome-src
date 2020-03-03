package com.mi.global.shop.buy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.checkout.UPIListAdapter;
import com.mi.global.shop.adapter.checkout.UPISelectListAdapter;
import com.mi.global.shop.buy.GoogleTezPayWrapper;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.buy.payu.PayUtil;
import com.mi.global.shop.ui.BaseFragment;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;

public class UPIFragment extends BaseFragment implements TextWatcher, View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6853a = "order_id_extra";
    public static final String c = "UPI";
    private static final Pattern k = Pattern.compile("[^A-Za-z0-9\\-\\.]");
    private static final Pattern l = Pattern.compile("[^A-Za-z0-9@\\-_\\.]");
    public List<OrderdetailFragment.PaymentMethod> b;
    private View d;
    /* access modifiers changed from: private */
    public UPIListAdapter e;
    private String f;
    /* access modifiers changed from: private */
    public PopupWindow g;
    private UPISelectListAdapter i;
    /* access modifiers changed from: private */
    public List<String> j;
    @BindView(2131494179)
    CustomTextView mBankView;
    @BindView(2131493011)
    CustomButtonView mPayNowBtn;
    @BindView(2131493606)
    LinearLayout mSelectGroup;
    @BindView(2131493611)
    LinearLayout mUPIEnterGroup;
    @BindView(2131494240)
    CustomEditTextView mUPIOtherView;
    @BindView(2131494281)
    CustomTextView mUPITip;
    @BindView(2131494280)
    CustomEditTextView mUPIView;
    @BindView(2131493703)
    NoScrollListView mUpiListView;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.shop_activity_tez, viewGroup, false);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.d.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.d);
            }
        }
        return this.d;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        if (getActivity() != null) {
            a();
            b();
        }
        super.onViewCreated(view, bundle);
    }

    private void a() {
        ButterKnife.bind((Object) this, this.d);
        PtrLocalDisplay.a((Context) getActivity());
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, PtrLocalDisplay.b - PtrLocalDisplay.a(65.0f)));
        this.e = new UPIListAdapter(getActivity());
        this.mUpiListView.setAdapter(this.e);
        this.mPayNowBtn.setOnClickListener(this);
        this.mSelectGroup.setOnClickListener(this);
        this.mUPIView.addTextChangedListener(this);
        this.mUPIView.setOnClickListener(this);
        this.mBankView.addTextChangedListener(this);
        this.mUPIOtherView.addTextChangedListener(this);
        l();
        g();
        if (e()) {
            getActivity().setTitle(getString(R.string.tez_title));
        }
        this.mUpiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UPIFragment.this.a(UPIFragment.this.b.get(i));
                UPIFragment.this.g();
                UPIFragment.this.k();
                UPIFragment.this.b.get(i).h = true;
                UPIFragment.this.e.notifyDataSetChanged();
                MiShopStatInterface.a(String.format("upi%s_click", new Object[]{Integer.valueOf(i + 1)}), "UPI");
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(OrderdetailFragment.PaymentMethod paymentMethod) {
        if (paymentMethod.k == 1) {
            this.mUPIEnterGroup.setVisibility(0);
            this.mUPIOtherView.setVisibility(8);
            this.mUPIView.setText("");
            this.mBankView.setText("");
            this.mUPITip.setText(getString(R.string.tez_upi_id));
            return;
        }
        this.mUPIEnterGroup.setVisibility(8);
        this.mUPIOtherView.setVisibility(0);
        this.mUPIOtherView.setText("");
        this.mUPITip.setText(getString(R.string.tez_upi_other_id));
    }

    private void b() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f = arguments.getString("order_id_extra");
        }
        if (PayU.ae != null && PayU.ae.size() > 0) {
            Iterator<OrderdetailFragment.PaymentMethod> it = PayU.ae.iterator();
            while (it.hasNext()) {
                OrderdetailFragment.PaymentMethod next = it.next();
                if (next.f.equals(getString(R.string.buy_confirm_PaymentKey_UPI))) {
                    this.b = next.j;
                    c();
                    return;
                }
            }
        }
    }

    private void c() {
        if (this.b == null && e()) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
        i();
        this.e.a(this.b);
    }

    private void f() {
        final String str;
        if (j() != null) {
            if (j().k == 1) {
                str = this.mUPIView.getText().toString().replace(" ", "") + "@" + this.mBankView.getText().toString().replace(" ", "");
            } else {
                str = this.mUPIOtherView.getText().toString().replace(" ", "");
            }
            final Params params = new Params();
            params.put(PayU.j, j().f);
            params.put("vpa", str);
            if ("tez".equalsIgnoreCase(j().f)) {
                GoogleTezPayWrapper googleTezPayWrapper = new GoogleTezPayWrapper();
                String str2 = null;
                try {
                    str2 = GoogleTezPayWrapper.a("", "", "", "", "", "", "", "", "", "");
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                googleTezPayWrapper.a(getActivity(), str2, new GoogleTezPayWrapper.IsReadyListener() {
                    public void a() {
                        params.put(PayU.j, "UPI");
                        UPIFragment.this.a(Constants.PAYTYPE_UPI, params, Constants.PAYTYPE_UPI, str);
                    }

                    public void b() {
                        UPIFragment.this.a(Constants.PAYTYPE_TEZ, params, Constants.PAYTYPE_UPI, str);
                    }
                });
            } else {
                a(Constants.PAYTYPE_UPI, params, Constants.PAYTYPE_UPI, str);
            }
            MiShopStatInterface.a("pay_click", "UPI", "channl", j().f6851a);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str, Params params, String str2, String str3) {
        PayUtil.a(this.f, Constants.PAY_BANK_PAYU, str, (ConfirmActivity) getActivity(), PayU.PaymentMode.UPI, params, "", "", "", str2, str3);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (e()) {
            this.mPayNowBtn.setClickable(false);
            this.mPayNowBtn.setBackgroundColor(getResources().getColor(R.color.delivery_tv_gray));
        }
    }

    private void h() {
        if (e()) {
            this.mPayNowBtn.setClickable(true);
            this.mPayNowBtn.setBackgroundColor(getResources().getColor(R.color.orange_red));
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_pay) {
            f();
        } else if (id == R.id.layout_select_group) {
            n();
            b((View) this.mBankView);
            MiShopStatInterface.a("select_click", "UPI");
        } else if (id == R.id.tv_upi_id) {
            MiShopStatInterface.a("id_click", "UPI");
        }
    }

    private void i() {
        if (this.b != null) {
            for (OrderdetailFragment.PaymentMethod next : this.b) {
                if (next.h) {
                    a(next);
                    return;
                }
            }
            if (this.b != null && this.b.size() > 0) {
                this.b.get(0).h = true;
            }
        }
    }

    private OrderdetailFragment.PaymentMethod j() {
        if (this.b == null) {
            return null;
        }
        for (OrderdetailFragment.PaymentMethod next : this.b) {
            if (next.h) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.b != null) {
            for (OrderdetailFragment.PaymentMethod paymentMethod : this.b) {
                paymentMethod.h = false;
            }
        }
    }

    private void l() {
        final View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.shop_layout_tez_popupwindow, (ViewGroup) null);
        NoScrollListView noScrollListView = (NoScrollListView) inflate.findViewById(R.id.lv_select_list);
        this.i = new UPISelectListAdapter(getActivity());
        noScrollListView.setAdapter(this.i);
        noScrollListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UPIFragment.this.mBankView.setText((CharSequence) UPIFragment.this.j.get(i));
                UPIFragment.this.m();
            }
        });
        this.mSelectGroup.post(new Runnable() {
            public void run() {
                PopupWindow unused = UPIFragment.this.g = new PopupWindow(inflate, UPIFragment.this.mSelectGroup.getMeasuredWidth(), -2, true);
                UPIFragment.this.g.setTouchable(true);
                UPIFragment.this.g.setFocusable(true);
                UPIFragment.this.g.setOutsideTouchable(true);
            }
        });
    }

    private void b(View view) {
        OrderdetailFragment.PaymentMethod j2;
        String[] split;
        if (this.g != null && j() != null && (j2 = j()) != null && !TextUtils.isEmpty(j2.i) && (split = j2.i.split(",")) != null) {
            this.j = Arrays.asList(split);
            this.i.a(this.j);
            this.g.showAsDropDown(view);
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.g != null) {
            this.g.dismiss();
        }
    }

    private void n() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (inputMethodManager.isActive() && getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 2);
        }
    }

    public void onDestroyView() {
        if (getActivity() != null) {
            getActivity().setTitle(R.string.buy_confirm_title);
        }
        super.onDestroyView();
    }

    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        o();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005f, code lost:
        if (android.text.TextUtils.isEmpty(r5.mBankView.getText().toString()) == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00ac, code lost:
        if (r0.contains("@") != false) goto L_0x0062;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void o() {
        /*
            r5 = this;
            com.mi.global.shop.buy.OrderdetailFragment$PaymentMethod r0 = r5.j()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            com.mi.global.shop.buy.OrderdetailFragment$PaymentMethod r0 = r5.j()
            int r0 = r0.k
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L_0x0066
            com.mi.global.shop.widget.CustomEditTextView r0 = r5.mUPIView
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.util.regex.Pattern r3 = k
            java.util.regex.Matcher r3 = r3.matcher(r0)
            boolean r3 = r3.find()
            if (r3 == 0) goto L_0x004b
            android.content.Context r1 = r5.getContext()
            int r4 = com.mi.global.shop.R.string.tez_upi_error_tip
            com.mi.util.MiToast.a((android.content.Context) r1, (int) r4, (int) r2)
            java.util.regex.Pattern r1 = k
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replaceAll(r1, r2)
            com.mi.global.shop.widget.CustomEditTextView r1 = r5.mUPIView
            r1.setText(r0)
            com.mi.global.shop.widget.CustomEditTextView r1 = r5.mUPIView
            int r0 = r0.length()
            r1.setSelection(r0)
            goto L_0x00af
        L_0x004b:
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0064
            com.mi.global.shop.widget.CustomTextView r0 = r5.mBankView
            java.lang.CharSequence r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0062
            goto L_0x0064
        L_0x0062:
            r3 = 0
            goto L_0x00af
        L_0x0064:
            r3 = 1
            goto L_0x00af
        L_0x0066:
            com.mi.global.shop.widget.CustomEditTextView r0 = r5.mUPIOtherView
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            java.util.regex.Pattern r3 = l
            java.util.regex.Matcher r3 = r3.matcher(r0)
            boolean r3 = r3.find()
            if (r3 == 0) goto L_0x00a0
            android.content.Context r1 = r5.getContext()
            int r4 = com.mi.global.shop.R.string.tez_upi_other_error_tip
            com.mi.util.MiToast.a((android.content.Context) r1, (int) r4, (int) r2)
            java.util.regex.Pattern r1 = k
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replaceAll(r1, r2)
            com.mi.global.shop.widget.CustomEditTextView r1 = r5.mUPIOtherView
            r1.setText(r0)
            com.mi.global.shop.widget.CustomEditTextView r1 = r5.mUPIOtherView
            int r0 = r0.length()
            r1.setSelection(r0)
            goto L_0x00af
        L_0x00a0:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x0064
            java.lang.String r3 = "@"
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x0062
            goto L_0x0064
        L_0x00af:
            if (r3 == 0) goto L_0x00b5
            r5.g()
            goto L_0x00b8
        L_0x00b5:
            r5.h()
        L_0x00b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.buy.UPIFragment.o():void");
    }
}
