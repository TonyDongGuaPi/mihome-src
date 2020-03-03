package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.passport.v2.utils.ActivatorPhoneController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class PassportRepoImpl$invalidateCachePhoneNum$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $slotId;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportRepoImpl$invalidateCachePhoneNum$1(Context context, int i) {
        super(0);
        this.$context = context;
        this.$slotId = i;
    }

    public final void invoke() {
        new ActivatorPhoneController(this.$context).invalidateCathePhoneNum(this.$slotId);
    }
}
