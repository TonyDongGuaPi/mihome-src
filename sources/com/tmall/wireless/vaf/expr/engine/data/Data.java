package com.tmall.wireless.vaf.expr.engine.data;

import android.util.Log;

public class Data {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f9351a = 0;
    public static final byte b = 1;
    public static final byte c = 2;
    public static final byte d = 3;
    public static final byte e = 4;
    private static final String h = "Data_TMTEST";
    private static ValueCache i = ValueCache.a();
    public Value f;
    public int g;

    public Data() {
        a();
    }

    public void a() {
        this.g = 0;
    }

    private void a(int i2, Value value) {
        if (value != null) {
            switch (i2) {
                case 1:
                    i.a((IntValue) value);
                    return;
                case 2:
                    i.a((FloatValue) value);
                    return;
                case 3:
                    i.a((StrValue) value);
                    return;
                case 4:
                    i.a((ObjValue) value);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean a(Object obj) {
        if (obj instanceof Integer) {
            a(((Integer) obj).intValue());
            return true;
        } else if (obj instanceof Float) {
            a(((Float) obj).floatValue());
            return true;
        } else if (obj instanceof String) {
            a((String) obj);
            return true;
        } else {
            b(obj);
            return true;
        }
    }

    public int b() {
        if (1 == this.g) {
            return ((IntValue) this.f).f9353a;
        }
        return 0;
    }

    public float c() {
        if (2 == this.g) {
            return ((FloatValue) this.f).f9352a;
        }
        return 0.0f;
    }

    public String d() {
        if (3 == this.g) {
            return ((StrValue) this.f).f9355a;
        }
        return null;
    }

    public Object e() {
        if (4 == this.g) {
            return ((ObjValue) this.f).f9354a;
        }
        return null;
    }

    public void a(int i2) {
        if (1 != this.g) {
            a(this.g, this.f);
            this.g = 1;
            this.f = i.a(i2);
            return;
        }
        ((IntValue) this.f).f9353a = i2;
    }

    public void a(float f2) {
        if (2 != this.g) {
            a(this.g, this.f);
            this.g = 2;
            this.f = i.a(f2);
            return;
        }
        ((FloatValue) this.f).f9352a = f2;
    }

    public void a(String str) {
        if (3 != this.g) {
            a(this.g, this.f);
            this.g = 3;
            this.f = i.a(str);
            return;
        }
        ((StrValue) this.f).f9355a = str;
    }

    public void b(Object obj) {
        if (4 != this.g) {
            a(this.g, this.f);
            this.g = 4;
            this.f = i.a(obj);
            return;
        }
        ((ObjValue) this.f).f9354a = obj;
    }

    public void a(Data data) {
        if (data == null) {
            Log.e(h, "copy failed");
        } else if (data.g == this.g) {
            this.f.a(data.f);
        } else {
            this.g = data.g;
            this.f = data.f.clone();
        }
    }

    public String toString() {
        switch (this.g) {
            case 1:
                return String.format("type:int value:" + this.f, new Object[0]);
            case 2:
                return String.format("type:float value:" + this.f, new Object[0]);
            case 3:
                return String.format("type:string value:" + this.f, new Object[0]);
            case 4:
                return String.format("type:object value:" + this.f, new Object[0]);
            default:
                return "type:none";
        }
    }
}
