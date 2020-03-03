package com.xiaomi.jr.personaldata;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import com.xiaomi.jr.personaldata.ApiHolder;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataCollector {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11006a = "DataCollector";
    private static final String b = "types";
    private static volatile DataCollector f;
    private Context c;
    private Executor d = Executors.newCachedThreadPool();
    private ConcurrentHashMap<Integer, CollectRunnable> e = new ConcurrentHashMap<>();

    private DataCollector(Context context) {
        this.c = context.getApplicationContext();
    }

    public static DataCollector a(Context context) {
        if (f == null) {
            synchronized (DataCollector.class) {
                if (f == null) {
                    f = new DataCollector(context);
                }
            }
        }
        return f;
    }

    private void a(int[] iArr) {
        this.d.execute(new Runnable(iArr) {
            private final /* synthetic */ int[] f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                ApiHolder.a().a(this.f$0);
            }
        });
    }

    public void a() {
        this.d.execute(new Runnable() {
            public final void run() {
                DataCollector.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        ApiHolder.TypeListResult a2 = ApiHolder.a().a();
        if (a2 != null) {
            a(a2.f10999a, a2.b, false);
        }
    }

    public void a(int[] iArr, boolean z, boolean z2) {
        MifiLog.c("TestData", "start collecting " + b(iArr) + "...");
        if (this.c == null || iArr == null || iArr.length == 0 || !ApiHolder.a().b()) {
            MifiLog.c("TestData", "stop collecting due to no types or not login or no imei");
            return;
        }
        if (z2) {
            a(iArr);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : iArr) {
            CollectRunnable collectRunnable = this.e.get(Integer.valueOf(i));
            if (collectRunnable == null) {
                arrayList.add(Integer.valueOf(i));
            } else if (!collectRunnable.g()) {
                this.e.remove(Integer.valueOf(i));
                arrayList.add(Integer.valueOf(i));
            } else {
                MifiLog.c("TestData", "type " + i + " is running already.");
            }
        }
        for (Integer intValue : arrayList) {
            final int intValue2 = intValue.intValue();
            final CollectRunnable a2 = a(intValue2);
            if (a2 != null) {
                if (z) {
                    PermissionManager.a(this.c, a2.a(), (Request.Callback) new Request.Callback() {
                        public void a(String str) {
                        }

                        public /* synthetic */ void b() {
                            Request.Callback.CC.$default$b(this);
                        }

                        public void a() {
                            DataCollector.this.a(intValue2, a2);
                        }
                    });
                } else {
                    a(intValue2, a2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, CollectRunnable collectRunnable) {
        this.d.execute(collectRunnable);
        this.e.put(Integer.valueOf(i), collectRunnable);
    }

    private static String b(int[] iArr) {
        String str = Operators.ARRAY_START_STR;
        if (iArr != null && iArr.length > 0) {
            for (int i : iArr) {
                str = str + i + ",";
            }
        }
        return str + Operators.ARRAY_END_STR;
    }

    private CollectRunnable a(int i) {
        if (i == 2) {
            return new CallLogRunnable(this.c);
        }
        if (i == 1) {
            return new ContactsRunnable(this.c);
        }
        if (i == 3) {
            return new SmsRunnable(this.c);
        }
        if (i == 4) {
            return new InstalledPackagesRunnable(this.c);
        }
        return null;
    }
}
