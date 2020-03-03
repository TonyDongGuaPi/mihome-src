package com.transitionseverywhere;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.transitionseverywhere.Transition;
import com.transitionseverywhere.utils.ViewGroupOverlayUtils;
import com.transitionseverywhere.utils.ViewGroupUtils;
import com.transitionseverywhere.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionManager {
    private static String c = "TransitionManager";
    private static Transition d = new AutoTransition();
    private static final String[] e = new String[0];
    /* access modifiers changed from: private */
    public static ArrayList<ViewGroup> f = new ArrayList<>();

    /* renamed from: a  reason: collision with root package name */
    ArrayMap<Scene, Transition> f9475a = new ArrayMap<>();
    ArrayMap<Scene, ArrayMap<Scene, Transition>> b = new ArrayMap<>();

    public void a(Transition transition) {
        d = transition;
    }

    public static Transition a() {
        return d;
    }

    public void a(Scene scene, Transition transition) {
        this.f9475a.put(scene, transition);
    }

    public void a(Scene scene, Scene scene2, Transition transition) {
        ArrayMap arrayMap = this.b.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap();
            this.b.put(scene2, arrayMap);
        }
        arrayMap.put(scene, transition);
    }

    private Transition c(Scene scene) {
        Scene a2;
        ArrayMap arrayMap;
        Transition transition;
        ViewGroup a3 = scene.a();
        if (a3 != null && (a2 = Scene.a((View) a3)) != null && (arrayMap = this.b.get(scene)) != null && (transition = (Transition) arrayMap.get(a2)) != null) {
            return transition;
        }
        Transition transition2 = this.f9475a.get(scene);
        return transition2 != null ? transition2 : d;
    }

    private static void c(Scene scene, Transition transition) {
        ViewGroup a2 = scene.a();
        if (!f.contains(a2)) {
            Transition transition2 = null;
            if (b()) {
                f.add(a2);
                if (transition != null) {
                    transition2 = transition.clone();
                    transition2.b(a2);
                }
                Scene a3 = Scene.a((View) a2);
                if (!(a3 == null || transition2 == null || !a3.d())) {
                    transition2.d(true);
                }
            }
            c(a2, transition2);
            scene.c();
            b(a2, transition2);
        }
    }

    /* access modifiers changed from: private */
    public static ArrayList<Transition> d(ViewGroup viewGroup) {
        ArrayList<Transition> arrayList = (ArrayList) viewGroup.getTag(R.id.runningTransitions);
        if (arrayList != null) {
            return arrayList;
        }
        ArrayList<Transition> arrayList2 = new ArrayList<>();
        viewGroup.setTag(R.id.runningTransitions, arrayList2);
        return arrayList2;
    }

    @TargetApi(12)
    private static void b(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null || !b()) {
            f.remove(viewGroup);
            return;
        }
        ViewGroupOverlayUtils.a(viewGroup);
        MultiListener multiListener = new MultiListener(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(multiListener);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
    }

    @TargetApi(12)
    private static class MultiListener implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {

        /* renamed from: a  reason: collision with root package name */
        Transition f9476a;
        ViewGroup b;

        public void onViewAttachedToWindow(View view) {
        }

        MultiListener(Transition transition, ViewGroup viewGroup) {
            this.f9476a = transition;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        public void onViewDetachedFromWindow(View view) {
            a();
            TransitionManager.f.remove(this.b);
            ArrayList c = TransitionManager.d(this.b);
            if (c.size() > 0) {
                Iterator it = c.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).f(this.b);
                }
            }
            this.f9476a.c(true);
        }

        public boolean onPreDraw() {
            a();
            if (!TransitionManager.f.remove(this.b)) {
                return true;
            }
            ArrayList c = TransitionManager.d(this.b);
            ArrayList arrayList = null;
            if (c.size() > 0) {
                arrayList = new ArrayList(c);
            }
            c.add(this.f9476a);
            this.f9476a.a((Transition.TransitionListener) new Transition.TransitionListenerAdapter() {
                public void b(Transition transition) {
                    TransitionManager.d(MultiListener.this.b).remove(transition);
                }
            });
            boolean b2 = TransitionManager.c((View) this.b);
            this.f9476a.a(this.b, false);
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).f(this.b);
                }
            }
            this.f9476a.a(this.b);
            return !b2;
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(View view) {
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        boolean a2 = ViewGroupUtils.a(viewGroup);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            a2 = c(viewGroup.getChildAt(i)) || a2;
        }
        return a2;
    }

    private static void c(ViewGroup viewGroup, Transition transition) {
        if (b()) {
            ArrayList<Transition> d2 = d(viewGroup);
            if (d2.size() > 0) {
                Iterator<Transition> it = d2.iterator();
                while (it.hasNext()) {
                    it.next().e(viewGroup);
                }
            }
            if (transition != null) {
                transition.a(viewGroup, true);
            }
        }
        Scene a2 = Scene.a((View) viewGroup);
        if (a2 != null) {
            a2.b();
        }
    }

    public void a(Scene scene) {
        c(scene, c(scene));
    }

    public static void b(Scene scene) {
        c(scene, d);
    }

    public static void b(Scene scene, Transition transition) {
        c(scene, transition);
    }

    public static void a(ViewGroup viewGroup) {
        a(viewGroup, (Transition) null);
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (!f.contains(viewGroup) && ViewUtils.a((View) viewGroup, true)) {
            f.add(viewGroup);
            if (transition == null) {
                transition = d;
            }
            Transition w = transition.clone();
            c(viewGroup, w);
            Scene.a(viewGroup, (Scene) null);
            b(viewGroup, w);
        }
    }

    public static void b(ViewGroup viewGroup) {
        f.remove(viewGroup);
        ArrayList<Transition> d2 = d(viewGroup);
        if (d2 != null && !d2.isEmpty()) {
            ArrayList arrayList = new ArrayList(d2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Transition) arrayList.get(size)).o();
            }
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static void a(View view, String str) {
        ViewUtils.a(view, str);
    }

    public static String a(View view) {
        return ViewUtils.c(view);
    }
}
