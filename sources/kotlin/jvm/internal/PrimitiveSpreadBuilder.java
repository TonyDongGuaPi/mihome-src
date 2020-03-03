package kotlin.jvm.internal;

import com.xiaomi.payment.data.MibiConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028\u0000H$¢\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000bX\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "spreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public abstract class PrimitiveSpreadBuilder<T> {

    /* renamed from: a  reason: collision with root package name */
    private int f2829a;
    private final T[] b = new Object[this.c];
    private final int c;

    private static /* synthetic */ void a() {
    }

    /* access modifiers changed from: protected */
    public abstract int a(@NotNull T t);

    public PrimitiveSpreadBuilder(int i) {
        this.c = i;
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.f2829a;
    }

    /* access modifiers changed from: protected */
    public final void b(int i) {
        this.f2829a = i;
    }

    public final void b(@NotNull T t) {
        Intrinsics.f(t, "spreadArgument");
        T[] tArr = this.b;
        int i = this.f2829a;
        this.f2829a = i + 1;
        tArr[i] = t;
    }

    /* access modifiers changed from: protected */
    public final int c() {
        int i = this.c - 1;
        int i2 = 0;
        if (i >= 0) {
            int i3 = 0;
            while (true) {
                T t = this.b[i3];
                i2 += t != null ? a(t) : 1;
                if (i3 == i) {
                    break;
                }
                i3++;
            }
        }
        return i2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final T a(@NotNull T t, @NotNull T t2) {
        int i;
        Intrinsics.f(t, MibiConstants.gf);
        Intrinsics.f(t2, "result");
        int i2 = this.c - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int i4 = 0;
            int i5 = 0;
            i = 0;
            while (true) {
                T t3 = this.b[i4];
                if (t3 != null) {
                    if (i5 < i4) {
                        int i6 = i4 - i5;
                        System.arraycopy(t, i5, t2, i, i6);
                        i += i6;
                    }
                    int a2 = a(t3);
                    System.arraycopy(t3, 0, t2, i, a2);
                    i += a2;
                    i5 = i4 + 1;
                }
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
            i3 = i5;
        } else {
            i = 0;
        }
        if (i3 < this.c) {
            System.arraycopy(t, i3, t2, i, this.c - i3);
        }
        return t2;
    }
}