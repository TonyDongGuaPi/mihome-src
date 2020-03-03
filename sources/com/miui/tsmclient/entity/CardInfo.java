package com.miui.tsmclient.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.CardEnvironmentConfig;
import com.miui.tsmclient.util.IDeviceInfo;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ReflectUtil;
import com.tsmclient.smartcard.model.TradeLog;
import com.tsmclient.smartcard.terminal.IScTerminal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class CardInfo implements Parcelable, JSONSerializable, ObjectParser<CardInfo> {
    private static final String CARD_EXTRA = "com.miui.tsmclient.entity.CardExtra";
    public static final String CARD_TYPE_BANKCARD = "BANKCARD";
    public static final String CARD_TYPE_BMAC = "BMAC";
    public static final String CARD_TYPE_DUMMY = "DUMMY";
    public static final String CARD_TYPE_LNT = "LNT";
    public static final String CARD_TYPE_MIFARE = "MIFARE_ENTRANCE";
    public static final String CARD_TYPE_SPTC = "SPTC";
    public static final String CARD_TYPE_SPTC_NEW = "SPTC_NEW";
    public static final String CARD_TYPE_SZT = "SZT";
    public static final String CARD_TYPE_VSIM = "VSIM";
    public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator<CardInfo>() {
        public CardInfo createFromParcel(Parcel parcel) {
            return new CardInfo(parcel);
        }

        public CardInfo[] newArray(int i) {
            return new CardInfo[i];
        }
    };
    private static final int EXTRA_CONFIGS_MASK_ISSUED = 1;
    public static final String KEY_AID = "aid";
    public static final String KEY_CARDNAME = "cardName";
    public static final String KEY_CARD_BALANCE = "card_balance";
    public static final String KEY_CARD_DEVICE = "cardDevice";
    public static final String KEY_CARD_END_DATE = "card_end_date";
    public static final String KEY_CARD_GROUP = "cardGroup";
    public static final String KEY_CARD_GROUP_ID = "groupId";
    public static final String KEY_CARD_GROUP_NAME = "groupName";
    public static final String KEY_CARD_NO = "card_no";
    public static final String KEY_CARD_START_DATE = "card_start_date";
    public static final String KEY_CARD_STATUS = "card_status";
    public static final String KEY_CARD_SUB_NAME = "card_sub_name";
    public static final String KEY_CARD_TYPE = "cardType";
    public static final String KEY_EXTRA = "extra";
    private static final String KEY_EXTRA_CARD_CONFIGS = "cardConfigs";
    private static final String KEY_EXTRA_ISSUED = "issued";
    public static final String KEY_HAS_ISSUE = "has_issue";
    public static final String KEY_ISSUEFEE = "issueFee";
    public static final String KEY_IS_DEFAULT = "is_default";
    public static final String KEY_READ_SE_CORRECTLY = "read_se_correctly";
    public static final String KEY_REAL_CARD_NO = "real_card_no";
    public static final String KEY_RECHARGE_STATUS = "rechargeStatus";
    public static final String KEY_RECHARGE_STATUS_DESC = "rechargeStatusDesc";
    public static final String KEY_SECURE = "secure";
    public static final String KEY_SERVICE_FEE_DESC = "serviceFeeDesc";
    public static final String KEY_STATUS = "status";
    public static final String KEY_STATUS_DESC = "statusDesc";
    public static final String KEY_TITLE = "title";
    public static final String KEY_UPDATE_CONTENT = "updateContent";
    public static final String KEY_UPDATE_KEY = "updateKey";
    public static final Set<String> SPTC_TYPE_SET = new HashSet();
    public static final int VALUE_UPDATE_CARD_ART_BY_STATUS = 0;
    private static final Map<String, IDeviceInfo> sDeviceInfoMap = new HashMap();
    public String mAid;
    public int mCardBalance;
    protected String mCardDevice;
    public int mCardGroupId;
    public String mCardName;
    public String mCardNo;
    public String mCardSubName;
    public String mCardType;
    public CardUIInfo mCardUIInfo;
    public DataSource mDataSource;
    public String mEndDate;
    private String mExtra;
    public String mGroupName;
    public int mGroupType;
    public boolean mHasIssue;
    public boolean mIsDefault;
    public boolean mIsPack;
    public boolean mIsReadSECorrectly;
    protected boolean mIsSecure;
    public int mIssueFee;
    public String mRealCardNo;
    private RechargeStatus mRechargeStatus;
    private String mRechargeStatusDesc;
    private String mServiceFeeDesc;
    public ServiceStatus mServiceStatus;
    private String mServiceStatusDesc;
    public String mStartDate;
    public Status mStatus;
    public List<TradeLog> mTradeLogs;

    public enum DataSource {
        Unknown,
        DB,
        Network,
        SE
    }

    public enum Status {
        ACTIVE,
        IN_BLACKLIST,
        NEGATIVE,
        DATA_ILLEGAL,
        START_DATE_INVALID,
        END_DATE_INVALID,
        LOCKED,
        INVALID
    }

    public boolean canTransferIn() {
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public String getUpdateArtContent() {
        return "";
    }

    public boolean hasIssueOrder() {
        return false;
    }

    public boolean hasRechargeOrder() {
        return false;
    }

    public boolean hasTransferInOrder() {
        return false;
    }

    public boolean isBankCard() {
        return false;
    }

    public boolean isMiFareCard() {
        return false;
    }

    public boolean isTransCard() {
        return false;
    }

    public void parseCardFromJson(JSONObject jSONObject) {
    }

    public boolean showBalance() {
        return false;
    }

    static {
        SPTC_TYPE_SET.add("SPTC");
        SPTC_TYPE_SET.add("SPTC_NEW");
    }

    public enum ServiceStatus {
        unknown(-1),
        active(0),
        negative(1),
        no_stock(2);
        
        private int mId;

        private ServiceStatus(int i) {
            this.mId = i;
        }

        public int getId() {
            return this.mId;
        }

        public static ServiceStatus getInstance(int i) {
            for (ServiceStatus serviceStatus : values()) {
                if (serviceStatus.mId == i) {
                    return serviceStatus;
                }
            }
            return unknown;
        }
    }

    public enum RechargeStatus {
        unknown(-1),
        available(0),
        unavailable(4);
        
        private int mId;

        private RechargeStatus(int i) {
            this.mId = i;
        }

        public int getId() {
            return this.mId;
        }

        public static RechargeStatus getInstance(int i) {
            for (RechargeStatus rechargeStatus : values()) {
                if (rechargeStatus.mId == i) {
                    return rechargeStatus;
                }
            }
            return unknown;
        }
    }

    private CardInfo(Parcel parcel) {
        this.mIsPack = false;
        this.mStatus = null;
        this.mDataSource = DataSource.Unknown;
        this.mIsSecure = false;
        readFromParcel(parcel);
    }

    public CardInfo(String str) {
        this(str, false);
    }

    public CardInfo(String str, boolean z) {
        this.mIsPack = false;
        this.mStatus = null;
        this.mDataSource = DataSource.Unknown;
        this.mIsSecure = false;
        this.mCardType = str;
        this.mIsSecure = z;
        this.mCardDevice = CardEnvironmentConfig.getDefaultDevice();
    }

    public JSONObject serialize() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", this.mCardName);
            jSONObject.put(KEY_CARD_NO, this.mCardNo);
            jSONObject.put("has_issue", this.mHasIssue);
            jSONObject.put(KEY_IS_DEFAULT, this.mIsDefault);
            jSONObject.put("card_balance", this.mCardBalance);
            if (this.mCardType != null) {
                jSONObject.put("cardName", this.mCardType.toString());
            }
            jSONObject.put(KEY_CARD_SUB_NAME, this.mCardSubName);
            jSONObject.put(KEY_ISSUEFEE, this.mIssueFee);
            jSONObject.put("aid", this.mAid);
            if (this.mServiceStatus != null) {
                jSONObject.put("status", this.mServiceStatus.getId());
            }
            jSONObject.put("statusDesc", this.mServiceStatusDesc);
            if (this.mRechargeStatus != null) {
                jSONObject.put(KEY_RECHARGE_STATUS, this.mRechargeStatus.getId());
            }
            jSONObject.put(KEY_RECHARGE_STATUS_DESC, this.mRechargeStatusDesc);
            if (this.mCardUIInfo != null) {
                jSONObject.put(CardUIInfo.KEY_CARD_UI_INFO, this.mCardUIInfo.serialize());
            }
            if (this.mStatus != null) {
                jSONObject.put(KEY_CARD_STATUS, this.mStatus.toString());
            }
            if (this.mStartDate != null) {
                jSONObject.put(KEY_CARD_START_DATE, this.mStartDate);
            }
            if (this.mEndDate != null) {
                jSONObject.put(KEY_CARD_END_DATE, this.mEndDate);
            }
            if (this.mRealCardNo != null) {
                jSONObject.put(KEY_REAL_CARD_NO, this.mRealCardNo);
            }
            jSONObject.put("secure", this.mIsSecure);
            jSONObject.put(KEY_READ_SE_CORRECTLY, this.mIsReadSECorrectly);
            jSONObject.put(KEY_CARD_GROUP, this.mGroupType);
            jSONObject.put(KEY_CARD_GROUP_NAME, this.mGroupName);
            jSONObject.put(KEY_CARD_GROUP_ID, this.mCardGroupId);
            if (this.mCardDevice != null) {
                jSONObject.put(KEY_CARD_DEVICE, this.mCardDevice);
            }
            jSONObject.put(KEY_SERVICE_FEE_DESC, this.mServiceFeeDesc);
            if (!TextUtils.isEmpty(this.mExtra)) {
                jSONObject.put("extra", this.mExtra);
            }
        } catch (JSONException e) {
            LogUtils.e("serialize cardInfo to JSONObject failed!", e);
        }
        return jSONObject;
    }

    public CardInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mCardName = jSONObject.optString("title");
            if (jSONObject.has(KEY_CARD_NO)) {
                this.mCardNo = jSONObject.optString(KEY_CARD_NO);
            }
            if (jSONObject.has("has_issue")) {
                this.mHasIssue = jSONObject.optBoolean("has_issue");
            }
            this.mIsDefault = jSONObject.optBoolean(KEY_IS_DEFAULT);
            if (jSONObject.has("card_balance")) {
                this.mCardBalance = jSONObject.optInt("card_balance");
            }
            if (jSONObject.has("cardName")) {
                this.mCardType = jSONObject.optString("cardName");
            }
            if (jSONObject.has(KEY_CARD_SUB_NAME)) {
                this.mCardSubName = jSONObject.optString(KEY_CARD_SUB_NAME);
            }
            this.mIssueFee = jSONObject.optInt(KEY_ISSUEFEE);
            this.mAid = jSONObject.optString("aid");
            this.mServiceStatus = ServiceStatus.getInstance(jSONObject.optInt("status", -1));
            this.mServiceStatusDesc = jSONObject.optString("statusDesc");
            this.mRechargeStatus = RechargeStatus.getInstance(jSONObject.optInt(KEY_RECHARGE_STATUS));
            this.mRechargeStatusDesc = jSONObject.optString(KEY_RECHARGE_STATUS_DESC);
            this.mCardUIInfo = new CardUIInfo();
            this.mCardUIInfo.parse(jSONObject);
            if (jSONObject.has(KEY_CARD_STATUS)) {
                this.mStatus = Status.valueOf(jSONObject.optString(KEY_CARD_STATUS));
            }
            if (jSONObject.has(KEY_CARD_START_DATE)) {
                this.mStartDate = jSONObject.optString(KEY_CARD_START_DATE);
            }
            if (jSONObject.has(KEY_CARD_END_DATE)) {
                this.mEndDate = jSONObject.optString(KEY_CARD_END_DATE);
            }
            if (jSONObject.has(KEY_REAL_CARD_NO)) {
                this.mRealCardNo = jSONObject.optString(KEY_REAL_CARD_NO);
            }
            this.mIsSecure = jSONObject.optBoolean("secure");
            this.mIsReadSECorrectly = jSONObject.optBoolean(KEY_READ_SE_CORRECTLY);
            this.mGroupType = jSONObject.optInt(KEY_CARD_GROUP, -1);
            this.mGroupName = jSONObject.optString(KEY_CARD_GROUP_NAME);
            if (jSONObject.has(KEY_CARD_GROUP_ID)) {
                this.mCardGroupId = jSONObject.optInt(KEY_CARD_GROUP_ID);
            }
            if (jSONObject.has(KEY_CARD_DEVICE)) {
                this.mCardDevice = jSONObject.optString(KEY_CARD_DEVICE);
            }
            if (jSONObject.has(KEY_SERVICE_FEE_DESC)) {
                this.mServiceFeeDesc = jSONObject.optString(KEY_SERVICE_FEE_DESC);
            }
            if (jSONObject.has("extra")) {
                this.mExtra = jSONObject.optString("extra");
            }
        }
        return this;
    }

    public int hashCode() {
        return this.mCardType.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardInfo)) {
            return false;
        }
        CardInfo cardInfo = (CardInfo) obj;
        if (!TextUtils.equals(this.mAid, cardInfo.mAid) || !TextUtils.equals(this.mCardDevice, cardInfo.mCardDevice)) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardName);
        parcel.writeString(this.mCardNo);
        parcel.writeByte(this.mHasIssue ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDefault ? (byte) 1 : 0);
        parcel.writeInt(this.mCardBalance);
        parcel.writeString(this.mCardType);
        parcel.writeValue(this.mServiceStatus);
        parcel.writeString(this.mServiceStatusDesc);
        parcel.writeValue(this.mRechargeStatus);
        parcel.writeString(this.mRechargeStatusDesc);
        parcel.writeByte(this.mIsPack ? (byte) 1 : 0);
        parcel.writeString(this.mCardSubName);
        parcel.writeParcelable(this.mCardUIInfo, i);
        parcel.writeInt(this.mIssueFee);
        parcel.writeString(this.mAid);
        parcel.writeList(this.mTradeLogs);
        parcel.writeString(this.mStatus == null ? "" : this.mStatus.name());
        parcel.writeString(this.mStartDate);
        parcel.writeString(this.mEndDate);
        parcel.writeString(this.mRealCardNo);
        parcel.writeValue(this.mDataSource);
        parcel.writeByte(this.mIsSecure ? (byte) 1 : 0);
        parcel.writeByte(this.mIsReadSECorrectly ? (byte) 1 : 0);
        parcel.writeInt(this.mGroupType);
        parcel.writeString(this.mGroupName);
        parcel.writeInt(this.mCardGroupId);
        parcel.writeString(this.mCardDevice);
        parcel.writeString(this.mServiceFeeDesc);
        parcel.writeString(this.mExtra);
    }

    public void readFromParcel(Parcel parcel) {
        this.mCardName = parcel.readString();
        this.mCardNo = parcel.readString();
        boolean z = false;
        this.mHasIssue = parcel.readByte() == 1;
        this.mIsDefault = parcel.readByte() == 1;
        this.mCardBalance = parcel.readInt();
        this.mCardType = parcel.readString();
        this.mServiceStatus = (ServiceStatus) parcel.readValue(ServiceStatus.class.getClassLoader());
        this.mServiceStatusDesc = parcel.readString();
        this.mRechargeStatus = (RechargeStatus) parcel.readValue(RechargeStatus.class.getClassLoader());
        this.mRechargeStatusDesc = parcel.readString();
        this.mIsPack = parcel.readByte() == 1;
        this.mCardSubName = parcel.readString();
        this.mCardUIInfo = (CardUIInfo) parcel.readParcelable(CardUIInfo.class.getClassLoader());
        this.mIssueFee = parcel.readInt();
        this.mAid = parcel.readString();
        this.mTradeLogs = parcel.readArrayList(TradeLog.class.getClassLoader());
        String readString = parcel.readString();
        if (!TextUtils.isEmpty(readString)) {
            this.mStatus = Status.valueOf(readString);
        }
        this.mStartDate = parcel.readString();
        this.mEndDate = parcel.readString();
        this.mRealCardNo = parcel.readString();
        this.mDataSource = (DataSource) parcel.readValue(DataSource.class.getClassLoader());
        this.mIsSecure = parcel.readByte() == 1;
        if (parcel.readByte() == 1) {
            z = true;
        }
        this.mIsReadSECorrectly = z;
        this.mGroupType = parcel.readInt();
        this.mGroupName = parcel.readString();
        this.mCardGroupId = parcel.readInt();
        this.mCardDevice = parcel.readString();
        this.mServiceFeeDesc = parcel.readString();
        this.mExtra = parcel.readString();
    }

    public void updateInfo(CardInfo cardInfo) {
        this.mCardNo = cardInfo.mCardNo;
        this.mHasIssue = cardInfo.mHasIssue;
        this.mIsDefault = cardInfo.mIsDefault;
        this.mCardBalance = cardInfo.mCardBalance;
        this.mCardSubName = cardInfo.mCardSubName;
        this.mStartDate = cardInfo.mStartDate;
        this.mEndDate = cardInfo.mEndDate;
        this.mRealCardNo = cardInfo.mRealCardNo;
        this.mStatus = cardInfo.mStatus;
        this.mIsReadSECorrectly = cardInfo.mIsReadSECorrectly;
    }

    public String mapAid() {
        return this.mAid;
    }

    public IScTerminal getTerminal() {
        try {
            return (IScTerminal) ReflectUtil.callObjectMethod(ReflectUtil.newInstance(CARD_EXTRA, (Class<?>[]) null, new Object[0]), "getTerminal", new Class[]{CardInfo.class}, this);
        } catch (Exception e) {
            LogUtils.e("getTerminal", e);
            throw new RuntimeException("get Terminal failed");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.miui.tsmclient.util.IDeviceInfo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.miui.tsmclient.util.IDeviceInfo getDeviceInfo() {
        /*
            r4 = this;
            java.lang.String r0 = "com.miui.tsmclient.mitsmsdk.DeviceInfoImpl"
            java.lang.String r1 = r4.mCardDevice
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x001d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.mCardDevice
            r0.append(r1)
            java.lang.String r1 = ".mitsmsdk.DeviceInfoImpl"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x001d:
            java.util.Map<java.lang.String, com.miui.tsmclient.util.IDeviceInfo> r1 = sDeviceInfoMap
            java.lang.Object r1 = r1.get(r0)
            com.miui.tsmclient.util.IDeviceInfo r1 = (com.miui.tsmclient.util.IDeviceInfo) r1
            if (r1 != 0) goto L_0x003b
            r2 = 0
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Object r2 = com.miui.tsmclient.util.ReflectUtil.newInstance((java.lang.String) r0, (java.lang.Class<?>[]) r2, (java.lang.Object[]) r3)
            boolean r3 = r2 instanceof com.miui.tsmclient.util.IDeviceInfo
            if (r3 == 0) goto L_0x003b
            r1 = r2
            com.miui.tsmclient.util.IDeviceInfo r1 = (com.miui.tsmclient.util.IDeviceInfo) r1
            java.util.Map<java.lang.String, com.miui.tsmclient.util.IDeviceInfo> r2 = sDeviceInfoMap
            r2.put(r0, r1)
        L_0x003b:
            if (r1 == 0) goto L_0x003e
            return r1
        L_0x003e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "device info can not be found!"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.entity.CardInfo.getDeviceInfo():com.miui.tsmclient.util.IDeviceInfo");
    }

    public void updateExtraInfo(Context context) {
        try {
            ReflectUtil.callObjectMethod(ReflectUtil.newInstance(CARD_EXTRA, (Class<?>[]) null, new Object[0]), "updateExtraInfo", new Class[]{Context.class, CardInfo.class}, context, this);
        } catch (Exception e) {
            LogUtils.e("updateExtraInfo", e);
        }
    }

    public boolean isServiceAvailable() {
        return this.mServiceStatus == ServiceStatus.active;
    }

    public String getServiceStatusDesc() {
        return this.mServiceStatusDesc;
    }

    public boolean isRechargeServiceAvailable() {
        return this.mRechargeStatus == RechargeStatus.available;
    }

    public String getRechargeStatusDesc() {
        return this.mRechargeStatusDesc;
    }

    public String getCardType() {
        if (CARD_TYPE_DUMMY.equals(this.mCardType)) {
            String cardType = CardConfigManager.getInstance().getCardType(this.mAid);
            if (!TextUtils.isEmpty(cardType)) {
                return cardType;
            }
        }
        return this.mCardType;
    }

    public boolean isSecure() {
        return this.mIsSecure;
    }

    public boolean isAid(String str) {
        return !TextUtils.isEmpty(this.mAid) && TextUtils.equals(str, this.mAid);
    }

    public CardInfo copy() {
        CardInfo makeCardInfo = CardInfoFactory.makeCardInfo(this.mCardType, serialize());
        makeCardInfo.mTradeLogs = this.mTradeLogs;
        return makeCardInfo;
    }

    public String getServiceFeeDesc() {
        return this.mServiceFeeDesc;
    }

    public int getIssueFee() {
        return this.mIssueFee;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public boolean isCardActive() {
        return this.mStatus == Status.ACTIVE;
    }

    public Status getCardStatus() {
        return this.mStatus;
    }

    public boolean isBalanceValid() {
        return this.mCardBalance != -999;
    }

    public boolean updateCardFromExtra() {
        if (TextUtils.isEmpty(this.mExtra)) {
            return false;
        }
        try {
            LogUtils.d("updateCardFromExtra：" + this.mExtra);
            JSONObject jSONObject = new JSONObject(this.mExtra);
            if ((jSONObject.optInt(KEY_EXTRA_CARD_CONFIGS, 0) & 1) == 0) {
                return false;
            }
            this.mHasIssue = jSONObject.optBoolean(KEY_EXTRA_ISSUED, false);
            if (!this.mHasIssue) {
                this.mStatus = null;
                this.mCardBalance = 0;
                this.mCardNo = null;
                this.mRealCardNo = null;
                this.mIsReadSECorrectly = true;
            }
            return true;
        } catch (JSONException e) {
            LogUtils.e("updateCardFromExtra error occurred", e);
            return false;
        }
    }
}
