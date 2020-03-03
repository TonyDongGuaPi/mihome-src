package com.mipay.common.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import com.mipay.common.data.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import junit.framework.Assert;

class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new g();

    /* renamed from: a  reason: collision with root package name */
    private static final String f8115a = "f";
    private StepActivity b;
    private FragmentManager c;
    /* access modifiers changed from: private */
    public ArrayList<d> d = new ArrayList<>();
    /* access modifiers changed from: private */
    public HashMap<String, d> e = new HashMap<>();
    private ArrayList<j> f = new ArrayList<>();

    f() {
    }

    private String a(Fragment fragment) {
        return String.valueOf(fragment.hashCode());
    }

    private boolean a(h hVar, String str, boolean z, boolean z2) {
        Assert.assertTrue(!TextUtils.isEmpty(str));
        if (!z2) {
            if (this.d.isEmpty()) {
                this.b.a(hVar.f8116a, hVar.b);
            } else {
                c().a(hVar);
            }
            return true;
        } else if (this.d.isEmpty()) {
            this.b.a(this.f, hVar, z2);
            return true;
        } else {
            d b2 = b();
            int indexOf = this.d.indexOf(b2);
            int i = indexOf;
            while (i >= 0) {
                d dVar = this.d.get(i);
                if (TextUtils.isEmpty(str) || dVar.b.contains(str)) {
                    break;
                }
                i--;
            }
            int i2 = z ? i - 1 : i;
            if (i2 < 0) {
                i2 = -1;
            }
            if (hVar == null) {
                l lVar = (l) this.c.findFragmentByTag(b2.f8114a);
                hVar = new h(lVar.b, lVar.c, str, z);
            }
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            for (int i3 = indexOf; i3 > i2; i3--) {
                d dVar2 = this.d.get(i3);
                Fragment findFragmentByTag = this.c.findFragmentByTag(dVar2.f8114a);
                this.d.remove(i3);
                this.e.remove(dVar2.f8114a);
                beginTransaction.remove(findFragmentByTag);
            }
            boolean z3 = false;
            if (i2 >= 0) {
                Fragment findFragmentByTag2 = this.c.findFragmentByTag(this.d.get(i2).f8114a);
                if (findFragmentByTag2 != null && (findFragmentByTag2 instanceof l)) {
                    z3 = true;
                }
                Assert.assertTrue(z3);
                ((l) findFragmentByTag2).a(hVar);
                if (i2 < indexOf) {
                    beginTransaction.show(findFragmentByTag2);
                    beginTransaction.commitAllowingStateLoss();
                }
            } else if (i == -1) {
                this.b.a(this.f, hVar, true);
            } else if (i == 0) {
                this.b.a(this.f, hVar, false);
            }
            return true;
        }
    }

    private boolean b(l lVar) {
        Assert.assertNotNull(lVar);
        d dVar = this.e.get(lVar.getTag());
        boolean z = false;
        if (dVar == null) {
            return false;
        }
        boolean z2 = dVar.d;
        String str = dVar.e;
        j jVar = new j(str, dVar.f, lVar.b, lVar.c);
        if (z2) {
            this.f.add(jVar);
        } else {
            Assert.assertTrue(!TextUtils.isEmpty(str));
            Fragment findFragmentByTag = this.c.findFragmentByTag(str);
            if (findFragmentByTag != null && (findFragmentByTag instanceof l)) {
                ((l) findFragmentByTag).a(jVar);
            }
        }
        int indexOf = this.d.indexOf(dVar);
        if (indexOf >= 0) {
            z = true;
        }
        Assert.assertTrue(z);
        FragmentTransaction beginTransaction = this.c.beginTransaction();
        beginTransaction.remove(lVar);
        if (indexOf == this.d.size() - 1 && indexOf != 0) {
            beginTransaction.show(this.c.findFragmentByTag(this.d.get(indexOf - 1).f8114a));
        }
        this.d.remove(indexOf);
        this.e.remove(dVar.f8114a);
        if (this.d.isEmpty()) {
            this.b.a(this.f);
        } else {
            beginTransaction.commitAllowingStateLoss();
        }
        return true;
    }

    private boolean b(Class<? extends l> cls, Bundle bundle, int i, String str, boolean z, String str2) {
        d dVar = new d();
        dVar.d = z;
        dVar.e = str;
        dVar.f = i;
        if (str2 != null) {
            dVar.b.add(str2);
        }
        try {
            l lVar = (l) cls.newInstance();
            if (bundle == null) {
                bundle = new Bundle();
            }
            lVar.setArguments(bundle);
            dVar.f8114a = a((Fragment) lVar);
            l c2 = c();
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            if (c2 != null) {
                beginTransaction.hide(c2);
            }
            beginTransaction.add(16908290, lVar, dVar.f8114a);
            beginTransaction.commitAllowingStateLoss();
            this.d.add(dVar);
            this.e.put(dVar.f8114a, dVar);
            if (!d.DEBUG) {
                return true;
            }
            String obj = toString();
            Log.v(obj, "onFragmentStart, curr=" + c2 + ", next = " + lVar);
            return true;
        } catch (IllegalAccessException | InstantiationException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(FragmentManager fragmentManager) {
        Iterator<d> it = this.d.iterator();
        while (it.hasNext()) {
            d next = it.next();
            next.c = fragmentManager.findFragmentByTag(next.f8114a).isHidden();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(StepActivity stepActivity) {
        this.b = stepActivity;
        this.c = stepActivity.getFragmentManager();
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar, boolean z) {
        synchronized (this) {
            if (hVar == null) {
                try {
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                a(hVar, hVar.c, hVar.d, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(l lVar, String str) {
        Assert.assertNotNull(lVar);
        synchronized (this) {
            d dVar = this.e.get(lVar.getTag());
            if (dVar != null) {
                dVar.b.add(str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.d.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean a(MenuItem menuItem) {
        synchronized (this) {
            if (this.d.isEmpty()) {
                return false;
            }
            boolean onOptionsItemSelected = c().onOptionsItemSelected(menuItem);
            return onOptionsItemSelected;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(l lVar) {
        boolean b2;
        synchronized (this) {
            b2 = b(lVar);
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class<? extends l> cls, Bundle bundle, int i, String str, boolean z, String str2) {
        boolean b2;
        synchronized (this) {
            b2 = b(cls, bundle, i, str, z, str2);
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, boolean z) {
        boolean a2;
        synchronized (this) {
            a2 = a((h) null, str, z, true);
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public d b() {
        if (this.d.isEmpty()) {
            return null;
        }
        return this.d.get(this.d.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void b(FragmentManager fragmentManager) {
        Iterator<d> it = this.d.iterator();
        FragmentTransaction fragmentTransaction = null;
        while (it.hasNext()) {
            d next = it.next();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(next.f8114a);
            if (next.c) {
                if (fragmentTransaction == null) {
                    fragmentTransaction = fragmentManager.beginTransaction();
                }
                fragmentTransaction.hide(findFragmentByTag);
            }
        }
        if (fragmentTransaction != null) {
            fragmentTransaction.commit();
        }
    }

    /* access modifiers changed from: package-private */
    public l c() {
        d b2 = b();
        if (b2 == null) {
            return null;
        }
        return (l) this.c.findFragmentByTag(b2.f8114a);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (this) {
            if (!this.d.isEmpty()) {
                c().onBackPressed();
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.d);
    }
}
