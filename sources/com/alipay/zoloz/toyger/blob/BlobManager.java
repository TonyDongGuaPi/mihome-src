package com.alipay.zoloz.toyger.blob;

import android.graphics.Bitmap;
import android.util.Log;
import com.alipay.zoloz.toyger.ToygerBiometricInfo;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.ToygerBlobConfig;
import java.util.List;
import java.util.Map;

public abstract class BlobManager<Info extends ToygerBiometricInfo> {
    protected static final String BLOB_ELEM_TYPE_DOC = "doc";
    protected static final String BLOB_ELEM_TYPE_FACE = "face";
    public static final String BLOB_VERSION = "1.0";
    protected static final int META_ALGRESULT_BAT = 3;
    protected static final int META_ALGRESULT_DRAGONFLY = 2;
    protected static final int META_ALGRESULT_VERIFY = 1;
    public static final int META_SERIALIZER_JSON = 1;
    public static final int META_SERIALIZER_PB = 2;
    protected static final String META_TYPE_DOC = "zdoc";
    protected static final String META_TYPE_FACE = "zface";
    public static final String SUB_TYPE_DARK = "Dark";
    public static final String SUB_TYPE_DEPTH = "Depth";
    public static final String SUB_TYPE_DOC_IMAGE = "docimage";
    public static final String SUB_TYPE_PANO = "Pano";
    public static final String SUB_TYPE_SURVEILLANCE = "Surveillance";
    public static final String SUB_TYPE_VERSION = "1.0";
    protected static final String TAG = "BlobManager";
    protected ToygerBlobConfig config;
    protected CryptoManager crypto;

    public abstract byte[] generateBlob(List<Info> list, Map<String, Object> map);

    public abstract byte[] getKey();

    public abstract boolean isUTF8();

    /* access modifiers changed from: protected */
    public byte[] processFrame(TGFrame tGFrame) {
        return processFrame(tGFrame, this.config.getDesiredWidth().intValue(), (int) (this.config.getCompressRate() * 100.0f));
    }

    /* access modifiers changed from: protected */
    public byte[] processFrame(TGFrame tGFrame, int i, int i2) {
        if (tGFrame == null || tGFrame.data == null) {
            Log.e(TAG, "BlobManager.processFrame(), frame data is null");
        } else {
            int frameMode = getFrameMode(tGFrame);
            if (frameMode < 0) {
                Log.e(TAG, "unsupported frame mode");
            } else {
                Bitmap bytes2Bitmap = BitmapHelper.bytes2Bitmap(tGFrame.data, tGFrame.width, tGFrame.height, frameMode);
                if (bytes2Bitmap == null) {
                    Log.e(TAG, "failed to encode bitmap");
                } else {
                    Bitmap rotateBitmap = BitmapHelper.rotateBitmap(bytes2Bitmap, tGFrame.rotation);
                    if (rotateBitmap == null) {
                        Log.e(TAG, "failed to rotate bitmap");
                    } else {
                        if (rotateBitmap.getWidth() <= i || i <= 0) {
                            i = rotateBitmap.getWidth();
                        }
                        if (i != tGFrame.width) {
                            rotateBitmap = BitmapHelper.resize(rotateBitmap, i);
                        }
                        if (rotateBitmap == null) {
                            Log.e(TAG, "failed to resize bitmap");
                        } else {
                            byte[] bitmapToByteArray = BitmapHelper.bitmapToByteArray(rotateBitmap, i2);
                            if (bitmapToByteArray == null) {
                                Log.e(TAG, "failed to jpeg encode");
                            } else {
                                byte[] encrypt = this.crypto.encrypt(bitmapToByteArray);
                                if (encrypt != null) {
                                    return encrypt;
                                }
                                Log.e(TAG, "failed to encrypt");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private int getFrameMode(TGFrame tGFrame) {
        switch (tGFrame.frameMode) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return -1;
        }
    }
}
