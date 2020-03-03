package com.taobao.weex.utils;

import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.URIAdapter;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.io.File;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class FontDO {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int STATE_FAILED = 3;
    public static final int STATE_INIT = 0;
    public static final int STATE_INVALID = -1;
    public static final int STATE_LOADING = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int TYPE_BASE64 = 5;
    public static final int TYPE_FILE = 2;
    public static final int TYPE_LOCAL = 3;
    public static final int TYPE_NATIVE = 4;
    public static final int TYPE_NETWORK = 1;
    public static final int TYPE_UNKNOWN = 0;
    private final String mFontFamilyName;
    private int mState = -1;
    private int mType = 1;
    private Typeface mTypeface;
    private String mUrl = "";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6340961318361737616L, "com/taobao/weex/utils/FontDO", 68);
        $jacocoData = a2;
        return a2;
    }

    public FontDO(String str, String str2, WXSDKInstance wXSDKInstance) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFontFamilyName = str;
        $jacocoInit[0] = true;
        parseSrc(str2, wXSDKInstance);
        $jacocoInit[1] = true;
    }

    public FontDO(String str, Typeface typeface) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mFontFamilyName = str;
        this.mTypeface = typeface;
        this.mType = 4;
        this.mState = 2;
        $jacocoInit[2] = true;
    }

    public String getFontFamilyName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mFontFamilyName;
        $jacocoInit[3] = true;
        return str;
    }

    private void parseSrc(String str, WXSDKInstance wXSDKInstance) {
        String str2;
        boolean[] $jacocoInit = $jacocoInit();
        if (str != null) {
            str2 = str.trim();
            $jacocoInit[4] = true;
        } else {
            str2 = "";
            $jacocoInit[5] = true;
        }
        if (wXSDKInstance == null) {
            $jacocoInit[6] = true;
        } else {
            $jacocoInit[7] = true;
            if (wXSDKInstance.getCustomFontNetworkHandler() == null) {
                $jacocoInit[8] = true;
            } else {
                $jacocoInit[9] = true;
                String fetchLocal = wXSDKInstance.getCustomFontNetworkHandler().fetchLocal(str2);
                $jacocoInit[10] = true;
                if (TextUtils.isEmpty(fetchLocal)) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    str2 = fetchLocal;
                }
            }
        }
        if (str2.isEmpty()) {
            this.mState = -1;
            $jacocoInit[13] = true;
            WXLogUtils.e("TypefaceUtil", "font src is empty.");
            $jacocoInit[14] = true;
            return;
        }
        if (str2.matches("^url\\((('.*')|(\".*\"))\\)$")) {
            $jacocoInit[15] = true;
            String substring = str2.substring(5, str2.length() - 2);
            $jacocoInit[16] = true;
            Uri parse = Uri.parse(substring);
            if (wXSDKInstance == null) {
                $jacocoInit[17] = true;
            } else {
                $jacocoInit[18] = true;
                parse = wXSDKInstance.rewriteUri(parse, URIAdapter.FONT);
                $jacocoInit[19] = true;
            }
            this.mUrl = parse.toString();
            try {
                $jacocoInit[20] = true;
                String scheme = parse.getScheme();
                $jacocoInit[21] = true;
                if ("http".equals(scheme)) {
                    $jacocoInit[22] = true;
                } else {
                    $jacocoInit[23] = true;
                    if ("https".equals(scheme)) {
                        $jacocoInit[24] = true;
                    } else {
                        if ("file".equals(scheme)) {
                            this.mType = 2;
                            $jacocoInit[26] = true;
                            this.mUrl = parse.getPath();
                            $jacocoInit[27] = true;
                        } else if ("local".equals(scheme)) {
                            this.mType = 3;
                            $jacocoInit[28] = true;
                        } else if ("data".equals(scheme)) {
                            $jacocoInit[29] = true;
                            long currentTimeMillis = System.currentTimeMillis();
                            $jacocoInit[30] = true;
                            String[] split = this.mUrl.split(",");
                            if (split == null) {
                                $jacocoInit[31] = true;
                            } else if (split.length != 2) {
                                $jacocoInit[32] = true;
                            } else {
                                String str3 = split[0];
                                $jacocoInit[33] = true;
                                if (TextUtils.isEmpty(str3)) {
                                    $jacocoInit[34] = true;
                                } else if (!str3.endsWith(ViewShot.Results.BASE_64)) {
                                    $jacocoInit[35] = true;
                                } else {
                                    String str4 = split[1];
                                    $jacocoInit[36] = true;
                                    if (TextUtils.isEmpty(str4)) {
                                        $jacocoInit[37] = true;
                                    } else {
                                        $jacocoInit[38] = true;
                                        String md5 = WXFileUtils.md5(str4);
                                        $jacocoInit[39] = true;
                                        File file = new File(WXEnvironment.getApplication().getCacheDir(), TypefaceUtil.FONT_CACHE_DIR_NAME);
                                        $jacocoInit[40] = true;
                                        if (file.exists()) {
                                            $jacocoInit[41] = true;
                                        } else {
                                            $jacocoInit[42] = true;
                                            file.mkdirs();
                                            $jacocoInit[43] = true;
                                        }
                                        File file2 = new File(file, md5);
                                        $jacocoInit[44] = true;
                                        if (file2.exists()) {
                                            $jacocoInit[45] = true;
                                        } else {
                                            $jacocoInit[46] = true;
                                            file2.createNewFile();
                                            $jacocoInit[47] = true;
                                            WXFileUtils.saveFile(file2.getPath(), Base64.decode(str4, 0), WXEnvironment.getApplication());
                                            $jacocoInit[48] = true;
                                        }
                                        this.mUrl = file2.getPath();
                                        this.mType = 5;
                                        $jacocoInit[49] = true;
                                        WXLogUtils.d("TypefaceUtil", "Parse base64 font cost " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                                        $jacocoInit[50] = true;
                                    }
                                }
                            }
                            $jacocoInit[51] = true;
                        } else {
                            WXLogUtils.e("TypefaceUtil", "Unknown scheme for font url: " + this.mUrl);
                            this.mType = 0;
                            $jacocoInit[52] = true;
                        }
                        this.mState = 0;
                        $jacocoInit[53] = true;
                        $jacocoInit[56] = true;
                    }
                }
                this.mType = 1;
                $jacocoInit[25] = true;
                this.mState = 0;
                $jacocoInit[53] = true;
            } catch (Exception e) {
                this.mType = -1;
                $jacocoInit[54] = true;
                WXLogUtils.e("TypefaceUtil", "URI.create(mUrl) failed mUrl: " + this.mUrl + "\n" + WXLogUtils.getStackTrace(e));
                $jacocoInit[55] = true;
            }
            $jacocoInit[56] = true;
        } else {
            this.mUrl = str2;
            this.mState = -1;
            $jacocoInit[57] = true;
        }
        if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            WXLogUtils.d("TypefaceUtil", "src:" + str2 + ", mUrl:" + this.mUrl + ", mType:" + this.mType);
            $jacocoInit[60] = true;
        }
        $jacocoInit[61] = true;
    }

    public String getUrl() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.mUrl;
        $jacocoInit[62] = true;
        return str;
    }

    public int getType() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mType;
        $jacocoInit[63] = true;
        return i;
    }

    public Typeface getTypeface() {
        boolean[] $jacocoInit = $jacocoInit();
        Typeface typeface = this.mTypeface;
        $jacocoInit[64] = true;
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTypeface = typeface;
        $jacocoInit[65] = true;
    }

    public int getState() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.mState;
        $jacocoInit[66] = true;
        return i;
    }

    public void setState(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mState = i;
        $jacocoInit[67] = true;
    }
}
