package com.mibi.common.base;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

class TaskHolder implements TaskManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7473a = "TaskHolder";
    private ArrayList<TaskInfo<?, ?>> b = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean c = true;

    public void c() {
    }

    public void d() {
    }

    TaskHolder() {
    }

    private class TaskInfo<Progress, TaskResult> implements TaskListener<Progress, TaskResult> {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public Task<Progress, TaskResult> c;
        /* access modifiers changed from: private */
        public TaskListener<Progress, TaskResult> d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private TaskResult l;
        private Progress m;

        private TaskInfo() {
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = false;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (this.c != null) {
                if (!this.g && this.d != null) {
                    this.c.a(this);
                    this.g = true;
                }
                if (!z) {
                    if (this.i) {
                        onTaskStart();
                    }
                    if (this.j) {
                        onProgressUpdate(this.m);
                    }
                    if (this.k) {
                        onTaskComplete(this.l);
                    }
                } else if (!this.e) {
                    this.i = false;
                    this.j = false;
                    this.k = false;
                    this.h = false;
                    this.c.h();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.c != null) {
                this.c.l();
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.c != null && this.e) {
                this.c.i();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            if (this.c != null) {
                this.c.l();
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            this.f = true;
            if (this.c != null) {
                if (this.g) {
                    this.g = false;
                    this.c.j();
                }
                if (this.e) {
                    this.c.i();
                }
                this.c.m();
            }
        }

        /* access modifiers changed from: package-private */
        public void e() {
            Log.v(TaskHolder.f7473a, "=========================uiReady,task =" + this.c);
            if (this.h) {
                onTaskComplete(this.l);
                this.h = false;
            }
        }

        public void onTaskStart() {
            this.i = true;
            if (!this.f) {
                this.e = true;
                if (this.d != null) {
                    this.d.onTaskStart();
                }
            }
        }

        public void onProgressUpdate(Progress progress) {
            this.j = true;
            this.m = progress;
            if (!this.f && this.d != null) {
                this.d.onProgressUpdate(progress);
            }
        }

        public void onTaskComplete(TaskResult taskresult) {
            this.k = true;
            this.l = taskresult;
            this.e = false;
            if (!this.f) {
                if (!TaskHolder.this.c) {
                    this.h = true;
                } else if (this.d != null) {
                    this.d.onTaskComplete(taskresult);
                }
            }
        }

        public void onTaskCancelled(TaskResult taskresult) {
            this.e = false;
            if (!this.f && this.d != null) {
                this.d.onTaskCancelled(taskresult);
            }
        }
    }

    public <Progress, TaskResult> int a(Task<Progress, TaskResult> task, TaskListener<Progress, TaskResult> taskListener) {
        if (task != null) {
            int size = this.b.size();
            TaskInfo taskInfo = new TaskInfo();
            int unused = taskInfo.b = size;
            Task unused2 = taskInfo.c = task;
            TaskListener unused3 = taskInfo.d = taskListener;
            this.b.add(taskInfo);
            return size;
        }
        throw new IllegalArgumentException("task cannot be null");
    }

    public <TaskResult> int a(Task<Void, TaskResult> task, final TaskCompleteListener<TaskResult> taskCompleteListener) {
        return a(task, new TaskListener<Void, TaskResult>() {
            /* renamed from: a */
            public void onProgressUpdate(Void voidR) {
            }

            public void onTaskCancelled(TaskResult taskresult) {
            }

            public void onTaskStart() {
            }

            public void onTaskComplete(TaskResult taskresult) {
                if (taskCompleteListener != null) {
                    taskCompleteListener.a(taskresult);
                }
            }
        });
    }

    public void a(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        if (i < this.b.size()) {
            this.b.get(i).a(z);
        }
    }

    public void b(int i) {
        if (i < this.b.size()) {
            this.b.get(i).b();
        }
    }

    public <Progress, TaskResult> int b(Task<Progress, TaskResult> task, TaskListener<Progress, TaskResult> taskListener) {
        int a2 = a(task, taskListener);
        a(a2);
        return a2;
    }

    public <TaskResult> int b(Task<Void, TaskResult> task, TaskCompleteListener<TaskResult> taskCompleteListener) {
        int a2 = a(task, taskCompleteListener);
        a(a2);
        return a2;
    }

    public void a() {
        this.c = false;
    }

    public void b() {
        this.c = true;
        Iterator<TaskInfo<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
    }

    public void e() {
        Iterator<TaskInfo<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void f() {
        Iterator<TaskInfo<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
    }

    public void g() {
        Iterator<TaskInfo<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        this.b.clear();
    }
}
