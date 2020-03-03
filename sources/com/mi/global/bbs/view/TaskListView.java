package com.mi.global.bbs.view;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.TaskItemViewModel;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.view.TaskItemView;
import java.util.ArrayList;
import java.util.List;

public class TaskListView extends LinearLayout {
    private LinearLayout.LayoutParams divideParam;
    private LinearLayout.LayoutParams itemParam;
    private List<TaskItemView> mTaskItemViews;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    private int padding;

    public interface OnItemClickListener {
        void onItemClick(TaskListView taskListView, int i, TaskItemView.TaskItemState taskItemState);
    }

    public TaskListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TaskListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TaskListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTaskItemViews = new ArrayList();
        setOrientation(1);
        this.padding = getResources().getDimensionPixelSize(R.dimen.common_padding);
        this.divideParam = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.divide_height));
        this.itemParam = new LinearLayout.LayoutParams(-1, -2);
    }

    public void addItemStringAndIconArray(String[] strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            addTaskItem(strArr[i], this.itemParam, i);
            if (i != length - 1) {
                addDivide(this.divideParam);
            }
        }
    }

    private void addTaskItem(String str, LinearLayout.LayoutParams layoutParams, final int i) {
        TaskItemView taskItemView = new TaskItemView(getContext());
        taskItemView.setItemTitle(str);
        taskItemView.setOnStateChangeListener(new TaskItemView.OnStateChangeListener() {
            public void onStateChange(TaskItemView taskItemView, TaskItemView.TaskItemState taskItemState) {
                if (TaskListView.this.onItemClickListener != null) {
                    TaskListView.this.onItemClickListener.onItemClick(TaskListView.this, i, taskItemState);
                }
            }
        });
        this.mTaskItemViews.add(taskItemView);
        addView(taskItemView, layoutParams);
    }

    private void addDivide(LinearLayout.LayoutParams layoutParams) {
        View view = new View(getContext());
        layoutParams.height = 27;
        view.setBackgroundColor(getResources().getColor(17170445));
        addView(view, layoutParams);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void setTaskData(List<TaskItemViewModel.TaskItemViewBean> list) {
        if (list != null) {
            int size = list.size();
            int size2 = this.mTaskItemViews.size();
            int i = 0;
            while (i < size && i < size2) {
                TaskItemView taskItemView = this.mTaskItemViews.get(i);
                TaskItemViewModel.TaskItemViewBean taskItemViewBean = list.get(i);
                if (taskItemViewBean.getTaskid() != 4) {
                    taskItemView.setItemSubTitle(Html.fromHtml("<font color=\"#999999\"> " + getQuantityString(R.plurals.add_points, taskItemViewBean.getPoint()) + "</font>"));
                } else {
                    taskItemView.setItemSubTitle(Html.fromHtml("<font color=\"#999999\"> " + getQuantityString(R.plurals._medals, taskItemViewBean.getPoint()) + "</font>"));
                }
                taskItemView.setTag(Integer.valueOf(taskItemViewBean.getTaskid()));
                taskItemView.setTaskUrl(ConnectionHelper.getAppIndexUrl() + taskItemViewBean.getDirecturl());
                int taskstatus = taskItemViewBean.getTaskstatus();
                TaskItemView.TaskItemState taskItemState = taskstatus == 0 ? TaskItemView.TaskItemState.UNFINISHED : taskstatus == 1 ? TaskItemView.TaskItemState.AWARD : TaskItemView.TaskItemState.COMPLETED;
                if (taskItemViewBean.getTaskid() == 3) {
                    taskItemView.setTaskItemState(taskItemState, taskItemViewBean.getProcess(), 5);
                } else if (taskItemViewBean.getTaskid() == 4) {
                    taskItemView.setTaskItemState(taskItemState, taskItemViewBean.getProcess(), 3);
                } else {
                    taskItemView.setTaskItemState(taskItemState, taskItemViewBean.getProcess(), 1);
                }
                i++;
            }
        }
    }

    public String getQuantityString(int i, int i2) {
        if (getContext() == null) {
            return "";
        }
        return getContext().getResources().getQuantityString(i, i2, new Object[]{Integer.valueOf(i2)});
    }

    public TaskItemView getTaskItemView(int i) {
        return this.mTaskItemViews.get(i);
    }

    public String getTaskUrl(int i) {
        return this.mTaskItemViews.get(i).getTaskUrl();
    }
}
