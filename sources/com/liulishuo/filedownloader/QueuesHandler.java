package com.liulishuo.filedownloader;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.SparseArray;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;
import java.util.List;

class QueuesHandler implements IQueuesHandler {

    /* renamed from: a  reason: collision with root package name */
    static final int f6409a = 1;
    static final int b = 2;
    static final int c = 3;
    /* access modifiers changed from: private */
    public final SparseArray<Handler> d = new SparseArray<>();

    public boolean a(FileDownloadListener fileDownloadListener) {
        int hashCode = fileDownloadListener.hashCode();
        List<BaseDownloadTask.IRunningTask> a2 = FileDownloadList.a().a(hashCode, fileDownloadListener);
        if (a(hashCode, a2, fileDownloadListener, false)) {
            return false;
        }
        for (BaseDownloadTask.IRunningTask X : a2) {
            X.X();
        }
        return true;
    }

    public boolean b(FileDownloadListener fileDownloadListener) {
        SerialHandlerCallback serialHandlerCallback = new SerialHandlerCallback();
        int hashCode = serialHandlerCallback.hashCode();
        List<BaseDownloadTask.IRunningTask> a2 = FileDownloadList.a().a(hashCode, fileDownloadListener);
        if (a(hashCode, a2, fileDownloadListener, true)) {
            return false;
        }
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.a("filedownloader serial thread %s-%d", fileDownloadListener, Integer.valueOf(hashCode)));
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), serialHandlerCallback);
        serialHandlerCallback.a(handler);
        serialHandlerCallback.a(a2);
        serialHandlerCallback.a(0);
        synchronized (this.d) {
            this.d.put(hashCode, handler);
        }
        return true;
    }

    public void a() {
        for (int i = 0; i < this.d.size(); i++) {
            a(this.d.get(this.d.keyAt(i)));
        }
    }

    public void a(List<Integer> list) {
        for (Integer intValue : list) {
            b(this.d.get(intValue.intValue()));
        }
    }

    public int b() {
        return this.d.size();
    }

    public boolean a(int i) {
        return this.d.get(i) != null;
    }

    private boolean a(int i, List<BaseDownloadTask.IRunningTask> list, FileDownloadListener fileDownloadListener, boolean z) {
        if (FileDownloadMonitor.c()) {
            FileDownloadMonitor.b().a(list.size(), true, fileDownloadListener);
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.e(FileDownloader.class, "start list attachKey[%d] size[%d] listener[%s] isSerial[%B]", Integer.valueOf(i), Integer.valueOf(list.size()), fileDownloadListener, Boolean.valueOf(z));
        }
        if (list != null && !list.isEmpty()) {
            return false;
        }
        FileDownloadLog.d(FileDownloader.class, "Tasks with the listener can't start, because can't find any task with the provided listener, maybe tasks instance has been started in the past, so they are all are inUsing, if in this case, you can use [BaseDownloadTask#reuse] to reuse theme first then start again: [%s, %B]", fileDownloadListener, Boolean.valueOf(z));
        return true;
    }

    private class SerialHandlerCallback implements Handler.Callback {
        private Handler b;
        private List<BaseDownloadTask.IRunningTask> c;
        private int d = 0;
        private SerialFinishListener e = new SerialFinishListener(new WeakReference(this));

        SerialHandlerCallback() {
        }

        public void a(Handler handler) {
            this.b = handler;
        }

        public void a(List<BaseDownloadTask.IRunningTask> list) {
            this.c = list;
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                if (message.arg1 >= this.c.size()) {
                    synchronized (QueuesHandler.this.d) {
                        QueuesHandler.this.d.remove(this.c.get(0).S());
                    }
                    FileDownloadListener fileDownloadListener = null;
                    if (!(this.b == null || this.b.getLooper() == null)) {
                        this.b.getLooper().quit();
                        this.b = null;
                        this.c = null;
                        this.e = null;
                    }
                    if (FileDownloadLog.f6465a) {
                        Class<SerialHandlerCallback> cls = SerialHandlerCallback.class;
                        Object[] objArr = new Object[2];
                        if (!(this.c == null || this.c.get(0) == null)) {
                            fileDownloadListener = this.c.get(0).P().t();
                        }
                        objArr[0] = fileDownloadListener;
                        objArr[1] = Integer.valueOf(message.arg1);
                        FileDownloadLog.c(cls, "final serial %s %d", objArr);
                    }
                    return true;
                }
                this.d = message.arg1;
                BaseDownloadTask.IRunningTask iRunningTask = this.c.get(this.d);
                synchronized (iRunningTask.Z()) {
                    if (iRunningTask.P().B() == 0) {
                        if (!FileDownloadList.a().a(iRunningTask)) {
                            iRunningTask.P().b(this.e.a(this.d + 1));
                            iRunningTask.X();
                        }
                    }
                    if (FileDownloadLog.f6465a) {
                        FileDownloadLog.c(SerialHandlerCallback.class, "direct go next by not contains %s %d", iRunningTask, Integer.valueOf(message.arg1));
                    }
                    a(message.arg1 + 1);
                    return true;
                }
            } else if (message.what == 2) {
                a();
            } else if (message.what == 3) {
                b();
            }
            return true;
        }

        public void a() {
            this.c.get(this.d).P().c((BaseDownloadTask.FinishListener) this.e);
            this.b.removeCallbacksAndMessages((Object) null);
        }

        public void b() {
            a(this.d);
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            if (this.b == null || this.c == null) {
                FileDownloadLog.d(this, "need go next %d, but params is not ready %s %s", Integer.valueOf(i), this.b, this.c);
                return;
            }
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.arg1 = i;
            if (FileDownloadLog.f6465a) {
                Class<SerialHandlerCallback> cls = SerialHandlerCallback.class;
                Object[] objArr = new Object[2];
                FileDownloadListener fileDownloadListener = null;
                if (!(this.c == null || this.c.get(0) == null)) {
                    fileDownloadListener = this.c.get(0).P().t();
                }
                objArr[0] = fileDownloadListener;
                objArr[1] = Integer.valueOf(obtainMessage.arg1);
                FileDownloadLog.c(cls, "start next %s %s", objArr);
            }
            this.b.sendMessage(obtainMessage);
        }
    }

    private static class SerialFinishListener implements BaseDownloadTask.FinishListener {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<SerialHandlerCallback> f6410a;
        private int b;

        private SerialFinishListener(WeakReference<SerialHandlerCallback> weakReference) {
            this.f6410a = weakReference;
        }

        public BaseDownloadTask.FinishListener a(int i) {
            this.b = i;
            return this;
        }

        public void a(BaseDownloadTask baseDownloadTask) {
            if (this.f6410a != null && this.f6410a.get() != null) {
                ((SerialHandlerCallback) this.f6410a.get()).a(this.b);
            }
        }
    }

    private void a(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void b(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
