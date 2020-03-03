package dk.madslee.imageCapInsets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import dk.madslee.imageCapInsets.utils.NinePatchBitmapFactory;
import dk.madslee.imageCapInsets.utils.RCTImageLoaderListener;
import dk.madslee.imageCapInsets.utils.RCTImageLoaderTask;

public class RCTImageCapInsetView extends ImageView {
    /* access modifiers changed from: private */
    public Rect mCapInsets = new Rect();
    private String mUri;

    public RCTImageCapInsetView(Context context) {
        super(context);
    }

    public void setCapInsets(Rect rect) {
        this.mCapInsets = rect;
        reload();
    }

    public void setSource(String str) {
        this.mUri = str;
        reload();
    }

    public void reload() {
        if (TextUtils.isEmpty(this.mUri)) {
            Log.w("RCTImageCapInsetView", "reload error: mUri is empty");
            return;
        }
        final String str = this.mUri + "-" + this.mCapInsets.toShortString();
        final RCTImageCache a2 = RCTImageCache.a();
        if (a2.b(str)) {
            setBackground(a2.a(str).getConstantState().newDrawable());
        } else {
            new RCTImageLoaderTask(this.mUri, getContext(), new RCTImageLoaderListener() {
                public void a(Bitmap bitmap) {
                    int round = Math.round((float) (bitmap.getDensity() / 160));
                    int i = RCTImageCapInsetView.this.mCapInsets.top * round;
                    int width = bitmap.getWidth() - (RCTImageCapInsetView.this.mCapInsets.right * round);
                    int height = bitmap.getHeight() - (RCTImageCapInsetView.this.mCapInsets.bottom * round);
                    NinePatchDrawable a2 = NinePatchBitmapFactory.a(RCTImageCapInsetView.this.getResources(), bitmap, i, RCTImageCapInsetView.this.mCapInsets.left * round, height, width, (String) null);
                    RCTImageCapInsetView.this.setBackground(a2);
                    a2.a(str, a2);
                }
            }).execute(new String[0]);
        }
    }
}
