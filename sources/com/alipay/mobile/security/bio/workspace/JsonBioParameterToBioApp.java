package com.alipay.mobile.security.bio.workspace;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfig;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfigContent;
import com.alipay.mobile.security.bio.common.statistics.RecordExtAction;
import java.util.HashMap;

public class JsonBioParameterToBioApp extends a {
    public JsonBioParameterToBioApp(Context context, BioTransfer bioTransfer) {
        super(context, bioTransfer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.mobile.security.bio.service.BioAppDescription toBioApp(com.alipay.mobile.security.bio.api.BioParameter r13) {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x0205
            java.lang.String r0 = r13.getProtocol()
            r1 = 1
            java.lang.String r2 = r12.b(r0)     // Catch:{ Exception -> 0x001c }
            boolean r3 = com.alipay.mobile.security.bio.utils.StringUtil.isNullorEmpty(r2)     // Catch:{ Exception -> 0x001c }
            if (r3 != 0) goto L_0x0028
            com.alipay.mobile.security.bio.workspace.BioTransfer r0 = r12.f1031a     // Catch:{ Exception -> 0x001a }
            r0.mIDFaceParam = r13     // Catch:{ Exception -> 0x001a }
            com.alipay.mobile.security.bio.workspace.BioTransfer r0 = r12.f1031a     // Catch:{ Exception -> 0x001a }
            r0.isIDFaceFlag = r1     // Catch:{ Exception -> 0x001a }
            goto L_0x0027
        L_0x001a:
            r0 = move-exception
            goto L_0x0020
        L_0x001c:
            r2 = move-exception
            r11 = r2
            r2 = r0
            r0 = r11
        L_0x0020:
            java.lang.String r0 = r0.toString()
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r0)
        L_0x0027:
            r0 = r2
        L_0x0028:
            com.alipay.mobile.security.bio.service.BioAppDescription r2 = new com.alipay.mobile.security.bio.service.BioAppDescription
            r2.<init>()
            java.lang.String r3 = getUniqueTag()
            r2.setTag(r3)
            java.lang.String r3 = r13.getRemoteURL()
            r2.setRemoteURL(r3)
            java.lang.String r3 = r13.getHeadImageUrl()
            r2.setHeadImageURL(r3)
            boolean r3 = com.alipay.mobile.security.bio.utils.StringUtil.isNullorEmpty(r0)
            if (r3 == 0) goto L_0x004a
            goto L_0x0204
        L_0x004a:
            java.lang.String r3 = "bid-log-key-public.key"
            java.lang.String r4 = "bid-log-key-public_t.key"
            java.lang.Class<com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfig> r5 = com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfig.class
            java.lang.Object r0 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r0, r5)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfig r0 = (com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfig) r0     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r0 == 0) goto L_0x0204
            java.lang.String r5 = r0.getContent()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r5 != 0) goto L_0x0060
            goto L_0x0204
        L_0x0060:
            com.alipay.mobile.security.bio.workspace.BioTransfer r5 = r12.f1031a     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r5 = r5.isIDFaceFlag     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r5 != 0) goto L_0x006e
            java.lang.String r5 = r0.getSign()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r5 != 0) goto L_0x006e
            goto L_0x0204
        L_0x006e:
            java.lang.String r5 = r0.getContent()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.Class<com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfigContent> r6 = com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfigContent.class
            java.lang.Object r5 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r5, r6)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfigContent r5 = (com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientConfigContent) r5     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r6 = r5.getAndroidcfg()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r7 = com.alipay.mobile.security.bio.utils.StringUtil.isNullorEmpty(r6)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r7 == 0) goto L_0x008a
            com.alipay.mobile.security.bio.workspace.ProtocolConfig r7 = new com.alipay.mobile.security.bio.workspace.ProtocolConfig     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r7.<init>()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x009c
        L_0x008a:
            java.lang.Class<com.alipay.mobile.security.bio.workspace.ProtocolConfig> r7 = com.alipay.mobile.security.bio.workspace.ProtocolConfig.class
            java.lang.Object r7 = com.alibaba.fastjson.JSON.parseObject((java.lang.String) r6, r7)     // Catch:{ Exception -> 0x0093 }
            com.alipay.mobile.security.bio.workspace.ProtocolConfig r7 = (com.alipay.mobile.security.bio.workspace.ProtocolConfig) r7     // Catch:{ Exception -> 0x0093 }
            goto L_0x009c
        L_0x0093:
            r7 = move-exception
            com.alipay.mobile.security.bio.utils.BioLog.w((java.lang.Throwable) r7)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            com.alipay.mobile.security.bio.workspace.ProtocolConfig r7 = new com.alipay.mobile.security.bio.workspace.ProtocolConfig     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r7.<init>()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
        L_0x009c:
            com.alipay.mobile.security.bio.workspace.BioTransfer r8 = r12.f1031a     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r8 = r8.isIDFaceFlag     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r8 != 0) goto L_0x00da
            if (r7 == 0) goto L_0x00b7
            int r8 = r7.getEnv()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r8 != 0) goto L_0x00b7
            com.alipay.mobile.security.bio.workspace.BioTransfer r4 = r12.f1031a     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            android.content.Context r4 = r4.mContext     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.io.InputStream r3 = r4.open(r3)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x00c3
        L_0x00b7:
            com.alipay.mobile.security.bio.workspace.BioTransfer r3 = r12.f1031a     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            android.content.Context r3 = r3.mContext     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.io.InputStream r3 = r3.open(r4)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
        L_0x00c3:
            java.lang.String r4 = r0.getSign()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r8 = 8
            byte[] r4 = android.util.Base64.decode(r4, r8)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r0 = r0.getContent()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            byte[] r0 = r0.getBytes()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r0 = com.alipay.mobile.security.bio.utils.RSASign.doCheck(r0, r4, r3)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x00db
        L_0x00da:
            r0 = 1
        L_0x00db:
            int r3 = r5.getType()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setBioType(r3)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r3 = " "
            java.lang.String r4 = ""
            java.lang.String r3 = r6.replaceAll(r3, r4)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r4 = "\"ui\":992"
            boolean r3 = r3.contains(r4)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r3 != 0) goto L_0x0199
            int r3 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setBioAction(r3)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            int r3 = r5.getType()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r4 = 100
            r8 = 992(0x3e0, float:1.39E-42)
            r9 = 991(0x3df, float:1.389E-42)
            if (r3 != r4) goto L_0x0172
            if (r7 == 0) goto L_0x0172
            int r3 = r7.getUi()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r4 = 303(0x12f, float:4.25E-43)
            r10 = 302(0x12e, float:4.23E-43)
            if (r3 != 0) goto L_0x013a
            com.alipay.mobile.security.bio.workspace.NavPageConfig r1 = r7.getNavigatepage()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 == 0) goto L_0x0199
            com.alipay.mobile.security.bio.workspace.NavPageConfig r1 = r7.getNavigatepage()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r1 = r1.isEnable()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 == 0) goto L_0x0199
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r10) goto L_0x012e
            r1 = -302(0xfffffffffffffed2, float:NaN)
            r2.setBioAction(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x012e:
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r4) goto L_0x0199
            r1 = -303(0xfffffffffffffed1, float:NaN)
            r2.setBioAction(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x013a:
            int r3 = r7.getUi()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r3 != r9) goto L_0x0154
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r10) goto L_0x014a
            r2.setBioAction(r9)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x014a:
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r4) goto L_0x0199
            r2.setBioAction(r8)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x0154:
            int r3 = r7.getUi()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r3 != r1) goto L_0x0199
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r10) goto L_0x0166
            r1 = 993(0x3e1, float:1.391E-42)
            r2.setBioAction(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x0166:
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r4) goto L_0x0199
            r1 = 994(0x3e2, float:1.393E-42)
            r2.setBioAction(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x0172:
            int r1 = r5.getType()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r3 = 201(0xc9, float:2.82E-43)
            if (r1 != r3) goto L_0x0199
            if (r7 == 0) goto L_0x0199
            int r1 = r7.getUi()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 != r9) goto L_0x0199
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r3 = 300(0x12c, float:4.2E-43)
            if (r1 != r3) goto L_0x018e
            r2.setBioAction(r9)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x0199
        L_0x018e:
            int r1 = r5.getSampleMode()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r3 = 301(0x12d, float:4.22E-43)
            if (r1 != r3) goto L_0x0199
            r2.setBioAction(r8)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
        L_0x0199:
            r2.setCfg(r6)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r1 = r5.getToken()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setBistoken(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            com.alipay.mobile.security.bio.workspace.BioTransfer r1 = r12.f1031a     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            com.alibaba.fastjson.JSONObject r1 = r1.mFcSpecialData     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setFcSpecialData(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setSigned(r0)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            boolean r0 = r13.isAutoClose()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setAutoClose(r0)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            android.os.Bundle r0 = r13.getBundle()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.setBundle(r0)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.util.Map r0 = r13.getExtProperty()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.util.Set r0 = r0.keySet()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
        L_0x01c7:
            boolean r1 = r0.hasNext()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            if (r1 == 0) goto L_0x0204
            java.lang.Object r1 = r0.next()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.util.Map r3 = r13.getExtProperty()     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            r2.addExtProperty(r1, r3)     // Catch:{ IllegalStateException -> 0x01fc, JSONException -> 0x01f3, IllegalArgumentException -> 0x01ea, IOException -> 0x01e1 }
            goto L_0x01c7
        L_0x01e1:
            r13 = move-exception
            java.lang.String r13 = r13.toString()
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r13)
            goto L_0x0204
        L_0x01ea:
            r13 = move-exception
            java.lang.String r13 = r13.toString()
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r13)
            goto L_0x0204
        L_0x01f3:
            r13 = move-exception
            java.lang.String r13 = r13.toString()
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r13)
            goto L_0x0204
        L_0x01fc:
            r13 = move-exception
            java.lang.String r13 = r13.toString()
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r13)
        L_0x0204:
            return r2
        L_0x0205:
            com.alipay.mobile.security.bio.exception.BioIllegalArgumentException r13 = new com.alipay.mobile.security.bio.exception.BioIllegalArgumentException
            r13.<init>()
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.workspace.JsonBioParameterToBioApp.toBioApp(com.alipay.mobile.security.bio.api.BioParameter):com.alipay.mobile.security.bio.service.BioAppDescription");
    }

    private String b(String str) {
        BisClientConfigContent bisClientConfigContent;
        JSONObject parseObject;
        BisClientConfig bisClientConfig = (BisClientConfig) JSON.parseObject(str, BisClientConfig.class);
        if (bisClientConfig == null || (bisClientConfigContent = (BisClientConfigContent) JSON.parseObject(bisClientConfig.getContent(), BisClientConfigContent.class)) == null || bisClientConfigContent.getType() != 200 || (parseObject = JSONObject.parseObject(bisClientConfigContent.getAndroidcfg())) == null) {
            return "";
        }
        if (this.f1031a.fcStep == 0) {
            HashMap hashMap = new HashMap();
            this.f1031a.fcToken = bisClientConfigContent.getToken();
            hashMap.put("fcToken", this.f1031a.fcToken);
            this.f1031a.mRecordExtService.write(RecordExtAction.RECORD_FC_ENTRY_SDK, hashMap);
            hashMap.put("params", str);
            this.f1031a.mRecordExtService.write(RecordExtAction.RECORD_FC_GET_PARAM, hashMap);
            return a(parseObject, false);
        } else if (this.f1031a.fcStep == 1) {
            return b(parseObject, false);
        } else {
            return "";
        }
    }
}
