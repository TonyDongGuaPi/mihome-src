package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComparatorUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2335a = 1;
    public static final int b = 2;
    public static final int c = 3;

    public static Comparator<Track> a(final LinkedHashMap<Integer, Boolean> linkedHashMap) {
        if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
            return new Comparator<Track>() {
                /* renamed from: a */
                public int compare(Track track, Track track2) {
                    int aj;
                    int aj2;
                    int O;
                    int O2;
                    long aH;
                    long aH2;
                    int i = 0;
                    for (Map.Entry entry : linkedHashMap.entrySet()) {
                        if (i != 0) {
                            return i;
                        }
                        if (((Integer) entry.getKey()).intValue() == 1) {
                            if (((Boolean) entry.getValue()).booleanValue()) {
                                aH = track.aH();
                                aH2 = track2.aH();
                            } else {
                                aH = track2.aH();
                                aH2 = track.aH();
                            }
                            long j = aH - aH2;
                            i = j >= 2147483647L ? 1 : j <= -2147483648L ? -1 : (int) j;
                        }
                        if (i != 0) {
                            return i;
                        }
                        if (((Integer) entry.getKey()).intValue() == 2) {
                            if (((Boolean) entry.getValue()).booleanValue()) {
                                O = track.O();
                                O2 = track2.O();
                            } else {
                                O = track2.O();
                                O2 = track.O();
                            }
                            i = O - O2;
                        }
                        if (i != 0) {
                            return i;
                        }
                        if (((Integer) entry.getKey()).intValue() == 3) {
                            if (((Boolean) entry.getValue()).booleanValue()) {
                                aj = track.aj();
                                aj2 = track2.aj();
                            } else {
                                aj = track2.aj();
                                aj2 = track.aj();
                            }
                            i = aj - aj2;
                            continue;
                        }
                        if (i != 0) {
                            return i;
                        }
                    }
                    return i;
                }
            };
        }
        throw new IllegalArgumentException("sortType 不能为空");
    }

    public static Comparator<Track> a(final boolean z) {
        return new Comparator<Track>() {
            /* renamed from: a */
            public int compare(Track track, Track track2) {
                long aH;
                long aH2;
                if (z) {
                    aH = track.aH();
                    aH2 = track2.aH();
                } else {
                    aH = track2.aH();
                    aH2 = track.aH();
                }
                long j = aH - aH2;
                if (j >= 2147483647L) {
                    return 1;
                }
                if (j <= -2147483648L) {
                    return -1;
                }
                return (int) j;
            }
        };
    }

    public static Comparator<Track> b(final boolean z) {
        return new Comparator<Track>() {
            /* renamed from: a */
            public int compare(Track track, Track track2) {
                return z ? track.O() - track2.O() : track2.O() - track.O();
            }
        };
    }

    public static Comparator<Track> c(final boolean z) {
        return new Comparator<Track>() {
            /* renamed from: a */
            public int compare(Track track, Track track2) {
                return z ? track.aj() - track2.aj() : track2.aj() - track.aj();
            }
        };
    }
}
