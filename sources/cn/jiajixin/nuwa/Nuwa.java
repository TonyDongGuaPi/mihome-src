package cn.jiajixin.nuwa;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.jiajixin.nuwa.util.AssetUtils;
import cn.jiajixin.nuwa.util.DexUtils;
import cn.jiajixin.nuwa.util.SecurityChecker;
import java.io.File;
import java.io.IOException;

public class Nuwa {

    /* renamed from: a  reason: collision with root package name */
    private static final String f661a = "nuwa";
    private static final String b = "hack.apk";
    private static final String c = "nuwa";
    private static final String d = "nuwaopt";
    private static SecurityChecker e;
    private static File f;

    public static void a(Context context) throws NuwaException {
        e = new SecurityChecker(context.getApplicationContext());
        f = new File(context.getFilesDir(), "nuwa");
        f.mkdir();
        try {
            a(context, AssetUtils.a(context, b, f));
        } catch (IOException e2) {
            Log.e("nuwa", "copy hack.apk failed");
            throw new NuwaException(e2.getMessage());
        }
    }

    public static void a(Context context, String str) throws NuwaException {
        Log.e("nuwa", "loadPatch:" + str);
        if (context == null || TextUtils.isEmpty(str)) {
            Log.e("nuwa", "context is null");
            throw new NuwaException("context is null");
        }
        File file = new File(str);
        if (!file.exists()) {
            Log.e("nuwa", str + " is null");
            throw new NuwaException(str + " is null");
        } else if (e.a(context, file)) {
            File file2 = new File(context.getFilesDir(), d);
            file2.mkdir();
            try {
                DexUtils.a(str, file2.getAbsolutePath());
                Log.e("nuwa", "loadPatch success:" + str);
            } catch (Exception e2) {
                Log.e("nuwa", "inject " + str + " failed");
                throw new NuwaException(e2.getMessage());
            }
        } else {
            Log.e("nuwa", str + "verifyApk failed");
            throw new NuwaException("verifyApk failed");
        }
    }
}
