package com.mi.global.shop.adapter;

import android.content.Context;
import android.view.View;

public abstract class BasePageIndicatorAdapter<T> extends BasePageAdapter<T> {
    public static final int c = 500;

    public BasePageIndicatorAdapter(Context context) {
        super(context);
    }

    public int getCount() {
        if (this.f5485a.size() == 1) {
            return 1;
        }
        return this.f5485a.size() == 0 ? 0 : 500;
    }

    public T a(int i) {
        if (i < 0 || i >= a()) {
            return this.f5485a.get(i % a());
        }
        return this.f5485a.get(i);
    }

    public int a() {
        if (this.f5485a != null) {
            return this.f5485a.size();
        }
        return 0;
    }

    public int getItemPosition(Object obj) {
        if (a() > 0) {
            return this.f5485a.contains(((View) obj).getTag()) ? -1 : -2;
        }
        return super.getItemPosition(obj);
    }
}
