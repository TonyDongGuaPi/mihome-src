package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.IFrameStreamInfo;
import com.iheartradio.m3u8.data.IStreamInfo;
import com.iheartradio.m3u8.data.MasterPlaylist;
import com.iheartradio.m3u8.data.MediaData;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.PlaylistData;
import com.iheartradio.m3u8.data.StreamInfo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class MasterPlaylistTagWriter extends ExtTagWriter {
    static final IExtTagWriter d = new MasterPlaylistTagWriter() {
        private final Map<String, AttributeWriter<MediaData>> g = new HashMap();

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.p;
        }

        {
            this.g.put("TYPE", new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return mediaData.a().getValue();
                }
            });
            this.g.put(Constants.o, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return mediaData.b();
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.c(), AnonymousClass1.this.b());
                }
            });
            this.g.put(Constants.r, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.d(), AnonymousClass1.this.b());
                }
            });
            this.g.put(Constants.s, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return mediaData.e();
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.f(), AnonymousClass1.this.b());
                }
            });
            this.g.put(Constants.t, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return mediaData.g();
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.h(), AnonymousClass1.this.b());
                }
            });
            this.g.put(Constants.u, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.i(), AnonymousClass1.this.b());
                }
            });
            this.g.put("DEFAULT", new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.j());
                }
            });
            this.g.put(Constants.w, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.k());
                }
            });
            this.g.put(Constants.x, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return true;
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.l());
                }
            });
            this.g.put(Constants.y, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return mediaData.m();
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(mediaData.n(), AnonymousClass1.this.b());
                }
            });
            this.g.put(Constants.z, new AttributeWriter<MediaData>() {
                /* renamed from: a */
                public boolean b(MediaData mediaData) {
                    return mediaData.o();
                }

                /* renamed from: b */
                public String a(MediaData mediaData) throws ParseException {
                    return WriteUtil.a(WriteUtil.a((List<? extends Object>) mediaData.p(), Constants.e), AnonymousClass1.this.b());
                }
            });
        }

        public void a(TagWriter tagWriter, Playlist playlist, MasterPlaylist masterPlaylist) throws IOException, ParseException {
            if (masterPlaylist.c().size() > 0) {
                for (MediaData a2 : masterPlaylist.c()) {
                    a(tagWriter, a2, this.g);
                }
            }
        }
    };
    static final IExtTagWriter e = new EXT_STREAM_INF<IFrameStreamInfo>() {
        public String b() {
            return Constants.B;
        }

        {
            this.g.put(Constants.o, new AttributeWriter<IFrameStreamInfo>() {
                /* renamed from: a */
                public boolean b(IFrameStreamInfo iFrameStreamInfo) {
                    return true;
                }

                /* renamed from: b */
                public String a(IFrameStreamInfo iFrameStreamInfo) throws ParseException {
                    return WriteUtil.a(iFrameStreamInfo.l(), AnonymousClass2.this.b());
                }
            });
        }

        public void a(TagWriter tagWriter, Playlist playlist, MasterPlaylist masterPlaylist) throws IOException, ParseException {
            for (IFrameStreamInfo a2 : masterPlaylist.b()) {
                a(tagWriter, a2, this.g);
            }
        }
    };
    static final IExtTagWriter f = new EXT_STREAM_INF<StreamInfo>() {
        public String b() {
            return Constants.A;
        }

        {
            this.g.put(Constants.J, new AttributeWriter<StreamInfo>() {
                /* renamed from: a */
                public boolean b(StreamInfo streamInfo) {
                    return streamInfo.l();
                }

                /* renamed from: b */
                public String a(StreamInfo streamInfo) throws ParseException {
                    return WriteUtil.a(streamInfo.m(), AnonymousClass3.this.b());
                }
            });
            this.g.put(Constants.K, new AttributeWriter<StreamInfo>() {
                /* renamed from: a */
                public boolean b(StreamInfo streamInfo) {
                    return streamInfo.n();
                }

                /* renamed from: b */
                public String a(StreamInfo streamInfo) throws ParseException {
                    return WriteUtil.a(streamInfo.o(), AnonymousClass3.this.b());
                }
            });
            this.g.put(Constants.L, new AttributeWriter<StreamInfo>() {
                /* renamed from: a */
                public boolean b(StreamInfo streamInfo) {
                    return streamInfo.p();
                }

                /* renamed from: b */
                public String a(StreamInfo streamInfo) throws ParseException {
                    return WriteUtil.a(streamInfo.q(), AnonymousClass3.this.b());
                }
            });
        }

        public void a(TagWriter tagWriter, Playlist playlist, MasterPlaylist masterPlaylist) throws IOException, ParseException {
            for (PlaylistData next : masterPlaylist.a()) {
                if (next.b()) {
                    a(tagWriter, next.c(), this.g);
                    tagWriter.b(next.a());
                }
            }
        }
    };

    MasterPlaylistTagWriter() {
    }

    public final void a(TagWriter tagWriter, Playlist playlist) throws IOException, ParseException {
        if (playlist.a()) {
            a(tagWriter, playlist, playlist.c());
        }
    }

    public void a(TagWriter tagWriter, Playlist playlist, MasterPlaylist masterPlaylist) throws IOException, ParseException {
        tagWriter.c(b());
    }

    static abstract class EXT_STREAM_INF<T extends IStreamInfo> extends MasterPlaylistTagWriter {
        final Map<String, AttributeWriter<T>> g = new HashMap();

        public abstract void a(TagWriter tagWriter, Playlist playlist, MasterPlaylist masterPlaylist) throws IOException, ParseException;

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        EXT_STREAM_INF() {
            this.g.put(Constants.C, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return true;
                }

                /* renamed from: b */
                public String a(T t) {
                    return Integer.toString(t.a());
                }
            });
            this.g.put(Constants.D, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return t.b();
                }

                /* renamed from: b */
                public String a(T t) {
                    return Integer.toString(t.c());
                }
            });
            this.g.put(Constants.E, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return t.d();
                }

                /* renamed from: b */
                public String a(T t) throws ParseException {
                    return WriteUtil.a(WriteUtil.a((List<? extends Object>) t.e(), Constants.e), EXT_STREAM_INF.this.b());
                }
            });
            this.g.put(Constants.F, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return t.f();
                }

                /* renamed from: b */
                public String a(T t) throws ParseException {
                    return WriteUtil.a(t.g());
                }
            });
            this.g.put(Constants.G, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return t.h();
                }

                /* renamed from: b */
                public String a(T t) throws ParseException {
                    return String.valueOf(t.i());
                }
            });
            this.g.put("VIDEO", new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return t.j();
                }

                /* renamed from: b */
                public String a(T t) throws ParseException {
                    return WriteUtil.a(t.k(), EXT_STREAM_INF.this.b());
                }
            });
            this.g.put(Constants.I, new AttributeWriter<T>() {
                /* renamed from: a */
                public boolean b(T t) {
                    return false;
                }

                /* renamed from: b */
                public String a(T t) {
                    return "";
                }
            });
        }
    }
}
