package com.xiaomi.accountsdk.utils;

import org.json.JSONException;

public abstract class XMPassportApiRequest {

    protected enum WebMethod {
        POST,
        GET
    }

    /* access modifiers changed from: protected */
    public abstract void addParams(EasyMap<String, String> easyMap);

    /* access modifiers changed from: protected */
    public abstract boolean allowRetryAuthToken();

    /* access modifiers changed from: protected */
    public abstract String getAuthToken();

    /* access modifiers changed from: protected */
    public abstract String getCUserId();

    /* access modifiers changed from: protected */
    public abstract String getUrl();

    /* access modifiers changed from: protected */
    public abstract String getUserId();

    /* access modifiers changed from: protected */
    public abstract WebMethod getWebMethod();

    /* access modifiers changed from: protected */
    public abstract String handleDataResult(Object obj) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract void invalidateAuthToken(String str);

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r1 = getAuthToken();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String execute() throws com.xiaomi.accountsdk.request.AccessDeniedException, com.xiaomi.accountsdk.request.InvalidResponseException, com.xiaomi.accountsdk.request.CipherException, java.io.IOException, org.json.JSONException {
        /*
            r10 = this;
            r0 = 0
        L_0x0001:
            r1 = 2
            if (r0 >= r1) goto L_0x007f
            java.lang.String r1 = r10.getAuthToken()
            com.xiaomi.accountsdk.account.data.ExtendedAuthToken r2 = com.xiaomi.accountsdk.account.data.ExtendedAuthToken.parse(r1)
            if (r2 != 0) goto L_0x0010
            goto L_0x007f
        L_0x0010:
            java.lang.String r3 = r2.authToken
            java.lang.String r8 = r2.security
            if (r3 == 0) goto L_0x007f
            if (r8 != 0) goto L_0x0019
            goto L_0x007f
        L_0x0019:
            com.xiaomi.accountsdk.utils.EasyMap r5 = new com.xiaomi.accountsdk.utils.EasyMap
            r5.<init>()
            r10.addParams(r5)
            com.xiaomi.accountsdk.utils.EasyMap r6 = new com.xiaomi.accountsdk.utils.EasyMap
            r6.<init>()
            java.lang.String r2 = "serviceToken"
            r6.easyPut(r2, r3)
            java.lang.String r2 = r10.getCUserId()
            if (r2 == 0) goto L_0x0037
            java.lang.String r3 = "cUserId"
            r6.easyPut(r3, r2)
            goto L_0x0040
        L_0x0037:
            java.lang.String r2 = "userId"
            java.lang.String r3 = r10.getUserId()
            r6.easyPut(r2, r3)
        L_0x0040:
            com.xiaomi.accountsdk.utils.XMPassportApiRequest$WebMethod r2 = r10.getWebMethod()     // Catch:{ AuthenticationFailureException -> 0x0073 }
            com.xiaomi.accountsdk.utils.XMPassportApiRequest$WebMethod r3 = com.xiaomi.accountsdk.utils.XMPassportApiRequest.WebMethod.GET     // Catch:{ AuthenticationFailureException -> 0x0073 }
            if (r2 != r3) goto L_0x0057
            java.lang.String r4 = r10.getUrl()     // Catch:{ AuthenticationFailureException -> 0x0073 }
            r7 = 1
            com.xiaomi.accountsdk.utils.AESCoder r9 = new com.xiaomi.accountsdk.utils.AESCoder     // Catch:{ AuthenticationFailureException -> 0x0073 }
            r9.<init>((java.lang.String) r8)     // Catch:{ AuthenticationFailureException -> 0x0073 }
            com.xiaomi.accountsdk.request.SimpleRequest$MapContent r2 = com.xiaomi.accountsdk.request.SecureRequestForAccount.getAsMap(r4, r5, r6, r7, r8, r9)     // Catch:{ AuthenticationFailureException -> 0x0073 }
            goto L_0x0065
        L_0x0057:
            java.lang.String r4 = r10.getUrl()     // Catch:{ AuthenticationFailureException -> 0x0073 }
            r7 = 1
            com.xiaomi.accountsdk.utils.AESCoder r9 = new com.xiaomi.accountsdk.utils.AESCoder     // Catch:{ AuthenticationFailureException -> 0x0073 }
            r9.<init>((java.lang.String) r8)     // Catch:{ AuthenticationFailureException -> 0x0073 }
            com.xiaomi.accountsdk.request.SimpleRequest$MapContent r2 = com.xiaomi.accountsdk.request.SecureRequestForAccount.postAsMap(r4, r5, r6, r7, r8, r9)     // Catch:{ AuthenticationFailureException -> 0x0073 }
        L_0x0065:
            if (r2 != 0) goto L_0x0068
            goto L_0x007f
        L_0x0068:
            java.lang.String r0 = "data"
            java.lang.Object r0 = r2.getFromBody(r0)
            java.lang.String r0 = r10.handleDataResult(r0)
            return r0
        L_0x0073:
            boolean r2 = r10.allowRetryAuthToken()
            if (r2 == 0) goto L_0x007f
            r10.invalidateAuthToken(r1)
            int r0 = r0 + 1
            goto L_0x0001
        L_0x007f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accountsdk.utils.XMPassportApiRequest.execute():java.lang.String");
    }
}
