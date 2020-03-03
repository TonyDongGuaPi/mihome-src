package com.mi.global.shop.buy;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import com.mi.global.shop.R;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.voice.dialog.BaseDialogFragment;
import com.mi.global.shop.widget.CustomTextView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
final class BFLVerifyOTPFragment$showVerifyFailedDialog$1$convertView$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ BaseDialogFragment $dialogFragment;
    final /* synthetic */ BFLVerifyOTPFragment$showVerifyFailedDialog$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BFLVerifyOTPFragment$showVerifyFailedDialog$1$convertView$1(BFLVerifyOTPFragment$showVerifyFailedDialog$1 bFLVerifyOTPFragment$showVerifyFailedDialog$1, BaseDialogFragment baseDialogFragment) {
        super(0);
        this.this$0 = bFLVerifyOTPFragment$showVerifyFailedDialog$1;
        this.$dialogFragment = baseDialogFragment;
    }

    public final void invoke() {
        this.$dialogFragment.dismiss();
        MiShopStatInterface.a("go_back_click", "OTP_verifcation", "key", this.this$0.b);
        if (Intrinsics.a((Object) this.this$0.b, (Object) "4") || Intrinsics.a((Object) this.this$0.b, (Object) "2")) {
            this.this$0.f6796a.f();
            BFLVerifyOTPFragment.g(this.this$0.f6796a).setVisibility(8);
            FragmentActivity activity = this.this$0.f6796a.getActivity();
            if (activity != null) {
                ((ConfirmActivity) activity).onBackPressed();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.mi.global.shop.buy.ConfirmActivity");
        } else if (Intrinsics.a((Object) this.this$0.b, (Object) "1")) {
            CustomTextView h = BFLVerifyOTPFragment.h(this.this$0.f6796a);
            FragmentActivity activity2 = this.this$0.f6796a.getActivity();
            if (activity2 != null) {
                h.setTextColor(ContextCompat.getColor(activity2, R.color.bfl_resend_otp));
                BFLVerifyOTPFragment.h(this.this$0.f6796a).setEnabled(true);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
        } else if (Intrinsics.a((Object) this.this$0.b, (Object) "3")) {
            BFLVerifyOTPFragment.i(this.this$0.f6796a).setText("");
            CustomTextView h2 = BFLVerifyOTPFragment.h(this.this$0.f6796a);
            FragmentActivity activity3 = this.this$0.f6796a.getActivity();
            if (activity3 != null) {
                h2.setTextColor(ContextCompat.getColor(activity3, R.color.title_text_color));
                BFLVerifyOTPFragment.h(this.this$0.f6796a).setEnabled(false);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.content.Context");
        }
    }
}
