package com.alipay.mobile.security.zim.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import cn.com.fmsh.communication.message.constants.Constants;
import com.alipay.biometrics.ui.widget.LoadingProgressDialog;
import com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse;
import com.alipay.bis.common.service.facade.gw.zim.ZimValidateGwResponse;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.alipay.mobile.security.bio.api.BioDetectorBuilder;
import com.alipay.mobile.security.bio.api.BioParameter;
import com.alipay.mobile.security.bio.api.BioProgressCallback;
import com.alipay.mobile.security.bio.module.MicroModule;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioUploadServiceCore;
import com.alipay.mobile.security.bio.service.ZimRecordService;
import com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.mobile.security.zim.api.ZIMCallback;
import com.alipay.mobile.security.zim.api.ZIMFacade;
import com.alipay.mobile.security.zim.api.ZIMResponse;
import com.alipay.mobile.security.zim.api.ZimProgressCallback;
import com.alipay.mobile.security.zim.gw.a;
import com.alipay.mobile.security.zim.gw.b;
import com.alipay.mobile.security.zim.gw.c;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class d extends ZIMFacade implements BioProgressCallback, c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1046a = Boolean.TRUE.toString();
    public static final String b = Boolean.FALSE.toString();
    private Context c;
    private ZIMCallback d;
    private String e = "";
    private Map<String, String> f;
    private String g;
    private a h;
    private BioDetector i;
    /* access modifiers changed from: private */
    public LoadingProgressDialog j;
    private BioServiceManager k;
    private boolean l;
    private BioParameter m = new BioParameter();

    public d(Context context) {
        this.c = context;
        try {
            Constructor<?> constructor = Class.forName(Env.getProtocolFormat(context) != 2 ? "com.alipay.mobile.security.zim.gw.JsonGwService" : "com.alipay.mobile.security.zim.gw.PbGwService").getConstructor(new Class[]{c.class});
            constructor.setAccessible(true);
            this.h = (a) constructor.newInstance(new Object[]{this});
        } catch (Throwable th) {
            BioLog.e(th);
            ZIMResponse zIMResponse = new ZIMResponse();
            zIMResponse.code = 1001;
            zIMResponse.extInfo.put(ZIMFacade.KEY_BIO_ACTION, String.valueOf(206));
            a(zIMResponse);
        }
    }

    private void b(Context context, String str) {
        if (context instanceof Activity) {
            this.j = new LoadingProgressDialog(context);
        }
        this.i = BioDetectorBuilder.create(context, new MicroModule(str));
        this.k = BioServiceManager.getCurrentInstance();
        ApSecurityService apSecurityService = (ApSecurityService) this.k.getBioService(ApSecurityService.class);
        if (apSecurityService == null) {
            BioLog.i("BioTransfer.buildBioParameter(): null == ApSecurityService");
            return;
        }
        BioLog.i("BioTransfer.buildBioParameter(): ApSecurityService.init()");
        apSecurityService.init(this.c);
    }

    public ZimInitGwResponse parse(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.h.convert(str);
        }
        return null;
    }

    public void verify(String str, Map<String, String> map, ZIMCallback zIMCallback) {
        ZimInitGwResponse zimInitGwResponse = null;
        if (map != null) {
            if (map.containsKey(ZIMFacade.KEY_INIT_RESP)) {
                zimInitGwResponse = parse(map.remove(ZIMFacade.KEY_INIT_RESP));
            }
            if (zimInitGwResponse != null) {
                map.remove(ZIMFacade.KEY_INIT_RESP_OLD);
            } else if (map.containsKey(ZIMFacade.KEY_INIT_RESP_OLD)) {
                zimInitGwResponse = parse(map.remove(ZIMFacade.KEY_INIT_RESP_OLD));
            }
        }
        verify(str, zimInitGwResponse, map, zIMCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        r4.e = r5;
        r4.f = r7;
        r4.d = r8;
        com.alipay.mobile.security.bio.utils.BioLog.d("verify(zimId=" + r5 + ", params=" + com.alipay.mobile.security.bio.utils.StringUtil.map2String(r7) + ", callback=" + r8 + com.taobao.weex.el.parse.Operators.BRACKET_END_STR);
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0072, code lost:
        if (r7 == null) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007a, code lost:
        if (r7.containsKey(com.alipay.mobile.security.zim.api.ZIMFacade.KEY_ENV_NAME) == false) goto L_0x0085;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007c, code lost:
        r8 = r7.remove(com.alipay.mobile.security.zim.api.ZIMFacade.KEY_ENV_NAME);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0085, code lost:
        a(r4.c, r8);
        b(r4.c, r5);
        r7 = com.alipay.mobile.security.zim.a.a.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0093, code lost:
        if (r7 != null) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0095, code lost:
        r7 = com.alipay.mobile.security.zim.a.a.a(r4.c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009b, code lost:
        r7.a(r5);
        r7.b(com.alipay.mobile.security.zim.a.a.f1043a);
        r7.b(com.alipay.mobile.security.zim.a.a.b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a9, code lost:
        if (r6 == null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ac, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ad, code lost:
        r1 = new java.util.HashMap();
        r2 = java.lang.Boolean.toString(r0);
        r1.put(com.alipay.mobile.security.bio.api.BioDetector.EXT_KEY_IS_MOCK, r2);
        r7.a(com.alipay.mobile.security.zim.a.a.c, (java.util.Map<java.lang.String, java.lang.String>) r1);
        r4.m.addExtProperty(com.alipay.mobile.security.bio.api.BioDetector.EXT_KEY_IS_MOCK, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c7, code lost:
        if (r0 == false) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c9, code lost:
        a(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cf, code lost:
        if (r4.j == null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00d7, code lost:
        if (r4.j.isShowing() != false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d9, code lost:
        ((android.app.Activity) r4.c).runOnUiThread(new com.alipay.mobile.security.zim.a.e(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e5, code lost:
        r6 = new com.alipay.bis.common.service.facade.gw.zim.ZimInitGwRequest();
        r6.zimId = r5;
        r6.metaInfo = a(r4.c, false);
        com.alipay.mobile.security.bio.utils.BioLog.i("zolozTime", "smiletopay get protocol begin");
        r4.h.init(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void verify(java.lang.String r5, com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse r6, java.util.Map<java.lang.String, java.lang.String> r7, com.alipay.mobile.security.zim.api.ZIMCallback r8) {
        /*
            r4 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x010d
            if (r8 == 0) goto L_0x0105
            monitor-enter(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0102 }
            r0.<init>()     // Catch:{ all -> 0x0102 }
            java.lang.String r1 = "zim is busy : "
            r0.append(r1)     // Catch:{ all -> 0x0102 }
            boolean r1 = r4.l     // Catch:{ all -> 0x0102 }
            r0.append(r1)     // Catch:{ all -> 0x0102 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0102 }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r0)     // Catch:{ all -> 0x0102 }
            boolean r0 = r4.l     // Catch:{ all -> 0x0102 }
            if (r0 == 0) goto L_0x0039
            com.alipay.mobile.security.zim.api.ZIMResponse r5 = new com.alipay.mobile.security.zim.api.ZIMResponse     // Catch:{ all -> 0x0102 }
            r5.<init>()     // Catch:{ all -> 0x0102 }
            r6 = 2006(0x7d6, float:2.811E-42)
            r5.code = r6     // Catch:{ all -> 0x0102 }
            java.lang.String r6 = "busy"
            r5.reason = r6     // Catch:{ all -> 0x0102 }
            r4.b((com.alipay.mobile.security.zim.api.ZIMResponse) r5)     // Catch:{ all -> 0x0102 }
            r8.response(r5)     // Catch:{ all -> 0x0102 }
            monitor-exit(r4)     // Catch:{ all -> 0x0102 }
            return
        L_0x0039:
            r0 = 1
            r4.l = r0     // Catch:{ all -> 0x0102 }
            monitor-exit(r4)     // Catch:{ all -> 0x0102 }
            r4.e = r5
            r4.f = r7
            r4.d = r8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "verify(zimId="
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = ", params="
            r1.append(r2)
            java.lang.String r2 = com.alipay.mobile.security.bio.utils.StringUtil.map2String(r7)
            r1.append(r2)
            java.lang.String r2 = ", callback="
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = ")"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            com.alipay.mobile.security.bio.utils.BioLog.d(r8)
            r8 = 0
            if (r7 == 0) goto L_0x0085
            java.lang.String r1 = "env_name"
            boolean r1 = r7.containsKey(r1)
            if (r1 == 0) goto L_0x0085
            java.lang.String r8 = "env_name"
            java.lang.Object r7 = r7.remove(r8)
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
        L_0x0085:
            android.content.Context r7 = r4.c
            a((android.content.Context) r7, (java.lang.String) r8)
            android.content.Context r7 = r4.c
            r4.b(r7, r5)
            com.alipay.mobile.security.zim.a.a r7 = com.alipay.mobile.security.zim.a.a.a()
            if (r7 != 0) goto L_0x009b
            android.content.Context r7 = r4.c
            com.alipay.mobile.security.zim.a.a r7 = com.alipay.mobile.security.zim.a.a.a((android.content.Context) r7)
        L_0x009b:
            r7.a((java.lang.String) r5)
            java.lang.String r8 = com.alipay.mobile.security.zim.a.a.f1043a
            r7.b(r8)
            java.lang.String r8 = com.alipay.mobile.security.zim.a.a.b
            r7.b(r8)
            r8 = 0
            if (r6 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            r0 = 0
        L_0x00ad:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.String r2 = java.lang.Boolean.toString(r0)
            java.lang.String r3 = "mock"
            r1.put(r3, r2)
            java.lang.String r3 = com.alipay.mobile.security.zim.a.a.c
            r7.a((java.lang.String) r3, (java.util.Map<java.lang.String, java.lang.String>) r1)
            com.alipay.mobile.security.bio.api.BioParameter r7 = r4.m
            java.lang.String r1 = "mock"
            r7.addExtProperty(r1, r2)
            if (r0 == 0) goto L_0x00cd
            r4.a((com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse) r6)
            goto L_0x0101
        L_0x00cd:
            com.alipay.biometrics.ui.widget.LoadingProgressDialog r6 = r4.j
            if (r6 == 0) goto L_0x00e5
            com.alipay.biometrics.ui.widget.LoadingProgressDialog r6 = r4.j
            boolean r6 = r6.isShowing()
            if (r6 != 0) goto L_0x00e5
            android.content.Context r6 = r4.c
            android.app.Activity r6 = (android.app.Activity) r6
            com.alipay.mobile.security.zim.a.e r7 = new com.alipay.mobile.security.zim.a.e
            r7.<init>(r4)
            r6.runOnUiThread(r7)
        L_0x00e5:
            com.alipay.bis.common.service.facade.gw.zim.ZimInitGwRequest r6 = new com.alipay.bis.common.service.facade.gw.zim.ZimInitGwRequest
            r6.<init>()
            r6.zimId = r5
            android.content.Context r5 = r4.c
            java.lang.String r5 = a((android.content.Context) r5, (boolean) r8)
            r6.metaInfo = r5
            java.lang.String r5 = "zolozTime"
            java.lang.String r7 = "smiletopay get protocol begin"
            com.alipay.mobile.security.bio.utils.BioLog.i(r5, r7)
            com.alipay.mobile.security.zim.gw.a r5 = r4.h
            r5.init(r6)
        L_0x0101:
            return
        L_0x0102:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0102 }
            throw r5
        L_0x0105:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "ZIMCallback cant be null"
            r5.<init>(r6)
            throw r5
        L_0x010d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "zimId cant be null"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.zim.a.d.verify(java.lang.String, com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse, java.util.Map, com.alipay.mobile.security.zim.api.ZIMCallback):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResult(com.alipay.mobile.security.bio.api.BioResponse r9) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "ZimPlatform.onResult(), response="
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.alipay.mobile.security.bio.utils.BioLog.i(r0)
            java.util.HashMap r0 = new java.util.HashMap
            r1 = 2
            r0.<init>(r1)
            java.lang.String r1 = "result"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            boolean r3 = r9.isSuccess()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            java.lang.String r1 = "message"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.lang.String r3 = r9.getResultMessage()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            java.lang.String r1 = "retCode"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            int r3 = r9.getResult()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            java.lang.String r1 = "subCode"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.lang.String r3 = r9.subCode
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            java.lang.String r1 = "subMsg"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ""
            r2.append(r3)
            java.lang.String r3 = r9.subMsg
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.put(r1, r2)
            com.alipay.mobile.security.zim.a.a r1 = com.alipay.mobile.security.zim.a.a.a()
            java.lang.String r2 = com.alipay.mobile.security.zim.a.a.f
            r1.a((java.lang.String) r2, (java.util.Map<java.lang.String, java.lang.String>) r0)
            java.lang.String r0 = r8.e
            java.lang.String r1 = "_bis"
            boolean r0 = r0.contains(r1)
            r1 = 209(0xd1, float:2.93E-43)
            r2 = 0
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x0163
            java.util.Map r0 = r9.getExt()
            if (r0 == 0) goto L_0x0172
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x0172
            java.lang.String r5 = "upload_response"
            boolean r5 = r0.containsKey(r5)
            if (r5 == 0) goto L_0x0172
            java.lang.String r4 = "upload_response"
            java.lang.Object r4 = r0.remove(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Class<com.alipay.mobile.security.bio.service.BioUploadResult> r5 = com.alipay.mobile.security.bio.service.BioUploadResult.class
            java.lang.Object r4 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r4, r5)
            com.alipay.mobile.security.bio.service.BioUploadResult r4 = (com.alipay.mobile.security.bio.service.BioUploadResult) r4
            com.alipay.mobile.security.zim.api.ZIMResponse r5 = new com.alipay.mobile.security.zim.api.ZIMResponse
            r5.<init>()
            int r6 = r4.validationRetCode
            r5.code = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = ""
            r6.append(r7)
            int r7 = r4.validationRetCode
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.reason = r6
            java.lang.String r6 = r4.subCode
            r5.subCode = r6
            java.lang.String r6 = r4.subMsg
            r5.msg = r6
            java.util.Map<java.lang.String, java.lang.String> r6 = r4.extParams
            if (r6 == 0) goto L_0x0111
            java.util.Map<java.lang.String, java.lang.String> r6 = r4.extParams
            boolean r6 = r6.isEmpty()
            if (r6 != 0) goto L_0x0111
            java.util.Map<java.lang.String, java.lang.String> r6 = r5.extInfo
            java.util.Map<java.lang.String, java.lang.String> r7 = r4.extParams
            r6.putAll(r7)
        L_0x0111:
            boolean r6 = r0.isEmpty()
            if (r6 != 0) goto L_0x011c
            java.util.Map<java.lang.String, java.lang.String> r6 = r5.extInfo
            r6.putAll(r0)
        L_0x011c:
            int r0 = r4.validationRetCode
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r0 == r6) goto L_0x012d
            r4 = 3001(0xbb9, float:4.205E-42)
            if (r0 == r4) goto L_0x0127
            goto L_0x0161
        L_0x0127:
            r8.retry()
        L_0x012a:
            r4 = r5
            goto L_0x01a7
        L_0x012d:
            boolean r0 = r4.hasNext
            if (r0 == 0) goto L_0x0161
            java.lang.String r0 = r4.nextProtocol
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x0161
            com.alipay.mobile.security.bio.api.BioParameter r0 = new com.alipay.mobile.security.bio.api.BioParameter
            r0.<init>()
            java.lang.String r3 = r4.nextProtocol
            r0.setProtocol(r3)
            java.lang.String r3 = "verifyid"
            java.lang.String r4 = r8.e
            r0.addExtProperty(r3, r4)
            java.lang.String r3 = "TOKEN_ID"
            java.lang.String r4 = r8.e
            r0.addExtProperty(r3, r4)
            com.alipay.mobile.security.zim.a.a r3 = com.alipay.mobile.security.zim.a.a.a()
            java.lang.String r4 = com.alipay.mobile.security.zim.a.a.e
            r3.b(r4)
            r8.m = r0
            r8.a((com.alipay.mobile.security.bio.api.BioParameter) r0)
            goto L_0x012a
        L_0x0161:
            r4 = r5
            goto L_0x0172
        L_0x0163:
            int r0 = r9.getResult()
            if (r0 == r1) goto L_0x0174
            int r0 = r9.getResult()
            r5 = 500(0x1f4, float:7.0E-43)
            if (r0 != r5) goto L_0x0172
            goto L_0x0174
        L_0x0172:
            r2 = 1
            goto L_0x01a7
        L_0x0174:
            com.alipay.bis.common.service.facade.gw.zim.ZimValidateJsonGwRequest r0 = new com.alipay.bis.common.service.facade.gw.zim.ZimValidateJsonGwRequest
            r0.<init>()
            java.lang.String r3 = r8.e
            r0.zimId = r3
            java.lang.String r3 = ""
            r0.zimData = r3
            com.alipay.mobile.security.zim.a.a r3 = com.alipay.mobile.security.zim.a.a.a()
            java.lang.String r5 = com.alipay.mobile.security.zim.a.a.g
            r3.b(r5)
            com.alipay.biometrics.ui.widget.LoadingProgressDialog r3 = r8.j
            if (r3 == 0) goto L_0x01a2
            com.alipay.biometrics.ui.widget.LoadingProgressDialog r3 = r8.j
            boolean r3 = r3.isShowing()
            if (r3 != 0) goto L_0x01a2
            android.content.Context r3 = r8.c
            android.app.Activity r3 = (android.app.Activity) r3
            com.alipay.mobile.security.zim.a.f r5 = new com.alipay.mobile.security.zim.a.f
            r5.<init>(r8)
            r3.runOnUiThread(r5)
        L_0x01a2:
            com.alipay.mobile.security.zim.gw.a r3 = r8.h
            r3.validate(r9, r0)
        L_0x01a7:
            if (r2 == 0) goto L_0x0241
            if (r4 != 0) goto L_0x022b
            com.alipay.mobile.security.zim.api.ZIMResponse r4 = new com.alipay.mobile.security.zim.api.ZIMResponse
            r4.<init>()
            int r0 = r9.getResult()
            r2 = 101(0x65, float:1.42E-43)
            r3 = 1003(0x3eb, float:1.406E-42)
            if (r0 == r2) goto L_0x020a
            int r0 = r9.getResult()
            r2 = 205(0xcd, float:2.87E-43)
            if (r0 == r2) goto L_0x020a
            int r0 = r9.getResult()
            r2 = 100
            if (r0 != r2) goto L_0x01cb
            goto L_0x020a
        L_0x01cb:
            int r0 = r9.getResult()
            r2 = 301(0x12d, float:4.22E-43)
            if (r0 == r2) goto L_0x0207
            int r0 = r9.getResult()
            r2 = 202(0xca, float:2.83E-43)
            if (r0 == r2) goto L_0x0207
            int r0 = r9.getResult()
            r2 = 210(0xd2, float:2.94E-43)
            if (r0 == r2) goto L_0x0207
            int r0 = r9.getResult()
            r2 = 207(0xcf, float:2.9E-43)
            if (r0 != r2) goto L_0x01ec
            goto L_0x0207
        L_0x01ec:
            int r0 = r9.getResult()
            r2 = 203(0xcb, float:2.84E-43)
            if (r0 != r2) goto L_0x01f7
            r4.code = r3
            goto L_0x020c
        L_0x01f7:
            int r0 = r9.getResult()
            if (r0 != r1) goto L_0x0202
            r0 = 1005(0x3ed, float:1.408E-42)
            r4.code = r0
            goto L_0x020c
        L_0x0202:
            r0 = 1001(0x3e9, float:1.403E-42)
            r4.code = r0
            goto L_0x020c
        L_0x0207:
            r4.code = r3
            goto L_0x020c
        L_0x020a:
            r4.code = r3
        L_0x020c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = r9.getResult()
            r0.append(r1)
            java.lang.String r1 = ""
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.reason = r0
            java.lang.String r0 = r9.subCode
            r4.subCode = r0
            java.lang.String r0 = r9.subMsg
            r4.msg = r0
        L_0x022b:
            r8.b((com.alipay.mobile.security.zim.api.ZIMResponse) r4)
            java.util.Map<java.lang.String, java.lang.String> r0 = r4.extInfo
            java.lang.String r1 = "zimAction"
            int r9 = r9.getResult()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            r0.put(r1, r9)
            r8.a((com.alipay.mobile.security.zim.api.ZIMResponse) r4)
        L_0x0241:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.zim.a.d.onResult(com.alipay.mobile.security.bio.api.BioResponse):void");
    }

    public void a(ZimInitGwResponse zimInitGwResponse) {
        Class<?> cls;
        BioLog.i("zolozTime", "smiletopay get protocol end");
        if (this.j != null && this.j.isShowing()) {
            ((Activity) this.c).runOnUiThread(new g(this));
        }
        boolean z = false;
        if (zimInitGwResponse.retCode == 1001 || zimInitGwResponse.retCode == 200) {
            HashMap hashMap = new HashMap();
            hashMap.put("result", b);
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "" + zimInitGwResponse.retCode);
            hashMap.put("message", "" + zimInitGwResponse.message);
            hashMap.put("subCode", zimInitGwResponse.retCodeSub);
            hashMap.put("subMsg", zimInitGwResponse.retMessageSub);
            if (zimInitGwResponse.extParams != null && !zimInitGwResponse.extParams.isEmpty()) {
                hashMap.putAll(zimInitGwResponse.extParams);
            }
            a.a().a(a.d, (Map<String, String>) hashMap);
            z = true;
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("result", f1046a);
            hashMap2.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "" + zimInitGwResponse.retCode);
            hashMap2.put("message", "" + zimInitGwResponse.message);
            hashMap2.put("subCode", zimInitGwResponse.retCodeSub);
            hashMap2.put("subMsg", zimInitGwResponse.retMessageSub);
            if (zimInitGwResponse.extParams != null && !zimInitGwResponse.extParams.isEmpty()) {
                hashMap2.putAll(zimInitGwResponse.extParams);
            }
            a.a().a(a.d, (Map<String, String>) hashMap2);
            if (zimInitGwResponse.extParams != null && !zimInitGwResponse.extParams.isEmpty()) {
                this.g = zimInitGwResponse.extParams.get(ZIMFacade.KEY_FACE_PAY_INFO);
            }
            this.m.setProtocol(zimInitGwResponse.protocol);
            if (this.f != null && this.f.containsKey(ZIMFacade.KEY_AUTO_CLOSE)) {
                this.m.setAutoClose(Boolean.parseBoolean(this.f.remove(ZIMFacade.KEY_AUTO_CLOSE)));
            }
            Map<String, String> extProperty = this.m.getExtProperty();
            extProperty.put(BioDetector.EXT_KEY_VERIFYID, this.e);
            extProperty.put("TOKEN_ID", this.e);
            if (this.f != null && !this.f.isEmpty()) {
                extProperty.putAll(this.f);
            }
            a.a().b(a.e);
            boolean z2 = !this.e.contains("_bis");
            this.m.isValidate = z2;
            if (z2) {
                try {
                    if (2 == Env.getProtocolFormat(this.c)) {
                        cls = Class.forName("com.alipay.mobile.security.bio.service.impl.BioUploadServiceCoreZhubPb");
                    } else {
                        cls = Class.forName("com.alipay.mobile.security.bio.service.impl.BioUploadServiceCoreZhubJson");
                    }
                    String name = BioUploadServiceCore.class.getName();
                    BioServiceManager currentInstance = BioServiceManager.getCurrentInstance();
                    currentInstance.putBioService(name, cls);
                    ((b) currentInstance.getBioService(name)).setZimId(this.e);
                } catch (Throwable th) {
                    BioLog.e(th);
                }
            }
            a(this.m);
        }
        if (z) {
            ZIMResponse zIMResponse = new ZIMResponse();
            if (200 == zimInitGwResponse.retCode) {
                zIMResponse.code = 2006;
            } else {
                zIMResponse.code = zimInitGwResponse.retCode;
            }
            zIMResponse.reason = "" + zimInitGwResponse.retCode;
            zIMResponse.subCode = zimInitGwResponse.retCodeSub;
            zIMResponse.msg = zimInitGwResponse.retMessageSub;
            zIMResponse.extInfo.put(ZIMFacade.KEY_BIO_ACTION, String.valueOf(206));
            b(zIMResponse);
            a(zIMResponse);
        }
    }

    private void a(BioParameter bioParameter) {
        try {
            BioLog.d(ZIMFacade.TAG, "ZimPlatform.auth()");
            this.i.auth(bioParameter, this);
        } catch (Throwable th) {
            BioLog.e(th);
            ZIMResponse zIMResponse = new ZIMResponse();
            zIMResponse.code = 1001;
            zIMResponse.reason = "" + th;
            zIMResponse.extInfo.put(ZIMFacade.KEY_BIO_ACTION, String.valueOf(206));
            b(zIMResponse);
            a(zIMResponse);
        }
    }

    public void retry() {
        BioLog.i(ZIMFacade.TAG, "ZIMFacade.retry()");
        command(4099);
        ((ZimRecordService) this.k.getBioService(ZimRecordService.class)).retry();
        a(this.m);
    }

    public boolean onFaceDetected(Map<String, String> map) {
        if (!(this.d instanceof ZimProgressCallback)) {
            return true;
        }
        if (!TextUtils.isEmpty(this.g)) {
            map.put(ZIMFacade.KEY_FACE_PAY_INFO, this.g);
        }
        ((ZimProgressCallback) this.d).onFaceDetected(map);
        return true;
    }

    public void a(ZimValidateGwResponse zimValidateGwResponse) {
        if (this.j != null && this.j.isShowing()) {
            ((Activity) this.c).runOnUiThread(new h(this));
        }
        b(zimValidateGwResponse);
        ZIMResponse zIMResponse = new ZIMResponse();
        boolean z = true;
        if (zimValidateGwResponse == null) {
            zIMResponse.extInfo.put(ZIMFacade.KEY_BIO_ACTION, String.valueOf(206));
            zIMResponse.code = 1001;
        } else {
            zIMResponse.bizData = zimValidateGwResponse.nextProtocol;
            zIMResponse.reason = zimValidateGwResponse.productRetCode + "";
            zIMResponse.code = zimValidateGwResponse.validationRetCode;
            if (zimValidateGwResponse.extParams != null) {
                for (String next : zimValidateGwResponse.extParams.keySet()) {
                    zIMResponse.extInfo.put(next, zimValidateGwResponse.extParams.get(next));
                }
            }
            if (zimValidateGwResponse.validationRetCode == 3001 || zimValidateGwResponse.validationRetCode == 3002) {
                z = false;
            }
        }
        if (z) {
            b(zIMResponse);
            if (a(zIMResponse)) {
                command(4099);
            }
        }
    }

    private boolean a(ZIMResponse zIMResponse) {
        MonitorLogService monitorLogService;
        BioLog.w((Throwable) new RuntimeException("doCallZimCallback(): zimResponse=" + zIMResponse + ", mZIMCallback=" + this.d));
        boolean response = this.d.response(zIMResponse);
        StringBuilder sb = new StringBuilder();
        sb.append("doCallZimCallback(): bRet=");
        sb.append(response);
        BioLog.w((Throwable) new RuntimeException(sb.toString()));
        a.a().b(a.j);
        if (!(this.k == null || (monitorLogService = (MonitorLogService) this.k.getBioService(MonitorLogService.class)) == null)) {
            monitorLogService.trigUpload();
        }
        if (response) {
            destroy();
        }
        return response;
    }

    public void destroy() {
        BioLog.e(ZIMFacade.TAG, "ZimPlatform.destroy()");
        this.l = false;
        a.a().b();
        if (this.i != null) {
            this.i.destroy();
        }
        if (this.h != null) {
            this.h.destroy();
        }
        this.c = null;
        this.k = null;
    }

    private void b(ZimValidateGwResponse zimValidateGwResponse) {
        HashMap hashMap = new HashMap();
        if (zimValidateGwResponse != null) {
            int i2 = zimValidateGwResponse.validationRetCode;
            if (i2 == 100 || i2 == 1000) {
                hashMap.put("result", f1046a);
            } else {
                hashMap.put("result", b);
            }
            hashMap.put("message", "");
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "" + zimValidateGwResponse.validationRetCode);
            hashMap.put("subCode", zimValidateGwResponse.retCodeSub);
            hashMap.put("subMsg", zimValidateGwResponse.retMessageSub);
        } else {
            hashMap.put("result", b);
            hashMap.put("message", "0");
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "0");
            hashMap.put("subCode", "");
            hashMap.put("subMsg", "");
        }
        a.a().a(a.h, (Map<String, String>) hashMap);
    }

    private void b(ZIMResponse zIMResponse) {
        HashMap hashMap = new HashMap(2);
        if (zIMResponse != null) {
            int i2 = zIMResponse.code;
            if (i2 == 100 || i2 == 1000) {
                hashMap.put("result", f1046a);
            } else {
                hashMap.put("result", b);
            }
            hashMap.put("message", "" + zIMResponse.reason);
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "" + zIMResponse.code);
            hashMap.put("subCode", zIMResponse.subCode);
            hashMap.put("subMsg", zIMResponse.msg);
        } else {
            hashMap.put("result", b);
            hashMap.put("message", "0");
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "0");
            hashMap.put("subCode", "");
            hashMap.put("subMsg", "");
        }
        a.a().a(a.i, (Map<String, String>) hashMap);
    }

    public void command(int i2) {
        this.i.command(i2);
    }
}
