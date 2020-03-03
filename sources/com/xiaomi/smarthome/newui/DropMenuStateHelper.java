package com.xiaomi.smarthome.newui;

import android.text.TextUtils;
import android.util.Log;
import com.drew.lang.annotations.NotNull;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;

public class DropMenuStateHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20257a = "DropMenuStateHelper";
    private static DropMenuStateHelper b;
    private PageBean c;
    private BehaviorSubject<PageBean> d = BehaviorSubject.create();
    private final List<PageBean.Classify> e = new ArrayList();
    private final List<PageBean> f = new ArrayList();

    private DropMenuStateHelper() {
    }

    public static DropMenuStateHelper a() {
        if (b == null) {
            b = new DropMenuStateHelper();
            LogUtil.a(f20257a, "getInstance: init");
        }
        return b;
    }

    @NotNull
    public PageBean b() {
        if (this.c == null) {
            this.c = PageBean.a();
        }
        return this.c;
    }

    public Observable<PageBean> c() {
        return this.d;
    }

    public List<PageBean> d() {
        return this.f;
    }

    public List<PageBean.Classify> e() {
        return this.e;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            while (i < this.f.size()) {
                PageBean pageBean = this.f.get(i);
                if (pageBean == null || !TextUtils.equals(pageBean.f, str)) {
                    i++;
                } else {
                    this.c = pageBean;
                    this.d.onNext(pageBean);
                    Log.d(f20257a, "selectRoom: pageBean: " + pageBean.f + " ; " + pageBean.e);
                    return;
                }
            }
        }
    }

    public void a(List<PageBean.Classify> list) {
        if (!this.e.equals(list)) {
            this.e.clear();
            this.f.clear();
            this.e.addAll(list);
            for (PageBean.Classify classify : this.e) {
                List<PageBean> list2 = classify.b;
                if (list2 != null && list2.size() > 0) {
                    this.f.addAll(list2);
                }
            }
        }
    }

    public void f() {
        this.c = null;
        this.f.clear();
        this.e.clear();
    }
}
