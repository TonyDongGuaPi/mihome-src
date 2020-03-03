package com.miui.tsmclient.entity;

import com.miui.tsmclient.util.FormatterUtils;

public class BankCardInputItemInfo {
    private String mContent;
    private FormatterUtils.FormatterType mFormatType;
    private boolean mShowFAQIcon = true;
    private String mTitle;
    private ItemType mType;

    public enum ItemType {
        CARD_NUM(0),
        VALID(1),
        CVV2(2),
        PASSWORD(3),
        PHONE(4);
        
        private int viewPosition;

        private ItemType(int i) {
            this.viewPosition = i;
        }

        public int getViewPosition() {
            return this.viewPosition;
        }
    }

    public ItemType getType() {
        return this.mType;
    }

    public void setType(ItemType itemType) {
        this.mType = itemType;
        switch (itemType) {
            case CARD_NUM:
                this.mFormatType = FormatterUtils.FormatterType.TYPE_NORMAL;
                return;
            case VALID:
                this.mFormatType = FormatterUtils.FormatterType.TYPE_VALID_DATE;
                return;
            case PHONE:
                this.mFormatType = FormatterUtils.FormatterType.TYPE_PHONE;
                return;
            default:
                return;
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getContent() {
        return this.mContent == null ? "" : this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public FormatterUtils.FormatterType getFormatType() {
        return this.mFormatType;
    }
}
