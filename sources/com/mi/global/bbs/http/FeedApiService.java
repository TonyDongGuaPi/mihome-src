package com.mi.global.bbs.http;

import com.mi.global.bbs.model.DiscoveryUserModel;
import com.mi.global.bbs.model.SubFollowItem;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FeedApiService {
    @GET("feed/getuser")
    Observable<DiscoveryUserModel> getDiscoveryPeople(@QueryMap Map<String, String> map);

    @GET("feed/getfeed")
    Observable<SubFollowItem> getFeed(@QueryMap Map<String, String> map);
}
