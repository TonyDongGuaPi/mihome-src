package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class MessageSnapshotThreadPool {

    /* renamed from: a  reason: collision with root package name */
    private final List<FlowSingleExecutor> f6437a = new ArrayList();
    /* access modifiers changed from: private */
    public final MessageSnapshotFlow.MessageReceiver b;

    MessageSnapshotThreadPool(int i, MessageSnapshotFlow.MessageReceiver messageReceiver) {
        this.b = messageReceiver;
        for (int i2 = 0; i2 < i; i2++) {
            this.f6437a.add(new FlowSingleExecutor(i2));
        }
    }

    public void a(MessageSnapshot messageSnapshot) {
        FlowSingleExecutor flowSingleExecutor = null;
        try {
            synchronized (this.f6437a) {
                int c = messageSnapshot.c();
                Iterator<FlowSingleExecutor> it = this.f6437a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FlowSingleExecutor next = it.next();
                    if (next.b.contains(Integer.valueOf(c))) {
                        flowSingleExecutor = next;
                        break;
                    }
                }
                if (flowSingleExecutor == null) {
                    int i = 0;
                    Iterator<FlowSingleExecutor> it2 = this.f6437a.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        FlowSingleExecutor next2 = it2.next();
                        if (next2.b.size() <= 0) {
                            flowSingleExecutor = next2;
                            break;
                        } else if (i == 0 || next2.b.size() < i) {
                            i = next2.b.size();
                            flowSingleExecutor = next2;
                        }
                    }
                }
                flowSingleExecutor.a(c);
            }
            flowSingleExecutor.a(messageSnapshot);
        } catch (Throwable th) {
            flowSingleExecutor.a(messageSnapshot);
            throw th;
        }
    }

    public class FlowSingleExecutor {
        /* access modifiers changed from: private */
        public final List<Integer> b = new ArrayList();
        private final Executor c;

        public FlowSingleExecutor(int i) {
            this.c = FileDownloadExecutors.a(1, "Flow-" + i);
        }

        public void a(int i) {
            this.b.add(Integer.valueOf(i));
        }

        public void a(final MessageSnapshot messageSnapshot) {
            this.c.execute(new Runnable() {
                public void run() {
                    MessageSnapshotThreadPool.this.b.receive(messageSnapshot);
                    FlowSingleExecutor.this.b.remove(Integer.valueOf(messageSnapshot.c()));
                }
            });
        }
    }
}
