package com.taobao.weex.appfram.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.xiaomi.youpin.share.model.ShareChannel;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXClipboardModule extends WXModule implements IWXClipboard {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String DATA = "data";
    private static final String RESULT = "result";
    private static final String RESULT_FAILED = "failed";
    private static final String RESULT_OK = "success";
    private final String CLIP_KEY = "WEEX_CLIP_KEY_MAIN";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5722756441368442696L, "com/taobao/weex/appfram/clipboard/WXClipboardModule", 74);
        $jacocoData = a2;
        return a2;
    }

    public WXClipboardModule() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    @JSMethod
    public void setString(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[1] = true;
            return;
        }
        Context context = this.mWXSDKInstance.getContext();
        $jacocoInit[2] = true;
        $jacocoInit[3] = true;
        ClipData newPlainText = ClipData.newPlainText("WEEX_CLIP_KEY_MAIN", str);
        $jacocoInit[4] = true;
        ((ClipboardManager) context.getSystemService(ShareChannel.d)).setPrimaryClip(newPlainText);
        $jacocoInit[5] = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x009d  */
    @com.taobao.weex.annotation.JSMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getString(@android.support.annotation.Nullable com.taobao.weex.bridge.JSCallback r8) {
        /*
            r7 = this;
            boolean[] r0 = $jacocoInit()
            com.taobao.weex.WXSDKInstance r1 = r7.mWXSDKInstance
            android.content.Context r1 = r1.getContext()
            r2 = 1
            r3 = 6
            r0[r3] = r2
            java.lang.String r3 = "clipboard"
            java.lang.Object r3 = r1.getSystemService(r3)
            android.content.ClipboardManager r3 = (android.content.ClipboardManager) r3
            r4 = 7
            r0[r4] = r2
            java.util.HashMap r4 = new java.util.HashMap
            r5 = 2
            r4.<init>(r5)
            r5 = 8
            r0[r5] = r2
            android.content.ClipData r3 = r3.getPrimaryClip()
            r5 = 9
            r0[r5] = r2
            if (r3 != 0) goto L_0x0032
            r1 = 10
            r0[r1] = r2
            goto L_0x003c
        L_0x0032:
            int r5 = r3.getItemCount()
            if (r5 > 0) goto L_0x0053
            r1 = 11
            r0[r1] = r2
        L_0x003c:
            java.lang.String r1 = "result"
            java.lang.String r3 = "failed"
            r4.put(r1, r3)
            r1 = 21
            r0[r1] = r2
            java.lang.String r1 = "data"
            java.lang.String r3 = ""
            r4.put(r1, r3)
            r1 = 22
            r0[r1] = r2
            goto L_0x0096
        L_0x0053:
            r5 = 12
            r0[r5] = r2
            r5 = 0
            android.content.ClipData$Item r3 = r3.getItemAt(r5)
            r5 = 13
            r0[r5] = r2
            java.lang.CharSequence r1 = r7.coerceToText(r1, r3)
            r3 = 14
            r0[r3] = r2
            java.lang.String r3 = "result"
            if (r1 == 0) goto L_0x0073
            java.lang.String r5 = "success"
            r6 = 15
            r0[r6] = r2
            goto L_0x0079
        L_0x0073:
            java.lang.String r5 = "failed"
            r6 = 16
            r0[r6] = r2
        L_0x0079:
            r4.put(r3, r5)
            r3 = 17
            r0[r3] = r2
            java.lang.String r3 = "data"
            if (r1 == 0) goto L_0x0089
            r5 = 18
            r0[r5] = r2
            goto L_0x008f
        L_0x0089:
            java.lang.String r1 = ""
            r5 = 19
            r0[r5] = r2
        L_0x008f:
            r4.put(r3, r1)
            r1 = 20
            r0[r1] = r2
        L_0x0096:
            if (r8 != 0) goto L_0x009d
            r8 = 23
            r0[r8] = r2
            goto L_0x00a8
        L_0x009d:
            r1 = 24
            r0[r1] = r2
            r8.invoke(r4)
            r8 = 25
            r0[r8] = r2
        L_0x00a8:
            r8 = 26
            r0[r8] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.clipboard.WXClipboardModule.getString(com.taobao.weex.bridge.JSCallback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0131  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.CharSequence coerceToText(android.content.Context r9, android.content.ClipData.Item r10) {
        /*
            r8 = this;
            boolean[] r0 = $jacocoInit()
            java.lang.CharSequence r1 = r10.getText()
            r2 = 1
            if (r1 == 0) goto L_0x0010
            r9 = 27
            r0[r9] = r2
            return r1
        L_0x0010:
            android.net.Uri r1 = r10.getUri()
            r3 = 0
            if (r1 == 0) goto L_0x014a
            r10 = 28
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0111, IOException -> 0x009f, all -> 0x009b }
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0111, IOException -> 0x009f, all -> 0x009b }
            java.lang.String r10 = "text/*"
            android.content.res.AssetFileDescriptor r9 = r9.openTypedAssetFileDescriptor(r1, r10, r3)     // Catch:{ FileNotFoundException -> 0x0111, IOException -> 0x009f, all -> 0x009b }
            r10 = 29
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0111, IOException -> 0x009f, all -> 0x009b }
            java.io.FileInputStream r9 = r9.createInputStream()     // Catch:{ FileNotFoundException -> 0x0111, IOException -> 0x009f, all -> 0x009b }
            r10 = 30
            r0[r10] = r2     // Catch:{ FileNotFoundException -> 0x0098, IOException -> 0x0093, all -> 0x0090 }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0098, IOException -> 0x0093, all -> 0x0090 }
            java.lang.String r4 = "UTF-8"
            r10.<init>(r9, r4)     // Catch:{ FileNotFoundException -> 0x0098, IOException -> 0x0093, all -> 0x0090 }
            r3 = 31
            r0[r3] = r2     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r4 = 128(0x80, float:1.794E-43)
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r4 = 8192(0x2000, float:1.14794E-41)
            char[] r4 = new char[r4]     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r5 = 32
            r0[r5] = r2     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
        L_0x004b:
            int r5 = r10.read(r4)     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            if (r5 <= 0) goto L_0x005e
            r6 = 33
            r0[r6] = r2     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r6 = 0
            r3.append(r4, r6, r5)     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r5 = 34
            r0[r5] = r2     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            goto L_0x004b
        L_0x005e:
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0113, IOException -> 0x008e }
            r1 = 36
            r0[r1] = r2     // Catch:{ IOException -> 0x006e }
            r10.close()     // Catch:{ IOException -> 0x006e }
            r10 = 37
            r0[r10] = r2
            goto L_0x0072
        L_0x006e:
            r10 = 38
            r0[r10] = r2
        L_0x0072:
            if (r9 != 0) goto L_0x0079
            r9 = 39
            r0[r9] = r2
            goto L_0x0089
        L_0x0079:
            r10 = 40
            r0[r10] = r2     // Catch:{ IOException -> 0x0085 }
            r9.close()     // Catch:{ IOException -> 0x0085 }
            r9 = 41
            r0[r9] = r2
            goto L_0x0089
        L_0x0085:
            r9 = 42
            r0[r9] = r2
        L_0x0089:
            r9 = 43
            r0[r9] = r2
            return r3
        L_0x008e:
            r3 = move-exception
            goto L_0x00a3
        L_0x0090:
            r1 = move-exception
            r10 = r3
            goto L_0x00de
        L_0x0093:
            r10 = move-exception
            r7 = r3
            r3 = r10
            r10 = r7
            goto L_0x00a3
        L_0x0098:
            r10 = r3
            goto L_0x0113
        L_0x009b:
            r1 = move-exception
            r9 = r3
            r10 = r9
            goto L_0x00de
        L_0x009f:
            r9 = move-exception
            r10 = r3
            r3 = r9
            r9 = r10
        L_0x00a3:
            r4 = 52
            r0[r4] = r2     // Catch:{ all -> 0x00dd }
            java.lang.String r4 = "ClippedData Failure loading text."
            com.taobao.weex.utils.WXLogUtils.w((java.lang.String) r4, (java.lang.Throwable) r3)     // Catch:{ all -> 0x00dd }
            if (r10 != 0) goto L_0x00b3
            r10 = 53
            r0[r10] = r2
            goto L_0x00c3
        L_0x00b3:
            r3 = 54
            r0[r3] = r2     // Catch:{ IOException -> 0x00bf }
            r10.close()     // Catch:{ IOException -> 0x00bf }
            r10 = 55
            r0[r10] = r2
            goto L_0x00c3
        L_0x00bf:
            r10 = 56
            r0[r10] = r2
        L_0x00c3:
            if (r9 != 0) goto L_0x00cb
            r9 = 57
            r0[r9] = r2
            goto L_0x0141
        L_0x00cb:
            r10 = 58
            r0[r10] = r2     // Catch:{ IOException -> 0x00d8 }
            r9.close()     // Catch:{ IOException -> 0x00d8 }
            r9 = 59
            r0[r9] = r2
            goto L_0x0141
        L_0x00d8:
            r9 = 60
            r0[r9] = r2
            goto L_0x0141
        L_0x00dd:
            r1 = move-exception
        L_0x00de:
            if (r10 != 0) goto L_0x00e5
            r10 = 61
            r0[r10] = r2
            goto L_0x00f5
        L_0x00e5:
            r3 = 62
            r0[r3] = r2     // Catch:{ IOException -> 0x00f1 }
            r10.close()     // Catch:{ IOException -> 0x00f1 }
            r10 = 63
            r0[r10] = r2
            goto L_0x00f5
        L_0x00f1:
            r10 = 64
            r0[r10] = r2
        L_0x00f5:
            if (r9 != 0) goto L_0x00fc
            r9 = 65
            r0[r9] = r2
            goto L_0x010c
        L_0x00fc:
            r10 = 66
            r0[r10] = r2     // Catch:{ IOException -> 0x0108 }
            r9.close()     // Catch:{ IOException -> 0x0108 }
            r9 = 67
            r0[r9] = r2
            goto L_0x010c
        L_0x0108:
            r9 = 68
            r0[r9] = r2
        L_0x010c:
            r9 = 69
            r0[r9] = r2
            throw r1
        L_0x0111:
            r9 = r3
            r10 = r9
        L_0x0113:
            if (r10 != 0) goto L_0x011a
            r10 = 44
            r0[r10] = r2
            goto L_0x012a
        L_0x011a:
            r3 = 45
            r0[r3] = r2     // Catch:{ IOException -> 0x0126 }
            r10.close()     // Catch:{ IOException -> 0x0126 }
            r10 = 46
            r0[r10] = r2
            goto L_0x012a
        L_0x0126:
            r10 = 47
            r0[r10] = r2
        L_0x012a:
            if (r9 != 0) goto L_0x0131
            r9 = 48
            r0[r9] = r2
            goto L_0x0141
        L_0x0131:
            r10 = 49
            r0[r10] = r2     // Catch:{ IOException -> 0x013d }
            r9.close()     // Catch:{ IOException -> 0x013d }
            r9 = 50
            r0[r9] = r2
            goto L_0x0141
        L_0x013d:
            r9 = 51
            r0[r9] = r2
        L_0x0141:
            java.lang.String r9 = r1.toString()
            r10 = 70
            r0[r10] = r2
            return r9
        L_0x014a:
            android.content.Intent r9 = r10.getIntent()
            if (r9 == 0) goto L_0x015d
            r10 = 71
            r0[r10] = r2
            java.lang.String r9 = r9.toUri(r2)
            r10 = 72
            r0[r10] = r2
            return r9
        L_0x015d:
            r9 = 73
            r0[r9] = r2
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.appfram.clipboard.WXClipboardModule.coerceToText(android.content.Context, android.content.ClipData$Item):java.lang.CharSequence");
    }
}
