package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.File;

public class ImageObject extends BaseMediaObject {
    public static final Parcelable.Creator<ImageObject> CREATOR = new Parcelable.Creator<ImageObject>() {
        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    };
    private static final int DATA_SIZE = 2097152;
    public byte[] imageData;
    public String imagePath;

    public int describeContents() {
        return 0;
    }

    public int getObjType() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public BaseMediaObject toExtraMediaObject(String str) {
        return this;
    }

    /* access modifiers changed from: protected */
    public String toExtraMediaString() {
        return "";
    }

    public ImageObject() {
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c A[SYNTHETIC, Splitter:B:16:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037 A[SYNTHETIC, Splitter:B:21:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setImageObject(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x001f }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r3.imageData = r4     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r1.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0017:
            r4 = move-exception
            r0 = r1
            goto L_0x0035
        L_0x001a:
            r4 = move-exception
            r0 = r1
            goto L_0x0020
        L_0x001d:
            r4 = move-exception
            goto L_0x0035
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r4.printStackTrace()     // Catch:{ all -> 0x001d }
            java.lang.String r4 = "Weibo.ImageObject"
            java.lang.String r1 = "put thumb failed"
            com.sina.weibo.sdk.utils.LogUtil.c(r4, r1)     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0034:
            return
        L_0x0035:
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x003f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.ImageObject.setImageObject(android.graphics.Bitmap):void");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            LogUtil.c("Weibo.ImageObject", "imageData and imagePath are null");
            return false;
        } else if (this.imageData != null && this.imageData.length > 2097152) {
            LogUtil.c("Weibo.ImageObject", "imageData is too large");
            return false;
        } else if (this.imagePath != null && this.imagePath.length() > 512) {
            LogUtil.c("Weibo.ImageObject", "imagePath is too length");
            return false;
        } else if (this.imagePath == null) {
            return true;
        } else {
            File file = new File(this.imagePath);
            try {
                if (file.exists() && file.length() != 0 && file.length() <= 10485760) {
                    return true;
                }
                LogUtil.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                return false;
            } catch (SecurityException unused) {
                LogUtil.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                return false;
            }
        }
    }
}
