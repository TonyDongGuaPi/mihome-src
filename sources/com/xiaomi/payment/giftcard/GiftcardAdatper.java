package com.xiaomi.payment.giftcard;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mibi.common.data.ArrayAdapter;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxGiftcardTask;
import java.util.Calendar;

abstract class GiftcardAdatper extends ArrayAdapter<RxGiftcardTask.Result.GiftcardItem> {
    protected TextView c;
    protected TextView d;
    protected Button e;

    /* access modifiers changed from: protected */
    public abstract String a(Resources resources, RxGiftcardTask.Result.GiftcardItem giftcardItem);

    /* access modifiers changed from: protected */
    public abstract void b();

    GiftcardAdatper(Context context) {
        super(context);
    }

    public final View a(Context context, int i, RxGiftcardTask.Result.GiftcardItem giftcardItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.mibi_giftcard_item, viewGroup, false);
        this.c = (TextView) inflate.findViewById(R.id.balance_with_unit);
        this.d = (TextView) inflate.findViewById(R.id.status);
        this.e = (Button) inflate.findViewById(R.id.use_now);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        b();
        return inflate;
    }

    public void a(View view, int i, RxGiftcardTask.Result.GiftcardItem giftcardItem) {
        if (giftcardItem != null) {
            Resources resources = this.f7498a.getResources();
            ((TextView) view.findViewById(R.id.balance_with_unit)).setText(a(resources, giftcardItem));
            ((TextView) view.findViewById(R.id.giftcard_item_detail)).setText(resources.getString(R.string.mibi_giftcard_acitivity_and_denomination, new Object[]{giftcardItem.mActivityDesc, Utils.a(giftcardItem.mGiftcardTotalBalance)}));
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(giftcardItem.mExpiredTime);
            ((TextView) view.findViewById(R.id.expired_time)).setText(resources.getString(R.string.mibi_giftcard_vaild_util, new Object[]{DateFormat.format(resources.getString(R.string.mibi_format_date), instance).toString()}));
            return;
        }
        throw new IllegalStateException("GiftcardItem data is null at this position " + i);
    }
}
