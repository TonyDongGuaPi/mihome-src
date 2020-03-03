package cn.tongdun.android.core.g9q9q9g9;

import android.text.TextUtils;
import cn.tongdun.android.core.qgg9qgg9999g9g.g9qqggg99gqq99g9q;
import cn.tongdun.android.shell.utils.BoxUtil;
import cn.tongdun.android.shell.utils.LogUtil;
import com.amap.location.common.model.AmapLoc;
import com.xiaomi.smarthome.framework.api.UserConfig;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class gqg9qq9gqq9q9q {
    static final HostnameVerifier gqg9qq9gqq9q9q = new qgg9qgg9999g9g();

    private static void gqg9qq9gqq9q9q(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty(gqg9qq9gqq9q9q("5864427e5375492c30013914", 46), str);
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setDoOutput(true);
    }

    private static String gqg9qq9gqq9q9q(URL url, byte[] bArr, String str, int i) throws Exception {
        HttpsURLConnection httpsURLConnection;
        List list;
        if (url.getProtocol().toLowerCase().equals(gqg9qq9gqq9q9q("732e682a6b", 84))) {
            HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) url.openConnection(Proxy.NO_PROXY);
            if (i == 1) {
                gqg9qq9gqq9q9q(httpsURLConnection2);
                httpsURLConnection = httpsURLConnection2;
            } else {
                httpsURLConnection = httpsURLConnection2;
                if (i == 2) {
                    httpsURLConnection2.setHostnameVerifier(gqg9qq9gqq9q9q);
                    httpsURLConnection = httpsURLConnection2;
                }
            }
        } else {
            httpsURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
        }
        gqg9qq9gqq9q9q(httpsURLConnection, str);
        BoxUtil.getdata(httpsURLConnection, bArr);
        httpsURLConnection.setRequestMethod(gqg9qq9gqq9q9q("4b5e4c59", 39));
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        outputStream.write(bArr);
        outputStream.flush();
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode != 200) {
            String str2 = gqg9qq9gqq9q9q("5860426049665e32183510301931513d032a15290a28173e527d5e765f33", 42) + responseCode;
            LogUtil.err(str2);
            return str2;
        }
        try {
            Map headerFields = httpsURLConnection.getHeaderFields();
            if (!(headerFields == null || (list = (List) headerFields.get(gqg9qq9gqq9q9q("4843421a2c362c322e3e", 19))) == null || list.size() <= 0)) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str3 = (String) list.get(i2);
                    if (str3.contains(gqg9qq9gqq9q9q("4359495430", 63))) {
                        String[] split = str3.split(gqg9qq9gqq9q9q(UserConfig.g, 73));
                        int length = split.length;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= length) {
                                break;
                            }
                            String str4 = split[i3];
                            if (str4.startsWith(gqg9qq9gqq9q9q("4330493d", 86))) {
                                String substring = str4.substring(5, str4.length());
                                if (!TextUtils.isEmpty(substring)) {
                                    g9qqggg99gqq99g9q.gqg9qq9gqq9q9q().gqg9qq9gqq9q9q(substring);
                                    break;
                                }
                            }
                            i3++;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        InputStream inputStream = httpsURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, gqg9qq9gqq9q9q("6e64672f72", 3)));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                inputStream.close();
                outputStream.close();
                return sb.toString();
            }
        }
    }

    public static String gqg9qq9gqq9q9q(String str, Map map, byte[] bArr, int i) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null) {
                sb.append((String) entry.getKey());
                sb.append(gqg9qq9gqq9q9q("26", 5));
                sb.append(URLEncoder.encode((String) entry.getValue(), gqg9qq9gqq9q9q("6e06674d72", 97)));
                sb.append(gqg9qq9gqq9q9q("3d", 126));
            }
        }
        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return gqg9qq9gqq9q9q(new URL(str + gqg9qq9gqq9q9q(AmapLoc.w, 51) + sb.toString()), bArr, gqg9qq9gqq9q9q("7a4f61536459664c7b4a7a0b3a072d163c4f6248645f6053", 56), i);
    }

    public static String gqg9qq9gqq9q9q(String str, Map map) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() != null) {
                sb.append((String) entry.getKey());
                sb.append(gqg9qq9gqq9q9q("26", 17));
                sb.append(URLEncoder.encode((String) entry.getValue(), gqg9qq9gqq9q9q("6e29676272", 78)));
                sb.append(gqg9qq9gqq9q9q("3d", 54));
            }
        }
        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return str + gqg9qq9gqq9q9q(AmapLoc.w, 61) + sb.toString();
    }

    public static void gqg9qq9gqq9q9q(HttpsURLConnection httpsURLConnection) {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            q9qq99qg9qqgqg9gqgg9 q9qq99qg9qqgqg9gqgg9 = new q9qq99qg9qqgqg9gqgg9();
            SSLContext instance = SSLContext.getInstance(gqg9qq9gqq9q9q("4f7d4b", 3));
            instance.init((KeyManager[]) null, new TrustManager[]{q9qq99qg9qqgqg9gqgg9}, (SecureRandom) null);
            sSLSocketFactory = instance.getSocketFactory();
        } catch (Exception unused) {
        }
        if (sSLSocketFactory != null) {
            httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
            httpsURLConnection.setHostnameVerifier(new q9gqqq99999qq());
            return;
        }
        throw new IllegalArgumentException(gqg9qq9gqq9q9q("fe37f649a84861487e77427b4a755b477c456b5e7655ea14ff4da35ce90fd96b9e73cf36ec50956a", 60));
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 102);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 27);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
