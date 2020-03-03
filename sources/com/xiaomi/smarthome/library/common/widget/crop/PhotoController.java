package com.xiaomi.smarthome.library.common.widget.crop;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.widget.Toast;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import com.xiaomi.smarthome.library.common.util.SDCardUtils;
import com.xiaomi.youpin.share.ShareObject;
import com.yanzhenjie.permission.Action;
import java.io.File;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class PhotoController {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19009a = 130;
    public static final int b = 140;
    public static final int c = 150;
    private final int d = 1;
    private final int e = 1;
    /* access modifiers changed from: private */
    public Activity f;
    /* access modifiers changed from: private */
    public String g;
    private String h;
    private IPhotoController i;

    public interface IPhotoController {
        void a(String str);
    }

    public PhotoController(Activity activity, IPhotoController iPhotoController) {
        this.f = activity;
        this.i = iPhotoController;
    }

    public void a() {
        File c2 = c();
        this.g = a(c2, DateFormat.format(StringUtils.SOURCE_DATE_FORMAT, System.currentTimeMillis()).toString() + ".jpg");
        PermissionHelper.a(this.f, true, new Action() {
            public void onAction(List<String> list) {
                PhotoController.b(PhotoController.this.f, PhotoController.this.g, 140);
            }
        });
    }

    @TargetApi(11)
    public void b() {
        Intent intent = new Intent();
        intent.setType("image/jpeg");
        intent.setAction("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        if (!CommonUtils.a((Context) this.f, intent)) {
            Toast.makeText(this.f, R.string.unsupported_intent, 0).show();
        } else {
            this.f.startActivityForResult(intent, 130);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, String str, int i2) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(AgentOptions.k, Uri.fromFile(new File(str)));
        if (!CommonUtils.a((Context) activity, intent)) {
            Toast.makeText(activity, R.string.unsupported_intent, 0).show();
        } else {
            activity.startActivityForResult(intent, i2);
        }
    }

    public static String a(String str) {
        return str.substring(0, str.lastIndexOf(".")).concat(String.valueOf(System.currentTimeMillis())).concat(".jpg");
    }

    private void a(Uri uri) {
        if (!this.f.isFinishing()) {
            this.h = a(CropUtils.c);
            CommonUtils.f(this.h);
            File file = new File(this.h);
            Intent intent = new Intent(this.f, CropImageActivity.class);
            intent.setDataAndType(uri, ShareObject.d);
            intent.putExtra(CropImageActivity.ASPECT_X, 1);
            intent.putExtra(CropImageActivity.ASPECT_Y, 1);
            intent.putExtra(CropImageActivity.OUTPUT_X, 720);
            intent.putExtra(CropImageActivity.OUTPUT_Y, 720);
            intent.putExtra(AgentOptions.k, Uri.fromFile(file));
            this.f.startActivityForResult(intent, 150);
        }
    }

    public File c() {
        File externalCacheDir;
        if (SDCardUtils.b() || (externalCacheDir = this.f.getExternalCacheDir()) == null) {
            return null;
        }
        if (externalCacheDir.isDirectory() || externalCacheDir.mkdirs()) {
            IOUtils.a(externalCacheDir);
        }
        File file = new File(externalCacheDir, "images");
        if (file.isDirectory() || file.mkdirs()) {
            IOUtils.a(file);
        }
        return file;
    }

    public String a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            return file2.getAbsolutePath();
        }
        int lastIndexOf = str.lastIndexOf(46);
        String str2 = "";
        if (lastIndexOf > 0) {
            String substring = str.substring(0, lastIndexOf);
            str2 = str.substring(lastIndexOf + 1);
            str = substring;
        }
        int i2 = 1;
        while (true) {
            File file3 = new File(file, String.format("%s_%d.%s", new Object[]{str, Integer.valueOf(i2), str2}));
            if (!file3.exists()) {
                return file3.getAbsolutePath();
            }
            i2++;
        }
    }

    public void a(int i2, int i3, Intent intent) {
        if (i3 == -1) {
            if (i2 == 130) {
                a(intent.getData());
            } else if (i2 == 140) {
                if (!TextUtils.isEmpty(this.g)) {
                    File file = new File(this.g);
                    if (file.isFile()) {
                        a(Uri.fromFile(file));
                    } else if (intent != null) {
                        a(intent.getData());
                    }
                }
            } else if (i2 == 150 && !TextUtils.isEmpty(this.h)) {
                File file2 = new File(this.h);
                if (file2.isFile() && file2.exists() && this.i != null) {
                    this.i.a(this.h);
                }
            }
        }
    }
}
