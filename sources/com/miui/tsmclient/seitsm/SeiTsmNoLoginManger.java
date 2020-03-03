package com.miui.tsmclient.seitsm;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.net.TSMNoLoginConstants;
import com.miui.tsmclient.net.TSMNoLoginManager;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SeiTsmNoLoginManger extends TSMNoLoginManager {
    public TsmRpcModels.TsmSessionInfo createSession(Context context, CardInfo cardInfo) throws IOException {
        String str;
        String nfcId = SysUtils.getNfcId(context);
        if (TextUtils.isEmpty(nfcId)) {
            LogUtils.w("failed to create session, nfc id is null or empty.");
            return null;
        }
        try {
            str = cardInfo.getTerminal().getCPLC();
        } catch (InterruptedException unused) {
            LogUtils.d("createSession is interrupted.");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtils.w("failed to create session, cplc is empty.");
            return null;
        }
        String imei = SysUtils.getImei(context, cardInfo);
        if (TextUtils.isEmpty(imei)) {
            LogUtils.w("failed to create session, deviceId is empty.");
            return null;
        }
        String str2 = (String) sendRequest(context, AuthRequest.AuthRequestBuilder.newBuilder((AccountInfo) null, TSMNoLoginConstants.URL_CREATE_SESSION, AuthRequest.RespContentType.protobuf).addParams(TSMNoLoginConstants.PARAM_NFC_ID, nfcId).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.SeInfo.newBuilder().setCplc(ByteString.copyFrom(Coder.hexStringToBytes(str))).setDeviceType(SysUtils.getDeviceModel(cardInfo)).setDeviceId(Coder.hashDeviceInfo(imei)).setActionSource(TsmRpcModels.ActionSource.APP_CLIENT).build().toByteArray())).create());
        if (!TextUtils.isEmpty(str2)) {
            return TsmRpcModels.TsmSessionInfo.parseFrom(Coder.decodeBase64ToByteArray(str2));
        }
        return null;
    }

    public TsmRpcModels.TsmAPDUCommand startSeOperation(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.SeOperationType seOperationType, CardInfo cardInfo, Bundle bundle) throws IOException {
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to start se operation, session info is null");
            return null;
        } else if (seOperationType == null) {
            LogUtils.w("failed to start se operation, operation type is null");
            return null;
        } else {
            TsmRpcModels.SeOperation.Builder type = TsmRpcModels.SeOperation.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setType(seOperationType);
            if (cardInfo != null && !TextUtils.isEmpty(cardInfo.mAid)) {
                type.setAid(cardInfo.mAid);
            }
            if (bundle != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.putOpt("nfc_id", bundle.get("nfc_id"));
                    LogUtils.v("json: " + jSONObject.toString());
                    type.setExtra(jSONObject.toString());
                } catch (JSONException e) {
                    LogUtils.e("failed to start se operation", e);
                }
            }
            String str = (String) sendRequest(context, AuthRequest.AuthRequestBuilder.newBuilder((AccountInfo) null, TSMNoLoginConstants.URL_START_SE_OPERATION, AuthRequest.RespContentType.protobuf).addParams("req", Coder.encodeBase64Coder(type.build().toByteArray())).create());
            if (!TextUtils.isEmpty(str)) {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
            }
            return null;
        }
    }

    public TsmRpcModels.TsmAPDUCommand processSeResponse(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, List<TsmRpcModels.SeAPDUResponseItem> list) throws IOException {
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to process se response, session info is null");
            return null;
        }
        String str = (String) sendRequest(context, AuthRequest.AuthRequestBuilder.newBuilder((AccountInfo) null, TSMNoLoginConstants.URL_PROCESS_SE_RESPONSE, AuthRequest.RespContentType.protobuf).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.SeAPDUResponse.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).addAllResponse(list).build().toByteArray())).create());
        if (!TextUtils.isEmpty(str)) {
            return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(str));
        }
        return null;
    }
}
