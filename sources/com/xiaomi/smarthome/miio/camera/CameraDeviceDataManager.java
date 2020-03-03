package com.xiaomi.smarthome.miio.camera;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import com.xiaomi.smarthome.miio.camera.alarm.CameraAlarmNetUtils;
import com.xiaomi.smarthome.miio.camera.alarm.ICameraAlarmCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.FileDownloadAndDecryptTask;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraDeviceDataManager {
    private static final String TAG = "CameraDeviceDataManager";
    private static CameraDeviceDataManager instance;

    public interface ICameraDeviceDataCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t, Object obj);
    }

    private CameraDeviceDataManager() {
    }

    public static CameraDeviceDataManager getInstance() {
        if (instance == null) {
            synchronized (CameraDeviceDataManager.class) {
                if (instance == null) {
                    instance = new CameraDeviceDataManager();
                }
            }
        }
        return instance;
    }

    public void getAlarmStatus(String str, final ICameraAlarmCallback<CameraAlarm> iCameraAlarmCallback) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("did", str);
                jSONObject.put("region", Locale.getDefault().getCountry());
                CameraAlarmNetUtils.getInstance().getAlarmStatus(jSONObject.toString(), new ICameraAlarmCallback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject, Object obj) {
                        if (iCameraAlarmCallback == null) {
                            return;
                        }
                        if (jSONObject != null) {
                            int optInt = jSONObject.optInt("code", -1);
                            if (optInt == 0) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("motionDetectionSwitch");
                                    if (optJSONObject2 != null) {
                                        boolean optBoolean = optJSONObject2.optBoolean("detectionSwitch", false);
                                        CameraAlarm cameraAlarm = new CameraAlarm();
                                        cameraAlarm.isAlarmEnabled = optBoolean;
                                        iCameraAlarmCallback.onSuccess(cameraAlarm, (Object) null);
                                        return;
                                    }
                                    iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "motionDetectionSwitch is null");
                                    return;
                                }
                                iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "data is null");
                                return;
                            }
                            ICameraAlarmCallback iCameraAlarmCallback = iCameraAlarmCallback;
                            iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_CODE, "code is not 0:" + optInt);
                            return;
                        }
                        iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "result is null");
                    }

                    public void onFailure(int i, String str) {
                        LogUtil.b(CameraDeviceDataManager.TAG, "errorCode:" + i + " errorInfo:" + str);
                        if (iCameraAlarmCallback != null) {
                            iCameraAlarmCallback.onFailure(i, str);
                        }
                    }
                });
            } catch (Exception unused) {
                if (iCameraAlarmCallback != null) {
                    iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, LogCategory.CATEGORY_EXCEPTION);
                }
            }
        } else if (iCameraAlarmCallback != null) {
            iCameraAlarmCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "did is null");
        }
    }

    public void getCloudStorageStatus(String str, final ICameraDeviceDataCallback<CameraCloudStorage> iCameraDeviceDataCallback) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", str);
                jSONObject.put("region", Locale.getDefault().getCountry());
                CloudVideoNetUtils.getInstance().checkCloudStorageStatus(jSONObject.toString(), new ICloudVideoCallback<JSONObject>() {
                    public void onCloudVideoSuccess(JSONObject jSONObject, Object obj) {
                        if (iCameraDeviceDataCallback == null) {
                            return;
                        }
                        if (jSONObject != null) {
                            int optInt = jSONObject.optInt("code", -1);
                            if (optInt == 0) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    boolean optBoolean = optJSONObject.optBoolean("vip", false);
                                    CameraCloudStorage cameraCloudStorage = new CameraCloudStorage();
                                    cameraCloudStorage.isCloudInUse = optBoolean;
                                    iCameraDeviceDataCallback.onSuccess(cameraCloudStorage, (Object) null);
                                    return;
                                }
                                iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "data is null");
                                return;
                            }
                            ICameraDeviceDataCallback iCameraDeviceDataCallback = iCameraDeviceDataCallback;
                            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_CODE, "code is not 0:" + optInt);
                            return;
                        }
                        iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "result is null");
                    }

                    public void onCloudVideoFailed(int i, String str) {
                        LogUtil.b(CameraDeviceDataManager.TAG, "errorCode:" + i + " errorInfo:" + str);
                        if (iCameraDeviceDataCallback != null) {
                            iCameraDeviceDataCallback.onFailure(i, str);
                        }
                    }
                });
            } catch (Exception unused) {
                if (iCameraDeviceDataCallback != null) {
                    iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, LogCategory.CATEGORY_EXCEPTION);
                }
            }
        } else if (iCameraDeviceDataCallback != null) {
            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "did is null");
        }
    }

    public void loadAllCameraCloudStorageStatus(String str, final ICameraDeviceDataCallback<JSONObject> iCameraDeviceDataCallback) {
        CloudVideoNetUtils.getInstance().loadAllCameraCloudStorageInfo(str, new ICloudVideoCallback<JSONObject>() {
            public void onCloudVideoSuccess(JSONObject jSONObject, Object obj) {
                if (iCameraDeviceDataCallback == null) {
                    return;
                }
                if (jSONObject != null) {
                    iCameraDeviceDataCallback.onSuccess(jSONObject, obj);
                } else {
                    iCameraDeviceDataCallback.onFailure(LoginErrorCode.aI, "result is null");
                }
            }

            public void onCloudVideoFailed(int i, String str) {
                if (iCameraDeviceDataCallback != null) {
                    iCameraDeviceDataCallback.onFailure(i, str);
                }
            }
        });
    }

    public void getLatestImageUri(String str, ICameraDeviceDataCallback<String> iCameraDeviceDataCallback) {
        final ImageInfo imageInfo = new ImageInfo();
        final ImageInfo imageInfo2 = new ImageInfo();
        Device b = SmartHomeDeviceManager.a().b(str);
        if (b != null) {
            if (CameraFrameManager.a().a(b.did) == null) {
                CameraFrameManager.a().a(b, false);
            }
            final String str2 = SHApplication.getAppContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/smarthome_snapshot_" + System.currentTimeMillis() + ".jpeg";
            if (!TextUtils.isEmpty(str2)) {
                final String str3 = str;
                final ICameraDeviceDataCallback<String> iCameraDeviceDataCallback2 = iCameraDeviceDataCallback;
                CameraFrameManager.a().a(b, (CameraFrameManager.CameraBitmapCallback) new CameraFrameManager.CameraBitmapCallback() {
                    public void onBitmapLoaded(final Bitmap bitmap, final long j) {
                        CameraDeviceDataManager.this.getLatestAlarmImageUri(str3, new ICameraDeviceDataCallback<ImageInfo>() {
                            public void onSuccess(ImageInfo imageInfo, Object obj) {
                                imageInfo.createTime = imageInfo.createTime;
                                imageInfo.url = imageInfo.url;
                                CameraDeviceDataManager.this.getLatestCloudVideoImageUri(str3, new ICameraDeviceDataCallback<ImageInfo>() {
                                    public void onSuccess(ImageInfo imageInfo, Object obj) {
                                        imageInfo2.createTime = imageInfo.createTime;
                                        imageInfo2.url = imageInfo.url;
                                        if (bitmap == null || j <= imageInfo.createTime || j <= imageInfo2.createTime) {
                                            CameraDeviceDataManager.this.downloadImage(imageInfo, imageInfo2, str2, iCameraDeviceDataCallback2);
                                        } else {
                                            CameraDeviceDataManager.this.saveBitmapToFile(bitmap, str2, iCameraDeviceDataCallback2);
                                        }
                                    }

                                    public void onFailure(int i, String str) {
                                        LogUtil.a(CameraDeviceDataManager.TAG, "getLatestAlarmImageUri getLatestCloudVideoImageUri1:" + i + "" + str);
                                        if (bitmap == null || j <= imageInfo.createTime || j <= imageInfo2.createTime) {
                                            CameraDeviceDataManager.this.downloadImage(imageInfo, imageInfo2, str2, iCameraDeviceDataCallback2);
                                        } else {
                                            CameraDeviceDataManager.this.saveBitmapToFile(bitmap, str2, iCameraDeviceDataCallback2);
                                        }
                                    }
                                });
                            }

                            public void onFailure(int i, String str) {
                                CameraDeviceDataManager.this.getLatestCloudVideoImageUri(str3, new ICameraDeviceDataCallback<ImageInfo>() {
                                    public void onSuccess(ImageInfo imageInfo, Object obj) {
                                        if (bitmap == null || j <= imageInfo.createTime || j <= imageInfo2.createTime) {
                                            CameraDeviceDataManager.this.downloadImage(imageInfo, imageInfo2, str2, iCameraDeviceDataCallback2);
                                        } else {
                                            CameraDeviceDataManager.this.saveBitmapToFile(bitmap, str2, iCameraDeviceDataCallback2);
                                        }
                                    }

                                    public void onFailure(int i, String str) {
                                        LogUtil.a(CameraDeviceDataManager.TAG, "getLatestAlarmImageUri getLatestCloudVideoImageUri2:" + i + "" + str);
                                        if (iCameraDeviceDataCallback2 != null) {
                                            iCameraDeviceDataCallback2.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "no alarm or cloud video");
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            } else if (iCameraDeviceDataCallback != null) {
                iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "imageFilePath is null");
            }
        } else if (iCameraDeviceDataCallback != null) {
            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "device is null");
        }
    }

    /* access modifiers changed from: private */
    public void getLatestAlarmImageUri(final String str, final ICameraDeviceDataCallback<ImageInfo> iCameraDeviceDataCallback) {
        try {
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(new Date());
            instance2.add(5, -7);
            instance2.set(11, 0);
            instance2.set(12, 0);
            instance2.set(13, 0);
            long timeInMillis = instance2.getTimeInMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str);
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put(Tags.Coupon.BEGIN_TIME, timeInMillis);
            jSONObject.put("endTime", System.currentTimeMillis());
            jSONObject.put("limit", 1);
            CameraAlarmNetUtils.getInstance().getAlarmList(jSONObject.toString(), new ICameraAlarmCallback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject, Object obj) {
                    if (iCameraDeviceDataCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "result is null");
                    } else if (jSONObject.optInt("code", -1) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("playUnits");
                            if (optJSONArray == null || optJSONArray.length() <= 0) {
                                iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "playUnits 0");
                                return;
                            }
                            try {
                                JSONObject jSONObject2 = optJSONArray.getJSONObject(0);
                                long optLong = jSONObject2.optLong("createTime");
                                String optString = jSONObject2.optString("imgStoreId");
                                String optString2 = jSONObject2.optString("fileId");
                                ImageInfo imageInfo = new ImageInfo();
                                imageInfo.createTime = optLong;
                                imageInfo.url = CloudVideoNetUtils.getInstance().getSnapshotUrl(str, optString2, optString);
                                iCameraDeviceDataCallback.onSuccess(imageInfo, (Object) null);
                            } catch (JSONException unused) {
                                iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "JSONException");
                            }
                        } else {
                            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "data null");
                        }
                    } else {
                        iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_CODE, "code not 0");
                    }
                }

                public void onFailure(int i, String str) {
                    LogUtil.b(CameraDeviceDataManager.TAG, "getLatestAlarmImageUri:" + i + "" + str);
                    iCameraDeviceDataCallback.onFailure(i, str);
                }
            });
        } catch (Exception unused) {
            if (iCameraDeviceDataCallback != null) {
                iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "Exception");
            }
        }
    }

    /* access modifiers changed from: private */
    public void getLatestCloudVideoImageUri(final String str, final ICameraDeviceDataCallback<ImageInfo> iCameraDeviceDataCallback) {
        try {
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(new Date());
            instance2.add(5, -7);
            instance2.set(11, 0);
            instance2.set(12, 0);
            instance2.set(13, 0);
            long timeInMillis = instance2.getTimeInMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("did", str);
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("language", Locale.getDefault().getLanguage());
            jSONObject.put(Tags.Coupon.BEGIN_TIME, timeInMillis);
            jSONObject.put("endTime", System.currentTimeMillis());
            jSONObject.put("limit", 1);
            CloudVideoNetUtils.getInstance().getVideoDailyListLimit(SHApplication.getAppContext(), jSONObject.toString(), new ICloudVideoCallback<List<DailyList>>() {
                public void onCloudVideoSuccess(List<DailyList> list, Object obj) {
                    if (iCameraDeviceDataCallback == null) {
                        return;
                    }
                    if (list == null || list.size() <= 0) {
                        iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "result is null");
                        return;
                    }
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.createTime = list.get(0).createTime;
                    imageInfo.url = CloudVideoNetUtils.getInstance().getSnapshotUrl(str, list.get(0).fileId, list.get(0).imgStoreId);
                    iCameraDeviceDataCallback.onSuccess(imageInfo, (Object) null);
                }

                public void onCloudVideoFailed(int i, String str) {
                    LogUtil.b(CameraDeviceDataManager.TAG, "getLatestCloudVideoImageUri:" + i + "" + str);
                    if (iCameraDeviceDataCallback != null) {
                        iCameraDeviceDataCallback.onFailure(i, str);
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void saveBitmapToFile(Bitmap bitmap, String str, ICameraDeviceDataCallback<String> iCameraDeviceDataCallback) {
        if (bitmap != null) {
            File file = new File(str);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                if (iCameraDeviceDataCallback != null && file.exists()) {
                    iCameraDeviceDataCallback.onSuccess(Uri.fromFile(file).toString(), (Object) null);
                }
            } catch (IOException e) {
                if (iCameraDeviceDataCallback != null) {
                    iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "IOException:" + e.getLocalizedMessage());
                }
            }
        } else if (iCameraDeviceDataCallback != null) {
            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "bitmap null");
        }
    }

    /* access modifiers changed from: private */
    public void downloadImage(ImageInfo imageInfo, ImageInfo imageInfo2, final String str, final ICameraDeviceDataCallback<String> iCameraDeviceDataCallback) {
        String str2;
        if (imageInfo != null && imageInfo2 != null && imageInfo.createTime > 0 && imageInfo2.createTime > 0) {
            if (imageInfo.createTime > imageInfo2.createTime) {
                str2 = imageInfo.url;
            } else {
                str2 = imageInfo2.url;
            }
            FileDownloadAndDecryptTask fileDownloadAndDecryptTask = new FileDownloadAndDecryptTask(new FileDownloadAndDecryptTask.IFileDownloadCallback() {
                public void onProgress(int i) {
                }

                public void onSuccess() {
                    LogUtil.a(CameraDeviceDataManager.TAG, "imageFilePath:" + str);
                    if (iCameraDeviceDataCallback != null) {
                        File file = new File(str);
                        if (file.exists()) {
                            iCameraDeviceDataCallback.onSuccess(Uri.fromFile(file).toString(), (Object) null);
                            return;
                        }
                        iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_IN_SUCCESS, "no file");
                    }
                }

                public void onFailure(int i) {
                    LogUtil.a(CameraDeviceDataManager.TAG, "onFailure:" + i);
                    if (iCameraDeviceDataCallback != null) {
                        iCameraDeviceDataCallback.onFailure(i, "no file");
                    }
                }
            });
            LogUtil.a(TAG, "downloadUrl:" + str2);
            fileDownloadAndDecryptTask.execute(new String[]{imageInfo.url, str});
        } else if (iCameraDeviceDataCallback != null) {
            iCameraDeviceDataCallback.onFailure(CameraAlarmNetUtils.FAIL_GENERAL, "param(s) invalid");
        }
    }

    private class ImageInfo {
        public long createTime;
        public String url;

        public ImageInfo() {
        }

        public ImageInfo(ImageInfo imageInfo) {
            this.createTime = imageInfo.createTime;
            this.url = imageInfo.url;
        }
    }
}
