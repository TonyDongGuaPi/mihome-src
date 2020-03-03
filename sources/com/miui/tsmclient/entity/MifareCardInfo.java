package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MifareCardInfo extends CardInfo {
    public static final String CARD_ART = "card_art";
    public static final int CARD_FACE1 = 1;
    public static final int CARD_FACE2 = 2;
    public static final String CARD_FINGER_FLAG = "card_finger_flag";
    public static final String CARD_INFO_DOOR_CARD_PRODUCT_ID = "door_card_product_id";
    public static final String CARD_INFO_MIFARE_CARD_TYPE = "mifare_card_type";
    public static final String CARD_NATIVE_DRAWABLE_RES = "card_native_drawable";
    public static final int CARD_STATUS_ACTIVE = 1;
    public static final int CARD_TYPE_DOOR_CARD = 1;
    public static final int CARD_TYPE_NORMAL = 0;
    public static final String CARD_USER_TERMS = "card_user_terms";
    public static final String CARD_VC_STATUS = "card_vc_status";
    public static final Parcelable.Creator<MifareCardInfo> CREATOR = new Parcelable.Creator<MifareCardInfo>() {
        public MifareCardInfo createFromParcel(Parcel parcel) {
            MifareCardInfo mifareCardInfo = new MifareCardInfo();
            mifareCardInfo.readFromParcel(parcel);
            return mifareCardInfo;
        }

        public MifareCardInfo[] newArray(int i) {
            return new MifareCardInfo[i];
        }
    };
    private static final int FLAG_CANCEL_FINGER_AUTH = 1;
    public static final String KEY_PRODUCT_ID = "productId";
    public String mCardArt;
    public int mCardFace;
    public int mFingerAuthFlag;
    public int mMifareCardType;
    private String mProductId;
    public String mUserTerms;
    public int mVCStatus;

    public static class MifareType {
        public static final int CARD_TYPE_COMMUNITY = 3;
        public static final int CARD_TYPE_CUSTOM_BLANK = 1;
        public static final int CARD_TYPE_DOOR_LOCK = 2;
        public static final int CARD_TYPE_NORMAL = 0;
    }

    public boolean isMiFareCard() {
        return true;
    }

    public MifareCardInfo() {
        super(CardInfo.CARD_TYPE_MIFARE);
        this.mIsSecure = false;
        this.mCardGroupId = CardGroupType.MIFARECARD.getId();
    }

    public JSONObject serialize() {
        JSONObject serialize = super.serialize();
        try {
            serialize.put("card_user_terms", this.mUserTerms);
            serialize.put("card_art", this.mCardArt);
            serialize.put("card_vc_status", this.mVCStatus);
            serialize.putOpt(CARD_NATIVE_DRAWABLE_RES, Integer.valueOf(this.mCardFace));
            serialize.put(CARD_FINGER_FLAG, this.mFingerAuthFlag);
            serialize.put(CARD_INFO_MIFARE_CARD_TYPE, this.mMifareCardType);
            serialize.put(CARD_INFO_DOOR_CARD_PRODUCT_ID, this.mProductId);
        } catch (JSONException e) {
            LogUtils.e("serialize bankcard info to json object failed!", e);
        }
        return serialize;
    }

    public CardInfo parse(JSONObject jSONObject) {
        super.parse(jSONObject);
        if (jSONObject != null) {
            this.mUserTerms = jSONObject.optString("card_user_terms");
            this.mCardArt = jSONObject.optString("card_art");
            this.mVCStatus = jSONObject.optInt("card_vc_status");
            this.mCardFace = jSONObject.optInt(CARD_NATIVE_DRAWABLE_RES);
            this.mFingerAuthFlag = jSONObject.optInt(CARD_FINGER_FLAG);
            this.mMifareCardType = jSONObject.optInt(CARD_INFO_MIFARE_CARD_TYPE);
            this.mProductId = jSONObject.optString(CARD_INFO_DOOR_CARD_PRODUCT_ID);
        }
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mUserTerms);
        parcel.writeString(this.mCardArt);
        parcel.writeInt(this.mVCStatus);
        parcel.writeInt(this.mCardFace);
        parcel.writeInt(this.mFingerAuthFlag);
        parcel.writeInt(this.mMifareCardType);
        parcel.writeString(this.mProductId);
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.mUserTerms = parcel.readString();
        this.mCardArt = parcel.readString();
        this.mVCStatus = parcel.readInt();
        this.mCardFace = parcel.readInt();
        this.mFingerAuthFlag = parcel.readInt();
        this.mMifareCardType = parcel.readInt();
        this.mProductId = parcel.readString();
    }

    public boolean isSecure() {
        return this.mFingerAuthFlag != 1;
    }

    public String getImageUrl() {
        return TextUtils.isEmpty(this.mCardArt) ? String.valueOf(this.mCardFace) : this.mCardArt;
    }

    public void cancelFinger() {
        this.mFingerAuthFlag = 1;
    }

    public String getProductId() {
        return this.mProductId == null ? "" : this.mProductId;
    }

    public void setProductId(String str) {
        this.mProductId = str;
    }

    public String getUpdateArtContent() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("aid", this.mAid);
            jSONObject.put("cardType", this.mCardType);
            jSONObject.put(KEY_PRODUCT_ID, this.mProductId);
            jSONObject.put(CardInfo.KEY_UPDATE_KEY, 0);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("status", 1);
            jSONObject.put(CardInfo.KEY_UPDATE_CONTENT, jSONObject2);
        } catch (JSONException e) {
            LogUtils.e("getUpdateArtContent error", e);
        }
        return jSONObject.toString();
    }

    public void parseCardFromJson(JSONObject jSONObject) {
        super.parseCardFromJson(jSONObject);
        if (jSONObject != null && jSONObject.has("card_art")) {
            this.mCardArt = jSONObject.optString("card_art");
        }
    }
}
