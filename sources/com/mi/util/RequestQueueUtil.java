package com.mi.util;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueUtil {

    /* renamed from: a  reason: collision with root package name */
    private static RequestQueue f1351a;

    public static void a(Context context) {
        f1351a = Volley.newRequestQueue(context);
    }

    public static RequestQueue a() {
        return f1351a;
    }
}
