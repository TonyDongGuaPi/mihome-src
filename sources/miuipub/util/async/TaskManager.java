package miuipub.util.async;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.util.SoftReferenceSingleton;
import miuipub.util.async.Task;
import miuipub.util.cache.Cache;
import miuipub.util.cache.LruCache;

public class TaskManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f3034a = "async";
    static final boolean b = true;
    static final boolean c = true;
    static final boolean d = false;
    static final boolean e = false;
    static final boolean f = false;
    public static final int g = -1;
    private static final SoftReferenceSingleton<TaskManager> h = new SoftReferenceSingleton<TaskManager>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public TaskManager createInstance() {
            return new TaskManager();
        }
    };
    private static final int i = 10;
    private static final int j = -1;
    private volatile boolean k;
    private final TaskQueue l;
    private Cache<String, Object> m;
    private TaskInfoDeliverer n;
    private ArrayList<TaskThread> o;
    private Object p;

    public TaskManager() {
        this(10, -1, -1);
    }

    public TaskManager(int i2, int i3, int i4) {
        this.p = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    TaskManager.this.d();
                } finally {
                    super.finalize();
                }
            }
        };
        this.k = false;
        this.l = new TaskQueue(this, i2);
        if (i3 < 0 && i3 <= 0) {
            i3 = 4;
        }
        this.o = new ArrayList<>(i3);
        for (int i5 = 0; i5 < i3; i5++) {
            this.o.add(new TaskThread(this, this.l, i5));
            this.o.get(i5).start();
        }
        this.n = new TaskInfoDeliverer(this);
        this.m = new LruCache(i4);
    }

    public static TaskManager a() {
        return h.get();
    }

    public void a(int i2) {
        this.m.a(i2);
    }

    public void b(int i2) {
        if (this.k) {
            Log.e("async", "Cannot add task into a shut down task manager");
            return;
        }
        if (i2 < 0 && i2 <= 0) {
            i2 = 4;
        }
        int size = this.o.size();
        if (i2 > size) {
            while (size < i2) {
                TaskThread taskThread = new TaskThread(this, this.l, size);
                taskThread.start();
                this.o.add(taskThread);
                size++;
            }
        } else if (i2 < size) {
            for (int i3 = size - 1; i3 >= i2; i3--) {
                this.o.get(i3).a();
                this.o.remove(i3);
            }
        }
    }

    public void a(Task<?> task) {
        Object a2;
        if (this.k) {
            Log.e("async", "Cannot add task into a shut down task manager");
        } else if (task.c()) {
            String str = "Task " + task + " has already added into task manager and not finish yet";
            Log.e("async", str);
            throw new IllegalArgumentException(str);
        } else if (task.b() != Task.Status.New && !task.g()) {
            throw new IllegalArgumentException("Status of task " + task + " is not New, and cannot restart.");
        } else if (!task.a(this)) {
            throw new IllegalArgumentException("Task " + task + " has already added into task manager and not finish yet");
        } else if (this.m != null && (task instanceof Cacheable) && (a2 = this.m.a(((Cacheable) task).a())) != null) {
            task.a(Task.Status.Queued, (Object) null);
            task.a(Task.Status.Executing, (Object) null);
            task.a(Task.Status.Done, a2);
        } else if (task.f() == Task.Priority.RealTime) {
            task.a(Task.Status.Queued, (Object) null);
            TaskThread.a(this, task);
        } else {
            this.l.b(task);
        }
    }

    public void b() {
        this.l.e();
    }

    public void c() {
        this.l.f();
    }

    public void d() {
        if (this != a() && !this.k) {
            this.k = true;
            Iterator<TaskThread> it = this.o.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.o.clear();
            this.m.a();
            g();
        }
    }

    private void g() {
        while (!this.l.c()) {
            Task<?> a2 = this.l.g();
            if (a2 != null) {
                a2.a(Task.Status.Canceled, (Object) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.k;
    }

    public void a(boolean z) {
        this.n.a(z);
    }

    /* access modifiers changed from: package-private */
    public Cache<String, Object> f() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean b(Task<?> task) {
        return this.l.a(task);
    }

    /* access modifiers changed from: package-private */
    public void a(Task<?> task, Task.Delivery delivery, Object obj) {
        this.n.a(task, delivery, obj);
    }
}
