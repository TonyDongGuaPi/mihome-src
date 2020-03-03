package com.miui.tsmclient.net;

import android.content.Context;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import java.io.IOException;

public class TSMNoLoginManager {
    private TSMNoLoginClient mTsmNoLoginClient = new TSMNoLoginClient();

    /* access modifiers changed from: protected */
    public Object sendRequest(Context context, AuthRequest authRequest) throws IOException {
        if (NetworkUtil.isConnected(context)) {
            try {
                return this.mTsmNoLoginClient.sendPostRequest(authRequest);
            } catch (AccessDeniedException e) {
                LogUtils.e("failed to send simple post request", e);
                return null;
            } catch (AuthenticationFailureException e2) {
                LogUtils.e("failed to send simple post request", e2);
                return null;
            }
        } else {
            throw new IOException("network unreachable!");
        }
    }
}
