package com.taobao.weex.utils;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class TypefaceUtil {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ACTION_TYPE_FACE_AVAILABLE = "type_face_available";
    public static final String FONT_CACHE_DIR_NAME = "font-family";
    private static final String TAG = "TypefaceUtil";
    private static final Map<String, FontDO> sCacheMap = new HashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-1593493587098604599L, "com/taobao/weex/utils/TypefaceUtil", 123);
        $jacocoData = a2;
        return a2;
    }

    public TypefaceUtil() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ boolean access$000(String str, String str2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean loadLocalFontFile = loadLocalFontFile(str, str2, z);
        $jacocoInit[120] = true;
        return loadLocalFontFile;
    }

    static /* synthetic */ Map access$100() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, FontDO> map = sCacheMap;
        $jacocoInit[121] = true;
        return map;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[122] = true;
    }

    public static void putFontDO(FontDO fontDO) {
        boolean[] $jacocoInit = $jacocoInit();
        if (fontDO == null) {
            $jacocoInit[1] = true;
        } else if (TextUtils.isEmpty(fontDO.getFontFamilyName())) {
            $jacocoInit[2] = true;
        } else {
            $jacocoInit[3] = true;
            sCacheMap.put(fontDO.getFontFamilyName(), fontDO);
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    public static void registerNativeFont(Map<String, Typeface> map) {
        boolean[] $jacocoInit = $jacocoInit();
        if (map == null) {
            $jacocoInit[6] = true;
        } else if (map.size() <= 0) {
            $jacocoInit[7] = true;
        } else {
            $jacocoInit[8] = true;
            $jacocoInit[9] = true;
            for (Map.Entry next : map.entrySet()) {
                $jacocoInit[11] = true;
                FontDO fontDO = new FontDO((String) next.getKey(), (Typeface) next.getValue());
                $jacocoInit[12] = true;
                putFontDO(fontDO);
                $jacocoInit[13] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[14] = true;
                } else {
                    $jacocoInit[15] = true;
                    WXLogUtils.d(TAG, "register new typeface: " + ((String) next.getKey()));
                    $jacocoInit[16] = true;
                }
                $jacocoInit[17] = true;
            }
            $jacocoInit[10] = true;
        }
        $jacocoInit[18] = true;
    }

    public static FontDO getFontDO(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        FontDO fontDO = sCacheMap.get(str);
        $jacocoInit[19] = true;
        return fontDO;
    }

    public static void removeFontDO(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        sCacheMap.remove(str);
        $jacocoInit[20] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void applyFontStyle(android.graphics.Paint r7, int r8, int r9, java.lang.String r10) {
        /*
            boolean[] r0 = $jacocoInit()
            android.graphics.Typeface r1 = r7.getTypeface()
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0012
            r4 = 21
            r0[r4] = r3
            r4 = 0
            goto L_0x001a
        L_0x0012:
            int r4 = r1.getStyle()
            r5 = 22
            r0[r5] = r3
        L_0x001a:
            r5 = -1
            if (r9 != r3) goto L_0x0022
            r9 = 23
            r0[r9] = r3
            goto L_0x0036
        L_0x0022:
            r6 = r4 & 1
            if (r6 != 0) goto L_0x002b
            r9 = 24
            r0[r9] = r3
            goto L_0x003b
        L_0x002b:
            if (r9 == r5) goto L_0x0032
            r9 = 25
            r0[r9] = r3
            goto L_0x003b
        L_0x0032:
            r9 = 26
            r0[r9] = r3
        L_0x0036:
            r9 = 27
            r0[r9] = r3
            r2 = 1
        L_0x003b:
            r9 = 2
            if (r8 != r9) goto L_0x0043
            r8 = 28
            r0[r8] = r3
            goto L_0x0056
        L_0x0043:
            r9 = r9 & r4
            if (r9 != 0) goto L_0x004b
            r8 = 29
            r0[r8] = r3
            goto L_0x005c
        L_0x004b:
            if (r8 == r5) goto L_0x0052
            r8 = 30
            r0[r8] = r3
            goto L_0x005c
        L_0x0052:
            r8 = 31
            r0[r8] = r3
        L_0x0056:
            r2 = r2 | 2
            r8 = 32
            r0[r8] = r3
        L_0x005c:
            if (r10 != 0) goto L_0x0063
            r8 = 33
            r0[r8] = r3
            goto L_0x006f
        L_0x0063:
            r8 = 34
            r0[r8] = r3
            android.graphics.Typeface r1 = getOrCreateTypeface(r10, r2)
            r8 = 35
            r0[r8] = r3
        L_0x006f:
            if (r1 == 0) goto L_0x0081
            r8 = 36
            r0[r8] = r3
            android.graphics.Typeface r8 = android.graphics.Typeface.create(r1, r2)
            r7.setTypeface(r8)
            r7 = 37
            r0[r7] = r3
            goto L_0x008c
        L_0x0081:
            android.graphics.Typeface r8 = android.graphics.Typeface.defaultFromStyle(r2)
            r7.setTypeface(r8)
            r7 = 38
            r0[r7] = r3
        L_0x008c:
            r7 = 39
            r0[r7] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.TypefaceUtil.applyFontStyle(android.graphics.Paint, int, int, java.lang.String):void");
    }

    public static Typeface getOrCreateTypeface(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        FontDO fontDO = sCacheMap.get(str);
        $jacocoInit[40] = true;
        if (fontDO == null) {
            $jacocoInit[41] = true;
        } else if (fontDO.getTypeface() == null) {
            $jacocoInit[42] = true;
        } else {
            $jacocoInit[43] = true;
            Typeface typeface = fontDO.getTypeface();
            $jacocoInit[44] = true;
            return typeface;
        }
        Typeface create = Typeface.create(str, i);
        $jacocoInit[45] = true;
        return create;
    }

    private static void loadFromAsset(FontDO fontDO, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            Typeface createFromAsset = Typeface.createFromAsset(WXEnvironment.getApplication().getAssets(), str);
            if (createFromAsset != null) {
                $jacocoInit[46] = true;
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[47] = true;
                } else {
                    $jacocoInit[48] = true;
                    WXLogUtils.d(TAG, "load asset file success");
                    $jacocoInit[49] = true;
                }
                fontDO.setState(2);
                $jacocoInit[50] = true;
                fontDO.setTypeface(createFromAsset);
                $jacocoInit[51] = true;
            } else {
                WXLogUtils.e(TAG, "Font asset file not found " + fontDO.getUrl());
                $jacocoInit[52] = true;
            }
            $jacocoInit[53] = true;
        } catch (Exception e) {
            $jacocoInit[54] = true;
            WXLogUtils.e(TAG, e.toString());
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
    }

    public static void loadTypeface(FontDO fontDO) {
        boolean[] $jacocoInit = $jacocoInit();
        if (fontDO == null) {
            $jacocoInit[57] = true;
        } else if (fontDO.getTypeface() != null) {
            $jacocoInit[58] = true;
        } else {
            $jacocoInit[59] = true;
            if (fontDO.getState() == 3) {
                $jacocoInit[60] = true;
            } else if (fontDO.getState() != 0) {
                $jacocoInit[61] = true;
            } else {
                $jacocoInit[62] = true;
            }
            fontDO.setState(1);
            $jacocoInit[63] = true;
            if (fontDO.getType() == 3) {
                $jacocoInit[64] = true;
                Uri parse = Uri.parse(fontDO.getUrl());
                $jacocoInit[65] = true;
                loadFromAsset(fontDO, parse.getPath().substring(1));
                $jacocoInit[66] = true;
                $jacocoInit[67] = true;
            } else if (fontDO.getType() == 1) {
                $jacocoInit[68] = true;
                String url = fontDO.getUrl();
                $jacocoInit[69] = true;
                String fontFamilyName = fontDO.getFontFamilyName();
                $jacocoInit[70] = true;
                String md5 = WXFileUtils.md5(url);
                $jacocoInit[71] = true;
                File file = new File(getFontCacheDir());
                $jacocoInit[72] = true;
                if (file.exists()) {
                    $jacocoInit[73] = true;
                } else {
                    $jacocoInit[74] = true;
                    file.mkdirs();
                    $jacocoInit[75] = true;
                }
                String str = file.getAbsolutePath() + File.separator + md5;
                $jacocoInit[76] = true;
                if (loadLocalFontFile(str, fontFamilyName, false)) {
                    $jacocoInit[77] = true;
                } else {
                    $jacocoInit[78] = true;
                    downloadFontByNetwork(url, str, fontFamilyName);
                    $jacocoInit[79] = true;
                }
                $jacocoInit[80] = true;
            } else {
                if (fontDO.getType() == 2) {
                    $jacocoInit[81] = true;
                } else if (fontDO.getType() != 5) {
                    $jacocoInit[82] = true;
                } else {
                    $jacocoInit[83] = true;
                }
                if (loadLocalFontFile(fontDO.getUrl(), fontDO.getFontFamilyName(), false)) {
                    $jacocoInit[84] = true;
                } else {
                    $jacocoInit[85] = true;
                    fontDO.setState(3);
                    $jacocoInit[86] = true;
                }
            }
        }
        $jacocoInit[87] = true;
    }

    private static void downloadFontByNetwork(final String str, final String str2, final String str3) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXHttpAdapter iWXHttpAdapter = WXSDKManager.getInstance().getIWXHttpAdapter();
        if (iWXHttpAdapter == null) {
            $jacocoInit[88] = true;
            WXLogUtils.e(TAG, "downloadFontByNetwork() IWXHttpAdapter == null");
            $jacocoInit[89] = true;
            return;
        }
        WXRequest wXRequest = new WXRequest();
        wXRequest.url = str;
        wXRequest.method = "GET";
        $jacocoInit[90] = true;
        iWXHttpAdapter.sendRequest(wXRequest, new IWXHttpAdapter.OnHttpListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2632467828818294730L, "com/taobao/weex/utils/TypefaceUtil$1", 30);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onHttpStart() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    WXLogUtils.d(TypefaceUtil.TAG, "downloadFontByNetwork begin url:" + str);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }

            public void onHeadersReceived(int i, Map<String, List<String>> map) {
                $jacocoInit()[5] = true;
            }

            public void onHttpUploadProgress(int i) {
                $jacocoInit()[6] = true;
            }

            public void onHttpResponseProgress(int i) {
                $jacocoInit()[7] = true;
            }

            /* JADX WARNING: Removed duplicated region for block: B:12:0x004c  */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0051  */
            /* JADX WARNING: Removed duplicated region for block: B:28:0x00a6  */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x00ab  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onHttpFinish(com.taobao.weex.common.WXResponse r7) {
                /*
                    r6 = this;
                    boolean[] r0 = $jacocoInit()
                    r1 = 1
                    r2 = 8
                    r0[r2] = r1
                    java.lang.String r2 = r7.statusCode
                    boolean r2 = android.text.TextUtils.isEmpty(r2)
                    r3 = 0
                    if (r2 == 0) goto L_0x0018
                    r2 = 9
                    r0[r2] = r1
                L_0x0016:
                    r2 = 0
                    goto L_0x0048
                L_0x0018:
                    r2 = 10
                    r0[r2] = r1     // Catch:{ NumberFormatException -> 0x0027 }
                    java.lang.String r2 = r7.statusCode     // Catch:{ NumberFormatException -> 0x0027 }
                    int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0027 }
                    r4 = 11
                    r0[r4] = r1
                    goto L_0x0048
                L_0x0027:
                    r2 = 12
                    r0[r2] = r1
                    java.lang.String r2 = "TypefaceUtil"
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "IWXHttpAdapter onHttpFinish statusCode:"
                    r4.append(r5)
                    java.lang.String r5 = r7.statusCode
                    r4.append(r5)
                    java.lang.String r4 = r4.toString()
                    com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r2, (java.lang.String) r4)
                    r2 = 13
                    r0[r2] = r1
                    goto L_0x0016
                L_0x0048:
                    r4 = 200(0xc8, float:2.8E-43)
                    if (r2 >= r4) goto L_0x0051
                    r7 = 14
                    r0[r7] = r1
                    goto L_0x0062
                L_0x0051:
                    r4 = 299(0x12b, float:4.19E-43)
                    if (r2 <= r4) goto L_0x005a
                    r7 = 15
                    r0[r7] = r1
                    goto L_0x0062
                L_0x005a:
                    byte[] r2 = r7.originalData
                    if (r2 != 0) goto L_0x0067
                    r7 = 16
                    r0[r7] = r1
                L_0x0062:
                    r7 = 23
                    r0[r7] = r1
                    goto L_0x00a4
                L_0x0067:
                    r2 = 17
                    r0[r2] = r1
                    java.lang.String r2 = r6
                    byte[] r7 = r7.originalData
                    android.app.Application r3 = com.taobao.weex.WXEnvironment.getApplication()
                    boolean r3 = com.taobao.weex.utils.WXFileUtils.saveFile(r2, r7, r3)
                    if (r3 == 0) goto L_0x008a
                    r7 = 18
                    r0[r7] = r1
                    java.lang.String r7 = r6
                    java.lang.String r2 = r7
                    boolean r3 = com.taobao.weex.utils.TypefaceUtil.access$000(r7, r2, r1)
                    r7 = 19
                    r0[r7] = r1
                    goto L_0x00a4
                L_0x008a:
                    boolean r7 = com.taobao.weex.WXEnvironment.isApkDebugable()
                    if (r7 != 0) goto L_0x0095
                    r7 = 20
                    r0[r7] = r1
                    goto L_0x00a4
                L_0x0095:
                    r7 = 21
                    r0[r7] = r1
                    java.lang.String r7 = "TypefaceUtil"
                    java.lang.String r2 = "downloadFontByNetwork() onHttpFinish success, but save file failed."
                    com.taobao.weex.utils.WXLogUtils.d((java.lang.String) r7, (java.lang.String) r2)
                    r7 = 22
                    r0[r7] = r1
                L_0x00a4:
                    if (r3 == 0) goto L_0x00ab
                    r7 = 24
                    r0[r7] = r1
                    goto L_0x00ce
                L_0x00ab:
                    r7 = 25
                    r0[r7] = r1
                    java.util.Map r7 = com.taobao.weex.utils.TypefaceUtil.access$100()
                    java.lang.String r2 = r7
                    java.lang.Object r7 = r7.get(r2)
                    com.taobao.weex.utils.FontDO r7 = (com.taobao.weex.utils.FontDO) r7
                    if (r7 != 0) goto L_0x00c2
                    r7 = 26
                    r0[r7] = r1
                    goto L_0x00ce
                L_0x00c2:
                    r2 = 27
                    r0[r2] = r1
                    r2 = 3
                    r7.setState(r2)
                    r7 = 28
                    r0[r7] = r1
                L_0x00ce:
                    r7 = 29
                    r0[r7] = r1
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.TypefaceUtil.AnonymousClass1.onHttpFinish(com.taobao.weex.common.WXResponse):void");
            }
        });
        $jacocoInit[91] = true;
    }

    private static boolean loadLocalFontFile(String str, final String str2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[92] = true;
        } else if (!TextUtils.isEmpty(str2)) {
            $jacocoInit[93] = true;
            try {
                File file = new File(str);
                $jacocoInit[96] = true;
                if (file.exists()) {
                    $jacocoInit[97] = true;
                    Typeface createFromFile = Typeface.createFromFile(str);
                    if (createFromFile != null) {
                        $jacocoInit[99] = true;
                        FontDO fontDO = sCacheMap.get(str2);
                        if (fontDO == null) {
                            $jacocoInit[100] = true;
                            $jacocoInit[113] = true;
                        } else {
                            $jacocoInit[101] = true;
                            fontDO.setState(2);
                            $jacocoInit[102] = true;
                            fontDO.setTypeface(createFromFile);
                            $jacocoInit[103] = true;
                            if (!WXEnvironment.isApkDebugable()) {
                                $jacocoInit[104] = true;
                            } else {
                                $jacocoInit[105] = true;
                                WXLogUtils.d(TAG, "load local font file success");
                                $jacocoInit[106] = true;
                            }
                            if (z) {
                                $jacocoInit[107] = true;
                                WXSDKManager.getInstance().getWXRenderManager().postOnUiThread((Runnable) new Runnable() {
                                    private static transient /* synthetic */ boolean[] $jacocoData;

                                    private static /* synthetic */ boolean[] $jacocoInit() {
                                        boolean[] zArr = $jacocoData;
                                        if (zArr != null) {
                                            return zArr;
                                        }
                                        boolean[] a2 = Offline.a(7718011995683149271L, "com/taobao/weex/utils/TypefaceUtil$2", 4);
                                        $jacocoData = a2;
                                        return a2;
                                    }

                                    {
                                        boolean[] $jacocoInit = $jacocoInit();
                                        $jacocoInit[0] = true;
                                    }

                                    public void run() {
                                        boolean[] $jacocoInit = $jacocoInit();
                                        Intent intent = new Intent(TypefaceUtil.ACTION_TYPE_FACE_AVAILABLE);
                                        $jacocoInit[1] = true;
                                        intent.putExtra("fontFamily", str2);
                                        $jacocoInit[2] = true;
                                        LocalBroadcastManager.getInstance(WXEnvironment.getApplication()).sendBroadcast(intent);
                                        $jacocoInit[3] = true;
                                    }
                                }, 100);
                                $jacocoInit[108] = true;
                            } else {
                                Intent intent = new Intent(ACTION_TYPE_FACE_AVAILABLE);
                                $jacocoInit[109] = true;
                                intent.putExtra("fontFamily", str2);
                                $jacocoInit[110] = true;
                                LocalBroadcastManager.getInstance(WXEnvironment.getApplication()).sendBroadcast(intent);
                                $jacocoInit[111] = true;
                            }
                            $jacocoInit[112] = true;
                            return true;
                        }
                    } else {
                        WXLogUtils.e(TAG, "load local font file failed, can't create font.");
                        $jacocoInit[114] = true;
                    }
                    $jacocoInit[115] = true;
                    $jacocoInit[118] = true;
                    return false;
                }
                $jacocoInit[98] = true;
                return false;
            } catch (Exception e) {
                $jacocoInit[116] = true;
                WXLogUtils.e(TAG, e.toString());
                $jacocoInit[117] = true;
            }
        } else {
            $jacocoInit[94] = true;
        }
        $jacocoInit[95] = true;
        return false;
    }

    private static String getFontCacheDir() {
        String str = WXEnvironment.getApplication().getCacheDir() + "/" + FONT_CACHE_DIR_NAME;
        $jacocoInit()[119] = true;
        return str;
    }
}
