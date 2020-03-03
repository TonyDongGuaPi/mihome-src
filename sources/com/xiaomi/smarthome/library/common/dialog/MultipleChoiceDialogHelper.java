package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class MultipleChoiceDialogHelper {
    private MultipleChoiceDialogHelper() {
    }

    public static void a(Context context, MLAlertDialog mLAlertDialog) {
        Window window;
        ViewGroup viewGroup;
        ViewParent parent;
        if (context != null && mLAlertDialog != null && (window = mLAlertDialog.getWindow()) != null && (viewGroup = (ViewGroup) window.findViewById(R.id.parentPanel)) != null && (parent = viewGroup.getParent()) != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup2 = (ViewGroup) parent;
            viewGroup.setBackground(new ColorDrawable(-1));
            viewGroup2.removeView(viewGroup);
            CardView cardView = new CardView(context);
            cardView.setRadius((float) DisplayUtils.a(18.0f));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            int a2 = DisplayUtils.a(8.0f);
            layoutParams.bottomMargin = a2;
            layoutParams.rightMargin = a2;
            layoutParams.topMargin = a2;
            layoutParams.leftMargin = a2;
            cardView.addView(viewGroup, new ViewGroup.MarginLayoutParams(-1, -1));
            viewGroup2.addView(cardView, layoutParams);
        }
    }
}
