package org.sufficientlysecure.htmltextview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URL;

public class HtmlHttpImageGetter implements Html.ImageGetter {

    /* renamed from: a  reason: collision with root package name */
    TextView f4185a;
    URI b;
    boolean c;
    private boolean d = false;
    private int e = 50;

    public HtmlHttpImageGetter(TextView textView) {
        this.f4185a = textView;
        this.c = false;
    }

    public HtmlHttpImageGetter(TextView textView, String str) {
        this.f4185a = textView;
        if (str != null) {
            this.b = URI.create(str);
        }
    }

    public HtmlHttpImageGetter(TextView textView, String str, boolean z) {
        this.f4185a = textView;
        this.c = z;
        if (str != null) {
            this.b = URI.create(str);
        }
    }

    public void a(boolean z) {
        a(z, 50);
    }

    public void a(boolean z, int i) {
        this.d = z;
        this.e = i;
    }

    public Drawable getDrawable(String str) {
        UrlDrawable urlDrawable = new UrlDrawable();
        new ImageGetterAsyncTask(urlDrawable, this, this.f4185a, this.c, this.d, this.e).execute(new String[]{str});
        return urlDrawable;
    }

    private static class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<UrlDrawable> f4186a;
        private final WeakReference<HtmlHttpImageGetter> b;
        private final WeakReference<View> c;
        private final WeakReference<Resources> d;
        private String e;
        private boolean f;
        private float g;
        private boolean h = false;
        private int i = 50;

        public ImageGetterAsyncTask(UrlDrawable urlDrawable, HtmlHttpImageGetter htmlHttpImageGetter, View view, boolean z, boolean z2, int i2) {
            this.f4186a = new WeakReference<>(urlDrawable);
            this.b = new WeakReference<>(htmlHttpImageGetter);
            this.c = new WeakReference<>(view);
            this.d = new WeakReference<>(view.getResources());
            this.f = z;
            this.h = z2;
            this.i = i2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Drawable doInBackground(String... strArr) {
            this.e = strArr[0];
            if (this.d.get() == null) {
                return null;
            }
            if (this.h) {
                return b((Resources) this.d.get(), this.e);
            }
            return a((Resources) this.d.get(), this.e);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Drawable drawable) {
            if (drawable == null) {
                Log.w(HtmlTextView.TAG, "Drawable result is null! (source: " + this.e + Operators.BRACKET_END_STR);
                return;
            }
            UrlDrawable urlDrawable = (UrlDrawable) this.f4186a.get();
            if (urlDrawable != null) {
                urlDrawable.setBounds(0, 0, (int) (((float) drawable.getIntrinsicWidth()) * this.g), (int) (((float) drawable.getIntrinsicHeight()) * this.g));
                urlDrawable.f4187a = drawable;
                HtmlHttpImageGetter htmlHttpImageGetter = (HtmlHttpImageGetter) this.b.get();
                if (htmlHttpImageGetter != null) {
                    htmlHttpImageGetter.f4185a.invalidate();
                    htmlHttpImageGetter.f4185a.setText(htmlHttpImageGetter.f4185a.getText());
                }
            }
        }

        public Drawable a(Resources resources, String str) {
            try {
                BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, a(str));
                this.g = b(bitmapDrawable);
                bitmapDrawable.setBounds(0, 0, (int) (((float) bitmapDrawable.getIntrinsicWidth()) * this.g), (int) (((float) bitmapDrawable.getIntrinsicHeight()) * this.g));
                return bitmapDrawable;
            } catch (Exception unused) {
                return null;
            }
        }

        public Drawable b(Resources resources, String str) {
            try {
                InputStream a2 = a(str);
                Bitmap bitmap = new BitmapDrawable(resources, a2).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, this.i, byteArrayOutputStream);
                bitmap.recycle();
                a2.close();
                Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                byteArrayOutputStream.close();
                this.g = a(decodeStream);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, decodeStream);
                bitmapDrawable.setBounds(0, 0, (int) (((float) bitmapDrawable.getIntrinsicWidth()) * this.g), (int) (((float) bitmapDrawable.getIntrinsicHeight()) * this.g));
                return bitmapDrawable;
            } catch (Exception unused) {
                return null;
            }
        }

        private float a(Bitmap bitmap) {
            View view = (View) this.c.get();
            if (view == null) {
                return 1.0f;
            }
            return ((float) view.getWidth()) / ((float) bitmap.getWidth());
        }

        private float b(Drawable drawable) {
            View view = (View) this.c.get();
            if (!this.f || view == null) {
                return 1.0f;
            }
            return ((float) view.getWidth()) / ((float) drawable.getIntrinsicWidth());
        }

        private InputStream a(String str) throws IOException {
            URL url;
            HtmlHttpImageGetter htmlHttpImageGetter = (HtmlHttpImageGetter) this.b.get();
            if (htmlHttpImageGetter == null) {
                return null;
            }
            if (htmlHttpImageGetter.b != null) {
                url = htmlHttpImageGetter.b.resolve(str).toURL();
            } else {
                url = URI.create(str).toURL();
            }
            return (InputStream) url.getContent();
        }
    }

    public class UrlDrawable extends BitmapDrawable {

        /* renamed from: a  reason: collision with root package name */
        protected Drawable f4187a;

        public UrlDrawable() {
        }

        public void draw(Canvas canvas) {
            if (this.f4187a != null) {
                this.f4187a.draw(canvas);
            }
        }
    }
}
