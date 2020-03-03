package cn.tongdun.android.core.g9q9q9g9;

import android.text.TextUtils;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class qgg9qgg9999g9g implements HostnameVerifier {
    qgg9qgg9999g9g() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.endsWith(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q("35063a152e04270c360a2d1b7b5676", 40)) && !str.endsWith(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q("350035013c022d196d596648", 60))) {
            return false;
        }
        String peerHost = sSLSession.getPeerHost();
        return peerHost.endsWith(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q("35043a172e06270e36082d197b5476", 42)) || peerHost.endsWith(gqg9qq9gqq9q9q.gqg9qq9gqq9q9q("357135703c732d686d286639", 77));
    }
}
