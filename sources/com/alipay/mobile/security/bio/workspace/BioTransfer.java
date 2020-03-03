package com.alipay.mobile.security.bio.workspace;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.mobile.security.bio.api.BioCallback;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.alipay.mobile.security.bio.api.BioParameter;
import com.alipay.mobile.security.bio.api.BioProgressCallback;
import com.alipay.mobile.security.bio.api.BioResponse;
import com.alipay.mobile.security.bio.common.statistics.RecordExtAction;
import com.alipay.mobile.security.bio.common.statistics.RecordExtService;
import com.alipay.mobile.security.bio.config.Constant;
import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.module.MicroModule;
import com.alipay.mobile.security.bio.service.BioAppDescription;
import com.alipay.mobile.security.bio.service.BioAppManager;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioUploadService;
import com.alipay.mobile.security.bio.service.BioUploadServiceCore;
import com.alipay.mobile.security.bio.service.local.rpc.BioRPCService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.StringUtil;
import com.coloros.mcssdk.mode.CommandMessage;
import com.xiaomi.smarthome.scene.GoLeaveHomeSceneCreateEditActivity;
import com.xiaomi.stat.d;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BioTransfer implements BioDetector {

    /* renamed from: a  reason: collision with root package name */
    private BioServiceManager f1028a;
    private LocalBroadcastManager b;
    private AuthBroadCastReceiver c;
    private ArrayList<String> d = new ArrayList<>(Arrays.asList(new String[]{"userid", "apdid", GoLeaveHomeSceneCreateEditActivity.SCENE_ID, "appid", "token_id", "usernamehidden", "abtest", BioDetector.EXT_KEY_VERIFYID}));
    private MicroModule e;
    /* access modifiers changed from: private */
    public BioCallback f;
    int fcStep = 0;
    String fcToken = "";
    /* access modifiers changed from: private */
    public BioAppManager g;
    boolean isIDFaceFlag = false;
    Context mContext;
    JSONObject mFcSpecialData;
    BioParameter mIDFaceParam;
    RecordExtService mRecordExtService;

    public BioTransfer(Context context, MicroModule microModule) {
        if (context != null) {
            String zimId = microModule == null ? null : microModule.getZimId();
            if (TextUtils.isEmpty(zimId)) {
                BioServiceManager.createInstance(context);
            } else {
                BioServiceManager.createInstance(context, zimId);
            }
            this.f1028a = BioServiceManager.getCurrentInstance();
            this.mRecordExtService = (RecordExtService) this.f1028a.getBioService(RecordExtService.class);
            this.g = (BioAppManager) this.f1028a.getBioService(BioAppManager.class);
            this.b = LocalBroadcastManager.getInstance(context.getApplicationContext());
            this.c = new AuthBroadCastReceiver();
            this.b.registerReceiver(this.c, new IntentFilter(Constant.BIOLOGY_CALLBACK_ACTION));
            this.b.registerReceiver(this.c, new IntentFilter(Constant.BIOLOGY_CALLBACK_PROGRESS_ACTION));
            BioLog.i("LocalBroadcastManager.registerReceiver(mAuthBroadCastReceiver)");
            this.mContext = context.getApplicationContext();
            this.e = microModule;
            return;
        }
        throw new BioIllegalArgumentException();
    }

    public void auth(BioParameter bioParameter, BioCallback bioCallback) {
        if (bioParameter == null || bioCallback == null) {
            throw new BioIllegalArgumentException();
        }
        preLoad();
        this.f = bioCallback;
        a(bioParameter.getBundle(), bioParameter);
        BioAppDescription a2 = a(bioParameter);
        BioLog.d("BioTransfer.auth(): app: " + a2);
        String put = this.g.put(a2, bioCallback);
        String remoteURL = bioParameter.getRemoteURL();
        BioLog.d("BioTransfer.auth(): remoteUrl:" + remoteURL);
        if (!TextUtils.isEmpty(remoteURL)) {
            ((BioRPCService) this.f1028a.getBioService(BioRPCService.class)).setRemoteUrl(remoteURL);
        }
        ((BioUploadService) this.f1028a.getBioService(BioUploadService.class)).clearUp();
        if (StringUtil.isNullorEmpty(this.f1028a.startBioActivity(a2, this.e))) {
            sendResponse(put, 201, "app is not installed");
        }
    }

    private BioAppDescription a(BioParameter bioParameter) {
        String str;
        Class<?> cls;
        int protocolFormat = Env.getProtocolFormat(this.mContext);
        bioParameter.addExtProperty("meta_serializer", String.valueOf(protocolFormat));
        if (bioParameter.isValidate) {
            str = protocolFormat != 2 ? "com.alipay.mobile.security.bio.workspace.JsonToBioApp" : "com.alipay.mobile.security.bio.workspace.PbToBioApp";
        } else {
            str = protocolFormat != 2 ? "com.alipay.mobile.security.bio.workspace.JsonBioParameterToBioApp" : "com.alipay.mobile.security.bio.workspace.PbBioParameterToBioApp";
            try {
                if (2 == Env.getProtocolFormat(this.mContext)) {
                    cls = Class.forName("com.alipay.mobile.security.bio.service.impl.BioUploadServiceCoreBisPb");
                } else {
                    cls = Class.forName("com.alipay.mobile.security.bio.service.impl.BioUploadServiceCoreBisJson");
                }
                BioServiceManager.getCurrentInstance().putBioService(BioUploadServiceCore.class.getName(), cls);
            } catch (Throwable th) {
                BioLog.e(th);
                return null;
            }
        }
        try {
            Constructor<?> constructor = Class.forName(str).getConstructor(new Class[]{Context.class, BioTransfer.class});
            constructor.setAccessible(true);
            return ((a) constructor.newInstance(new Object[]{this.mContext, this})).toBioApp(bioParameter);
        } catch (Throwable th2) {
            BioLog.e(th2);
            return null;
        }
    }

    private BioParameter a(Bundle bundle, BioParameter bioParameter) {
        if (bundle == null && bioParameter == null) {
            throw new BioIllegalArgumentException("paramter is null");
        } else if (bundle == null) {
            return bioParameter;
        } else {
            String string = bundle.getString(BioDetector.EXT_KEY_MODULE_DATA_BUNDLE);
            BioLog.i("moduleData:" + string);
            if (TextUtils.isEmpty(string)) {
                return bioParameter;
            }
            JSONObject parseObject = JSONObject.parseObject(string);
            String string2 = parseObject.getString(BioDetector.EXT_KEY_SCENE_ID_BUNDLE);
            String string3 = parseObject.getString(BioDetector.EXT_KEY_USER_ID_BUNDLE);
            String string4 = parseObject.getString(BioDetector.EXT_KEY_BISCFG_BUNDLE);
            String string5 = bundle.getString("TOKEN_ID");
            String string6 = bundle.getString("APDID");
            String string7 = bundle.getString(BioDetector.EXT_KEY_VERIFYID);
            String string8 = parseObject.getString("appid");
            bioParameter.setHeadImageUrl(bundle != null ? bundle.getString("headurl") : null);
            BioLog.i("biscfg:" + string4);
            if (!StringUtil.isNullorEmpty(string4)) {
                bioParameter.setProtocol(string4);
            }
            if (TextUtils.isEmpty(string7)) {
                string7 = "";
            }
            bioParameter.addExtProperty(BioDetector.EXT_KEY_VERIFYID, string7);
            if (TextUtils.isEmpty(string6)) {
                string6 = "";
            }
            bioParameter.addExtProperty("APDID", string6);
            if (TextUtils.isEmpty(string2)) {
                string2 = "";
            }
            bioParameter.addExtProperty("SCENE_ID", string2);
            if (TextUtils.isEmpty(string5)) {
                string5 = "";
            }
            bioParameter.addExtProperty("TOKEN_ID", string5);
            if (TextUtils.isEmpty(string3)) {
                string3 = "";
            }
            bioParameter.addExtProperty("userid", string3);
            if (TextUtils.isEmpty(string8)) {
                string8 = "";
            }
            bioParameter.addExtProperty("appid", string8);
            if (bundle.containsKey("RequestPage")) {
                String valueOf = String.valueOf(bundle.getInt("RequestPage"));
                if (TextUtils.isEmpty(valueOf)) {
                    valueOf = "";
                }
                bioParameter.addExtProperty(BioDetector.EXT_KEY_PAGENUM, valueOf);
            }
            if (bundle.containsKey("RequestCardType")) {
                String string9 = bundle.getString("RequestCardType");
                if (TextUtils.isEmpty(string9)) {
                    string9 = "";
                }
                bioParameter.addExtProperty("card_type", string9);
            }
            if (bundle.containsKey("RequestTotalPagesNum")) {
                String valueOf2 = String.valueOf(bundle.getInt("RequestTotalPagesNum"));
                if (TextUtils.isEmpty(valueOf2)) {
                    valueOf2 = "";
                }
                bioParameter.addExtProperty(BioDetector.EXT_KEY_TOTAL_PAGE_NUM, valueOf2);
            }
            return bioParameter;
        }
    }

    public void sendResponse(String str, int i, String str2) {
        Intent intent = new Intent(Constant.BIOLOGY_CALLBACK_ACTION);
        BioResponse bioResponse = new BioResponse();
        bioResponse.setSuccess(false);
        bioResponse.setResult(i);
        bioResponse.setResultMessage(str2);
        bioResponse.setTag(str);
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BIOLOGY_INTENT_ACTION_REV, JSON.toJSONString(bioResponse));
        intent.putExtras(bundle);
        BioLog.w((Throwable) new RuntimeException("" + bioResponse));
        this.b.sendBroadcast(intent);
    }

    public void destroy() {
        this.g = null;
        this.f = null;
        BioServiceManager.destroyInstance();
        if (this.b != null) {
            this.b.unregisterReceiver(this.c);
        }
    }

    public class AuthBroadCastReceiver extends BroadcastReceiver {
        public AuthBroadCastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(Constant.BIOLOGY_INTENT_ACTION_REV);
            BioLog.i("rev action='" + action);
            if (TextUtils.isEmpty(stringExtra)) {
                BioLog.w((Throwable) new RuntimeException("why the AuthBroadCastReceiver.rev is empty?"));
                return;
            }
            BioResponse bioResponse = null;
            try {
                bioResponse = (BioResponse) JSON.parseObject(stringExtra, BioResponse.class);
            } catch (Throwable th) {
                BioLog.w(th);
            }
            if (bioResponse == null) {
                BioLog.w((Throwable) new RuntimeException("Failed to parse {" + stringExtra + "} to com.alipay.mobile.security.bio.api.BioResponse"));
                return;
            }
            String tag = bioResponse.getTag();
            if (BioTransfer.this.g == null) {
                BioLog.w((Throwable) new RuntimeException("BioAppManager in receiver is null"));
                return;
            }
            BioCallback bioCallback = BioTransfer.this.g.getBioCallback(tag);
            if (TextUtils.equals(Constant.BIOLOGY_CALLBACK_PROGRESS_ACTION, action)) {
                BioLog.i("zolozTime", "rev progress action!");
                if (bioCallback == null) {
                    BioLog.w((Throwable) new RuntimeException("Failed to getBioCallback() by " + tag));
                } else if (bioCallback instanceof BioProgressCallback) {
                    ((BioProgressCallback) bioCallback).onFaceDetected(bioResponse.getExt());
                } else {
                    BioLog.i("biologyCallback=" + bioCallback + ", has no method: onFaceDetected(Map<String, String>)");
                }
            } else {
                if (bioCallback == null) {
                    try {
                        if (!BioTransfer.this.isIDFaceFlag) {
                            return;
                        }
                    } catch (Exception e) {
                        BioLog.e((Throwable) e);
                        return;
                    }
                }
                Map ext = bioResponse.getExt();
                if (ext == null || !"face".equals(ext.get(d.ak))) {
                    if (BioTransfer.this.isIDFaceFlag) {
                        bioResponse.setToken(BioTransfer.this.fcToken);
                        HashMap hashMap = new HashMap();
                        hashMap.put("fcToken", BioTransfer.this.fcToken);
                        if (BioTransfer.this.fcStep == 1) {
                            BioTransfer.this.mRecordExtService.write(RecordExtAction.RECORD_FC_FACE_CALL_BACK_BIS_SYSTEM, hashMap);
                        }
                        hashMap.put("param", bioResponse.toString());
                        BioTransfer.this.mRecordExtService.write(RecordExtAction.RECORD_FC_CALL_BACK_VERITY_SYSTEM, hashMap);
                    }
                    if (BioTransfer.this.isIDFaceFlag) {
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put("fcToken", BioTransfer.this.fcToken);
                        BioTransfer.this.mRecordExtService.write(RecordExtAction.RECORD_FC_EXIT_SDK, hashMap2);
                    }
                    int bioAction = BioTransfer.this.g.getBioAppDescription(tag).getBioAction() - 1000;
                    if (992 == bioAction || 993 == bioAction) {
                        if (ext == null) {
                            ext = new HashMap();
                            bioResponse.setExt(ext);
                        }
                        ext.put(BioDetector.EXT_KEY_UI, bioAction + "");
                    }
                    bioCallback.onResult(bioResponse);
                    return;
                }
                BioTransfer.this.fcStep = 1;
                BioTransfer.this.auth(BioTransfer.this.mIDFaceParam, BioTransfer.this.f);
            }
        }
    }

    public int preLoad() {
        return this.f1028a.preLoad();
    }

    public void command(int i) {
        BioLog.i(CommandMessage.COMMAND + i);
        String str = "";
        switch (i) {
            case 4097:
                str = Constant.BIOLOGY_FLAG_SERVER_SUCCESS;
                break;
            case 4098:
                str = Constant.BIOLOGY_FLAG_SERVER_CONTINUE;
                break;
            case 4099:
                str = Constant.BIOLOGY_FLAG_AUTOCLOSE;
                break;
            default:
                switch (i) {
                    case 8193:
                        str = Constant.BIOLOGY_FLAG_SERVER_FAIL;
                        break;
                    case 8194:
                        str = Constant.BIOLOGY_FLAG_SERVER_RETRY;
                        break;
                }
        }
        BioLog.w("BioTransfer.command() action=" + str);
        if (!StringUtil.isNullorEmpty(str)) {
            this.b.sendBroadcast(new Intent(str));
        }
    }
}
