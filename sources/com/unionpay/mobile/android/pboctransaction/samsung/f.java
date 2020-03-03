package com.unionpay.mobile.android.pboctransaction.samsung;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.miuipub.internal.hybrid.SignUtils;
import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.data.Amount;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public final class f implements c {
    private String A = "";
    private String B = "";
    private GetVendorPayStatusRequestParams C;
    private long D = 8000;
    /* access modifiers changed from: private */
    public boolean E = false;
    private final Handler.Callback F = new g(this);
    /* access modifiers changed from: private */
    public final Handler G = new Handler(this.F);
    private InitRequestParams H;
    private SendApduRequestParams I;
    private final UPTsmAddon.UPTsmConnectionListener J = new h(this);

    /* renamed from: a  reason: collision with root package name */
    String f9658a = "19999741583305435775450371903957622252895007857586703985696530069777024392884287211670048223494223356836139331264745305488035196420545843046674853984852305228918004888414759300445308845681087472809487791392726663269247999482633231304479943902981311338709709401000108625221857486530967716878328228310703650408575058288784573884262229674701501842066793928002725038356122707147929560460157457327696696471785787505023643000687928051333648369477362945785046976181683285277919023274376124429148429078602516462345014971452220809120399264066736558357572250447243744965533695780751271768398207631002867947152625578881506566297";
    String b = "65537";
    String c = "5929703506495688276130676968213384164609348882017291684789802337394713840702726472221198819456433069055388915357817202968369194525956730949539025096963015440180443916974948318765778051794088998339276397676916425744003507605582286608745438301704836361482343765671805403004194772735755889141443700570750608527755694790475628670051863813384800013641474007746161600969180035295709344887092512089121125289090881005234379649440422346798246278284328310221953743757037875834557694749810951089453346522229122216198442376081589767583019062954875861469699069474707285206935898628020341168773624455554331118138151051364372906861";
    String d = "UnionPay";
    String e = "";
    boolean f = false;
    boolean g = false;
    boolean h = false;
    private final String i = "A0000003334355502D4D4F42494C45";
    private final int j = 10000;
    private Context k;
    private b l;
    /* access modifiers changed from: private */
    public a m;
    private UPTsmAddon n;
    /* access modifiers changed from: private */
    public Handler o = null;
    private int p = 0;
    private final int q = 8;
    private boolean r = false;
    /* access modifiers changed from: private */
    public String s = "-1";
    /* access modifiers changed from: private */
    public String t = "";
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public String v = null;
    private boolean w = false;
    /* access modifiers changed from: private */
    public String x = "";
    private String y = "-1";
    private String z = "-1";

    public interface a {
        void a(boolean z);
    }

    public f(a aVar) {
        this.m = aVar;
    }

    static /* synthetic */ void a(f fVar, int i2, String str) {
        if (i2 != 1000) {
            if (i2 != 1018) {
                switch (i2) {
                    case 1011:
                        k.c("uppay", "open channel fail");
                        fVar.s = null;
                        fVar.t = "";
                        fVar.r = true;
                        return;
                    case 1012:
                        k.c("uppay", "apdu fail");
                        fVar.G.removeMessages(3);
                        fVar.u = true;
                        return;
                    case 1013:
                        k.c("uppay", "close channel fail");
                        fVar.w = true;
                        return;
                    case 1014:
                        fVar.o.sendMessage(fVar.G.obtainMessage(8, (Object) null));
                        return;
                    case 1015:
                        k.c("uppay-spay", "get spay list call back");
                        fVar.o.sendMessage(fVar.o.obtainMessage(2001, str));
                        return;
                    case 1016:
                        break;
                    default:
                        return;
                }
            }
            k.c("uppay-spay", "check spay support fail");
            fVar.m.a(false);
            return;
        }
        fVar.a(false);
    }

    private void a(String str, String str2) {
        this.I = new SendApduRequestParams();
        this.I.setChannel(str2);
        this.I.setHexApdu(str);
        if (this.f) {
            this.I.setReserve(g());
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (this.l == null) {
            return;
        }
        if (z2) {
            this.l.a();
        } else {
            this.l.b();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.H == null) {
            this.H = new InitRequestParams();
            if (this.f) {
                this.H.setSignature(g());
                this.H.setReserve(g());
            }
        }
        try {
            int init = this.n.init(this.H, new e(1000, this.G));
            if (init != 0) {
                this.G.sendMessage(Message.obtain(this.G, 1, 1000, 0, ""));
            } else {
                this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1000, 0, ""), this.D);
            }
            k.c("uppay", "ret = " + init);
        } catch (RemoteException e2) {
            a(false);
            e2.printStackTrace();
        }
    }

    private String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("signature", this.e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0047 A[LOOP:0: B:13:0x0047->B:16:0x0051, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String a(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto L_0x0009
            java.lang.String r7 = ""
            return r7
        L_0x0009:
            java.lang.String r0 = ""
            r6.t = r0
            r0 = 0
            r6.r = r0
            com.unionpay.tsmservice.request.OpenChannelRequestParams r1 = new com.unionpay.tsmservice.request.OpenChannelRequestParams     // Catch:{ RemoteException -> 0x0043 }
            r1.<init>()     // Catch:{ RemoteException -> 0x0043 }
            r1.setAppAID(r7)     // Catch:{ RemoteException -> 0x0043 }
            boolean r2 = r6.f     // Catch:{ RemoteException -> 0x0043 }
            if (r2 == 0) goto L_0x0023
            java.lang.String r2 = r6.g()     // Catch:{ RemoteException -> 0x0043 }
            r1.setReserve(r2)     // Catch:{ RemoteException -> 0x0043 }
        L_0x0023:
            com.unionpay.tsmservice.UPTsmAddon r2 = r6.n     // Catch:{ RemoteException -> 0x0043 }
            com.unionpay.mobile.android.pboctransaction.samsung.e r3 = new com.unionpay.mobile.android.pboctransaction.samsung.e     // Catch:{ RemoteException -> 0x0043 }
            android.os.Handler r4 = r6.G     // Catch:{ RemoteException -> 0x0043 }
            r5 = 1011(0x3f3, float:1.417E-42)
            r3.<init>(r5, r4)     // Catch:{ RemoteException -> 0x0043 }
            int r1 = r2.openChannel(r1, r3)     // Catch:{ RemoteException -> 0x0043 }
            if (r1 == 0) goto L_0x0047
            android.os.Handler r1 = r6.G     // Catch:{ RemoteException -> 0x0043 }
            android.os.Handler r2 = r6.G     // Catch:{ RemoteException -> 0x0043 }
            r3 = 1
            java.lang.String r4 = ""
            android.os.Message r2 = android.os.Message.obtain(r2, r3, r5, r0, r4)     // Catch:{ RemoteException -> 0x0043 }
            r1.sendMessage(r2)     // Catch:{ RemoteException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0047:
            java.lang.String r1 = r6.t
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x0053
            boolean r1 = r6.r
            if (r1 == 0) goto L_0x0047
        L_0x0053:
            java.lang.String r1 = "A0000003334355502D4D4F42494C45"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x0060
            java.lang.String r7 = r6.s
            r6.y = r7
            goto L_0x0064
        L_0x0060:
            java.lang.String r7 = r6.s
            r6.z = r7
        L_0x0064:
            r6.r = r0
            java.lang.String r7 = r6.t
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.samsung.f.a(java.lang.String):java.lang.String");
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        if (this.n == null) {
            return null;
        }
        try {
            if (!com.unionpay.mobile.android.model.b.aB || !com.unionpay.mobile.android.model.b.aA || !com.unionpay.mobile.android.model.b.bo) {
                GetSeAppListRequestParams getSeAppListRequestParams = new GetSeAppListRequestParams();
                if (this.f) {
                    getSeAppListRequestParams.setReserve(g());
                }
                if (this.n.getSEAppList(getSeAppListRequestParams, new e(1014, this.G)) != 0) {
                    this.G.sendMessage(Message.obtain(this.G, 1, 1014, 0, ""));
                } else {
                    this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1014, 0, ""), this.D);
                }
                k.c("uppay", "readList");
                return null;
            }
            GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams = new GetCardInfoBySpayRequestParams();
            Amount amount = new Amount();
            amount.setProductPrice(this.A);
            String e2 = e.e(this.B);
            if (!TextUtils.isEmpty(e2)) {
                amount.setCurrencyType(e2);
            }
            getCardInfoBySpayRequestParams.setAmount(amount);
            if (this.f) {
                getCardInfoBySpayRequestParams.setReserve(g());
            }
            int cardInfoBySamsungPay = this.n.getCardInfoBySamsungPay(getCardInfoBySpayRequestParams, new e(1015, this.G));
            if (cardInfoBySamsungPay != 0) {
                this.G.sendMessage(Message.obtain(this.G, 1, 1015, 0, ""));
            } else {
                this.G.sendMessageDelayed(Message.obtain(this.G, 4, 1015, 0, ""), 8000);
            }
            k.c("uppay", "readList: " + cardInfoBySamsungPay);
            k.c("uppay", "readList");
            return null;
        } catch (RemoteException e3) {
            a(false);
            e3.printStackTrace();
        } catch (Exception e4) {
            a(false);
            e4.printStackTrace();
        }
    }

    public final void a() {
        if (this.n != null) {
            this.n.removeConnectionListener(this.J);
            this.n.unbind();
        }
    }

    public final void a(Handler handler) {
        this.o = handler;
    }

    public final void a(b bVar, Context context) {
        this.l = bVar;
        this.k = context;
        try {
            this.e = a.a(KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new RSAPrivateKeySpec(new BigInteger(this.f9658a), new BigInteger(this.c))), this.d);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
        }
        if (com.unionpay.mobile.android.model.b.bm) {
            this.D = 60000;
        }
        this.f = !"com.unionpay.uppay".equals(com.unionpay.mobile.android.utils.f.b(this.k));
        this.n = UPTsmAddon.getInstance(context);
        if (!this.g) {
            this.n.addConnectionListener(this.J);
            this.g = true;
        }
        k.c("uppay-spay", "type se  bind service");
        if (this.n != null && !this.n.isConnected()) {
            k.c("uppay", "bind service");
            if (!this.n.bind()) {
                a(false);
            }
        } else if (this.n != null && this.n.isConnected()) {
            k.c("uppay", "tem service already connected");
            if (!this.h) {
                f();
            } else {
                a(true);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0066 A[LOOP:0: B:13:0x0066->B:16:0x0070, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0046 A[Catch:{ RemoteException -> 0x0054 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] a(byte[] r7, int r8) {
        /*
            r6 = this;
            r0 = 0
            r6.v = r0
            r1 = 0
            r6.u = r1
            java.lang.String r2 = "uppay"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "--->"
            r3.<init>(r4)
            java.lang.String r4 = com.unionpay.mobile.android.pboctransaction.e.a((byte[]) r7)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.unionpay.mobile.android.utils.k.a(r2, r3)
            r2 = 1
            if (r8 != 0) goto L_0x002a
            java.lang.String r7 = com.unionpay.mobile.android.pboctransaction.e.a((byte[]) r7)
            java.lang.String r8 = r6.z
        L_0x0026:
            r6.a((java.lang.String) r7, (java.lang.String) r8)
            goto L_0x0033
        L_0x002a:
            if (r8 != r2) goto L_0x0033
            java.lang.String r7 = com.unionpay.mobile.android.pboctransaction.e.a((byte[]) r7)
            java.lang.String r8 = r6.y
            goto L_0x0026
        L_0x0033:
            com.unionpay.tsmservice.UPTsmAddon r7 = r6.n     // Catch:{ RemoteException -> 0x0054 }
            com.unionpay.tsmservice.request.SendApduRequestParams r8 = r6.I     // Catch:{ RemoteException -> 0x0054 }
            com.unionpay.mobile.android.pboctransaction.samsung.e r3 = new com.unionpay.mobile.android.pboctransaction.samsung.e     // Catch:{ RemoteException -> 0x0054 }
            android.os.Handler r4 = r6.G     // Catch:{ RemoteException -> 0x0054 }
            r5 = 1012(0x3f4, float:1.418E-42)
            r3.<init>(r5, r4)     // Catch:{ RemoteException -> 0x0054 }
            int r7 = r7.sendApdu(r8, r3)     // Catch:{ RemoteException -> 0x0054 }
            if (r7 == 0) goto L_0x0058
            android.os.Handler r7 = r6.G     // Catch:{ RemoteException -> 0x0054 }
            android.os.Handler r8 = r6.G     // Catch:{ RemoteException -> 0x0054 }
            java.lang.String r3 = ""
            android.os.Message r8 = android.os.Message.obtain(r8, r2, r5, r1, r3)     // Catch:{ RemoteException -> 0x0054 }
            r7.sendMessage(r8)     // Catch:{ RemoteException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0058:
            android.os.Handler r7 = r6.G
            android.os.Handler r8 = r6.G
            r2 = 3
            android.os.Message r8 = android.os.Message.obtain(r8, r2)
            r2 = 10000(0x2710, double:4.9407E-320)
            r7.sendMessageDelayed(r8, r2)
        L_0x0066:
            java.lang.String r7 = r6.v
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x0072
            boolean r7 = r6.u
            if (r7 == 0) goto L_0x0066
        L_0x0072:
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r2 = "mApduResult: "
            r8.<init>(r2)
            java.lang.String r2 = r6.v
            r8.append(r2)
            java.lang.String r2 = ",mApduReturn:"
            r8.append(r2)
            boolean r2 = r6.u
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r8)
            java.lang.String r7 = r6.v
            if (r7 == 0) goto L_0x00ae
            java.lang.String r7 = r6.v
            byte[] r0 = com.unionpay.mobile.android.pboctransaction.e.a((java.lang.String) r7)
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r2 = "ret1 <---"
            r8.<init>(r2)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r8)
        L_0x00ae:
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r2 = "<---"
            r8.<init>(r2)
            java.lang.String r2 = r6.v
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r8)
            r6.u = r1
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "ret2 <---"
            r8.<init>(r1)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            com.unionpay.mobile.android.utils.k.a(r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.samsung.f.a(byte[], int):byte[]");
    }

    public final void b() {
    }

    public final void b(String str) {
        this.A = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004e A[LOOP:0: B:14:0x004e->B:17:0x0058, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa A[LOOP:1: B:33:0x00aa->B:36:0x00b4, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c() {
        /*
            r7 = this;
            java.lang.String r0 = r7.y
            r1 = 1
            r2 = 1013(0x3f5, float:1.42E-42)
            r3 = 0
            if (r0 == 0) goto L_0x0060
            java.lang.String r0 = "-1"
            java.lang.String r4 = r7.y
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0060
            java.lang.String r0 = ""
            r7.x = r0
            r7.w = r3
            com.unionpay.tsmservice.request.CloseChannelRequestParams r0 = new com.unionpay.tsmservice.request.CloseChannelRequestParams     // Catch:{ RemoteException -> 0x004a }
            r0.<init>()     // Catch:{ RemoteException -> 0x004a }
            java.lang.String r4 = r7.y     // Catch:{ RemoteException -> 0x004a }
            r0.setChannel(r4)     // Catch:{ RemoteException -> 0x004a }
            boolean r4 = r7.f     // Catch:{ RemoteException -> 0x004a }
            if (r4 == 0) goto L_0x002d
            java.lang.String r4 = r7.g()     // Catch:{ RemoteException -> 0x004a }
            r0.setReserve(r4)     // Catch:{ RemoteException -> 0x004a }
        L_0x002d:
            com.unionpay.tsmservice.UPTsmAddon r4 = r7.n     // Catch:{ RemoteException -> 0x004a }
            com.unionpay.mobile.android.pboctransaction.samsung.e r5 = new com.unionpay.mobile.android.pboctransaction.samsung.e     // Catch:{ RemoteException -> 0x004a }
            android.os.Handler r6 = r7.G     // Catch:{ RemoteException -> 0x004a }
            r5.<init>(r2, r6)     // Catch:{ RemoteException -> 0x004a }
            int r0 = r4.closeChannel(r0, r5)     // Catch:{ RemoteException -> 0x004a }
            if (r0 == 0) goto L_0x004e
            android.os.Handler r0 = r7.G     // Catch:{ RemoteException -> 0x004a }
            android.os.Handler r4 = r7.G     // Catch:{ RemoteException -> 0x004a }
            java.lang.String r5 = ""
            android.os.Message r4 = android.os.Message.obtain(r4, r1, r2, r3, r5)     // Catch:{ RemoteException -> 0x004a }
            r0.sendMessage(r4)     // Catch:{ RemoteException -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004e:
            java.lang.String r0 = r7.x
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x005a
            boolean r0 = r7.w
            if (r0 == 0) goto L_0x004e
        L_0x005a:
            java.lang.String r0 = "-1"
            r7.y = r0
            r7.w = r3
        L_0x0060:
            java.lang.String r0 = r7.z
            if (r0 == 0) goto L_0x00bc
            java.lang.String r0 = "-1"
            java.lang.String r4 = r7.z
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x00bc
            java.lang.String r0 = ""
            r7.x = r0
            r7.w = r3
            com.unionpay.tsmservice.request.CloseChannelRequestParams r0 = new com.unionpay.tsmservice.request.CloseChannelRequestParams     // Catch:{ RemoteException -> 0x00a6 }
            r0.<init>()     // Catch:{ RemoteException -> 0x00a6 }
            java.lang.String r4 = r7.z     // Catch:{ RemoteException -> 0x00a6 }
            r0.setChannel(r4)     // Catch:{ RemoteException -> 0x00a6 }
            boolean r4 = r7.f     // Catch:{ RemoteException -> 0x00a6 }
            if (r4 == 0) goto L_0x0089
            java.lang.String r4 = r7.g()     // Catch:{ RemoteException -> 0x00a6 }
            r0.setReserve(r4)     // Catch:{ RemoteException -> 0x00a6 }
        L_0x0089:
            com.unionpay.tsmservice.UPTsmAddon r4 = r7.n     // Catch:{ RemoteException -> 0x00a6 }
            com.unionpay.mobile.android.pboctransaction.samsung.e r5 = new com.unionpay.mobile.android.pboctransaction.samsung.e     // Catch:{ RemoteException -> 0x00a6 }
            android.os.Handler r6 = r7.G     // Catch:{ RemoteException -> 0x00a6 }
            r5.<init>(r2, r6)     // Catch:{ RemoteException -> 0x00a6 }
            int r0 = r4.closeChannel(r0, r5)     // Catch:{ RemoteException -> 0x00a6 }
            if (r0 == 0) goto L_0x00aa
            android.os.Handler r0 = r7.G     // Catch:{ RemoteException -> 0x00a6 }
            android.os.Handler r4 = r7.G     // Catch:{ RemoteException -> 0x00a6 }
            java.lang.String r5 = ""
            android.os.Message r1 = android.os.Message.obtain(r4, r1, r2, r3, r5)     // Catch:{ RemoteException -> 0x00a6 }
            r0.sendMessage(r1)     // Catch:{ RemoteException -> 0x00a6 }
            goto L_0x00aa
        L_0x00a6:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00aa:
            java.lang.String r0 = r7.x
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00b6
            boolean r0 = r7.w
            if (r0 == 0) goto L_0x00aa
        L_0x00b6:
            java.lang.String r0 = "-1"
            r7.z = r0
            r7.w = r3
        L_0x00bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.samsung.f.c():void");
    }

    public final void c(String str) {
        this.B = str;
    }

    public final void d() {
    }

    public final boolean e() {
        try {
            k.c("uppay", "getVendorPayStatus()");
            if (this.C == null) {
                this.C = new GetVendorPayStatusRequestParams();
                if (this.f) {
                    this.C.setReserve(g());
                }
            }
            if (this.n.getVendorPayStatus(this.C, new e(1018, this.G)) == 0) {
                return true;
            }
            this.G.sendMessage(Message.obtain(this.G, 1, 1018, 0, ""));
            return false;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
