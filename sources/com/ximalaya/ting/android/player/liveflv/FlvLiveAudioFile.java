package com.ximalaya.ting.android.player.liveflv;

import com.ximalaya.ting.android.player.BufferItem;
import com.ximalaya.ting.android.player.Logger;
import com.ximalaya.ting.android.player.PlayerUtil;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.model.JNIDataModel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FlvLiveAudioFile {

    /* renamed from: a  reason: collision with root package name */
    private FlvLiveReadThread f2302a;
    private XMediaplayerJNI b;
    private int c = 800;
    private LinkedBlockingQueue<BufferItem> d;
    private volatile boolean e = false;
    private volatile boolean f = true;
    private int g = 100;
    private Object h = new Object();
    private boolean i = false;

    public FlvLiveAudioFile(XMediaplayerJNI xMediaplayerJNI) {
        this.b = xMediaplayerJNI;
        this.d = new LinkedBlockingQueue<>(1024);
    }

    public int a(JNIDataModel jNIDataModel) {
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "FlvLiveAudioFile readData start time:" + System.currentTimeMillis());
        b();
        BufferItem bufferItem = null;
        try {
            if (this.d.size() > 0 || PlayerUtil.a(this.b.mContext)) {
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT wait");
                this.e = true;
                bufferItem = this.d.poll(20000, TimeUnit.MILLISECONDS);
                this.e = false;
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT wait end");
            }
            if (bufferItem == null || bufferItem.c != this.c) {
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv dataStreamInputFuncCallBackT 3");
                if (bufferItem != null) {
                    if (!bufferItem.b) {
                        if (bufferItem.b()) {
                            a();
                            return 0;
                        }
                        jNIDataModel.buf = bufferItem.a().array();
                        jNIDataModel.fileSize = (long) jNIDataModel.buf.length;
                        jNIDataModel.bufSize = jNIDataModel.buf.length;
                        String str2 = XMediaplayerJNI.Tag;
                        Logger.a(str2, (Object) "flv buf fileSize:" + jNIDataModel.fileSize);
                        return jNIDataModel.buf.length;
                    }
                }
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv dataStreamInputFuncCallBackT timeout item null");
                a();
                return -1;
            }
            Logger.a(XMediaplayerJNI.Tag, (Object) "flv dataStreamInputFuncCallBackT releae last data");
            a();
            return -2;
        } catch (InterruptedException unused) {
            a();
            return -1;
        }
    }

    private void b() {
        if (this.f2302a == null || this.f2302a.c()) {
            this.d = new LinkedBlockingQueue<>(1024);
            this.f2302a = new FlvLiveReadThread(this.b, this.b.getPlayUrl(), this.d);
            this.i = false;
            this.f = true;
        }
        if (!this.f2302a.isAlive() && !this.i) {
            this.i = true;
            this.f2302a.start();
        }
    }

    public void a() {
        Logger.a(XMediaplayerJNI.Tag, (Object) "flv FlvLiveFile relase readDataT");
        this.i = false;
        if (this.f2302a != null) {
            this.f2302a.b();
        }
        if (this.d != null) {
            int size = this.d.size();
            String str = XMediaplayerJNI.Tag;
            Logger.a(str, (Object) "flv readDataT relase isPollData:" + this.e + " size:" + size);
            if (size != 0 || !this.e) {
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT relase clear item start");
                this.d.clear();
                Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT relase clear item end");
                return;
            }
            Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT relase put last buf item start");
            BufferItem bufferItem = new BufferItem();
            bufferItem.b = true;
            bufferItem.c = this.c;
            this.d.add(bufferItem);
            Logger.a(XMediaplayerJNI.Tag, (Object) "flv readDataT relase put last buf item end");
        }
    }
}
