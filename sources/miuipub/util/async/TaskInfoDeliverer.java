package miuipub.util.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.miuipub.internal.util.PackageConstants;
import miuipub.util.Pools;
import miuipub.util.async.Task;
import miuipub.util.concurrent.ConcurrentRingQueue;
import miuipub.util.concurrent.Queue;

class TaskInfoDeliverer {

    /* renamed from: a  reason: collision with root package name */
    private static final Pools.Pool<TaskDeliveryInfo> f3029a = Pools.a(new Pools.Manager<TaskDeliveryInfo>() {
        /* renamed from: a */
        public TaskDeliveryInfo b() {
            return new TaskDeliveryInfo();
        }

        public void a(TaskDeliveryInfo taskDeliveryInfo) {
            taskDeliveryInfo.a();
        }
    }, 8);
    private TaskManager b;
    /* access modifiers changed from: private */
    public DeliverHandler c = new DeliverHandler(PackageConstants.a().getMainLooper());
    private ConcurrentRingQueue<TaskDeliveryInfo> d = new ConcurrentRingQueue<>(20, true, true);

    static class TaskDeliveryInfo {

        /* renamed from: a  reason: collision with root package name */
        public Task<?> f3033a;
        public Task.Delivery b;
        public Object c;

        TaskDeliveryInfo() {
        }

        public void a() {
            this.f3033a = null;
            this.b = null;
            this.c = null;
        }
    }

    private static class DeliverHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        static final int f3032a = 0;

        public DeliverHandler(Looper looper) {
            super(looper);
        }

        public void a(TaskInfoDeliverer taskInfoDeliverer) {
            obtainMessage(0, taskInfoDeliverer).sendToTarget();
        }

        public void handleMessage(Message message) {
            ((TaskInfoDeliverer) message.obj).a();
            message.obj = null;
        }
    }

    public TaskInfoDeliverer(TaskManager taskManager) {
        this.b = taskManager;
    }

    public void a(boolean z) {
        Looper mainLooper = PackageConstants.a().getMainLooper();
        if (z && this.c.getLooper() != mainLooper) {
            this.c.getLooper().quit();
            this.c = new DeliverHandler(mainLooper);
        } else if (!z && this.c.getLooper() == mainLooper) {
            AnonymousClass2 r3 = new Thread() {
                public void run() {
                    Looper.prepare();
                    DeliverHandler unused = TaskInfoDeliverer.this.c = new DeliverHandler(Looper.myLooper());
                    Looper.loop();
                }
            };
            r3.setName("TaskInfoDeliverer-Callback");
            r3.start();
        }
    }

    public void a(final Task<?> task, Task.Delivery delivery, Object obj) {
        TaskDeliveryInfo b2 = f3029a.b();
        b2.f3033a = task;
        b2.b = delivery;
        b2.c = obj;
        if (delivery == Task.Delivery.Result && obj == null) {
            this.d.a(new Queue.Predicate<TaskDeliveryInfo>() {
                public boolean a(TaskDeliveryInfo taskDeliveryInfo) {
                    return taskDeliveryInfo != null && taskDeliveryInfo.f3033a == task;
                }
            });
        }
        this.d.b(b2);
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (Looper.myLooper() == this.c.getLooper()) {
            while (!this.d.c()) {
                TaskDeliveryInfo g = this.d.g();
                if (g != null) {
                    g.f3033a.a(this.b, g.b, g.c);
                    f3029a.b(g);
                }
            }
            return;
        }
        this.c.a(this);
    }
}
