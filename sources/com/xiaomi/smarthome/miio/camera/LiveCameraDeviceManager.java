package com.xiaomi.smarthome.miio.camera;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.SystemUtils;
import com.xiaomi.smarthomedevice.R;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;

public class LiveCameraDeviceManager {
    public static final String EXTRA_DEVICE_MODEL = "extra_device_model";
    public static final String FILE_NAME = "live_camera_device";
    public static final String TAG = "LiveCameraDeviceManager";
    public static final float UPDATE_DISTANCE = 10000.0f;
    private static LiveCameraDeviceManager __INSTACNE__;
    private static Object sLock = new Object();
    ArrayList<LiveCameraDevice> liveCameraDeviceList = new ArrayList<>();
    boolean mHasUpdateData = false;
    volatile boolean mIsUpdating = false;
    double mLastLatitude = 0.0d;
    double mLastLongitude = 0.0d;

    private LiveCameraDeviceManager() {
        new Thread() {
            public void run() {
                super.run();
                LiveCameraDeviceManager.this.mIsUpdating = true;
                try {
                    Log.d(LiveCameraDeviceManager.TAG, "initial");
                    FileInputStream openFileInput = CommonApplication.getAppContext().openFileInput(LiveCameraDeviceManager.FILE_NAME);
                    DataInputStream dataInputStream = new DataInputStream(openFileInput);
                    new Date();
                    dataInputStream.readLong();
                    LiveCameraDeviceManager.this.mLastLongitude = dataInputStream.readDouble();
                    LiveCameraDeviceManager.this.mLastLatitude = dataInputStream.readDouble();
                    int readInt = dataInputStream.readInt();
                    if (readInt > 0) {
                        if (readInt <= 102400) {
                            byte[] bArr = new byte[readInt];
                            dataInputStream.read(bArr);
                            openFileInput.close();
                            LiveCameraDeviceManager.this.updateDevice(new JSONArray(new String(bArr, "UTF-8")));
                            LiveCameraDeviceManager.this.mHasUpdateData = true;
                            LiveCameraDeviceManager.this.mIsUpdating = false;
                        }
                    }
                    CommonApplication.getAppContext().deleteFile(LiveCameraDeviceManager.FILE_NAME);
                } catch (Throwable unused) {
                    CommonApplication.getAppContext().deleteFile(LiveCameraDeviceManager.FILE_NAME);
                }
                LiveCameraDeviceManager.this.mIsUpdating = false;
            }
        }.start();
    }

    public static LiveCameraDeviceManager instance() {
        if (__INSTACNE__ == null) {
            synchronized (sLock) {
                if (__INSTACNE__ == null) {
                    __INSTACNE__ = new LiveCameraDeviceManager();
                }
            }
        }
        return __INSTACNE__;
    }

    public ArrayList<LiveCameraDevice> getLiveCameraDeviceList() {
        return this.liveCameraDeviceList;
    }

    public void updateDevice(JSONArray jSONArray) {
        if (jSONArray != null) {
            this.liveCameraDeviceList.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                LiveCameraDevice parse = LiveCameraDevice.parse(jSONArray.optJSONObject(i));
                if (parse != null) {
                    this.liveCameraDeviceList.add(parse);
                }
            }
        }
    }

    public void update() {
        if (!this.mIsUpdating) {
            Log.d(TAG, "update");
            this.mIsUpdating = true;
            Location b = SHLocationManager.a().b();
            if (b != null) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.mLastLatitude, this.mLastLongitude, b.getLatitude(), b.getLongitude(), fArr);
                if (fArr[0] < 10000.0f) {
                    this.mIsUpdating = false;
                    return;
                }
            }
            Log.d(TAG, "update from server");
            SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                public void onSucceed(String str, Location location) {
                    super.onSucceed(str, location);
                    LiveCameraDeviceManager.this.updateDevice(location);
                }

                public void onFailure(String str) {
                    super.onFailure(str);
                    LiveCameraDeviceManager.this.mIsUpdating = false;
                }

                public void onTimeout(String str) {
                    super.onTimeout(str);
                    LiveCameraDeviceManager.this.mIsUpdating = false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateDevice(final Location location) {
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                double d;
                double d2;
                if (location != null) {
                    d = location.getLatitude();
                    d2 = location.getLongitude();
                } else {
                    d2 = 0.0d;
                    d = 0.0d;
                }
                DevicelibApi.getLiveCameraList(CommonApplication.getAppContext(), d2, d, new AsyncCallback<JSONArray, Error>() {
                    public void onSuccess(JSONArray jSONArray) {
                        Log.d("test", "onSuccess");
                        LiveCameraDeviceManager.this.mIsUpdating = false;
                        LiveCameraDeviceManager.this.mLastLongitude = location.getLongitude();
                        LiveCameraDeviceManager.this.mLastLatitude = location.getLatitude();
                        if (jSONArray != null) {
                            try {
                                FileOutputStream openFileOutput = CommonApplication.getAppContext().openFileOutput(LiveCameraDeviceManager.FILE_NAME, 0);
                                DataOutputStream dataOutputStream = new DataOutputStream(openFileOutput);
                                byte[] bytes = jSONArray.toString().getBytes("UTF-8");
                                dataOutputStream.writeLong(new Date().getTime());
                                dataOutputStream.writeDouble(location.getLongitude());
                                dataOutputStream.writeDouble(location.getLatitude());
                                dataOutputStream.writeInt(bytes.length);
                                dataOutputStream.write(bytes);
                                openFileOutput.close();
                            } catch (Exception unused) {
                            }
                            LiveCameraDeviceManager.this.updateDevice(jSONArray);
                        }
                    }

                    public void onFailure(Error error) {
                        LiveCameraDeviceManager.this.mIsUpdating = false;
                    }
                });
            }
        });
    }

    public void openLiveCamera(Context context, LiveCameraDevice liveCameraDevice) {
        PluginRecord d = CoreApi.a().d(liveCameraDevice.model);
        Intent intent = new Intent();
        intent.putExtra("id", liveCameraDevice.did);
        intent.putExtra("name", liveCameraDevice.name);
        intent.putExtra("video_play_url", liveCameraDevice.mVideoPlayUrl);
        intent.putExtra("dev_desc", liveCameraDevice.mDevDes);
        intent.putExtra("dev_location", liveCameraDevice.mAddressDes);
        intent.putExtra("extra_device_model", liveCameraDevice.model);
        PluginApi.getInstance().sendMessage(context, d, 20, intent, (DeviceStat) null, (RunningProcess) null, false, new SendMessageCallbackImpl(context));
    }

    public static class SendMessageCallbackImpl extends PluginApi.SendMessageCallback {
        WeakReference<Context> conextRef;
        XQProgressHorizontalDialog mXQProgressHorizontalDialog;

        public SendMessageCallbackImpl(Context context) {
            this.conextRef = new WeakReference<>(context);
            this.mXQProgressHorizontalDialog = new XQProgressHorizontalDialog(context);
            this.mXQProgressHorizontalDialog.setMessage(context.getString(R.string.plugin_loading));
            this.mXQProgressHorizontalDialog.a(0, 100);
            this.mXQProgressHorizontalDialog.c();
        }

        public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            super.onDownloadStart(pluginRecord, pluginDownloadTask);
            this.mXQProgressHorizontalDialog.show();
        }

        public void onDownloadProgress(PluginRecord pluginRecord, float f) {
            super.onDownloadProgress(pluginRecord, f);
            this.mXQProgressHorizontalDialog.a(100, (int) (f * 100.0f));
        }

        public void onDownloadCancel() {
            this.mXQProgressHorizontalDialog.dismiss();
        }

        public void onDownloadFailure(PluginError pluginError) {
            this.mXQProgressHorizontalDialog.dismiss();
            if (this.conextRef.get() != null) {
                Toast.makeText((Context) this.conextRef.get(), R.string.plugin_loading_fail, 1).show();
            }
        }

        public void onDownloadSuccess(PluginRecord pluginRecord) {
            super.onDownloadSuccess(pluginRecord);
            this.mXQProgressHorizontalDialog.dismiss();
        }
    }

    public String getLiveCameraLastPicture(Context context, LiveCameraDevice liveCameraDevice) {
        String str = context.getDir(StatUtil.b, 0).getAbsolutePath() + "/" + liveCameraDevice.did + ".jpg";
        if (new File(str).exists()) {
            return str;
        }
        return null;
    }

    public void setLiveCameraView(SimpleDraweeView simpleDraweeView, LiveCameraDevice liveCameraDevice) {
        String liveCameraLastPicture = getLiveCameraLastPicture(simpleDraweeView.getContext(), liveCameraDevice);
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.camera_bg)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
        if (liveCameraLastPicture != null) {
            simpleDraweeView.setImageURI(Uri.parse("file://" + liveCameraLastPicture));
        } else if (TextUtils.isEmpty(liveCameraDevice.mThumbNailUrl)) {
            simpleDraweeView.setImageURI(SystemUtils.a(R.drawable.camera_bg));
        } else {
            simpleDraweeView.setImageURI(Uri.parse(liveCameraDevice.mThumbNailUrl));
        }
    }
}
