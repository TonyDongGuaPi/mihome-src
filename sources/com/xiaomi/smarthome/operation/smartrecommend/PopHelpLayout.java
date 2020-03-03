package com.xiaomi.smarthome.operation.smartrecommend;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import java.util.concurrent.TimeUnit;

public class PopHelpLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21149a = "PopHelpLayout";
    private String b;
    private Disposable c;
    Subject<Integer> mSubject = null;

    public PopHelpLayout(Context context) {
        super(context);
    }

    public PopHelpLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PopHelpLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0 && isShown()) {
            a().onNext(1);
        }
    }

    public void attachAdUrl(String str) {
        this.b = str;
    }

    public void logAdPopUp() {
        a().onNext(1);
    }

    private Subject<Integer> a() {
        if (this.mSubject == null) {
            this.mSubject = PublishSubject.create();
            this.c = this.mSubject.debounce(200, TimeUnit.MILLISECONDS).subscribe(new Consumer() {
                public final void accept(Object obj) {
                    PopHelpLayout.this.a((Integer) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        return this.mSubject;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Integer num) throws Exception {
        if (!TextUtils.isEmpty(this.b)) {
            STAT.e.e(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.dispose();
        }
        if (this.mSubject != null) {
            this.mSubject = null;
        }
    }
}
