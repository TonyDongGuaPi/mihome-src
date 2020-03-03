package com.xiaomi.smarthome.newui.card.carditem;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.BaseCardRender;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.profile.ProfileCard;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Type18CardItem<C extends Card<E>, E extends Card.CardType<T>, T> extends CardItem<C, E, T> {
    private TextView I;
    private int J;
    private int K;
    private int L;

    /* renamed from: a  reason: collision with root package name */
    private TextView f20529a;

    /* access modifiers changed from: protected */
    public void a(View view, int i, int i2) {
    }

    public Type18CardItem(E e, T[] tArr) {
        super(e, tArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        try {
            RelativeLayout relativeLayout = (RelativeLayout) a(viewGroup, R.layout.miui10_card_item_text_string);
            this.J = viewGroup.getResources().getColor(R.color.tv_card_title_offline);
            this.f20529a = (TextView) relativeLayout.findViewById(R.id.desc);
            this.I = (TextView) relativeLayout.findViewById(R.id.value);
            TextView textView = this.f20529a;
            TextView textView2 = this.I;
            if (textView == null) {
                return;
            }
            if (textView2 != null) {
                this.K = textView.getCurrentTextColor();
                this.L = textView2.getCurrentTextColor();
                List<T> list = l().c;
                if (list.size() == 3 && ((Card.CardType) list.get(0)).b() && ((Card.CardType) list.get(1)).b() && ((Card.CardType) list.get(2)).b()) {
                    this.I.setTextSize(1, 33.0f);
                }
                a((View) relativeLayout, i, i2);
                d(this.G.b(k(), m()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.f20529a = null;
        this.I = null;
    }

    private void d(Object obj) {
        SimpleDateFormat simpleDateFormat;
        if (!j()) {
            TextView textView = this.f20529a;
            TextView textView2 = this.I;
            if (textView != null && textView2 != null) {
                try {
                    String a2 = this.G.a(k(), m(), obj);
                    Pair<Object, Long> d = this.G.d(k(), m());
                    if (d != null) {
                        Object obj2 = d.second;
                        if (a2 != null) {
                            textView.setText(String.valueOf(a2));
                        }
                        long a3 = NumberUtils.a(obj2, 0);
                        if (a3 != 0) {
                            Locale a4 = LocaleUtil.a();
                            long j = a3 * 1000;
                            if (System.currentTimeMillis() - 86400000 > j) {
                                simpleDateFormat = new SimpleDateFormat("MM/dd", a4);
                            } else {
                                simpleDateFormat = new SimpleDateFormat("HH:mm", a4);
                            }
                            textView2.setText(simpleDateFormat.format(new Date(j)));
                        } else {
                            textView2.setText(CommonApplication.getAppContext().getString(R.string.card_item_fail));
                        }
                        Card l = l();
                        if ((k() instanceof BleDevice) || (((l instanceof ProfileCard) && ((ProfileCard) l).g) || (k().isOnline && this.H == BaseCardRender.DataInitState.DONE))) {
                            textView2.setTextColor(this.L);
                            textView.setTextColor(this.K);
                            return;
                        }
                        textView2.setTextColor(this.J);
                        textView.setTextColor(this.J);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a(String str, Object obj) {
        if (!j() && this.E != null && !this.E.isFinishing()) {
            d(obj);
        }
    }
}
