package dk.madslee.imageCapInsets;

import android.graphics.drawable.NinePatchDrawable;
import java.util.HashMap;

public class RCTImageCache {

    /* renamed from: a  reason: collision with root package name */
    private static RCTImageCache f2579a;
    private HashMap<String, NinePatchDrawable> b = new HashMap<>();

    public static RCTImageCache a() {
        if (f2579a == null) {
            f2579a = new RCTImageCache();
        }
        return f2579a;
    }

    protected RCTImageCache() {
    }

    public void a(String str, NinePatchDrawable ninePatchDrawable) {
        this.b.put(str, ninePatchDrawable);
    }

    public NinePatchDrawable a(String str) {
        return this.b.get(str);
    }

    public boolean b(String str) {
        return this.b.containsKey(str);
    }
}
