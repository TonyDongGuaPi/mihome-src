package com.mi.global.shop.buy;

import android.util.Log;
import com.mi.global.shop.newmodel.GetOtpResult;
import com.mi.global.shop.request.SimpleCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"com/mi/global/shop/buy/BFLVerifyOTPFragment$getOtpSend$callback$1", "Lcom/mi/global/shop/request/SimpleCallback;", "Lcom/mi/global/shop/newmodel/GetOtpResult;", "error", "", "errmsg", "", "success", "result", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment$getOtpSend$callback$1 extends SimpleCallback<GetOtpResult> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BFLVerifyOTPFragment f6794a;

    BFLVerifyOTPFragment$getOtpSend$callback$1(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        this.f6794a = bFLVerifyOTPFragment;
    }

    public void a(@Nullable GetOtpResult getOtpResult) {
        this.f6794a.b();
        if (getOtpResult != null && getOtpResult.data != null) {
            this.f6794a.q = getOtpResult.data.transactionCode;
        }
    }

    public void a(@NotNull String str) {
        Intrinsics.f(str, "errmsg");
        super.a(str);
        this.f6794a.b();
        Log.e("zhangrr", "BFLVerifyOTPFragment.error() called. " + str);
    }
}
