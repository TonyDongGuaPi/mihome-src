package com.j256.ormlite.stmt;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.taobao.weex.el.parse.Operators;
import java.sql.SQLException;

public abstract class BaseArgumentHolder implements ArgumentHolder {
    private String columnName = null;
    private FieldType fieldType = null;
    private SqlType sqlType = null;

    /* access modifiers changed from: protected */
    public abstract Object getValue();

    /* access modifiers changed from: protected */
    public abstract boolean isValueSet();

    public abstract void setValue(Object obj);

    public BaseArgumentHolder() {
    }

    public BaseArgumentHolder(String str) {
        this.columnName = str;
    }

    public BaseArgumentHolder(SqlType sqlType2) {
        this.sqlType = sqlType2;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setMetaInfo(String str) {
        if (this.columnName != null && !this.columnName.equals(str)) {
            throw new IllegalArgumentException("Column name cannot be set twice from " + this.columnName + " to " + str + ".  Using a SelectArg twice in query with different columns?");
        }
        this.columnName = str;
    }

    public void setMetaInfo(FieldType fieldType2) {
        if (this.fieldType == null || this.fieldType == fieldType2) {
            this.fieldType = fieldType2;
            return;
        }
        throw new IllegalArgumentException("FieldType name cannot be set twice from " + this.fieldType + " to " + fieldType2 + ".  Using a SelectArg twice in query with different columns?");
    }

    public void setMetaInfo(String str, FieldType fieldType2) {
        setMetaInfo(str);
        setMetaInfo(fieldType2);
    }

    public Object getSqlArgValue() throws SQLException {
        if (isValueSet()) {
            Object value = getValue();
            if (value == null) {
                return null;
            }
            if (this.fieldType == null) {
                return value;
            }
            if (!this.fieldType.isForeign() || this.fieldType.getType() != value.getClass()) {
                return this.fieldType.convertJavaFieldToSqlArgValue(value);
            }
            return this.fieldType.getForeignRefField().extractJavaFieldValue(value);
        }
        throw new SQLException("Column value has not been set for " + this.columnName);
    }

    public FieldType getFieldType() {
        return this.fieldType;
    }

    public SqlType getSqlType() {
        return this.sqlType;
    }

    public String toString() {
        if (!isValueSet()) {
            return "[unset]";
        }
        try {
            Object sqlArgValue = getSqlArgValue();
            if (sqlArgValue == null) {
                return "[null]";
            }
            return sqlArgValue.toString();
        } catch (SQLException e) {
            return "[could not get value: " + e + Operators.ARRAY_END_STR;
        }
    }
}
