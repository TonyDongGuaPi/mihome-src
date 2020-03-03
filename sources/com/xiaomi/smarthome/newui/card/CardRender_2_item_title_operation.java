package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.PaletteCardItem;
import com.xiaomi.smarthome.newui.card.profile.PaletteCtCardItem;
import com.xiaomi.smarthome.newui.card.profile.PlusMinusCardItem;
import com.xiaomi.smarthome.newui.card.spec.SpecPlusMinusCardItem;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_2_item_title_operation<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_2_item_title_operation(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        CardItem cardItem;
        CardItem cardItem2;
        CardItem cardItem3;
        super.b();
        int size = this.f20457a.size();
        View view = null;
        if (size >= 3) {
            cardItem3 = (CardItem) this.f20457a.get(0);
            cardItem2 = (CardItem) this.f20457a.get(1);
            cardItem = (CardItem) this.f20457a.get(2);
        } else if (size == 2) {
            cardItem = (CardItem) this.f20457a.get(1);
            cardItem2 = (CardItem) this.f20457a.get(0);
            cardItem3 = null;
        } else {
            return;
        }
        if (cardItem3 == null || !cardItem3.G.h() || !cardItem2.G.f() || !cardItem.G.d()) {
            if (cardItem3 != null && cardItem3.G.g() && cardItem.G.f()) {
                view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_robotanim_2button, this.c, false);
            }
            if (size != 3 || !(cardItem2 instanceof PaletteCardItem) || !(cardItem instanceof PaletteCtCardItem)) {
                if (cardItem3 != null && cardItem3.G.e() && cardItem2.G.f() && cardItem.G.f()) {
                    view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_number_2button, this.c, false);
                }
                if (cardItem3 != null && cardItem3.G.b() && cardItem2.G.f() && cardItem.G.f()) {
                    view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_text_2button, this.c, false);
                }
                if (view != null) {
                    this.c.addView(view);
                    if (cardItem3 != null) {
                        cardItem3.a((ViewGroup) view.findViewById(R.id.ll_layout_top), 3, 0);
                    }
                    cardItem2.a((ViewGroup) view.findViewById(R.id.ll_left), 3, 1);
                    cardItem.a((ViewGroup) view.findViewById(R.id.ll_right), 3, 2);
                    a(this.c, this.e);
                    a(view);
                    return;
                }
                if (cardItem2.G.d() && cardItem.G.d()) {
                    view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_title_opration_2_long_shape, this.c, false);
                }
                if (cardItem2.G.f()) {
                    if ((cardItem instanceof PlusMinusCardItem) || (cardItem instanceof SpecPlusMinusCardItem)) {
                        view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_title_opration_button_long_shape, this.c, false);
                    } else if (cardItem.G.d()) {
                        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_3_operation, this.c, false);
                        this.c.addView(inflate);
                        if (cardItem3 != null) {
                            cardItem3.a((ViewGroup) inflate.findViewById(R.id.ll_title_content), 3, 0);
                        }
                        cardItem2.a((ViewGroup) inflate.findViewById(R.id.ll_left), 3, 1);
                        cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_right), 3, 2);
                        a(this.c, this.e);
                        a(inflate);
                        return;
                    }
                }
                if (cardItem2.G.e()) {
                    view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_title_opration_number_long_shape, this.c, false);
                }
                if (cardItem2.G.b() && cardItem.G.f()) {
                    view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_title_opration_text_button, this.c, false);
                }
                if (view == null) {
                    Device device = this.e;
                    Log.e("cardRender", "the type of cardItem illegal,model:" + (device != null ? device.model : ""));
                    return;
                }
                this.c.addView(view);
                if (cardItem3 != null) {
                    cardItem3.a((ViewGroup) view.findViewById(R.id.ll_title_content), 3, 0);
                }
                cardItem2.a((ViewGroup) view.findViewById(R.id.ll_layout_top), 3, 1);
                cardItem.a((ViewGroup) view.findViewById(R.id.ll_layout_bottom), 3, 2);
                a(this.c, this.e);
                a(view);
                return;
            }
            a(cardItem3, cardItem2, cardItem);
            return;
        }
        View inflate2 = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_2_item_vertical_2button_longshape, this.c, false);
        this.c.addView(inflate2);
        cardItem3.a((ViewGroup) inflate2.findViewById(R.id.ll_left), 3, 0);
        cardItem2.a((ViewGroup) inflate2.findViewById(R.id.ll_right), 3, 1);
        cardItem.a((ViewGroup) inflate2.findViewById(R.id.ll_bottom), 3, 2);
        a(this.c, this.e);
        a(inflate2);
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

    private void a(CardItem cardItem, CardItem cardItem2, CardItem cardItem3) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_lamp, this.c, false);
        this.c.addView(inflate);
        if (cardItem != null) {
            cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_title_content), 3, 0);
        }
        PaletteCardItem paletteCardItem = (PaletteCardItem) cardItem2;
        PaletteCtCardItem paletteCtCardItem = (PaletteCtCardItem) cardItem3;
        paletteCardItem.a(paletteCtCardItem);
        paletteCtCardItem.a((ViewGroup) inflate.findViewById(R.id.ll_content), 3, 1);
        paletteCardItem.a((ViewGroup) inflate.findViewById(R.id.ll_content), 3, 1);
        a(this.c, this.e);
        a(inflate);
    }
}
