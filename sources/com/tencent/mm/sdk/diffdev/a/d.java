package com.tencent.mm.sdk.diffdev.a;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.tencent.mm.sdk.diffdev.OAuthErrCode;
import com.tencent.mm.sdk.diffdev.OAuthListener;
import java.io.File;
import org.json.JSONObject;

public final class d extends AsyncTask<Void, Void, a> {
    /* access modifiers changed from: private */
    public static final boolean ai = (Environment.getExternalStorageState().equals("mounted") && new File(Environment.getExternalStorageDirectory().getAbsolutePath()).canWrite());
    /* access modifiers changed from: private */
    public static final String aj = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/tencent/MicroMsg/oauth_qrcode.png");
    private static String ak;
    private String al;
    private String am;
    private OAuthListener an;
    private f ao;
    private String appId;
    private String scope;
    private String signature;

    static class a {
        public OAuthErrCode ap;
        public String aq;
        public String ar;
        public String as;
        public int at;
        public String au;
        public byte[] av;

        private a() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0032 A[SYNTHETIC, Splitter:B:20:0x0032] */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x003e A[SYNTHETIC, Splitter:B:25:0x003e] */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean a(java.lang.String r2, byte[] r3) {
            /*
                r0 = 0
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0025 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0025 }
                r1.write(r3)     // Catch:{ Exception -> 0x0020, all -> 0x001d }
                r1.flush()     // Catch:{ Exception -> 0x0020, all -> 0x001d }
                r1.close()     // Catch:{ IOException -> 0x0010 }
                goto L_0x0014
            L_0x0010:
                r2 = move-exception
                r2.printStackTrace()
            L_0x0014:
                java.lang.String r2 = "MicroMsg.SDK.GetQRCodeResult"
                java.lang.String r3 = "writeToFile ok!"
                android.util.Log.d(r2, r3)
                r2 = 1
                return r2
            L_0x001d:
                r2 = move-exception
                r0 = r1
                goto L_0x003c
            L_0x0020:
                r2 = move-exception
                r0 = r1
                goto L_0x0026
            L_0x0023:
                r2 = move-exception
                goto L_0x003c
            L_0x0025:
                r2 = move-exception
            L_0x0026:
                r2.printStackTrace()     // Catch:{ all -> 0x0023 }
                java.lang.String r2 = "MicroMsg.SDK.GetQRCodeResult"
                java.lang.String r3 = "write to file error"
                android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0023 }
                if (r0 == 0) goto L_0x003a
                r0.close()     // Catch:{ IOException -> 0x0036 }
                goto L_0x003a
            L_0x0036:
                r2 = move-exception
                r2.printStackTrace()
            L_0x003a:
                r2 = 0
                return r2
            L_0x003c:
                if (r0 == 0) goto L_0x0046
                r0.close()     // Catch:{ IOException -> 0x0042 }
                goto L_0x0046
            L_0x0042:
                r3 = move-exception
                r3.printStackTrace()
            L_0x0046:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.sdk.diffdev.a.d.a.a(java.lang.String, byte[]):boolean");
        }

        public static a d(byte[] bArr) {
            OAuthErrCode oAuthErrCode;
            String str;
            String str2;
            Object[] objArr;
            a aVar = new a();
            if (bArr == null || bArr.length == 0) {
                Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, buf is null");
                oAuthErrCode = OAuthErrCode.WechatAuth_Err_NetworkErr;
            } else {
                try {
                } catch (Exception e) {
                    str = "MicroMsg.SDK.GetQRCodeResult";
                    str2 = "parse fail, build String fail, ex = %s";
                    objArr = new Object[]{e.getMessage()};
                    Log.e(str, String.format(str2, objArr));
                    oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                    aVar.ap = oAuthErrCode;
                    return aVar;
                }
                try {
                    JSONObject jSONObject = new JSONObject(new String(bArr, "utf-8"));
                    int i = jSONObject.getInt("errcode");
                    if (i != 0) {
                        Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("resp errcode = %d", new Object[]{Integer.valueOf(i)}));
                        aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                        aVar.at = i;
                        aVar.au = jSONObject.optString("errmsg");
                        return aVar;
                    }
                    String string = jSONObject.getJSONObject("qrcode").getString("qrcodebase64");
                    if (string != null) {
                        if (string.length() != 0) {
                            byte[] decode = Base64.decode(string, 0);
                            if (decode != null) {
                                if (decode.length != 0) {
                                    if (d.ai) {
                                        File file = new File(d.aj);
                                        file.mkdirs();
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        if (!a(d.aj, decode)) {
                                            Log.e("MicroMsg.SDK.GetQRCodeResult", String.format("writeToFile fail, qrcodeBuf length = %d", new Object[]{Integer.valueOf(decode.length)}));
                                            aVar.ap = OAuthErrCode.WechatAuth_Err_NormalErr;
                                            return aVar;
                                        }
                                        aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                        aVar.as = d.aj;
                                        aVar.aq = jSONObject.getString("uuid");
                                        aVar.ar = jSONObject.getString("appname");
                                        Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in external storage, uuid = %s, appname = %s, imgPath = %s", new Object[]{aVar.aq, aVar.ar, aVar.as}));
                                        return aVar;
                                    }
                                    aVar.ap = OAuthErrCode.WechatAuth_Err_OK;
                                    aVar.av = decode;
                                    aVar.aq = jSONObject.getString("uuid");
                                    aVar.ar = jSONObject.getString("appname");
                                    Log.d("MicroMsg.SDK.GetQRCodeResult", String.format("parse succ, save in memory, uuid = %s, appname = %s, imgBufLength = %d", new Object[]{aVar.aq, aVar.ar, Integer.valueOf(aVar.av.length)}));
                                    return aVar;
                                }
                            }
                            Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBuf is null");
                            aVar.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                            return aVar;
                        }
                    }
                    Log.e("MicroMsg.SDK.GetQRCodeResult", "parse fail, qrcodeBase64 is null");
                    aVar.ap = OAuthErrCode.WechatAuth_Err_JsonDecodeErr;
                    return aVar;
                } catch (Exception e2) {
                    str = "MicroMsg.SDK.GetQRCodeResult";
                    str2 = "parse json fail, ex = %s";
                    objArr = new Object[]{e2.getMessage()};
                    Log.e(str, String.format(str2, objArr));
                    oAuthErrCode = OAuthErrCode.WechatAuth_Err_NormalErr;
                    aVar.ap = oAuthErrCode;
                    return aVar;
                }
            }
            aVar.ap = oAuthErrCode;
            return aVar;
        }
    }

    static {
        ak = null;
        ak = "http://open.weixin.qq.com/connect/sdk/qrconnect?appid=%s&noncestr=%s&timestamp=%s&scope=%s&signature=%s";
    }

    public d(String str, String str2, String str3, String str4, String str5, OAuthListener oAuthListener) {
        this.appId = str;
        this.scope = str2;
        this.al = str3;
        this.am = str4;
        this.signature = str5;
        this.an = oAuthListener;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        Log.i("MicroMsg.SDK.GetQRCodeTask", "external storage available = " + ai);
        String format = String.format(ak, new Object[]{this.appId, this.al, this.am, this.scope, this.signature});
        long currentTimeMillis = System.currentTimeMillis();
        byte[] b = e.b(format, -1);
        Log.d("MicroMsg.SDK.GetQRCodeTask", String.format("doInBackground, url = %s, time consumed = %d(ms)", new Object[]{format, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return a.d(b);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        a aVar = (a) obj;
        if (aVar.ap == OAuthErrCode.WechatAuth_Err_OK) {
            Log.d("MicroMsg.SDK.GetQRCodeTask", "onPostExecute, get qrcode success");
            this.an.onAuthGotQrcode(aVar.as, aVar.av);
            this.ao = new f(aVar.aq, this.an);
            f fVar = this.ao;
            if (Build.VERSION.SDK_INT >= 11) {
                fVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else {
                fVar.execute(new Void[0]);
            }
        } else {
            Log.e("MicroMsg.SDK.GetQRCodeTask", String.format("onPostExecute, get qrcode fail, OAuthErrCode = %s", new Object[]{aVar.ap}));
            this.an.onAuthFinish(aVar.ap, (String) null);
        }
    }

    public final boolean q() {
        Log.i("MicroMsg.SDK.GetQRCodeTask", "cancelTask");
        return this.ao == null ? cancel(true) : this.ao.cancel(true);
    }
}
