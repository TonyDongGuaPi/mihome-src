package com.miui.tsmclient.entity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.common.net.HttpClient;
import com.miui.tsmclient.net.request.ConfigRulesRequest;
import com.miui.tsmclient.util.EnvironmentConfig;
import com.miui.tsmclient.util.IOUtils;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ObjectUtils;
import com.tsmclient.smartcard.handler.ISmartCardHandler;
import com.tsmclient.smartcard.model.ConfigRules;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class CardConfigManager {
    public static final boolean STAGING = EnvironmentConfig.isStaging();
    private static volatile CardConfigManager sInstance;
    private Context mContext;
    private int mFetchConfigCount;
    private ReentrantLock mLock = new ReentrantLock();
    private Map<String, CardConfig> mSupportedTransCardMap;

    private CardConfigManager() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public static CardConfigManager getInstance() {
        if (sInstance == null) {
            synchronized (CardConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new CardConfigManager();
                }
            }
        }
        return sInstance;
    }

    public List<String> getSupportedTransCardAids() {
        ArrayList arrayList = new ArrayList(getSupportedTransCardMap().keySet());
        LogUtils.d("getSupportedTransCardAids:" + Arrays.toString(arrayList.toArray()));
        return arrayList;
    }

    public List<String> getSupportedTransCardTypes() {
        Map<String, CardConfig> supportedTransCardMap = getSupportedTransCardMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, CardConfig> value : supportedTransCardMap.entrySet()) {
            String access$000 = ((CardConfig) value.getValue()).mCardType;
            if (!TextUtils.isEmpty(access$000)) {
                arrayList.add(access$000);
            }
        }
        LogUtils.d("getSupportedTransCardTypes:" + Arrays.toString(arrayList.toArray()));
        return arrayList;
    }

    public Map<String, CardConfig> getSupportedTransCardMap() {
        return getSupportedTransCardMap(false);
    }

    public Map<String, CardConfig> getSupportedTransCardMap(boolean z) {
        boolean isMainThread = isMainThread();
        if (!isMainThread) {
            this.mLock.lock();
        }
        try {
            Map<String, CardConfig> map = this.mSupportedTransCardMap;
            if (z || ObjectUtils.isCollectionEmpty((Map<?, ?>) map)) {
                map = fetchCardConfig();
            }
            if (map == null) {
                return Collections.emptyMap();
            }
            if (!isMainThread) {
                this.mSupportedTransCardMap = map;
            }
            if (!isMainThread) {
                this.mLock.unlock();
            }
            return map;
        } finally {
            if (!isMainThread) {
                this.mLock.unlock();
            }
        }
    }

    public String getCardType(String str) {
        CardConfig cardConfig = getCardConfig(str);
        if (cardConfig == null) {
            return null;
        }
        return cardConfig.getCardType();
    }

    public CardConfig.OperationType getOperationType(String str) {
        CardConfig.OperationType operationType;
        CardConfig cardConfigByType = getCardConfigByType(str);
        StringBuilder sb = new StringBuilder();
        sb.append("getOperationType cardType:");
        sb.append(str);
        sb.append(", operationType:");
        if (cardConfigByType == null) {
            operationType = null;
        } else {
            operationType = cardConfigByType.mOperationType;
        }
        sb.append(operationType);
        LogUtils.i(sb.toString());
        if (cardConfigByType == null) {
            return null;
        }
        return cardConfigByType.mOperationType;
    }

    public CardConfig getCardConfigByType(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (Map.Entry next : getSupportedTransCardMap().entrySet()) {
                if (str.equals(((CardConfig) next.getValue()).mCardType)) {
                    return (CardConfig) next.getValue();
                }
            }
        }
        return null;
    }

    public CardConfig getCardConfig(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getSupportedTransCardMap().get(str);
    }

    public void parseCardRulesToBundle(String str, Bundle bundle) {
        ConfigRules localConfigRules;
        if (bundle == null) {
            throw new IllegalArgumentException("readCardOption can't be null");
        } else if (!STAGING || (localConfigRules = getLocalConfigRules(str)) == null) {
            CardConfig cardConfigByType = getCardConfigByType(str);
            if (cardConfigByType != null && !TextUtils.isEmpty(cardConfigByType.mCardRulesUrl)) {
                try {
                    ConfigRules configRules = (ConfigRules) HttpClient.getInstance(this.mContext).execute(new ConfigRulesRequest(cardConfigByType.mCardRulesUrl)).getResult();
                    if (configRules != null) {
                        bundle.putParcelable(ISmartCardHandler.KEY_READ_CARD_OPTION_RULES, configRules);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("cardType:");
                    sb.append(str);
                    sb.append(", configRules version:");
                    sb.append(configRules == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : configRules.mVersion);
                    LogUtils.i(sb.toString());
                } catch (IOException e) {
                    LogUtils.e("parseCardRulesToBundle:" + str + " Exception occurred.", e);
                }
            }
        } else {
            bundle.putParcelable(ISmartCardHandler.KEY_READ_CARD_OPTION_RULES, localConfigRules);
            LogUtils.w("cardType:" + str + " using local configRules version:" + localConfigRules.mVersion);
        }
    }

    private ConfigRules getLocalConfigRules(String str) {
        File[] listFiles;
        FileReader fileReader;
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith("json");
            }
        })) != null && listFiles.length > 0) {
            Gson gson = new Gson();
            try {
                fileReader = new FileReader(listFiles[0]);
                try {
                    return (ConfigRules) gson.fromJson((Reader) fileReader, ConfigRules.class);
                } catch (Exception e) {
                    e = e;
                    LogUtils.e("getLocalConfigRules failed", e);
                    IOUtils.closeQuietly((Reader) fileReader);
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                fileReader = null;
                LogUtils.e("getLocalConfigRules failed", e);
                IOUtils.closeQuietly((Reader) fileReader);
                return null;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.miui.tsmclient.entity.GroupConfigInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, com.miui.tsmclient.entity.CardConfigManager.CardConfig> fetchCardConfig() {
        /*
            r5 = this;
            boolean r0 = r5.isMainThread()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "fetchCardConfig count:"
            r1.append(r2)
            int r2 = r5.mFetchConfigCount
            int r2 = r2 + 1
            r5.mFetchConfigCount = r2
            r1.append(r2)
            if (r0 == 0) goto L_0x001c
            java.lang.String r2 = " on main thread"
            goto L_0x001e
        L_0x001c:
            java.lang.String r2 = ""
        L_0x001e:
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.miui.tsmclient.util.LogUtils.i(r1)
            r1 = 0
            if (r0 != 0) goto L_0x0063
            com.miui.tsmclient.net.request.ConfigListRequest r0 = new com.miui.tsmclient.net.request.ConfigListRequest
            java.lang.String r2 = "CARD_CONFIG"
            r0.<init>(r1, r2, r1)
            android.content.Context r2 = r5.mContext     // Catch:{ IOException -> 0x005b }
            com.miui.tsmclient.common.net.HttpClient r2 = com.miui.tsmclient.common.net.HttpClient.getInstance(r2)     // Catch:{ IOException -> 0x005b }
            com.miui.tsmclient.common.net.Response r2 = r2.execute(r0)     // Catch:{ IOException -> 0x005b }
            java.lang.Object r2 = r2.getResult()     // Catch:{ IOException -> 0x005b }
            com.miui.tsmclient.entity.GroupConfigInfo r2 = (com.miui.tsmclient.entity.GroupConfigInfo) r2     // Catch:{ IOException -> 0x005b }
            boolean r0 = r0.isSuccess()     // Catch:{ IOException -> 0x0059 }
            if (r0 == 0) goto L_0x0064
            com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ IOException -> 0x0059 }
            r0.<init>()     // Catch:{ IOException -> 0x0059 }
            java.lang.String r0 = r0.toJson((java.lang.Object) r2)     // Catch:{ IOException -> 0x0059 }
            android.content.Context r3 = r5.mContext     // Catch:{ IOException -> 0x0059 }
            java.lang.String r4 = "key_card_config"
            com.miui.tsmclient.util.PrefUtils.putString(r3, r4, r0)     // Catch:{ IOException -> 0x0059 }
            goto L_0x0064
        L_0x0059:
            r0 = move-exception
            goto L_0x005d
        L_0x005b:
            r0 = move-exception
            r2 = r1
        L_0x005d:
            java.lang.String r3 = "ConfigListSyncRequest Exception occurred"
            com.miui.tsmclient.util.LogUtils.e(r3, r0)
            goto L_0x0064
        L_0x0063:
            r2 = r1
        L_0x0064:
            if (r2 != 0) goto L_0x0082
            android.content.Context r0 = r5.mContext
            java.lang.String r3 = "key_card_config"
            java.lang.String r0 = com.miui.tsmclient.util.PrefUtils.getString(r0, r3, r1)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x0082
            com.google.gson.Gson r2 = new com.google.gson.Gson
            r2.<init>()
            java.lang.Class<com.miui.tsmclient.entity.GroupConfigInfo> r3 = com.miui.tsmclient.entity.GroupConfigInfo.class
            java.lang.Object r0 = r2.fromJson((java.lang.String) r0, r3)
            r2 = r0
            com.miui.tsmclient.entity.GroupConfigInfo r2 = (com.miui.tsmclient.entity.GroupConfigInfo) r2
        L_0x0082:
            if (r2 == 0) goto L_0x00bc
            com.google.gson.GsonBuilder r0 = new com.google.gson.GsonBuilder
            r0.<init>()
            java.lang.Class<com.miui.tsmclient.entity.CardConfigManager$CardConfig$OperationType> r3 = com.miui.tsmclient.entity.CardConfigManager.CardConfig.OperationType.class
            com.miui.tsmclient.entity.CardConfigManager$CardConfig$OperationTypeDeserializer r4 = new com.miui.tsmclient.entity.CardConfigManager$CardConfig$OperationTypeDeserializer
            r4.<init>()
            r0.registerTypeAdapter(r3, r4)
            com.google.gson.Gson r0 = r0.create()
            java.lang.String r1 = "CARD_CONFIG"
            java.lang.Class<com.miui.tsmclient.entity.CardConfigManager$CardConfig> r3 = com.miui.tsmclient.entity.CardConfigManager.CardConfig.class
            java.util.List r0 = r2.getInfoList((java.lang.String) r1, (java.lang.reflect.Type) r3, (com.google.gson.Gson) r0)
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x00a8:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00bc
            java.lang.Object r2 = r0.next()
            com.miui.tsmclient.entity.CardConfigManager$CardConfig r2 = (com.miui.tsmclient.entity.CardConfigManager.CardConfig) r2
            java.lang.String r3 = r2.mCardAid
            r1.put(r3, r2)
            goto L_0x00a8
        L_0x00bc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.entity.CardConfigManager.fetchCardConfig():java.util.Map");
    }

    private boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static class CardConfig {
        /* access modifiers changed from: private */
        @SerializedName("cardAid")
        public String mCardAid;
        @SerializedName("cardIconUrl")
        private String mCardIconUrl;
        @SerializedName("cardName")
        private String mCardName;
        /* access modifiers changed from: private */
        @SerializedName("cardRulesUrl")
        public String mCardRulesUrl;
        /* access modifiers changed from: private */
        @SerializedName("cardType")
        public String mCardType;
        @SerializedName("hciRule")
        private HCIRule mHCIRule;
        @SerializedName("judgeOverdrawIllegal")
        private boolean mJudgeOverdrawIllegal;
        @SerializedName("needExtraInfo")
        private boolean mNeedExtraInfo;
        /* access modifiers changed from: private */
        @SerializedName("operationType")
        public OperationType mOperationType = OperationType.SYNC;
        @SerializedName("supportCityUHci")
        private boolean mSupportCityUHci;
        @SerializedName("supportRechargeOnInvalidDate")
        private boolean mSupportRechargeOnInvalidDate;
        @SerializedName("tips")
        private String mTips;

        public OperationType getOperationType() {
            return this.mOperationType;
        }

        public String getCardAid() {
            return this.mCardAid;
        }

        public String getCardType() {
            return this.mCardType;
        }

        public String getCardRulesUrl() {
            return this.mCardRulesUrl;
        }

        public String getCardIconUrl() {
            return this.mCardIconUrl;
        }

        public String getCardName() {
            return this.mCardName;
        }

        public boolean isSupportCityUHci() {
            return this.mSupportCityUHci;
        }

        public boolean isNeedExtraInfo() {
            return this.mNeedExtraInfo;
        }

        public HCIRule getHCIRule() {
            return this.mHCIRule;
        }

        public String getTips() {
            return this.mTips;
        }

        public boolean isJudgeOverdrawIllegal() {
            return this.mJudgeOverdrawIllegal;
        }

        public boolean isSupportRechargeOnInvalidDate() {
            return this.mSupportRechargeOnInvalidDate;
        }

        public enum OperationType {
            SYNC(0),
            ASYNC(1);
            
            private int mValue;

            private OperationType(int i) {
                this.mValue = i;
            }

            public static OperationType getOperationType(int i) {
                for (OperationType operationType : values()) {
                    if (operationType.mValue == i) {
                        return operationType;
                    }
                }
                return SYNC;
            }
        }

        private static class OperationTypeDeserializer implements JsonDeserializer<OperationType> {
            private OperationTypeDeserializer() {
            }

            public OperationType deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return OperationType.getOperationType(jsonElement.getAsInt());
            }
        }
    }

    public static class HCIRule {
        @SerializedName("balancetOffset")
        private int mBalancetOffset;
        @SerializedName("dataLength")
        private int mDataLength;
        @SerializedName("terminalNo")
        private int mTerminalNo;
        @SerializedName("tradeAmountOffset")
        private int mTradeAmountOffset;

        public int getDataLength() {
            return this.mDataLength;
        }

        public int getTradeAmountOffset() {
            return this.mTradeAmountOffset;
        }

        public int getBalancetOffset() {
            return this.mBalancetOffset;
        }

        public int getTerminalNo() {
            return this.mTerminalNo;
        }
    }
}
