package com.xiaomi.youpin.youpin_common.widget.video;

import android.content.Context;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.xiaomi.youpin.youpin_common.UserAgent;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import java.util.Map;
import okhttp3.Call;

public class DataSourceUtil {

    /* renamed from: a  reason: collision with root package name */
    private static DataSource.Factory f23827a;
    private static DataSource.Factory b;
    private static String c;

    private DataSourceUtil() {
    }

    public static void a(String str) {
        c = str;
    }

    public static String a(Context context) {
        if (c == null) {
            c = UserAgent.d();
        }
        return c;
    }

    public static DataSource.Factory b(Context context) {
        if (f23827a == null) {
            f23827a = c(context);
        }
        return f23827a;
    }

    public static void a(DataSource.Factory factory) {
        f23827a = factory;
    }

    public static DataSource.Factory a(Context context, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        if (b == null || (map != null && !map.isEmpty())) {
            b = b(context, defaultBandwidthMeter, map);
        }
        return b;
    }

    public static void b(DataSource.Factory factory) {
        b = factory;
    }

    private static DataSource.Factory c(Context context) {
        return new RawResourceDataSourceFactory(context.getApplicationContext());
    }

    private static DataSource.Factory b(Context context, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        return new DefaultDataSourceFactory(context, (TransferListener) defaultBandwidthMeter, (DataSource.Factory) c(context, defaultBandwidthMeter, map));
    }

    private static HttpDataSource.Factory c(Context context, DefaultBandwidthMeter defaultBandwidthMeter, Map<String, String> map) {
        OkHttpDataSourceFactory okHttpDataSourceFactory = new OkHttpDataSourceFactory((Call.Factory) YouPinHttpsApi.a().b(), a(context), (TransferListener) defaultBandwidthMeter);
        if (map != null) {
            okHttpDataSourceFactory.getDefaultRequestProperties().set(map);
        }
        return okHttpDataSourceFactory;
    }
}
