package com.alipay.mobile.security.zim.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.mobile.security.bio.common.record.MetaRecord;
import com.alipay.mobile.security.bio.log.BehaviourIdEnum;
import com.alipay.mobile.security.bio.log.VerifyBehavior;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.ZimRecordService;
import com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.json.JSONObject;

public class a {

    /* renamed from: a  reason: collision with root package name */
    static String f1043a = "zimStart";
    static String b = "verifyRequest";
    static String c = "initProdRequest";
    static String d = "initProdResponse";
    static String e = "authRequest";
    static String f = "authResponse";
    public static String g = "validateRequest";
    public static String h = "validateResponse";
    static String i = "verifyResponse";
    static String j = "zimExit";
    private static a m;
    protected Map<String, String> k = new HashMap();
    protected Context l;
    private final String n;
    private int o = 0;
    private int p = 0;
    private ZimRecordService q;
    private HashMap<String, MetaRecord> r = new HashMap<>();
    private MonitorLogService s;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            m = new a(context);
            aVar = m;
        }
        return aVar;
    }

    public static a a() {
        return m;
    }

    public a(Context context) {
        this.l = context;
        Random random = new Random();
        this.n = (UUID.randomUUID().toString() + random.nextInt() + "").replace("-", "").toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("mUniqueID is:");
        sb.append(this.n);
        BioLog.i(sb.toString());
        this.k.put("logModelVersion", "V1.0");
        this.k.put("logPlanId", "ZOLOZ_LOGPLAN_ALIPAYCLOUD_V1");
        this.k.put("logType", "BI_C_V1");
        this.r.put(f1043a, new MetaRecord("UC-RZHY-170807-01", "event", "20001117", f1043a, 1));
        this.r.put(b, new MetaRecord("UC-RZHY-170807-01", "event", "20001117", b, 1));
        this.r.put(c, new MetaRecord("UC-RZHY-170807-02", "event", "20001117", c, 1));
        this.r.put(d, new MetaRecord("UC-RZHY-170807-03", "event", "20001117", d, 1));
        this.r.put(e, new MetaRecord("UC-RZHY-170807-04", "event", "20001117", e, 1));
        this.r.put(f, new MetaRecord("UC-RZHY-170807-05", "event", "20001117", f, 1));
        this.r.put(g, new MetaRecord("UC-RZHY-170807-06", "event", "20001117", g, 1));
        this.r.put(h, new MetaRecord("UC-RZHY-170807-07", "event", "20001117", h, 1));
        this.r.put(i, new MetaRecord("UC-RZHY-170807-08", "event", "20001117", i, 1));
        this.r.put(j, new MetaRecord("UC-RZHY-170807-09", "event", "20001117", j, 1));
    }

    public void a(String str) {
        BioServiceManager currentInstance = BioServiceManager.getCurrentInstance();
        if (this.q == null) {
            this.q = (ZimRecordService) currentInstance.getBioService(ZimRecordService.class.getName());
            this.k.put("zimId", str);
            String str2 = "";
            ApSecurityService apSecurityService = (ApSecurityService) currentInstance.getBioService(ApSecurityService.class);
            if (apSecurityService != null) {
                str2 = apSecurityService.getApDidToken();
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = ApSecurityService.getStaticApDidToken();
            }
            this.k.put("apdidToken", str2);
            this.q.init(this.n, this.o, this.p, this.k);
            if (this.s != null) {
                this.s.destroy();
                this.s = null;
            }
        }
    }

    public boolean b(String str) {
        return a(str, (Map<String, String>) null);
    }

    public boolean a(String str, Map<String, String> map) {
        if (this.r.containsKey(str)) {
            return a(this.r.get(str), map);
        }
        BioLog.w("Not support record: key=" + str);
        return false;
    }

    public boolean a(MetaRecord metaRecord, Map<String, String> map) {
        JSONObject jSONObject;
        if (this.q != null) {
            return this.q.write(metaRecord, map);
        }
        if (this.s == null) {
            this.s = (MonitorLogService) BioServiceManager.getLocalService(this.l, MonitorLogService.class);
            if (this.s == null) {
                BioLog.w((Throwable) new IllegalStateException("mZimRecordService == null && mMonitorLogService == null"));
                return false;
            }
            this.s.create((BioServiceManager) null);
        }
        VerifyBehavior verifyBehavior = new VerifyBehavior();
        verifyBehavior.setUserCaseID(metaRecord.getCaseID());
        String actionID = metaRecord.getActionID();
        verifyBehavior.setAppID(metaRecord.getAppID());
        verifyBehavior.setSeedID(metaRecord.getSeedID());
        verifyBehavior.setParam1(this.n);
        StringBuilder sb = new StringBuilder();
        int i2 = this.o + 1;
        this.o = i2;
        sb.append(i2);
        sb.append("");
        verifyBehavior.setParam2(sb.toString());
        verifyBehavior.setParam3(this.p + "");
        verifyBehavior.setBizType(metaRecord.getBizType());
        verifyBehavior.setLoggerLevel(metaRecord.getPriority());
        verifyBehavior.addExtParam(ViewShot.Results.BASE_64, "true");
        HashMap hashMap = new HashMap();
        for (String next : this.k.keySet()) {
            String str = this.k.get(next);
            if (ZimRecordService.EXCLUDE_EXT_PARAMS.contains(next)) {
                verifyBehavior.addExtParam(next, str);
            } else {
                hashMap.put(next, str);
            }
        }
        verifyBehavior.addExtParam("publicParam", Base64.encodeToString(new JSONObject(hashMap).toString().getBytes(), 2));
        if (map != null) {
            jSONObject = new JSONObject(map);
        } else {
            jSONObject = new JSONObject();
        }
        verifyBehavior.addExtParam("extParam", Base64.encodeToString(jSONObject.toString().getBytes(), 2));
        this.s.logBehavior(BehaviourIdEnum.convert(actionID), verifyBehavior);
        return true;
    }

    public void b() {
        this.r.clear();
        this.q = null;
        this.p = 0;
        this.o = 0;
        m = null;
    }
}
