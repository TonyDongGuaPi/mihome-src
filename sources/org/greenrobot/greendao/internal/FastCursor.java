package org.greenrobot.greendao.internal;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

public final class FastCursor implements Cursor {

    /* renamed from: a  reason: collision with root package name */
    private final CursorWindow f3528a;
    private int b;
    private final int c;

    public Uri getNotificationUri() {
        return null;
    }

    public FastCursor(CursorWindow cursorWindow) {
        this.f3528a = cursorWindow;
        this.c = cursorWindow.getNumRows();
    }

    public int getCount() {
        return this.f3528a.getNumRows();
    }

    public int getPosition() {
        return this.b;
    }

    public boolean move(int i) {
        return moveToPosition(this.b + i);
    }

    public boolean moveToPosition(int i) {
        if (i < 0 || i >= this.c) {
            return false;
        }
        this.b = i;
        return true;
    }

    public boolean moveToFirst() {
        this.b = 0;
        if (this.c > 0) {
            return true;
        }
        return false;
    }

    public boolean moveToLast() {
        if (this.c <= 0) {
            return false;
        }
        this.b = this.c - 1;
        return true;
    }

    public boolean moveToNext() {
        if (this.b >= this.c - 1) {
            return false;
        }
        this.b++;
        return true;
    }

    public boolean moveToPrevious() {
        if (this.b <= 0) {
            return false;
        }
        this.b--;
        return true;
    }

    public boolean isFirst() {
        return this.b == 0;
    }

    public boolean isLast() {
        return this.b == this.c - 1;
    }

    public boolean isBeforeFirst() {
        throw new UnsupportedOperationException();
    }

    public boolean isAfterLast() {
        throw new UnsupportedOperationException();
    }

    public int getColumnIndex(String str) {
        throw new UnsupportedOperationException();
    }

    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    public String getColumnName(int i) {
        throw new UnsupportedOperationException();
    }

    public String[] getColumnNames() {
        throw new UnsupportedOperationException();
    }

    public int getColumnCount() {
        throw new UnsupportedOperationException();
    }

    public byte[] getBlob(int i) {
        return this.f3528a.getBlob(this.b, i);
    }

    public String getString(int i) {
        return this.f3528a.getString(this.b, i);
    }

    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        throw new UnsupportedOperationException();
    }

    public short getShort(int i) {
        return this.f3528a.getShort(this.b, i);
    }

    public int getInt(int i) {
        return this.f3528a.getInt(this.b, i);
    }

    public long getLong(int i) {
        return this.f3528a.getLong(this.b, i);
    }

    public float getFloat(int i) {
        return this.f3528a.getFloat(this.b, i);
    }

    public double getDouble(int i) {
        return this.f3528a.getDouble(this.b, i);
    }

    public boolean isNull(int i) {
        return this.f3528a.isNull(this.b, i);
    }

    public void deactivate() {
        throw new UnsupportedOperationException();
    }

    public boolean requery() {
        throw new UnsupportedOperationException();
    }

    public void close() {
        throw new UnsupportedOperationException();
    }

    public boolean isClosed() {
        throw new UnsupportedOperationException();
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        throw new UnsupportedOperationException();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        throw new UnsupportedOperationException();
    }

    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        throw new UnsupportedOperationException();
    }

    public boolean getWantsAllOnMoveCalls() {
        throw new UnsupportedOperationException();
    }

    public Bundle getExtras() {
        throw new UnsupportedOperationException();
    }

    public Bundle respond(Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    public int getType(int i) {
        throw new UnsupportedOperationException();
    }
}
