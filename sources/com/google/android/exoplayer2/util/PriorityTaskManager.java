package com.google.android.exoplayer2.util;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    private int highestPriority = Integer.MIN_VALUE;
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());

    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i, int i2) {
            super("Priority too low [priority=" + i + ", highest=" + i2 + Operators.ARRAY_END_STR);
        }
    }

    public void add(int i) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i));
            this.highestPriority = Math.max(this.highestPriority, i);
        }
    }

    public void proceed(int i) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority != i) {
                this.lock.wait();
            }
        }
    }

    public boolean proceedNonBlocking(int i) {
        boolean z;
        synchronized (this.lock) {
            z = this.highestPriority == i;
        }
        return z;
    }

    public void proceedOrThrow(int i) throws PriorityTooLowException {
        synchronized (this.lock) {
            if (this.highestPriority != i) {
                throw new PriorityTooLowException(i, this.highestPriority);
            }
        }
    }

    public void remove(int i) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : ((Integer) Util.castNonNull(this.queue.peek())).intValue();
            this.lock.notifyAll();
        }
    }
}
