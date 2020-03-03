package in.cashify.otex.widget;

import android.content.Context;
import android.os.AsyncTask;
import in.cashify.otex.R;

public class DiagnoseMicHeaderView extends DiagnoseHeaderView {
    public VisualizerView b;
    public b c;
    public a.a.a.b d;
    public int e = -1;

    public class b extends AsyncTask<Void, Void, Void> {
        public b() {
        }

        public final int a(int i, short[] sArr) {
            double d = 0.0d;
            for (int i2 = 0; i2 < i; i2++) {
                double d2 = (double) (sArr[i2] * sArr[i2]);
                Double.isNaN(d2);
                d += d2;
            }
            double d3 = (double) i;
            Double.isNaN(d3);
            return (int) (Math.log10(Math.abs(d / d3) / 32768.0d) * 20.0d);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0093, code lost:
            if (r14.getState() == 1) goto L_0x0095;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void doInBackground(java.lang.Void... r14) {
            /*
                r13 = this;
                r14 = -16
                android.os.Process.setThreadPriority(r14)
                r14 = 2
                r0 = 16
                r1 = 44100(0xac44, float:6.1797E-41)
                int r7 = android.media.AudioRecord.getMinBufferSize(r1, r0, r14)
                int r8 = r7 >> 1
                short[] r9 = new short[r8]
                r10 = 0
                r11 = 0
                int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00ac }
                r3 = 23
                r12 = 1
                if (r2 < r3) goto L_0x0047
                android.media.AudioRecord$Builder r2 = new android.media.AudioRecord$Builder     // Catch:{ all -> 0x00ac }
                r2.<init>()     // Catch:{ all -> 0x00ac }
                android.media.AudioRecord$Builder r2 = r2.setAudioSource(r12)     // Catch:{ all -> 0x00ac }
                android.media.AudioFormat$Builder r3 = new android.media.AudioFormat$Builder     // Catch:{ all -> 0x00ac }
                r3.<init>()     // Catch:{ all -> 0x00ac }
                android.media.AudioFormat$Builder r14 = r3.setEncoding(r14)     // Catch:{ all -> 0x00ac }
                android.media.AudioFormat$Builder r14 = r14.setSampleRate(r1)     // Catch:{ all -> 0x00ac }
                android.media.AudioFormat$Builder r14 = r14.setChannelMask(r0)     // Catch:{ all -> 0x00ac }
                android.media.AudioFormat r14 = r14.build()     // Catch:{ all -> 0x00ac }
                android.media.AudioRecord$Builder r14 = r2.setAudioFormat(r14)     // Catch:{ all -> 0x00ac }
                android.media.AudioRecord$Builder r14 = r14.setBufferSizeInBytes(r7)     // Catch:{ all -> 0x00ac }
                android.media.AudioRecord r14 = r14.build()     // Catch:{ all -> 0x00ac }
                goto L_0x0054
            L_0x0047:
                android.media.AudioRecord r14 = new android.media.AudioRecord     // Catch:{ all -> 0x00ac }
                r3 = 1
                r4 = 44100(0xac44, float:6.1797E-41)
                r5 = 16
                r6 = 2
                r2 = r14
                r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00ac }
            L_0x0054:
                if (r14 == 0) goto L_0x00a4
                int r0 = r14.getState()     // Catch:{ all -> 0x00ac }
                if (r0 == 0) goto L_0x00a4
                r14.startRecording()     // Catch:{ all -> 0x0099 }
                r0 = 0
            L_0x0061:
                boolean r2 = r13.isCancelled()     // Catch:{ all -> 0x0099 }
                if (r2 != 0) goto L_0x008f
                int r2 = r14.read(r9, r11, r8)     // Catch:{ all -> 0x0099 }
                int r2 = r13.a(r2, r9)     // Catch:{ all -> 0x0099 }
                in.cashify.otex.widget.DiagnoseMicHeaderView r3 = in.cashify.otex.widget.DiagnoseMicHeaderView.this     // Catch:{ all -> 0x0099 }
                int r3 = r3.e     // Catch:{ all -> 0x0099 }
                if (r2 <= r3) goto L_0x0079
                in.cashify.otex.widget.DiagnoseMicHeaderView r3 = in.cashify.otex.widget.DiagnoseMicHeaderView.this     // Catch:{ all -> 0x0099 }
                r3.e = r2     // Catch:{ all -> 0x0099 }
            L_0x0079:
                long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0099 }
                r5 = 0
                long r3 = r3 - r0
                r5 = 100
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r7 <= 0) goto L_0x0061
                long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0099 }
                in.cashify.otex.widget.DiagnoseMicHeaderView r3 = in.cashify.otex.widget.DiagnoseMicHeaderView.this     // Catch:{ all -> 0x0099 }
                r3.a(r2)     // Catch:{ all -> 0x0099 }
                goto L_0x0061
            L_0x008f:
                int r0 = r14.getState()     // Catch:{ all -> 0x00a3 }
                if (r0 != r12) goto L_0x00a3
            L_0x0095:
                r14.stop()     // Catch:{ all -> 0x00a3 }
                goto L_0x00a0
            L_0x0099:
                int r0 = r14.getState()     // Catch:{ all -> 0x00a3 }
                if (r0 != r12) goto L_0x00a3
                goto L_0x0095
            L_0x00a0:
                r14.release()     // Catch:{ all -> 0x00a3 }
            L_0x00a3:
                return r10
            L_0x00a4:
                java.lang.UnsupportedOperationException r14 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x00ac }
                java.lang.String r0 = "Cannot create AudioRecord"
                r14.<init>(r0)     // Catch:{ all -> 0x00ac }
                throw r14     // Catch:{ all -> 0x00ac }
            L_0x00ac:
                in.cashify.otex.widget.DiagnoseMicHeaderView r14 = in.cashify.otex.widget.DiagnoseMicHeaderView.this
                a.a.a.b r0 = new a.a.a.b
                r1 = 4004(0xfa4, float:5.611E-42)
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                java.lang.String r2 = "me"
                r0.<init>(r2, r1, r11)
                a.a.a.b unused = r14.d = r0
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.DiagnoseMicHeaderView.b.doInBackground(java.lang.Void[]):java.lang.Void");
        }

        public void onPreExecute() {
            super.onPreExecute();
            DiagnoseMicHeaderView.this.e = -1;
        }
    }

    public DiagnoseMicHeaderView(Context context) {
        super(context);
    }

    public DiagnoseMicHeaderView(Context context, int i) {
        super(context, i);
    }

    public void a() {
        b bVar = this.c;
        if (bVar != null && !bVar.isCancelled()) {
            this.c.cancel(true);
        }
    }

    public void a(int i) {
        VisualizerView visualizerView = this.b;
        if (visualizerView != null) {
            visualizerView.b(i);
        }
    }

    public void a(Context context, int i) {
        super.a(context, i);
        this.b = (VisualizerView) this.f2616a.findViewById(R.id.visualizer_view);
        this.b.getHolder().setFormat(-3);
    }

    public void c() {
        this.c = new b();
        this.c.execute(new Void[0]);
    }

    public void d() {
        b bVar = this.c;
        if (bVar != null && !bVar.isCancelled()) {
            this.c.cancel(true);
        }
    }
}
