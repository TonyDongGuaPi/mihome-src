package com.xiaomi.woltest;

import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IOUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23142a = "IOUtil";
    public static final String b = "US-ASCII";
    public static final String c = "UTF-8";
    public static final String d = "UTF-16";
    public static final String e = "UTF-16LE";
    public static final String f = "UTF-16BE";
    public static final String g = "ISO-8859-1";

    public static byte[] a(InputStream inputStream) throws IOException {
        return a(inputStream, 1024);
    }

    public static byte[] a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            return null;
        }
        if (i < 1) {
            i = 1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static String a(InputStream inputStream, String str) throws IOException, IllegalCharsetNameException {
        byte[] a2 = a(inputStream);
        if (a2 == null) {
            return "";
        }
        if (str == null || !Charset.isSupported(str)) {
            return new String(a2, "UTF-8");
        }
        return new String(a2, str);
    }

    public static int[] a(String str) {
        try {
            return a(new JSONArray(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static int[] a(JSONArray jSONArray) {
        int[] iArr = null;
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        if (length > 0) {
            iArr = new int[length];
            int i = 0;
            while (i < length) {
                try {
                    iArr[i] = jSONArray.getInt(i);
                    i++;
                } catch (JSONException unused) {
                }
            }
        }
        return iArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            while (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static Map<String, Object> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, a(jSONObject.opt(next)));
        }
        return hashMap;
    }

    private static Object a(Object obj) {
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                arrayList.add(a(jSONArray.opt(i)));
            }
            return arrayList;
        } else if (obj == JSONObject.NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static String b(String str) throws IOException {
        Log.i(f23142a, "gzipCompress()");
        if (str == null) {
            return null;
        }
        Log.i(f23142a, "input string length: " + str.length() + ", data: " + str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes());
        gZIPOutputStream.close();
        byte[] encode = Base64.encode(byteArrayOutputStream.toByteArray(), 0);
        if (encode == null) {
            return null;
        }
        String str2 = new String(encode, "UTF-8");
        Log.i(f23142a, "output string length: " + str2.length() + ", data: " + str2);
        return str2;
    }

    public static String c(String str) throws IOException, IllegalArgumentException {
        Log.i(f23142a, "gzipDecompress()");
        if (str == null) {
            return null;
        }
        Log.i(f23142a, "input string length: " + str.length() + ", data: " + str);
        byte[] bytes = str.getBytes("UTF-8");
        if (bytes == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(Base64.decode(bytes, 0)))));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                Log.i(f23142a, "output string length: " + sb.length() + ", data: " + sb.toString());
                bufferedReader.close();
                return sb.toString();
            }
        }
    }
}
