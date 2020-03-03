package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upwidget.q;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.aa;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.ah;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.ap;
import com.unionpay.mobile.android.widgets.av;
import com.unionpay.mobile.android.widgets.e;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.u;
import com.unionpay.mobile.android.widgets.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a extends LinearLayout implements q.a, aa.a, ah.a, aj.a, ap.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f9705a;
    private m b;
    private ArrayList<z> c;
    private long d;
    private b e;
    private boolean f;
    private boolean g;
    private JSONObject h;
    private String i;

    /* renamed from: com.unionpay.mobile.android.upviews.a$a  reason: collision with other inner class name */
    public class C0077a {

        /* renamed from: a  reason: collision with root package name */
        public int f9706a = 0;
        public String b;

        C0077a(String str) {
            this.b = str;
        }

        public final void a(int i, String str) {
            this.b = str;
            this.f9706a = i;
        }

        public final boolean a() {
            return this.f9706a == 0;
        }
    }

    public interface b {
        void a(C0077a aVar);

        void a(boolean z);

        void c(String str);

        void c(String str, String str2);

        void r();
    }

    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2) {
        this(context, jSONArray, j, bVar, str, z, str2, (byte) 0);
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, byte b2) {
        this(context, jSONArray, j, bVar, str, z, str2, 0);
    }

    private a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, String str2, char c2) {
        this(context, jSONArray, j, bVar, str, z, false, (z) null, (JSONArray) null, str2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(Context context, JSONArray jSONArray, long j, b bVar, String str, boolean z, boolean z2, z zVar, JSONArray jSONArray2, String str2) {
        super(context);
        this.f9705a = null;
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = null;
        this.f = false;
        this.g = true;
        this.h = null;
        this.i = "";
        this.f9705a = context;
        this.d = j;
        this.e = bVar;
        this.f = z2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        int i2 = com.unionpay.mobile.android.global.a.f;
        layoutParams.bottomMargin = i2;
        layoutParams.topMargin = i2;
        setLayoutParams(layoutParams);
        setPadding(0, 0, 0, 0);
        setOrientation(1);
        setBackgroundColor(0);
        a(jSONArray, str, z, zVar, jSONArray2, str2);
    }

    public a(Context context, JSONArray jSONArray, b bVar, String str) {
        this(context, jSONArray, -1, bVar, (String) null, true, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        r0.a(-1, java.lang.String.format(r1, r5));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.unionpay.mobile.android.upviews.a.C0077a a(boolean r9) {
        /*
            r8 = this;
            com.unionpay.mobile.android.upviews.a$a r0 = new com.unionpay.mobile.android.upviews.a$a
            java.lang.String r1 = ""
            r0.<init>(r1)
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r1 = r8.c
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x007e
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r1 = r8.c
            java.util.Iterator r1 = r1.iterator()
        L_0x0013:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x007e
            java.lang.Object r4 = r1.next()
            com.unionpay.mobile.android.widgets.z r4 = (com.unionpay.mobile.android.widgets.z) r4
            boolean r5 = r4 instanceof com.unionpay.mobile.android.widgets.af
            r6 = -1
            if (r5 == 0) goto L_0x0051
            boolean r5 = r4.c()
            if (r5 != 0) goto L_0x003e
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r4 = r4.aC
            java.lang.Object[] r5 = new java.lang.Object[r3]
            com.unionpay.mobile.android.languages.c r7 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r7 = r7.aE
            r5[r2] = r7
        L_0x0036:
            java.lang.String r4 = java.lang.String.format(r4, r5)
            r0.a(r6, r4)
            goto L_0x0013
        L_0x003e:
            boolean r4 = r4.b()
            if (r4 != 0) goto L_0x0013
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r4 = r4.aD
            java.lang.Object[] r5 = new java.lang.Object[r3]
            com.unionpay.mobile.android.languages.c r7 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r7 = r7.aE
            r5[r2] = r7
            goto L_0x0036
        L_0x0051:
            boolean r5 = r4.c()
            if (r5 != 0) goto L_0x006b
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.aC
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r4 = r4.r()
            r5[r2] = r4
        L_0x0063:
            java.lang.String r1 = java.lang.String.format(r1, r5)
            r0.a(r6, r1)
            goto L_0x007e
        L_0x006b:
            boolean r5 = r4.b()
            if (r5 != 0) goto L_0x0013
            com.unionpay.mobile.android.languages.c r1 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r1 = r1.aD
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r4 = r4.r()
            r5[r2] = r4
            goto L_0x0063
        L_0x007e:
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x0085
            return r0
        L_0x0085:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r4 = r8.c
            if (r4 == 0) goto L_0x00e0
            r4 = 0
        L_0x008f:
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r5 = r8.c
            int r5 = r5.size()
            if (r4 >= r5) goto L_0x00e0
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r5 = r8.c
            java.lang.Object r5 = r5.get(r4)
            com.unionpay.mobile.android.widgets.z r5 = (com.unionpay.mobile.android.widgets.z) r5
            boolean r6 = r5 instanceof com.unionpay.mobile.android.widgets.ad
            if (r6 != 0) goto L_0x00dd
            boolean r5 = r5 instanceof com.unionpay.mobile.android.widgets.UPWidget
            if (r5 == 0) goto L_0x00a9
            if (r9 == 0) goto L_0x00dd
        L_0x00a9:
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r5 = r8.c
            java.lang.Object r5 = r5.get(r4)
            com.unionpay.mobile.android.widgets.z r5 = (com.unionpay.mobile.android.widgets.z) r5
            java.lang.String r5 = r5.h()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x00dd
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r5 = r8.c
            java.lang.Object r5 = r5.get(r4)
            com.unionpay.mobile.android.widgets.z r5 = (com.unionpay.mobile.android.widgets.z) r5
            boolean r5 = r5.f()
            if (r5 == 0) goto L_0x00dd
            java.lang.String r5 = ","
            r1.append(r5)
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r5 = r8.c
            java.lang.Object r5 = r5.get(r4)
            com.unionpay.mobile.android.widgets.z r5 = (com.unionpay.mobile.android.widgets.z) r5
            java.lang.String r5 = r5.h()
            r1.append(r5)
        L_0x00dd:
            int r4 = r4 + 1
            goto L_0x008f
        L_0x00e0:
            java.lang.String r9 = r1.toString()
            int r1 = r9.length()
            if (r1 <= r3) goto L_0x00ee
            java.lang.String r9 = r9.substring(r3)
        L_0x00ee:
            r0.a(r2, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a(boolean):com.unionpay.mobile.android.upviews.a$a");
    }

    private static z a(List<z> list, String str) {
        for (z next : list) {
            if (next.n().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0047 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.unionpay.mobile.android.upviews.a.C0077a a() {
        /*
            r7 = this;
            com.unionpay.mobile.android.upviews.a$a r0 = new com.unionpay.mobile.android.upviews.a$a
            java.lang.String r1 = ""
            r0.<init>(r1)
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r1 = r7.c
            java.lang.String r2 = "pin"
            com.unionpay.mobile.android.widgets.z r1 = a((java.util.List<com.unionpay.mobile.android.widgets.z>) r1, (java.lang.String) r2)
            if (r1 == 0) goto L_0x004e
            boolean r2 = r1.c()
            r3 = -1
            r4 = 0
            r5 = 1
            if (r2 != 0) goto L_0x002e
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aC
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = r1.r()
            r5[r4] = r6
        L_0x0026:
            java.lang.String r2 = java.lang.String.format(r2, r5)
            r0.a(r3, r2)
            goto L_0x0041
        L_0x002e:
            boolean r2 = r1.b()
            if (r2 != 0) goto L_0x0041
            com.unionpay.mobile.android.languages.c r2 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r2 = r2.aD
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = r1.r()
            r5[r4] = r6
            goto L_0x0026
        L_0x0041:
            boolean r2 = r0.a()
            if (r2 != 0) goto L_0x0048
            return r0
        L_0x0048:
            java.lang.String r1 = r1.a()
            r0.b = r1
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a():com.unionpay.mobile.android.upviews.a$a");
    }

    public final String a(String str) {
        z a2 = a((List<z>) this.c, str);
        String str2 = "";
        if (a2 != null) {
            str2 = a2.h();
        }
        k.a("uppay", " name:" + str + ", value:" + str2);
        return str2;
    }

    public final void a(int i2) {
        z a2 = a((List<z>) this.c, "sms");
        if (a2 != null) {
            ((ap) a2).a(i2);
        }
    }

    public final void a(View.OnClickListener onClickListener) {
        z c2 = c("promotion");
        if (c2 != null && (c2 instanceof aj)) {
            ((aj) c2).b(onClickListener);
        }
    }

    public final void a(m mVar, JSONObject jSONObject) {
        this.b = mVar;
        this.h = jSONObject;
    }

    public final void a(u uVar, String str) {
        if (this.e != null) {
            boolean z = true;
            if (str != null && str.length() > 0) {
                if (this.c != null) {
                    Iterator<z> it = this.c.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        z next = it.next();
                        if (next instanceof aa) {
                            aa aaVar = (aa) next;
                            if (!aaVar.a(uVar) && !aaVar.c()) {
                                break;
                            }
                        }
                    }
                }
                z = false;
            }
            this.e.a(z);
        }
    }

    public final void a(z zVar) {
        String str;
        Object[] objArr;
        String str2;
        Object[] objArr2;
        boolean z = zVar instanceof ap;
        if (this.e != null && z) {
            d();
            C0077a aVar = new C0077a("");
            z a2 = a((List<z>) this.c, "mobile");
            z a3 = a((List<z>) this.c, "pan");
            z a4 = a((List<z>) this.c, "card");
            z a5 = a((List<z>) this.c, "area_code");
            String str3 = "";
            if (a3 != null) {
                if (!a3.c()) {
                    str2 = c.bD.aC;
                    objArr2 = new Object[]{c.bD.aE};
                } else if (!a3.b()) {
                    str2 = c.bD.aD;
                    objArr2 = new Object[]{c.bD.aE};
                } else {
                    str3 = str3 + a3.h();
                }
                aVar.a(-1, String.format(str2, objArr2));
            }
            if (aVar.a()) {
                if (a2 != null) {
                    if (!a2.c()) {
                        str = c.bD.aC;
                        objArr = new Object[]{a2.r()};
                    } else if (!a2.b()) {
                        str = c.bD.aD;
                        objArr = new Object[]{a2.r()};
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str3);
                        sb.append(str3.length() == 0 ? "" : ",");
                        str3 = sb.toString() + a2.h();
                    }
                    aVar.a(-1, String.format(str, objArr));
                }
                if (aVar.a()) {
                    if (a4 != null && a4.h().length() > 0) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str3);
                        sb2.append(str3.length() == 0 ? "" : ",");
                        str3 = sb2.toString() + a4.h();
                    }
                    if (a5 != null && a5.h().length() > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str3);
                        sb3.append(str3.length() == 0 ? "" : ",");
                        str3 = sb3.toString() + a5.h();
                    }
                    aVar.a(0, str3);
                }
            }
            this.e.a(aVar);
        }
    }

    public final void a(String str, String str2) {
        if (this.e != null) {
            d();
            this.e.c(str, str2);
        }
    }

    public final void a(String str, boolean z) {
        String str2 = "promotion".equalsIgnoreCase(str) ? "instalment" : "promotion";
        z c2 = c(str);
        z c3 = c(str2);
        if (c2 != null) {
            if (c2 instanceof aj) {
                ((aj) c2).a(z);
                if (c3 != null) {
                    p pVar = (p) c3;
                    if (pVar.g()) {
                        Toast.makeText(this.f9705a, this.i, 1).show();
                        pVar.b(false);
                    }
                }
            } else if (c2 instanceof p) {
                if (z) {
                    this.e.r();
                }
                ((p) c2).b(z);
            }
        }
    }

    public final void a(JSONArray jSONArray) {
        z c2 = c("promotion");
        if (c2 != null && (c2 instanceof aj)) {
            ((aj) c2).a(jSONArray);
        }
    }

    public final void a(JSONArray jSONArray, String str) {
        z c2 = c("promotion");
        if (c2 != null && (c2 instanceof aj)) {
            ((aj) c2).a(jSONArray, str);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: com.unionpay.mobile.android.widgets.ah} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: com.unionpay.mobile.android.widgets.ah} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: com.unionpay.mobile.android.widgets.ah} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.unionpay.mobile.android.widgets.ah} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: com.unionpay.mobile.android.widgets.z} */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v16, types: [com.unionpay.mobile.android.widgets.aj] */
    /* JADX WARNING: type inference failed for: r3v17, types: [com.unionpay.mobile.android.widgets.p] */
    /* JADX WARNING: type inference failed for: r3v18, types: [com.unionpay.mobile.android.widgets.bd] */
    /* JADX WARNING: type inference failed for: r3v19, types: [com.unionpay.mobile.android.widgets.ao] */
    /* JADX WARNING: type inference failed for: r3v20, types: [com.unionpay.mobile.android.widgets.au] */
    /* JADX WARNING: type inference failed for: r3v21, types: [com.unionpay.mobile.android.widgets.y] */
    /* JADX WARNING: type inference failed for: r3v22, types: [com.unionpay.mobile.android.widgets.ae] */
    /* JADX WARNING: type inference failed for: r3v23, types: [com.unionpay.mobile.android.widgets.g] */
    /* JADX WARNING: type inference failed for: r3v24, types: [com.unionpay.mobile.android.widgets.f] */
    /* JADX WARNING: type inference failed for: r3v25, types: [com.unionpay.mobile.android.widgets.ad] */
    /* JADX WARNING: type inference failed for: r3v26, types: [com.unionpay.mobile.android.widgets.at] */
    /* JADX WARNING: type inference failed for: r3v28, types: [com.unionpay.mobile.android.widgets.av] */
    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r3v38, types: [com.unionpay.mobile.android.widgets.UPWidget] */
    /* JADX WARNING: type inference failed for: r3v39, types: [com.unionpay.mobile.android.widgets.ap] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(org.json.JSONArray r21, java.lang.String r22, boolean r23, com.unionpay.mobile.android.widgets.z r24, org.json.JSONArray r25, java.lang.String r26) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r24
            r10 = r26
            if (r1 == 0) goto L_0x0294
            int r3 = r21.length()
            if (r3 > 0) goto L_0x0012
            goto L_0x0294
        L_0x0012:
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r3 = r0.c
            r11 = 1
            if (r3 != 0) goto L_0x001f
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r11)
            r0.c = r3
            goto L_0x0024
        L_0x001f:
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r3 = r0.c
            r3.clear()
        L_0x0024:
            r20.removeAllViews()
            r12 = -1
            r0.setBackgroundColor(r12)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            r13 = -2
            r3.<init>(r12, r13)
            java.lang.String r3 = ""
            int r4 = com.unionpay.mobile.android.global.a.I
            int r5 = com.unionpay.mobile.android.global.a.f
            int r5 = r5 * 4
            int r14 = r4 - r5
            r9 = 0
            r8 = 0
            r16 = 0
        L_0x003f:
            int r4 = r21.length()
            if (r8 >= r4) goto L_0x0294
            org.json.JSONObject r7 = r1.getJSONObject(r8)     // Catch:{ JSONException -> 0x01a0 }
            java.lang.String r4 = "type"
            java.lang.String r17 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r7, (java.lang.String) r4)     // Catch:{ JSONException -> 0x01a0 }
            android.content.Context r4 = r0.f9705a     // Catch:{ JSONException -> 0x019c }
            java.lang.String r3 = "type"
            java.lang.String r3 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r7, (java.lang.String) r3)     // Catch:{ JSONException -> 0x019c }
            java.lang.String r5 = "pan"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x019c }
            if (r5 == 0) goto L_0x0069
            com.unionpay.mobile.android.widgets.af r3 = new com.unionpay.mobile.android.widgets.af     // Catch:{ JSONException -> 0x019c }
            r3.<init>(r4, r14, r7, r10)     // Catch:{ JSONException -> 0x019c }
        L_0x0064:
            r5 = r25
            r15 = r8
            goto L_0x0195
        L_0x0069:
            java.lang.String r5 = "mobile"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x019c }
            if (r5 == 0) goto L_0x0077
            com.unionpay.mobile.android.widgets.ah r3 = new com.unionpay.mobile.android.widgets.ah     // Catch:{ JSONException -> 0x019c }
            r3.<init>(r4, r14, r7, r10)     // Catch:{ JSONException -> 0x019c }
            goto L_0x0064
        L_0x0077:
            java.lang.String r5 = "sms"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x019c }
            if (r5 == 0) goto L_0x009a
            com.unionpay.mobile.android.widgets.ap r18 = new com.unionpay.mobile.android.widgets.ap     // Catch:{ JSONException -> 0x0095 }
            r19 = 0
            r3 = r18
            r5 = r14
            r6 = r7
            r7 = r26
            r15 = r8
            r8 = r19
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ JSONException -> 0x0096 }
        L_0x008f:
            r5 = r25
            r3 = r18
            goto L_0x0195
        L_0x0095:
            r15 = r8
        L_0x0096:
            r5 = r25
            goto L_0x01a5
        L_0x009a:
            r15 = r8
            java.lang.String r5 = "cvn2"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00ac
            com.unionpay.mobile.android.widgets.e r3 = new com.unionpay.mobile.android.widgets.e     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r7, r10)     // Catch:{ JSONException -> 0x0096 }
        L_0x00a8:
            r5 = r25
            goto L_0x0195
        L_0x00ac:
            java.lang.String r5 = "expire"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00ba
            com.unionpay.mobile.android.widgets.av r3 = new com.unionpay.mobile.android.widgets.av     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r7, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x00ba:
            java.lang.String r5 = "pwd"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00d0
            com.unionpay.mobile.android.widgets.UPWidget r18 = new com.unionpay.mobile.android.widgets.UPWidget     // Catch:{ JSONException -> 0x0096 }
            long r5 = r0.d     // Catch:{ JSONException -> 0x0096 }
            r3 = r18
            r8 = r7
            r7 = r14
            r9 = r26
            r3.<init>(r4, r5, r7, r8, r9)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x008f
        L_0x00d0:
            r8 = r7
            java.lang.String r5 = "text"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00df
            com.unionpay.mobile.android.widgets.at r3 = new com.unionpay.mobile.android.widgets.at     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x00df:
            java.lang.String r5 = "string"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00ed
            com.unionpay.mobile.android.widgets.ad r3 = new com.unionpay.mobile.android.widgets.ad     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x00ed:
            java.lang.String r5 = "cert_id"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x00fb
            com.unionpay.mobile.android.widgets.f r3 = new com.unionpay.mobile.android.widgets.f     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x00fb:
            java.lang.String r5 = "cert_type"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0109
            com.unionpay.mobile.android.widgets.g r3 = new com.unionpay.mobile.android.widgets.g     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0109:
            java.lang.String r5 = "name"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0117
            com.unionpay.mobile.android.widgets.ae r3 = new com.unionpay.mobile.android.widgets.ae     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0117:
            java.lang.String r5 = "hidden"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0125
            com.unionpay.mobile.android.widgets.y r3 = new com.unionpay.mobile.android.widgets.y     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0125:
            java.lang.String r5 = "user_name"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0134
            com.unionpay.mobile.android.widgets.au r3 = new com.unionpay.mobile.android.widgets.au     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0134:
            java.lang.String r5 = "password"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0143
            com.unionpay.mobile.android.widgets.ao r3 = new com.unionpay.mobile.android.widgets.ao     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0143:
            java.lang.String r5 = "point"
            boolean r5 = r5.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r5 == 0) goto L_0x0152
            com.unionpay.mobile.android.widgets.bd r3 = new com.unionpay.mobile.android.widgets.bd     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r14, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0152:
            java.lang.String r4 = "instalment"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r4 == 0) goto L_0x0169
            com.unionpay.mobile.android.widgets.p r3 = new com.unionpay.mobile.android.widgets.p     // Catch:{ JSONException -> 0x0096 }
            android.content.Context r4 = r0.f9705a     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r8, r10)     // Catch:{ JSONException -> 0x0096 }
            r4 = r3
            com.unionpay.mobile.android.widgets.p r4 = (com.unionpay.mobile.android.widgets.p) r4     // Catch:{ JSONException -> 0x0096 }
            r4.a((com.unionpay.mobile.android.upwidget.q.a) r0)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0169:
            java.lang.String r4 = "promotion"
            boolean r4 = r4.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r4 == 0) goto L_0x0180
            com.unionpay.mobile.android.widgets.aj r3 = new com.unionpay.mobile.android.widgets.aj     // Catch:{ JSONException -> 0x0096 }
            android.content.Context r4 = r0.f9705a     // Catch:{ JSONException -> 0x0096 }
            r3.<init>(r4, r8, r10, r0)     // Catch:{ JSONException -> 0x0096 }
            r4 = r3
            com.unionpay.mobile.android.widgets.aj r4 = (com.unionpay.mobile.android.widgets.aj) r4     // Catch:{ JSONException -> 0x0096 }
            r4.a((com.unionpay.mobile.android.upwidget.q.a) r0)     // Catch:{ JSONException -> 0x0096 }
            goto L_0x00a8
        L_0x0180:
            java.lang.String r4 = "area_code"
            boolean r3 = r4.equalsIgnoreCase(r3)     // Catch:{ JSONException -> 0x0096 }
            if (r3 == 0) goto L_0x0192
            com.unionpay.mobile.android.widgets.a r3 = new com.unionpay.mobile.android.widgets.a     // Catch:{ JSONException -> 0x0096 }
            android.content.Context r4 = r0.f9705a     // Catch:{ JSONException -> 0x0096 }
            r5 = r25
            r3.<init>(r4, r8, r5, r10)     // Catch:{ JSONException -> 0x01a5 }
            goto L_0x0195
        L_0x0192:
            r5 = r25
            r3 = 0
        L_0x0195:
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams     // Catch:{ JSONException -> 0x01a6 }
            r4.<init>(r12, r13)     // Catch:{ JSONException -> 0x01a6 }
            r6 = r4
            goto L_0x01af
        L_0x019c:
            r5 = r25
            r15 = r8
            goto L_0x01a5
        L_0x01a0:
            r5 = r25
            r15 = r8
            r17 = r3
        L_0x01a5:
            r3 = 0
        L_0x01a6:
            java.lang.String r4 = "uppay"
            java.lang.String r6 = "json parser exception!!! - UPRuleView"
            com.unionpay.mobile.android.utils.k.c(r4, r6)
            r6 = r16
        L_0x01af:
            r4 = r17
            if (r3 == 0) goto L_0x0282
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.UPWidget
            if (r7 == 0) goto L_0x01ca
            r7 = r3
            com.unionpay.mobile.android.widgets.UPWidget r7 = (com.unionpay.mobile.android.widgets.UPWidget) r7
            long r8 = r0.d
            r7.a((long) r8)
            r8 = r22
            r7.b((java.lang.String) r8)
            r9 = r23
            r7.b((boolean) r9)
            goto L_0x01e3
        L_0x01ca:
            r8 = r22
            r9 = r23
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.ap
            if (r7 == 0) goto L_0x01d9
            r7 = r3
            com.unionpay.mobile.android.widgets.ap r7 = (com.unionpay.mobile.android.widgets.ap) r7
            r7.a((com.unionpay.mobile.android.widgets.ap.a) r0)
            goto L_0x01e3
        L_0x01d9:
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.ah
            if (r7 == 0) goto L_0x01e3
            r7 = r3
            com.unionpay.mobile.android.widgets.ah r7 = (com.unionpay.mobile.android.widgets.ah) r7
            r7.a((com.unionpay.mobile.android.widgets.ah.a) r0)
        L_0x01e3:
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.aa
            if (r7 == 0) goto L_0x01f1
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.bd
            if (r7 != 0) goto L_0x01f1
            r7 = r3
            com.unionpay.mobile.android.widgets.aa r7 = (com.unionpay.mobile.android.widgets.aa) r7
            r7.a((com.unionpay.mobile.android.widgets.aa.a) r0)
        L_0x01f1:
            java.lang.String r7 = "instalment"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L_0x027a
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            android.content.Context r13 = r0.f9705a
            r7.<init>(r13)
            r13 = -3419943(0xffffffffffcbd0d9, float:NaN)
            r7.setBackgroundColor(r13)
            android.widget.LinearLayout$LayoutParams r13 = new android.widget.LinearLayout$LayoutParams
            r13.<init>(r12, r11)
            r12 = 1092616192(0x41200000, float:10.0)
            if (r15 == 0) goto L_0x0219
            android.content.Context r11 = r0.f9705a
            int r11 = com.unionpay.mobile.android.utils.g.a(r11, r12)
            r13.leftMargin = r11
        L_0x0217:
            r11 = 0
            goto L_0x0229
        L_0x0219:
            boolean r11 = r0.f
            if (r11 == 0) goto L_0x0217
            android.content.Context r11 = r0.f9705a
            int r11 = com.unionpay.mobile.android.utils.g.a(r11, r12)
            r13.leftMargin = r11
            r11 = 0
            r0.setPadding(r11, r11, r11, r11)
        L_0x0229:
            boolean r12 = r0.f
            if (r12 == 0) goto L_0x0241
            if (r15 != 0) goto L_0x0241
            if (r2 == 0) goto L_0x0241
            r0.addView(r2, r6)
            int r12 = r3.getVisibility()
            if (r12 != 0) goto L_0x023d
            r0.addView(r7, r13)
        L_0x023d:
            r0.addView(r3, r6)
            goto L_0x0256
        L_0x0241:
            int r12 = r3.getVisibility()
            if (r12 != 0) goto L_0x0256
            r0.addView(r7, r13)
            r0.addView(r3, r6)
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.af
            if (r7 == 0) goto L_0x0256
            if (r2 == 0) goto L_0x0256
            r0.addView(r2, r6)
        L_0x0256:
            int r7 = r21.length()
            r12 = 1
            int r7 = r7 - r12
            if (r15 == r7) goto L_0x0262
            boolean r7 = r3 instanceof com.unionpay.mobile.android.widgets.aj
            if (r7 == 0) goto L_0x027a
        L_0x0262:
            android.widget.LinearLayout r7 = new android.widget.LinearLayout
            android.content.Context r12 = r0.f9705a
            r7.<init>(r12)
            r12 = -3419943(0xffffffffffcbd0d9, float:NaN)
            r7.setBackgroundColor(r12)
            android.widget.LinearLayout$LayoutParams r12 = new android.widget.LinearLayout$LayoutParams
            r11 = -1
            r13 = 1
            r12.<init>(r11, r13)
            r0.addView(r7, r12)
            goto L_0x027c
        L_0x027a:
            r11 = -1
            r13 = 1
        L_0x027c:
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r7 = r0.c
            r7.add(r3)
            goto L_0x0288
        L_0x0282:
            r8 = r22
            r9 = r23
            r11 = -1
            r13 = 1
        L_0x0288:
            int r3 = r15 + 1
            r8 = r3
            r3 = r4
            r16 = r6
            r9 = 0
            r11 = 1
            r12 = -1
            r13 = -2
            goto L_0x003f
        L_0x0294:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.a(org.json.JSONArray, java.lang.String, boolean, com.unionpay.mobile.android.widgets.z, org.json.JSONArray, java.lang.String):void");
    }

    public final void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            String a2 = j.a(jSONObject, "instalment_empty_info");
            if (!TextUtils.isEmpty(a2)) {
                ((p) c("instalment")).a(false);
                ((p) c("instalment")).b(false);
                Toast.makeText(this.f9705a, a2, 1).show();
                return;
            }
            ((p) c("instalment")).a(true);
            ((p) c("instalment")).b(true);
            ((p) a((List<z>) this.c, "instalment_policy")).a(j.d(jSONObject, "new_instalments"));
        }
    }

    public final C0077a b() {
        return a(true);
    }

    public final String b(String str) {
        z a2 = a((List<z>) this.c, str);
        return a2 != null ? a2.a() : "";
    }

    public final void b(View.OnClickListener onClickListener) {
        z c2 = c("promotion");
        if (c2 != null && (c2 instanceof aj)) {
            ((aj) c2).c(onClickListener);
        }
    }

    public final z c(String str) {
        if (this.c != null && this.c.size() > 0 && !TextUtils.isEmpty(str)) {
            Iterator<z> it = this.c.iterator();
            while (it.hasNext()) {
                z next = it.next();
                if (next.o().equalsIgnoreCase(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    public final HashMap<String, String> c() {
        if (!a(false).a()) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.c != null) {
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                z zVar = this.c.get(i2);
                if (!(zVar instanceof ad) && !(zVar instanceof UPWidget) && !TextUtils.isEmpty(zVar.a())) {
                    hashMap.put(zVar.n(), zVar.a());
                }
            }
        }
        return hashMap;
    }

    public final void c(View.OnClickListener onClickListener) {
        z c2 = c("promotion");
        if (c2 != null && (c2 instanceof aj)) {
            ((aj) c2).a(onClickListener);
        }
    }

    public final void d(String str) {
        this.i = str;
    }

    public final boolean d() {
        boolean z;
        if (this.c != null) {
            Iterator<z> it = this.c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                z next = it.next();
                if (next instanceof UPWidget) {
                    UPWidget uPWidget = (UPWidget) next;
                    if (uPWidget.j()) {
                        uPWidget.k();
                        z = true;
                        break;
                    }
                }
            }
        }
        z = false;
        ((InputMethodManager) this.f9705a.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        return z;
    }

    public final void e(String str) {
        if (this.e != null) {
            this.e.c(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean e() {
        /*
            r5 = this;
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r0 = r5.c
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0026
            java.util.ArrayList<com.unionpay.mobile.android.widgets.z> r0 = r5.c
            java.util.Iterator r0 = r0.iterator()
        L_0x000c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0026
            java.lang.Object r3 = r0.next()
            com.unionpay.mobile.android.widgets.z r3 = (com.unionpay.mobile.android.widgets.z) r3
            boolean r4 = r3 instanceof com.unionpay.mobile.android.widgets.aa
            if (r4 == 0) goto L_0x000c
            com.unionpay.mobile.android.widgets.aa r3 = (com.unionpay.mobile.android.widgets.aa) r3
            boolean r3 = r3.c()
            if (r3 != 0) goto L_0x000c
            r0 = 1
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r0 != 0) goto L_0x002a
            return r1
        L_0x002a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.a.e():boolean");
    }

    public final void f() {
        if (this.c != null && this.c.size() > 0) {
            Iterator<z> it = this.c.iterator();
            while (it.hasNext()) {
                z next = it.next();
                if ((next instanceof UPWidget) || (next instanceof e) || (next instanceof av)) {
                    ((aa) next).g();
                }
            }
        }
    }

    public final void g() {
        z c2 = c("instalment");
        if (c2 != null) {
            p pVar = (p) c2;
            if (pVar.g()) {
                Toast.makeText(this.f9705a, this.i, 1).show();
                pVar.b(false);
            }
        }
    }
}
