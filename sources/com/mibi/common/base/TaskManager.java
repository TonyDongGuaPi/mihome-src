package com.mibi.common.base;

public interface TaskManager {
    <TaskResult> int a(Task<Void, TaskResult> task, TaskCompleteListener<TaskResult> taskCompleteListener);

    <Progress, TaskResult> int a(Task<Progress, TaskResult> task, TaskListener<Progress, TaskResult> taskListener);

    void a(int i);

    void a(int i, boolean z);

    <TaskResult> int b(Task<Void, TaskResult> task, TaskCompleteListener<TaskResult> taskCompleteListener);

    <Progress, TaskResult> int b(Task<Progress, TaskResult> task, TaskListener<Progress, TaskResult> taskListener);

    void b(int i);
}
