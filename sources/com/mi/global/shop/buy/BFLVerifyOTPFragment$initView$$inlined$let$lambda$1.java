package com.mi.global.shop.buy;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J*\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0016J*\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000e¸\u0006\u0000"}, d2 = {"com/mi/global/shop/buy/BFLVerifyOTPFragment$initView$1$1", "Landroid/text/TextWatcher;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment$initView$$inlined$let$lambda$1 implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BFLVerifyOTPFragment f6790a;

    public void afterTextChanged(@Nullable Editable editable) {
    }

    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
    }

    BFLVerifyOTPFragment$initView$$inlined$let$lambda$1(BFLVerifyOTPFragment bFLVerifyOTPFragment) {
        this.f6790a = bFLVerifyOTPFragment;
    }

    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        BFLVerifyOTPFragment.a(this.f6790a).setEnabled(charSequence != null && charSequence.length() == 6);
    }
}
