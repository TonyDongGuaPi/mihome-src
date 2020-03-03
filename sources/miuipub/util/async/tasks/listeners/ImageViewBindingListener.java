package miuipub.util.async.tasks.listeners;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;
import com.miuipub.internal.util.PackageConstants;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import miuipub.util.async.Task;
import miuipub.util.async.TaskManager;

public class ImageViewBindingListener extends BaseTaskListener {

    /* renamed from: a  reason: collision with root package name */
    private static final LinkedHashSet<ImageViewBindingListener> f3046a = new LinkedHashSet<>();
    private final WeakReference<ImageView> b;
    private WeakReference<Task<?>> c;
    private Drawable d;
    private Drawable e;

    public ImageViewBindingListener(ImageView imageView) {
        this.b = new WeakReference<>(imageView);
    }

    public ImageViewBindingListener a(Drawable drawable) {
        this.d = drawable;
        return this;
    }

    public ImageViewBindingListener b(Drawable drawable) {
        this.e = drawable;
        return this;
    }

    private boolean a(ImageViewBindingListener imageViewBindingListener) {
        return this.b.get() == imageViewBindingListener.b.get();
    }

    public void a(TaskManager taskManager, Task<?> task) {
        ImageView imageView;
        Task task2;
        if (!(this.c == null || (task2 = (Task) this.c.get()) == null)) {
            task2.e();
        }
        this.c = new WeakReference<>(task);
        Task task3 = null;
        Iterator it = f3046a.iterator();
        while (true) {
            if (it.hasNext()) {
                ImageViewBindingListener imageViewBindingListener = (ImageViewBindingListener) it.next();
                if (imageViewBindingListener != this && a(imageViewBindingListener)) {
                    task3 = (Task) imageViewBindingListener.c.get();
                    break;
                }
            } else {
                break;
            }
        }
        if (task3 != null) {
            task3.e();
        }
        f3046a.add(this);
        if (this.d != null && (imageView = (ImageView) this.b.get()) != null) {
            imageView.setImageDrawable(this.d);
        }
    }

    public Object a(TaskManager taskManager, Task<?> task, Object obj) {
        ImageView imageView = (ImageView) this.b.get();
        if (imageView != null) {
            Drawable[] drawableArr = {imageView.getDrawable(), null};
            if (drawableArr[0] != null) {
                Resources resources = PackageConstants.a().getResources();
                if (obj instanceof Bitmap) {
                    drawableArr[1] = new BitmapDrawable(resources, (Bitmap) obj);
                } else {
                    drawableArr[1] = (Drawable) obj;
                }
                TransitionDrawable transitionDrawable = new TransitionDrawable(drawableArr);
                transitionDrawable.setCrossFadeEnabled(true);
                imageView.setImageDrawable(transitionDrawable);
                transitionDrawable.startTransition(resources.getInteger(17694720));
            } else if (obj instanceof Bitmap) {
                imageView.setImageBitmap((Bitmap) obj);
            } else {
                imageView.setImageDrawable((Drawable) obj);
            }
        }
        return obj;
    }

    public void a(TaskManager taskManager, Task<?> task, Exception exc) {
        ImageView imageView;
        if (this.e != null && (imageView = (ImageView) this.b.get()) != null) {
            imageView.setImageDrawable(this.e);
        }
    }

    public void c(TaskManager taskManager, Task<?> task) {
        f3046a.remove(this);
    }
}
