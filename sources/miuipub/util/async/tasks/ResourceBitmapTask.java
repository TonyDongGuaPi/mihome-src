package miuipub.util.async.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.miuipub.internal.util.PackageConstants;
import miuipub.util.async.Cacheable;
import miuipub.util.async.Task;

public class ResourceBitmapTask extends Task<Bitmap> implements Cacheable {

    /* renamed from: a  reason: collision with root package name */
    private String f3045a;
    private int b;
    private BitmapFactory.Options c;

    public ResourceBitmapTask(int i) {
        this(i, (BitmapFactory.Options) null);
    }

    public ResourceBitmapTask(int i, BitmapFactory.Options options) {
        this.b = i;
        this.c = options;
    }

    public String d() {
        if (this.f3045a == null) {
            this.f3045a = "resource_" + this.b;
        }
        return this.f3045a;
    }

    /* renamed from: i */
    public Bitmap h() throws Exception {
        FileBitmapTask.f3040a.acquireUninterruptibly();
        try {
            return BitmapFactory.decodeResource(PackageConstants.a().getResources(), this.b, this.c);
        } finally {
            FileBitmapTask.f3040a.release();
        }
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
}
