package com.xiaomi.jr.appbase;

import android.app.Activity;
import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.common.utils.Constants;
import java.util.ArrayList;

public class MiFiActivityManager {

    /* renamed from: a  reason: collision with root package name */
    private static MiFiActivityManager f1389a = new MiFiActivityManager();
    private final ArrayList<Activity> b = new ArrayList<>();
    private boolean c;

    public static MiFiActivityManager a() {
        return f1389a;
    }

    public void a(Activity activity) {
        if (!this.c) {
            String name = activity.getClass().getName();
            if (name.startsWith(AppConstants.I) || (Constants.f1410a && name.startsWith(activity.getPackageName()))) {
                this.c = true;
            }
        }
        if (this.c && !this.b.contains(activity)) {
            this.b.add(activity);
        }
    }

    public void b(Activity activity) {
        if (this.b.contains(activity)) {
            this.b.remove(activity);
        }
    }

    public boolean c(Activity activity) {
        return this.b.contains(activity);
    }

    public void b() {
        if (this.b.size() > 0) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                this.b.get(size).finish();
            }
            this.b.clear();
        }
        this.c = false;
    }
}
