package miuipub.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    public static final int c = -1;
    public static final int d = -2;

    /* renamed from: a  reason: collision with root package name */
    private DataSetObservable f3058a = new DataSetObservable();

    public abstract int a();

    public int a(Object obj) {
        return -1;
    }

    public void a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void a(View view) {
    }

    public abstract boolean a(int i);

    public abstract boolean a(View view, Object obj);

    public void b(View view) {
    }

    public void b(View view, int i, Object obj) {
    }

    public Parcelable c() {
        return null;
    }

    public CharSequence d(int i) {
        return null;
    }

    public float e(int i) {
        return 1.0f;
    }

    public void a(ViewGroup viewGroup) {
        a((View) viewGroup);
    }

    public Object a(ViewGroup viewGroup, int i) {
        return a((View) viewGroup, i);
    }

    public void a(ViewGroup viewGroup, int i, Object obj) {
        a((View) viewGroup, i, obj);
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        b((View) viewGroup, i, obj);
    }

    public void b(ViewGroup viewGroup) {
        b((View) viewGroup);
    }

    public Object a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public void a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void d() {
        this.f3058a.notifyChanged();
    }

    /* access modifiers changed from: package-private */
    public void a(DataSetObserver dataSetObserver) {
        this.f3058a.registerObserver(dataSetObserver);
    }

    /* access modifiers changed from: package-private */
    public void b(DataSetObserver dataSetObserver) {
        this.f3058a.unregisterObserver(dataSetObserver);
    }
}
