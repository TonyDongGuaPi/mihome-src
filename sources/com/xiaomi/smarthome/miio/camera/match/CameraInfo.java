package com.xiaomi.smarthome.miio.camera.match;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.taobao.weex.el.parse.Operators;
import java.io.File;

public class CameraInfo {
    public static final String TAG = "CameraInfo";
    public Bitmap mLastAvatarBitmap;
    long mLastAvatarBitmapLastModifyTime;
    public Bitmap mLastSnapBitmap;
    String mUid;

    public CameraInfo(String str) {
        this.mUid = str;
    }

    public void loadLastAvataBitmap(String str, Context context) {
        try {
            File fileStreamPath = context.getFileStreamPath(str.toLowerCase().replace(Operators.CONDITION_IF_MIDDLE, '_'));
            if (fileStreamPath.exists()) {
                long lastModified = fileStreamPath.lastModified();
                if (lastModified > this.mLastAvatarBitmapLastModifyTime) {
                    this.mLastAvatarBitmapLastModifyTime = lastModified;
                    this.mLastAvatarBitmap = BitmapFactory.decodeFile(fileStreamPath.getAbsolutePath());
                }
            }
        } catch (Exception unused) {
        }
    }

    public void loadLastSnapBitmap(String str, Context context) {
        if (this.mLastSnapBitmap == null) {
            try {
                String replace = str.toLowerCase().replace(Operators.CONDITION_IF_MIDDLE, '_');
                File fileStreamPath = context.getFileStreamPath(replace + "_snap");
                if (fileStreamPath.exists()) {
                    this.mLastSnapBitmap = BitmapFactory.decodeFile(fileStreamPath.getAbsolutePath());
                }
            } catch (Exception unused) {
            }
        }
    }
}
