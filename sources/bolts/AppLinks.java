package bolts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Map;

public final class AppLinks {

    /* renamed from: a  reason: collision with root package name */
    static final String f496a = "al_applink_data";
    static final String b = "extras";
    static final String c = "target_url";

    public static Bundle a(Intent intent) {
        return intent.getBundleExtra(f496a);
    }

    public static Bundle b(Intent intent) {
        Bundle a2 = a(intent);
        if (a2 == null) {
            return null;
        }
        return a2.getBundle("extras");
    }

    public static Uri c(Intent intent) {
        String string;
        Bundle a2 = a(intent);
        if (a2 == null || (string = a2.getString(c)) == null) {
            return intent.getData();
        }
        return Uri.parse(string);
    }

    public static Uri a(Context context, Intent intent) {
        String string;
        Bundle a2 = a(intent);
        if (a2 == null || (string = a2.getString(c)) == null) {
            return null;
        }
        MeasurementEvent.a(context, MeasurementEvent.e, intent, (Map<String, String>) null);
        return Uri.parse(string);
    }
}
