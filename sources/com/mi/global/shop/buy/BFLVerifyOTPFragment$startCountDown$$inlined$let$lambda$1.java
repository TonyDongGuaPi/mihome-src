package com.mi.global.shop.buy;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007¸\u0006\u0000"}, d2 = {"com/mi/global/shop/buy/BFLVerifyOTPFragment$startCountDown$1$1", "Landroid/os/CountDownTimer;", "onFinish", "", "onTick", "millisUntilFinished", "", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment$startCountDown$$inlined$let$lambda$1 extends CountDownTimer {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BFLVerifyOTPFragment f6793a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BFLVerifyOTPFragment$startCountDown$$inlined$let$lambda$1(long j, long j2, BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        super(j, j2);
        this.f6793a = bFLVerifyOTPFragment;
    }

    public void onFinish() {
        BFLVerifyOTPFragment.g(this.f6793a).setVisibility(8);
        BFLVerifyOTPFragment.h(this.f6793a).setEnabled(true);
        FragmentActivity activity = this.f6793a.getActivity();
        if (activity != null) {
            Intrinsics.b(activity, "it");
            if (!activity.isFinishing()) {
                BFLVerifyOTPFragment.h(this.f6793a).setTextColor(ContextCompat.getColor(activity, R.color.bfl_resend_otp));
            }
        }
        this.f6793a.i = null;
    }

    public void onTick(long j) {
        CustomTextView g = BFLVerifyOTPFragment.g(this.f6793a);
        StringBuilder sb = new StringBuilder();
        sb.append(j / ((long) 1000));
        sb.append('S');
        g.setText(sb.toString());
    }
}
