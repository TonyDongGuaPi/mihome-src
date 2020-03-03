package com.xiaomi.smarthome.leonids;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public class AnimatedParticle extends Particle {
    private AnimationDrawable m;
    private int n = 0;

    public AnimatedParticle(AnimationDrawable animationDrawable) {
        this.m = animationDrawable;
        this.f18429a = ((BitmapDrawable) this.m.getFrame(0)).getBitmap();
        for (int i = 0; i < this.m.getNumberOfFrames(); i++) {
            this.n += this.m.getDuration(i);
        }
    }

    public boolean a(long j) {
        boolean a2 = super.a(j);
        if (a2) {
            long j2 = 0;
            long j3 = j - this.l;
            int i = 0;
            if (j3 > ((long) this.n)) {
                if (this.m.isOneShot()) {
                    return false;
                }
                j3 %= (long) this.n;
            }
            while (true) {
                if (i >= this.m.getNumberOfFrames()) {
                    break;
                }
                j2 += (long) this.m.getDuration(i);
                if (j2 > j3) {
                    this.f18429a = ((BitmapDrawable) this.m.getFrame(i)).getBitmap();
                    break;
                }
                i++;
            }
        }
        return a2;
    }
}
