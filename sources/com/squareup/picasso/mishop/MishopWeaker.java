package com.squareup.picasso.mishop;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class MishopWeaker implements Handler.Callback {
    private static final int ADD_REMOVE_PRE_DRAW_LISTENER = 4;
    private static final int ARG_ADD_PRE_DRAW_LISTENER = 1;
    private static final int ARG_REMOVE_PRE_DRAW_LISTENER = -1;
    private static final boolean DEBUG = false;
    private static final int MANAGE_WEAK_IMAGE_BKG = 1;
    private static final int RELOAD_IMAGE_ON_PRE_DRAW = 3;
    private static final int SET_IMAGE_VIEW_TO_WEAK = 2;
    private static final String TAG = "MishopWeaker";
    private static AtomicBoolean mMishopEnabled = new AtomicBoolean(false);
    private static Picasso mPicasso;
    private static MishopWeaker sInstance;
    private Handler mBkgHandler;
    /* access modifiers changed from: private */
    public Handler mMainThreadHandler = new Handler(Looper.getMainLooper(), this);
    /* access modifiers changed from: private */
    public MishopLruCache mMishopLruCache = MishopLruCache.getInstance();
    private Set<ImageViewHolder> mWeakImageSet = new HashSet();

    protected MishopWeaker(Picasso picasso) {
        mPicasso = picasso;
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mBkgHandler = new Handler(handlerThread.getLooper(), this);
    }

    public static synchronized MishopWeaker getInstance(Picasso picasso) {
        MishopWeaker mishopWeaker;
        synchronized (MishopWeaker.class) {
            if (sInstance == null) {
                sInstance = new MishopWeaker(picasso);
            }
            mishopWeaker = sInstance;
        }
        return mishopWeaker;
    }

    public static void enableMishop(boolean z) {
        mMishopEnabled.set(z);
    }

    public static boolean mishopEnabled(int i) {
        if (mMishopEnabled.get() && MemoryPolicy.shouldUseMemoryCache(i)) {
            return true;
        }
        return false;
    }

    public void onImageViewRequest(ImageView imageView, ImageViewAction imageViewAction, String str) {
        imageView.setTag(new ImageTag(imageViewAction, str));
    }

    public void onImageViewBind(ImageView imageView) {
        Message obtainMessage = this.mBkgHandler.obtainMessage(1);
        obtainMessage.obj = imageView;
        obtainMessage.sendToTarget();
    }

    public boolean isActionExpired(ImageView imageView, ImageViewAction imageViewAction) {
        Object tag = imageView.getTag();
        if (tag == null || !(tag instanceof ImageTag) || imageViewAction == ((ImageTag) tag).mAction) {
            return false;
        }
        return true;
    }

    public boolean reloadImage(ImageView imageView) {
        Object tag = imageView.getTag();
        if (tag == null || !(tag instanceof ImageTag)) {
            return false;
        }
        ImageTag imageTag = (ImageTag) imageView.getTag();
        if (imageTag.mAction == null) {
            return false;
        }
        mPicasso.resumeAction(imageTag.mAction);
        return true;
    }

    public boolean handleMessage(Message message) {
        int intrinsicWidth;
        int currentSize;
        Object tag;
        ImageTag imageTag;
        Object tag2;
        ImageTag imageTag2;
        ImageView imageView;
        switch (message.what) {
            case 1:
                ImageViewHolder createImageHolder = createImageHolder((ImageView) message.obj);
                if (createImageHolder != null) {
                    ArrayList<ImageViewHolder> arrayList = new ArrayList<>();
                    synchronized (this.mWeakImageSet) {
                        if (!this.mWeakImageSet.contains(createImageHolder)) {
                            addImageViewToSet(createImageHolder);
                        }
                        for (ImageViewHolder next : this.mWeakImageSet) {
                            ImageView imageView2 = (ImageView) next.mImageView.get();
                            if (imageView2 == null) {
                                arrayList.add(next);
                            } else {
                                Object tag3 = imageView2.getTag();
                                if (tag3 != null && (tag3 instanceof ImageTag)) {
                                    if (((ImageTag) tag3).mWeak != null) {
                                        ImageTag imageTag3 = (ImageTag) tag3;
                                        boolean z = imageTag3.mWeak.get();
                                        boolean contains = this.mMishopLruCache.contains(imageTag3.mCacheKey);
                                        if (z || contains) {
                                            if (contains && z && imageView2.getDrawable() != null) {
                                                imageTag3.mWeak.set(false);
                                            }
                                        } else if (!visibleByUser(imageView2)) {
                                            imageTag3.mWeak.set(true);
                                            Message obtainMessage = this.mMainThreadHandler.obtainMessage(2);
                                            obtainMessage.obj = imageView2;
                                            obtainMessage.sendToTarget();
                                        } else {
                                            Drawable drawable = imageView2.getDrawable();
                                            if (drawable != null && (intrinsicWidth = drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight()) > 0 && (currentSize = this.mMishopLruCache.getCurrentSize()) > (this.mMishopLruCache.getMaxSize() * 7) / 10) {
                                                this.mMishopLruCache.trimToSize(currentSize - intrinsicWidth);
                                            }
                                        }
                                    }
                                }
                                arrayList.add(next);
                            }
                        }
                        for (ImageViewHolder removeImageViewFromSet : arrayList) {
                            removeImageViewFromSet(removeImageViewFromSet);
                        }
                    }
                    break;
                }
                break;
            case 2:
                ImageView imageView3 = (ImageView) message.obj;
                if (imageView3 != null && (tag = imageView3.getTag()) != null && (tag instanceof ImageTag) && ((imageTag = (ImageTag) tag) == null || imageTag.mWeak.get())) {
                    imageView3.setImageDrawable((Drawable) null);
                    break;
                }
            case 3:
                ImageView imageView4 = (ImageView) message.obj;
                if (imageView4 != null && (tag2 = imageView4.getTag()) != null && (tag2 instanceof ImageTag) && (imageTag2 = (ImageTag) tag2) != null && imageTag2.mWeak.get() && imageView4.hasWindowFocus()) {
                    mPicasso.resumeAction(imageTag2.mAction);
                    break;
                }
            case 4:
                ImageViewHolder imageViewHolder = (ImageViewHolder) message.obj;
                if (!(imageViewHolder == null || (imageView = (ImageView) imageViewHolder.mImageView.get()) == null)) {
                    if (message.arg1 != 1) {
                        if (message.arg1 == -1) {
                            imageView.getViewTreeObserver().removeOnPreDrawListener(imageViewHolder);
                            break;
                        }
                    } else {
                        imageView.getViewTreeObserver().addOnPreDrawListener(imageViewHolder);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean visibleByUser(ImageView imageView) {
        Context context;
        if (imageView == null || !imageView.hasWindowFocus() || (context = imageView.getContext()) == null) {
            return false;
        }
        int[] iArr = new int[2];
        imageView.getLocationOnScreen(iArr);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new Rect(iArr[0], iArr[1], iArr[0] + imageView.getWidth(), iArr[1] + imageView.getHeight()).intersect(new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels));
    }

    private boolean addImageViewToSet(ImageViewHolder imageViewHolder) {
        if (imageViewHolder == null || ((ImageView) imageViewHolder.mImageView.get()) == null) {
            return false;
        }
        Message obtainMessage = this.mMainThreadHandler.obtainMessage(4);
        obtainMessage.arg1 = 1;
        obtainMessage.obj = imageViewHolder;
        obtainMessage.sendToTarget();
        this.mWeakImageSet.add(imageViewHolder);
        return true;
    }

    private boolean removeImageViewFromSet(ImageViewHolder imageViewHolder) {
        if (!this.mWeakImageSet.contains(imageViewHolder)) {
            return false;
        }
        if (((ImageView) imageViewHolder.mImageView.get()) != null) {
            Message obtainMessage = this.mMainThreadHandler.obtainMessage(4);
            obtainMessage.arg1 = -1;
            obtainMessage.obj = imageViewHolder;
            obtainMessage.sendToTarget();
        }
        this.mWeakImageSet.remove(imageViewHolder);
        return true;
    }

    public ImageViewHolder createImageHolder(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        return new ImageViewHolder(imageView);
    }

    private class ImageViewHolder implements ViewTreeObserver.OnPreDrawListener {
        private int mHashCode;
        /* access modifiers changed from: private */
        public WeakReference<ImageView> mImageView;

        protected ImageViewHolder(ImageView imageView) {
            this.mImageView = new WeakReference<>(imageView);
            this.mHashCode = imageView.hashCode();
        }

        public boolean onPreDraw() {
            Object tag;
            ImageView imageView = (ImageView) this.mImageView.get();
            if (imageView == null || (tag = imageView.getTag()) == null || !(tag instanceof ImageTag)) {
                return true;
            }
            ImageTag imageTag = (ImageTag) tag;
            if (imageTag.mWeak.get()) {
                if (!MishopWeaker.this.mMishopLruCache.contains(imageTag.mCacheKey) || imageView.getDrawable() == null) {
                    Message obtainMessage = MishopWeaker.this.mMainThreadHandler.obtainMessage(3);
                    obtainMessage.obj = imageView;
                    MishopWeaker.this.mMainThreadHandler.sendMessageDelayed(obtainMessage, (long) ((int) (Math.random() * 100.0d)));
                } else {
                    imageTag.mWeak.set(false);
                }
            }
            return true;
        }

        public int hashCode() {
            return this.mHashCode;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ImageViewHolder) || this.mHashCode != ((ImageViewHolder) obj).mHashCode) {
                return false;
            }
            return true;
        }
    }

    private static class ImageTag {
        ImageViewAction mAction;
        String mCacheKey;
        AtomicBoolean mWeak = new AtomicBoolean(false);

        public ImageTag(ImageViewAction imageViewAction, String str) {
            this.mAction = imageViewAction;
            this.mCacheKey = str;
        }
    }
}
