package dk.madslee.imageCapInsets.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.taobao.weex.annotation.JSMethod;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class RCTResourceDrawableIdHelper {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Integer> f2583a = new HashMap();

    public int a(Context context, @Nullable String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String replace = str.toLowerCase().replace("-", JSMethod.NOT_SET);
        if (this.f2583a.containsKey(replace)) {
            return this.f2583a.get(replace).intValue();
        }
        int identifier = context.getResources().getIdentifier(replace, "drawable", context.getPackageName());
        this.f2583a.put(replace, Integer.valueOf(identifier));
        return identifier;
    }

    @Nullable
    public Drawable b(Context context, @Nullable String str) {
        int a2 = a(context, str);
        if (a2 > 0) {
            return context.getResources().getDrawable(a2);
        }
        return null;
    }

    public Uri c(Context context, @Nullable String str) {
        int a2 = a(context, str);
        return a2 > 0 ? new Uri.Builder().scheme("res").path(String.valueOf(a2)).build() : Uri.EMPTY;
    }
}
