package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class SignCalendarView_ViewBinding implements Unbinder {
    private SignCalendarView target;
    private View view2131493747;
    private View view2131493825;

    @UiThread
    public SignCalendarView_ViewBinding(SignCalendarView signCalendarView) {
        this(signCalendarView, signCalendarView);
    }

    @UiThread
    public SignCalendarView_ViewBinding(final SignCalendarView signCalendarView, View view) {
        this.target = signCalendarView;
        View findRequiredView = Utils.findRequiredView(view, R.id.pre_month_iv, "field 'preMonthIv' and method 'onClick'");
        signCalendarView.preMonthIv = (ImageView) Utils.castView(findRequiredView, R.id.pre_month_iv, "field 'preMonthIv'", ImageView.class);
        this.view2131493825 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signCalendarView.onClick(view);
            }
        });
        signCalendarView.currentDateTv = (TextView) Utils.findRequiredViewAsType(view, R.id.current_date_tv, "field 'currentDateTv'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.next_month_iv, "field 'nextMonthIv' and method 'onClick'");
        signCalendarView.nextMonthIv = (ImageView) Utils.castView(findRequiredView2, R.id.next_month_iv, "field 'nextMonthIv'", ImageView.class);
        this.view2131493747 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signCalendarView.onClick(view);
            }
        });
        signCalendarView.monthView = (MonthView) Utils.findRequiredViewAsType(view, R.id.month_view, "field 'monthView'", MonthView.class);
    }

    @CallSuper
    public void unbind() {
        SignCalendarView signCalendarView = this.target;
        if (signCalendarView != null) {
            this.target = null;
            signCalendarView.preMonthIv = null;
            signCalendarView.currentDateTv = null;
            signCalendarView.nextMonthIv = null;
            signCalendarView.monthView = null;
            this.view2131493825.setOnClickListener((View.OnClickListener) null);
            this.view2131493825 = null;
            this.view2131493747.setOnClickListener((View.OnClickListener) null);
            this.view2131493747 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
