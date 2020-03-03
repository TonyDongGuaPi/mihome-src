package com.xiaomi.smarthome.miio.camera.face.activity;

import android.content.Context;
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
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;

public class FaceManagerBaseActivity extends BaseActivity {
    private static final String TAG = "FaceManagerBaseActivity";
    private static int count;
    protected static FaceManager mFaceManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (mFaceManager == null) {
            try {
                DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(getIntent().getStringExtra("extra_device_did"));
                mFaceManager = FaceManager.getInstance(new BaseDevice(deviceByDid));
                if (MijiaCameraDevice.a(deviceByDid).n()) {
                    LogUtil.a(Tag.b, "initConfig(true)");
                    initConfig(true);
                } else {
                    LogUtil.a(Tag.b, "initConfig(false)");
                    initConfig(false);
                }
            } catch (Exception e) {
                LogUtil.b(TAG, "" + e.getLocalizedMessage());
            }
        }
        count++;
    }

    private void initConfig(boolean z) {
        AppConfig.a((Context) this);
        Context applicationContext = getApplicationContext();
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
        builder.a(new DisplayImageOptions.Builder().b(true).d(true).a(Bitmap.Config.RGB_565).e(true).d());
        ImageLoader.a().a(builder.c());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        count--;
        if (count == 0) {
            mFaceManager.releaseFaceImage();
            mFaceManager = null;
            if (ImageLoader.a().b()) {
                ImageLoader.a().l();
            }
        }
    }
}
