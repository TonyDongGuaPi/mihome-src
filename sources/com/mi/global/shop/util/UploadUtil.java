package com.mi.global.shop.util;

import android.graphics.Bitmap;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mi.global.shop.request.MultipartRequest;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ThreadPool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class UploadUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7115a = "http://upload.global.mi.com/upload/multiimgs";

    public interface UploadCallback {
        void a(String str);

        void a(ArrayList<String> arrayList);
    }

    public static void a(final ArrayList<String> arrayList, final UploadCallback uploadCallback) {
        if (uploadCallback != null) {
            if (arrayList == null || arrayList.size() == 0) {
                uploadCallback.a("image is empty");
                return;
            }
            final HashMap hashMap = new HashMap();
            hashMap.put("area", "in");
            hashMap.put("authId", "101");
            ThreadPool.a(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < arrayList.size(); i++) {
                        Bitmap a2 = ImageUtil.a((String) arrayList.get(i), 400, 400);
                        String a3 = ImageUtil.a(i);
                        ImageUtil.a(a2, a3, Bitmap.CompressFormat.JPEG, 100);
                        arrayList.add(a3);
                        if (a2 != null) {
                            a2.recycle();
                        }
                    }
                    UploadUtil.b(UploadUtil.f7115a, hashMap, arrayList, uploadCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void b(String str, Map<String, String> map, List<String> list, final UploadCallback uploadCallback) {
        MultipartRequest multipartRequest = new MultipartRequest(str, map, list, new Response.Listener<JSONObject>() {
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a0, code lost:
                if (android.text.TextUtils.isEmpty(r5) == false) goto L_0x00a4;
             */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onResponse(org.json.JSONObject r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "Photo failed to upload"
                    if (r5 == 0) goto L_0x00a3
                    java.lang.String r1 = "result"
                    org.json.JSONObject r5 = r5.optJSONObject(r1)
                    if (r5 == 0) goto L_0x00a3
                    java.lang.String r1 = "code"
                    r2 = -1
                    int r1 = r5.optInt(r1, r2)
                    if (r1 != 0) goto L_0x0096
                    java.lang.String r1 = "urls"
                    java.lang.String r1 = r5.optString(r1)
                    boolean r2 = android.text.TextUtils.isEmpty(r1)
                    if (r2 != 0) goto L_0x0096
                    com.google.gson.Gson r2 = new com.google.gson.Gson
                    r2.<init>()
                    com.mi.global.shop.util.UploadUtil$2$1 r3 = new com.mi.global.shop.util.UploadUtil$2$1
                    r3.<init>()
                    java.lang.reflect.Type r3 = r3.getType()
                    java.lang.Object r1 = r2.fromJson((java.lang.String) r1, (java.lang.reflect.Type) r3)
                    java.util.ArrayList r1 = (java.util.ArrayList) r1
                    if (r1 == 0) goto L_0x0096
                    int r2 = r1.size()
                    if (r2 <= 0) goto L_0x0096
                    java.util.ArrayList r5 = new java.util.ArrayList
                    r5.<init>()
                    java.util.Iterator r0 = r1.iterator()
                L_0x0047:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L_0x0090
                    java.lang.Object r1 = r0.next()
                    java.lang.String r1 = (java.lang.String) r1
                    boolean r2 = android.text.TextUtils.isEmpty(r1)
                    if (r2 != 0) goto L_0x008c
                    java.lang.String r2 = "http:"
                    boolean r2 = r1.startsWith(r2)
                    if (r2 != 0) goto L_0x008c
                    java.lang.String r2 = "//"
                    boolean r2 = r1.startsWith(r2)
                    if (r2 == 0) goto L_0x007b
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "http:"
                    r2.append(r3)
                    r2.append(r1)
                    java.lang.String r1 = r2.toString()
                    goto L_0x008c
                L_0x007b:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "http://"
                    r2.append(r3)
                    r2.append(r1)
                    java.lang.String r1 = r2.toString()
                L_0x008c:
                    r5.add(r1)
                    goto L_0x0047
                L_0x0090:
                    com.mi.global.shop.util.UploadUtil$UploadCallback r0 = r10
                    r0.a((java.util.ArrayList<java.lang.String>) r5)
                    return
                L_0x0096:
                    java.lang.String r1 = "message"
                    java.lang.String r5 = r5.optString(r1)
                    boolean r1 = android.text.TextUtils.isEmpty(r5)
                    if (r1 != 0) goto L_0x00a3
                    goto L_0x00a4
                L_0x00a3:
                    r5 = r0
                L_0x00a4:
                    com.mi.global.shop.util.UploadUtil$UploadCallback r0 = r10
                    r0.a((java.lang.String) r5)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.util.UploadUtil.AnonymousClass2.onResponse(org.json.JSONObject):void");
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                uploadCallback.a("Network error or input info illegal");
            }
        });
        multipartRequest.setRetryPolicy(new DefaultRetryPolicy((((list == null ? 0 : list.size()) * 2) + 1) * 2500, 0, 1.0f));
        RequestQueueUtil.a().add(multipartRequest);
    }
}
