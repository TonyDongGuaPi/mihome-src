package com.xiaomi.shopviews.model;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;
import java.util.ArrayList;

public class HomeSectionItem implements Serializable {
    private static final long serialVersionUID = 8845429397605358753L;
    public String award_desc;
    public String big_img_url;
    public String btn_type;
    public String desc_begin;
    public String desc_will_begin;
    public ArrayList<String> img_url_array;
    public String img_url_start;
    public String img_url_unstart;
    public boolean isRecord;
    public boolean isRequesting;
    public boolean isSubmergeInGallery;
    public String live_code;
    public HomeAction mAction;
    public HomeAction mActionAward;
    public String mActionDesc;
    public String mActionTitle;
    public String mActivityCode;
    public String mBannerImgUrl;
    public String mBgColorRect;
    public String mBgColorSquare;
    public String mBtnTxt;
    public String mButtonText;
    public int mButtonType;
    public boolean mCanGet;
    public String mCategoryId;
    public String mCategoryMoreName;
    public String mCategoryName;
    public String mCategoryTitle;
    public String mCategoryTitleName;
    public int mColumnSpan;
    public int mColumnStart;
    public String mCommentAuthor;
    public String mCommentBrief;
    public float mCommentStar;
    public String mCommentTotal;
    public String mCountDownEndUrl;
    public String mCountDownTitleUrl;
    public String mDateDesc;
    public String mDesc;
    public long mEndTime;
    public String mFavorDesc;
    public String mGroupId;
    public int mHeight;
    public String mImageSelectUrl;
    public String mImageUrl;
    public ArrayList<HomeSectionItem> mItems;
    public String mLabelContent;
    public String mLabelTitle;
    public String mLogCode;
    public String mMenuTip;
    public String mMoreName;
    public String mNewImageUrl;
    public String mNewsId;
    public String mNoticeTitle;
    public int mPosition;
    public String mPreferential;
    public String mProductBrief;
    public String mProductId;
    public String mProductMarketPrice;
    public String mProductName;
    public String mProductPrice;
    public String mProductTag;
    public ArrayList<String> mProductTagArray;
    public String mProductTip;
    public int mProgress;
    public int mRank;
    public int mRowSpan;
    public int mRowStart;
    public long mServerTime;
    public String mServiceName;
    public String mSmallImgUrl;
    public long mStartTime;
    public String mSubTitle;
    public ArrayList<HomeTags> mTags;
    public String mText;
    public String mTextAlign;
    public boolean mTextBold;
    public String mTextColor;
    public String mTextColorMill;
    public String mTextColorTime;
    public String mTextColorUnit;
    public int mTextLines;
    public float mTextSize;
    public String mTitle;
    public String mType;
    public String mUrl;
    public String mVNavNormalColor;
    public String mVNavSeletedColor;
    public String mVideoUrl;
    public int mWidth;
    public int mX;
    public int mY;
    public String not_start_title;
    public String notice;
    public boolean notice_action;
    public String qrcode_img;
    public String qrcode_title;
    public String red_award_txt;
    public String red_bottom_txt;
    public String red_btn_txt;
    public String sell_out_img;
    public String share_content;
    public String share_desc;
    public String share_img;
    public String share_link;
    public int share_lottery_num;
    public String share_title;
    public String share_xcx_img;
    public String share_xcx_link;
    public String share_xcx_title;
    public boolean showPriceQi;
    public boolean slide_not_show;
    public String start_title;
    public String subtitle_begin;
    public String subtitle_end;
    public String subtitle_will_begin;
    public String title_end;
    public String title_future;
    public String title_today;
    public String title_tomorrow;

    public static void performItemClick(HomeSectionItem homeSectionItem) {
    }

    public void setMainImgUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mImageUrl = str;
        }
    }

    public void setPlayUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mVideoUrl = str;
        }
    }

    public void setVideoUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mVideoUrl = str;
        }
    }

    public String toString() {
        return "HomeSectionItem{mAction=" + this.mAction + ", mImageUrl='" + this.mImageUrl + Operators.SINGLE_QUOTE + ", mRowStart=" + this.mRowStart + ", mColumnStart=" + this.mColumnStart + ", mRowSpan=" + this.mRowSpan + ", mColumnSpan=" + this.mColumnSpan + ", mProductName='" + this.mProductName + Operators.SINGLE_QUOTE + ", mProductPrice='" + this.mProductPrice + Operators.SINGLE_QUOTE + ", mProductMarketPrice='" + this.mProductMarketPrice + Operators.SINGLE_QUOTE + ", mPreferential='" + this.mPreferential + Operators.SINGLE_QUOTE + ", mRank=" + this.mRank + ", mCommentStar=" + this.mCommentStar + ", mCommentTotal='" + this.mCommentTotal + Operators.SINGLE_QUOTE + ", mCommentBrief='" + this.mCommentBrief + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
