package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.xiaomi.passport.ui.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J*\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0017H\u0016J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/xiaomi/passport/ui/internal/FaceBookAuthProvider;", "Lcom/xiaomi/passport/ui/internal/SNSAuthProvider;", "Lcom/facebook/FacebookCallback;", "Lcom/facebook/login/LoginResult;", "()V", "EMAIL", "", "PUBLIC_PROFILE", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "mScopes", "", "sCallbackManager", "Lcom/facebook/CallbackManager;", "getAppId", "getIconRes", "", "getRequestCode", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCancel", "onError", "error", "Lcom/facebook/FacebookException;", "onSuccess", "result", "startLogin", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class FaceBookAuthProvider extends SNSAuthProvider implements FacebookCallback<LoginResult> {
    private final String EMAIL = "email";
    private final String PUBLIC_PROFILE = "public_profile";
    @NotNull
    public Context context;
    private final List<String> mScopes = new ArrayList();
    private CallbackManager sCallbackManager;

    public FaceBookAuthProvider() {
        super(PassportUI.FACEBOOK_AUTH_PROVIDER);
    }

    @NotNull
    public final Context getContext() {
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.c("context");
        }
        return context2;
    }

    public final void setContext(@NotNull Context context2) {
        Intrinsics.f(context2, "<set-?>");
        this.context = context2;
    }

    @NotNull
    public String getAppId(@NotNull Context context2) {
        Intrinsics.f(context2, "context");
        String string = context2.getString(R.string.facebook_application_id);
        Intrinsics.b(string, "context.getString(R.stri….facebook_application_id)");
        return string;
    }

    public int getIconRes() {
        return R.drawable.sns_facebook_logo;
    }

    public int getRequestCode() {
        return CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode();
    }

    /* access modifiers changed from: protected */
    public void startLogin(@NotNull Activity activity) {
        Intrinsics.f(activity, "activity");
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.b(applicationContext, "activity.applicationContext");
        this.context = applicationContext;
        CallbackManager create = CallbackManager.Factory.create();
        Intrinsics.b(create, "CallbackManager.Factory.create()");
        this.sCallbackManager = create;
        LoginManager instance = LoginManager.getInstance();
        CallbackManager callbackManager = this.sCallbackManager;
        if (callbackManager == null) {
            Intrinsics.c("sCallbackManager");
        }
        instance.registerCallback(callbackManager, this);
        ArrayList arrayList = new ArrayList(this.mScopes);
        if (!arrayList.contains(this.EMAIL)) {
            arrayList.add(this.EMAIL);
        }
        if (!arrayList.contains(this.PUBLIC_PROFILE)) {
            arrayList.add(this.PUBLIC_PROFILE);
        }
        instance.logInWithReadPermissions(activity, (Collection<String>) arrayList);
    }

    public void onCancel() {
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.c("context");
        }
        Toast.makeText(context2, "onCancel", 0).show();
    }

    public void onError(@Nullable FacebookException facebookException) {
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.c("context");
        }
        Toast.makeText(context2, facebookException != null ? facebookException.getMessage() : null, 0).show();
    }

    public void onSuccess(@Nullable LoginResult loginResult) {
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.c("context");
        }
        if (loginResult == null) {
            Intrinsics.a();
        }
        AccessToken accessToken = loginResult.getAccessToken();
        Intrinsics.b(accessToken, "result!!.accessToken");
        String token = accessToken.getToken();
        Intrinsics.b(token, "result!!.accessToken.token");
        storeSnsToken(context2, token);
    }

    public void onActivityResult(@NotNull Activity activity, int i, int i2, @Nullable Intent intent) {
        Intrinsics.f(activity, "activity");
        CallbackManager callbackManager = this.sCallbackManager;
        if (callbackManager == null) {
            Intrinsics.c("sCallbackManager");
        }
        callbackManager.onActivityResult(i, i2, intent);
    }
}
