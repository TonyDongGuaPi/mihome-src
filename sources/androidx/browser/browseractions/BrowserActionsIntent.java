package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserActionsIntent {

    /* renamed from: a  reason: collision with root package name */
    public static final String f482a = "androidx.browser.browseractions.APP_ID";
    public static final String b = "androidx.browser.browseractions.browser_action_open";
    public static final String c = "androidx.browser.browseractions.ICON_ID";
    public static final String d = "androidx.browser.browseractions.TITLE";
    public static final String e = "androidx.browser.browseractions.ACTION";
    public static final String f = "androidx.browser.browseractions.extra.TYPE";
    public static final String g = "androidx.browser.browseractions.extra.MENU_ITEMS";
    public static final String h = "androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT";
    public static final int i = 5;
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 3;
    public static final int n = 4;
    public static final int o = 5;
    public static final int p = -1;
    public static final int q = 0;
    public static final int r = 1;
    public static final int s = 2;
    public static final int t = 3;
    public static final int u = 4;
    private static final String v = "BrowserActions";
    private static final String w = "https://www.example.com";
    private static BrowserActionsFallDialogListener y;
    @NonNull
    private final Intent x;

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    interface BrowserActionsFallDialogListener {
        void a();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsItemId {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsUrlType {
    }

    @NonNull
    public Intent a() {
        return this.x;
    }

    BrowserActionsIntent(@NonNull Intent intent) {
        this.x = intent;
    }

    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final Intent f483a = new Intent(BrowserActionsIntent.b);
        private Context b;
        private Uri c;
        private int d;
        private ArrayList<Bundle> e = null;
        private PendingIntent f = null;

        public Builder(Context context, Uri uri) {
            this.b = context;
            this.c = uri;
            this.d = 0;
            this.e = new ArrayList<>();
        }

        public Builder a(int i) {
            this.d = i;
            return this;
        }

        public Builder a(ArrayList<BrowserActionItem> arrayList) {
            if (arrayList.size() <= 5) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (TextUtils.isEmpty(arrayList.get(i).b()) || arrayList.get(i).c() == null) {
                        throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
                    }
                    this.e.add(a(arrayList.get(i)));
                }
                return this;
            }
            throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
        }

        public Builder a(BrowserActionItem... browserActionItemArr) {
            return a((ArrayList<BrowserActionItem>) new ArrayList(Arrays.asList(browserActionItemArr)));
        }

        public Builder a(PendingIntent pendingIntent) {
            this.f = pendingIntent;
            return this;
        }

        private Bundle a(BrowserActionItem browserActionItem) {
            Bundle bundle = new Bundle();
            bundle.putString(BrowserActionsIntent.d, browserActionItem.b());
            bundle.putParcelable(BrowserActionsIntent.e, browserActionItem.c());
            if (browserActionItem.a() != 0) {
                bundle.putInt(BrowserActionsIntent.c, browserActionItem.a());
            }
            return bundle;
        }

        public BrowserActionsIntent a() {
            this.f483a.setData(this.c);
            this.f483a.putExtra(BrowserActionsIntent.f, this.d);
            this.f483a.putParcelableArrayListExtra(BrowserActionsIntent.g, this.e);
            this.f483a.putExtra(BrowserActionsIntent.f482a, PendingIntent.getActivity(this.b, 0, new Intent(), 0));
            if (this.f != null) {
                this.f483a.putExtra(BrowserActionsIntent.h, this.f);
            }
            return new BrowserActionsIntent(this.f483a);
        }
    }

    public static void a(Context context, Uri uri) {
        a(context, new Builder(context, uri).a().a());
    }

    public static void a(Context context, Uri uri, int i2, ArrayList<BrowserActionItem> arrayList, PendingIntent pendingIntent) {
        a(context, new Builder(context, uri).a(i2).a(arrayList).a(pendingIntent).a().a());
    }

    public static void a(Context context, Intent intent) {
        a(context, intent, a(context));
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void a(Context context, Intent intent, List<ResolveInfo> list) {
        if (list == null || list.size() == 0) {
            b(context, intent);
            return;
        }
        int i2 = 0;
        if (list.size() == 1) {
            intent.setPackage(list.get(0).activityInfo.packageName);
        } else {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(w)), 65536);
            if (resolveActivity != null) {
                String str = resolveActivity.activityInfo.packageName;
                while (true) {
                    if (i2 >= list.size()) {
                        break;
                    } else if (str.equals(list.get(i2).activityInfo.packageName)) {
                        intent.setPackage(str);
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        ContextCompat.startActivity(context, intent, (Bundle) null);
    }

    private static List<ResolveInfo> a(Context context) {
        return context.getPackageManager().queryIntentActivities(new Intent(b, Uri.parse(w)), 131072);
    }

    private static void b(Context context, Intent intent) {
        Uri data = intent.getData();
        int intExtra = intent.getIntExtra(f, 0);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(g);
        a(context, data, intExtra, parcelableArrayListExtra != null ? a((ArrayList<Bundle>) parcelableArrayListExtra) : null);
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void a(BrowserActionsFallDialogListener browserActionsFallDialogListener) {
        y = browserActionsFallDialogListener;
    }

    private static void a(Context context, Uri uri, int i2, List<BrowserActionItem> list) {
        new BrowserActionsFallbackMenuUi(context, uri, list).a();
        if (y != null) {
            y.a();
        }
    }

    public static List<BrowserActionItem> a(ArrayList<Bundle> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Bundle bundle = arrayList.get(i2);
            String string = bundle.getString(d);
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(e);
            int i3 = bundle.getInt(c);
            if (TextUtils.isEmpty(string) || pendingIntent == null) {
                throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
            }
            arrayList2.add(new BrowserActionItem(string, pendingIntent, i3));
        }
        return arrayList2;
    }

    public static String a(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(f482a);
        if (pendingIntent == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return pendingIntent.getCreatorPackage();
        }
        return pendingIntent.getTargetPackage();
    }
}
