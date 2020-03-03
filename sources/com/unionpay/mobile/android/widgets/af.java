package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextWatcher;
import com.google.code.microlog4android.format.PatternFormatter;
import com.taobao.weex.common.Constants;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class af extends aa {
    private TextWatcher c = new ag(this);
    private ArrayList<a> o = null;

    class a {
        private String b = null;
        private String c = null;
        private String d = null;

        public a(JSONObject jSONObject) {
            this.b = j.a(jSONObject, PatternFormatter.PATTERN_PROPERTY);
            this.c = j.a(jSONObject, Constants.Name.PREFIX);
            this.d = j.a(jSONObject, "isCheck");
        }

        public final String a() {
            return this.b;
        }

        public final String b() {
            return this.c;
        }

        public final boolean c() {
            return this.d == null || !"false".equalsIgnoreCase(this.d);
        }
    }

    public af(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str, (byte) 0);
        this.b.a(this.c);
        this.b.a((InputFilter) new InputFilter.LengthFilter(23));
        this.b.a(2);
        if (this.i) {
            this.b.setEnabled(false);
        }
        JSONArray d = j.d(jSONObject, "regex");
        if (d != null) {
            if (this.o == null) {
                this.o = new ArrayList<>();
            }
            for (int i2 = 0; i2 < d.length(); i2++) {
                JSONObject jSONObject2 = (JSONObject) j.b(d, i2);
                if (jSONObject2 != null) {
                    this.o.add(new a(jSONObject2));
                }
            }
        }
    }

    private static boolean b(String str) {
        char c2;
        int length = str.length();
        int i = length - 2;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            c2 = '0';
            if (i < 0) {
                break;
            }
            int charAt = str.charAt(i) - '0';
            if (i2 % 2 == 0) {
                int i4 = charAt * 2;
                charAt = (i4 % 10) + (i4 / 10);
            }
            i3 += charAt;
            i--;
            i2++;
        }
        int i5 = i3 % 10;
        if (i5 != 0) {
            c2 = (char) ((10 - i5) + 48);
        }
        return c2 == str.charAt(length - 1);
    }

    public final String a() {
        return (this.i ? i() : this.b.b()).replace(" ", "");
    }

    public final boolean b() {
        if (this.i) {
            return true;
        }
        String a2 = a();
        if (this.o != null && this.o.size() > 0) {
            int i = 0;
            boolean z = false;
            while (i < this.o.size()) {
                a aVar = this.o.get(i);
                if (aVar.a() != null) {
                    z = a2.matches(aVar.a());
                }
                if (!z) {
                    i++;
                } else if (!aVar.c()) {
                    return 13 <= a2.length() && 19 >= a2.length();
                } else {
                    return b(aVar.b() + a2);
                }
            }
        }
        return 13 <= a2.length() && 19 >= a2.length() && b(a2);
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_input_cardNO";
    }
}
