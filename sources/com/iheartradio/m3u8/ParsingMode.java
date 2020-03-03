package com.iheartradio.m3u8;

public class ParsingMode {

    /* renamed from: a  reason: collision with root package name */
    public static final ParsingMode f6027a = new Builder().c();
    public static final ParsingMode b = new Builder().a().b().c();
    public final boolean c;
    public final boolean d;

    private ParsingMode(boolean z, boolean z2) {
        this.c = z;
        this.d = z2;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private boolean f6028a = false;
        private boolean b = false;

        public Builder a() {
            this.f6028a = true;
            return this;
        }

        public Builder b() {
            this.b = true;
            return this;
        }

        public ParsingMode c() {
            return new ParsingMode(this.f6028a, this.b);
        }
    }
}
