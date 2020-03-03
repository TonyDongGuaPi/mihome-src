package com.xiaomi.passport.ui.internal;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/graphics/Bitmap;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PhTicketSignInFragment$chooseToSignInOrSignUp$1 extends Lambda implements Function1<Bitmap, Unit> {
    final /* synthetic */ View $view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhTicketSignInFragment$chooseToSignInOrSignUp$1(View view) {
        super(1);
        this.$view = view;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Bitmap) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Bitmap bitmap) {
        Intrinsics.f(bitmap, "it");
        View view = this.$view;
        ImageView imageView = (ImageView) (view != null ? view.findViewById(R.id.image_user_avatar) : null);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
