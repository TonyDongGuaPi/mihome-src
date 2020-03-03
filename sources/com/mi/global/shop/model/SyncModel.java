package com.mi.global.shop.model;

import com.mi.global.shop.newmodel.NewPageMessage;
import com.mi.global.shop.newmodel.sync.NewSyncData;
import org.json.JSONObject;

public class SyncModel extends com.mi.model.SyncModel {
    public static NewSyncData data = null;
    public static boolean hardwareAccelerateModel = true;
    public static String[] inAppUrls = null;
    public static String[] inBrowserUrls = null;
    public static String[] inHardAccelerUrls = null;
    public static String[] inSoftWareUrls = null;
    public static JSONObject response = null;
    public static boolean useHttps = false;
    public static NewPageMessage userCenterPageMessage;
}
