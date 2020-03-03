package com.xiaomi.smarthome.miio.camera;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.miio.camera.IQueryCameraThumbService;
import java.io.File;

public class QueryCameraThumbService extends Service {

    public class MyBinder extends IQueryCameraThumbService.Stub {
        public MyBinder() {
        }

        public Bitmap queryCameraThumb(String str) throws RemoteException {
            try {
                File fileStreamPath = QueryCameraThumbService.this.getFileStreamPath(str.toLowerCase().replace(Operators.CONDITION_IF_MIDDLE, '_'));
                if (fileStreamPath.exists()) {
                    return BitmapFactory.decodeFile(fileStreamPath.getAbsolutePath());
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void onCreate() {
        super.onCreate();
    }
}
