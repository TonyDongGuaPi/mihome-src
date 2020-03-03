package com.mics.widget.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import com.mics.widget.RecyclerViewScrollCompat;

public class ChatUtils {
    public static void a(EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() <= 0 || charSequence.toString().trim().length() <= 0) {
                    view.setVisibility(4);
                } else {
                    view.setVisibility(0);
                }
            }
        });
    }

    public static void a(RecyclerView recyclerView) {
        boolean c = c(recyclerView);
        boolean isComputingLayout = recyclerView.isComputingLayout();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!isComputingLayout && (layoutManager instanceof LinearLayoutManager)) {
            recyclerView.scrollToPosition(layoutManager.getItemCount() - 1);
            ((LinearLayoutManager) layoutManager).setStackFromEnd(c);
        }
    }

    public static boolean b(RecyclerView recyclerView) {
        if (!(recyclerView instanceof RecyclerViewScrollCompat)) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if ((linearLayoutManager.getItemCount() - 1) - linearLayoutManager.findLastVisibleItemPosition() <= 3) {
                return true;
            }
            return false;
        } else if (((RecyclerViewScrollCompat) recyclerView).getScrollOffset() < 0.5f) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean c(RecyclerView recyclerView) {
        View findViewByPosition;
        View findViewByPosition2;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (findLastVisibleItemPosition < 0 || (findViewByPosition = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition)) == null) {
            return false;
        }
        int height = recyclerView.getHeight();
        int bottom = findViewByPosition.getBottom();
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, recyclerView.getResources().getDisplayMetrics());
        int i = bottom - (height - applyDimension);
        if (i > 0) {
            return true;
        }
        if (i >= 0 && (findViewByPosition2 = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition())) != null && findViewByPosition2.getTop() - applyDimension <= 0) {
            return true;
        }
        return false;
    }
}
