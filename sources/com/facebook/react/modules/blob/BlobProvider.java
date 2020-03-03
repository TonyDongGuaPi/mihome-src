package com.facebook.react.modules.blob;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

public final class BlobProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Nullable
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    @Nullable
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0051, code lost:
        r3 = r6;
        r6 = r5;
        r5 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.ParcelFileDescriptor openFile(android.net.Uri r5, java.lang.String r6) throws java.io.FileNotFoundException {
        /*
            r4 = this;
            java.lang.String r0 = "r"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0088
            android.content.Context r6 = r4.getContext()
            android.content.Context r6 = r6.getApplicationContext()
            boolean r0 = r6 instanceof com.facebook.react.ReactApplication
            r1 = 0
            if (r0 == 0) goto L_0x002c
            com.facebook.react.ReactApplication r6 = (com.facebook.react.ReactApplication) r6
            com.facebook.react.ReactNativeHost r6 = r6.getReactNativeHost()
            com.facebook.react.ReactInstanceManager r6 = r6.getReactInstanceManager()
            com.facebook.react.bridge.ReactContext r6 = r6.getCurrentReactContext()
            java.lang.Class<com.facebook.react.modules.blob.BlobModule> r0 = com.facebook.react.modules.blob.BlobModule.class
            com.facebook.react.bridge.NativeModule r6 = r6.getNativeModule(r0)
            com.facebook.react.modules.blob.BlobModule r6 = (com.facebook.react.modules.blob.BlobModule) r6
            goto L_0x002d
        L_0x002c:
            r6 = r1
        L_0x002d:
            if (r6 == 0) goto L_0x0080
            byte[] r6 = r6.resolve((android.net.Uri) r5)
            if (r6 == 0) goto L_0x0060
            android.os.ParcelFileDescriptor[] r5 = android.os.ParcelFileDescriptor.createPipe()     // Catch:{ IOException -> 0x005f }
            r0 = 0
            r0 = r5[r0]
            r2 = 1
            r5 = r5[r2]
            android.os.ParcelFileDescriptor$AutoCloseOutputStream r2 = new android.os.ParcelFileDescriptor$AutoCloseOutputStream     // Catch:{ IOException -> 0x005e }
            r2.<init>(r5)     // Catch:{ IOException -> 0x005e }
            r2.write(r6)     // Catch:{ Throwable -> 0x004e, all -> 0x004b }
            r2.close()     // Catch:{ IOException -> 0x005e }
            return r0
        L_0x004b:
            r5 = move-exception
            r6 = r1
            goto L_0x0054
        L_0x004e:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r6 = move-exception
            r3 = r6
            r6 = r5
            r5 = r3
        L_0x0054:
            if (r6 == 0) goto L_0x005a
            r2.close()     // Catch:{ Throwable -> 0x005d }
            goto L_0x005d
        L_0x005a:
            r2.close()     // Catch:{ IOException -> 0x005e }
        L_0x005d:
            throw r5     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            return r1
        L_0x005f:
            return r1
        L_0x0060:
            java.io.FileNotFoundException r6 = new java.io.FileNotFoundException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Cannot open "
            r0.append(r1)
            java.lang.String r5 = r5.toString()
            r0.append(r5)
            java.lang.String r5 = ", blob not found."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r6.<init>(r5)
            throw r6
        L_0x0080:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = "No blob module associated with BlobProvider"
            r5.<init>(r6)
            throw r5
        L_0x0088:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Cannot open "
            r1.append(r2)
            java.lang.String r5 = r5.toString()
            r1.append(r5)
            java.lang.String r5 = " in mode '"
            r1.append(r5)
            r1.append(r6)
            java.lang.String r5 = "'"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.blob.BlobProvider.openFile(android.net.Uri, java.lang.String):android.os.ParcelFileDescriptor");
    }
}
