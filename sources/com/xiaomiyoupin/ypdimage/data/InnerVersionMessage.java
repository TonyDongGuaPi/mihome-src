package com.xiaomiyoupin.ypdimage.data;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class InnerVersionMessage extends Message implements Serializable {
    private Result result;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result2) {
        this.result = result2;
    }

    public static class Result implements Serializable {
        private String innerVersion;

        public String getInnerVersion() {
            return this.innerVersion;
        }

        public void setInnerVersion(String str) {
            this.innerVersion = str;
        }

        public String toString() {
            return toJsonObject().toString();
        }

        public JSONObject toJsonObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("innerVersion", this.innerVersion);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public String toString() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = null;
            jSONObject.put("error", getError() == null ? null : getError().toJsonObject());
            if (getResult() != null) {
                jSONObject2 = getResult().toJsonObject();
            }
            jSONObject.put("result", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
