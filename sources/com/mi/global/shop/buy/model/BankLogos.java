package com.mi.global.shop.buy.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import java.util.HashMap;
import java.util.Map;

public class BankLogos {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6880a = "CITI";
    private static final String b = "HDFC";
    private static final String c = "AXIB";
    private static final String d = "HDFB";
    private static final String e = "ICIB";
    private static final String f = "SBIB";
    private static final String g = "ADBB";
    private static final String h = "CABB";
    private static final String i = "AXIS";
    private static final String j = "AMEX";
    private static final String k = "ICICI";
    private static final String l = "KOTAK";
    private static final String m = "SBI";
    private static final String n = "BFL";
    private static Map<String, Drawable> o = new HashMap();

    static {
        Resources resources = ShopApp.g().getResources();
        o.put(f6880a, resources.getDrawable(R.drawable.shop_netbank_citi));
        o.put(b, resources.getDrawable(R.drawable.shop_netbank_hdfc));
        o.put(c, resources.getDrawable(R.drawable.shop_netbank_axis));
        o.put(d, resources.getDrawable(R.drawable.shop_netbank_hdfc));
        o.put(e, resources.getDrawable(R.drawable.shop_netbank_icici));
        o.put(f, resources.getDrawable(R.drawable.shop_netbank_indiabank));
        o.put(g, resources.getDrawable(R.drawable.shop_netbank_adbb));
        o.put(h, resources.getDrawable(R.drawable.shop_netbank_cabb));
        o.put(i, resources.getDrawable(R.drawable.shop_netbank_axis));
        o.put("AMEX", resources.getDrawable(R.drawable.shop_emi_amex));
        o.put(k, resources.getDrawable(R.drawable.shop_netbank_icici));
        o.put(l, resources.getDrawable(R.drawable.shop_emi_kotak));
        o.put(m, resources.getDrawable(R.drawable.shop_netbank_indiabank));
        o.put("BFL", resources.getDrawable(R.drawable.netbank_bfl));
    }

    public static Drawable a(String str) {
        return o.get(str);
    }
}
