package com.google.android.exoplayer2.source;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final Object UID = new Object();
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    @Nullable
    private final Object tag;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    public int getPeriodCount() {
        return 1;
    }

    public int getWindowCount() {
        return 1;
    }

    public SinglePeriodTimeline(long j, boolean z, boolean z2) {
        this(j, z, z2, (Object) null);
    }

    public SinglePeriodTimeline(long j, boolean z, boolean z2, @Nullable Object obj) {
        this(j, j, 0, 0, z, z2, obj);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, boolean z, boolean z2, @Nullable Object obj) {
        this(C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, j4, z, z2, obj);
    }

    public SinglePeriodTimeline(long j, long j2, long j3, long j4, long j5, long j6, boolean z, boolean z2, @Nullable Object obj) {
        this.presentationStartTimeMs = j;
        this.windowStartTimeMs = j2;
        this.periodDurationUs = j3;
        this.windowDurationUs = j4;
        this.windowPositionInPeriodUs = j5;
        this.windowDefaultStartPositionUs = j6;
        this.isSeekable = z;
        this.isDynamic = z2;
        this.tag = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r3 > r0.windowDurationUs) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.Timeline.Window getWindow(int r21, com.google.android.exoplayer2.Timeline.Window r22, boolean r23, long r24) {
        /*
            r20 = this;
            r0 = r20
            r3 = 0
            r4 = 1
            r5 = r21
            com.google.android.exoplayer2.util.Assertions.checkIndex(r5, r3, r4)
            if (r23 == 0) goto L_0x000f
            java.lang.Object r3 = r0.tag
        L_0x000d:
            r5 = r3
            goto L_0x0011
        L_0x000f:
            r3 = 0
            goto L_0x000d
        L_0x0011:
            long r3 = r0.windowDefaultStartPositionUs
            boolean r6 = r0.isDynamic
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r6 == 0) goto L_0x0034
            r9 = 0
            int r6 = (r24 > r9 ? 1 : (r24 == r9 ? 0 : -1))
            if (r6 == 0) goto L_0x0034
            long r9 = r0.windowDurationUs
            int r6 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x002a
        L_0x0028:
            r12 = r7
            goto L_0x0035
        L_0x002a:
            r6 = 0
            long r3 = r3 + r24
            long r1 = r0.windowDurationUs
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x0034
            goto L_0x0028
        L_0x0034:
            r12 = r3
        L_0x0035:
            long r6 = r0.presentationStartTimeMs
            long r8 = r0.windowStartTimeMs
            boolean r10 = r0.isSeekable
            boolean r11 = r0.isDynamic
            long r14 = r0.windowDurationUs
            r16 = 0
            r17 = 0
            long r1 = r0.windowPositionInPeriodUs
            r4 = r22
            r18 = r1
            com.google.android.exoplayer2.Timeline$Window r1 = r4.set(r5, r6, r8, r10, r11, r12, r14, r16, r17, r18)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SinglePeriodTimeline.getWindow(int, com.google.android.exoplayer2.Timeline$Window, boolean, long):com.google.android.exoplayer2.Timeline$Window");
    }

    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        Assertions.checkIndex(i, 0, 1);
        return period.set((Object) null, z ? UID : null, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    public int getIndexOfPeriod(Object obj) {
        return UID.equals(obj) ? 0 : -1;
    }

    public Object getUidOfPeriod(int i) {
        Assertions.checkIndex(i, 0, 1);
        return UID;
    }
}
