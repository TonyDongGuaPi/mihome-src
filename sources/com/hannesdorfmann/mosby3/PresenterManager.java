package com.hannesdorfmann.mosby3;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.Map;
import java.util.UUID;

public final class PresenterManager {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f5745a = false;
    public static final String b = "PresenterManager";
    static final String c = "com.hannesdorfmann.mosby3.MosbyPresenterManagerActivityId";
    static final Application.ActivityLifecycleCallbacks d = new Application.ActivityLifecycleCallbacks() {
        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            String string;
            if (bundle != null && (string = bundle.getString(PresenterManager.c)) != null) {
                PresenterManager.e.put(activity, string);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            String str = (String) PresenterManager.e.get(activity);
            if (str != null) {
                bundle.putString(PresenterManager.c, str);
            }
        }

        public void onActivityDestroyed(Activity activity) {
            String str;
            if (!activity.isChangingConfigurations() && (str = (String) PresenterManager.e.get(activity)) != null) {
                ActivityScopedCache activityScopedCache = (ActivityScopedCache) PresenterManager.f.get(str);
                if (activityScopedCache != null) {
                    activityScopedCache.a();
                    PresenterManager.f.remove(str);
                }
                if (PresenterManager.f.isEmpty()) {
                    activity.getApplication().unregisterActivityLifecycleCallbacks(PresenterManager.d);
                    if (PresenterManager.f5745a) {
                        Log.d(PresenterManager.b, "Unregistering ActivityLifecycleCallbacks");
                    }
                }
            }
            PresenterManager.e.remove(activity);
        }
    };
    /* access modifiers changed from: private */
    public static final Map<Activity, String> e = new ArrayMap();
    /* access modifiers changed from: private */
    public static final Map<String, ActivityScopedCache> f = new ArrayMap();

    private PresenterManager() {
        throw new RuntimeException("Not instantiatable!");
    }

    @MainThread
    @NonNull
    static ActivityScopedCache a(@NonNull Activity activity) {
        if (activity != null) {
            String str = e.get(activity);
            if (str == null) {
                str = UUID.randomUUID().toString();
                e.put(activity, str);
                if (e.size() == 1) {
                    activity.getApplication().registerActivityLifecycleCallbacks(d);
                    if (f5745a) {
                        Log.d(b, "Registering ActivityLifecycleCallbacks");
                    }
                }
            }
            ActivityScopedCache activityScopedCache = f.get(str);
            if (activityScopedCache != null) {
                return activityScopedCache;
            }
            ActivityScopedCache activityScopedCache2 = new ActivityScopedCache();
            f.put(str, activityScopedCache2);
            return activityScopedCache2;
        }
        throw new NullPointerException("Activity is null");
    }

    @Nullable
    @MainThread
    static ActivityScopedCache b(@NonNull Activity activity) {
        if (activity != null) {
            String str = e.get(activity);
            if (str == null) {
                return null;
            }
            return f.get(str);
        }
        throw new NullPointerException("Activity is null");
    }

    @Nullable
    public static <P> P a(@NonNull Activity activity, @NonNull String str) {
        if (activity == null) {
            throw new NullPointerException("Activity is null");
        } else if (str != null) {
            ActivityScopedCache b2 = b(activity);
            if (b2 == null) {
                return null;
            }
            return b2.a(str);
        } else {
            throw new NullPointerException("View id is null");
        }
    }

    @Nullable
    public static <VS> VS b(@NonNull Activity activity, @NonNull String str) {
        if (activity == null) {
            throw new NullPointerException("Activity is null");
        } else if (str != null) {
            ActivityScopedCache b2 = b(activity);
            if (b2 == null) {
                return null;
            }
            return b2.b(str);
        } else {
            throw new NullPointerException("View id is null");
        }
    }

    @NonNull
    public static Activity a(@NonNull Context context) {
        if (context == null) {
            throw new NullPointerException("context == null");
        } else if (context instanceof Activity) {
            return (Activity) context;
        } else {
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            throw new IllegalStateException("Could not find the surrounding Activity");
        }
    }

    static void a() {
        e.clear();
        for (ActivityScopedCache a2 : f.values()) {
            a2.a();
        }
        f.clear();
    }

    public static void a(@NonNull Activity activity, @NonNull String str, @NonNull MvpPresenter<? extends MvpView> mvpPresenter) {
        if (activity != null) {
            a(activity).a(str, mvpPresenter);
            return;
        }
        throw new NullPointerException("Activity is null");
    }

    public static void a(@NonNull Activity activity, @NonNull String str, @NonNull Object obj) {
        if (activity != null) {
            a(activity).a(str, obj);
            return;
        }
        throw new NullPointerException("Activity is null");
    }

    public static void c(@NonNull Activity activity, @NonNull String str) {
        if (activity != null) {
            ActivityScopedCache b2 = b(activity);
            if (b2 != null) {
                b2.c(str);
                return;
            }
            return;
        }
        throw new NullPointerException("Activity is null");
    }
}
