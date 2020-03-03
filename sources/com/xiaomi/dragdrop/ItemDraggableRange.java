package com.xiaomi.dragdrop;

import com.taobao.weex.el.parse.Operators;

public class ItemDraggableRange {

    /* renamed from: a  reason: collision with root package name */
    private final int f10102a;
    private final int b;

    /* access modifiers changed from: protected */
    public String c() {
        return "ItemDraggableRange";
    }

    public ItemDraggableRange(int i, int i2) {
        if (i <= i2) {
            this.f10102a = i;
            this.b = i2;
            return;
        }
        throw new IllegalArgumentException("end position (= " + i2 + ") is smaller than start position (=" + i + Operators.BRACKET_END_STR);
    }

    public int a() {
        return this.f10102a;
    }

    public int b() {
        return this.b;
    }

    public boolean a(int i) {
        return i >= this.f10102a && i <= this.b;
    }

    public String toString() {
        return c() + "{mStart=" + this.f10102a + ", mEnd=" + this.b + Operators.BLOCK_END;
    }
}
