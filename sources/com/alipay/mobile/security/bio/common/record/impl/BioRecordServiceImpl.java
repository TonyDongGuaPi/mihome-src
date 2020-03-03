package com.alipay.mobile.security.bio.common.record.impl;

import com.alipay.mobile.security.bio.common.record.MetaRecord;
import com.alipay.mobile.security.bio.log.BehaviourIdEnum;
import com.alipay.mobile.security.bio.log.VerifyBehavior;
import com.alipay.mobile.security.bio.service.BioRecordService;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.monitorlog.MonitorLogService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.SignHelper;
import java.util.Hashtable;
import java.util.Map;

public class BioRecordServiceImpl extends BioRecordService {

    /* renamed from: a  reason: collision with root package name */
    protected int f976a;
    protected String b;
    protected Map<String, String> c;
    protected Object d;
    private MonitorLogService e;

    public void onDestroy() {
    }

    public BioRecordServiceImpl() {
        this.f976a = 0;
        this.b = "";
        this.c = new Hashtable();
        this.d = new Object();
        this.b = SignHelper.SHA1("" + System.currentTimeMillis() + Math.round(10000.0f));
        synchronized (this.d) {
            if (this.c != null) {
                this.c.clear();
            }
        }
    }

    public void onCreate(BioServiceManager bioServiceManager) {
        super.onCreate(bioServiceManager);
        this.e = (MonitorLogService) bioServiceManager.getBioService(MonitorLogService.class);
        BioLog.w(getClass().getName() + " call mBioServiceManager.getBioService(MonitorLogService.class): " + this.e);
    }

    protected static void a(Map<String, String> map, Map<String, String> map2) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                Object key = next.getKey();
                Object value = next.getValue();
                if (!(key == null || value == null)) {
                    map2.put(key.toString(), value.toString());
                }
            }
        }
    }

    public void write(MetaRecord metaRecord) {
        superWrite(metaRecord);
        BioLog.i(getClass().getSimpleName() + "(sequenceId=" + this.f976a + "):" + metaRecord);
        if (metaRecord != null) {
            VerifyBehavior verifyBehavior = new VerifyBehavior();
            verifyBehavior.setUserCaseID(metaRecord.getCaseID());
            String actionID = metaRecord.getActionID();
            verifyBehavior.setAppID(metaRecord.getAppID());
            verifyBehavior.setSeedID(metaRecord.getSeedID());
            verifyBehavior.setParam1(this.b);
            verifyBehavior.setParam2(metaRecord.getParam2());
            verifyBehavior.setParam3(metaRecord.getParam3());
            verifyBehavior.setBizType(metaRecord.getBizType());
            verifyBehavior.setLoggerLevel(metaRecord.getPriority());
            a(verifyBehavior, this.c);
            a(verifyBehavior, metaRecord.getParam4());
            BehaviourIdEnum convert = BehaviourIdEnum.convert(actionID);
            if (this.e == null) {
                BioLog.e((Throwable) new RuntimeException(getClass().getName() + ".write(" + metaRecord + ") failed. MonitorLogService==null"));
                return;
            }
            this.e.logBehavior(convert, verifyBehavior);
        }
    }

    private void a(VerifyBehavior verifyBehavior, Map<String, String> map) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                Object key = next.getKey();
                Object value = next.getValue();
                if (!(key == null || value == null)) {
                    verifyBehavior.addExtParam(key.toString(), value.toString());
                }
            }
        }
    }

    public void superWrite(MetaRecord metaRecord) {
        synchronized (this.d) {
            if (metaRecord != null) {
                try {
                    metaRecord.setParam1(this.b);
                    if (metaRecord.isEnableSequence()) {
                        this.f976a++;
                        metaRecord.setSequenceId(this.f976a);
                        this.c.put("sequence_id", "" + this.f976a);
                    } else {
                        this.c.remove("sequence_id");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public String getUniqueID() {
        return this.b;
    }

    public void setUniqueID(String str) {
        this.b = str;
    }

    public void setExtProperty(Map<String, String> map) {
        synchronized (this.d) {
            a(map, this.c);
        }
    }

    public int getSequenceID() {
        return this.f976a;
    }
}
