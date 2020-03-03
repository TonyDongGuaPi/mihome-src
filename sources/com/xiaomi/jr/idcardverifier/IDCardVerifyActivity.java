package com.xiaomi.jr.idcardverifier;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.megvii.idcardquality.IDCardQualityAssessment;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import com.miui.supportlite.app.Activity;
import com.miui.supportlite.app.AlertDialog;
import com.miui.supportlite.app.ProgressDialog;
import com.xiaomi.jr.capturephoto.ShutterView;
import com.xiaomi.jr.capturephoto.utils.ICamera;
import com.xiaomi.jr.ciphersuite.AESUtils;
import com.xiaomi.jr.ciphersuite.RSAUtils;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.BitmapUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.idcardverifier.IDCardVerifyActivity;
import com.xiaomi.jr.idcardverifier.animator.TranslateAnimator;
import com.xiaomi.jr.idcardverifier.http.VerifyHttpManager;
import com.xiaomi.jr.idcardverifier.http.VerifyResponse;
import com.xiaomi.jr.idcardverifier.utils.RotatorUtil;
import com.xiaomi.jr.idcardverifier.utils.VerifyConstants;
import com.xiaomi.jr.idcardverifier.utils.VerifyStatUtils;
import com.xiaomi.jr.idcardverifier.utils.VerifyUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

public class IDCardVerifyActivity extends Activity implements Camera.PreviewCallback, TextureView.SurfaceTextureListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10854a = "IDCardVerifyActivity";
    private static final int b = 100;
    private static final int c = 101;
    private static final int d = 102;
    private static final int e = 20000;
    private static final int f = 500;
    private static final int g = 600;
    private static final int h = 100;
    private static final Executor i = Executors.newSingleThreadExecutor();
    private IDCardQualityResult.IDCardFailedType A;
    /* access modifiers changed from: private */
    public State B;
    /* access modifiers changed from: private */
    public QualityAssessmentThread C;
    private boolean D;
    private boolean E;
    private Bitmap F;
    private Bitmap G;
    private Bitmap H;
    private Bitmap I;
    private TranslateAnimator J;
    private String K;
    private String L;
    private String M;
    /* access modifiers changed from: private */
    public String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private String R;
    private String S;
    private boolean T;
    private Call U;
    private Call V;
    private Call W;
    /* access modifiers changed from: private */
    public int X = 600;
    /* access modifiers changed from: private */
    public Handler Y = new Handler();
    private Runnable Z = new Runnable() {
        public final void run() {
            IDCardVerifyActivity.this.g();
        }
    };
    /* access modifiers changed from: private */
    public ICamera j;
    /* access modifiers changed from: private */
    public TextureView k;
    /* access modifiers changed from: private */
    public ImageView l;
    /* access modifiers changed from: private */
    public PreviewMaskResultView m;
    /* access modifiers changed from: private */
    public TextView n;
    /* access modifiers changed from: private */
    public ImageView o;
    /* access modifiers changed from: private */
    public TextView p;
    /* access modifiers changed from: private */
    public TextView q;
    private TextView r;
    /* access modifiers changed from: private */
    public RelativeLayout s;
    private ImageView t;
    private LinearLayout u;
    private Button v;
    private Button w;
    /* access modifiers changed from: private */
    public ShutterView x;
    private ProgressDialog y;
    /* access modifiers changed from: private */
    public RectF z;

    private enum PreviewDataType {
        DATA,
        CMD_QUIT
    }

    public enum State {
        SCAN_FRONT_SIDE_ONGOING,
        SCAN_BACK_SIDE_ONGOING,
        UPLOAD_SCAN_FRONT_SIDE_ONGOING,
        UPLOAD_SCAN_BACK_SIDE_ONGOING,
        UPLOAD_SCAN_FRONT_SIDE_SUCCESS,
        UPLOAD_SCAN_BACK_SIDE_SUCCESS,
        PICK_FRONT_SIDE_PREVIEW,
        PICK_BACK_SIDE_PREVIEW,
        UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING,
        CAPTURE_FRONT_SIDE_ONGOING,
        CAPTURE_BACK_SIDE_ONGOING,
        CAPTURE_FRONT_SIDE_COMPLETE,
        CAPTURE_BACK_SIDE_COMPLETE,
        UPLOAD_CAPTURE_FRONT_SIDE_ONGOING,
        UPLOAD_CAPTURE_BACK_SIDE_ONGOING,
        UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS,
        UPLOAD_CAPTURE_BACK_SIDE_SUCCESS,
        VERIFY_ONGOING
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private class PreviewData {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f10862a;
        public PreviewDataType b;

        public PreviewData(byte[] bArr, PreviewDataType previewDataType) {
            this.f10862a = bArr;
            this.b = previewDataType;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            setResult(10);
            finish();
            return;
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        getWindow().setFlags(128, 128);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setNavigationBarColor(getResources().getColor(17170444));
            getWindow().getDecorView().setSystemUiVisibility(1792);
            getWindow().setNavigationBarColor(0);
        }
        boolean z2 = true;
        if (!a()) {
            VerifyUtils.a((Context) this, R.string.argument_illegal_waring);
            z2 = false;
        }
        this.C = new QualityAssessmentThread();
        if (!this.C.a()) {
            VerifyUtils.a((Context) this, R.string.detector_init_fail);
            z2 = false;
        }
        if (!z2) {
            setResult(10);
            finish();
            return;
        }
        i.execute(this.C);
        setContentView(R.layout.activity_id_card_verify);
        b();
        this.J = new TranslateAnimator(this);
        this.j = new ICamera(this);
        b(State.SCAN_FRONT_SIDE_ONGOING);
    }

    private boolean a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.K = intent.getStringExtra("partnerId");
            this.R = intent.getStringExtra("sign");
            this.M = intent.getStringExtra(VerifyConstants.j);
            this.S = intent.getStringExtra(VerifyConstants.r);
            if (!TextUtils.isEmpty(this.K) && !TextUtils.isEmpty(this.R) && !TextUtils.isEmpty(this.M) && !TextUtils.isEmpty(this.S)) {
                this.L = intent.getStringExtra(VerifyConstants.i);
                this.O = intent.getBooleanExtra(VerifyConstants.k, false);
                this.N = intent.getStringExtra("processId");
                this.P = intent.getBooleanExtra(VerifyConstants.o, false);
                this.Q = intent.getBooleanExtra(VerifyConstants.p, false);
                int intExtra = intent.getIntExtra(VerifyConstants.m, 0);
                if (intExtra <= 0) {
                    return true;
                }
                this.X = intExtra;
                return true;
            }
        }
        return false;
    }

    private void b() {
        this.k = (TextureView) findViewById(R.id.textureview);
        this.k.setSurfaceTextureListener(this);
        this.k.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.f(view);
            }
        });
        this.l = (ImageView) findViewById(R.id.mask_border);
        this.m = (PreviewMaskResultView) findViewById(R.id.preview_mask_result_view);
        this.n = (TextView) findViewById(R.id.scan_tips_textview);
        this.o = (ImageView) findViewById(R.id.scan_tips_imageview);
        this.p = (TextView) findViewById(R.id.scan_state_textview);
        this.q = (TextView) findViewById(R.id.pick_from_album_textview);
        this.r = (TextView) findViewById(R.id.scan_description_textview);
        this.s = (RelativeLayout) findViewById(R.id.scan_tips_layout);
        this.t = (ImageView) findViewById(R.id.pick_preview_imageview);
        this.u = (LinearLayout) findViewById(R.id.bottom_button_layout);
        this.v = (Button) findViewById(R.id.bottom_left_button);
        this.w = (Button) findViewById(R.id.bottom_right_button);
        this.x = (ShutterView) findViewById(R.id.shutter_view);
        this.x.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.e(view);
            }
        });
        this.v.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.d(view);
            }
        });
        this.w.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.c(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.b(view);
            }
        });
        this.l.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                IDCardVerifyActivity.this.a(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
        findViewById(R.id.close_imageview).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                IDCardVerifyActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        this.j.b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        new CapturePhotoThread().start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (this.B == State.PICK_FRONT_SIDE_PREVIEW) {
            a(IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
            VerifyStatUtils.a(R.string.stat_event_retry_pick_front_side_click);
        } else if (this.B == State.PICK_BACK_SIDE_PREVIEW) {
            a(IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            VerifyStatUtils.a(R.string.stat_event_retry_pick_back_side_click);
        } else if (this.B == State.CAPTURE_FRONT_SIDE_COMPLETE) {
            b(this.I);
            b(State.CAPTURE_FRONT_SIDE_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_retry_capture_photo_click, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        } else if (this.B == State.CAPTURE_BACK_SIDE_COMPLETE) {
            b(this.I);
            b(State.CAPTURE_BACK_SIDE_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_retry_capture_photo_click, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.B == State.PICK_FRONT_SIDE_PREVIEW) {
            a(IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            VerifyStatUtils.a(R.string.stat_event_pick_back_side_click);
        } else if (this.B == State.PICK_BACK_SIDE_PREVIEW) {
            b(State.UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_upload_photo_click, State.PICK_BACK_SIDE_PREVIEW, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            this.Y.postDelayed(new Runnable() {
                public final void run() {
                    IDCardVerifyActivity.this.n();
                }
            }, 500);
        } else if (this.B == State.CAPTURE_FRONT_SIDE_COMPLETE || this.B == State.CAPTURE_BACK_SIDE_COMPLETE) {
            boolean z2 = this.B == State.CAPTURE_FRONT_SIDE_COMPLETE;
            b(z2 ? State.UPLOAD_CAPTURE_FRONT_SIDE_ONGOING : State.UPLOAD_CAPTURE_BACK_SIDE_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_upload_photo_click, z2 ? State.CAPTURE_FRONT_SIDE_COMPLETE : State.CAPTURE_BACK_SIDE_COMPLETE, z2 ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            this.Y.postDelayed(new Runnable() {
                public final void run() {
                    IDCardVerifyActivity.this.m();
                }
            }, 500);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void n() {
        if (this.F != null && !this.F.isRecycled()) {
            a(this.F, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        } else if (this.G != null && !this.G.isRecycled()) {
            a(this.G, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void m() {
        a(this.I, this.B == State.UPLOAD_CAPTURE_FRONT_SIDE_ONGOING ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            a(IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
            VerifyStatUtils.a(R.string.stat_event_pick_from_album_click, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
            a(IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            VerifyStatUtils.a(R.string.stat_event_pick_from_album_click, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.z == null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.id_card_mask_border_width);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.id_card_mask_border_corner_radius);
            int color = getResources().getColor(R.color.id_card_mask_color);
            this.z = new RectF((float) (i2 + dimensionPixelSize), (float) (i3 + dimensionPixelSize), (float) (i4 - dimensionPixelSize), (float) (i5 - dimensionPixelSize));
            this.m.setMaskRect(this.z);
            this.m.setMaskBorderCornerRadius(dimensionPixelSize2);
            this.m.setMaskColor(color);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        VerifyStatUtils.b(R.string.stat_event_close_click, this.B);
        setResult(-1);
        finish();
    }

    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void b(State state) {
        if (state == State.SCAN_FRONT_SIDE_ONGOING) {
            this.o.setVisibility(8);
            this.u.setVisibility(8);
            this.t.setVisibility(8);
            this.n.setText(R.string.scan_id_card_front_side);
            this.p.setText((CharSequence) null);
            this.s.setVisibility(0);
            this.n.setVisibility(0);
            this.r.setVisibility(0);
            this.l.setImageResource(R.drawable.id_card_front_mask_border);
            h();
            this.Y.postDelayed(new Runnable() {
                public final void run() {
                    IDCardVerifyActivity.this.l();
                }
            }, 100);
            if (!TextUtils.isEmpty(this.N)) {
                this.q.setVisibility(0);
                d();
            } else {
                this.q.setVisibility(8);
                j();
            }
        } else if (state == State.UPLOAD_SCAN_FRONT_SIDE_ONGOING || state == State.UPLOAD_SCAN_BACK_SIDE_ONGOING) {
            this.x.setVisibility(8);
            this.q.setVisibility(8);
            this.p.setText(R.string.uploading);
            i();
            f();
        } else if (state == State.UPLOAD_CAPTURE_FRONT_SIDE_ONGOING || state == State.UPLOAD_CAPTURE_BACK_SIDE_ONGOING) {
            this.u.setVisibility(8);
            this.x.setVisibility(8);
            this.q.setVisibility(8);
            this.p.setText(R.string.uploading);
        } else if (state == State.UPLOAD_SCAN_FRONT_SIDE_SUCCESS || state == State.UPLOAD_SCAN_BACK_SIDE_SUCCESS || state == State.UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS || state == State.UPLOAD_CAPTURE_BACK_SIDE_SUCCESS) {
            this.n.setVisibility(8);
            this.o.setVisibility(0);
            this.o.setImageResource(R.drawable.id_card_upload_success);
            this.p.setText(R.string.upload_success);
        } else if (state == State.SCAN_BACK_SIDE_ONGOING) {
            this.J.a((TranslateAnimator.TranslateAnimatorListener) new TranslateAnimator.TranslateAnimatorListener() {
                public void b() {
                }

                public void a() {
                    IDCardVerifyActivity.this.o.setVisibility(8);
                    IDCardVerifyActivity.this.n.setText(R.string.scan_id_card_back_side);
                    IDCardVerifyActivity.this.p.setText((CharSequence) null);
                    IDCardVerifyActivity.this.n.setVisibility(0);
                    IDCardVerifyActivity.this.s.setVisibility(0);
                    IDCardVerifyActivity.this.q.setVisibility(0);
                    IDCardVerifyActivity.this.l.setImageResource(R.drawable.id_card_back_mask_border);
                    IDCardVerifyActivity.this.h();
                    IDCardVerifyActivity.this.Y.postDelayed(new Runnable() {
                        public final void run() {
                            IDCardVerifyActivity.AnonymousClass1.this.c();
                        }
                    }, 100);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void c() {
                    IDCardVerifyActivity.this.m.setResultImage((Bitmap) null);
                    IDCardVerifyActivity.this.C.a(IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
                    IDCardVerifyActivity.this.e();
                    VerifyStatUtils.a(R.string.stat_event_begin_scan, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
                    VerifyStatUtils.a(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
                }
            });
            this.J.a((View) this.s);
        } else if (state == State.VERIFY_ONGOING) {
            this.J.a((TranslateAnimator.TranslateAnimatorListener) new TranslateAnimator.TranslateAnimatorListener() {
                public void a() {
                    IDCardVerifyActivity.this.p.setText(R.string.verify_ongoing);
                    IDCardVerifyActivity.this.o.setImageResource(R.drawable.id_card_verify_ongoing);
                }

                public void b() {
                    IDCardVerifyActivity.this.k();
                }
            });
            this.J.a((View) this.s);
        } else if (state == State.PICK_FRONT_SIDE_PREVIEW) {
            this.s.setVisibility(8);
            this.t.setVisibility(0);
            this.u.setVisibility(0);
            this.v.setText(R.string.retry_pick_id_card_front_side);
            this.w.setText(R.string.pick_id_card_back_side);
            this.C.b();
        } else if (state == State.PICK_BACK_SIDE_PREVIEW) {
            this.s.setVisibility(8);
            this.t.setVisibility(0);
            this.u.setVisibility(0);
            this.v.setText(R.string.retry_pick_id_card_back_side);
            this.w.setText(R.string.upload_photo);
            this.C.b();
        } else if (state == State.CAPTURE_FRONT_SIDE_ONGOING) {
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.u.setVisibility(8);
            this.n.setText(R.string.capture_id_card_front_side);
            this.p.setText((CharSequence) null);
            this.x.setVisibility(0);
            this.l.setImageResource(R.drawable.id_card_capture_mask_border);
            h();
            this.C.b();
        } else if (state == State.CAPTURE_BACK_SIDE_ONGOING) {
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.u.setVisibility(8);
            if (this.B == State.UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS) {
                this.J.a((TranslateAnimator.TranslateAnimatorListener) new TranslateAnimator.TranslateAnimatorListener() {
                    public void b() {
                    }

                    public void a() {
                        IDCardVerifyActivity.this.o.setVisibility(8);
                        IDCardVerifyActivity.this.q.setVisibility(8);
                        IDCardVerifyActivity.this.n.setText(R.string.capture_id_card_back_side);
                        IDCardVerifyActivity.this.p.setText((CharSequence) null);
                        IDCardVerifyActivity.this.n.setVisibility(0);
                        IDCardVerifyActivity.this.x.setVisibility(0);
                        IDCardVerifyActivity.this.h();
                    }
                });
                this.J.a((View) this.s);
            } else {
                this.o.setVisibility(8);
                this.q.setVisibility(8);
                this.n.setText(R.string.capture_id_card_back_side);
                this.p.setText((CharSequence) null);
                this.n.setVisibility(0);
                this.x.setVisibility(0);
                h();
            }
            this.C.b();
        } else if (state == State.CAPTURE_FRONT_SIDE_COMPLETE || state == State.CAPTURE_BACK_SIDE_COMPLETE) {
            this.x.setVisibility(8);
            this.u.setVisibility(0);
            this.p.setText(R.string.capture_complete);
            this.v.setText(R.string.id_card_retake);
            this.w.setText(R.string.upload_photo);
            i();
        } else if (state == State.UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING) {
            this.u.setVisibility(8);
            this.y = new ProgressDialog();
            this.y.a((CharSequence) getString(R.string.upload_pick_photo_ongoing));
            this.y.setCancelable(false);
            Utils.a((DialogFragment) this.y, getSupportFragmentManager(), "progress dialog");
        }
        this.B = state;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.m.setResultImage((Bitmap) null);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING || this.B == State.SCAN_BACK_SIDE_ONGOING) {
            if (!this.j.a()) {
                if (this.j.a(false) != null) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
                    this.j.a(layoutParams);
                    this.k.setLayoutParams(layoutParams);
                    c();
                } else {
                    Toast.makeText(this, getString(R.string.open_camera_fail), 1).show();
                    return;
                }
            }
            h();
            if (!TextUtils.isEmpty(this.N)) {
                VerifyStatUtils.a(VerifyStatUtils.b(R.string.stat_event_scan_success, this.B == State.SCAN_FRONT_SIDE_ONGOING ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            VerifyStatUtils.c(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT));
        } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
            VerifyStatUtils.c(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
        }
        this.j.e();
        return true;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr != null) {
            this.C.a(new PreviewData(bArr, PreviewDataType.DATA));
        }
    }

    private void c() {
        if (this.T) {
            return;
        }
        if (this.j.h() != this.j.g() || this.j.i() != this.j.f()) {
            HashMap hashMap = new HashMap();
            hashMap.put("screenWidth", String.valueOf(this.j.f()));
            hashMap.put("screenHeight", String.valueOf(this.j.g()));
            hashMap.put("previewWidth", String.valueOf(this.j.h()));
            hashMap.put("previewHeight", String.valueOf(this.j.i()));
            VerifyStatUtils.a(R.string.stat_event_unmatched_preview_size, (Map<String, String>) hashMap);
            this.T = true;
        }
    }

    /* access modifiers changed from: private */
    public void a(IDCardQualityResult iDCardQualityResult) {
        if (!this.D && !this.E) {
            this.C.b();
            State state = null;
            this.A = null;
            if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
                state = State.UPLOAD_SCAN_FRONT_SIDE_ONGOING;
                VerifyStatUtils.b(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT));
            } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
                state = State.UPLOAD_SCAN_BACK_SIDE_ONGOING;
                VerifyStatUtils.b(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
            }
            if (state != null) {
                Bitmap b2 = iDCardQualityResult.b();
                this.m.setResultImage(b2);
                b(state);
                this.Y.postDelayed(new Runnable(b2) {
                    private final /* synthetic */ Bitmap f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        IDCardVerifyActivity.this.c(this.f$1);
                    }
                }, 500);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(Bitmap bitmap) {
        a(bitmap, this.B == State.UPLOAD_SCAN_FRONT_SIDE_ONGOING ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
    }

    /* access modifiers changed from: private */
    public void b(IDCardQualityResult iDCardQualityResult) {
        IDCardAttr.IDCardSide iDCardSide;
        IDCardQualityResult.IDCardFailedType iDCardFailedType;
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            iDCardSide = IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT;
        } else {
            iDCardSide = this.B == State.SCAN_BACK_SIDE_ONGOING ? IDCardAttr.IDCardSide.IDCARD_SIDE_BACK : null;
        }
        if (iDCardSide != null && iDCardQualityResult.b != null && (iDCardFailedType = iDCardQualityResult.b.get(0)) != this.A) {
            this.A = iDCardFailedType;
            this.p.setText(VerifyUtils.a(this, iDCardFailedType, iDCardSide));
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.C.a(IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        e();
        VerifyStatUtils.a(R.string.stat_event_begin_scan, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        VerifyStatUtils.a(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT));
    }

    /* access modifiers changed from: private */
    public void a(Bitmap bitmap) {
        if (ActivityChecker.a((android.app.Activity) this) && bitmap != null) {
            State state = null;
            if (this.B == State.CAPTURE_FRONT_SIDE_ONGOING) {
                state = State.CAPTURE_FRONT_SIDE_COMPLETE;
                VerifyStatUtils.a(R.string.stat_event_capture_photo_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
            } else if (this.B == State.CAPTURE_BACK_SIDE_ONGOING) {
                state = State.CAPTURE_BACK_SIDE_COMPLETE;
                VerifyStatUtils.a(R.string.stat_event_capture_photo_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            }
            if (state != null) {
                this.I = bitmap;
                b(state);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 || i2 == 101) {
            if (i3 == -1 && intent != null) {
                this.t.setImageBitmap((Bitmap) null);
                b(this.H);
                if (i2 == 100) {
                    b(this.F);
                    this.F = null;
                } else {
                    b(this.G);
                    this.G = null;
                }
                Uri data = intent.getData();
                Bitmap a2 = BitmapUtils.a((Context) this, data, this.j.f(), 0);
                Bitmap a3 = BitmapUtils.a((Context) this, data, this.X, this.X);
                if (a2 != null && a3 != null) {
                    if (i2 == 100) {
                        this.F = a3;
                        b(State.PICK_FRONT_SIDE_PREVIEW);
                        VerifyStatUtils.a(R.string.stat_event_pick_photo_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
                    } else {
                        this.G = a3;
                        b(State.PICK_BACK_SIDE_PREVIEW);
                        VerifyStatUtils.a(R.string.stat_event_pick_photo_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
                    }
                    this.H = a2;
                    this.t.setImageBitmap(a2);
                }
            }
        } else if (i2 != 102) {
        } else {
            if (i3 == 100) {
                b(this.F);
                b(this.G);
                b(State.SCAN_FRONT_SIDE_ONGOING);
                return;
            }
            finish();
        }
    }

    private void b(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if ((this.B == State.SCAN_FRONT_SIDE_ONGOING || this.B == State.SCAN_BACK_SIDE_ONGOING) && !TextUtils.isEmpty(this.N)) {
            e();
        }
        this.E = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.Y.removeCallbacks(this.Z);
        this.E = true;
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.C != null) {
            this.C.c();
        }
        b(this.F);
        b(this.G);
        if (this.U != null) {
            this.U.cancel();
        }
        if (this.V != null) {
            this.V.cancel();
        }
        if (this.W != null) {
            this.W.cancel();
        }
        String b2 = VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        String b3 = VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
        VerifyStatUtils.c(b2);
        VerifyStatUtils.c(b3);
        this.Y.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void e() {
        this.Y.removeCallbacks(this.Z);
        this.Y.postDelayed(this.Z, 20000);
    }

    private void f() {
        this.Y.removeCallbacks(this.Z);
    }

    private void a(IDCardAttr.IDCardSide iDCardSide) {
        if (iDCardSide != null) {
            try {
                startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT ? 100 : 101);
            } catch (ActivityNotFoundException unused) {
                VerifyUtils.a((Context) this, R.string.open_album_fail);
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        i();
        f();
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            VerifyStatUtils.a(R.string.stat_event_scan_timeout, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
            VerifyStatUtils.c(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT));
        } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
            VerifyStatUtils.a(R.string.stat_event_scan_timeout, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            VerifyStatUtils.c(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
        }
        this.D = true;
        Utils.a((DialogFragment) new AlertDialog.Builder(this).a(R.string.id_card_scan_failed).a(false).a(R.string.take_photo_manualy, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                IDCardVerifyActivity.this.c(dialogInterface, i);
            }
        }).b(R.string.id_card_rescan, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                IDCardVerifyActivity.this.b(dialogInterface, i);
            }
        }).a(), getSupportFragmentManager(), "ScanTimeoutDialog");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(DialogInterface dialogInterface, int i2) {
        this.D = false;
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            b(State.CAPTURE_FRONT_SIDE_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_capture_photo_manualy_click, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
        } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
            b(State.CAPTURE_BACK_SIDE_ONGOING);
            VerifyStatUtils.a(R.string.stat_event_capture_photo_manualy_click, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        this.D = false;
        h();
        e();
        if (this.B == State.SCAN_FRONT_SIDE_ONGOING) {
            VerifyStatUtils.a(R.string.stat_event_rescan_click, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT);
            VerifyStatUtils.a(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT));
        } else if (this.B == State.SCAN_BACK_SIDE_ONGOING) {
            VerifyStatUtils.a(R.string.stat_event_rescan_click, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            VerifyStatUtils.a(VerifyStatUtils.b(R.string.stat_event_scan_success, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK));
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.j.a()) {
            this.j.a(this.k.getSurfaceTexture());
            this.j.f10352a.setPreviewCallback(this);
        }
    }

    private void i() {
        this.j.d();
    }

    private void j() {
        FormBody.Builder add = new FormBody.Builder().add(VerifyConstants.j, this.M).add("partnerId", this.K).add(BaseService.TIME_STAMP, this.S).add("sign", this.R);
        if (!TextUtils.isEmpty(this.L)) {
            add.add(VerifyConstants.i, this.L);
        }
        this.U = VerifyHttpManager.a(this).a().newCall(new Request.Builder().url(VerifyConstants.d).post(add.build()).build());
        this.U.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                IDCardVerifyActivity.this.Y.post(new Runnable(iOException) {
                    private final /* synthetic */ IOException f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass4.this.a(this.f$1);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(IOException iOException) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    IDCardVerifyActivity iDCardVerifyActivity = IDCardVerifyActivity.this;
                    iDCardVerifyActivity.onRequestError("startProcess failed, " + iOException.toString(), 11);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                IDCardVerifyActivity.this.Y.post(new Runnable(IDCardVerifyActivity.this.a(response)) {
                    private final /* synthetic */ VerifyResponse f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass4.this.a(this.f$1);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(VerifyResponse verifyResponse) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    if (verifyResponse == null || !verifyResponse.f()) {
                        IDCardVerifyActivity.this.onRequestError(verifyResponse != null ? verifyResponse.b() : "start process failed", 11);
                        return;
                    }
                    String unused = IDCardVerifyActivity.this.N = verifyResponse.e();
                    if (TextUtils.isEmpty(IDCardVerifyActivity.this.N)) {
                        IDCardVerifyActivity.this.onRequestError("process id is null", 11);
                    } else if (IDCardVerifyActivity.this.B == State.SCAN_FRONT_SIDE_ONGOING) {
                        IDCardVerifyActivity.this.q.setVisibility(0);
                        IDCardVerifyActivity.this.d();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public VerifyResponse a(Response response) {
        if (!(response == null || !response.isSuccessful() || response.body() == null)) {
            try {
                return (VerifyResponse) new Gson().fromJson(response.body().string(), VerifyResponse.class);
            } catch (IOException unused) {
            }
        }
        return null;
    }

    private void a(final Bitmap bitmap, final IDCardAttr.IDCardSide iDCardSide) {
        String a2 = AESUtils.a();
        String a3 = RSAUtils.a(RSAUtils.c(VerifyConstants.f10875a), a2);
        if (TextUtils.isEmpty(a3)) {
            MifiLog.c(f10854a, "encryptedAESKey generate failded");
            setResult(10);
            finish();
            return;
        }
        String a4 = AESUtils.a("AES/ECB/PKCS5Padding", BitmapUtils.a(bitmap), a2);
        if (TextUtils.isEmpty(a4)) {
            MifiLog.c(f10854a, "bitmap encrypt failded");
            setResult(10);
            finish();
            return;
        }
        VerifyStatUtils.a(VerifyStatUtils.c(R.string.stat_event_upload_success, this.B));
        this.V = VerifyHttpManager.a(this).a().newCall(new Request.Builder().url(VerifyConstants.e).post(new FormBody.Builder().add("processId", this.N).add("partnerId", this.K).add("imageType", String.valueOf(iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT ? 1 : 2)).add("image", a4).add("pass", a3).build()).build());
        this.V.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                IDCardVerifyActivity.this.Y.post(new Runnable(iDCardSide, bitmap) {
                    private final /* synthetic */ IDCardAttr.IDCardSide f$1;
                    private final /* synthetic */ Bitmap f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass5.this.a(this.f$1, this.f$2);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(IDCardAttr.IDCardSide iDCardSide, Bitmap bitmap) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    IDCardVerifyActivity.this.a(iDCardSide, bitmap);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                IDCardVerifyActivity.this.Y.post(new Runnable(IDCardVerifyActivity.this.a(response), bitmap, iDCardSide) {
                    private final /* synthetic */ VerifyResponse f$1;
                    private final /* synthetic */ Bitmap f$2;
                    private final /* synthetic */ IDCardAttr.IDCardSide f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass5.this.a(this.f$1, this.f$2, this.f$3);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(VerifyResponse verifyResponse, Bitmap bitmap, IDCardAttr.IDCardSide iDCardSide) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    if (verifyResponse == null || !verifyResponse.f()) {
                        IDCardVerifyActivity.this.a(iDCardSide, bitmap);
                        return;
                    }
                    bitmap.recycle();
                    IDCardVerifyActivity.this.b(iDCardSide);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(IDCardAttr.IDCardSide iDCardSide) {
        State state;
        VerifyStatUtils.b(VerifyStatUtils.c(R.string.stat_event_upload_success, this.B));
        if (this.B == State.UPLOAD_AND_VERIFY_PICK_PHOTO_ONGOING) {
            if (iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
                a(this.G, IDCardAttr.IDCardSide.IDCARD_SIDE_BACK);
            } else if (iDCardSide == IDCardAttr.IDCardSide.IDCARD_SIDE_BACK) {
                k();
            }
        }
        State state2 = null;
        if (this.B == State.UPLOAD_SCAN_FRONT_SIDE_ONGOING) {
            state2 = State.UPLOAD_SCAN_FRONT_SIDE_SUCCESS;
            state = State.SCAN_BACK_SIDE_ONGOING;
        } else if (this.B == State.UPLOAD_SCAN_BACK_SIDE_ONGOING) {
            state2 = State.UPLOAD_SCAN_BACK_SIDE_SUCCESS;
            state = State.VERIFY_ONGOING;
        } else if (this.B == State.UPLOAD_CAPTURE_FRONT_SIDE_ONGOING) {
            state2 = State.UPLOAD_CAPTURE_FRONT_SIDE_SUCCESS;
            state = State.CAPTURE_BACK_SIDE_ONGOING;
        } else if (this.B == State.UPLOAD_CAPTURE_BACK_SIDE_ONGOING) {
            state2 = State.UPLOAD_CAPTURE_BACK_SIDE_SUCCESS;
            state = State.VERIFY_ONGOING;
        } else {
            state = null;
        }
        if (state2 != null && state != null) {
            b(state2);
            this.Y.postDelayed(new Runnable(state) {
                private final /* synthetic */ IDCardVerifyActivity.State f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    IDCardVerifyActivity.this.b(this.f$1);
                }
            }, 500);
        }
    }

    /* access modifiers changed from: private */
    public void a(IDCardAttr.IDCardSide iDCardSide, Bitmap bitmap) {
        VerifyStatUtils.c(VerifyStatUtils.c(R.string.stat_event_upload_success, this.B));
        VerifyStatUtils.a(R.string.stat_event_upload_fail, this.B);
        setResult(12);
        Utils.a((DialogFragment) new AlertDialog.Builder(this).a(R.string.id_card_upload_failed).a(false).a(R.string.do_vefify_next_time, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                IDCardVerifyActivity.this.a(dialogInterface, i);
            }
        }).b(R.string.id_card_retry_upload, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(bitmap, iDCardSide) {
            private final /* synthetic */ Bitmap f$1;
            private final /* synthetic */ IDCardAttr.IDCardSide f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                IDCardVerifyActivity.this.a(this.f$1, this.f$2, dialogInterface, i);
            }
        }).a(), getSupportFragmentManager(), "ScanTimeoutDialog");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        finish();
        VerifyStatUtils.a(R.string.stat_event_do_vefify_next_time_click);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Bitmap bitmap, IDCardAttr.IDCardSide iDCardSide, DialogInterface dialogInterface, int i2) {
        a(bitmap, iDCardSide);
        VerifyStatUtils.a(R.string.stat_event_retry_upload_photo_click);
    }

    /* access modifiers changed from: private */
    public void k() {
        this.W = VerifyHttpManager.a(this).a().newCall(new Request.Builder().url(VerifyConstants.f).post(new FormBody.Builder().add("processId", this.N).add("partnerId", this.K).add("isBind", String.valueOf(this.O ? 1 : 0)).build()).build());
        this.W.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                IDCardVerifyActivity.this.Y.post(new Runnable(iOException) {
                    private final /* synthetic */ IOException f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass6.this.a(this.f$1);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(IOException iOException) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    IDCardVerifyActivity iDCardVerifyActivity = IDCardVerifyActivity.this;
                    iDCardVerifyActivity.a("commitIdCard failed, " + iOException.toString());
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                String str;
                VerifyResponse verifyResponse = null;
                if (!(response == null || !response.isSuccessful() || response.body() == null)) {
                    try {
                        str = response.body().string();
                        try {
                            verifyResponse = (VerifyResponse) new Gson().fromJson(str, VerifyResponse.class);
                        } catch (IOException unused) {
                        }
                    } catch (IOException unused2) {
                    }
                    IDCardVerifyActivity.this.Y.post(new Runnable(verifyResponse, str, response) {
                        private final /* synthetic */ VerifyResponse f$1;
                        private final /* synthetic */ String f$2;
                        private final /* synthetic */ Response f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void run() {
                            IDCardVerifyActivity.AnonymousClass6.this.a(this.f$1, this.f$2, this.f$3);
                        }
                    });
                }
                str = null;
                IDCardVerifyActivity.this.Y.post(new Runnable(verifyResponse, str, response) {
                    private final /* synthetic */ VerifyResponse f$1;
                    private final /* synthetic */ String f$2;
                    private final /* synthetic */ Response f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void run() {
                        IDCardVerifyActivity.AnonymousClass6.this.a(this.f$1, this.f$2, this.f$3);
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(VerifyResponse verifyResponse, String str, Response response) {
                if (ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                    if (verifyResponse != null) {
                        IDCardVerifyActivity.this.a(verifyResponse, str);
                    } else {
                        IDCardVerifyActivity.this.a("commitIdCard failed, verify response is null");
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("httpStatusCode", String.valueOf(response.code()));
                    if (verifyResponse != null) {
                        hashMap.put("verifyResultCode", String.valueOf(verifyResponse.a()));
                    }
                    VerifyStatUtils.a(R.string.stat_event_verify_result, (Map<String, String>) hashMap);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(VerifyResponse verifyResponse, String str) {
        if (this.y != null) {
            this.y.dismissAllowingStateLoss();
        }
        Intent intent = new Intent();
        intent.putExtra(VerifyConstants.n, str);
        setResult(0, intent);
        if ((!this.P || !verifyResponse.f()) && (!this.Q || verifyResponse.f())) {
            Intent intent2 = new Intent();
            intent2.setClass(this, VerifyResultActivity.class);
            intent2.putExtra(VerifyConstants.n, verifyResponse);
            Utils.a((Context) this, intent2);
            startActivityForResult(intent2, 102);
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.y != null) {
            this.y.dismissAllowingStateLoss();
        }
        onRequestError(str, 13);
    }

    public void onRequestError(String str, int i2) {
        VerifyUtils.a((Context) this, (CharSequence) str);
        setResult(i2);
        finish();
    }

    private class QualityAssessmentThread extends Thread {
        private volatile IDCardAttr.IDCardSide b;
        private volatile boolean c;
        private BlockingQueue<PreviewData> d;
        private IDCardQualityAssessment e;

        public QualityAssessmentThread() {
        }

        public boolean a() {
            this.d = new LinkedBlockingDeque(1);
            this.e = new IDCardQualityAssessment();
            return this.e.a(IDCardVerifyActivity.this.getApplicationContext(), VerifyUtils.b(IDCardVerifyActivity.this.getApplicationContext()));
        }

        public void a(IDCardAttr.IDCardSide iDCardSide) {
            if (iDCardSide != null) {
                b();
                this.b = iDCardSide;
            }
        }

        public void b() {
            this.b = null;
            this.d.clear();
        }

        public void a(PreviewData previewData) {
            if (previewData == null) {
                return;
            }
            if (this.b != null || previewData.b == PreviewDataType.CMD_QUIT) {
                this.d.offer(previewData);
            }
        }

        public void c() {
            b();
            a(new PreviewData((byte[]) null, PreviewDataType.CMD_QUIT));
            this.c = true;
        }

        public void run() {
            int dimensionPixelSize = IDCardVerifyActivity.this.getResources().getDimensionPixelSize(R.dimen.id_card_mask_border_width);
            while (true) {
                try {
                    if (this.c) {
                        break;
                    }
                    PreviewData take = this.d.take();
                    if (this.c) {
                        break;
                    } else if (take.b == PreviewDataType.CMD_QUIT) {
                        break;
                    } else if (this.b != null) {
                        int i = IDCardVerifyActivity.this.j.i();
                        int h = IDCardVerifyActivity.this.j.h();
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) IDCardVerifyActivity.this.k.getLayoutParams();
                        float f = (((float) layoutParams.width) * 1.0f) / ((float) i);
                        float f2 = (float) dimensionPixelSize;
                        IDCardQualityResult a2 = this.e.a(RotatorUtil.a(take.f10862a, h, i, 90), i, h, this.b, new Rect(Math.round(((((float) (-layoutParams.leftMargin)) + IDCardVerifyActivity.this.z.left) - f2) / f), Math.round(((((float) (-layoutParams.topMargin)) + IDCardVerifyActivity.this.z.top) - f2) / f), Math.round(((((float) (-layoutParams.leftMargin)) + IDCardVerifyActivity.this.z.right) + f2) / f), Math.round(((((float) (-layoutParams.topMargin)) + IDCardVerifyActivity.this.z.bottom) + f2) / f)));
                        if (a2 != null) {
                            IDCardVerifyActivity.this.Y.post(new Runnable(a2) {
                                private final /* synthetic */ IDCardQualityResult f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    IDCardVerifyActivity.QualityAssessmentThread.this.a(this.f$1);
                                }
                            });
                        }
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            this.e.b();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(IDCardQualityResult iDCardQualityResult) {
            if (!ActivityChecker.a((android.app.Activity) IDCardVerifyActivity.this)) {
                this.c = true;
            } else if (iDCardQualityResult.a()) {
                IDCardVerifyActivity.this.a(iDCardQualityResult);
            } else {
                IDCardVerifyActivity.this.b(iDCardQualityResult);
            }
        }
    }

    private class CapturePhotoThread extends Thread {
        private CapturePhotoThread() {
        }

        public void run() {
            try {
                IDCardVerifyActivity.this.j.f10352a.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, (Camera.PictureCallback) null, new Camera.PictureCallback() {
                    public final void onPictureTaken(byte[] bArr, Camera camera) {
                        IDCardVerifyActivity.CapturePhotoThread.this.a(bArr, camera);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(byte[] bArr, Camera camera) {
            if (bArr != null) {
                try {
                    Bitmap a2 = BitmapUtils.a((Context) IDCardVerifyActivity.this, bArr, IDCardVerifyActivity.this.X, IDCardVerifyActivity.this.X);
                    if (a2 != null) {
                        IDCardVerifyActivity.this.Y.post(new Runnable(a2) {
                            private final /* synthetic */ Bitmap f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                IDCardVerifyActivity.CapturePhotoThread.this.a(this.f$1);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(Bitmap bitmap) {
            IDCardVerifyActivity.this.a(bitmap);
        }
    }
}
