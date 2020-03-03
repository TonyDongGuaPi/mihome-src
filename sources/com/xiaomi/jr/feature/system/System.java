package com.xiaomi.jr.feature.system;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.common.utils.StatusBarHelper;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.hybrid.HybridFeature;
import com.xiaomi.jr.hybrid.HybridUtils;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.jr.hybrid.Request;
import com.xiaomi.jr.hybrid.Response;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.jr.hybrid.annotation.Action;
import com.xiaomi.jr.hybrid.annotation.Feature;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.PermissionUtil;
import com.xiaomi.jr.permission.Request;
import com.xiaomi.jr.reminder.ReminderUtil;
import com.xiaomi.jr.reminder.RemindersInfo;

@Feature("System")
public class System extends HybridFeature {

    private static class LaunchAppParam {
        @SerializedName("url")

        /* renamed from: a  reason: collision with root package name */
        String f10795a;
        @SerializedName("fallbackUrl")
        String b;

        private LaunchAppParam() {
        }
    }

    private static class GetSmsCaptchaParam {
        @SerializedName("timeout")

        /* renamed from: a  reason: collision with root package name */
        long f10794a;
        @SerializedName("patternRules")
        String b;

        private GetSmsCaptchaParam() {
        }
    }

    @Action(paramClazz = String.class)
    public Response isAppInstalled(Request<String> request) {
        return new Response(Boolean.valueOf(AppUtils.a(HybridUtils.a((Request) request), request.c())));
    }

    @Action(paramClazz = LaunchAppParam.class)
    public Response launchApp(Request<LaunchAppParam> request) {
        DeeplinkUtils.openExternalUrl(request.a().c(), request.c().f10795a, request.c().b);
        return Response.j;
    }

    @Action
    public Response pickContact(final Request request) {
        PermissionManager.a(HybridUtils.a(request), "android.permission.READ_CONTACTS", (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                System.this.performPickContact(request);
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }

    /* access modifiers changed from: private */
    public void performPickContact(final com.xiaomi.jr.hybrid.Request<String> request) {
        HybridUtils.a(request, 24, new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI), new NativeInterface.Callback() {
            /* JADX WARNING: Removed duplicated region for block: B:26:0x0074  */
            /* JADX WARNING: Removed duplicated region for block: B:35:0x009a  */
            /* JADX WARNING: Removed duplicated region for block: B:39:0x00a3  */
            /* JADX WARNING: Removed duplicated region for block: B:44:0x00ae  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(java.lang.Object... r12) {
                /*
                    r11 = this;
                    super.a(r12)
                    r0 = 0
                    r1 = r12[r0]
                    java.lang.Integer r1 = (java.lang.Integer) r1
                    int r1 = r1.intValue()
                    r2 = 1
                    r12 = r12[r2]
                    android.content.Intent r12 = (android.content.Intent) r12
                    com.xiaomi.jr.hybrid.Response r3 = new com.xiaomi.jr.hybrid.Response
                    r4 = 0
                    r3.<init>(r4)
                    r5 = -1
                    if (r1 != r5) goto L_0x00a7
                    if (r12 == 0) goto L_0x00a7
                    com.xiaomi.jr.hybrid.Request r1 = r4
                    android.app.Activity r1 = com.xiaomi.jr.hybrid.HybridUtils.b(r1)
                    boolean r1 = com.xiaomi.jr.common.app.ActivityChecker.a((android.app.Activity) r1)
                    if (r1 == 0) goto L_0x00a7
                    android.net.Uri r12 = r12.getData()
                    if (r12 == 0) goto L_0x0033
                    java.lang.String r12 = r12.getLastPathSegment()
                    goto L_0x0034
                L_0x0033:
                    r12 = r4
                L_0x0034:
                    if (r12 == 0) goto L_0x00a7
                    com.xiaomi.jr.hybrid.Request r1 = r4     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    android.content.Context r1 = com.xiaomi.jr.hybrid.HybridUtils.a((com.xiaomi.jr.hybrid.Request) r1)     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    android.content.ContentResolver r5 = r1.getContentResolver()     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    android.net.Uri r6 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    r7 = 0
                    java.lang.String r8 = "_id = ? "
                    java.lang.String[] r9 = new java.lang.String[r2]     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    r9[r0] = r12     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    r10 = 0
                    android.database.Cursor r12 = r5.query(r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x007b, all -> 0x0078 }
                    if (r12 == 0) goto L_0x0071
                    boolean r0 = r12.moveToFirst()     // Catch:{ Exception -> 0x006e }
                    if (r0 == 0) goto L_0x0071
                    java.lang.String r0 = "display_name"
                    int r0 = r12.getColumnIndex(r0)     // Catch:{ Exception -> 0x006e }
                    java.lang.String r0 = r12.getString(r0)     // Catch:{ Exception -> 0x006e }
                    java.lang.String r1 = "data1"
                    int r1 = r12.getColumnIndex(r1)     // Catch:{ Exception -> 0x006c }
                    java.lang.String r1 = r12.getString(r1)     // Catch:{ Exception -> 0x006c }
                    r4 = r0
                    goto L_0x0072
                L_0x006c:
                    r1 = move-exception
                    goto L_0x007e
                L_0x006e:
                    r1 = move-exception
                    r0 = r4
                    goto L_0x007e
                L_0x0071:
                    r1 = r4
                L_0x0072:
                    if (r12 == 0) goto L_0x00a8
                    r12.close()
                    goto L_0x00a8
                L_0x0078:
                    r0 = move-exception
                    r12 = r4
                    goto L_0x00a1
                L_0x007b:
                    r1 = move-exception
                    r12 = r4
                    r0 = r12
                L_0x007e:
                    r2 = 200(0xc8, float:2.8E-43)
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a0 }
                    r5.<init>()     // Catch:{ all -> 0x00a0 }
                    java.lang.String r6 = "query contact fail: "
                    r5.append(r6)     // Catch:{ all -> 0x00a0 }
                    java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a0 }
                    r5.append(r1)     // Catch:{ all -> 0x00a0 }
                    java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x00a0 }
                    r3.a(r2, r1)     // Catch:{ all -> 0x00a0 }
                    if (r12 == 0) goto L_0x009d
                    r12.close()
                L_0x009d:
                    r1 = r4
                    r4 = r0
                    goto L_0x00a8
                L_0x00a0:
                    r0 = move-exception
                L_0x00a1:
                    if (r12 == 0) goto L_0x00a6
                    r12.close()
                L_0x00a6:
                    throw r0
                L_0x00a7:
                    r1 = r4
                L_0x00a8:
                    int r12 = r3.a()
                    if (r12 != 0) goto L_0x00c4
                    org.json.JSONObject r12 = new org.json.JSONObject
                    r12.<init>()
                    java.lang.String r0 = "name"
                    r12.put(r0, r4)     // Catch:{ JSONException -> 0x00bd }
                    java.lang.String r0 = "number"
                    r12.put(r0, r1)     // Catch:{ JSONException -> 0x00bd }
                L_0x00bd:
                    java.lang.String r12 = r12.toString()
                    r3.a((java.lang.Object) r12)
                L_0x00c4:
                    com.xiaomi.jr.hybrid.Request r12 = r4
                    com.xiaomi.jr.hybrid.HybridUtils.a((com.xiaomi.jr.hybrid.Request) r12, (com.xiaomi.jr.hybrid.Response) r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.feature.system.System.AnonymousClass2.a(java.lang.Object[]):void");
            }
        });
    }

    @Action(paramClazz = RemindersInfo.class)
    public Response addAllReminders(com.xiaomi.jr.hybrid.Request<RemindersInfo> request) {
        ReminderUtil.a(HybridUtils.a((com.xiaomi.jr.hybrid.Request) request), request.c());
        return Response.j;
    }

    @Action(paramClazz = String.class)
    public Response removeAllReminders(com.xiaomi.jr.hybrid.Request<String> request) {
        ReminderUtil.a(HybridUtils.a((com.xiaomi.jr.hybrid.Request) request), request.c());
        return Response.j;
    }

    @Action(paramClazz = GetSmsCaptchaParam.class)
    public Response getSmsCaptcha(final com.xiaomi.jr.hybrid.Request<GetSmsCaptchaParam> request) {
        PermissionManager.a(HybridUtils.a((com.xiaomi.jr.hybrid.Request) request), "android.permission.READ_SMS", (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                Bundle bundle = new Bundle();
                bundle.putLong("timeout", ((GetSmsCaptchaParam) request.c()).f10794a);
                bundle.putString(WebEvent.F, ((GetSmsCaptchaParam) request.c()).b);
                HybridUtils.a(request, 17, bundle, new NativeInterface.Callback() {
                    public void a(Object... objArr) {
                        super.a(objArr);
                        int i = 0;
                        String str = objArr[0];
                        String str2 = objArr[1];
                        com.xiaomi.jr.hybrid.Request request = request;
                        if (str2 != null) {
                            i = 200;
                        }
                        HybridUtils.a(request, new Response(i, str, str2));
                    }
                });
            }

            public void a(String str) {
                HybridUtils.a(request, (Response) new Response.PermissionDeniedResponse(str));
            }
        });
        return Response.j;
    }

    @Action
    public Response readClipboard(com.xiaomi.jr.hybrid.Request request) {
        return new Response(Utils.c(HybridUtils.a(request)));
    }

    @Action(paramClazz = String.class)
    public Response writeClipboard(com.xiaomi.jr.hybrid.Request<String> request) {
        Utils.a(HybridUtils.a((com.xiaomi.jr.hybrid.Request) request), request.c());
        return Response.j;
    }

    @Action
    public Response getStatusBarHeight(com.xiaomi.jr.hybrid.Request request) {
        return new Response(Integer.valueOf(Utils.b(HybridUtils.a(request))));
    }

    @Action(paramClazz = Boolean.class)
    public Response setStatusBarDarkMode(com.xiaomi.jr.hybrid.Request<Boolean> request) {
        StatusBarHelper.a(HybridUtils.b(request), request.c().booleanValue());
        return Response.j;
    }

    @Action
    public Response isNotificationEnabled(com.xiaomi.jr.hybrid.Request request) {
        return new Response(Boolean.valueOf(PermissionUtil.b(HybridUtils.a(request))));
    }

    @Action
    public Response gotoNotificationSetting(com.xiaomi.jr.hybrid.Request request) {
        PermissionUtil.c(HybridUtils.a(request));
        return Response.j;
    }
}
