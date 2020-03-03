package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CardUIInfo implements Parcelable, JSONSerializable, ObjectParser<CardUIInfo> {
    public static final Parcelable.Creator<CardUIInfo> CREATOR = new Parcelable.Creator<CardUIInfo>() {
        public CardUIInfo createFromParcel(Parcel parcel) {
            return new CardUIInfo(parcel);
        }

        public CardUIInfo[] newArray(int i) {
            return new CardUIInfo[i];
        }
    };
    public static final String KEY_CARD_GIF_BG_URL = "gifUrl";
    public static final String KEY_CARD_UI_INFO = "card_ui_info";
    public static final String KEY_DETAILDESC = "detailDesc";
    public static final String KEY_ISSUEDDETAILBG = "issuedDetailBg";
    public static final String KEY_ISSUEDLISTBG = "issuedListBg";
    public static final String KEY_ISSUEDLISTBGHD = "issuedListBgHd";
    public static final String KEY_LOGO = "logo";
    public static final String KEY_PREISSUEDDETAILBG = "preIssuedDetailBg";
    public static final String KEY_PREISSUEDLISTBG = "preIssuedListBg";
    public static final String KEY_SUBTITILE = "subTitle";
    public static final String KEY_THEME_FG_RULES = "themeFgRules";
    public String mCardDesc;
    public String mCardDetailDesc;
    public String mCardGifBgHdUrl;
    public String mCardIssuedListBgHdUrl;
    public String mCardIssuedListBgUrl;
    public String mCardLogoUrl;
    public String mCardPreIssuedListBgUrl;
    public String mIssuedDetailBgUrl;
    public String mPreIssuedDetailBgUrl;
    private Map<String, String> mThemeFgRules;

    public int describeContents() {
        return 0;
    }

    private CardUIInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public CardUIInfo() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardDesc);
        parcel.writeString(this.mCardPreIssuedListBgUrl);
        parcel.writeString(this.mCardIssuedListBgUrl);
        parcel.writeString(this.mCardLogoUrl);
        parcel.writeString(this.mPreIssuedDetailBgUrl);
        parcel.writeString(this.mCardDetailDesc);
        parcel.writeString(this.mIssuedDetailBgUrl);
        parcel.writeString(this.mCardIssuedListBgHdUrl);
        parcel.writeValue(this.mThemeFgRules);
        parcel.writeString(this.mCardGifBgHdUrl);
    }

    public void readFromParcel(Parcel parcel) {
        this.mCardDesc = parcel.readString();
        this.mCardPreIssuedListBgUrl = parcel.readString();
        this.mCardIssuedListBgUrl = parcel.readString();
        this.mCardLogoUrl = parcel.readString();
        this.mPreIssuedDetailBgUrl = parcel.readString();
        this.mCardDetailDesc = parcel.readString();
        this.mIssuedDetailBgUrl = parcel.readString();
        this.mCardIssuedListBgHdUrl = parcel.readString();
        this.mThemeFgRules = (Map) parcel.readValue(Map.class.getClassLoader());
        this.mCardGifBgHdUrl = parcel.readString();
    }

    public CardUIInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (jSONObject.has(KEY_CARD_UI_INFO)) {
                doParse(jSONObject.optJSONObject(KEY_CARD_UI_INFO));
            } else {
                doParse(jSONObject);
            }
        }
        return this;
    }

    private void doParse(JSONObject jSONObject) {
        this.mCardDesc = jSONObject.optString(KEY_SUBTITILE);
        this.mCardPreIssuedListBgUrl = jSONObject.optString(KEY_PREISSUEDLISTBG);
        this.mCardIssuedListBgUrl = jSONObject.optString(KEY_ISSUEDLISTBG);
        this.mCardLogoUrl = jSONObject.optString(KEY_LOGO);
        this.mPreIssuedDetailBgUrl = jSONObject.optString(KEY_PREISSUEDDETAILBG);
        this.mCardDetailDesc = jSONObject.optString(KEY_DETAILDESC);
        this.mIssuedDetailBgUrl = jSONObject.optString(KEY_ISSUEDDETAILBG);
        this.mCardIssuedListBgHdUrl = jSONObject.optString(KEY_ISSUEDLISTBGHD);
        this.mThemeFgRules = (Map) new Gson().fromJson(jSONObject.optString(KEY_THEME_FG_RULES), new TypeToken<Map<String, String>>() {
        }.getType());
        this.mCardGifBgHdUrl = jSONObject.optString(KEY_CARD_GIF_BG_URL);
    }

    public JSONObject serialize() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_SUBTITILE, this.mCardDesc);
            jSONObject.put(KEY_PREISSUEDLISTBG, this.mCardPreIssuedListBgUrl);
            jSONObject.put(KEY_ISSUEDLISTBG, this.mCardIssuedListBgUrl);
            jSONObject.put(KEY_LOGO, this.mCardLogoUrl);
            jSONObject.put(KEY_PREISSUEDDETAILBG, this.mPreIssuedDetailBgUrl);
            jSONObject.put(KEY_DETAILDESC, this.mCardDetailDesc);
            jSONObject.put(KEY_ISSUEDDETAILBG, this.mIssuedDetailBgUrl);
            jSONObject.put(KEY_ISSUEDLISTBGHD, this.mCardIssuedListBgHdUrl);
            jSONObject.put(KEY_THEME_FG_RULES, new Gson().toJson((Object) this.mThemeFgRules));
            jSONObject.put(KEY_CARD_GIF_BG_URL, this.mCardGifBgHdUrl);
        } catch (JSONException e) {
            LogUtils.e("serialize card ui info to json object failed!", e);
        }
        return jSONObject;
    }

    public String getThemeFgArt(String str) {
        return this.mThemeFgRules == null ? "" : this.mThemeFgRules.get(str);
    }
}
