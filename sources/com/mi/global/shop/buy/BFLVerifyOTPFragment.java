package com.mi.global.shop.buy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import com.android.volley.DefaultRetryPolicy;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.newmodel.GetOtpResult;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayGoBFLResult;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.ui.BaseFragment;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.voice.dialog.BaseDialogFragment;
import com.mi.global.shop.voice.dialog.ViewConvertListener;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.log.LogUtil;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 12\u00020\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0006\u0010\u001e\u001a\u00020\u001dJ\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\u0012\u0010!\u001a\u00020\u001d2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J&\u0010$\u001a\u0004\u0018\u00010\u00192\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u001a\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u00192\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010+\u001a\u00020\u001dH\u0002J\u0006\u0010,\u001a\u00020\u001dJ\u001c\u0010-\u001a\u00020\u001d2\b\u0010.\u001a\u0004\u0018\u00010\b2\b\u0010/\u001a\u0004\u0018\u00010\bH\u0002J\b\u00100\u001a\u00020\u001dH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/mi/global/shop/buy/BFLVerifyOTPFragment;", "Lcom/mi/global/shop/ui/BaseFragment;", "()V", "loadingDialog", "Landroid/app/ProgressDialog;", "mBFLOtpTips", "Lcom/mi/global/shop/widget/CustomTextView;", "mCardNum", "", "mConfirmPayNow", "Lcom/mi/global/shop/widget/CustomButtonView;", "mConstraintLayout", "Landroid/support/constraint/ConstraintLayout;", "mCountDownTime", "mEmiCode", "mGateWay", "mLastFourNumPhone", "mOtpNum", "Lcom/mi/global/shop/widget/CustomEditTextView;", "mReGetCountDownTimer", "Landroid/os/CountDownTimer;", "mResendOTP", "mTerms", "mTransactionCode", "mView", "Landroid/view/View;", "getHeight", "", "getOtpSend", "", "hideLoading", "initView", "initViewState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "realRay", "showLoading", "showVerifyFailedDialog", "failedType", "failedMsg", "startCountDown", "Companion", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment extends BaseFragment {
    private static final DefaultRetryPolicy A = new DefaultRetryPolicy(30000, 0, 1.0f);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f6789a = "card_num";
    @NotNull
    public static final String b = "emi_code";
    @NotNull
    public static final String c = "terms";
    @NotNull
    public static final String d = "gateway";
    @NotNull
    public static final String e = "last_four_mobile_num";
    @NotNull
    public static final String f = "transaction_code";
    public static final Companion g = new Companion((DefaultConstructorMarker) null);
    private static final long x = 60000;
    private static final String y = "BFLVerifyOTPFragment";
    private static final String z = "OTP_verifcation";
    private HashMap B;
    /* access modifiers changed from: private */
    public CountDownTimer i;
    /* access modifiers changed from: private */
    public CustomTextView j;
    private CustomTextView k;
    /* access modifiers changed from: private */
    public CustomButtonView l;
    /* access modifiers changed from: private */
    public CustomTextView m;
    /* access modifiers changed from: private */
    public CustomEditTextView n;
    private ConstraintLayout o;
    private String p;
    /* access modifiers changed from: private */
    public String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private View v;
    private ProgressDialog w;

    public View a(int i2) {
        if (this.B == null) {
            this.B = new HashMap();
        }
        View view = (View) this.B.get(Integer.valueOf(i2));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i2);
        this.B.put(Integer.valueOf(i2), findViewById);
        return findViewById;
    }

    public void c() {
        if (this.B != null) {
            this.B.clear();
        }
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        c();
    }

    public static final /* synthetic */ CustomButtonView a(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        CustomButtonView customButtonView = bFLVerifyOTPFragment.l;
        if (customButtonView == null) {
            Intrinsics.c("mConfirmPayNow");
        }
        return customButtonView;
    }

    public static final /* synthetic */ CustomTextView g(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        CustomTextView customTextView = bFLVerifyOTPFragment.m;
        if (customTextView == null) {
            Intrinsics.c("mCountDownTime");
        }
        return customTextView;
    }

    public static final /* synthetic */ CustomTextView h(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        CustomTextView customTextView = bFLVerifyOTPFragment.j;
        if (customTextView == null) {
            Intrinsics.c("mResendOTP");
        }
        return customTextView;
    }

    public static final /* synthetic */ CustomEditTextView i(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        CustomEditTextView customEditTextView = bFLVerifyOTPFragment.n;
        if (customEditTextView == null) {
            Intrinsics.c("mOtpNum");
        }
        return customEditTextView;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/mi/global/shop/buy/BFLVerifyOTPFragment$Companion;", "", "()V", "ALL_COUNT_DOWM_TIME", "", "CARD_NUM", "", "EMI_CODE", "GATEWAY", "LAST_FOUR_NUMBER", "PAGE_ID", "TAG", "TERMS", "TRANSACTION_CODE", "defaultRetryPolicy", "Lcom/android/volley/DefaultRetryPolicy;", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public void onCreate(@Nullable Bundle bundle) {
        LogUtil.a(y, "onCreate");
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.f(layoutInflater, "inflater");
        LogUtil.b(y, "onCreateView");
        if (this.v == null) {
            this.v = layoutInflater.inflate(R.layout.shop_activity_bfl_otp_verify, viewGroup, false);
        } else {
            View view = this.v;
            ViewParent parent = view != null ? view.getParent() : null;
            if (!(parent instanceof ViewGroup)) {
                parent = null;
            }
            ViewGroup viewGroup2 = (ViewGroup) parent;
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.v);
            }
        }
        return this.v;
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        if (getActivity() != null) {
            h();
            f();
        }
        super.onViewCreated(view, bundle);
    }

    /* access modifiers changed from: private */
    public final void f() {
        CustomButtonView customButtonView = this.l;
        if (customButtonView == null) {
            Intrinsics.c("mConfirmPayNow");
        }
        customButtonView.setEnabled(false);
        CustomTextView customTextView = this.j;
        if (customTextView == null) {
            Intrinsics.c("mResendOTP");
        }
        customTextView.setEnabled(false);
        CustomTextView customTextView2 = this.m;
        if (customTextView2 == null) {
            Intrinsics.c("mCountDownTime");
        }
        customTextView2.setVisibility(0);
        CustomEditTextView customEditTextView = this.n;
        if (customEditTextView == null) {
            Intrinsics.c("mOtpNum");
        }
        customEditTextView.setText("");
        CustomTextView customTextView3 = this.j;
        if (customTextView3 == null) {
            Intrinsics.c("mResendOTP");
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            customTextView3.setTextColor(ContextCompat.getColor(activity, R.color.title_text_color));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
    }

    private final int g() {
        Context context = getContext();
        Object systemService = context != null ? context.getSystemService("window") : null;
        if (systemService != null) {
            Point point = new Point();
            ((WindowManager) systemService).getDefaultDisplay().getSize(point);
            return point.y;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    private final void h() {
        View view = this.v;
        if (view != null) {
            View findViewById = view.findViewById(R.id.tv_tips_otp);
            if (findViewById != null) {
                this.k = (CustomTextView) findViewById;
                View findViewById2 = view.findViewById(R.id.btn_confirm_pay_now);
                if (findViewById2 != null) {
                    this.l = (CustomButtonView) findViewById2;
                    View findViewById3 = view.findViewById(R.id.tv_resend_otp);
                    if (findViewById3 != null) {
                        this.j = (CustomTextView) findViewById3;
                        View findViewById4 = view.findViewById(R.id.tv_countdown_otp_second);
                        if (findViewById4 != null) {
                            this.m = (CustomTextView) findViewById4;
                            View findViewById5 = view.findViewById(R.id.et_otp_num);
                            if (findViewById5 != null) {
                                this.n = (CustomEditTextView) findViewById5;
                                View findViewById6 = view.findViewById(R.id.cl_tv_otp_xiaomi);
                                if (findViewById6 != null) {
                                    this.o = (ConstraintLayout) findViewById6;
                                    ConstraintLayout constraintLayout = this.o;
                                    if (constraintLayout == null) {
                                        Intrinsics.c("mConstraintLayout");
                                    }
                                    ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
                                    if (layoutParams != null) {
                                        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                                        layoutParams2.topMargin = g() - DensityUtil.a(100.0f);
                                        ConstraintLayout constraintLayout2 = this.o;
                                        if (constraintLayout2 == null) {
                                            Intrinsics.c("mConstraintLayout");
                                        }
                                        constraintLayout2.setLayoutParams(layoutParams2);
                                        Bundle arguments = getArguments();
                                        if (arguments != null) {
                                            this.r = arguments.getString(f6789a);
                                            this.s = arguments.getString(b);
                                            this.t = arguments.getString(c);
                                            this.u = arguments.getString(d);
                                            this.p = arguments.getString(e);
                                            this.q = arguments.getString(f);
                                        }
                                        FragmentActivity activity = getActivity();
                                        String str = null;
                                        if (activity != null) {
                                            Intrinsics.b(activity, "activity");
                                            Resources resources = activity.getResources();
                                            if (resources != null) {
                                                str = resources.getString(R.string.bfl_otp_tips);
                                            }
                                        }
                                        if (str == null) {
                                            str = "";
                                        }
                                        CustomTextView customTextView = this.k;
                                        if (customTextView == null) {
                                            Intrinsics.c("mBFLOtpTips");
                                        }
                                        StringCompanionObject stringCompanionObject = StringCompanionObject.f2835a;
                                        Object[] objArr = {this.p};
                                        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
                                        Intrinsics.b(format, "java.lang.String.format(format, *args)");
                                        customTextView.setText(Html.fromHtml(format));
                                        CustomEditTextView customEditTextView = this.n;
                                        if (customEditTextView == null) {
                                            Intrinsics.c("mOtpNum");
                                        }
                                        customEditTextView.addTextChangedListener(new BFLVerifyOTPFragment$initView$$inlined$let$lambda$1(this));
                                        CustomTextView customTextView2 = this.j;
                                        if (customTextView2 == null) {
                                            Intrinsics.c("mResendOTP");
                                        }
                                        customTextView2.setOnClickListener(new BFLVerifyOTPFragment$initView$$inlined$let$lambda$2(this));
                                        CustomButtonView customButtonView = this.l;
                                        if (customButtonView == null) {
                                            Intrinsics.c("mConfirmPayNow");
                                        }
                                        customButtonView.setOnClickListener(new BFLVerifyOTPFragment$initView$$inlined$let$lambda$3(this));
                                    } else {
                                        throw new TypeCastException("null cannot be cast to non-null type android.support.constraint.ConstraintLayout.LayoutParams");
                                    }
                                } else {
                                    throw new TypeCastException("null cannot be cast to non-null type android.support.constraint.ConstraintLayout");
                                }
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.widget.CustomEditTextView");
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.widget.CustomTextView");
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.widget.CustomTextView");
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.widget.CustomButtonView");
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.widget.CustomTextView");
            }
        }
        k();
    }

    /* access modifiers changed from: private */
    public final void i() {
        a();
        MiShopStatInterface.a("pay_click", z);
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bi()).buildUpon();
        Context context = getContext();
        if (context != null) {
            buildUpon.appendQueryParameter("id", ((ConfirmActivity) context).getconfirmOrder().f6881a);
            buildUpon.appendQueryParameter("bank", this.u);
            buildUpon.appendQueryParameter("type", Constants.PAYTYPE_EMI);
            buildUpon.appendQueryParameter("checkcode", this.r);
            buildUpon.appendQueryParameter("emibank", Cards.k);
            buildUpon.appendQueryParameter(PayU.j, this.s);
            buildUpon.appendQueryParameter(c, this.t);
            buildUpon.appendQueryParameter("transactioncode", this.q);
            CustomEditTextView customEditTextView = this.n;
            if (customEditTextView == null) {
                Intrinsics.c("mOtpNum");
            }
            buildUpon.appendQueryParameter("phonecode", customEditTextView.getText().toString());
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewPayGoBFLResult.class, (Map<String, String>) null, new BFLVerifyOTPFragment$realRay$callback$1(this));
            simpleProtobufRequest.setTag(y);
            simpleProtobufRequest.setRetryPolicy(A);
            RequestQueueUtil.a().add(simpleProtobufRequest);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.buy.ConfirmActivity");
    }

    /* access modifiers changed from: private */
    public final void j() {
        MiShopStatInterface.a("resend_otp_click", z);
        a();
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.bg()).buildUpon();
        Context context = getContext();
        if (context != null) {
            buildUpon.appendQueryParameter("id", ((ConfirmActivity) context).getconfirmOrder().f6881a);
            buildUpon.appendQueryParameter(d, this.u);
            buildUpon.appendQueryParameter("cardcode", this.r);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), GetOtpResult.class, (Map<String, String>) null, new BFLVerifyOTPFragment$getOtpSend$callback$1(this));
            simpleProtobufRequest.setTag(y);
            simpleProtobufRequest.setRetryPolicy(A);
            RequestQueueUtil.a().add(simpleProtobufRequest);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.buy.ConfirmActivity");
    }

    /* access modifiers changed from: private */
    public final void a(String str, String str2) {
        if (getActivity() != null) {
            BaseDialogFragment d2 = BFLDialogFragment.f6788a.a().a((ViewConvertListener) new BFLVerifyOTPFragment$showVerifyFailedDialog$1(this, str, str2)).a(R.layout.shop_layout_dialog_bfl_otp_failed).d(30);
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.a();
            }
            Intrinsics.b(activity, "activity!!");
            FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
            Intrinsics.b(supportFragmentManager, "activity!!.supportFragmentManager");
            d2.a(supportFragmentManager);
        }
    }

    /* access modifiers changed from: private */
    public final void k() {
        CountDownTimer countDownTimer = this.i;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CustomEditTextView customEditTextView = this.n;
        if (customEditTextView == null) {
            Intrinsics.c("mOtpNum");
        }
        customEditTextView.setText("");
        if (this.v != null) {
            this.i = new BFLVerifyOTPFragment$startCountDown$$inlined$let$lambda$1(60000, 1000, this).start();
        }
    }

    public final void a() {
        ProgressDialog progressDialog;
        if (BaseActivity.isActivityAlive(getActivity()) && (progressDialog = this.w) != null) {
            progressDialog.show();
        }
    }

    public final void b() {
        if (BaseActivity.isActivityAlive(getActivity()) && isAdded() && this.w != null) {
            ProgressDialog progressDialog = this.w;
            if (progressDialog == null) {
                Intrinsics.a();
            }
            if (progressDialog.isShowing()) {
                ProgressDialog progressDialog2 = this.w;
                if (progressDialog2 == null) {
                    Intrinsics.a();
                }
                progressDialog2.dismiss();
            }
        }
    }
}
