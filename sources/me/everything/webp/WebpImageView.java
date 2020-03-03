package me.everything.webp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class WebpImageView extends ImageView {
    public static final boolean NATIVE_WEB_P_SUPPORT = (Build.VERSION.SDK_INT >= 18);

    public WebpImageView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public WebpImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public WebpImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        Bitmap bitmap;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.webp);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.webp_webp_src, 0);
        obtainStyledAttributes.recycle();
        byte[] streamToBytes = streamToBytes(getResources().openRawResource(resourceId));
        if (!NATIVE_WEB_P_SUPPORT) {
            bitmap = WebPDecoder.a().a(streamToBytes);
        } else {
            bitmap = BitmapFactory.decodeByteArray(streamToBytes, 0, streamToBytes.length);
        }
        setImageBitmap(bitmap);
    }

    private static byte[] streamToBytes(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
                bArr = new byte[1024];
            }
        } catch (Exception unused) {
            return null;
        }
    }
}
