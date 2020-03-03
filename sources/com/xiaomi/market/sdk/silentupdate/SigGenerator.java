package com.xiaomi.market.sdk.silentupdate;

import android.util.Base64;
import com.alipay.sdk.sys.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SigGenerator {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11120a = "HmacSHA256";
    private static Random b = new SecureRandom();

    public static String a(String str, String str2, String str3, String str4, String str5) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        TreeMap treeMap = new TreeMap();
        treeMap.put("appClientId", str2);
        treeMap.put("nonce", str);
        treeMap.put("id", str3);
        treeMap.put("ref", str4);
        return a(str5, (TreeMap<String, String>) treeMap);
    }

    private static String a(String str, TreeMap<String, String> treeMap) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : treeMap.entrySet()) {
            String str2 = (String) next.getKey();
            if (sb.length() > 0) {
                sb.append(a.b);
            }
            sb.append(str2 + "=" + URLEncoder.encode((String) next.getValue(), "UTF-8"));
        }
        sb.append("\n");
        return a(a(sb.toString().getBytes("UTF-8"), str.getBytes("UTF-8")));
    }

    public static String a() {
        long nextLong = b.nextLong();
        return nextLong + ":" + ((int) (System.currentTimeMillis() / 60000));
    }

    private static String a(byte[] bArr) {
        try {
            return new String(Base64.encode(bArr, 9), "UTF-8").trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, f11120a);
        Mac instance = Mac.getInstance(f11120a);
        instance.init(secretKeySpec);
        instance.update(bArr);
        return instance.doFinal();
    }

    private static class NameValuePair {

        /* renamed from: a  reason: collision with root package name */
        private String f11121a;
        private String b;

        private NameValuePair() {
        }

        public String a() {
            return this.f11121a;
        }

        public void a(String str) {
            this.f11121a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }
    }
}
