package com.xiaomi.shop2.model;

import com.alibaba.fastjson.annotation.JSONField;

@Deprecated
public class PluginCounter {
    @JSONField(name = "count")
    public int mCount;
    @JSONField(name = "plugin_id")
    public String mPluginId;
    @JSONField(name = "name")
    public String mPluginName;
    @JSONField(name = "tip")
    public String mPluginTip;
    @JSONField(name = "show_point")
    public boolean mShowPoint;
}
