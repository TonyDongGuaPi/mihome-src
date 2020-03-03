package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.IFrameStreamInfo;
import com.iheartradio.m3u8.data.MediaData;
import com.iheartradio.m3u8.data.MediaType;
import com.iheartradio.m3u8.data.StreamInfo;
import com.iheartradio.m3u8.data.StreamInfoBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MasterPlaylistLineParser implements LineParser {

    /* renamed from: a  reason: collision with root package name */
    static final IExtTagParser f5945a = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f5946a = new MasterPlaylistLineParser(this);
        private final Map<String, AttributeParser<MediaData.Builder>> b = new HashMap();

        public String a() {
            return Constants.p;
        }

        public boolean b() {
            return true;
        }

        {
            this.b.put("TYPE", new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    MediaType fromValue = MediaType.fromValue(attribute.b);
                    if (fromValue != null) {
                        builder.a(fromValue);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.INVALID_MEDIA_TYPE, AnonymousClass1.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.o, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    builder.a(ParseUtil.a(ParseUtil.f(attribute.b, AnonymousClass1.this.a()), parseState.b));
                }
            });
            this.b.put(Constants.r, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    String f = ParseUtil.f(attribute.b, AnonymousClass1.this.a());
                    if (!f.isEmpty()) {
                        builder.b(f);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.EMPTY_MEDIA_GROUP_ID, AnonymousClass1.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.s, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    builder.c(ParseUtil.f(attribute.b, AnonymousClass1.this.a()));
                }
            });
            this.b.put(Constants.t, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    builder.d(ParseUtil.f(attribute.b, AnonymousClass1.this.a()));
                }
            });
            this.b.put(Constants.u, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    String f = ParseUtil.f(attribute.b, AnonymousClass1.this.a());
                    if (!f.isEmpty()) {
                        builder.e(f);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.EMPTY_MEDIA_NAME, AnonymousClass1.this.a(), attribute.toString());
                }
            });
            this.b.put("DEFAULT", new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    boolean a2 = ParseUtil.a(attribute, AnonymousClass1.this.a());
                    builder.a(a2);
                    parseState.c().f = a2;
                    if (!a2) {
                        return;
                    }
                    if (!parseState.c().g) {
                        builder.b(true);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.AUTO_SELECT_DISABLED_FOR_DEFAULT, AnonymousClass1.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.w, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    boolean a2 = ParseUtil.a(attribute, AnonymousClass1.this.a());
                    builder.b(a2);
                    parseState.c().g = !a2;
                    if (parseState.c().f && !a2) {
                        throw ParseException.create(ParseExceptionType.AUTO_SELECT_DISABLED_FOR_DEFAULT, AnonymousClass1.this.a(), attribute.toString());
                    }
                }
            });
            this.b.put(Constants.x, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    builder.c(ParseUtil.a(attribute, AnonymousClass1.this.a()));
                }
            });
            this.b.put(Constants.y, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    String f = ParseUtil.f(attribute.b, AnonymousClass1.this.a());
                    if (Constants.am.matcher(f).matches()) {
                        builder.f(f);
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.INVALID_MEDIA_IN_STREAM_ID, AnonymousClass1.this.a(), attribute.toString());
                }
            });
            this.b.put(Constants.z, new AttributeParser<MediaData.Builder>() {
                public void a(Attribute attribute, MediaData.Builder builder, ParseState parseState) throws ParseException {
                    String[] split = ParseUtil.f(attribute.b, AnonymousClass1.this.a()).split(Constants.e);
                    if (split.length != 0) {
                        builder.a((List<String>) Arrays.asList(split));
                        return;
                    }
                    throw ParseException.create(ParseExceptionType.EMPTY_MEDIA_CHARACTERISTICS, AnonymousClass1.this.a(), attribute.toString());
                }
            });
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5946a.a(str, parseState);
            MediaData.Builder builder = new MediaData.Builder();
            parseState.c().b();
            ParseUtil.a(str, builder, parseState, this.b, a());
            parseState.c().c.add(builder.a());
        }
    };
    static final IExtTagParser b = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f5958a = new MasterPlaylistLineParser(this);
        private final Map<String, AttributeParser<IFrameStreamInfo.Builder>> b = MasterPlaylistLineParser.a(a());

        public String a() {
            return Constants.B;
        }

        public boolean b() {
            return true;
        }

        {
            this.b.put(Constants.o, new AttributeParser<IFrameStreamInfo.Builder>() {
                public void a(Attribute attribute, IFrameStreamInfo.Builder builder, ParseState parseState) throws ParseException {
                    builder.b(ParseUtil.f(attribute.b, AnonymousClass2.this.a()));
                }
            });
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5958a.a(str, parseState);
            IFrameStreamInfo.Builder builder = new IFrameStreamInfo.Builder();
            ParseUtil.a(str, builder, parseState, this.b, a());
            parseState.c().b.add(builder.a());
        }
    };
    static final IExtTagParser c = new IExtTagParser() {

        /* renamed from: a  reason: collision with root package name */
        private final LineParser f5960a = new MasterPlaylistLineParser(this);
        private final Map<String, AttributeParser<StreamInfo.Builder>> b = MasterPlaylistLineParser.a(a());

        public String a() {
            return Constants.A;
        }

        public boolean b() {
            return true;
        }

        {
            this.b.put(Constants.J, new AttributeParser<StreamInfo.Builder>() {
                public void a(Attribute attribute, StreamInfo.Builder builder, ParseState parseState) throws ParseException {
                    builder.a(ParseUtil.f(attribute.b, AnonymousClass3.this.a()));
                }
            });
            this.b.put(Constants.K, new AttributeParser<StreamInfo.Builder>() {
                public void a(Attribute attribute, StreamInfo.Builder builder, ParseState parseState) throws ParseException {
                    builder.d(ParseUtil.f(attribute.b, AnonymousClass3.this.a()));
                }
            });
            this.b.put(Constants.L, new AttributeParser<StreamInfo.Builder>() {
                public void a(Attribute attribute, StreamInfo.Builder builder, ParseState parseState) throws ParseException {
                    if (!attribute.b.equals(Constants.ax)) {
                        builder.e(ParseUtil.f(attribute.b, AnonymousClass3.this.a()));
                    }
                }
            });
        }

        public void a(String str, ParseState parseState) throws ParseException {
            this.f5960a.a(str, parseState);
            StreamInfo.Builder builder = new StreamInfo.Builder();
            ParseUtil.a(str, builder, parseState, this.b, a());
            parseState.c().e = builder.a();
        }
    };
    private final IExtTagParser d;
    private final LineParser e;

    MasterPlaylistLineParser(IExtTagParser iExtTagParser) {
        this(iExtTagParser, new ExtLineParser(iExtTagParser));
    }

    MasterPlaylistLineParser(IExtTagParser iExtTagParser, LineParser lineParser) {
        this.d = iExtTagParser;
        this.e = lineParser;
    }

    public void a(String str, ParseState parseState) throws ParseException {
        if (!parseState.e()) {
            parseState.d();
            this.e.a(str, parseState);
            return;
        }
        throw ParseException.create(ParseExceptionType.MASTER_IN_MEDIA, this.d.a());
    }

    static <T extends StreamInfoBuilder> Map<String, AttributeParser<T>> a(final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.C, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                t.d(ParseUtil.a(attribute.b, str));
            }
        });
        hashMap.put(Constants.D, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                t.c(ParseUtil.a(attribute.b, str));
            }
        });
        hashMap.put(Constants.E, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                String[] split = ParseUtil.f(attribute.b, str).split(Constants.e);
                if (split.length > 0) {
                    t.b((List<String>) Arrays.asList(split));
                }
            }
        });
        hashMap.put(Constants.F, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                t.b(ParseUtil.e(attribute.b, str));
            }
        });
        hashMap.put(Constants.G, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                t.b(ParseUtil.c(attribute.b, str));
            }
        });
        hashMap.put("VIDEO", new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
                t.c(ParseUtil.f(attribute.b, str));
            }
        });
        hashMap.put(Constants.I, new AttributeParser<T>() {
            public void a(Attribute attribute, T t, ParseState parseState) throws ParseException {
            }
        });
        return hashMap;
    }
}
