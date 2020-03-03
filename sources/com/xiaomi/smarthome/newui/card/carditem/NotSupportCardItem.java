package com.xiaomi.smarthome.newui.card.carditem;

import android.view.ViewGroup;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.CardItem;

public class NotSupportCardItem<C extends Card<E>, E extends Card.CardType<T>, T> extends CardItem<C, E, T> {
    public void a(String str, Object obj) {
    }

    public NotSupportCardItem(E e, T[] tArr) {
        super(e, tArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        a(viewGroup, R.layout.card_item_not_support);
    }

    public void i() {
        super.i();
        this.E = null;
    }
}
