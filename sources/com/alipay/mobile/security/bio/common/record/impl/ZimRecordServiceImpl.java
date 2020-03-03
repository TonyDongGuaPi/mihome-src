package com.alipay.mobile.security.bio.common.record.impl;

import android.util.Base64;
import com.alipay.mobile.security.bio.common.record.MetaRecord;
import com.alipay.mobile.security.bio.log.BehaviourIdEnum;
import com.alipay.mobile.security.bio.log.VerifyBehavior;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.ZimRecordService;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.StringUtil;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class ZimRecordServiceImpl extends ZimRecordService {
    public static final String LOG_CLASSIFIERS = "log_classifiers";
    public static final String ZOLOZ_RECORDS = "zoloz_records";

    /* renamed from: a  reason: collision with root package name */
    protected String f977a = "";
    protected int b = 0;
    protected int c = -1;
    protected Map<String, String> d = new Hashtable();
    private MonitorLogService e;
    private final Set<String> f = new HashSet(Arrays.asList(MetaRecord.DEFAULT_LOG_CLASSIFIERS.split("#")));

    public void onCreate(BioServiceManager bioServiceManager) {
        super.onCreate(bioServiceManager);
        this.e = (MonitorLogService) bioServiceManager.getBioService(MonitorLogService.class);
    }

    public boolean write(MetaRecord metaRecord) {
        return write(metaRecord, (Map<String, String>) null);
    }

    public boolean write(MetaRecord metaRecord, Map<String, String> map) {
        JSONObject jSONObject;
        if (metaRecord != null && this.f.contains(metaRecord.getClassifier())) {
            VerifyBehavior verifyBehavior = new VerifyBehavior();
            verifyBehavior.setUserCaseID(metaRecord.getCaseID());
            String actionID = metaRecord.getActionID();
            verifyBehavior.setAppID(metaRecord.getAppID());
            verifyBehavior.setSeedID(metaRecord.getSeedID());
            verifyBehavior.setParam1(this.f977a);
            StringBuilder sb = new StringBuilder();
            int i = this.b + 1;
            this.b = i;
            sb.append(i);
            sb.append("");
            verifyBehavior.setParam2(sb.toString());
            verifyBehavior.setParam3(this.c + "");
            verifyBehavior.setBizType(metaRecord.getBizType());
            verifyBehavior.setLoggerLevel(metaRecord.getPriority());
            verifyBehavior.addExtParam(ViewShot.Results.BASE_64, "true");
            HashMap hashMap = new HashMap();
            for (String next : this.d.keySet()) {
                String str = this.d.get(next);
                if (EXCLUDE_EXT_PARAMS.contains(next)) {
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
            BehaviourIdEnum convert = BehaviourIdEnum.convert(actionID);
            if (this.e == null) {
                BioLog.e((Throwable) new RuntimeException(getClass().getName() + ".write(" + metaRecord + ") failed. MonitorLogService==null"));
            } else {
                this.e.logBehavior(convert, verifyBehavior);
                return true;
            }
        }
        return false;
    }

    public void init(String str, int i, int i2, Map<String, String> map) {
        this.f977a = str;
        this.b = i;
        this.c = i2;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                if (!(next == null || next.getKey() == null || next.getValue() == null)) {
                    this.d.put(next.getKey(), next.getValue());
                }
            }
        }
        BioLog.d("mExtParams:" + StringUtil.map2String(this.d));
    }

    public void addExtProperty(String str, String str2) {
        this.d.put(str, str2);
    }

    public void addExtProperties(Map<String, String> map) {
        this.d.putAll(map);
    }

    public String getUniqueID() {
        return this.f977a;
    }

    public int getSequenceID() {
        return this.b;
    }

    public int getRetryID() {
        return this.c;
    }

    public void retry() {
        this.c++;
    }

    public void setLogClassifier(Set<String> set) {
        if (set != null) {
            this.f.clear();
            this.f.addAll(set);
        }
    }

    public Map<String, String> getExtProperties() {
        return this.d;
    }
}
