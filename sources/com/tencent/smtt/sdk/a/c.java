package com.tencent.smtt.sdk.a;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.n;
import com.tencent.smtt.utils.s;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

final class c extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9121a;
    final /* synthetic */ ThirdAppInfoNew b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    c(String str, Context context, ThirdAppInfoNew thirdAppInfoNew) {
        super(str);
        this.f9121a = context;
        this.b = thirdAppInfoNew;
    }

    public void run() {
        String str;
        if (Build.VERSION.SDK_INT >= 8) {
            JSONObject jSONObject = null;
            if (b.f9120a == null) {
                try {
                    b.f9120a = "65dRa93L".getBytes("utf-8");
                } catch (UnsupportedEncodingException unused) {
                    b.f9120a = null;
                    TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                }
            }
            if (b.f9120a == null) {
                TbsLog.e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                return;
            }
            String string = TbsDownloadConfig.getInstance(this.f9121a).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
            String str2 = "";
            String str3 = "";
            if (!TextUtils.isEmpty(string)) {
                str2 = string.substring(0, string.indexOf(a.b));
                str3 = string.substring(string.indexOf(a.b) + 1, string.length());
            }
            boolean z = TextUtils.isEmpty(str2) || str2.length() != 96 || TextUtils.isEmpty(str3) || str3.length() != 24;
            try {
                s a2 = s.a();
                if (z) {
                    str = a2.b() + n.a().b();
                } else {
                    str = a2.f() + str2;
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", "close");
                }
                try {
                    jSONObject = b.c(this.b, this.f9121a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (jSONObject == null) {
                    TbsLog.e("sdkreport", "post -- jsonData is null!");
                    return;
                }
                try {
                    byte[] bytes = jSONObject.toString().getBytes("utf-8");
                    byte[] a3 = z ? n.a().a(bytes) : n.a(bytes, str3);
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a3.length));
                    try {
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        outputStream.write(a3);
                        outputStream.flush();
                        if (httpURLConnection.getResponseCode() != 200) {
                            TbsLog.e("sdkreport", "Post failed -- not 200");
                        }
                    } catch (Throwable th) {
                        TbsLog.e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                    }
                } catch (Throwable unused2) {
                }
            } catch (IOException e2) {
                TbsLog.e("sdkreport", "Post failed -- IOException:" + e2);
            } catch (AssertionError e3) {
                TbsLog.e("sdkreport", "Post failed -- AssertionError:" + e3);
            } catch (NoClassDefFoundError e4) {
                TbsLog.e("sdkreport", "Post failed -- NoClassDefFoundError:" + e4);
            }
        }
    }
}
