package com.google.webrtc.apm;

public class WebRtcJni {
    /* access modifiers changed from: private */
    public static native int WebRtcAecm_BufferFarend(long j, short[] sArr, int i);

    /* access modifiers changed from: private */
    public static native int WebRtcAecm_BufferFarendBytes(long j, byte[] bArr, int i);

    /* access modifiers changed from: private */
    public static native long WebRtcAecm_Create();

    /* access modifiers changed from: private */
    public static native int WebRtcAecm_Free(long j);

    /* access modifiers changed from: private */
    public static native int WebRtcAecm_Init(long j, int i);

    /* access modifiers changed from: private */
    public static native short[] WebRtcAecm_Process(long j, short[] sArr, short[] sArr2, int i, int i2);

    /* access modifiers changed from: private */
    public static native int WebRtcAecm_set_config(long j, int i, int i2);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_AddFarend(long j, short[] sArr, int i);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_AddMic(long j, short[] sArr, int i);

    /* access modifiers changed from: private */
    public static native long WebRtcAgc_Create();

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_Free(long j);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_Init(long j, int i, int i2, int i3, int i4);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_Process(long j, short[] sArr, int i, short[] sArr2, int i2, int[] iArr, int i3, int[] iArr2);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_VirtualMic(long j, short[] sArr, int i, int i2, int[] iArr);

    /* access modifiers changed from: private */
    public static native int WebRtcAgc_set_config(long j, int i, int i2, int i3);

    /* access modifiers changed from: private */
    public static native long WebRtcNs_Create();

    /* access modifiers changed from: private */
    public static native int WebRtcNs_Free(long j);

    /* access modifiers changed from: private */
    public static native int WebRtcNs_Init(long j, int i);

    /* access modifiers changed from: private */
    public static native short[] WebRtcNs_Process(long j, short[] sArr, int i);

    /* access modifiers changed from: private */
    public static native int WebRtcNs_set_policy(long j, int i);

    /* access modifiers changed from: private */
    public static native long WebRtcVad_Create();

    /* access modifiers changed from: private */
    public static native int WebRtcVad_Free(long j);

    /* access modifiers changed from: private */
    public static native int WebRtcVad_Init(long j);

    /* access modifiers changed from: private */
    public static native int WebRtcVad_Process(long j, int i, short[] sArr);

    /* access modifiers changed from: private */
    public static native int WebRtcVad_set_mode(long j, int i);

    static {
        System.loadLibrary("webrtc_apm");
    }

    public static class WebRtcVad {
        private long ctx;
        private Ticker ticker;

        public WebRtcVad(int i) {
            this.ctx = 0;
            this.ticker = new Ticker();
            this.ctx = WebRtcJni.WebRtcVad_Create();
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcVad_Init(this.ctx);
                int unused2 = WebRtcJni.WebRtcVad_set_mode(this.ctx, i);
                return;
            }
            throw new RuntimeException("WebRtcVad_Create failed");
        }

        public void release() {
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcVad_Free(this.ctx);
                this.ctx = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            release();
        }

        public boolean process(int i, short[] sArr) {
            return process(i, sArr, true);
        }

        public boolean process(int i, short[] sArr, boolean z) {
            if (1 == WebRtcJni.WebRtcVad_Process(this.ctx, i, sArr)) {
                this.ticker.resetTime();
                return true;
            } else if (!z || this.ticker.elapsedTime() >= 3000) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static class WebRtcNs {
        private long ctx;

        public WebRtcNs(int i, int i2) {
            this.ctx = 0;
            this.ctx = WebRtcJni.WebRtcNs_Create();
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcNs_Init(this.ctx, i);
                int unused2 = WebRtcJni.WebRtcNs_set_policy(this.ctx, i2);
                return;
            }
            throw new RuntimeException("WebRtcNs_Create failed");
        }

        public void release() {
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcNs_Free(this.ctx);
                this.ctx = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            release();
        }

        public short[] process(short[] sArr, int i) {
            return WebRtcJni.WebRtcNs_Process(this.ctx, sArr, i);
        }
    }

    public static class WebRtcAecm {
        private long ctx;

        public WebRtcAecm(int i, boolean z, int i2) {
            this.ctx = 0;
            this.ctx = WebRtcJni.WebRtcAecm_Create();
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcAecm_Init(this.ctx, i);
                int unused2 = WebRtcJni.WebRtcAecm_set_config(this.ctx, z ? 1 : 0, i2);
                return;
            }
            throw new RuntimeException("WebRtcAecm_Create failed");
        }

        public void release() {
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcAecm_Free(this.ctx);
                this.ctx = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            release();
        }

        public synchronized short[] process(short[] sArr, short[] sArr2, int i, int i2) {
            return WebRtcJni.WebRtcAecm_Process(this.ctx, sArr, sArr2, i, i2);
        }

        public synchronized int bufferFarend(short[] sArr, int i) {
            return WebRtcJni.WebRtcAecm_BufferFarend(this.ctx, sArr, i);
        }

        public synchronized int bufferFarendBytes(byte[] bArr, int i) {
            return WebRtcJni.WebRtcAecm_BufferFarendBytes(this.ctx, bArr, i);
        }
    }

    public static class WebRtcAgc {
        private long ctx;

        public static class ResultOfProcess {
            public short[] out;
            public int outMicLevel;
            public int ret;
            public int saturationWarning;
        }

        public static class ResultOfVirtualMic {
            public int micLevelOut;
            public int ret;
        }

        public WebRtcAgc(int i, int i2, int i3, int i4) {
            this.ctx = 0;
            this.ctx = WebRtcJni.WebRtcAgc_Create();
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcAgc_Init(this.ctx, i, i2, i3, i4);
                return;
            }
            throw new RuntimeException("WebRtcAgc_Create failed");
        }

        public void release() {
            if (this.ctx != 0) {
                int unused = WebRtcJni.WebRtcAgc_Free(this.ctx);
                this.ctx = 0;
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            release();
        }

        public int setConfig(int i, int i2, boolean z) {
            return WebRtcJni.WebRtcAgc_set_config(this.ctx, i, i2, z ? 1 : 0);
        }

        public int addFarend(short[] sArr, int i) {
            return WebRtcJni.WebRtcAgc_AddFarend(this.ctx, sArr, i);
        }

        public int addMic(short[] sArr, int i) {
            return WebRtcJni.WebRtcAgc_AddMic(this.ctx, sArr, i);
        }

        public ResultOfVirtualMic virtualMic(short[] sArr, int i, int i2) {
            ResultOfVirtualMic resultOfVirtualMic = new ResultOfVirtualMic();
            int[] iArr = new int[1];
            resultOfVirtualMic.ret = WebRtcJni.WebRtcAgc_VirtualMic(this.ctx, sArr, i, i2, iArr);
            resultOfVirtualMic.micLevelOut = iArr[0];
            return resultOfVirtualMic;
        }

        public ResultOfProcess process(short[] sArr, int i, int i2, int i3) {
            ResultOfProcess resultOfProcess = new ResultOfProcess();
            short[] sArr2 = sArr;
            resultOfProcess.out = new short[sArr2.length];
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            resultOfProcess.ret = WebRtcJni.WebRtcAgc_Process(this.ctx, sArr2, i, resultOfProcess.out, i2, iArr, i3, iArr2);
            resultOfProcess.outMicLevel = iArr[0];
            resultOfProcess.saturationWarning = iArr2[0];
            return resultOfProcess;
        }
    }
}
