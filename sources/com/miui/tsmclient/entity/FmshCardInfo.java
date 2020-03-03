package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.Coder;
import org.json.JSONException;
import org.json.JSONObject;

public class FmshCardInfo extends PayableCardInfo {
    public static final String CARD_INFO_FIELD_APP_NO = "app_no";
    public static final Parcelable.Creator<FmshCardInfo> CREATOR = new Parcelable.Creator<FmshCardInfo>() {
        public FmshCardInfo createFromParcel(Parcel parcel) {
            FmshCardInfo fmshCardInfo = new FmshCardInfo();
            fmshCardInfo.readFromParcel(parcel);
            return fmshCardInfo;
        }

        public FmshCardInfo[] newArray(int i) {
            return new FmshCardInfo[i];
        }
    };
    private static final String PATTERN_TRADE_TIME_SOURCE_DEFAULT = "yyyyMMdd HHmmss";
    private static final String PATTERN_TRADE_TIME_TARGET_DEFAULT = "yyyy/MM/dd HH:mm:ss";
    public byte[] mAppNo;

    public String getTradeTimeSourceFormat() {
        return PATTERN_TRADE_TIME_SOURCE_DEFAULT;
    }

    public String getTradeTimeTargetFormat() {
        return PATTERN_TRADE_TIME_TARGET_DEFAULT;
    }

    public FmshCardInfo() {
        this((CardAppInfo) null);
    }

    public FmshCardInfo(CardAppInfo cardAppInfo) {
        this(cardAppInfo, (String) null);
    }

    public FmshCardInfo(CardAppInfo cardAppInfo, String str) {
        super(str);
        fillInfo(cardAppInfo);
    }

    /* access modifiers changed from: protected */
    public void fillInfo(CardAppInfo cardAppInfo) {
        if (cardAppInfo != null) {
            this.mHasIssue = true;
            this.mCardBalance = cardAppInfo.balance;
            this.mCardNo = cardAppInfo.cardFaceNo;
            this.mAppNo = cardAppInfo.appNo;
        }
    }

    public JSONObject serialize() {
        JSONObject serialize = super.serialize();
        try {
            serialize.put(CARD_INFO_FIELD_APP_NO, Coder.bytesToHexString(this.mAppNo));
        } catch (JSONException e) {
            LogUtils.e("serialize cardinfo to jsonobject failed!", e);
        }
        return serialize;
    }

    public CardInfo parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject != null) {
            this.mAppNo = Coder.hexStringToBytes(jSONObject.optString(CARD_INFO_FIELD_APP_NO));
        }
        return this;
    }

    public void updateInfo(FmshCardInfo fmshCardInfo) {
        super.updateInfo(fmshCardInfo);
        this.mAppNo = fmshCardInfo.mRealCardNo == null ? null : Coder.hexStringToBytes(fmshCardInfo.mRealCardNo);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        writeBytesWithNull(parcel, this.mAppNo);
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.mAppNo = readBytesWithNull(parcel);
    }

    private byte[] readBytesWithNull(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        parcel.readByteArray(bArr);
        return bArr;
    }

    private void writeBytesWithNull(Parcel parcel, byte[] bArr) {
        if (bArr == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(bArr.length);
        parcel.writeByteArray(bArr);
    }
}
