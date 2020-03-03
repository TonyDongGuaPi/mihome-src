package com.xiaomi.jr.mipay.safekeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.util.ArrayList;

public class SafeKeyboard {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10977a = "none";
    public static final String b = "number";
    public static final String c = "mipayPassword";
    static final String d = "SafeKeyboard";
    private static final String i = "SafeKeyboard";
    private static final String j = "Row";
    private static final String k = "Key";
    public int e;
    public int f;
    public boolean g;
    public String h;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public int o;
    /* access modifiers changed from: private */
    public int p;
    /* access modifiers changed from: private */
    public int q;
    /* access modifiers changed from: private */
    public int r;
    private int s;
    private int t;
    private String u;
    private int v;
    private ArrayList<Row> w = new ArrayList<>();

    public static class Row {

        /* renamed from: a  reason: collision with root package name */
        public int f10979a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public boolean h;
        ArrayList<Key> i = new ArrayList<>();
        /* access modifiers changed from: private */
        public SafeKeyboard j;

        public Row(SafeKeyboard safeKeyboard) {
            this.j = safeKeyboard;
        }

        public Row(Resources resources, SafeKeyboard safeKeyboard, XmlResourceParser xmlResourceParser) {
            this.j = safeKeyboard;
            TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Jr_Mipay_SafeKeyboard);
            this.f10979a = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyWidth, safeKeyboard.n, safeKeyboard.l);
            this.b = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyHeight, safeKeyboard.o, safeKeyboard.m);
            this.d = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_horizontalGap, safeKeyboard.n, safeKeyboard.p);
            this.e = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_verticalGap, safeKeyboard.o, safeKeyboard.q);
            int resourceId = obtainAttributes.getResourceId(R.styleable.Jr_Mipay_SafeKeyboard_keyBackground, -1);
            this.c = resourceId == -1 ? safeKeyboard.r : resourceId;
            this.f = obtainAttributes.getDimensionPixelOffset(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelSize, safeKeyboard.e);
            this.g = obtainAttributes.getColor(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelColor, safeKeyboard.f);
            this.h = obtainAttributes.getBoolean(R.styleable.Jr_Mipay_SafeKeyboard_keyEnabled, safeKeyboard.g);
            obtainAttributes.recycle();
        }

        public ArrayList<Key> a() {
            return this.i;
        }
    }

    public static class Key {

        /* renamed from: a  reason: collision with root package name */
        public int f10978a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public String f;
        public Drawable g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        private SafeKeyboard m;

        public Key(Row row) {
            this.m = row.j;
            this.i = row.b;
            this.j = row.d;
            this.h = row.f10979a;
        }

        public Key(Resources resources, Row row, int i2, int i3, XmlResourceParser xmlResourceParser) {
            this(row);
            this.k = i2;
            this.l = i3;
            TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Jr_Mipay_SafeKeyboard);
            this.h = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyWidth, this.m.n, row.f10979a);
            this.i = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyHeight, this.m.o, row.b);
            this.j = SafeKeyboard.a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_horizontalGap, this.m.n, row.d);
            int resourceId = obtainAttributes.getResourceId(R.styleable.Jr_Mipay_SafeKeyboard_keyBackground, -1);
            this.b = resourceId == -1 ? row.c : resourceId;
            obtainAttributes.recycle();
            this.k += this.j;
            TypedArray obtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Jr_Mipay_SafeKeyboard_Key);
            TypedValue typedValue = new TypedValue();
            obtainAttributes2.getValue(R.styleable.Jr_Mipay_SafeKeyboard_Key_code, typedValue);
            if (typedValue.type == 16 || typedValue.type == 17) {
                this.f10978a = typedValue.data;
            }
            this.g = obtainAttributes2.getDrawable(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyIcon);
            if (this.g != null) {
                this.g.setBounds(0, 0, this.g.getIntrinsicWidth(), this.g.getIntrinsicHeight());
            }
            this.f = obtainAttributes2.getString(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyLabel);
            this.c = obtainAttributes2.getDimensionPixelOffset(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyLabelSize, row.f);
            this.d = obtainAttributes2.getColor(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyLabelColor, row.g);
            this.e = obtainAttributes2.getBoolean(R.styleable.Jr_Mipay_SafeKeyboard_Key_keyEnabled, row.h);
            obtainAttributes2.recycle();
        }
    }

    public SafeKeyboard(Context context, String str) {
        XmlResourceParser xmlResourceParser;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.n = displayMetrics.widthPixels;
        this.o = displayMetrics.heightPixels;
        this.t = 0;
        this.s = 0;
        this.l = this.n / 10;
        this.m = this.l;
        this.q = 0;
        this.r = -1;
        this.u = "";
        this.v = 0;
        this.h = str;
        if ("number".equalsIgnoreCase(str)) {
            xmlResourceParser = context.getResources().getXml(R.xml.jr_mipay_safe_keyboard_input_number);
        } else {
            xmlResourceParser = c.equalsIgnoreCase(str) ? context.getResources().getXml(R.xml.jr_mipay_safe_keyboard_input_mipay_password) : null;
        }
        if (xmlResourceParser != null) {
            a(context, xmlResourceParser);
        }
    }

    private void a(Context context, XmlResourceParser xmlResourceParser) {
        Resources resources = context.getResources();
        Key key = null;
        Row row = null;
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (true) {
            boolean z2 = false;
            while (true) {
                try {
                    int next = xmlResourceParser.next();
                    if (next == 1) {
                        break loop0;
                    } else if (next == 2) {
                        String name = xmlResourceParser.getName();
                        if (j.equals(name)) {
                            row = a(resources, xmlResourceParser);
                            this.w.add(row);
                            i3 = 0;
                            z2 = true;
                        } else if ("Key".equals(name)) {
                            Key a2 = a(resources, row, i3, i2, xmlResourceParser);
                            row.i.add(a2);
                            key = a2;
                            z = true;
                        } else if ("SafeKeyboard".equals(name)) {
                            b(resources, xmlResourceParser);
                            i2 += this.v;
                        }
                    } else if (next != 3) {
                        continue;
                    } else if (z) {
                        i3 += key.j + key.h;
                        if (i3 > this.t) {
                            this.t = i3;
                        }
                        z = false;
                    } else if (z2) {
                        break;
                    }
                } catch (Exception e2) {
                    Log.e("SafeKeyboard", "Parse error:" + e2);
                    e2.printStackTrace();
                }
            }
            i2 = i2 + row.e + row.b;
        }
        this.s = i2 - this.q;
    }

    /* access modifiers changed from: protected */
    public Row a(Resources resources, XmlResourceParser xmlResourceParser) {
        return new Row(resources, this, xmlResourceParser);
    }

    /* access modifiers changed from: protected */
    public Key a(Resources resources, Row row, int i2, int i3, XmlResourceParser xmlResourceParser) {
        return new Key(resources, row, i2, i3, xmlResourceParser);
    }

    public ArrayList<Row> a() {
        return this.w;
    }

    public int b() {
        return this.s;
    }

    public int c() {
        return this.t;
    }

    public String d() {
        return this.u;
    }

    public int e() {
        return this.v;
    }

    public String f() {
        return this.h;
    }

    private void b(Resources resources, XmlResourceParser xmlResourceParser) {
        TypedArray obtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), R.styleable.Jr_Mipay_SafeKeyboard);
        this.l = a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyWidth, this.n, this.n / 10);
        this.m = a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyHeight, this.o, 100);
        this.p = a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_horizontalGap, this.n, 0);
        this.q = a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_verticalGap, this.o, 0);
        this.r = obtainAttributes.getResourceId(R.styleable.Jr_Mipay_SafeKeyboard_keyBackground, -1);
        this.u = obtainAttributes.getString(R.styleable.Jr_Mipay_SafeKeyboard_keyboardBarTitle);
        this.v = a(obtainAttributes, R.styleable.Jr_Mipay_SafeKeyboard_keyboardBarHeight, this.o, 0);
        this.e = obtainAttributes.getDimensionPixelSize(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelSize, 60);
        this.f = obtainAttributes.getColor(R.styleable.Jr_Mipay_SafeKeyboard_keyLabelColor, -16777216);
        this.g = obtainAttributes.getBoolean(R.styleable.Jr_Mipay_SafeKeyboard_keyEnabled, true);
        obtainAttributes.recycle();
    }

    static int a(TypedArray typedArray, int i2, int i3, int i4) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue == null) {
            return i4;
        }
        if (peekValue.type == 5) {
            return typedArray.getDimensionPixelOffset(i2, i4);
        }
        return peekValue.type == 6 ? Math.round(typedArray.getFraction(i2, i3, i3, (float) i4)) : i4;
    }
}
