package com.xiaomi.smarthome.newui.card.spec;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.CardParticleHelper;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;

public class SpecRobotAnimCardItem extends SpecCardItem {
    static final long P = 200;
    ImageView I;
    int M = -1;
    Handler N;
    ViewGroup O;
    CardParticleHelper Q;

    /* renamed from: a  reason: collision with root package name */
    ImageView f20600a;

    /* access modifiers changed from: package-private */
    public void b() {
    }

    public SpecRobotAnimCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(String str, Object obj) {
        int g;
        if (!j() && k() != null && this.E != null && !this.E.isFinishing() && this.M != (g = g())) {
            this.M = g;
            b(this.M);
        }
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.card_item_robot_anim);
        this.f20600a = (ImageView) a2.findViewById(R.id.iv_robot_bg);
        this.I = (ImageView) a2.findViewById(R.id.iv_robot_circle);
        this.O = (ViewGroup) a2.findViewById(R.id.ll_particle);
        ViewGroup viewGroup2 = this.O;
        if (viewGroup2 != null) {
            this.Q = CardParticleHelper.a(viewGroup2);
            this.M = g();
            b(this.M);
        }
    }

    public void i() {
        super.i();
        a(true);
        if (this.N != null) {
            this.N.removeCallbacksAndMessages((Object) null);
        }
        CardParticleHelper cardParticleHelper = this.Q;
        if (cardParticleHelper != null) {
            cardParticleHelper.a();
        }
        this.Q = null;
        this.E = null;
        this.f20600a = null;
        this.I = null;
        this.O = null;
    }

    private void b(int i) {
        if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE) {
            d();
        } else if (i == 9 || i == 12) {
            c();
        } else {
            switch (i) {
                case 2:
                    a();
                    return;
                case 3:
                    d();
                    return;
                default:
                    d();
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        ImageView imageView = this.I;
        ImageView imageView2 = this.f20600a;
        Activity activity = this.E;
        ViewGroup viewGroup = this.O;
        if (imageView != null && imageView2 != null && activity != null && viewGroup != null) {
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView2.setImageResource(R.drawable.robot_bg);
            Animation loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_360_repeat);
            loadAnimation.setInterpolator(new LinearInterpolator());
            imageView.setAnimation(loadAnimation);
            imageView.startAnimation(loadAnimation);
            if (this.N != null) {
                this.N.removeCallbacksAndMessages((Object) null);
            }
            this.N = new WaveHandler();
            this.N.sendEmptyMessage(1);
            viewGroup.post(new Runnable(activity) {
                private final /* synthetic */ Activity f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SpecRobotAnimCardItem.this.a(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Activity activity) {
        CardParticleHelper cardParticleHelper;
        if (!j() && this.M == 2 && (cardParticleHelper = this.Q) != null) {
            cardParticleHelper.a(activity);
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        ImageView imageView = this.I;
        if (imageView != null) {
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView.setImageResource(R.drawable.robot_error);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        ImageView imageView = this.I;
        ImageView imageView2 = this.f20600a;
        CardParticleHelper cardParticleHelper = this.Q;
        if (imageView != null && imageView2 != null && cardParticleHelper != null) {
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView.setImageDrawable((Drawable) null);
            imageView2.setImageResource(R.drawable.robot_bg_disable);
            if (this.N != null) {
                this.N.removeCallbacksAndMessages((Object) null);
            }
            cardParticleHelper.a();
        }
    }

    /* access modifiers changed from: package-private */
    public int g() {
        Object e = ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
        if (e == null) {
            return -1;
        }
        return ((Integer) e).intValue();
    }

    class WaveHandler extends Handler {
        WaveHandler() {
        }

        public void handleMessage(Message message) {
            Log.i("SpecRobotAnimCardItem", "waveHandler msg:" + message.what);
            ImageView imageView = SpecRobotAnimCardItem.this.I;
            if (SpecRobotAnimCardItem.this.j() || SpecRobotAnimCardItem.this.E == null || SpecRobotAnimCardItem.this.E.isFinishing() || imageView == null) {
                SpecRobotAnimCardItem.this.N.removeCallbacksAndMessages((Object) null);
                return;
            }
            if (message.what == 1) {
                imageView.setImageResource(R.drawable.robot_wave1);
                SpecRobotAnimCardItem.this.N.sendMessageDelayed(SpecRobotAnimCardItem.this.N.obtainMessage(2, imageView), 200);
            }
            if (message.what == 2) {
                imageView.setImageResource(R.drawable.robot_wave2);
                SpecRobotAnimCardItem.this.N.sendMessageDelayed(SpecRobotAnimCardItem.this.N.obtainMessage(3, imageView), 200);
            }
            if (message.what == 3) {
                imageView.setImageResource(R.drawable.robot_wave3);
                SpecRobotAnimCardItem.this.N.sendMessageDelayed(SpecRobotAnimCardItem.this.N.obtainMessage(4, imageView), 200);
            }
            if (message.what == 4) {
                imageView.setImageDrawable((Drawable) null);
                SpecRobotAnimCardItem.this.N.sendMessageDelayed(SpecRobotAnimCardItem.this.N.obtainMessage(1, imageView), 200);
            }
        }
    }
}
