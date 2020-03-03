package com.xiaomi.smarthome.newui.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;

public class SlideWallpaperView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static String f20813a = SlideWallpaperView.class.getCanonicalName();
    /* access modifiers changed from: private */
    public boolean b;
    private boolean c;

    private AnimateWallpaperManager getManager() {
        return AnimateWallpaperManager.a();
    }

    public SlideWallpaperView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlideWallpaperView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlideWallpaperView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.c = false;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_wallpaper_slider, this, true);
    }

    private <V extends View> void a(int i, Call<V> call) {
        View findViewById = findViewById(i);
        if (findViewById != null) {
            try {
                call.a(findViewById);
            } catch (Exception e) {
                Log.e(f20813a, "failed to process slide wallpaper", e);
            }
        }
    }

    public void loadAnimation(final Runnable runnable) {
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.load(runnable);
            }
        });
    }

    public void setSlideImage(final Bitmap bitmap) {
        if (bitmap != null) {
            a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
                public void a(AnimateWallpaperView animateWallpaperView) {
                    ImageView imageView = (ImageView) SlideWallpaperView.this.findViewById(R.id.wallpaper_slide_image);
                    AnimateWallpaperManager.a(imageView, bitmap);
                    imageView.setX((float) animateWallpaperView.getWidth());
                    imageView.setMinimumWidth(animateWallpaperView.getWidth());
                    imageView.setVisibility(0);
                    boolean unused = SlideWallpaperView.this.b = true;
                }
            });
        }
    }

    public void moveSliderTo(final float f) {
        if (this.b) {
            a(R.id.wallpaper_slide_image, new Call<ImageView>() {
                public void a(ImageView imageView) {
                    AnimateWallpaperView animateWallpaperView = (AnimateWallpaperView) SlideWallpaperView.this.findViewById(R.id.wallpaper_slide_main);
                    float width = (float) animateWallpaperView.getWidth();
                    float f = ((f + width) % (2.0f * width)) - width;
                    imageView.setX(f);
                    if (f < 0.0f) {
                        animateWallpaperView.setX(f + width);
                    } else {
                        animateWallpaperView.setX(f - width);
                    }
                }
            });
        }
    }

    public float getSliderX() {
        View findViewById = findViewById(R.id.wallpaper_slide_image);
        if (findViewById == null) {
            return Float.MIN_VALUE;
        }
        return findViewById.getX();
    }

    public void setAnimationBottom(final int i) {
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.setAnimationBottom(i);
            }
        });
    }

    public float getAnimationBottom() {
        AnimateWallpaperView animateWallpaperView = (AnimateWallpaperView) findViewById(R.id.wallpaper_slide_main);
        if (animateWallpaperView == null) {
            return 0.0f;
        }
        return animateWallpaperView.getAnimationBottom();
    }

    public void setAnimationLoop(final boolean z) {
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.setAnimationLoop(z);
            }
        });
    }

    public void playAnimation() {
        this.c = true;
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.playAnimation();
            }
        });
    }

    public void pauseAnimation() {
        this.c = false;
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.pauseAnimation();
            }
        });
    }

    public void clearAnimation() {
        a(R.id.wallpaper_slide_main, new Call<AnimateWallpaperView>() {
            public void a(AnimateWallpaperView animateWallpaperView) {
                animateWallpaperView.clearAnimation();
            }
        });
    }

    public boolean isPlaying() {
        return this.c;
    }
}
