package com.xiaomi.smarthome.miio.camera.face;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.model.Tags;
import com.mijia.model.alarm.AlarmNetUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.face.model.CommonResult;
import com.xiaomi.smarthome.miio.camera.face.model.FaceIdMetaResult;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfos;
import com.xiaomi.smarthome.miio.camera.face.model.UnmarkedFaceInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FaceManager {
    public static final String TAG = "FaceManager";
    public static Gson gGson = new Gson();
    private static FaceManager sInstance;
    private Map<String, String> cachedFaceImage = new HashMap();
    private BaseDevice mDevice;
    /* access modifiers changed from: private */
    public long unMarkedFaceNextTime;

    public interface IFaceCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t, Object obj);
    }

    private FaceManager(BaseDevice baseDevice) {
        this.mDevice = baseDevice;
    }

    public static FaceManager getInstance(BaseDevice baseDevice) {
        if (sInstance == null) {
            synchronized (FaceManager.class) {
                if (sInstance == null) {
                    sInstance = new FaceManager(baseDevice);
                }
            }
        }
        return sInstance;
    }

    public String getDeviceId() {
        return this.mDevice.getDid();
    }

    public String getModel() {
        return this.mDevice.getModel();
    }

    public BaseDevice getDevice() {
        return this.mDevice;
    }

    public void releaseFaceImage() {
        this.cachedFaceImage.clear();
        sInstance = null;
    }

    public void addFigure(String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureInfo", str);
            jSONObject.put("figureName", str);
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.a(str2, "addFigure request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/add/figure", "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_addFigure=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, commonResult);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_addFigure code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void modifyFigure(String str, String str2, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureId", str);
            jSONObject.put("figureInfo", str2);
            jSONObject.put("figureName", str2);
            String jSONObject2 = jSONObject.toString();
            String str3 = TAG;
            LogUtil.a(str3, "modifyFigure request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/modify/figure", "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_modifyFigure=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult == null) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onFailure(0, "commonResult=null");
                        }
                    } else if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, commonResult);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_modifyFigure code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void deleteFigure(String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureId", str);
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.a(str2, "deleteFigure request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/delete/figure", "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_deleteFigure=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, commonResult);
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_deleteFigure code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void deleteFigures(final IFaceCallback iFaceCallback, Object... objArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            JSONArray jSONArray = new JSONArray();
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ids", jSONArray);
            jSONObject.put("figureIds", jSONObject2.toString());
            String jSONObject3 = jSONObject.toString();
            LogUtil.a(TAG, "deleteFigures request params=" + jSONObject3);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/delete/figures", "POST", jSONObject3, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_deleteFigures=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, commonResult);
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_deleteFigures code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getFigures(final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "getFigures request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", FaceConfiguration.FACE_APP_GET_FIGURES, "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_getFigures=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, FaceManager.gGson.fromJson(commonResult.data, FigureInfos.class));
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFigures code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getFigureByMarkedFace(String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("faceId", str);
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.a(str2, "getFigureByMarkedFace request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/get/figureByMarkedFace", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_getFigureByMarkedFace=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, FaceManager.gGson.fromJson(commonResult.data, FigureInfo.class));
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFigureByMarkedFace code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getFigureFaces(final IFaceCallback iFaceCallback, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureId", str);
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.c(str2, "getFigureFaces request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/get/figureFaces", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.c(str, "onSuccess_getFigureFaces=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, FaceManager.gGson.fromJson(commonResult.data, FaceIdMetaResult.class));
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFigureFaces code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getFaceIdsByFiledId(final IFaceCallback iFaceCallback, Object... objArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            JSONArray jSONArray = new JSONArray();
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("fileIds", jSONArray);
            jSONObject.put("fileIds", jSONObject2);
            String jSONObject3 = jSONObject.toString();
            LogUtil.c(TAG, "getFaceIdsByFiledId request params=" + jSONObject3);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", AlarmNetUtils.B, "GET", jSONObject3, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.c(str, "onSuccess_getFaceIdsByFiledId=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFaceIdsByFiledId code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public String getFaceImg(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.cachedFaceImage.containsKey(str)) {
            return this.cachedFaceImage.get(str);
        }
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("faceId", str);
            jSONObject2.put(Constants.Name.PREFIX, "business.smartcamera.");
            jSONObject2.put("method", "GET");
            jSONObject2.put("path", FaceConfiguration.FACE_APP_GET_FACE_IMG);
            str2 = XmPluginHostApi.instance().generateRequestUrl(this.mDevice.getModel(), jSONObject2, jSONObject);
            try {
                this.cachedFaceImage.put(str, str2);
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            str2 = null;
            LogUtil.b(TAG, "" + e.getLocalizedMessage());
            return str2;
        }
        return str2;
    }

    public void getFaces(final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "getFaces request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/get/faces", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_getFaces=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFaces code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void addFaceToFigure(String str, String str2, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("faceId", str);
            jSONObject.put("figureId", str2);
            String jSONObject2 = jSONObject.toString();
            String str3 = TAG;
            LogUtil.a(str3, "addFaceToFigure request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/add/face", "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_addFaceToFigure=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_addFaceToFigure code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void deleteFaceFromFigure(String str, String str2, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("faceId", str);
            jSONObject.put("figureId", str2);
            String jSONObject2 = jSONObject.toString();
            String str3 = TAG;
            LogUtil.a(str3, "deleteFaceFromFigure request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", AlarmNetUtils.D, "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_deleteFaceFromFigure=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_deleteFaceFromFigure code=" + i + " mgetgetgetsg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void deleteFacesFromFigure(Object[] objArr, String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureId", str);
            JSONArray jSONArray = new JSONArray();
            for (Object put : objArr) {
                jSONArray.put(put);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ids", jSONArray);
            jSONObject.put("faceIds", jSONObject2.toString());
            String jSONObject3 = jSONObject.toString();
            LogUtil.a(TAG, "deleteFacesFromFigure request params=" + jSONObject3);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/delete/faces", "POST", jSONObject3, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_deleteFacesFromFigure=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_deleteFacesFromFigure code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getUnmarkFaces(int i, boolean z, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put(Tags.Coupon.BEGIN_TIME, 0);
            if (z) {
                jSONObject.put("endTime", this.unMarkedFaceNextTime);
            } else {
                this.unMarkedFaceNextTime = -1;
                jSONObject.put("endTime", System.currentTimeMillis());
            }
            jSONObject.put("limit", i);
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "getUnmarkFaces request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/get/unmarkFaces", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_getFigureFaces=" + jSONObject.toString());
                    CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                    if (commonResult.code == 0) {
                        if (iFaceCallback != null) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            long unused = FaceManager.this.unMarkedFaceNextTime = optJSONObject.optLong("nextTime");
                            iFaceCallback.onSuccess(null, FaceManager.gGson.fromJson(optJSONObject.optJSONArray("faceInfoMetas").toString(), new TypeToken<ArrayList<UnmarkedFaceInfo>>() {
                            }.getType()));
                        }
                    } else if (iFaceCallback != null) {
                        IFaceCallback iFaceCallback = iFaceCallback;
                        int i = commonResult.code;
                        iFaceCallback.onFailure(i, commonResult.code + "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getUnmarkFaces code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (JSONException e) {
            String str2 = TAG;
            LogUtil.b(str2, "" + e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void getFigureByName(String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("figureName", str);
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.a(str2, "getFigureByName request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", FaceConfiguration.FACE_APP_GET_FIGURE_BY_NAME, "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_getFigureByName=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_getFigureByName code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void putFaceSwitch(boolean z, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "putFaceSwitch request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", AlarmNetUtils.x, "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_putFaceSwitch=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_putFaceSwitch code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.toString());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void putBabyCrySwitch(boolean z, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("open", z);
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "putBabyCrySwitch request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", AlarmNetUtils.y, "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_putBabyCrySwitch=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_putBabyCrySwitch code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void feedBack(String str, boolean z, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("region", Locale.getDefault().getCountry());
            jSONObject.put("fileId", str);
            jSONObject.put("isVisible", z);
            jSONObject.put("type", "face");
            String jSONObject2 = jSONObject.toString();
            String str2 = TAG;
            LogUtil.a(str2, "feedBack request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", AlarmNetUtils.C, "POST", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_feedBack=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_feedBack code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void searchFaces(String[] strArr, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("model", this.mDevice.getModel());
            JSONArray jSONArray = new JSONArray();
            for (String put : strArr) {
                jSONArray.put(put);
            }
            jSONObject.put("faceIds", jSONArray);
            String jSONObject2 = jSONObject.toString();
            LogUtil.a(TAG, "searchFaces request params=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/faces/search", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_searchFaces=" + jSONObject.toString());
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(null, jSONObject);
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_searchFaces code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (Exception e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void uploadImageFile(Context context, String str, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", this.mDevice.getModel());
            jSONObject.put("did", this.mDevice.getDid());
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            XmPluginHostApi.instance().uploadImageFile(this.mDevice.getModel(), this.mDevice.getDid(), "business.smartcamera", "/miot/camera/app/v1/add/photo", jSONObject, arrayList, new ICloudDataCallback<JSONObject>() {
                public void onCloudDataProgress(int i) {
                }

                public void onCloudDataSuccess(JSONObject jSONObject, Object obj) {
                    if (iFaceCallback != null) {
                        iFaceCallback.onSuccess(jSONObject, obj);
                    }
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "uploadImageFile result:" + jSONObject + ":" + obj);
                }

                public void onCloudDataFailed(int i, String str) {
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                    String str2 = FaceManager.TAG;
                    LogUtil.a(str2, "uploadImageFile errorCode" + i + ":" + str);
                }
            });
        } catch (JSONException e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }

    public void markUseFreeFaceService(Context context, final IFaceCallback iFaceCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mDevice.getDid());
            jSONObject.put("region", Locale.getDefault().getCountry());
            String jSONObject2 = jSONObject.toString();
            String str = TAG;
            LogUtil.a(str, "mark free face service=" + jSONObject2);
            XmPluginHostApi.instance().callSmartHomeApi(this.mDevice.getModel(), "business.smartcamera.", "/miot/camera/app/v1/vip/freeface/addFreeface", "GET", jSONObject2, new Callback<JSONObject>() {
                public void onSuccess(JSONObject jSONObject) {
                    String str = FaceManager.TAG;
                    LogUtil.a(str, "onSuccess_mark free face service=" + jSONObject.toString());
                    if ("ok".equals(jSONObject.optString("result"))) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onSuccess(null, "");
                        }
                    } else if (iFaceCallback != null) {
                        iFaceCallback.onFailure(400209, "");
                    }
                }

                public void onFailure(int i, String str) {
                    String str2 = FaceManager.TAG;
                    LogUtil.b(str2, "onFailure_searchFaces code=" + i + " msg=" + str);
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(i, str);
                    }
                }
            }, Parser.DEFAULT_PARSER);
        } catch (JSONException e) {
            LogUtil.b(TAG, e.getLocalizedMessage());
            if (iFaceCallback != null) {
                iFaceCallback.onFailure(-1001, "" + e.getLocalizedMessage());
            }
        }
    }
}
