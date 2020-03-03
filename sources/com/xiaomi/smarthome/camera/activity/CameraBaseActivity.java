package com.xiaomi.smarthome.camera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.mijia.app.AppConfig;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.Tag;
import com.mijia.model.CameraImageLoader;
import com.mijia.model.CameraImageLoaderEx;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.page.PictureShareActivity;
import com.xiaomi.smarthome.framework.page.verify.DevicePinVerifyEnterActivity;
import com.xiaomi.smarthome.library.common.ThreadPool;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public abstract class CameraBaseActivity extends CommonActivity {
    public static final int ACTIVITY_REQUEST_VERIFY_PINCODE = 9999;
    protected static final int RETRY_DELAYED_TIME = 5000;
    public static int count;
    protected boolean isV4;
    protected MijiaCameraDevice mCameraDevice;
    protected DeviceStat mDeviceStat;
    public String mDid = "";
    protected boolean mIsResumed = false;

    public Activity activity() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void doCreate(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void handleVideoPlayErrorOnMISS() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.a((Activity) this);
        if (bundle != null) {
            this.mDeviceStat = (DeviceStat) bundle.getParcelable("extra_device");
            if (this.mDeviceStat == null) {
                this.mDeviceStat = (DeviceStat) getIntent().getParcelableExtra("extra_device");
            }
        }
        this.mDid = getIntent().getStringExtra("extra_device_did");
        this.isV4 = getIntent().getBooleanExtra("is_v4", false);
        if (this.mDeviceStat == null) {
            this.mDeviceStat = XmPluginHostApi.instance().getDeviceByDid(this.mDid);
        }
        if (this.mDeviceStat == null) {
            finish();
            return;
        }
        this.mCameraDevice = MijiaCameraDevice.a(this.mDeviceStat);
        if (!ImageLoader.a().b()) {
            if (this.mCameraDevice.n()) {
                LogUtil.a(Tag.b, "initConfig(true)");
                initConfig(true);
            } else {
                LogUtil.a(Tag.b, "initConfig(false)");
                initConfig(false);
            }
        }
        count++;
        doCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        getWindow().setFlags(128, 128);
        this.mIsResumed = true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (9999 == i && i2 == 0) {
            finish();
        }
    }

    public void onPause() {
        super.onPause();
        getWindow().clearFlags(128);
        this.mIsResumed = false;
    }

    public void onStop() {
        super.onStop();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public void onDestroy() {
        super.onDestroy();
        ViewUtils.b((Activity) this);
        ViewUtils.a((Activity) this);
        count--;
        if (count == 0 && ImageLoader.a().b()) {
            MijiaCameraDevice.s();
            ThreadPool.a(new Runnable() {
                public void run() {
                    ImageLoader.a().l();
                }
            });
        }
    }

    public void startActivity(Intent intent) {
        intent.putExtra("extra_device_did", this.mDid);
        intent.putExtra("is_v4", this.isV4);
        super.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        intent.putExtra("extra_device_did", this.mDid);
        intent.putExtra("is_v4", this.isV4);
        super.startActivityForResult(intent, i);
    }

    public void runMainThread(Runnable runnable) {
        if (this.mIsResumed) {
            this.mHandler.post(new Runnable(runnable) {
                private final /* synthetic */ Runnable f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CameraBaseActivity.lambda$runMainThread$0(CameraBaseActivity.this, this.f$1);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$runMainThread$0(CameraBaseActivity cameraBaseActivity, Runnable runnable) {
        if (cameraBaseActivity.mIsResumed) {
            runnable.run();
        }
    }

    public void openShareVideoActivity(Activity activity, String str, String str2, String str3, int i) {
        PictureShareActivity.share(activity, str, str2, str3, i);
    }

    public void enableVerifyPincode() {
        if (this.mDeviceStat != null && this.mDeviceStat.isSetPinCode != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("extra_device_did", this.mDid);
            Intent intent = new Intent(this, DevicePinVerifyEnterActivity.class);
            intent.putExtras(bundle);
            intent.putExtra("verfy_pincode_first", true);
            startActivityForResult(intent, 9999);
        }
    }

    public void openSharePictureActivity(String str, String str2, String str3) {
        PictureShareActivity.share((Context) activity(), str, str2, str3);
    }

    private void initConfig(final boolean z) {
        AppConfig.a((Context) this);
        ThreadPool.a(new Runnable() {
            public void run() {
                Context applicationContext = CameraBaseActivity.this.getApplicationContext();
                ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(applicationContext);
                builder.b(3);
                builder.a();
                builder.b((FileNameGenerator) new Md5FileNameGenerator());
                builder.f(52428800);
                builder.a(QueueProcessingType.LIFO);
                if (z) {
                    builder.a((ImageDownloader) new CameraImageLoaderEx(applicationContext));
                } else {
                    builder.a((ImageDownloader) new CameraImageLoader(applicationContext));
                }
                builder.b();
                builder.a(new DisplayImageOptions.Builder().b(true).d(true).a(Bitmap.Config.RGB_565).d());
                ImageLoader.a().a(builder.c());
            }
        });
    }
}
