package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.List;

@KeepForSdk
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zzap;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DependencyCycleException(java.util.List<com.google.firebase.components.Component<?>> r4) {
        /*
            r3 = this;
            java.lang.String r0 = "Dependency cycle detected: "
            java.lang.Object[] r1 = r4.toArray()
            java.lang.String r1 = java.util.Arrays.toString(r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0019
            java.lang.String r0 = r0.concat(r1)
            goto L_0x001f
        L_0x0019:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            r0 = r1
        L_0x001f:
            r3.<init>(r0)
            r3.zzap = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.DependencyCycleException.<init>(java.util.List):void");
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zzap;
    }
}
