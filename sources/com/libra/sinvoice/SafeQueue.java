package com.libra.sinvoice;

public class SafeQueue implements IQueue {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6250a = "SafeQueue";
    private static final int b = 20;
    private static final int c = 1;
    private static final int d = 2;
    private volatile int e;
    private volatile int f;
    private volatile int g;
    private volatile int h;
    private volatile BufferData[] i;

    public SafeQueue(int i2) {
        if (i2 > 0) {
            this.h = i2;
            this.i = new BufferData[(i2 + 1)];
            this.f = 0;
            this.g = 0;
        }
        this.e = 2;
    }

    public void a(BufferData[] bufferDataArr) {
        this.f = 0;
        if (bufferDataArr != null) {
            for (int i2 = 0; i2 < bufferDataArr.length; i2++) {
                this.i[i2] = bufferDataArr[i2];
            }
            this.g = bufferDataArr.length;
        } else {
            this.g = 0;
        }
        this.e = 1;
    }

    public void a() {
        this.e = 2;
    }

    public BufferData b() {
        while (1 == this.e) {
            if (this.f != this.g) {
                BufferData bufferData = this.i[this.f];
                this.f++;
                if (this.f <= this.h) {
                    return bufferData;
                }
                this.f = 0;
                return bufferData;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public boolean a(BufferData bufferData) {
        while (1 == this.e) {
            int i2 = this.g + 1;
            if (i2 > this.h) {
                i2 = 0;
            }
            if (i2 != this.f) {
                this.i[this.g] = bufferData;
                this.g = i2;
                return true;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
