package org.mp4parser.streaming.extensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.mp4parser.streaming.SampleExtension;

public class CompositionTimeSampleExtension implements SampleExtension {

    /* renamed from: a  reason: collision with root package name */
    public static Map<Long, CompositionTimeSampleExtension> f4062a = Collections.synchronizedMap(new HashMap());
    private long b;

    public static CompositionTimeSampleExtension a(long j) {
        CompositionTimeSampleExtension compositionTimeSampleExtension = f4062a.get(Long.valueOf(j));
        if (compositionTimeSampleExtension != null) {
            return compositionTimeSampleExtension;
        }
        CompositionTimeSampleExtension compositionTimeSampleExtension2 = new CompositionTimeSampleExtension();
        compositionTimeSampleExtension2.b = j;
        f4062a.put(Long.valueOf(j), compositionTimeSampleExtension2);
        return compositionTimeSampleExtension2;
    }

    public long a() {
        return this.b;
    }

    public String toString() {
        return "ctts=" + this.b;
    }
}
