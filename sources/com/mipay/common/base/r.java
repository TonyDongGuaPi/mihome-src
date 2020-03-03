package com.mipay.common.base;

public interface r<Progress, TaskResult> {
    void onProgressUpdate(Progress progress);

    void onTaskCancelled(TaskResult taskresult);

    void onTaskComplete(TaskResult taskresult);

    void onTaskStart();
}
