package com.xiaomi.shopviews.adapter;

import com.xiaomi.shopviews.model.item.PageDataBean;
import com.xiaomi.shopviews.model.item.PageDataBeanExtend;

public interface ProviderClickListener {
    void a(int i, int i2);

    void a(PageDataBean pageDataBean, String str, Object obj);

    void a(PageDataBean pageDataBean, String str, String str2, String str3, int i, int i2);

    void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean);

    void a(String str, PageDataBean.AssemblyInfoBean assemblyInfoBean, String str2);

    void a(String str, PageDataBeanExtend.NavList navList, String str2);

    void a(String str, String str2);

    void a(String str, String str2, String str3, String str4);

    void b(String str, PageDataBeanExtend.NavList navList, String str2);
}
