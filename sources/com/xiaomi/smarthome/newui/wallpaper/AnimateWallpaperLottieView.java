package com.xiaomi.smarthome.newui.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.wallpaper.AnimateWallpaper;
import java.io.InputStream;

public class AnimateWallpaperLottieView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f20751a = AnimateWallpaperLottieView.class.getCanonicalName();
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public Boolean e;
    private String f;

    private AnimateWallpaperManager getManager() {
        return AnimateWallpaperManager.a();
    }

    public AnimateWallpaperLottieView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AnimateWallpaperLottieView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AnimateWallpaperLottieView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = null;
        this.f = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_wallpaper, this, true);
    }

    private void a(Call<LottieAnimationView> call) {
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.wallpaper_lottie);
        if (lottieAnimationView != null) {
            try {
                call.a(lottieAnimationView);
            } catch (Exception e2) {
                Log.e(f20751a, "failed to process lottie", e2);
            }
        }
    }

    public void setAnimationBottom(final int i) {
        View findViewById = findViewById(R.id.wallpaper_lottie);
        if (findViewById != null) {
            findViewById.setY((float) (i - findViewById.getHeight()));
            ImageView imageView = (ImageView) findViewById(R.id.wallpaper_image);
            imageView.setY((float) (i - imageView.getHeight()));
            if (this.b) {
                a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                    public void a(LottieAnimationView lottieAnimationView) {
                        lottieAnimationView.setY((float) (i - lottieAnimationView.getHeight()));
                        ImageView imageView = (ImageView) AnimateWallpaperLottieView.this.findViewById(R.id.wallpaper_image);
                        imageView.setY((float) (i - imageView.getHeight()));
                    }
                });
            }
        }
    }

    public void setAnimationLoop(final boolean z) {
        if (this.b) {
            a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                public void a(LottieAnimationView lottieAnimationView) {
                    lottieAnimationView.loop(z);
                }
            });
        }
    }

    public void playAnimation() {
        if (this.b) {
            a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                public void a(LottieAnimationView lottieAnimationView) {
                    if (!AnimateWallpaperLottieView.this.d) {
                        boolean unused = AnimateWallpaperLottieView.this.d = true;
                        if (AnimateWallpaperLottieView.this.c) {
                            boolean unused2 = AnimateWallpaperLottieView.this.c = false;
                            lottieAnimationView.resumeAnimation();
                            return;
                        }
                        lottieAnimationView.playAnimation();
                    }
                }
            });
        }
    }

    public void pauseAnimation() {
        if (this.b && this.d) {
            a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                public void a(LottieAnimationView lottieAnimationView) {
                    boolean unused = AnimateWallpaperLottieView.this.d = false;
                    boolean unused2 = AnimateWallpaperLottieView.this.c = true;
                    lottieAnimationView.pauseAnimation();
                }
            });
        }
    }

    public void clearAnimation() {
        if (this.b) {
            a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                public void a(LottieAnimationView lottieAnimationView) {
                    boolean unused = AnimateWallpaperLottieView.this.d = false;
                    boolean unused2 = AnimateWallpaperLottieView.this.c = false;
                    boolean unused3 = AnimateWallpaperLottieView.this.b = false;
                    lottieAnimationView.cancelAnimation();
                    lottieAnimationView.clearAnimation();
                }
            });
        }
    }

    public void load(final Runnable runnable) {
        new Runnable() {
            public void run() {
                AnimateWallpaperLottieView.this.a(AnimateWallpaperManager.a().g(), (Call<Boolean>) new Call<Boolean>() {
                    public void a(Boolean bool) {
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
            }
        }.run();
    }

    /* access modifiers changed from: private */
    public void a(final AnimateWallpaper animateWallpaper, final Call<Boolean> call) {
        final boolean z = this.e != null;
        if (z) {
            if (this.e.booleanValue() || animateWallpaper.d().equals(this.f)) {
                return;
            }
            if (this.b) {
                a((Call<LottieAnimationView>) new Call<LottieAnimationView>() {
                    public void a(LottieAnimationView lottieAnimationView) {
                        boolean unused = AnimateWallpaperLottieView.this.d = false;
                        boolean unused2 = AnimateWallpaperLottieView.this.c = false;
                        lottieAnimationView.cancelAnimation();
                    }
                });
            }
        }
        this.e = true;
        this.f = animateWallpaper.d();
        this.b = false;
        final AnimateWallpaper.Item g = animateWallpaper.g();
        if (g.a()) {
            final Call<Boolean> call2 = call;
            final AnimateWallpaper animateWallpaper2 = animateWallpaper;
            final AnimateWallpaper.Item item = g;
            g.b((Call<InputStream>) new Call<InputStream>() {
                public void a(InputStream inputStream) {
                    if (inputStream == null) {
                        Log.e(AnimateWallpaperLottieView.f20751a, "failed to load lottie file");
                        call2.a(false);
                        Boolean unused = AnimateWallpaperLottieView.this.e = false;
                        return;
                    }
                    try {
                        AnimateWallpaperLottieView.this.findViewById(R.id.wallpaper_image).setVisibility(0);
                        final LottieAnimationView lottieAnimationView = (LottieAnimationView) AnimateWallpaperLottieView.this.findViewById(R.id.wallpaper_lottie);
                        lottieAnimationView.setVisibility(0);
                        LottieComposition.Factory.fromInputStream(inputStream, new OnCompositionLoadedListener() {
                            public void onCompositionLoaded(@Nullable LottieComposition lottieComposition) {
                                if (lottieComposition == null) {
                                    String access$400 = AnimateWallpaperLottieView.f20751a;
                                    Log.e(access$400, "failed to load lottie composition of " + animateWallpaper2.d());
                                    call2.a(false);
                                    Boolean unused = AnimateWallpaperLottieView.this.e = false;
                                    return;
                                }
                                boolean unused2 = AnimateWallpaperLottieView.this.b = true;
                                AnimateWallpaperLottieView.this.a(item, lottieComposition);
                                AnonymousClass1 r3 = new Runnable() {
                                    public void run() {
                                        Bitmap b = item.b();
                                        int height = lottieAnimationView.getHeight();
                                        if (height < 1) {
                                            Point point = new Point();
                                            ((WindowManager) AnimateWallpaperLottieView.this.getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
                                            height = (point.x * b.getHeight()) / b.getWidth();
                                        }
                                        AnimateWallpaperManager.a((View) lottieAnimationView, b);
                                        lottieAnimationView.getLayoutParams().height = height;
                                        lottieAnimationView.setMinimumHeight(height);
                                        lottieAnimationView.setMaxHeight(height);
                                        AnimateWallpaperLottieView.this.setAnimationBottom(height);
                                        call2.a(true);
                                        Boolean unused = AnimateWallpaperLottieView.this.e = false;
                                    }
                                };
                                if (z) {
                                    r3.run();
                                } else {
                                    AnimateWallpaperLottieView.this.a((Runnable) r3);
                                }
                            }
                        });
                    } catch (Exception e2) {
                        String access$400 = AnimateWallpaperLottieView.f20751a;
                        Log.e(access$400, "failed to load lottie composition of " + animateWallpaper2.d(), e2);
                        call2.a(false);
                        Boolean unused2 = AnimateWallpaperLottieView.this.e = false;
                    }
                }
            }.c());
            return;
        }
        g.c(new Call<InputStream>() {
            public void a(InputStream inputStream) {
                boolean z;
                try {
                    AnimateWallpaperLottieView.this.findViewById(R.id.wallpaper_lottie).setVisibility(4);
                    AnimateWallpaperLottieView.this.findViewById(R.id.wallpaper_image).setVisibility(4);
                    Bitmap a2 = AnimateWallpaper.a(inputStream);
                    if (a2 == null) {
                        AnimateWallpaperLottieView.this.setBackgroundColor(g.b((Integer) -1).intValue());
                    } else {
                        AnimateWallpaperManager.a((View) AnimateWallpaperLottieView.this, a2);
                    }
                    z = true;
                } catch (Exception e) {
                    String access$400 = AnimateWallpaperLottieView.f20751a;
                    Log.e(access$400, "failed to load wallpaper " + animateWallpaper.d(), e);
                    z = false;
                }
                call.a(Boolean.valueOf(z));
                Boolean unused = AnimateWallpaperLottieView.this.e = false;
            }
        }.c());
    }

    /* access modifiers changed from: private */
    public void a(final Runnable runnable) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AnimateWallpaperLottieView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                runnable.run();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final AnimateWallpaper.Item item, LottieComposition lottieComposition) {
        if (lottieComposition != null) {
            LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.wallpaper_lottie);
            lottieAnimationView.setComposition(lottieComposition);
            lottieAnimationView.setImageAssetDelegate(new ImageAssetDelegate() {
                public Bitmap fetchBitmap(LottieImageAsset lottieImageAsset) {
                    return item.b(lottieImageAsset.getFileName());
                }
            });
            lottieAnimationView.loop(true);
        }
        Integer a2 = item.a((Integer) null);
        if (a2 != null) {
            ((ImageView) findViewById(R.id.wallpaper_image)).setBackgroundColor(a2.intValue());
        }
        setBackgroundColor(item.b((Integer) -1).intValue());
    }
}
