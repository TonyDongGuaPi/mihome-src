package com.mi.global.shop.buy;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.mi.global.shop.activity.PayResultWebActivity;
import com.mi.global.shop.newmodel.pay.payinfo.NewPayGoBFLResult;
import com.mi.global.shop.request.SimpleCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"com/mi/global/shop/buy/BFLVerifyOTPFragment$realRay$callback$1", "Lcom/mi/global/shop/request/SimpleCallback;", "Lcom/mi/global/shop/newmodel/pay/payinfo/NewPayGoBFLResult;", "error", "", "errmsg", "", "success", "result", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment$realRay$callback$1 extends SimpleCallback<NewPayGoBFLResult> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BFLVerifyOTPFragment f6795a;

    BFLVerifyOTPFragment$realRay$callback$1(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        this.f6795a = bFLVerifyOTPFragment;
    }

    public void a(@Nullable NewPayGoBFLResult newPayGoBFLResult) {
        this.f6795a.b();
        NewPayGoBFLResult.NewPayGoResultData newPayGoResultData = newPayGoBFLResult != null ? newPayGoBFLResult.data : null;
        if (newPayGoResultData != null && this.f6795a.getActivity() != null && !TextUtils.isEmpty(newPayGoResultData.params)) {
            NewPayGoBFLResult.Params params = (NewPayGoBFLResult.Params) new Gson().fromJson(newPayGoResultData.params, NewPayGoBFLResult.Params.class);
            if (params != null && !TextUtils.isEmpty(params.url)) {
                Intent intent = new Intent(this.f6795a.getActivity(), PayResultWebActivity.class);
                intent.putExtra("url", params.url);
                FragmentActivity activity = this.f6795a.getActivity();
                if (activity != null) {
                    activity.startActivityForResult(intent, 101);
                }
            } else if (params != null && !TextUtils.isEmpty(params.code)) {
                this.f6795a.a(params.code, params.msg);
            }
        }
    }

    public void a(@NotNull String str) {
        Intrinsics.f(str, "errmsg");
        super.a(str);
        this.f6795a.b();
    }
}
