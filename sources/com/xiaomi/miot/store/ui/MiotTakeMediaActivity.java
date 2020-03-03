package com.xiaomi.miot.store.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class MiotTakeMediaActivity extends FragmentActivity {
    public static final String KEY_TAKE_MEDIA = "key_take_media";
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 1;
    private static final int REQUEST_CODE_CAPTURE_VIDEO = 2;
    public static final String TAKE_RESULT = "take_result";
    public static final String TYPE_PHOTO = "type_photo";
    public static final String TYPE_VIDEO = "type_video";
    public static final String TakeMediaResultBroadCast = "com.xiaomi.TakeMediaResultBroadCast";
    private String mMediaPath;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra(KEY_TAKE_MEDIA);
        if (TYPE_PHOTO.equals(stringExtra)) {
            takePhoto();
        } else if (TYPE_VIDEO.equals(stringExtra)) {
            takeVideo();
        } else {
            onBackPressed();
        }
    }

    private void takeVideo() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) == null) {
            takeFinish("");
            return;
        }
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File contentDir = getContentDir();
        File file = new File(contentDir, format + ".mp4");
        this.mMediaPath = file.getPath();
        intent.putExtra(AgentOptions.k, Uri.fromFile(file));
        intent.putExtra("android.intent.extra.durationLimit", 60);
        intent.putExtra("android.intent.extra.videoQuality", 0);
        startActivityForResult(intent, 2);
    }

    private void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) == null) {
            takeFinish("");
            return;
        }
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File contentDir = getContentDir();
        File file = new File(contentDir, format + ".jpg");
        this.mMediaPath = file.getPath();
        intent.putExtra(AgentOptions.k, Uri.fromFile(file));
        startActivityForResult(intent, 1);
    }

    private File getContentDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Camera");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                case 2:
                    takeFinish(this.mMediaPath);
                    return;
                default:
                    return;
            }
        } else {
            takeFinish("");
        }
    }

    public void onBackPressed() {
        takeFinish("");
    }

    /* access modifiers changed from: package-private */
    public void takeFinish(String str) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(str)) {
            setResult(0);
        } else {
            intent.putExtra(TAKE_RESULT, str);
            setResult(-1, intent);
        }
        intent.setAction(TakeMediaResultBroadCast);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        finish();
    }
}
