package cn.fraudmetrix.octopus.aspirit.b;

import android.support.annotation.NonNull;
import java.io.InputStream;
import java.util.Map;

public interface f {

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static f f623a;

        public static synchronized f a() {
            f fVar;
            synchronized (a.class) {
                if (f623a == null) {
                    f623a = new cn.fraudmetrix.octopus.aspirit.d.a.a();
                }
                fVar = f623a;
            }
            return fVar;
        }
    }

    void a(@NonNull String str, String str2, b<String, ?> bVar);

    void a(@NonNull String str, @NonNull Map<String, Object> map, b<InputStream, ?> bVar);

    void b(@NonNull String str, Map<String, Object> map, b<String, ?> bVar);

    void c(@NonNull String str, Map<String, Object> map, b<String, ?> bVar);
}
