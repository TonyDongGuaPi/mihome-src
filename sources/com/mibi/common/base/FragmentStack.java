package com.mibi.common.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import com.mibi.common.data.CommonConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import junit.framework.Assert;

class FragmentStack implements Parcelable {
    public static final Parcelable.Creator<FragmentStack> CREATOR = new Parcelable.Creator<FragmentStack>() {
        /* renamed from: a */
        public FragmentStack[] newArray(int i) {
            return new FragmentStack[i];
        }

        /* renamed from: a */
        public FragmentStack createFromParcel(Parcel parcel) {
            FragmentStack fragmentStack = new FragmentStack();
            ArrayList unused = fragmentStack.d = parcel.readArrayList(FragmentInfo.class.getClassLoader());
            HashMap unused2 = fragmentStack.e = new HashMap();
            Iterator it = fragmentStack.d.iterator();
            while (it.hasNext()) {
                FragmentInfo fragmentInfo = (FragmentInfo) it.next();
                fragmentStack.e.put(fragmentInfo.f7458a, fragmentInfo);
            }
            return fragmentStack;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final String f7459a = "FragmentStack";
    private StepActivity b;
    private FragmentManager c;
    /* access modifiers changed from: private */
    public ArrayList<FragmentInfo> d = new ArrayList<>();
    /* access modifiers changed from: private */
    public HashMap<String, FragmentInfo> e = new HashMap<>();
    private ArrayList<ResultInfo> f = new ArrayList<>();

    public int describeContents() {
        return 0;
    }

    FragmentStack() {
    }

    /* access modifiers changed from: package-private */
    public void a(StepActivity stepActivity) {
        this.b = stepActivity;
        this.c = stepActivity.getFragmentManager();
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.d.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public FragmentInfo b() {
        if (this.d.isEmpty()) {
            return null;
        }
        return this.d.get(this.d.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public StepFragment c() {
        FragmentInfo b2 = b();
        if (b2 == null) {
            return null;
        }
        return (StepFragment) this.c.findFragmentByTag(b2.f7458a);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Class<? extends StepFragment> cls, Bundle bundle, int i, String str, boolean z, String str2) {
        boolean b2;
        synchronized (this) {
            b2 = b(cls, bundle, i, str, z, str2);
        }
        return b2;
    }

    private boolean b(Class<? extends StepFragment> cls, Bundle bundle, int i, String str, boolean z, String str2) {
        FragmentInfo fragmentInfo = new FragmentInfo();
        fragmentInfo.d = z;
        fragmentInfo.e = str;
        fragmentInfo.f = i;
        if (str2 != null) {
            fragmentInfo.b.add(str2);
        }
        try {
            StepFragment stepFragment = (StepFragment) cls.newInstance();
            if (bundle == null) {
                bundle = new Bundle();
            }
            stepFragment.setArguments(bundle);
            fragmentInfo.f7458a = a((Fragment) stepFragment);
            StepFragment c2 = c();
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            if (c2 != null) {
                beginTransaction.setCustomAnimations(stepFragment.J().a(), c2.J().b());
                beginTransaction.hide(c2);
            }
            beginTransaction.add(16908290, stepFragment, fragmentInfo.f7458a);
            beginTransaction.commitAllowingStateLoss();
            this.d.add(fragmentInfo);
            this.e.put(fragmentInfo.f7458a, fragmentInfo);
            if (!CommonConstants.b) {
                return true;
            }
            String obj = toString();
            Log.v(obj, "onFragmentStart, curr=" + c2 + ", next = " + stepFragment);
            return true;
        } catch (InstantiationException e2) {
            Log.e(f7459a, "FragmentStack startFragmentLocked InstantiationException ", e2);
            return false;
        } catch (IllegalAccessException e3) {
            Log.e(f7459a, "FragmentStack startFragmentLocked IllegalAccessException ", e3);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(StepFragment stepFragment) {
        boolean b2;
        synchronized (this) {
            b2 = b(stepFragment);
        }
        return b2;
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, boolean z) {
        boolean a2;
        synchronized (this) {
            a2 = a((JumpBackResultInfo) null, str, z, true);
        }
        return a2;
    }

    private boolean b(StepFragment stepFragment) {
        Assert.assertNotNull(stepFragment);
        FragmentInfo fragmentInfo = this.e.get(stepFragment.getTag());
        boolean z = false;
        if (fragmentInfo == null) {
            return false;
        }
        boolean z2 = fragmentInfo.d;
        String str = fragmentInfo.e;
        ResultInfo resultInfo = new ResultInfo(str, fragmentInfo.f, stepFragment.o, stepFragment.p);
        if (z2) {
            this.f.add(resultInfo);
        } else {
            Assert.assertTrue(!TextUtils.isEmpty(str));
            Fragment findFragmentByTag = this.c.findFragmentByTag(str);
            if (findFragmentByTag != null && (findFragmentByTag instanceof StepFragment)) {
                ((StepFragment) findFragmentByTag).a(resultInfo);
            }
        }
        int indexOf = this.d.indexOf(fragmentInfo);
        if (indexOf >= 0) {
            z = true;
        }
        Assert.assertTrue(z);
        FragmentTransaction beginTransaction = this.c.beginTransaction();
        if (indexOf != this.d.size() - 1 || indexOf == 0) {
            beginTransaction.remove(stepFragment);
        } else {
            StepFragment stepFragment2 = (StepFragment) this.c.findFragmentByTag(this.d.get(indexOf - 1).f7458a);
            beginTransaction.setCustomAnimations(stepFragment2.J().c(), stepFragment.J().d());
            beginTransaction.remove(stepFragment);
            beginTransaction.show(stepFragment2);
        }
        this.d.remove(indexOf);
        this.e.remove(fragmentInfo.f7458a);
        if (this.d.isEmpty()) {
            this.b.close(this.f);
        } else {
            beginTransaction.commitAllowingStateLoss();
        }
        return true;
    }

    private boolean a(JumpBackResultInfo jumpBackResultInfo, String str, boolean z, boolean z2) {
        Assert.assertTrue(!TextUtils.isEmpty(str));
        if (!z2) {
            if (this.d.isEmpty()) {
                this.b.doJumpBackResult(jumpBackResultInfo.f7460a, jumpBackResultInfo.b);
            } else {
                c().a(jumpBackResultInfo);
            }
            return true;
        } else if (this.d.isEmpty()) {
            this.b.closeJumpBack(this.f, jumpBackResultInfo, z2);
            return true;
        } else {
            FragmentInfo b2 = b();
            int indexOf = this.d.indexOf(b2);
            int i = indexOf;
            while (i >= 0) {
                FragmentInfo fragmentInfo = this.d.get(i);
                if (TextUtils.isEmpty(str) || fragmentInfo.b.contains(str)) {
                    break;
                }
                i--;
            }
            int i2 = z ? i - 1 : i;
            if (i2 < 0) {
                i2 = -1;
            }
            if (jumpBackResultInfo == null) {
                StepFragment stepFragment = (StepFragment) this.c.findFragmentByTag(b2.f7458a);
                jumpBackResultInfo = new JumpBackResultInfo(stepFragment.o, stepFragment.p, str, z);
            }
            FragmentTransaction beginTransaction = this.c.beginTransaction();
            if (i2 >= 0 && i2 < indexOf) {
                beginTransaction.setCustomAnimations(((StepFragment) this.c.findFragmentByTag(this.d.get(i2).f7458a)).J().c(), ((StepFragment) this.c.findFragmentByTag(b2.f7458a)).J().d());
            }
            for (int i3 = indexOf; i3 > i2; i3--) {
                FragmentInfo fragmentInfo2 = this.d.get(i3);
                Fragment findFragmentByTag = this.c.findFragmentByTag(fragmentInfo2.f7458a);
                this.d.remove(i3);
                this.e.remove(fragmentInfo2.f7458a);
                beginTransaction.remove(findFragmentByTag);
            }
            if (i2 >= 0) {
                StepFragment stepFragment2 = (StepFragment) this.c.findFragmentByTag(this.d.get(i2).f7458a);
                stepFragment2.a(jumpBackResultInfo);
                if (i2 < indexOf) {
                    beginTransaction.show(stepFragment2);
                    beginTransaction.commitAllowingStateLoss();
                }
            } else if (i == -1) {
                this.b.closeJumpBack(this.f, jumpBackResultInfo, true);
            } else if (i == 0) {
                this.b.closeJumpBack(this.f, jumpBackResultInfo, false);
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(JumpBackResultInfo jumpBackResultInfo, boolean z) {
        synchronized (this) {
            if (jumpBackResultInfo == null) {
                try {
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                a(jumpBackResultInfo, jumpBackResultInfo.c, jumpBackResultInfo.d, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(StepFragment stepFragment, String str) {
        Assert.assertNotNull(stepFragment);
        synchronized (this) {
            FragmentInfo fragmentInfo = this.e.get(stepFragment.getTag());
            if (fragmentInfo != null) {
                fragmentInfo.b.add(str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (this) {
            if (!this.d.isEmpty()) {
                c().F();
            }
        }
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

    private String a(Fragment fragment) {
        return String.valueOf(fragment.hashCode());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.d);
    }

    /* access modifiers changed from: package-private */
    public void a(FragmentManager fragmentManager) {
        Iterator<FragmentInfo> it = this.d.iterator();
        while (it.hasNext()) {
            FragmentInfo next = it.next();
            next.c = fragmentManager.findFragmentByTag(next.f7458a).isHidden();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(FragmentManager fragmentManager) {
        Iterator<FragmentInfo> it = this.d.iterator();
        FragmentTransaction fragmentTransaction = null;
        while (it.hasNext()) {
            FragmentInfo next = it.next();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(next.f7458a);
            if (findFragmentByTag == null) {
                it.remove();
            } else if (next.c) {
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
}
