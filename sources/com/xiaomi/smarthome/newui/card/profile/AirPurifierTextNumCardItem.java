package com.xiaomi.smarthome.newui.card.profile;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.CardParticleHelper;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import java.util.Map;

public class AirPurifierTextNumCardItem extends ProfileCardItem {
    static final String I = "prop.mode";

    /* renamed from: a  reason: collision with root package name */
    static final String f20530a = "silent";
    private TextView J;
    private TextView S;
    private TextView T;
    private int U;
    private int V;
    /* access modifiers changed from: private */
    public int W;
    private int X;
    /* access modifiers changed from: private */
    public CardParticleHelper Y;
    private ViewGroup Z;
    /* access modifiers changed from: private */
    public View aa;
    /* access modifiers changed from: private */
    public View ab;
    private Object ac = null;

    public AirPurifierTextNumCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_air_purifier_text_num);
        this.aa = a2.findViewById(R.id.view_particle_top);
        this.ab = a2.findViewById(R.id.view_particle_bottom);
        this.U = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.Z = (ViewGroup) a2.findViewById(R.id.rl_partical_parent);
        this.J = (TextView) a2.findViewById(R.id.desc);
        this.S = (TextView) a2.findViewById(R.id.value);
        this.T = (TextView) a2.findViewById(R.id.unit);
        TextView textView = this.J;
        TextView textView2 = this.S;
        TextView textView3 = this.T;
        if (textView != null && textView2 != null && textView3 != null) {
            this.V = textView.getCurrentTextColor();
            this.W = textView2.getCurrentTextColor();
            this.X = textView3.getCurrentTextColor();
            if (((ProfileCard) l()).b == 4) {
                textView2.setTextSize(2, 36.67f);
                textView.setTextSize(2, 12.0f);
                textView3.setTextSize(2, 13.33f);
            }
            b((String) null, (Object) null);
        }
    }

    private void a(double d, TextView textView, TextView textView2) {
        String str = n().f;
        String str2 = n().g;
        if (!TextUtils.isEmpty(str)) {
            textView.setText(String.format(str, new Object[]{Double.valueOf(d)}));
        } else {
            textView.setText(String.valueOf(d));
        }
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
    }

    public void i() {
        super.i();
        a(true);
        CardParticleHelper cardParticleHelper = this.Y;
        if (cardParticleHelper != null) {
            cardParticleHelper.a();
            this.Y = null;
        }
        this.E = null;
        this.J = null;
        this.S = null;
        this.T = null;
        this.Z = null;
    }

    public void a(String str, Object obj) {
        if (!j() && k() != null) {
            b(str, obj);
        }
    }

    private void a(Object obj, Device device, TextView textView, TextView textView2) {
        if (obj == null || obj.toString().equals("null")) {
            a(0.0d, textView, textView2);
            return;
        }
        try {
            double parseDouble = Double.parseDouble(String.valueOf(obj));
            if (n().b()) {
                parseDouble *= n().e;
            }
            a(parseDouble, textView, textView2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(final String str, final Object obj) {
        if (!j()) {
            TextView textView = this.J;
            TextView textView2 = this.S;
            TextView textView3 = this.T;
            final ViewGroup viewGroup = this.Z;
            CardParticleHelper cardParticleHelper = this.Y;
            if (textView != null && textView2 != null && textView3 != null && viewGroup != null) {
                String o = o();
                if (o != null) {
                    textView.setText(o);
                }
                if (this.w.equals(ControlCardInfoManager.g)) {
                    if (n() == null) {
                        a((Object) null, k(), textView2, textView3);
                    }
                    Map<String, Object> b = ControlCardInfoManager.f().b(k().did);
                    if (b == null || b.get(this.w) == null) {
                        a((Object) null, k(), textView2, textView3);
                    } else {
                        a(b.get(this.w), k(), textView2, textView3);
                    }
                } else {
                    a(((ProfileCardType) this.G).b(k(), this.w), k(), textView2, textView3);
                }
                if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE) {
                    textView.setTextColor(this.U);
                    textView2.setTextColor(this.U);
                    textView3.setTextColor(this.U);
                    if (cardParticleHelper != null) {
                        cardParticleHelper.a();
                        return;
                    }
                    return;
                }
                String d = d(((ProfileCardType) this.G).b(k(), this.w));
                if (!TextUtils.isEmpty(d)) {
                    this.W = Color.parseColor(d);
                    textView2.setTextColor(this.W);
                }
                if (cardParticleHelper == null) {
                    viewGroup.post(new Runnable() {
                        public void run() {
                            if (!AirPurifierTextNumCardItem.this.j()) {
                                CardParticleHelper unused = AirPurifierTextNumCardItem.this.Y = CardParticleHelper.a(viewGroup);
                                CardParticleHelper b2 = AirPurifierTextNumCardItem.this.Y;
                                View c2 = AirPurifierTextNumCardItem.this.aa;
                                View d2 = AirPurifierTextNumCardItem.this.ab;
                                Activity e = AirPurifierTextNumCardItem.this.E;
                                if (b2 != null && c2 != null && d2 != null && e != null) {
                                    b2.a(e, c2, d2);
                                    b2.a(AirPurifierTextNumCardItem.this.W);
                                    AirPurifierTextNumCardItem.this.c(str, obj);
                                }
                            }
                        }
                    });
                    return;
                }
                cardParticleHelper.a(this.W);
                c(str, obj);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(String str, Object obj) {
        CardParticleHelper cardParticleHelper = this.Y;
        if (!TextUtils.isEmpty(str) && I.equals(str) && obj != null && !obj.equals(this.ac)) {
            if (f20530a.equals(obj)) {
                if (cardParticleHelper != null) {
                    cardParticleHelper.a(0.5f);
                }
            } else if (f20530a.equals(this.ac) && cardParticleHelper != null) {
                cardParticleHelper.a(2.0f);
            }
            this.ac = obj;
        }
    }
}
