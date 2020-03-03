package com.tiqiaa.icontrol.util;

import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.Feature;
import com.tiqiaa.common.IJsonable;
import java.util.HashMap;
import java.util.Map;

@JSONType(orders = {"responseType", "data"})
public class ResponseDTO extends DTO implements IJsonable {
    public static final String RESPONSE_PARAMS = "response_params";
    public static final int RESPONSE_TYPE_DATA_ANALYSIS_EXCEPTION = -4;
    public static final int RESPONSE_TYPE_NET_TRANSATON_EXCEPTION = -3;
    public static final int RESPONSE_TYPE_OPERATE_EXCEPTION = -2;
    public static final int RESPONSE_TYPE_SDK_NOT_SUPPORT = -1;
    public static final int RESPONSE_TYPE_SUCCESS = 0;
    protected static final String TAG = "RESPONSEDTO";
    @JSONField(name = "data")
    private ReponseParams data;
    @JSONField(name = "responseType")
    private int responseType;

    public int getResponseType() {
        return this.responseType;
    }

    public void setResponseType(int i) {
        this.responseType = i;
    }

    public ReponseParams getData() {
        return this.data;
    }

    public void setData(ReponseParams reponseParams) {
        this.data = reponseParams;
    }

    public static ResponseDTO getDTO(String str) {
        ResponseDTO responseDTO = (ResponseDTO) JSON.parseObject(str, ResponseDTO.class);
        LogUtil.d(TAG, "rsDto=" + responseDTO);
        return responseDTO;
    }

    @JSONType(orders = {"resultType", "extraParams", "concernObj"})
    public static class ReponseParams implements IJsonable {
        @JSONField(name = "concernObj")
        private Object concernObj;
        @JSONField(name = "extraParams")
        private Map<String, Object> extraParams;
        @JSONField(name = "resultType")
        private int resultType;

        public int getResultType() {
            return this.resultType;
        }

        public void setResultType(int i) {
            this.resultType = i;
        }

        public Object getConcernObj() {
            return this.concernObj;
        }

        public void setConcernObj(Object obj) {
            this.concernObj = obj;
        }

        public <T> T getConcernObj(Class<T> cls) {
            if (this.concernObj != null && !this.concernObj.equals("")) {
                return JSON.parseObject(this.concernObj.toString(), cls);
            }
            LogUtil.e(ResponseDTO.TAG, "getConcernObj.........this.concernObj==null");
            return null;
        }

        public <T> T getConcernObj(TypeReference<T> typeReference) {
            if (this.concernObj != null && !this.concernObj.equals("")) {
                return JSON.parseObject(this.concernObj.toString(), typeReference, new Feature[0]);
            }
            LogUtil.e(ResponseDTO.TAG, "getConcernObj.........this.concernObj==null");
            return null;
        }

        public Map<String, Object> getExtraParams() {
            return this.extraParams;
        }

        public void setExtraParams(Map<String, Object> map) {
            this.extraParams = map;
        }

        public void putExtraString(String str, String str2) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, str2);
        }

        public void putExtraChar(String str, String str2) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, str2);
        }

        public void putExtraByte(String str, byte b) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, Byte.valueOf(b));
        }

        public void putExtraInt(String str, int i) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, Integer.valueOf(i));
        }

        public void putExtraLong(String str, long j) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, Long.valueOf(j));
        }

        public void putExtraBoolean(String str, boolean z) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, Boolean.valueOf(z));
        }

        public void putExtraObj(String str, Object obj) {
            if (this.extraParams == null) {
                this.extraParams = new HashMap();
            }
            this.extraParams.put(str, obj);
        }

        public String getExtraString(String str) {
            if (this.extraParams == null || this.extraParams.get(str) == null) {
                return null;
            }
            return (String) this.extraParams.get(str);
        }

        public byte getExtraByte(String str) {
            if (this.extraParams == null || this.extraParams.get(str) == null) {
                return -1;
            }
            return ((Byte) this.extraParams.get(str)).byteValue();
        }

        public int getExtraInt(String str) {
            if (this.extraParams == null || this.extraParams.get(str) == null) {
                return -1;
            }
            return ((Integer) this.extraParams.get(str)).intValue();
        }

        public long getExtraLong(String str) {
            if (this.extraParams == null || this.extraParams.get(str) == null) {
                return -1;
            }
            return ((Long) this.extraParams.get(str)).longValue();
        }

        public boolean getExtraBoolean(String str) throws NullPointerException {
            if (this.extraParams != null && this.extraParams.get(str) != null) {
                return ((Boolean) this.extraParams.get(str)).booleanValue();
            }
            LogUtil.e(ResponseDTO.TAG, "getExtraBoolean..............没有 参数名 为 " + str + " 的Boolean类型参数");
            return false;
        }

        public Object getExtraObj(String str) {
            if (this.extraParams == null) {
                return null;
            }
            return this.extraParams.get(str);
        }
    }
}
