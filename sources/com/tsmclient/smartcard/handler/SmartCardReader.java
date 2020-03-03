package com.tsmclient.smartcard.handler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.handler.config.ConfigCardHandler;
import com.tsmclient.smartcard.model.ConfigRules;
import com.tsmclient.smartcard.terminal.IScTerminal;
import java.io.IOException;

public class SmartCardReader extends TagReader {
    public SmartCardReader(Activity activity) {
        this(activity, (Handler) null, (Handler) null);
    }

    public SmartCardReader(Activity activity, Handler handler, Handler handler2) {
        super(activity, handler, handler2);
    }

    public static Bundle readCard(IScTerminal iScTerminal, String str, Context context) {
        return readCard(iScTerminal, str, context, new Bundle());
    }

    public static Bundle readCard(IScTerminal iScTerminal, String str, Context context, Bundle bundle) {
        try {
            Bundle doReadCard = doReadCard(iScTerminal, str, context, bundle);
            Log.d("SmartCardReader", "readCard:" + str + " success.");
            return doReadCard;
        } catch (IOException e) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("success", false);
            bundle2.putInt("error", 1);
            Log.e("SmartCardReader", "readCard:" + str + " occurred IOException.", e);
            return bundle2;
        } catch (UnProcessableCardException e2) {
            Bundle bundle3 = new Bundle();
            bundle3.putBoolean("success", false);
            bundle3.putInt("error", 2);
            Log.e("SmartCardReader", "readCard:" + str + " occurred UnProcessableCardException.", e2);
            return bundle3;
        } catch (Exception e3) {
            Bundle bundle4 = new Bundle();
            bundle4.putBoolean("success", false);
            bundle4.putInt("error", 3);
            Log.e("SmartCardReader", "readCard:" + str + " occurred Exception.", e3);
            return bundle4;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0023, code lost:
        throw new java.io.IOException("read card is interrupted");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.os.Bundle doReadCard(com.tsmclient.smartcard.terminal.IScTerminal r0, java.lang.String r1, android.content.Context r2, android.os.Bundle r3) throws java.io.IOException, com.tsmclient.smartcard.exception.UnProcessableCardException {
        /*
            if (r0 == 0) goto L_0x0030
            com.tsmclient.smartcard.handler.ISmartCardHandler r1 = getHandler(r2, r1, r3)
            if (r1 == 0) goto L_0x0028
            r0.open()     // Catch:{ InterruptedException -> 0x0015 }
            android.os.Bundle r1 = r1.onHandleCard(r0, r3)     // Catch:{ InterruptedException -> 0x0015 }
            r0.close()
            return r1
        L_0x0013:
            r1 = move-exception
            goto L_0x0024
        L_0x0015:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0013 }
            r1.interrupt()     // Catch:{ all -> 0x0013 }
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0013 }
            java.lang.String r2 = "read card is interrupted"
            r1.<init>(r2)     // Catch:{ all -> 0x0013 }
            throw r1     // Catch:{ all -> 0x0013 }
        L_0x0024:
            r0.close()
            throw r1
        L_0x0028:
            com.tsmclient.smartcard.exception.UnProcessableCardException r0 = new com.tsmclient.smartcard.exception.UnProcessableCardException
            java.lang.String r1 = "No matched handler"
            r0.<init>(r1)
            throw r0
        L_0x0030:
            com.tsmclient.smartcard.exception.UnProcessableCardException r0 = new com.tsmclient.smartcard.exception.UnProcessableCardException
            java.lang.String r1 = "IScTerminal is null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.SmartCardReader.doReadCard(com.tsmclient.smartcard.terminal.IScTerminal, java.lang.String, android.content.Context, android.os.Bundle):android.os.Bundle");
    }

    private static ISmartCardHandler getHandler(Context context, String str, Bundle bundle) {
        if (bundle != null && bundle.containsKey(ISmartCardHandler.KEY_READ_CARD_OPTION_RULES)) {
            return new ConfigCardHandler(str, (ConfigRules) bundle.getParcelable(ISmartCardHandler.KEY_READ_CARD_OPTION_RULES));
        }
        if (TextUtils.equals(str, "SZT")) {
            return new SZTCardHandler();
        }
        if (TextUtils.equals(str, "SPTC") || TextUtils.equals(str, "SPTC_NEW")) {
            return new CityUCardHandler(str);
        }
        if (TextUtils.equals(str, "BMAC")) {
            return new BMACCardHandler();
        }
        if (TextUtils.equals(str, "LNT")) {
            return new LingNanCardHandler();
        }
        if (TextUtils.equals(str, CardConstants.BANK_CARD_ID)) {
            return new BankCardHandler();
        }
        if (TextUtils.equals(str, CardConstants.SUZHOUTONG)) {
            return new SuZhouTongCardHandler();
        }
        if (TextUtils.equals(str, CardConstants.WHT)) {
            return new WHTCardHandler();
        }
        if (TextUtils.equals(str, CardConstants.HZT)) {
            return new HZTCardHandler();
        }
        if (TextUtils.equals(str, CardConstants.CST)) {
            return new CSTCardHandler();
        }
        return null;
    }
}
