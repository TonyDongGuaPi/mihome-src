package com.xiaomi.smarthome.newui.card.spec;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.ArrayList;
import org.apache.commons.cli.HelpFormatter;

public class SpecTextStringCardItem extends SpecCardItem {
    TextView I;
    private int M;
    private int N;
    private int O;

    /* renamed from: a  reason: collision with root package name */
    TextView f20607a;

    /* access modifiers changed from: protected */
    public void a(View view, int i, int i2) {
    }

    public SpecTextStringCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        RelativeLayout relativeLayout = (RelativeLayout) a(viewGroup, R.layout.miui10_card_item_text_string);
        this.M = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
        this.f20607a = (TextView) relativeLayout.findViewById(R.id.desc);
        this.I = (TextView) relativeLayout.findViewById(R.id.value);
        TextView textView = this.f20607a;
        TextView textView2 = this.I;
        if (textView != null && textView2 != null) {
            this.N = textView.getCurrentTextColor();
            this.O = textView2.getCurrentTextColor();
            if (((SpecCard) l()).b != 0) {
                ((RelativeLayout.LayoutParams) textView.getLayoutParams()).addRule(5, R.id.value);
            }
            CardItemUtils.a(l(), textView, textView2, (TextView) null);
            CardItemUtils.a((ArrayList) ((SpecCard) l()).c, relativeLayout, i2);
            a((View) relativeLayout, i, i2);
            a(this.w, ((SpecCardType) this.G).b(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
        }
    }

    public void i() {
        super.i();
        a(true);
        this.f20607a = null;
        this.I = null;
    }

    private void d(Object obj) {
        if (!j()) {
            TextView textView = this.f20607a;
            TextView textView2 = this.I;
            if (textView != null && textView2 != null) {
                String b = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
                if (b != null) {
                    textView.setVisibility(0);
                    textView.setText(b);
                } else {
                    textView.setVisibility(8);
                }
                if (obj == null) {
                    textView2.setText(HelpFormatter.f);
                } else {
                    textView2.setText(String.valueOf(((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, obj)));
                }
                String b2 = ((SpecCardType) this.G).b(k(), this.K, obj);
                if (!TextUtils.isEmpty(b2)) {
                    this.O = Color.parseColor(b2);
                }
                if (!k().isOnline || this.H != BaseCardRender.DataInitState.DONE) {
                    textView2.setTextColor(this.M);
                    textView.setTextColor(this.M);
                    return;
                }
                textView2.setTextColor(this.O);
                textView.setTextColor(this.N);
            }
        }
    }

    public void a(String str, Object obj) {
        if (!j()) {
            d(obj);
        }
    }
}
