package org.xutils.http.body;

import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.xutils.common.util.KeyValue;

public class UrlEncodedParamsBody implements RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f10781a;
    private String b = "UTF-8";

    public void a(String str) {
    }

    public UrlEncodedParamsBody(List<KeyValue> list, String str) throws IOException {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (KeyValue next : list) {
                String str2 = next.f4233a;
                String a2 = next.a();
                if (!TextUtils.isEmpty(str2) && a2 != null) {
                    if (sb.length() > 0) {
                        sb.append(a.b);
                    }
                    sb.append(Uri.encode(str2, this.b));
                    sb.append("=");
                    sb.append(Uri.encode(a2, this.b));
                }
            }
        }
        this.f10781a = sb.toString().getBytes(this.b);
    }

    public long b() {
        return (long) this.f10781a.length;
    }

    public String a() {
        return "application/x-www-form-urlencoded;charset=" + this.b;
    }

    public void a(OutputStream outputStream) throws IOException {
        outputStream.write(this.f10781a);
        outputStream.flush();
    }
}
