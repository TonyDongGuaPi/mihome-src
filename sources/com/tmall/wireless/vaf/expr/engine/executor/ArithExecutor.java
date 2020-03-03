package com.tmall.wireless.vaf.expr.engine.executor;

import android.util.Log;
import com.libra.virtualview.common.StringBase;
import com.tmall.wireless.vaf.expr.engine.data.Data;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public abstract class ArithExecutor extends Executor {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f9357a = -1;
    public static final byte b = 0;
    public static final byte c = 1;
    public static final byte d = 2;
    public static final byte e = 3;
    public static final byte f = 4;
    private static final String j = "ArithExecutor_TMTEST";
    protected int g;
    protected int h;
    protected Set<Object> i;

    public void a() {
        super.a();
        this.h = 256;
    }

    private void c() {
        if (this.i == null) {
            this.i = new HashSet(10);
        }
    }

    /* access modifiers changed from: protected */
    public Data a(int i2) {
        Data data = new Data();
        switch (i2) {
            case 0:
                if (!a(data)) {
                    return null;
                }
                break;
            case 1:
                data.a(this.r.f());
                break;
            case 2:
                data.a(Float.intBitsToFloat(this.r.f()));
                break;
            case 3:
                data.a(this.p.a(this.r.f()));
                break;
            case 4:
                if (!b(data)) {
                    return null;
                }
                break;
            default:
                Log.e(j, "can not read this type:" + i2);
                return null;
        }
        return data;
    }

    /* access modifiers changed from: protected */
    public Set<Object> b() {
        ViewBase w;
        ViewBase viewBase;
        c();
        HashSet<ViewBase> hashSet = new HashSet<>(10);
        this.i.clear();
        int f2 = this.r.f();
        if (f2 > -1) {
            Data a2 = this.s.a(f2);
            if (a2 == null) {
                Log.e(j, "read obj from register failed  registerId:" + f2);
            } else if (4 == a2.g) {
                hashSet.add(a2.e());
            } else {
                Log.e(j, "read obj from register failed obj:" + a2);
            }
        }
        byte d2 = this.r.d();
        this.g = d2;
        boolean z = true;
        boolean z2 = false;
        if (d2 >= 1) {
            if (hashSet.size() <= 0) {
                int f3 = this.r.f();
                if (-1068784020 == f3) {
                    if (3 == d2) {
                        String a3 = this.p.a(this.r.f());
                        Object b2 = this.q.b(a3);
                        if (b2 != null) {
                            hashSet.add(b2);
                        } else {
                            Log.e(j, "findObject can not find module:" + a3);
                        }
                    } else {
                        Log.e(j, "findObject count invalidate:" + d2);
                    }
                    return hashSet;
                } else if (3076010 == f3) {
                    hashSet.add(this.t);
                    return hashSet;
                } else if (3559070 == f3) {
                    hashSet.add(this.n);
                } else if (!this.p.b(f3)) {
                    if (f2 > -1) {
                        viewBase = null;
                    } else {
                        viewBase = this.q.a(f3);
                    }
                    if (viewBase == null) {
                        Log.e(j, "findObject can not find com id:" + f3);
                    } else {
                        hashSet.add(viewBase);
                    }
                } else {
                    Log.e(j, "findObject first token invalidate id:" + f3);
                }
            }
            if (hashSet.size() > 0) {
                int i2 = 0;
                while (i2 < d2 - 2) {
                    int f4 = this.r.f();
                    if (this.p.b(f4)) {
                        switch (f4) {
                            case StringBase.at:
                                this.i.clear();
                                for (Object next : hashSet) {
                                    if (next instanceof ViewBase) {
                                        ViewBase w2 = ((ViewBase) next).w();
                                        if (w2 != null) {
                                            this.i.add(w2);
                                        }
                                    } else {
                                        Log.w(j, "warning");
                                    }
                                }
                                hashSet.clear();
                                hashSet.addAll(this.i);
                                break;
                            case StringBase.au:
                                this.i.clear();
                                for (Object next2 : hashSet) {
                                    if (next2 instanceof ViewBase) {
                                        for (ViewBase w3 = ((ViewBase) next2).w(); w3 != null; w3 = w3.w()) {
                                            this.i.add(w3);
                                        }
                                    }
                                }
                                hashSet.clear();
                                hashSet.addAll(this.i);
                                break;
                            case StringBase.as:
                            case StringBase.be:
                                break;
                            case StringBase.av:
                                this.i.clear();
                                for (Object next3 : hashSet) {
                                    if ((next3 instanceof ViewBase) && (w = ((ViewBase) next3).w()) != null && (w instanceof Layout)) {
                                        this.i.addAll(((Layout) w).b());
                                        this.i.remove(next3);
                                    }
                                }
                                hashSet.clear();
                                hashSet.addAll(this.i);
                                break;
                            default:
                                Log.e(j, "findObject invalidate system id:" + f4);
                                z = false;
                                break;
                        }
                    } else {
                        this.i.clear();
                        for (ViewBase a4 : hashSet) {
                            ViewBase a5 = a4.a(this.p.a(f4));
                            if (a5 != null) {
                                this.i.add(a5);
                            } else {
                                Log.e(j, "can not find obj:" + this.p.a(f4));
                            }
                        }
                        hashSet.clear();
                        hashSet.addAll(this.i);
                    }
                    if (!z) {
                        z2 = z;
                    } else {
                        i2++;
                    }
                }
                z2 = z;
            }
        } else {
            Log.e(j, "findObject count invalidate:" + d2);
        }
        if (!z2) {
            return null;
        }
        return hashSet;
    }

    private void a(Set<Object> set) {
        c();
        Set<Object> set2 = this.i;
        this.i = set;
    }

    /* access modifiers changed from: protected */
    public boolean a(Data data) {
        Set<Object> b2 = b();
        if (b2 == null) {
            return false;
        }
        int f2 = this.r.f();
        for (Object next : b2) {
            if (next == this.t) {
                Class[] clsArr = {String.class};
                try {
                    next = next.getClass().getMethod("getData", clsArr).invoke(next, new Object[]{this.p.a(f2)});
                } catch (NoSuchMethodException e2) {
                    e2.printStackTrace();
                    Log.e(j, "getData NoSuchMethodException:");
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                    Log.e(j, "getData InvocationTargetException:");
                } catch (IllegalAccessException e4) {
                    e4.printStackTrace();
                    Log.e(j, "getData IllegalAccessException:");
                }
            } else {
                next = this.q.a(next, f2);
            }
            if (next == null) {
                Log.e(j, "getProperty failed");
            } else if (next instanceof Integer) {
                data.a(((Integer) next).intValue());
                return true;
            } else if (next instanceof Float) {
                data.a(((Float) next).floatValue());
                return true;
            } else if (next instanceof String) {
                data.a((String) next);
                return true;
            } else {
                data.b(next);
                return true;
            }
        }
        return true;
    }

    private boolean b(Data data) {
        int f2 = this.r.f();
        if (f2 < this.h) {
            this.h = f2;
        }
        Data a2 = this.s.a(f2);
        if (a2 == null) {
            return false;
        }
        data.a(a2);
        return true;
    }
}
