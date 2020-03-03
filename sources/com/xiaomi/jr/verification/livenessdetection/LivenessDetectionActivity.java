package com.xiaomi.jr.verification.livenessdetection;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.miui.supportlite.app.Activity;
import com.miui.supportlite.app.AlertDialog;
import com.xiaomi.jr.antifraud.Tongdun;
import com.xiaomi.jr.antifraud.por.EventTracker;
import com.xiaomi.jr.antifraud.por.PorData;
import com.xiaomi.jr.capturephoto.RotateTips;
import com.xiaomi.jr.capturephoto.utils.ICamera;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.utils.Client;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.dialog.DialogManager;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.stats.StatUtils;
import com.xiaomi.jr.verification.Constants;
import com.xiaomi.jr.verification.R;
import com.xiaomi.jr.verification.VerificationUtils;
import com.xiaomi.jr.verification.http.VerificationApi;
import com.xiaomi.jr.verification.http.VerificationHttpManager;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionFailedType;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionFrame;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionListener;
import com.xiaomi.jr.verification.livenessdetection.detector.DetectionType;
import com.xiaomi.jr.verification.livenessdetection.detector.Detector;
import com.xiaomi.jr.verification.livenessdetection.detector.FaceplusplusDetector;
import com.xiaomi.jr.verification.livenessdetection.utils.IDetection;
import com.xiaomi.jr.verification.livenessdetection.utils.IMediaPlayer;
import com.xiaomi.jr.verification.model.VerifyResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class LivenessDetectionActivity extends Activity implements Camera.PreviewCallback, TextureView.SurfaceTextureListener, DetectionListener {
    public static final int DETECTION_TIME_OUT = 10000;
    public static final int ERROR_ACCESS_DENY = 10004;
    private static final int H = -1;
    public static final int VERIFY_STATUS_FORBID_ACCESS = 200203;
    public static final int VERIFY_STATUS_HITRULE_MANUAL = 200204;
    public static final int VERIFY_STATUS_ONLY_MANUAL = 200202;
    public static final int VERIFY_STATUS_PASS = 2003;
    public static final int VERIFY_STATUS_RETRY_SYSTEM = 200201;

    /* renamed from: a  reason: collision with root package name */
    private static final String f11051a = "LivenessDetection";
    private static final boolean b = MifiLog.f1417a;
    private static final long v = 15000;
    private List<DetectionFailedType> A = new ArrayList();
    private Handler B = new Handler();
    private Executor C = Executors.newSingleThreadExecutor();
    private int D;
    private int E;
    private String F;
    private String G;
    private ICamera.FramePrepareCallback I = new ICamera.FramePrepareCallback() {
        public void a(byte[] bArr) {
            if (LivenessDetectionActivity.this.s == null) {
                try {
                    Camera.Size unused = LivenessDetectionActivity.this.s = LivenessDetectionActivity.this.q.f10352a.getParameters().getPreviewSize();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return;
                }
            }
            LivenessDetectionActivity.this.n.a(bArr, LivenessDetectionActivity.this.s.width, LivenessDetectionActivity.this.s.height);
        }
    };
    private TextureView c;
    /* access modifiers changed from: private */
    public TextView d;
    /* access modifiers changed from: private */
    public TextView e;
    private ImageView f;
    private ViewGroup g;
    private CircleButton h;
    private CircleButton i;
    private CircleButton j;
    /* access modifiers changed from: private */
    public CircleButton[] k;
    private DebugView l;
    private LinearLayout m;
    /* access modifiers changed from: private */
    public Detector n;
    /* access modifiers changed from: private */
    public IDetection o;
    /* access modifiers changed from: private */
    public IMediaPlayer p;
    /* access modifiers changed from: private */
    public ICamera q;
    private Rect r;
    /* access modifiers changed from: private */
    public Camera.Size s;
    private boolean t;
    private boolean u;
    private long w;
    private boolean x;
    /* access modifiers changed from: private */
    public int y;
    private int z;

    public enum FinishType {
        SUCCESS,
        PREPARE_FAIL,
        TOO_MANY_ACTION_FAIL
    }

    private String d(int i2) {
        return i2 == 1 ? "face++" : "unknown";
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        getWindow().setFlags(128, 128);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setNavigationBarColor(getResources().getColor(17170444));
        }
        setContentView(R.layout.liveness_detection_activity);
        this.d = (TextView) findViewById(R.id.guide);
        this.e = (TextView) findViewById(R.id.tips);
        this.f = (ImageView) findViewById(R.id.count_down);
        this.g = (ViewGroup) findViewById(R.id.detection_step);
        this.h = (CircleButton) findViewById(R.id.first_circle);
        this.h.setNormalAndHighLightResId(R.drawable.scan_first_btn_normal, R.drawable.scan_first_btn_high_light).setDuration(10000);
        this.i = (CircleButton) findViewById(R.id.second_circle);
        this.i.setNormalAndHighLightResId(R.drawable.scan_second_btn_normal, R.drawable.scan_second_btn_high_light).setDuration(10000);
        this.j = (CircleButton) findViewById(R.id.third_circle);
        this.j.setNormalAndHighLightResId(R.drawable.scan_third_btn_normal, R.drawable.scan_third_btn_high_light).setDuration(10000);
        this.k = new CircleButton[]{this.h, this.i, this.j};
        this.l = (DebugView) findViewById(R.id.debug);
        if (b) {
            this.l.setVisibility(0);
        }
        this.m = (LinearLayout) findViewById(R.id.loading);
        this.c = (TextureView) findViewById(R.id.camera_view);
        this.c.setSurfaceTextureListener(this);
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LivenessDetectionActivity.this.a(view);
            }
        });
        a();
        a(getString(R.string.stat_face_verify_pv), (Map<String, String>) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }

    private void a() {
        this.D = getIntent().getIntExtra(Constants.f11041a, 0);
        this.E = getIntent().getIntExtra(Constants.b, 0);
        this.F = getIntent().getStringExtra(Constants.c);
        this.G = getIntent().getStringExtra("extra");
        this.o = new IDetection(this);
        this.p = new IMediaPlayer(this);
        this.p.a((IMediaPlayer.OnPlayListener) new IMediaPlayer.OnPlayListener() {
            public void a(int i) {
                if (i == R.raw.next_step) {
                    LivenessDetectionActivity.this.d.setText(LivenessDetectionActivity.this.getString(R.string.liveness_action_next));
                    return;
                }
                LivenessDetectionActivity.this.e.setText("");
                DetectionType b = LivenessDetectionActivity.this.p.b(i);
                if (b != null && LivenessDetectionActivity.this.y < LivenessDetectionActivity.this.o.f11056a) {
                    LivenessDetectionActivity.this.d.setText(LivenessDetectionActivity.this.o.a(b));
                    LivenessDetectionActivity.this.k[LivenessDetectionActivity.this.y].setState(1);
                    LivenessDetectionActivity.this.n.a(b);
                }
            }

            public void b(int i) {
                if (i == R.raw.next_step) {
                    LivenessDetectionActivity.this.p.b();
                    LivenessDetectionActivity.this.p.a(LivenessDetectionActivity.this.p.a(LivenessDetectionActivity.this.o.b.get(LivenessDetectionActivity.this.y)));
                }
            }
        });
        this.q = new ICamera(this);
        b();
    }

    private void b() {
        this.C.execute(new Runnable() {
            public final void run() {
                LivenessDetectionActivity.this.k();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void k() {
        String str;
        if (this.D == 1) {
            this.n = new FaceplusplusDetector(this, 10000);
            this.n.a(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
            this.n.a((DetectionListener) this);
            boolean a2 = this.n.a();
            if (a2) {
                str = null;
            } else {
                str = getString(R.string.init_detector_fail);
            }
            a(a2, str);
            return;
        }
        MifiLog.b(f11051a, "invalid detector type.");
        a(false, getString(R.string.invalid_detector_type));
    }

    private void a(boolean z2, String str) {
        this.B.post(new Runnable(z2, str) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                LivenessDetectionActivity.this.c(this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(boolean z2, String str) {
        if (!ActivityChecker.a((android.app.Activity) this)) {
            return;
        }
        if (z2) {
            e();
            this.t = true;
            if (b) {
                String d2 = d(this.D);
                DebugView debugView = this.l;
                debugView.updateProvider(d2 + " inside");
                return;
            }
            return;
        }
        if (str != null) {
            Toast.makeText(this, str, 1).show();
        }
        finish();
    }

    private void c() {
        float f2;
        float f3;
        float f4;
        ImageView imageView = (ImageView) findViewById(R.id.liveness_bg);
        float width = (float) imageView.getWidth();
        float height = (float) imageView.getHeight();
        float intrinsicWidth = (float) imageView.getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) imageView.getDrawable().getIntrinsicHeight();
        boolean z2 = width / height > intrinsicWidth / intrinsicHeight;
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(R.dimen.face_allow_area_width);
        float dimensionPixelSize2 = (float) getResources().getDimensionPixelSize(R.dimen.face_allow_area_height);
        float dimensionPixelSize3 = (float) getResources().getDimensionPixelSize(R.dimen.face_allow_area_center_x);
        float dimensionPixelSize4 = (float) getResources().getDimensionPixelSize(R.dimen.face_allow_area_center_y);
        if (z2) {
            float f5 = width / intrinsicWidth;
            f3 = (dimensionPixelSize3 - (dimensionPixelSize / 2.0f)) * f5;
            f2 = ((dimensionPixelSize4 - (dimensionPixelSize2 / 2.0f)) * f5) - (((intrinsicHeight * f5) - height) / 2.0f);
            f4 = f5;
        } else {
            f4 = height / intrinsicHeight;
            f3 = ((dimensionPixelSize3 - (dimensionPixelSize / 2.0f)) * f4) - (((intrinsicWidth * f4) - width) / 2.0f);
            f2 = (dimensionPixelSize4 - (dimensionPixelSize2 / 2.0f)) * f4;
        }
        float f6 = dimensionPixelSize2 * f4;
        float f7 = f3 + (dimensionPixelSize * f4);
        float f8 = f2 + f6;
        float f9 = 0.0f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f7 > width) {
            f7 = width;
        }
        if (f2 >= 0.0f) {
            f9 = f2;
        }
        if (f8 <= height) {
            height = f8;
        }
        this.r = new Rect((int) f3, (int) f9, (int) f7, (int) height);
    }

    private boolean d() {
        if (this.q.f10352a == null) {
            return false;
        }
        this.o.a();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.q.a()) {
            return;
        }
        if (this.q.a(true) != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.c.getLayoutParams();
            this.q.a(layoutParams);
            this.c.setLayoutParams(layoutParams);
            if (this.q.b) {
                new RotateTips(this).a();
                return;
            }
            return;
        }
        Toast.makeText(this, getString(R.string.open_camera_fail), 1).show();
        finish();
    }

    private void e() {
        this.d.setText(getString(R.string.liveness_prepare_guide));
        this.e.setText(getString(R.string.liveness_prepare_tip));
        this.g.setVisibility(4);
        this.f.setVisibility(4);
        for (CircleButton state : this.k) {
            state.setState(0);
        }
        this.w = System.currentTimeMillis();
        this.x = false;
        this.u = false;
        this.z = 0;
        this.y = 0;
    }

    private void f() {
        this.g.setVisibility(4);
        this.f.setVisibility(0);
        this.f.postDelayed(new Runnable() {
            public final void run() {
                LivenessDetectionActivity.this.j();
            }
        }, 3000);
        ((AnimationDrawable) this.f.getDrawable()).start();
        a(getString(R.string.stat_start_liveness_detect), (Map<String, String>) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j() {
        if (!this.u) {
            g();
        }
    }

    private void g() {
        this.d.setText("");
        this.f.setVisibility(4);
        this.g.setVisibility(0);
        if (d()) {
            a(this.y);
        }
    }

    private void h() {
        this.n.a(DetectionType.NONE);
        e();
    }

    private void a(int i2) {
        if (b(i2)) {
            this.p.b();
            if (i2 == 0) {
                this.p.a(this.p.a(this.o.b.get(i2)));
                return;
            }
            this.p.a(R.raw.next_step);
        }
    }

    private boolean b(int i2) {
        return i2 >= 0 && i2 < this.o.f11056a;
    }

    private void c(int i2) {
        if (b(i2)) {
            this.k[i2].setState(2);
        }
    }

    private void a(int i2, DetectionFailedType detectionFailedType) {
        if (b(i2)) {
            this.k[i2].setState(2);
            this.e.setText(this.o.a(detectionFailedType));
        }
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        StringBuilder sb = new StringBuilder();
        sb.append("onPreviewFrame: init=");
        sb.append(this.t);
        sb.append(", data=");
        sb.append(bArr.length);
        sb.append(", w=");
        int i2 = -1;
        sb.append(this.s != null ? this.s.width : -1);
        sb.append(", h=");
        if (this.s != null) {
            i2 = this.s.height;
        }
        sb.append(i2);
        Detector.b(sb.toString());
        if (this.t) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            if (this.q.c != ICamera.FlipPreviewFrameMode.NONE) {
                this.q.a(bArr2, this.I);
            } else {
                this.I.a(bArr2);
            }
        }
        try {
            camera.addCallbackBuffer(bArr);
        } catch (Exception e2) {
            MifiLog.e(f11051a, "camera addCallbackBuffer exception: " + e2.getMessage());
        }
    }

    public void onDetectionSuccess() {
        if (!this.u) {
            MifiLog.b(f11051a, "onDetectionSuccess, step: " + this.y);
            c(this.y);
            int i2 = this.y + 1;
            this.y = i2;
            a(i2);
            this.z++;
            b(true, (String) null);
            if (this.y >= this.o.f11056a) {
                a(FinishType.SUCCESS);
            }
        }
    }

    public void onDetectionFailed(DetectionFailedType detectionFailedType) {
        if (!this.u) {
            StringBuilder sb = new StringBuilder();
            sb.append("onDetectionFailed, step: ");
            sb.append(this.y);
            sb.append(", fail type: ");
            String str = null;
            sb.append(detectionFailedType != null ? detectionFailedType.toString() : null);
            MifiLog.b(f11051a, sb.toString());
            a(this.y, detectionFailedType);
            int i2 = this.y + 1;
            this.y = i2;
            a(i2);
            if (detectionFailedType != null) {
                str = detectionFailedType.toString();
            }
            boolean z2 = false;
            b(false, str);
            if (!this.A.contains(detectionFailedType)) {
                this.A.add(detectionFailedType);
            }
            if (this.y >= this.o.f11056a) {
                if (this.z >= this.o.f11056a - 1) {
                    z2 = true;
                }
                a(z2 ? FinishType.SUCCESS : FinishType.TOO_MANY_ACTION_FAIL);
            }
        }
    }

    private void b(boolean z2, String str) {
        String string = getString(z2 ? R.string.stat_liveness_action_success : R.string.stat_liveness_action_fail, new Object[]{this.o.b(this.o.b.get(this.y - 1))});
        HashMap hashMap = new HashMap();
        hashMap.put("failType", str);
        MifiLog.b("TestStat", "action stat: " + hashMap.toString());
        a(string, (Map<String, String>) hashMap);
        EventTracker.a().a(new EventTracker.Event(string));
    }

    private void a(FinishType finishType) {
        if (!this.u) {
            this.u = true;
            if (finishType == FinishType.SUCCESS) {
                byte[] c2 = this.n.c();
                byte[] d2 = this.n.d();
                HashMap hashMap = new HashMap();
                if (this.D == 1) {
                    hashMap.put(Constants.h, this.n.a(Constants.h));
                }
                a(c2, d2, (Map<String, String>) hashMap);
                a(getString(R.string.stat_liveness_detect_success), (Map<String, String>) null);
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (finishType != FinishType.TOO_MANY_ACTION_FAIL) {
                sb.append(this.o.a(finishType));
                sb.append("。");
            } else {
                for (int i2 = 0; i2 < this.A.size(); i2++) {
                    sb.append(this.o.a(this.A.get(i2)));
                    if (i2 < this.A.size() - 1) {
                        sb.append("，");
                    } else {
                        sb.append("。");
                    }
                }
            }
            a(getString(R.string.title_verify_fail), sb.toString(), getString(R.string.retry), getString(R.string.stat_liveness_fail_retry), getString(R.string.stat_liveness_fail_cancel));
        }
    }

    public void onFrameDetected(long j2, DetectionFrame detectionFrame) {
        if (this.x) {
            return;
        }
        if (System.currentTimeMillis() - this.w > v) {
            a(FinishType.PREPARE_FAIL);
        } else if (detectionFrame != null) {
            if (this.r == null) {
                c();
            }
            Rect rect = detectionFrame.b;
            if (b) {
                this.l.updateFaceAllowRect(this.r);
                this.l.updateFaceRect(rect);
                DebugView debugView = this.l;
                debugView.updateQuality("face quality: " + detectionFrame.f11054a);
            }
            if (!this.r.contains(rect)) {
                this.e.setText(getString(R.string.liveness_prepare_overstep_face_allow_area));
            } else if (detectionFrame.f11054a < ((float) this.E)) {
                this.e.setText(R.string.liveness_prepare_low_face_quality);
            } else {
                MifiLog.b(f11051a, "prepare stage pass!");
                this.x = true;
                this.e.setText(R.string.liveness_prepare_tip);
                if (b) {
                    this.l.updateFaceAllowRect((Rect) null);
                    this.l.updateFaceRect((Rect) null);
                    this.l.updateQuality((String) null);
                }
                f();
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.q.a((Camera.PreviewCallback) this);
        this.q.a(surfaceTexture);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.q.d();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.n != null) {
            this.n.b();
        }
        if (this.q != null) {
            this.q.e();
        }
        if (this.p != null) {
            this.p.b();
        }
    }

    private void a(String str, Map<String, String> map) {
        StatUtils.a((Context) this, getString(R.string.stat_category_system_verify), str, map, getIntent().getExtras());
    }

    private void a(byte[] bArr, byte[] bArr2, Map<String, String> map) {
        this.C.execute(new Runnable(bArr, map, bArr2) {
            private final /* synthetic */ byte[] f$1;
            private final /* synthetic */ Map f$2;
            private final /* synthetic */ byte[] f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                LivenessDetectionActivity.this.a(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(byte[] bArr, Map map, byte[] bArr2) {
        int i2;
        boolean z2;
        int i3;
        int i4;
        MultipartBody.Part part;
        boolean z3;
        a(true);
        Location a2 = Utils.a((Context) this);
        String b2 = Tongdun.b(this);
        try {
            VerificationApi a3 = VerificationHttpManager.a();
            String e2 = Client.e(this);
            double d2 = 0.0d;
            double longitude = a2 != null ? a2.getLongitude() : 0.0d;
            if (a2 != null) {
                d2 = a2.getLatitude();
            }
            double d3 = d2;
            if (b2 == null) {
                b2 = "";
            }
            String porData = new PorData(this).a().b().c().toString();
            int i5 = this.D;
            int i6 = this.D == 1 ? 2 : 1;
            String str = this.G;
            MultipartBody.Part createFormData = MultipartBody.Part.createFormData("image", "no-name", RequestBody.create(MediaType.parse("application/octet-stream"), bArr));
            String str2 = this.D == 1 ? (String) map.get(Constants.h) : null;
            if (this.D == 1) {
                part = MultipartBody.Part.createFormData(Constants.g, "no-name", RequestBody.create(MediaType.parse("application/octet-stream"), bArr2));
            } else {
                part = null;
            }
            Response<MiFiResponse<VerifyResult>> execute = a3.a(e2, longitude, d3, b2, porData, i5, i6, str, (String) null, (String) null, (String) null, (String) null, createFormData, str2, part).execute();
            MiFiResponse body = execute.isSuccessful() ? execute.body() : null;
            if (body != null) {
                if (body.c()) {
                    i2 = ((VerifyResult) body.d()).b;
                    i3 = -1;
                } else {
                    i3 = body.a();
                    i2 = -1;
                }
                try {
                    i4 = i();
                    z3 = false;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    i4 = -1;
                    z2 = true;
                    a(false);
                    a(i2, i3, i4, z2);
                }
            } else {
                i4 = -1;
                i3 = -1;
                i2 = -1;
                z3 = true;
            }
            z2 = z3;
        } catch (Exception e4) {
            e = e4;
            i3 = -1;
            i2 = -1;
            e.printStackTrace();
            i4 = -1;
            z2 = true;
            a(false);
            a(i2, i3, i4, z2);
        }
        a(false);
        a(i2, i3, i4, z2);
    }

    private void a(boolean z2) {
        this.B.post(new Runnable(z2) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                LivenessDetectionActivity.this.b(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z2) {
        this.m.setVisibility(z2 ? 0 : 8);
    }

    private void a(int i2, int i3, int i4, boolean z2) {
        this.B.post(new Runnable(z2, i2, i3, i4) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ int f$3;
            private final /* synthetic */ int f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                LivenessDetectionActivity.this.a(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z2, int i2, int i3, int i4) {
        String string;
        String string2;
        if (z2) {
            string = getString(R.string.message_verify_request_fail);
        } else if (i2 == 200201 || !(i3 == -1 || i3 == 10004 || i4 <= 0)) {
            string = getString(R.string.message_verify_reject);
        } else {
            VerificationUtils.a(this, this.F, true, i2, i3);
            finish();
            return;
        }
        String str = string;
        String string3 = getString(R.string.title_verify_fail);
        if (i4 != -1) {
            string2 = getString(R.string.retry_with_left_times, new Object[]{Integer.valueOf(i4)});
        } else {
            string2 = getString(R.string.retry);
        }
        a(string3, str, string2, getString(R.string.stat_verify_fail_retry), getString(R.string.stat_verify_fail_cancel));
    }

    private void a(String str, String str2, String str3, String str4, String str5) {
        DialogManager.a((DialogFragment) new AlertDialog.Builder(this).a((CharSequence) str).b((CharSequence) str2).a((CharSequence) str3, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(str4) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                LivenessDetectionActivity.this.b(this.f$1, dialogInterface, i);
            }
        }).b(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(str5) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                LivenessDetectionActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).a(false).a(), (Context) this, "verify_result");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(String str, DialogInterface dialogInterface, int i2) {
        a(str, (Map<String, String>) null);
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, DialogInterface dialogInterface, int i2) {
        a(str, (Map<String, String>) null);
        finish();
    }

    private int i() {
        Integer num;
        try {
            num = (Integer) VerificationHttpManager.a(VerificationHttpManager.a().a().execute());
        } catch (IOException e2) {
            e2.printStackTrace();
            num = null;
        }
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
