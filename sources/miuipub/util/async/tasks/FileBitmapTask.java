package miuipub.util.async.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.concurrent.Semaphore;
import miuipub.util.async.Cacheable;
import miuipub.util.async.Task;

public class FileBitmapTask extends Task<Bitmap> implements Cacheable {

    /* renamed from: a  reason: collision with root package name */
    static final Semaphore f3040a = new Semaphore(2);
    private String b;
    private BitmapFactory.Options c;

    public FileBitmapTask(String str) {
        this(str, (BitmapFactory.Options) null);
    }

    public FileBitmapTask(String str, BitmapFactory.Options options) {
        this.b = str;
        this.c = options;
    }

    public String d() {
        return this.b;
    }

    /* renamed from: i */
    public Bitmap h() throws Exception {
        f3040a.acquireUninterruptibly();
        try {
            return miuipub.graphics.BitmapFactory.decodeFile(this.b, this.c);
        } finally {
            f3040a.release();
        }
    }

    public String a() {
        return this.b;
    }

    public int a(Object obj) {
        if (obj instanceof Bitmap) {
            return ((Bitmap) obj).getByteCount();
        }
        return 0;
    }
}
