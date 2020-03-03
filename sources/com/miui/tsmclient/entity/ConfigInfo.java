package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.miui.tsmclient.common.data.CommonResponseInfo;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConfigInfo extends CommonResponseInfo {
    public static final String BANNER_IMG = "BANNER_IMG";
    public static final String BANNER_LINK = "BANNER_LINK";
    public static final String CARD_DETAIL_BANNER_LIST = "CARD_DETAIL_BANNER_LIST";
    public static final String CARD_DETAIL_BG = "CARD_DETAIL_BG";
    public static final String CARD_LIST_BANNER_LIST = "CARD_LIST_BANNER_LIST";
    public static final String CARD_RECHARGE_BANNER_LIST = "CARD_RECHARGE_BANNER_LIST";
    public static final String CITY_LIST = "CITY_LIST";
    public static final String COMMA_SEPARATOR = "\\s*,\\s*";
    public static final String CUSTOMER_SERVICE_PHONE = "CUSTOMER_SERVICE_PHONE";
    public static final String DETAIL_FOOTER_INFO = "DETAIL_FOOTER_INFO";
    public static final String FEATURE_INVOICE = "FEATURE_INVOICE";
    public static final String FEATURE_SERVICE_INVOICE = "FEATURE_SERVICE_INVOICE";
    public static final String ISSUE_CARD_NOTICE = "ISSUE_CARD_NOTICE";
    public static final String MAINTAIN_INFO = "MAINTAIN_INFO";
    public static final String SWITCH_TRANSFER = "SWITCH_TRANSFER";
    public static final String TRANSFER_BALANCE_DESC = "TRANSFER_BALANCE_DESC";
    public static final String TRANSFER_CARD_IN_PROCESS = "TRANSFER_CARD_IN_PROCESS";
    public static final String TRANSFER_CARD_TYPE = "TRANSFER_CARD_";
    public static final String TRANSFER_FEE_DESC = "TRANSFER_FEE_DESC";
    public static final String TRANSFER_INTRODUCTION = "TRANSFER_INTRODUCTION";
    public static final String TRANSFER_OP_GUIDE = "TRANSFER_OP_GUIDE";
    public static final String USER_GUIDE = "USER_GUIDE";
    @SerializedName("data")
    private Map<String, ConfigItem> mConfigData;

    public static class BannerInfo {
        @SerializedName("img")
        public String mBannerImg;
        @SerializedName("link")
        public String mBannerLink;
    }

    public static class ConfigItem {
        @SerializedName("cardName")
        public String mCardName;
        @SerializedName("content")
        public String mContent;
        @SerializedName("key")
        public String mKey;
        @SerializedName("name")
        public String mName;
        @SerializedName("type")
        public String mType;
    }

    public static class FooterInfo {
        @SerializedName("content")
        public String mFooterContent;
        @SerializedName("keyword")
        public String mFooterKeyword;
        @SerializedName("link")
        public String mFooterLink;
    }

    public Map<String, ConfigItem> getConfigMap() {
        return this.mConfigData;
    }

    public boolean getSupportFeature(String str, String str2) {
        if (!(this.mConfigData == null || this.mConfigData.get(str) == null)) {
            String str3 = this.mConfigData.get(str).mContent;
            if (!TextUtils.isEmpty(str3)) {
                String[] split = str3.replace(" ", "").toLowerCase().split(COMMA_SEPARATOR);
                String lowerCase = str2.toLowerCase();
                for (String equals : split) {
                    if (TextUtils.equals(equals, lowerCase)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<BannerInfo> getBannerList(String str) {
        BannerInfo[] bannerInfoArr = (BannerInfo[]) getInfo(str, BannerInfo[].class);
        if (bannerInfoArr != null) {
            return Arrays.asList(bannerInfoArr);
        }
        return Collections.emptyList();
    }

    public FooterInfo getFooter(String str) {
        return (FooterInfo) getInfo(str, FooterInfo.class);
    }

    public MaintainInfo getMaintainInfo() {
        return (MaintainInfo) getInfo(MAINTAIN_INFO, MaintainInfo.class);
    }

    public <T> T getInfo(String str, Class<T> cls) {
        ConfigItem configItem = getConfigMap().get(str);
        if (configItem == null) {
            return null;
        }
        String str2 = configItem.mContent;
        if (!TextUtils.isEmpty(str2)) {
            return new Gson().fromJson(str2, cls);
        }
        return null;
    }

    public String getContentByConfigKey(String str) {
        if (this.mConfigData == null || this.mConfigData.get(str) == null) {
            return null;
        }
        return this.mConfigData.get(str).mContent;
    }

    public static class MaintainInfo implements Parcelable {
        public static final Parcelable.Creator<MaintainInfo> CREATOR = new Parcelable.Creator<MaintainInfo>() {
            public MaintainInfo createFromParcel(Parcel parcel) {
                return new MaintainInfo(parcel);
            }

            public MaintainInfo[] newArray(int i) {
                return new MaintainInfo[i];
            }
        };
        @SerializedName("content")
        public String mContent;
        @SerializedName("iconUrl")
        public String mIconUrl;
        @SerializedName("id")
        public String mId;
        @SerializedName("tips")
        public String mTips;
        @SerializedName("title")
        public String mTitle;

        public int describeContents() {
            return 0;
        }

        protected MaintainInfo(Parcel parcel) {
            this.mId = parcel.readString();
            this.mIconUrl = parcel.readString();
            this.mTitle = parcel.readString();
            this.mContent = parcel.readString();
            this.mTips = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mId);
            parcel.writeString(this.mIconUrl);
            parcel.writeString(this.mTitle);
            parcel.writeString(this.mContent);
            parcel.writeString(this.mTips);
        }
    }
}
