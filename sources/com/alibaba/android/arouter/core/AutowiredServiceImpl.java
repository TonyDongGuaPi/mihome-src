package com.alibaba.android.arouter.core;

import android.content.Context;
import android.util.LruCache;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.utils.Consts;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/arouter/service/autowired")
public class AutowiredServiceImpl implements AutowiredService {

    /* renamed from: a  reason: collision with root package name */
    private LruCache<String, ISyringe> f714a;
    private List<String> b;

    public void init(Context context) {
        this.f714a = new LruCache<>(66);
        this.b = new ArrayList();
    }

    public void autowire(Object obj) {
        String name = obj.getClass().getName();
        try {
            if (!this.b.contains(name)) {
                ISyringe iSyringe = this.f714a.get(name);
                if (iSyringe == null) {
                    iSyringe = (ISyringe) Class.forName(obj.getClass().getName() + Consts.g).getConstructor(new Class[0]).newInstance(new Object[0]);
                }
                iSyringe.inject(obj);
                this.f714a.put(name, iSyringe);
            }
        } catch (Exception unused) {
            this.b.add(name);
        }
    }
}
