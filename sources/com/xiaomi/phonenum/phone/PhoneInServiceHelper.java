package com.xiaomi.phonenum.phone;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = 21)
class PhoneInServiceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f12571a;
    /* access modifiers changed from: private */
    public final CountDownLatch b = new CountDownLatch(1);
    /* access modifiers changed from: private */
    public final int c;
    /* access modifiers changed from: private */
    public PhoneStateListener d;
    /* access modifiers changed from: private */
    public TelephonyManager e;

    static {
        HandlerThread handlerThread = new HandlerThread("PhoneInServiceListener");
        handlerThread.start();
        f12571a = new Handler(handlerThread.getLooper());
    }

    private PhoneInServiceHelper(Context context, int i) {
        this.c = i;
        this.e = (TelephonyManager) context.getSystemService("phone");
    }

    static boolean a(Context context, int i, long j) throws InterruptedException {
        return new PhoneInServiceHelper(context, i).a(j);
    }

    private boolean a(long j) throws InterruptedException {
        a();
        try {
            return this.b.await(j, TimeUnit.MILLISECONDS);
        } finally {
            b();
        }
    }

    private void a() {
        f12571a.post(new Runnable() {
            public void run() {
                PhoneStateListener unused = PhoneInServiceHelper.this.d = new PhoneStateListenerRfle(PhoneInServiceHelper.this.c) {
                    public void onServiceStateChanged(ServiceState serviceState) {
                        if (serviceState.getState() == 0) {
                            PhoneInServiceHelper.this.b.countDown();
                        }
                    }
                };
                PhoneInServiceHelper.this.e.listen(PhoneInServiceHelper.this.d, 1);
            }
        });
    }

    private void b() {
        f12571a.post(new Runnable() {
            public void run() {
                PhoneInServiceHelper.this.e.listen(PhoneInServiceHelper.this.d, 0);
            }
        });
    }

    static class ReflectUtil {
        ReflectUtil() {
        }

        static void a(Object obj, String str, Object obj2) {
            Field a2 = a(obj, str);
            if (a2 != null) {
                try {
                    a2.set(obj, obj2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Could not find field [" + str + "] on target [" + obj + Operators.ARRAY_END_STR);
            }
        }

        private static Field a(Object obj, String str) {
            if (obj == null) {
                throw new IllegalArgumentException("object can'd be null");
            } else if (str == null || str.length() <= 0) {
                throw new IllegalArgumentException("fieldName can'd be blank");
            } else {
                Class cls = obj.getClass();
                while (cls != Object.class) {
                    try {
                        Field declaredField = cls.getDeclaredField(str);
                        a(declaredField);
                        return declaredField;
                    } catch (NoSuchFieldException unused) {
                        cls = cls.getSuperclass();
                    }
                }
                return null;
            }
        }

        private static void a(Field field) {
            if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
                field.setAccessible(true);
            }
        }
    }

    private class PhoneStateListenerRfle extends PhoneStateListener {
        public PhoneStateListenerRfle(int i) {
            ReflectUtil.a(this, "mSubId", Integer.valueOf(i));
        }
    }
}
