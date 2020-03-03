package com.mi.multimonitor;

public interface Request extends Runnable {
    public static final String METHOD_POST = "POST";
    public static final String RESULT_CODE_KEY = "errno";
    public static final int SUCCESS_CODE = 0;
    public static final int TIME_OUT = 5000;

    String getBody();

    String getUrl();
}
