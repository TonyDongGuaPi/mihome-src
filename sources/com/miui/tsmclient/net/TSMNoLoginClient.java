package com.miui.tsmclient.net;

import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.util.LogUtils;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class TSMNoLoginClient {
    private static final String KEY_DATA = "data";
    private static final String KEY_ERRCODE = "errCode";
    private static final String KEY_ERRDESC = "errDesc";

    /* access modifiers changed from: package-private */
    public Object sendPostRequest(AuthRequest authRequest) throws AccessDeniedException, AuthenticationFailureException, IOException {
        SimpleRequest.StringContent postAsString = SimpleRequest.postAsString(authRequest.getRequestFullUrl(), authRequest.getParams(), authRequest.getCookies(), true);
        if (authRequest.getRespContentType() == AuthRequest.RespContentType.json) {
            return handleJsonResult(postAsString);
        }
        return postAsString.getBody();
    }

    private Object handleJsonResult(SimpleRequest.StringContent stringContent) throws IOException {
        if (stringContent == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(stringContent.getBody());
            if (jSONObject.optInt("errCode") == 200) {
                return jSONObject.opt("data");
            }
            throw new IOException(jSONObject.optString("errDesc"));
        } catch (JSONException e) {
            LogUtils.e("failed to parse tsm api response", e);
            return null;
        }
    }
}
