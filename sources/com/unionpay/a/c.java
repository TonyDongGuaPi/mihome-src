package com.unionpay.a;

import android.content.Context;
import com.unionpay.utils.b;
import com.unionpay.utils.j;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    private HttpURLConnection f9537a = null;
    private byte[] b = null;
    private String c = null;
    private InputStream d = null;
    private d e = null;
    private Context f;

    public c(d dVar, Context context) {
        this.e = dVar;
        this.f = context;
    }

    public final int a() {
        String str;
        String str2;
        HttpURLConnection httpURLConnection;
        j.a("uppay", "HttpConn.connect() +++");
        int i = 1;
        if (this.e == null) {
            j.b("uppay", "params==null!!!");
            return 1;
        }
        try {
            URL a2 = this.e.a();
            if ("https".equals(a2.getProtocol().toLowerCase())) {
                httpURLConnection = (HttpsURLConnection) a2.openConnection();
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(new a(this.f).a().getSocketFactory());
            } else {
                httpURLConnection = (HttpURLConnection) a2.openConnection();
            }
            httpURLConnection.setRequestMethod(this.e.b());
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setUseCaches(false);
            HashMap d2 = this.e.d();
            if (d2 != null) {
                for (String str3 : d2.keySet()) {
                    httpURLConnection.setRequestProperty(str3, (String) d2.get(str3));
                }
            }
            String b2 = this.e.b();
            char c2 = 65535;
            int hashCode = b2.hashCode();
            if (hashCode != 70454) {
                if (hashCode == 2461856) {
                    if (b2.equals("POST")) {
                        c2 = 1;
                    }
                }
            } else if (b2.equals("GET")) {
                c2 = 0;
            }
            switch (c2) {
                case 0:
                    break;
                case 1:
                    httpURLConnection.setDoOutput(true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                    outputStreamWriter.write(this.e.c());
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    break;
            }
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                this.d = httpURLConnection.getInputStream();
                if (this.d != null) {
                    this.c = b.a(this.d, "UTF-8");
                    i = 0;
                }
            } else if (httpURLConnection.getResponseCode() == 401) {
                i = 8;
            } else {
                j.b("uppay", "http status code:" + httpURLConnection.getResponseCode());
            }
        } catch (SSLHandshakeException e2) {
            j.a("uppay", "e0:" + e2.getMessage());
            i = 4;
        } catch (IOException e3) {
            str = "uppay";
            if (("e1: " + e3) != null) {
                str2 = e3.getMessage();
                j.b(str, str2);
            }
            str2 = "e == null";
            j.b(str, str2);
        } catch (IllegalStateException e4) {
            str = "uppay";
            if (("e2: " + e4) != null) {
                str2 = e4.getMessage();
                j.b(str, str2);
            }
            str2 = "e == null";
            j.b(str, str2);
        } catch (Exception e5) {
            str = "uppay";
            if (("e3: " + e5) != null) {
                str2 = e5.getMessage();
                j.b(str, str2);
            }
            str2 = "e == null";
            j.b(str, str2);
        }
        j.a("uppay", "HttpConn.connect() ---");
        return i;
    }

    public final String b() {
        return this.c;
    }
}
