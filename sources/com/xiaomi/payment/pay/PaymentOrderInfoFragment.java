package com.xiaomi.payment.pay;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.data.Image;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.contract.PaymentOrderInfoContract;
import com.xiaomi.payment.pay.presenter.PaymentOrderInfoPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxCheckPaymentTask;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import com.xiaomi.payment.ui.fragment.query.PayQuickQueryFragment;
import com.xiaomi.payment.ui.fragment.query.PayResultFragment;
import com.xiaomi.payment.ui.fragment.query.PayWaitQueryFragment;
import java.util.ArrayList;
import junit.framework.Assert;

public class PaymentOrderInfoFragment extends BaseProcessFragment implements PaymentOrderInfoContract.View {
    public static final String A = "useConsumedDiscountGiftCard";
    public static final int v = 1;
    public static final int w = 2;
    public static final int x = 3;
    public static final int y = 4;
    public static final String z = "discountGiftcardIndex";
    private TextView B;
    private TextView C;
    private View D;
    private View E;
    private TextView F;
    private TextView G;
    private TextView H;
    private View I;
    private ImageView J;
    private TextView K;
    private TextView L;
    private TextView M;
    private Button N;
    /* access modifiers changed from: private */
    public ProgressButton O;
    private String P;
    /* access modifiers changed from: private */
    public long Q;
    /* access modifiers changed from: private */
    public long R;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public boolean S = true;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public boolean T = true;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public boolean U = true;
    /* access modifiers changed from: private */
    public boolean V;
    private long W;
    /* access modifiers changed from: private */
    public long X;
    /* access modifiers changed from: private */
    public long Y;
    /* access modifiers changed from: private */
    public String Z;
    /* access modifiers changed from: private */
    public RechargeType aa;
    /* access modifiers changed from: private */
    public ArrayList<RxCheckPaymentTask.Result.DiscountGiftCard> ab;
    /* access modifiers changed from: private */
    public RxRechargeTypeTask.Result ac;
    private RxCheckPaymentTask.Result ad;
    private Drawable ae;
    private Image.ThumbnailFormat af;
    /* access modifiers changed from: private */
    public int ag = -1;
    private View.OnClickListener ah = new View.OnClickListener() {
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(MibiConstants.eF, PaymentOrderInfoFragment.this.S);
            bundle.putLong("giftcardValue", PaymentOrderInfoFragment.this.X);
            bundle.putBoolean(MibiConstants.eH, PaymentOrderInfoFragment.this.T);
            bundle.putString(MibiConstants.eI, PaymentOrderInfoFragment.this.Z);
            bundle.putLong(MibiConstants.eJ, PaymentOrderInfoFragment.this.Y);
            bundle.putBoolean(MibiConstants.eK, PaymentOrderInfoFragment.this.U);
            bundle.putLong("balance", PaymentOrderInfoFragment.this.R);
            bundle.putLong("price", PaymentOrderInfoFragment.this.Q);
            bundle.putSerializable(MibiConstants.gN, PaymentOrderInfoFragment.this.ab);
            bundle.putSerializable(PaymentOrderInfoFragment.z, Integer.valueOf(PaymentOrderInfoFragment.this.ag));
            bundle.putBoolean(PaymentOrderInfoFragment.A, PaymentOrderInfoFragment.this.V);
            PaymentOrderInfoFragment.this.a((Class<? extends StepFragment>) PaymentBalanceInfoFragment.class, bundle, 1, (String) null);
        }
    };
    private View.OnClickListener ai = new View.OnClickListener() {
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(MibiConstants.he, PaymentOrderInfoFragment.this.ac.mRechargeTypes);
            bundle.putSerializable(MibiConstants.hf, PaymentOrderInfoFragment.this.aa);
            PaymentOrderInfoFragment.this.a((Class<? extends StepFragment>) PaymenPayListFragment.class, bundle, 2, (String) null);
        }
    };
    private View.OnClickListener aj = new View.OnClickListener() {
        public void onClick(View view) {
            PaymentOrderInfoFragment.this.O.startProgress();
            ((PaymentOrderInfoPresenter) PaymentOrderInfoFragment.this.H_()).b(PaymentOrderInfoFragment.this.S, PaymentOrderInfoFragment.this.T, PaymentOrderInfoFragment.this.ag, PaymentOrderInfoFragment.this.U);
        }
    };
    private View.OnClickListener ak = new View.OnClickListener() {
        public void onClick(View view) {
            Utils.a(PaymentOrderInfoFragment.this.getActivity());
        }
    };
    private View.OnClickListener al = new View.OnClickListener() {
        public void onClick(View view) {
            PaymentOrderInfoFragment.this.ac();
        }
    };

    public void a(int i, boolean z2) {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public IPresenter onCreatePresenter() {
        return new PaymentOrderInfoPresenter();
    }

    public void k() {
        super.k();
        this.O.stopProgress();
        MistatisticUtils.a((Fragment) this, "Pay:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "Pay:");
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment, (ViewGroup) null);
        this.B = (TextView) inflate.findViewById(R.id.text_order_name);
        this.C = (TextView) inflate.findViewById(R.id.text_order_value);
        this.D = inflate.findViewById(R.id.balance);
        this.F = (TextView) inflate.findViewById(R.id.text_balance_name);
        this.G = (TextView) inflate.findViewById(R.id.text_balance_value);
        this.E = inflate.findViewById(R.id.text_balance_value_with_unit);
        this.H = (TextView) inflate.findViewById(R.id.text_balance_no_use);
        this.D.setOnClickListener(this.ah);
        this.I = inflate.findViewById(R.id.pay_type);
        this.J = (ImageView) inflate.findViewById(R.id.icon_pay_type);
        this.K = (TextView) inflate.findViewById(R.id.text_pay_type_name);
        this.I.setOnClickListener(this.ai);
        this.L = (TextView) inflate.findViewById(R.id.discount);
        this.M = (TextView) inflate.findViewById(R.id.errorDesc);
        this.N = (Button) inflate.findViewById(R.id.button_call);
        if (!Utils.b()) {
            this.N.setOnClickListener(this.ak);
        } else {
            this.N.setOnClickListener(this.al);
        }
        this.O = (ProgressButton) inflate.findViewById(R.id.button_pay);
        this.O.setOnClickListener(this.aj);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.ad = (RxCheckPaymentTask.Result) bundle.getSerializable(MibiConstants.cW);
        Assert.assertNotNull(this.ad);
        this.P = this.ad.mOrderTitle;
        this.Q = this.ad.mOrderPrice;
        this.R = this.ad.mBalance;
        this.X = this.ad.mGiftcardValue;
        this.Y = this.ad.mPartnerGiftcardValue;
        this.S = this.ad.mUseGiftcard;
        this.T = this.ad.mUsePartnerGiftcard;
        this.Z = this.ad.mPartnerGiftcardName;
        this.W = this.ad.getTotalBalance();
        this.ab = this.ad.mDiscountGiftCards;
        this.ac = (RxRechargeTypeTask.Result) bundle.getSerializable(MibiConstants.cF);
        if (this.ab != null && this.ab.size() > 0) {
            int i = 0;
            if (this.ad.mOrderConsumedDiscountGiftcardId == 0) {
                this.ag = 0;
                this.V = false;
                return;
            }
            while (true) {
                if (i >= this.ab.size()) {
                    break;
                } else if (this.ab.get(i).mGiftCardId == ((long) this.ad.mOrderConsumedDiscountGiftcardId)) {
                    this.ag = i;
                    break;
                } else {
                    i++;
                }
            }
            this.V = true;
        }
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(MibiCodeConstants.b);
        R();
    }

    private void R() {
        X();
        Y();
        Z();
        aa();
    }

    private void S() {
        Y();
        aa();
    }

    private void X() {
        this.B.setText(this.P);
        this.C.setText(Utils.a(this.Q));
    }

    private void Y() {
        boolean z2;
        this.D.setVisibility(0);
        this.F.setText(R.string.mibi_order_balance_lable);
        if ((!this.S || this.X <= 0) && ((!this.T || this.Y <= 0) && this.ag < 0)) {
            z2 = false;
        } else {
            this.F.setText(R.string.mibi_order_balance_lable_append);
            z2 = true;
        }
        if (this.R > 0) {
            z2 = true;
        }
        long c = ((PaymentOrderInfoPresenter) H_()).c(this.S, this.T, this.ag, this.U);
        if (c == 0) {
            if (!z2) {
                this.D.setVisibility(8);
                return;
            }
            this.E.setVisibility(8);
            this.H.setVisibility(0);
        } else if (c != this.R || c < this.Q || z2) {
            this.E.setVisibility(0);
            this.H.setVisibility(8);
            this.G.setText(Utils.a(c));
        } else {
            this.E.setVisibility(0);
            this.H.setVisibility(8);
            this.G.setText(Utils.a(c));
        }
    }

    private void Z() {
        if (this.Q <= this.W) {
            this.I.setVisibility(8);
            return;
        }
        this.I.setVisibility(0);
        if (this.ac != null) {
            if (this.aa == null) {
                this.aa = this.ac.mLastChargeType;
            }
            if (this.aa == null) {
                this.aa = this.ac.mRechargeTypes.get(0);
            }
            this.K.setText(this.aa.mTitle);
            ab();
            Image.a((Context) getActivity()).a(this.aa.mIcon, this.af).a(this.ae).a(this.J);
            if (TextUtils.isEmpty(this.ac.mDirectPayDiscount)) {
                this.L.setVisibility(8);
                return;
            }
            this.L.setVisibility(0);
            this.L.setText(this.ac.mDirectPayDiscount);
            return;
        }
        throw new IllegalArgumentException("default pay type should not be null");
    }

    private void aa() {
        long d = ((PaymentOrderInfoPresenter) H_()).d(this.S, this.T, this.ag, this.U);
        if (d > 0) {
            this.O.setText(getString(R.string.mibi_label_pay_remain, new Object[]{Utils.a(d)}));
            return;
        }
        this.O.setText(getString(R.string.mibi_button_pay));
    }

    private void ab() {
        this.af = Image.ThumbnailFormat.b(getResources().getDimensionPixelSize(R.dimen.mibi_pay_list_item_icon_width), 1);
        this.ae = getResources().getDrawable(R.drawable.mibi_ic_recharge_item_default);
    }

    public void N() {
        this.O.stopProgress();
        L();
    }

    public void O() {
        this.L.setVisibility(8);
        this.M.setVisibility(0);
        this.M.setText(getString(R.string.mibi_error_frozen_summary));
        this.N.setVisibility(0);
        this.O.setVisibility(8);
    }

    public void Q() {
        a((Class<? extends StepFragment>) PaymentCheckPasswordFragment.class, (Bundle) null, 4, (String) null);
    }

    public void e(Bundle bundle) {
        a((Class<? extends StepFragment>) PaymentVerifySMSCodeFragment.class, bundle, 4, (String) null);
    }

    public void f(Bundle bundle) {
        Assert.assertNotNull(bundle);
        e(bundle.getString(MibiConstants.dw));
    }

    public void g(Bundle bundle) {
        a((Class<? extends StepFragment>) PayResultFragment.class, bundle, 3, (String) null);
    }

    public void h(Bundle bundle) {
        a(RechargeAndPayTransitFragment.class, bundle, 3, (String) null, TranslucentActivity.class);
    }

    public void a(int i, String str, Throwable th) {
        a(i, str, (Bundle) null);
    }

    public void a(int i, String str, Bundle bundle) {
        this.O.stopProgress();
        Toast.makeText(getActivity(), str, 0).show();
        b(1101, bundle);
    }

    private void e(final String str) {
        a(getString(R.string.mibi_title_bind_phone), getString(R.string.mibi_summary_bind_phone), getString(R.string.mibi_button_bind_phone), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String c = MibiConstants.c(MibiConstants.bt);
                if (!TextUtils.isEmpty(str)) {
                    c = str;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(c));
                intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                intent.addFlags(8388608);
                PaymentOrderInfoFragment.this.startActivity(intent);
            }
        });
    }

    /* access modifiers changed from: private */
    public void ac() {
        a(getString(R.string.mibi_title_custom_service_phone), getString(R.string.mibi_summary_custom_service_phone) + Utils.c, getString(R.string.mibi_button_custom_service_phone), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    public void a(int i, int i2, Intent intent) {
        String paymentOrderInfoFragment = toString();
        Log.v(paymentOrderInfoFragment, this + ".doActivityResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, intent);
        b(i, i2, intent != null ? intent.getExtras() : null);
    }

    public void a(int i, int i2, Bundle bundle) {
        String paymentOrderInfoFragment = toString();
        Log.v(paymentOrderInfoFragment, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        b(i, i2, bundle);
    }

    public void a(int i, Bundle bundle) {
        String paymentOrderInfoFragment = toString();
        Log.v(paymentOrderInfoFragment, this + ".doJumpBackResult, resultCode = " + i);
        super.a(i, bundle);
        b(3, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, Bundle bundle) {
        boolean z2 = true;
        if (i == 1) {
            if (i2 == -1 && bundle != null) {
                this.S = bundle.getBoolean(MibiConstants.eF, true);
                this.T = bundle.getBoolean(MibiConstants.eH, true);
                this.U = bundle.getBoolean(MibiConstants.eK, true);
                this.ag = bundle.getInt(z, -1);
                S();
            }
        } else if (i == 2) {
            if (bundle != null) {
                this.aa = (RechargeType) bundle.getSerializable(MibiConstants.cG);
                this.ac.mLastChargeType = this.aa;
                Z();
            }
        } else if (i == 4) {
            if (i2 == -1) {
                ((PaymentOrderInfoPresenter) H_()).a(this.S, this.T, this.ag, this.U);
            } else if (i2 == 1104) {
                int i3 = bundle.getInt(MibiConstants.da);
                if (i3 == 9) {
                    this.N.setVisibility(0);
                    this.O.setVisibility(8);
                } else if (i3 == 1985) {
                    a((Class<? extends StepFragment>) PaymentCheckPasswordFragment.class, (Bundle) null, 4, (String) null);
                } else if (i3 == 7001) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("processId", this.t);
                    bundle2.putString(MibiConstants.dl, bundle.getString(MibiConstants.dl));
                    a((Class<? extends StepFragment>) PaymentVerifySMSCodeFragment.class, bundle2, 4, (String) null);
                } else if (i3 == 7002) {
                    e(bundle.getString(MibiConstants.dw));
                }
            }
        } else if (i != 3) {
        } else {
            if (i2 == 1103) {
                this.b.m().a(this.t, "price", (Object) Long.valueOf(this.Q));
                if (bundle != null) {
                    z2 = bundle.getBoolean(MibiConstants.hi, true);
                }
                if (z2) {
                    a((Class<? extends StepFragment>) PayQuickQueryFragment.class, (Bundle) null, 0, (String) null);
                } else {
                    a((Class<? extends StepFragment>) PayWaitQueryFragment.class, (Bundle) null, 0, (String) null);
                }
            } else if (i2 == 0) {
                c(0);
            } else if (i2 == 1005) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_process_fail), 0).show();
                b(1101, bundle);
            } else if (i2 == 1001) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_result_fail), 0).show();
                b(1101, bundle);
            } else if (i2 == 1002) {
                b(1101, bundle);
                E();
            } else if (i2 == 1003) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_success_pay_fail), 0).show();
                c(1102);
                E();
            } else if (i2 == 1004 || i2 == 1100) {
                b(1100, bundle);
                E();
            } else {
                if (bundle != null) {
                    int i4 = bundle.getInt(MibiConstants.da);
                    Toast.makeText(getActivity(), getString(R.string.mibi_progress_error_pay_title) + i4, 0).show();
                }
                b(1101, bundle);
            }
        }
    }
}
