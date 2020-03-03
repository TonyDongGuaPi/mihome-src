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

public class CardRender_3button_status_horizontal<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_3button_status_horizontal(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        super.b();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_vertical_status_3button, this.c, false);
        this.c.addView(inflate);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_top), 3, 0);
        ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_left), 3, 1);
        ((CardItem) this.f20457a.get(2)).a((ViewGroup) inflate.findViewById(R.id.ll_middle), 3, 2);
        ((CardItem) this.f20457a.get(3)).a((ViewGroup) inflate.findViewById(R.id.ll_right), 3, 3);
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
