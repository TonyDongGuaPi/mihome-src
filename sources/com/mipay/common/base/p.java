package com.mipay.common.base;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

class p implements s {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8121a = "TaskHolder";
    private ArrayList<a<?, ?>> b = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean c = true;

    private class a<Progress, TaskResult> implements r<Progress, TaskResult> {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public m<Progress, TaskResult> c;
        /* access modifiers changed from: private */
        public r<Progress, TaskResult> d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private TaskResult l;
        private Progress m;

        private a() {
            this.h = false;
            this.i = false;
            this.j = false;
            this.k = false;
        }

        /* synthetic */ a(p pVar, q qVar) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.c != null) {
                this.c.f();
            }
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
                    this.c.b();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.c != null && this.e) {
                this.c.c();
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            if (this.c != null) {
                this.c.f();
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            this.f = true;
            if (this.c != null) {
                if (this.g) {
                    this.g = false;
                    this.c.d();
                }
                if (this.e) {
                    this.c.c();
                }
                this.c.g();
            }
        }

        /* access modifiers changed from: package-private */
        public void e() {
            Log.v(p.f8121a, "=========================uiReady,task =" + this.c);
            if (this.h) {
                onTaskComplete(this.l);
                this.h = false;
            }
        }

        public void onProgressUpdate(Progress progress) {
            this.j = true;
            this.m = progress;
            if (!this.f && this.d != null) {
                this.d.onProgressUpdate(progress);
            }
        }

        public void onTaskCancelled(TaskResult taskresult) {
            this.e = false;
            if (!this.f && this.d != null) {
                this.d.onTaskCancelled(taskresult);
            }
        }

        public void onTaskComplete(TaskResult taskresult) {
            this.k = true;
            this.l = taskresult;
            this.e = false;
            if (!this.f) {
                if (!p.this.c) {
                    this.h = true;
                } else if (this.d != null) {
                    this.d.onTaskComplete(taskresult);
                }
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
    }

    p() {
    }

    public <TaskResult> int a(m<Void, TaskResult> mVar, o<TaskResult> oVar) {
        return a(mVar, new q(this, oVar));
    }

    public <Progress, TaskResult> int a(m<Progress, TaskResult> mVar, r<Progress, TaskResult> rVar) {
        if (mVar != null) {
            int size = this.b.size();
            a aVar = new a(this, (q) null);
            int unused = aVar.b = size;
            m unused2 = aVar.c = mVar;
            r unused3 = aVar.d = rVar;
            this.b.add(aVar);
            return size;
        }
        throw new IllegalArgumentException("task cannot be null");
    }

    public void a() {
        this.c = false;
    }

    public void a(int i) {
        a(i, true);
    }

    public void a(int i, boolean z) {
        if (i < this.b.size()) {
            this.b.get(i).a(z);
        }
    }

    public <TaskResult> int b(m<Void, TaskResult> mVar, o<TaskResult> oVar) {
        int a2 = a(mVar, oVar);
        a(a2);
        return a2;
    }

    public <Progress, TaskResult> int b(m<Progress, TaskResult> mVar, r<Progress, TaskResult> rVar) {
        int a2 = a(mVar, rVar);
        a(a2);
        return a2;
    }

    public void b() {
        this.c = true;
        Iterator<a<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().e();
        }
    }

    public void b(int i) {
        if (i < this.b.size()) {
            this.b.get(i).b();
        }
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
        Iterator<a<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public void f() {
        Iterator<a<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().c();
        }
    }

    public void g() {
        Iterator<a<?, ?>> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        this.b.clear();
    }
}
