package com.xiaomi.mishopsdk.utils;

import android.content.Context;
import android.os.Handler;
import com.mishopsdk.volley.DefaultRetryPolicy;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.toolbox.Volley;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.utils.FileDownloadQuest;
import java.io.File;
import java.util.HashMap;

public class PluginQueueManager extends RequestQueueManager {
    private static PluginQueueManager pluginInstance;
    private final String TAG = "PluginQueueManager";

    public static PluginQueueManager getQueueInstance() {
        if (pluginInstance == null) {
            pluginInstance = new PluginQueueManager();
        }
        return pluginInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mRequestQueue = Volley.newRequestQueue(context);
    }

    public void downLoadFile(Object obj, String str, HashMap<String, String> hashMap, String str2, Handler handler, Response.Listener<File> listener) {
        try {
            FileDownloadQuest build = ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) ((FileDownloadQuest.Builder) FileDownloadQuest.builder().setUrl(str)).setRequestBody(hashMap != null ? hashMap.toString().getBytes("utf-8") : null)).setListner(listener)).setMethod(0)).setShouldCache(false)).setTag(obj)).setPriority(Request.Priority.LOW)).setFileLocalPath(str2).setDownloadHander(handler).build();
            build.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 1.0f));
            this.mRequestQueue.add(build);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
