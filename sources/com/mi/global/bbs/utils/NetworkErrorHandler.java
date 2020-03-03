package com.mi.global.bbs.utils;

import com.mi.global.bbs.model.BaseResult;
import java.io.IOException;
import retrofit2.HttpException;

public class NetworkErrorHandler {
    private NetworkErrorHandler() {
    }

    public static String handle(Throwable th) {
        if (th instanceof HttpException) {
            try {
                BaseResult baseResult = (BaseResult) JsonParser.parse(((HttpException) th).response().errorBody().string(), BaseResult.class);
                if (baseResult != null) {
                    return baseResult.getErrmsg();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
