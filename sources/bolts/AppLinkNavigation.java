package bolts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import bolts.AppLink;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkNavigation {

    /* renamed from: a  reason: collision with root package name */
    private static final String f494a = "user_agent";
    private static final String b = "version";
    private static final String c = "referer_app_link";
    private static final String d = "app_name";
    private static final String e = "package";
    private static final String f = "1.0";
    private static AppLinkResolver g;
    private final AppLink h;
    private final Bundle i;
    private final Bundle j;

    public enum NavigationResult {
        FAILED("failed", false),
        WEB("web", true),
        APP("app", true);
        
        private String code;
        private boolean succeeded;

        public String getCode() {
            return this.code;
        }

        public boolean isSucceeded() {
            return this.succeeded;
        }

        private NavigationResult(String str, boolean z) {
            this.code = str;
            this.succeeded = z;
        }
    }

    public AppLinkNavigation(AppLink appLink, Bundle bundle, Bundle bundle2) {
        if (appLink != null) {
            bundle = bundle == null ? new Bundle() : bundle;
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            this.h = appLink;
            this.i = bundle;
            this.j = bundle2;
            return;
        }
        throw new IllegalArgumentException("appLink must not be null.");
    }

    public AppLink a() {
        return this.h;
    }

    public Bundle b() {
        return this.j;
    }

    public Bundle c() {
        return this.i;
    }

    private Bundle b(Context context) {
        String string;
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString("package", packageName);
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (!(applicationInfo == null || (string = context.getString(applicationInfo.labelRes)) == null)) {
                bundle2.putString("app_name", string);
            }
        }
        bundle.putAll(b());
        bundle.putString("target_url", a().a().toString());
        bundle.putString("version", "1.0");
        bundle.putString("user_agent", "Bolts Android 1.4.0");
        bundle.putBundle(c, bundle2);
        bundle.putBundle("extras", c());
        return bundle;
    }

    private Object a(Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            return a((Bundle) obj);
        }
        if (obj instanceof CharSequence) {
            return obj.toString();
        }
        if (obj instanceof List) {
            JSONArray jSONArray = new JSONArray();
            for (Object a2 : (List) obj) {
                jSONArray.put(a(a2));
            }
            return jSONArray;
        }
        int i2 = 0;
        if (obj instanceof SparseArray) {
            JSONArray jSONArray2 = new JSONArray();
            SparseArray sparseArray = (SparseArray) obj;
            while (i2 < sparseArray.size()) {
                jSONArray2.put(sparseArray.keyAt(i2), a(sparseArray.valueAt(i2)));
                i2++;
            }
            return jSONArray2;
        } else if (obj instanceof Character) {
            return obj.toString();
        } else {
            if (obj instanceof Boolean) {
                return obj;
            }
            if (obj instanceof Number) {
                if ((obj instanceof Double) || (obj instanceof Float)) {
                    return Double.valueOf(((Number) obj).doubleValue());
                }
                return Long.valueOf(((Number) obj).longValue());
            } else if (obj instanceof boolean[]) {
                JSONArray jSONArray3 = new JSONArray();
                boolean[] zArr = (boolean[]) obj;
                int length = zArr.length;
                while (i2 < length) {
                    jSONArray3.put(a((Object) Boolean.valueOf(zArr[i2])));
                    i2++;
                }
                return jSONArray3;
            } else if (obj instanceof char[]) {
                JSONArray jSONArray4 = new JSONArray();
                char[] cArr = (char[]) obj;
                int length2 = cArr.length;
                while (i2 < length2) {
                    jSONArray4.put(a((Object) Character.valueOf(cArr[i2])));
                    i2++;
                }
                return jSONArray4;
            } else if (obj instanceof CharSequence[]) {
                JSONArray jSONArray5 = new JSONArray();
                CharSequence[] charSequenceArr = (CharSequence[]) obj;
                int length3 = charSequenceArr.length;
                while (i2 < length3) {
                    jSONArray5.put(a((Object) charSequenceArr[i2]));
                    i2++;
                }
                return jSONArray5;
            } else if (obj instanceof double[]) {
                JSONArray jSONArray6 = new JSONArray();
                double[] dArr = (double[]) obj;
                int length4 = dArr.length;
                while (i2 < length4) {
                    jSONArray6.put(a((Object) Double.valueOf(dArr[i2])));
                    i2++;
                }
                return jSONArray6;
            } else if (obj instanceof float[]) {
                JSONArray jSONArray7 = new JSONArray();
                float[] fArr = (float[]) obj;
                int length5 = fArr.length;
                while (i2 < length5) {
                    jSONArray7.put(a((Object) Float.valueOf(fArr[i2])));
                    i2++;
                }
                return jSONArray7;
            } else if (obj instanceof int[]) {
                JSONArray jSONArray8 = new JSONArray();
                int[] iArr = (int[]) obj;
                int length6 = iArr.length;
                while (i2 < length6) {
                    jSONArray8.put(a((Object) Integer.valueOf(iArr[i2])));
                    i2++;
                }
                return jSONArray8;
            } else if (obj instanceof long[]) {
                JSONArray jSONArray9 = new JSONArray();
                long[] jArr = (long[]) obj;
                int length7 = jArr.length;
                while (i2 < length7) {
                    jSONArray9.put(a((Object) Long.valueOf(jArr[i2])));
                    i2++;
                }
                return jSONArray9;
            } else if (obj instanceof short[]) {
                JSONArray jSONArray10 = new JSONArray();
                short[] sArr = (short[]) obj;
                int length8 = sArr.length;
                while (i2 < length8) {
                    jSONArray10.put(a((Object) Short.valueOf(sArr[i2])));
                    i2++;
                }
                return jSONArray10;
            } else if (!(obj instanceof String[])) {
                return null;
            } else {
                JSONArray jSONArray11 = new JSONArray();
                String[] strArr = (String[]) obj;
                int length9 = strArr.length;
                while (i2 < length9) {
                    jSONArray11.put(a((Object) strArr[i2]));
                    i2++;
                }
                return jSONArray11;
            }
        }
    }

    private JSONObject a(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            jSONObject.put(str, a(bundle.get(str)));
        }
        return jSONObject;
    }

    public NavigationResult a(Context context) {
        Intent intent;
        Intent intent2;
        PackageManager packageManager = context.getPackageManager();
        Bundle b2 = b(context);
        Iterator<AppLink.Target> it = a().b().iterator();
        while (true) {
            if (!it.hasNext()) {
                intent = null;
                break;
            }
            AppLink.Target next = it.next();
            intent = new Intent("android.intent.action.VIEW");
            if (next.a() != null) {
                intent.setData(next.a());
            } else {
                intent.setData(this.h.a());
            }
            intent.setPackage(next.d());
            if (next.c() != null) {
                intent.setClassName(next.d(), next.c());
            }
            intent.putExtra("al_applink_data", b2);
            if (packageManager.resolveActivity(intent, 65536) != null) {
                break;
            }
        }
        NavigationResult navigationResult = NavigationResult.FAILED;
        if (intent != null) {
            navigationResult = NavigationResult.APP;
            intent2 = intent;
        } else {
            Uri c2 = a().c();
            if (c2 != null) {
                try {
                    intent2 = new Intent("android.intent.action.VIEW", c2.buildUpon().appendQueryParameter("al_applink_data", a(b2).toString()).build());
                    navigationResult = NavigationResult.WEB;
                } catch (JSONException e2) {
                    a(context, intent, NavigationResult.FAILED, e2);
                    throw new RuntimeException(e2);
                }
            } else {
                intent2 = null;
            }
        }
        a(context, intent2, navigationResult, (JSONException) null);
        if (intent2 != null) {
            context.startActivity(intent2);
        }
        return navigationResult;
    }

    private void a(Context context, Intent intent, NavigationResult navigationResult, JSONException jSONException) {
        HashMap hashMap = new HashMap();
        if (jSONException != null) {
            hashMap.put("error", jSONException.getLocalizedMessage());
        }
        hashMap.put("success", navigationResult.isSucceeded() ? "1" : "0");
        hashMap.put("type", navigationResult.getCode());
        MeasurementEvent.a(context, MeasurementEvent.d, intent, (Map<String, String>) hashMap);
    }

    public static void a(AppLinkResolver appLinkResolver) {
        g = appLinkResolver;
    }

    public static AppLinkResolver d() {
        return g;
    }

    private static AppLinkResolver c(Context context) {
        if (d() != null) {
            return d();
        }
        return new WebViewAppLinkResolver(context);
    }

    public static NavigationResult a(Context context, AppLink appLink) {
        return new AppLinkNavigation(appLink, (Bundle) null, (Bundle) null).a(context);
    }

    public static Task<NavigationResult> a(final Context context, Uri uri, AppLinkResolver appLinkResolver) {
        return appLinkResolver.getAppLinkFromUrlInBackground(uri).c(new Continuation<AppLink, NavigationResult>() {
            /* renamed from: a */
            public NavigationResult then(Task<AppLink> task) throws Exception {
                return AppLinkNavigation.a(context, task.f());
            }
        }, Task.b);
    }

    public static Task<NavigationResult> a(Context context, URL url, AppLinkResolver appLinkResolver) {
        return a(context, Uri.parse(url.toString()), appLinkResolver);
    }

    public static Task<NavigationResult> a(Context context, String str, AppLinkResolver appLinkResolver) {
        return a(context, Uri.parse(str), appLinkResolver);
    }

    public static Task<NavigationResult> a(Context context, Uri uri) {
        return a(context, uri, c(context));
    }

    public static Task<NavigationResult> a(Context context, URL url) {
        return a(context, url, c(context));
    }

    public static Task<NavigationResult> a(Context context, String str) {
        return a(context, str, c(context));
    }
}
