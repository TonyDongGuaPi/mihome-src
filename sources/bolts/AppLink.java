package bolts;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

public class AppLink {

    /* renamed from: a  reason: collision with root package name */
    private Uri f492a;
    private List<Target> b;
    private Uri c;

    public static class Target {

        /* renamed from: a  reason: collision with root package name */
        private final Uri f493a;
        private final String b;
        private final String c;
        private final String d;

        public Target(String str, String str2, Uri uri, String str3) {
            this.b = str;
            this.c = str2;
            this.f493a = uri;
            this.d = str3;
        }

        public Uri a() {
            return this.f493a;
        }

        public String b() {
            return this.d;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.b;
        }
    }

    public AppLink(Uri uri, List<Target> list, Uri uri2) {
        this.f492a = uri;
        this.b = list == null ? Collections.emptyList() : list;
        this.c = uri2;
    }

    public Uri a() {
        return this.f492a;
    }

    public List<Target> b() {
        return Collections.unmodifiableList(this.b);
    }

    public Uri c() {
        return this.c;
    }
}
