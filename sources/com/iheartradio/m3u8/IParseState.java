package com.iheartradio.m3u8;

interface IParseState<T> {
    T a() throws ParseException;
}
