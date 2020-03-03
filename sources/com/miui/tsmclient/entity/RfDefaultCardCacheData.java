package com.miui.tsmclient.entity;

import android.text.TextUtils;

public class RfDefaultCardCacheData {
    private static final String SEPARATOR = "&";
    private String mAid;
    private int mCardGroupId;

    public RfDefaultCardCacheData(CardInfo cardInfo) {
        if (cardInfo != null) {
            this.mAid = cardInfo.mAid;
            this.mCardGroupId = cardInfo.mCardGroupId;
            return;
        }
        this.mAid = "none";
    }

    public RfDefaultCardCacheData(String str) {
        String[] split;
        if (!TextUtils.isEmpty(str) && (split = str.split("&")) != null && split.length > 0) {
            this.mAid = split[0];
            if (split.length > 1 && TextUtils.isDigitsOnly(split[1])) {
                this.mCardGroupId = Integer.valueOf(split[1]).intValue();
            }
        }
    }

    public String buildCacheStr() {
        return this.mAid + "&" + this.mCardGroupId;
    }

    public CardInfo getCardInfo() {
        CardInfo cardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);
        cardInfo.mAid = this.mAid;
        cardInfo.mCardGroupId = this.mCardGroupId;
        return cardInfo;
    }

    public String getAid() {
        return this.mAid;
    }

    public CardGroupType getCardGroupType() {
        return CardGroupType.newInstance(this.mCardGroupId);
    }
}
