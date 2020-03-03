package com.xiaomi.passport.utils;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public class AMPassTokenUpdateUtil {
    private static final int MAX_TIMES_PER_DAY = 100;
    private static final long MS_PER_DAY = TimeUnit.DAYS.toMillis(1);
    private static final String SP_KEY_DATE = "date";
    private static final String SP_KEY_TIMES = "frequency";
    private static final String TAG = "AMPassTokenUpdateUtil";
    private final SharedPreferencesUtil spUtil;

    public AMPassTokenUpdateUtil(Context context) {
        this.spUtil = new SharedPreferencesUtil(context, "passport_passtoken_update_util");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean needUpdatePassToken(java.lang.String r5, com.xiaomi.accountsdk.account.data.AccountInfo r6) {
        /*
            r4 = this;
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r6.getRePassToken()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x000f
            return r0
        L_0x000f:
            java.lang.Class<com.xiaomi.passport.utils.AMPassTokenUpdateUtil> r2 = com.xiaomi.passport.utils.AMPassTokenUpdateUtil.class
            monitor-enter(r2)
            java.lang.String r3 = com.xiaomi.accountsdk.utils.CloudCoder.getMd5DigestUpperCase(r5)     // Catch:{ all -> 0x003f }
            java.lang.String r6 = r6.getPassToken()     // Catch:{ all -> 0x003f }
            java.lang.String r1 = r1.toUpperCase()     // Catch:{ all -> 0x003f }
            boolean r5 = android.text.TextUtils.equals(r6, r5)     // Catch:{ all -> 0x003f }
            if (r5 != 0) goto L_0x003d
            boolean r5 = android.text.TextUtils.equals(r1, r3)     // Catch:{ all -> 0x003f }
            if (r5 == 0) goto L_0x003d
            boolean r5 = r4.checkFrequency()     // Catch:{ all -> 0x003f }
            if (r5 == 0) goto L_0x003d
            r4.incrementFrequency()     // Catch:{ all -> 0x003f }
            java.lang.String r5 = "AMPassTokenUpdateUtil"
            java.lang.String r6 = "need to update password in AM"
            com.xiaomi.accountsdk.utils.AccountLog.d(r5, r6)     // Catch:{ all -> 0x003f }
            r5 = 1
            monitor-exit(r2)     // Catch:{ all -> 0x003f }
            return r5
        L_0x003d:
            monitor-exit(r2)     // Catch:{ all -> 0x003f }
            return r0
        L_0x003f:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.utils.AMPassTokenUpdateUtil.needUpdatePassToken(java.lang.String, com.xiaomi.accountsdk.account.data.AccountInfo):boolean");
    }

    private boolean checkFrequency() {
        return getSpDate() != nowDate() || getSpTimes() < 100;
    }

    private void incrementFrequency() {
        if (getSpDate() == nowDate()) {
            saveSpTimes(getSpTimes() + 1);
            return;
        }
        saveSpDate(nowDate());
        saveSpTimes(1);
    }

    private long nowDate() {
        return System.currentTimeMillis() / MS_PER_DAY;
    }

    private long getSpDate() {
        return this.spUtil.getLong("date", 0);
    }

    private void saveSpDate(long j) {
        this.spUtil.saveLong("date", j);
    }

    private int getSpTimes() {
        return this.spUtil.getInt("frequency", 0);
    }

    private void saveSpTimes(int i) {
        this.spUtil.saveInt("frequency", i);
    }
}
