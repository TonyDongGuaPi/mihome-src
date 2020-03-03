package com.xiaomi.youpin.app_sdk.url_dispatch;

import android.app.Activity;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UrlActivityManager {

    /* renamed from: a  reason: collision with root package name */
    List<ActivityData> f23208a = new LinkedList();
    Set<String> b = new HashSet();

    private static class InstanceHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static UrlActivityManager f23210a = new UrlActivityManager();

        private InstanceHolder() {
        }
    }

    public static UrlActivityManager a() {
        return InstanceHolder.f23210a;
    }

    private static class ActivityData {

        /* renamed from: a  reason: collision with root package name */
        String f23209a;
        WeakReference<Activity> b;

        ActivityData(String str, Activity activity) {
            this.f23209a = str;
            this.b = new WeakReference<>(activity);
        }
    }

    public void a(String str, Activity activity) {
        if (this.b.contains(str)) {
            Iterator<ActivityData> it = this.f23208a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityData next = it.next();
                if (TextUtils.equals(str, next.f23209a) && activity != next.b.get()) {
                    if (next.b.get() != null && !((Activity) next.b.get()).isFinishing()) {
                        ((Activity) next.b.get()).finish();
                    }
                    this.f23208a.remove(next);
                }
            }
            this.b.remove(str);
        }
        this.f23208a.add(new ActivityData(str, activity));
        this.b.add(str);
    }

    public void a(Activity activity) {
        String str;
        Iterator<ActivityData> it = this.f23208a.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ActivityData next = it.next();
            if (next.b.get() == activity) {
                this.f23208a.remove(next);
                str = next.f23209a;
                break;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            this.b.remove(str);
        }
    }

    public boolean a(String str) {
        if (this.f23208a.size() > 0) {
            ActivityData activityData = this.f23208a.get(this.f23208a.size() - 1);
            if (TextUtils.equals(str, activityData.f23209a) && activityData.b.get() != null && !((Activity) activityData.b.get()).isFinishing()) {
                return true;
            }
            if (TextUtils.isEmpty(str) || !str.contains("&spmref") || !TextUtils.equals(str.substring(0, str.indexOf("&spmref")), activityData.f23209a) || activityData.b.get() == null || ((Activity) activityData.b.get()).isFinishing()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
