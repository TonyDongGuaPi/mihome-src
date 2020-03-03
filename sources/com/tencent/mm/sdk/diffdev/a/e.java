package com.tencent.mm.sdk.diffdev.a;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public final class e {
    public static byte[] b(String str, int i) {
        if (str == null || str.length() == 0) {
            Log.e("MicroMsg.SDK.NetUtil", "httpGet, url is null");
            return null;
        }
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(str);
        if (i >= 0) {
            try {
                HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), i);
            } catch (Exception e) {
                Log.e("MicroMsg.SDK.NetUtil", "httpGet, Exception ex = " + e.getMessage());
                return null;
            } catch (IncompatibleClassChangeError e2) {
                Log.e("MicroMsg.SDK.NetUtil", "httpGet, IncompatibleClassChangeError ex = " + e2.getMessage());
                return null;
            } catch (Throwable th) {
                Log.e("MicroMsg.SDK.NetUtil", "httpGet, Throwable ex = " + th.getMessage());
                return null;
            }
        }
        HttpResponse execute = defaultHttpClient.execute(httpGet);
        if (execute.getStatusLine().getStatusCode() == 200) {
            return EntityUtils.toByteArray(execute.getEntity());
        }
        Log.e("MicroMsg.SDK.NetUtil", "httpGet fail, status code = " + execute.getStatusLine().getStatusCode());
        return null;
    }
}