package com.xiaomi.smarthome.newui.wallpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.wallpaper.AnimateWallpaper;
import java.io.Closeable;
import java.io.InputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.json.JSONObject;

public class AnimateWallpaperView extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f20767a = AnimateWallpaperView.class.getCanonicalName();
    /* access modifiers changed from: private */
    public boolean b;
    private View c;
    /* access modifiers changed from: private */
    public Boolean d;
    private String e;

    public AnimateWallpaperView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AnimateWallpaperView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AnimateWallpaperView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        this.d = null;
        this.e = null;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_wallpaper_animate, this, true);
    }

    private void a(Call<LibAnimationView> call) {
        LibAnimationView libAnimationView = (LibAnimationView) findViewById(R.id.wallpaper_animate);
        if (libAnimationView != null) {
            try {
                call.a(libAnimationView);
            } catch (Exception e2) {
                Log.e(f20767a, "failed to process lib_animate_view", e2);
            }
        }
    }

    public void setAnimationBottom(final int i) {
        if (this.b) {
            a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                public void a(LibAnimationView libAnimationView) {
                    libAnimationView.setY((float) (i - libAnimationView.getHeight()));
                    ImageView imageView = (ImageView) AnimateWallpaperView.this.findViewById(R.id.wallpaper_image);
                    imageView.setY((float) (i - imageView.getHeight()));
                }
            });
        }
    }

    public float getAnimationBottom() {
        if (this.c == null) {
            this.c = (LibAnimationView) findViewById(R.id.wallpaper_animate);
        }
        if (this.c == null) {
            return 0.0f;
        }
        return this.c.getY() + ((float) this.c.getHeight());
    }

    public void setAnimationLoop(final boolean z) {
        if (this.b) {
            a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                public void a(LibAnimationView libAnimationView) {
                    libAnimationView.setLoop(z);
                }
            });
        }
    }

    public void playAnimation() {
        if (this.b) {
            a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                public void a(LibAnimationView libAnimationView) {
                    libAnimationView.playAnimation();
                }
            });
        }
    }

    public void pauseAnimation() {
        if (this.b) {
            a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                public void a(LibAnimationView libAnimationView) {
                    libAnimationView.pauseAnimation();
                }
            });
        }
    }

    public void clearAnimation() {
        super.clearAnimation();
        if (this.b) {
            a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                public void a(LibAnimationView libAnimationView) {
                    boolean unused = AnimateWallpaperView.this.b = false;
                    libAnimationView.clearAnimation();
                }
            });
        }
    }

    public void load(final Runnable runnable) {
        new Runnable() {
            public void run() {
                AnimateWallpaperView.this.a(AnimateWallpaperManager.a().g(), (Call<Boolean>) new Call<Boolean>() {
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
    public void a(LibAnimationView libAnimationView, AnimateWallpaper.Item item) {
        ImageView imageView = (ImageView) findViewById(R.id.wallpaper_image);
        imageView.setVisibility(0);
        Integer a2 = item.a((Integer) null);
        if (a2 != null) {
            imageView.setBackgroundColor(a2.intValue());
        }
        setBackgroundColor(item.b((Integer) -1).intValue());
        Bitmap b2 = item.b();
        AnimateWallpaperManager.a((View) libAnimationView, b2);
        ViewGroup.LayoutParams layoutParams = libAnimationView.getLayoutParams();
        if (layoutParams.width < 1) {
            Point point = new Point();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
            layoutParams.width = point.x;
        }
        layoutParams.height = (layoutParams.width * b2.getHeight()) / b2.getWidth();
        libAnimationView.setLayoutParams(layoutParams);
        libAnimationView.setMinimumHeight(layoutParams.height);
        setAnimationBottom(layoutParams.height);
    }

    /* access modifiers changed from: private */
    public void a(final AnimateWallpaper animateWallpaper, final Call<Boolean> call) {
        if (this.d != null) {
            if (this.d.booleanValue() || animateWallpaper.d().equals(this.e)) {
                return;
            }
            if (this.b) {
                a((Call<LibAnimationView>) new Call<LibAnimationView>() {
                    public void a(LibAnimationView libAnimationView) {
                        libAnimationView.clearAnimation();
                    }
                });
            }
        }
        this.d = true;
        this.e = animateWallpaper.d();
        this.b = false;
        ((ImageView) findViewById(R.id.wallpaper_image)).setImageDrawable((Drawable) null);
        findViewById(R.id.app_icon).setVisibility(8);
        final AnimateWallpaper.Item g = animateWallpaper.g();
        if (g.a()) {
            g.a((Call<InputStream>) new Call<InputStream>() {
                public void a(InputStream inputStream) {
                    if (inputStream == null) {
                        Log.e(AnimateWallpaperView.f20767a, "failed to load lottie file");
                        if (call != null) {
                            call.a(false);
                        }
                        Boolean unused = AnimateWallpaperView.this.d = false;
                        return;
                    }
                    try {
                        LibAnimationConfig libAnimationConfig = new LibAnimationConfig(new JSONObject(new String(IOUtils.a(inputStream))));
                        LibAnimationView libAnimationView = (LibAnimationView) AnimateWallpaperView.this.findViewById(R.id.wallpaper_animate);
                        if (libAnimationView == null) {
                            IOUtils.a((Closeable) inputStream);
                            return;
                        }
                        libAnimationView.setVisibility(0);
                        boolean unused2 = AnimateWallpaperView.this.b = true;
                        AnimateWallpaperView.this.a(libAnimationView, g);
                        libAnimationView.load(libAnimationConfig, new LibAnimationBitmapLoader() {
                            public Bitmap a(String str) {
                                return g.a(str);
                            }
                        });
                        libAnimationView.setLoop(true);
                        if (call != null) {
                            call.a(true);
                        }
                        Boolean unused3 = AnimateWallpaperView.this.d = false;
                        IOUtils.a((Closeable) inputStream);
                    } catch (Exception e) {
                        String access$200 = AnimateWallpaperView.f20767a;
                        Log.e(access$200, "failed to load lottie composition of " + animateWallpaper.d(), e);
                        if (call != null) {
                            call.a(false);
                        }
                        Boolean unused4 = AnimateWallpaperView.this.d = false;
                    } catch (Throwable th) {
                        IOUtils.a((Closeable) inputStream);
                        throw th;
                    }
                }
            }.c());
        } else {
            g.c(new Call<InputStream>() {
                public void a(InputStream inputStream) {
                    boolean z;
                    try {
                        AnimateWallpaperView.this.findViewById(R.id.wallpaper_animate).setVisibility(4);
                        AnimateWallpaperView.this.findViewById(R.id.wallpaper_image).setVisibility(4);
                        Bitmap a2 = AnimateWallpaper.a(inputStream);
                        if (a2 == null) {
                            AnimateWallpaperView.this.setBackgroundColor(g.b((Integer) -1).intValue());
                        } else {
                            AnimateWallpaperManager.a((View) AnimateWallpaperView.this, a2);
                        }
                        IOUtils.a((Closeable) inputStream);
                        z = true;
                    } catch (Exception e) {
                        String access$200 = AnimateWallpaperView.f20767a;
                        Log.e(access$200, "failed to load wallpaper " + animateWallpaper.d(), e);
                        IOUtils.a((Closeable) inputStream);
                        z = false;
                    } catch (Throwable th) {
                        IOUtils.a((Closeable) inputStream);
                        throw th;
                    }
                    if (call != null) {
                        call.a(Boolean.valueOf(z));
                    }
                    Boolean unused = AnimateWallpaperView.this.d = false;
                }
            }.c());
        }
    }

    private void a(final Runnable runnable) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                AnimateWallpaperView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                runnable.run();
            }
        });
    }
}
