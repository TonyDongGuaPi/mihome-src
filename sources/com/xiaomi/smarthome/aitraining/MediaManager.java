package com.xiaomi.smarthome.aitraining;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.xiaomi.smarthome.framework.log.LogUtil;

public class MediaManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13681a = "MediaManager";
    /* access modifiers changed from: private */
    public MediaPlayer b;
    private boolean c;
    private SensorManager d;
    /* access modifiers changed from: private */
    public Sensor e;
    private Context f;
    private AudioManager g;
    private long h;
    private long i;

    public void a(String str, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (this.b == null) {
            this.b = new MediaPlayer();
            this.b.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    MediaManager.this.b.reset();
                    return false;
                }
            });
        } else {
            this.b.reset();
        }
        try {
            this.b.setAudioStreamType(3);
            this.b.setOnCompletionListener(onCompletionListener);
            this.b.setDataSource(str);
            this.b.prepare();
            this.b.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.i = 0;
        this.h = System.currentTimeMillis();
    }

    public void a(String str, MediaPlayer.OnCompletionListener onCompletionListener, Context context) {
        if (this.b == null) {
            this.b = new MediaPlayer();
            this.b.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    MediaManager.this.b.reset();
                    return false;
                }
            });
        } else {
            this.b.reset();
        }
        try {
            this.b.setAudioStreamType(3);
            this.b.setOnCompletionListener(onCompletionListener);
            this.b.setDataSource(str);
            this.b.prepare();
            this.b.start();
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.i = 0;
        this.h = System.currentTimeMillis();
    }

    private void a(Context context) {
        this.f = context;
        this.d = (SensorManager) context.getSystemService("sensor");
        this.e = this.d.getDefaultSensor(8);
        this.d.registerListener(new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int i) {
            }

            public void onSensorChanged(SensorEvent sensorEvent) {
                try {
                    if (sensorEvent.values[0] >= MediaManager.this.e.getMaximumRange()) {
                        MediaManager.this.a(true);
                    } else {
                        MediaManager.this.a(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, this.e, 3);
    }

    public void a() {
        if (this.b != null && this.b.isPlaying()) {
            this.b.pause();
            this.c = true;
        }
        this.i = (this.i + System.currentTimeMillis()) - this.h;
    }

    public void b() {
        if (this.b != null && this.c) {
            this.b.start();
            this.c = false;
        }
        this.h = System.currentTimeMillis();
    }

    public void c() {
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
        this.i = (this.i + System.currentTimeMillis()) - this.h;
    }

    public long d() {
        if (this.b == null) {
            return 0;
        }
        return (this.i + System.currentTimeMillis()) - this.h;
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        ((Activity) this.f).setVolumeControlStream(1);
        a();
        if (z) {
            this.g.setMicrophoneMute(false);
            this.g.setSpeakerphoneOn(true);
            this.g.setMode(0);
            LogUtil.b(f13681a, "changeAdapterType: 当前为扩音模式");
        } else {
            this.g.setSpeakerphoneOn(false);
            this.g.setMicrophoneMute(true);
            this.g.setMode(0);
            this.g.setMode(3);
            LogUtil.b(f13681a, "changeAdapterType: 当前为耳麦模式");
        }
        b();
    }
}
