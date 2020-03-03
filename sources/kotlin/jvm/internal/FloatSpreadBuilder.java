package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/jvm/internal/FloatSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class FloatSpreadBuilder extends PrimitiveSpreadBuilder<float[]> {

    /* renamed from: a  reason: collision with root package name */
    private final float[] f2823a;

    public FloatSpreadBuilder(int i) {
        super(i);
        this.f2823a = new float[i];
    }

    /* access modifiers changed from: protected */
    public int a(@NotNull float[] fArr) {
        Intrinsics.f(fArr, "receiver$0");
        return fArr.length;
    }

    public final void a(float f) {
        float[] fArr = this.f2823a;
        int b = b();
        b(b + 1);
        fArr[b] = f;
    }

    @NotNull
    public final float[] a() {
        return (float[]) a(this.f2823a, new float[c()]);
    }
}
