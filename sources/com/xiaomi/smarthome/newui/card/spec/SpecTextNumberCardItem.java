package com.xiaomi.smarthome.newui.card.spec;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.library.common.FontManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;

public class SpecTextNumberCardItem extends SpecCardItem {
    private int I;
    private int M;
    private int N;
    private TextView O;
    private TextView P;
    private TextView Q;

    /* renamed from: a  reason: collision with root package name */
    private int f20606a;

    public SpecTextNumberCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_text_number);
        this.f20606a = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.O = (TextView) a2.findViewById(R.id.desc);
        this.P = (TextView) a2.findViewById(R.id.value);
        this.Q = (TextView) a2.findViewById(R.id.unit);
        TextView textView = this.O;
        TextView textView2 = this.P;
        TextView textView3 = this.Q;
        if (textView != null && textView2 != null && textView3 != null) {
            this.I = textView.getCurrentTextColor();
            this.M = textView2.getCurrentTextColor();
            textView2.setText(CommonApplication.getAppContext().getString(R.string.card_item_fail));
            this.N = textView3.getCurrentTextColor();
            String g = ((SpecCardType) this.G).f(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
            if (!TextUtils.isEmpty(g)) {
                textView3.setText(g);
                ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
                if ((layoutParams instanceof LinearLayout.LayoutParams) && g.length() > 3 && i == 3) {
                    ((LinearLayout.LayoutParams) layoutParams).weight = 0.0f;
                }
            }
            CardItemUtils.a(l(), textView, textView2, textView3);
            CardItemUtils.a((ArrayList) ((SpecCard) l()).c, a2, i2);
            textView2.setTypeface(FontManager.a(textView2.getContext(), "fonts/Mitype2018-50.otf"));
            a(this.w, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
        }
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            d(obj);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Object obj, Device device, TextView textView, int i) {
        String a2 = ((SpecCardType) this.G).a((Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj, ((SpecCardType) this.G).e(device, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
        if (TextUtils.isEmpty(a2)) {
            textView.setText(HelpFormatter.f);
        } else {
            textView.setText(a2);
        }
        textView.setTextColor(this.M);
    }

    private void d(Object obj) {
        int i;
        if (!j()) {
            TextView textView = this.O;
            TextView textView2 = this.P;
            TextView textView3 = this.Q;
            if (textView != null && textView2 != null && textView3 != null) {
                String b = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
                if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE) {
                    textView.setTextColor(this.f20606a);
                    textView2.setTextColor(this.f20606a);
                    textView3.setTextColor(this.f20606a);
                    i = this.f20606a;
                } else {
                    textView2.setTextColor(this.M);
                    textView.setTextColor(this.I);
                    textView3.setTextColor(this.N);
                    String b2 = ((SpecCardType) this.G).b(k(), this.K, obj);
                    if (!TextUtils.isEmpty(b2)) {
                        i = Color.parseColor(b2);
                    } else {
                        i = this.M;
                    }
                }
                if (b != null) {
                    textView.setText(b);
                }
                a(obj, k(), textView2, i);
            }
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.O = null;
        this.P = null;
        this.Q = null;
    }
}
