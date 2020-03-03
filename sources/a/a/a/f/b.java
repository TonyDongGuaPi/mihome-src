package a.a.a.f;

import a.a.a.g.a;
import android.content.Context;
import in.cashify.otex.R;
import java.io.InputStream;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public final a.a.a.g.b f404a;

    public b(Context context, String str) {
        this.f404a = a(context, str);
    }

    public final a.a.a.g.b a(Context context, String str) {
        InputStream openRawResource = context.getResources().openRawResource(R.raw.otex_public);
        a.a.a.g.b a2 = new a(openRawResource).a(str);
        openRawResource.close();
        return a2;
    }

    public String a() {
        return this.f404a.a();
    }

    public String b() {
        return this.f404a.b();
    }
}
