package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/DoubleSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class DoubleSpreadBuilder extends PrimitiveSpreadBuilder<double[]> {

    /* renamed from: a  reason: collision with root package name */
    private final double[] f2820a;

    public DoubleSpreadBuilder(int i) {
        super(i);
        this.f2820a = new double[i];
    }

    /* access modifiers changed from: protected */
    public int a(@NotNull double[] dArr) {
        Intrinsics.f(dArr, "receiver$0");
        return dArr.length;
    }

    public final void a(double d) {
        double[] dArr = this.f2820a;
        int b = b();
        b(b + 1);
        dArr[b] = d;
    }

    @NotNull
    public final double[] a() {
        return (double[]) a(this.f2820a, new double[c()]);
    }
}
