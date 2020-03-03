package com.xiaomi.smarthome.miui10;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.leonids.ParticleSystem;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import java.util.ArrayList;
import java.util.List;

public class CardParticleHelper {

    /* renamed from: a  reason: collision with root package name */
    private List<ParticleSystem> f20129a = new ArrayList();
    private ViewGroup b;

    public static CardParticleHelper a(ViewGroup viewGroup) {
        CardParticleHelper cardParticleHelper = new CardParticleHelper();
        cardParticleHelper.b = viewGroup;
        return cardParticleHelper;
    }

    public void a(Activity activity, View view, View view2) {
        Activity activity2 = activity;
        if (this.b != null && activity2 != null) {
            if (!(activity2 instanceof BaseActivity) || ((BaseActivity) activity2).isValid()) {
                ParticleSystem b2 = new ParticleSystem(activity, 30, R.drawable.card_particle_airpurifier, 8000).a(this.b).a(0.01f, 0.03f, 20, 160).a(ParticleSystem.ParticleSystemType.AirPurifier).b(0.4f, 1.0f);
                b2.b(view, 80, 10);
                this.f20129a.add(b2);
                ParticleSystem b3 = new ParticleSystem(activity, 30, R.drawable.card_particle_airpurifier, 8000).a(this.b).a(0.01f, 0.03f, -20, -160).a(ParticleSystem.ParticleSystemType.AirPurifier).b(0.4f, 1.0f);
                b3.b(view2, 80, 10);
                this.f20129a.add(b3);
            }
        }
    }

    public void a(Activity activity) {
        if (this.b != null && activity != null) {
            if (!(activity instanceof BaseActivity) || ((BaseActivity) activity).isValid()) {
                int height = ((this.b.getWidth() > this.b.getHeight() ? this.b.getHeight() : this.b.getWidth()) / 2) - DisplayUtils.a(5.0f);
                ParticleSystem b2 = new ParticleSystem(activity, 300, R.drawable.card_particle_sweeper, (long) Constants.x).a(this.b).a(0.015f, 0.015f).d(5.0E-5f).a(ParticleSystem.ParticleSystemType.Sweeper).b(0.3f, 0.8f);
                b2.b(height - DisplayUtils.a(20.0f), height, 30);
                this.f20129a.add(b2);
            }
        }
    }

    public void a(int i) {
        if (this.f20129a != null && this.f20129a.size() != 0) {
            for (ParticleSystem a2 : this.f20129a) {
                a2.a(i);
            }
        }
    }

    public void a(float f) {
        if (this.f20129a != null && this.f20129a.size() != 0) {
            for (ParticleSystem b2 : this.f20129a) {
                b2.b(f);
            }
        }
    }

    public void b(float f) {
        if (this.f20129a != null && this.f20129a.size() != 0) {
            for (ParticleSystem e : this.f20129a) {
                e.e(f);
            }
        }
    }

    public void a() {
        for (ParticleSystem next : this.f20129a) {
            if (next != null) {
                next.a();
                next.b();
            }
        }
        this.f20129a.clear();
    }
}
