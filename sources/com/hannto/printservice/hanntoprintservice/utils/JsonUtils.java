package com.hannto.printservice.hanntoprintservice.utils;

import com.hannto.printservice.hanntoprintservice.entity.JobStatusEntity;
import com.hannto.printservice.hanntoprintservice.entity.PrintInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5783a = "accessKeyId";
    private static final String b = "bucketName";
    private static final String c = "expires";
    private static final String d = "objectName";
    private static final String e = "signature";
    private static final String f = "job_status";

    public static PrintInfo a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        return new PrintInfo(jSONObject.getString(f5783a), jSONObject.getString(b), jSONObject.getString("expires"), jSONObject.getString(d), jSONObject.getString("signature"));
    }

    public static JobStatusEntity b(String str) throws JSONException {
        return new JobStatusEntity(new JSONObject(str).getString(f));
    }
}
