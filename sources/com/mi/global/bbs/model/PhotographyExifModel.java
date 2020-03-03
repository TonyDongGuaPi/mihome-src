package com.mi.global.bbs.model;

import android.media.ExifInterface;
import android.text.TextUtils;
import java.io.IOException;

public class PhotographyExifModel extends BaseResult {
    public static final String EMPTY_STR = "-";
    private static final String SEPARATOR = ":::";
    String aperture;
    String date;
    String exposure_time;
    String focalLength;
    String iso;
    String manufacturer;
    String model;

    public String getManufacturer() {
        if (TextUtils.isEmpty(this.manufacturer)) {
            return "-";
        }
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getModel() {
        if (TextUtils.isEmpty(this.model)) {
            return "-";
        }
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getFocalLength() {
        if (TextUtils.isEmpty(this.focalLength)) {
            return "-";
        }
        return this.focalLength;
    }

    public void setFocalLength(String str) {
        this.focalLength = str;
    }

    public String getAperture() {
        if (TextUtils.isEmpty(this.aperture)) {
            return "-";
        }
        return this.aperture;
    }

    public void setAperture(String str) {
        this.aperture = str;
    }

    public String getExposure_time() {
        if (TextUtils.isEmpty(this.exposure_time)) {
            return "-";
        }
        return this.exposure_time;
    }

    public void setExposure_time(String str) {
        this.exposure_time = str;
    }

    public String getIso() {
        if (TextUtils.isEmpty(this.iso)) {
            return "-";
        }
        return this.iso;
    }

    public void setIso(String str) {
        this.iso = str;
    }

    public String getDate() {
        if (TextUtils.isEmpty(this.date)) {
            return "-";
        }
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public static PhotographyExifModel parseStringToExifModel(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(SEPARATOR);
        if (split.length < 7) {
            return null;
        }
        PhotographyExifModel photographyExifModel = new PhotographyExifModel();
        photographyExifModel.manufacturer = split[0];
        photographyExifModel.model = split[1];
        photographyExifModel.focalLength = split[2];
        photographyExifModel.aperture = split[3];
        photographyExifModel.exposure_time = split[4];
        photographyExifModel.iso = split[5];
        photographyExifModel.date = split[6];
        return photographyExifModel;
    }

    public static String setExifIntoString(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            ExifInterface exifInterface = new ExifInterface(str);
            String attribute = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_MAKE);
            String attribute2 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_MODEL);
            String attribute3 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_FOCAL_LENGTH);
            String attribute4 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_F_NUMBER);
            String attribute5 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_EXPOSURE_TIME);
            String attribute6 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_ISO_SPEED_RATINGS);
            String attribute7 = exifInterface.getAttribute(android.support.media.ExifInterface.TAG_DATETIME);
            if (TextUtils.isEmpty(attribute)) {
                attribute = "";
            }
            sb.append(attribute);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute2)) {
                attribute2 = "";
            }
            sb.append(attribute2);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute3)) {
                attribute3 = "";
            }
            sb.append(attribute3);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute4)) {
                attribute4 = "";
            }
            sb.append(attribute4);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute5)) {
                attribute5 = "";
            }
            sb.append(attribute5);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute6)) {
                attribute6 = "";
            }
            sb.append(attribute6);
            sb.append(SEPARATOR);
            if (TextUtils.isEmpty(attribute7)) {
                attribute7 = "";
            }
            sb.append(attribute7);
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
