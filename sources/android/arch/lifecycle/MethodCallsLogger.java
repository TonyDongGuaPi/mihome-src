package android.arch.lifecycle;

import android.support.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MethodCallsLogger {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Integer> f456a = new HashMap();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean a(String str, int i) {
        Integer num = this.f456a.get(str);
        boolean z = false;
        int intValue = num != null ? num.intValue() : 0;
        if ((intValue & i) != 0) {
            z = true;
        }
        this.f456a.put(str, Integer.valueOf(i | intValue));
        return !z;
    }
}
