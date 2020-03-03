package com.xiaomi.shopviews;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.ViewManager;
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.event.IEventProcessor;
import com.tmall.wireless.vaf.virtualview.view.image.ImageBase;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.adapter.bigvision.GravityImage;
import com.xiaomi.shopviews.adapter.countdown.CountDown;

public class WidgetApplication {

    /* renamed from: a  reason: collision with root package name */
    public static Application f13060a = null;
    public static boolean b = false;
    private static VafContext c;
    private static ViewManager d;

    static class ImageTarget extends SimpleTarget<Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        ImageBase f13062a;

        public ImageTarget(ImageBase imageBase) {
            this.f13062a = imageBase;
        }

        public void a(Bitmap bitmap, Transition transition) {
            this.f13062a.b(bitmap);
        }
    }

    public static void a(final Application application) {
        f13060a = application;
        ScreenInfo.a().a(f13060a);
        c = new VafContext((Context) application);
        c.a((ImageLoader.IImageLoaderAdapter) new ImageLoader.IImageLoaderAdapter() {
            public void a(String str, int i, int i2, ImageLoader.Listener listener) {
            }

            public void a(String str, ImageBase imageBase, int i, int i2) {
                Glide.c(application).j().a(str).a(new ImageTarget(imageBase));
            }
        });
        d = c.p();
        d.a((Context) application);
        c.e().a(0, (IEventProcessor) new IEventProcessor() {
            public boolean a(EventData eventData) {
                Log.d("ClickProcessorImpl", "event " + eventData.b);
                return true;
            }
        });
        d.a().a(1100, (ViewBase.IBuilder) new GravityImage.Builder());
        d.a().a(1101, (ViewBase.IBuilder) new CountDown.Builder());
    }

    public static VafContext a() {
        return c;
    }

    public static VafContext b() {
        return c;
    }

    public static ViewManager c() {
        return d;
    }
}
