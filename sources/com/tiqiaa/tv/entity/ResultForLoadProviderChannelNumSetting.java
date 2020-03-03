package com.tiqiaa.tv.entity;

import com.imi.fastjson.annotation.JSONField;
import com.tiqiaa.common.IJsonable;
import java.util.List;

public class ResultForLoadProviderChannelNumSetting implements IJsonable {
    @JSONField(name = "nums")
    List<ChannelNum> nums;
    @JSONField(name = "reset_provider")
    TvProvider reset_provider;

    public TvProvider getReset_provider() {
        return this.reset_provider;
    }

    public void setReset_provider(TvProvider tvProvider) {
        this.reset_provider = tvProvider;
    }

    public List<ChannelNum> getNums() {
        return this.nums;
    }

    public void setNums(List<ChannelNum> list) {
        this.nums = list;
    }
}
