package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class qq9g9qqqq9qqgg9q9 {
    static final String[] gqg9qq9gqq9q9q = {gqg9qq9gqq9q9q("676c5069517148", 5), gqg9qq9gqq9q9q("752e452f", 75), gqg9qq9gqq9q9q("77514653", 34), gqg9qq9gqq9q9q("673a4c3d", 80), gqg9qq9gqq9q9q("71264a2a", 83), gqg9qq9gqq9q9q("774a5741472e", 43), gqg9qq9gqq9q9q("775e5755474b", 63), gqg9qq9gqq9q9q("032a1b2c1b", 17), gqg9qq9gqq9q9q("7a0b5f1f4e", 98), gqg9qq9gqq9q9q("7a144e115f", 125), gqg9qq9gqq9q9q("7a214b30", 72), gqg9qq9gqq9q9q("7b2a4821", 85), gqg9qq9gqq9q9q("776d5766477b", 12), gqg9qq9gqq9q9q("7e735d", 25), gqg9qq9gqq9q9q("77505f524b", 47), gqg9qq9gqq9q9q("7a764b675a", 31), gqg9qq9gqq9q9q("757a59", 28), gqg9qq9gqq9q9q("66334f3f5f385634", 81), gqg9qq9gqq9q9q("7b4d52405d", 33)};
    private static final String q9gqqq99999qq = gqg9qq9gqq9q9q("530f6b19761f7b552144284d3d553a542d034e1d74194d28442151395638410c6d0362056012", 114);
    private static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("51706133223b23362b233a2d7f776e7e676b7f6c7e7b29011808111d091a080d3c21332e352c221b1f", 14);
    public static final String[] qgg9qgg9999g9g = {gqg9qq9gqq9q9q("555376774e704f7b6957734c69", 35), gqg9qq9gqq9q9q("5577765050474157504c5b4b4c7061", 7), gqg9qq9gqq9q9q("550f763753305864275f3f503847", 127), gqg9qq9gqq9q9q("55297619570a48004e2c63", 89), gqg9qq9gqq9q9q("553476164f1045166d3a653f47045f0b581c", 68), gqg9qq9gqq9q9q("556476434c477271656a6d674f5c57535044", 20), gqg9qq9gqq9q9q("556976535d425e5a43436b6f71746b7260425a5e", 25), gqg9qq9gqq9q9q("552376195d085e1043096736722161347a2946064a0e", 83), gqg9qq9gqq9q9q("555876625d735e6b4372674d725a614f7a52", 40), gqg9qq9gqq9q9q("5568764f4c4b6e747b636876736b4f44434c", 24), gqg9qq9gqq9q9q("552176054e024f097e247731", 81), gqg9qq9gqq9q9q("550e76345d255e3d43247c09751c", 126), gqg9qq9gqq9q9q("55417676507f505f735371466c406d", 49), gqg9qq9gqq9q9q("556b76515d5d5352595f4444434d676b6e6b4b4c4345", 27), gqg9qq9gqq9q9q("553076055b0574237d235804500d", 64)};

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00f5, code lost:
        if (r9 != null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0101, code lost:
        if (r9 != null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0103, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0106, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00fc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999 gqg9qq9gqq9q9q(int r9, android.content.Context r10) {
        /*
            java.lang.String r0 = "516a6270737b69357c3527242e2d3b353c342b627778737c74747d"
            r1 = 20
            java.lang.String r0 = gqg9qq9gqq9q9q((java.lang.String) r0, (int) r1)
            android.net.Uri r2 = android.net.Uri.parse(r0)
            android.content.ContentResolver r1 = r10.getContentResolver()
            r10 = 6
            r0 = 0
            java.lang.String[] r3 = new java.lang.String[r10]     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String r10 = "6d4052"
            r4 = 4
            java.lang.String r10 = gqg9qq9gqq9q9q((java.lang.String) r10, (int) r4)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r7 = 0
            r3[r7] = r10     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String r10 = "41207712411f"
            r5 = 72
            java.lang.String r10 = gqg9qq9gqq9q9q((java.lang.String) r10, (int) r5)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r8 = 1
            r3[r8] = r10     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r10 = 2
            java.lang.String r5 = "5b6469585f55"
            r6 = 28
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r6)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r3[r10] = r5     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r10 = 3
            java.lang.String r5 = "517870786b747c594d56415e"
            r6 = 8
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r6)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r3[r10] = r5     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String r10 = "5f146d"
            r5 = 104(0x68, float:1.46E-43)
            java.lang.String r10 = gqg9qq9gqq9q9q((java.lang.String) r10, (int) r5)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r3[r4] = r10     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r10 = 5
            java.lang.String r4 = "5f1960"
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r3[r10] = r4     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String r10 = "41547766416b05761869"
            r4 = 60
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r10, (int) r4)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String[] r5 = new java.lang.String[r8]     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r5[r7] = r9     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            r6 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0100, all -> 0x00f8 }
            if (r9 == 0) goto L_0x00f5
            boolean r10 = r9.moveToFirst()     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            if (r10 == 0) goto L_0x00f5
            java.lang.String r10 = "6d2652"
            r1 = 98
            java.lang.String r10 = gqg9qq9gqq9q9q((java.lang.String) r10, (int) r1)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r10 = r9.getColumnIndex(r10)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r10 = r9.getInt(r10)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r1 = "5b5569695f64"
            r2 = 45
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r2)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r1 = r9.getColumnIndex(r1)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r1 = r9.getString(r1)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r2 = "512170216b2d7c004d0f4107"
            r3 = 81
            java.lang.String r2 = gqg9qq9gqq9q9q((java.lang.String) r2, (int) r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r2 = r9.getColumnIndex(r2)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r2 = r9.getString(r2)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r3 = "5f1d6d"
            r4 = 97
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r4)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r3 = r9.getColumnIndex(r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r3 = r9.getInt(r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r4 = "5f2660"
            r5 = 87
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r4 = r9.getColumnIndex(r4)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            int r4 = r9.getInt(r4)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            r5.<init>()     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            r5.append(r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r3 = "17532705"
            r6 = 52
            java.lang.String r3 = gqg9qq9gqq9q9q((java.lang.String) r3, (int) r6)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            r6[r7] = r4     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r3 = java.lang.String.format(r3, r6)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            r5.append(r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            if (r10 <= 0) goto L_0x00f5
            cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999 r4 = new cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            r4.<init>(r10, r1, r2, r3)     // Catch:{ Exception -> 0x0101, all -> 0x00f3 }
            if (r9 == 0) goto L_0x00f2
            r9.close()
        L_0x00f2:
            return r4
        L_0x00f3:
            r10 = move-exception
            goto L_0x00fa
        L_0x00f5:
            if (r9 == 0) goto L_0x0106
            goto L_0x0103
        L_0x00f8:
            r10 = move-exception
            r9 = r0
        L_0x00fa:
            if (r9 == 0) goto L_0x00ff
            r9.close()
        L_0x00ff:
            throw r10
        L_0x0100:
            r9 = r0
        L_0x0101:
            if (r9 == 0) goto L_0x0106
        L_0x0103:
            r9.close()
        L_0x0106:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.qgg9qgg9999g9g.qq9g9qqqq9qqgg9q9.gqg9qq9gqq9q9q(int, android.content.Context):cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999");
    }

    public static Map gqg9qq9gqq9q9q(Context context, TelephonyManager telephonyManager) {
        Map map;
        char c = 0;
        try {
            Class<?> cls = Class.forName(q9qq99qg9qqgqg9gqgg9);
            map = gqg9qq9gqq9q9q(context, telephonyManager, cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context}), cls);
            try {
                if (map.size() > 0) {
                    c = 1;
                }
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            map = new HashMap();
        }
        if (c == 0 && gqgqgqq9gq9q9q9.qgg9qgg9999g9g(23) && telephonyManager.getPhoneCount() == 2) {
            map = gqg9qq9gqq9q9q(context, telephonyManager, telephonyManager, telephonyManager.getClass());
            if (map.size() > 0) {
                c = 2;
            }
        }
        if (c == 0) {
            try {
                Class<?> cls2 = Class.forName(q9gqqq99999qq);
                Object systemService = context.getSystemService(gqg9qq9gqq9q9q("420777067c3c4e225426", 109));
                if (systemService == null) {
                    systemService = gqg9qq9gqq9q9q((Object) null, cls2, gqg9qq9gqq9q9q("55787648574b505f4947", 8));
                }
                Map gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(context, telephonyManager, systemService, cls2);
                try {
                    if (gqg9qq9gqq9q9q2.size() > 0) {
                        c = 3;
                    }
                } catch (ClassNotFoundException unused3) {
                }
                map = gqg9qq9gqq9q9q2;
            } catch (ClassNotFoundException unused4) {
            }
        }
        return c == 0 ? qgg9qgg9999g9g(context, telephonyManager) : map;
    }

    public static Map qgg9qgg9999g9g(Context context, TelephonyManager telephonyManager) {
        String str;
        String str2;
        String str3;
        HashMap hashMap = new HashMap();
        g9g99g99999999 gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q(0, context);
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.READ_PHONE_STATE")) {
            hashMap.put(qgg9qgg9999g9g[1], telephonyManager.getSubscriberId());
            hashMap.put(qgg9qgg9999g9g[2], telephonyManager.getLine1Number());
            hashMap.put(qgg9qgg9999g9g[3], telephonyManager.getDeviceId());
            hashMap.put(qgg9qgg9999g9g[4], telephonyManager.getVoiceMailNumber());
            if (gqg9qq9gqq9q9q2 != null) {
                str3 = gqg9qq9gqq9q9q2.qgg9qgg9999g9g();
            } else {
                str3 = telephonyManager.getSimSerialNumber();
            }
            hashMap.put(qgg9qgg9999g9g[5], str3);
        }
        hashMap.put(qgg9qgg9999g9g[6], telephonyManager.getNetworkCountryIso());
        if (gqg9qq9gqq9q9q2 != null) {
            str = gqg9qq9gqq9q9q2.q9qq99qg9qqgqg9gqgg9();
            str2 = gqg9qq9gqq9q9q2.q9gqqq99999qq();
        } else {
            str = telephonyManager.getNetworkOperatorName();
            str2 = telephonyManager.getNetworkOperator();
        }
        hashMap.put(qgg9qgg9999g9g[7], str);
        hashMap.put(qgg9qgg9999g9g[8], str2);
        String str4 = "";
        if (telephonyManager.getSimState() == 5) {
            str4 = telephonyManager.getSimOperatorName();
        }
        hashMap.put(qgg9qgg9999g9g[9], str4);
        int phoneType = telephonyManager.getPhoneType();
        String str5 = qgg9qgg9999g9g[10];
        hashMap.put(str5, phoneType + "");
        hashMap.put(qgg9qgg9999g9g[11], String.valueOf(telephonyManager.getNetworkType()));
        String str6 = "";
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
            qgg9qgg9999g9g qgg9qgg9999g9g2 = new qgg9qgg9999g9g();
            try {
                if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(17)) {
                    gqg9qq9gqq9q9q(telephonyManager.getCellLocation(), qgg9qgg9999g9g2);
                    gqg9qq9gqq9q9q(telephonyManager.getNeighboringCellInfo(), qgg9qgg9999g9g2);
                } else {
                    List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
                    if (allCellInfo != null) {
                        qgg9qgg9999g9g((List) allCellInfo, qgg9qgg9999g9g2);
                    }
                }
            } catch (Throwable unused) {
            }
            str6 = qgg9qgg9999g9g2.gqg9qq9gqq9q9q();
        }
        hashMap.put(qgg9qgg9999g9g[12], str6);
        return hashMap;
    }

    /* JADX WARNING: type inference failed for: r20v0, types: [java.lang.Object] */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:86|87|88|89|90|91|92|93|(1:100)|101|(1:107)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:89:0x045a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0494 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.Map gqg9qq9gqq9q9q(android.content.Context r18, android.telephony.TelephonyManager r19, java.lang.Object r20, java.lang.Class r21) {
        /*
            r0 = r18
            r1 = r20
            r2 = r21
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.lang.String r4 = r21.getName()
            java.lang.String r5 = q9qq99qg9qqgqg9gqgg9
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0027
            r4 = 23
            boolean r4 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9.qgg9qgg9999g9g(r4)
            if (r4 == 0) goto L_0x0027
            java.lang.Class r4 = r19.getClass()
            r5 = r4
            r4 = r19
            goto L_0x0029
        L_0x0027:
            r4 = r1
            r5 = r2
        L_0x0029:
            r6 = 0
            cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999 r7 = gqg9qq9gqq9q9q((int) r6, (android.content.Context) r0)
            r8 = 1
            cn.tongdun.android.core.qgg9qgg9999g9g.g9g99g99999999 r9 = gqg9qq9gqq9q9q((int) r8, (android.content.Context) r0)
            if (r7 == 0) goto L_0x003a
            int r10 = r7.gqg9qq9gqq9q9q()
            goto L_0x003b
        L_0x003a:
            r10 = 0
        L_0x003b:
            if (r9 == 0) goto L_0x0042
            int r11 = r9.gqg9qq9gqq9q9q()
            goto L_0x0043
        L_0x0042:
            r11 = 1
        L_0x0043:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q((android.content.Context) r0, (java.lang.String) r12)
            if (r12 == 0) goto L_0x0222
            java.lang.String[] r12 = qgg9qgg9999g9g
            r12 = r12[r8]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r17 = java.lang.Integer.TYPE
            r14[r6] = r17
            java.lang.Object[] r13 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r10)
            r13[r6] = r17
            java.lang.String r12 = gqg9qq9gqq9q9q(r4, r5, r12, r14, r13)
            java.lang.String[] r13 = qgg9qgg9999g9g
            r13 = r13[r8]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r17 = java.lang.Integer.TYPE
            r14[r6] = r17
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r11)
            r15[r6] = r17
            java.lang.String r13 = gqg9qq9gqq9q9q(r4, r5, r13, r14, r15)
            java.lang.String r14 = "1f"
            r15 = 105(0x69, float:1.47E-43)
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r12)
            if (r14 == 0) goto L_0x0097
            java.lang.String r14 = "1f"
            r15 = 110(0x6e, float:1.54E-43)
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r13)
            if (r14 == 0) goto L_0x0097
            java.lang.String r12 = r19.getSubscriberId()
        L_0x0097:
            java.lang.String[] r14 = qgg9qgg9999g9g
            r14 = r14[r8]
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (java.lang.String) r13)
            r3.put(r14, r12)
            java.lang.String[] r12 = qgg9qgg9999g9g
            r13 = 2
            r12 = r12[r13]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r14[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r10)
            r15[r6] = r17
            java.lang.String r12 = gqg9qq9gqq9q9q(r4, r5, r12, r14, r15)
            java.lang.String[] r14 = qgg9qgg9999g9g
            r14 = r14[r13]
            java.lang.Class[] r13 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r13[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r11)
            r15[r6] = r17
            java.lang.String r13 = gqg9qq9gqq9q9q(r4, r5, r14, r13, r15)
            java.lang.String r14 = "1f"
            r15 = 45
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r12)
            if (r14 == 0) goto L_0x00ef
            java.lang.String r14 = "1f"
            r15 = 54
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r13)
            if (r14 == 0) goto L_0x00ef
            java.lang.String r12 = r19.getLine1Number()
        L_0x00ef:
            java.lang.String[] r14 = qgg9qgg9999g9g
            r15 = 2
            r14 = r14[r15]
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (java.lang.String) r13)
            r3.put(r14, r12)
            java.lang.String[] r12 = qgg9qgg9999g9g
            r13 = 3
            r12 = r12[r13]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r14[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r16 = java.lang.Integer.valueOf(r6)
            r15[r6] = r16
            java.lang.String r12 = gqg9qq9gqq9q9q(r4, r5, r12, r14, r15)
            java.lang.String[] r14 = qgg9qgg9999g9g
            r14 = r14[r13]
            java.lang.Class[] r13 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r13[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r8)
            r15[r6] = r17
            java.lang.String r13 = gqg9qq9gqq9q9q(r4, r5, r14, r13, r15)
            java.lang.String r14 = "1f"
            r15 = 123(0x7b, float:1.72E-43)
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r12)
            if (r14 == 0) goto L_0x0148
            java.lang.String r14 = "1f"
            r15 = 95
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r13)
            if (r14 == 0) goto L_0x0148
            java.lang.String r12 = r19.getDeviceId()
        L_0x0148:
            java.lang.String[] r14 = qgg9qgg9999g9g
            r15 = 3
            r14 = r14[r15]
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (java.lang.String) r13)
            r3.put(r14, r12)
            int r12 = android.os.Build.VERSION.SDK_INT
            r13 = 27
            r14 = 4
            if (r12 >= r13) goto L_0x01b4
            java.lang.String[] r12 = qgg9qgg9999g9g
            r12 = r12[r14]
            java.lang.Class[] r13 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r13[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r10)
            r15[r6] = r17
            java.lang.String r12 = gqg9qq9gqq9q9q(r4, r5, r12, r13, r15)
            java.lang.String[] r13 = qgg9qgg9999g9g
            r13 = r13[r14]
            java.lang.Class[] r15 = new java.lang.Class[r8]
            java.lang.Class r17 = java.lang.Integer.TYPE
            r15[r6] = r17
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r11)
            r14[r6] = r17
            java.lang.String r13 = gqg9qq9gqq9q9q(r4, r5, r13, r15, r14)
            java.lang.String r14 = "1f"
            r15 = 116(0x74, float:1.63E-43)
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r12)
            if (r14 == 0) goto L_0x01a7
            java.lang.String r14 = "1f"
            r15 = 83
            java.lang.String r14 = gqg9qq9gqq9q9q((java.lang.String) r14, (int) r15)
            boolean r14 = r14.equals(r13)
            if (r14 == 0) goto L_0x01a7
            java.lang.String r12 = r19.getVoiceMailNumber()
        L_0x01a7:
            java.lang.String[] r14 = qgg9qgg9999g9g
            r15 = 4
            r14 = r14[r15]
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (java.lang.String) r13)
            r3.put(r14, r12)
            goto L_0x01c4
        L_0x01b4:
            r15 = 4
            java.lang.String[] r12 = qgg9qgg9999g9g
            r12 = r12[r15]
            java.lang.String r13 = "1f5e2c"
            r14 = 45
            java.lang.String r13 = gqg9qq9gqq9q9q((java.lang.String) r13, (int) r14)
            r3.put(r12, r13)
        L_0x01c4:
            java.lang.String r12 = "1f"
            r13 = 111(0x6f, float:1.56E-43)
            gqg9qq9gqq9q9q((java.lang.String) r12, (int) r13)
            r12 = 5
            if (r7 == 0) goto L_0x01d3
            java.lang.String r4 = r7.qgg9qgg9999g9g()
            goto L_0x01e9
        L_0x01d3:
            java.lang.String[] r13 = qgg9qgg9999g9g
            r13 = r13[r12]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r14[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r10)
            r15[r6] = r17
            java.lang.String r4 = gqg9qq9gqq9q9q(r4, r5, r13, r14, r15)
        L_0x01e9:
            java.lang.String r5 = "1f"
            r13 = 96
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r13)
            if (r9 == 0) goto L_0x01f7
            java.lang.String r5 = r9.qgg9qgg9999g9g()
        L_0x01f7:
            java.lang.String r13 = "1f"
            r14 = 99
            java.lang.String r13 = gqg9qq9gqq9q9q((java.lang.String) r13, (int) r14)
            boolean r13 = r13.equals(r4)
            if (r13 == 0) goto L_0x0217
            java.lang.String r13 = "1f"
            r14 = 109(0x6d, float:1.53E-43)
            java.lang.String r13 = gqg9qq9gqq9q9q((java.lang.String) r13, (int) r14)
            boolean r13 = r13.equals(r5)
            if (r13 == 0) goto L_0x0217
            java.lang.String r4 = r19.getSimSerialNumber()
        L_0x0217:
            java.lang.String[] r13 = qgg9qgg9999g9g
            r12 = r13[r12]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r5)
            r3.put(r12, r4)
        L_0x0222:
            java.lang.String[] r4 = qgg9qgg9999g9g
            r5 = 6
            r4 = r4[r5]
            java.lang.Class[] r12 = new java.lang.Class[r8]
            java.lang.Class r13 = java.lang.Integer.TYPE
            r12[r6] = r13
            java.lang.Object[] r13 = new java.lang.Object[r8]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r10)
            r13[r6] = r14
            java.lang.String r4 = gqg9qq9gqq9q9q(r1, r2, r4, r12, r13)
            java.lang.String r12 = "1f"
            r13 = 9
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (int) r13)
            boolean r12 = r12.equals(r4)
            if (r12 == 0) goto L_0x024b
            java.lang.String r4 = r19.getNetworkCountryIso()
        L_0x024b:
            java.lang.String[] r12 = qgg9qgg9999g9g
            r12 = r12[r5]
            java.lang.Class[] r14 = new java.lang.Class[r8]
            java.lang.Class r15 = java.lang.Integer.TYPE
            r14[r6] = r15
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r11)
            r15[r6] = r17
            java.lang.String r12 = gqg9qq9gqq9q9q(r1, r2, r12, r14, r15)
            java.lang.String[] r14 = qgg9qgg9999g9g
            r5 = r14[r5]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r12)
            r3.put(r5, r4)
            java.lang.String r4 = "1f"
            r5 = 3
            gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)
            r4 = 7
            if (r7 == 0) goto L_0x027a
            java.lang.String r5 = r7.q9qq99qg9qqgqg9gqgg9()
            goto L_0x0290
        L_0x027a:
            java.lang.String[] r5 = qgg9qgg9999g9g
            r5 = r5[r4]
            java.lang.Class[] r12 = new java.lang.Class[r8]
            java.lang.Class r14 = java.lang.Integer.TYPE
            r12[r6] = r14
            java.lang.Object[] r14 = new java.lang.Object[r8]
            java.lang.Integer r15 = java.lang.Integer.valueOf(r10)
            r14[r6] = r15
            java.lang.String r5 = gqg9qq9gqq9q9q(r1, r2, r5, r12, r14)
        L_0x0290:
            java.lang.String r12 = "1f"
            r14 = 43
            java.lang.String r12 = gqg9qq9gqq9q9q((java.lang.String) r12, (int) r14)
            boolean r12 = r12.equals(r5)
            if (r12 == 0) goto L_0x02a2
            java.lang.String r5 = r19.getNetworkOperatorName()
        L_0x02a2:
            java.lang.String r12 = ""
            if (r9 == 0) goto L_0x02aa
            java.lang.String r12 = r9.q9qq99qg9qqgqg9gqgg9()
        L_0x02aa:
            java.lang.String[] r15 = qgg9qgg9999g9g
            r4 = r15[r4]
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (java.lang.String) r12)
            r3.put(r4, r5)
            java.lang.String r4 = "1f"
            r5 = 124(0x7c, float:1.74E-43)
            gqg9qq9gqq9q9q((java.lang.String) r4, (int) r5)
            if (r7 == 0) goto L_0x02c3
            java.lang.String r4 = r7.q9gqqq99999qq()
            goto L_0x02db
        L_0x02c3:
            java.lang.String[] r4 = qgg9qgg9999g9g
            r5 = 8
            r4 = r4[r5]
            java.lang.Class[] r5 = new java.lang.Class[r8]
            java.lang.Class r7 = java.lang.Integer.TYPE
            r5[r6] = r7
            java.lang.Object[] r7 = new java.lang.Object[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)
            r7[r6] = r12
            java.lang.String r4 = gqg9qq9gqq9q9q(r1, r2, r4, r5, r7)
        L_0x02db:
            java.lang.String r5 = "1f"
            r7 = 104(0x68, float:1.46E-43)
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r7)
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x02ed
            java.lang.String r4 = r19.getNetworkOperator()
        L_0x02ed:
            java.lang.String r5 = ""
            if (r9 == 0) goto L_0x02f5
            java.lang.String r5 = r9.q9gqqq99999qq()
        L_0x02f5:
            java.lang.String[] r7 = qgg9qgg9999g9g
            r9 = 8
            r7 = r7[r9]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r5)
            r3.put(r7, r4)
            java.lang.String[] r4 = qgg9qgg9999g9g
            r4 = r4[r13]
            java.lang.Class[] r5 = new java.lang.Class[r8]
            java.lang.Class r7 = java.lang.Integer.TYPE
            r5[r6] = r7
            java.lang.Object[] r7 = new java.lang.Object[r8]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            r7[r6] = r9
            java.lang.String r4 = gqg9qq9gqq9q9q(r1, r2, r4, r5, r7)
            java.lang.String r5 = "1f"
            r7 = 82
            java.lang.String r5 = gqg9qq9gqq9q9q((java.lang.String) r5, (int) r7)
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x032a
            java.lang.String r4 = r19.getSimOperatorName()
        L_0x032a:
            java.lang.String[] r5 = qgg9qgg9999g9g
            r5 = r5[r13]
            java.lang.Class[] r7 = new java.lang.Class[r8]
            java.lang.Class r9 = java.lang.Integer.TYPE
            r7[r6] = r9
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r8)
            r9[r6] = r12
            java.lang.String r5 = gqg9qq9gqq9q9q(r1, r2, r5, r7, r9)
            java.lang.String[] r7 = qgg9qgg9999g9g
            r7 = r7[r13]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r5)
            r3.put(r7, r4)
            java.lang.String[] r4 = qgg9qgg9999g9g
            r5 = 10
            r4 = r4[r5]
            java.lang.Class[] r7 = new java.lang.Class[r8]
            java.lang.Class r9 = java.lang.Integer.TYPE
            r7[r6] = r9
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)
            r9[r6] = r12
            java.lang.String r4 = gqg9qq9gqq9q9q(r1, r2, r4, r7, r9)
            java.lang.String r7 = "1f"
            r9 = 58
            java.lang.String r7 = gqg9qq9gqq9q9q((java.lang.String) r7, (int) r9)
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x0386
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r7 = r19.getPhoneType()
            r4.append(r7)
            java.lang.String r7 = ""
            r4.append(r7)
            java.lang.String r4 = r4.toString()
        L_0x0386:
            java.lang.String[] r7 = qgg9qgg9999g9g
            r7 = r7[r5]
            java.lang.Class[] r9 = new java.lang.Class[r8]
            java.lang.Class r12 = java.lang.Integer.TYPE
            r9[r6] = r12
            java.lang.Object[] r12 = new java.lang.Object[r8]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            r12[r6] = r13
            java.lang.String r7 = gqg9qq9gqq9q9q(r1, r2, r7, r9, r12)
            java.lang.String[] r9 = qgg9qgg9999g9g
            r5 = r9[r5]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r7)
            r3.put(r5, r4)
            java.lang.String[] r4 = qgg9qgg9999g9g
            r5 = 11
            r4 = r4[r5]
            java.lang.Class[] r7 = new java.lang.Class[r8]
            java.lang.Class r9 = java.lang.Integer.TYPE
            r7[r6] = r9
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)
            r9[r6] = r12
            java.lang.String r4 = gqg9qq9gqq9q9q(r1, r2, r4, r7, r9)
            java.lang.String r7 = "1f"
            r9 = 38
            java.lang.String r7 = gqg9qq9gqq9q9q((java.lang.String) r7, (int) r9)
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x03e2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r7 = r19.getNetworkType()
            r4.append(r7)
            java.lang.String r7 = ""
            r4.append(r7)
            java.lang.String r4 = r4.toString()
        L_0x03e2:
            java.lang.String[] r7 = qgg9qgg9999g9g
            r7 = r7[r5]
            java.lang.Class[] r9 = new java.lang.Class[r8]
            java.lang.Class r12 = java.lang.Integer.TYPE
            r9[r6] = r12
            java.lang.Object[] r12 = new java.lang.Object[r8]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            r12[r6] = r13
            java.lang.String r7 = gqg9qq9gqq9q9q(r1, r2, r7, r9, r12)
            java.lang.String[] r9 = qgg9qgg9999g9g
            r5 = r9[r5]
            java.lang.String r4 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r7)
            r3.put(r5, r4)
            java.lang.String r4 = ""
            java.lang.String r5 = ""
            r7 = 2
            java.lang.String[] r7 = new java.lang.String[r7]
            java.lang.String r9 = "android.permission.ACCESS_COARSE_LOCATION"
            r7[r6] = r9
            java.lang.String r9 = "android.permission.ACCESS_FINE_LOCATION"
            r7[r8] = r9
            boolean r0 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q((android.content.Context) r0, (java.lang.String[]) r7)
            r7 = 12
            if (r0 == 0) goto L_0x052d
            cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g r0 = new cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g
            r0.<init>()
            cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g r4 = new cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g
            r4.<init>()
            java.lang.String[] r5 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x045a }
            r5 = r5[r7]     // Catch:{ Throwable -> 0x045a }
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x045a }
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x045a }
            r9[r6] = r12     // Catch:{ Throwable -> 0x045a }
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x045a }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x045a }
            r12[r6] = r13     // Catch:{ Throwable -> 0x045a }
            java.lang.Object r5 = qgg9qgg9999g9g(r1, r2, r5, r9, r12)     // Catch:{ Throwable -> 0x045a }
            java.lang.String[] r9 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x045a }
            r9 = r9[r7]     // Catch:{ Throwable -> 0x045a }
            java.lang.Class[] r12 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x045a }
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x045a }
            r12[r6] = r13     // Catch:{ Throwable -> 0x045a }
            java.lang.Object[] r13 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x045a }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x045a }
            r13[r6] = r15     // Catch:{ Throwable -> 0x045a }
            java.lang.Object r9 = qgg9qgg9999g9g(r1, r2, r9, r12, r13)     // Catch:{ Throwable -> 0x045a }
            android.telephony.CellLocation r5 = (android.telephony.CellLocation) r5     // Catch:{ Throwable -> 0x045a }
            gqg9qq9gqq9q9q((android.telephony.CellLocation) r5, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r0)     // Catch:{ Throwable -> 0x045a }
            android.telephony.CellLocation r9 = (android.telephony.CellLocation) r9     // Catch:{ Throwable -> 0x045a }
            gqg9qq9gqq9q9q((android.telephony.CellLocation) r9, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r4)     // Catch:{ Throwable -> 0x045a }
        L_0x045a:
            java.lang.String[] r5 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x0494 }
            r9 = 13
            r5 = r5[r9]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x0494 }
            r9[r6] = r12     // Catch:{ Throwable -> 0x0494 }
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x0494 }
            r12[r6] = r13     // Catch:{ Throwable -> 0x0494 }
            java.lang.Object r5 = qgg9qgg9999g9g(r1, r2, r5, r9, r12)     // Catch:{ Throwable -> 0x0494 }
            java.lang.String[] r9 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x0494 }
            r12 = 13
            r9 = r9[r12]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Class[] r12 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Class r13 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x0494 }
            r12[r6] = r13     // Catch:{ Throwable -> 0x0494 }
            java.lang.Object[] r13 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0494 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x0494 }
            r13[r6] = r15     // Catch:{ Throwable -> 0x0494 }
            java.lang.Object r9 = qgg9qgg9999g9g(r1, r2, r9, r12, r13)     // Catch:{ Throwable -> 0x0494 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ Throwable -> 0x0494 }
            gqg9qq9gqq9q9q((java.util.List) r5, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r0)     // Catch:{ Throwable -> 0x0494 }
            java.util.List r9 = (java.util.List) r9     // Catch:{ Throwable -> 0x0494 }
            gqg9qq9gqq9q9q((java.util.List) r9, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r4)     // Catch:{ Throwable -> 0x0494 }
        L_0x0494:
            java.lang.String[] r5 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x04ce }
            r9 = 14
            r5 = r5[r9]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x04ce }
            r9[r6] = r12     // Catch:{ Throwable -> 0x04ce }
            java.lang.Object[] r12 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x04ce }
            r12[r6] = r10     // Catch:{ Throwable -> 0x04ce }
            java.lang.Object r5 = qgg9qgg9999g9g(r1, r2, r5, r9, r12)     // Catch:{ Throwable -> 0x04ce }
            java.lang.String[] r9 = qgg9qgg9999g9g     // Catch:{ Throwable -> 0x04ce }
            r10 = 14
            r9 = r9[r10]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Class[] r10 = new java.lang.Class[r8]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Class r12 = java.lang.Integer.TYPE     // Catch:{ Throwable -> 0x04ce }
            r10[r6] = r12     // Catch:{ Throwable -> 0x04ce }
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x04ce }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ Throwable -> 0x04ce }
            r8[r6] = r11     // Catch:{ Throwable -> 0x04ce }
            java.lang.Object r1 = qgg9qgg9999g9g(r1, r2, r9, r10, r8)     // Catch:{ Throwable -> 0x04ce }
            java.util.List r5 = (java.util.List) r5     // Catch:{ Throwable -> 0x04ce }
            qgg9qgg9999g9g((java.util.List) r5, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r0)     // Catch:{ Throwable -> 0x04ce }
            java.util.List r1 = (java.util.List) r1     // Catch:{ Throwable -> 0x04ce }
            qgg9qgg9999g9g((java.util.List) r1, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r4)     // Catch:{ Throwable -> 0x04ce }
        L_0x04ce:
            java.lang.String r1 = r0.gqg9qq9gqq9q9q()
            java.lang.String r5 = r4.gqg9qq9gqq9q9q()
            java.lang.String r2 = "695f"
            java.lang.String r2 = gqg9qq9gqq9q9q((java.lang.String) r2, (int) r14)
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x04fd
            java.lang.String r2 = "6942"
            r4 = 54
            java.lang.String r2 = gqg9qq9gqq9q9q((java.lang.String) r2, (int) r4)
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x04fd
            java.util.List r2 = r19.getAllCellInfo()
            if (r2 == 0) goto L_0x04fd
            qgg9qgg9999g9g((java.util.List) r2, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r0)
            java.lang.String r1 = r0.gqg9qq9gqq9q9q()
        L_0x04fd:
            r4 = r1
            java.lang.String r1 = "6921"
            r2 = 85
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r2)
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x052d
            java.lang.String r1 = "6952"
            r2 = 38
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r1, (int) r2)
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L_0x052d
            r1 = 26
            boolean r1 = cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(r1)
            if (r1 == 0) goto L_0x052d
            android.telephony.CellLocation r1 = r19.getCellLocation()
            gqg9qq9gqq9q9q((android.telephony.CellLocation) r1, (cn.tongdun.android.core.q9gqqq99999qq.qgg9qgg9999g9g) r0)
            java.lang.String r4 = r0.gqg9qq9gqq9q9q()
        L_0x052d:
            java.lang.String[] r0 = qgg9qgg9999g9g
            r0 = r0[r7]
            java.lang.String r1 = gqg9qq9gqq9q9q((java.lang.String) r4, (java.lang.String) r5)
            r3.put(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.tongdun.android.core.qgg9qgg9999g9g.qq9g9qqqq9qqgg9q9.gqg9qq9gqq9q9q(android.content.Context, android.telephony.TelephonyManager, java.lang.Object, java.lang.Class):java.util.Map");
    }

    private static Object gqg9qq9gqq9q9q(Object obj, Class cls, String str) {
        try {
            return cls.getDeclaredMethod(str, new Class[0]).invoke(obj, new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String gqg9qq9gqq9q9q(Object obj, Class cls, String str, Class[] clsArr, Object... objArr) {
        try {
            Object invoke = cls.getDeclaredMethod(str, clsArr).invoke(obj, objArr);
            if (invoke == null) {
                return gqg9qq9gqq9q9q("1f", 122);
            }
            if (invoke.toString().isEmpty()) {
                return "";
            }
            return invoke.toString();
        } catch (Throwable unused) {
            return gqg9qq9gqq9q9q("1f", 70);
        }
    }

    private static Object qgg9qgg9999g9g(Object obj, Class cls, String str, Class[] clsArr, Object... objArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr).invoke(obj, objArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String gqg9qq9gqq9q9q(String str, String str2) {
        if (str == null || str.equals("")) {
            str = gqg9qq9gqq9q9q("1f", 48);
        }
        if (str2 == null || str2.equals("")) {
            str2 = gqg9qq9gqq9q9q("1f", 49);
        }
        return str + gqg9qq9gqq9q9q("1e", 98) + str2;
    }

    private static void gqg9qq9gqq9q9q(CellLocation cellLocation, qgg9qgg9999g9g qgg9qgg9999g9g2) {
        if (cellLocation == null) {
            return;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            qgg9qgg9999g9g2.gqg9qq9gqq9q9q(0, gsmCellLocation.getLac(), gsmCellLocation.getCid());
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            qgg9qgg9999g9g2.gqg9qq9gqq9q9q(1, cdmaCellLocation.getNetworkId(), cdmaCellLocation.getSystemId(), cdmaCellLocation.getBaseStationId(), cdmaCellLocation.getBaseStationLatitude(), cdmaCellLocation.getBaseStationLongitude());
        }
    }

    private static void gqg9qq9gqq9q9q(List list, qgg9qgg9999g9g qgg9qgg9999g9g2) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                NeighboringCellInfo neighboringCellInfo = (NeighboringCellInfo) it.next();
                qgg9qgg9999g9g2.gqg9qq9gqq9q9q(0, neighboringCellInfo.getLac(), neighboringCellInfo.getCid(), neighboringCellInfo.getRssi());
            }
        }
    }

    @TargetApi(17)
    private static void qgg9qgg9999g9g(List list, qgg9qgg9999g9g qgg9qgg9999g9g2) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CellInfo cellInfo = (CellInfo) it.next();
                if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
                    CellSignalStrengthGsm cellSignalStrength = cellInfoGsm.getCellSignalStrength();
                    if (Build.VERSION.SDK_INT >= 24) {
                        qgg9qgg9999g9g2.gqg9qq9gqq9q9q(0, cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellIdentity.getArfcn(), cellIdentity.getBsic(), cellSignalStrength.getDbm());
                    } else {
                        qgg9qgg9999g9g2.gqg9qq9gqq9q9q(0, cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), 0, 0, cellSignalStrength.getDbm());
                    }
                } else if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
                    qgg9qgg9999g9g2.gqg9qq9gqq9q9q(1, cellIdentity2.getNetworkId(), cellIdentity2.getSystemId(), cellIdentity2.getBasestationId(), cellIdentity2.getLatitude(), cellIdentity2.getLongitude(), cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    if (cellInfoLte.isRegistered()) {
                        CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                        CellSignalStrengthLte cellSignalStrength2 = cellInfoLte.getCellSignalStrength();
                        if (Build.VERSION.SDK_INT >= 24) {
                            qgg9qgg9999g9g2.gqg9qq9gqq9q9q(2, cellIdentity3.getMnc(), cellIdentity3.getTac(), cellIdentity3.getCi(), cellIdentity3.getEarfcn(), cellIdentity3.getPci(), cellSignalStrength2.getDbm());
                        } else {
                            qgg9qgg9999g9g2.gqg9qq9gqq9q9q(2, cellIdentity3.getMnc(), cellIdentity3.getTac(), cellIdentity3.getCi(), 0, 0, cellSignalStrength2.getDbm());
                        }
                    }
                } else if (gqgqgqq9gq9q9q9.qgg9qgg9999g9g(18) && (cellInfo instanceof CellInfoWcdma)) {
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                    CellSignalStrengthWcdma cellSignalStrength3 = cellInfoWcdma.getCellSignalStrength();
                    if (Build.VERSION.SDK_INT >= 24) {
                        qgg9qgg9999g9g2.gqg9qq9gqq9q9q(3, cellIdentity4.getMnc(), cellIdentity4.getLac(), cellIdentity4.getCid(), cellIdentity4.getUarfcn(), cellIdentity4.getPsc(), cellSignalStrength3.getDbm());
                    } else {
                        qgg9qgg9999g9g2.gqg9qq9gqq9q9q(3, cellIdentity4.getMnc(), cellIdentity4.getLac(), cellIdentity4.getCid(), 0, 0, cellSignalStrength3.getDbm());
                    }
                }
            }
        }
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 114);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 50);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
