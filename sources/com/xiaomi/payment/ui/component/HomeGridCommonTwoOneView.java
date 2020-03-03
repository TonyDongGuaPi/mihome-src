package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.platform.R;

public class HomeGridCommonTwoOneView extends BaseHomeGridCommonView {

    /* renamed from: a  reason: collision with root package name */
    private TextView f12467a;
    private TextView b;

    public HomeGridCommonTwoOneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f12467a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.summary);
    }

    public void bindItemInfo(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
        super.bindItemInfo(singleGridItemInfo);
        String str = "";
        String str2 = "";
        String str3 = "";
        if (singleGridItemInfo.g == 0) {
            UnevenGridData.NormalGridItemInfo normalGridItemInfo = (UnevenGridData.NormalGridItemInfo) singleGridItemInfo;
            str = normalGridItemInfo.f12234a;
            str3 = normalGridItemInfo.b;
            str2 = normalGridItemInfo.c;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f12467a.setText(str);
            this.f12467a.setVisibility(0);
        } else {
            this.f12467a.setVisibility(8);
        }
        if (!TextUtils.isEmpty(str3)) {
            this.b.setText(str3);
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        setImageUrl(str2);
    }
}
