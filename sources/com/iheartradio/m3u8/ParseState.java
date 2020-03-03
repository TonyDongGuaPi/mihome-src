package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;

class ParseState implements IParseState<Playlist> {

    /* renamed from: a  reason: collision with root package name */
    static final int f6026a = -1;
    public final Encoding b;
    private MasterParseState c;
    private MediaParseState d;
    private boolean e;
    private boolean f;
    private int g = -1;

    public ParseState(Encoding encoding) {
        this.b = encoding;
    }

    public boolean b() {
        return this.c != null;
    }

    public MasterParseState c() {
        return this.c;
    }

    public void d() throws ParseException {
        if (e()) {
            throw new ParseException(ParseExceptionType.MASTER_IN_MEDIA);
        } else if (this.c == null) {
            this.c = new MasterParseState();
        }
    }

    public boolean e() {
        return this.d != null;
    }

    public MediaParseState f() {
        return this.d;
    }

    public void g() throws ParseException {
        if (this.d == null) {
            this.d = new MediaParseState();
        }
    }

    public boolean h() {
        return this.e;
    }

    public void i() {
        this.e = true;
    }

    public boolean j() {
        return this.f;
    }

    public void k() throws ParseException {
        if (!b()) {
            f().e = true;
            return;
        }
        throw new ParseException(ParseExceptionType.MEDIA_IN_MASTER);
    }

    public int l() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    /* renamed from: m */
    public Playlist a() throws ParseException {
        Playlist.Builder builder = new Playlist.Builder();
        if (b()) {
            builder.a(this.c.a());
        } else if (e()) {
            builder.a(this.d.a()).a(this.e);
        } else {
            throw new ParseException(ParseExceptionType.UNKNOWN_PLAYLIST_TYPE);
        }
        return builder.a(this.g == -1 ? 1 : this.g).a();
    }
}
