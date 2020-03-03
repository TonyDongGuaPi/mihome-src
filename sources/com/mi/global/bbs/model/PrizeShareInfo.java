package com.mi.global.bbs.model;

import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONObject;

public class PrizeShareInfo {
    private String shareDes;
    private String shareImaUrl;
    private String shareTitle;
    private String shareUrl;

    public PrizeShareInfo() {
    }

    public PrizeShareInfo(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                setNoData();
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            this.shareTitle = jSONObject.optString("shareTitle");
            this.shareDes = jSONObject.optString("shareDes");
            this.shareUrl = jSONObject.optString("shareUrl");
            this.shareImaUrl = jSONObject.optString("shareImaUrl");
        } catch (Exception unused) {
            setNoData();
        }
    }

    public String toString() {
        return "PrizeShareInfo{shareTitle='" + this.shareTitle + Operators.SINGLE_QUOTE + ", shareDes='" + this.shareDes + Operators.SINGLE_QUOTE + ", shareUrl='" + this.shareUrl + Operators.SINGLE_QUOTE + ", shareImaUrl='" + this.shareImaUrl + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    private void setNoData() {
        this.shareTitle = "Come join me on Mi Community to win!";
        this.shareDes = "Join the party and win up to 300k rupees worth of prizes.";
        this.shareUrl = ConnectionHelper.getAppIndexUrl() + "in/thread-10405-1-0.html";
        this.shareImaUrl = Constants.ShareContent.SHARE_IMG_URL;
    }

    public String getShareTitle() {
        return this.shareTitle;
    }

    public void setShareTitle(String str) {
        this.shareTitle = str;
    }

    public String getShareDes() {
        return this.shareDes;
    }

    public void setShareDes(String str) {
        this.shareDes = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getShareImaUrl() {
        return this.shareImaUrl;
    }

    public void setShareImaUrl(String str) {
        this.shareImaUrl = str;
    }
}
