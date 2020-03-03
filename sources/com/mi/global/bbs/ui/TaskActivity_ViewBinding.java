package com.mi.global.bbs.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.TaskListView;

public class TaskActivity_ViewBinding implements Unbinder {
    private TaskActivity target;

    @UiThread
    public TaskActivity_ViewBinding(TaskActivity taskActivity) {
        this(taskActivity, taskActivity.getWindow().getDecorView());
    }

    @UiThread
    public TaskActivity_ViewBinding(TaskActivity taskActivity, View view) {
        this.target = taskActivity;
        taskActivity.missionRabbit = (ImageView) Utils.findRequiredViewAsType(view, R.id.mission_rabbit, "field 'missionRabbit'", ImageView.class);
        taskActivity.tvMissionTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.mission_title, "field 'tvMissionTitle'", TextView.class);
        taskActivity.mRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.outer_refresh_layout, "field 'mRefreshLayout'", SwipeRefreshLayout.class);
        taskActivity.taskListLayout = (TaskListView) Utils.findRequiredViewAsType(view, R.id.daily_task_list, "field 'taskListLayout'", TaskListView.class);
        taskActivity.newbieListLayout = (TaskListView) Utils.findRequiredViewAsType(view, R.id.newbie_task_list, "field 'newbieListLayout'", TaskListView.class);
        taskActivity.newBieMissionCompleteLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.newbie_mission_complete_layout, "field 'newBieMissionCompleteLayout'", LinearLayout.class);
        taskActivity.newBieMissionTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.new_bie_mission_title, "field 'newBieMissionTitle'", TextView.class);
        taskActivity.mTaskScroll = (ScrollView) Utils.findRequiredViewAsType(view, R.id.task_scroll, "field 'mTaskScroll'", ScrollView.class);
    }

    @CallSuper
    public void unbind() {
        TaskActivity taskActivity = this.target;
        if (taskActivity != null) {
            this.target = null;
            taskActivity.missionRabbit = null;
            taskActivity.tvMissionTitle = null;
            taskActivity.mRefreshLayout = null;
            taskActivity.taskListLayout = null;
            taskActivity.newbieListLayout = null;
            taskActivity.newBieMissionCompleteLayout = null;
            taskActivity.newBieMissionTitle = null;
            taskActivity.mTaskScroll = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
