package com.trello.rxlifecycle2.android;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RxLifecycleAndroid {

    /* renamed from: a  reason: collision with root package name */
    private static final Function<ActivityEvent, ActivityEvent> f9518a = new Function<ActivityEvent, ActivityEvent>() {
        /* renamed from: a */
        public ActivityEvent apply(ActivityEvent activityEvent) throws Exception {
            switch (AnonymousClass3.f9519a[activityEvent.ordinal()]) {
                case 1:
                    return ActivityEvent.DESTROY;
                case 2:
                    return ActivityEvent.STOP;
                case 3:
                    return ActivityEvent.PAUSE;
                case 4:
                    return ActivityEvent.STOP;
                case 5:
                    return ActivityEvent.DESTROY;
                case 6:
                    throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + activityEvent + " not yet implemented");
            }
        }
    };
    private static final Function<FragmentEvent, FragmentEvent> b = new Function<FragmentEvent, FragmentEvent>() {
        /* renamed from: a */
        public FragmentEvent apply(FragmentEvent fragmentEvent) throws Exception {
            switch (AnonymousClass3.b[fragmentEvent.ordinal()]) {
                case 1:
                    return FragmentEvent.DETACH;
                case 2:
                    return FragmentEvent.DESTROY;
                case 3:
                    return FragmentEvent.DESTROY_VIEW;
                case 4:
                    return FragmentEvent.STOP;
                case 5:
                    return FragmentEvent.PAUSE;
                case 6:
                    return FragmentEvent.STOP;
                case 7:
                    return FragmentEvent.DESTROY_VIEW;
                case 8:
                    return FragmentEvent.DESTROY;
                case 9:
                    return FragmentEvent.DETACH;
                case 10:
                    throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                default:
                    throw new UnsupportedOperationException("Binding to " + fragmentEvent + " not yet implemented");
            }
        }
    };

    private RxLifecycleAndroid() {
        throw new AssertionError("No instances");
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> a(@NonNull Observable<ActivityEvent> observable) {
        return RxLifecycle.a(observable, f9518a);
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> b(@NonNull Observable<FragmentEvent> observable) {
        return RxLifecycle.a(observable, b);
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> a(@NonNull View view) {
        Preconditions.a(view, "view == null");
        return RxLifecycle.a(Observable.create(new ViewDetachesOnSubscribe(view)));
    }

    /* renamed from: com.trello.rxlifecycle2.android.RxLifecycleAndroid$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9519a = new int[ActivityEvent.values().length];
        static final /* synthetic */ int[] b = new int[FragmentEvent.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|(2:29|30)|31|33|34|35|36|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|5|6|7|(2:9|10)|11|(2:13|14)|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|31|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0097 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00a1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00ab */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b5 */
        static {
            /*
                com.trello.rxlifecycle2.android.FragmentEvent[] r0 = com.trello.rxlifecycle2.android.FragmentEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r0 = 1
                int[] r1 = b     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.trello.rxlifecycle2.android.FragmentEvent r2 = com.trello.rxlifecycle2.android.FragmentEvent.ATTACH     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = b     // Catch:{ NoSuchFieldError -> 0x001f }
                com.trello.rxlifecycle2.android.FragmentEvent r3 = com.trello.rxlifecycle2.android.FragmentEvent.CREATE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = b     // Catch:{ NoSuchFieldError -> 0x002a }
                com.trello.rxlifecycle2.android.FragmentEvent r4 = com.trello.rxlifecycle2.android.FragmentEvent.CREATE_VIEW     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = b     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.trello.rxlifecycle2.android.FragmentEvent r5 = com.trello.rxlifecycle2.android.FragmentEvent.START     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                r4 = 5
                int[] r5 = b     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.trello.rxlifecycle2.android.FragmentEvent r6 = com.trello.rxlifecycle2.android.FragmentEvent.RESUME     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                r5 = 6
                int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x004b }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.PAUSE     // Catch:{ NoSuchFieldError -> 0x004b }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.STOP     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r8 = 7
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DESTROY_VIEW     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r8 = 8
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x006e }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DESTROY     // Catch:{ NoSuchFieldError -> 0x006e }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r8 = 9
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r6 = b     // Catch:{ NoSuchFieldError -> 0x007a }
                com.trello.rxlifecycle2.android.FragmentEvent r7 = com.trello.rxlifecycle2.android.FragmentEvent.DETACH     // Catch:{ NoSuchFieldError -> 0x007a }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r8 = 10
                r6[r7] = r8     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                com.trello.rxlifecycle2.android.ActivityEvent[] r6 = com.trello.rxlifecycle2.android.ActivityEvent.values()
                int r6 = r6.length
                int[] r6 = new int[r6]
                f9519a = r6
                int[] r6 = f9519a     // Catch:{ NoSuchFieldError -> 0x008d }
                com.trello.rxlifecycle2.android.ActivityEvent r7 = com.trello.rxlifecycle2.android.ActivityEvent.CREATE     // Catch:{ NoSuchFieldError -> 0x008d }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x008d }
                r6[r7] = r0     // Catch:{ NoSuchFieldError -> 0x008d }
            L_0x008d:
                int[] r0 = f9519a     // Catch:{ NoSuchFieldError -> 0x0097 }
                com.trello.rxlifecycle2.android.ActivityEvent r6 = com.trello.rxlifecycle2.android.ActivityEvent.START     // Catch:{ NoSuchFieldError -> 0x0097 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0097 }
                r0[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0097 }
            L_0x0097:
                int[] r0 = f9519a     // Catch:{ NoSuchFieldError -> 0x00a1 }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.RESUME     // Catch:{ NoSuchFieldError -> 0x00a1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a1 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a1 }
            L_0x00a1:
                int[] r0 = f9519a     // Catch:{ NoSuchFieldError -> 0x00ab }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.PAUSE     // Catch:{ NoSuchFieldError -> 0x00ab }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ab }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x00ab }
            L_0x00ab:
                int[] r0 = f9519a     // Catch:{ NoSuchFieldError -> 0x00b5 }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.STOP     // Catch:{ NoSuchFieldError -> 0x00b5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b5 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x00b5 }
            L_0x00b5:
                int[] r0 = f9519a     // Catch:{ NoSuchFieldError -> 0x00bf }
                com.trello.rxlifecycle2.android.ActivityEvent r1 = com.trello.rxlifecycle2.android.ActivityEvent.DESTROY     // Catch:{ NoSuchFieldError -> 0x00bf }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bf }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00bf }
            L_0x00bf:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.trello.rxlifecycle2.android.RxLifecycleAndroid.AnonymousClass3.<clinit>():void");
        }
    }
}
