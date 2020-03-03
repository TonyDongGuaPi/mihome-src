package com.xiaomi.passport.ui.internal;

import android.support.v4.app.Fragment;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SignInContract;", "", "()V", "View", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class SignInContract {

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0003H&J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H&¨\u0006\u0019"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SignInContract$View;", "", "dismissProgress", "", "gotoBindSnsFragment", "e", "Lcom/xiaomi/passport/ui/internal/NeedBindSnsException;", "gotoFragment", "fragment", "Landroid/support/v4/app/Fragment;", "addToBackStack", "", "loginCancelled", "loginSuccess", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "openNotificationUrl", "url", "", "showNetworkError", "Ljava/io/IOException;", "showProgress", "showUnKnowError", "tr", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public interface View {
        void dismissProgress();

        void gotoBindSnsFragment(@NotNull NeedBindSnsException needBindSnsException);

        void gotoFragment(@NotNull Fragment fragment, boolean z);

        void loginCancelled();

        void loginSuccess(@NotNull AccountInfo accountInfo);

        void openNotificationUrl(@NotNull String str);

        void showNetworkError(@NotNull IOException iOException);

        void showProgress();

        void showUnKnowError(@NotNull Throwable th);

        @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 10})
        public static final class DefaultImpls {
            public static /* synthetic */ void gotoFragment$default(View view, Fragment fragment, boolean z, int i, Object obj) {
                if (obj == null) {
                    if ((i & 2) != 0) {
                        z = false;
                    }
                    view.gotoFragment(fragment, z);
                    return;
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: gotoFragment");
            }
        }
    }
}
