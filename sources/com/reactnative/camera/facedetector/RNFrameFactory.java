package com.reactnative.camera.facedetector;

import android.graphics.Bitmap;
import com.google.android.gms.vision.Frame;
import com.reactnative.camera.utils.ImageDimensions;
import java.nio.ByteBuffer;

public class RNFrameFactory {
    public static RNFrame a(byte[] bArr, int i, int i2, int i3) {
        Frame.Builder builder = new Frame.Builder();
        builder.setImageData(ByteBuffer.wrap(bArr), i, i2, 17);
        if (i3 == 90) {
            builder.setRotation(1);
        } else if (i3 == 180) {
            builder.setRotation(2);
        } else if (i3 != 270) {
            builder.setRotation(0);
        } else {
            builder.setRotation(3);
        }
        return new RNFrame(builder.build(), new ImageDimensions(i, i2, i3));
    }

    public static RNFrame a(Bitmap bitmap) {
        Frame.Builder builder = new Frame.Builder();
        builder.setBitmap(bitmap);
        return new RNFrame(builder.build(), new ImageDimensions(bitmap.getWidth(), bitmap.getHeight()));
    }
}