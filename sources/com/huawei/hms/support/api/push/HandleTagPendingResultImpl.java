package com.huawei.hms.support.api.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.push.TagsResp;
import com.huawei.hms.support.api.push.a.a.a.c;
import org.json.JSONArray;
import org.json.JSONObject;

public class HandleTagPendingResultImpl extends a<HandleTagsResult, TagsResp> {

    /* renamed from: a  reason: collision with root package name */
    private ApiClient f5884a;

    public HandleTagPendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        super(apiClient, str, iMessageEntity);
        this.f5884a = apiClient;
    }

    public HandleTagsResult onComplete(TagsResp tagsResp) {
        if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("HandleTagPendingResultImpl", "report tag completely, retcode is:" + tagsResp.getRetCode());
        }
        if (907122001 == tagsResp.getRetCode()) {
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("HandleTagPendingResultImpl", "report tag success.");
            }
            a(this.f5884a, tagsResp.getContent());
        }
        HandleTagsResult handleTagsResult = new HandleTagsResult();
        handleTagsResult.setStatus(new Status(tagsResp.getRetCode()));
        handleTagsResult.setTagsRes(tagsResp);
        return handleTagsResult;
    }

    private static void a(ApiClient apiClient, String str) {
        if (apiClient != null) {
            try {
                JSONArray a2 = com.huawei.hms.support.api.push.a.a.a.a.a(str);
                if (a2 != null) {
                    c cVar = new c(apiClient.getContext(), "tags_info");
                    int length = a2.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = a2.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("tagKey");
                            int optInt = optJSONObject.optInt("opType");
                            if (1 == optInt) {
                                cVar.a(optString, (Object) optJSONObject.optString("tagValue"));
                            } else if (2 == optInt) {
                                cVar.d(optString);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if (com.huawei.hms.support.log.a.c()) {
                    com.huawei.hms.support.log.a.c("HandleTagPendingResultImpl", "when adding or deleting tags from file excepiton," + e.getMessage());
                }
            }
        } else if (com.huawei.hms.support.log.a.a()) {
            com.huawei.hms.support.log.a.a("HandleTagPendingResultImpl", "the client is null when adding or deleting tags from file.");
        }
    }
}
