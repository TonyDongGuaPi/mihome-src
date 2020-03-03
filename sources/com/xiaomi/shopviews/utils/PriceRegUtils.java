package com.xiaomi.shopviews.utils;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.xiaomi.base.utils.TopAlignSuperscriptSpan;
import com.xiaomi.shopviews.model.HomeSectionItem;

public class PriceRegUtils {
    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && !str.equals("0");
    }

    public static boolean a(String str, String str2) {
        return !TextUtils.equals(str, str2) && a(str2);
    }

    public static SpannableString b(String str) {
        SpannableString spannableString = new SpannableString("?" + str);
        spannableString.setSpan(new StrikethroughSpan(), 1, spannableString.length(), 33);
        return spannableString;
    }

    public static SpannableString c(String str) {
        SpannableString spannableString = new SpannableString("?" + str);
        spannableString.setSpan(new TopAlignSuperscriptSpan(0.35f), 0, 1, 33);
        return spannableString;
    }

    public static void a(TextView textView, TextView textView2, TextView textView3, HomeSectionItem homeSectionItem) {
        if (textView != null && textView2 != null && textView3 != null && homeSectionItem != null) {
            if (a(homeSectionItem.mProductPrice)) {
                SpannableString c = c(homeSectionItem.mProductPrice);
                c.setSpan(new StyleSpan(1), 0, c.length(), 18);
                textView.setText(c);
                if (homeSectionItem.showPriceQi) {
                    textView3.setVisibility(0);
                } else {
                    textView3.setVisibility(8);
                }
                if (a(homeSectionItem.mProductPrice, homeSectionItem.mProductMarketPrice)) {
                    textView2.setVisibility(0);
                    textView2.setText(b(homeSectionItem.mProductMarketPrice));
                    return;
                }
                textView2.setVisibility(8);
                return;
            }
            textView.setText("");
            textView3.setVisibility(4);
            textView2.setVisibility(4);
        }
    }

    public static void a(TextView textView, TextView textView2, TextView textView3, HomeSectionItem homeSectionItem, int i) {
        textView.setTextColor(i);
        textView3.setTextColor(i);
        a(textView, textView2, textView3, homeSectionItem);
    }

    public static void b(TextView textView, TextView textView2, TextView textView3, HomeSectionItem homeSectionItem) {
        if (textView != null && textView2 != null && textView3 != null && homeSectionItem != null) {
            if (a(homeSectionItem.mProductPrice)) {
                textView.setText(c(homeSectionItem.mProductPrice));
                if (homeSectionItem.showPriceQi) {
                    textView3.setVisibility(0);
                } else {
                    textView3.setVisibility(8);
                }
                if (a(homeSectionItem.mProductPrice, homeSectionItem.mProductMarketPrice)) {
                    textView2.setVisibility(0);
                    textView2.setText(b(homeSectionItem.mProductMarketPrice));
                    return;
                }
                textView2.setVisibility(4);
                return;
            }
            textView.setText("");
            textView3.setVisibility(4);
            textView2.setVisibility(4);
        }
    }

    public static void a(TextView textView, TextView textView2, String str) {
        if (textView == null) {
            return;
        }
        if (a(str)) {
            textView.setText(str);
            return;
        }
        textView.setText("");
        if (textView2 != null) {
            textView2.setVisibility(4);
        }
    }

    public static void a(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (a(str)) {
            textView.setText(str);
        } else {
            textView.setText("");
        }
    }

    public static void c(TextView textView, TextView textView2, TextView textView3, HomeSectionItem homeSectionItem) {
        if (textView != null && textView2 != null && textView3 != null && homeSectionItem != null) {
            if (a(homeSectionItem.mProductPrice)) {
                textView.setText(c(homeSectionItem.mProductPrice));
                if (homeSectionItem.showPriceQi) {
                    textView3.setVisibility(0);
                } else {
                    textView3.setVisibility(8);
                }
                if (a(homeSectionItem.mProductPrice, homeSectionItem.mProductMarketPrice)) {
                    textView2.setVisibility(0);
                    textView2.setText(b(homeSectionItem.mProductMarketPrice));
                    return;
                }
                textView2.setVisibility(8);
                return;
            }
            textView.setText("");
            textView3.setVisibility(4);
            textView2.setVisibility(4);
        }
    }
}
