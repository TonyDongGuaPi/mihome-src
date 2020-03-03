package com.xiaomi.mishopsdk.widget.special.listview;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.mishopsdk.util.DensityUtil;

public class SwipeMenuView extends LinearLayout implements View.OnClickListener {
    private SwipeMenuLayout mLayout;
    private SwipeMenuListView mListView;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;

    public interface OnSwipeItemClickListener {
        void onItemClick(SwipeMenuView swipeMenuView, SwipeMenu swipeMenu, int i);
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public SwipeMenuView(SwipeMenu swipeMenu, SwipeMenuListView swipeMenuListView) {
        super(swipeMenu.getContext());
        this.mListView = swipeMenuListView;
        this.mMenu = swipeMenu;
        for (SwipeMenuItem next : swipeMenu.getMenuItems()) {
            addItem(next, next.getId());
        }
    }

    private void addItem(SwipeMenuItem swipeMenuItem, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(swipeMenuItem.getWidth(), -1);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setId(i);
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundDrawable(swipeMenuItem.getBackground());
        linearLayout.setOnClickListener(this);
        addView(linearLayout);
        if (swipeMenuItem.getIcon() != null) {
            linearLayout.addView(createIcon(swipeMenuItem), new LinearLayout.LayoutParams(DensityUtil.dip2px(32.0f), DensityUtil.dip2px(32.0f)));
        }
        if (!TextUtils.isEmpty(swipeMenuItem.getTitle())) {
            linearLayout.addView(createTitle(swipeMenuItem));
        }
    }

    private ImageView createIcon(SwipeMenuItem swipeMenuItem) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(swipeMenuItem.getIcon());
        return imageView;
    }

    private TextView createTitle(SwipeMenuItem swipeMenuItem) {
        TextView textView = new TextView(getContext());
        textView.setText(swipeMenuItem.getTitle());
        textView.setGravity(17);
        textView.setTextSize((float) swipeMenuItem.getTitleSize());
        textView.setTextColor(swipeMenuItem.getTitleColor());
        return textView;
    }

    public void onClick(View view) {
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, this.mMenu, view.getId());
        }
    }

    public OnSwipeItemClickListener getOnSwipeItemClickListener() {
        return this.onItemClickListener;
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onSwipeItemClickListener) {
        this.onItemClickListener = onSwipeItemClickListener;
    }

    public void setLayout(SwipeMenuLayout swipeMenuLayout) {
        this.mLayout = swipeMenuLayout;
    }
}
