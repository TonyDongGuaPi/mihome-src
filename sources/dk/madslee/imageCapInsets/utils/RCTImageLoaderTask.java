package dk.madslee.imageCapInsets.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.URL;

public class RCTImageLoaderTask extends AsyncTask<String, Void, Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final String f2582a;
    private final Context b;
    private final RCTResourceDrawableIdHelper c = new RCTResourceDrawableIdHelper();
    private final RCTImageLoaderListener d;

    public RCTImageLoaderTask(String str, Context context, RCTImageLoaderListener rCTImageLoaderListener) {
        this.f2582a = str;
        this.b = context;
        this.d = rCTImageLoaderListener;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap doInBackground(String... strArr) {
        if (this.f2582a.startsWith("http")) {
            return b(this.f2582a);
        }
        return a(this.f2582a);
    }

    private Bitmap a(String str) {
        return BitmapFactory.decodeResource(this.b.getResources(), this.c.a(this.b, str));
    }

    private Bitmap b(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap bitmap) {
        if (!isCancelled()) {
            this.d.a(bitmap);
        }
    }
}
