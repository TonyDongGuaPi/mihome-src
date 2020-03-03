package com.xiaomi.smarthome.newui.card.spec;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueList;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ValueRange;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.Operation;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import com.xiaomi.smarthome.newui.widget.ShiftChooser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class SpecShiftChooserCardItem extends SpecCardItem {
    String[] I;
    private ShiftChooser M;
    private List<Integer> N = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    int f20602a = 0;

    public SpecShiftChooserCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        SpecCard specCard = (SpecCard) l();
        Device k = k();
        ShiftChooser shiftChooser = (ShiftChooser) a(viewGroup, R.layout.card_item_shift_chooser).findViewById(R.id.shift_chooser);
        if (this.K == null || !(this.K.second instanceof SpecProperty)) {
            Log.e("mijia-card", "SpecAirPurifierTextNumCardItem.changeSpeedIfNeed  mPair.second is not SpecProperty");
            return;
        }
        this.M = shiftChooser;
        if (shiftChooser != null) {
            ConstraintValue constraintValue = ((SpecProperty) this.K.second).getPropertyDefinition().getConstraintValue();
            if (constraintValue instanceof ValueRange) {
                ValueRange valueRange = (ValueRange) constraintValue;
                double a2 = NumberUtils.a(valueRange.maxValue().getObjectValue(), 1.0d);
                double a3 = NumberUtils.a(valueRange.stepValue().getObjectValue(), 0.5d);
                this.N.clear();
                for (double a4 = NumberUtils.a(valueRange.minValue().getObjectValue(), 0.0d); a4 <= a2; a4 += a3) {
                    this.N.add(Integer.valueOf((int) a4));
                }
            } else if (constraintValue instanceof ValueList) {
                List<ValueDefinition> values = ((ValueList) constraintValue).values();
                if (values == null || values.size() == 0) {
                    shiftChooser.setVisibility(8);
                    LogUtilGrey.a("mijia-card", "SpecShiftChooserCardItem values is null");
                    return;
                }
                this.N.clear();
                for (ValueDefinition value : values) {
                    this.N.add(Integer.valueOf(NumberUtils.a(value.value().getObjectValue(), 0)));
                }
            }
            this.I = new String[this.N.size()];
            String b = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K);
            for (int i3 = 0; i3 < this.N.size(); i3++) {
                String a5 = ((SpecCardType) this.G).a(k(), (Pair<SpecService, ? extends Spec.SpecItem>) this.K, (Object) this.N.get(i3));
                if (TextUtils.isEmpty(a5) || a5.equals(b)) {
                    String[] strArr = this.I;
                    strArr[i3] = this.N.get(i3) + b;
                } else {
                    this.I[i3] = a5;
                }
            }
            shiftChooser.setTotalShifts(this.N.size());
            shiftChooser.setShiftsTitles(this.I);
            a(this.w, ((SpecCardType) this.G).b(k, (Pair<SpecService, ? extends Spec.SpecItem>) this.K));
            shiftChooser.setOnShiftChangedListener(new ShiftChooser.OnShiftChangedListener() {
                public final void onShiftChanged(ShiftChooser shiftChooser, int i) {
                    SpecShiftChooserCardItem.this.a(shiftChooser, i);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ShiftChooser shiftChooser, int i) {
        if (!j()) {
            MiotSpecCardManager.f().a(k().did, (SpecService) this.K.first, (SpecProperty) this.K.second, this.N.get(i), (AsyncCallback<JSONObject, Error>) null);
        }
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
        this.M = null;
    }

    private void d(Object obj) {
        ShiftChooser shiftChooser = this.M;
        if (shiftChooser != null && obj != null) {
            for (int i = 0; i < this.N.size(); i++) {
                if (String.valueOf(obj).equals(String.valueOf(this.N.get(i)))) {
                    shiftChooser.scrollToShift(i, false);
                    this.f20602a = i;
                    return;
                }
            }
        }
    }

    private void a(Device device, Card card) {
        b(a(device, (Operation) null));
    }

    private void b(boolean z) {
        ShiftChooser shiftChooser = this.M;
        if (shiftChooser != null) {
            shiftChooser.setCanChoose(z);
            shiftChooser.setClickable(z);
            shiftChooser.setFocusable(z);
            shiftChooser.updateEnableUI(z);
        }
    }

    public void a(String str, Object obj) {
        ShiftChooser shiftChooser = this.M;
        Device k = k();
        if (!j() && shiftChooser != null) {
            a(k, l());
            d(obj);
        }
    }
}
