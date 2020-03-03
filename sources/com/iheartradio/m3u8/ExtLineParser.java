package com.iheartradio.m3u8;

import java.util.regex.Matcher;

class ExtLineParser implements LineParser {

    /* renamed from: a  reason: collision with root package name */
    static final IExtTagParser f5940a = new IExtTagParser() {
        public String a() {
            return Constants.m;
        }

        public boolean b() {
            return false;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            if (!parseState.h()) {
                parseState.i();
                return;
            }
            throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
        }
    };
    static final IExtTagParser b = new IExtTagParser() {
        public String a() {
            return null;
        }

        public boolean b() {
            return false;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            if (parseState.b()) {
                parseState.c().d.add(str);
            } else if (parseState.e()) {
                parseState.f().b.add(str);
            }
        }
    };
    static final IExtTagParser c = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final ExtLineParser f5941a = new ExtLineParser(this);

        public String a() {
            return Constants.n;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5941a.a(str, parseState);
            Matcher a2 = ParseUtil.a(Constants.ah, str, a());
            if (parseState.l() == -1) {
                int a3 = ParseUtil.a(a2.group(1), a());
                if (a3 < 1) {
                    throw ParseException.create(ParseExceptionType.INVALID_COMPATIBILITY_VERSION, a(), str);
                } else if (a3 <= 5) {
                    parseState.a(a3);
                } else {
                    throw ParseException.create(ParseExceptionType.UNSUPPORTED_COMPATIBILITY_VERSION, a(), str);
                }
            } else {
                throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
            }
        }
    };
    private final IExtTagParser d;

    ExtLineParser(IExtTagParser iExtTagParser) {
        this.d = iExtTagParser;
    }

    public void a(String str, ParseState parseState) throws ParseException {
        if (this.d.b() && str.indexOf(":") != this.d.a().length() + 1) {
            throw ParseException.create(ParseExceptionType.MISSING_EXT_TAG_SEPARATOR, this.d.a(), str);
        }
    }
}
