package com.xiaomi.mishopsdk;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.mishopsdk.volley.Response;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestConstants;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.io.http.ShopJSONRequest;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.utils.HomepageFloadAd;
import com.xiaomi.shop2.model.BaseUserCentralCounterInfo;
import java.util.HashMap;
import java.util.Map;

public class SdkUtils {
    private static final String TAG = "SdkUtils";
    static final String _PARAM_CID = "cid";
    static final String _PARAM_PID = "pid";
    static final String _PARAM_ROOT = "root";
    static final String _SCHEME_HOST_HTTP = "http";
    static final String _SCHEME_HOST_SDK = "mishopsdk";
    static Map<String, String> mMapMijiaIds = new HashMap<String, String>() {
        {
            put("101", "99101");
            put("104", "99104");
            put(Constants.Plugin.PLUGINID_HDCHANNEL, "9915102");
        }
    };

    public interface IUserCounterInfoUpdater {
        void onUserCountInfoUpdated(BaseUserCentralCounterInfo baseUserCentralCounterInfo, boolean z);
    }

    private static class _PluginInfo {
        public String path;
        public String pid;
        public Bundle vParams;

        private _PluginInfo() {
        }
    }

    public static boolean startNewPluginActivity(Activity activity, Uri uri) {
        _PluginInfo pluginInfo = getPluginInfo(uri);
        if (pluginInfo == null) {
            return false;
        }
        if (TextUtils.isEmpty(pluginInfo.pid)) {
            pluginInfo.pid = "100";
            pluginInfo.path = null;
        }
        try {
            BasePluginFragment.Fasade.startNewPluginActivity(activity, pluginInfo.pid, pluginInfo.vParams, pluginInfo.path);
            return true;
        } catch (Exception e) {
            Log.d(TAG, "startNewPluginActivity failed.", (Object) e);
            return true;
        }
    }

    public static boolean checkPluginHomePage(Uri uri) {
        _PluginInfo pluginInfo;
        if (uri == null || (pluginInfo = getPluginInfo(uri)) == null || TextUtils.isEmpty(pluginInfo.pid)) {
            return false;
        }
        Bundle bundle = pluginInfo.vParams;
        if (bundle == null || !bundle.containsKey("noCta")) {
            return pluginInfo.pid.equalsIgnoreCase("100");
        }
        return bundle.getString("noCta").equalsIgnoreCase("true");
    }

    public static boolean checkSDKMyService(Uri uri) {
        _PluginInfo pluginInfo;
        Bundle bundle;
        if (uri == null || (pluginInfo = getPluginInfo(uri)) == null || TextUtils.isEmpty(pluginInfo.pid) || (bundle = pluginInfo.vParams) == null || !bundle.containsKey("source")) {
            return false;
        }
        return bundle.getString("source").equalsIgnoreCase("miservice");
    }

    public static View createViewOfPluginActivity(LocalActivityManager localActivityManager, Uri uri) {
        _PluginInfo pluginInfo = getPluginInfo(uri);
        if (pluginInfo == null) {
            return null;
        }
        if (TextUtils.isEmpty(pluginInfo.pid)) {
            pluginInfo.pid = "100";
            pluginInfo.path = null;
        }
        View decorView = localActivityManager.startActivity(pluginInfo.pid, BasePluginFragment.createPluginIntent(pluginInfo.pid, pluginInfo.vParams, pluginInfo.path, (String) null)).getDecorView();
        adjustActivityDecorView(decorView);
        return decorView;
    }

    public static void getUserCounterInfo(final IUserCounterInfoUpdater iUserCounterInfoUpdater) {
        ShopJSONRequest.Builder<?> builder = ShopJSONRequest.builder();
        RequestQueueManager.getInstance().addRequest(((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) ((ShopJSONRequest.Builder) builder.setUrl(HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "user/counter")).setClass(BaseUserCentralCounterInfo.class).setShouldCache(true)).setListner(new Response.SimpleListener<BaseUserCentralCounterInfo>() {
            public void onSuccess(BaseUserCentralCounterInfo baseUserCentralCounterInfo, boolean z) {
                super.onSuccess(baseUserCentralCounterInfo, z);
                if (baseUserCentralCounterInfo != null) {
                    iUserCounterInfoUpdater.onUserCountInfoUpdated(baseUserCentralCounterInfo, z);
                }
            }
        })).build());
    }

    public static void requestStartAd(Activity activity) {
        HomepageFloadAd.requestAndShowAd(activity);
    }

    public static _PluginInfo getPluginInfo(Uri uri) {
        Bundle parseUriParam = parseUriParam(uri);
        if (parseUriParam == null) {
            Log.d(TAG, "getPluginInfo, parseUriParam return null.");
            return null;
        }
        _PluginInfo _plugininfo = new _PluginInfo();
        _plugininfo.vParams = parseUriParam;
        _plugininfo.path = uri.getHost();
        if (uri.getScheme().equalsIgnoreCase("http") && _plugininfo.path.equalsIgnoreCase("m.mi.com") && parseUriParam != null) {
            _plugininfo.path = parseUriParam.getString("root");
        }
        if (parseUriParam.containsKey(_PARAM_CID)) {
            String string = parseUriParam.getString(_PARAM_CID);
            if (RequestConstants.SDK_Channel.isChannelIdValid(string)) {
                RequestConstants.SDK_Channel.channel_id = string;
            }
        }
        ShopApp.channelId = RequestConstants.SDK_Channel.channel_id;
        if (parseUriParam.containsKey("pid")) {
            _plugininfo.pid = parseUriParam.getString("pid");
        }
        return _plugininfo;
    }

    private static Bundle parseUriParam(Uri uri) {
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return null;
        }
        if (!scheme.equalsIgnoreCase(_SCHEME_HOST_SDK) && !scheme.equalsIgnoreCase("http")) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (Build.VERSION.SDK_INT >= 11) {
            for (String next : uri.getQueryParameterNames()) {
                String queryParameter = uri.getQueryParameter(next);
                if (!TextUtils.isEmpty(queryParameter)) {
                    bundle.putString(next, queryParameter);
                }
            }
        }
        initAddCartPath(bundle);
        return bundle;
    }

    private static void initAddCartPath(Bundle bundle) {
        if (bundle != null && bundle.containsKey(_PARAM_CID) && TextUtils.isEmpty(bundle.getString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS))) {
            bundle.putString(Constants.Plugin.ARGUMENT_PLUGINPREVIOUS, bundle.getString(_PARAM_CID));
        }
    }

    private static void adjustActivityDecorView(View view) {
        try {
            ViewGroup viewGroup = (ViewGroup) view;
            ViewGroup viewGroup2 = (ViewGroup) forceFindSubViewById(viewGroup, 16908300);
            if (viewGroup2 != null) {
                ViewGroup viewGroup3 = (ViewGroup) forceFindSubViewById(viewGroup2, 16908300);
                if (viewGroup3 == null) {
                    viewGroup3 = viewGroup2;
                }
                ((ViewGroup) viewGroup3.getParent()).removeAllViews();
                viewGroup.removeAllViews();
                viewGroup.addView(viewGroup3);
                return;
            }
            Log.d(TAG, "find directView failed, adjustActivityDecorView failed.");
        } catch (Exception e) {
            Log.d(TAG, "adjustActivityDecorView failed.", (Object) e);
        }
    }

    private static View forceFindSubViewById(ViewGroup viewGroup, int i) {
        if (i < 0) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getId() == i) {
                view = childAt;
            } else if (childAt instanceof ViewGroup) {
                view = forceFindSubViewById((ViewGroup) childAt, i);
            }
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    public static boolean updateMijiaMode() {
        RequestConstants.updateChannel();
        return true;
    }

    public static String mapMijiaPluginId(String str) {
        return (!TextUtils.isEmpty(str) && mMapMijiaIds.containsKey(str)) ? mMapMijiaIds.get(str) : str;
    }

    public static String getClientId() {
        return AndroidUtil.getMetaDataString("CLIENT_ID").substring(1);
    }

    public static String getChannelId() {
        String substring = AndroidUtil.getMetaDataString("CHANNEL_ID").substring(1);
        if (ShopApp.isMijiaMode) {
            String substring2 = AndroidUtil.getMetaDataString("MIJIA_CHANNEL_ID").substring(1);
            if (!TextUtils.isEmpty(substring2)) {
                substring = substring2;
            }
        }
        return TextUtils.isEmpty(substring) ? "0000.0000" : substring;
    }

    public static String getChannelIdPre() {
        String metaDataString = AndroidUtil.getMetaDataString("CHANNEL_ID_PRE");
        if (!ShopApp.isMijiaMode) {
            return metaDataString;
        }
        String substring = AndroidUtil.getMetaDataString("MIJIA_CHANNEL_ID_PRE").substring(1);
        return !TextUtils.isEmpty(substring) ? substring : metaDataString;
    }
}
