package com.mi.global.shop.buy.model;

import com.mi.global.shop.model.common.UserCardsType;
import com.squareup.wire.Wire;

public class CreditCardItem {

    /* renamed from: a  reason: collision with root package name */
    public String f6883a;
    public String b;
    public boolean c = false;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;

    public static CreditCardItem a(UserCardsType userCardsType) {
        CreditCardItem creditCardItem = new CreditCardItem();
        creditCardItem.b = (String) Wire.get(userCardsType.card_type, "");
        creditCardItem.d = (String) Wire.get(userCardsType.card_token, "");
        creditCardItem.e = (String) Wire.get(userCardsType.card_no, "");
        creditCardItem.f = (String) Wire.get(userCardsType.card_mode, "");
        creditCardItem.f6883a = ((String) Wire.get(userCardsType.expiry_month, "")) + " / " + ((String) Wire.get(userCardsType.expiry_year, ""));
        creditCardItem.g = (String) Wire.get(userCardsType.name_on_card, "");
        creditCardItem.h = (String) Wire.get(userCardsType.card_brand, "");
        return creditCardItem;
    }
}
