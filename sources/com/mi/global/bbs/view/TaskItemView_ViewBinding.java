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

public class TaskItemView_ViewBinding implements Unbinder {
    private TaskItemView target;
    private View view2131494054;

    @UiThread
    public TaskItemView_ViewBinding(TaskItemView taskItemView) {
        this(taskItemView, taskItemView);
    }

    @UiThread
    public TaskItemView_ViewBinding(final TaskItemView taskItemView, View view) {
        this.target = taskItemView;
        taskItemView.taskItemTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.task_item_title, "field 'taskItemTitle'", TextView.class);
        taskItemView.taskItemSubTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.task_item_subTitle, "field 'taskItemSubTitle'", TextView.class);
        taskItemView.taskItemProgressIcon = (ProgressView) Utils.findRequiredViewAsType(view, R.id.task_item_progress_icon, "field 'taskItemProgressIcon'", ProgressView.class);
        taskItemView.taskItemProgressText = (TextView) Utils.findRequiredViewAsType(view, R.id.task_item_progress_text, "field 'taskItemProgressText'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.task_item_button, "field 'taskItemButton' and method 'onClick'");
        taskItemView.taskItemButton = (TextView) Utils.castView(findRequiredView, R.id.task_item_button, "field 'taskItemButton'", TextView.class);
        this.view2131494054 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                taskItemView.onClick();
            }
        });
        taskItemView.taskItemStateIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.task_item_state_icon, "field 'taskItemStateIcon'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        TaskItemView taskItemView = this.target;
        if (taskItemView != null) {
            this.target = null;
            taskItemView.taskItemTitle = null;
            taskItemView.taskItemSubTitle = null;
            taskItemView.taskItemProgressIcon = null;
            taskItemView.taskItemProgressText = null;
            taskItemView.taskItemButton = null;
            taskItemView.taskItemStateIcon = null;
            this.view2131494054.setOnClickListener((View.OnClickListener) null);
            this.view2131494054 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
