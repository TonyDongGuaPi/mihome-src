package com.reactnative.camera;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.CamcorderProfile;
import android.os.Build;
import android.support.media.ExifInterface;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.android.gms.vision.face.Face;
import com.google.zxing.Result;
import com.mi.mistatistic.sdk.data.EventData;
import com.reactnative.camera.events.BarCodeReadEvent;
import com.reactnative.camera.events.CameraMountErrorEvent;
import com.reactnative.camera.events.CameraReadyEvent;
import com.reactnative.camera.events.FaceDetectionErrorEvent;
import com.reactnative.camera.events.FacesDetectedEvent;
import com.reactnative.camera.facedetector.RNFaceDetector;
import com.reactnative.camera.utils.ImageDimensions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RNCameraViewHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String[][] f8669a = {new String[]{EventData.b, ExifInterface.TAG_ARTIST}, new String[]{"int", ExifInterface.TAG_BITS_PER_SAMPLE}, new String[]{"int", ExifInterface.TAG_COMPRESSION}, new String[]{EventData.b, ExifInterface.TAG_COPYRIGHT}, new String[]{EventData.b, ExifInterface.TAG_DATETIME}, new String[]{EventData.b, ExifInterface.TAG_IMAGE_DESCRIPTION}, new String[]{"int", ExifInterface.TAG_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_IMAGE_WIDTH}, new String[]{"int", ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT}, new String[]{"int", ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH}, new String[]{EventData.b, ExifInterface.TAG_MAKE}, new String[]{EventData.b, ExifInterface.TAG_MODEL}, new String[]{"int", ExifInterface.TAG_ORIENTATION}, new String[]{"int", ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION}, new String[]{"int", ExifInterface.TAG_PLANAR_CONFIGURATION}, new String[]{"double", ExifInterface.TAG_PRIMARY_CHROMATICITIES}, new String[]{"double", ExifInterface.TAG_REFERENCE_BLACK_WHITE}, new String[]{"int", ExifInterface.TAG_RESOLUTION_UNIT}, new String[]{"int", ExifInterface.TAG_ROWS_PER_STRIP}, new String[]{"int", ExifInterface.TAG_SAMPLES_PER_PIXEL}, new String[]{EventData.b, ExifInterface.TAG_SOFTWARE}, new String[]{"int", ExifInterface.TAG_STRIP_BYTE_COUNTS}, new String[]{"int", ExifInterface.TAG_STRIP_OFFSETS}, new String[]{"int", ExifInterface.TAG_TRANSFER_FUNCTION}, new String[]{"double", ExifInterface.TAG_WHITE_POINT}, new String[]{"double", ExifInterface.TAG_X_RESOLUTION}, new String[]{"double", ExifInterface.TAG_Y_CB_CR_COEFFICIENTS}, new String[]{"int", ExifInterface.TAG_Y_CB_CR_POSITIONING}, new String[]{"int", ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING}, new String[]{"double", ExifInterface.TAG_Y_RESOLUTION}, new String[]{"double", ExifInterface.TAG_APERTURE_VALUE}, new String[]{"double", ExifInterface.TAG_BRIGHTNESS_VALUE}, new String[]{EventData.b, ExifInterface.TAG_CFA_PATTERN}, new String[]{"int", ExifInterface.TAG_COLOR_SPACE}, new String[]{EventData.b, ExifInterface.TAG_COMPONENTS_CONFIGURATION}, new String[]{"double", ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL}, new String[]{"int", ExifInterface.TAG_CONTRAST}, new String[]{"int", ExifInterface.TAG_CUSTOM_RENDERED}, new String[]{EventData.b, ExifInterface.TAG_DATETIME_DIGITIZED}, new String[]{EventData.b, ExifInterface.TAG_DATETIME_ORIGINAL}, new String[]{EventData.b, ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION}, new String[]{"double", ExifInterface.TAG_DIGITAL_ZOOM_RATIO}, new String[]{EventData.b, ExifInterface.TAG_EXIF_VERSION}, new String[]{"double", ExifInterface.TAG_EXPOSURE_BIAS_VALUE}, new String[]{"double", ExifInterface.TAG_EXPOSURE_INDEX}, new String[]{"int", ExifInterface.TAG_EXPOSURE_MODE}, new String[]{"int", ExifInterface.TAG_EXPOSURE_PROGRAM}, new String[]{"double", ExifInterface.TAG_EXPOSURE_TIME}, new String[]{"double", ExifInterface.TAG_F_NUMBER}, new String[]{EventData.b, ExifInterface.TAG_FILE_SOURCE}, new String[]{"int", ExifInterface.TAG_FLASH}, new String[]{"double", ExifInterface.TAG_FLASH_ENERGY}, new String[]{EventData.b, ExifInterface.TAG_FLASHPIX_VERSION}, new String[]{"double", ExifInterface.TAG_FOCAL_LENGTH}, new String[]{"int", ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM}, new String[]{"int", ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT}, new String[]{"double", ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION}, new String[]{"double", ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION}, new String[]{"int", ExifInterface.TAG_GAIN_CONTROL}, new String[]{"int", ExifInterface.TAG_ISO_SPEED_RATINGS}, new String[]{EventData.b, ExifInterface.TAG_IMAGE_UNIQUE_ID}, new String[]{"int", ExifInterface.TAG_LIGHT_SOURCE}, new String[]{EventData.b, ExifInterface.TAG_MAKER_NOTE}, new String[]{"double", ExifInterface.TAG_MAX_APERTURE_VALUE}, new String[]{"int", ExifInterface.TAG_METERING_MODE}, new String[]{"int", ExifInterface.TAG_NEW_SUBFILE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_OECF}, new String[]{"int", ExifInterface.TAG_PIXEL_X_DIMENSION}, new String[]{"int", ExifInterface.TAG_PIXEL_Y_DIMENSION}, new String[]{EventData.b, ExifInterface.TAG_RELATED_SOUND_FILE}, new String[]{"int", ExifInterface.TAG_SATURATION}, new String[]{"int", ExifInterface.TAG_SCENE_CAPTURE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_SCENE_TYPE}, new String[]{"int", ExifInterface.TAG_SENSING_METHOD}, new String[]{"int", ExifInterface.TAG_SHARPNESS}, new String[]{"double", ExifInterface.TAG_SHUTTER_SPEED_VALUE}, new String[]{EventData.b, ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE}, new String[]{EventData.b, ExifInterface.TAG_SPECTRAL_SENSITIVITY}, new String[]{"int", ExifInterface.TAG_SUBFILE_TYPE}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED}, new String[]{EventData.b, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL}, new String[]{"int", ExifInterface.TAG_SUBJECT_AREA}, new String[]{"double", ExifInterface.TAG_SUBJECT_DISTANCE}, new String[]{"int", ExifInterface.TAG_SUBJECT_DISTANCE_RANGE}, new String[]{"int", ExifInterface.TAG_SUBJECT_LOCATION}, new String[]{EventData.b, ExifInterface.TAG_USER_COMMENT}, new String[]{"int", ExifInterface.TAG_WHITE_BALANCE}, new String[]{"int", ExifInterface.TAG_GPS_ALTITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_AREA_INFORMATION}, new String[]{"double", ExifInterface.TAG_GPS_DOP}, new String[]{EventData.b, ExifInterface.TAG_GPS_DATESTAMP}, new String[]{"double", ExifInterface.TAG_GPS_DEST_BEARING}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_BEARING_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_DISTANCE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_DISTANCE_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_LATITUDE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_LATITUDE_REF}, new String[]{"double", ExifInterface.TAG_GPS_DEST_LONGITUDE}, new String[]{EventData.b, ExifInterface.TAG_GPS_DEST_LONGITUDE_REF}, new String[]{"int", ExifInterface.TAG_GPS_DIFFERENTIAL}, new String[]{"double", ExifInterface.TAG_GPS_IMG_DIRECTION}, new String[]{EventData.b, ExifInterface.TAG_GPS_IMG_DIRECTION_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_LATITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_LONGITUDE_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_MAP_DATUM}, new String[]{EventData.b, ExifInterface.TAG_GPS_MEASURE_MODE}, new String[]{EventData.b, ExifInterface.TAG_GPS_PROCESSING_METHOD}, new String[]{EventData.b, ExifInterface.TAG_GPS_SATELLITES}, new String[]{"double", ExifInterface.TAG_GPS_SPEED}, new String[]{EventData.b, ExifInterface.TAG_GPS_SPEED_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_STATUS}, new String[]{EventData.b, ExifInterface.TAG_GPS_TIMESTAMP}, new String[]{"double", ExifInterface.TAG_GPS_TRACK}, new String[]{EventData.b, ExifInterface.TAG_GPS_TRACK_REF}, new String[]{EventData.b, ExifInterface.TAG_GPS_VERSION_ID}, new String[]{EventData.b, ExifInterface.TAG_INTEROPERABILITY_INDEX}, new String[]{"int", ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH}, new String[]{"int", ExifInterface.TAG_DNG_VERSION}, new String[]{"int", ExifInterface.TAG_DEFAULT_CROP_SIZE}, new String[]{"int", ExifInterface.TAG_ORF_PREVIEW_IMAGE_START}, new String[]{"int", ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH}, new String[]{"int", ExifInterface.TAG_ORF_ASPECT_FRAME}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_SENSOR_TOP_BORDER}, new String[]{"int", ExifInterface.TAG_RW2_ISO}};

    public static void a(ViewGroup viewGroup) {
        ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraMountErrorEvent.a(viewGroup.getId()));
    }

    public static void b(ViewGroup viewGroup) {
        ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(CameraReadyEvent.a(viewGroup.getId()));
    }

    public static void a(ViewGroup viewGroup, SparseArray<Face> sparseArray, ImageDimensions imageDimensions) {
        float f = viewGroup.getResources().getDisplayMetrics().density;
        double width = (double) viewGroup.getWidth();
        double b = (double) (((float) imageDimensions.b()) * f);
        Double.isNaN(width);
        Double.isNaN(b);
        double d = width / b;
        double height = (double) viewGroup.getHeight();
        double c = (double) (((float) imageDimensions.c()) * f);
        Double.isNaN(height);
        Double.isNaN(c);
        ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FacesDetectedEvent.a(viewGroup.getId(), sparseArray, imageDimensions, d, height / c));
    }

    public static void a(ViewGroup viewGroup, RNFaceDetector rNFaceDetector) {
        ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(FaceDetectionErrorEvent.a(viewGroup.getId(), rNFaceDetector));
    }

    public static void a(ViewGroup viewGroup, Result result) {
        ((UIManagerModule) ((ReactContext) viewGroup.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(BarCodeReadEvent.a(viewGroup.getId(), result));
    }

    public static int a(int i, int i2) {
        if (i2 == 1) {
            return ((i - 90) + 360) % 360;
        }
        return (((-i) + 90) + 360) % 360;
    }

    public static CamcorderProfile a(int i) {
        CamcorderProfile camcorderProfile = CamcorderProfile.get(1);
        switch (i) {
            case 0:
                return Build.VERSION.SDK_INT >= 21 ? CamcorderProfile.get(8) : camcorderProfile;
            case 1:
                return CamcorderProfile.get(6);
            case 2:
                return CamcorderProfile.get(5);
            case 3:
                return CamcorderProfile.get(4);
            case 4:
                CamcorderProfile camcorderProfile2 = CamcorderProfile.get(4);
                camcorderProfile2.videoFrameWidth = 640;
                return camcorderProfile2;
            default:
                return camcorderProfile;
        }
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
            java.lang.String[][] r1 = f8669a
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
        throw new UnsupportedOperationException("Method not decompiled: com.reactnative.camera.RNCameraViewHelper.a(android.support.media.ExifInterface):com.facebook.react.bridge.WritableMap");
    }

    public static Bitmap b(int i, int i2) {
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
        canvas.drawText(new SimpleDateFormat("dd.MM.YY HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime()), f * 0.1f, f2 * 0.9f, paint2);
        return createBitmap;
    }
}
