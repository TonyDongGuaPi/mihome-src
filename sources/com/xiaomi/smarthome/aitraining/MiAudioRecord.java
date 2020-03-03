package com.xiaomi.smarthome.aitraining;

import com.taobao.weex.el.parse.Operators;

public class MiAudioRecord {

    /* renamed from: a  reason: collision with root package name */
    private String f13685a;
    private String b;
    private long c;
    private boolean d;
    private boolean e;

    public boolean a() {
        return this.e;
    }

    public String b() {
        return this.f13685a;
    }

    public void a(String str) {
        this.f13685a = str;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public long d() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public boolean e() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public String toString() {
        return "Record{id='" + this.f13685a + Operators.SINGLE_QUOTE + ", path='" + this.b + Operators.SINGLE_QUOTE + ", second=" + this.c + ", isPlayed=" + this.d + ", isPlaying=" + this.e + Operators.BLOCK_END;
    }
}
