package com.xiaomi.mistatistic.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import com.xiaomi.mistatistic.sdk.controller.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class o {
    private static o d;

    /* renamed from: a  reason: collision with root package name */
    private long f12050a = 0;
    private boolean b = false;
    /* access modifiers changed from: private */
    public a c = new a();
    /* access modifiers changed from: private */
    public Handler e = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 100 && o.this.h()) {
                h.a("handle shaking....");
                o.this.c(o.this.c.a());
                o.this.g();
            }
        }
    };
    private boolean f = false;

    public static o a() {
        if (d == null) {
            d = new o();
        }
        return d;
    }

    private o() {
    }

    public boolean b() {
        return this.b;
    }

    public void c() {
        this.b = true;
    }

    public void d() {
        this.b = false;
        if (e()) {
            f();
        }
    }

    public boolean e() {
        return this.f;
    }

    public void a(Activity activity) {
        SensorManager sensorManager = (SensorManager) activity.getSystemService("sensor");
        sensorManager.registerListener(this.c, sensorManager.getDefaultSensor(1), 3);
        this.c.a(activity);
        this.f = true;
        h.a("enable AcceleroMeterSensor...");
    }

    public void f() {
        SensorManager sensorManager = (SensorManager) c.a().getSystemService("sensor");
        sensorManager.unregisterListener(this.c, sensorManager.getDefaultSensor(1));
        this.f = false;
        this.c.b();
        h.a("disable AcceleroMeterSensor...");
    }

    class a implements SensorEventListener {
        private SoftReference<Activity> b;

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public a() {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] fArr = sensorEvent.values;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            float f4 = (float) 19;
            if (Math.abs(f) > f4 || Math.abs(f2) > f4 || Math.abs(f3) > f4) {
                o.this.e.sendEmptyMessage(100);
                h.a("shaking...");
            }
        }

        public void a(Activity activity) {
            this.b = new SoftReference<>(activity);
        }

        public Activity a() {
            return this.b.get();
        }

        public void b() {
            try {
                if (this.b != null) {
                    this.b.clear();
                }
            } catch (Exception e) {
                h.a("clearActivity exception: ", (Throwable) e);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public File b(Activity activity) throws IOException {
        View rootView = activity.getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        File file = new File(activity.getExternalCacheDir(), "snapshot.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        fileOutputStream.close();
        return file;
    }

    public void g() {
        this.f12050a = System.currentTimeMillis();
    }

    public boolean h() {
        return System.currentTimeMillis() > this.f12050a + 5000;
    }

    /* access modifiers changed from: private */
    public void c(final Activity activity) {
        d.b().a((d.a) new d.a() {
            public void a() {
                int i;
                try {
                    File b2 = o.this.b(activity);
                    JSONObject jSONObject = new JSONObject();
                    DisplayMetrics displayMetrics = c.a().getResources().getDisplayMetrics();
                    int i2 = 0;
                    if (displayMetrics != null) {
                        i2 = displayMetrics.widthPixels;
                        i = displayMetrics.heightPixels;
                    } else {
                        i = 0;
                    }
                    jSONObject.put("height", Integer.toString(i));
                    jSONObject.put("width", Integer.toString(i2));
                    r rVar = new r();
                    rVar.a(activity);
                    List<com.xiaomi.mistatistic.sdk.data.a> a2 = rVar.a();
                    JSONArray jSONArray = new JSONArray();
                    for (com.xiaomi.mistatistic.sdk.data.a a3 : a2) {
                        jSONArray.put(a3.a());
                    }
                    jSONObject.put("clickable_views", jSONArray);
                    HashMap hashMap = new HashMap();
                    hashMap.put("appId", c.b());
                    hashMap.put("appKey", c.c());
                    hashMap.put("deviceId", new e().a());
                    hashMap.put("meta", jSONObject.toString());
                    String a4 = j.a("https://dev.mi.com/mistats/xmstats/event/dynamic/upload", (Map<String, String>) hashMap, b2, "test");
                    h.a("upload snapshot with clickable views " + a2.size());
                    h.a(a4);
                } catch (Exception e) {
                    h.a("uploadSnapShot task exception: ", (Throwable) e);
                }
            }
        });
    }
}
