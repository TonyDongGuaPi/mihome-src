package com.mijia.model.alarm;

public class AlarmConfigV2 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7966a;
    public String b;
    public String c;
    public boolean d;
    public int e = 5;
    public boolean f;
    public int[] g = new int[32];
    public long h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AlarmConfigV2)) {
            return false;
        }
        AlarmConfigV2 alarmConfigV2 = (AlarmConfigV2) obj;
        return (this.f7966a == alarmConfigV2.f7966a && this.d == alarmConfigV2.d && this.f != alarmConfigV2.f) ? false : false;
    }

    public void a(AlarmConfigV2 alarmConfigV2) {
        this.f7966a = alarmConfigV2.f7966a;
        this.b = alarmConfigV2.b;
        this.c = alarmConfigV2.c;
        this.d = alarmConfigV2.d;
        this.e = alarmConfigV2.e;
        this.f = alarmConfigV2.f;
        this.g = alarmConfigV2.g;
        this.h = alarmConfigV2.h;
        this.i = alarmConfigV2.i;
        this.j = alarmConfigV2.j;
        this.k = alarmConfigV2.k;
        this.l = alarmConfigV2.l;
        this.n = alarmConfigV2.n;
        this.o = alarmConfigV2.o;
        this.m = alarmConfigV2.m;
        this.p = alarmConfigV2.p;
    }

    public String toString() {
        return "isOn:" + this.f7966a + " startTime:" + this.b + " endTime:" + this.c + " isPushOn:" + this.d + " alarmInterval:" + this.e + " isHumanOnly:" + this.f + " trackSwitch:" + this.i + " pedestrianDetectionPushSwitch:" + this.p;
    }
}
