package com.mi.global.shop.model;

import android.content.Context;
import android.text.TextUtils;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.util.HomeThemeItemClick;
import org.json.JSONObject;

public class AdInfo {
    public int duration;
    private String mIsNeedLogin;
    private String mKeyword;
    public String mLinkUrl;
    public String mOpenType;
    public String mProductId;
    public String picUrl;
    public String text;

    public static AdInfo parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        AdInfo adInfo = new AdInfo();
        adInfo.text = jSONObject.optString("text");
        adInfo.mOpenType = jSONObject.optString(Tags.ServicesInfo.SERVICES_INFO_OPEN_TYPE);
        adInfo.mLinkUrl = jSONObject.optString(Tags.ServicesInfo.SERVICES_INFO_LINK_URL);
        adInfo.mProductId = jSONObject.optString("product_id");
        adInfo.mKeyword = jSONObject.optString("keyword");
        adInfo.mIsNeedLogin = jSONObject.optString("is_login");
        adInfo.duration = jSONObject.optInt("duration", 3);
        if (!TextUtils.isEmpty(adInfo.mLinkUrl) || !TextUtils.isEmpty(adInfo.mProductId) || !TextUtils.isEmpty(adInfo.mKeyword)) {
            return adInfo;
        }
        return null;
    }

    public void handleClick(Context context) {
        HomeThemeItem homeThemeItem = new HomeThemeItem();
        homeThemeItem.mOpenType = this.mOpenType;
        homeThemeItem.mLinkUrl = this.mLinkUrl;
        homeThemeItem.mProductId = this.mProductId;
        homeThemeItem.mKeyword = this.mKeyword;
        homeThemeItem.mIsNeedLogin = this.mIsNeedLogin;
        HomeThemeItemClick.a(context, homeThemeItem);
    }
}
