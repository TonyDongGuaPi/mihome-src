package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import miui.vip.QueryCallback;

public class MiuiVipService {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16375a = "com.xiaomi.vip.action.VIP_LEVEL_LIST";
    public static final String b = "com.xiaomi.vip.action.VIP_TASK_LIST";
    public static final String c = "com.xiaomi.vip.action.VIP_AWARD_LIST";
    public static final String d = "com.xiaomi.vip.action.VIP_ACHIEVEMENT_LIST";
    public static final String e = "http://file.market.xiaomi.com/download/MiVip/";
    public static final String f = "http://file.market.xiaomi.com/download/MiVip/0af465f08ba2c7effc250527f44bacd767340264c";
    public static final String[] g = {"0d9805094a28781efa59445967e590841a04066ba", "000ce04dfe5a94ac1280083c1f520dcde1e2a011f", "0f0ce41dfb5e98ac2480013c11020bc1e8e421fd0", "0d980a59402b741e00594a5966259b83108f7e46d", "0b980a59402b741e00594a5966559b84108f7e46d", "01980a59402b741e00594a5966c59b88108fae46d", "06980a59402b741e00594a5966e59b8b108fce46d", "060ce04dfe5b94ac8280023c1f020dc3e1e27011f", "0d9805094b28701ef959405966e59e841304066ba", "0af465f08ba2c7effc250827fc4ba6d76a340264c", "0af465f08ba2c7effc250a27fd4ba3d76a340264c", "040ce04dfe5a94ac82800b3c1f520dcbe1e22011f", "0a980a59402d741e20594f5966559b82108ffe46d", "0d9805094d28721eff59475963e59d841c04066ba", "0f0ce41dfa5e98ac2b800a3c1b0207c1ede421fd0", "0af465f088a2c2effb250527f74ba9d76a340264c", "03f46c5083a8c4ef20250b27f67ba4d16f2ff472a", "060ce04dfe5a94ac82800a3c1f020dc2e1e2f011f", "0f0ce41dfa5e98ac2a80023c120202c1e4e421fd0", "0f0ce41dfa5e98ac2a80043c110207c1ece421fd0", "040ce04dfe5a94ac82800a3c1f620dc0e1e29011f", "0af465f088a2c2effb250927f24ba0d76e340264c", "0ff46c5083a8c4ef20250b27f6bba4d36f2fd472a", "0f0ce41dfa5e98ac2a800c3c1b020ec1e5e421fd0", "07980a59402a741e80594a5966959b89108f5e46d", "04980a59402a741e80594a5966c59b85108ffe46d", "0d9805094a28781ef459485962e598841d04066ba", "080ce04dfe5a94acb280033c1f520dcae1e2a011f", "0f0ce41dfa5e9fac2d80023c100204c1e2e421fd0", "02980a59402b741ea0594c5966859b8e108f7e46d"};
    private static Object h = new Object();
    private static MiuiVipService i;
    private Class j;
    private Class k;
    private Object l;

    public static String a(int i2) {
        if (i2 <= 0 || i2 > g.length) {
            return null;
        }
        return e + g[i2 - 1];
    }

    public static MiuiVipService a() {
        if (i == null) {
            synchronized (h) {
                if (i == null) {
                    i = new MiuiVipService();
                }
            }
        }
        if (i.l == null) {
            return null;
        }
        return i;
    }

    private MiuiVipService() {
        Method method;
        try {
            this.j = Class.forName("miui.vip.VipService");
            if (!(this.j == null || (method = this.j.getMethod("instance", new Class[0])) == null)) {
                this.l = method.invoke((Object) null, new Object[0]);
            }
            if (this.l != null) {
                this.k = Class.forName("miui.vip.QueryCallback");
                if (this.k != null) {
                    Class<?> cls = Class.forName("miui.vip.VipAchievement");
                    if (cls != null) {
                        if (cls.getField("url") != null) {
                            if (cls.getField("isOwned") != null) {
                                Class<?> cls2 = Class.forName("miui.vip.VipUserInfo");
                                if (cls2 != null) {
                                    if (cls2.getField("level") != null) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
        }
        this.l = null;
    }

    public boolean a(QueryCallback queryCallback) {
        if (this.l == null || this.k == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("connect", new Class[]{this.k});
            if (method != null) {
                method.invoke(this.l, new Object[]{queryCallback});
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    public boolean b(QueryCallback queryCallback) {
        if (this.l == null || this.k == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("disconnect", new Class[]{this.k});
            if (method != null) {
                method.invoke(this.l, new Object[]{queryCallback});
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    public boolean b() {
        if (this.l == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("queryUserVipInfo", new Class[0]);
            if (method != null) {
                method.invoke(this.l, new Object[0]);
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    public boolean c() {
        if (this.l == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("queryAchievements", new Class[0]);
            if (method != null) {
                method.invoke(this.l, new Object[0]);
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    public boolean a(Context context) {
        if (this.j == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("startVipActivity", new Class[]{Context.class, String.class});
            if (method != null) {
                method.invoke((Object) null, new Object[]{context, f16375a});
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }

    public boolean b(Context context) {
        if (this.j == null) {
            return false;
        }
        try {
            Method method = this.j.getMethod("startVipActivity", new Class[]{Context.class, String.class});
            if (method != null) {
                method.invoke((Object) null, new Object[]{context, d});
                return true;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return false;
    }
}
