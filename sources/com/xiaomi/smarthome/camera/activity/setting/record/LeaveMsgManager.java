package com.xiaomi.smarthome.camera.activity.setting.record;

import android.content.Context;
import com.google.gson.Gson;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.camera.activity.setting.RecordingVoiceActivity;
import com.xiaomi.smarthome.camera.activity.setting.bean.CommonResult;
import com.xiaomi.smarthome.camera.activity.setting.bean.Desc;
import com.xiaomi.smarthome.camera.activity.setting.bean.GetLeavMsgData;
import com.xiaomi.smarthome.camera.activity.setting.bean.LeaveMsg;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class LeaveMsgManager {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String TAG = RecordingVoiceActivity.class.getSimpleName();
    public static final String URL_HOST_PREFIX_BUSINESS = "business.smartcamera";
    public static final String URL_HOST_PREFIX_PROCESSOR = "processor.smartcamera";
    public static Gson gson = new Gson();
    private static LeaveMsgManager leaveMsgManager = new LeaveMsgManager();
    /* access modifiers changed from: private */
    public ArrayList<LeaveMsg> allMsgs = new ArrayList<>();
    private Context context;
    DownLoadLeaveMsgTask downLoadLeaveMsgTask = new DownLoadLeaveMsgTask();

    public static LeaveMsgManager getInstance(Context context2) {
        leaveMsgManager.context = context2;
        return leaveMsgManager;
    }

    public void addDownLoadItem(long j, String str, String str2) {
        final String str3 = str2;
        final long j2 = j;
        final String str4 = str;
        this.downLoadLeaveMsgTask.start(new Runnable() {
            public void run() {
                String audioFilePath = LeaveMsgUtil.getAudioFilePath(str3, j2);
                SDKLog.b(LeaveMsgManager.TAG, "addDownLoadItem ==filePath" + audioFilePath);
                SDKLog.b(LeaveMsgManager.TAG, "dataHex===" + str4);
                try {
                    byte[] a2 = Util.a(str4);
                    File file = new File(audioFilePath);
                    if (!file.exists()) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write(a2);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            LeaveMsgManager.this.downLoadLeaveMsgTask.successCount++;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    class DownLoadLeaveMsgTask {
        LinkedBlockingDeque<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<>();
        public volatile int successCount = 0;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, this.linkedBlockingDeque);

        DownLoadLeaveMsgTask() {
        }

        public void start(Runnable runnable) {
            this.threadPoolExecutor.execute(runnable);
        }
    }

    public void putData(CameraDevice cameraDevice, long j, String str, String str2, final Callback<Object> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", cameraDevice.getDid());
            jSONObject.put("type", Constants.J);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", str2);
            jSONObject2.put("duration", j * 1000);
            jSONObject.put("desc", jSONObject2);
            jSONObject.put("itemData", str);
        } catch (Exception e) {
            SDKLog.b(TAG, e.toString());
        }
        XmPluginHostApi.instance().callSmartHomeApi(cameraDevice.getModel(), "business.smartcamera", LeaveMsgConfigure.common_data_put, "POST", jSONObject.toString(), new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    String str = LeaveMsgManager.TAG;
                    SDKLog.b(str, "putData onSuccess=" + jSONObject.toString());
                    long j = jSONObject.getJSONObject("data").getLong("id");
                    if (callback != null) {
                        callback.onSuccess(Long.valueOf(j));
                    }
                } catch (Exception e) {
                    SDKLog.b(LeaveMsgManager.TAG, e.toString());
                    if (callback != null) {
                        callback.onFailure(0, e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = LeaveMsgManager.TAG;
                SDKLog.b(str2, "putData onFailure=" + i + "  " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public ArrayList<LeaveMsg> getAllMsgs() {
        return this.allMsgs;
    }

    public void getData(final CameraDevice cameraDevice, int i, int i2, final Callback<Object> callback) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", cameraDevice.getDid());
            jSONObject.put("type", Constants.J);
            jSONObject.put("startItemId", i);
            jSONObject.put("limit", 1);
        } catch (Exception e) {
            SDKLog.b(TAG, e.toString());
        }
        String jSONObject2 = jSONObject.toString();
        String str = TAG;
        SDKLog.b(str, "getData params=" + jSONObject2);
        getOneMsg(cameraDevice, jSONObject2, new Callback<GetLeavMsgData>() {
            public void onSuccess(GetLeavMsgData getLeavMsgData) {
                try {
                    String str = LeaveMsgManager.TAG;
                    SDKLog.b(str, "getData onSuccess=" + getLeavMsgData.toString());
                    LeaveMsgManager.this.allMsgs.addAll(getLeavMsgData.resultList);
                    if (getLeavMsgData.isContinue) {
                        jSONObject.put("startItemId", getLeavMsgData.nextStartItemId);
                        String jSONObject = jSONObject.toString();
                        String str2 = LeaveMsgManager.TAG;
                        SDKLog.b(str2, "getData params=" + jSONObject);
                        LeaveMsgManager.this.getOneMsg(cameraDevice, jSONObject, this);
                    } else if (callback != null) {
                        callback.onSuccess(null);
                    }
                } catch (Exception e) {
                    if (callback != null) {
                        callback.onFailure(0, e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = LeaveMsgManager.TAG;
                SDKLog.b(str2, "getData onFailure=" + i + "  " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void getOneMsg(final CameraDevice cameraDevice, String str, final Callback<GetLeavMsgData> callback) {
        XmPluginHostApi.instance().callSmartHomeApi(cameraDevice.getModel(), "business.smartcamera", LeaveMsgConfigure.common_data_scan, "GET", str, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                String str = LeaveMsgManager.TAG;
                SDKLog.b(str, "getData onSuccess=" + jSONObject.toString());
                try {
                    CommonResult commonResult = (CommonResult) LeaveMsgManager.gson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        String jsonObject = commonResult.data.getAsJsonObject().toString();
                        String str2 = LeaveMsgManager.TAG;
                        SDKLog.b(str2, "dataJson=" + jsonObject);
                        GetLeavMsgData getLeavMsgData = (GetLeavMsgData) LeaveMsgManager.gson.fromJson(jsonObject, GetLeavMsgData.class);
                        Iterator<LeaveMsg> it = getLeavMsgData.resultList.iterator();
                        while (it.hasNext()) {
                            LeaveMsg next = it.next();
                            next.descObj = (Desc) LeaveMsgManager.gson.fromJson(next.desc, Desc.class);
                            String str3 = next.itemData;
                            next.itemData = null;
                            LeaveMsgManager.this.addDownLoadItem(next.itemId, str3, cameraDevice.getDid());
                        }
                        if (callback != null) {
                            callback.onSuccess(getLeavMsgData);
                        }
                    }
                } catch (Exception e) {
                    if (callback != null) {
                        callback.onFailure(0, e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = LeaveMsgManager.TAG;
                SDKLog.b(str2, "getData onFailure=" + i + "  " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void removeItem(CameraDevice cameraDevice, long j, final Callback<Object> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", cameraDevice.getDid());
            jSONObject.put("type", Constants.J);
            jSONObject.put("itemId", j);
        } catch (Exception e) {
            SDKLog.b(TAG, e.toString());
        }
        String jSONObject2 = jSONObject.toString();
        String str = TAG;
        SDKLog.b(str, "removeItem params=" + jSONObject2);
        XmPluginHostApi.instance().callSmartHomeApi(cameraDevice.getModel(), "business.smartcamera", LeaveMsgConfigure.common_data_remove, "POST", jSONObject2, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    String str = LeaveMsgManager.TAG;
                    SDKLog.b(str, "removeItem onSuccess=" + jSONObject.toString());
                    boolean z = jSONObject.getJSONObject("data").getBoolean("removeResult");
                    if (!z) {
                        String str2 = LeaveMsgManager.TAG;
                        SDKLog.b(str2, "removeResult=" + z);
                        if (callback != null) {
                            callback.onFailure(0, "removeResult = false");
                        }
                    } else if (callback != null) {
                        callback.onSuccess(Boolean.valueOf(z));
                    }
                } catch (Exception e) {
                    SDKLog.b(LeaveMsgManager.TAG, e.toString());
                    if (callback != null) {
                        callback.onFailure(0, e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = LeaveMsgManager.TAG;
                SDKLog.b(str2, "removeItem onFailure=" + i + "  " + str);
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void modifyItem(CameraDevice cameraDevice, long j, String str, long j2, Callback<Object> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", cameraDevice.getDid());
            jSONObject.put("type", Constants.J);
            long j3 = j;
            jSONObject.put("itemId", j);
            JSONObject jSONObject2 = new JSONObject();
            String str2 = str;
            jSONObject2.put("name", str);
            jSONObject2.put("duration", j2);
            jSONObject.put("desc", jSONObject2);
        } catch (Exception e) {
            SDKLog.b(TAG, e.toString());
        }
        String jSONObject3 = jSONObject.toString();
        String str3 = TAG;
        SDKLog.b(str3, "modifyItem params=" + jSONObject3);
        final Callback<Object> callback2 = callback;
        XmPluginHostApi.instance().callSmartHomeApi(cameraDevice.getModel(), "business.smartcamera", LeaveMsgConfigure.common_data_modify, "POST", jSONObject3, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                try {
                    String str = LeaveMsgManager.TAG;
                    SDKLog.b(str, "modifyItem onSuccess=" + jSONObject.toString());
                    boolean z = jSONObject.getJSONObject("data").getBoolean("modifyResult");
                    if (!z) {
                        String str2 = LeaveMsgManager.TAG;
                        SDKLog.b(str2, "modifyResult=" + z);
                        if (callback2 != null) {
                            callback2.onFailure(0, "modifyResult = false");
                        }
                    } else if (callback2 != null) {
                        callback2.onSuccess(Boolean.valueOf(z));
                    }
                } catch (Exception e) {
                    SDKLog.b(LeaveMsgManager.TAG, e.toString());
                    if (callback2 != null) {
                        callback2.onFailure(0, e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = LeaveMsgManager.TAG;
                SDKLog.b(str2, "modifyItem onFailure=" + i + "  " + str);
                if (callback2 != null) {
                    callback2.onFailure(i, str);
                }
            }
        }, Parser.DEFAULT_PARSER);
    }
}
