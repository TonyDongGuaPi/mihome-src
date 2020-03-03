package org.cybergarage.util;

public class ThreadCore implements Runnable {
    private Thread mThreadObject = null;

    public void run() {
    }

    public void setThreadObject(Thread thread) {
        this.mThreadObject = thread;
    }

    public Thread getThreadObject() {
        return this.mThreadObject;
    }

    public void start() {
        if (getThreadObject() == null) {
            Thread thread = new Thread(this, "Cyber.ThreadCore");
            setThreadObject(thread);
            thread.start();
        }
    }

    public boolean isRunnable() {
        return Thread.currentThread() == getThreadObject();
    }

    public void stop() {
        Thread threadObject = getThreadObject();
        if (threadObject != null) {
            threadObject.interrupt();
            setThreadObject((Thread) null);
        }
    }

    public void restart() {
        stop();
        start();
    }
}
