package miuipub.util.async;

class TaskThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final TaskManager f3038a;
    private final TaskQueue b;
    private volatile boolean c = false;

    public TaskThread(TaskManager taskManager, TaskQueue taskQueue, int i) {
        this.f3038a = taskManager;
        this.b = taskQueue;
        setName("TaskThread-" + i);
    }

    public static void a(final TaskManager taskManager, final Task<?> task) {
        AnonymousClass1 r0 = new Thread() {
            public void run() {
                TaskThread.b(taskManager, this, task);
            }
        };
        r0.setName("TaskThread-RealTime");
        r0.start();
    }

    public void a() {
        this.c = true;
        interrupt();
    }

    public void run() {
        TaskQueue taskQueue = this.b;
        while (!this.c) {
            Task<?> a2 = taskQueue.g();
            if (a2 != null) {
                b(this.f3038a, this, a2);
                setPriority(5);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void b(miuipub.util.async.TaskManager r4, java.lang.Thread r5, miuipub.util.async.Task<?> r6) {
        /*
            if (r4 == 0) goto L_0x0053
            if (r6 == 0) goto L_0x0053
            miuipub.util.async.Task$Status r0 = miuipub.util.async.Task.Status.Executing
            r1 = 0
            r6.a((miuipub.util.async.Task.Status) r0, (java.lang.Object) r1)
            r6.a((java.lang.Thread) r5)
            java.lang.Object r5 = r6.h()     // Catch:{ Exception -> 0x0024 }
            miuipub.util.async.Task$Status r0 = miuipub.util.async.Task.Status.Done     // Catch:{ Exception -> 0x0022 }
            if (r5 != 0) goto L_0x001d
            java.lang.NullPointerException r2 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0022 }
            java.lang.String r3 = "result is null"
            r2.<init>(r3)     // Catch:{ Exception -> 0x0022 }
            goto L_0x001e
        L_0x001d:
            r2 = r5
        L_0x001e:
            r6.a((miuipub.util.async.Task.Status) r0, (java.lang.Object) r2)     // Catch:{ Exception -> 0x0022 }
            goto L_0x0030
        L_0x0022:
            r0 = move-exception
            goto L_0x0026
        L_0x0024:
            r0 = move-exception
            r5 = r1
        L_0x0026:
            miuipub.util.async.Task$Status r2 = miuipub.util.async.Task.Status.Done
            miuipub.util.async.TaskExecutingException r3 = new miuipub.util.async.TaskExecutingException
            r3.<init>(r0)
            r6.a((miuipub.util.async.Task.Status) r2, (java.lang.Object) r3)
        L_0x0030:
            miuipub.util.cache.Cache r4 = r4.f()
            if (r4 == 0) goto L_0x0050
            if (r5 == 0) goto L_0x0050
            boolean r0 = r6 instanceof miuipub.util.async.Cacheable
            if (r0 == 0) goto L_0x0050
            r0 = r6
            miuipub.util.async.Cacheable r0 = (miuipub.util.async.Cacheable) r0
            java.lang.String r2 = r0.a()
            if (r2 == 0) goto L_0x0050
            java.lang.String r2 = r0.a()
            int r0 = r0.a(r5)
            r4.a(r2, r5, r0)
        L_0x0050:
            r6.a((java.lang.Thread) r1)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.util.async.TaskThread.b(miuipub.util.async.TaskManager, java.lang.Thread, miuipub.util.async.Task):void");
    }
}
