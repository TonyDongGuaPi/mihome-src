package com.xiaomi.smarthome.newui.card.profile;

import com.xiaomi.smarthome.newui.card.CameraCardItem;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.carditem.NotSupportCardItem;
import com.xiaomi.smarthome.newui.card.profile.PropItem;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.util.List;

public abstract class ProfileCardItem extends CardItem<ProfileCard, ProfileCardType, String> {
    protected final String K;
    protected final String L;
    protected final String M;
    protected final String N;
    protected final int O;
    public final String P;
    public final long Q;
    public final long R;

    protected ProfileCardItem(ProfileCardType profileCardType) {
        super(profileCardType, new String[]{profileCardType.c});
        this.P = profileCardType.i;
        this.K = profileCardType.j;
        this.R = profileCardType.k;
        this.Q = profileCardType.l;
        this.L = profileCardType.m;
        this.M = profileCardType.n;
        this.N = profileCardType.f;
        this.O = profileCardType.o;
    }

    public static CardItem a(ProfileCardType profileCardType) {
        switch (profileCardType.b) {
            case 1:
                return new SingleButtonItem(profileCardType);
            case 2:
                return new ToggleButtonItem(profileCardType);
            case 3:
                return new MultiButtonCardItem(profileCardType);
            case 4:
                return new PlusMinusCardItem(profileCardType);
            case 5:
                return new SlideCardItem2(profileCardType);
            case 6:
                return new CameraCardItem(profileCardType);
            case 7:
                return new TextNumberCardItem(profileCardType);
            case 8:
                return new TextStringCardItem(profileCardType);
            case 9:
                return new NotSupportCardItem(profileCardType, new String[]{profileCardType.c});
            case 10:
                return new Type10CardItem(profileCardType);
            case 11:
                return new GradientSlideCardItem(profileCardType);
            case 12:
                return new ShiftChooserCardItem(profileCardType);
            case 13:
                return new PaletteCardItem(profileCardType);
            case 14:
                return new RobotAnimCardItem(profileCardType);
            case 15:
                return new ImageCardItem(profileCardType);
            case 16:
                return new PaletteCtCardItem(profileCardType);
            case 17:
                return new AirPurifierTextNumCardItem(profileCardType);
            default:
                return new NotSupportCardItem(profileCardType, new String[]{profileCardType.c});
        }
    }

    public String d(Object obj) {
        List<PropItem.PropExtraItem> list;
        if (n() == null || (list = n().i) == null) {
            return null;
        }
        for (PropItem.PropExtraItem next : list) {
            if (next.f20558a == null) {
                try {
                    double a2 = NumberUtils.a((Object) String.valueOf(obj), 0.0d);
                    if (a2 >= next.c && a2 <= next.d) {
                        return next.e;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (String.valueOf(next.f20558a).equals(String.valueOf(obj))) {
                return next.e;
            }
        }
        return null;
    }

    public String o() {
        return ((ProfileCardType) this.G).a(k(), (String) m());
    }

    public ProfileCardType p() {
        return (ProfileCardType) this.G;
    }
}
