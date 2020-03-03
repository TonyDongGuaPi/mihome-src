package com.xiaomi.qrcode2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.R;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

public class BarcodeGenActivity extends Activity {
    public static final String BARCODE = "barcode";
    public static final String TAG = "BarcodeGenActivity";
    String mBarcode;
    Bitmap mBitmap;

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        this.mBarcode = getIntent().getStringExtra(BARCODE);
        if (TextUtils.isEmpty(this.mBarcode)) {
            setResult(0);
            finish();
            return;
        }
        setContentView(R.layout.barcode_gen_activity);
        ImageView imageView = (ImageView) findViewById(R.id.barcode_image);
        this.mBitmap = a(this.mBarcode);
        if (this.mBitmap != null) {
            imageView.setImageBitmap(this.mBitmap);
            return;
        }
        setResult(0);
        finish();
    }

    private Bitmap a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i = (int) (((float) displayMetrics.widthPixels) - (displayMetrics.density * 0.0f));
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix a2 = new QRCodeWriter().a(str, BarcodeFormat.QR_CODE, i, i, hashtable);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (a2.a(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            this.mBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return this.mBitmap;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }
}
