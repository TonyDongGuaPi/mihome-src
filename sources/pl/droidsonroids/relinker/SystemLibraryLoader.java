package pl.droidsonroids.relinker;

import android.os.Build;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import pl.droidsonroids.relinker.ReLinker;

final class SystemLibraryLoader implements ReLinker.LibraryLoader {
    SystemLibraryLoader() {
    }

    public void a(String str) {
        System.loadLibrary(str);
    }

    public void b(String str) {
        System.load(str);
    }

    public String c(String str) {
        if (!str.startsWith(ShareConstants.o) || !str.endsWith(".so")) {
            return System.mapLibraryName(str);
        }
        return str;
    }

    public String d(String str) {
        return str.substring(3, str.length() - 3);
    }

    public String[] a() {
        if (Build.VERSION.SDK_INT >= 21 && Build.SUPPORTED_ABIS.length > 0) {
            return Build.SUPPORTED_ABIS;
        }
        if (!TextUtils.a(Build.CPU_ABI2)) {
            return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        return new String[]{Build.CPU_ABI};
    }
}
