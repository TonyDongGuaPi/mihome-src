package com.huawei.hms.support.api.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.push.GetTagsResp;
import com.huawei.hms.support.api.push.HmsPushConst;
import com.huawei.hms.support.api.push.a.a.a.c;

public class GetTagsPendingResultImpl extends a<GetTagResult, GetTagsResp> {

    /* renamed from: a  reason: collision with root package name */
    private ApiClient f5882a;

    public GetTagsPendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        super(apiClient, str, iMessageEntity);
        this.f5882a = apiClient;
    }

    public GetTagResult onComplete(GetTagsResp getTagsResp) {
        GetTagResult getTagResult = new GetTagResult();
        try {
            getTagsResp.setTags(new c(this.f5882a.getContext(), "tags_info").a());
            getTagResult.setTagsRes(getTagsResp);
            getTagResult.setStatus(Status.SUCCESS);
        } catch (Exception e) {
            if (com.huawei.hms.support.log.a.d()) {
                com.huawei.hms.support.log.a.d("GetTagsPendingResultImpl", "get tags failed, error:" + e.getMessage());
            }
            getTagResult.setTagsRes(getTagsResp);
            getTagResult.setStatus(new Status(HmsPushConst.ErrorCode.REPORT_SYSTEM_ERROR));
        }
        return getTagResult;
    }
}
