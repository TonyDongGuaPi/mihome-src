package com.alipay.zoloz.toyger;

import android.util.Log;

public class ToygerServiceState {
    public static final int FINISH = 5;
    public static final int INIT = 2;
    public static final int LOADED = 1;
    public static final int READY = 3;
    public static final int RUNNING = 4;
    private static final String TAG = "TOYGER";
    public static final int UNLOAD = 0;
    private int mToygerServiceState = 0;

    public void set(int i) {
        this.mToygerServiceState = i;
        Log.i("TOYGER", "ToygerServiceState.set() : newState=" + this.mToygerServiceState);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        if (r6 != 4) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r6 != 3) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        if (r6 != 2) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        if (r6 != 1) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r6 != 3) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        if (r6 != 5) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean update(int r6) {
        /*
            r5 = this;
            int r0 = r5.mToygerServiceState
            r1 = 3
            r2 = 1
            switch(r0) {
                case 0: goto L_0x0026;
                case 1: goto L_0x0020;
                case 2: goto L_0x001b;
                case 3: goto L_0x0015;
                case 4: goto L_0x000d;
                case 5: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0028
        L_0x0008:
            if (r6 == 0) goto L_0x0029
            if (r6 == r1) goto L_0x0029
            goto L_0x0028
        L_0x000d:
            if (r6 == 0) goto L_0x0029
            if (r6 == r1) goto L_0x0029
            r1 = 5
            if (r6 == r1) goto L_0x0029
            goto L_0x0028
        L_0x0015:
            if (r6 == 0) goto L_0x0029
            r1 = 4
            if (r6 == r1) goto L_0x0029
            goto L_0x0028
        L_0x001b:
            if (r6 == 0) goto L_0x0029
            if (r6 == r1) goto L_0x0029
            goto L_0x0028
        L_0x0020:
            if (r6 == 0) goto L_0x0029
            r1 = 2
            if (r6 == r1) goto L_0x0029
            goto L_0x0028
        L_0x0026:
            if (r6 == r2) goto L_0x0029
        L_0x0028:
            r2 = 0
        L_0x0029:
            if (r2 == 0) goto L_0x0056
            r5.mToygerServiceState = r6
            java.lang.String r1 = "TOYGER"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "ToygerServiceState.update() : oldState="
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = ", newState="
            r3.append(r0)
            r3.append(r6)
            java.lang.String r6 = ", mToygerServiceState="
            r3.append(r6)
            int r6 = r5.mToygerServiceState
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            android.util.Log.i(r1, r6)
            goto L_0x007e
        L_0x0056:
            java.lang.String r1 = "TOYGER"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "ToygerServiceState.update() : oldState="
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = ", newState="
            r3.append(r0)
            r3.append(r6)
            java.lang.String r6 = ", mToygerServiceState="
            r3.append(r6)
            int r6 = r5.mToygerServiceState
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            android.util.Log.e(r1, r6)
        L_0x007e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.zoloz.toyger.ToygerServiceState.update(int):boolean");
    }

    public boolean isReady() {
        return this.mToygerServiceState == 3;
    }

    public boolean isRunning() {
        return this.mToygerServiceState == 4;
    }

    public boolean isLoaded() {
        return this.mToygerServiceState == 1;
    }

    public boolean isUnLoad() {
        return this.mToygerServiceState == 0;
    }

    public boolean isFinish() {
        return this.mToygerServiceState == 5;
    }
}
