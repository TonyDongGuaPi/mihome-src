package com.ximalaya.ting.android.player;

import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.model.JNIDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class HlsAudioFile {

    /* renamed from: a  reason: collision with root package name */
    private String f2272a;
    private String b;
    private List<String> c = new ArrayList();
    private HlsReadThread d;
    private XMediaplayerJNI e;
    private LinkedBlockingQueue<BufferItem> f;
    private volatile boolean g = false;
    private boolean h = false;
    private int i = -1;

    public int a() {
        if (this.d == null || d() == 0) {
            return 0;
        }
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "DownloadThread hls mHlsReadThread.getCacheIndex():" + this.d.b() + "getPlayUrlsLength():" + d());
        int b2 = (int) ((((float) (this.d.b() + -1)) / ((float) d())) * 100.0f);
        if (b2 < 0) {
            return 0;
        }
        return b2;
    }

    public HlsAudioFile(String str, XMediaplayerJNI xMediaplayerJNI) {
        this.f2272a = str;
        this.e = xMediaplayerJNI;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void a(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            this.c.addAll(Arrays.asList(strArr));
            g();
        }
    }

    public List<String> c() {
        return this.c;
    }

    public String a(int i2) {
        if (i2 < this.c.size()) {
            return this.c.get(i2);
        }
        return null;
    }

    public int d() {
        return this.c.size();
    }

    public int e() {
        if (this.b == null) {
            return -1;
        }
        return this.c.indexOf(this.b);
    }

    public long a(JNIDataModel jNIDataModel) {
        boolean z;
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "hls readData callback:" + System.currentTimeMillis());
        this.b = jNIDataModel.filePath;
        if (!this.e.getAudioType().equals(XMediaplayerJNI.AudioType.HLS_FILE)) {
            int e2 = e();
            String str2 = XMediaplayerJNI.Tag;
            Logger.a(str2, (Object) "HlsReadThread notify555 curIndex:" + e2 + "lastIndex:" + this.i);
            z = this.i + 1 != e2;
            this.i = e2;
        } else {
            z = false;
        }
        g();
        if (z) {
            this.f = new LinkedBlockingQueue<>(3);
            this.d.a(this.f);
        }
        BufferItem bufferItem = null;
        try {
            if (this.f.size() > 0 || PlayerUtil.a(this.e.mContext)) {
                this.g = true;
                bufferItem = this.f.poll(30000, TimeUnit.MILLISECONDS);
                this.g = false;
            }
            Logger.a(XMediaplayerJNI.Tag, (Object) "dataStreamInputFuncCallBackT 3");
            if (bufferItem != null) {
                if (!bufferItem.b) {
                    jNIDataModel.buf = bufferItem.a().array();
                    jNIDataModel.fileSize = (long) jNIDataModel.buf.length;
                    return jNIDataModel.fileSize;
                }
            }
            Logger.a(XMediaplayerJNI.Tag, (Object) "dataStreamInputFuncCallBackT timeout item null");
            f();
            return -1;
        } catch (InterruptedException unused) {
            f();
            return -1;
        }
    }

    private void g() {
        if (this.d == null || this.d.d()) {
            this.f = new LinkedBlockingQueue<>(3);
            this.d = new HlsReadThread(this, this.e, this.f2272a, this.f);
            this.h = false;
        }
        if (!this.d.isAlive() && !this.h && e() >= 0) {
            this.h = true;
            this.d.start();
        }
        this.d.a();
    }

    public void f() {
        if (this.d != null) {
            this.d.c();
        }
        if (this.f == null) {
            return;
        }
        if (this.f.size() != 0 || !this.g) {
            this.f.clear();
            return;
        }
        BufferItem bufferItem = new BufferItem();
        bufferItem.b = true;
        bufferItem.c = 500;
        this.f.add(bufferItem);
    }
}
