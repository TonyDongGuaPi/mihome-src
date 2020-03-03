package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import com.facebook.react.uimanager.ThemedReactContext;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.shop.utils.IOUtils;
import dk.madslee.imageCapInsets.RCTImageCache;
import dk.madslee.imageCapInsets.RCTImageCapInsetManager;
import dk.madslee.imageCapInsets.RCTImageCapInsetView;
import dk.madslee.imageCapInsets.utils.NinePatchBitmapFactory;
import dk.madslee.imageCapInsets.utils.RCTImageLoaderListener;
import dk.madslee.imageCapInsets.utils.RCTImageLoaderTask;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MiotImageCapInsetManager extends RCTImageCapInsetManager {
    private static final String TAG = "MiotImageCapInsetManager";

    /* access modifiers changed from: protected */
    public RCTImageCapInsetView createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTImageCapInsetView(themedReactContext) {
            /* access modifiers changed from: private */
            public boolean isLocalFile = false;
            private Rect mCapInsets = new Rect();
            /* access modifiers changed from: private */
            public String mUri;

            public void setCapInsets(Rect rect) {
                this.mCapInsets = rect;
                reload();
            }

            public void setSource(String str) {
                this.isLocalFile = str.startsWith("file://");
                this.mUri = str;
                reload();
            }

            public void reload() {
                if (TextUtils.isEmpty(this.mUri)) {
                    LogUtil.e(MiotImageCapInsetManager.TAG, "MiotImageCapInsetManager.RCTImageCapInsetView,reload error: mUri is empty");
                    return;
                }
                final String str = this.mUri + "-" + this.mCapInsets.toShortString();
                final RCTImageCache a2 = RCTImageCache.a();
                if (a2.b(str)) {
                    setBackground(a2.a(str).getConstantState().newDrawable());
                } else {
                    new RCTImageLoaderTask(this.mUri, getContext(), new RCTImageLoaderListener() {
                        public void a(Bitmap bitmap) {
                            AnonymousClass1.this.reloadImage(a2, str, bitmap);
                        }
                    }) {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Bitmap doInBackground(String... strArr) {
                            if (AnonymousClass1.this.isLocalFile) {
                                return AnonymousClass1.this.loadBitmap(AnonymousClass1.this.mUri);
                            }
                            return super.doInBackground(strArr);
                        }
                    }.execute(new String[0]);
                }
            }

            /* access modifiers changed from: private */
            public void reloadImage(RCTImageCache rCTImageCache, String str, Bitmap bitmap) {
                int round = Math.round((float) (bitmap.getDensity() / 160));
                int i = this.mCapInsets.top * round;
                int width = bitmap.getWidth() - (this.mCapInsets.right * round);
                int height = bitmap.getHeight() - (this.mCapInsets.bottom * round);
                NinePatchDrawable a2 = NinePatchBitmapFactory.a(getResources(), bitmap, i, this.mCapInsets.left * round, height, width, (String) null);
                setBackground(a2);
                rCTImageCache.a(str, a2);
            }

            /* access modifiers changed from: private */
            public Bitmap loadBitmap(String str) {
                Throwable th;
                InputStream inputStream;
                InputStream inputStream2 = null;
                try {
                    inputStream = new URL(str).openStream();
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                        IOUtils.a((Closeable) inputStream);
                        return decodeStream;
                    } catch (IOException e) {
                        e = e;
                        try {
                            e.printStackTrace();
                            IOUtils.a((Closeable) inputStream);
                            return null;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            inputStream2 = inputStream;
                            th = th3;
                            IOUtils.a((Closeable) inputStream2);
                            throw th;
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    inputStream = null;
                    e.printStackTrace();
                    IOUtils.a((Closeable) inputStream);
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    IOUtils.a((Closeable) inputStream2);
                    throw th;
                }
            }
        };
    }
}
