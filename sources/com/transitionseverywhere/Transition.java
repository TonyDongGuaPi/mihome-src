package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import com.taobao.weex.el.parse.Operators;
import com.transitionseverywhere.utils.AnimatorUtils;
import com.transitionseverywhere.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

@TargetApi(14)
public abstract class Transition implements Cloneable {
    private static final String M = "viewName";
    private static final String N = "id";
    private static final String O = "itemId";
    private static final int[] P = {2, 1, 3, 4};
    private static final ThreadLocal<ArrayMap<Animator, AnimationInfo>> T = new ThreadLocal<>();

    /* renamed from: a  reason: collision with root package name */
    private static final String f9470a = "Transition";
    private static final int b = 1;
    private static final int c = 4;
    private static final String d = "instance";
    private static final String e = "name";
    protected static final boolean f = false;
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 3;
    public static final int j = 4;
    ArrayList<TransitionValues> A;
    ArrayList<TransitionValues> B;
    ViewGroup C = null;
    boolean D = false;
    int E = 0;
    boolean F = false;
    ArrayList<TransitionListener> G = null;
    ArrayList<Animator> H = new ArrayList<>();
    TransitionPropagation I;
    EpicenterCallback J;
    ArrayMap<String, String> K;
    PathMotion L = PathMotion.f9461a;
    private String Q = getClass().getName();
    private TransitionValuesMaps R = new TransitionValuesMaps();
    private TransitionValuesMaps S = new TransitionValuesMaps();
    /* access modifiers changed from: private */
    public ArrayList<Animator> U = new ArrayList<>();
    private boolean V = false;
    long k = -1;
    long l = -1;
    TimeInterpolator m = null;
    ArrayList<Integer> n = new ArrayList<>();
    ArrayList<View> o = new ArrayList<>();
    ArrayList<String> p = null;
    ArrayList<Class> q = null;
    ArrayList<Integer> r = null;
    ArrayList<View> s = null;
    ArrayList<Class> t = null;
    ArrayList<String> u = null;
    ArrayList<Integer> v = null;
    ArrayList<View> w = null;
    ArrayList<Class> x = null;
    TransitionSet y = null;
    int[] z = P;

    public static abstract class EpicenterCallback {
        public abstract Rect a(Transition transition);
    }

    public interface TransitionListener {
        void a(Transition transition);

        void b(Transition transition);

        void c(Transition transition);

        void d(Transition transition);

        void e(Transition transition);
    }

    public static class TransitionListenerAdapter implements TransitionListener {
        public void a(Transition transition) {
        }

        public void b(Transition transition) {
        }

        public void c(Transition transition) {
        }

        public void d(Transition transition) {
        }

        public void e(Transition transition) {
        }
    }

    private static boolean a(int i2) {
        return i2 >= 1 && i2 <= 4;
    }

    public Animator a(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public abstract void a(TransitionValues transitionValues);

    public String[] a() {
        return null;
    }

    public abstract void b(TransitionValues transitionValues);

    public void c(int i2, boolean z2) {
    }

    public Transition() {
    }

    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
        long j2 = (long) obtainStyledAttributes.getInt(R.styleable.Transition_duration, -1);
        if (j2 >= 0) {
            a(j2);
        } else {
            long j3 = (long) obtainStyledAttributes.getInt(R.styleable.Transition_android_duration, -1);
            if (j3 >= 0) {
                a(j3);
            }
        }
        long j4 = (long) obtainStyledAttributes.getInt(R.styleable.Transition_startDelay, -1);
        if (j4 > 0) {
            b(j4);
        }
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.Transition_interpolator, 0);
        if (resourceId > 0) {
            a((TimeInterpolator) AnimationUtils.loadInterpolator(context, resourceId));
        } else {
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.Transition_android_interpolator, 0);
            if (resourceId2 > 0) {
                a((TimeInterpolator) AnimationUtils.loadInterpolator(context, resourceId2));
            }
        }
        String string = obtainStyledAttributes.getString(R.styleable.Transition_matchOrder);
        if (string != null) {
            a(d(string));
        }
        obtainStyledAttributes.recycle();
    }

    private static int[] d(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(trim)) {
                iArr[i2] = 3;
            } else if (d.equalsIgnoreCase(trim)) {
                iArr[i2] = 1;
            } else if ("name".equalsIgnoreCase(trim)) {
                iArr[i2] = 2;
            } else if (M.equalsIgnoreCase(trim)) {
                iArr[i2] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                iArr[i2] = 4;
            } else if (trim.isEmpty()) {
                int[] iArr2 = new int[(iArr.length - 1)];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                i2--;
                iArr = iArr2;
            } else {
                throw new InflateException("Unknown match type in matchOrder: '" + trim + "'");
            }
            i2++;
        }
        return iArr;
    }

    public Transition a(long j2) {
        this.l = j2;
        return this;
    }

    public long e() {
        return this.l;
    }

    public Transition b(long j2) {
        this.k = j2;
        return this;
    }

    public long f() {
        return this.k;
    }

    public Transition a(TimeInterpolator timeInterpolator) {
        this.m = timeInterpolator;
        return this;
    }

    public TimeInterpolator g() {
        return this.m;
    }

    public Transition a(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.z = P;
        } else {
            int i2 = 0;
            while (i2 < iArr.length) {
                if (!a(iArr[i2])) {
                    throw new IllegalArgumentException("matches contains invalid value");
                } else if (!a(iArr, i2)) {
                    i2++;
                } else {
                    throw new IllegalArgumentException("matches contains a duplicate value");
                }
            }
            this.z = (int[]) iArr.clone();
        }
        return this;
    }

    private static boolean a(int[] iArr, int i2) {
        int i3 = iArr[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (iArr[i4] == i3) {
                return true;
            }
        }
        return false;
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        TransitionValues remove;
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View keyAt = arrayMap.keyAt(size);
            if (!(keyAt == null || !b(keyAt) || (remove = arrayMap2.remove(keyAt)) == null || remove.f9482a == null || !b(remove.f9482a))) {
                this.A.add(arrayMap.removeAt(size));
                this.B.add(remove);
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        View view;
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = longSparseArray.valueAt(i2);
            if (valueAt != null && b(valueAt) && (view = longSparseArray2.get(longSparseArray.keyAt(i2))) != null && b(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = sparseArray.valueAt(i2);
            if (valueAt != null && b(valueAt) && (view = sparseArray2.get(sparseArray.keyAt(i2))) != null && b(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = arrayMap3.valueAt(i2);
            if (valueAt != null && b(valueAt) && (view = arrayMap4.get(arrayMap3.keyAt(i2))) != null && b(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (!(transitionValues == null || transitionValues2 == null)) {
                    this.A.add(transitionValues);
                    this.B.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void b(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            this.A.add(arrayMap.valueAt(i2));
            this.B.add((Object) null);
        }
        for (int i3 = 0; i3 < arrayMap2.size(); i3++) {
            this.B.add(arrayMap2.valueAt(i3));
            this.A.add((Object) null);
        }
    }

    private void a(TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2) {
        ArrayMap arrayMap = new ArrayMap((SimpleArrayMap) transitionValuesMaps.f9483a);
        ArrayMap arrayMap2 = new ArrayMap((SimpleArrayMap) transitionValuesMaps2.f9483a);
        for (int i2 : this.z) {
            switch (i2) {
                case 1:
                    a((ArrayMap<View, TransitionValues>) arrayMap, (ArrayMap<View, TransitionValues>) arrayMap2);
                    break;
                case 2:
                    a((ArrayMap<View, TransitionValues>) arrayMap, (ArrayMap<View, TransitionValues>) arrayMap2, transitionValuesMaps.d, transitionValuesMaps2.d);
                    break;
                case 3:
                    a((ArrayMap<View, TransitionValues>) arrayMap, (ArrayMap<View, TransitionValues>) arrayMap2, transitionValuesMaps.b, transitionValuesMaps2.b);
                    break;
                case 4:
                    a((ArrayMap<View, TransitionValues>) arrayMap, (ArrayMap<View, TransitionValues>) arrayMap2, transitionValuesMaps.c, transitionValuesMaps2.c);
                    break;
            }
        }
        b((ArrayMap<View, TransitionValues>) arrayMap, (ArrayMap<View, TransitionValues>) arrayMap2);
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        int i2;
        int i3;
        Animator a2;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        TransitionValues transitionValues2;
        Animator animator2;
        Animator animator3;
        ViewGroup viewGroup2 = viewGroup;
        ArrayMap<Animator, AnimationInfo> b2 = b();
        this.H.size();
        SparseArray sparseArray = new SparseArray();
        int size = arrayList.size();
        long j2 = Long.MAX_VALUE;
        int i4 = 0;
        while (i4 < size) {
            TransitionValues transitionValues3 = arrayList.get(i4);
            TransitionValues transitionValues4 = arrayList2.get(i4);
            if (transitionValues3 != null && !transitionValues3.c.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.c.contains(this)) {
                transitionValues4 = null;
            }
            if (!(transitionValues3 == null && transitionValues4 == null)) {
                if ((transitionValues3 == null || transitionValues4 == null || a(transitionValues3, transitionValues4)) && (a2 = a(viewGroup2, transitionValues3, transitionValues4)) != null) {
                    if (transitionValues4 != null) {
                        view = transitionValues4.f9482a;
                        String[] a3 = a();
                        if (view == null || a3 == null || a3.length <= 0) {
                            i3 = size;
                            i2 = i4;
                            animator2 = a2;
                            transitionValues2 = null;
                        } else {
                            transitionValues2 = new TransitionValues();
                            transitionValues2.f9482a = view;
                            Animator animator4 = a2;
                            i3 = size;
                            TransitionValues transitionValues5 = transitionValuesMaps2.f9483a.get(view);
                            if (transitionValues5 != null) {
                                int i5 = 0;
                                while (i5 < a3.length) {
                                    transitionValues2.b.put(a3[i5], transitionValues5.b.get(a3[i5]));
                                    i5++;
                                    i4 = i4;
                                    transitionValues5 = transitionValues5;
                                    ArrayList<TransitionValues> arrayList3 = arrayList2;
                                }
                            }
                            i2 = i4;
                            synchronized (T) {
                                int size2 = b2.size();
                                int i6 = 0;
                                while (true) {
                                    if (i6 >= size2) {
                                        animator3 = animator4;
                                        break;
                                    }
                                    AnimationInfo animationInfo = b2.get(b2.keyAt(i6));
                                    if (animationInfo.c != null && animationInfo.f9473a == view && (((animationInfo.b == null && x() == null) || (animationInfo.b != null && animationInfo.b.equals(x()))) && animationInfo.c.equals(transitionValues2))) {
                                        animator3 = null;
                                        break;
                                    }
                                    i6++;
                                }
                            }
                            animator2 = animator3;
                        }
                        animator = animator2;
                        transitionValues = transitionValues2;
                    } else {
                        i3 = size;
                        i2 = i4;
                        view = transitionValues3.f9482a;
                        animator = a2;
                        transitionValues = null;
                    }
                    if (animator != null) {
                        if (this.I != null) {
                            long a4 = this.I.a(viewGroup2, this, transitionValues3, transitionValues4);
                            sparseArray.put(this.H.size(), Long.valueOf(a4));
                            j2 = Math.min(a4, j2);
                        }
                        b2.put(animator, new AnimationInfo(view, x(), this, ViewUtils.f(viewGroup), transitionValues));
                        this.H.add(animator);
                        j2 = j2;
                    }
                    i4 = i2 + 1;
                    size = i3;
                }
            }
            i3 = size;
            i2 = i4;
            i4 = i2 + 1;
            size = i3;
        }
        if (sparseArray.size() != 0) {
            for (int i7 = 0; i7 < sparseArray.size(); i7++) {
                Animator animator5 = this.H.get(sparseArray.keyAt(i7));
                animator5.setStartDelay((((Long) sparseArray.valueAt(i7)).longValue() - j2) + animator5.getStartDelay());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(View view) {
        if (view == null) {
            return false;
        }
        int id = view.getId();
        if (this.r != null && this.r.contains(Integer.valueOf(id))) {
            return false;
        }
        if (this.s != null && this.s.contains(view)) {
            return false;
        }
        if (this.t != null) {
            int size = this.t.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.t.get(i2).isInstance(view)) {
                    return false;
                }
            }
        }
        String c2 = ViewUtils.c(view);
        if (this.u != null && c2 != null && this.u.contains(c2)) {
            return false;
        }
        if ((this.n.size() == 0 && this.o.size() == 0 && ((this.q == null || this.q.isEmpty()) && (this.p == null || this.p.isEmpty()))) || this.n.contains(Integer.valueOf(id)) || this.o.contains(view)) {
            return true;
        }
        if (this.p != null && this.p.contains(c2)) {
            return true;
        }
        if (this.q != null) {
            for (int i3 = 0; i3 < this.q.size(); i3++) {
                if (this.q.get(i3).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static ArrayMap<Animator, AnimationInfo> b() {
        ArrayMap<Animator, AnimationInfo> arrayMap = T.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, AnimationInfo> arrayMap2 = new ArrayMap<>();
        T.set(arrayMap2);
        return arrayMap2;
    }

    /* access modifiers changed from: protected */
    public void h() {
        n();
        ArrayMap<Animator, AnimationInfo> b2 = b();
        Iterator<Animator> it = this.H.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (b2.containsKey(next)) {
                n();
                a(next, b2);
            }
        }
        this.H.clear();
        o();
    }

    private void a(Animator animator, final ArrayMap<Animator, AnimationInfo> arrayMap) {
        if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    Transition.this.U.add(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    arrayMap.remove(animator);
                    Transition.this.U.remove(animator);
                }
            });
            a(animator);
        }
    }

    public Transition c(int i2) {
        if (i2 > 0) {
            this.n.add(Integer.valueOf(i2));
        }
        return this;
    }

    public Transition a(String str) {
        if (str != null) {
            if (this.p == null) {
                this.p = new ArrayList<>();
            }
            this.p.add(str);
        }
        return this;
    }

    public Transition a(Class cls) {
        if (cls != null) {
            if (this.q == null) {
                this.q = new ArrayList<>();
            }
            this.q.add(cls);
        }
        return this;
    }

    public Transition b(String str) {
        if (!(str == null || this.p == null)) {
            this.p.remove(str);
        }
        return this;
    }

    public Transition d(int i2) {
        if (i2 > 0) {
            this.n.remove(Integer.valueOf(i2));
        }
        return this;
    }

    public Transition a(int i2, boolean z2) {
        if (i2 >= 0) {
            this.r = a(this.r, Integer.valueOf(i2), z2);
        }
        return this;
    }

    public Transition a(String str, boolean z2) {
        this.u = a(this.u, str, z2);
        return this;
    }

    public Transition b(int i2, boolean z2) {
        if (i2 >= 0) {
            this.v = a(this.v, Integer.valueOf(i2), z2);
        }
        return this;
    }

    public Transition a(View view, boolean z2) {
        this.s = a(this.s, view, z2);
        return this;
    }

    public Transition b(View view, boolean z2) {
        this.w = a(this.w, view, z2);
        return this;
    }

    private static <T> ArrayList<T> a(ArrayList<T> arrayList, T t2, boolean z2) {
        if (t2 == null) {
            return arrayList;
        }
        if (z2) {
            return ArrayListManager.a(arrayList, t2);
        }
        return ArrayListManager.b(arrayList, t2);
    }

    public Transition a(Class cls, boolean z2) {
        this.t = a(this.t, cls, z2);
        return this;
    }

    public Transition b(Class cls, boolean z2) {
        this.x = a(this.x, cls, z2);
        return this;
    }

    public Transition c(View view) {
        this.o.add(view);
        return this;
    }

    public Transition d(View view) {
        if (view != null) {
            this.o.remove(view);
        }
        return this;
    }

    public Transition b(Class cls) {
        if (cls != null) {
            this.q.remove(cls);
        }
        return this;
    }

    public List<Integer> i() {
        return this.n;
    }

    public List<View> j() {
        return this.o;
    }

    public List<String> k() {
        return this.p;
    }

    public List<String> l() {
        return this.p;
    }

    public List<Class> m() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, boolean z2) {
        c(z2);
        if ((this.n.size() > 0 || this.o.size() > 0) && ((this.p == null || this.p.isEmpty()) && (this.q == null || this.q.isEmpty()))) {
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                View findViewById = viewGroup.findViewById(this.n.get(i2).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues();
                    transitionValues.f9482a = findViewById;
                    if (z2) {
                        a(transitionValues);
                    } else {
                        b(transitionValues);
                    }
                    transitionValues.c.add(this);
                    c(transitionValues);
                    if (z2) {
                        a(this.R, findViewById, transitionValues);
                    } else {
                        a(this.S, findViewById, transitionValues);
                    }
                }
            }
            for (int i3 = 0; i3 < this.o.size(); i3++) {
                View view = this.o.get(i3);
                TransitionValues transitionValues2 = new TransitionValues();
                transitionValues2.f9482a = view;
                if (z2) {
                    a(transitionValues2);
                } else {
                    b(transitionValues2);
                }
                transitionValues2.c.add(this);
                c(transitionValues2);
                if (z2) {
                    a(this.R, view, transitionValues2);
                } else {
                    a(this.S, view, transitionValues2);
                }
            }
        } else {
            e(viewGroup, z2);
        }
        if (!z2 && this.K != null) {
            int size = this.K.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i4 = 0; i4 < size; i4++) {
                arrayList.add(this.R.d.remove(this.K.keyAt(i4)));
            }
            for (int i5 = 0; i5 < size; i5++) {
                View view2 = (View) arrayList.get(i5);
                if (view2 != null) {
                    this.R.d.put(this.K.valueAt(i5), view2);
                }
            }
        }
    }

    static void a(TransitionValuesMaps transitionValuesMaps, View view, TransitionValues transitionValues) {
        transitionValuesMaps.f9483a.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (transitionValuesMaps.b.indexOfKey(id) >= 0) {
                transitionValuesMaps.b.put(id, (Object) null);
            } else {
                transitionValuesMaps.b.put(id, view);
            }
        }
        String c2 = ViewUtils.c(view);
        if (c2 != null) {
            if (transitionValuesMaps.d.containsKey(c2)) {
                transitionValuesMaps.d.put(c2, null);
            } else {
                transitionValuesMaps.d.put(c2, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (transitionValuesMaps.c.indexOfKey(itemIdAtPosition) >= 0) {
                    View view2 = transitionValuesMaps.c.get(itemIdAtPosition);
                    if (view2 != null) {
                        ViewUtils.b(view2, false);
                        transitionValuesMaps.c.put(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                ViewUtils.b(view, true);
                transitionValuesMaps.c.put(itemIdAtPosition, view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z2) {
        if (z2) {
            this.R.f9483a.clear();
            this.R.b.clear();
            this.R.c.clear();
            this.R.d.clear();
            this.A = null;
            return;
        }
        this.S.f9483a.clear();
        this.S.b.clear();
        this.S.c.clear();
        this.S.d.clear();
        this.B = null;
    }

    private void e(View view, boolean z2) {
        if (view != null) {
            int id = view.getId();
            if (this.r != null && this.r.contains(Integer.valueOf(id))) {
                return;
            }
            if (this.s == null || !this.s.contains(view)) {
                if (this.t != null) {
                    int size = this.t.size();
                    int i2 = 0;
                    while (i2 < size) {
                        if (!this.t.get(i2).isInstance(view)) {
                            i2++;
                        } else {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues = new TransitionValues();
                    transitionValues.f9482a = view;
                    if (z2) {
                        a(transitionValues);
                    } else {
                        b(transitionValues);
                    }
                    transitionValues.c.add(this);
                    c(transitionValues);
                    if (z2) {
                        a(this.R, view, transitionValues);
                    } else {
                        a(this.S, view, transitionValues);
                    }
                }
                if (!(view instanceof ViewGroup)) {
                    return;
                }
                if (this.v != null && this.v.contains(Integer.valueOf(id))) {
                    return;
                }
                if (this.w == null || !this.w.contains(view)) {
                    if (this.x != null) {
                        int size2 = this.x.size();
                        int i3 = 0;
                        while (i3 < size2) {
                            if (!this.x.get(i3).isInstance(view)) {
                                i3++;
                            } else {
                                return;
                            }
                        }
                    }
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                        e(viewGroup.getChildAt(i4), z2);
                    }
                }
            }
        }
    }

    public TransitionValues c(View view, boolean z2) {
        if (this.y != null) {
            return this.y.c(view, z2);
        }
        return (z2 ? this.R : this.S).f9483a.get(view);
    }

    /* access modifiers changed from: package-private */
    public TransitionValues d(View view, boolean z2) {
        if (this.y != null) {
            return this.y.d(view, z2);
        }
        ArrayList<TransitionValues> arrayList = z2 ? this.A : this.B;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i2 = -1;
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            TransitionValues transitionValues = arrayList.get(i3);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.f9482a == view) {
                i2 = i3;
                break;
            }
            i3++;
        }
        if (i2 < 0) {
            return null;
        }
        return (z2 ? this.B : this.A).get(i2);
    }

    public void e(View view) {
        if (!this.V) {
            synchronized (T) {
                ArrayMap<Animator, AnimationInfo> b2 = b();
                int size = b2.size();
                if (view != null) {
                    Object f2 = ViewUtils.f(view);
                    for (int i2 = size - 1; i2 >= 0; i2--) {
                        AnimationInfo valueAt = b2.valueAt(i2);
                        if (!(valueAt.f9473a == null || f2 == null || !f2.equals(valueAt.d))) {
                            AnimatorUtils.a(b2.keyAt(i2));
                        }
                    }
                }
            }
            if (this.G != null && this.G.size() > 0) {
                ArrayList arrayList = (ArrayList) this.G.clone();
                int size2 = arrayList.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    ((TransitionListener) arrayList.get(i3)).c(this);
                }
            }
            this.F = true;
        }
    }

    public void f(View view) {
        if (this.F) {
            if (!this.V) {
                ArrayMap<Animator, AnimationInfo> b2 = b();
                int size = b2.size();
                Object f2 = ViewUtils.f(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    AnimationInfo valueAt = b2.valueAt(i2);
                    if (!(valueAt.f9473a == null || f2 == null || !f2.equals(valueAt.d))) {
                        AnimatorUtils.b(b2.keyAt(i2));
                    }
                }
                if (this.G != null && this.G.size() > 0) {
                    ArrayList arrayList = (ArrayList) this.G.clone();
                    int size2 = arrayList.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((TransitionListener) arrayList.get(i3)).d(this);
                    }
                }
            }
            this.F = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup) {
        AnimationInfo animationInfo;
        this.A = new ArrayList<>();
        this.B = new ArrayList<>();
        a(this.R, this.S);
        ArrayMap<Animator, AnimationInfo> b2 = b();
        synchronized (T) {
            int size = b2.size();
            Object f2 = ViewUtils.f(viewGroup);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                Animator keyAt = b2.keyAt(i2);
                if (!(keyAt == null || (animationInfo = b2.get(keyAt)) == null || animationInfo.f9473a == null || animationInfo.d != f2)) {
                    TransitionValues transitionValues = animationInfo.c;
                    View view = animationInfo.f9473a;
                    TransitionValues c2 = c(view, true);
                    TransitionValues d2 = d(view, true);
                    if (c2 == null && d2 == null) {
                        d2 = this.S.f9483a.get(view);
                    }
                    if (!(c2 == null && d2 == null) && animationInfo.e.a(transitionValues, d2)) {
                        if (!keyAt.isRunning()) {
                            if (!AnimatorUtils.c(keyAt)) {
                                b2.remove(keyAt);
                            }
                        }
                        keyAt.cancel();
                    }
                }
            }
        }
        a(viewGroup, this.R, this.S, this.A, this.B);
        h();
    }

    public boolean a(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] a2 = a();
        if (a2 != null) {
            int length = a2.length;
            int i2 = 0;
            while (i2 < length) {
                if (!a(transitionValues, transitionValues2, a2[i2])) {
                    i2++;
                }
            }
            return false;
        }
        for (String a3 : transitionValues.b.keySet()) {
            if (a(transitionValues, transitionValues2, a3)) {
            }
        }
        return false;
        return true;
    }

    private static boolean a(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        if (transitionValues.b.containsKey(str) != transitionValues2.b.containsKey(str)) {
            return false;
        }
        Object obj = transitionValues.b.get(str);
        Object obj2 = transitionValues2.b.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    /* access modifiers changed from: protected */
    public void a(Animator animator) {
        if (animator == null) {
            o();
            return;
        }
        if (e() >= 0) {
            animator.setDuration(e());
        }
        if (f() >= 0) {
            animator.setStartDelay(f() + animator.getStartDelay());
        }
        if (g() != null) {
            animator.setInterpolator(g());
        }
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Transition.this.o();
                animator.removeListener(this);
            }
        });
        animator.start();
    }

    /* access modifiers changed from: protected */
    public void n() {
        if (this.E == 0) {
            if (this.G != null && this.G.size() > 0) {
                ArrayList arrayList = (ArrayList) this.G.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList.get(i2)).e(this);
                }
            }
            this.V = false;
        }
        this.E++;
    }

    /* access modifiers changed from: protected */
    public void o() {
        this.E--;
        if (this.E == 0) {
            if (this.G != null && this.G.size() > 0) {
                ArrayList arrayList = (ArrayList) this.G.clone();
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList.get(i2)).b(this);
                }
            }
            for (int i3 = 0; i3 < this.R.c.size(); i3++) {
                View valueAt = this.R.c.valueAt(i3);
                if (valueAt != null) {
                    ViewUtils.b(valueAt, false);
                }
            }
            for (int i4 = 0; i4 < this.S.c.size(); i4++) {
                View valueAt2 = this.S.c.valueAt(i4);
                if (valueAt2 != null) {
                    ViewUtils.b(valueAt2, false);
                }
            }
            this.V = true;
        }
    }

    /* access modifiers changed from: protected */
    public void p() {
        for (int size = this.U.size() - 1; size >= 0; size--) {
            this.U.get(size).cancel();
        }
        if (this.G != null && this.G.size() > 0) {
            ArrayList arrayList = (ArrayList) this.G.clone();
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((TransitionListener) arrayList.get(i2)).a(this);
            }
        }
    }

    public Transition a(TransitionListener transitionListener) {
        if (this.G == null) {
            this.G = new ArrayList<>();
        }
        this.G.add(transitionListener);
        return this;
    }

    public Transition b(TransitionListener transitionListener) {
        if (this.G == null) {
            return this;
        }
        this.G.remove(transitionListener);
        if (this.G.size() == 0) {
            this.G = null;
        }
        return this;
    }

    public Transition a(EpicenterCallback epicenterCallback) {
        this.J = epicenterCallback;
        return this;
    }

    public EpicenterCallback q() {
        return this.J;
    }

    public Rect r() {
        if (this.J == null) {
            return null;
        }
        return this.J.a(this);
    }

    public Transition a(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.L = PathMotion.f9461a;
        } else {
            this.L = pathMotion;
        }
        return this;
    }

    public PathMotion s() {
        return this.L;
    }

    public Transition a(TransitionPropagation transitionPropagation) {
        this.I = transitionPropagation;
        return this;
    }

    public TransitionPropagation t() {
        return this.I;
    }

    /* access modifiers changed from: package-private */
    public void c(TransitionValues transitionValues) {
        String[] a2;
        if (this.I != null && !transitionValues.b.isEmpty() && (a2 = this.I.a()) != null) {
            boolean z2 = false;
            int i2 = 0;
            while (true) {
                if (i2 >= a2.length) {
                    z2 = true;
                    break;
                } else if (!transitionValues.b.containsKey(a2[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
            if (!z2) {
                this.I.a(transitionValues);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Transition b(ViewGroup viewGroup) {
        this.C = viewGroup;
        return this;
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z2) {
        this.D = z2;
    }

    public boolean u() {
        return this.D;
    }

    public void a(ArrayMap<String, String> arrayMap) {
        this.K = arrayMap;
    }

    public ArrayMap<String, String> v() {
        return this.K;
    }

    public String toString() {
        return c("");
    }

    /* renamed from: w */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            try {
                transition.H = new ArrayList<>();
                transition.R = new TransitionValuesMaps();
                transition.S = new TransitionValuesMaps();
                transition.A = null;
                transition.B = null;
                return transition;
            } catch (CloneNotSupportedException unused) {
                return transition;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public String x() {
        return this.Q;
    }

    /* access modifiers changed from: package-private */
    public String c(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.l != -1) {
            str2 = str2 + "dur(" + this.l + ") ";
        }
        if (this.k != -1) {
            str2 = str2 + "dly(" + this.k + ") ";
        }
        if (this.m != null) {
            str2 = str2 + "interp(" + this.m + ") ";
        }
        if (this.n.size() <= 0 && this.o.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.n.size() > 0) {
            String str4 = str3;
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                if (i2 > 0) {
                    str4 = str4 + ", ";
                }
                str4 = str4 + this.n.get(i2);
            }
            str3 = str4;
        }
        if (this.o.size() > 0) {
            for (int i3 = 0; i3 < this.o.size(); i3++) {
                if (i3 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.o.get(i3);
            }
        }
        return str3 + Operators.BRACKET_END_STR;
    }

    public static class AnimationInfo {

        /* renamed from: a  reason: collision with root package name */
        public View f9473a;
        String b;
        TransitionValues c;
        Object d;
        Transition e;

        AnimationInfo(View view, String str, Transition transition, Object obj, TransitionValues transitionValues) {
            this.f9473a = view;
            this.b = str;
            this.c = transitionValues;
            this.d = obj;
            this.e = transition;
        }
    }

    private static class ArrayListManager {
        private ArrayListManager() {
        }

        static <T> ArrayList<T> a(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
            return arrayList;
        }

        static <T> ArrayList<T> b(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(t);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }
}
