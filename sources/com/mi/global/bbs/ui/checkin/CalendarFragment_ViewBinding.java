package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.SignCalendarView;

public class CalendarFragment_ViewBinding implements Unbinder {
    private CalendarFragment target;

    @UiThread
    public CalendarFragment_ViewBinding(CalendarFragment calendarFragment, View view) {
        this.target = calendarFragment;
        calendarFragment.mSignedCalendarView = (SignCalendarView) Utils.findRequiredViewAsType(view, R.id.signed_calendar_view, "field 'mSignedCalendarView'", SignCalendarView.class);
    }

    @CallSuper
    public void unbind() {
        CalendarFragment calendarFragment = this.target;
        if (calendarFragment != null) {
            this.target = null;
            calendarFragment.mSignedCalendarView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
