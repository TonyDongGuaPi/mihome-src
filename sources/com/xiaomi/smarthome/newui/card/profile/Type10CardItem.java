package com.xiaomi.smarthome.newui.card.profile;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.miotcard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class Type10CardItem extends ProfileCardItem {
    public void a(String str, Object obj) {
    }

    public Type10CardItem(ProfileCardType profileCardType) {
        super(profileCardType);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        SimpleDateFormat simpleDateFormat;
        super.a(viewGroup, i, i2);
        View a2 = a(viewGroup, R.layout.miui10_card_item_text_number);
        TextView textView = (TextView) a2.findViewById(R.id.desc);
        TextView textView2 = (TextView) a2.findViewById(R.id.value);
        TextView textView3 = (TextView) a2.findViewById(R.id.unit);
        a(a2, i, i2);
        String a3 = a();
        if (a3 != null) {
            textView.setText(a3);
        } else {
            textView.setText(o());
        }
        Pair<Object, Long> e = ((ProfileCardType) this.G).d(k(), this.w);
        if (e != null) {
            long parseLong = Long.parseLong(String.valueOf(e.second));
            Locale a4 = LocaleUtil.a();
            if ((System.currentTimeMillis() / 1000) - 86400 > parseLong) {
                simpleDateFormat = new SimpleDateFormat("MM/dd", a4);
            } else {
                simpleDateFormat = new SimpleDateFormat("HH:mm", a4);
            }
            textView2.setText(simpleDateFormat.format(new Date(parseLong * 1000)));
        }
    }

    /* access modifiers changed from: protected */
    public String a() {
        Map<String, String> map;
        if (n() == null || (map = n().l) == null) {
            return null;
        }
        return a(map);
    }

    public void i() {
        super.i();
        a(true);
        this.E = null;
    }
}
