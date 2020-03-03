package com.tiqiaa.icontrol.util;

import android.content.Context;
import com.imi.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tiqiaa.irdnasdk.IrDnaSdkHelper;
import com.tiqiaa.util.NetUtil;

public class NetUtils {
    public static final String REQUEST_CLIENT_PARAMS_KEY = "client_request_params";
    private static final String TAG = "NetUtils";
    private static final String TQ_REQUEST_PARAMS_KEY = "params";
    private Context context;
    private HttpUtils httpUtils = new HttpUtils(15000);

    public NetUtils(Context context2) {
        this.context = context2;
    }

    public void doPost(String str, Object obj, RequestCallBack<String> requestCallBack) {
        try {
            RequestParams requestParams = new RequestParams();
            requestParams.d("token", IrDnaSdkHelper.getToken(0));
            if (obj != null) {
                requestParams.d("params", NetUtil.encode(this.context, JSON.toJSONString(obj), 0));
                LogUtil.e(TAG, "\r\nrequest param:" + JSON.toJSONString(obj));
            }
            this.httpUtils.a(HttpRequest.HttpMethod.POST, str, requestParams, requestCallBack);
        } catch (Exception e) {
            e.printStackTrace();
            requestCallBack.onFailure((HttpException) null, e.toString());
        }
    }

    public void doPostTv(String str, RequestDTO requestDTO, RequestCallBack<String> requestCallBack) {
        try {
            LogUtil.d(TAG, "postRequest..............01");
            String requestJson = DTOUtil.getRequestJson(requestDTO);
            RequestParams requestParams = new RequestParams();
            requestParams.d("client_request_params", requestJson);
            this.httpUtils.a(HttpRequest.HttpMethod.POST, str, requestParams, requestCallBack);
        } catch (Exception e) {
            e.printStackTrace();
            requestCallBack.onFailure((HttpException) null, e.toString());
        }
    }
}
