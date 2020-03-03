package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brentvatne.react.ReactVideoView;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.miui.tsmclient.entity.CardInfo;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.download.Downloads;
import java.io.IOException;
import java.util.ArrayList;

@ReactModule(name = "CameraRollManager")
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ASSET_TYPE_ALL = "All";
    private static final String ASSET_TYPE_PHOTOS = "Photos";
    private static final String ASSET_TYPE_VIDEOS = "Videos";
    private static final String ERROR_UNABLE_TO_FILTER = "E_UNABLE_TO_FILTER";
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final String NAME = "CameraRollManager";
    /* access modifiers changed from: private */
    public static final String[] PROJECTION = {"_id", "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude", Downloads._DATA};
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    public String getName() {
        return NAME;
    }

    public CameraRollManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void saveToCameraRoll(String str, String str2, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(str), promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        private static final int SAVE_BUFFER_SIZE = 1048576;
        private final Context mContext;
        /* access modifiers changed from: private */
        public final Promise mPromise;
        private final Uri mUri;

        public SaveToCameraRoll(ReactContext reactContext, Uri uri, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mUri = uri;
            this.mPromise = promise;
        }

        /* JADX WARNING: type inference failed for: r1v19, types: [java.nio.channels.ReadableByteChannel] */
        /* access modifiers changed from: protected */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x004f A[Catch:{ IOException -> 0x0133, all -> 0x0131 }] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006d A[SYNTHETIC, Splitter:B:20:0x006d] */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x015e A[SYNTHETIC, Splitter:B:76:0x015e] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void doInBackgroundGuarded(java.lang.Void... r11) {
            /*
                r10 = this;
                java.io.File r11 = new java.io.File
                android.net.Uri r0 = r10.mUri
                java.lang.String r0 = r0.getPath()
                r11.<init>(r0)
                r0 = 0
                android.net.Uri r1 = r10.mUri     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.lang.String r1 = r1.getScheme()     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.lang.String r2 = "http"
                boolean r2 = r1.equals(r2)     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                if (r2 != 0) goto L_0x002d
                java.lang.String r2 = "https"
                boolean r1 = r1.equals(r2)     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                if (r1 == 0) goto L_0x0023
                goto L_0x002d
            L_0x0023:
                java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                r1.<init>(r11)     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.nio.channels.FileChannel r1 = r1.getChannel()     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                goto L_0x0040
            L_0x002d:
                java.net.URL r1 = new java.net.URL     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                android.net.Uri r2 = r10.mUri     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                r1.<init>(r2)     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.io.InputStream r1 = r1.openStream()     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
                java.nio.channels.ReadableByteChannel r1 = java.nio.channels.Channels.newChannel(r1)     // Catch:{ IOException -> 0x013b, all -> 0x0138 }
            L_0x0040:
                java.lang.String r2 = android.os.Environment.DIRECTORY_DCIM     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.io.File r2 = android.os.Environment.getExternalStoragePublicDirectory(r2)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r2.mkdirs()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                boolean r3 = r2.isDirectory()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                if (r3 != 0) goto L_0x006d
                com.facebook.react.bridge.Promise r11 = r10.mPromise     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r2 = "E_UNABLE_TO_LOAD"
                java.lang.String r3 = "External media storage directory not available"
                r11.reject((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                if (r1 == 0) goto L_0x006c
                boolean r11 = r1.isOpen()
                if (r11 == 0) goto L_0x006c
                r1.close()     // Catch:{ IOException -> 0x0064 }
                goto L_0x006c
            L_0x0064:
                r11 = move-exception
                java.lang.String r0 = "ReactNative"
                java.lang.String r1 = "Could not close input channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r11)
            L_0x006c:
                return
            L_0x006d:
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r4 = r11.getName()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r3.<init>(r2, r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r11 = r11.getName()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r4 = 46
                int r5 = r11.indexOf(r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r6 = 0
                if (r5 < 0) goto L_0x0098
                int r5 = r11.lastIndexOf(r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r5 = r11.substring(r6, r5)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                int r4 = r11.lastIndexOf(r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r11 = r11.substring(r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r4 = 0
                r9 = r5
                r5 = r11
                r11 = r9
                goto L_0x009c
            L_0x0098:
                java.lang.String r4 = ""
                r5 = r4
                r4 = 0
            L_0x009c:
                boolean r7 = r3.createNewFile()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                if (r7 != 0) goto L_0x00c2
                java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r7.<init>()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r7.append(r11)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r8 = "_"
                r7.append(r8)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                int r8 = r4 + 1
                r7.append(r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r7.append(r5)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.lang.String r4 = r7.toString()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r3.<init>(r2, r4)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r4 = r8
                goto L_0x009c
            L_0x00c2:
                java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r11.<init>(r3)     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                java.nio.channels.FileChannel r11 = r11.getChannel()     // Catch:{ IOException -> 0x0133, all -> 0x0131 }
                r2 = 1048576(0x100000, float:1.469368E-39)
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
            L_0x00d1:
                int r4 = r1.read(r2)     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                if (r4 <= 0) goto L_0x00e1
                r2.flip()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r11.write(r2)     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r2.compact()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                goto L_0x00d1
            L_0x00e1:
                r2.flip()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
            L_0x00e4:
                boolean r4 = r2.hasRemaining()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                if (r4 == 0) goto L_0x00ee
                r11.write(r2)     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                goto L_0x00e4
            L_0x00ee:
                r1.close()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r11.close()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                android.content.Context r2 = r10.mContext     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r4 = 1
                java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r4[r6] = r3     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                com.facebook.react.modules.camera.CameraRollManager$SaveToCameraRoll$1 r3 = new com.facebook.react.modules.camera.CameraRollManager$SaveToCameraRoll$1     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                r3.<init>()     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                android.media.MediaScannerConnection.scanFile(r2, r4, r0, r3)     // Catch:{ IOException -> 0x012c, all -> 0x0127 }
                if (r1 == 0) goto L_0x011b
                boolean r0 = r1.isOpen()
                if (r0 == 0) goto L_0x011b
                r1.close()     // Catch:{ IOException -> 0x0113 }
                goto L_0x011b
            L_0x0113:
                r0 = move-exception
                java.lang.String r1 = "ReactNative"
                java.lang.String r2 = "Could not close input channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
            L_0x011b:
                if (r11 == 0) goto L_0x016a
                boolean r0 = r11.isOpen()
                if (r0 == 0) goto L_0x016a
                r11.close()     // Catch:{ IOException -> 0x0162 }
                goto L_0x016a
            L_0x0127:
                r0 = move-exception
                r9 = r0
                r0 = r11
                r11 = r9
                goto L_0x016f
            L_0x012c:
                r0 = move-exception
                r9 = r1
                r1 = r11
                r11 = r0
                goto L_0x0136
            L_0x0131:
                r11 = move-exception
                goto L_0x016f
            L_0x0133:
                r11 = move-exception
                r9 = r1
                r1 = r0
            L_0x0136:
                r0 = r9
                goto L_0x013d
            L_0x0138:
                r11 = move-exception
                r1 = r0
                goto L_0x016f
            L_0x013b:
                r11 = move-exception
                r1 = r0
            L_0x013d:
                com.facebook.react.bridge.Promise r2 = r10.mPromise     // Catch:{ all -> 0x016b }
                r2.reject((java.lang.Throwable) r11)     // Catch:{ all -> 0x016b }
                if (r0 == 0) goto L_0x0156
                boolean r11 = r0.isOpen()
                if (r11 == 0) goto L_0x0156
                r0.close()     // Catch:{ IOException -> 0x014e }
                goto L_0x0156
            L_0x014e:
                r11 = move-exception
                java.lang.String r0 = "ReactNative"
                java.lang.String r2 = "Could not close input channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Throwable) r11)
            L_0x0156:
                if (r1 == 0) goto L_0x016a
                boolean r11 = r1.isOpen()
                if (r11 == 0) goto L_0x016a
                r1.close()     // Catch:{ IOException -> 0x0162 }
                goto L_0x016a
            L_0x0162:
                r11 = move-exception
                java.lang.String r0 = "ReactNative"
                java.lang.String r1 = "Could not close output channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r11)
            L_0x016a:
                return
            L_0x016b:
                r11 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
            L_0x016f:
                if (r1 == 0) goto L_0x0183
                boolean r2 = r1.isOpen()
                if (r2 == 0) goto L_0x0183
                r1.close()     // Catch:{ IOException -> 0x017b }
                goto L_0x0183
            L_0x017b:
                r1 = move-exception
                java.lang.String r2 = "ReactNative"
                java.lang.String r3 = "Could not close input channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Throwable) r1)
            L_0x0183:
                if (r0 == 0) goto L_0x0197
                boolean r1 = r0.isOpen()
                if (r1 == 0) goto L_0x0197
                r0.close()     // Catch:{ IOException -> 0x018f }
                goto L_0x0197
            L_0x018f:
                r0 = move-exception
                java.lang.String r1 = "ReactNative"
                java.lang.String r2 = "Could not close output channel"
                com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Throwable) r0)
            L_0x0197:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.camera.CameraRollManager.SaveToCameraRoll.doInBackgroundGuarded(java.lang.Void[]):void");
        }
    }

    @ReactMethod
    public void getPhotos(ReadableMap readableMap, Promise promise) {
        int i = readableMap.getInt("first");
        String string = readableMap.hasKey("after") ? readableMap.getString("after") : null;
        String string2 = readableMap.hasKey(CardInfo.KEY_CARD_GROUP_NAME) ? readableMap.getString(CardInfo.KEY_CARD_GROUP_NAME) : null;
        String string3 = readableMap.hasKey("assetType") ? readableMap.getString("assetType") : ASSET_TYPE_PHOTOS;
        ReadableArray array = readableMap.hasKey("mimeTypes") ? readableMap.getArray("mimeTypes") : null;
        if (!readableMap.hasKey("groupTypes")) {
            new GetMediaTask(getReactApplicationContext(), i, string, string2, array, string3, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
    }

    private static class GetMediaTask extends GuardedAsyncTask<Void, Void> {
        @Nullable
        private final String mAfter;
        private final String mAssetType;
        private final Context mContext;
        private final int mFirst;
        @Nullable
        private final String mGroupName;
        @Nullable
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;

        private GetMediaTask(ReactContext reactContext, int i, @Nullable String str, @Nullable String str2, @Nullable ReadableArray readableArray, String str3, Promise promise) {
            super(reactContext);
            this.mContext = reactContext;
            this.mFirst = i;
            this.mAfter = str;
            this.mGroupName = str2;
            this.mMimeTypes = readableArray;
            this.mPromise = promise;
            this.mAssetType = str3;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... voidArr) {
            Cursor query;
            StringBuilder sb = new StringBuilder("1");
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.mAfter)) {
                sb.append(" AND datetaken < ?");
                arrayList.add(this.mAfter);
            }
            if (!TextUtils.isEmpty(this.mGroupName)) {
                sb.append(" AND bucket_display_name = ?");
                arrayList.add(this.mGroupName);
            }
            String str = this.mAssetType;
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1905167199) {
                if (hashCode != -1732810888) {
                    if (hashCode == 65921 && str.equals(CameraRollManager.ASSET_TYPE_ALL)) {
                        c = 2;
                    }
                } else if (str.equals(CameraRollManager.ASSET_TYPE_VIDEOS)) {
                    c = 1;
                }
            } else if (str.equals(CameraRollManager.ASSET_TYPE_PHOTOS)) {
                c = 0;
            }
            switch (c) {
                case 0:
                    sb.append(" AND media_type = 1");
                    break;
                case 1:
                    sb.append(" AND media_type = 3");
                    break;
                case 2:
                    sb.append(" AND media_type IN (3,1)");
                    break;
                default:
                    Promise promise = this.mPromise;
                    promise.reject(CameraRollManager.ERROR_UNABLE_TO_FILTER, "Invalid filter option: '" + this.mAssetType + "'. Expected one of '" + CameraRollManager.ASSET_TYPE_PHOTOS + "', '" + CameraRollManager.ASSET_TYPE_VIDEOS + "' or '" + CameraRollManager.ASSET_TYPE_ALL + "'.");
                    return;
            }
            if (this.mMimeTypes != null && this.mMimeTypes.size() > 0) {
                sb.append(" AND mime_type IN (");
                for (int i = 0; i < this.mMimeTypes.size(); i++) {
                    sb.append("?,");
                    arrayList.add(this.mMimeTypes.getString(i));
                }
                sb.replace(sb.length() - 1, sb.length(), Operators.BRACKET_END_STR);
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            ContentResolver contentResolver = this.mContext.getContentResolver();
            try {
                query = contentResolver.query(MediaStore.Files.getContentUri("external"), CameraRollManager.PROJECTION, sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]), "datetaken DESC, date_modified DESC LIMIT " + (this.mFirst + 1));
                if (query == null) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get media");
                    return;
                }
                CameraRollManager.putEdges(contentResolver, query, writableNativeMap, this.mFirst);
                CameraRollManager.putPageInfo(query, writableNativeMap, this.mFirst);
                query.close();
                this.mPromise.resolve(writableNativeMap);
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get media: need READ_EXTERNAL_STORAGE permission", (Throwable) e);
            } catch (Throwable th) {
                query.close();
                this.mPromise.resolve(writableNativeMap);
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void putPageInfo(Cursor cursor, WritableMap writableMap, int i) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putBoolean("has_next_page", i < cursor.getCount());
        if (i < cursor.getCount()) {
            cursor.moveToPosition(i - 1);
            writableNativeMap.putString("end_cursor", cursor.getString(cursor.getColumnIndex("datetaken")));
        }
        writableMap.putMap("page_info", writableNativeMap);
    }

    /* access modifiers changed from: private */
    public static void putEdges(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i) {
        WritableNativeArray writableNativeArray;
        Cursor cursor2 = cursor;
        WritableNativeArray writableNativeArray2 = new WritableNativeArray();
        cursor.moveToFirst();
        int columnIndex = cursor2.getColumnIndex("_id");
        int columnIndex2 = cursor2.getColumnIndex("mime_type");
        int columnIndex3 = cursor2.getColumnIndex("bucket_display_name");
        int columnIndex4 = cursor2.getColumnIndex("datetaken");
        int columnIndex5 = cursor2.getColumnIndex("width");
        int columnIndex6 = cursor2.getColumnIndex("height");
        int columnIndex7 = cursor2.getColumnIndex("longitude");
        int columnIndex8 = cursor2.getColumnIndex("latitude");
        int columnIndex9 = cursor2.getColumnIndex(Downloads._DATA);
        int i2 = i;
        int i3 = 0;
        while (i3 < i2 && !cursor.isAfterLast()) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            WritableNativeMap writableNativeMap3 = writableNativeMap2;
            WritableNativeArray writableNativeArray3 = writableNativeArray2;
            WritableNativeMap writableNativeMap4 = writableNativeMap;
            int i4 = i3;
            int i5 = columnIndex;
            int i6 = columnIndex8;
            int i7 = columnIndex5;
            int i8 = columnIndex7;
            if (putImageInfo(contentResolver, cursor, writableNativeMap2, columnIndex, columnIndex5, columnIndex6, columnIndex9, columnIndex2)) {
                WritableNativeMap writableNativeMap5 = writableNativeMap3;
                putBasicNodeInfo(cursor2, writableNativeMap5, columnIndex2, columnIndex3, columnIndex4);
                putLocationInfo(cursor2, writableNativeMap5, i8, i6);
                writableNativeMap4.putMap("node", writableNativeMap5);
                writableNativeArray = writableNativeArray3;
                writableNativeArray.pushMap(writableNativeMap4);
            } else {
                writableNativeArray = writableNativeArray3;
                i4--;
            }
            cursor.moveToNext();
            i3 = i4 + 1;
            i2 = i;
            writableNativeArray2 = writableNativeArray;
            columnIndex8 = i6;
            columnIndex7 = i8;
            columnIndex = i5;
            columnIndex5 = i7;
        }
        writableMap.putArray("edges", writableNativeArray2);
    }

    private static void putBasicNodeInfo(Cursor cursor, WritableMap writableMap, int i, int i2, int i3) {
        writableMap.putString("type", cursor.getString(i));
        writableMap.putString("group_name", cursor.getString(i2));
        double d = (double) cursor.getLong(i3);
        Double.isNaN(d);
        writableMap.putDouble("timestamp", d / 1000.0d);
    }

    private static boolean putImageInfo(ContentResolver contentResolver, Cursor cursor, WritableMap writableMap, int i, int i2, int i3, int i4, int i5) {
        AssetFileDescriptor openAssetFileDescriptor;
        MediaMetadataRetriever mediaMetadataRetriever;
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Uri parse = Uri.parse("file://" + cursor.getString(i4));
        writableNativeMap.putString("uri", parse.toString());
        float f = (float) cursor.getInt(i2);
        float f2 = (float) cursor.getInt(i3);
        String string = cursor.getString(i5);
        if (string != null && string.startsWith("video")) {
            try {
                openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(parse, "r");
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(openAssetFileDescriptor.getFileDescriptor());
                if (f <= 0.0f || f2 <= 0.0f) {
                    try {
                        f = (float) Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                        f2 = (float) Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                    } catch (NumberFormatException e) {
                        FLog.e(ReactConstants.TAG, "Number format exception occurred while trying to fetch video metadata for " + parse.toString(), (Throwable) e);
                        mediaMetadataRetriever.release();
                        openAssetFileDescriptor.close();
                        return false;
                    }
                }
                writableNativeMap.putInt(ReactVideoView.EVENT_PROP_PLAYABLE_DURATION, Integer.parseInt(mediaMetadataRetriever.extractMetadata(9)) / 1000);
                mediaMetadataRetriever.release();
                openAssetFileDescriptor.close();
            } catch (Exception e2) {
                FLog.e(ReactConstants.TAG, "Could not get video metadata for " + parse.toString(), (Throwable) e2);
                return false;
            } catch (Throwable th) {
                mediaMetadataRetriever.release();
                openAssetFileDescriptor.close();
                throw th;
            }
        }
        if (f <= 0.0f || f2 <= 0.0f) {
            try {
                AssetFileDescriptor openAssetFileDescriptor2 = contentResolver.openAssetFileDescriptor(parse, "r");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(openAssetFileDescriptor2.getFileDescriptor(), (Rect) null, options);
                f = (float) options.outWidth;
                f2 = (float) options.outHeight;
                openAssetFileDescriptor2.close();
            } catch (IOException e3) {
                FLog.e(ReactConstants.TAG, "Could not get width/height for " + parse.toString(), (Throwable) e3);
                return false;
            }
        }
        writableNativeMap.putDouble("width", (double) f);
        writableNativeMap.putDouble("height", (double) f2);
        writableMap.putMap("image", writableNativeMap);
        return true;
    }

    private static void putLocationInfo(Cursor cursor, WritableMap writableMap, int i, int i2) {
        double d = cursor.getDouble(i);
        double d2 = cursor.getDouble(i2);
        if (d > 0.0d || d2 > 0.0d) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putDouble("longitude", d);
            writableNativeMap.putDouble("latitude", d2);
            writableMap.putMap("location", writableNativeMap);
        }
    }
}
