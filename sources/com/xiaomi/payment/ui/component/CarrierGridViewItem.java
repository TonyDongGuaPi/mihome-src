package com.xiaomi.payment.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.mibi.common.component.CommonGridViewItem;
import com.xiaomi.payment.platform.R;

public class CarrierGridViewItem extends CommonGridViewItem {

    /* renamed from: a  reason: collision with root package name */
    private Carrier f12451a;
    private TextView b;
    private ImageView c;
    private String d;

    private enum Carrier {
        CMCC(R.drawable.mibi_carrier_cmcc_icon),
        UNICOM(R.drawable.mibi_carrier_unicom_icon),
        TELCOM(R.drawable.mibi_carrier_telcom_icon);
        
        private int mImageRes;

        private Carrier(int i) {
            this.mImageRes = i;
        }

        public int getImageRes() {
            return this.mImageRes;
        }

        public static Carrier getFromName(String str) {
            if (str.equalsIgnoreCase("CMCC")) {
                return CMCC;
            }
            if (str.equalsIgnoreCase("UNICOM")) {
                return UNICOM;
            }
            if (str.equalsIgnoreCase("TELCOM")) {
                return TELCOM;
            }
            return null;
        }
    }

    public CarrierGridViewItem(Context context) {
        this(context, (AttributeSet) null);
    }

    public CarrierGridViewItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.carrier_title);
        this.c = (ImageView) findViewById(R.id.carrier_icon);
    }

    public void setCarrierInfo(String str, String str2) {
        this.d = str;
        this.f12451a = Carrier.getFromName(str);
        if (this.f12451a != null) {
            this.c.setImageResource(this.f12451a.getImageRes());
        }
        this.b.setText(str2);
    }

    public String getCarrierName() {
        return this.d;
    }
}
