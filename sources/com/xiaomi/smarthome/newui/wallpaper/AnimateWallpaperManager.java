package com.xiaomi.smarthome.newui.wallpaper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.woltest.IOUtil;
import com.xiaomi.youpin.utils.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class AnimateWallpaperManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20766a = AnimateWallpaperManager.class.getCanonicalName();
    private static AnimateWallpaperManager b = null;
    private static String c = null;
    private Map<String, AnimateWallpaper> d = new LinkedHashMap();

    public static final AnimateWallpaperManager a() {
        if (b == null) {
            synchronized (AnimateWallpaperManager.class) {
                b = new AnimateWallpaperManager();
            }
        }
        return b;
    }

    public static String b() {
        if (c != null) {
            return c;
        }
        Context appContext = SHApplication.getAppContext();
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            c = appContext.getExternalCacheDir().getPath();
        } else {
            c = appContext.getCacheDir().getPath();
        }
        if (!c.endsWith(File.pathSeparator)) {
            c += File.pathSeparator;
        }
        c += "wallpapers/";
        FileUtils.g(c);
        return c;
    }

    public static final String a(String str) {
        String str2 = b() + str;
        FileUtils.g(str2);
        return str2;
    }

    public static InputStream b(String str) {
        try {
            return new FileInputStream(b() + str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static InputStream c(String str) {
        try {
            AssetManager assets = SHApplication.getAppContext().getAssets();
            return assets.open("wallpapers/" + str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static SharedPreferences c() {
        return SHApplication.getAppContext().getSharedPreferences("wallpapers", 0);
    }

    private AnimateWallpaperManager() {
        try {
            JSONArray jSONArray = new JSONObject(new String(IOUtil.a(c("conf.json")))).getJSONArray("list");
            for (int i = 0; i < jSONArray.length(); i++) {
                AnimateWallpaper animateWallpaper = new AnimateWallpaper(jSONArray.getJSONObject(i));
                if (animateWallpaper.b()) {
                    this.d.put(animateWallpaper.d(), animateWallpaper);
                }
            }
        } catch (Exception e) {
            Log.e(f20766a, "Failed to initialize manager", e);
        }
    }

    public boolean d() {
        return !this.d.isEmpty();
    }

    public int e() {
        return this.d.size();
    }

    public String f() {
        String string = c().getString("current", "");
        if (this.d.containsKey(string)) {
            return string;
        }
        if (this.d.isEmpty()) {
            return "";
        }
        return this.d.keySet().iterator().next();
    }

    public AnimateWallpaper g() {
        return this.d.get(f());
    }

    public void d(String str) {
        c().edit().putString("current", str).apply();
    }

    public Set<String> h() {
        return this.d.keySet();
    }

    public AnimateWallpaper e(String str) {
        return this.d.get(str);
    }

    public static final void a(ImageView imageView, Bitmap bitmap) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap2 = (drawable == null || !(drawable instanceof BitmapDrawable)) ? null : ((BitmapDrawable) drawable).getBitmap();
        imageView.setImageDrawable(new BitmapDrawable(imageView.getContext().getResources(), bitmap));
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
    }

    public static final void a(View view, Bitmap bitmap) {
        Drawable background = view.getBackground();
        Bitmap bitmap2 = (background == null || !(background instanceof BitmapDrawable)) ? null : ((BitmapDrawable) background).getBitmap();
        view.setBackgroundDrawable(new BitmapDrawable(view.getContext().getResources(), bitmap));
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
    }
}
