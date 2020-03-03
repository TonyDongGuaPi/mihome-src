package com.mics.widget;

import android.os.Bundle;
import android.view.View;
import com.mics.widget.CategoryPop;
import java.util.List;

public class CategoryPopAdapter extends CategoryPop.Adapter {

    /* renamed from: a  reason: collision with root package name */
    private List<Object> f7803a;
    private CategoryPop.Adapter.OnItemClickListener b;

    public CategoryPopAdapter(List<Object> list) {
        this.f7803a = list;
    }

    /* access modifiers changed from: protected */
    public int a() {
        if (this.f7803a == null) {
            return 0;
        }
        return this.f7803a.size();
    }

    /* access modifiers changed from: protected */
    public String a(int i) {
        Bundle c = c(i);
        if (c == null) {
            return null;
        }
        return c.getString("icon");
    }

    /* access modifiers changed from: protected */
    public String b(int i) {
        Bundle c = c(i);
        if (c == null) {
            return null;
        }
        return c.getString("name");
    }

    private Bundle c(int i) {
        if (this.f7803a == null || i >= this.f7803a.size() || i < 0) {
            return null;
        }
        Object obj = this.f7803a.get(i);
        if (obj instanceof Bundle) {
            return (Bundle) obj;
        }
        throw new RuntimeException("List<E> E只能是Bundle类型。实际类型为：" + obj.getClass());
    }

    /* access modifiers changed from: protected */
    public void a(int i, View view) {
        if (this.b != null) {
            this.b.a(i, view);
        }
    }

    public void a(CategoryPop.Adapter.OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
    }
}
