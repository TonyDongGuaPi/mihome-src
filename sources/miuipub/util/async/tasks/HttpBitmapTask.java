package miuipub.util.async.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.util.Map;
import miuipub.net.http.HttpResponse;
import miuipub.net.http.HttpSession;
import miuipub.util.async.Cacheable;
import miuipub.util.async.tasks.HttpTask;

public class HttpBitmapTask extends HttpTask<Bitmap> implements Cacheable {

    /* renamed from: a  reason: collision with root package name */
    private BitmapFactory.Options f3041a;

    public HttpBitmapTask(String str) {
        this((HttpSession) null, HttpTask.Method.Get, str, (Map<String, String>) null, (BitmapFactory.Options) null);
    }

    public HttpBitmapTask(HttpSession httpSession, String str) {
        this(httpSession, HttpTask.Method.Get, str, (Map<String, String>) null, (BitmapFactory.Options) null);
    }

    public HttpBitmapTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map) {
        this(httpSession, method, str, map, (BitmapFactory.Options) null);
    }

    public HttpBitmapTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map, BitmapFactory.Options options) {
        super(httpSession, method, str, map);
        this.f3041a = options;
    }

    public String a() {
        return d();
    }

    public int a(Object obj) {
        if (obj instanceof Bitmap) {
            return ((Bitmap) obj).getByteCount();
        }
        return 0;
    }

    /* renamed from: i */
    public Bitmap h() throws Exception {
        HttpResponse j = j();
        FileBitmapTask.f3040a.acquireUninterruptibly();
        try {
            return miuipub.graphics.BitmapFactory.decodeStream(j.b(), (Rect) null, this.f3041a);
        } finally {
            j.g();
            FileBitmapTask.f3040a.release();
        }
    }
}
