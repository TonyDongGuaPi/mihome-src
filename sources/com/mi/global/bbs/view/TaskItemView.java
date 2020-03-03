package com.mi.global.bbs.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.ProgressView;

public class TaskItemView extends FrameLayout implements ProgressView.OnAnimationEndListener {
    private static final int COMPLETE_DEGREE_DONE = 1;
    private static final int COMPLETE_DEGREE_NONE = 0;
    public static final int TSAK_PRECESS_ALL_DEFUALT = 1;
    public static final int TSAK_PRECESS_ALL_FINISH_COUNT = 3;
    public static final int TSAK_PRECESS_ALL_FOLLOW_USER_COUNT = 5;
    public static final int TSAK_PRECESS_DEFUALT = 0;
    private OnStateChangeListener mOnStateChangeListener;
    private TaskItemState mTaskItemState;
    private String mTaskUrl;
    @BindView(2131494054)
    TextView taskItemButton;
    @BindView(2131494055)
    ProgressView taskItemProgressIcon;
    @BindView(2131494056)
    TextView taskItemProgressText;
    @BindView(2131494057)
    ImageView taskItemStateIcon;
    @BindView(2131494058)
    TextView taskItemSubTitle;
    @BindView(2131494059)
    TextView taskItemTitle;

    public interface OnStateChangeListener {
        void onStateChange(TaskItemView taskItemView, TaskItemState taskItemState);
    }

    public enum TaskItemState {
        UNFINISHED,
        AWARD,
        COMPLETED
    }

    public String getTaskUrl() {
        return this.mTaskUrl;
    }

    public void setTaskUrl(String str) {
        this.mTaskUrl = str;
    }

    public TaskItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TaskItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaskItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTaskItemState = TaskItemState.UNFINISHED;
        LayoutInflater.from(context).inflate(R.layout.bbs_task_item, this, true);
        ButterKnife.bind((View) this);
        this.taskItemProgressIcon.setOnAnimationEndListener(this);
        showByState(this.mTaskItemState, 0, 1);
    }

    @OnClick({2131494054})
    public void onClick() {
        dispatchState(this.mTaskItemState);
    }

    private void dispatchState(TaskItemState taskItemState) {
        if (this.mOnStateChangeListener != null) {
            this.mOnStateChangeListener.onStateChange(this, taskItemState);
        }
    }

    public TaskItemView setItemTitle(CharSequence charSequence) {
        this.taskItemTitle.setText(charSequence);
        return this;
    }

    public TaskItemView setItemSubTitle(CharSequence charSequence) {
        this.taskItemSubTitle.setText(charSequence);
        return this;
    }

    public void setTaskItemState(TaskItemState taskItemState, int i, int i2) {
        this.mTaskItemState = taskItemState;
        showByState(taskItemState, i, i2);
    }

    private void showByState(TaskItemState taskItemState, int i, int i2) {
        if (taskItemState == TaskItemState.UNFINISHED) {
            TextView textView = this.taskItemProgressText;
            textView.setText(i + "/" + i2);
            this.taskItemButton.setBackgroundResource(R.drawable.complete_bt_selector);
            this.taskItemButton.setText(getResources().getString(R.string.do_it_now));
            this.taskItemButton.setVisibility(0);
            this.taskItemProgressIcon.setCompleted(((float) i) / ((float) i2), i, i2);
            this.taskItemStateIcon.setVisibility(8);
            return;
        }
        if (taskItemState == TaskItemState.AWARD) {
            this.taskItemButton.setBackgroundResource(R.drawable.award_bt_selector);
            this.taskItemButton.setText(getResources().getString(R.string.claim_reward));
            this.taskItemButton.setVisibility(0);
            this.taskItemStateIcon.setVisibility(8);
        } else {
            this.taskItemButton.setVisibility(8);
            this.taskItemStateIcon.setVisibility(0);
        }
        this.taskItemProgressIcon.setCompleted(1.0f, i, i2);
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.mOnStateChangeListener = onStateChangeListener;
    }

    public void onCompleteAnimationEnd(int i, int i2) {
        TextView textView = this.taskItemProgressText;
        textView.setText(i + "/" + i2);
    }
}
