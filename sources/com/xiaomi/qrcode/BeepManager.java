package com.xiaomi.qrcode;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import com.xiaomi.smarthome.R;
import java.io.Closeable;

final class BeepManager implements MediaPlayer.OnErrorListener, Closeable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12953a = "BeepManager";
    private static final float b = 0.1f;
    private static final long c = 200;
    private final Activity d;
    private MediaPlayer e = null;
    private boolean f;
    private boolean g;

    BeepManager(Activity activity) {
        this.d = activity;
        a();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a() {
        this.f = a((SharedPreferences) null, this.d);
        this.g = false;
        if (this.f && this.e == null) {
            this.d.setVolumeControlStream(3);
            this.e = a(this.d);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        if (this.f && this.e != null) {
            this.e.start();
        }
        if (this.g) {
            ((Vibrator) this.d.getSystemService("vibrator")).vibrate(200);
        }
    }

    private static boolean a(SharedPreferences sharedPreferences, Context context) {
        return ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2;
    }

    private MediaPlayer a(Context context) {
        AssetFileDescriptor openRawResourceFd;
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            openRawResourceFd = context.getResources().openRawResourceFd(R.raw.beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(0.1f, 0.1f);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (Exception e2) {
            Log.w(f12953a, e2);
            mediaPlayer.release();
            return null;
        } catch (Throwable th) {
            openRawResourceFd.close();
            throw th;
        }
    }

    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            try {
                this.d.finish();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            close();
            a();
        }
        return true;
    }

    public synchronized void close() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
    }
}
