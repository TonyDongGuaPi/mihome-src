package com.reactnative.camera.RCTCamera;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.util.Log;
import com.tiqiaa.constant.KeyType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCTCamera {

    /* renamed from: a  reason: collision with root package name */
    private static RCTCamera f8650a;
    private static final Resolution e = new Resolution(KeyType.SHUTTER_TWO, 480);
    private static final Resolution f = new Resolution(1280, 720);
    private static final Resolution g = new Resolution(1920, 1080);
    private final HashMap<Integer, CameraInfoWrapper> b = new HashMap<>();
    private final HashMap<Integer, Integer> c = new HashMap<>();
    private final Map<Number, Camera> d = new HashMap();
    private boolean h = false;
    private List<String> i = null;
    private int j = -1;
    private int k = 0;
    private int l = 0;

    public static RCTCamera a() {
        return f8650a;
    }

    public static void a(int i2) {
        f8650a = new RCTCamera(i2);
    }

    public synchronized Camera b(int i2) {
        if (this.d.get(Integer.valueOf(i2)) == null && this.c.get(Integer.valueOf(i2)) != null) {
            try {
                this.d.put(Integer.valueOf(i2), Camera.open(this.c.get(Integer.valueOf(i2)).intValue()));
                k(i2);
            } catch (Exception e2) {
                Log.e("RCTCamera", "acquireCameraInstance failed", e2);
            }
        }
        return this.d.get(Integer.valueOf(i2));
    }

    public void c(int i2) {
        Camera camera = this.d.get(Integer.valueOf(i2));
        if (camera != null) {
            this.d.remove(Integer.valueOf(i2));
            camera.release();
        }
    }

    public int d(int i2) {
        CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.c;
    }

    public int e(int i2) {
        CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.d;
    }

    public int f(int i2) {
        CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.f;
    }

    public int g(int i2) {
        CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
        if (cameraInfoWrapper == null) {
            return 0;
        }
        return cameraInfoWrapper.e;
    }

    public Camera.Size a(List<Camera.Size> list, int i2, int i3) {
        Camera.Size size = null;
        for (Camera.Size next : list) {
            if (next.width <= i2 && next.height <= i3) {
                if (size == null || next.width * next.height > size.width * size.height) {
                    size = next;
                }
            }
        }
        return size;
    }

    private Camera.Size b(List<Camera.Size> list) {
        Camera.Size size = null;
        for (Camera.Size next : list) {
            if (size == null || next.width * next.height < size.width * size.height) {
                size = next;
            }
        }
        return size;
    }

    private Camera.Size b(List<Camera.Size> list, int i2, int i3) {
        Camera.Size size = null;
        for (Camera.Size next : list) {
            if (size != null) {
                if (Math.sqrt(Math.pow((double) (next.width - i2), 2.0d) + Math.pow((double) (next.height - i3), 2.0d)) >= Math.sqrt(Math.pow((double) (size.width - i2), 2.0d) + Math.pow((double) (size.height - i3), 2.0d))) {
                }
            }
            size = next;
        }
        return size;
    }

    /* access modifiers changed from: protected */
    public List<Camera.Size> a(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedVideoSizes = parameters.getSupportedVideoSizes();
        if (supportedVideoSizes != null) {
            return supportedVideoSizes;
        }
        return parameters.getSupportedPreviewSizes();
    }

    public int b() {
        return this.j;
    }

    public void h(int i2) {
        if (this.j != i2) {
            this.j = i2;
            k(1);
            k(2);
        }
    }

    public boolean c() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public List<String> d() {
        return this.i;
    }

    public void a(List<String> list) {
        this.i = list;
    }

    public int e() {
        return this.k;
    }

    public void i(int i2) {
        this.l = i2;
    }

    public int f() {
        return this.l;
    }

    public void j(int i2) {
        this.k = i2;
        k(1);
        k(2);
    }

    public void a(int i2, int i3) {
        Camera camera = this.d.get(Integer.valueOf(i2));
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            boolean z = true;
            if (i3 != 1) {
                z = false;
            }
            parameters.setRecordingHint(z);
            camera.setParameters(parameters);
        }
    }

    public void a(int i2, String str) {
        Camera b2 = b(i2);
        if (b2 != null) {
            Camera.Parameters parameters = b2.getParameters();
            Camera.Size size = null;
            List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1078030475:
                    if (str.equals("medium")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -318184504:
                    if (str.equals("preview")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 107348:
                    if (str.equals("low")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 1604548:
                    if (str.equals("480p")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 1688155:
                    if (str.equals("720p")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 3202466:
                    if (str.equals("high")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 46737913:
                    if (str.equals("1080p")) {
                        c2 = 6;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    size = b(supportedPictureSizes);
                    break;
                case 1:
                    size = supportedPictureSizes.get(supportedPictureSizes.size() / 2);
                    break;
                case 2:
                    size = a(parameters.getSupportedPictureSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                    break;
                case 3:
                    Camera.Size a2 = a(parameters.getSupportedPreviewSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
                    size = b(parameters.getSupportedPictureSizes(), a2.width, a2.height);
                    break;
                case 4:
                    size = a(supportedPictureSizes, e.f8652a, e.b);
                    break;
                case 5:
                    size = a(supportedPictureSizes, f.f8652a, f.b);
                    break;
                case 6:
                    size = a(supportedPictureSizes, g.f8652a, g.b);
                    break;
            }
            if (size != null) {
                parameters.setPictureSize(size.width, size.height);
                b2.setParameters(parameters);
            }
        }
    }

    public CamcorderProfile b(int i2, String str) {
        Camera.Size size;
        CamcorderProfile camcorderProfile;
        Camera b2 = b(i2);
        if (b2 == null) {
            return null;
        }
        List<Camera.Size> a2 = a(b2);
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1078030475:
                if (str.equals("medium")) {
                    c2 = 1;
                    break;
                }
                break;
            case 107348:
                if (str.equals("low")) {
                    c2 = 0;
                    break;
                }
                break;
            case 1604548:
                if (str.equals("480p")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1688155:
                if (str.equals("720p")) {
                    c2 = 4;
                    break;
                }
                break;
            case 3202466:
                if (str.equals("high")) {
                    c2 = 2;
                    break;
                }
                break;
            case 46737913:
                if (str.equals("1080p")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                size = b(a2);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 4);
                break;
            case 1:
                size = a2.get(a2.size() / 2);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 5);
                break;
            case 2:
                size = a(a2, Integer.MAX_VALUE, Integer.MAX_VALUE);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 1);
                break;
            case 3:
                size = a(a2, e.f8652a, e.b);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 4);
                break;
            case 4:
                size = a(a2, f.f8652a, f.b);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 5);
                break;
            case 5:
                size = a(a2, g.f8652a, g.b);
                camcorderProfile = CamcorderProfile.get(this.c.get(Integer.valueOf(i2)).intValue(), 6);
                break;
            default:
                camcorderProfile = null;
                size = null;
                break;
        }
        if (camcorderProfile == null) {
            return null;
        }
        if (size != null) {
            camcorderProfile.videoFrameHeight = size.height;
            camcorderProfile.videoFrameWidth = size.width;
        }
        return camcorderProfile;
    }

    public void b(int i2, int i3) {
        Camera b2 = b(i2);
        if (b2 != null) {
            Camera.Parameters parameters = b2.getParameters();
            String flashMode = parameters.getFlashMode();
            switch (i3) {
                case 0:
                    flashMode = "off";
                    break;
                case 1:
                    flashMode = "torch";
                    break;
            }
            List<String> supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes != null && supportedFlashModes.contains(flashMode)) {
                parameters.setFlashMode(flashMode);
                b2.setParameters(parameters);
            }
        }
    }

    public void c(int i2, int i3) {
        Camera b2 = b(i2);
        if (b2 != null) {
            Camera.Parameters parameters = b2.getParameters();
            String flashMode = parameters.getFlashMode();
            switch (i3) {
                case 0:
                    flashMode = "off";
                    break;
                case 1:
                    flashMode = "on";
                    break;
                case 2:
                    flashMode = "auto";
                    break;
            }
            List<String> supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes != null && supportedFlashModes.contains(flashMode)) {
                parameters.setFlashMode(flashMode);
                b2.setParameters(parameters);
            }
        }
    }

    public void d(int i2, int i3) {
        Camera b2 = b(i2);
        if (b2 != null) {
            Camera.Parameters parameters = b2.getParameters();
            int maxZoom = parameters.getMaxZoom();
            if (parameters.isZoomSupported() && i3 >= 0 && i3 < maxZoom) {
                parameters.setZoom(i3);
                b2.setParameters(parameters);
            }
        }
    }

    public void e(int i2, int i3) {
        int i4;
        Camera camera = this.d.get(Integer.valueOf(i2));
        if (camera != null) {
            CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
            int i5 = cameraInfoWrapper.f8651a.orientation;
            if (cameraInfoWrapper.f8651a.facing == 1) {
                i4 = (i5 + (i3 * 90)) % 360;
            } else {
                i4 = ((i5 - (i3 * 90)) + 360) % 360;
            }
            cameraInfoWrapper.b = i4;
            Camera.Parameters parameters = camera.getParameters();
            parameters.setRotation(cameraInfoWrapper.b);
            try {
                camera.setParameters(parameters);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void k(int i2) {
        int i3;
        int i4;
        Camera camera = this.d.get(Integer.valueOf(i2));
        if (camera != null) {
            CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
            int i5 = cameraInfoWrapper.f8651a.orientation;
            if (cameraInfoWrapper.f8651a.facing == 1) {
                i3 = ((this.k * 90) + i5) % 360;
                i4 = ((720 - i5) - (this.k * 90)) % 360;
            } else {
                i3 = ((i5 - (this.k * 90)) + 360) % 360;
                i4 = i3;
            }
            cameraInfoWrapper.b = i3;
            i(i3);
            camera.setDisplayOrientation(i4);
            Camera.Parameters parameters = camera.getParameters();
            parameters.setRotation(cameraInfoWrapper.b);
            Camera.Size a2 = a(parameters.getSupportedPreviewSizes(), Integer.MAX_VALUE, Integer.MAX_VALUE);
            int i6 = a2.width;
            int i7 = a2.height;
            parameters.setPreviewSize(i6, i7);
            try {
                camera.setParameters(parameters);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (cameraInfoWrapper.b == 0 || cameraInfoWrapper.b == 180) {
                cameraInfoWrapper.c = i6;
                cameraInfoWrapper.d = i7;
                return;
            }
            cameraInfoWrapper.c = i7;
            cameraInfoWrapper.d = i6;
        }
    }

    public void a(int i2, int i3, int i4) {
        CameraInfoWrapper cameraInfoWrapper = this.b.get(Integer.valueOf(i2));
        if (cameraInfoWrapper != null) {
            cameraInfoWrapper.e = i3;
            cameraInfoWrapper.f = i4;
        }
    }

    private RCTCamera(int i2) {
        this.k = i2;
        for (int i3 = 0; i3 < Camera.getNumberOfCameras(); i3++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i3, cameraInfo);
            if (cameraInfo.facing == 1 && this.b.get(1) == null) {
                this.b.put(1, new CameraInfoWrapper(cameraInfo));
                this.c.put(1, Integer.valueOf(i3));
                b(1);
                c(1);
            } else if (cameraInfo.facing == 0 && this.b.get(2) == null) {
                this.b.put(2, new CameraInfoWrapper(cameraInfo));
                this.c.put(2, Integer.valueOf(i3));
                b(2);
                c(2);
            }
        }
    }

    private class CameraInfoWrapper {

        /* renamed from: a  reason: collision with root package name */
        public final Camera.CameraInfo f8651a;
        public int b = 0;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public int f = -1;

        public CameraInfoWrapper(Camera.CameraInfo cameraInfo) {
            this.f8651a = cameraInfo;
        }
    }

    private static class Resolution {

        /* renamed from: a  reason: collision with root package name */
        public int f8652a;
        public int b;

        public Resolution(int i, int i2) {
            this.f8652a = i;
            this.b = i2;
        }
    }
}
