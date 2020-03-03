package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.tiqiaa.common.IJsonable;
import com.tiqiaa.icontrol.util.LanguageUtils;

@Table(name = "tb_tv_provider")
public class TvProvider implements IJsonable {
    public static final int CUSTOM_PROVIDER_ID = -1;
    public static final int EMPTY_PROVIDER_ID = 16777215;
    public static final int OTT_PROVIDER_ID = 15658734;
    @JSONField(name = "custom")
    boolean custom;
    @Id
    @NoAutoIncrement
    @JSONField(name = "id")
    int id;
    @JSONField(name = "name")
    String name;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isCustom() {
        return this.custom;
    }

    public void setCustom(boolean z) {
        this.custom = z;
    }

    public static TvProvider createEmptyProvider() {
        TvProvider tvProvider = new TvProvider();
        tvProvider.setId(16777215);
        if (LanguageUtils.getLang() == 0) {
            tvProvider.setName("自定义");
        } else {
            tvProvider.setName("Custom Provider");
        }
        return tvProvider;
    }

    public static TvProvider createOttProvider() {
        TvProvider tvProvider = new TvProvider();
        tvProvider.setId(OTT_PROVIDER_ID);
        if (LanguageUtils.getLang() == 0) {
            tvProvider.setName("OTT盒子");
        } else {
            tvProvider.setName("OTT Box");
        }
        return tvProvider;
    }
}
