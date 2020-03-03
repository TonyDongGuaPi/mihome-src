package bolts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.xiaomi.smarthome.constants.AppConstants;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MeasurementEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final String f505a = "com.parse.bolts.measurement_event";
    public static final String b = "event_name";
    public static final String c = "event_args";
    public static final String d = "al_nav_out";
    public static final String e = "al_nav_in";
    private Context f;
    private String g;
    private Bundle h;

    static void a(Context context, String str, Intent intent, Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (intent != null) {
            Bundle a2 = AppLinks.a(intent);
            if (a2 != null) {
                bundle = a(context, str, a2, intent);
            } else {
                Uri data = intent.getData();
                if (data != null) {
                    bundle.putString("intentData", data.toString());
                }
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str2 : extras.keySet()) {
                        bundle.putString(str2, a(extras.get(str2)));
                    }
                }
            }
        }
        if (map != null) {
            for (String next : map.keySet()) {
                bundle.putString(next, map.get(next));
            }
        }
        new MeasurementEvent(context, str, bundle).a();
    }

    private MeasurementEvent(Context context, String str, Bundle bundle) {
        this.f = context.getApplicationContext();
        this.g = str;
        this.h = bundle;
    }

    private void a() {
        if (this.g == null) {
            Log.d(getClass().getName(), "Event name is required");
        }
        try {
            Class<?> cls = Class.forName("android.support.v4.content.LocalBroadcastManager");
            Method method = cls.getMethod("getInstance", new Class[]{Context.class});
            Method method2 = cls.getMethod("sendBroadcast", new Class[]{Intent.class});
            Object invoke = method.invoke((Object) null, new Object[]{this.f});
            Intent intent = new Intent(f505a);
            intent.putExtra(b, this.g);
            intent.putExtra(c, this.h);
            method2.invoke(invoke, new Object[]{intent});
        } catch (Exception unused) {
            Log.d(getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
        }
    }

    private static Bundle a(Context context, String str, Bundle bundle, Intent intent) {
        Bundle bundle2 = new Bundle();
        ComponentName resolveActivity = intent.resolveActivity(context.getPackageManager());
        if (resolveActivity != null) {
            bundle2.putString(AppConstants.x, resolveActivity.getShortClassName());
        }
        if (d.equals(str)) {
            if (resolveActivity != null) {
                bundle2.putString("package", resolveActivity.getPackageName());
            }
            if (intent.getData() != null) {
                bundle2.putString("outputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("outputURLScheme", intent.getScheme());
            }
        } else if (e.equals(str)) {
            if (intent.getData() != null) {
                bundle2.putString("inputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("inputURLScheme", intent.getScheme());
            }
        }
        for (String str2 : bundle.keySet()) {
            Object obj = bundle.get(str2);
            if (obj instanceof Bundle) {
                Bundle bundle3 = (Bundle) obj;
                for (String str3 : bundle3.keySet()) {
                    String a2 = a(bundle3.get(str3));
                    if (str2.equals("referer_app_link")) {
                        if (str3.equalsIgnoreCase("url")) {
                            bundle2.putString("refererURL", a2);
                        } else if (str3.equalsIgnoreCase("app_name")) {
                            bundle2.putString("refererAppName", a2);
                        } else if (str3.equalsIgnoreCase("package")) {
                            bundle2.putString("sourceApplication", a2);
                        }
                    }
                    bundle2.putString(str2 + "/" + str3, a2);
                }
            } else {
                String a3 = a(obj);
                if (str2.equals("target_url")) {
                    Uri parse = Uri.parse(a3);
                    bundle2.putString("targetURL", parse.toString());
                    bundle2.putString("targetURLHost", parse.getHost());
                } else {
                    bundle2.putString(str2, a3);
                }
            }
        }
        return bundle2;
    }

    private static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj.toString();
        }
        try {
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj).toString();
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj).toString();
            }
            return obj.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
