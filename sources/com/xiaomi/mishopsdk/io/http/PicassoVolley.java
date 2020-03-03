package com.xiaomi.mishopsdk.io.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import com.mishopsdk.volley.RequestQueue;
import com.mishopsdk.volley.toolbox.BasicNetwork;
import com.mishopsdk.volley.toolbox.DiskBasedCache;
import com.mishopsdk.volley.toolbox.HttpClientStack;
import com.mishopsdk.volley.toolbox.HttpStack;
import java.io.File;

public class PicassoVolley {
    private static final String DEFAULT_CACHE_DIR = "picasso-volley";

    public static RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        String str;
        File file = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        try {
            String packageName = context.getPackageName();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            str = packageName + "/" + packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "volley/0";
        }
        if (httpStack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                httpStack = new PicassoHurlStack();
            } else {
                httpStack = new HttpClientStack(AndroidHttpClient.newInstance(str));
            }
        }
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(file), new BasicNetwork(httpStack));
        requestQueue.start();
        return requestQueue;
    }
}
