package com.miui.tsmclient.seitsm;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.entity.BankCardInfo;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.InAppTransData;
import com.miui.tsmclient.entity.MifareCardInfo;
import com.miui.tsmclient.entity.MifareTag;
import com.miui.tsmclient.entity.RiskInfo;
import com.miui.tsmclient.entity.UserExceptionLogInfo;
import com.miui.tsmclient.model.mitsm.PullDataOperationType;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.AuthRequest;
import com.miui.tsmclient.net.BaseAuthManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.seitsm.Exception.SeiTSMApiException;
import com.miui.tsmclient.seitsm.TsmRpcModels;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.StringUtils;
import com.miui.tsmclient.util.SysUtils;
import com.miui.tsmclient.util.TSMLocationService;
import com.taobao.weex.annotation.JSMethod;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SeiTsmAuthManager extends BaseAuthManager {
    public static final String APPLY_CHANNEL_BANK = "00";
    public static final String APPLY_CHANNEL_XIAOMI = "01";
    public static final String EXTRA_APPLY_CHANNEL = "extra_apply_channel";
    public static final String EXTRA_AUTHENTICATION_CODE = "authentication_code";
    public static final String EXTRA_CARD_NAME = "card_name";
    public static final String EXTRA_CITY_ID = "extra_city_id";
    public static final String EXTRA_NEED_PHONE_NUMBER = "need_phone_number";
    public static final String EXTRA_NFC_ID = "nfc_id";
    public static final String EXTRA_SOURCE_CHANNEL = "extra_source_channel";

    public TsmRpcModels.TsmSessionInfo createSession(Context context, CardInfo cardInfo) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(cardInfo);
        String imei = SysUtils.getImei(context, cardInfo);
        if (!TextUtils.isEmpty(imei)) {
            TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel(cardInfo));
            try {
                return TsmRpcModels.TsmSessionInfo.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CREATE_SESSION, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.SeInfo.newBuilder().setCplc(ByteString.copyFrom(Coder.hexStringToBytes(cplc))).setDeviceType(SysUtils.getDeviceModel(cardInfo)).setDeviceId(Coder.hashDeviceInfo(imei)).setActionSource(TsmRpcModels.ActionSource.APP_CLIENT).setDeviceInfo(deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType(cardInfo)).setTsmclientVersionCode(SysUtils.getAppVersionCode(context)).build()).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("createSession failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("createSession failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            throw new SeiTSMApiException(15);
        }
    }

    public TsmRpcModels.TsmAPDUCommand startSeOperation(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, TsmRpcModels.SeOperationType seOperationType, CardInfo cardInfo, Bundle bundle) throws SeiTSMApiException {
        JSONObject jSONObject;
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to start se operation, session info is null");
            throw new SeiTSMApiException(1);
        } else if (seOperationType != null) {
            TsmRpcModels.SeOperation.Builder type = TsmRpcModels.SeOperation.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setType(seOperationType);
            if (cardInfo != null && !TextUtils.isEmpty(cardInfo.mAid)) {
                type.setAid(cardInfo.mAid);
            }
            if (bundle != null) {
                String string = bundle.getString("authentication_code");
                if (!TextUtils.isEmpty(string)) {
                    type.setAuthenticationCode(ByteString.copyFrom(Coder.hexStringToBytes(string)));
                }
                String string2 = bundle.getString("extra_city_id");
                if (!TextUtils.isEmpty(string2)) {
                    type.setCityId(string2);
                }
                String string3 = bundle.getString("extra_source_channel");
                if (!TextUtils.isEmpty(string3)) {
                    type.setSourceChannel(string3);
                }
                String string4 = bundle.getString(EXTRA_NEED_PHONE_NUMBER);
                try {
                    if (!TextUtils.isEmpty(string4)) {
                        jSONObject = new JSONObject(string4);
                    } else {
                        jSONObject = new JSONObject();
                    }
                    if (cardInfo != null && !TextUtils.isEmpty(cardInfo.mCardType)) {
                        jSONObject.put("card_name", cardInfo.mCardType);
                    }
                    jSONObject.putOpt(TSMAuthContants.PARAM_SERVICE_TYPE, Integer.valueOf(bundle.getInt(TSMAuthContants.PARAM_SERVICE_TYPE, -1)));
                    jSONObject.putOpt("nfc_id", bundle.get("nfc_id"));
                    String string5 = bundle.getString(Constants.EXTRA_BUSINESS_ID);
                    if (!TextUtils.isEmpty(string5)) {
                        jSONObject.putOpt(Constants.EXTRA_BUSINESS_ID, string5);
                    }
                    LogUtils.v("json: " + jSONObject.toString());
                    type.setExtra(jSONObject.toString());
                } catch (JSONException e) {
                    LogUtils.e("failed to start se operation", e);
                }
            }
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_START_SE_OPERATION, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(type.build().toByteArray())).create())));
            } catch (AuthApiException e2) {
                LogUtils.e("startSeOperation failed, code: " + e2.mErrorCode + ", msg: " + e2.mErrorMsg, e2);
                throw new SeiTSMApiException(e2.mErrorCode, e2.mErrorMsg);
            } catch (InvalidProtocolBufferException e3) {
                LogUtils.e("startSeOperation failed", e3);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to start se operation, operation type is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.TsmAPDUCommand saveAppKey(Context context, Bundle bundle, TsmRpcModels.TsmSessionInfo tsmSessionInfo) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to save app key, session info is null");
            throw new SeiTSMApiException(1);
        } else if (bundle != null) {
            String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
            String imei = SysUtils.getImei(context, (CardInfo) null);
            if (!TextUtils.isEmpty(imei)) {
                try {
                    return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_SAVE_APP_KEY, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.SaveAppKeyRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setAppName("tsmclient").setCpuModel(bundle.getString(Constants.KEY_CPU_MODEL)).setTzId(bundle.getString(Constants.KEY_TZID)).setKeyAlg(bundle.getString(Constants.KEY_KEY_ALG)).setPkX(bundle.getString(Constants.KEY_PKX)).setPkY(bundle.getString(Constants.KEY_PKY)).setClientSign(bundle.getString("sign")).setDeviceModel(bundle.getString("deviceModel")).setDeviceId(Coder.hashDeviceInfo(imei)).setCplc(cplc).build().toByteArray())).create())));
                } catch (AuthApiException e) {
                    LogUtils.e("saveAppKey failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                    throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
                } catch (InvalidProtocolBufferException e2) {
                    LogUtils.e("saveAppKey failed", e2);
                    throw new SeiTSMApiException(16);
                }
            } else {
                LogUtils.w("failed to save app key, deviceId  is empty.");
                throw new SeiTSMApiException(15);
            }
        } else {
            LogUtils.w("failed to save app key, param is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.TsmAPDUCommand processSeResponse(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, List<TsmRpcModels.SeAPDUResponseItem> list) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo != null) {
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_PROCESS_SE_RESPONSE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.SeAPDUResponse.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).addAllResponse(list).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("processSeResponse failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("processSeResponse failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to process se response, session info is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.TsmAPDUCommand startTopupOperation(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, CardInfo cardInfo, String str, Bundle bundle) throws SeiTSMApiException {
        String str2;
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to start top-up operation, session info is null");
            throw new SeiTSMApiException(1);
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(cardInfo.mAid)) {
            LogUtils.w("failed to start top-up operation, authenticationCode is empty or appAid is empty");
            throw new SeiTSMApiException(1);
        } else {
            TsmRpcModels.TopUpOperation.Builder newBuilder = TsmRpcModels.TopUpOperation.newBuilder();
            TsmRpcModels.TopUpOperation.Builder authenticationCode = newBuilder.setSessionId(tsmSessionInfo.getSessionId()).setAid(cardInfo.mAid).setAuthenticationCode(ByteString.copyFrom(Coder.hexStringToBytes(str)));
            if (TextUtils.isEmpty(cardInfo.mCardNo)) {
                str2 = "";
            } else if (TextUtils.isEmpty(cardInfo.mRealCardNo)) {
                str2 = cardInfo.mCardNo;
            } else {
                str2 = cardInfo.mCardNo + i.b + cardInfo.mRealCardNo;
            }
            authenticationCode.setCardNumber(str2).setBalance(cardInfo.mCardBalance);
            if (bundle != null) {
                String string = bundle.getString("extra_city_id");
                if (!TextUtils.isEmpty(string)) {
                    newBuilder.setCityId(string);
                }
            }
            if (bundle != null && bundle.containsKey(EXTRA_NEED_PHONE_NUMBER)) {
                String string2 = bundle.getString(EXTRA_NEED_PHONE_NUMBER);
                if (!TextUtils.isEmpty(string2)) {
                    newBuilder.setExtra(string2);
                }
            }
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_TOPUP, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(newBuilder.build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("startTopupOperation failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("startTopupOperation failed", e2);
                throw new SeiTSMApiException(16);
            }
        }
    }

    public TsmRpcModels.CommonResponse requestVerificationCode(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, String str) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to request verification code, session info is null");
            throw new SeiTSMApiException(1);
        } else if (!TextUtils.isEmpty(str)) {
            try {
                return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_REQUEST_VERTIFICATION_CODE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.RequestVerificationCode.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setVirtualCardReferenceId(str).setOptMethod(TsmRpcModels.OtpMethod.SMS).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("requestVerificationCode failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("requestVerificationCode failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to request verification code, card reference id is empty");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse verifyVerificationCode(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, String str, String str2) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to verify verification code, session info is null");
            throw new SeiTSMApiException(1);
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtils.w("failed to verify verification code, card referenceId or verification code is empty");
            throw new SeiTSMApiException(1);
        } else {
            try {
                return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_VERIFICATION_CODE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.VerifyVerificationCode.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setVirtualCardReferenceId(str).setVerificationCode(str2).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("verifyVerificationCode failed, code: " + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("verifyVerificationCode failed", e2);
                throw new SeiTSMApiException(16);
            }
        }
    }

    public TsmRpcModels.QueryBankCardInfoResponse queryBankCardInfo(Context context, String str) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        TsmRpcModels.QueryBankCardInfoRequest.Builder cplc = TsmRpcModels.QueryBankCardInfoRequest.newBuilder().setCplc(getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY)));
        if (!TextUtils.isEmpty(str)) {
            cplc.setAid(str);
        }
        try {
            return TsmRpcModels.QueryBankCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_BANK_CARD_INFO, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(cplc.build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("queryBankCardInfo failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("queryBankCardInfo failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.CommonResponse deleteAllBankCards(Context context) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        try {
            return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_DELETE_BANK_CARDS, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).create())));
        } catch (AuthApiException e) {
            LogUtils.e("deleteAllBankCards failed, code: " + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("deleteAllBankCards failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.QueryPanResponse queryPan(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, Bundle bundle) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo != null) {
            TsmRpcModels.QueryPanRequest.Builder sessionId = TsmRpcModels.QueryPanRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId());
            if (bundle.getLong(TSMAuthContants.PARAM_CARD_BIND_ID) != 0) {
                sessionId.setBindId(bundle.getLong(TSMAuthContants.PARAM_CARD_BIND_ID));
            } else {
                sessionId.setPan(bundle.getString("pan"));
            }
            try {
                return TsmRpcModels.QueryPanResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_QUERY_PAN, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(sessionId.build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("queryPan failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("queryPan failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to query pan, session info is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.VirtualCardInfoResponse enrollUPCard(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, CardInfo cardInfo, Bundle bundle) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to enroll up card, session info is null");
            throw new SeiTSMApiException(1);
        } else if (bundle != null) {
            byte[] byteArray = bundle.getByteArray(TSMAuthContants.PARAM_CIPHER_CARD_INFO);
            if (byteArray != null) {
                TsmRpcModels.EnrollUPCardRequest.Builder sourceChannel = TsmRpcModels.EnrollUPCardRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setCardInfo(ByteString.copyFrom(byteArray)).setSourceChannel(bundle.getString("extra_source_channel"));
                RiskInfo riskInfo = (RiskInfo) bundle.getParcelable(TSMAuthContants.PARAM_RISK_INFO);
                if (riskInfo != null) {
                    Location lastLocation = TSMLocationService.getInstance(context).getLastLocation();
                    riskInfo.setDeviceLocation(StringUtils.join(",", String.valueOf(lastLocation.getLongitude()), String.valueOf(lastLocation.getLatitude())));
                    sourceChannel.setRiskInfo(riskInfo.buildTSMRiskInfoBuilder().build());
                }
                BankCardInfo bankCardInfo = (BankCardInfo) cardInfo;
                if (bankCardInfo.mBankCardType == 1) {
                    sourceChannel.setBankCardType(TsmRpcModels.BankCardType.DEBIT);
                    byte[] byteArray2 = bundle.getByteArray(TSMAuthContants.PARAM_CIPHER_PIN_INFO);
                    if (byteArray2 != null) {
                        sourceChannel.setPinInfo(ByteString.copyFrom(byteArray2));
                    }
                } else {
                    sourceChannel.setBankCardType(TsmRpcModels.BankCardType.CREDIT);
                    byte[] byteArray3 = bundle.getByteArray(TSMAuthContants.PARAM_CIPHER_CVV2_INFO);
                    if (byteArray3 != null) {
                        sourceChannel.setCvn2Info(ByteString.copyFrom(byteArray3));
                    }
                }
                if (!TextUtils.isEmpty(bankCardInfo.mBankCardPan)) {
                    sourceChannel.setCardNumber(bankCardInfo.mBankCardPan);
                }
                String string = bundle.getString(EXTRA_APPLY_CHANNEL);
                if (!TextUtils.isEmpty(string)) {
                    sourceChannel.setApplyChannel(string);
                } else {
                    sourceChannel.setApplyChannel("01");
                }
                try {
                    return TsmRpcModels.VirtualCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_ENROLL_UP_CARD, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(sourceChannel.build().toByteArray())).create())));
                } catch (AuthApiException e) {
                    LogUtils.e("enrollUPCard failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                    throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
                } catch (InvalidProtocolBufferException e2) {
                    LogUtils.e("enrollUPCard failed", e2);
                    throw new SeiTSMApiException(16);
                }
            } else {
                LogUtils.d("failed to enroll up card, crypt card info is null");
                throw new SeiTSMApiException(1);
            }
        } else {
            LogUtils.d("failed to enroll up card, param is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.TsmAPDUCommand preparePayApplet(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, Bundle bundle) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to prepare pay applet, session info is null");
            throw new SeiTSMApiException(1);
        } else if (bundle != null) {
            TsmRpcModels.CardIssuerInfo.Builder newBuilder = TsmRpcModels.CardIssuerInfo.newBuilder();
            int i = bundle.getInt(TSMAuthContants.PARAM_ISSUER_CHANNEL);
            if (i == 2) {
                newBuilder.setCardIssueChannel(TsmRpcModels.CardIssueChannel.CMB);
            } else if (i == 1) {
                newBuilder.setCardIssueChannel(TsmRpcModels.CardIssueChannel.UP);
            }
            String string = bundle.getString(TSMAuthContants.PARAM_ISSUER_ID);
            if (string != null) {
                newBuilder.setIssuerId(string);
            }
            TsmRpcModels.PreparePayAppletRequest.Builder sessionId = TsmRpcModels.PreparePayAppletRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId());
            switch (bundle.getInt(TSMAuthContants.PARAM_BANKCARD_TYPE)) {
                case 1:
                    sessionId.setCardType(TsmRpcModels.BankCardType.DEBIT);
                    break;
                case 2:
                    sessionId.setCardType(TsmRpcModels.BankCardType.CREDIT);
                    break;
            }
            sessionId.setCardIssuerInfo(newBuilder.build());
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_PREPARE_PAY_APPLET, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(sessionId.build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("preparePayApplet failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("preparePayApplet failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to prepare pay applet, param is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.TsmAPDUCommand pullPersonData(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, CardInfo cardInfo, Bundle bundle) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo != null) {
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_PULL_PERSO_DATA, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.PullPersoDataRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("pullPersoData failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("pullPersoData failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to pull personal data, session info is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse persoFinishNotify(Context context, CardInfo cardInfo, boolean z, List<TsmRpcModels.SeAPDUResponseItem> list, TsmRpcModels.TsmSessionInfo tsmSessionInfo) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo != null) {
            if (cardInfo != null) {
                BankCardInfo bankCardInfo = (BankCardInfo) cardInfo;
                if (bankCardInfo.mVCReferenceId != null) {
                    TsmRpcModels.PersoFinishNotifyRequest.Builder sessionId = TsmRpcModels.PersoFinishNotifyRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId());
                    sessionId.setOperationResult(z);
                    sessionId.setVirtualCardReferenceId(bankCardInfo.mVCReferenceId);
                    sessionId.addAllResponse(list);
                    try {
                        return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_PERSO_FINISH_NOTIFY, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(sessionId.build().toByteArray())).create())));
                    } catch (AuthApiException e) {
                        LogUtils.e("persoFinishNotify failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                        throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
                    } catch (InvalidProtocolBufferException e2) {
                        LogUtils.e("persoFinishNotify failed", e2);
                        throw new SeiTSMApiException(16);
                    }
                }
            }
            LogUtils.w("failed to notify personal finishing, card info or card referenceId is null");
            throw new SeiTSMApiException(1);
        }
        LogUtils.w("failed to notify personal finishing, session info is null");
        throw new SeiTSMApiException(1);
    }

    public TsmRpcModels.TsmAPDUCommand pullBusCardPersoData(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, PullDataOperationType pullDataOperationType) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo != null) {
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, pullDataOperationType.getPath(), AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.PullBusCardDataRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e(pullDataOperationType.getDescription() + ": pullBusCardPersoData failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e(pullDataOperationType.getDescription() + ": pullBusCardPersoData failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w(pullDataOperationType.getDescription() + ": failed to pull bus card personal data, session info is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse processFinishNotify(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, boolean z, TsmRpcModels.SeOperationType seOperationType, List<TsmRpcModels.SeAPDUResponseItem> list) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (tsmSessionInfo == null) {
            LogUtils.w("failed to process finishing notification, session info is null");
            throw new SeiTSMApiException(1);
        } else if (seOperationType != null) {
            try {
                return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_PROCESS_FINISH_NOTIFY, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.ProcessFinishNotifyRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).setActionResult(z).setActionType(seOperationType).addAllResponse(list).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("processFinishNotify failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("processFinishNotify failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to process finishing notification, se operation type is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse isBankCardServiceAvailable(Context context) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel((CardInfo) null));
        try {
            return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_IS_BANKCARD_SERVICE_AVAILABLE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType((CardInfo) null)).build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("isBankCardServiceAvailable failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("isBankCardServiceAvailable failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.CommonResponse isServiceAvailable(Context context, TsmRpcModels.ServiceType serviceType, String str) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
        TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel((CardInfo) null));
        TsmRpcModels.DeviceInfo build = deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType((CardInfo) null)).build();
        TsmRpcModels.CommonRiskInfo.Builder addAllSimNumber = TsmRpcModels.CommonRiskInfo.newBuilder().setAccountId(accountInfo.getUserId()).setMd5Imei(Coder.encodeMD5(SysUtils.getImei(context, (CardInfo) null))).addAllSimNumber(SysUtils.getSIMNumber((CardInfo) null));
        Location lastLocation = TSMLocationService.getInstance(context).getLastLocation();
        if (lastLocation != null) {
            addAllSimNumber.setLocation(StringUtils.join(",", String.valueOf(lastLocation.getLongitude()), String.valueOf(lastLocation.getLatitude())));
        }
        try {
            return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CHECK_SERVICE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.CheckServiceRequest.newBuilder().setDeviceInfo(build).setSeviceType(serviceType).setRiskInfo(addAllSimNumber.build()).setCplc(cplc).setExtra(str).build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("isServiceAvailable failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("isServiceAvailable failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.TsmAPDUCommand installDoorCard(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, String str, MifareTag mifareTag, int i, String str2, String str3) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        TsmRpcModels.DoorCardType valueOf = TsmRpcModels.DoorCardType.valueOf(i);
        if (valueOf == null) {
            LogUtils.w("failed to install door card, card type is empty.");
            throw new SeiTSMApiException(1);
        } else if (mifareTag == null && i == 0) {
            LogUtils.w("failed to install door card, tag info is null");
            throw new SeiTSMApiException(1);
        } else {
            TsmRpcModels.MifareCardParam.Builder newBuilder = TsmRpcModels.MifareCardParam.newBuilder();
            if (mifareTag != null) {
                if (!TextUtils.isEmpty(mifareTag.getUid())) {
                    newBuilder.setUid(mifareTag.getUid());
                }
                if (!TextUtils.isEmpty(mifareTag.getAtqa())) {
                    newBuilder.setAtqa(mifareTag.getAtqa());
                }
                if (!TextUtils.isEmpty(mifareTag.getSak())) {
                    newBuilder.setSak(mifareTag.getSak());
                }
                if (!TextUtils.isEmpty(mifareTag.getBlockContent())) {
                    newBuilder.setContent(mifareTag.getBlockContent());
                }
                newBuilder.setSize(mifareTag.getSize());
            }
            TsmRpcModels.EnrollDoorCardRequest.Builder sessionId = TsmRpcModels.EnrollDoorCardRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId());
            if (str == null) {
                str = "";
            }
            TsmRpcModels.EnrollDoorCardRequest.Builder mifareCardParam = sessionId.setProductId(str).setCardType(valueOf).setMifareCardParam(newBuilder);
            if (str2 == null) {
                str2 = "";
            }
            TsmRpcModels.EnrollDoorCardRequest.Builder issuerToken = mifareCardParam.setIssuerToken(str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "tsmclient";
            }
            try {
                return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_DOOR_CARD_ENROLL, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(issuerToken.setApplyChannel(str3).build().toByteArray())).create())));
            } catch (AuthApiException e) {
                LogUtils.e("installDoorCard failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("installDoorCard failed", e2);
                throw new SeiTSMApiException(16);
            }
        }
    }

    public TsmRpcModels.QueryDoorCardInfoResponse queryMifareCardList(Context context) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
        TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel((CardInfo) null));
        try {
            return TsmRpcModels.QueryDoorCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray((String) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_MIFARE_LIST, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams(TSMAuthContants.PARAM_CPLC, cplc).addParams("req", Coder.encodeBase64Coder(deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType((CardInfo) null)).build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("queryMifareCardList failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("queryMifareCardList failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.DoorCardInfo queryDoorCardInfoByAid(Context context, String str, int i, String str2) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        TsmRpcModels.DoorCardType valueOf = TsmRpcModels.DoorCardType.valueOf(i);
        if (valueOf != null) {
            String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
            TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel((CardInfo) null));
            TsmRpcModels.DeviceInfo build = deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType((CardInfo) null)).build();
            TsmRpcModels.QueryDoorCardRequest.Builder cardType = TsmRpcModels.QueryDoorCardRequest.newBuilder().setCplc(cplc).setAid(str).setCardType(valueOf);
            if (str2 == null) {
                str2 = "";
            }
            try {
                TsmRpcModels.QueryDoorCardInfoResponse parseFrom = TsmRpcModels.QueryDoorCardInfoResponse.parseFrom(Coder.decodeBase64ToByteArray((String) sendGetRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_DOOR_CARD_QUERY_BY_AID, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(cardType.setProductId(str2).setDeviceInfo(build).build().toByteArray())).create())));
                if (parseFrom != null && parseFrom.getCardInfoListList() != null && parseFrom.getCardInfoListList().size() > 0) {
                    return parseFrom.getCardInfoListList().get(0);
                }
                throw new SeiTSMApiException(16);
            } catch (AuthApiException e) {
                LogUtils.e("queryMifareCardInfoByAid failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("queryMifareCardInfoByAid failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to query door card info by aid, card type is empty.");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse updateMifareCard(Context context, MifareCardInfo... mifareCardInfoArr) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
        if (mifareCardInfoArr == null || mifareCardInfoArr.length < 1) {
            LogUtils.w("failed to update mifare card, card info is null");
        }
        ArrayList arrayList = new ArrayList();
        for (MifareCardInfo mifareCardInfo : mifareCardInfoArr) {
            arrayList.add(TsmRpcModels.DoorCardInfo.newBuilder().setAid(mifareCardInfo.mAid == null ? "" : mifareCardInfo.mAid).setCardType(TsmRpcModels.DoorCardType.valueOf(mifareCardInfo.mMifareCardType > 0 ? 1 : mifareCardInfo.mMifareCardType)).setName(mifareCardInfo.mCardName).setFingerFlag(mifareCardInfo.mFingerAuthFlag).setProductId(mifareCardInfo.getProductId()).build());
        }
        try {
            return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPDATE_MIFARE_CARD, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.UpdateDoorCardRequest.newBuilder().setCplc(cplc).addAllCardInfo(arrayList).build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("updateMifareCard failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("updateMifareCard failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.InAppTransCommand requestInappTransCommand(Context context, InAppTransData inAppTransData) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (inAppTransData != null) {
            LogUtils.d("transdata " + inAppTransData.toString());
            try {
                String sendRequest = sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_INAPP_REQUEST_PAY, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.InAppTransData.newBuilder().setOrderNumber(inAppTransData.getOrderNumber()).setMerchantName(inAppTransData.getMerchantName()).setMerchantId(inAppTransData.getMerchantId()).setTotalAmount(inAppTransData.getOrderAmount()).setDiscountAmount(inAppTransData.getDiscountAmount()).setPayAmount(inAppTransData.getPayAmount()).setEncryptPayAmount(inAppTransData.getEncryptPayAmount()).setCurrencyCode(inAppTransData.getCurrencyCode()).setSign(inAppTransData.getSignedData()).setSignKeyIndex(inAppTransData.getSignKeyIndex()).setPan(inAppTransData.getPan()).setPbocAid(inAppTransData.getPayCardAppletAid()).setAcquirer(inAppTransData.getChannel()).setVerifyMethod(inAppTransData.getVerifyMethod()).build().toByteArray())).create());
                LogUtils.d("response " + sendRequest);
                return TsmRpcModels.InAppTransCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest));
            } catch (AuthApiException e) {
                LogUtils.e("requestInappTransCommand failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("requestInappTransCommand failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to request inapp transaction command, transaction data is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse notifyResult(Context context, int i, InAppTransData inAppTransData) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        if (inAppTransData != null) {
            try {
                String sendRequest = sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_INAPP_NOTIFY_PAY_RESULT, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.InAppPayResult.newBuilder().setOrderNumber(inAppTransData.getOrderNumber()).setMerchantId(inAppTransData.getMerchantId()).setPan(inAppTransData.getPan()).setAcquirer(inAppTransData.getChannel()).setResult(i).build().toByteArray())).create());
                LogUtils.i("response " + sendRequest);
                return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest));
            } catch (AuthApiException e) {
                LogUtils.e("notifyResult failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
                throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("notifyResult failed", e2);
                throw new SeiTSMApiException(16);
            }
        } else {
            LogUtils.w("failed to request inapp transaction command, transaction data is null");
            throw new SeiTSMApiException(1);
        }
    }

    public TsmRpcModels.CommonResponse uploadExceptionUserLog(Context context, UserExceptionLogInfo userExceptionLogInfo) throws SeiTSMApiException {
        if (userExceptionLogInfo == null) {
            return null;
        }
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
        AuthRequest.AuthRequestBuilder newBuilder = AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_UPLOAD_EXCEPTION_USER_LOG, AuthRequest.RespContentType.protobuf);
        newBuilder.addParams(TSMAuthContants.PARAM_CPLC, cplc);
        for (Map.Entry next : userExceptionLogInfo.getParams().entrySet()) {
            newBuilder.addParams((String) next.getKey(), (String) next.getValue());
        }
        try {
            return TsmRpcModels.CommonResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, newBuilder.create())));
        } catch (AuthApiException e) {
            LogUtils.e("uploadExceptionUserLog failed, code: " + e.mErrorCode + ", msg:" + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("uploadExceptionUserLog failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    public TsmRpcModels.TsmAPDUCommand pullBusCardTransferData(Context context, TsmRpcModels.TsmSessionInfo tsmSessionInfo, boolean z) {
        AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context);
        if (loadAccountInfo == null) {
            LogUtils.e("failed to pull bus card transfer data, account info is null");
            return null;
        } else if (tsmSessionInfo == null) {
            LogUtils.e("failed to pull bus card transfer  data, session info is null");
            return null;
        } else {
            try {
                String sendRequest = sendRequest(context, loadAccountInfo, AuthRequest.AuthRequestBuilder.newBuilder(loadAccountInfo, z ? TSMAuthContants.URL_PULL_BUS_CARD_TRANSFER_IN_DATA : TSMAuthContants.URL_PULL_BUS_CARD_TRANSFER_OUT_DATA, AuthRequest.RespContentType.protobuf).addParams("userId", loadAccountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.PullBusCardDataRequest.newBuilder().setSessionId(tsmSessionInfo.getSessionId()).build().toByteArray())).create());
                if (!TextUtils.isEmpty(sendRequest)) {
                    return TsmRpcModels.TsmAPDUCommand.parseFrom(Coder.decodeBase64ToByteArray(sendRequest));
                }
            } catch (AuthApiException e) {
                LogUtils.e("pullBusCardTransferData failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            } catch (InvalidProtocolBufferException e2) {
                LogUtils.e("pullBusCardTransferData failed", e2);
            }
            return null;
        }
    }

    public TsmRpcModels.CheckSeUpgradeResponse checkSeUpgrade(Context context, TsmRpcModels.ServiceType serviceType) throws SeiTSMApiException {
        AccountInfo accountInfo = getAccountInfo(context);
        String cplc = getCplc(new CardInfo(CardInfo.CARD_TYPE_DUMMY));
        TsmRpcModels.DeviceInfo.Builder deviceModel = TsmRpcModels.DeviceInfo.newBuilder().setDeviceModel(SysUtils.getDeviceModel((CardInfo) null));
        try {
            return TsmRpcModels.CheckSeUpgradeResponse.parseFrom(Coder.decodeBase64ToByteArray(sendRequest(context, accountInfo, AuthRequest.AuthRequestBuilder.newBuilder(accountInfo, TSMAuthContants.URL_CHECK_SE_UPGRADE, AuthRequest.RespContentType.protobuf).addParams("userId", accountInfo.getUserId()).addParams("req", Coder.encodeBase64Coder(TsmRpcModels.CheckServiceRequest.newBuilder().setDeviceInfo(deviceModel.setLang(Locale.getDefault().getLanguage() + JSMethod.NOT_SET + Locale.getDefault().getCountry()).setMiuiSystemVersion(SysUtils.getRomVersion()).setMiuiRomType(SysUtils.getMIUIRomType((CardInfo) null)).build()).setSeviceType(serviceType).setCplc(cplc).build().toByteArray())).create())));
        } catch (AuthApiException e) {
            LogUtils.e("checkSeUpgrade failed, code: " + e.mErrorCode + ", msg: " + e.mErrorMsg, e);
            throw new SeiTSMApiException(e.mErrorCode, e.mErrorMsg);
        } catch (InvalidProtocolBufferException e2) {
            LogUtils.e("checkSeUpgrade failed", e2);
            throw new SeiTSMApiException(16);
        }
    }

    /* access modifiers changed from: protected */
    public String sendRequest(Context context, AccountInfo accountInfo, AuthRequest authRequest) throws AuthApiException {
        return (String) super.sendRequest(context, accountInfo, authRequest);
    }

    private String getCplc(CardInfo cardInfo) throws SeiTSMApiException {
        try {
            return cardInfo.getTerminal().getCPLC();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LogUtils.e("failed to get cplc", e);
            throw new SeiTSMApiException(11);
        } catch (IOException e2) {
            LogUtils.e("failed to get cplc", e2);
            throw new SeiTSMApiException(13);
        }
    }

    private AccountInfo getAccountInfo(Context context) throws SeiTSMApiException {
        AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context);
        if (loadAccountInfo != null) {
            return loadAccountInfo;
        }
        throw new SeiTSMApiException(14);
    }
}
