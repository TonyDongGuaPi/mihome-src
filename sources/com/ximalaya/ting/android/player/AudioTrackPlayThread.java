package com.ximalaya.ting.android.player;

import android.annotation.TargetApi;
import android.media.AudioTrack;
import android.os.Build;
import com.ximalaya.ting.android.player.XMediaplayerJNI;
import com.ximalaya.ting.android.player.soundtouch.SoundTouch;
import com.ximalaya.ting.android.player.soundtouch.SoundTouchDataModel;

public class AudioTrackPlayThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2268a = 2;
    public static final int b = 12;
    public static final int c = 3;
    public static final int d = 44100;
    public static final int e = 2;
    public static final int f = 2;
    public static final int g = 1;
    public static final int h = 1;
    public static final int i = 4;
    private static int l = 8192;
    private static int m = 16384;
    private static int n = -2;
    private float A;
    private volatile boolean j = false;
    private XMediaplayerJNI k;
    private volatile AudioTrack o;
    private int p;
    private byte[] q;
    private boolean r = false;
    private Object s = new Object();
    private volatile boolean t = false;
    private long u = 0;
    private SoundTouch v = null;
    private int w = 0;
    private boolean x = false;
    private byte[] y;
    private volatile boolean z = false;

    public AudioTrackPlayThread(XMediaplayerJNI xMediaplayerJNI) {
        super("AudioTrackPlayThreadForPlayer");
        this.k = xMediaplayerJNI;
        l();
    }

    private void l() {
        Logger.a(XMediaplayerJNI.Tag, (Object) "initAudioTrack");
        int minBufferSize = AudioTrack.getMinBufferSize(44100, 12, 2);
        this.p = minBufferSize * 4;
        if (minBufferSize < l) {
            this.p = l;
        } else if (minBufferSize > m) {
            this.p = m;
        } else {
            this.p = minBufferSize;
        }
        this.o = new AudioTrack(3, 44100, 12, 2, this.p, 1);
        this.q = new byte[this.p];
    }

    public void a(float f2) {
        a(f2, 0.0f, 1.0f);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(float r2, float r3, float r4) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.j     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x003a
            boolean r0 = com.ximalaya.ting.android.player.soundtouch.SoundTouch.f     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x000a
            goto L_0x003a
        L_0x000a:
            com.ximalaya.ting.android.player.soundtouch.SoundTouch r0 = r1.v     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x0014
            com.ximalaya.ting.android.player.soundtouch.SoundTouch r0 = com.ximalaya.ting.android.player.soundtouch.SoundTouch.a()     // Catch:{ all -> 0x003c }
            r1.v = r0     // Catch:{ all -> 0x003c }
        L_0x0014:
            r0 = 1
            r1.x = r0     // Catch:{ all -> 0x003c }
            com.ximalaya.ting.android.player.soundtouch.SoundTouch r0 = r1.v     // Catch:{ all -> 0x003c }
            r0.a((float) r2)     // Catch:{ all -> 0x003c }
            com.ximalaya.ting.android.player.soundtouch.SoundTouch r0 = r1.v     // Catch:{ all -> 0x003c }
            r0.b(r3)     // Catch:{ all -> 0x003c }
            com.ximalaya.ting.android.player.soundtouch.SoundTouch r0 = r1.v     // Catch:{ all -> 0x003c }
            r0.c(r4)     // Catch:{ all -> 0x003c }
            r0 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x0038
            r2 = 0
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0038
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x0038
            r2 = 0
            r1.x = r2     // Catch:{ all -> 0x003c }
        L_0x0038:
            monitor-exit(r1)
            return
        L_0x003a:
            monitor-exit(r1)
            return
        L_0x003c:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.a(float, float, float):void");
    }

    public int a() {
        return this.p;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        r3 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            r0 = 1
            r14.r = r0
        L_0x0003:
            boolean r1 = r14.j
            r2 = 0
            if (r1 != 0) goto L_0x01b5
            java.lang.Object r1 = r14.s     // Catch:{ InterruptedException -> 0x01af }
            monitor-enter(r1)     // Catch:{ InterruptedException -> 0x01af }
            boolean r3 = r14.j     // Catch:{ all -> 0x01ac }
            if (r3 == 0) goto L_0x0012
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            goto L_0x01b5
        L_0x0012:
            byte[] r3 = r14.y     // Catch:{ all -> 0x01ac }
            if (r3 == 0) goto L_0x0027
            byte[] r3 = r14.y     // Catch:{ all -> 0x01ac }
            byte[] r4 = r14.q     // Catch:{ all -> 0x01ac }
            byte[] r5 = r14.y     // Catch:{ all -> 0x01ac }
            int r5 = r5.length     // Catch:{ all -> 0x01ac }
            java.lang.System.arraycopy(r3, r2, r4, r2, r5)     // Catch:{ all -> 0x01ac }
            byte[] r3 = r14.y     // Catch:{ all -> 0x01ac }
            int r3 = r3.length     // Catch:{ all -> 0x01ac }
            r4 = 0
            r14.y = r4     // Catch:{ all -> 0x01ac }
            goto L_0x0032
        L_0x0027:
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            byte[] r4 = r14.q     // Catch:{ all -> 0x01ac }
            byte[] r5 = r14.q     // Catch:{ all -> 0x01ac }
            int r5 = r5.length     // Catch:{ all -> 0x01ac }
            int r3 = r3.getOutputData(r4, r5)     // Catch:{ all -> 0x01ac }
        L_0x0032:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ac }
            r5.<init>()     // Catch:{ all -> 0x01ac }
            java.lang.String r6 = "AudioTrackPlayThread result0:"
            r5.append(r6)     // Catch:{ all -> 0x01ac }
            r5.append(r3)     // Catch:{ all -> 0x01ac }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x01ac }
            r4 = 701(0x2bd, float:9.82E-43)
            r5 = 4
            r6 = 30000(0x7530, double:1.4822E-319)
            r8 = -1
            if (r3 <= 0) goto L_0x00d6
        L_0x0050:
            int r9 = r14.p     // Catch:{ all -> 0x01ac }
            if (r3 >= r9) goto L_0x00d6
            boolean r9 = r14.j     // Catch:{ all -> 0x01ac }
            if (r9 != 0) goto L_0x00d6
            int r9 = r14.p     // Catch:{ all -> 0x01ac }
            int r9 = r9 - r3
            byte[] r9 = new byte[r9]     // Catch:{ all -> 0x01ac }
            boolean r10 = r14.j     // Catch:{ all -> 0x01ac }
            if (r10 == 0) goto L_0x0063
            goto L_0x00d6
        L_0x0063:
            com.ximalaya.ting.android.player.XMediaplayerJNI r10 = r14.k     // Catch:{ all -> 0x01ac }
            int r11 = r9.length     // Catch:{ all -> 0x01ac }
            int r10 = r10.getOutputData(r9, r11)     // Catch:{ all -> 0x01ac }
            java.lang.String r11 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ac }
            r12.<init>()     // Catch:{ all -> 0x01ac }
            java.lang.String r13 = "AudioTrackPlayThread result1 reLength:"
            r12.append(r13)     // Catch:{ all -> 0x01ac }
            r12.append(r10)     // Catch:{ all -> 0x01ac }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r11, (java.lang.Object) r12)     // Catch:{ all -> 0x01ac }
            int r11 = n     // Catch:{ all -> 0x01ac }
            if (r10 != r11) goto L_0x0087
            int r3 = n     // Catch:{ all -> 0x01ac }
            goto L_0x00d6
        L_0x0087:
            if (r10 != r8) goto L_0x008b
        L_0x0089:
            r3 = -1
            goto L_0x00d6
        L_0x008b:
            if (r10 <= 0) goto L_0x00aa
            byte[] r11 = r14.q     // Catch:{ all -> 0x01ac }
            java.lang.System.arraycopy(r9, r2, r11, r3, r10)     // Catch:{ all -> 0x01ac }
            int r3 = r3 + r10
            java.lang.String r9 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ac }
            r11.<init>()     // Catch:{ all -> 0x01ac }
            java.lang.String r12 = "AudioTrackPlayThread result2:"
            r11.append(r12)     // Catch:{ all -> 0x01ac }
            r11.append(r10)     // Catch:{ all -> 0x01ac }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x01ac }
            goto L_0x0050
        L_0x00aa:
            if (r10 != 0) goto L_0x0089
            r14.t = r0     // Catch:{ all -> 0x01ac }
            java.lang.String r9 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.String r10 = "AudioTrackPlayThread mAudioTrack wait0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r9 = r14.k     // Catch:{ all -> 0x01ac }
            r9.outputDataAppointment()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r9 = r14.k     // Catch:{ all -> 0x01ac }
            boolean r9 = r9.isBuffing     // Catch:{ all -> 0x01ac }
            if (r9 != 0) goto L_0x00cd
            com.ximalaya.ting.android.player.XMediaplayerJNI r9 = r14.k     // Catch:{ all -> 0x01ac }
            int r9 = r9.a()     // Catch:{ all -> 0x01ac }
            if (r9 != r5) goto L_0x00cd
            com.ximalaya.ting.android.player.XMediaplayerJNI r9 = r14.k     // Catch:{ all -> 0x01ac }
            r9.onInfoInner(r4)     // Catch:{ all -> 0x01ac }
        L_0x00cd:
            java.lang.Object r9 = r14.s     // Catch:{ all -> 0x01ac }
            r9.wait(r6)     // Catch:{ all -> 0x01ac }
            r14.t = r2     // Catch:{ all -> 0x01ac }
            goto L_0x0050
        L_0x00d6:
            boolean r9 = r14.j     // Catch:{ all -> 0x01ac }
            if (r9 == 0) goto L_0x00dd
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            goto L_0x01b5
        L_0x00dd:
            java.lang.String r9 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ac }
            r10.<init>()     // Catch:{ all -> 0x01ac }
            java.lang.String r11 = "AudioTrackPlayThread result3:"
            r10.append(r11)     // Catch:{ all -> 0x01ac }
            r10.append(r3)     // Catch:{ all -> 0x01ac }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x01ac }
            int r9 = n     // Catch:{ all -> 0x01ac }
            r10 = 702(0x2be, float:9.84E-43)
            if (r3 != r9) goto L_0x011b
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.String r4 = "AudioTrackPlayThread decode over"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            boolean r3 = r3.isBuffing     // Catch:{ all -> 0x01ac }
            if (r3 == 0) goto L_0x010b
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            r3.onInfoInner(r10)     // Catch:{ all -> 0x01ac }
        L_0x010b:
            r14.m()     // Catch:{ all -> 0x01ac }
            android.media.AudioTrack r3 = r14.o     // Catch:{ all -> 0x01ac }
            r3.stop()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            r3.onCompletionInner()     // Catch:{ all -> 0x01ac }
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            goto L_0x01b5
        L_0x011b:
            if (r3 != r8) goto L_0x011e
            goto L_0x0190
        L_0x011e:
            if (r3 != 0) goto L_0x014a
            r14.t = r0     // Catch:{ all -> 0x01ac }
            java.lang.String r8 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.String r9 = "AudioTrackPlayThread wait"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r8, (java.lang.Object) r9)     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r14.k     // Catch:{ all -> 0x01ac }
            r8.outputDataAppointment()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r14.k     // Catch:{ all -> 0x01ac }
            boolean r8 = r8.isBuffing     // Catch:{ all -> 0x01ac }
            if (r8 != 0) goto L_0x0141
            com.ximalaya.ting.android.player.XMediaplayerJNI r8 = r14.k     // Catch:{ all -> 0x01ac }
            int r8 = r8.a()     // Catch:{ all -> 0x01ac }
            if (r8 != r5) goto L_0x0141
            com.ximalaya.ting.android.player.XMediaplayerJNI r5 = r14.k     // Catch:{ all -> 0x01ac }
            r5.onInfoInner(r4)     // Catch:{ all -> 0x01ac }
        L_0x0141:
            java.lang.Object r4 = r14.s     // Catch:{ all -> 0x01ac }
            r4.wait(r6)     // Catch:{ all -> 0x01ac }
            r14.t = r2     // Catch:{ all -> 0x01ac }
            r8 = r3
            goto L_0x0190
        L_0x014a:
            if (r3 <= 0) goto L_0x0190
            com.ximalaya.ting.android.player.XMediaplayerJNI r4 = r14.k     // Catch:{ all -> 0x01ac }
            boolean r4 = r4.isBuffing     // Catch:{ all -> 0x01ac }
            if (r4 == 0) goto L_0x0157
            com.ximalaya.ting.android.player.XMediaplayerJNI r4 = r14.k     // Catch:{ all -> 0x01ac }
            r4.onInfoInner(r10)     // Catch:{ all -> 0x01ac }
        L_0x0157:
            byte[] r4 = r14.q     // Catch:{ all -> 0x01ac }
            int r8 = r14.a((byte[]) r4, (int) r3)     // Catch:{ all -> 0x01ac }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01ac }
            long r9 = r14.u     // Catch:{ all -> 0x01ac }
            r5 = 0
            long r3 = r3 - r9
            r9 = 1000(0x3e8, double:4.94E-321)
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 < 0) goto L_0x0190
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01ac }
            r14.u = r3     // Catch:{ all -> 0x01ac }
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x01ac }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ac }
            r4.<init>()     // Catch:{ all -> 0x01ac }
            java.lang.String r5 = "AudioTrackPlayThread ttseek3:"
            r4.append(r5)     // Catch:{ all -> 0x01ac }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01ac }
            r4.append(r9)     // Catch:{ all -> 0x01ac }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x01ac }
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            r3.mOnTimedChangeListenerInner()     // Catch:{ all -> 0x01ac }
        L_0x0190:
            if (r8 >= 0) goto L_0x019b
            com.ximalaya.ting.android.player.XMediaplayerJNI r3 = r14.k     // Catch:{ all -> 0x01ac }
            r4 = 8
            r3.onErrorInner(r4, r0)     // Catch:{ all -> 0x01ac }
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            goto L_0x01b5
        L_0x019b:
            boolean r3 = r14.z     // Catch:{ all -> 0x01ac }
            if (r3 != 0) goto L_0x01a9
            r14.t = r0     // Catch:{ all -> 0x01ac }
            java.lang.Object r3 = r14.s     // Catch:{ all -> 0x01ac }
            r3.wait(r6)     // Catch:{ all -> 0x01ac }
            r14.t = r2     // Catch:{ all -> 0x01ac }
            goto L_0x019b
        L_0x01a9:
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            goto L_0x0003
        L_0x01ac:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01ac }
            throw r2     // Catch:{ InterruptedException -> 0x01af }
        L_0x01af:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x01b5:
            r14.r = r2
            r14.g()
            r14.o()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.run():void");
    }

    private void m() {
        if (this.v != null && this.x && this.k.getAudioType().equals(XMediaplayerJNI.AudioType.NORMAL_FILE)) {
            Logger.a((Object) "flushAllDataInSoundTouch 0");
            SoundTouchDataModel soundTouchDataModel = new SoundTouchDataModel();
            int a2 = this.v.a(soundTouchDataModel);
            byte[] bArr = soundTouchDataModel.b;
            if (a2 == 0 || bArr == null || bArr.length == 0) {
                Logger.a((Object) "flushAllDataInSoundTouch 1");
                return;
            }
            int i2 = 0;
            if (this.o != null && this.o.getPlayState() == 3) {
                while (a2 > 0 && this.z && bArr != null) {
                    if (this.o != null && this.o.getPlayState() == 3) {
                        int write = this.o.write(bArr, i2, a2);
                        if (write <= 0) {
                            break;
                        }
                        i2 += write;
                        a2 -= write;
                    }
                }
            }
            Logger.a((Object) "flushAllDataInSoundTouch 2");
        }
    }

    private int a(byte[] bArr, int i2) {
        boolean z2;
        int i3;
        int i4;
        String str = XMediaplayerJNI.Tag;
        Logger.a(str, (Object) "AudioTrackPlayThread audioTrackWrite:" + i2);
        if (this.v == null || !this.x || !this.k.getAudioType().equals(XMediaplayerJNI.AudioType.NORMAL_FILE)) {
            z2 = false;
        } else {
            SoundTouchDataModel soundTouchDataModel = new SoundTouchDataModel(bArr, i2);
            i2 = this.v.a(soundTouchDataModel);
            bArr = soundTouchDataModel.b;
            z2 = true;
            if (i2 == 0 || bArr == null || bArr.length == 0) {
                return 0;
            }
        }
        if (this.o == null || this.o.getPlayState() != 3) {
            i4 = 0;
            i3 = 0;
        } else {
            i4 = 0;
            i3 = 0;
            while (i2 > 0 && this.z && bArr != null) {
                if (this.o != null && this.o.getPlayState() == 3) {
                    int write = this.o.write(bArr, i4, i2);
                    if (write <= 0) {
                        break;
                    }
                    i3 += write;
                    i4 += write;
                    i2 -= write;
                }
            }
        }
        if (i2 > 0 && !z2) {
            this.y = new byte[i2];
            System.arraycopy(bArr, i4, this.y, 0, i2);
        }
        if (i3 > 0 && this.k.mOnPlayDataOutputListener != null) {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i3);
            this.k.mOnPlayDataOutputListener.a(bArr2);
        }
        return i3;
    }

    public void b() {
        Logger.a(XMediaplayerJNI.Tag, (Object) "AudioTrackPlayThread dataReady");
        if (this.t) {
            synchronized (this.s) {
                Logger.a(XMediaplayerJNI.Tag, (Object) "AudioTrackPlayThread notify");
                this.s.notify();
                this.t = false;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r2 = this;
            monitor-enter(r2)
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "AudioTrackPlayThread startPlay0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x003e }
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x003e }
            int r0 = r0.getPlayState()     // Catch:{ all -> 0x003e }
            r1 = 3
            if (r0 == r1) goto L_0x0021
            r2.n()     // Catch:{ all -> 0x003e }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "AudioTrackPlayThread startPlay1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x003e }
        L_0x0021:
            r2.b()     // Catch:{ all -> 0x003e }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "AudioTrackPlayThread startPlay isRunning0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x003e }
            boolean r0 = r2.r     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "AudioTrackPlayThread startPlay isRunning1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x003e }
            r0 = 1
            r2.r = r0     // Catch:{ all -> 0x003e }
            r2.start()     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r2)
            return
        L_0x003e:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.c():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void d() {
        /*
            r2 = this;
            monitor-enter(r2)
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 0
            r2.z = r0     // Catch:{ all -> 0x0028 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "AudioTrackPlayThread pausePlay0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0028 }
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x0028 }
            int r0 = r0.getPlayState()     // Catch:{ all -> 0x0028 }
            r1 = 3
            if (r0 != r1) goto L_0x0026
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x0028 }
            r0.pause()     // Catch:{ all -> 0x0028 }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "AudioTrackPlayThread pausePlay1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r2)
            return
        L_0x0028:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.d():void");
    }

    public void e() {
        synchronized (this.s) {
            this.y = null;
            if (this.o != null && !this.j) {
                this.o.flush();
            }
        }
    }

    public void f() {
        d();
        e();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void g() {
        /*
            r2 = this;
            monitor-enter(r2)
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            r0 = 0
            r2.z = r0     // Catch:{ all -> 0x002d }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x002d }
            java.lang.String r1 = "AudioTrackPlayThread stopPlay0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x002d }
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x002d }
            int r0 = r0.getPlayState()     // Catch:{ all -> 0x002d }
            r1 = 1
            if (r0 == r1) goto L_0x002b
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x002d }
            r0.stop()     // Catch:{ all -> 0x002d }
            android.media.AudioTrack r0 = r2.o     // Catch:{ all -> 0x002d }
            r0.flush()     // Catch:{ all -> 0x002d }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x002d }
            java.lang.String r1 = "AudioTrackPlayThread stopPlay1"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r2)
            return
        L_0x002d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.g():void");
    }

    public synchronized void h() {
        this.z = false;
        this.j = true;
        Logger.a(XMediaplayerJNI.Tag, (Object) "AudioTrackPlayThread releasePlay");
        interrupt();
        if (!this.r) {
            o();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void n() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r3)
            return
        L_0x0007:
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            int r0 = r0.getState()     // Catch:{ all -> 0x006b }
            r1 = 1
            if (r0 != r1) goto L_0x002f
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            int r0 = r0.getPlayState()     // Catch:{ all -> 0x006b }
            r2 = 3
            if (r0 == r2) goto L_0x002f
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "AudioTrackPlayThread startPlay3"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r2)     // Catch:{ all -> 0x006b }
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            r0.play()     // Catch:{ all -> 0x006b }
            r3.z = r1     // Catch:{ all -> 0x006b }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "AudioTrackPlayThread startPlay4"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x006b }
            goto L_0x0069
        L_0x002f:
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            if (r0 == 0) goto L_0x0051
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "AudioTrackPlayThread startPlay5"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r2)     // Catch:{ all -> 0x006b }
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            r0.release()     // Catch:{ all -> 0x006b }
            r3.l()     // Catch:{ all -> 0x006b }
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            r0.play()     // Catch:{ all -> 0x006b }
            r3.z = r1     // Catch:{ all -> 0x006b }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "AudioTrackPlayThread startPlay6"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x006b }
            goto L_0x0069
        L_0x0051:
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r2 = "AudioTrackPlayThread startPlay7"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r2)     // Catch:{ all -> 0x006b }
            r3.l()     // Catch:{ all -> 0x006b }
            android.media.AudioTrack r0 = r3.o     // Catch:{ all -> 0x006b }
            r0.play()     // Catch:{ all -> 0x006b }
            r3.z = r1     // Catch:{ all -> 0x006b }
            java.lang.String r0 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x006b }
            java.lang.String r1 = "AudioTrackPlayThread startPlay8"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x006b }
        L_0x0069:
            monitor-exit(r3)
            return
        L_0x006b:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioTrackPlayThread.n():void");
    }

    public boolean i() {
        return this.j;
    }

    private synchronized void o() {
        this.z = false;
        this.j = true;
        Logger.a(XMediaplayerJNI.Tag, (Object) "AudioTrackPlayThread audioTrackRelease");
        if (this.o != null) {
            this.o.release();
            this.o = null;
        }
        this.v = null;
    }

    public boolean j() {
        return this.o != null && this.o.getPlayState() == 3;
    }

    public float k() {
        return this.A;
    }

    public synchronized void b(float f2) {
        this.A = f2;
        if (this.o != null && !this.j) {
            if (Build.VERSION.SDK_INT >= 21) {
                a(this.o, f2);
            } else {
                b(this.o, f2);
            }
        }
    }

    @TargetApi(21)
    private static void a(AudioTrack audioTrack, float f2) {
        if (audioTrack != null) {
            audioTrack.setVolume(f2 * AudioTrack.getMaxVolume());
        }
    }

    private static void b(AudioTrack audioTrack, float f2) {
        if (audioTrack != null) {
            audioTrack.setStereoVolume(AudioTrack.getMaxVolume() * f2, f2 * AudioTrack.getMaxVolume());
        }
    }
}
