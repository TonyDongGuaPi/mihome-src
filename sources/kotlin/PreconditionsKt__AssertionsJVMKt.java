package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\b\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\b¨\u0006\u0007"}, d2 = {"assert", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/PreconditionsKt")
class PreconditionsKt__AssertionsJVMKt {
    @InlineOnly
    private static final void a(boolean z) {
        if (_Assertions.f2694a && !z) {
            throw new AssertionError("Assertion failed");
        }
    }

    @InlineOnly
    private static final void a(boolean z, Function0<? extends Object> function0) {
        if (_Assertions.f2694a && !z) {
            throw new AssertionError(function0.invoke());
        }
    }
}
