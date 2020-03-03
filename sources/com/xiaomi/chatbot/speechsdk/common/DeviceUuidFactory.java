package com.xiaomi.chatbot.speechsdk.common;

import java.util.UUID;

public class DeviceUuidFactory {
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_FILE = "device_id.xml";
    protected static UUID uuid;

    /* JADX WARNING: Can't wrap try/catch for region: R(2:24|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        uuid = java.util.UUID.randomUUID();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0073 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceUuidFactory(android.content.Context r5) {
        /*
            r4 = this;
            r4.<init>()
            java.util.UUID r0 = uuid
            if (r0 != 0) goto L_0x0091
            java.lang.Class<com.xiaomi.chatbot.speechsdk.common.DeviceUuidFactory> r0 = com.xiaomi.chatbot.speechsdk.common.DeviceUuidFactory.class
            monitor-enter(r0)
            java.util.UUID r1 = uuid     // Catch:{ all -> 0x008e }
            if (r1 != 0) goto L_0x008c
            java.lang.String r1 = "device_id.xml"
            r2 = 0
            android.content.SharedPreferences r1 = r5.getSharedPreferences(r1, r2)     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "device_id"
            r3 = 0
            java.lang.String r2 = r1.getString(r2, r3)     // Catch:{ all -> 0x008e }
            if (r2 == 0) goto L_0x0025
            java.util.UUID r5 = java.util.UUID.fromString(r2)     // Catch:{ all -> 0x008e }
            uuid = r5     // Catch:{ all -> 0x008e }
            goto L_0x008c
        L_0x0025:
            android.content.ContentResolver r2 = r5.getContentResolver()     // Catch:{ all -> 0x008e }
            java.lang.String r3 = "android_id"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r3)     // Catch:{ all -> 0x008e }
            java.lang.String r3 = "9774d56d682e549c"
            boolean r3 = r3.equals(r2)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            if (r3 != 0) goto L_0x0044
            java.lang.String r5 = "utf8"
            byte[] r5 = r2.getBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            java.util.UUID r5 = java.util.UUID.nameUUIDFromBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            uuid = r5     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            goto L_0x0079
        L_0x0044:
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            int r2 = android.support.v4.app.ActivityCompat.checkSelfPermission(r5, r2)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            if (r2 == 0) goto L_0x0053
            java.util.UUID r5 = java.util.UUID.randomUUID()     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            uuid = r5     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            goto L_0x0079
        L_0x0053:
            java.lang.String r2 = "phone"
            java.lang.Object r5 = r5.getSystemService(r2)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            java.lang.String r5 = r5.getDeviceId()     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            if (r5 == 0) goto L_0x006c
            java.lang.String r2 = "utf8"
            byte[] r5 = r5.getBytes(r2)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            java.util.UUID r5 = java.util.UUID.nameUUIDFromBytes(r5)     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            goto L_0x0070
        L_0x006c:
            java.util.UUID r5 = java.util.UUID.randomUUID()     // Catch:{ UnsupportedEncodingException -> 0x0073 }
        L_0x0070:
            uuid = r5     // Catch:{ UnsupportedEncodingException -> 0x0073 }
            goto L_0x0079
        L_0x0073:
            java.util.UUID r5 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x008e }
            uuid = r5     // Catch:{ all -> 0x008e }
        L_0x0079:
            android.content.SharedPreferences$Editor r5 = r1.edit()     // Catch:{ all -> 0x008e }
            java.lang.String r1 = "device_id"
            java.util.UUID r2 = uuid     // Catch:{ all -> 0x008e }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008e }
            android.content.SharedPreferences$Editor r5 = r5.putString(r1, r2)     // Catch:{ all -> 0x008e }
            r5.commit()     // Catch:{ all -> 0x008e }
        L_0x008c:
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            goto L_0x0091
        L_0x008e:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            throw r5
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.chatbot.speechsdk.common.DeviceUuidFactory.<init>(android.content.Context):void");
    }

    public String getId() {
        return uuid.toString();
    }
}
