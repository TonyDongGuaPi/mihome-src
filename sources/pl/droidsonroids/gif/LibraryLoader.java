package pl.droidsonroids.gif;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import pl.droidsonroids.relinker.ReLinker;

public class LibraryLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11975a = "pl_droidsonroids_gif";
    @SuppressLint({"StaticFieldLeak"})
    private static Context b;

    private LibraryLoader() {
    }

    public static void a(@NonNull Context context) {
        b = context.getApplicationContext();
    }

    private static Context b() {
        if (b == null) {
            try {
                b = (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e);
            }
        }
        return b;
    }

    static void a() {
        try {
            System.loadLibrary(f11975a);
        } catch (UnsatisfiedLinkError unused) {
            ReLinker.a(b(), f11975a);
        }
    }
}
