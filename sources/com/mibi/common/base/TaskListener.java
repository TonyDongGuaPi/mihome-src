package com.mibi.common.base;

public interface TaskListener<Progress, TaskResult> {
    void onProgressUpdate(Progress progress);

    void onTaskCancelled(TaskResult taskresult);

    void onTaskComplete(TaskResult taskresult);

    void onTaskStart();
}
