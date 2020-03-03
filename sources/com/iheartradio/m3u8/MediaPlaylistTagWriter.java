package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.EncryptionData;
import com.iheartradio.m3u8.data.MediaPlaylist;
import com.iheartradio.m3u8.data.Playlist;
import com.iheartradio.m3u8.data.StartData;
import com.iheartradio.m3u8.data.TrackData;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class MediaPlaylistTagWriter extends ExtTagWriter {
    static final IExtTagWriter d = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        public String b() {
            return Constants.U;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException {
            if (!mediaPlaylist.f()) {
                tagWriter.c(b());
            }
        }
    };
    static final IExtTagWriter e = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        public String b() {
            return Constants.V;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException {
            if (mediaPlaylist.e()) {
                tagWriter.c(b());
            }
        }
    };
    static final IExtTagWriter f = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.M;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException {
            if (mediaPlaylist.k() != null) {
                tagWriter.a(b(), mediaPlaylist.k().getValue());
            }
        }
    };
    static final IExtTagWriter g = new MediaPlaylistTagWriter() {
        private final Map<String, AttributeWriter<StartData>> m = new HashMap();

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.P;
        }

        {
            this.m.put(Constants.Q, new AttributeWriter<StartData>() {
                /* renamed from: a */
                public boolean b(StartData startData) {
                    return true;
                }

                /* renamed from: b */
                public String a(StartData startData) throws ParseException {
                    return Float.toString(startData.a());
                }
            });
            this.m.put(Constants.R, new AttributeWriter<StartData>() {
                /* renamed from: a */
                public boolean b(StartData startData) {
                    return true;
                }

                /* renamed from: b */
                public String a(StartData startData) throws ParseException {
                    return startData.b() ? Constants.ad : Constants.ae;
                }
            });
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
            if (mediaPlaylist.j()) {
                a(tagWriter, mediaPlaylist.i(), this.m);
            }
        }
    };
    static final IExtTagWriter h = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.O;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
            tagWriter.a(b(), Integer.toString(mediaPlaylist.c()));
        }
    };
    static final IExtTagWriter i = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.S;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
            tagWriter.a(b(), Integer.toString(mediaPlaylist.d()));
        }
    };
    static final IExtTagWriter j = new MediaPlaylistTagWriter() {
        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) {
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.T;
        }
    };
    static final IExtTagWriter k = new MediaPlaylistTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.X;
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
            for (TrackData next : mediaPlaylist.b()) {
                StringBuilder sb = new StringBuilder();
                if (playlist.f() <= 3) {
                    sb.append(Integer.toString((int) next.c().f6058a));
                } else {
                    sb.append(Float.toString(next.c().f6058a));
                }
                if (next.c().b != null) {
                    sb.append(Constants.e);
                    sb.append(next.c().b);
                }
                if (next.h()) {
                    tagWriter.c(Constants.W);
                }
                tagWriter.a(b(), sb.toString());
                tagWriter.b(next.a());
            }
        }
    };
    static final ExtTagWriter l = new MediaPlaylistTagWriter() {
        private final Map<String, AttributeWriter<EncryptionData>> m = new HashMap();

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.Y;
        }

        {
            this.m.put(Constants.Z, new AttributeWriter<EncryptionData>() {
                /* renamed from: a */
                public boolean b(EncryptionData encryptionData) {
                    return true;
                }

                /* renamed from: b */
                public String a(EncryptionData encryptionData) {
                    return encryptionData.a().getValue();
                }
            });
            this.m.put(Constants.o, new AttributeWriter<EncryptionData>() {
                /* renamed from: a */
                public boolean b(EncryptionData encryptionData) {
                    return true;
                }

                /* renamed from: b */
                public String a(EncryptionData encryptionData) throws ParseException {
                    return WriteUtil.a(encryptionData.c(), AnonymousClass9.this.b());
                }
            });
            this.m.put(Constants.aa, new AttributeWriter<EncryptionData>() {
                /* renamed from: a */
                public boolean b(EncryptionData encryptionData) {
                    return encryptionData.d();
                }

                /* renamed from: b */
                public String a(EncryptionData encryptionData) {
                    return WriteUtil.a(encryptionData.e());
                }
            });
            this.m.put(Constants.ab, new AttributeWriter<EncryptionData>() {
                /* renamed from: a */
                public boolean b(EncryptionData encryptionData) {
                    return true;
                }

                /* renamed from: b */
                public String a(EncryptionData encryptionData) throws ParseException {
                    return WriteUtil.a(encryptionData.g(), AnonymousClass9.this.b());
                }
            });
            this.m.put(Constants.ac, new AttributeWriter<EncryptionData>() {
                /* renamed from: a */
                public boolean b(EncryptionData encryptionData) {
                    return true;
                }

                /* renamed from: b */
                public String a(EncryptionData encryptionData) throws ParseException {
                    return WriteUtil.a(WriteUtil.a((List<? extends Object>) encryptionData.i(), "/"), AnonymousClass9.this.b());
                }
            });
        }

        public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
            if (mediaPlaylist.b().size() > 0) {
                TrackData trackData = mediaPlaylist.b().get(0);
                if (trackData.d()) {
                    a(tagWriter, trackData.i(), this.m);
                }
            }
        }
    };

    MediaPlaylistTagWriter() {
    }

    public final void a(TagWriter tagWriter, Playlist playlist) throws IOException, ParseException {
        if (playlist.b()) {
            a(tagWriter, playlist, playlist.d());
        }
    }

    public void a(TagWriter tagWriter, Playlist playlist, MediaPlaylist mediaPlaylist) throws IOException, ParseException {
        tagWriter.c(b());
    }
}
