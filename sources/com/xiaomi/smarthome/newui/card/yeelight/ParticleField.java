package com.xiaomi.smarthome.newui.card.yeelight;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

class ParticleField extends View {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<Particle> f20613a;

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
        this.f20613a = arrayList;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        while (i < this.f20613a.size()) {
            try {
                this.f20613a.get(i).a(canvas);
                i++;
            } catch (Exception unused) {
                return;
            }
        }
    }
}
