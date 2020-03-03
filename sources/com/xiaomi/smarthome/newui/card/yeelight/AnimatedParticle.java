package com.xiaomi.smarthome.newui.card.yeelight;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public class AnimatedParticle extends Particle {
    private AnimationDrawable y;
    private int z = 0;

    public AnimatedParticle(AnimationDrawable animationDrawable) {
        this.y = animationDrawable;
        this.f = ((BitmapDrawable) this.y.getFrame(0)).getBitmap();
        for (int i = 0; i < this.y.getNumberOfFrames(); i++) {
            this.z += this.y.getDuration(i);
        }
    }

    public boolean a(long j) {
        boolean a2 = super.a(j);
        if (a2) {
            long j2 = 0;
            long j3 = j - this.x;
            int i = 0;
            if (j3 > ((long) this.z)) {
                if (this.y.isOneShot()) {
                    return false;
                }
                j3 %= (long) this.z;
            }
            while (true) {
                if (i >= this.y.getNumberOfFrames()) {
                    break;
                }
                j2 += (long) this.y.getDuration(i);
                if (j2 > j3) {
                    this.f = ((BitmapDrawable) this.y.getFrame(i)).getBitmap();
                    break;
                }
                i++;
            }
        }
        return a2;
    }
}
