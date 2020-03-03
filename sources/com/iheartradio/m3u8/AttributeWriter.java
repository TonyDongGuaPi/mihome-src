package com.iheartradio.m3u8;

interface AttributeWriter<T> {
    String a(T t) throws ParseException;

    boolean b(T t);
}
