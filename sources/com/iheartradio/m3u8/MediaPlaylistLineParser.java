package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.EncryptionMethod;
import com.iheartradio.m3u8.data.PlaylistType;
import com.iheartradio.m3u8.data.StartData;
import com.iheartradio.m3u8.data.TrackInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

class MediaPlaylistLineParser implements LineParser {

    /* renamed from: a  reason: collision with root package name */
    static final IExtTagParser f5993a = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f5994a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.U;
        }

        public boolean b() {
            return false;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5994a.a(str, parseState);
            ParseUtil.a(Constants.ao, str, a());
            parseState.f().k = true;
        }
    };
    static final IExtTagParser b = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6007a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.V;
        }

        public boolean b() {
            return false;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6007a.a(str, parseState);
            ParseUtil.a(Constants.ap, str, a());
            if (parseState.l() >= 4) {
                parseState.k();
                return;
            }
            throw ParseException.create(ParseExceptionType.REQUIRES_PROTOCOL_VERSION_4_OR_HIGHER, a());
        }
    };
    static final IExtTagParser c = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6008a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.M;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6008a.a(str, parseState);
            Matcher a2 = ParseUtil.a(Constants.ak, str, a());
            if (parseState.f().f == null) {
                parseState.f().f = (PlaylistType) ParseUtil.a(a2.group(1), PlaylistType.class, a());
                return;
            }
            throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
        }
    };
    static final IExtTagParser d = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6009a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.N;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6009a.a(str, parseState);
            ParseUtil.a(Constants.al, str, a());
            if (parseState.f().j == null) {
                parseState.f().j = ParseUtil.b(str, a());
                return;
            }
            throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
        }
    };
    static final IExtTagParser e = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6012a = new MediaPlaylistLineParser(this);
        private final Map<String, AttributeParser<StartData.Builder>> b = new HashMap();

        public String a() {
            return Constants.P;
        }

        public boolean b() {
            return true;
        }

        {
            this.b.put(Constants.Q, new AttributeParser<StartData.Builder>() {
                public void a(Attribute attribute, StartData.Builder builder, ParseState parseState) throws ParseException {
                    builder.a(ParseUtil.c(attribute.b, AnonymousClass5.this.a()));
                }
            });
            this.b.put(Constants.R, new AttributeParser<StartData.Builder>() {
                public void a(Attribute attribute, StartData.Builder builder, ParseState parseState) throws ParseException {
                    builder.a(ParseUtil.a(attribute, AnonymousClass5.this.a()));
                }
            });
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6012a.a(str, parseState);
            StartData.Builder builder = new StartData.Builder();
            ParseUtil.a(str, builder, parseState, this.b, a());
            parseState.f().i = builder.a();
        }
    };
    static final IExtTagParser f = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6015a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.O;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6015a.a(str, parseState);
            Matcher a2 = ParseUtil.a(Constants.ai, str, a());
            if (parseState.f().c == null) {
                parseState.f().c = Integer.valueOf(ParseUtil.a(a2.group(1), a()));
                return;
            }
            throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
        }
    };
    static final IExtTagParser g = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6016a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.S;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6016a.a(str, parseState);
            Matcher a2 = ParseUtil.a(Constants.aj, str, a());
            if (parseState.f().d == null) {
                parseState.f().d = Integer.valueOf(ParseUtil.a(a2.group(1), a()));
                return;
            }
            throw ParseException.create(ParseExceptionType.MULTIPLE_EXT_TAG_INSTANCES, a(), str);
        }
    };
    static final IExtTagParser h = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6017a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.T;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6017a.a(str, parseState);
        }
    };
    static final IExtTagParser i = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6018a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.X;
        }

        public boolean b() {
            return true;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6018a.a(str, parseState);
            Matcher a2 = ParseUtil.a(Constants.an, str, a());
            parseState.f().g = new TrackInfo(ParseUtil.c(a2.group(1), a()), a2.group(2));
        }
    };
    static final IExtTagParser j = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f5995a = new MediaPlaylistLineParser(this);

        public String a() {
            return Constants.W;
        }

        public boolean b() {
            return false;
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5995a.a(str, parseState);
            ParseUtil.a(Constants.aq, str, a());
            parseState.f().l = true;
        }
    };
    static final IExtTagParser k = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f6001a = new MediaPlaylistLineParser(this);
        private final Map<String, AttributeParser<EncryptionData.Builder>> b = new HashMap();

        public String a() {
            return Constants.Y;
        }

        public boolean b() {
            return true;
        }

        {
            this.b.put(Constants.Z, new AttributeParser<EncryptionData.Builder>() {
                public void a(Attribute attribute, EncryptionData.Builder builder, ParseState parseState) throws ParseException {
                    EncryptionMethod fromValue = EncryptionMethod.fromValue(attribute.b);
                    if (fromValue != null) {
                        builder.a(fromValue);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.INVALID_ENCRYPTION_METHOD, AnonymousClass11.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.o, new AttributeParser<EncryptionData.Builder>() {
                public void a(Attribute attribute, EncryptionData.Builder builder, ParseState parseState) throws ParseException {
                    builder.a(ParseUtil.a(ParseUtil.f(attribute.b, AnonymousClass11.this.a()), parseState.b));
                }
            });
            this.b.put(Constants.aa, new AttributeParser<EncryptionData.Builder>() {
                public void a(Attribute attribute, EncryptionData.Builder builder, ParseState parseState) throws ParseException {
                    List<Byte> d = ParseUtil.d(attribute.b, AnonymousClass11.this.a());
                    if (d.size() == 16 || d.size() == 32) {
                        builder.a(d);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.INVALID_IV_SIZE, AnonymousClass11.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.ab, new AttributeParser<EncryptionData.Builder>() {
                public void a(Attribute attribute, EncryptionData.Builder builder, ParseState parseState) throws ParseException {
                    builder.b(ParseUtil.f(attribute.b, AnonymousClass11.this.a()));
                }
            });
            this.b.put(Constants.ac, new AttributeParser<EncryptionData.Builder>() {
                public void a(Attribute attribute, EncryptionData.Builder builder, ParseState parseState) throws ParseException {
                    String[] split = ParseUtil.f(attribute.b, AnonymousClass11.this.a()).split("/");
                    ArrayList arrayList = new ArrayList();
                    int length = split.length;
                    int i = 0;
                    while (i < length) {
                        try {
                            arrayList.add(Integer.valueOf(Integer.parseInt(split[i])));
                            i++;
                        } catch (NumberFormatException unused) {
                            throw ParseException.create(ParseExceptionType.INVALID_KEY_FORMAT_VERSIONS, AnonymousClass11.this.a(), attribute.toString());
                        }
                    }
                    builder.b((List<Integer>) arrayList);
                }
            });
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f6001a.a(str, parseState);
            EncryptionData.Builder b2 = new EncryptionData.Builder().b("identity").b(Constants.ay);
            ParseUtil.a(str, b2, parseState, this.b, a());
            EncryptionData a2 = b2.a();
            if (a2.a() == EncryptionMethod.NONE || a2.c() != null) {
                parseState.f().h = a2;
                return;
            }
            throw ParseException.create(ParseExceptionType.MISSING_ENCRYPTION_URI, a(), str);
        }
    };
    private final IExtTagParser l;
    private final LineParser m;

    MediaPlaylistLineParser(IExtTagParser iExtTagParser) {
        this(iExtTagParser, new ExtLineParser(iExtTagParser));
    }

    MediaPlaylistLineParser(IExtTagParser iExtTagParser, LineParser lineParser) {
        this.l = iExtTagParser;
        this.m = lineParser;
    }

    public void a(String str, ParseState parseState) throws ParseException {
        if (!parseState.b()) {
            parseState.g();
            this.m.a(str, parseState);
            return;
        }
        throw ParseException.create(ParseExceptionType.MEDIA_IN_MASTER, this.l.a());
    }
}
