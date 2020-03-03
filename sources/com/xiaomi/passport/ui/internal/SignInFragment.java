package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.SignInContract;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\"H\u0016J\u0012\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u0017H\u0016J\b\u0010'\u001a\u00020\u0017H\u0016J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u0017H\u0016J\u0010\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u000200H\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u00061"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SignInFragment;", "Landroid/support/v4/app/Fragment;", "Lcom/xiaomi/passport/ui/internal/SignInContract$View;", "()V", "addAccountListener", "Lcom/xiaomi/passport/ui/internal/AddAccountListener;", "getAddAccountListener", "()Lcom/xiaomi/passport/ui/internal/AddAccountListener;", "setAddAccountListener", "(Lcom/xiaomi/passport/ui/internal/AddAccountListener;)V", "mCommonErrorHandler", "Lcom/xiaomi/passport/ui/internal/CommonErrorHandler;", "getMCommonErrorHandler", "()Lcom/xiaomi/passport/ui/internal/CommonErrorHandler;", "mProgressHolder", "Lcom/xiaomi/passport/ui/internal/ProgressHolder;", "mWebAuth", "Lcom/xiaomi/passport/ui/internal/WebAuth;", "getMWebAuth", "()Lcom/xiaomi/passport/ui/internal/WebAuth;", "setMWebAuth", "(Lcom/xiaomi/passport/ui/internal/WebAuth;)V", "dismissProgress", "", "gotoBindSnsFragment", "e", "Lcom/xiaomi/passport/ui/internal/NeedBindSnsException;", "gotoFragment", "fragment", "addToBackStack", "", "loginCancelled", "loginSuccess", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "onAttach", "context", "Landroid/content/Context;", "onDestroyView", "onDetach", "openNotificationUrl", "url", "", "showNetworkError", "Ljava/io/IOException;", "showProgress", "showUnKnowError", "tr", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class SignInFragment extends Fragment implements SignInContract.View {
    private HashMap _$_findViewCache;
    @Nullable
    private AddAccountListener addAccountListener;
    @NotNull
    private final CommonErrorHandler mCommonErrorHandler = new CommonErrorHandler();
    private final ProgressHolder mProgressHolder = new ProgressHolder();
    @NotNull
    private WebAuth mWebAuth = new WebAuth();

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Nullable
    public final AddAccountListener getAddAccountListener() {
        return this.addAccountListener;
    }

    public final void setAddAccountListener(@Nullable AddAccountListener addAccountListener2) {
        this.addAccountListener = addAccountListener2;
    }

    @NotNull
    public final WebAuth getMWebAuth() {
        return this.mWebAuth;
    }

    public final void setMWebAuth(@NotNull WebAuth webAuth) {
        Intrinsics.f(webAuth, "<set-?>");
        this.mWebAuth = webAuth;
    }

    @NotNull
    public final CommonErrorHandler getMCommonErrorHandler() {
        return this.mCommonErrorHandler;
    }

    public void onAttach(@Nullable Context context) {
        super.onAttach(context);
        if (context instanceof AddAccountListener) {
            this.addAccountListener = (AddAccountListener) context;
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (context == null) {
            Intrinsics.a();
        }
        sb.append(context.toString());
        sb.append(" must implement AddAccountListener");
        throw new RuntimeException(sb.toString());
    }

    public void onDetach() {
        super.onDetach();
        this.addAccountListener = null;
    }

    public void onDestroyView() {
        dismissProgress();
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void showNetworkError(@NotNull IOException iOException) {
        Intrinsics.f(iOException, "e");
        if (getContext() != null) {
            CommonErrorHandler commonErrorHandler = this.mCommonErrorHandler;
            Context context = getContext();
            if (context == null) {
                Intrinsics.a();
            }
            commonErrorHandler.onIOError(iOException, context, (ConstraintLayout) _$_findCachedViewById(R.id.fragment_main));
        }
    }

    public void showUnKnowError(@NotNull Throwable th) {
        Intrinsics.f(th, BaseInfo.KEY_TIME_RECORD);
        if (getContext() != null) {
            CommonErrorHandler commonErrorHandler = this.mCommonErrorHandler;
            Context context = getContext();
            if (context == null) {
                Intrinsics.a();
            }
            commonErrorHandler.onUnKnowError(th, context);
        }
    }

    public void showProgress() {
        if (getContext() != null) {
            ProgressHolder progressHolder = this.mProgressHolder;
            Context context = getContext();
            if (context == null) {
                Intrinsics.a();
            }
            progressHolder.showProgress(context);
        }
    }

    public void dismissProgress() {
        this.mProgressHolder.dismissProgress();
    }

    public void gotoBindSnsFragment(@NotNull NeedBindSnsException needBindSnsException) {
        Intrinsics.f(needBindSnsException, "e");
        AddAccountListener addAccountListener2 = this.addAccountListener;
        if (addAccountListener2 != null) {
            addAccountListener2.gotoFragment(this.mWebAuth.getSnsBindFragment(needBindSnsException), true);
        }
    }

    public void openNotificationUrl(@NotNull String str) {
        Intrinsics.f(str, "url");
        AddAccountListener addAccountListener2 = this.addAccountListener;
        if (addAccountListener2 != null) {
            addAccountListener2.gotoFragment(this.mWebAuth.getNotificationFragment(str), true);
        }
    }

    public void loginSuccess(@NotNull AccountInfo accountInfo) {
        Intrinsics.f(accountInfo, "accountInfo");
        AddAccountListener addAccountListener2 = this.addAccountListener;
        if (addAccountListener2 != null) {
            addAccountListener2.loginSuccess(accountInfo);
        }
    }

    public void loginCancelled() {
        AddAccountListener addAccountListener2 = this.addAccountListener;
        if (addAccountListener2 != null) {
            addAccountListener2.loginCancelled();
        }
    }

    public void gotoFragment(@NotNull Fragment fragment, boolean z) {
        Intrinsics.f(fragment, "fragment");
        AddAccountListener addAccountListener2 = this.addAccountListener;
        if (addAccountListener2 != null) {
            addAccountListener2.gotoFragment(fragment, z);
        }
    }
}
