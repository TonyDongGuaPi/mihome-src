package com.liulishuo.filedownloader.message;

public class MessageSnapshotFlow {

    /* renamed from: a  reason: collision with root package name */
    private volatile MessageSnapshotThreadPool f6435a;
    private volatile MessageReceiver b;

    public static final class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final MessageSnapshotFlow f6436a = new MessageSnapshotFlow();
    }

    public interface MessageReceiver {
        void receive(MessageSnapshot messageSnapshot);
    }

    public static MessageSnapshotFlow a() {
        return HolderClass.f6436a;
    }

    public void a(MessageReceiver messageReceiver) {
        this.b = messageReceiver;
        if (messageReceiver == null) {
            this.f6435a = null;
        } else {
            this.f6435a = new MessageSnapshotThreadPool(5, messageReceiver);
        }
    }

    public void a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof IFlowDirectly) {
            if (this.b != null) {
                this.b.receive(messageSnapshot);
            }
        } else if (this.f6435a != null) {
            this.f6435a.a(messageSnapshot);
        }
    }
}
