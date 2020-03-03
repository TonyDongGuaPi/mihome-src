package com.xiaomi.smarthome.mitsmsdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.account.OAuthAccountManager;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.PrefUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MiOAuthImpl implements OAuthAccountManager.IMiOAuth {

    /* renamed from: a  reason: collision with root package name */
    static ExecutorService f20058a = Executors.newCachedThreadPool();
    private Context b;

    public MiOAuthImpl(Context context) {
        this.b = context;
    }

    public Future<Bundle> getAccessToken(String str) {
        LogUtils.i("MiOAuthImpl getAccessToken by OAuth");
        final String d = NfcChannelManager.a().d();
        final String e = NfcChannelManager.a().e();
        if (TextUtils.isEmpty(PrefUtils.getString(this.b, OAuthAccountManager.MiOAuthConstant.TOKEN, (String) null))) {
            PrefUtils.putString(this.b, OAuthAccountManager.MiOAuthConstant.TOKEN, d);
            PrefUtils.putString(this.b, "userId", e);
        }
        return f20058a.submit(new Callable<Bundle>() {
            /* renamed from: a */
            public Bundle call() throws Exception {
                Bundle bundle = new Bundle();
                bundle.putString(OAuthAccountManager.MiOAuthConstant.TOKEN, d);
                bundle.putString("userId", e);
                return bundle;
            }
        });
    }

    public void invalidateAccessToken(String str, String str2) {
        PrefUtils.remove(this.b, OAuthAccountManager.MiOAuthConstant.TOKEN);
    }
}
