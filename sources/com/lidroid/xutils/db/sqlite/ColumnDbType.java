package com.lidroid.xutils.db.sqlite;

import com.mics.core.data.request.SendText;

public enum ColumnDbType {
    INTEGER("INTEGER"),
    REAL("REAL"),
    TEXT(SendText.TEXT),
    BLOB("BLOB");
    
    private String value;

    private ColumnDbType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
