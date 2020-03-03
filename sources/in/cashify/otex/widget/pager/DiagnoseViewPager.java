package in.cashify.otex.widget.pager;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;
import java.lang.reflect.Field;

public class DiagnoseViewPager extends ViewPager {

    public class a extends Scroller {
        public a(DiagnoseViewPager diagnoseViewPager, Context context) {
            super(context);
        }

        public void startScroll(int i, int i2, int i3, int i4) {
            super.startScroll(i, i2, i3, i4, 500);
        }

        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            super.startScroll(i, i2, i3, i4, 500);
        }
    }

    public DiagnoseViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (Build.VERSION.SDK_INT >= 19) {
            a();
        }
    }

    public final void a() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, new a(this, getContext()));
        } catch (Exception unused) {
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
