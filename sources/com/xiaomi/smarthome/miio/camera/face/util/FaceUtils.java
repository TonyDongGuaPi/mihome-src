package com.xiaomi.smarthome.miio.camera.face.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.PermissionChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.JsonObject;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.face.FaceConfiguration;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceCameraActivity;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceEmptyActivity;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerActivity;
import com.xiaomi.smarthome.miio.camera.face.model.CommonResult;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfo;
import com.xiaomi.smarthome.miio.camera.face.model.FigureInfos;
import com.xiaomi.smarthome.miio.camera.face.photopicker.PhotoPickerActivity;
import com.xiaomi.smarthome.miio.camera.face.widget.FaceMarkDialog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class FaceUtils {
    public static final int REQUEST_ALBUM = 101;
    public static final int REQUEST_CAMERA = 102;
    private static String[] permitArray = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private static boolean isEmojiCharacter(char c) {
        return c == 0 || c == 9 || c == 10 || c == 13 || (c >= ' ' && c <= 55295) || ((c >= 57344 && c <= 65533) || (c >= 0 && c <= 65535));
    }

    public static void showPickAlbumGuideDialog(Activity activity, final String str, final View.OnClickListener onClickListener) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.face_pick_album_guide_dialog, (ViewGroup) null);
        builder.b(inflate);
        final MLAlertDialog c = builder.c();
        inflate.findViewById(R.id.btn_know).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                c.dismiss();
                FaceUtils.setNeedPickAlbumGuide(str, false);
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
        c.show();
    }

    public static void showSelectDialog(final Activity activity, final String str) {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        String[] strArr = {activity.getString(R.string.select_dialog_camera), activity.getString(R.string.select_dialog_album), activity.getString(R.string.cancel)};
        builder.a((CharSequence) activity.getString(R.string.select_dialog_title));
        builder.a((CharSequence[]) strArr, 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    FaceUtils.tryJumpFaceCamera(activity);
                } else if (i == 1) {
                    FaceUtils.tryChoosePicture(activity, str);
                }
                dialogInterface.dismiss();
            }
        });
        builder.d();
    }

    public static void tryJumpFaceCamera(final Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            activity.startActivityForResult(new Intent(activity, FaceCameraActivity.class), 102);
        } else if (XmPluginHostApi.instance().getApiLevel() >= 75) {
            XmPluginHostApi.instance().checkAndRequestPermisson(activity, true, new Callback<List<String>>() {
                public void onSuccess(List<String> list) {
                    activity.startActivityForResult(new Intent(activity, FaceCameraActivity.class), 102);
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((CharSequence) "permission error");
                }
            }, permitArray);
        } else if (PermissionChecker.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ToastUtil.a((CharSequence) activity.getString(R.string.no_write_permission));
        } else if (PermissionChecker.checkSelfPermission(activity, "android.permission.CAMERA") != 0) {
            ToastUtil.a((CharSequence) activity.getString(R.string.camera_no_permission));
        } else {
            activity.startActivityForResult(new Intent(activity, FaceCameraActivity.class), 102);
        }
    }

    public static void tryChoosePicture(final Activity activity, String str) {
        if (getNeedPickAlbumGuide(str)) {
            showPickAlbumGuideDialog(activity, str, new View.OnClickListener() {
                public void onClick(View view) {
                    activity.startActivityForResult(new Intent(activity, PhotoPickerActivity.class), 101);
                }
            });
        } else {
            activity.startActivityForResult(new Intent(activity, PhotoPickerActivity.class), 101);
        }
    }

    public static void processAddFaceToFigure(Context context, String str, final String str2, final FaceManager faceManager, final FaceManager.IFaceCallback iFaceCallback) {
        faceManager.uploadImageFile(context, str, new FaceManager.IFaceCallback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject, Object obj) {
                try {
                    faceManager.addFaceToFigure(((JSONObject) jSONObject.getJSONObject("data").getJSONArray("faceInfoMetas").get(0)).getString("faceId"), str2, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            if (iFaceCallback != null) {
                                iFaceCallback.onSuccess(obj, obj2);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (iFaceCallback != null) {
                                iFaceCallback.onFailure(i, str);
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (iFaceCallback != null) {
                        iFaceCallback.onFailure(-1, "Json parse expection");
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (iFaceCallback != null) {
                    iFaceCallback.onFailure(i, str);
                }
            }
        });
    }

    public static void processMarkFace(final Context context, final String str, final FaceManager faceManager, final FaceManager.IFaceCallback iFaceCallback) {
        faceManager.getFigures(new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                FaceUtils.showFaceMarkDialog(context, str, ((FigureInfos) obj2).figureInfos, faceManager, iFaceCallback);
            }

            public void onFailure(int i, String str) {
                FaceUtils.showFaceMarkDialog(context, str, (List<FigureInfo>) null, faceManager, iFaceCallback);
            }
        });
    }

    public static void processReplaceFace(Context context, String str, String str2, String str3, FaceManager faceManager, FaceManager.IFaceCallback iFaceCallback) {
        final Context context2 = context;
        final String str4 = str;
        final FaceManager faceManager2 = faceManager;
        final FaceManager.IFaceCallback iFaceCallback2 = iFaceCallback;
        final String str5 = str2;
        final String str6 = str3;
        faceManager.getFigures(new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                FaceUtils.showFaceReplaceDialog(context2, str4, ((FigureInfos) obj2).figureInfos, faceManager2, iFaceCallback2, str5, str6);
            }

            public void onFailure(int i, String str) {
                FaceUtils.showFaceReplaceDialog(context2, str4, (ArrayList<FigureInfo>) null, faceManager2, iFaceCallback2, str5, str6);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void showFaceReplaceDialog(Context context, String str, ArrayList<FigureInfo> arrayList, FaceManager faceManager, FaceManager.IFaceCallback iFaceCallback, String str2, String str3) {
        FaceMarkDialog faceMarkDialog = new FaceMarkDialog(context, str, arrayList, faceManager, str3);
        final String str4 = str3;
        final FaceManager faceManager2 = faceManager;
        final String str5 = str;
        final String str6 = str2;
        final FaceManager.IFaceCallback iFaceCallback2 = iFaceCallback;
        final Context context2 = context;
        faceMarkDialog.setOnNameSelectListener(new FaceMarkDialog.OnNameSelectListener() {
            public void onNameSelected(final String str, boolean z) {
                if (!str4.equals(str)) {
                    faceManager2.deleteFaceFromFigure(str5, str6, new FaceManager.IFaceCallback() {
                        public void onSuccess(Object obj, Object obj2) {
                            faceManager2.getFigureByName(str, new FaceManager.IFaceCallback<JSONObject>() {
                                public void onSuccess(JSONObject jSONObject, Object obj) {
                                    try {
                                        final JSONObject jSONObject2 = ((JSONObject) obj).getJSONObject("data");
                                        faceManager2.addFaceToFigure(str5, jSONObject2.getString("figureId"), new FaceManager.IFaceCallback() {
                                            public void onSuccess(Object obj, Object obj2) {
                                                if (iFaceCallback2 != null) {
                                                    iFaceCallback2.onSuccess(jSONObject2.toString(), obj2);
                                                }
                                            }

                                            public void onFailure(int i, String str) {
                                                if (i == 400302) {
                                                    ToastUtil.a((int) R.string.figure_max_tips);
                                                } else if (i == 400305) {
                                                    ToastUtil.a((int) R.string.face_max_tips);
                                                } else if (iFaceCallback2 != null) {
                                                    iFaceCallback2.onFailure(i, str);
                                                }
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        if (iFaceCallback2 != null) {
                                            FaceManager.IFaceCallback iFaceCallback = iFaceCallback2;
                                            iFaceCallback.onFailure(-1, e.getLocalizedMessage() + "");
                                        }
                                    }
                                }

                                public void onFailure(int i, String str) {
                                    FaceUtils.processAddFace(context2, str, str5, faceManager2, iFaceCallback2);
                                }
                            });
                        }

                        public void onFailure(int i, String str) {
                            iFaceCallback2.onFailure(i, str);
                        }
                    });
                }
            }
        });
        faceMarkDialog.show();
    }

    /* access modifiers changed from: private */
    public static void showFaceMarkDialog(final Context context, final String str, List<FigureInfo> list, final FaceManager faceManager, final FaceManager.IFaceCallback iFaceCallback) {
        FaceMarkDialog faceMarkDialog = new FaceMarkDialog(context, str, list, faceManager);
        faceMarkDialog.setOnNameSelectListener(new FaceMarkDialog.OnNameSelectListener() {
            public void onNameSelected(final String str, boolean z) {
                faceManager.getFigureByName(str, new FaceManager.IFaceCallback<JSONObject>() {
                    public void onSuccess(JSONObject jSONObject, Object obj) {
                        try {
                            final JSONObject jSONObject2 = ((JSONObject) obj).getJSONObject("data");
                            faceManager.addFaceToFigure(str, jSONObject2.getString("figureId"), new FaceManager.IFaceCallback() {
                                public void onSuccess(Object obj, Object obj2) {
                                    if (iFaceCallback != null) {
                                        iFaceCallback.onSuccess(jSONObject2.toString(), obj2);
                                    }
                                }

                                public void onFailure(int i, String str) {
                                    if (i == 400302) {
                                        ToastUtil.a((int) R.string.figure_max_tips);
                                    } else if (i == 400305) {
                                        ToastUtil.a((int) R.string.face_max_tips);
                                    } else if (iFaceCallback != null) {
                                        iFaceCallback.onFailure(i, str);
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (iFaceCallback != null) {
                                FaceManager.IFaceCallback iFaceCallback = iFaceCallback;
                                iFaceCallback.onFailure(-1, e.getLocalizedMessage() + "");
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        FaceUtils.processAddFace(context, str, str, faceManager, iFaceCallback);
                    }
                });
            }
        });
        faceMarkDialog.show();
    }

    public static void processAddFace(Context context, final String str, final String str2, final FaceManager faceManager, final FaceManager.IFaceCallback iFaceCallback) {
        faceManager.addFigure(str, new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                final JsonObject asJsonObject = ((CommonResult) obj2).data.getAsJsonObject();
                faceManager.addFaceToFigure(str2, asJsonObject.get("figureId").getAsString(), new FaceManager.IFaceCallback() {
                    public void onSuccess(Object obj, Object obj2) {
                        if (iFaceCallback != null) {
                            asJsonObject.addProperty("figureInfo", str);
                            asJsonObject.addProperty("figureName", str);
                            iFaceCallback.onSuccess(asJsonObject.toString(), obj2);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (iFaceCallback != null) {
                            iFaceCallback.onFailure(i, str);
                        }
                    }
                });
            }

            public void onFailure(int i, String str) {
                if (i == 400302) {
                    ToastUtil.a((int) R.string.figure_max_tips);
                } else if (iFaceCallback != null) {
                    iFaceCallback.onFailure(i, str);
                }
            }
        });
    }

    public static boolean getNeedFaceGuide(String str) {
        Context context = XmPluginHostApi.instance().context();
        StringBuilder sb = new StringBuilder();
        sb.append("camera_need_face_guide_");
        sb.append(str);
        return GlobalProvider.getInt(context, sb.toString(), 1) != 0;
    }

    public static void setNeedFaceGuide(String str, boolean z) {
        Context context = XmPluginHostApi.instance().context();
        GlobalProvider.save(context, "camera_need_face_guide_" + str, z ? 1 : 0);
    }

    public static boolean getNeedFaceGuideForUsingFreeFaceService(String str) {
        Context context = XmPluginHostApi.instance().context();
        StringBuilder sb = new StringBuilder();
        sb.append("camera_need_face_guide_");
        sb.append(str);
        return GlobalProvider.getInt(context, sb.toString(), 1) != 0;
    }

    public static void setNeedFaceGuideForUsingFreeFaceService(String str, boolean z) {
        Context context = XmPluginHostApi.instance().context();
        GlobalProvider.save(context, "camera_need_face_guide_" + str, z ? 1 : 0);
    }

    public static boolean getNeedPickAlbumGuide(String str) {
        Context context = XmPluginHostApi.instance().context();
        return context.getSharedPreferences("mijia_camera_" + str, 0).getBoolean("need_pick_album_guide", true);
    }

    public static void setNeedPickAlbumGuide(String str, boolean z) {
        Context context = XmPluginHostApi.instance().context();
        context.getSharedPreferences("mijia_camera_" + str, 0).edit().putBoolean("need_pick_album_guide", z).apply();
    }

    public static boolean containsEmoji(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isEmojiCharacter(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        if (getNeedFaceGuide(r6 + r1) == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004e, code lost:
        if (getNeedFaceGuideForUsingFreeFaceService(r6 + r1) != false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        r0 = new android.content.Intent(r5, com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.class);
        r0.putExtra("extra_device_did", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r6 = new org.json.JSONObject(r7);
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_tips_info, r6.optString("face_tips"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_from_lowpower, r6.optBoolean("from_lowpower"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_is_vip_user, r6.optBoolean("is_vip_user"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_buy_cloud_url, r6.optString("buy_cloud_url"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_buy_cloud_title, r6.optString("buy_cloud_title"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_face_from_camera, r6.optBoolean("is_from_camera"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_FACE_TRY, r6.optBoolean("try_use_face"));
        r0.putExtra(com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.KEY_EXTRA_IS_USING_FREE_FACE, r6.optBoolean("is_using_service"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00ba, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00bb, code lost:
        com.xiaomi.smarthome.framework.log.LogUtil.b("openFaceManagerActivity", r6.toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void openFaceManagerActivity(int r4, android.app.Activity r5, java.lang.String r6, java.lang.String r7) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x000c }
            r0.<init>(r7)     // Catch:{ Exception -> 0x000c }
            java.lang.String r1 = "is_using_service"
            boolean r0 = r0.optBoolean(r1)     // Catch:{ Exception -> 0x000c }
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            com.xiaomi.smarthome.device.api.XmPluginHostApi r1 = com.xiaomi.smarthome.device.api.XmPluginHostApi.instance()
            com.xiaomi.smarthome.device.api.DeviceStat r1 = r1.getDeviceByDid(r6)
            java.lang.String r1 = r1.userId
            boolean r2 = getNeedFaceGuide(r6)
            r3 = 1
            if (r2 != 0) goto L_0x0022
            openFaceActivity(r5, r6, r3, r4)
            return
        L_0x0022:
            if (r0 != 0) goto L_0x0039
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            boolean r2 = getNeedFaceGuide(r2)
            if (r2 != 0) goto L_0x0050
        L_0x0039:
            if (r0 == 0) goto L_0x00c8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            boolean r0 = getNeedFaceGuideForUsingFreeFaceService(r0)
            if (r0 == 0) goto L_0x00c8
        L_0x0050:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity> r1 = com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity.class
            r0.<init>(r5, r1)
            java.lang.String r1 = "extra_device_did"
            r0.putExtra(r1, r6)
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00ba }
            r6.<init>(r7)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_tips_info"
            java.lang.String r1 = "face_tips"
            java.lang.String r1 = r6.optString(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_from_lowpower"
            java.lang.String r1 = "from_lowpower"
            boolean r1 = r6.optBoolean(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_is_vip_user"
            java.lang.String r1 = "is_vip_user"
            boolean r1 = r6.optBoolean(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_buy_cloud_url"
            java.lang.String r1 = "buy_cloud_url"
            java.lang.String r1 = r6.optString(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_buy_cloud_title"
            java.lang.String r1 = "buy_cloud_title"
            java.lang.String r1 = r6.optString(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "KEY_EXTRA_face_from_camera"
            java.lang.String r1 = "is_from_camera"
            boolean r1 = r6.optBoolean(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "key_extra_try_face"
            java.lang.String r1 = "try_use_face"
            boolean r1 = r6.optBoolean(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r1)     // Catch:{ JSONException -> 0x00ba }
            java.lang.String r7 = "key_extra_is_using_free_service"
            java.lang.String r1 = "is_using_service"
            boolean r6 = r6.optBoolean(r1)     // Catch:{ JSONException -> 0x00ba }
            r0.putExtra(r7, r6)     // Catch:{ JSONException -> 0x00ba }
            goto L_0x00c4
        L_0x00ba:
            r6 = move-exception
            java.lang.String r7 = "openFaceManagerActivity"
            java.lang.String r6 = r6.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.b(r7, r6)
        L_0x00c4:
            r5.startActivityForResult(r0, r4)
            goto L_0x00cb
        L_0x00c8:
            openFaceActivity(r5, r6, r3, r4)
        L_0x00cb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.face.util.FaceUtils.openFaceManagerActivity(int, android.app.Activity, java.lang.String, java.lang.String):void");
    }

    public static void openFaceActivity(final Activity activity, final String str, final boolean z, final int i) {
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        String str2 = deviceByDid.userId;
        String str3 = deviceByDid.model;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("model", str3);
        } catch (Exception unused) {
        }
        String jSONObject2 = jSONObject.toString();
        SDKLog.c("Face, ", "params=" + jSONObject2);
        XmPluginHostApi.instance().callSmartHomeApi(str3, "business.smartcamera.", FaceConfiguration.FACE_APP_GET_FIGURES, "GET", jSONObject2, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                CommonResult commonResult = (CommonResult) FaceManager.gGson.fromJson(jSONObject.toString(), CommonResult.class);
                if (commonResult.code == 0) {
                    FigureInfos figureInfos = (FigureInfos) FaceManager.gGson.fromJson(commonResult.data, FigureInfos.class);
                    if (figureInfos == null || figureInfos.figureInfos == null || figureInfos.figureInfos.size() <= 0) {
                        FaceUtils.jumpEmptyFaceActivity(activity, str, z, i);
                    } else {
                        FaceUtils.jumpFaceManagerActivity(activity, str, i);
                    }
                } else {
                    FaceUtils.jumpEmptyFaceActivity(activity, str, z, i);
                }
            }

            public void onFailure(int i, String str) {
                FaceUtils.jumpEmptyFaceActivity(activity, str, z, i);
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* access modifiers changed from: private */
    public static void jumpFaceManagerActivity(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, FaceManagerActivity.class);
        intent.putExtra("extra_device_did", str);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: private */
    public static void jumpEmptyFaceActivity(Activity activity, String str, boolean z, int i) {
        if (!z) {
            String str2 = XmPluginHostApi.instance().getDeviceByDid(str).userId;
            setNeedFaceGuideForUsingFreeFaceService(str + str2, false);
        }
        Intent intent = new Intent(activity, FaceEmptyActivity.class);
        intent.putExtra("extra_device_did", str);
        activity.startActivityForResult(intent, i);
    }
}
