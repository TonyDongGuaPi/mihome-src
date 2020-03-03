package com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import com.xiaomi.smarthome.R;
import java.lang.ref.WeakReference;

public final class MeasureHelper {
    private int mCurrentAspectRatio = 0;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WeakReference<View> mWeakView;

    public MeasureHelper(View view) {
        this.mWeakView = new WeakReference<>(view);
    }

    public View getView() {
        if (this.mWeakView == null) {
            return null;
        }
        return (View) this.mWeakView.get();
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        this.mVideoSarNum = i;
        this.mVideoSarDen = i2;
    }

    public void setVideoRotation(int i) {
        this.mVideoRotationDegree = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0101, code lost:
        if (r1 > r9) goto L_0x0125;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doMeasure(int r9, int r10) {
        /*
            r8 = this;
            int r0 = r8.mVideoRotationDegree
            r1 = 270(0x10e, float:3.78E-43)
            r2 = 90
            if (r0 == r2) goto L_0x000c
            int r0 = r8.mVideoRotationDegree
            if (r0 != r1) goto L_0x000f
        L_0x000c:
            r7 = r10
            r10 = r9
            r9 = r7
        L_0x000f:
            int r0 = r8.mVideoWidth
            int r0 = android.view.View.getDefaultSize(r0, r9)
            int r3 = r8.mVideoHeight
            int r3 = android.view.View.getDefaultSize(r3, r10)
            int r4 = r8.mCurrentAspectRatio
            r5 = 3
            if (r4 != r5) goto L_0x0022
            goto L_0x0125
        L_0x0022:
            int r4 = r8.mVideoWidth
            if (r4 <= 0) goto L_0x0123
            int r4 = r8.mVideoHeight
            if (r4 <= 0) goto L_0x0123
            int r0 = android.view.View.MeasureSpec.getMode(r9)
            int r9 = android.view.View.MeasureSpec.getSize(r9)
            int r3 = android.view.View.MeasureSpec.getMode(r10)
            int r10 = android.view.View.MeasureSpec.getSize(r10)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r4) goto L_0x00bc
            if (r3 != r4) goto L_0x00bc
            float r0 = (float) r9
            float r3 = (float) r10
            float r4 = r0 / r3
            int r5 = r8.mCurrentAspectRatio
            switch(r5) {
                case 4: goto L_0x0071;
                case 5: goto L_0x0063;
                default: goto L_0x0049;
            }
        L_0x0049:
            int r1 = r8.mVideoWidth
            float r1 = (float) r1
            int r2 = r8.mVideoHeight
            float r2 = (float) r2
            float r5 = r1 / r2
            int r1 = r8.mVideoSarNum
            if (r1 <= 0) goto L_0x007e
            int r1 = r8.mVideoSarDen
            if (r1 <= 0) goto L_0x007e
            int r1 = r8.mVideoSarNum
            float r1 = (float) r1
            float r5 = r5 * r1
            int r1 = r8.mVideoSarDen
            float r1 = (float) r1
            float r5 = r5 / r1
            goto L_0x007e
        L_0x0063:
            r5 = 1068149419(0x3faaaaab, float:1.3333334)
            int r6 = r8.mVideoRotationDegree
            if (r6 == r2) goto L_0x006e
            int r2 = r8.mVideoRotationDegree
            if (r2 != r1) goto L_0x007e
        L_0x006e:
            r5 = 1061158912(0x3f400000, float:0.75)
            goto L_0x007e
        L_0x0071:
            r5 = 1071877689(0x3fe38e39, float:1.7777778)
            int r6 = r8.mVideoRotationDegree
            if (r6 == r2) goto L_0x007c
            int r2 = r8.mVideoRotationDegree
            if (r2 != r1) goto L_0x007e
        L_0x007c:
            r5 = 1058013184(0x3f100000, float:0.5625)
        L_0x007e:
            int r1 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x0084
            r1 = 1
            goto L_0x0085
        L_0x0084:
            r1 = 0
        L_0x0085:
            int r2 = r8.mCurrentAspectRatio
            switch(r2) {
                case 0: goto L_0x00a2;
                case 1: goto L_0x0097;
                case 2: goto L_0x008a;
                case 3: goto L_0x008a;
                case 4: goto L_0x00a2;
                case 5: goto L_0x00a2;
                default: goto L_0x008a;
            }
        L_0x008a:
            if (r1 == 0) goto L_0x00ad
            int r10 = r8.mVideoWidth
            int r9 = java.lang.Math.min(r10, r9)
            float r10 = (float) r9
            float r10 = r10 / r5
            int r10 = (int) r10
            goto L_0x0125
        L_0x0097:
            if (r1 == 0) goto L_0x009e
            float r3 = r3 * r5
            int r9 = (int) r3
            goto L_0x0125
        L_0x009e:
            float r0 = r0 / r5
            int r10 = (int) r0
            goto L_0x0125
        L_0x00a2:
            if (r1 == 0) goto L_0x00a8
            float r0 = r0 / r5
            int r10 = (int) r0
            goto L_0x0125
        L_0x00a8:
            float r3 = r3 * r5
            int r9 = (int) r3
            goto L_0x0125
        L_0x00ad:
            int r9 = r8.mVideoHeight
            int r9 = java.lang.Math.min(r9, r10)
            float r10 = (float) r9
            float r10 = r10 * r5
            int r10 = (int) r10
            r7 = r10
            r10 = r9
            r9 = r7
            goto L_0x0125
        L_0x00bc:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r0 != r1) goto L_0x00e6
            if (r3 != r1) goto L_0x00e6
            int r0 = r8.mVideoWidth
            int r0 = r0 * r10
            int r1 = r8.mVideoHeight
            int r1 = r1 * r9
            if (r0 >= r1) goto L_0x00d4
            int r9 = r8.mVideoWidth
            int r9 = r9 * r10
            int r0 = r8.mVideoHeight
            int r9 = r9 / r0
            goto L_0x0125
        L_0x00d4:
            int r0 = r8.mVideoWidth
            int r0 = r0 * r10
            int r1 = r8.mVideoHeight
            int r1 = r1 * r9
            if (r0 <= r1) goto L_0x0125
            int r10 = r8.mVideoHeight
            int r10 = r10 * r9
            int r0 = r8.mVideoWidth
            int r10 = r10 / r0
            goto L_0x0125
        L_0x00e6:
            if (r0 != r1) goto L_0x00f6
            int r0 = r8.mVideoHeight
            int r0 = r0 * r9
            int r1 = r8.mVideoWidth
            int r0 = r0 / r1
            if (r3 != r4) goto L_0x00f4
            if (r0 <= r10) goto L_0x00f4
            goto L_0x0125
        L_0x00f4:
            r10 = r0
            goto L_0x0125
        L_0x00f6:
            if (r3 != r1) goto L_0x0106
            int r1 = r8.mVideoWidth
            int r1 = r1 * r10
            int r2 = r8.mVideoHeight
            int r1 = r1 / r2
            if (r0 != r4) goto L_0x0104
            if (r1 <= r9) goto L_0x0104
            goto L_0x0125
        L_0x0104:
            r9 = r1
            goto L_0x0125
        L_0x0106:
            int r1 = r8.mVideoWidth
            int r2 = r8.mVideoHeight
            if (r3 != r4) goto L_0x0116
            if (r2 <= r10) goto L_0x0116
            int r1 = r8.mVideoWidth
            int r1 = r1 * r10
            int r2 = r8.mVideoHeight
            int r1 = r1 / r2
            goto L_0x0117
        L_0x0116:
            r10 = r2
        L_0x0117:
            if (r0 != r4) goto L_0x0104
            if (r1 <= r9) goto L_0x0104
            int r10 = r8.mVideoHeight
            int r10 = r10 * r9
            int r0 = r8.mVideoWidth
            int r10 = r10 / r0
            goto L_0x0125
        L_0x0123:
            r9 = r0
            r10 = r3
        L_0x0125:
            r8.mMeasuredWidth = r9
            r8.mMeasuredHeight = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage.MeasureHelper.doMeasure(int, int):void");
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
    }

    @NonNull
    public static String getAspectRatioText(Context context, int i) {
        switch (i) {
            case 0:
                return context.getString(R.string.VideoView_ar_aspect_fit_parent);
            case 1:
                return context.getString(R.string.VideoView_ar_aspect_fill_parent);
            case 2:
                return context.getString(R.string.VideoView_ar_aspect_wrap_content);
            case 3:
                return context.getString(R.string.VideoView_ar_match_parent);
            case 4:
                return context.getString(R.string.VideoView_ar_16_9_fit_parent);
            case 5:
                return context.getString(R.string.VideoView_ar_4_3_fit_parent);
            default:
                return context.getString(R.string.N_A);
        }
    }
}
