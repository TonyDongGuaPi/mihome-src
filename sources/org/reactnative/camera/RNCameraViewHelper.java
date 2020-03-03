package org.reactnative.camera;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.CamcorderProfile;
import android.os.Build;
import android.support.media.ExifInterface;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.mi.mistatistic.sdk.data.EventData;
import com.xiaomi.zxing.Result;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.events.BarCodeReadEvent;
import org.reactnative.camera.events.BarcodeDetectionErrorEvent;
import org.reactnative.camera.events.BarcodesDetectedEvent;
import org.reactnative.camera.events.CameraMountErrorEvent;
import org.reactnative.camera.events.CameraReadyEvent;
import org.reactnative.camera.events.FaceDetectionErrorEvent;
import org.reactnative.camera.events.FacesDetectedEvent;
import org.reactnative.camera.events.PictureSavedEvent;
import org.reactnative.camera.events.PictureTakenEvent;
import org.reactnative.camera.events.TextRecognizedEvent;
import org.reactnative.facedetector.RNFaceDetector;

public class RNCameraViewHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String[][] f4135a = {new String[]{EventData.b, ExifInterface.TAG_ARTIST}, new String[]{"int", ExifInterface.TAG_BITS_PER_SAMPLE}, new String[]{"int", ExifInterface.TAG_COMPRESSION}, new String[]{EventData.b, ExifInterface.TAG_COPYRIGHT}, new String[]{EventData.b, ExifInterface.TAG_DATETIME}, new String[]{EventData.b, ExifInterface.TAG_IMAGE_DESCRIPTION}, new String[]{"int", ExifInterface.TAG_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_IMAGE_WIDTH}, new String[]{"int", ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT}, new String[]{"int", ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH}, new String[]{EventData.b, ExifInterface.TAG_MAKE}, new String[]{EventData.b, ExifInterface.TAG_MODEL}, new String[]{"int", ExifInterface.TAG_ORIENTATION}, new String[]{"int", ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION}, new String[]{"int", ExifInterface.TAG_PLANAR_CONFIGURATION}, new String[]{"double", ExifInterface.TAG_PRIMARY_CHROMATICITIES}, new String[]{"double", ExifInterface.TAG_REFERENCE_BLACK_WHITE}, new String[]{"int", ExifInterface.TAG_RESOLUTION_UNIT}, new String[]{"int", ExifInterface.TAG_ROWS_PER_STRIP}, new String[]{"int", ExifInterface.TAG_SAMPLES_PER_PIXEL}, new String[]{EventData.b, ExifInterface.TAG_SOFTWARE}, new String[]{"int", ExifInterface.TAG_STRIP_BYTE_COUNTS}, new String[]{"int", ExifInterface.TAG_STRIP_OFFSETS}, new String[]{"int", ExifInterface.TAG_TRANSFER_FUNCTION}, new String[]{"double", ExifInterface.TAG_WHITE_POINT}, new String[]{"double", ExifInterface.TAG_X_RESOLUTION}, new String[]{"double", ExifInterface.TAG_Y_CB_CR_COEFFICIENTS}, new String[]{"int", ExifInterface.TAG_Y_CB_CR_POSITIONING}, new String[]{"int", ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING}, new String[]{"double", ExifInterface.TAG_Y_RESOLUTION}, new String[]{"double", ExifInterface.TAG_APERTURE_VALUE}, new String[]{"double", ExifInterface.TAG_BRIGHTNESS_VALUE}, new String[]{EventData.b, ExifInterface.TAG_CFA_PATTERN}, new String[]{"int", ExifInterface.TAG_COLOR_SPACE}, new String[]{EventData.b, ExifInterface.TAG_COMPONENTS_CONFIGURATION}, new String[]{"double", ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL}, new String[]{"int", ExifInterface.TAG_CONTRAST}, new String[]{"int", ExifInterface.TAG_CUSTOM_RENDERED}, new String[]{EventData.b, ExifInterface.TAG_DATETIME_DIGITIZED}, new String[]{EventData.b, ExifInterface.TAG_DATETIME_ORIGINAL}, new String[]{EventData.b, ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION}, new String[]{"double", ExifInterface.TAG_DIGITAL_ZOOM_RATIO}, new String[]{EventData.b, ExifInterface.TAG_EXIF_VERSION}, new String[]{"double", ExifInterface.TAG_EXPOSURE_BIAS_VALUE}, new String[]{"double", ExifInterface.TAG_EXPOSURE_INDEX}, new String[]{"int", ExifInterface.TAG_EXPOSURE_MODE}, new String[]{"int", ExifInterface.TAG_EXPOSURE_PROGRAM}, new String[]{"double", ExifInterface.TAG_EXPOSURE_TIME}, new String[]{"double", ExifInterface.TAG_F_NUMBER}, new String[]{EventData.b, ExifInterface.TAG_FILE_SOURCE}, new String[]{"int", ExifInterface.TAG_FLASH}, new String[]{"double", ExifInterface.TAG_FLASH_ENERGY}, new String[]{EventData.b, ExifInterface.TAG_FLASHPIX_VERSION}, new String[]{"double", ExifInterface.TAG_FOCAL_LENGTH}, new String[]{"int", ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM}, new String[]{"int", ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT}, new String[]{"double", ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION}, new String[]{"double", ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION}, new String[]{"int", ExifInterface.TAG_GAIN_CONTROL}, new String[]{"int", ExifInterface.TAG_ISO_SPEED_RATINGS}, new String[]{EventData.b, ExifInterface.TAG_IMAGE_UNIQUE_ID}, new String[]{"int", ExifInterface.TAG_LIGHT_SOURCE}, new String[]{EventData.b, ExifInterface.TAG_MAKER_NOTE}, new String[]{"double", ExifInterface.TAG_MAX_APERTURE_VALUE}, new String[]{"int", ExifInterface.TAG_METERING_MODE}, new String[]{"int", ExifInterface.TAG_NEW_SUBFILE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_OECF}, new String[]{"int", ExifInterface.TAG_PIXEL_X_DIMENSION}, new String[]{"int", ExifInterface.TAG_PIXEL_Y_DIMENSION}, new String[]{EventData.b, ExifInterface.TAG_RELATED_SOUND_FILE}, new String[]{"int", ExifInterface.TAG_SATURATION}, new String[]{"int", ExifInterface.TAG_SCENE_CAPTURE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_SCENE_TYPE}, new String[]{"int", ExifInterface.TAG_SENSING_METHOD}, new String[]{"int", ExifInterface.TAG_SHARPNESS}, new String[]{"double", ExifInterface.TAG_SHUTTER_SPEED_VALUE}, new String[]{EventData.b, ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE}, new String[]{EventData.b, ExifInterface.TAG_SPECTRAL_SENSITIVITY}, new String[]{"int", ExifInterface.TAG_SUBFILE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL}, new String[]{"int", ExifInterface.TAG_SUBJECT_AREA}, new String[]{"double", ExifInterface.TAG_SUBJECT_DISTANCE}, new String[]{"int", ExifInterface.TAG_SUBJECT_DISTANCE_RANGE}, new String[]{"int", ExifInterface.TAG_SUBJECT_LOCATION}, new String[]{EventData.b, ExifInterface.TAG_USER_COMMENT}, new String[]{"int", ExifInterface.TAG_WHITE_BALANCE}, new String[]{"int", ExifInterface.TAG_GPS_ALTITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_AREA_INFORMATION}, new String[]{"double", ExifInterface.TAG_GPS_DOP}, new String[]{EventData.b, ExifInterface.TAG_GPS_DATESTAMP}, new String[]{"double", ExifInterface.TAG_GPS_DEST_BEARING}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_BEARING_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_DISTANCE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_DISTANCE_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_LATITUDE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_LATITUDE_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_LONGITUDE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_LONGITUDE_REF}, new String[]{"int", ExifInterface.TAG_GPS_DIFFERENTIAL}, new String[]{"double", ExifInterface.TAG_GPS_IMG_DIRECTION}, new String[]{EventData.b, ExifInterface.TAG_GPS_IMG_DIRECTION_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_LATITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_LONGITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_MAP_DATUM}, new String[]{EventData.b, ExifInterface.TAG_GPS_MEASURE_MODE}, new String[]{EventData.b, ExifInterface.TAG_GPS_PROCESSING_METHOD}, new String[]{EventData.b, ExifInterface.TAG_GPS_SATELLITES}, new String[]{"double", ExifInterface.TAG_GPS_SPEED}, new String[]{EventData.b, ExifInterface.TAG_GPS_SPEED_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_STATUS}, new String[]{EventData.b, ExifInterface.TAG_GPS_TIMESTAMP}, new String[]{"double", ExifInterface.TAG_GPS_TRACK}, new String[]{EventData.b, ExifInterface.TAG_GPS_TRACK_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_VERSION_ID}, new String[]{EventData.b, ExifInterface.TAG_INTEROPERABILITY_INDEX}, new String[]{"int", ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH}, new String[]{"int", ExifInterface.TAG_DNG_VERSION}, new String[]{"int", ExifInterface.TAG_DEFAULT_CROP_SIZE}, new String[]{"int", ExifInterface.TAG_ORF_PREVIEW_IMAGE_START}, new String[]{"int", ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_ORF_ASPECT_FRAME}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_TOP_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_ISO}};

    private static boolean b(int i) {
        return i == 90 || i == 270;
    }

    public static void a(final ViewGroup viewGroup, final String str) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraMountErrorEvent.a(viewGroup.getId(), str));
            }
        });
    }

    public static void a(final ViewGroup viewGroup) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraReadyEvent.a(viewGroup.getId()));
            }
        });
    }

    public static void a(final ViewGroup viewGroup, final WritableMap writableMap) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(PictureSavedEvent.a(viewGroup.getId(), writableMap));
            }
        });
    }

    public static void b(final ViewGroup viewGroup) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(PictureTakenEvent.a(viewGroup.getId()));
            }
        });
    }

    public static void a(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FacesDetectedEvent.a(viewGroup.getId(), writableArray));
            }
        });
    }

    public static void a(final ViewGroup viewGroup, final RNFaceDetector rNFaceDetector) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FaceDetectionErrorEvent.a(viewGroup.getId(), rNFaceDetector));
            }
        });
    }

    public static void b(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarcodesDetectedEvent.a(viewGroup.getId(), writableArray));
            }
        });
    }

    public static void a(final ViewGroup viewGroup, final RNBarcodeDetector rNBarcodeDetector) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarcodeDetectionErrorEvent.a(viewGroup.getId(), rNBarcodeDetector));
            }
        });
    }

    public static void a(ViewGroup viewGroup, Result result, int i, int i2) {
        ReactContext reactContext = (ReactContext) viewGroup.getContext();
        final ViewGroup viewGroup2 = viewGroup;
        final Result result2 = result;
        final int i3 = i;
        final int i4 = i2;
        final ReactContext reactContext2 = reactContext;
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext2.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarCodeReadEvent.a(viewGroup2.getId(), result2, i3, i4));
            }
        });
    }

    public static void c(final ViewGroup viewGroup, final WritableArray writableArray) {
        final ReactContext reactContext = (ReactContext) viewGroup.getContext();
        reactContext.runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(TextRecognizedEvent.a(viewGroup.getId(), writableArray));
            }
        });
    }

    public static int a(int i, int i2, int i3) {
        if (i2 == 1) {
            return (i3 + i) % 360;
        }
        return ((i3 - i) + (b(i) ? 180 : 0)) % 360;
    }

    private static int c(int i) {
        switch (i) {
            case 0:
                return Build.VERSION.SDK_INT >= 21 ? 8 : 6;
            case 1:
                return 6;
            case 2:
                return 5;
            case 3:
                return 4;
            case 4:
                return 4;
            default:
                return 1;
        }
    }

    public static CamcorderProfile a(int i) {
        CamcorderProfile camcorderProfile = CamcorderProfile.get(1);
        int c = c(i);
        if (CamcorderProfile.hasProfile(c)) {
            camcorderProfile = CamcorderProfile.get(c);
            if (i == 4) {
                camcorderProfile.videoFrameWidth = 640;
            }
        }
        return camcorderProfile;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r8.equals("int") == false) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0068 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.react.bridge.WritableMap a(android.support.media.ExifInterface r13) {
        /*
            com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String[][] r1 = f4135a
            int r2 = r1.length
            r3 = 0
            r4 = 0
        L_0x0009:
            r5 = 0
            r7 = 1
            if (r4 >= r2) goto L_0x006b
            r8 = r1[r4]
            r9 = r8[r7]
            java.lang.String r10 = r13.getAttribute(r9)
            if (r10 == 0) goto L_0x0068
            r8 = r8[r3]
            r10 = -1
            int r11 = r8.hashCode()
            r12 = -1325958191(0xffffffffb0f77bd1, float:-1.8006806E-9)
            if (r11 == r12) goto L_0x0042
            r12 = -891985903(0xffffffffcad56011, float:-6991880.5)
            if (r11 == r12) goto L_0x0038
            r12 = 104431(0x197ef, float:1.46339E-40)
            if (r11 == r12) goto L_0x002f
            goto L_0x004c
        L_0x002f:
            java.lang.String r11 = "int"
            boolean r8 = r8.equals(r11)
            if (r8 == 0) goto L_0x004c
            goto L_0x004d
        L_0x0038:
            java.lang.String r7 = "string"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x004c
            r7 = 0
            goto L_0x004d
        L_0x0042:
            java.lang.String r7 = "double"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x004c
            r7 = 2
            goto L_0x004d
        L_0x004c:
            r7 = -1
        L_0x004d:
            switch(r7) {
                case 0: goto L_0x0061;
                case 1: goto L_0x0059;
                case 2: goto L_0x0051;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x0068
        L_0x0051:
            double r5 = r13.getAttributeDouble(r9, r5)
            r0.putDouble(r9, r5)
            goto L_0x0068
        L_0x0059:
            int r5 = r13.getAttributeInt(r9, r3)
            r0.putInt(r9, r5)
            goto L_0x0068
        L_0x0061:
            java.lang.String r5 = r13.getAttribute(r9)
            r0.putString(r9, r5)
        L_0x0068:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x006b:
            double[] r1 = r13.getLatLong()
            if (r1 == 0) goto L_0x0088
            java.lang.String r2 = "GPSLatitude"
            r3 = r1[r3]
            r0.putDouble(r2, r3)
            java.lang.String r2 = "GPSLongitude"
            r3 = r1[r7]
            r0.putDouble(r2, r3)
            java.lang.String r1 = "GPSAltitude"
            double r2 = r13.getAltitude(r5)
            r0.putDouble(r1, r2)
        L_0x0088:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.RNCameraViewHelper.a(android.support.media.ExifInterface):com.facebook.react.bridge.WritableMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0071 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.support.media.ExifInterface r10, com.facebook.react.bridge.ReadableMap r11) {
        /*
            java.lang.String[][] r0 = f4135a
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x0005:
            if (r3 >= r1) goto L_0x0074
            r4 = r0[r3]
            r5 = 1
            r6 = r4[r5]
            boolean r7 = r11.hasKey(r6)
            if (r7 == 0) goto L_0x0071
            r4 = r4[r2]
            r7 = -1
            int r8 = r4.hashCode()
            r9 = -1325958191(0xffffffffb0f77bd1, float:-1.8006806E-9)
            if (r8 == r9) goto L_0x003d
            r9 = -891985903(0xffffffffcad56011, float:-6991880.5)
            if (r8 == r9) goto L_0x0033
            r9 = 104431(0x197ef, float:1.46339E-40)
            if (r8 == r9) goto L_0x0029
            goto L_0x0047
        L_0x0029:
            java.lang.String r8 = "int"
            boolean r4 = r4.equals(r8)
            if (r4 == 0) goto L_0x0047
            r4 = 1
            goto L_0x0048
        L_0x0033:
            java.lang.String r5 = "string"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0047
            r4 = 0
            goto L_0x0048
        L_0x003d:
            java.lang.String r5 = "double"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0047
            r4 = 2
            goto L_0x0048
        L_0x0047:
            r4 = -1
        L_0x0048:
            switch(r4) {
                case 0: goto L_0x006a;
                case 1: goto L_0x005b;
                case 2: goto L_0x004c;
                default: goto L_0x004b;
            }
        L_0x004b:
            goto L_0x0071
        L_0x004c:
            double r4 = r11.getDouble(r6)
            java.lang.String r4 = java.lang.Double.toString(r4)
            r10.setAttribute(r6, r4)
            r11.getDouble(r6)
            goto L_0x0071
        L_0x005b:
            int r4 = r11.getInt(r6)
            java.lang.String r4 = java.lang.Integer.toString(r4)
            r10.setAttribute(r6, r4)
            r11.getInt(r6)
            goto L_0x0071
        L_0x006a:
            java.lang.String r4 = r11.getString(r6)
            r10.setAttribute(r6, r4)
        L_0x0071:
            int r3 = r3 + 1
            goto L_0x0005
        L_0x0074:
            java.lang.String r0 = "GPSLatitude"
            boolean r0 = r11.hasKey(r0)
            if (r0 == 0) goto L_0x0093
            java.lang.String r0 = "GPSLongitude"
            boolean r0 = r11.hasKey(r0)
            if (r0 == 0) goto L_0x0093
            java.lang.String r0 = "GPSLatitude"
            double r0 = r11.getDouble(r0)
            java.lang.String r2 = "GPSLongitude"
            double r2 = r11.getDouble(r2)
            r10.setLatLong(r0, r2)
        L_0x0093:
            java.lang.String r0 = "GPSAltitude"
            boolean r0 = r11.hasKey(r0)
            if (r0 == 0) goto L_0x00a4
            java.lang.String r0 = "GPSAltitude"
            double r0 = r11.getDouble(r0)
            r10.setAltitude(r0)
        L_0x00a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.RNCameraViewHelper.a(android.support.media.ExifInterface, com.facebook.react.bridge.ReadableMap):void");
    }

    public static void b(ExifInterface exifInterface) {
        for (String[] strArr : f4135a) {
            exifInterface.setAttribute(strArr[1], (String) null);
        }
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, (String) null);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, (String) null);
        exifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, (String) null);
    }

    public static Bitmap a(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(-16777216);
        float f = (float) i;
        float f2 = (float) i2;
        canvas.drawRect(0.0f, 0.0f, f, f2, paint);
        Paint paint2 = new Paint();
        paint2.setColor(-256);
        paint2.setTextSize(35.0f);
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G '->' HH:mm:ss z");
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.1f * f, f2 * 0.2f, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.2f * f, f2 * 0.4f, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), 0.3f * f, 0.6f * f2, paint2);
        canvas.drawText(simpleDateFormat.format(instance.getTime()), f * 0.4f, f2 * 0.8f, paint2);
        return createBitmap;
    }
}
