package com.mishopsdk.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.mishopsdk.volley.toolbox.ImageLoader;

public class NetworkImageView extends ImageView {
    /* access modifiers changed from: private */
    public int mDefaultImageId;
    /* access modifiers changed from: private */
    public int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    public NetworkImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setImageUrl(String str, ImageLoader imageLoader) {
        this.mUrl = str;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int i) {
        this.mDefaultImageId = i;
    }

    public void setErrorImageResId(int i) {
        this.mErrorImageId = i;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadImageIfNecessary(final boolean r8) {
        /*
            r7 = this;
            int r0 = r7.getWidth()
            int r1 = r7.getHeight()
            android.view.ViewGroup$LayoutParams r2 = r7.getLayoutParams()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0026
            android.view.ViewGroup$LayoutParams r2 = r7.getLayoutParams()
            int r2 = r2.width
            r5 = -2
            if (r2 != r5) goto L_0x001b
            r2 = 1
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            android.view.ViewGroup$LayoutParams r6 = r7.getLayoutParams()
            int r6 = r6.height
            if (r6 != r5) goto L_0x0027
            r5 = 1
            goto L_0x0028
        L_0x0026:
            r2 = 0
        L_0x0027:
            r5 = 0
        L_0x0028:
            if (r2 == 0) goto L_0x002d
            if (r5 == 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r3 = 0
        L_0x002e:
            if (r0 != 0) goto L_0x0035
            if (r1 != 0) goto L_0x0035
            if (r3 != 0) goto L_0x0035
            return
        L_0x0035:
            java.lang.String r3 = r7.mUrl
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x004d
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r8 = r7.mImageContainer
            if (r8 == 0) goto L_0x0049
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r8 = r7.mImageContainer
            r8.cancelRequest()
            r8 = 0
            r7.mImageContainer = r8
        L_0x0049:
            r7.setDefaultImageOrNull()
            return
        L_0x004d:
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r3 = r7.mImageContainer
            if (r3 == 0) goto L_0x0070
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r3 = r7.mImageContainer
            java.lang.String r3 = r3.getRequestUrl()
            if (r3 == 0) goto L_0x0070
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r3 = r7.mImageContainer
            java.lang.String r3 = r3.getRequestUrl()
            java.lang.String r6 = r7.mUrl
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x0068
            return
        L_0x0068:
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r3 = r7.mImageContainer
            r3.cancelRequest()
            r7.setDefaultImageOrNull()
        L_0x0070:
            if (r2 == 0) goto L_0x0073
            r0 = 0
        L_0x0073:
            if (r5 == 0) goto L_0x0076
            r1 = 0
        L_0x0076:
            com.mishopsdk.volley.toolbox.ImageLoader r2 = r7.mImageLoader
            java.lang.String r3 = r7.mUrl
            com.mishopsdk.volley.toolbox.NetworkImageView$1 r4 = new com.mishopsdk.volley.toolbox.NetworkImageView$1
            r4.<init>(r8)
            com.mishopsdk.volley.toolbox.ImageLoader$ImageContainer r8 = r2.get(r3, r4, r0, r1)
            r7.mImageContainer = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mishopsdk.volley.toolbox.NetworkImageView.loadImageIfNecessary(boolean):void");
    }

    private void setDefaultImageOrNull() {
        if (this.mDefaultImageId != 0) {
            setImageResource(this.mDefaultImageId);
        } else {
            setImageBitmap((Bitmap) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.mImageContainer != null) {
            this.mImageContainer.cancelRequest();
            setImageBitmap((Bitmap) null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
