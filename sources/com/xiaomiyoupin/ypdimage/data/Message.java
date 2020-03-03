package com.xiaomiyoupin.ypdimage.data;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class Message implements Serializable {
    private Error error;

    public Error getError() {
        return this.error;
    }

    public void setError(Error error2) {
        this.error = error2;
    }

    public static class Error implements Serializable {
        private int code;
        private String message;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public String toString() {
            return toJsonObject().toString();
        }

        public JSONObject toJsonObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", this.code);
                jSONObject.put("message", this.message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }
}
