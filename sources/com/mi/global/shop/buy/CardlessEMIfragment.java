package com.mi.global.shop.buy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.PayResultWebActivity;
import com.mi.global.shop.adapter.checkout.CardlessPlanListAdapter;
import com.mi.global.shop.newmodel.checkout.NewCardlessEMIPlanResult;
import com.mi.global.shop.newmodel.checkout.NewCreateLoanApplicationResult;
import com.mi.global.shop.newmodel.checkout.NewLoanPayResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.ui.BaseFragment;
import com.mi.global.shop.util.ClickUtil;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;
import com.mi.global.shop.widget.dialog.CustomProgressDialog;
import com.mi.global.shop.widget.dialog.CustomVerifyOTPDialog;
import com.mi.global.shop.widget.dialog.EMIAgreementDialog;
import com.mi.global.shop.widget.dialog.EMIConfirmDialog;
import com.mi.global.shop.widget.ptr.util.PtrLocalDisplay;
import com.mi.log.LogUtil;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import java.util.List;
import java.util.Map;

public class CardlessEMIfragment extends BaseFragment implements CustomVerifyOTPDialog.OnclickListener, EMIAgreementDialog.Builder.OnclickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6807a = "order_id_extra";
    public static final String b = "order_total_extra";
    private static final String e = "CardlessEMIfragment";
    public NewCardlessEMIPlanResult.EmiPlansData c;
    private View d;
    private List<NewCardlessEMIPlanResult.CardlessEMIPlanOption> f;
    private CardlessPlanListAdapter g;
    /* access modifiers changed from: private */
    public EMIConfirmDialog i;
    /* access modifiers changed from: private */
    public EMIAgreementDialog j;
    private CustomVerifyOTPDialog k;
    /* access modifiers changed from: private */
    public CustomProgressDialog l;
    private String m;
    @BindView(2131493669)
    RelativeLayout mContentGroup;
    @BindView(2131493294)
    CustomTextView mEMIErrorTip;
    @BindView(2131493008)
    CustomButtonView mPayNowBtn;
    @BindView(2131493700)
    NoScrollListView mPlanListView;
    @BindView(2131494246)
    CustomTextView mPlanTips;
    private String n;
    private DefaultRetryPolicy o = new DefaultRetryPolicy(30000, 0, 1.0f);

    public void onCreate(Bundle bundle) {
        LogUtil.b(e, "onCreate");
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.b(e, "onCreateView");
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.shop_activity_cardless_emi, viewGroup, false);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.d.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.d);
                LogUtil.b(e, "onCreateView remove from parent");
            }
        }
        return this.d;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        if (getActivity() != null) {
            c();
            f();
        }
        super.onViewCreated(view, bundle);
    }

    private void c() {
        ButterKnife.bind((Object) this, this.d);
        PtrLocalDisplay.a((Context) getActivity());
        this.mContentGroup.setLayoutParams(new LinearLayout.LayoutParams(-1, PtrLocalDisplay.b - PtrLocalDisplay.a(65.0f)));
        getActivity().setTitle(getString(R.string.cardless_emi_title));
        this.g = new CardlessPlanListAdapter(getActivity());
        this.l = new CustomProgressDialog(getActivity());
    }

    private void f() {
        this.l.a();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.m = arguments.getString("order_id_extra");
            this.n = arguments.getString(b);
        }
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bc()).buildUpon();
        buildUpon.appendQueryParameter("basketamount", this.n);
        buildUpon.appendQueryParameter("order_id", this.m);
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewCardlessEMIPlanResult.class, new SimpleCallback<NewCardlessEMIPlanResult>() {
            public void a(NewCardlessEMIPlanResult newCardlessEMIPlanResult) {
                CardlessEMIfragment.this.l.b();
                if (newCardlessEMIPlanResult != null && newCardlessEMIPlanResult.data != null) {
                    CardlessEMIfragment.this.c = newCardlessEMIPlanResult.data;
                    CardlessEMIfragment.this.h();
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
            }

            public void a(String str) {
                super.a(str);
                CardlessEMIfragment.this.l.b();
                CardlessEMIfragment.this.mContentGroup.setVisibility(4);
            }
        });
        simpleProtobufRequest.setTag(e);
        simpleProtobufRequest.setRetryPolicy(this.o);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private void g() {
        if (this.f != null) {
            for (NewCardlessEMIPlanResult.CardlessEMIPlanOption cardlessEMIPlanOption : this.f) {
                if (cardlessEMIPlanOption.isDefault) {
                    return;
                }
            }
            if (this.f != null && this.f.size() > 0) {
                this.f.get(0).isDefault = true;
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.c.status != 4) {
            this.mContentGroup.setVisibility(0);
            this.mPlanListView.setAdapter(this.g);
            if (this.c.status < 3) {
                if (!TextUtils.isEmpty(this.c.statusMessage)) {
                    this.mEMIErrorTip.setVisibility(0);
                    this.mEMIErrorTip.setText(this.c.statusMessage);
                }
            } else if (this.c.emiPlans != null) {
                CustomTextView customTextView = this.mPlanTips;
                int i2 = R.string.cardless_emi_tips;
                customTextView.setText(getString(i2, this.c.emiPlans.basketAmount + ""));
                this.f = this.c.emiPlans.emiOptions;
                g();
                this.g.a(this.f);
            }
        } else if (!TextUtils.isEmpty(this.c.statusMessage)) {
            this.j = new EMIAgreementDialog.Builder(getActivity(), this).a(this.c.statusMessage).a();
            this.j.a();
        }
    }

    @OnClick({2131493008})
    public void cardlessPayNow(View view) {
        NewCardlessEMIPlanResult.CardlessEMIPlanOption j2;
        if (!ClickUtil.a() && (j2 = j()) != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            int i2 = R.string.cardless_emi_confirm_dialog_content_amt;
            sb.append(getString(i2, j2.totalMonthlyAmount, j2.numberOfMonths + ""));
            this.i = new EMIConfirmDialog.Builder(getActivity()).a(getString(R.string.cardless_emi_confirm_dialog_title)).b(sb.toString()).b(getString(R.string.cardless_emi_otp_cancel), (DialogInterface.OnClickListener) null).a(getString(R.string.cardless_emi_otp_confirm), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CardlessEMIfragment.this.k();
                }
            }).a();
            this.i.a();
        }
    }

    public void a() {
        l();
    }

    public void b() {
        i();
    }

    public void a(String str) {
        this.l.a();
        b(str);
    }

    private void i() {
        if (this.k == null) {
            this.k = new CustomVerifyOTPDialog(getActivity(), this);
        }
        this.k.a(this.c.phoneNumber);
        this.k.d();
    }

    private NewCardlessEMIPlanResult.CardlessEMIPlanOption j() {
        if (this.f == null) {
            return null;
        }
        for (NewCardlessEMIPlanResult.CardlessEMIPlanOption next : this.f) {
            if (next.isDefault) {
                return next;
            }
        }
        return null;
    }

    public void onDestroyView() {
        if (getActivity() != null) {
            getActivity().setTitle(R.string.buy_confirm_title);
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void k() {
        this.l.a();
        this.mPayNowBtn.setClickable(false);
        this.mPayNowBtn.setBackgroundColor(getResources().getColor(R.color.light_grey));
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bd()).buildUpon();
        buildUpon.appendQueryParameter("order_id", this.m);
        buildUpon.appendQueryParameter(BFLVerifyOTPFragment.c, j().numberOfMonths + "");
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewCreateLoanApplicationResult.class, new SimpleCallback<NewCreateLoanApplicationResult>() {
            public void a(NewCreateLoanApplicationResult newCreateLoanApplicationResult) {
                if (CardlessEMIfragment.this.i != null) {
                    CardlessEMIfragment.this.i.b();
                }
                CardlessEMIfragment.this.m();
                if (newCreateLoanApplicationResult != null && newCreateLoanApplicationResult.data != null && newCreateLoanApplicationResult.data.result && !TextUtils.isEmpty(newCreateLoanApplicationResult.data.content)) {
                    EMIAgreementDialog unused = CardlessEMIfragment.this.j = new EMIAgreementDialog.Builder(CardlessEMIfragment.this.getActivity(), CardlessEMIfragment.this).a(newCreateLoanApplicationResult.data.content).a();
                    CardlessEMIfragment.this.j.a();
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
            }

            public void a(String str) {
                super.a(str);
                CardlessEMIfragment.this.m();
            }
        });
        simpleProtobufRequest.setTag(e);
        simpleProtobufRequest.setRetryPolicy(this.o);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private void l() {
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bf()).buildUpon();
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewCreateLoanApplicationResult.class, new SimpleCallback<NewCreateLoanApplicationResult>() {
            public void a(NewCreateLoanApplicationResult newCreateLoanApplicationResult) {
                if (newCreateLoanApplicationResult != null && newCreateLoanApplicationResult.data != null && newCreateLoanApplicationResult.data.result) {
                }
            }

            public void onErrorResponse(VolleyError volleyError) {
                super.onErrorResponse(volleyError);
            }

            public void a(String str) {
                super.a(str);
            }
        });
        simpleProtobufRequest.setTag(e);
        simpleProtobufRequest.setRetryPolicy(this.o);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    private void b(String str) {
        StringBuilder sb;
        int i2;
        if (j() != null) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bh()).buildUpon();
            buildUpon.appendQueryParameter("id", this.m);
            buildUpon.appendQueryParameter("bank", "mifinance");
            buildUpon.appendQueryParameter("type", Constants.PAYTYPE_EMI);
            buildUpon.appendQueryParameter("phonecode", str);
            if (this.c.status == 4) {
                sb = new StringBuilder();
                i2 = this.c.terms;
            } else {
                sb = new StringBuilder();
                i2 = j().numberOfMonths;
            }
            sb.append(i2);
            sb.append("");
            buildUpon.appendQueryParameter(BFLVerifyOTPFragment.c, sb.toString());
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewLoanPayResult.class, (Map<String, String>) null, new SimpleCallback<NewLoanPayResult>() {
                public void a(NewLoanPayResult newLoanPayResult) {
                    NewLoanPayResult.Params params;
                    CardlessEMIfragment.this.l.b();
                    if (newLoanPayResult != null && newLoanPayResult.data != null && CardlessEMIfragment.this.getActivity() != null) {
                        if (TextUtils.isEmpty(newLoanPayResult.data.params) || (params = (NewLoanPayResult.Params) new Gson().fromJson(newLoanPayResult.data.params, NewLoanPayResult.Params.class)) == null || TextUtils.isEmpty(params.url)) {
                            CardlessEMIfragment.this.getActivity().finish();
                            return;
                        }
                        Intent intent = new Intent(CardlessEMIfragment.this.getActivity(), PayResultWebActivity.class);
                        intent.putExtra("url", params.url);
                        CardlessEMIfragment.this.getActivity().startActivityForResult(intent, 101);
                    }
                }

                public void onErrorResponse(VolleyError volleyError) {
                    super.onErrorResponse(volleyError);
                }

                public void a(String str) {
                    super.a(str);
                    CardlessEMIfragment.this.l.b();
                }
            });
            simpleProtobufRequest.setTag(e);
            simpleProtobufRequest.setRetryPolicy(this.o);
            RequestQueueUtil.a().add(simpleProtobufRequest);
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        this.l.b();
        this.mPayNowBtn.setClickable(true);
        this.mPayNowBtn.setBackgroundColor(getResources().getColor(R.color.orange_red));
    }
}
