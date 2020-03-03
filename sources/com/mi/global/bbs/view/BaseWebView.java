package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import com.mi.log.LogUtil;
import java.util.ArrayList;

public class BaseWebView extends WebView {
    public ArrayList<String> urlHistorys = new ArrayList<>();

    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public String getLastUrl() {
        WebHistoryItem itemAtIndex;
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        if (copyBackForwardList == null || copyBackForwardList.getSize() <= 1 || (itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1)) == null) {
            return "";
        }
        return itemAtIndex.getUrl();
    }

    public String getLastTitle() {
        WebHistoryItem itemAtIndex;
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        if (copyBackForwardList == null || copyBackForwardList.getSize() <= 1 || (itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1)) == null) {
            return "";
        }
        return itemAtIndex.getTitle();
    }

    public String getCurrentUrl() {
        WebHistoryItem itemAtIndex;
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        if (copyBackForwardList == null || copyBackForwardList.getSize() <= 1 || (itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex())) == null) {
            return "";
        }
        return itemAtIndex.getUrl();
    }

    public String getCurrentTitle() {
        WebHistoryItem itemAtIndex;
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        if (copyBackForwardList == null || copyBackForwardList.getSize() <= 1 || (itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex())) == null) {
            return "";
        }
        return itemAtIndex.getTitle();
    }

    public void printHistoryList() {
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        if (copyBackForwardList.getSize() > 1) {
            for (int i = 0; i < copyBackForwardList.getCurrentIndex(); i++) {
                WebHistoryItem itemAtIndex = copyBackForwardList.getItemAtIndex(i);
                LogUtil.b("BaseWebView", "currentIndex:" + copyBackForwardList.getCurrentIndex() + ", index:" + i + ", url:" + itemAtIndex.getUrl());
            }
        }
    }
}
