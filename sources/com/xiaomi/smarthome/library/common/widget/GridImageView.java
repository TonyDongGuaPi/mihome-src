package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.BitmapUtils;

public class GridImageView extends View {
    Bitmap[] mBitmaps;
    String[] mImageNames;
    Paint mPaint = new Paint();
    boolean mRefresh = false;

    public GridImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint.setAntiAlias(true);
    }

    public void setImageFiles(String[] strArr) {
        this.mImageNames = strArr;
        this.mRefresh = true;
        postInvalidate();
    }

    /* access modifiers changed from: package-private */
    public void loadBitmap() {
        this.mRefresh = false;
        if (this.mImageNames != null && this.mImageNames.length != 0) {
            if (this.mBitmaps != null) {
                for (int i = 0; i < this.mBitmaps.length; i++) {
                    this.mBitmaps[i].recycle();
                    this.mBitmaps[i] = null;
                }
            }
            this.mBitmaps = new Bitmap[this.mImageNames.length];
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            int bitmapSize = getBitmapSize();
            for (int i2 = 0; i2 < this.mImageNames.length; i2++) {
                this.mBitmaps[i2] = BitmapUtils.b(BitmapFactory.decodeFile(this.mImageNames[i2], options), bitmapSize, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getHorizalBitmapCount() {
        if (this.mBitmaps == null) {
            return 0;
        }
        switch (this.mBitmaps.length) {
            case 1:
                return 1;
            case 2:
            case 3:
            case 4:
                return 2;
            default:
                return 3;
        }
    }

    /* access modifiers changed from: package-private */
    public int getBitmapSize() {
        int length = this.mBitmaps.length;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.grid_bitmap_margin);
        int horizalBitmapCount = getHorizalBitmapCount();
        return (getWidth() - (dimensionPixelSize * (horizalBitmapCount + 1))) / horizalBitmapCount;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mRefresh) {
            loadBitmap();
        }
        if (this.mBitmaps != null && this.mBitmaps.length != 0) {
            int length = this.mBitmaps.length;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.grid_bitmap_margin);
            int horizalBitmapCount = getHorizalBitmapCount();
            int width = (getWidth() - ((horizalBitmapCount + 1) * dimensionPixelSize)) / horizalBitmapCount;
            for (int i = 0; i < length; i++) {
                int i2 = width + dimensionPixelSize;
                canvas.drawBitmap(this.mBitmaps[i], (float) (((i % horizalBitmapCount) * i2) + dimensionPixelSize), (float) (((i / horizalBitmapCount) * i2) + (dimensionPixelSize * 2)), this.mPaint);
            }
        }
    }
}
