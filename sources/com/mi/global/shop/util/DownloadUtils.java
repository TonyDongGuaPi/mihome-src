package com.mi.global.shop.util;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.webkit.URLUtil;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.log.LogUtil;

public class DownloadUtils {
    public static long a(Context context, String str, String str2, String str3) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        String b = WebViewCookieManager.b(ConnectionHelper.E);
        LogUtil.b(b);
        request.addRequestHeader("Cookie", b);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        if (Build.VERSION.SDK_INT >= 16) {
            request.setAllowedOverMetered(false);
        }
        request.setVisibleInDownloadsUi(true);
        request.setAllowedOverRoaming(true);
        request.setAllowedNetworkTypes(2);
        String guessFileName = URLUtil.guessFileName(str, str2, str3);
        LogUtil.b("fileName:" + guessFileName);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, guessFileName);
        return ((DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).enqueue(request);
    }

    public static int a(Context context, long j) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(new long[]{j});
        Cursor query2 = ((DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).query(query);
        if (!query2.moveToFirst()) {
            query2.close();
            return -1;
        }
        int i = query2.getInt(query2.getColumnIndex("status"));
        query2.close();
        return i;
    }
}
