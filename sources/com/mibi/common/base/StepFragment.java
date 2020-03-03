package com.mibi.common.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.ui.animation.SlideAnimatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.app.Fragment;

public class StepFragment extends Fragment {
    static final int q = 100000;
    static final int r = 100001;
    static final int s = 999999;

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<ResultInfo> f7469a;
    private JumpBackResultInfo b;
    private StepActivity c;
    /* access modifiers changed from: private */
    public IAnimatorFactory d;
    int o = 0;
    Bundle p;

    public interface IAnimatorFactory {
        int a();

        Animator a(Activity activity, int i, boolean z, int i2);

        int b();

        int c();

        int d();
    }

    public void C() {
    }

    public void D() {
    }

    public void a(int i, int i2, Intent intent) {
    }

    public void a(int i, int i2, Bundle bundle) {
    }

    public void a(int i, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void a(Activity activity) {
    }

    public void a(Intent intent) {
    }

    public void a(Bundle bundle) {
    }

    public boolean a(MenuItem menuItem) {
        return false;
    }

    public void c(Bundle bundle) {
    }

    public void d(Bundle bundle) {
    }

    public void f(boolean z) {
    }

    public void k() {
    }

    public void l() {
    }

    public void m() {
    }

    public void n() {
    }

    public void o() {
    }

    /* access modifiers changed from: protected */
    public void r() {
    }

    /* access modifiers changed from: protected */
    public void s() {
    }

    /* access modifiers changed from: protected */
    public void t() {
    }

    /* access modifiers changed from: protected */
    public void u() {
    }

    /* access modifiers changed from: package-private */
    public void a(ResultInfo resultInfo) {
        if (this.f7469a == null) {
            this.f7469a = new ArrayList<>();
        }
        this.f7469a.add(resultInfo);
    }

    /* access modifiers changed from: package-private */
    public void a(JumpBackResultInfo jumpBackResultInfo) {
        if (isResumed()) {
            a(jumpBackResultInfo.f7460a, jumpBackResultInfo.b);
        } else {
            this.b = jumpBackResultInfo;
        }
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.c = (StepActivity) getActivity();
            a(activity);
        } catch (ClassCastException unused) {
            throw new ClassCastException(getActivity().toString() + " must be a StepActivity");
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        d(bundle);
    }

    public final View b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return a(layoutInflater, viewGroup, bundle);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new View(getActivity());
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c(bundle);
    }

    public final void onStart() {
        super.onStart();
        m();
    }

    public final void onResume() {
        if (this.f7469a != null) {
            Iterator<ResultInfo> it = this.f7469a.iterator();
            while (it.hasNext()) {
                ResultInfo next = it.next();
                a(next.b, next.c, next.d);
            }
            this.f7469a = null;
        }
        if (this.b != null) {
            a(this.b.f7460a, this.b.b);
            this.b = null;
        }
        super.onResume();
        k();
    }

    public final void onPause() {
        super.onPause();
        l();
    }

    public final void onStop() {
        super.onStop();
        n();
    }

    public final void onDestroy() {
        super.onDestroy();
        o();
    }

    public final void onDestroyView() {
        super.onDestroyView();
        D();
    }

    public final void onDetach() {
        super.onDetach();
        C();
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (!(i2 == 100001 || i2 == s) || !intent.hasExtra(CommonConstants.aA)) {
            a(i, i2, intent);
            return;
        }
        Iterator it = intent.getParcelableArrayListExtra(CommonConstants.aA).iterator();
        while (it.hasNext()) {
            ResultInfo resultInfo = (ResultInfo) it.next();
            Intent intent2 = null;
            if (resultInfo.d != null) {
                intent2 = new Intent();
                intent2.putExtras(resultInfo.d);
            }
            a(i, resultInfo.c, intent2);
        }
        if (i2 == s) {
            this.c.mFragmentStack.a((JumpBackResultInfo) intent.getParcelableExtra(CommonConstants.aB), intent.getBooleanExtra(CommonConstants.aC, true));
        }
    }

    public final void e(boolean z) {
        super.e(z);
        if (!z) {
            onPause();
            onStop();
        } else {
            onStart();
            onResume();
        }
        f(z);
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        this.c.addFlagForFragment(this, str);
    }

    /* access modifiers changed from: protected */
    public void a_(Class<? extends StepFragment> cls, Bundle bundle) {
        a(cls, bundle, -1, (String) null, (Class<? extends StepActivity>) null);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends StepFragment> cls, Bundle bundle, String str, Class<? extends StepActivity> cls2) {
        a(cls, bundle, -1, str, cls2);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends StepFragment> cls, Bundle bundle, int i, String str) {
        a(cls, bundle, i, str, (Class<? extends StepActivity>) null);
    }

    public void a(Class<? extends StepFragment> cls, Bundle bundle, int i, String str, Class<? extends StepActivity> cls2) {
        this.c.startFragmentForResult(this, cls, bundle, i, str, cls2);
    }

    public void E() {
        this.c.finishFragment(this);
    }

    public void c(String str) {
        a(str, true);
    }

    public void a(String str, boolean z) {
        this.c.finishFragmentJumpBack(str, z);
    }

    public final void F() {
        y();
    }

    public void y() {
        E();
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return a(menuItem);
    }

    public final void c(int i) {
        this.o = i;
        this.p = null;
    }

    public final void b(int i, Bundle bundle) {
        this.o = i;
        this.p = bundle;
    }

    public int G() {
        return this.o;
    }

    public Bundle H() {
        return this.p;
    }

    /* access modifiers changed from: protected */
    public IAnimatorFactory I() {
        return new SlideAnimatorFactory();
    }

    public IAnimatorFactory J() {
        if (this.d == null) {
            this.d = I();
        }
        return this.d;
    }

    public final Animator onCreateAnimator(int i, boolean z, final int i2) {
        Animator a2;
        if (this.d == null || (a2 = this.d.a(getActivity(), i, z, i2)) == null) {
            return null;
        }
        a2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                StepFragment.this.r();
            }

            public void onAnimationEnd(Animator animator) {
                StepFragment.this.s();
                if (i2 == StepFragment.this.d.a()) {
                    StepFragment.this.t();
                } else if (i2 == StepFragment.this.d.c()) {
                    StepFragment.this.u();
                }
            }
        });
        return a2;
    }
}
