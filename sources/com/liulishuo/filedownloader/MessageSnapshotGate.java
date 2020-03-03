package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.List;

public class MessageSnapshotGate implements MessageSnapshotFlow.MessageReceiver {
    private boolean a(List<BaseDownloadTask.IRunningTask> list, MessageSnapshot messageSnapshot) {
        if (list.size() > 1 && messageSnapshot.b() == -3) {
            for (BaseDownloadTask.IRunningTask Q : list) {
                if (Q.Q().c(messageSnapshot)) {
                    return true;
                }
            }
        }
        for (BaseDownloadTask.IRunningTask Q2 : list) {
            if (Q2.Q().b(messageSnapshot)) {
                return true;
            }
        }
        if (-4 == messageSnapshot.b()) {
            for (BaseDownloadTask.IRunningTask Q3 : list) {
                if (Q3.Q().d(messageSnapshot)) {
                    return true;
                }
            }
        }
        if (list.size() == 1) {
            return list.get(0).Q().a(messageSnapshot);
        }
        return false;
    }

    public void receive(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.c()).intern()) {
            List<BaseDownloadTask.IRunningTask> c = FileDownloadList.a().c(messageSnapshot.c());
            if (c.size() > 0) {
                BaseDownloadTask P = c.get(0).P();
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(this, "~~~callback %s old[%s] new[%s] %d", Integer.valueOf(messageSnapshot.c()), Byte.valueOf(P.B()), Byte.valueOf(messageSnapshot.b()), Integer.valueOf(c.size()));
                }
                if (!a(c, messageSnapshot)) {
                    StringBuilder sb = new StringBuilder("The event isn't consumed, id:" + messageSnapshot.c() + " status:" + messageSnapshot.b() + " task-count:" + c.size());
                    for (BaseDownloadTask.IRunningTask P2 : c) {
                        sb.append(" | ");
                        sb.append(P2.P().B());
                    }
                    FileDownloadLog.b(this, sb.toString(), new Object[0]);
                }
            } else {
                FileDownloadLog.b(this, "Receive the event %d, but there isn't any running task in the upper layer", Byte.valueOf(messageSnapshot.b()));
            }
        }
    }
}
