package com.xiaomi.jr.deeplink;

import android.content.Intent;
import android.util.SparseArray;

public class CustomDeeplinkHandler {
    public static final String KEY_HANDLER_ID = "handler_id";
    private static SparseArray<Handler> sHandlerMap = new SparseArray<>();

    public interface Handler {
        void handle(Object obj, Intent intent);
    }

    public static void addHandler(Handler handler) {
        sHandlerMap.put(handler.hashCode(), handler);
    }

    public static void handleIntent(Object obj, Intent intent) {
        Handler handler = sHandlerMap.get(intent.getIntExtra(KEY_HANDLER_ID, 0));
        if (handler != null) {
            handler.handle(obj, intent);
        }
    }
}
