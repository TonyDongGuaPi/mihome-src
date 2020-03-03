package com.xiaomi.mishopsdk.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.event.GoLoginEvent;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.JSONUtil;
import com.xiaomi.mobilestats.StatService;
import de.greenrobot.event.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ItemActionClick {
    public static String OPEN_TYPE_BROWSER = "open_web";
    public static String OPEN_TYPE_INNER_WEB = "inner_web";
    public static String OPEN_TYPE_PLUGIN = "plugin";
    private static final String TAG = "HomeThemeItemClick";

    private ItemActionClick() {
    }

    public static void performActionClick(Context context, ItemAction itemAction) {
        if (itemAction != null && !itemAction.isEmpty()) {
            if (!itemAction.isNeedLogin() || LoginManager.getInstance().hasLogin()) {
                handleAction(context, itemAction);
            } else {
                EventBus.getDefault().post(new GoLoginEvent());
            }
        }
    }

    private static void handleAction(Context context, ItemAction itemAction) {
        String str = itemAction.mType;
        String str2 = itemAction.mPath;
        if (OPEN_TYPE_PLUGIN.equals(str)) {
            try {
                BasePluginFragment.startNewPlugin((Activity) context, new JSONObject(JSONUtil.format(itemAction)), new Bundle());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (OPEN_TYPE_INNER_WEB.equals(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str2);
            BasePluginFragment.Fasade.startNewPluginActivity((Activity) context, Constants.Plugin.PLUGINID_WEBVIEW, bundle);
        } else if (OPEN_TYPE_BROWSER.equals(str)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str2));
            context.startActivity(intent);
        }
        if (!TextUtils.isEmpty(itemAction.mLogCode)) {
            StatService.onEvent(context, itemAction.mLogCode, "");
        }
    }

    protected static Bundle getPathHost(String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str) || str.indexOf("?") == -1 || str.indexOf("?") == str.length()) {
            return bundle;
        }
        String substring = str.substring(str.indexOf("?") + 1, str.length());
        if (!TextUtils.isEmpty(substring)) {
            String[] split = substring.split(a.b);
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3.length == 2) {
                    bundle.putString(split3[0], split3[1]);
                }
            }
        }
        return bundle;
    }
}
