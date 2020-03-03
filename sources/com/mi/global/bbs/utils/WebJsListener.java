package com.mi.global.bbs.utils;

import android.webkit.JavascriptInterface;

public interface WebJsListener {
    @JavascriptInterface
    void disableRefresh(boolean z);

    @JavascriptInterface
    void finishCurrentPage();

    @JavascriptInterface
    int getAppVersion();

    @JavascriptInterface
    void getHeaderMenu(String str);

    @JavascriptInterface
    void onLogOut();

    @JavascriptInterface
    void routeToComments();

    @JavascriptInterface
    void routeToMedal();

    @JavascriptInterface
    void routeToPlate(String str, String str2);

    @JavascriptInterface
    void routeToUserCenter(String str);

    @JavascriptInterface
    void shareEvent(String str);

    @JavascriptInterface
    void showLikeAndComments(String str, String str2, String str3, String str4);

    @JavascriptInterface
    void showReplyPanel(String str, String str2);

    @JavascriptInterface
    void showReplyPersonPanel(String str, String str2, String str3, String str4);

    @JavascriptInterface
    void takePicture();

    @JavascriptInterface
    void watchBigPics(String[] strArr, String str);
}
