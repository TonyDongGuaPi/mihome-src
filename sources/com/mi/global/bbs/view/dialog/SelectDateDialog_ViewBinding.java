package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontTextView;
import com.weigan.loopview.LoopView;

public class SelectDateDialog_ViewBinding implements Unbinder {
    private SelectDateDialog target;
    private View view2131493045;
    private View view2131493758;

    @UiThread
    public SelectDateDialog_ViewBinding(SelectDateDialog selectDateDialog) {
        this(selectDateDialog, selectDateDialog.getWindow().getDecorView());
    }

    @UiThread
    public SelectDateDialog_ViewBinding(final SelectDateDialog selectDateDialog, View view) {
        this.target = selectDateDialog;
        selectDateDialog.mTitle = (FontTextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'mTitle'", FontTextView.class);
        selectDateDialog.mMonthView = (LoopView) Utils.findRequiredViewAsType(view, R.id.month_view, "field 'mMonthView'", LoopView.class);
        selectDateDialog.mDayView = (LoopView) Utils.findRequiredViewAsType(view, R.id.day_view, "field 'mDayView'", LoopView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cancel, "method 'onViewClicked'");
        this.view2131493045 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                selectDateDialog.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ok, "method 'onViewClicked'");
        this.view2131493758 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                selectDateDialog.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SelectDateDialog selectDateDialog = this.target;
        if (selectDateDialog != null) {
            this.target = null;
            selectDateDialog.mTitle = null;
            selectDateDialog.mMonthView = null;
            selectDateDialog.mDayView = null;
            this.view2131493045.setOnClickListener((View.OnClickListener) null);
            this.view2131493045 = null;
            this.view2131493758.setOnClickListener((View.OnClickListener) null);
            this.view2131493758 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
