package cn.com.fmsh.communication.message.tagvalue;

import cn.com.fmsh.communication.message.enumerate.ETagType;
import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {
    private static HandlerFactory handlerFactory;
    private Map<ETagType, StringValueHandler> stringValueHandler = new HashMap();

    private HandlerFactory() {
        stringValueHandlerInit();
    }

    private void stringValueHandlerInit() {
        this.stringValueHandler.put(ETagType.S, new StringValueHandler4asc());
        this.stringValueHandler.put(ETagType.N, new StringValueHandler4cn());
        this.stringValueHandler.put(ETagType.U, new StringValueHandler4utf());
        this.stringValueHandler.put(ETagType.H, new StringValueHandler4hex());
        this.stringValueHandler.put(ETagType.G, new StringValueHandler4gbk());
    }

    public static HandlerFactory instance() {
        if (handlerFactory == null) {
            handlerFactory = new HandlerFactory();
        }
        return handlerFactory;
    }

    public StringValueHandler getStringValueHandle(ETagType eTagType) {
        return this.stringValueHandler.get(eTagType);
    }
}
