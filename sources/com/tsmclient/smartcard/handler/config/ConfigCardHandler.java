package com.tsmclient.smartcard.handler.config;

import android.text.TextUtils;
import android.util.Log;
import com.miui.tsmclient.util.StringUtils;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.exception.CardStatusException;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.handler.BaseTransCardHandler;
import com.tsmclient.smartcard.model.ConfigRules;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ConfigCardHandler extends BaseTransCardHandler {
    private static final int CONFIG_CARD_ENGINE_VERSION = 2;
    private static final String TAG = "ConfigCardHandler";
    private String mCardType;
    private Map<String, ScResponse> mCommandResult = new HashMap();
    private ConfigRules mConfigRules;

    public ConfigCardHandler(String str, ConfigRules configRules) {
        this.mCardType = str;
        this.mConfigRules = configRules;
    }

    /* access modifiers changed from: protected */
    public String getCardType() {
        return this.mCardType;
    }

    /* access modifiers changed from: protected */
    public void selectVerify() throws IOException, UnProcessableCardException {
        if (this.mConfigRules != null) {
            Log.d(TAG, "selectVerify card:" + this.mCardType + ", version:" + this.mConfigRules.mVersion + ", engine:" + 2);
            executeCommands(this.mConfigRules.mSelectVerifyCommandList);
            return;
        }
        throw new IOException("config rules of " + this.mCardType + " can't be null");
    }

    /* access modifiers changed from: protected */
    public void otherVerify() throws IOException, UnProcessableCardException {
        executeCommands(this.mConfigRules.mOtherVerifyCommandList);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d A[SYNTHETIC, Splitter:B:18:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getBalance() throws java.io.IOException, com.tsmclient.smartcard.exception.UnProcessableCardException {
        /*
            r6 = this;
            com.tsmclient.smartcard.model.ConfigRules r0 = r6.mConfigRules
            java.util.List<com.tsmclient.smartcard.model.ConfigRules$ParseDataCommand> r0 = r0.mReadBalanceCommandList
            if (r0 == 0) goto L_0x008f
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x000e
            goto L_0x008f
        L_0x000e:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r6.executeCommandsWithElements(r0, r1)     // Catch:{ CardStatusException -> 0x0017 }
            goto L_0x001f
        L_0x0017:
            r0 = move-exception
            java.lang.String r2 = "ConfigCardHandler"
            java.lang.String r3 = "CardStatusException occurred on getBalance."
            android.util.Log.e(r2, r3, r0)
        L_0x001f:
            java.lang.String r0 = "e_balance"
            java.lang.Object r0 = r1.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            r3 = 0
            if (r2 != 0) goto L_0x004e
            int r0 = com.tsmclient.smartcard.Coder.hexStringToInt(r0)     // Catch:{ Exception -> 0x0033 }
            goto L_0x004f
        L_0x0033:
            r0 = move-exception
            java.lang.String r2 = "ConfigCardHandler"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getBalance failed: "
            r4.append(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            android.util.Log.d(r2, r0)
        L_0x004e:
            r0 = 0
        L_0x004f:
            java.lang.String r2 = "overdrawn"
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x008a
            int r1 = com.tsmclient.smartcard.Coder.hexStringToInt(r1)     // Catch:{ Exception -> 0x006f }
            java.lang.String r2 = "overdrawn"
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ Exception -> 0x006c }
            r6.updateCardInfo(r2, r3)     // Catch:{ Exception -> 0x006c }
            r3 = r1
            goto L_0x008a
        L_0x006c:
            r2 = move-exception
            r3 = r1
            goto L_0x0070
        L_0x006f:
            r2 = move-exception
        L_0x0070:
            java.lang.String r1 = "ConfigCardHandler"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getOverdraw failed: "
            r4.append(r5)
            java.lang.String r2 = r2.getMessage()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            android.util.Log.d(r1, r2)
        L_0x008a:
            if (r3 >= 0) goto L_0x008d
            goto L_0x008e
        L_0x008d:
            int r0 = r0 - r3
        L_0x008e:
            return r0
        L_0x008f:
            int r0 = super.getBalance()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.handler.config.ConfigCardHandler.getBalance():int");
    }

    /* access modifiers changed from: protected */
    public void readRecord(ArrayList<TradeLog> arrayList, boolean z) throws IOException {
        List<ConfigRules.ReadRecordCommand> readRecordCommandList = this.mConfigRules.getReadRecordCommandList();
        if (readRecordCommandList.isEmpty()) {
            super.readRecord(arrayList, z);
            return;
        }
        try {
            for (ConfigRules.ReadRecordCommand next : readRecordCommandList) {
                if (next.isExecute(this.mInternalRead)) {
                    executeCommands(next.mPreReadCommandList);
                    if (next.isReadRecordInstructionListEmpty()) {
                        super.readRecord(arrayList, z);
                    } else {
                        for (ConfigRules.ReadRecordInstruction next2 : next.getReadRecordInstructionList()) {
                            readRecord(arrayList, next2.mSkipOnceRead, next2.getP2(), next2.getSfi(), next2.isSkipParsingYear());
                        }
                    }
                }
            }
            Collections.sort(arrayList);
        } catch (UnProcessableCardException unused) {
            throw new IOException("failed to get record");
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException {
        HashMap hashMap = new HashMap();
        try {
            executeCommandsWithElements(this.mConfigRules.mCardNumValidDateCommandList, hashMap);
        } catch (CardStatusException e) {
            Log.e(TAG, "CardStatusException occurred on getCardNumAndValidDate.", e);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringUtils.SOURCE_DATE_FORMAT, Locale.getDefault());
        Date date = new Date();
        simpleDateFormat.setLenient(false);
        String str = (String) hashMap.get(CardConstants.VALID_START);
        if (!TextUtils.isEmpty(str)) {
            try {
                hashMap.put(CardConstants.STATUS_VALID_START_DATE, String.valueOf(!date.before(simpleDateFormat.parse(str))));
            } catch (ParseException e2) {
                hashMap.put(CardConstants.STATUS_VALID_START_DATE, Boolean.FALSE.toString());
                Log.e(TAG, "parse start date failed.", e2);
            }
        }
        String str2 = (String) hashMap.get(CardConstants.VALID_END);
        if (!TextUtils.isEmpty(str2)) {
            try {
                Date parse = simpleDateFormat.parse(str2);
                Calendar instance = Calendar.getInstance();
                instance.setTime(parse);
                instance.add(5, 1);
                hashMap.put(CardConstants.STATUS_VALID_END_DATE, String.valueOf(!date.after(instance.getTime())));
            } catch (ParseException e3) {
                hashMap.put(CardConstants.STATUS_VALID_END_DATE, Boolean.FALSE.toString());
                Log.e(TAG, "parse end date failed.", e3);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void readCardStatus(Map<String, String> map) throws IOException, UnProcessableCardException, CardStatusException {
        super.readCardStatus(map);
        executeCommandsWithElements(this.mConfigRules.mReadCardStatusCommandList, map);
    }

    private void mapValue(String str, String str2, Map<String, String> map, Map<String, String> map2) {
        if (map != null && !map.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (Pattern.compile((String) next.getKey()).matcher(str2).matches()) {
                    str2 = (String) next.getValue();
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(str2)) {
            map2.remove(str);
        } else {
            map2.put(str, str2);
        }
    }

    private void executeCommandsWithElements(List<ConfigRules.ParseDataCommand> list, Map<String, String> map) throws IOException, UnProcessableCardException, CardStatusException {
        ScResponse executeCommand;
        if (list != null) {
            for (ConfigRules.ParseDataCommand next : list) {
                if (next.isExecute(this.mInternalRead) && (executeCommand = executeCommand(next)) != null) {
                    if (!TextUtils.isEmpty(next.mResponseKey)) {
                        mapValue(next.mResponseKey, executeCommand.getStatus().toString(), next.mResponseMap, map);
                        if (!TextUtils.isEmpty(next.mExpectResponse)) {
                            Log.d(TAG, "responseKey: " + next.mResponseKey + ", expect: " + next.mExpectResponse);
                            assertPattern(next.mExpectResponse, map.get(next.mResponseKey), next.mResponseExceptionType);
                        }
                    }
                    if (next.mElementList == null) {
                        continue;
                    } else {
                        ByteArray data = executeCommand.getData();
                        int length = data.length();
                        for (ConfigRules.Element next2 : next.mElementList) {
                            if (next2.mOffset + next2.mLength <= length) {
                                String bytesToHexString = Coder.bytesToHexString(data.duplicate(next2.mOffset, next2.mLength).toBytes());
                                if (next2.mHexOffset >= 0) {
                                    if (bytesToHexString == null || bytesToHexString.length() < next2.mHexOffset + next2.mHexLength) {
                                        throw new UnProcessableCardException(this.mCardType + ": unsupported card type. Caused by HexOffset of " + next2.mKey);
                                    }
                                    bytesToHexString = bytesToHexString.substring(next2.mHexOffset, next2.mHexOffset + next2.mHexLength);
                                }
                                StringBuilder sb = new StringBuilder();
                                if (!TextUtils.isEmpty(next2.mPrefixHex)) {
                                    sb.append(next2.mPrefixHex);
                                }
                                if (bytesToHexString == null) {
                                    bytesToHexString = "";
                                }
                                sb.append(bytesToHexString);
                                if (!TextUtils.isEmpty(next2.mSuffixHex)) {
                                    sb.append(next2.mSuffixHex);
                                }
                                mapValue(next2.mKey, sb.toString(), next2.mValueMap, map);
                                if (!TextUtils.isEmpty(next2.mExpectValue)) {
                                    Log.d(TAG, "element: " + next2.mKey + ", expect: " + next2.mExpectValue);
                                    assertPattern(next2.mExpectValue, map.get(next2.mKey), next2.mValueExceptionType);
                                }
                            } else {
                                throw new UnProcessableCardException(this.mCardType + ": unsupported card type. Caused by Offset of " + next2.mKey);
                            }
                        }
                        continue;
                    }
                }
            }
        }
    }

    private void executeCommands(List<ConfigRules.Command> list) throws IOException, UnProcessableCardException {
        if (list != null) {
            for (ConfigRules.Command next : list) {
                if (next.isExecute(this.mInternalRead)) {
                    executeCommand(next);
                }
            }
        }
    }

    private ScResponse executeCommand(ConfigRules.Command command) throws IOException, UnProcessableCardException {
        String str;
        String str2;
        if (command == null || TextUtils.isEmpty(command.mApdu) || 2 < command.mMinVersion) {
            return null;
        }
        if (command.mMaxVersion != -1 && 2 > command.mMaxVersion) {
            return null;
        }
        if (!TextUtils.isEmpty(command.mPreConditionKey)) {
            ScResponse scResponse = this.mCommandResult.get(command.mPreConditionKey);
            try {
                String str3 = command.mPreConditionExpectStatus;
                if (scResponse == null) {
                    str = "";
                } else {
                    str = scResponse.getStatus().toString();
                }
                assertPattern(str3, str);
                String str4 = command.mPreConditionExpectData;
                if (scResponse == null) {
                    str2 = "";
                } else {
                    str2 = scResponse.getData().toString();
                }
                assertPattern(str4, str2);
            } catch (UnProcessableCardException e) {
                Log.d(TAG, "precondition failed: " + e.getMessage());
                return null;
            }
        }
        Log.d(TAG, "executeCommand send: " + command.mApdu);
        ResponseImpl responseImpl = new ResponseImpl(transceive(Coder.hexStringToBytes(command.mApdu)));
        Log.d(TAG, "executeCommand receive: " + responseImpl.toString());
        this.mCommandResult.put(command.mApdu, responseImpl);
        assertPattern(command.mExpectStatus, responseImpl.getStatus().toString());
        assertPattern(command.mExpectData, responseImpl.getData().toString());
        return responseImpl;
    }

    private void assertPattern(String str, String str2, int i) throws UnProcessableCardException, CardStatusException {
        if (!TextUtils.isEmpty(str) && !Pattern.compile(str).matcher(str2).matches()) {
            Log.d(TAG, "assertPattern expect: " + str + ", but: " + str2 + ", throw: " + i);
            if (i == 1) {
                throw new CardStatusException(this.mCardType + " status is abnormal. Caused by " + str2 + " not matching " + str);
            }
            throw new UnProcessableCardException(this.mCardType + ": unsupported card type. Caused by " + str2 + " not matching " + str);
        }
    }

    private void assertPattern(String str, String str2) throws UnProcessableCardException {
        try {
            assertPattern(str, str2, 0);
        } catch (CardStatusException e) {
            Log.e(TAG, "CardStatusException occurred", e);
        }
    }
}
