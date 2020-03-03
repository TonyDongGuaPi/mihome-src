package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.MultiButtonCardItem;
import com.xiaomi.smarthome.newui.card.profile.PlusMinusCardItem;
import com.xiaomi.smarthome.newui.card.spec.SpecMultiButtonCardItem;
import com.xiaomi.smarthome.newui.card.spec.SpecPlusMinusCardItem;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_2_item_horizontal2<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_2_item_horizontal2(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        super.b();
        if (this.f20457a.size() == 2) {
            k();
        }
        if (this.f20457a.size() == 3) {
            j();
        }
        this.f20457a.size();
    }

    private void j() {
        CardItem cardItem = (CardItem) this.f20457a.get(0);
        CardItem cardItem2 = (CardItem) this.f20457a.get(1);
        View inflate = (!cardItem.G.e() || !cardItem2.G.f()) ? null : LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_number_2button, this.c, false);
        if (cardItem.G.b() && cardItem2.G.f()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_text_2button, this.c, false);
        }
        if (cardItem.G.g() && cardItem2.G.f()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_robotanim_2button, this.c, false);
        }
        if (inflate != null) {
            this.c.addView(inflate);
            cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_layout_top), 3, 0);
            cardItem2.a((ViewGroup) inflate.findViewById(R.id.ll_left), 3, 1);
            ((CardItem) this.f20457a.get(2)).a((ViewGroup) inflate.findViewById(R.id.ll_right), 3, 2);
            a(this.c, this.e);
            a(inflate);
            return;
        }
        if (cardItem.G.f() && cardItem2.G.f() && ((CardItem) this.f20457a.get(2)).G.d()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_2button_longshape, this.c, false);
        }
        if (inflate != null) {
            this.c.addView(inflate);
            cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_left), 3, 0);
            cardItem2.a((ViewGroup) inflate.findViewById(R.id.ll_right), 3, 1);
            ((CardItem) this.f20457a.get(2)).a((ViewGroup) inflate.findViewById(R.id.ll_bottom), 3, 2);
            a(this.c, this.e);
            a(inflate);
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

    private void k() {
        CardItem cardItem = (CardItem) this.f20457a.get(0);
        CardItem cardItem2 = (CardItem) this.f20457a.get(1);
        View inflate = (!cardItem.G.d() || !cardItem2.G.d()) ? null : LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_2_long_shape, this.c, false);
        if (cardItem.G.f()) {
            if ((cardItem2 instanceof PlusMinusCardItem) || (cardItem2 instanceof SpecPlusMinusCardItem) || (cardItem2 instanceof MultiButtonCardItem) || (cardItem2 instanceof SpecMultiButtonCardItem)) {
                inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_button_long_shape, this.c, false);
            } else if (cardItem2.G.d()) {
                View inflate2 = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_2_operation, this.c, false);
                this.c.addView(inflate2);
                ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate2.findViewById(R.id.ll_left), 2, 0);
                ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate2.findViewById(R.id.ll_right), 2, 1);
                a(this.c, this.e);
                a(inflate2);
                return;
            }
        }
        if (cardItem.G.e()) {
            if (cardItem2.G.d()) {
                inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_number_long_shape, this.c, false);
            }
            if (cardItem2.G.f()) {
                inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_number_button, this.c, false);
            }
        }
        if (cardItem.G.b() && cardItem2.G.f()) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_text_button, this.c, false);
        }
        if (inflate != null) {
            this.c.addView(inflate);
            ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_top), 2, 0);
            ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_bottom), 2, 1);
            a(this.c, this.e);
            a(inflate);
        }
    }
}
