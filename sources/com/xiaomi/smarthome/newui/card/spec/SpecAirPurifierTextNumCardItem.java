package com.xiaomi.smarthome.newui.card.spec;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.CardParticleHelper;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;

public class SpecAirPurifierTextNumCardItem extends SpecTextNumberCardItem {
    static final String I = "prop.mode";

    /* renamed from: a  reason: collision with root package name */
    static final String f20592a = "silent";
    private CardParticleHelper M;
    private ViewGroup N;
    private View O;
    private View P;

    public SpecAirPurifierTextNumCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    /* access modifiers changed from: protected */
    public View a(ViewGroup viewGroup, int i) {
        View a2 = super.a(viewGroup, R.layout.miui10_card_item_air_purifier_text_num);
        this.N = (ViewGroup) a2.findViewById(R.id.rl_partical_parent);
        this.P = a2.findViewById(R.id.view_particle_bottom);
        this.O = a2.findViewById(R.id.view_particle_top);
        return a2;
    }

    /* access modifiers changed from: protected */
    public void a(Object obj, Device device, TextView textView, int i) {
        super.a(obj, device, textView, i);
        CardParticleHelper cardParticleHelper = this.M;
        ViewGroup viewGroup = this.N;
        if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE || TextUtils.isEmpty(((SpecCardType) this.G).b(k(), this.K, obj))) {
            if (cardParticleHelper != null) {
                cardParticleHelper.a();
            }
        } else if (cardParticleHelper == null) {
            viewGroup.post(new Runnable(viewGroup, i, obj) {
                private final /* synthetic */ ViewGroup f$1;
                private final /* synthetic */ int f$2;
                private final /* synthetic */ Object f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    SpecAirPurifierTextNumCardItem.this.a(this.f$1, this.f$2, this.f$3);
                }
            });
        } else {
            cardParticleHelper.a(i);
            d(obj);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ViewGroup viewGroup, int i, Object obj) {
        if (!j()) {
            this.M = CardParticleHelper.a(viewGroup);
            CardParticleHelper cardParticleHelper = this.M;
            View view = this.O;
            View view2 = this.P;
            Activity activity = this.E;
            if (cardParticleHelper != null && view != null && view2 != null && activity != null) {
                cardParticleHelper.a(activity, view, view2);
                cardParticleHelper.a(i);
                d(obj);
            }
        }
    }

    private void d(Object obj) {
        CardParticleHelper cardParticleHelper = this.M;
        if (this.K.second instanceof SpecProperty) {
            String a2 = SpecUtils.a((SpecProperty) this.K.second, obj);
            if (a2 != null && cardParticleHelper != null) {
                if (f20592a.equalsIgnoreCase(a2)) {
                    cardParticleHelper.a(0.5f);
                } else {
                    cardParticleHelper.a(2.0f);
                }
            }
        } else {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
        }
    }

    public void i() {
        super.i();
        CardParticleHelper cardParticleHelper = this.M;
        if (cardParticleHelper != null) {
            cardParticleHelper.a();
            this.M = null;
        }
        this.N = null;
        this.P = null;
        this.O = null;
    }
}
