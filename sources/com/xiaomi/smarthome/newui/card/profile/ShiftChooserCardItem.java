package com.xiaomi.smarthome.newui.card.profile;

import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.widget.ShiftChooser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ShiftChooserCardItem extends ProfileCardItem {
    int I = 0;
    String[] J;
    private ShiftChooser S;

    /* renamed from: a  reason: collision with root package name */
    List<Integer> f20562a = new ArrayList();

    public ShiftChooserCardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        ProfileCard profileCard = (ProfileCard) l();
        final Device k = k();
        ShiftChooser shiftChooser = (ShiftChooser) a(viewGroup, R.layout.card_item_shift_chooser).findViewById(R.id.shift_chooser);
        this.S = shiftChooser;
        if (shiftChooser != null) {
            shiftChooser.setTotalShifts(this.y.size());
            this.J = new String[this.y.size()];
            for (int i3 = 0; i3 < this.y.size(); i3++) {
                this.J[i3] = a(((Operation) this.y.get(i3)).f20520a);
            }
            shiftChooser.setShiftsTitles(this.J);
            a(this.w, ((ProfileCardType) this.G).b(k(), this.w));
            shiftChooser.setOnShiftChangedListener(new ShiftChooser.OnShiftChangedListener() {
                public void onShiftChanged(ShiftChooser shiftChooser, int i) {
                    if (!ShiftChooserCardItem.this.j() && (k instanceof MiioDeviceV2)) {
                        Operation operation = (Operation) ShiftChooserCardItem.this.y.get(i);
                        operation.a((ProfileCardType) ShiftChooserCardItem.this.G, operation.b, k, (AsyncCallback<JSONObject, Error>) null);
                    }
                }
            });
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.S = null;
    }

    private void e(Object obj) {
        k();
        ShiftChooser shiftChooser = this.S;
        if (shiftChooser != null && obj != null) {
            int i = 0;
            while (i < this.y.size()) {
                Operation operation = (Operation) this.y.get(i);
                if (operation == null || !operation.a(String.valueOf(obj))) {
                    i++;
                } else {
                    shiftChooser.scrollToShift(i, false);
                    this.I = i;
                    return;
                }
            }
        }
    }

    private void a(Device device, Card card, ShiftChooser shiftChooser) {
        this.f20562a.clear();
        for (int i = 0; i < this.y.size(); i++) {
            if (!a(device, (Operation) this.y.get(i))) {
                this.f20562a.add(Integer.valueOf(i));
            }
        }
        if (this.f20562a.size() >= shiftChooser.getTotalShifts()) {
            b(false);
        } else {
            b(true);
        }
    }

    private void b(boolean z) {
        ShiftChooser shiftChooser = this.S;
        if (shiftChooser != null) {
            shiftChooser.setCanChoose(z);
            shiftChooser.setClickable(z);
            shiftChooser.setFocusable(z);
            shiftChooser.updateEnableUI(z);
        }
    }

    public void a(String str, Object obj) {
        ShiftChooser shiftChooser = this.S;
        Device k = k();
        if (!j() && k != null && shiftChooser != null) {
            a(k, l(), shiftChooser);
            e(obj);
        }
    }
}
