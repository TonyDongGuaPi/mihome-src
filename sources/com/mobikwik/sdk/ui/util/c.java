package com.mobikwik.sdk.ui.util;

import android.content.BroadcastReceiver;

public class c extends BroadcastReceiver {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r4, android.content.Intent r5) {
        /*
            r3 = this;
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            android.os.Bundle r5 = r5.getExtras()     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = "pdus"
            java.lang.Object r5 = r5.get(r2)     // Catch:{ Exception -> 0x0028 }
            java.lang.Object[] r5 = (java.lang.Object[]) r5     // Catch:{ Exception -> 0x0028 }
            java.lang.Object[] r5 = (java.lang.Object[]) r5     // Catch:{ Exception -> 0x0028 }
            r2 = 0
            r5 = r5[r2]     // Catch:{ Exception -> 0x0028 }
            byte[] r5 = (byte[]) r5     // Catch:{ Exception -> 0x0028 }
            byte[] r5 = (byte[]) r5     // Catch:{ Exception -> 0x0028 }
            android.telephony.SmsMessage r5 = android.telephony.SmsMessage.createFromPdu(r5)     // Catch:{ Exception -> 0x0028 }
            java.lang.String r2 = r5.getMessageBody()     // Catch:{ Exception -> 0x0028 }
            java.lang.String r5 = r5.getOriginatingAddress()     // Catch:{ Exception -> 0x0026 }
            goto L_0x002e
        L_0x0026:
            r5 = move-exception
            goto L_0x002a
        L_0x0028:
            r5 = move-exception
            r2 = r0
        L_0x002a:
            r5.printStackTrace()
            r5 = r1
        L_0x002e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SMS SENDER > "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SMS BODY   > "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r0)
            boolean r0 = com.mobikwik.sdk.lib.utils.Utils.isNull(r5)
            if (r0 != 0) goto L_0x00c0
            boolean r0 = com.mobikwik.sdk.lib.utils.Utils.isNull(r2)
            if (r0 == 0) goto L_0x0063
            goto L_0x00c0
        L_0x0063:
            java.lang.String r0 = "[a-zA-Z]{2,2}-MOBIKW"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r5 = r0.matcher(r5)
            boolean r5 = r5.matches()
            if (r5 != 0) goto L_0x0079
            java.lang.String r4 = "SMS sender not matched "
        L_0x0075:
            com.mobikwik.sdk.lib.utils.Utils.print(r4)
            return
        L_0x0079:
            java.lang.String r5 = "One Time Password (OTP) for your Mobikwik wallet is: "
            boolean r5 = r2.contains(r5)
            if (r5 == 0) goto L_0x00bf
            java.lang.String r5 = "One Time Password (OTP) for your Mobikwik wallet is: "
            int r5 = r2.indexOf(r5)
            java.lang.String r0 = "One Time Password (OTP) for your Mobikwik wallet is: "
            int r0 = r0.length()
            int r5 = r5 + r0
            java.lang.String r0 = "."
            int r0 = r2.indexOf(r0, r5)
            java.lang.String r5 = r2.substring(r5, r0)
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "AUTO_OTP_FILTER"
            r0.<init>(r1)
            java.lang.String r1 = "otpValue"
            r0.putExtra(r1, r5)
            android.support.v4.content.LocalBroadcastManager r4 = android.support.v4.content.LocalBroadcastManager.getInstance(r4)
            r4.sendBroadcast(r0)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "SMS OTP > "
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r4)
        L_0x00bf:
            return
        L_0x00c0:
            java.lang.String r4 = "Incomplete DATA !!!"
            goto L_0x0075
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.ui.util.c.onReceive(android.content.Context, android.content.Intent):void");
    }
}
