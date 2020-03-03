package com.xiaomi.smarthome.newui.card.profile;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.CardParticleHelper;
import com.xiaomi.smarthome.newui.card.BaseCardRender;

public class RobotAnimCardItem extends ProfileCardItem {
    static final long U = 200;
    ImageView I;
    int J = -1;
    Handler S;
    ViewGroup T;
    CardParticleHelper V;

    /* renamed from: a  reason: collision with root package name */
    ImageView f20559a;

    /* access modifiers changed from: package-private */
    public void b() {
    }

    public RobotAnimCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(String str, Object obj) {
        int g;
        if (!j() && k() != null && this.E != null && !this.E.isFinishing() && this.J != (g = g())) {
            this.J = g;
            b(this.J);
        }
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        View a2 = a(viewGroup, R.layout.card_item_robot_anim);
        this.f20559a = (ImageView) a2.findViewById(R.id.iv_robot_bg);
        this.I = (ImageView) a2.findViewById(R.id.iv_robot_circle);
        this.T = (ViewGroup) a2.findViewById(R.id.ll_particle);
        ViewGroup viewGroup2 = this.T;
        if (viewGroup2 != null) {
            this.V = CardParticleHelper.a(viewGroup2);
            this.J = g();
            b(this.J);
        }
    }

    public void i() {
        super.i();
        a(true);
        if (this.S != null) {
            this.S.removeCallbacksAndMessages((Object) null);
        }
        CardParticleHelper cardParticleHelper = this.V;
        if (cardParticleHelper != null) {
            cardParticleHelper.a();
        }
        this.V = null;
        this.E = null;
        this.f20559a = null;
        this.I = null;
        this.T = null;
    }

    private void b(int i) {
        if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE) {
            d();
        } else if (i == 9 || i == 12) {
            c();
        } else {
            switch (i) {
                case 5:
                    a();
                    return;
                case 6:
                    b();
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
        ImageView imageView2 = this.f20559a;
        final Activity activity = this.E;
        ViewGroup viewGroup = this.T;
        if (imageView != null && imageView2 != null && activity != null && viewGroup != null) {
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView2.setImageResource(R.drawable.robot_bg);
            Animation loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.rotate_360_repeat);
            loadAnimation.setInterpolator(new LinearInterpolator());
            imageView.setAnimation(loadAnimation);
            imageView.startAnimation(loadAnimation);
            if (this.S != null) {
                this.S.removeCallbacksAndMessages((Object) null);
            }
            this.S = new WaveHandler();
            this.S.sendEmptyMessage(1);
            viewGroup.post(new Runnable() {
                public void run() {
                    CardParticleHelper cardParticleHelper;
                    if (!RobotAnimCardItem.this.j() && RobotAnimCardItem.this.J == 5 && (cardParticleHelper = RobotAnimCardItem.this.V) != null) {
                        cardParticleHelper.a(activity);
                    }
                }
            });
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
        ImageView imageView2 = this.f20559a;
        CardParticleHelper cardParticleHelper = this.V;
        if (imageView != null && imageView2 != null && cardParticleHelper != null) {
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView.setImageDrawable((Drawable) null);
            imageView2.setImageResource(R.drawable.robot_bg_disable);
            if (this.S != null) {
                this.S.removeCallbacksAndMessages((Object) null);
            }
            cardParticleHelper.a();
        }
    }

    /* access modifiers changed from: package-private */
    public int g() {
        try {
            return Integer.valueOf(String.valueOf(((ProfileCardType) this.G).b(k(), this.w))).intValue();
        } catch (Exception e) {
            LogUtilGrey.a("mijia-card", Log.getStackTraceString(e));
            return -1;
        }
    }

    class WaveHandler extends Handler {
        WaveHandler() {
        }

        public void handleMessage(Message message) {
            Log.i("RobotAnimCardItem", "waveHandler msg:" + message.what);
            ImageView imageView = RobotAnimCardItem.this.I;
            if (RobotAnimCardItem.this.j() || RobotAnimCardItem.this.E == null || RobotAnimCardItem.this.E.isFinishing() || imageView == null) {
                RobotAnimCardItem.this.S.removeCallbacksAndMessages((Object) null);
                return;
            }
            if (message.what == 1) {
                imageView.setImageResource(R.drawable.robot_wave1);
                RobotAnimCardItem.this.S.sendMessageDelayed(RobotAnimCardItem.this.S.obtainMessage(2, imageView), 200);
            }
            if (message.what == 2) {
                imageView.setImageResource(R.drawable.robot_wave2);
                RobotAnimCardItem.this.S.sendMessageDelayed(RobotAnimCardItem.this.S.obtainMessage(3, imageView), 200);
            }
            if (message.what == 3) {
                imageView.setImageResource(R.drawable.robot_wave3);
                RobotAnimCardItem.this.S.sendMessageDelayed(RobotAnimCardItem.this.S.obtainMessage(4, imageView), 200);
            }
            if (message.what == 4) {
                imageView.setImageDrawable((Drawable) null);
                RobotAnimCardItem.this.S.sendMessageDelayed(RobotAnimCardItem.this.S.obtainMessage(1, imageView), 200);
            }
        }
    }
}
