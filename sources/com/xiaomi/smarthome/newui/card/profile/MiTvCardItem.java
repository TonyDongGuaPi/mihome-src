package com.xiaomi.smarthome.newui.card.profile;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.other.OtherCard;
import com.xiaomi.smarthome.newui.card.other.OtherCardType;
import com.xiaomi.smarthome.newui.widget.CirclePadButton;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import org.apache.http.NameValuePair;

public class MiTvCardItem extends CardItem<OtherCard, OtherCardType, Object> {
    private static final String I = "xiaomi.tv.v1";

    /* renamed from: a  reason: collision with root package name */
    private static final String f20544a = "http://%s:6095/controller?action=keyevent&keycode=%s";
    private View J;
    private CirclePadButton K;

    public MiTvCardItem(OtherCardType otherCardType) {
        super(otherCardType, (T[]) null);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        this.J = a(viewGroup, R.layout.card_item_mitv);
        View view = this.J;
        if (view != null) {
            this.K = (CirclePadButton) view.findViewById(R.id.circle_pad);
            CirclePadButton circlePadButton = this.K;
            if (circlePadButton != null) {
                if (k().isOnline) {
                    b(true);
                } else {
                    b(false);
                }
                circlePadButton.setListener(new CirclePadButton.ClickListener() {
                    public void onClick(CirclePadButton.MODE mode) {
                        if (!MiTvCardItem.this.j()) {
                            switch (AnonymousClass3.f20547a[mode.ordinal()]) {
                                case 1:
                                    MiTvCardItem.this.c(CameraPropertyBase.l);
                                    return;
                                case 2:
                                    MiTvCardItem.this.c("volumeup");
                                    return;
                                case 3:
                                    MiTvCardItem.this.c("volumedown");
                                    return;
                                default:
                                    return;
                            }
                        }
                    }
                });
            }
        }
    }

    /* renamed from: com.xiaomi.smarthome.newui.card.profile.MiTvCardItem$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f20547a = new int[CirclePadButton.MODE.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.xiaomi.smarthome.newui.widget.CirclePadButton$MODE[] r0 = com.xiaomi.smarthome.newui.widget.CirclePadButton.MODE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f20547a = r0
                int[] r0 = f20547a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.smarthome.newui.widget.CirclePadButton$MODE r1 = com.xiaomi.smarthome.newui.widget.CirclePadButton.MODE.Switch     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f20547a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.smarthome.newui.widget.CirclePadButton$MODE r1 = com.xiaomi.smarthome.newui.widget.CirclePadButton.MODE.Plus     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f20547a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.smarthome.newui.widget.CirclePadButton$MODE r1 = com.xiaomi.smarthome.newui.widget.CirclePadButton.MODE.Minus     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.MiTvCardItem.AnonymousClass3.<clinit>():void");
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.J = null;
        this.K = null;
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        b(false);
        if (k().location == Device.Location.REMOTE) {
            Toast.makeText(CommonApplication.getAppContext(), R.string.mitv_is_not_in_local, 0).show();
        }
        XmPluginHostApi.instance().callHttpApi("xiaomi.tv.v1", String.format(f20544a, new Object[]{k().ip, str}), "GET", (List<NameValuePair>) null, new Callback<Void>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (!MiTvCardItem.this.j()) {
                    MiTvCardItem.this.k().location = Device.Location.LOCAL;
                    MiTvCardItem.this.b(true);
                }
            }

            public void onFailure(int i, String str) {
                if (!MiTvCardItem.this.j()) {
                    MiTvCardItem.this.k().location = Device.Location.REMOTE;
                    MiTvCardItem.this.b(true);
                }
            }
        }, (Parser) null);
        if (!TextUtils.isEmpty(MIUI10CardActivity.sRoomName)) {
            STAT.d.c(k().model, MIUI10CardActivity.sRoomName, DeviceUtils.c(k()));
        }
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        CirclePadButton circlePadButton = this.K;
        if (circlePadButton != null) {
            circlePadButton.setTouchable(z);
            circlePadButton.updateUnableUI(z);
        }
    }

    public void a(String str, Object obj) {
        if (!j() && k() != null) {
            if (k().isOnline) {
                b(true);
            } else {
                b(false);
            }
        }
    }
}
