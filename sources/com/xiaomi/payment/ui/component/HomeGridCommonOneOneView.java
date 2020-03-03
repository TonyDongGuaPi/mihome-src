package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xiaomi.payment.data.UnevenGridData;
import com.xiaomi.payment.platform.R;

public class HomeGridCommonOneOneView extends BaseHomeGridCommonView {

    /* renamed from: a  reason: collision with root package name */
    private TextView f12466a;

    public HomeGridCommonOneOneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f12466a = (TextView) findViewById(R.id.title);
    }

    public void bindItemInfo(UnevenGridData.SingleGridItemInfo singleGridItemInfo) {
        super.bindItemInfo(singleGridItemInfo);
        String str = "";
        String str2 = "";
        if (singleGridItemInfo.g == 0) {
            UnevenGridData.NormalGridItemInfo normalGridItemInfo = (UnevenGridData.NormalGridItemInfo) singleGridItemInfo;
            str = normalGridItemInfo.f12234a;
            str2 = normalGridItemInfo.c;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f12466a.setText(str);
            this.f12466a.setVisibility(0);
        } else {
            this.f12466a.setVisibility(8);
        }
        setImageUrl(str2);
    }
}
