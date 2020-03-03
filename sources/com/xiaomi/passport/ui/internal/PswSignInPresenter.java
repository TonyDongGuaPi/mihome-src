package com.xiaomi.passport.ui.internal;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.passport.ui.internal.PswSignInContract;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010!\u001a\u00020\u0005H\u0016J0\u0010%\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u001eH\u0016R\u000e\u0010\n\u001a\u00020\u0005XD¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006."}, d2 = {"Lcom/xiaomi/passport/ui/internal/PswSignInPresenter;", "Lcom/xiaomi/passport/ui/internal/PswSignInContract$Presenter;", "context", "Landroid/content/Context;", "sid", "", "view", "Lcom/xiaomi/passport/ui/internal/PswSignInContract$View;", "name", "(Landroid/content/Context;Ljava/lang/String;Lcom/xiaomi/passport/ui/internal/PswSignInContract$View;Ljava/lang/String;)V", "TAG", "getContext", "()Landroid/content/Context;", "getName", "()Ljava/lang/String;", "provider", "Lcom/xiaomi/passport/ui/internal/AuthProvider;", "getProvider", "()Lcom/xiaomi/passport/ui/internal/AuthProvider;", "setProvider", "(Lcom/xiaomi/passport/ui/internal/AuthProvider;)V", "getSid", "getView", "()Lcom/xiaomi/passport/ui/internal/PswSignInContract$View;", "getSignedInUserIds", "", "()[Ljava/lang/String;", "saveSignedInUserId", "", "credential", "Lcom/xiaomi/passport/ui/internal/IdPswBaseAuthCredential;", "signInIdAndPsw", "id", "psw", "signInPhoneAndPsw", "phone", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "signInVStep2", "step1Token", "metaLoginData", "Lcom/xiaomi/accountsdk/account/data/MetaLoginData;", "step2code", "trustCurrentEnv", "", "signInWithAuthCredential", "authCredential", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PswSignInPresenter implements PswSignInContract.Presenter {
    /* access modifiers changed from: private */
    public final String TAG;
    @NotNull
    private final Context context;
    @NotNull
    private final String name;
    @NotNull
    private AuthProvider provider;
    @NotNull
    private final String sid;
    @NotNull
    private final PswSignInContract.View view;

    public void signInPhoneAndPsw(@NotNull PhoneWrapper phoneWrapper, @NotNull String str) {
        Intrinsics.f(phoneWrapper, "phone");
        Intrinsics.f(str, "psw");
    }

    public PswSignInPresenter(@NotNull Context context2, @NotNull String str, @NotNull PswSignInContract.View view2, @NotNull String str2) {
        Intrinsics.f(context2, "context");
        Intrinsics.f(str, "sid");
        Intrinsics.f(view2, "view");
        Intrinsics.f(str2, "name");
        this.context = context2;
        this.sid = str;
        this.view = view2;
        this.name = str2;
        this.TAG = "PswSignIn";
        AuthProvider provider2 = PassportUI.INSTANCE.getProvider(this.name);
        if (provider2 == null) {
            Intrinsics.a();
        }
        this.provider = provider2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PswSignInPresenter(Context context2, String str, PswSignInContract.View view2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, str, view2, (i & 8) != 0 ? PassportUI.ID_PSW_AUTH_PROVIDER : str2);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getSid() {
        return this.sid;
    }

    @NotNull
    public final PswSignInContract.View getView() {
        return this.view;
    }

    @NotNull
    public final AuthProvider getProvider() {
        return this.provider;
    }

    public final void setProvider(@NotNull AuthProvider authProvider) {
        Intrinsics.f(authProvider, "<set-?>");
        this.provider = authProvider;
    }

    public void signInIdAndPsw(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "id");
        Intrinsics.f(str2, "psw");
        signInWithAuthCredential(new IdPswAuthCredential(str, str2, this.sid));
    }

    public void signInVStep2(@NotNull String str, @NotNull String str2, @NotNull MetaLoginData metaLoginData, @NotNull String str3, boolean z) {
        Intrinsics.f(str, "id");
        Intrinsics.f(str2, "step1Token");
        Intrinsics.f(metaLoginData, "metaLoginData");
        Intrinsics.f(str3, "step2code");
        signInWithAuthCredential(new IdPswVStep2AuthCredential(str, str2, metaLoginData, str3, z, this.sid));
    }

    public void signInWithAuthCredential(@NotNull IdPswBaseAuthCredential idPswBaseAuthCredential) {
        Intrinsics.f(idPswBaseAuthCredential, "authCredential");
        this.view.showProgress();
        this.provider.signInAndStoreAccount(this.context, idPswBaseAuthCredential).get(new PswSignInPresenter$signInWithAuthCredential$1(this, idPswBaseAuthCredential), new PswSignInPresenter$signInWithAuthCredential$2(this, idPswBaseAuthCredential));
    }

    public final void saveSignedInUserId(@NotNull IdPswBaseAuthCredential idPswBaseAuthCredential) {
        Intrinsics.f(idPswBaseAuthCredential, "credential");
        HashSet q = ArraysKt.q((T[]) getSignedInUserIds());
        q.add(idPswBaseAuthCredential.getId());
        this.context.getSharedPreferences("passport_ui", 0).edit().putStringSet("sign_in_user_id", q).apply();
    }

    @NotNull
    public String[] getSignedInUserIds() {
        Set<String> stringSet = this.context.getSharedPreferences("passport_ui", 0).getStringSet("sign_in_user_id", new HashSet());
        Intrinsics.b(stringSet, "sf.getStringSet(\"sign_in…r_id\", HashSet<String>())");
        Collection collection = stringSet;
        if (collection != null) {
            Object[] array = collection.toArray(new String[0]);
            if (array != null) {
                return (String[]) array;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }
}
