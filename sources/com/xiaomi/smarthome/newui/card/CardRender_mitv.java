package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.MiTvCardItem;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_mitv<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    ProgressItemView f;
    View g;

    public CardRender_mitv(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
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

    public void b() {
        super.b();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_mitv, this.c, false);
        this.c.addView(inflate);
        ((MiTvCardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_content), 1, 0);
        a(this.c, this.e);
        a(inflate);
    }
}
