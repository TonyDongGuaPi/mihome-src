package com.xiaomi.payment.giftcard;

import android.content.Context;
import android.content.res.Resources;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxGiftcardTask;

class AvailableGiftcardAdatper extends GiftcardAdatper {
    public AvailableGiftcardAdatper(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.d.setVisibility(8);
        this.e.setVisibility(8);
        this.c.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_giftcard_item_light));
        this.d.setVisibility(0);
        this.d.setText(this.f7498a.getString(R.string.mibi_giftcard_in_available));
        this.d.setTextColor(this.f7498a.getResources().getColor(R.color.mibi_text_color_giftcard_item_light));
    }

    /* access modifiers changed from: protected */
    public String a(Resources resources, RxGiftcardTask.Result.GiftcardItem giftcardItem) {
        StringBuilder sb = new StringBuilder();
        sb.append(resources.getString(R.string.mibi_unit_value_remained, new Object[]{Utils.a(giftcardItem.mGiftcardAvailableBalance)}));
        if (giftcardItem.mOrderFeeRequired > 0) {
            sb.append(" ");
            sb.append(resources.getString(R.string.mibi_giftcard_discount_detail2, new Object[]{Utils.a((long) giftcardItem.mOrderFeeRequired)}));
        }
        return sb.toString();
    }
}
