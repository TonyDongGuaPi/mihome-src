package miuipub.graphics.drawable;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.net.Uri;
import android.os.SystemClock;
import com.miuipub.internal.graphics.gif.DecodeGifImageHelper;
import com.miuipub.internal.graphics.gif.GifDecoder;
import java.util.ArrayList;
import miuipub.io.ResettableInputStream;

public class GifAnimationDrawable extends AnimationDrawable {

    /* renamed from: a  reason: collision with root package name */
    private final DecodeGifImageHelper f2931a = new DecodeGifImageHelper();
    private Resources b;
    private DrawableContainer.DrawableContainerState c;
    private ArrayList<Integer> d = new ArrayList<>();
    private ArrayList<Integer> e = new ArrayList<>();
    private int f;

    public final boolean selectDrawable(int i) {
        a(i);
        this.f = i;
        return super.selectDrawable(i);
    }

    private void a(int i) {
        if (!this.f2931a.c.isEmpty()) {
            DecodeGifImageHelper.GifFrame gifFrame = this.f2931a.c.get(0);
            if (this.f2931a.c.size() > 1) {
                this.f2931a.c.remove(0);
            }
            this.f2931a.a();
            this.c.getChildren()[i] = new BitmapDrawable(this.b, gifFrame.f8249a);
            this.d.add(i, Integer.valueOf(gifFrame.b));
        }
    }

    private boolean a(DecodeGifImageHelper.GifDecodeResult gifDecodeResult) {
        if (gifDecodeResult.f8248a == null || !gifDecodeResult.b) {
            return false;
        }
        GifDecoder gifDecoder = gifDecodeResult.f8248a;
        this.f2931a.e = gifDecoder.a();
        int c2 = gifDecoder.c();
        if (c2 <= 0) {
            return false;
        }
        for (int i = 0; i < c2; i++) {
            if (this.f2931a.e) {
                addFrame(new BitmapDrawable(this.b, gifDecoder.c(i)), gifDecoder.b(i));
            } else {
                this.f2931a.c.add(new DecodeGifImageHelper.GifFrame(gifDecoder.c(i), gifDecoder.b(i), i));
            }
        }
        if (!this.f2931a.e) {
            this.f2931a.b();
            DecodeGifImageHelper.GifFrame gifFrame = this.f2931a.c.get(0);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.b, gifFrame.f8249a);
            addFrame(bitmapDrawable, gifFrame.b);
            addFrame(bitmapDrawable, gifFrame.b);
        }
        setOneShot(false);
        super.selectDrawable(0);
        return true;
    }

    /* access modifiers changed from: protected */
    public final void setConstantState(DrawableContainer.DrawableContainerState drawableContainerState) {
        super.setConstantState(drawableContainerState);
        this.c = drawableContainerState;
    }

    public final int getDuration(int i) {
        return this.d.get(i).intValue();
    }

    public final void addFrame(Drawable drawable, int i) {
        super.addFrame(drawable, i);
        this.d.add(Integer.valueOf(i));
        this.e.add(Integer.valueOf(i));
    }

    public final void scheduleSelf(Runnable runnable, long j) {
        if (j == SystemClock.uptimeMillis() + ((long) this.e.get(this.f).intValue())) {
            j = SystemClock.uptimeMillis() + ((long) this.d.get(this.f).intValue());
        }
        super.scheduleSelf(runnable, j);
    }

    private boolean a(Resources resources, ResettableInputStream resettableInputStream) {
        this.b = resources;
        this.f2931a.g = resettableInputStream;
        return a(this.f2931a.a(0));
    }

    public boolean a(Context context, Uri uri) {
        return a(context.getResources(), new ResettableInputStream(context, uri));
    }

    public boolean a(Context context, String str) {
        return a(context.getResources(), new ResettableInputStream(str));
    }

    public boolean a(Context context, AssetManager assetManager, String str) {
        return a(context.getResources(), new ResettableInputStream(assetManager, str));
    }

    public final void a(long j) {
        this.f2931a.b = j;
    }
}
