package com.megvii.licensemanager;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private static List<ILicenseManager> b = new ArrayList();
    private static final String c = "http://api.faceid.com/faceid/v1/sdk/authm";

    /* renamed from: a  reason: collision with root package name */
    private Context f6668a;

    private void b() {
    }

    public Manager(Context context) {
        if (context != null) {
            this.f6668a = context.getApplicationContext();
            return;
        }
        throw new InvalidParameterException("context can not be null");
    }

    public String a(String str) {
        if (this.f6668a == null || b.size() == 0) {
            return null;
        }
        this.f6668a = this.f6668a.getApplicationContext();
        StringBuilder sb = new StringBuilder();
        for (ILicenseManager context : b) {
            sb.append(context.getContext(str));
            sb.append('$');
        }
        String sb2 = sb.toString();
        return sb2.substring(0, sb2.length() - 1);
    }

    public Map<String, Long> b(String str) {
        if (str == null || this.f6668a == null) {
            return null;
        }
        String[] split = str.split("\\$");
        if (split.length != b.size()) {
            return null;
        }
        this.f6668a = this.f6668a.getApplicationContext();
        HashMap hashMap = new HashMap(split.length);
        for (int i = 0; i < b.size(); i++) {
            hashMap.put(b.get(i).getVersion(), Long.valueOf(b.get(i).setLicense(split[i])));
        }
        return hashMap;
    }

    public HashMap<String, Long> a() {
        HashMap<String, Long> hashMap = new HashMap<>();
        for (ILicenseManager next : b) {
            hashMap.put(next.getVersion(), Long.valueOf(next.checkCachedLicense()));
        }
        return hashMap;
    }

    public synchronized boolean a(ILicenseManager iLicenseManager) {
        boolean z;
        z = false;
        for (ILicenseManager version : b) {
            if (version.getVersion().equals(iLicenseManager.getVersion())) {
                z = true;
            }
        }
        if (!z) {
            b.add(iLicenseManager);
        }
        return !z;
    }

    public synchronized Map<String, Long> c(String str) {
        return b(d(a(str)));
    }

    private static String d(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(c).openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "text/plain");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(str.getBytes());
            bufferedOutputStream.close();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
