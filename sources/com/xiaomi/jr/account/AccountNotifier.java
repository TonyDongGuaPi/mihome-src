package com.xiaomi.jr.account;

import android.content.Context;
import com.xiaomi.jr.common.utils.Algorithms;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountNotifier {

    /* renamed from: a  reason: collision with root package name */
    private final List<WeakReference<AccountLoginCallback>> f1379a = new ArrayList();
    private final List<WeakReference<AccountLogoutCallback>> b = new ArrayList();

    public interface AccountLoginCallback {
        void a(int i);
    }

    public interface AccountLogoutCallback {
        void onLogout();
    }

    public void a(AccountLoginCallback accountLoginCallback) {
        Algorithms.a(this.f1379a, accountLoginCallback);
    }

    public void a(AccountLogoutCallback accountLogoutCallback) {
        Algorithms.a(this.b, accountLogoutCallback);
    }

    public void a(Context context, int i) {
        if (i == -1) {
            PostLoginTasks.a(context);
        }
        a(i);
    }

    public void a(Context context) {
        PostLogoutTasks.a(context);
        a();
    }

    private void a(int i) {
        Iterator<WeakReference<AccountLoginCallback>> it = this.f1379a.iterator();
        while (it.hasNext()) {
            AccountLoginCallback accountLoginCallback = (AccountLoginCallback) it.next().get();
            if (accountLoginCallback != null) {
                accountLoginCallback.a(i);
            }
            it.remove();
        }
    }

    private void a() {
        Iterator<WeakReference<AccountLogoutCallback>> it = this.b.iterator();
        while (it.hasNext()) {
            AccountLogoutCallback accountLogoutCallback = (AccountLogoutCallback) it.next().get();
            if (accountLogoutCallback != null) {
                accountLogoutCallback.onLogout();
            }
            it.remove();
        }
    }
}
