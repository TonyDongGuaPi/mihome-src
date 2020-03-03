package com.miui.tsmclient.entity;

import android.text.TextUtils;
import com.tsmclient.smartcard.terminal.IScTerminal;
import com.tsmclient.smartcard.terminal.TerminalManager;
import com.tsmclient.smartcard.terminal.TerminalType;

public class CardExtra {
    public IScTerminal getTerminal(CardInfo cardInfo) {
        TerminalType terminalType;
        if (TextUtils.isEmpty(cardInfo.mCardDevice) || TextUtils.equals(cardInfo.mCardDevice, "com.miui.tsmclient")) {
            terminalType = cardInfo.isSecure() ? TerminalType.SPI : TerminalType.I2C;
        } else {
            terminalType = TerminalType.PERIPHERAL;
        }
        return TerminalManager.getInstance().getTerminal(cardInfo.mCardDevice, terminalType);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.miui.tsmclient.entity.CardExtraInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateExtraInfo(android.content.Context r6, com.miui.tsmclient.entity.CardInfo r7) {
        /*
            r5 = this;
            if (r7 != 0) goto L_0x0003
            return
        L_0x0003:
            r7.updateCardFromExtra()
            if (r6 == 0) goto L_0x00c8
            boolean r0 = r7.mHasIssue
            if (r0 == 0) goto L_0x00c8
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            if (r0 != r1) goto L_0x0018
            goto L_0x00c8
        L_0x0018:
            com.miui.tsmclient.entity.CardConfigManager r0 = com.miui.tsmclient.entity.CardConfigManager.getInstance()
            java.lang.String r1 = r7.mCardType
            com.miui.tsmclient.entity.CardConfigManager$CardConfig r0 = r0.getCardConfigByType(r1)
            if (r0 == 0) goto L_0x00c7
            boolean r0 = r0.isNeedExtraInfo()
            if (r0 != 0) goto L_0x002c
            goto L_0x00c7
        L_0x002c:
            android.content.Context r6 = r6.getApplicationContext()
            java.lang.String r0 = "key_card_extra_%1$s"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = r7.mCardType
            r1[r2] = r3
            java.lang.String r0 = java.lang.String.format(r0, r1)
            r1 = 0
            java.lang.String r2 = com.miui.tsmclient.util.PrefUtils.getString(r6, r0, r1)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x008f
            com.miui.tsmclient.net.request.CardExtraInfoRequest r3 = new com.miui.tsmclient.net.request.CardExtraInfoRequest     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            com.tsmclient.smartcard.terminal.IScTerminal r4 = r5.getTerminal(r7)     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            java.lang.String r4 = r4.getCPLC()     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            r3.<init>(r7, r4)     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            com.miui.tsmclient.common.net.HttpClient r4 = com.miui.tsmclient.common.net.HttpClient.getInstance(r6)     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            com.miui.tsmclient.common.net.Response r4 = r4.execute(r3)     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            java.lang.Object r4 = r4.getResult()     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            com.miui.tsmclient.entity.CardExtraInfo r4 = (com.miui.tsmclient.entity.CardExtraInfo) r4     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            boolean r3 = r3.isSuccess()     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            if (r3 == 0) goto L_0x008f
            java.lang.String r3 = r4.toString()     // Catch:{ IOException -> 0x0088, InterruptedException -> 0x0079 }
            com.miui.tsmclient.util.PrefUtils.putString(r6, r0, r3)     // Catch:{ IOException -> 0x0076, InterruptedException -> 0x0073 }
            r2 = r3
            goto L_0x008f
        L_0x0073:
            r6 = move-exception
            r2 = r3
            goto L_0x007a
        L_0x0076:
            r6 = move-exception
            r2 = r3
            goto L_0x0089
        L_0x0079:
            r6 = move-exception
        L_0x007a:
            java.lang.String r0 = "updateExtraInfo failed"
            com.miui.tsmclient.util.LogUtils.e(r0, r6)
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
            goto L_0x008f
        L_0x0088:
            r6 = move-exception
        L_0x0089:
            java.lang.String r0 = "updateExtraInfo failed"
            com.miui.tsmclient.util.LogUtils.e(r0, r6)
        L_0x008f:
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 != 0) goto L_0x00a3
            com.google.gson.Gson r6 = new com.google.gson.Gson
            r6.<init>()
            java.lang.Class<com.miui.tsmclient.entity.CardExtraInfo> r0 = com.miui.tsmclient.entity.CardExtraInfo.class
            java.lang.Object r6 = r6.fromJson((java.lang.String) r2, r0)
            r1 = r6
            com.miui.tsmclient.entity.CardExtraInfo r1 = (com.miui.tsmclient.entity.CardExtraInfo) r1
        L_0x00a3:
            if (r1 != 0) goto L_0x00a6
            return
        L_0x00a6:
            java.lang.String r6 = r1.getCardNumber()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x00b6
            java.lang.String r6 = r1.getCardNumber()
            r7.mCardNo = r6
        L_0x00b6:
            java.lang.String r6 = r1.getRealCardNumber()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x00c6
            java.lang.String r6 = r1.getRealCardNumber()
            r7.mRealCardNo = r6
        L_0x00c6:
            return
        L_0x00c7:
            return
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.entity.CardExtra.updateExtraInfo(android.content.Context, com.miui.tsmclient.entity.CardInfo):void");
    }
}
