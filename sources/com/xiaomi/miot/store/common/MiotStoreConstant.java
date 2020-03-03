package com.xiaomi.miot.store.common;

import android.os.Environment;
import com.taobao.weex.adapter.URIAdapter;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.File;

public class MiotStoreConstant {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11377a = "com.xiaomi.miot.store.action.COMMON_EVENT";
    public static final String b = "com.xiaomi.miot.store.extra.COMMON_EVENT";
    public static final String c = "com.xiaomi.miot.store.action.OPEN_ACTIVITY";
    public static final String d = "action_dismiss_main_rn_mask_view";
    public static final String e = "action_show_main_rn_mask_view";
    public static final String f = "broadcast_notify_datachanged";
    public static String g = (Environment.getExternalStorageDirectory().getPath() + File.separator + "MiotStore");
    public static String h = (g + File.separator + URIAdapter.BUNDLE);
    public static String i = "com_xiaomi_miot_store";
    public static String j = "StoreETag";
    public static String k = "JSMD5";
    public static String l = DTransferConstants.l;
    public static String m = "last_rn_version";
    public static String n = "last_rn_version_error_count";
    public static String o = "last_update_time";
    public static String p = "rn_version_and_etag";
    public static long q = 60000;
}
