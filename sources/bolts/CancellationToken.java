package bolts;

import java.util.Locale;
import java.util.concurrent.CancellationException;

public class CancellationToken {

    /* renamed from: a  reason: collision with root package name */
    private final CancellationTokenSource f500a;

    CancellationToken(CancellationTokenSource cancellationTokenSource) {
        this.f500a = cancellationTokenSource;
    }

    public boolean a() {
        return this.f500a.a();
    }

    public CancellationTokenRegistration a(Runnable runnable) {
        return this.f500a.a(runnable);
    }

    public void b() throws CancellationException {
        this.f500a.d();
    }

    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.f500a.a())});
    }
}
