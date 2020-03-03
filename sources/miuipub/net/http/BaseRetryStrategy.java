package miuipub.net.http;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.SSLException;

public class BaseRetryStrategy implements RetryStrategy {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2972a = 10000;
    public static final int b = 3;
    public static final float c = 1.0f;
    private static ArrayList<Class<?>> d = new ArrayList<>();
    private int e;
    private int f;
    private final int g;
    private final float h;

    static {
        d.add(InterruptedIOException.class);
        d.add(SSLException.class);
    }

    public BaseRetryStrategy() {
        this(10000, 3, 1.0f);
    }

    public BaseRetryStrategy(int i, int i2, float f2) {
        this.e = i;
        this.g = i2;
        this.h = f2;
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public boolean a(Throwable th) {
        this.f++;
        this.e = (int) (((float) this.e) + (((float) this.e) * this.h));
        if (!c() || !b(th)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return this.f <= this.g;
    }

    /* access modifiers changed from: protected */
    public boolean b(Throwable th) {
        Iterator<Class<?>> it = d.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
