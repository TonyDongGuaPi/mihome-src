package com.xiaomi.smarthome.library.common.widget;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.leonids.ParticleSystem;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ParticleSystemHelper {

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<View> f18912a;
    private List<ParticleSystem> b = new ArrayList();

    private ParticleSystemHelper() {
    }

    public static ParticleSystemHelper a(View view) {
        ParticleSystemHelper particleSystemHelper = new ParticleSystemHelper();
        particleSystemHelper.f18912a = new WeakReference<>(view);
        return particleSystemHelper;
    }

    public void a(Activity activity, int i, ViewGroup viewGroup) {
        View view = (View) this.f18912a.get();
        if (view != null && i > 0) {
            a();
            for (int i2 = 0; i2 < i; i2++) {
                ParticleSystem a2 = new ParticleSystem(activity, 160, (int) R.drawable.weather_raining_raindrop, 2000).a(viewGroup).b(1.0f, 1.0f).a(0.25f, 0.25f, 120, 120).a(20, 20);
                a2.b(view, 80, 10);
                this.b.add(a2);
            }
        }
    }

    public void b(Activity activity, int i, ViewGroup viewGroup) {
        int i2 = i;
        ViewGroup viewGroup2 = viewGroup;
        View view = (View) this.f18912a.get();
        if (view != null && i2 > 0) {
            a();
            if (i2 == 1) {
                ParticleSystem a2 = new ParticleSystem(activity, 40, (int) R.drawable.weather_snowing_snowflake, 8000).a(0.05f, 0.05f, 70, 120).a(viewGroup2);
                a2.b(view, 80, 5);
                this.b.add(a2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = i3 % 2;
                float f = i4 == 0 ? 1.0f : 0.5f;
                float f2 = i4 == 0 ? 0.07f : 0.05f;
                ParticleSystem particleSystem = r12;
                ParticleSystem particleSystem2 = new ParticleSystem(activity, 40, R.drawable.weather_snowing_snowflake, 8000);
                ParticleSystem b2 = particleSystem.a(f2, f2, 70, 120).a(viewGroup2).b(f, f);
                b2.b(view, 80, 5);
                this.b.add(b2);
            }
        }
    }

    public void a() {
        if (this.b.size() > 0) {
            for (int i = 0; i < this.b.size(); i++) {
                ParticleSystem particleSystem = this.b.get(i);
                if (particleSystem != null) {
                    particleSystem.a();
                    particleSystem.b();
                }
            }
            this.b.clear();
        }
    }
}
