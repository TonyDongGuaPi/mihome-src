package com.facebook.react.devsupport;

import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.Pair;
import com.alipay.mobile.security.bio.workspace.Env;
import com.facebook.react.bridge.NativeDeltaClient;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import okhttp3.Headers;
import okio.BufferedSource;

public abstract class BundleDeltaClient {
    private static final String METRO_DELTA_ID_HEADER = "X-Metro-Delta-ID";
    @Nullable
    private String mRevisionId;

    public enum ClientType {
        NONE,
        DEV_SUPPORT,
        NATIVE
    }

    public abstract boolean canHandle(ClientType clientType);

    /* access modifiers changed from: protected */
    public abstract Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException;

    static boolean isDeltaUrl(String str) {
        return str.indexOf(".delta?") != -1;
    }

    @Nullable
    static BundleDeltaClient create(ClientType clientType) {
        switch (clientType) {
            case DEV_SUPPORT:
                return new BundleDeltaJavaClient();
            case NATIVE:
                return new BundleDeltaNativeClient();
            default:
                return null;
        }
    }

    public final synchronized String extendUrlForDelta(String str) {
        if (this.mRevisionId != null) {
            str = str + "&revisionId=" + this.mRevisionId;
        }
        return str;
    }

    public synchronized void reset() {
        this.mRevisionId = null;
    }

    public synchronized Pair<Boolean, NativeDeltaClient> processDelta(Headers headers, BufferedSource bufferedSource, File file) throws IOException {
        this.mRevisionId = headers.get(METRO_DELTA_ID_HEADER);
        return processDelta(bufferedSource, file);
    }

    private static class BundleDeltaJavaClient extends BundleDeltaClient {
        final TreeMap<Number, byte[]> mModules;
        byte[] mPostCode;
        byte[] mPreCode;

        private BundleDeltaJavaClient() {
            this.mModules = new TreeMap<>();
        }

        public boolean canHandle(ClientType clientType) {
            return clientType == ClientType.DEV_SUPPORT;
        }

        public synchronized void reset() {
            BundleDeltaClient.super.reset();
            this.mPreCode = null;
            this.mPostCode = null;
            this.mModules.clear();
        }

        /* JADX INFO: finally extract failed */
        public synchronized Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(bufferedSource.inputStream()));
            jsonReader.beginObject();
            int i = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equals(Env.NAME_PRE)) {
                    this.mPreCode = jsonReader.nextString().getBytes();
                } else if (nextName.equals("post")) {
                    this.mPostCode = jsonReader.nextString().getBytes();
                } else if (nextName.equals("modules")) {
                    i += setModules(jsonReader, this.mModules);
                } else if (nextName.equals("added")) {
                    i += setModules(jsonReader, this.mModules);
                } else if (nextName.equals("modified")) {
                    i += setModules(jsonReader, this.mModules);
                } else if (nextName.equals(Downloads.COLUMN_DELETED)) {
                    i += removeModules(jsonReader, this.mModules);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            if (i == 0) {
                return Pair.create(Boolean.FALSE, (Object) null);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(this.mPreCode);
                fileOutputStream.write(10);
                for (byte[] write : this.mModules.values()) {
                    fileOutputStream.write(write);
                    fileOutputStream.write(10);
                }
                fileOutputStream.write(this.mPostCode);
                fileOutputStream.write(10);
                fileOutputStream.flush();
                fileOutputStream.close();
                return Pair.create(Boolean.TRUE, (Object) null);
            } catch (Throwable th) {
                fileOutputStream.flush();
                fileOutputStream.close();
                throw th;
            }
        }

        private static int setModules(JsonReader jsonReader, TreeMap<Number, byte[]> treeMap) throws IOException {
            jsonReader.beginArray();
            int i = 0;
            while (jsonReader.hasNext()) {
                jsonReader.beginArray();
                treeMap.put(Integer.valueOf(jsonReader.nextInt()), jsonReader.nextString().getBytes());
                jsonReader.endArray();
                i++;
            }
            jsonReader.endArray();
            return i;
        }

        private static int removeModules(JsonReader jsonReader, TreeMap<Number, byte[]> treeMap) throws IOException {
            jsonReader.beginArray();
            int i = 0;
            while (jsonReader.hasNext()) {
                treeMap.remove(Integer.valueOf(jsonReader.nextInt()));
                i++;
            }
            jsonReader.endArray();
            return i;
        }
    }

    private static class BundleDeltaNativeClient extends BundleDeltaClient {
        private final NativeDeltaClient nativeClient;

        private BundleDeltaNativeClient() {
            this.nativeClient = new NativeDeltaClient();
        }

        public boolean canHandle(ClientType clientType) {
            return clientType == ClientType.NATIVE;
        }

        /* access modifiers changed from: protected */
        public Pair<Boolean, NativeDeltaClient> processDelta(BufferedSource bufferedSource, File file) throws IOException {
            this.nativeClient.processDelta(bufferedSource);
            return Pair.create(Boolean.FALSE, this.nativeClient);
        }

        public void reset() {
            BundleDeltaClient.super.reset();
            this.nativeClient.reset();
        }
    }
}
