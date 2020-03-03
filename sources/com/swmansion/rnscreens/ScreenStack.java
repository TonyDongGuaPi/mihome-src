package com.swmansion.rnscreens;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.swmansion.rnscreens.Screen;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ScreenStack extends ScreenContainer<ScreenStackFragment> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8952a = "RN_SCREEN_LAST";
    private final ArrayList<ScreenStackFragment> b = new ArrayList<>();
    private final Set<ScreenStackFragment> c = new HashSet();
    /* access modifiers changed from: private */
    public ScreenStackFragment d = null;
    private final FragmentManager.OnBackStackChangedListener e = new FragmentManager.OnBackStackChangedListener() {
        public void onBackStackChanged() {
            if (ScreenStack.this.getFragmentManager().getBackStackEntryCount() == 0) {
                ScreenStack.this.dismiss(ScreenStack.this.d);
            }
        }
    };
    private final FragmentManager.FragmentLifecycleCallbacks f = new FragmentManager.FragmentLifecycleCallbacks() {
        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            if (ScreenStack.this.d == fragment) {
                ScreenStack.this.setupBackHandlerIfNeeded(ScreenStack.this.d);
            }
        }
    };

    public ScreenStack(Context context) {
        super(context);
    }

    public void dismiss(ScreenStackFragment screenStackFragment) {
        this.c.add(screenStackFragment);
        onUpdate();
    }

    public Screen getTopScreen() {
        return this.d.a();
    }

    public Screen getRootScreen() {
        int screenCount = getScreenCount();
        for (int i = 0; i < screenCount; i++) {
            Screen screenAt = getScreenAt(i);
            if (!this.c.contains(screenAt.getFragment())) {
                return screenAt;
            }
        }
        throw new IllegalStateException("Stack has no root screen set");
    }

    /* access modifiers changed from: protected */
    public ScreenStackFragment adapt(Screen screen) {
        return new ScreenStackFragment(screen);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.removeOnBackStackChangedListener(this.e);
        getFragmentManager().unregisterFragmentLifecycleCallbacks(this.f);
        if (!fragmentManager.isStateSaved()) {
            fragmentManager.popBackStack(f8952a, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getFragmentManager().registerFragmentLifecycleCallbacks(this.f, false);
    }

    /* access modifiers changed from: protected */
    public void removeScreenAt(int i) {
        this.c.remove(getScreenAt(i));
        super.removeScreenAt(i);
    }

    /* access modifiers changed from: protected */
    public void onUpdate() {
        Iterator<ScreenStackFragment> it = this.b.iterator();
        while (it.hasNext()) {
            ScreenStackFragment next = it.next();
            if (!this.mScreenFragments.contains(next) || this.c.contains(next)) {
                getOrCreateTransaction().remove(next);
            }
        }
        int size = this.mScreenFragments.size() - 1;
        ScreenStackFragment screenStackFragment = null;
        final ScreenStackFragment screenStackFragment2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            ScreenStackFragment screenStackFragment3 = (ScreenStackFragment) this.mScreenFragments.get(size);
            if (!this.c.contains(screenStackFragment3)) {
                if (screenStackFragment2 != null) {
                    screenStackFragment = screenStackFragment3;
                    break;
                } else if (screenStackFragment3.a().getStackPresentation() != Screen.StackPresentation.TRANSPARENT_MODAL) {
                    screenStackFragment2 = screenStackFragment3;
                    break;
                } else {
                    screenStackFragment2 = screenStackFragment3;
                }
            }
            size--;
        }
        Iterator it2 = this.mScreenFragments.iterator();
        while (it2.hasNext()) {
            ScreenStackFragment screenStackFragment4 = (ScreenStackFragment) it2.next();
            if (!this.b.contains(screenStackFragment4) && !this.c.contains(screenStackFragment4)) {
                getOrCreateTransaction().add(getId(), (Fragment) screenStackFragment4);
            }
            if (!(screenStackFragment4 == screenStackFragment2 || screenStackFragment4 == screenStackFragment || this.c.contains(screenStackFragment4))) {
                getOrCreateTransaction().hide(screenStackFragment4);
            }
        }
        if (screenStackFragment != null) {
            getOrCreateTransaction().show(screenStackFragment).runOnCommit(new Runnable() {
                public void run() {
                    screenStackFragment2.a().bringToFront();
                }
            });
        }
        if (screenStackFragment2 != null) {
            getOrCreateTransaction().show(screenStackFragment2);
        }
        if (!this.b.contains(screenStackFragment2)) {
            if (this.d != null) {
                int i = 4097;
                switch (this.d.a().getStackAnimation()) {
                    case NONE:
                        i = 0;
                        break;
                    case FADE:
                        i = 4099;
                        break;
                }
                getOrCreateTransaction().setTransition(i);
            }
        } else if (this.d != null && !this.d.equals(screenStackFragment2)) {
            int i2 = 8194;
            switch (this.d.a().getStackAnimation()) {
                case NONE:
                    i2 = 0;
                    break;
                case FADE:
                    i2 = 4099;
                    break;
            }
            getOrCreateTransaction().setTransition(i2);
        }
        this.d = screenStackFragment2;
        this.b.clear();
        this.b.addAll(this.mScreenFragments);
        tryCommitTransaction();
        if (this.d != null) {
            setupBackHandlerIfNeeded(this.d);
        }
        Iterator<ScreenStackFragment> it3 = this.b.iterator();
        while (it3.hasNext()) {
            it3.next().c();
        }
    }

    /* access modifiers changed from: private */
    public void setupBackHandlerIfNeeded(ScreenStackFragment screenStackFragment) {
        if (this.d.isResumed()) {
            getFragmentManager().removeOnBackStackChangedListener(this.e);
            getFragmentManager().popBackStack(f8952a, 1);
            ScreenStackFragment screenStackFragment2 = null;
            int i = 0;
            int size = this.b.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                ScreenStackFragment screenStackFragment3 = this.b.get(i);
                if (!this.c.contains(screenStackFragment3)) {
                    screenStackFragment2 = screenStackFragment3;
                    break;
                }
                i++;
            }
            if (screenStackFragment != screenStackFragment2 && screenStackFragment.d()) {
                getFragmentManager().beginTransaction().hide(screenStackFragment).show(screenStackFragment).addToBackStack(f8952a).setPrimaryNavigationFragment(screenStackFragment).commitAllowingStateLoss();
                getFragmentManager().addOnBackStackChangedListener(this.e);
            }
        }
    }
}
