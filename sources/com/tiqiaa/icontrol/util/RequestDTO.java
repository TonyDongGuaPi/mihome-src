package com.tiqiaa.icontrol.util;

import android.content.Context;
import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.Feature;
import com.tiqiaa.common.IJsonable;
import java.util.Locale;

@JSONType(orders = {"sdkNumber", "partnerNumber", "data"})
public class RequestDTO extends DTO implements IJsonable {
    private static String PHONE_IMEI = null;
    public static final String REQUEST_CLIENT_PARAMS_KEY = "client_request_params";
    public static final String REQUEST_PARAMS = "request_params";
    private static String TIQIAA_KEY = "00000000000000000000000000000000TIQIAA";
    private static final long serialVersionUID = -4877933754225633794L;
    @JSONField(name = "app_version")
    private int app_version;
    @JSONField(name = "client_type")
    private int client_type;
    @JSONField(name = "data")
    private Object data;
    @JSONField(name = "imei")
    private String imei;
    @JSONField(name = "langue")
    private int langue;
    @JSONField(name = "locale")
    private String locale;
    @JSONField(name = "location_json")
    private String location_json;
    @JSONField(name = "package_name")
    private String package_name;
    @JSONField(name = "partnerNumber")
    private String partnerNumber;
    @JSONField(name = "sdkNumber")
    private String sdkNumber;
    @JSONField(name = "sdkVersion")
    private int sdkVersion;

    public RequestDTO(Context context) {
        this.sdkVersion = 9;
        this.sdkVersion = 10;
        this.sdkNumber = "0000000000000001";
        this.partnerNumber = TIQIAA_KEY;
        this.client_type = 0;
        this.imei = PHONE_IMEI;
        this.app_version = PhoneHelper.getAppVersion(context);
        this.package_name = PhoneHelper.getPackageName(context);
        this.locale = Locale.getDefault().toString();
    }

    public static void setTiqiaaKey(String str) {
        TIQIAA_KEY = str;
    }

    public static String getTiqiaaKey() {
        return TIQIAA_KEY;
    }

    public int getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(int i) {
        this.sdkVersion = i;
    }

    public int getLangue() {
        return this.langue;
    }

    public void setLangue(int i) {
        this.langue = i;
    }

    public String getSdkNumber() {
        return this.sdkNumber;
    }

    public void setSdkNumber(String str) {
        this.sdkNumber = str;
    }

    public String getPartnerNumber() {
        return this.partnerNumber;
    }

    public void setPartnerNumber(String str) {
        this.partnerNumber = str;
    }

    public String getLocation_json() {
        return this.location_json;
    }

    public void setLocation_json(String str) {
        this.location_json = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public int getClient_type() {
        return this.client_type;
    }

    public void setClient_type(int i) {
        this.client_type = i;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    public void setPackage_name(String str) {
        this.package_name = str;
    }

    public int getApp_version() {
        return this.app_version;
    }

    public void setApp_version(int i) {
        this.app_version = i;
    }

    public <T> T getData(Class<T> cls) {
        if (this.data == null || this.data.equals("")) {
            return null;
        }
        return JSON.parseObject(this.data.toString(), cls);
    }

    public <T> T getData(TypeReference<T> typeReference) {
        if (this.data == null || this.data.equals("")) {
            return null;
        }
        return JSON.parseObject(this.data.toString(), typeReference, new Feature[0]);
    }

    public static RequestDTO getDTO(String str) {
        return (RequestDTO) JSON.parseObject(str, RequestDTO.class);
    }
}
