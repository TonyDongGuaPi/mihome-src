package com.xiaomi.ai.utils;

import android.text.TextUtils;
import com.xiaomi.ai.HTTPCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class f {
    public static String a() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(instance.getTime()) + " GMT";
    }

    public static String a(HTTPCallback hTTPCallback, String str, Map<String, String> map, String str2) {
        InputStreamReader inputStreamReader;
        String str3;
        if (!TextUtils.isEmpty(str2)) {
            StringBuffer stringBuffer = new StringBuffer();
            if (hTTPCallback != null) {
                try {
                    hTTPCallback.a();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    if (hTTPCallback != null) {
                        str3 = e.toString();
                        hTTPCallback.a(-1, str3);
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    if (hTTPCallback != null) {
                        str3 = e2.toString();
                        hTTPCallback.a(-1, str3);
                    }
                }
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            for (String next : map.keySet()) {
                httpURLConnection.setRequestProperty(next, map.get(next));
            }
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(str2.getBytes());
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            Log.d("Track", "code " + responseCode);
            if (responseCode != 200) {
                inputStreamReader = new InputStreamReader(httpURLConnection.getErrorStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine + "\n");
                }
            } else {
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                while (true) {
                    String readLine2 = bufferedReader2.readLine();
                    if (readLine2 == null) {
                        break;
                    }
                    stringBuffer.append(readLine2 + "\n");
                }
            }
            inputStreamReader.close();
            outputStream.close();
            if (hTTPCallback != null) {
                if (responseCode != 200) {
                    hTTPCallback.a(responseCode, stringBuffer.toString());
                } else {
                    hTTPCallback.a(stringBuffer.toString());
                }
            }
            return stringBuffer.toString();
        } else if (hTTPCallback == null) {
            return null;
        } else {
            hTTPCallback.a(-1, "args error");
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        r1 = r1.getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r1) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            r0 = 0
            if (r1 != 0) goto L_0x000c
            return r0
        L_0x000c:
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 != 0) goto L_0x0013
            return r0
        L_0x0013:
            boolean r1 = r1.isAvailable()
            if (r1 == 0) goto L_0x001b
            r1 = 1
            return r1
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.f.a(android.content.Context):boolean");
    }
}
