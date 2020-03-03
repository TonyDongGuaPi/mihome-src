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

public class CardRender_3_item_vertical<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_3_item_vertical(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        super.b();
        if (this.f20457a.size() == 2) {
            j();
        } else if (this.f20457a.size() == 3) {
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_3_item_vertical, this.c, false);
            this.c.addView(inflate);
            ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_top), 3, 0);
            ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_center), 3, 1);
            ((CardItem) this.f20457a.get(2)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_bottom), 3, 2);
            a(this.c, this.e);
            a(inflate);
        }
    }

    public void j() {
        if (this.f20457a.size() == 2) {
            CardItem cardItem = (CardItem) this.f20457a.get(1);
            if (((CardItem) this.f20457a.get(0)).G.d() && cardItem.G.d()) {
                View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_2_long_shape, this.c, false);
                this.c.addView(inflate);
                ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_top), 2, 0);
                ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_bottom), 2, 1);
                a(this.c, this.e);
                a(inflate);
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
