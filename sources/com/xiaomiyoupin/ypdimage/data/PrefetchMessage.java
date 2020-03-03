package com.xiaomiyoupin.ypdimage.data;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class PrefetchMessage extends Message implements Serializable {
    private Result result;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result2) {
        this.result = result2;
    }

    public static class Result implements Serializable {
        private boolean finished;
        private String url;

        public boolean isFinished() {
            return this.finished;
        }

        public void setFinished(boolean z) {
            this.finished = z;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String toString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("finished", isFinished());
                jSONObject.put("url", this.url);
                return jSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return super.toString();
            }
        }

        public JSONObject toJsonObject() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("finished", isFinished());
                jSONObject.put("url", this.url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = null;
            jSONObject.put("error", getError() == null ? null : getError().toJsonObject());
            if (getResult() != null) {
                jSONObject2 = getResult().toJsonObject();
            }
            jSONObject.put("result", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
