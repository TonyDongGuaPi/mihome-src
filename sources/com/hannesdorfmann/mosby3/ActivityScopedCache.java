package com.hannesdorfmann.mosby3;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import java.util.Map;

class ActivityScopedCache {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, PresenterHolder> f5741a = new ArrayMap();

    ActivityScopedCache() {
    }

    public void a() {
        this.f5741a.clear();
    }

    @Nullable
    public <P> P a(@NonNull String str) {
        PresenterHolder presenterHolder = this.f5741a.get(str);
        if (presenterHolder == null) {
            return null;
        }
        return presenterHolder.f5742a;
    }

    @Nullable
    public <VS> VS b(@NonNull String str) {
        PresenterHolder presenterHolder = this.f5741a.get(str);
        if (presenterHolder == null) {
            return null;
        }
        return presenterHolder.b;
    }

    public void a(@NonNull String str, @NonNull MvpPresenter<? extends MvpView> mvpPresenter) {
        if (str == null) {
            throw new NullPointerException("ViewId is null");
        } else if (mvpPresenter != null) {
            PresenterHolder presenterHolder = this.f5741a.get(str);
            if (presenterHolder == null) {
                PresenterHolder presenterHolder2 = new PresenterHolder();
                MvpPresenter unused = presenterHolder2.f5742a = mvpPresenter;
                this.f5741a.put(str, presenterHolder2);
                return;
            }
            MvpPresenter unused2 = presenterHolder.f5742a = mvpPresenter;
        } else {
            throw new NullPointerException("Presenter is null");
        }
    }

    public void a(@NonNull String str, @NonNull Object obj) {
        if (str == null) {
            throw new NullPointerException("ViewId is null");
        } else if (obj != null) {
            PresenterHolder presenterHolder = this.f5741a.get(str);
            if (presenterHolder == null) {
                PresenterHolder presenterHolder2 = new PresenterHolder();
                Object unused = presenterHolder2.b = obj;
                this.f5741a.put(str, presenterHolder2);
                return;
            }
            Object unused2 = presenterHolder.b = obj;
        } else {
            throw new NullPointerException("ViewState is null");
        }
    }

    public void c(@NonNull String str) {
        if (str != null) {
            this.f5741a.remove(str);
            return;
        }
        throw new NullPointerException("View Id is null");
    }

    static final class PresenterHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public MvpPresenter<?> f5742a;
        /* access modifiers changed from: private */
        public Object b;

        PresenterHolder() {
        }
    }
}
