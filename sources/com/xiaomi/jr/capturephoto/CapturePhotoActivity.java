package com.xiaomi.jr.capturephoto;

import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.OrientationEventListener;
import android.view.TextureView;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.supportlite.app.Activity;
import com.xiaomi.jr.capturephoto.utils.ICamera;

public class CapturePhotoActivity extends Activity implements TextureView.SurfaceTextureListener {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ENABLE_SWITCH_FRONT_BACK_CAMERA = "enable_switch_front_back_camera";
    public static final String KEY_IS_FRONT_CAMERA = "is_front_camera";
    public static final String KEY_MASK_PREVIEW_RECT = "mask_preview_rect";
    public static final String KEY_MASK_RESOURCE_ID = "mask_resource_id";
    public static final String KEY_PHOTO_HEIGHT = "photo_height";
    public static final String KEY_PHOTO_NAME = "photo_name";
    public static final String KEY_PHOTO_WIDTH = "photo_width";
    public static final String KEY_SHOW_RESULT = "show_result";
    public static final String KEY_TITLE = "title";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10346a = "CapturePhotoActivity";
    private static final String b = "description_textview";
    private boolean c;
    private String d;
    private int e;
    private int f;
    private boolean g;
    private RectF h;
    private boolean i;
    private TextureView j;
    private ICamera k;
    private OrientationEventListener l;
    /* access modifiers changed from: private */
    public int m;
    private View n;
    private ShutterView o;
    private Button p;
    private Button q;
    private Button r;

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        int i2;
        int identifier;
        TextView textView;
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.c = extras.getBoolean(KEY_IS_FRONT_CAMERA);
            this.d = extras.getString(KEY_PHOTO_NAME);
            this.e = extras.getInt(KEY_PHOTO_WIDTH);
            this.f = extras.getInt(KEY_PHOTO_HEIGHT);
            this.g = extras.getBoolean(KEY_SHOW_RESULT);
            this.h = (RectF) extras.getParcelable(KEY_MASK_PREVIEW_RECT);
            this.i = extras.getBoolean(KEY_ENABLE_SWITCH_FRONT_BACK_CAMERA);
            i2 = extras.getInt(KEY_MASK_RESOURCE_ID);
            str2 = extras.getString("title");
            str = extras.getString("description");
        } else {
            str = null;
            str2 = null;
            i2 = 0;
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        getWindow().setFlags(128, 128);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setNavigationBarColor(getResources().getColor(17170444));
        }
        setContentView(R.layout.capture_photo_activity);
        ViewStub viewStub = (ViewStub) findViewById(R.id.mask);
        if (i2 != 0) {
            viewStub.setLayoutResource(i2);
            this.n = viewStub.inflate();
            if (!(TextUtils.isEmpty(str) || (identifier = getResources().getIdentifier(b, "id", getPackageName())) == 0 || (textView = (TextView) this.n.findViewById(identifier)) == null)) {
                textView.setText(str);
            }
        }
        ((TextView) findViewById(R.id.title_textview)).setText(str2);
        this.j = (TextureView) findViewById(R.id.camera_view);
        this.j.setSurfaceTextureListener(this);
        this.j.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.f(view);
            }
        });
        this.o = (ShutterView) findViewById(R.id.shutter);
        this.p = (Button) findViewById(R.id.retake_button);
        this.q = (Button) findViewById(R.id.ok_button);
        this.r = (Button) findViewById(R.id.switch_button);
        this.p.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.e(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.d(view);
            }
        });
        this.r.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.c(view);
            }
        });
        if (this.i) {
            this.r.setVisibility(0);
        }
        findViewById(R.id.shutter).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.b(view);
            }
        });
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CapturePhotoActivity.this.a(view);
            }
        });
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        this.k.b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        a(false);
        setResult(0);
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        this.k.e();
        this.c = !this.c;
        a();
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        new Thread(new Runnable() {
            public final void run() {
                CapturePhotoActivity.this.d();
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d() {
        try {
            this.k.f10352a.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, (Camera.PictureCallback) null, new Camera.PictureCallback() {
                public final void onPictureTaken(byte[] bArr, Camera camera) {
                    CapturePhotoActivity.this.a(bArr, camera);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00aa A[Catch:{ Exception -> 0x00fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4 A[ADDED_TO_REGION, Catch:{ Exception -> 0x00fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0 A[Catch:{ Exception -> 0x00fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dd A[Catch:{ Exception -> 0x00fc }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0135  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void a(byte[] r14, android.hardware.Camera r15) {
        /*
            r13 = this;
            if (r14 != 0) goto L_0x0003
            return
        L_0x0003:
            r15 = 1
            r0 = 0
            int r1 = r14.length     // Catch:{ Exception -> 0x00fc }
            android.graphics.Bitmap r14 = android.graphics.BitmapFactory.decodeByteArray(r14, r0, r1)     // Catch:{ Exception -> 0x00fc }
            if (r14 != 0) goto L_0x0010
            r13.b()     // Catch:{ Exception -> 0x00fc }
            return
        L_0x0010:
            int r1 = r14.getWidth()     // Catch:{ Exception -> 0x00fc }
            int r9 = r14.getHeight()     // Catch:{ Exception -> 0x00fc }
            com.xiaomi.jr.capturephoto.utils.ICamera r2 = r13.k     // Catch:{ Exception -> 0x00fc }
            int r10 = r2.j()     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r2 = r13.h     // Catch:{ Exception -> 0x00fc }
            r11 = -1082130432(0xffffffffbf800000, float:-1.0)
            r12 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x008a
            com.xiaomi.jr.capturephoto.utils.ICamera r2 = r13.k     // Catch:{ Exception -> 0x00fc }
            int r2 = r2.g()     // Catch:{ Exception -> 0x00fc }
            if (r1 != r2) goto L_0x008a
            com.xiaomi.jr.capturephoto.utils.ICamera r2 = r13.k     // Catch:{ Exception -> 0x00fc }
            int r2 = r2.f()     // Catch:{ Exception -> 0x00fc }
            if (r9 != r2) goto L_0x008a
            android.graphics.Matrix r7 = new android.graphics.Matrix     // Catch:{ Exception -> 0x00fc }
            r7.<init>()     // Catch:{ Exception -> 0x00fc }
            boolean r2 = r13.c     // Catch:{ Exception -> 0x00fc }
            if (r2 == 0) goto L_0x0042
            r7.postScale(r11, r12)     // Catch:{ Exception -> 0x00fc }
        L_0x0042:
            float r2 = (float) r10     // Catch:{ Exception -> 0x00fc }
            r7.postRotate(r2)     // Catch:{ Exception -> 0x00fc }
            r3 = 0
            r4 = 0
            r8 = 1
            r2 = r14
            r5 = r1
            r6 = r9
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r3 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r3 = r3.left     // Catch:{ Exception -> 0x00fc }
            int r3 = com.xiaomi.jr.common.utils.DensityUtils.a(r13, r3)     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r4 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r4 = r4.top     // Catch:{ Exception -> 0x00fc }
            int r4 = com.xiaomi.jr.common.utils.DensityUtils.a(r13, r4)     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r5 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r5 = r5.right     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r6 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r6 = r6.left     // Catch:{ Exception -> 0x00fc }
            float r5 = r5 - r6
            int r5 = com.xiaomi.jr.common.utils.DensityUtils.a(r13, r5)     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r6 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r6 = r6.bottom     // Catch:{ Exception -> 0x00fc }
            android.graphics.RectF r7 = r13.h     // Catch:{ Exception -> 0x00fc }
            float r7 = r7.top     // Catch:{ Exception -> 0x00fc }
            float r6 = r6 - r7
            int r6 = com.xiaomi.jr.common.utils.DensityUtils.a(r13, r6)     // Catch:{ Exception -> 0x00fc }
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00fc }
            java.lang.String r4 = r13.d     // Catch:{ Exception -> 0x00fc }
            com.xiaomi.jr.common.app.PhotoManager.a(r4, r3)     // Catch:{ Exception -> 0x00fc }
            if (r2 == r3) goto L_0x008a
            if (r2 == r14) goto L_0x008a
            r2.recycle()     // Catch:{ Exception -> 0x00fc }
        L_0x008a:
            android.graphics.Matrix r7 = new android.graphics.Matrix     // Catch:{ Exception -> 0x00fc }
            r7.<init>()     // Catch:{ Exception -> 0x00fc }
            boolean r2 = r13.c     // Catch:{ Exception -> 0x00fc }
            if (r2 == 0) goto L_0x0096
            r7.postScale(r11, r12)     // Catch:{ Exception -> 0x00fc }
        L_0x0096:
            float r2 = (float) r10     // Catch:{ Exception -> 0x00fc }
            r7.postRotate(r2)     // Catch:{ Exception -> 0x00fc }
            r2 = 270(0x10e, float:3.78E-43)
            r3 = 90
            if (r10 == r3) goto L_0x00a6
            if (r10 != r2) goto L_0x00a3
            goto L_0x00a6
        L_0x00a3:
            int r4 = r13.f     // Catch:{ Exception -> 0x00fc }
            goto L_0x00a8
        L_0x00a6:
            int r4 = r13.e     // Catch:{ Exception -> 0x00fc }
        L_0x00a8:
            if (r10 == r3) goto L_0x00b0
            if (r10 != r2) goto L_0x00ad
            goto L_0x00b0
        L_0x00ad:
            int r2 = r13.e     // Catch:{ Exception -> 0x00fc }
            goto L_0x00b2
        L_0x00b0:
            int r2 = r13.f     // Catch:{ Exception -> 0x00fc }
        L_0x00b2:
            if (r4 <= 0) goto L_0x00ce
            if (r2 <= 0) goto L_0x00ce
            com.xiaomi.jr.common.utils.BitmapUtils$CropConfig r1 = com.xiaomi.jr.common.utils.BitmapUtils.a((android.graphics.Bitmap) r14, (int) r4, (int) r2)     // Catch:{ Exception -> 0x00fc }
            float r2 = r1.f10363a     // Catch:{ Exception -> 0x00fc }
            float r3 = r1.f10363a     // Catch:{ Exception -> 0x00fc }
            r7.postScale(r2, r3)     // Catch:{ Exception -> 0x00fc }
            int r2 = r1.b     // Catch:{ Exception -> 0x00fc }
            int r3 = r1.c     // Catch:{ Exception -> 0x00fc }
            int r4 = r1.d     // Catch:{ Exception -> 0x00fc }
            int r1 = r1.e     // Catch:{ Exception -> 0x00fc }
            r6 = r1
            r5 = r4
            r4 = r3
            r3 = r2
            goto L_0x00ef
        L_0x00ce:
            if (r4 <= 0) goto L_0x00dd
            float r2 = (float) r4     // Catch:{ Exception -> 0x00fc }
            float r2 = r2 * r12
            int r3 = r14.getWidth()     // Catch:{ Exception -> 0x00fc }
            float r3 = (float) r3     // Catch:{ Exception -> 0x00fc }
            float r2 = r2 / r3
            r7.postScale(r2, r2)     // Catch:{ Exception -> 0x00fc }
            goto L_0x00eb
        L_0x00dd:
            if (r2 <= 0) goto L_0x00eb
            float r2 = (float) r2     // Catch:{ Exception -> 0x00fc }
            float r2 = r2 * r12
            int r3 = r14.getHeight()     // Catch:{ Exception -> 0x00fc }
            float r3 = (float) r3     // Catch:{ Exception -> 0x00fc }
            float r2 = r2 / r3
            r7.postScale(r2, r2)     // Catch:{ Exception -> 0x00fc }
        L_0x00eb:
            r5 = r1
            r6 = r9
            r3 = 0
            r4 = 0
        L_0x00ef:
            r8 = 1
            r2 = r14
            android.graphics.Bitmap r14 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00fc }
            java.lang.String r1 = r13.d     // Catch:{ Exception -> 0x00fc }
            com.xiaomi.jr.common.app.PhotoManager.b(r1, r14)     // Catch:{ Exception -> 0x00fc }
            r14 = 1
            goto L_0x0118
        L_0x00fc:
            r14 = move-exception
            java.lang.String r1 = "CapturePhotoActivity"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "exception occurs on picture process: "
            r2.append(r3)
            java.lang.String r14 = r14.getMessage()
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            com.xiaomi.jr.common.utils.MifiLog.e(r1, r14)
            r14 = 0
        L_0x0118:
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            java.lang.String r2 = "photo_name"
            java.lang.String r3 = r13.d
            r1.putExtra(r2, r3)
            if (r14 == 0) goto L_0x0127
            r0 = -1
        L_0x0127:
            r13.setResult(r0, r1)
            boolean r14 = r13.g
            if (r14 == 0) goto L_0x0135
            r13.c()
            r13.a((boolean) r15)
            goto L_0x0138
        L_0x0135:
            r13.finish()
        L_0x0138:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.capturephoto.CapturePhotoActivity.a(byte[], android.hardware.Camera):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }

    private void a() {
        if (this.k == null) {
            this.k = new ICamera(this);
        }
        if (this.k.a(this.c) != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            this.k.a(layoutParams);
            this.j.setLayoutParams(layoutParams);
            if (this.k.b) {
                new RotateTips(this).a();
                return;
            }
            return;
        }
        Toast.makeText(this, getString(R.string.open_camera_fail), 1).show();
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        this.l = new OrientationEventListener(this, 3) {
            public void onOrientationChanged(int i) {
                if ((i >= 0 && i < 45) || (i > 315 && i <= 360)) {
                    int unused = CapturePhotoActivity.this.m = 0;
                } else if (i >= 45 && i < 135) {
                    int unused2 = CapturePhotoActivity.this.m = 90;
                } else if (i < 135 || i >= 225) {
                    int unused3 = CapturePhotoActivity.this.m = 270;
                } else {
                    int unused4 = CapturePhotoActivity.this.m = 180;
                }
            }
        };
        this.l.enable();
        super.onPostCreate(bundle);
    }

    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.l.disable();
        this.k.e();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        b();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        c();
        return false;
    }

    private void b() {
        this.k.a(this.j.getSurfaceTexture());
    }

    private void c() {
        this.k.d();
    }

    private void a(boolean z) {
        if (z) {
            if (this.n != null) {
                this.n.setVisibility(8);
            }
            this.o.setVisibility(8);
            this.p.setVisibility(0);
            this.q.setVisibility(0);
            if (this.i) {
                this.r.setVisibility(8);
                return;
            }
            return;
        }
        if (this.n != null) {
            this.n.setVisibility(0);
        }
        this.o.setVisibility(0);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        if (this.i) {
            this.r.setVisibility(0);
        }
    }
}
