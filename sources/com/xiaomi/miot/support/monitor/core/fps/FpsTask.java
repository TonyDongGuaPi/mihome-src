package com.xiaomi.miot.support.monitor.core.fps;

import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.Choreographer;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import com.xiaomi.miot.support.monitor.core.tasks.BaseTask;

@RequiresApi(api = 16)
public class FpsTask extends BaseTask implements Choreographer.FrameCallback {

    /* renamed from: a  reason: collision with root package name */
    private final String f1484a = "fps";
    private long e = 0;
    private long f = 0;
    private int g = 0;
    private int h = 1;
    private String i = "";

    public String a() {
        return "fps";
    }

    public void a(String str, int i2) {
        this.h = i2;
        this.i = str;
        this.e = this.f;
        this.g = 0;
    }

    public void a(String str) {
        int i2;
        if (this.e == 0) {
            this.e = this.f;
        } else if (TextUtils.equals(this.i, str)) {
            float f2 = ((float) (this.f - this.e)) / 1000000.0f;
            if ((this.g > 0 || f2 > 0.0f) && (i2 = (int) (((float) (this.g * 1000)) / f2)) > 0) {
                MiotDataStorage.a().a(new FpsInfo(str, i2, this.h));
            }
        }
    }

    @RequiresApi(api = 16)
    public void b() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.b();
                Choreographer.getInstance().postFrameCallback(this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @RequiresApi(api = 16)
    public void doFrame(long j) {
        this.g++;
        this.f = j;
        if (this.e == 0) {
            this.e = this.f;
        }
        if (f()) {
            this.c = true;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        this.c = false;
    }
}
