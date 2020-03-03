package com.xiaomi.ai.streamplayer;

import android.media.AudioTrack;
import com.xiaomi.ai.PCMInfo;
import com.xiaomi.ai.utils.Log;

public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9943a = "MiSpeechSDK:StramingPlayer";
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 3;
    /* access modifiers changed from: private */
    public boolean b = false;
    private int g = 1;
    /* access modifiers changed from: private */
    public a h;
    private b i = new b();
    /* access modifiers changed from: private */
    public a j;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public int l;

    public interface a {
        void a(k kVar);

        void a(k kVar, int i, String str);

        void a(k kVar, AudioTrack audioTrack);

        void a(k kVar, PCMInfo pCMInfo);
    }

    private class b extends Thread {

        /* renamed from: a  reason: collision with root package name */
        final int f9944a;
        int b;
        int c;
        private AudioTrack e;

        private b() {
            this.f9944a = 5;
            this.c = 0;
        }

        private void a() {
            if (this.e != null) {
                try {
                    if (k.this.l == 1) {
                        this.e.stop();
                    }
                } catch (Exception unused) {
                }
                synchronized (this) {
                    this.e.release();
                    this.e = null;
                    Log.d(k.f9943a, "releaseAudioTrack");
                }
            }
        }

        private void a(byte[] bArr) {
            if (this.e != null && bArr != null && !k.this.b) {
                this.e.write(bArr, 0, bArr.length);
            }
        }

        private boolean a(int i, int i2) {
            AudioTrack audioTrack;
            if (i2 == 1) {
                audioTrack = new AudioTrack(k.this.k, i, 4, 2, AudioTrack.getMinBufferSize(i, 4, 2), 1);
            } else if (i2 == 2) {
                audioTrack = new AudioTrack(k.this.k, i, 12, 2, AudioTrack.getMinBufferSize(i, 12, 2), 1);
            } else {
                throw new IllegalArgumentException("not supported chanel numbers" + i2);
            }
            this.e = audioTrack;
            if (this.e.getState() != 0) {
                if (k.this.l == 1) {
                    this.e.play();
                }
                return true;
            } else if (k.this.h == null) {
                return false;
            } else {
                k.this.h.a(k.this, 15, "STATE_UNINITIALIZED");
                return false;
            }
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0014 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b() {
            /*
                r3 = this;
                android.media.AudioTrack r0 = r3.e
                monitor-enter(r3)
                if (r0 == 0) goto L_0x0014
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this     // Catch:{ Exception -> 0x0014 }
                int r1 = r1.l     // Catch:{ Exception -> 0x0014 }
                r2 = 1
                if (r1 != r2) goto L_0x0014
                r0.pause()     // Catch:{ Exception -> 0x0014 }
                goto L_0x0014
            L_0x0012:
                r0 = move-exception
                goto L_0x0016
            L_0x0014:
                monitor-exit(r3)     // Catch:{ all -> 0x0012 }
                return
            L_0x0016:
                monitor-exit(r3)     // Catch:{ all -> 0x0012 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.streamplayer.k.b.b():void");
        }

        private void c() {
            if (this.e != null && !k.this.b) {
                this.e.flush();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a0, code lost:
            com.xiaomi.ai.streamplayer.k.b(r15.d).a(r15.d);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0121, code lost:
            if (com.xiaomi.ai.streamplayer.k.b(r15.d) != null) goto L_0x00a0;
         */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x0125 A[LOOP:0: B:1:0x000e->B:71:0x0125, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x0114 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r15 = this;
                super.run()
                com.xiaomi.ai.streamplayer.k r0 = com.xiaomi.ai.streamplayer.k.this
                int r0 = r0.l
                r1 = 0
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
            L_0x000e:
                com.xiaomi.ai.streamplayer.k r6 = com.xiaomi.ai.streamplayer.k.this
                boolean r6 = r6.b
                if (r6 != 0) goto L_0x012b
                r6 = 2
                r7 = 1
                if (r1 != 0) goto L_0x00b9
                com.xiaomi.ai.streamplayer.k r8 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.a r8 = r8.j
                com.xiaomi.ai.streamplayer.a$a r8 = r8.e()
                if (r8 == 0) goto L_0x003f
                int r9 = r8.b
                if (r9 <= 0) goto L_0x003f
                boolean r2 = r8.a(r2)
                if (r2 != 0) goto L_0x003f
                r15.a()
                int r2 = r8.f9934a
                int r9 = r8.b
                boolean r2 = r15.a(r2, r9)
                if (r2 != 0) goto L_0x003f
                goto L_0x012b
            L_0x003f:
                com.xiaomi.ai.streamplayer.k r2 = com.xiaomi.ai.streamplayer.k.this
                boolean r2 = r2.b
                if (r2 == 0) goto L_0x0049
                goto L_0x012b
            L_0x0049:
                if (r8 == 0) goto L_0x00ad
                byte[] r1 = r8.e
                if (r1 == 0) goto L_0x0096
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r1 = r1.h
                if (r1 == 0) goto L_0x0066
                if (r3 != 0) goto L_0x0066
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r1 = r1.h
                com.xiaomi.ai.streamplayer.k r2 = com.xiaomi.ai.streamplayer.k.this
                android.media.AudioTrack r3 = r15.e
                r1.a((com.xiaomi.ai.streamplayer.k) r2, (android.media.AudioTrack) r3)
            L_0x0066:
                if (r0 != r6) goto L_0x0090
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r1 = r1.h
                if (r1 == 0) goto L_0x0095
                byte[] r1 = r8.e
                int r1 = r1.length
                int r4 = r4 + r1
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r1 = r1.h
                com.xiaomi.ai.streamplayer.k r2 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.PCMInfo r3 = new com.xiaomi.ai.PCMInfo
                int r10 = r8.f9934a
                int r11 = r8.b
                int r12 = r8.c
                boolean r13 = r8.d
                byte[] r14 = r8.e
                r9 = r3
                r9.<init>(r10, r11, r12, r13, r14)
                r1.a((com.xiaomi.ai.streamplayer.k) r2, (com.xiaomi.ai.PCMInfo) r3)
                goto L_0x0095
            L_0x0090:
                byte[] r1 = r8.e
                r15.a((byte[]) r1)
            L_0x0095:
                r3 = 1
            L_0x0096:
                boolean r1 = r8.d
                if (r1 == 0) goto L_0x00ad
                if (r0 != r7) goto L_0x00a0
                r15.c()
                goto L_0x00ad
            L_0x00a0:
                com.xiaomi.ai.streamplayer.k r0 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r0 = r0.h
                com.xiaomi.ai.streamplayer.k r1 = com.xiaomi.ai.streamplayer.k.this
                r0.a(r1)
                goto L_0x012b
            L_0x00ad:
                com.xiaomi.ai.streamplayer.k r2 = com.xiaomi.ai.streamplayer.k.this
                boolean r2 = r2.b
                if (r2 == 0) goto L_0x00b7
                goto L_0x012b
            L_0x00b7:
                r2 = r8
                goto L_0x00d3
            L_0x00b9:
                com.xiaomi.ai.streamplayer.k r8 = com.xiaomi.ai.streamplayer.k.this
                boolean r8 = r8.b
                if (r8 == 0) goto L_0x00c3
                goto L_0x012b
            L_0x00c3:
                monitor-enter(r15)     // Catch:{ InterruptedException -> 0x012b }
                r8 = 40
                r15.wait(r8)     // Catch:{ all -> 0x0128 }
                monitor-exit(r15)     // Catch:{ all -> 0x0128 }
                com.xiaomi.ai.streamplayer.k r8 = com.xiaomi.ai.streamplayer.k.this
                boolean r8 = r8.b
                if (r8 == 0) goto L_0x00d3
                goto L_0x012b
            L_0x00d3:
                if (r3 == 0) goto L_0x00ee
                if (r1 == 0) goto L_0x00ee
                android.media.AudioTrack r8 = r15.e
                if (r8 == 0) goto L_0x00ee
                int r8 = r15.b
                if (r8 == 0) goto L_0x00ee
                android.media.AudioTrack r8 = r15.e
                int r8 = r8.getPlaybackHeadPosition()
                int r9 = r15.b
                if (r8 != r9) goto L_0x00ee
                int r8 = r15.c
                int r8 = r8 + r7
                r15.c = r8
            L_0x00ee:
                if (r0 != r6) goto L_0x00ff
                if (r3 == 0) goto L_0x00ff
                if (r1 == 0) goto L_0x00ff
                android.media.AudioTrack r6 = r15.e
                if (r6 == 0) goto L_0x00ff
                if (r4 != r5) goto L_0x00ff
                int r5 = r15.c
                int r5 = r5 + r7
                r15.c = r5
            L_0x00ff:
                if (r3 == 0) goto L_0x010d
                android.media.AudioTrack r5 = r15.e
                if (r5 == 0) goto L_0x010d
                android.media.AudioTrack r5 = r15.e
                int r5 = r5.getPlaybackHeadPosition()
                r15.b = r5
            L_0x010d:
                if (r1 == 0) goto L_0x0125
                int r5 = r15.c
                r6 = 5
                if (r5 < r6) goto L_0x0125
                java.lang.String r0 = "MiSpeechSDK:StramingPlayer"
                java.lang.String r1 = "on end"
                com.xiaomi.ai.utils.Log.a(r0, r1)
                com.xiaomi.ai.streamplayer.k r0 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.k$a r0 = r0.h
                if (r0 == 0) goto L_0x012b
                goto L_0x00a0
            L_0x0125:
                r5 = r4
                goto L_0x000e
            L_0x0128:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x0128 }
                throw r0     // Catch:{ InterruptedException -> 0x012b }
            L_0x012b:
                r15.a()
                com.xiaomi.ai.streamplayer.k r0 = com.xiaomi.ai.streamplayer.k.this
                com.xiaomi.ai.streamplayer.a r0 = r0.j
                r0.b()
                java.lang.String r0 = "MiSpeechSDK:StramingPlayer"
                java.lang.String r1 = "release decoder and track"
                com.xiaomi.ai.utils.Log.a(r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.streamplayer.k.b.run():void");
        }

        public void start() {
            super.start();
        }
    }

    public k(int i2) {
        this.k = i2;
        this.l = 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.g     // Catch:{ all -> 0x001f }
            r1 = 2
            if (r0 != r1) goto L_0x001d
            boolean r0 = r2.b     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000b
            goto L_0x001d
        L_0x000b:
            r0 = 0
            r2.b = r0     // Catch:{ all -> 0x001f }
            r0 = 3
            r2.g = r0     // Catch:{ all -> 0x001f }
            com.xiaomi.ai.streamplayer.a r0 = r2.j     // Catch:{ all -> 0x001f }
            r0.a()     // Catch:{ all -> 0x001f }
            com.xiaomi.ai.streamplayer.k$b r0 = r2.i     // Catch:{ all -> 0x001f }
            r0.start()     // Catch:{ all -> 0x001f }
            monitor-exit(r2)
            return
        L_0x001d:
            monitor-exit(r2)
            return
        L_0x001f:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.streamplayer.k.a():void");
    }

    public void a(int i2) {
        this.l = i2;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public synchronized void a(String str) {
        if ("mp3".equalsIgnoreCase(str)) {
            this.j = TTSDecoderConfig.f9933a ? new d() : new f();
            this.g = 2;
        } else {
            throw new RuntimeException("not supported  tts codec !!");
        }
    }

    public void a(byte[] bArr, int i2, int i3) {
        this.j.a(bArr, i2, i3);
    }

    public synchronized void b() {
        if (this.g == 3) {
            this.j.d();
            this.g = 3;
        }
    }

    public synchronized void c() {
        this.b = true;
        this.i.b();
        b();
        this.j.c();
    }
}
