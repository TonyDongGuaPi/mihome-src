package com.alipay.zoloz.hardware.camera.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.alipay.android.phone.a.a.a;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.utils.DisplayUtil;
import com.alipay.mobile.security.faceauth.circle.protocol.DeviceSetting;
import com.alipay.zoloz.hardware.camera.b;
import com.alipay.zoloz.hardware.camera.c;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    static c mCameraInterface;

    /* renamed from: a  reason: collision with root package name */
    private DeviceSetting f1201a;
    b mCameraCallback;
    Context mContext;
    float mPreviewRate = DisplayUtil.getScreenRate(this.mContext);
    SurfaceHolder mSurfaceHolder = getHolder();

    public CameraSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context.getApplicationContext();
        this.mSurfaceHolder.setFormat(-2);
        this.mSurfaceHolder.setType(3);
        this.mSurfaceHolder.addCallback(this);
    }

    public void init(DeviceSetting[] deviceSettingArr) {
        this.f1201a = com.alipay.zoloz.hardware.camera.a.b.a(deviceSettingArr);
        getCameraImpl(this.mContext);
        if (mCameraInterface != null) {
            mCameraInterface.initCamera(this.f1201a);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        BioLog.i("surfaceCreated...");
        if (mCameraInterface != null) {
            mCameraInterface.setCallback(this.mCameraCallback);
        }
        try {
            if (mCameraInterface != null) {
                mCameraInterface.startCamera();
            }
            if (this.mCameraCallback != null) {
                this.mCameraCallback.onSurfaceCreated();
            }
        } catch (Exception unused) {
            if (this.mCameraCallback != null) {
                this.mCameraCallback.onError(-1);
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        BioLog.i("surfaceChanged...");
        if (mCameraInterface != null) {
            mCameraInterface.startPreview(this.mSurfaceHolder, this.mPreviewRate, i2, i3);
            if (this.mCameraCallback != null) {
                int cameraViewRotation = mCameraInterface.getCameraViewRotation();
                if (cameraViewRotation == 90 || cameraViewRotation == 270) {
                    i2 = mCameraInterface.getPreviewHeight();
                    i3 = mCameraInterface.getPreviewWidth();
                } else if (cameraViewRotation == 0 || cameraViewRotation == 180) {
                    i2 = mCameraInterface.getPreviewWidth();
                    i3 = mCameraInterface.getPreviewHeight();
                }
                this.mCameraCallback.onSurfaceChanged((double) i2, (double) i3);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        BioLog.i("surfaceDestroyed...");
        if (mCameraInterface != null) {
            mCameraInterface.stopCamera();
            mCameraInterface.setCallback((b) null);
        }
        if (this.mCameraCallback != null) {
            this.mCameraCallback.onSurfaceDestroyed();
        }
    }

    public static c getCameraImpl(Context context) {
        if (mCameraInterface == null) {
            try {
                mCameraInterface = (c) Class.forName("com.alipay.zoloz.hardware.camera.impl.AndroidImpl").getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
        return mCameraInterface;
    }

    public static String getCameraName() {
        try {
            Field field = a.class.getField("a");
            field.setAccessible(true);
            return (String) field.get(a.class);
        } catch (NoSuchFieldException e) {
            BioLog.w((Throwable) e);
            return a.f813a;
        } catch (IllegalAccessException e2) {
            BioLog.w((Throwable) e2);
            return a.f813a;
        }
    }

    public SurfaceHolder getSurfaceHolder() {
        return this.mSurfaceHolder;
    }

    public void setCameraCallback(b bVar) {
        this.mCameraCallback = bVar;
    }
}
