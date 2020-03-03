package com.alipay.zoloz.toyger.upload;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisBehavCommon;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisBehavLog;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisBehavToken;
import com.alipay.bis.common.service.facade.gw.model.common.BisJson.BisClientInfo;
import com.alipay.mobile.security.bio.runtime.Runtime;
import com.alipay.mobile.security.bio.service.BioAppDescription;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioTaskService;
import com.alipay.mobile.security.bio.service.local.apsecurity.ApSecurityService;
import com.alipay.mobile.security.bio.task.SubTask;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.FileUtil;
import com.alipay.mobile.security.faceauth.InvokeType;
import com.alipay.mobile.security.faceauth.UserVerifyInfo;
import com.alipay.zoloz.toyger.ToygerService;
import com.alipay.zoloz.toyger.bean.ToygerFrame;
import com.alipay.zoloz.toyger.extservice.record.TimeRecord;
import com.alipay.zoloz.toyger.extservice.record.ToygerRecordService;
import com.alipay.zoloz.toyger.interfaces.ToygerCallback;
import com.alipay.zoloz.toyger.workspace.FaceRemoteConfig;
import com.alipay.zoloz.toyger.workspace.ToygerWorkspace;
import com.alipay.zoloz.toyger.workspace.task.ToygerBaseTask;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UploadManager {
    public static final String TAG = "UploadManager";
    private BioAppDescription mBioAppDescription;
    private BioServiceManager mBioServiceManager;
    private BioTaskService mBioTaskService = ((BioTaskService) this.mBioServiceManager.getBioService(BioTaskService.class));
    private Context mContext;
    private NineShootManager mNineShootManager;
    private ToygerCallback mToygerCallback;
    ToygerWorkspace mToygerWorkspace;
    private UploadChannel mUploadChannel;
    private String publicKey;

    public UploadManager(ToygerWorkspace toygerWorkspace, BioServiceManager bioServiceManager, ToygerCallback toygerCallback) {
        this.mToygerWorkspace = toygerWorkspace;
        this.mBioServiceManager = bioServiceManager;
        this.mContext = bioServiceManager.getBioApplicationContext();
        this.mBioAppDescription = toygerCallback.getAppDescription();
        this.mToygerCallback = toygerCallback;
        this.publicKey = getPublicKey(this.mContext, this.mToygerCallback.getRemoteConfig());
        Map<String, String> extProperty = this.mToygerCallback.getAppDescription().getExtProperty();
        try {
            Constructor<?> constructor = Class.forName(((extProperty == null || !extProperty.containsKey("meta_serializer")) ? 2 : Integer.parseInt(extProperty.get("meta_serializer"))) != 2 ? "com.alipay.zoloz.toyger.upload.UploadChannelByJson" : "com.alipay.zoloz.toyger.upload.UploadChannelByPb").getConstructor(new Class[]{BioServiceManager.class});
            constructor.setAccessible(true);
            this.mUploadChannel = (UploadChannel) constructor.newInstance(new Object[]{bioServiceManager});
        } catch (Throwable th) {
            BioLog.e(th);
        }
        this.mNineShootManager = new NineShootManager(toygerWorkspace.getToygerFaceService(), toygerCallback.getRemoteConfig());
    }

    public void uploadFaceInfo(ToygerFrame toygerFrame) {
        if (this.mUploadChannel != null) {
            UploadContent uploadContent = toygerFrame.uploadContent;
            BisBehavLog bisBehavLogData = getBisBehavLogData(this.mToygerCallback.getUserVerifyInfo(), InvokeType.NORMAL);
            String bistoken = this.mToygerCallback.getAppDescription().getBistoken();
            TimeRecord.getInstance().setUploadStartTime(System.currentTimeMillis());
            HashMap hashMap = new HashMap();
            hashMap.put("faceQuality", toygerFrame.tgFaceAttr.quality + "");
            ((ToygerRecordService) this.mBioServiceManager.getBioService(ToygerRecordService.class)).write(ToygerRecordService.UPLOAD_START, hashMap);
            Log.i("zolozTime", "upload face begin");
            this.mUploadChannel.uploadFaceInfo(uploadContent, bisBehavLogData, bistoken, this.publicKey);
        }
        uploadNineShoot();
    }

    public void uploadNineShoot() {
        if (this.mUploadChannel != null && this.mNineShootManager != null && this.mNineShootManager.isNeedUpload()) {
            this.mUploadChannel.uploadNineShoot(getNineShoot(), getBisBehavLogData(this.mToygerCallback.getUserVerifyInfo(), InvokeType.MONITOR), this.mBioAppDescription.getBistoken(), this.publicKey);
        }
    }

    public void uploadBehaviourLog(InvokeType invokeType) {
        if (this.mUploadChannel != null) {
            this.mUploadChannel.uploadBehaviourLog(getBisBehavLogData(this.mToygerCallback.getUserVerifyInfo(), invokeType), this.mBioAppDescription.getBistoken(), this.publicKey);
        }
    }

    public static String getPublicKey(Context context, FaceRemoteConfig faceRemoteConfig) {
        byte[] bArr;
        try {
            BioLog.i("PublicKey:" + faceRemoteConfig.getEnv());
            if (faceRemoteConfig.getEnv() == 0) {
                bArr = FileUtil.getAssetsData(context, "bid-log-key-public.key");
            } else {
                bArr = FileUtil.getAssetsData(context, "bid-log-key-public_t.key");
            }
            return new String(bArr);
        } catch (IllegalStateException e) {
            BioLog.e(e.toString());
            return "";
        } catch (IllegalArgumentException e2) {
            BioLog.e(e2.toString());
            return "";
        }
    }

    private BisBehavLog getBisBehavLogData(UserVerifyInfo userVerifyInfo, InvokeType invokeType) {
        BisBehavLog bisBehavLog = new BisBehavLog();
        BisClientInfo bisClientInfo = new BisClientInfo();
        bisClientInfo.setClientVer(Runtime.getFrameworkVersion(this.mContext));
        bisClientInfo.setModel(Build.MODEL);
        bisClientInfo.setOs("android");
        bisClientInfo.setOsVer(Build.VERSION.RELEASE);
        BisBehavToken bisBehavToken = new BisBehavToken();
        bisBehavToken.setApdid(userVerifyInfo.apdid);
        bisBehavToken.setAppid(userVerifyInfo.appid);
        ApSecurityService apSecurityService = (ApSecurityService) this.mBioServiceManager.getBioService(ApSecurityService.class);
        if (apSecurityService != null) {
            bisBehavToken.setApdidToken(apSecurityService.getApDidToken());
        }
        bisBehavToken.setBehid(userVerifyInfo.behid);
        if (this.mBioAppDescription != null) {
            bisBehavToken.setToken(this.mBioAppDescription.getBistoken());
            if (this.mBioAppDescription.getBioAction() == 991 || this.mBioAppDescription.getBioAction() == 992) {
                bisBehavToken.setSampleMode(302);
            } else if (this.mBioAppDescription.getBioAction() == 992) {
                bisBehavToken.setSampleMode(303);
            } else {
                bisBehavToken.setSampleMode(this.mBioAppDescription.getBioAction());
            }
            bisBehavToken.setType(this.mBioAppDescription.getBioType());
            bisBehavToken.setBizid(this.mBioAppDescription.getBistoken());
        }
        bisBehavToken.setUid(userVerifyInfo.userid);
        bisBehavToken.setVtoken(userVerifyInfo.vtoken);
        bisBehavToken.setVerifyid(userVerifyInfo.verifyid);
        BisBehavCommon bisBehavCommon = new BisBehavCommon();
        bisBehavCommon.setInvtp(invokeType.toString());
        bisBehavCommon.setTm("");
        bisBehavCommon.setRetry("" + this.mToygerCallback.getRetryTime());
        ArrayList arrayList = new ArrayList();
        Iterator<SubTask> it = this.mBioTaskService.getTasks().iterator();
        while (it.hasNext()) {
            ToygerBaseTask toygerBaseTask = (ToygerBaseTask) it.next();
            if (toygerBaseTask != null && toygerBaseTask.isHasBeHaviorInfo()) {
                arrayList.add(toygerBaseTask.getBisBehavTask());
            }
        }
        bisBehavLog.setBehavCommon(bisBehavCommon);
        bisBehavLog.setBehavTask(arrayList);
        bisBehavLog.setBehavToken(bisBehavToken);
        bisBehavLog.setClientInfo(bisClientInfo);
        BioLog.i("bisBehavLog:" + JSON.toJSONString(bisBehavLog));
        return bisBehavLog;
    }

    public UploadContent getNineShoot() {
        if (this.mNineShootManager == null) {
            return null;
        }
        Map<String, Object> generateMonitorBlob = this.mToygerWorkspace.getToygerFaceService().generateMonitorBlob();
        UploadContent uploadContent = new UploadContent((byte[]) generateMonitorBlob.get("content"), (byte[]) generateMonitorBlob.get("key"), ((Boolean) generateMonitorBlob.get(ToygerService.KEY_RES_9_IS_UTF8)).booleanValue());
        BioLog.i("getNineShoot DONE");
        return uploadContent;
    }

    public NineShootManager getNineShootManager() {
        return this.mNineShootManager;
    }

    public void destroy() {
        if (this.mNineShootManager != null) {
            this.mNineShootManager.destroy();
        }
    }
}
