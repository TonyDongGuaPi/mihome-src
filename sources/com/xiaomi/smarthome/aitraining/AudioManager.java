package com.xiaomi.smarthome.aitraining;

import android.media.MediaRecorder;
import android.view.Surface;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import java.io.File;

public class AudioManager {

    /* renamed from: a  reason: collision with root package name */
    public AudioStageListener f13680a;
    private MediaRecorder b;
    private String c;
    private String d;
    private boolean e;
    private long f;
    private long g;

    public interface AudioStageListener {
        void a();
    }

    public AudioManager(String str) {
        this.c = str;
    }

    public void a(AudioStageListener audioStageListener) {
        this.f13680a = audioStageListener;
    }

    public void a() {
        try {
            File file = new File(this.c);
            FileUtils.d(this.c);
            this.d = file.getAbsolutePath();
            this.b = new MediaRecorder();
            this.b.setOutputFile(this.d);
            this.b.setAudioSource(1);
            this.b.setOutputFormat(0);
            this.b.setAudioEncoder(3);
            this.b.prepare();
            this.b.start();
            this.f = System.currentTimeMillis();
            this.g = 0;
            this.e = true;
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b() {
        if (this.b != null) {
            try {
                this.b.setOnErrorListener((MediaRecorder.OnErrorListener) null);
                this.b.setOnInfoListener((MediaRecorder.OnInfoListener) null);
                this.b.setPreviewDisplay((Surface) null);
                this.b.stop();
            } catch (Exception | IllegalStateException | RuntimeException unused) {
            }
            this.e = false;
            this.b.release();
            this.b = null;
            this.g = System.currentTimeMillis() - this.f;
        }
    }

    public long c() {
        if (this.e) {
            return System.currentTimeMillis() - this.f;
        }
        return this.g;
    }

    public boolean d() {
        return this.e;
    }

    public void e() {
        b();
        if (this.d != null) {
            new File(this.d).delete();
            this.d = null;
        }
    }

    public String f() {
        return this.d;
    }
}
