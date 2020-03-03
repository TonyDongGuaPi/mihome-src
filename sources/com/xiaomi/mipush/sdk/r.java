package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.push.iz;

public class r {
    public static <T extends iz<T, ?>> void a(Context context, Config config) {
        if (config != null) {
            Intent intent = new Intent();
            intent.setAction("action_cr_config");
            intent.putExtra("action_cr_event_switch", config.c());
            intent.putExtra("action_cr_event_frequency", config.f());
            intent.putExtra("action_cr_perf_switch", config.d());
            intent.putExtra("action_cr_perf_frequency", config.g());
            intent.putExtra("action_cr_event_en", config.b());
            intent.putExtra("action_cr_max_file_size", config.e());
            aw.a(context).a(intent);
        }
    }
}
