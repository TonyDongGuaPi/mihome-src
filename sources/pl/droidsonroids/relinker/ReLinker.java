package pl.droidsonroids.relinker;

import android.content.Context;
import java.io.File;

public class ReLinker {

    public interface LibraryInstaller {
        void a(Context context, String[] strArr, String str, File file, ReLinkerInstance reLinkerInstance);
    }

    public interface LibraryLoader {
        void a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        String d(String str);
    }

    public interface LoadListener {
        void a();

        void a(Throwable th);
    }

    public interface Logger {
        void a(String str);
    }

    public static void a(Context context, String str) {
        a(context, str, (String) null, (LoadListener) null);
    }

    public static void a(Context context, String str, String str2) {
        a(context, str, str2, (LoadListener) null);
    }

    public static void a(Context context, String str, LoadListener loadListener) {
        a(context, str, (String) null, loadListener);
    }

    public static void a(Context context, String str, String str2, LoadListener loadListener) {
        new ReLinkerInstance().a(context, str, str2, loadListener);
    }

    public static ReLinkerInstance a() {
        return new ReLinkerInstance().a();
    }

    public static ReLinkerInstance a(Logger logger) {
        return new ReLinkerInstance().a(logger);
    }

    public static ReLinkerInstance b() {
        return new ReLinkerInstance().b();
    }

    private ReLinker() {
    }
}
