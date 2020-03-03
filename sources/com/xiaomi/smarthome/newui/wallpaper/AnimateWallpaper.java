package com.xiaomi.smarthome.newui.wallpaper;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.framework.plugin.ZipFileUtils;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AnimateWallpaper {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f20746a = AnimateWallpaper.class.getCanonicalName();
    private final JSONObject b;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public File d = null;
    private String e = null;
    /* access modifiers changed from: private */
    public Float f = null;
    /* access modifiers changed from: private */
    public List<Item> g = new ArrayList();
    private WeakReference<Call<AnimateWallpaper>> h = null;

    public final class Item {
        private boolean b;
        private String c;
        private Integer[] d;
        private JSONObject e;
        private String f;

        private Item(JSONObject jSONObject) {
            this.b = false;
            this.c = null;
            this.d = new Integer[]{null, null};
            this.f = null;
            this.e = jSONObject;
            this.b = !AnimateWallpaper.this.a(jSONObject, "lottie").isEmpty();
            if (!this.b) {
                this.b = true ^ AnimateWallpaper.this.a(jSONObject, "ani").isEmpty();
            }
            a(jSONObject);
            this.c = AnimateWallpaper.this.a(jSONObject, "bg");
            if (this.c.isEmpty()) {
                this.c = "bg.png";
            }
            this.f = AnimateWallpaper.this.a(jSONObject, SharePatchInfo.g);
            if (this.f == null) {
                this.f = "";
            }
            if (!this.f.isEmpty()) {
                this.f += "/";
            }
        }

        public boolean a() {
            return this.b;
        }

        public Integer a(Integer num) {
            return this.d[0] == null ? num : this.d[0];
        }

        public Integer b(Integer num) {
            return this.d[1] == null ? num : this.d[1];
        }

        public Bitmap b() {
            AnimateWallpaper animateWallpaper = AnimateWallpaper.this;
            return animateWallpaper.a(this.f + this.c);
        }

        public Bitmap a(String str) {
            AnimateWallpaper animateWallpaper = AnimateWallpaper.this;
            return animateWallpaper.a(this.f + str);
        }

        public boolean a(Call<InputStream> call) {
            return a(c(), call);
        }

        private String c() {
            String trim = AnimateWallpaper.this.a(this.e, "ani").trim();
            if (trim.isEmpty()) {
                trim = "ani.json";
            }
            return this.f + trim;
        }

        /* access modifiers changed from: private */
        public String d() {
            String trim = AnimateWallpaper.this.a(this.e, "lottie").trim();
            if (trim.isEmpty()) {
                trim = "lottie.json";
            }
            return this.f + trim;
        }

        public boolean b(Call<InputStream> call) {
            return a(d(), call);
        }

        public Bitmap b(String str) {
            AnimateWallpaper animateWallpaper = AnimateWallpaper.this;
            return animateWallpaper.a(this.f + "images/" + str);
        }

        private boolean a(final String str, final Call<InputStream> call) {
            if (this.b) {
                return AnimateWallpaper.this.a((Runnable) new Runnable() {
                    public void run() {
                        InputStream inputStream;
                        if (AnimateWallpaper.this.d == null) {
                            inputStream = AnimateWallpaper.this.d(str);
                        } else {
                            inputStream = AnimateWallpaper.this.c ? AnimateWallpaper.this.f(str) : null;
                        }
                        call.a(inputStream);
                    }
                });
            }
            call.a(null);
            return true;
        }

        /* access modifiers changed from: private */
        public String e() {
            String trim = AnimateWallpaper.this.a(this.e, "picture").trim();
            if (trim.isEmpty()) {
                trim = "wallpaper.jpg";
            }
            return this.f + trim;
        }

        public boolean c(final Call<InputStream> call) {
            if (!this.b) {
                return AnimateWallpaper.this.a((Runnable) new Runnable() {
                    public void run() {
                        InputStream inputStream;
                        String a2 = Item.this.e();
                        if (AnimateWallpaper.this.d == null) {
                            inputStream = AnimateWallpaper.this.d(a2);
                        } else {
                            inputStream = AnimateWallpaper.this.c ? AnimateWallpaper.this.f(a2) : null;
                        }
                        call.a(inputStream);
                    }
                });
            }
            call.a(null);
            return true;
        }

        private void a(JSONObject jSONObject) {
            try {
                Object obj = jSONObject.get("color");
                if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    switch (jSONArray.length()) {
                        case 0:
                            return;
                        case 1:
                            this.d[1] = Integer.valueOf(Color.parseColor(jSONArray.optString(0)));
                            return;
                        default:
                            this.d[0] = Integer.valueOf(Color.parseColor(jSONArray.optString(0)));
                            this.d[1] = Integer.valueOf(Color.parseColor(jSONArray.optString(1)));
                            return;
                    }
                } else {
                    this.d[1] = Integer.valueOf(Color.parseColor(jSONObject.optString("color")));
                }
            } catch (Exception unused) {
            }
        }
    }

    public AnimateWallpaper(JSONObject jSONObject) {
        this.b = jSONObject;
        this.e = a(jSONObject, "name").trim();
        if (a(jSONObject, "url").isEmpty()) {
            this.c = true;
        } else {
            this.d = new File(AnimateWallpaperManager.a(d()));
            this.c = !StringUtil.a((Object[]) this.d.list());
        }
        Object opt = jSONObject.opt("content");
        if (opt == null) {
            this.g.add(new Item(jSONObject));
        } else if (opt instanceof JSONObject) {
            this.g.add(new Item((JSONObject) opt));
        } else {
            JSONArray jSONArray = (JSONArray) opt;
            for (int i = 0; i < jSONArray.length(); i++) {
                this.g.add(new Item(jSONArray.optJSONObject(i)));
            }
        }
    }

    /* access modifiers changed from: private */
    public String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.optString(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    public boolean a() {
        return this.c;
    }

    public boolean b() {
        return this.b != null && !this.e.isEmpty() && !this.g.isEmpty();
    }

    public boolean c() {
        return AnimateWallpaperManager.a().g() == this;
    }

    public String d() {
        return this.e;
    }

    private static AssetManager j() {
        return SHApplication.getAppContext().getAssets();
    }

    public boolean e() {
        return this.f != null;
    }

    public float f() {
        if (this.f == null) {
            return 0.0f;
        }
        return this.f.floatValue();
    }

    public void a(Call<AnimateWallpaper> call) {
        if (call != null) {
            this.h = new WeakReference<>(call);
        }
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        Call call;
        this.f = Float.valueOf(f2);
        if (this.h != null && (call = (Call) this.h.get()) != null) {
            call.a(this);
        }
    }

    public Item g() {
        switch (this.g.size()) {
            case 0:
                return null;
            case 1:
                return this.g.get(0);
            default:
                return this.g.get((int) (System.currentTimeMillis() % ((long) this.g.size())));
        }
    }

    public boolean a(Runnable runnable) {
        if (this.d == null || this.c) {
            runnable.run();
            return true;
        } else if (this.f != null) {
            return false;
        } else {
            a(0.0f);
            String a2 = a(this.b, "url");
            String str = f20746a;
            Log.d(str, "start to download " + a2);
            String a3 = AnimateWallpaperManager.a("temp_wallpaper");
            final String path = new File(a3, this.e + ".zip").getPath();
            FileUtils.d(path);
            final String path2 = new File(a3, this.e + "_unzip").getPath();
            FileUtils.e(path2);
            FileUtils.g(path2);
            final String str2 = a2;
            final Runnable runnable2 = runnable;
            NetworkUtils.a(SHApplication.getAppContext(), a2, new File(path), new NetworkUtils.OnDownloadProgress() {
                public void a(long j, long j2) {
                    AnimateWallpaper.this.a(j2 > 0 ? ((float) j) / ((float) j2) : 0.0f);
                }

                public void a(String str) {
                    String str2;
                    Float unused = AnimateWallpaper.this.f = null;
                    if (path.endsWith(".zip")) {
                        if (!ZipFileUtils.a(path, path2)) {
                            String i = AnimateWallpaper.f20746a;
                            Log.d(i, "failed to unzip " + str2);
                            runnable2.run();
                            return;
                        }
                        String i2 = AnimateWallpaper.f20746a;
                        Log.d(i2, "success to download " + str2);
                        FileUtils.e(AnimateWallpaper.this.d.getPath());
                        File[] listFiles = new File(path2).listFiles();
                        if (listFiles != null || listFiles.length >= 1 || listFiles[0].isDirectory()) {
                            listFiles[0].renameTo(AnimateWallpaper.this.d);
                        } else {
                            runnable2.run();
                            return;
                        }
                    } else if (AnimateWallpaper.this.g.size() == 1) {
                        Item item = (Item) AnimateWallpaper.this.g.get(0);
                        if (item.a()) {
                            str2 = item.d();
                        } else {
                            str2 = item.e();
                        }
                        File file = new File(AnimateWallpaper.this.d, str2);
                        FileUtils.g(file.getParent());
                        new File(path).renameTo(file);
                    } else {
                        runnable2.run();
                        return;
                    }
                    boolean unused2 = AnimateWallpaper.this.c = !StringUtil.a((Object[]) AnimateWallpaper.this.d.list());
                    runnable2.run();
                }

                public void a() {
                    String i = AnimateWallpaper.f20746a;
                    Log.d(i, "downloading stopped: " + str2);
                    runnable2.run();
                }

                public void b() {
                    String i = AnimateWallpaper.f20746a;
                    Log.d(i, "failed to download " + str2);
                    Float unused = AnimateWallpaper.this.f = null;
                    runnable2.run();
                }
            }, false, false);
            return false;
        }
    }

    private String c(String str) {
        return "wallpapers/" + this.e + "/" + str;
    }

    /* access modifiers changed from: private */
    public InputStream d(String str) {
        try {
            return j().open(c(str));
        } catch (IOException e2) {
            String str2 = f20746a;
            Log.e(str2, "failed to load asset file " + str, e2);
            return null;
        }
    }

    private File e(String str) {
        return new File(this.d, str);
    }

    /* access modifiers changed from: private */
    public InputStream f(String str) {
        try {
            return new FileInputStream(e(str));
        } catch (FileNotFoundException e2) {
            String str2 = f20746a;
            Log.e(str2, "failed to load cache file " + str, e2);
            return null;
        }
    }

    public static final Bitmap a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        try {
            return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } catch (Exception unused) {
            return null;
        } finally {
            IOUtils.a(inputStream);
        }
    }

    public static final Bitmap a(InputStream inputStream, int i) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inDensity = 160;
        options.inSampleSize = i;
        try {
            return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
        } catch (Exception unused) {
            return null;
        } finally {
            IOUtils.a(inputStream);
        }
    }

    public Bitmap h() {
        String a2 = a(this.b, "show");
        if (a2.isEmpty()) {
            a2 = "show.jpg";
        }
        Bitmap a3 = a(d(a2), 4);
        if (a3 == null) {
            String str = f20746a;
            Log.e(str, "Failed to Load Showcase of " + this.e);
        }
        return a3;
    }

    public Bitmap a(String str) {
        return a(b(str));
    }

    public InputStream b(String str) {
        if (this.d == null) {
            return d(str);
        }
        if (this.c) {
            return f(str);
        }
        return null;
    }
}
