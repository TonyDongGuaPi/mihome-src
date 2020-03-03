package kotlin.math;

import kotlin.Metadata;
import kotlin.jvm.JvmField;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/math/Constants;", "", "()V", "LN2", "", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class Constants {
    @JvmField

    /* renamed from: a  reason: collision with root package name */
    public static final double f2836a = Math.log(2.0d);
    @JvmField
    public static final double b = Math.ulp(1.0d);
    @JvmField
    public static final double c = Math.sqrt(b);
    @JvmField
    public static final double d = Math.sqrt(c);
    @JvmField
    public static final double e;
    @JvmField
    public static final double f;
    public static final Constants g = new Constants();

    static {
        double d2 = (double) 1;
        double d3 = c;
        Double.isNaN(d2);
        e = d2 / d3;
        double d4 = d;
        Double.isNaN(d2);
        f = d2 / d4;
    }

    private Constants() {
    }
}
