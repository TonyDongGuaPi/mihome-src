package com.xiaomi.smarthome.newui.card;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.card.profile.MultiButtonCardItem;
import com.xiaomi.smarthome.newui.card.profile.PaletteCardItem;
import com.xiaomi.smarthome.newui.card.profile.PaletteCtCardItem;
import com.xiaomi.smarthome.newui.widget.DeviceDownloadItemViewWrapper;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import java.util.ArrayList;
import java.util.HashSet;

public class CardRender_1_item<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    public static final int f = 4;
    ProgressItemView g;
    View h;
    private HashSet<String> i = new HashSet<>();
    private CameraCardItem j;

    public CardRender_1_item(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }

    public boolean a(String str, CameraCardItem cameraCardItem) {
        if (this.i.contains(str)) {
            return true;
        }
        Intent intent = new Intent(this.d, GlobalSetting.A);
        intent.putExtra("extra_device_did", str);
        intent.putExtra("verfy_pincode_first", true);
        ((Activity) this.d).startActivityForResult(intent, 4);
        this.j = cameraCardItem;
        return false;
    }

    public void a(boolean z) {
        if (z && this.j != null) {
            this.i.add(this.j.g());
            this.j.h();
        }
    }

    public void b() {
        super.b();
        if (this.f20457a.size() == 3 && (this.f20457a.get(1) instanceof PaletteCardItem) && (this.f20457a.get(2) instanceof PaletteCtCardItem)) {
            l();
        } else if (this.f20457a.size() == 1 && (this.f20457a.get(0) instanceof CameraCardItem)) {
            k();
        } else if (this.f20457a.size() == 1) {
            j();
        } else if (this.f20457a.size() == 2) {
            m();
        } else if (this.f20457a.size() == 3) {
            n();
        } else {
            throw new IllegalArgumentException("the number of cardItem illegal " + this.e.getName() + "model:" + this.e.model);
        }
    }

    public void h() {
        if (this.f20457a.size() == 1 && (this.f20457a.get(0) instanceof CameraCardItem)) {
            ((CameraCardItem) this.f20457a.get(0)).d();
        }
    }

    public ProgressItemView c() {
        return this.g;
    }

    public View d() {
        return this.h;
    }

    private void j() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item, this.c, false);
        CardItem cardItem = (CardItem) this.f20457a.get(0);
        if (cardItem instanceof MultiButtonCardItem) {
            inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_multibutton, this.c, false);
        }
        this.c.addView(inflate);
        cardItem.a((ViewGroup) inflate.findViewById(R.id.ll_content), 1, 0);
        a(this.c, this.e);
        a(inflate);
    }

    private void k() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_camera, this.c, false);
        this.c.addView(inflate);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_content), 1, 0);
        a(this.c, this.e);
        a(inflate);
    }

    private void l() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_lamp, this.c, false);
        this.c.addView(inflate);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) inflate.findViewById(R.id.ll_title_content), 3, 0);
        PaletteCardItem paletteCardItem = (PaletteCardItem) this.f20457a.get(1);
        PaletteCtCardItem paletteCtCardItem = (PaletteCtCardItem) this.f20457a.get(2);
        paletteCardItem.a(paletteCtCardItem);
        paletteCtCardItem.a((ViewGroup) inflate.findViewById(R.id.ll_content), 3, 1);
        paletteCardItem.a((ViewGroup) inflate.findViewById(R.id.ll_content), 3, 1);
        a(this.c, this.e);
        a(inflate);
    }

    private void m() {
        View view;
        if (((CardItem) this.f20457a.get(1)).G.d()) {
            view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_2_operation, this.c, false);
        } else {
            view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_2_subitem, this.c, false);
        }
        this.c.addView(view);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) view.findViewById(R.id.ll_left), 2, 0);
        ((CardItem) this.f20457a.get(1)).a((ViewGroup) view.findViewById(R.id.ll_right), 2, 1);
        a(this.c, this.e);
        a(view);
    }

    private void n() {
        View view;
        if (((CardItem) this.f20457a.get(2)).G.d()) {
            view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_3_operation, this.c, false);
        } else {
            view = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_1_item_3_subitem, this.c, false);
        }
        this.c.addView(view);
        ((CardItem) this.f20457a.get(0)).a((ViewGroup) view.findViewById(R.id.ll_title_content), 3, 0);
        ((CardItem) this.f20457a.get(1)).a((ViewGroup) view.findViewById(R.id.ll_left), 3, 1);
        ((CardItem) this.f20457a.get(2)).a((ViewGroup) view.findViewById(R.id.ll_right), 3, 2);
        a(this.c, this.e);
        a(view);
    }

    public void a(DeviceDownloadItemViewWrapper deviceDownloadItemViewWrapper) {
        if (this.f20457a.get(0) instanceof CameraCardItem) {
            ((CardItem) this.f20457a.get(0)).a(deviceDownloadItemViewWrapper);
        }
    }

    private void a(View view) {
        this.g = (ProgressItemView) view.findViewById(R.id.dpb_enter_device);
        this.h = view.findViewById(R.id.progress_enter_device);
    }
}
