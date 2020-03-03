package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.ITaskHunter;
import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import junit.framework.Assert;

class FileDownloadMessenger implements IFileDownloadMessenger {

    /* renamed from: a  reason: collision with root package name */
    private BaseDownloadTask.IRunningTask f6395a;
    private BaseDownloadTask.LifeCycleCallback b;
    private Queue<MessageSnapshot> c;
    private boolean d = false;

    FileDownloadMessenger(BaseDownloadTask.IRunningTask iRunningTask, BaseDownloadTask.LifeCycleCallback lifeCycleCallback) {
        b(iRunningTask, lifeCycleCallback);
    }

    private void b(BaseDownloadTask.IRunningTask iRunningTask, BaseDownloadTask.LifeCycleCallback lifeCycleCallback) {
        this.f6395a = iRunningTask;
        this.b = lifeCycleCallback;
        this.c = new LinkedBlockingQueue();
    }

    public boolean a() {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify begin %s", this.f6395a);
        }
        if (this.f6395a == null) {
            FileDownloadLog.d(this, "can't begin the task, the holder fo the messenger is nil, %d", Integer.valueOf(this.c.size()));
            return false;
        }
        this.b.a();
        return true;
    }

    public void a(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify pending %s", this.f6395a);
        }
        this.b.f_();
        k(messageSnapshot);
    }

    public void b(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify started %s", this.f6395a);
        }
        this.b.f_();
        k(messageSnapshot);
    }

    public void c(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify connected %s", this.f6395a);
        }
        this.b.f_();
        k(messageSnapshot);
    }

    public void d(MessageSnapshot messageSnapshot) {
        BaseDownloadTask P = this.f6395a.P();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify progress %s %d %d", P, Long.valueOf(P.w()), Long.valueOf(P.z()));
        }
        if (P.n() > 0) {
            this.b.f_();
            k(messageSnapshot);
        } else if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify progress but client not request notify %s", this.f6395a);
        }
    }

    public void e(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify block completed %s %s", this.f6395a, Thread.currentThread().getName());
        }
        this.b.f_();
        k(messageSnapshot);
    }

    public void f(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            BaseDownloadTask P = this.f6395a.P();
            FileDownloadLog.c(this, "notify retry %s %d %d %s", this.f6395a, Integer.valueOf(P.K()), Integer.valueOf(P.L()), P.E());
        }
        this.b.f_();
        k(messageSnapshot);
    }

    public void g(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify warn %s", this.f6395a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    public void h(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify error %s %s", this.f6395a, this.f6395a.P().E());
        }
        this.b.c();
        k(messageSnapshot);
    }

    public void i(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify paused %s", this.f6395a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    public void j(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "notify completed %s", this.f6395a);
        }
        this.b.c();
        k(messageSnapshot);
    }

    private void k(MessageSnapshot messageSnapshot) {
        if (this.f6395a == null) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(this, "occur this case, it would be the host task of this messenger has been over(paused/warn/completed/error) on the other thread before receiving the snapshot(id[%d], status[%d])", Integer.valueOf(messageSnapshot.c()), Byte.valueOf(messageSnapshot.b()));
            }
        } else if (this.d || this.f6395a.P().t() == null) {
            if ((FileDownloadMonitor.c() || this.f6395a.aa()) && messageSnapshot.b() == 4) {
                this.b.c();
            }
            a((int) messageSnapshot.b());
        } else {
            this.c.offer(messageSnapshot);
            FileDownloadMessageStation.a().a((IFileDownloadMessenger) this);
        }
    }

    private void a(int i) {
        if (FileDownloadStatus.a(i)) {
            if (!this.c.isEmpty()) {
                MessageSnapshot peek = this.c.peek();
                FileDownloadLog.d(this, "the messenger[%s](with id[%d]) has already accomplished all his job, but there still are some messages in parcel queue[%d] queue-top-status[%d]", this, Integer.valueOf(peek.c()), Integer.valueOf(this.c.size()), Byte.valueOf(peek.b()));
            }
            this.f6395a = null;
        }
    }

    public void b() {
        if (!this.d) {
            MessageSnapshot poll = this.c.poll();
            byte b2 = poll.b();
            BaseDownloadTask.IRunningTask iRunningTask = this.f6395a;
            boolean z = false;
            String a2 = FileDownloadUtils.a("can't handover the message, no master to receive this message(status[%d]) size[%d]", Integer.valueOf(b2), Integer.valueOf(this.c.size()));
            if (iRunningTask != null) {
                z = true;
            }
            Assert.assertTrue(a2, z);
            BaseDownloadTask P = iRunningTask.P();
            FileDownloadListener t = P.t();
            ITaskHunter.IMessageHandler Q = iRunningTask.Q();
            a((int) b2);
            if (t != null && !t.isInvalid()) {
                if (b2 == 4) {
                    try {
                        t.blockComplete(P);
                        j(((BlockCompleteMessage) poll).a());
                    } catch (Throwable th) {
                        h(Q.a(th));
                    }
                } else {
                    FileDownloadLargeFileListener fileDownloadLargeFileListener = null;
                    if (t instanceof FileDownloadLargeFileListener) {
                        fileDownloadLargeFileListener = (FileDownloadLargeFileListener) t;
                    }
                    switch (b2) {
                        case -4:
                            t.warn(P);
                            return;
                        case -3:
                            t.completed(P);
                            return;
                        case -2:
                            if (fileDownloadLargeFileListener != null) {
                                fileDownloadLargeFileListener.c(P, poll.h(), poll.i());
                                return;
                            }
                            t.paused(P, poll.j(), poll.k());
                            return;
                        case -1:
                            t.error(P, poll.d());
                            return;
                        case 1:
                            if (fileDownloadLargeFileListener != null) {
                                fileDownloadLargeFileListener.a(P, poll.h(), poll.i());
                                return;
                            }
                            t.pending(P, poll.j(), poll.k());
                            return;
                        case 2:
                            if (fileDownloadLargeFileListener != null) {
                                fileDownloadLargeFileListener.a(P, poll.g(), poll.f(), P.w(), poll.i());
                                return;
                            }
                            t.connected(P, poll.g(), poll.f(), P.v(), poll.k());
                            return;
                        case 3:
                            if (fileDownloadLargeFileListener != null) {
                                fileDownloadLargeFileListener.b(P, poll.h(), P.z());
                                return;
                            }
                            t.progress(P, poll.j(), P.y());
                            return;
                        case 5:
                            if (fileDownloadLargeFileListener != null) {
                                fileDownloadLargeFileListener.a(P, poll.d(), poll.e(), poll.h());
                                return;
                            }
                            t.retry(P, poll.d(), poll.e(), poll.j());
                            return;
                        case 6:
                            t.started(P);
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    public boolean c() {
        return this.f6395a.P().M();
    }

    public void a(BaseDownloadTask.IRunningTask iRunningTask, BaseDownloadTask.LifeCycleCallback lifeCycleCallback) {
        if (this.f6395a == null) {
            b(iRunningTask, lifeCycleCallback);
        } else {
            throw new IllegalStateException(FileDownloadUtils.a("the messenger is working, can't re-appointment for %s", iRunningTask));
        }
    }

    public boolean d() {
        return this.c.peek().b() == 4;
    }

    public void e() {
        this.d = true;
    }

    public String toString() {
        int i;
        Object[] objArr = new Object[2];
        if (this.f6395a == null) {
            i = -1;
        } else {
            i = this.f6395a.P().k();
        }
        objArr[0] = Integer.valueOf(i);
        objArr[1] = super.toString();
        return FileDownloadUtils.a("%d:%s", objArr);
    }
}
