package com.xiaomi.smarthome.newui.card.profile;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.CardItemUtils;
import java.util.ArrayList;

public class TextStringCardItem extends ProfileCardItem {
    TextView I;
    private int J;
    private int S;
    private int T;

    /* renamed from: a  reason: collision with root package name */
    TextView f20571a;

    /* access modifiers changed from: protected */
    public void a(View view, int i, int i2) {
    }

    public TextStringCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        try {
            RelativeLayout relativeLayout = (RelativeLayout) a(viewGroup, R.layout.miui10_card_item_text_string);
            this.J = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
            this.f20571a = (TextView) relativeLayout.findViewById(R.id.desc);
            this.I = (TextView) relativeLayout.findViewById(R.id.value);
            TextView textView = this.f20571a;
            TextView textView2 = this.I;
            if (textView == null) {
                return;
            }
            if (textView2 != null) {
                this.S = textView.getCurrentTextColor();
                this.T = textView2.getCurrentTextColor();
                CardItemUtils.a((ArrayList) ((ProfileCard) l()).c, relativeLayout, i2);
                int size = ((ProfileCard) l()).c.size();
                if (size == 3 && ((ProfileCardType) ((ProfileCard) l()).c.get(0)).b() && ((ProfileCardType) ((ProfileCard) l()).c.get(1)).b() && ((ProfileCardType) ((ProfileCard) l()).c.get(2)).b()) {
                    this.I.setSingleLine(true);
                    this.I.setMaxLines(1);
                    this.I.setTextSize(1, 33.0f);
                } else if (size == 1) {
                    this.I.setSingleLine(false);
                    this.I.setMaxLines(2);
                    relativeLayout.getLayoutParams().height = -2;
                }
                a((View) relativeLayout, i, i2);
                e(((ProfileCardType) this.G).b(k(), this.w));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20571a = null;
        this.I = null;
    }

    private void e(Object obj) {
        if (!j()) {
            TextView textView = this.f20571a;
            TextView textView2 = this.I;
            if (textView != null && textView2 != null) {
                String o = o();
                if (o != null) {
                    textView.setText(o);
                }
                textView2.setText(String.valueOf(((ProfileCardType) this.G).a(k(), this.w, obj)));
                String d = d(obj);
                if (!TextUtils.isEmpty(d)) {
                    this.T = Color.parseColor(d);
                } else {
                    this.T = Color.parseColor("#CC000000");
                }
                if ((k() instanceof BleDevice) || ((ProfileCard) l()).g || (k().isOnline && this.H == BaseCardRender.DataInitState.DONE)) {
                    textView2.setTextColor(this.T);
                    textView.setTextColor(this.S);
                    return;
                }
                textView2.setTextColor(this.J);
                textView.setTextColor(this.J);
            }
        }
    }

    public void a(String str, Object obj) {
        if (!j() && k() != null && this.E != null && !this.E.isFinishing()) {
            e(obj);
        }
    }
}
