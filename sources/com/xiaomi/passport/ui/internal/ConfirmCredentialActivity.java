package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, d2 = {"Lcom/xiaomi/passport/ui/internal/ConfirmCredentialActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setAddAccountResultAndFinish", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class ConfirmCredentialActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Context context = this;
        AuthenticatorUtil.removeAccount(context, AuthenticatorUtil.getXiaomiAccount(context));
        setAddAccountResultAndFinish();
    }

    private final void setAddAccountResultAndFinish() {
        Parcelable parcelableExtra = getIntent().getParcelableExtra("accountAuthenticatorResponse");
        Intrinsics.b(parcelableExtra, "intent.getParcelableExtr…T_AUTHENTICATOR_RESPONSE)");
        AuthenticatorUtil.handleAccountAuthenticatorResponse(parcelableExtra, AccountHelper.getAccountAuthenticatorResponseResult(0, (AccountInfo) null, getIntent().getBooleanExtra("need_retry_on_authenticator_response_result", false)));
        finish();
    }
}
