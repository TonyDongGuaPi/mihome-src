package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.ImageCardItem;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_2_item_horizontal<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    ProgressItemView f;
    private View g;

    public CardRender_2_item_horizontal(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        super.b();
        CardItem cardItem = (CardItem) this.f20457a.get(0);
        View inflate = cardItem.G.f() ? LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_horizontal_2_button, this.c, false) : null;
        if (cardItem.G.e()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_horizontal_2_number, this.c, false);
        }
        if (cardItem instanceof ImageCardItem) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_horizontal_2_image, this.c, false);
        }
        if (inflate == null) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_2_subitem, this.c, false);
        }
        this.c.addView(inflate);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_left), 2, 0);
        ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_right), 2, 1);
        a(this.c, this.e);
        a(inflate);
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
