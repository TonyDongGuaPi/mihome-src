package miuipub.hybrid.feature;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.xiaomi.jr.mipay.common.MipayConstants;
import java.util.Map;
import miuipub.hybrid.HybridFeature;
import miuipub.hybrid.LifecycleListener;
import miuipub.hybrid.NativeInterface;
import miuipub.hybrid.Request;
import miuipub.hybrid.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact implements HybridFeature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2954a = "com.android.contacts";
    private static final String b = "pickPhoneNumber";
    private static final String c = "lookup";
    private static final int d = 1;
    private static final String e = "contactList";
    private static final String f = "displayName";
    private static final String g = "number";
    private static final String h = "type";
    private static final String i = "label";
    private static final String[] j = {"display_name", "data4", "data2", "data3"};

    public void a(Map<String, String> map) {
    }

    public Response a(Request request) {
        if (TextUtils.equals(request.a(), b)) {
            return d(request);
        }
        if (TextUtils.equals(request.a(), c)) {
            return c(request);
        }
        return new Response(204, "no such action");
    }

    private Response c(final Request request) {
        try {
            String string = new JSONObject(request.b()).getString(MipayConstants.ah);
            final ContentResolver contentResolver = request.e().a().getContentResolver();
            new AsyncTask<String, Void, JSONObject>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public JSONObject doInBackground(String... strArr) {
                    return Contact.b(contentResolver, strArr[0]);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(JSONObject jSONObject) {
                    request.c().a(new Response(0, jSONObject));
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{string});
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new Response(204, "param not valid");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0076, code lost:
        if (r7 == null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x006a, code lost:
        if (r7 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x006c, code lost:
        r7.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject b(android.content.ContentResolver r7, java.lang.String r8) {
        /*
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            android.net.Uri r1 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI
            android.net.Uri$Builder r1 = r1.buildUpon()
            android.net.Uri$Builder r8 = r1.appendPath(r8)
            android.net.Uri r2 = r8.build()
            java.lang.String[] r3 = j
            r4 = 0
            r5 = 0
            r6 = 0
            r1 = r7
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)
            if (r7 == 0) goto L_0x007f
        L_0x001f:
            boolean r8 = r7.moveToNext()     // Catch:{ JSONException -> 0x0072 }
            if (r8 == 0) goto L_0x006a
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0072 }
            r8.<init>()     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "displayName"
            java.lang.String r2 = "display_name"
            int r2 = r7.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = r7.getString(r2)     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "number"
            java.lang.String r2 = "data4"
            int r2 = r7.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = r7.getString(r2)     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "type"
            java.lang.String r2 = "data2"
            int r2 = r7.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = r7.getString(r2)     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r1 = "label"
            java.lang.String r2 = "data3"
            int r2 = r7.getColumnIndex(r2)     // Catch:{ JSONException -> 0x0072 }
            java.lang.String r2 = r7.getString(r2)     // Catch:{ JSONException -> 0x0072 }
            r8.put(r1, r2)     // Catch:{ JSONException -> 0x0072 }
            r0.put(r8)     // Catch:{ JSONException -> 0x0072 }
            goto L_0x001f
        L_0x006a:
            if (r7 == 0) goto L_0x007f
        L_0x006c:
            r7.close()
            goto L_0x007f
        L_0x0070:
            r8 = move-exception
            goto L_0x0079
        L_0x0072:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0070 }
            if (r7 == 0) goto L_0x007f
            goto L_0x006c
        L_0x0079:
            if (r7 == 0) goto L_0x007e
            r7.close()
        L_0x007e:
            throw r8
        L_0x007f:
            org.json.JSONObject r7 = new org.json.JSONObject
            r7.<init>()
            java.lang.String r8 = "contactList"
            r7.put(r8, r0)     // Catch:{ JSONException -> 0x008a }
            goto L_0x008e
        L_0x008a:
            r8 = move-exception
            r8.printStackTrace()
        L_0x008e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.hybrid.feature.Contact.b(android.content.ContentResolver, java.lang.String):org.json.JSONObject");
    }

    private Response d(final Request request) {
        final NativeInterface e2 = request.e();
        Activity a2 = e2.a();
        e2.a(new LifecycleListener() {
            public void a(int i, int i2, Intent intent) {
                e2.b(this);
                if (i != 1) {
                    return;
                }
                if (i2 == -1) {
                    final ContentResolver contentResolver = request.e().a().getContentResolver();
                    new AsyncTask<Uri, Void, JSONObject>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public JSONObject doInBackground(Uri... uriArr) {
                            return Contact.b(contentResolver, uriArr[0]);
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public void onPostExecute(JSONObject jSONObject) {
                            request.c().a(new Response(0, jSONObject));
                        }
                    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{intent.getData()});
                } else if (i2 == 0) {
                    request.c().a(new Response(100, "cancel"));
                } else {
                    request.c().a(new Response(200));
                }
            }
        });
        Intent intent = new Intent("android.intent.action.PICK", ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        intent.setType("vnd.android.cursor.dir/phone_v2");
        intent.setPackage(f2954a);
        a2.startActivityForResult(intent, 1);
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0060, code lost:
        if (r6 == null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0054, code lost:
        if (r6 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0056, code lost:
        r6.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject b(android.content.ContentResolver r6, android.net.Uri r7) {
        /*
            java.lang.String[] r2 = j
            r3 = 0
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r7
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            org.json.JSONObject r7 = new org.json.JSONObject
            r7.<init>()
            if (r6 == 0) goto L_0x0069
            boolean r0 = r6.moveToFirst()     // Catch:{ JSONException -> 0x005c }
            if (r0 == 0) goto L_0x0054
            java.lang.String r0 = "displayName"
            java.lang.String r1 = "display_name"
            int r1 = r6.getColumnIndex(r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r1 = r6.getString(r1)     // Catch:{ JSONException -> 0x005c }
            r7.put(r0, r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r0 = "number"
            java.lang.String r1 = "data4"
            int r1 = r6.getColumnIndex(r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r1 = r6.getString(r1)     // Catch:{ JSONException -> 0x005c }
            r7.put(r0, r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r0 = "type"
            java.lang.String r1 = "data2"
            int r1 = r6.getColumnIndex(r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r1 = r6.getString(r1)     // Catch:{ JSONException -> 0x005c }
            r7.put(r0, r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r0 = "label"
            java.lang.String r1 = "data3"
            int r1 = r6.getColumnIndex(r1)     // Catch:{ JSONException -> 0x005c }
            java.lang.String r1 = r6.getString(r1)     // Catch:{ JSONException -> 0x005c }
            r7.put(r0, r1)     // Catch:{ JSONException -> 0x005c }
        L_0x0054:
            if (r6 == 0) goto L_0x0069
        L_0x0056:
            r6.close()
            goto L_0x0069
        L_0x005a:
            r7 = move-exception
            goto L_0x0063
        L_0x005c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x005a }
            if (r6 == 0) goto L_0x0069
            goto L_0x0056
        L_0x0063:
            if (r6 == 0) goto L_0x0068
            r6.close()
        L_0x0068:
            throw r7
        L_0x0069:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.hybrid.feature.Contact.b(android.content.ContentResolver, android.net.Uri):org.json.JSONObject");
    }

    public HybridFeature.Mode b(Request request) {
        if (TextUtils.equals(request.a(), c) || TextUtils.equals(request.a(), b)) {
            return HybridFeature.Mode.CALLBACK;
        }
        return null;
    }
}
