package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.snscorelib.internal.entity.SNSBindParameter;
import com.xiaomi.passport.snscorelib.internal.request.SNSRequest;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "invoke"}, k = 3, mv = {1, 1, 10})
final class SNSAuthProvider$signInWithAuthCredential$1 extends Lambda implements Function0<AccountInfo> {
    final /* synthetic */ Context $context;
    final /* synthetic */ AuthCredential $credential;
    final /* synthetic */ SNSAuthProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SNSAuthProvider$signInWithAuthCredential$1(SNSAuthProvider sNSAuthProvider, AuthCredential authCredential, Context context) {
        super(0);
        this.this$0 = sNSAuthProvider;
        this.$credential = authCredential;
        this.$context = context;
    }

    @NotNull
    public final AccountInfo invoke() {
        try {
            AuthCredential authCredential = this.$credential;
            if (authCredential instanceof SnsCodeAuthCredential) {
                return this.this$0.signInWithSnsCodeAuthCredential(this.$context, (SnsCodeAuthCredential) this.$credential);
            }
            if (authCredential instanceof SnsTokenAuthCredential) {
                return this.this$0.signInWithSnsTokenAuthCredential(this.$context, (SnsTokenAuthCredential) this.$credential);
            }
            throw new IllegalStateException("not support originAuthCredential:" + this.$credential);
        } catch (SNSRequest.NeedLoginForBindException e) {
            SNSAuthProvider sNSAuthProvider = this.this$0;
            SNSBindParameter sNSBindParameter = e.getSNSBindParameter();
            Intrinsics.b(sNSBindParameter, "e.snsBindParameter");
            sNSAuthProvider.storeBindParameter(sNSBindParameter);
            throw e;
        }
    }
}
