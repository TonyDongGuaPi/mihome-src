package com.miui.tsmclient.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.entity.CardConfigManager;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.InvalidTLVException;
import com.tsmclient.smartcard.exception.TagNotFoundException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class SysUtils {
    private static final byte STATE_ACTIVATED = 1;
    private static final byte STATE_DEACTIVITED = 0;
    private static final byte STATE_NON_ACTIVATABLE = Byte.MIN_VALUE;
    private static String sDefaultTransCardAid;
    private static String sImei;
    private static String sNfcId;

    private SysUtils() {
    }

    public static String getImei(Context context, CardInfo cardInfo) {
        return DeviceUtils.getImei(context, cardInfo);
    }

    public static boolean isESEEnabled(Context context, CardInfo cardInfo) {
        return getPeripheralInfo(cardInfo).isESEEnabled(context);
    }

    public static String getDefaultTransCard(Context context) {
        boolean isESEEnabled = isESEEnabled(context, (CardInfo) null);
        if (TextUtils.isEmpty(sDefaultTransCardAid) || !isESEEnabled) {
            sDefaultTransCardAid = PrefUtils.getDefaultCard(context, true);
            if (TextUtils.isEmpty(sDefaultTransCardAid)) {
                if (PrefUtils.getBoolean(context, PrefUtils.PREF_KEY_DEFAULT_TRANSCARD_CHECKED, false)) {
                    return sDefaultTransCardAid;
                }
                CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY, true);
                List<String> supportedTransCardAids = CardConfigManager.getInstance().getSupportedTransCardAids();
                boolean isEmpty = supportedTransCardAids.isEmpty();
                Iterator<String> it = supportedTransCardAids.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    try {
                        Map<String, ByteArray> cardActivationState = cardInfo.getTerminal().getCardActivationState(next);
                        if (cardActivationState != null) {
                            Iterator<Map.Entry<String, ByteArray>> it2 = cardActivationState.entrySet().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                Map.Entry next2 = it2.next();
                                ByteArray byteArray = (ByteArray) next2.getValue();
                                if (byteArray != null && byteArray.get(1) == 1) {
                                    sDefaultTransCardAid = (String) next2.getKey();
                                    PrefUtils.setDefaultCard(context, sDefaultTransCardAid, true);
                                    break;
                                }
                            }
                        } else {
                            LogUtils.d("getDefaultTransCard failed when read appAid:" + next);
                            isEmpty = true;
                            break;
                        }
                    } catch (InterruptedException e) {
                        LogUtils.e("failed to get default transit card", e);
                    }
                }
                if (!isEmpty || !TextUtils.isEmpty(sDefaultTransCardAid)) {
                    PrefUtils.putBoolean(context, PrefUtils.PREF_KEY_DEFAULT_TRANSCARD_CHECKED, true);
                } else {
                    PrefUtils.remove(context, PrefUtils.PREF_KEY_DEFAULT_TRANSCARD_CHECKED);
                }
            }
            if (!TextUtils.isEmpty(sDefaultTransCardAid) && isESEEnabled) {
                PrefUtils.putSecureSettings(context, PrefUtils.SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD, 1);
            }
            return sDefaultTransCardAid;
        }
        PrefUtils.putSecureSettings(context, PrefUtils.SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD, 1);
        return sDefaultTransCardAid;
    }

    public static void clearCardCache(Context context, CardInfo cardInfo) {
        CardInfoManager.getInstance(context).remove(cardInfo);
        boolean isTransCard = cardInfo.isTransCard();
        if (TextUtils.equals(cardInfo.mAid, PrefUtils.getDefaultCard(context, isTransCard))) {
            LogUtils.d("clear default card: " + cardInfo.mAid + " cache called!");
            PrefUtils.setDefaultCard(context, "", isTransCard);
            if (isTransCard) {
                sDefaultTransCardAid = null;
            }
        }
        PrefUtils.remove(context, String.format(PrefUtils.PREF_KEY_CARD_EXTRA, new Object[]{cardInfo.mCardType}));
    }

    public static boolean setDefaultTransCard(Context context, CardInfo cardInfo) {
        if (cardInfo == null || TextUtils.isEmpty(cardInfo.mAid)) {
            LogUtils.w("Aid of default card must not be empty");
            return false;
        }
        String defaultTransCard = getDefaultTransCard(context);
        if (cardInfo.mAid.equals(defaultTransCard)) {
            return true;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("aid", getAidFactor(cardInfo.mAid));
        AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_LAUNCH, new Object[]{"setDefaultCard"}), (Map<String, String>) hashMap);
        if (!NfcConfig.setConfig(context, cardInfo.mAid)) {
            AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_FAILED, new Object[]{"setDefaultCard"}), (Map<String, String>) hashMap);
            return false;
        }
        if (!TextUtils.isEmpty(defaultTransCard)) {
            boolean deactivateCard = cardInfo.getTerminal().deactivateCard(defaultTransCard);
            if (TextUtils.equals(PrefUtils.getString(context, PrefUtils.PREF_KEY_LAST_CARD, (String) null), defaultTransCard)) {
                PrefUtils.remove(context, PrefUtils.PREF_KEY_LAST_CARD);
            }
            LogUtils.d("deactivateCard appAid: " + defaultTransCard + " result: " + deactivateCard);
        }
        boolean activateCard = cardInfo.getTerminal().activateCard(cardInfo.mAid);
        LogUtils.d("activateCard appAid: " + cardInfo.mAid + " result: " + activateCard);
        if (!activateCard) {
            AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_FAILED, new Object[]{"setDefaultCard"}), (Map<String, String>) hashMap);
        } else {
            onActivateCardSuccess(context, cardInfo, true);
        }
        return activateCard;
    }

    public static boolean activateCard(Context context, CardInfo cardInfo) {
        if (cardInfo == null || TextUtils.isEmpty(cardInfo.mAid)) {
            LogUtils.w("Aid of activated card must not be empty");
            return false;
        }
        String mapAid = cardInfo.mapAid();
        LogUtils.d("activateCard appAid:" + mapAid);
        HashMap hashMap = new HashMap();
        hashMap.put("aid", getAidFactor(cardInfo.mAid));
        AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_LAUNCH, new Object[]{"activateCard"}), (Map<String, String>) hashMap);
        if (cardInfo.getTerminal().activateCard(mapAid)) {
            onActivateCardSuccess(context, cardInfo, false);
            LogUtils.d("activateCard appAid:" + mapAid + " success");
            AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_SUCCESS, new Object[]{"activateCard"}), (Map<String, String>) hashMap);
            return true;
        }
        AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_FAILED, new Object[]{"activateCard"}), (Map<String, String>) hashMap);
        return false;
    }

    public static boolean deactivateCard(Context context, CardInfo cardInfo) {
        if (cardInfo == null || TextUtils.isEmpty(cardInfo.mAid)) {
            LogUtils.w("Aid of deactivated card must not be empty");
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("aid", getAidFactor(cardInfo.mAid));
        AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_LAUNCH, new Object[]{"deactivateCard"}), (Map<String, String>) hashMap);
        if (cardInfo.getTerminal().deactivateCard(cardInfo.mapAid())) {
            if (cardInfo.isTransCard()) {
                sDefaultTransCardAid = null;
                PrefUtils.setDefaultCard(context, (String) null, true);
            } else {
                PrefUtils.setDefaultCard(context, (String) null, false);
            }
            LogUtils.d("deactivateCard appAid:" + cardInfo.mapAid() + " success");
            AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_SUCCESS, new Object[]{"deactivateCard"}), (Map<String, String>) hashMap);
            return true;
        }
        AnalyticManager.getInstance().recordEvent(AnalyticManager.CATEGORY_NFC, String.format(AnalyticManager.KEY_OPERATION_FAILED, new Object[]{"deactivateCard"}), (Map<String, String>) hashMap);
        return false;
    }

    public static final boolean isBankCardAid(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX));
    }

    public static Bundle getTransCardState(Context context) {
        String str;
        Bundle bundle = new Bundle();
        int issuedTransCardsCount = CardInfoManager.getInstance(context).getIssuedTransCardsCount();
        CardInfo defaultTransCard = CardInfoManager.getInstance(context).getDefaultTransCard();
        int i = 0;
        if (defaultTransCard != null) {
            CardInfoManager.getInstance(context).updateCards(defaultTransCard);
            if (defaultTransCard.mCardBalance != -999) {
                i = defaultTransCard.mCardBalance;
            }
            str = defaultTransCard.getCardType();
        } else {
            str = null;
        }
        bundle.putInt(MiTsmConstants.KEY_CARD_QUANTITY, issuedTransCardsCount);
        bundle.putString(MiTsmConstants.KEY_DEFAULT_CARD_TYPE, str);
        bundle.putInt(MiTsmConstants.KEY_DEFAULT_CARD_BALANCE, i);
        bundle.putInt(MiTsmConstants.KEY_EXTRA_INFO, 1);
        LogUtils.d("getTransCardState: " + bundle);
        return bundle;
    }

    public static Bundle getNFCCardState(Context context) {
        Bundle transCardState = getTransCardState(context);
        transCardState.putInt(MiTsmConstants.KEY_MIPAY_CARD_QUANTITY, CardInfoManager.getInstance(context).getBankCardsCount());
        transCardState.putInt(MiTsmConstants.KEY_MIFARE_CARD_QUANTITY, CardInfoManager.getInstance(context).getMifareCardsCount());
        LogUtils.d("getNFCCardState:" + transCardState);
        return transCardState;
    }

    public static int getTransCardCounts(Context context) throws IOException {
        int issuedTransCardsCount = CardInfoManager.getInstance(context).getIssuedTransCardsCount();
        LogUtils.d("getTransCardCounts: " + issuedTransCardsCount);
        return issuedTransCardsCount;
    }

    public static int getBankCardCounts(Context context) throws InvalidTLVException, IOException, TagNotFoundException {
        Set<String> seBankCards = getSeBankCards(context);
        if (seBankCards == null) {
            return 0;
        }
        return seBankCards.size();
    }

    public static Set<String> getSeBankCards(Context context) throws InvalidTLVException, IOException, TagNotFoundException {
        Map<String, ByteArray> map;
        try {
            map = CardInfoFactory.makeCardInfo("BANKCARD", (JSONObject) null).getTerminal().getCardActivationState(Coder.bytesToHexString(APDUConstants.PBOC_CARD_AID_PREFFIX));
        } catch (InterruptedException unused) {
            map = null;
        }
        if (map != null) {
            return map.keySet();
        }
        return null;
    }

    public static Bundle getSignedSpiPK(Context context) {
        try {
            String signedSpiPK = CardInfoFactory.makeCardInfo("BANKCARD", (JSONObject) null).getTerminal().getSignedSpiPK();
            if (signedSpiPK != null) {
                LogUtils.d("signedPK : " + signedSpiPK);
                String[] split = signedSpiPK.split(a.b);
                HashMap hashMap = new HashMap();
                for (int i = 0; i < split.length; i++) {
                    int indexOf = split[i].indexOf(61);
                    if (indexOf != -1 && indexOf < split[i].length() - 1) {
                        hashMap.put(split[i].substring(0, indexOf), split[i].substring(indexOf + 1, split[i].length()));
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString(Constants.KEY_CPU_MODEL, (String) hashMap.get(Constants.KEY_CPU_MODEL));
                bundle.putString("deviceModel", (String) hashMap.get("deviceModel"));
                bundle.putString(Constants.KEY_KEY_ALG, (String) hashMap.get(Constants.KEY_KEY_ALG));
                bundle.putString(Constants.KEY_PKX, (String) hashMap.get(Constants.KEY_PKX));
                bundle.putString(Constants.KEY_PKY, (String) hashMap.get(Constants.KEY_PKY));
                bundle.putString(Constants.KEY_TZID, (String) hashMap.get(Constants.KEY_TZID));
                bundle.putString("sign", (String) hashMap.get("sign"));
                return bundle;
            }
        } catch (IOException e) {
            LogUtils.e("getSignedSpiPk error", e);
        } catch (InterruptedException e2) {
            LogUtils.e("getSignedSpiPk is interrupted.", e2);
        }
        return null;
    }

    public static String getMIUIRomType(CardInfo cardInfo) {
        return DeviceUtils.getMIUIRomType(cardInfo);
    }

    public static PackageInfo getAppInfo(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
            if (TextUtils.equals(str, next.packageName)) {
                return next;
            }
        }
        return null;
    }

    public static boolean isForegroundApp(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(ActivityManager.class)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == myPid && TextUtils.equals(next.processName, context.getPackageName()) && next.importance == 100) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getSIMNumber(CardInfo cardInfo) {
        return DeviceUtils.getSIMNumber(cardInfo);
    }

    public static String getDeviceModel(CardInfo cardInfo) {
        return DeviceUtils.getDeviceModel(cardInfo);
    }

    public static String getRomVersion() {
        return DeviceUtils.getRomVersion();
    }

    public static String getAndroidVersion() {
        return DeviceUtils.getAndroidVersion();
    }

    public static String getAppVersionName(Context context) {
        return DeviceUtils.getAppVersionName(context);
    }

    public static int getAppVersionCode(Context context) {
        return DeviceUtils.getAppVersionCode(context);
    }

    public static String getDeviceLanguage6393() {
        return DeviceUtils.getDeviceLanguage6393();
    }

    public static boolean checkMD5(String str, File file) {
        boolean z;
        if (TextUtils.isEmpty(str) || file == null || !file.exists()) {
            return false;
        }
        try {
            String calculateMD5 = calculateMD5(file);
            if (calculateMD5 == null) {
                return false;
            }
            z = calculateMD5.equalsIgnoreCase(str);
            if (z) {
                return z;
            }
            try {
                LogUtils.d("file md5:" + calculateMD5 + " is different with " + str);
                return z;
            } catch (RuntimeException e) {
                e = e;
            }
        } catch (RuntimeException e2) {
            e = e2;
            z = false;
            LogUtils.e("checkMD5 error occurred.", e);
            return z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0049, code lost:
        throw new java.lang.RuntimeException("Unable to process file for MD5", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0041, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0041 A[ExcHandler: IOException (r6v2 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:1:0x0003] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String calculateMD5(java.io.File r6) throws java.lang.RuntimeException {
        /*
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r2.<init>(r6)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r6 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r6]     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
        L_0x0010:
            int r3 = r2.read(r6)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r4 = 0
            if (r3 <= 0) goto L_0x001b
            r1.update(r6, r4, r3)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            goto L_0x0010
        L_0x001b:
            byte[] r6 = r1.digest()     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            java.math.BigInteger r1 = new java.math.BigInteger     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r3 = 1
            r1.<init>(r3, r6)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            java.lang.String r6 = "%32s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r5 = 16
            java.lang.String r1 = r1.toString(r5)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r3[r4] = r1     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            java.lang.String r6 = java.lang.String.format(r6, r3)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r1 = 32
            r3 = 48
            java.lang.String r6 = r6.replace(r1, r3)     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004a, IOException -> 0x0041 }
            r2.close()     // Catch:{ FileNotFoundException | NoSuchAlgorithmException -> 0x004b, IOException -> 0x0041 }
            goto L_0x004b
        L_0x0041:
            r6 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Unable to process file for MD5"
            r0.<init>(r1, r6)
            throw r0
        L_0x004a:
            r6 = r0
        L_0x004b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.util.SysUtils.calculateMD5(java.io.File):java.lang.String");
    }

    public static String getNfcId(Context context) {
        LogUtils.d("read nfcId from cache");
        if (TextUtils.isEmpty(sNfcId)) {
            LogUtils.d("read nfcId from pref");
            sNfcId = PrefUtils.getString(context, PrefUtils.PREF_KEY_NFC_ID, "");
            if (TextUtils.isEmpty(sNfcId)) {
                LogUtils.d("read nfcId");
                byte[] nfccDieid = getNfccDieid(NfcAdapter.getDefaultAdapter(context));
                if (nfccDieid != null) {
                    sNfcId = Coder.bytesToHexString(nfccDieid);
                    if ("00000000000000000000000000000000".equals(sNfcId)) {
                        sNfcId = null;
                    }
                    PrefUtils.putString(context, PrefUtils.PREF_KEY_NFC_ID, sNfcId);
                }
            }
        }
        return sNfcId;
    }

    public static String getAidFactor(String str) {
        return (str == null || str.length() < 3 || !isBankCardAid(str)) ? str : str.substring(0, str.length() - 3);
    }

    public static String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e("md5 error", e);
            return null;
        }
    }

    private static byte[] getNfccDieid(NfcAdapter nfcAdapter) {
        if (nfcAdapter == null) {
            return null;
        }
        try {
            Method method = nfcAdapter.getClass().getMethod("getNfccDieid", new Class[0]);
            method.setAccessible(true);
            return (byte[]) method.invoke(nfcAdapter, new Object[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static IDeviceInfo getPeripheralInfo(CardInfo cardInfo) {
        if (cardInfo == null) {
            cardInfo = CardInfoFactory.makeCardInfo(CardInfo.CARD_TYPE_DUMMY, (JSONObject) null);
        }
        return cardInfo.getDeviceInfo();
    }

    private static void onActivateCardSuccess(Context context, CardInfo cardInfo, boolean z) {
        if (cardInfo.isTransCard()) {
            sDefaultTransCardAid = cardInfo.mAid;
            PrefUtils.setDefaultCard(context, sDefaultTransCardAid, true);
            if (z) {
                PrefUtils.putSecureSettings(context, PrefUtils.SETTINGS_SYSTEM_PREF_KEY_TRANS_CARD, 1);
                return;
            }
            return;
        }
        PrefUtils.setDefaultCard(context, cardInfo.mAid, false);
    }
}
