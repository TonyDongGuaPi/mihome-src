package com.transitionseverywhere;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.transitionseverywhere.Transition;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet extends Transition {
    public static final int d = 0;
    public static final int e = 1;
    private boolean M = true;

    /* renamed from: a  reason: collision with root package name */
    ArrayList<Transition> f9478a = new ArrayList<>();
    int b;
    boolean c = false;

    public TransitionSet() {
    }

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TransitionSet);
        a(obtainStyledAttributes.getInt(R.styleable.TransitionSet_transitionOrdering, 0));
        obtainStyledAttributes.recycle();
    }

    public TransitionSet a(int i) {
        switch (i) {
            case 0:
                this.M = true;
                break;
            case 1:
                this.M = false;
                break;
            default:
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    public int b() {
        return this.M ^ true ? 1 : 0;
    }

    public TransitionSet b(Transition transition) {
        if (transition != null) {
            d(transition);
            if (this.l >= 0) {
                transition.a(this.l);
            }
            if (this.m != null) {
                transition.a(this.m);
            }
        }
        return this;
    }

    private void d(Transition transition) {
        this.f9478a.add(transition);
        transition.y = this;
    }

    public int c() {
        return this.f9478a.size();
    }

    public Transition b(int i) {
        if (i < 0 || i >= this.f9478a.size()) {
            return null;
        }
        return this.f9478a.get(i);
    }

    /* renamed from: c */
    public TransitionSet a(long j) {
        super.a(j);
        if (this.l >= 0 && this.f9478a != null) {
            int size = this.f9478a.size();
            for (int i = 0; i < size; i++) {
                this.f9478a.get(i).a(j);
            }
        }
        return this;
    }

    /* renamed from: d */
    public TransitionSet b(long j) {
        return (TransitionSet) super.b(j);
    }

    /* renamed from: b */
    public TransitionSet a(TimeInterpolator timeInterpolator) {
        super.a(timeInterpolator);
        if (!(this.m == null || this.f9478a == null)) {
            int size = this.f9478a.size();
            for (int i = 0; i < size; i++) {
                this.f9478a.get(i).a(this.m);
            }
        }
        return this;
    }

    /* renamed from: a */
    public TransitionSet c(View view) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).c(view);
        }
        return (TransitionSet) super.c(view);
    }

    /* renamed from: e */
    public TransitionSet c(int i) {
        for (int i2 = 0; i2 < this.f9478a.size(); i2++) {
            this.f9478a.get(i2).c(i);
        }
        return (TransitionSet) super.c(i);
    }

    /* renamed from: d */
    public TransitionSet a(String str) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(str);
        }
        return (TransitionSet) super.a(str);
    }

    /* renamed from: c */
    public TransitionSet a(Class cls) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(cls);
        }
        return (TransitionSet) super.a(cls);
    }

    /* renamed from: c */
    public TransitionSet a(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.a(transitionListener);
    }

    /* renamed from: f */
    public TransitionSet d(int i) {
        for (int i2 = 0; i2 < this.f9478a.size(); i2++) {
            this.f9478a.get(i2).d(i);
        }
        return (TransitionSet) super.d(i);
    }

    /* renamed from: g */
    public TransitionSet d(View view) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).d(view);
        }
        return (TransitionSet) super.d(view);
    }

    /* renamed from: d */
    public TransitionSet b(Class cls) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).b(cls);
        }
        return (TransitionSet) super.b(cls);
    }

    /* renamed from: e */
    public TransitionSet b(String str) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).b(str);
        }
        return (TransitionSet) super.b(str);
    }

    public Transition a(View view, boolean z) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(view, z);
        }
        return super.a(view, z);
    }

    public Transition a(String str, boolean z) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(str, z);
        }
        return super.a(str, z);
    }

    public Transition a(int i, boolean z) {
        for (int i2 = 0; i2 < this.f9478a.size(); i2++) {
            this.f9478a.get(i2).a(i, z);
        }
        return super.a(i, z);
    }

    public Transition a(Class cls, boolean z) {
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(cls, z);
        }
        return super.a(cls, z);
    }

    /* renamed from: d */
    public TransitionSet b(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.b(transitionListener);
    }

    /* renamed from: b */
    public TransitionSet a(PathMotion pathMotion) {
        super.a(pathMotion);
        for (int i = 0; i < this.f9478a.size(); i++) {
            this.f9478a.get(i).a(pathMotion);
        }
        return this;
    }

    public void c(int i, boolean z) {
        int size = this.f9478a.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f9478a.get(i2).c(i, z);
        }
    }

    public TransitionSet c(Transition transition) {
        this.f9478a.remove(transition);
        transition.y = null;
        return this;
    }

    private void y() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<Transition> it = this.f9478a.iterator();
        while (it.hasNext()) {
            it.next().a((Transition.TransitionListener) transitionSetListener);
        }
        this.b = this.f9478a.size();
    }

    static class TransitionSetListener extends Transition.TransitionListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        TransitionSet f9480a;

        TransitionSetListener(TransitionSet transitionSet) {
            this.f9480a = transitionSet;
        }

        public void e(Transition transition) {
            if (!this.f9480a.c) {
                this.f9480a.n();
                this.f9480a.c = true;
            }
        }

        public void b(Transition transition) {
            TransitionSet transitionSet = this.f9480a;
            transitionSet.b--;
            if (this.f9480a.b == 0) {
                this.f9480a.c = false;
                this.f9480a.o();
            }
            transition.b((Transition.TransitionListener) this);
        }
    }

    /* access modifiers changed from: protected */
    public void a(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long f = f();
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            Transition transition = this.f9478a.get(i);
            if (f > 0 && (this.M || i == 0)) {
                long f2 = transition.f();
                if (f2 > 0) {
                    transition.b(f2 + f);
                } else {
                    transition.b(f);
                }
            }
            transition.a(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    /* access modifiers changed from: protected */
    public void h() {
        if (this.f9478a.isEmpty()) {
            n();
            o();
            return;
        }
        y();
        int size = this.f9478a.size();
        if (!this.M) {
            for (int i = 1; i < size; i++) {
                final Transition transition = this.f9478a.get(i);
                this.f9478a.get(i - 1).a((Transition.TransitionListener) new Transition.TransitionListenerAdapter() {
                    public void b(Transition transition) {
                        transition.h();
                        transition.b((Transition.TransitionListener) this);
                    }
                });
            }
            Transition transition2 = this.f9478a.get(0);
            if (transition2 != null) {
                transition2.h();
                return;
            }
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.f9478a.get(i2).h();
        }
    }

    public void a(TransitionValues transitionValues) {
        if (b(transitionValues.f9482a)) {
            Iterator<Transition> it = this.f9478a.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.b(transitionValues.f9482a)) {
                    next.a(transitionValues);
                    transitionValues.c.add(next);
                }
            }
        }
    }

    public void b(TransitionValues transitionValues) {
        if (b(transitionValues.f9482a)) {
            Iterator<Transition> it = this.f9478a.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.b(transitionValues.f9482a)) {
                    next.b(transitionValues);
                    transitionValues.c.add(next);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(TransitionValues transitionValues) {
        super.c(transitionValues);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).c(transitionValues);
        }
    }

    public void e(View view) {
        super.e(view);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).e(view);
        }
    }

    public void f(View view) {
        super.f(view);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).f(view);
        }
    }

    /* access modifiers changed from: protected */
    public void p() {
        super.p();
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).p();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public TransitionSet b(ViewGroup viewGroup) {
        super.b(viewGroup);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).b(viewGroup);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void d(boolean z) {
        super.d(z);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).d(z);
        }
    }

    /* renamed from: b */
    public TransitionSet a(TransitionPropagation transitionPropagation) {
        super.a(transitionPropagation);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).a(transitionPropagation);
        }
        return this;
    }

    /* renamed from: b */
    public TransitionSet a(Transition.EpicenterCallback epicenterCallback) {
        super.a(epicenterCallback);
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            this.f9478a.get(i).a(epicenterCallback);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public String c(String str) {
        String c2 = super.c(str);
        for (int i = 0; i < this.f9478a.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(c2);
            sb.append("\n");
            sb.append(this.f9478a.get(i).c(str + "  "));
            c2 = sb.toString();
        }
        return c2;
    }

    /* renamed from: d */
    public TransitionSet w() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.f9478a = new ArrayList<>();
        int size = this.f9478a.size();
        for (int i = 0; i < size; i++) {
            transitionSet.d(this.f9478a.get(i).clone());
        }
        return transitionSet;
    }
}
