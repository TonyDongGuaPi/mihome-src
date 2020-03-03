package com.xiaomi.smarthome.leonids;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

class ParticleField extends View {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<Particle> f18430a;

    public ParticleField(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ParticleField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ParticleField(Context context) {
        super(context);
    }

    public void a(ArrayList<Particle> arrayList) {
        this.f18430a = arrayList;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (this.f18430a) {
            for (int i = 0; i < this.f18430a.size(); i++) {
                Particle particle = this.f18430a.get(i);
                if (particle != null) {
                    particle.a(canvas);
                }
            }
        }
    }
}
