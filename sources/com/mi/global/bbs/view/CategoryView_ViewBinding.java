package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class CategoryView_ViewBinding implements Unbinder {
    private CategoryView target;

    @UiThread
    public CategoryView_ViewBinding(CategoryView categoryView) {
        this(categoryView, categoryView);
    }

    @UiThread
    public CategoryView_ViewBinding(CategoryView categoryView, View view) {
        this.target = categoryView;
        categoryView.mCategoryTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.mCategoryTextView, "field 'mCategoryTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CategoryView categoryView = this.target;
        if (categoryView != null) {
            this.target = null;
            categoryView.mCategoryTextView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
