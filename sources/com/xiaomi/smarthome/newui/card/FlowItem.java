package com.xiaomi.smarthome.newui.card;

public class FlowItem {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20502a = "FlowItem";
    private int b;
    private FlowItemMode c;
    private int d;
    private int e;

    public FlowItem(int i, FlowItemMode flowItemMode, int i2, int i3) {
        this.b = i;
        this.c = flowItemMode;
        this.d = i2;
        this.e = i3;
    }

    public int a() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public FlowItemMode b() {
        return this.c;
    }

    public void a(FlowItemMode flowItemMode) {
        this.c = flowItemMode;
    }

    public int c() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int d() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public static FlowItem a(String str) {
        String[] split = str.split(",");
        if (split.length != 4) {
            return null;
        }
        return new FlowItem(Integer.valueOf(split[0]).intValue(), FlowItemMode.values()[Integer.valueOf(split[1]).intValue()], Integer.valueOf(split[2]).intValue(), Integer.valueOf(split[3]).intValue());
    }

    public String e() {
        return a() + "," + b().ordinal() + "," + c() + "," + d();
    }
}
