package com.xiaomi.smarthome.newui.card.spec;

import android.util.Pair;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.carditem.NotSupportCardItem;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.card.profile.SpecCardType;
import java.util.ArrayList;

public abstract class SpecCardItem extends CardItem<SpecCard, SpecCardType, Pair<SpecService, ? extends Spec.SpecItem>> {
    protected final ArrayList<String> J;
    public final Pair<SpecService, ? extends Spec.SpecItem> K;
    public final PropItem L;

    protected SpecCardItem(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        super(specCardType, pairArr);
        this.L = specCardType.e;
        this.J = specCardType.j;
        this.K = pairArr[0];
    }

    public static CardItem a(SpecCardType specCardType, Pair<SpecService, ? extends Spec.SpecItem>[] pairArr) {
        switch (specCardType.b) {
            case 1:
                return new SpecSingleButtonItem(specCardType, pairArr);
            case 2:
                return new SpecToggleButtonItem(specCardType, pairArr);
            case 3:
                return new SpecMultiButtonCardItem(specCardType, pairArr);
            case 4:
                return new SpecPlusMinusCardItem(specCardType, pairArr);
            case 5:
                return new SpecSlideCardItem2(specCardType, pairArr);
            case 7:
                return new SpecTextNumberCardItem(specCardType, pairArr);
            case 8:
                return new SpecTextStringCardItem(specCardType, pairArr);
            case 11:
                return new SpecGradientSlideCardItem(specCardType, pairArr);
            case 12:
                return new SpecShiftChooserCardItem(specCardType, pairArr);
            case 14:
                return new SpecRobotAnimCardItem(specCardType, pairArr);
            case 17:
                return new SpecAirPurifierTextNumCardItem(specCardType, pairArr);
            default:
                return new NotSupportCardItem(specCardType, pairArr);
        }
    }
}
