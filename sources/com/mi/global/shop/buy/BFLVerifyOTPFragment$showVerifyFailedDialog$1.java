package com.mi.global.shop.buy;

import com.mi.global.shop.R;
import com.mi.global.shop.voice.dialog.BaseDialogFragment;
import com.mi.global.shop.voice.dialog.ViewConvertListener;
import com.mi.global.shop.voice.dialog.ViewHolder;
import com.mobikwik.sdk.lib.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/mi/global/shop/buy/BFLVerifyOTPFragment$showVerifyFailedDialog$1", "Lcom/mi/global/shop/voice/dialog/ViewConvertListener;", "convertView", "", "holder", "Lcom/mi/global/shop/voice/dialog/ViewHolder;", "dialogFragment", "Lcom/mi/global/shop/voice/dialog/BaseDialogFragment;", "shopsdk_release"}, k = 1, mv = {1, 1, 15})
public final class BFLVerifyOTPFragment$showVerifyFailedDialog$1 implements ViewConvertListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BFLVerifyOTPFragment f6796a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    BFLVerifyOTPFragment$showVerifyFailedDialog$1(BFLVerifyOTPFragment bFLVerifyOTPFragment, String str, String str2) {
        this.f6796a = bFLVerifyOTPFragment;
        this.b = str;
        this.c = str2;
    }

    public void a(@NotNull ViewHolder viewHolder, @NotNull BaseDialogFragment baseDialogFragment) {
        Intrinsics.f(viewHolder, Constants.HOLDER);
        Intrinsics.f(baseDialogFragment, "dialogFragment");
        viewHolder.a(R.id.tv_failed_go_back, (Function0<Unit>) new BFLVerifyOTPFragment$showVerifyFailedDialog$1$convertView$1(this, baseDialogFragment));
        if (this.c != null) {
            viewHolder.a(R.id.tv_failed_reason, this.c);
        }
    }
}
