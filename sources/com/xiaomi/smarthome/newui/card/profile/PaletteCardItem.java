package com.xiaomi.smarthome.newui.card.profile;

import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.ProductModel;
import com.xiaomi.smarthome.newui.card.profile.TouchView;
import com.xiaomi.smarthome.newui.card.profile.YeelightControlView;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import org.json.JSONObject;

public class PaletteCardItem extends ProfileCardItem {
    PaletteCtCardItem I;
    String J = "off";

    /* renamed from: a  reason: collision with root package name */
    YeelightControlView f20550a;

    public PaletteCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(PaletteCtCardItem paletteCtCardItem) {
        this.I = paletteCtCardItem;
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        this.f20550a = (YeelightControlView) a(viewGroup, R.layout.card_item_palette).findViewById(R.id.controlView);
        YeelightControlView yeelightControlView = this.f20550a;
        if (this.f20550a != null) {
            yeelightControlView.setProductModel(new ProductModel.Color(ProductModel.d));
            yeelightControlView.addActionListener(new YeelightControlView.ActionListener() {
                public void a() {
                }

                public void a(int i, int[] iArr, int i2) {
                }

                public void a(boolean z) {
                }

                public void a(boolean z, int i) {
                }

                public void b() {
                }

                public void c() {
                }

                public void c(int i) {
                }

                public void d() {
                }

                public void d(int i) {
                }

                public void e() {
                }

                public void e(int i) {
                }

                public void f() {
                }

                public void f(int i) {
                }

                public void g() {
                }

                public void g(int i) {
                }

                public void h() {
                }

                public void i() {
                }

                public void a(int i) {
                    if (!PaletteCardItem.this.j() && (PaletteCardItem.this.k() instanceof MiioDeviceV2)) {
                        PaletteCardItem.this.a((Object) Integer.valueOf(i), PaletteCardItem.this.k(), (AsyncCallback<JSONObject, Error>) null);
                    }
                }

                public void b(int i) {
                    if (!PaletteCardItem.this.j() && (PaletteCardItem.this.k() instanceof MiioDeviceV2)) {
                        PaletteCardItem.this.b(Integer.valueOf(i), PaletteCardItem.this.k(), (AsyncCallback<JSONObject, Error>) null);
                    }
                }
            });
            int a2 = a();
            int b = b();
            this.J = a(k(), (Operation) this.y.get(0)) ? "on" : "off";
            yeelightControlView.initState(new LightState(this.J, TouchView.ControlMode.SUNSHINE.ordinal(), a2, b, -65536, !this.J.equals("on")), false);
        }
    }

    public void i() {
        super.i();
        a(true);
        YeelightControlView yeelightControlView = this.f20550a;
        if (yeelightControlView != null) {
            yeelightControlView.onPause();
        }
        this.E = null;
        this.f20550a = null;
        this.I = null;
    }

    public void a(String str, Object obj) {
        YeelightControlView yeelightControlView;
        if (!j() && k() != null && (yeelightControlView = this.f20550a) != null && this.E != null && !this.E.isFinishing()) {
            int a2 = a();
            int b = b();
            String str2 = a(k(), (Operation) this.y.get(0)) ? "on" : "off";
            boolean z = !str2.equals("on");
            if (!this.J.equals(str2)) {
                yeelightControlView.initState(new LightState(str2, TouchView.ControlMode.SUNSHINE.ordinal(), a2, b, -65536, z), false);
                this.J = str2;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Object obj, Device device, AsyncCallback<JSONObject, Error> asyncCallback) {
        ((Operation) this.y.get(0)).a((ProfileCardType) this.G, obj, device, asyncCallback);
    }

    /* access modifiers changed from: private */
    public void b(Object obj, Device device, AsyncCallback<JSONObject, Error> asyncCallback) {
        PaletteCtCardItem paletteCtCardItem = this.I;
        if (paletteCtCardItem != null) {
            paletteCtCardItem.a(obj, device, asyncCallback);
        }
    }

    private int a() {
        Object d = ((ProfileCardType) this.G).b(k(), this.w);
        if (d != null && (d instanceof String)) {
            return NumberUtils.a((Object) (String) d, 0);
        }
        return 0;
    }

    private int b() {
        Object a2;
        PaletteCtCardItem paletteCtCardItem = this.I;
        if (paletteCtCardItem == null || (a2 = paletteCtCardItem.a()) == null || !(a2 instanceof String)) {
            return YelightColorUtils.g;
        }
        return NumberUtils.a((Object) (String) a2, 0);
    }
}
