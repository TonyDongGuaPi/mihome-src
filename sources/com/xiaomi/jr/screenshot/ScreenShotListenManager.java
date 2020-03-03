package com.xiaomi.jr.screenshot;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.smarthome.download.Downloads;
import java.util.ArrayList;
import java.util.List;

public class ScreenShotListenManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11026a = "ScreenShotListenManager";
    private static final String[] b = {Downloads._DATA, "datetaken"};
    private static final String[] c = {Downloads._DATA, "datetaken", "width", "height"};
    private static final String[] d = {"screenshot", "screen_shot", "screen-shot", "screen shot", "screencapture", "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap", "screen-cap", "screen cap", "截屏"};
    private static Point e;
    private static ScreenShotListenManager n;
    private final List<String> f = new ArrayList();
    private Context g;
    private OnScreenShotListener h;
    private long i;
    private String j;
    private MediaContentObserver k;
    private MediaContentObserver l;
    private final Handler m = new Handler(Looper.getMainLooper());

    public interface OnScreenShotListener {
        void a(String str);
    }

    private ScreenShotListenManager(Context context) {
        if (context != null) {
            this.g = context;
            if (e == null) {
                e = Utils.e(context);
                if (e != null) {
                    Log.d(f11026a, "Screen Real Size: " + e.x + " * " + e.y);
                    return;
                }
                Log.w(f11026a, "Get screen real size failed.");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The context must not be null.");
    }

    public static ScreenShotListenManager a(Context context) {
        if (n == null) {
            synchronized (ScreenShotListenManager.class) {
                if (n == null) {
                    n = new ScreenShotListenManager(context);
                }
            }
        }
        return n;
    }

    public synchronized void a() {
        Utils.a();
        if (this.k == null || this.l == null) {
            this.f.clear();
            this.i = System.currentTimeMillis();
            this.k = new MediaContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, this.m);
            this.l = new MediaContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.m);
            this.g.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, this.k);
            this.g.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.l);
        }
    }

    public synchronized void b() {
        Utils.a();
        if (this.k != null) {
            try {
                this.g.getContentResolver().unregisterContentObserver(this.k);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.k = null;
        }
        if (this.l != null) {
            try {
                this.g.getContentResolver().unregisterContentObserver(this.l);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.l = null;
        }
        this.i = 0;
        this.f.clear();
    }

    public String c() {
        return this.j;
    }

    public void d() {
        this.j = null;
    }

    /* access modifiers changed from: private */
    public void a(Uri uri) {
        Cursor cursor;
        Exception e2;
        int i2;
        int i3;
        int i4;
        try {
            cursor = this.g.getContentResolver().query(uri, Build.VERSION.SDK_INT < 16 ? b : c, (String) null, (String[]) null, "date_added desc limit 1");
            if (cursor == null) {
                try {
                    Log.e(f11026a, "Deviant logic.");
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    try {
                        e2.printStackTrace();
                        if (cursor == null || cursor.isClosed()) {
                            return;
                        }
                        cursor.close();
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            } else if (!cursor.moveToFirst()) {
                Log.d(f11026a, "Cursor no data.");
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } else {
                int columnIndex = cursor.getColumnIndex(Downloads._DATA);
                int columnIndex2 = cursor.getColumnIndex("datetaken");
                int i5 = -1;
                if (Build.VERSION.SDK_INT >= 16) {
                    i5 = cursor.getColumnIndex("width");
                    i2 = cursor.getColumnIndex("height");
                } else {
                    i2 = -1;
                }
                String string = cursor.getString(columnIndex);
                long j2 = cursor.getLong(columnIndex2);
                if (i5 < 0 || i2 < 0) {
                    Point a2 = a(string);
                    int i6 = a2.x;
                    i3 = a2.y;
                    i4 = i6;
                } else {
                    i4 = cursor.getInt(i5);
                    i3 = cursor.getInt(i2);
                }
                a(string, j2, i4, i3);
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
                cursor.close();
            }
        } catch (Exception e4) {
            e2 = e4;
            cursor = null;
            e2.printStackTrace();
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
    }

    private Point a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Point(options.outWidth, options.outHeight);
    }

    private void a(String str, long j2, int i2, int i3) {
        if (b(str, j2, i2, i3)) {
            Log.d(f11026a, "ScreenShot: path = " + str + "; size = " + i2 + " * " + i3 + "; date = " + j2);
            this.j = str;
            if (this.h != null && !b(str)) {
                this.h.a(str);
                return;
            }
            return;
        }
        Log.w(f11026a, "Media content changed, but not screenshot: path = " + str + "; size = " + i2 + " * " + i3 + "; date = " + j2);
    }

    private boolean b(String str, long j2, int i2, int i3) {
        if (j2 < this.i || System.currentTimeMillis() - j2 > 10000) {
            return false;
        }
        if ((e != null && ((i2 > e.x || i3 > e.y) && (i3 > e.x || i2 > e.y))) || TextUtils.isEmpty(str)) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        for (String contains : d) {
            if (lowerCase.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    private boolean b(String str) {
        if (this.f.contains(str)) {
            return true;
        }
        if (this.f.size() >= 20) {
            for (int i2 = 0; i2 < 5; i2++) {
                this.f.remove(0);
            }
        }
        this.f.add(str);
        return false;
    }

    public void a(OnScreenShotListener onScreenShotListener) {
        this.h = onScreenShotListener;
    }

    private class MediaContentObserver extends ContentObserver {
        private Uri b;

        public MediaContentObserver(Uri uri, Handler handler) {
            super(handler);
            this.b = uri;
        }

        public void onChange(boolean z) {
            super.onChange(z);
            ScreenShotListenManager.this.a(this.b);
        }
    }
}
