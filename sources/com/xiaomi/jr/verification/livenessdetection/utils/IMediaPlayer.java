package com.xiaomi.jr.verification.livenessdetection.utils;

import android.content.Context;
import android.media.MediaPlayer;
import com.xiaomi.jr.verification.R;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionType;

public class IMediaPlayer {

    /* renamed from: a  reason: collision with root package name */
    public MediaPlayer f11057a = new MediaPlayer();
    private Context b;
    /* access modifiers changed from: private */
    public OnPlayListener c;

    public interface OnPlayListener {
        void a(int i);

        void b(int i);
    }

    public IMediaPlayer(Context context) {
        this.b = context;
    }

    public void a() {
        this.b = null;
        if (this.f11057a != null) {
            this.f11057a.reset();
            this.f11057a.release();
            this.f11057a = null;
        }
    }

    public void b() {
        if (this.f11057a != null) {
            this.f11057a.reset();
        }
    }

    public void a(OnPlayListener onPlayListener) {
        this.c = onPlayListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A[SYNTHETIC, Splitter:B:20:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e A[SYNTHETIC, Splitter:B:25:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final int r9) {
        /*
            r8 = this;
            android.media.MediaPlayer r0 = r8.f11057a
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            android.media.MediaPlayer r0 = r8.f11057a
            r0.reset()
            r0 = 0
            android.content.Context r1 = r8.b     // Catch:{ Exception -> 0x004d }
            android.content.res.Resources r1 = r1.getResources()     // Catch:{ Exception -> 0x004d }
            android.content.res.AssetFileDescriptor r1 = r1.openRawResourceFd(r9)     // Catch:{ Exception -> 0x004d }
            android.media.MediaPlayer r2 = r8.f11057a     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            java.io.FileDescriptor r3 = r1.getFileDescriptor()     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            long r4 = r1.getStartOffset()     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            long r6 = r1.getLength()     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r2.setDataSource(r3, r4, r6)     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            android.media.MediaPlayer r0 = r8.f11057a     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer$1 r2 = new com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer$1     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r0.setOnPreparedListener(r2)     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            android.media.MediaPlayer r0 = r8.f11057a     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer$2 r2 = new com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer$2     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r2.<init>(r9)     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r0.setOnCompletionListener(r2)     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            android.media.MediaPlayer r9 = r8.f11057a     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            r9.prepareAsync()     // Catch:{ Exception -> 0x0047, all -> 0x0045 }
            if (r1 == 0) goto L_0x005b
            r1.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005b
        L_0x0045:
            r9 = move-exception
            goto L_0x005c
        L_0x0047:
            r9 = move-exception
            r0 = r1
            goto L_0x004e
        L_0x004a:
            r9 = move-exception
            r1 = r0
            goto L_0x005c
        L_0x004d:
            r9 = move-exception
        L_0x004e:
            r9.printStackTrace()     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r9 = move-exception
            r9.printStackTrace()
        L_0x005b:
            return
        L_0x005c:
            if (r1 == 0) goto L_0x0066
            r1.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0066:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer.a(int):void");
    }

    public int a(DetectionType detectionType) {
        switch (detectionType) {
            case BLINK:
                return R.raw.detection_type_eye_blink;
            case MOUTH:
                return R.raw.detection_type_mouth_open;
            case POS_PITCH_UP:
                return R.raw.detection_type_pitch_up;
            case POS_YAW_LEFT:
                return R.raw.detection_type_yaw_left;
            case POS_YAW_RIGHT:
                return R.raw.detection_type_yaw_right;
            default:
                return -1;
        }
    }

    public DetectionType b(int i) {
        if (i == R.raw.detection_type_eye_blink) {
            return DetectionType.BLINK;
        }
        if (i == R.raw.detection_type_mouth_open) {
            return DetectionType.MOUTH;
        }
        if (i == R.raw.detection_type_pitch_up) {
            return DetectionType.POS_PITCH_UP;
        }
        if (i == R.raw.detection_type_yaw_left) {
            return DetectionType.POS_YAW_LEFT;
        }
        if (i == R.raw.detection_type_yaw_right) {
            return DetectionType.POS_YAW_RIGHT;
        }
        return null;
    }
}
