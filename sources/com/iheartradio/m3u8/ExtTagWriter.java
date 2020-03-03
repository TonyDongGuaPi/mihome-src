package com.iheartradio.m3u8;

import com.iheartradio.m3u8.data.Playlist;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

abstract class ExtTagWriter implements IExtTagWriter {

    /* renamed from: a  reason: collision with root package name */
    static final IExtTagWriter f5942a = new ExtTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        public String b() {
            return Constants.m;
        }
    };
    static final IExtTagWriter b = new ExtTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return null;
        }

        public void a(TagWriter tagWriter, Playlist playlist) throws IOException {
            List<String> list;
            if (playlist.a() && playlist.c().d()) {
                list = playlist.c().e();
            } else if (playlist.d().g()) {
                list = playlist.d().h();
            } else {
                list = Collections.emptyList();
            }
            for (String b : list) {
                tagWriter.b(b);
            }
        }
    };
    static final IExtTagWriter c = new ExtTagWriter() {
        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public String b() {
            return Constants.n;
        }

        public void a(TagWriter tagWriter, Playlist playlist) throws IOException {
            tagWriter.a(b(), Integer.toString(playlist.f()));
        }
    };

    /* access modifiers changed from: package-private */
    public abstract boolean a();

    ExtTagWriter() {
    }

    public void a(TagWriter tagWriter, Playlist playlist) throws IOException, ParseException {
        if (!a()) {
            tagWriter.c(b());
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void a(TagWriter tagWriter, T t, Map<String, ? extends AttributeWriter<T>> map) throws IOException, ParseException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            AttributeWriter attributeWriter = (AttributeWriter) next.getValue();
            String str = (String) next.getKey();
            if (attributeWriter.b(t)) {
                String a2 = attributeWriter.a(t);
                sb.append(str);
                sb.append("=");
                sb.append(a2);
                sb.append(Constants.f);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        tagWriter.a(b(), sb.toString());
    }
}
