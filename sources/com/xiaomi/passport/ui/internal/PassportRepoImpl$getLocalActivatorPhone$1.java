package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.passport.v2.utils.ActivatorPhoneController;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PassportRepoImpl$getLocalActivatorPhone$1 extends Lambda implements Function0<List<? extends ActivatorPhoneInfo>> {
    final /* synthetic */ Context $context;
    final /* synthetic */ boolean $useLocalCache;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportRepoImpl$getLocalActivatorPhone$1(Context context, boolean z) {
        super(0);
        this.$context = context;
        this.$useLocalCache = z;
    }

    @NotNull
    public final List<ActivatorPhoneInfo> invoke() {
        try {
            Object obj = new ActivatorPhoneController(this.$context).getLocalActivatorPhone(new PassportRepoImpl$getLocalActivatorPhone$1$future$1(), this.$useLocalCache).get();
            if (obj != null) {
                return (List) obj;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo>");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                Intrinsics.a();
            }
            throw cause;
        }
    }
}
