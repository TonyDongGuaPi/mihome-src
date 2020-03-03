package com.xiaomi.smarthome.newui.card;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.measurement.AppMeasurement;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;

public class CardRender_2_item_vertical<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    private ProgressItemView f;
    private View g;

    public CardRender_2_item_vertical(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public void b() {
        super.b();
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_4_item_2_line, this.c, false);
        this.c.addView(inflate);
        int size = this.f20457a.size();
        try {
            ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_topleft), size, 0);
            ((CardItem) this.f20457a.get(1)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_topright), size, 1);
            ((CardItem) this.f20457a.get(2)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_bottomleft), size, 2);
            ((CardItem) this.f20457a.get(3)).a((ViewGroup) inflate.findViewById(R.id.ll_layout_bottomright), size, 3);
        } catch (Exception e) {
            Log.e("mijia-card", AppMeasurement.Param.FATAL, e);
            ToastManager a2 = ToastManager.a();
            a2.a("卡片样式配置错误 " + this.b.b);
        }
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
