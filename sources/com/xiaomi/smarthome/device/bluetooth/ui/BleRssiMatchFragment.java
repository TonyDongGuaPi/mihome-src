package com.xiaomi.smarthome.device.bluetooth.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.device.BaikeApi;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.RssiMonitor;
import com.xiaomi.smarthome.device.bluetooth.ui.LoopRunView;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.BitmapUtils;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.functions.Consumer;

public class BleRssiMatchFragment extends BleMatchFragment {
    private static final int d = 3000;
    private static final int e = 300;
    private static final int f = 5;
    private static final int g = 1000;
    private static final int h = 1700;
    /* access modifiers changed from: private */
    public AnimationController A = new AnimationController() {
        public void a() {
            BleRssiMatchFragment.this.a(BleRssiMatchFragment.this.i, BleRssiMatchFragment.this.x, 425);
        }
    };
    /* access modifiers changed from: private */
    public ImageView i;
    /* access modifiers changed from: private */
    public ImageView j;
    /* access modifiers changed from: private */
    public ImageView k;
    /* access modifiers changed from: private */
    public ImageView l;
    private ImageView m;
    private View n;
    private LoopRunView o;
    private LoopRunView p;
    private Bitmap q;
    private Bitmap r;
    /* access modifiers changed from: private */
    public Handler s;
    /* access modifiers changed from: private */
    public boolean t = false;
    private TextView u;
    /* access modifiers changed from: private */
    public Runnable v = new Runnable() {
        public void run() {
            if (!BleRssiMatchFragment.this.t) {
                if (BLEDeviceManager.e()) {
                    BLEDeviceManager.a(new SearchRequest.Builder().a(3000, 4).a(), BleRssiMatchFragment.this.w);
                } else {
                    BleRssiMatchFragment.this.s.postDelayed(BleRssiMatchFragment.this.v, 300);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final MiioBtSearchResponse w = new MiioBtSearchResponse() {
        public void c() {
        }

        public void a() {
            RssiMonitor.a();
        }

        public void a(BleDevice bleDevice) {
            boolean z = BleRssiMatchFragment.this.f15225a.model.equals(bleDevice.model) || BleRssiMatchFragment.this.a(bleDevice.model);
            if (BleRssiMatchFragment.this.f15225a != null && z && !BLEDeviceManager.c(bleDevice) && bleDevice.d() != null) {
                RssiMonitor.a(bleDevice);
            }
        }

        public void b() {
            RssiMonitor.RssiHolder b = RssiMonitor.b();
            BluetoothMyLogger.d("onRssiMonitorStopped: " + b);
            if (b != null) {
                int e = BleRssiMatchFragment.this.d();
                BluetoothMyLogger.d(String.format("Rssi Threadhold: %d", new Object[]{Integer.valueOf(e)}));
                if ((e < 0 && b.b() >= e) || e >= 0) {
                    ((BleMatchActivity) BleRssiMatchFragment.this.mContext).onDeviceMatched(b.c());
                    return;
                }
            }
            String str = "";
            if (BleRssiMatchFragment.this.f15225a != null) {
                str = BleRssiMatchFragment.this.f15225a.model;
            }
            ((BleMatchActivity) BleRssiMatchFragment.this.mContext).onDeviceNotFound(str);
        }
    };
    /* access modifiers changed from: private */
    public AnimationController x = new AnimationController() {
        public void a() {
            BleRssiMatchFragment.this.a(BleRssiMatchFragment.this.j, BleRssiMatchFragment.this.y, 425);
        }
    };
    /* access modifiers changed from: private */
    public AnimationController y = new AnimationController() {
        public void a() {
            BleRssiMatchFragment.this.a(BleRssiMatchFragment.this.k, BleRssiMatchFragment.this.z, 425);
        }
    };
    /* access modifiers changed from: private */
    public AnimationController z = new AnimationController() {
        public void a() {
            BleRssiMatchFragment.this.a(BleRssiMatchFragment.this.l, BleRssiMatchFragment.this.A, 425);
        }
    };

    public interface AnimationController {
        void a();
    }

    public static BleRssiMatchFragment b() {
        return new BleRssiMatchFragment();
    }

    @SuppressLint({"CheckResult"})
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ble_rssi_match, (ViewGroup) null);
        this.i = (ImageView) inflate.findViewById(R.id.wave1);
        this.j = (ImageView) inflate.findViewById(R.id.wave2);
        this.k = (ImageView) inflate.findViewById(R.id.wave3);
        this.l = (ImageView) inflate.findViewById(R.id.wave4);
        this.m = (ImageView) inflate.findViewById(R.id.phone);
        this.n = inflate.findViewById(R.id.arrow);
        this.o = (LoopRunView) inflate.findViewById(R.id.left);
        this.p = (LoopRunView) inflate.findViewById(R.id.right);
        this.u = (TextView) inflate.findViewById(R.id.device_detail);
        BaikeApi.a(this.f15225a.model).subscribe(new Consumer() {
            public final void accept(Object obj) {
                BleRssiMatchFragment.this.b((String) obj);
            }
        });
        a((View) this.i, this.x);
        this.s = new Handler(Looper.getMainLooper());
        a(500);
        c();
        if (this.f15225a != null) {
            STAT.c.f(this.f15225a.model, SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE == 7 ? 1 : 2);
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(String str) throws Exception {
        this.u.setVisibility(0);
        this.u.setOnClickListener(new View.OnClickListener(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BleRssiMatchFragment.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, View view) {
        OperationCommonWebViewActivity.start(getContext(), str, (String) null);
        STAT.d.bn(this.f15225a.model);
    }

    public void c() {
        BluetoothLog.e("startWatchAdvertisement");
        if (BLEDeviceManager.e()) {
            BLEDeviceManager.a(new SearchRequest.Builder().a(3000, 4).a(), this.w);
            return;
        }
        BLEDeviceManager.f();
        this.s.postDelayed(this.v, 300);
    }

    /* access modifiers changed from: private */
    public int d() {
        return BleDevice.e(a());
    }

    public void onPause() {
        Log.i("stopScan", "BRMF stop");
        BluetoothHelper.b();
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.o != null) {
            this.o.destroy();
        }
        if (this.p != null) {
            this.p.destroy();
        }
        if (this.q != null) {
            BitmapUtils.a(this.q);
        }
        if (this.r != null) {
            BitmapUtils.a(this.r);
        }
        this.i.clearAnimation();
        this.j.clearAnimation();
        this.k.clearAnimation();
        this.l.clearAnimation();
    }

    private void a(long j2) {
        this.s.postDelayed(new Runnable() {
            public void run() {
                BleRssiMatchFragment.this.e();
            }
        }, j2);
    }

    /* access modifiers changed from: private */
    public void e() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -141.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                BleRssiMatchFragment.this.f();
            }
        });
        this.m.startAnimation(translateAnimation);
    }

    /* access modifiers changed from: private */
    public void f() {
        this.n.setVisibility(0);
        this.q = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_icon);
        this.r = a(this.q);
        LoopRunView.Configs configs = new LoopRunView.Configs();
        configs.f15280a = 300;
        configs.b = LoopRunView.Direction.UP;
        configs.c = 5;
        this.o.startCycleAnimation(this.q, configs);
        LoopRunView.Configs configs2 = new LoopRunView.Configs();
        configs2.f15280a = 300;
        configs2.b = LoopRunView.Direction.DOWN;
        configs2.c = 5;
        this.p.startCycleAnimation(this.r, configs2);
    }

    private Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        matrix.postRotate(180.0f, (float) (width / 2), (float) (height / 2));
        canvas.drawBitmap(bitmap, matrix, new Paint(1));
        return createBitmap;
    }

    /* access modifiers changed from: package-private */
    public void a(final View view, final AnimationController animationController, long j2) {
        view.postDelayed(new Runnable() {
            public void run() {
                BleRssiMatchFragment.this.a(view, animationController);
            }
        }, j2);
    }

    /* access modifiers changed from: package-private */
    public void a(View view, final AnimationController animationController) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.setDuration(1600);
        animationSet.addAnimation(new AlphaAnimation(0.5f, 0.02f));
        animationSet.addAnimation(new ScaleAnimation(1.0f, 3.5f, 1.0f, 3.5f, 1, 0.5f, 1, 0.5f));
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                animationController.a();
            }
        });
        view.startAnimation(animationSet);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.t = true;
        this.s.removeCallbacks(this.v);
    }
}
