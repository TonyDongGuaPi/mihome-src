package com.mi.global.bbs.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.ui.DownloadPluginAcitvity;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.Utils;
import java.net.URI;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class DownloadPluginDialogUtil {
    public static void download(final Context context, final String str) {
        if (!LoginManager.getInstance().hasLogin()) {
            ((BaseActivity) context).gotoAccount();
        } else if (Utils.Preference.getBooleanPref(BBSApplication.getInstance(), str, false) || NetworkUtil.isWIFINetwork()) {
            startActivity(context, str);
        } else {
            List<NameValuePair> parse = URLEncodedUtils.parse(URI.create(str), "UTF-8");
            String str2 = null;
            for (int i = 0; i < parse.size(); i++) {
                BasicNameValuePair basicNameValuePair = (BasicNameValuePair) parse.get(i);
                if ("size".equals(basicNameValuePair.getName())) {
                    str2 = basicNameValuePair.getValue();
                }
            }
            DialogFactory.showAlert(context, context.getResources().getString(R.string.bbs_download_plugin_dialog_message, new Object[]{str2}), new DialogFactory.DefaultOnAlertClick() {
                public void onOkClick(View view) {
                    DownloadPluginDialogUtil.startActivity(context, str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void startActivity(Context context, String str) {
        Intent intent = new Intent(context, DownloadPluginAcitvity.class);
        intent.putExtra("url", str);
        context.startActivity(intent);
    }
}
