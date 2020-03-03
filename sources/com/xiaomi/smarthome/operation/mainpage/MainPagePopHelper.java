package com.xiaomi.smarthome.operation.mainpage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.TabPageIndicatorNew;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.concurrent.TimeUnit;

public class MainPagePopHelper extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21133a = "CustomCardView";
    private String b;
    private View.OnLayoutChangeListener c;
    private TabPageIndicatorNew d;
    private Disposable e;
    int lastSelected = -1;
    Subject<Integer> mSubject = null;

    public MainPagePopHelper(Context context) {
        super(context);
    }

    public MainPagePopHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MainPagePopHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            this.d = (TabPageIndicatorNew) ((Activity) getContext()).findViewById(R.id.indicator);
            this.c = new View.OnLayoutChangeListener() {
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    MainPagePopHelper.this.a(view, i, i2, i3, i4, i5, i6, i7, i8);
                }
            };
            this.d.addOnLayoutChangeListener(this.c);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int selectedTabIndex = this.d.getSelectedTabIndex();
        if (this.lastSelected != -1 && selectedTabIndex != this.lastSelected && selectedTabIndex == 0 && isShown()) {
            a();
        }
        this.lastSelected = selectedTabIndex;
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0 && isShown()) {
            a();
        }
    }

    public void attachAdUrl(String str) {
        this.b = str;
    }

    private void a() {
        b().onNext(1);
    }

    private Subject<Integer> b() {
        if (this.mSubject == null) {
            this.mSubject = PublishSubject.create();
            this.e = this.mSubject.debounce(200, TimeUnit.MILLISECONDS).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    MainPagePopHelper.this.a((Integer) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        return this.mSubject;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Integer num) throws Exception {
        if (!TextUtils.isEmpty(this.b)) {
            STAT.e.d(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.d == null || this.c == null)) {
            this.d.removeOnLayoutChangeListener(this.c);
        }
        if (this.e != null) {
            this.e.dispose();
        }
        if (this.mSubject != null) {
            this.mSubject = null;
        }
    }
}
