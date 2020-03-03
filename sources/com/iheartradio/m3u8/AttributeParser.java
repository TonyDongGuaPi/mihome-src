package com.iheartradio.m3u8;

interface AttributeParser<Builder> {
    void a(Attribute attribute, Builder builder, ParseState parseState) throws ParseException;
}
