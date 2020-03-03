package com.xiaomi.smarthome.library.common.widget.crop;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ImageUtils;
import com.xiaomi.smarthome.library.common.widget.crop.BitmapManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class CropImageActivity extends BaseActivity {
    public static final String ACTION_INLINE_DATA = "inline-data";
    public static final String ASPECT_X = "aspectX";
    public static final String ASPECT_Y = "aspectY";
    public static final int CANNOT_STAT_ERROR = -2;
    public static final String CIRCLE_CROP = "circleCrop";
    public static final String IMAGE_PATH = "image-path";
    public static final int NO_STORAGE_ERROR = -1;
    public static final String ORIENTATION_IN_DEGREES = "orientation_in_degrees";
    public static final String OUTPUT_X = "outputX";
    public static final String OUTPUT_Y = "outputY";
    public static final String RETURN_DATA = "return-data";
    public static final String RETURN_DATA_AS_BITMAP = "data";
    public static final String SCALE = "scale";
    public static final String SCALE_UP_IF_NEEDED = "scaleUpIfNeeded";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18995a = "CropImage";
    final int IMAGE_MAX_SIZE = 1024;
    private Bitmap.CompressFormat b = Bitmap.CompressFormat.JPEG;
    private Uri c = null;
    /* access modifiers changed from: private */
    public boolean d = true;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public final Handler f = new Handler();
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public int h;
    private int i;
    private int j;
    private boolean k;
    /* access modifiers changed from: private */
    public CropImageView l;
    private ContentResolver m;
    HighlightView mCrop;
    Runnable mRunFaceDetection = new Runnable() {

        /* renamed from: a  reason: collision with root package name */
        float f19001a = 1.0f;
        Matrix b;
        FaceDetector.Face[] c = new FaceDetector.Face[3];
        int d;

        /* access modifiers changed from: private */
        public void a(FaceDetector.Face face) {
            PointF pointF = new PointF();
            face.getMidPoint(pointF);
            pointF.x *= this.f19001a;
            pointF.y *= this.f19001a;
            HighlightView highlightView = new HighlightView(CropImageActivity.this.l);
            Rect rect = new Rect(0, 0, CropImageActivity.this.n.getWidth(), CropImageActivity.this.n.getHeight());
            float f = (float) ((int) pointF.x);
            float f2 = (float) ((int) pointF.y);
            RectF rectF = new RectF(f, f2, f, f2);
            float f3 = (float) (-(((int) (face.eyesDistance() * this.f19001a)) * 2));
            rectF.inset(f3, f3);
            if (rectF.left < 0.0f) {
                rectF.inset(-rectF.left, -rectF.left);
            }
            if (rectF.top < 0.0f) {
                rectF.inset(-rectF.top, -rectF.top);
            }
            if (rectF.right > ((float) rect.right)) {
                rectF.inset(rectF.right - ((float) rect.right), rectF.right - ((float) rect.right));
            }
            if (rectF.bottom > ((float) rect.bottom)) {
                rectF.inset(rectF.bottom - ((float) rect.bottom), rectF.bottom - ((float) rect.bottom));
            }
            highlightView.a(this.b, rect, rectF, CropImageActivity.this.e, (CropImageActivity.this.g == 0 || CropImageActivity.this.h == 0) ? false : true);
            CropImageActivity.this.l.add(highlightView);
        }

        /* access modifiers changed from: private */
        public void a() {
            int i;
            HighlightView highlightView = new HighlightView(CropImageActivity.this.l);
            int width = CropImageActivity.this.n.getWidth();
            int height = CropImageActivity.this.n.getHeight();
            Rect rect = new Rect(0, 0, width, height);
            int min = (Math.min(width, height) * 2) / 3;
            if (CropImageActivity.this.g == 0 || CropImageActivity.this.h == 0) {
                i = min;
            } else if (CropImageActivity.this.g > CropImageActivity.this.h) {
                i = (CropImageActivity.this.h * min) / CropImageActivity.this.g;
            } else {
                i = min;
                min = (CropImageActivity.this.g * min) / CropImageActivity.this.h;
            }
            int i2 = (width - min) / 2;
            int i3 = (height - i) / 2;
            highlightView.a(this.b, rect, new RectF((float) i2, (float) i3, (float) (i2 + min), (float) (i3 + i)), CropImageActivity.this.e, (CropImageActivity.this.g == 0 || CropImageActivity.this.h == 0) ? false : true);
            CropImageActivity.this.l.mHighlightViews.clear();
            CropImageActivity.this.l.add(highlightView);
        }

        private Bitmap b() {
            if (CropImageActivity.this.n == null) {
                return null;
            }
            if (CropImageActivity.this.n.getWidth() > 256) {
                this.f19001a = 256.0f / ((float) CropImageActivity.this.n.getWidth());
            }
            Matrix matrix = new Matrix();
            matrix.setScale(this.f19001a, this.f19001a);
            return Bitmap.createBitmap(CropImageActivity.this.n, 0, 0, CropImageActivity.this.n.getWidth(), CropImageActivity.this.n.getHeight(), matrix, true);
        }

        public void run() {
            this.b = CropImageActivity.this.l.getImageMatrix();
            Bitmap b2 = b();
            this.f19001a = 1.0f / this.f19001a;
            if (b2 != null && CropImageActivity.this.d) {
                this.d = new FaceDetector(b2.getWidth(), b2.getHeight(), this.c.length).findFaces(b2, this.c);
            }
            if (!(b2 == null || b2 == CropImageActivity.this.n)) {
                b2.recycle();
            }
            CropImageActivity.this.f.post(new Runnable() {
                public void run() {
                    CropImageActivity.this.mWaitingToPick = AnonymousClass5.this.d > 1;
                    if (AnonymousClass5.this.d > 0) {
                        for (int i = 0; i < AnonymousClass5.this.d; i++) {
                            AnonymousClass5.this.a(AnonymousClass5.this.c[i]);
                        }
                    } else {
                        AnonymousClass5.this.a();
                    }
                    CropImageActivity.this.l.invalidate();
                    if (CropImageActivity.this.l.mHighlightViews.size() == 1) {
                        CropImageActivity.this.mCrop = CropImageActivity.this.l.mHighlightViews.get(0);
                        CropImageActivity.this.mCrop.a(true);
                    }
                    if (AnonymousClass5.this.d > 1) {
                        Toast.makeText(CropImageActivity.this, "Multi face crop help", 0).show();
                    }
                }
            });
        }
    };
    boolean mSaving;
    protected TextTitleBar mTitleBar;
    boolean mWaitingToPick;
    /* access modifiers changed from: private */
    public Bitmap n;
    private String o;
    private Uri p;
    private boolean q = true;
    private final BitmapManager.ThreadSet r = new BitmapManager.ThreadSet();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = getContentResolver();
        setContentView(R.layout.cropimage);
        this.mTitleBar = (TextTitleBar) findViewById(R.id.title_bar);
        this.l = (CropImageView) findViewById(R.id.image);
        showStorageToast(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.getString(CIRCLE_CROP) != null) {
                if (Build.VERSION.SDK_INT > 11) {
                    this.l.setLayerType(1, (Paint) null);
                }
                this.e = true;
                this.g = 1;
                this.h = 1;
            }
            this.o = extras.getString(IMAGE_PATH);
            this.p = intent.getData();
            if (TextUtils.isEmpty(this.o) && this.p != null) {
                this.o = this.p.getPath();
            }
            this.c = (Uri) extras.getParcelable(AgentOptions.k);
            this.n = a(this.p);
            if (!extras.containsKey(ASPECT_X) || !(extras.get(ASPECT_X) instanceof Integer)) {
                throw new IllegalArgumentException("aspect_x must be integer");
            }
            this.g = extras.getInt(ASPECT_X);
            if (!extras.containsKey(ASPECT_Y) || !(extras.get(ASPECT_Y) instanceof Integer)) {
                throw new IllegalArgumentException("aspect_y must be integer");
            }
            this.h = extras.getInt(ASPECT_Y);
            this.i = extras.getInt(OUTPUT_X);
            this.j = extras.getInt(OUTPUT_Y);
            this.k = extras.getBoolean("scale", true);
            this.q = extras.getBoolean(SCALE_UP_IF_NEEDED, true);
        }
        if (this.n == null) {
            finish();
            return;
        }
        this.mTitleBar.setTitle((int) R.string.crop_image_title);
        this.mTitleBar.getLeftView().setText(R.string.cancel);
        this.mTitleBar.getLeftView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CropImageActivity.this.finish();
            }
        });
        this.mTitleBar.getRightView().setText(R.string.crop_image_next);
        this.mTitleBar.getRightView().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CropImageActivity.this.b();
                } catch (Exception unused) {
                    CropImageActivity.this.finish();
                }
            }
        });
        a();
    }

    private Bitmap a(Uri uri) {
        try {
            InputStream openInputStream = this.m.openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
            openInputStream.close();
            if (options.outHeight > 1024 || options.outWidth > 1024) {
                double max = (double) Math.max(options.outHeight, options.outWidth);
                Double.isNaN(max);
                i2 = (int) Math.pow(2.0d, (double) ((int) Math.round(Math.log(1024.0d / max) / Math.log(0.5d))));
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i2;
            InputStream openInputStream2 = this.m.openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, (Rect) null, options2);
            openInputStream2.close();
            return ImageUtils.a(decodeStream, this.o);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void a() {
        if (!isFinishing()) {
            this.l.setImageBitmapResetBase(this.n, true);
            CropUtils.a((Activity) this, (String) null, getResources().getString(R.string.please_wait), (Runnable) new Runnable() {
                public void run() {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final Bitmap access$100 = CropImageActivity.this.n;
                    CropImageActivity.this.f.post(new Runnable() {
                        public void run() {
                            if (!(access$100 == CropImageActivity.this.n || access$100 == null)) {
                                CropImageActivity.this.l.setImageBitmapResetBase(access$100, true);
                                CropImageActivity.this.n.recycle();
                                Bitmap unused = CropImageActivity.this.n = access$100;
                            }
                            if (CropImageActivity.this.l.getScale() == 1.0f) {
                                CropImageActivity.this.l.center(true, true);
                            }
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                        CropImageActivity.this.mRunFaceDetection.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, this.f);
        }
    }

    /* access modifiers changed from: private */
    public void b() throws Exception {
        final Bitmap bitmap;
        if (!this.mSaving && this.mCrop != null) {
            this.mSaving = true;
            Rect c2 = this.mCrop.c();
            int width = c2.width();
            int height = c2.height();
            try {
                Bitmap createBitmap = Bitmap.createBitmap(width, height, this.e ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                if (createBitmap != null) {
                    new Canvas(createBitmap).drawBitmap(this.n, c2, new Rect(0, 0, width, height), (Paint) null);
                    if (this.e) {
                        Canvas canvas = new Canvas(createBitmap);
                        Path path = new Path();
                        float f2 = ((float) width) / 2.0f;
                        path.addCircle(f2, ((float) height) / 2.0f, f2, Path.Direction.CW);
                        canvas.clipPath(path, Region.Op.DIFFERENCE);
                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    }
                    if (this.i == 0 || this.j == 0) {
                        bitmap = createBitmap;
                    } else if (this.k) {
                        bitmap = CropUtils.a(new Matrix(), createBitmap, this.i, this.j, this.q);
                        if (createBitmap != bitmap) {
                            createBitmap.recycle();
                        }
                    } else {
                        bitmap = Bitmap.createBitmap(this.i, this.j, Bitmap.Config.RGB_565);
                        Canvas canvas2 = new Canvas(bitmap);
                        Rect c3 = this.mCrop.c();
                        Rect rect = new Rect(0, 0, this.i, this.j);
                        int width2 = (c3.width() - rect.width()) / 2;
                        int height2 = (c3.height() - rect.height()) / 2;
                        c3.inset(Math.max(0, width2), Math.max(0, height2));
                        rect.inset(Math.max(0, -width2), Math.max(0, -height2));
                        canvas2.drawBitmap(this.n, c3, rect, (Paint) null);
                        createBitmap.recycle();
                    }
                    Bundle extras = getIntent().getExtras();
                    if (extras == null || (extras.getParcelable("data") == null && !extras.getBoolean(RETURN_DATA))) {
                        CropUtils.a((Activity) this, (String) null, getString(R.string.saving_image), (Runnable) new Runnable() {
                            public void run() {
                                CropImageActivity.this.a(bitmap);
                            }
                        }, this.f);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", bitmap);
                    setResult(-1, new Intent().setAction(ACTION_INLINE_DATA).putExtras(bundle));
                    finish();
                }
            } catch (Exception e2) {
                throw e2;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Bitmap bitmap) {
        if (this.c != null) {
            OutputStream outputStream = null;
            try {
                OutputStream openOutputStream = this.m.openOutputStream(this.c);
                if (openOutputStream != null) {
                    try {
                        bitmap.compress(this.b, 90, openOutputStream);
                    } catch (IOException e2) {
                        e = e2;
                        outputStream = openOutputStream;
                    } catch (Throwable th) {
                        th = th;
                        outputStream = openOutputStream;
                        CropUtils.a(outputStream);
                        throw th;
                    }
                }
                CropUtils.a(openOutputStream);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(this.c.toString());
                intent.putExtras(bundle);
                intent.putExtra(IMAGE_PATH, this.o);
                setResult(-1, intent);
            } catch (IOException e3) {
                e = e3;
                try {
                    Log.e(f18995a, "Cannot open file: " + this.c, e);
                    setResult(0);
                    finish();
                    CropUtils.a(outputStream);
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    CropUtils.a(outputStream);
                    throw th;
                }
            }
        } else {
            Log.e(f18995a, "not defined image url");
        }
        bitmap.recycle();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        BitmapManager.b().b(this.r);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.n != null) {
            this.n.recycle();
        }
    }

    public static void showStorageToast(Activity activity) {
        showStorageToast(activity, calculatePicturesRemaining(activity));
    }

    public static void showStorageToast(Activity activity, int i2) {
        String str;
        if (i2 == -1) {
            str = Environment.getExternalStorageState().equals("checking") ? activity.getString(R.string.preparing_card) : activity.getString(R.string.no_storage_card);
        } else {
            str = i2 < 1 ? activity.getString(R.string.not_enough_space) : null;
        }
        if (str != null) {
            Toast.makeText(activity, str, 0).show();
        }
    }

    public static int calculatePicturesRemaining(Activity activity) {
        String str;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                str = Environment.getExternalStorageDirectory().toString();
            } else {
                str = activity.getFilesDir().toString();
            }
            StatFs statFs = new StatFs(str);
            return (int) ((((float) statFs.getAvailableBlocks()) * ((float) statFs.getBlockSize())) / 400000.0f);
        } catch (Exception unused) {
            return -2;
        }
    }
}
