package com.ximalaya.ting.android.opensdk.player.advertis;

import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager;
import java.util.Map;

public interface IXmAdsDataHandle {
    String a(Track track, Map<String, String> map, IDataCallBack<AdvertisList> iDataCallBack);

    void a();

    void a(int i, int i2);

    void a(XmAdsManager.TaskWrapper taskWrapper);

    void a(String str);
}
