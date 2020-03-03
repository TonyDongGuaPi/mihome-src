package com.xiaomi.payment.giftcard;

import android.content.Context;

class GiftcardAdapterFactory {
    GiftcardAdapterFactory() {
    }

    public static GiftcardAdatper a(Context context, int i) {
        switch (i) {
            case 1:
                return new AvailableGiftcardAdatper(context);
            case 2:
                return new UnvailableGiftcardAdatper(context);
            case 3:
                return new UsedGiftcardAdatper(context);
            default:
                throw new IllegalStateException("type error:" + i);
        }
    }
}
