package com.alipay.mobile.security.bio.service.impl;

import android.content.Context;
import android.content.Intent;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.alipay.mobile.security.bio.common.record.impl.BioRecordServiceImpl;
import com.alipay.mobile.security.bio.common.record.impl.ZimRecordServiceImpl;
import com.alipay.mobile.security.bio.config.Constant;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.module.MicroModule;
import com.alipay.mobile.security.bio.runtime.Runtime;
import com.alipay.mobile.security.bio.service.BioAppDescription;
import com.alipay.mobile.security.bio.service.BioAppManager;
import com.alipay.mobile.security.bio.service.BioMetaInfo;
import com.alipay.mobile.security.bio.service.BioRecordService;
import com.alipay.mobile.security.bio.service.BioService;
import com.alipay.mobile.security.bio.service.BioServiceDescription;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioStoreService;
import com.alipay.mobile.security.bio.service.BioTaskService;
import com.alipay.mobile.security.bio.service.BioUploadService;
import com.alipay.mobile.security.bio.service.ZimRecordService;
import com.alipay.mobile.security.bio.service.local.LocalService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.annotation.JSMethod;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import net.qiujuer.genius.ui.Ui;

public class BioServiceManagerImpl extends BioServiceManager {
    /* access modifiers changed from: private */
    public static boolean h = false;
    private Hashtable<String, BioService> c = new Hashtable<>();
    /* access modifiers changed from: private */
    public final HashMap<String, BioService> d = new HashMap<>();
    private Hashtable<String, BioAppDescription> e = new Hashtable<>();
    private HashMap<String, LocalService> f = new HashMap<>();
    private HashMap<String, BioServiceDescription> g = new HashMap<>();

    public BioServiceManagerImpl(Context context, String str) {
        super(context, str);
        a(context);
        a();
        b(this.f1004a);
    }

    private void a(Context context) {
        Runtime.getLocalService(context, this.f, this.g);
        for (LocalService create : this.f.values()) {
            create.create(this);
        }
    }

    private void a() {
        BioStoreServiceImpl bioStoreServiceImpl = new BioStoreServiceImpl();
        this.c.put(BioStoreService.class.getName(), bioStoreServiceImpl);
        BioTaskServiceImpl bioTaskServiceImpl = new BioTaskServiceImpl(this.f1004a);
        this.c.put(BioTaskService.class.getName(), bioTaskServiceImpl);
        BioRecordServiceImpl bioRecordServiceImpl = new BioRecordServiceImpl();
        this.c.put(BioRecordService.class.getName(), bioRecordServiceImpl);
        ZimRecordServiceImpl zimRecordServiceImpl = new ZimRecordServiceImpl();
        this.c.put(ZimRecordService.class.getName(), zimRecordServiceImpl);
        BioUploadServiceImpl bioUploadServiceImpl = new BioUploadServiceImpl();
        this.c.put(BioUploadService.class.getName(), bioUploadServiceImpl);
        BioAppManager bioAppManager = new BioAppManager();
        this.c.put(BioAppManager.class.getName(), bioAppManager);
        bioStoreServiceImpl.create(this);
        bioTaskServiceImpl.create(this);
        bioRecordServiceImpl.create(this);
        zimRecordServiceImpl.create(this);
        bioUploadServiceImpl.create(this);
        bioAppManager.create(this);
    }

    private void b(Context context) {
        for (BioMetaInfo next : Runtime.getBioMetaInfoList(context, this)) {
            for (BioServiceDescription a2 : next.getExtServices()) {
                a(a2);
            }
            for (BioAppDescription a3 : next.getApplications()) {
                a(a3);
            }
        }
        synchronized (this.d) {
            for (String str : this.d.keySet()) {
                this.d.get(str).create(this);
            }
        }
    }

    private void a(BioServiceDescription bioServiceDescription) {
        try {
            BioService bioService = (BioService) bioServiceDescription.getClazz().newInstance();
            synchronized (this.d) {
                this.d.put(bioServiceDescription.getInterfaceName(), bioService);
            }
        } catch (InstantiationException e2) {
            BioLog.e(bioServiceDescription.getInterfaceName() + e2.toString());
        } catch (IllegalAccessException e3) {
            BioLog.e(bioServiceDescription.getInterfaceName() + e3.toString());
        } catch (Throwable th) {
            BioLog.e(bioServiceDescription.getInterfaceName() + th.toString());
        }
    }

    private void a(BioAppDescription bioAppDescription) {
        if (bioAppDescription != null) {
            String str = "bio_type_" + bioAppDescription.getBioType() + JSMethod.NOT_SET + bioAppDescription.getBioAction();
            if (!this.e.containsKey(str)) {
                this.e.put(str, bioAppDescription);
                return;
            }
            BioLog.d("app exist:" + this.e.get(str).toString());
            BioLog.d("app input:" + bioAppDescription.toString());
        }
    }

    public <T> T getBioService(Class<T> cls) {
        return getBioService(cls.getName());
    }

    public <T> T getBioService(String str) {
        T t;
        try {
            t = this.f.get(str);
        } catch (Throwable th) {
            BioLog.w("Failed to call mLocalServices.get(" + str + "): " + th.toString());
            t = null;
        }
        if (t == null) {
            try {
                BioServiceDescription remove = this.g.remove(str);
                if (remove != null) {
                    T t2 = (LocalService) remove.getClazz().newInstance();
                    t2.create(this);
                    this.f.put(remove.getInterfaceName(), t2);
                    t = t2;
                }
            } catch (Throwable th2) {
                BioLog.w("Failed to call create LazyLocalService(" + str + "): " + th2.toString());
            }
        }
        if (t == null) {
            try {
                t = this.c.get(str);
            } catch (Throwable th3) {
                BioLog.w("Failed to call mSystemServices.get(" + str + "): " + th3.toString());
            }
        }
        if (t != null) {
            return t;
        }
        try {
            synchronized (this.d) {
                try {
                    T t3 = this.d.get(str);
                    try {
                        return t3;
                    } catch (Throwable th4) {
                        T t4 = t3;
                        th = th4;
                        t = t4;
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    throw th;
                }
            }
        } catch (Throwable th6) {
            BioLog.w("Failed to call mExtServices.get(" + str + "): " + th6.toString());
            return t;
        }
    }

    public <T extends BioService> T putBioService(String str, Class<T> cls) {
        T t;
        try {
            t = (BioService) cls.newInstance();
            try {
                t.create(this);
                this.c.put(str, t);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            t = null;
            BioLog.e(th);
            return t;
        }
        return t;
    }

    public void destroy() {
        if (this.d != null) {
            synchronized (this.d) {
                for (String str : this.d.keySet()) {
                    this.d.get(str).destroy();
                }
                this.d.clear();
            }
        }
        if (this.c != null) {
            for (String str2 : this.c.keySet()) {
                this.c.get(str2).destroy();
            }
            this.c.clear();
        }
        if (this.f != null) {
            for (String str3 : this.f.keySet()) {
                this.f.get(str3).destroy();
            }
            this.f.clear();
        }
        if (this.g != null) {
            this.g.clear();
        }
        if (this.e != null) {
            this.e.clear();
        }
    }

    public String startBioActivity(BioAppDescription bioAppDescription, MicroModule microModule) {
        if (bioAppDescription == null) {
            return "";
        }
        String str = "bio_type_" + bioAppDescription.getBioType() + JSMethod.NOT_SET + bioAppDescription.getBioAction();
        BioLog.i("appID:" + str);
        if (!this.e.containsKey(str)) {
            return "";
        }
        BioAppDescription bioAppDescription2 = this.e.get(str);
        bioAppDescription.setAppName(bioAppDescription2.getAppName());
        bioAppDescription.setAppInterfaceName(bioAppDescription2.getAppInterfaceName());
        a(bioAppDescription, microModule);
        return str;
    }

    private boolean b(BioAppDescription bioAppDescription) {
        Map<String, String> extProperty = bioAppDescription.getExtProperty();
        return extProperty != null && !extProperty.isEmpty() && extProperty.containsKey(BioDetector.EXT_KEY_AUTH_IN_BACKGROUND) && Boolean.parseBoolean(extProperty.get(BioDetector.EXT_KEY_AUTH_IN_BACKGROUND));
    }

    private void a(BioAppDescription bioAppDescription, MicroModule microModule) {
        String appInterfaceName = bioAppDescription.getAppInterfaceName();
        if (!StringUtil.isNullorEmpty(appInterfaceName)) {
            Intent intent = new Intent();
            intent.setClassName(this.f1004a, appInterfaceName);
            int i = Ui.b;
            if (b(bioAppDescription)) {
                i = 805339136;
            }
            intent.setFlags(i);
            intent.putExtra(Constant.BIOLOGY_INTENT_ACTION_INFO, bioAppDescription.getTag());
            boolean z = false;
            if (Runtime.isRunningOnQuinox(this.f1004a)) {
                try {
                    z = Runtime.startActivity(intent);
                } catch (Throwable th) {
                    BioLog.w(th);
                }
                BioLog.d("Runtime.startActivity(intent=" + intent + ") : bRet=" + z);
            }
            if (z) {
                return;
            }
            if (this.f1004a != null) {
                this.f1004a.startActivity(intent);
            } else {
                BioLog.e("start APP context null");
            }
        } else {
            throw new BioIllegalArgumentException();
        }
    }

    public int preLoad() {
        BioLog.i("preload:" + h);
        if (h) {
            return 0;
        }
        h = true;
        new Thread(new a(this), "loadingResource").start();
        return 1;
    }
}
