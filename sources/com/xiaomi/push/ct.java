package com.xiaomi.push;

import android.util.Base64;
import com.alipay.sdk.sys.a;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import org.apache.http.NameValuePair;

class ct {
    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(c(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String a(List<NameValuePair> list, String str) {
        Collections.sort(list, new cu());
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (NameValuePair next : list) {
            if (!z) {
                sb.append(a.b);
            }
            sb.append(next.getName());
            sb.append("=");
            sb.append(next.getValue());
            z = false;
        }
        sb.append(a.b);
        sb.append(str);
        return a(new String(Base64.encode(c(sb.toString()), 2)));
    }

    public static void b(String str) {
    }

    private static byte[] c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }
}
