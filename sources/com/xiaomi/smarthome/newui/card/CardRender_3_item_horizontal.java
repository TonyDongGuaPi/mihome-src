package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_3_item_horizontal<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_3_item_horizontal(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        View view;
        super.b();
        if (this.f20457a.size() >= 3) {
            CardItem cardItem = (CardItem) this.f20457a.get(0);
            CardItem cardItem2 = (CardItem) this.f20457a.get(1);
            CardItem cardItem3 = (CardItem) this.f20457a.get(2);
            if (cardItem.G.f()) {
                view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_3_item_horizontal_button, this.c, false);
            } else if (cardItem.G.e() && cardItem2.G.e() && cardItem3.G.e()) {
                view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_3_item_horizontal_num, this.c, false);
            } else if (cardItem.G.b() && cardItem2.G.b() && cardItem3.G.b()) {
                view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_3_item_horizontal_text, this.c, false);
            } else if ((cardItem.G.b() || cardItem.G.e()) && ((cardItem2.G.b() || cardItem2.G.e()) && (cardItem3.G.b() || cardItem3.G.e()))) {
                View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_3_item_2_line, this.c, false);
                this.c.addView(inflate);
                cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_top), 3, 0);
                cardItem2.a((ViewGroup) inflate.findViewById(R.id.ll_left), 3, 1);
                cardItem3.a((ViewGroup) inflate.findViewById(R.id.ll_right), 3, 2);
                a(this.c, this.e);
                a(inflate);
                return;
            } else {
                view = null;
            }
            if (view != null) {
                this.c.addView(view);
                cardItem.a((ViewGroup) view.findViewById(R.id.ll_left), 3, 0);
                cardItem2.a((ViewGroup) view.findViewById(R.id.ll_layout_center), 3, 1);
                cardItem3.a((ViewGroup) view.findViewById(R.id.ll_right), 3, 2);
                a(this.c, this.e);
                a(view);
            }
        }
    }

    public ProgressItemView c() {
        return this.f;
    }

    private void a(View view) {
        this.f = (ProgressItemView) view.findViewById(R.id.dpb_enter_device);
        this.g = view.findViewById(R.id.progress_enter_device);
    }

    public View d() {
        return this.g;
    }
}
