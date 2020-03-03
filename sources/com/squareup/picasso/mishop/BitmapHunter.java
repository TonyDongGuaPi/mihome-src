package com.squareup.picasso.mishop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.NetworkInfo;
import android.os.Build;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.squareup.picasso.mishop.Downloader;
import com.squareup.picasso.mishop.NetworkRequestHandler;
import com.squareup.picasso.mishop.Picasso;
import com.squareup.picasso.mishop.RequestHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class BitmapHunter implements Runnable {
    private static final Object DECODE_LOCK = new Object();
    private static final RequestHandler ERRORING_HANDLER = new RequestHandler() {
        public boolean canHandleRequest(Request request) {
            return true;
        }

        public RequestHandler.Result load(Request request, int i) throws IOException {
            throw new IllegalStateException("Unrecognized type of request: " + request);
        }
    };
    private static final ThreadLocal<StringBuilder> NAME_BUILDER = new ThreadLocal<StringBuilder>() {
        /* access modifiers changed from: protected */
        public StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    };
    private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifOrientation;
    Future<?> future;
    final String key;
    Picasso.LoadedFrom loadedFrom;
    final int memoryPolicy;
    int networkPolicy;
    final Picasso picasso;
    Picasso.Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
    final Stats stats;

    static int getExifRotation(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    static int getExifTranslation(int i) {
        if (!(i == 2 || i == 7)) {
            switch (i) {
                case 4:
                case 5:
                    break;
                default:
                    return 1;
            }
        }
        return -1;
    }

    private static boolean shouldResize(boolean z, int i, int i2, int i3, int i4) {
        return !z || i > i3 || i2 > i4;
    }

    BitmapHunter(Picasso picasso2, Dispatcher dispatcher2, Cache cache2, Stats stats2, Action action2, RequestHandler requestHandler2) {
        this.picasso = picasso2;
        this.dispatcher = dispatcher2;
        this.cache = cache2;
        this.stats = stats2;
        this.action = action2;
        this.key = action2.getKey();
        this.data = action2.getRequest();
        this.priority = action2.getPriority();
        this.memoryPolicy = action2.getMemoryPolicy();
        this.networkPolicy = action2.getNetworkPolicy();
        this.requestHandler = requestHandler2;
        this.retryCount = requestHandler2.getRetryCount();
    }

    static Bitmap decodeStream(InputStream inputStream, Request request) throws IOException {
        MarkableInputStream markableInputStream = new MarkableInputStream(inputStream);
        long savePosition = markableInputStream.savePosition(655360000);
        BitmapFactory.Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        boolean requiresInSampleSize = RequestHandler.requiresInSampleSize(createBitmapOptions);
        boolean isWebPFile = Utils.isWebPFile(markableInputStream);
        boolean z = request.purgeable && Build.VERSION.SDK_INT < 21;
        markableInputStream.reset(savePosition);
        if (isWebPFile || z) {
            byte[] byteArray = Utils.toByteArray(markableInputStream);
            if (requiresInSampleSize) {
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            }
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, createBitmapOptions);
        }
        if (requiresInSampleSize) {
            BitmapFactory.decodeStream(markableInputStream, (Rect) null, createBitmapOptions);
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            markableInputStream.reset(savePosition);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(markableInputStream, (Rect) null, createBitmapOptions);
        if (decodeStream != null || (decodeStream = decodeStreamInBuffer(markableInputStream, createBitmapOptions)) != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeStreamInBuffer(java.io.InputStream r6, android.graphics.BitmapFactory.Options r7) {
        /*
            r0 = 0
            r1 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r2.<init>()     // Catch:{ IOException -> 0x0023, all -> 0x0020 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x001e }
        L_0x000b:
            int r4 = r6.read(r3)     // Catch:{ IOException -> 0x001e }
            r5 = -1
            if (r4 == r5) goto L_0x0016
            r2.write(r3, r0, r4)     // Catch:{ IOException -> 0x001e }
            goto L_0x000b
        L_0x0016:
            byte[] r6 = r2.toByteArray()     // Catch:{ IOException -> 0x001e }
            com.squareup.picasso.mishop.Utils.closeQuietly(r2)
            goto L_0x002c
        L_0x001e:
            r6 = move-exception
            goto L_0x0025
        L_0x0020:
            r6 = move-exception
            r2 = r1
            goto L_0x0038
        L_0x0023:
            r6 = move-exception
            r2 = r1
        L_0x0025:
            r6.printStackTrace()     // Catch:{ all -> 0x0037 }
            com.squareup.picasso.mishop.Utils.closeQuietly(r2)
            r6 = r1
        L_0x002c:
            if (r6 == 0) goto L_0x0036
            int r2 = r6.length
            if (r2 <= 0) goto L_0x0036
            int r1 = r6.length
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeByteArray(r6, r0, r1, r7)
        L_0x0036:
            return r1
        L_0x0037:
            r6 = move-exception
        L_0x0038:
            com.squareup.picasso.mishop.Utils.closeQuietly(r2)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.mishop.BitmapHunter.decodeStreamInBuffer(java.io.InputStream, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public void run() {
        try {
            updateThreadName(this.data);
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "executing", Utils.getLogIdsForHunter(this));
            }
            this.result = hunt();
            if (this.result == null) {
                this.dispatcher.dispatchFailed(this);
            } else {
                this.dispatcher.dispatchComplete(this);
            }
        } catch (Downloader.ResponseException e) {
            if (!e.localCacheOnly || e.responseCode != 504) {
                this.exception = e;
            }
            this.dispatcher.dispatchFailed(this);
        } catch (NetworkRequestHandler.ContentLengthException e2) {
            this.exception = e2;
            this.dispatcher.dispatchRetry(this);
        } catch (IOException e3) {
            this.exception = e3;
            this.dispatcher.dispatchRetry(this);
        } catch (OutOfMemoryError e4) {
            StringWriter stringWriter = new StringWriter();
            this.stats.createSnapshot().dump(new PrintWriter(stringWriter));
            this.exception = new RuntimeException(stringWriter.toString(), e4);
            this.dispatcher.dispatchFailed(this);
        } catch (Exception e5) {
            this.exception = e5;
            this.dispatcher.dispatchFailed(this);
        } catch (Throwable th) {
            Thread.currentThread().setName("Picasso-Idle");
            throw th;
        }
        Thread.currentThread().setName("Picasso-Idle");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public Bitmap hunt() throws IOException {
        Bitmap bitmap;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy)) {
            bitmap = this.cache.get(this.key);
            if (bitmap != null) {
                this.stats.dispatchCacheHit();
                this.loadedFrom = Picasso.LoadedFrom.MEMORY;
                if (this.picasso.loggingEnabled) {
                    Utils.log("Hunter", "decoded", this.data.logId(), "from cache");
                }
                return bitmap;
            }
        } else {
            bitmap = null;
        }
        this.data.networkPolicy = this.retryCount == 0 ? NetworkPolicy.OFFLINE.index : this.networkPolicy;
        RequestHandler.Result load = this.requestHandler.load(this.data, this.networkPolicy);
        if (load != null) {
            this.loadedFrom = load.getLoadedFrom();
            this.exifOrientation = load.getExifOrientation();
            bitmap = load.getBitmap();
            if (bitmap == null) {
                InputStream stream = load.getStream();
                try {
                    Bitmap decodeStream = decodeStream(stream, this.data);
                    Utils.closeQuietly(stream);
                    bitmap = decodeStream;
                } catch (Throwable th) {
                    Utils.closeQuietly(stream);
                    throw th;
                }
            }
        }
        if (bitmap != null) {
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId());
            }
            this.stats.dispatchBitmapDecoded(bitmap);
            if (this.data.needsTransformation() || this.exifOrientation != 0) {
                synchronized (DECODE_LOCK) {
                    if (this.data.needsMatrixTransform() || this.exifOrientation != 0) {
                        bitmap = transformResult(this.data, bitmap, this.exifOrientation);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", BindingXConstants.v, this.data.logId());
                        }
                    }
                    if (this.data.hasCustomTransformations()) {
                        bitmap = applyCustomTransformations(this.data.transformations, bitmap);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", BindingXConstants.v, this.data.logId(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.stats.dispatchBitmapTransformed(bitmap);
                }
            }
        }
        return bitmap;
    }

    /* access modifiers changed from: package-private */
    public void attach(Action action2) {
        boolean z = this.picasso.loggingEnabled;
        Request request = action2.request;
        if (this.action == null) {
            this.action = action2;
            if (!z) {
                return;
            }
            if (this.actions == null || this.actions.isEmpty()) {
                Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
            } else {
                Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
            }
        } else {
            if (this.actions == null) {
                this.actions = new ArrayList(3);
            }
            this.actions.add(action2);
            if (z) {
                Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
            }
            Picasso.Priority priority2 = action2.getPriority();
            if (priority2.ordinal() > this.priority.ordinal()) {
                this.priority = priority2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void detach(Action action2) {
        boolean z;
        if (this.action == action2) {
            this.action = null;
            z = true;
        } else {
            z = this.actions != null ? this.actions.remove(action2) : false;
        }
        if (z && action2.getPriority() == this.priority) {
            this.priority = computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action2.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }

    private Picasso.Priority computeNewPriority() {
        Picasso.Priority priority2 = Picasso.Priority.LOW;
        boolean z = true;
        boolean z2 = this.actions != null && !this.actions.isEmpty();
        if (this.action == null && !z2) {
            z = false;
        }
        if (!z) {
            return priority2;
        }
        if (this.action != null) {
            priority2 = this.action.getPriority();
        }
        if (z2) {
            int size = this.actions.size();
            for (int i = 0; i < size; i++) {
                Picasso.Priority priority3 = this.actions.get(i).getPriority();
                if (priority3.ordinal() > priority2.ordinal()) {
                    priority2 = priority3;
                }
            }
        }
        return priority2;
    }

    /* access modifiers changed from: package-private */
    public boolean cancel() {
        if (this.action != null) {
            return false;
        }
        if ((this.actions == null || this.actions.isEmpty()) && this.future != null && this.future.cancel(false)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.future != null && this.future.isCancelled();
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        if (!(this.retryCount > 0)) {
            return false;
        }
        this.retryCount--;
        return this.requestHandler.shouldRetry(z, networkInfo);
    }

    /* access modifiers changed from: package-private */
    public boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }

    /* access modifiers changed from: package-private */
    public Bitmap getResult() {
        return this.result;
    }

    /* access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* access modifiers changed from: package-private */
    public int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    /* access modifiers changed from: package-private */
    public Request getData() {
        return this.data;
    }

    /* access modifiers changed from: package-private */
    public Action getAction() {
        return this.action;
    }

    /* access modifiers changed from: package-private */
    public Picasso getPicasso() {
        return this.picasso;
    }

    /* access modifiers changed from: package-private */
    public List<Action> getActions() {
        return this.actions;
    }

    /* access modifiers changed from: package-private */
    public Exception getException() {
        return this.exception;
    }

    /* access modifiers changed from: package-private */
    public Picasso.LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }

    /* access modifiers changed from: package-private */
    public Picasso.Priority getPriority() {
        return this.priority;
    }

    static void updateThreadName(Request request) {
        String name = request.getName();
        StringBuilder sb = NAME_BUILDER.get();
        sb.ensureCapacity("Picasso-".length() + name.length());
        sb.replace("Picasso-".length(), sb.length(), name);
        Thread.currentThread().setName(sb.toString());
    }

    static BitmapHunter forRequest(Picasso picasso2, Dispatcher dispatcher2, Cache cache2, Stats stats2, Action action2) {
        Request request = action2.getRequest();
        List<RequestHandler> requestHandlers = picasso2.getRequestHandlers();
        int size = requestHandlers.size();
        for (int i = 0; i < size; i++) {
            RequestHandler requestHandler2 = requestHandlers.get(i);
            if (requestHandler2.canHandleRequest(request)) {
                return new BitmapHunter(picasso2, dispatcher2, cache2, stats2, action2, requestHandler2);
            }
        }
        return new BitmapHunter(picasso2, dispatcher2, cache2, stats2, action2, ERRORING_HANDLER);
    }

    static Bitmap applyCustomTransformations(List<Transformation> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        while (i < size) {
            final Transformation transformation = list.get(i);
            try {
                Bitmap transform = transformation.transform(bitmap);
                if (transform == null) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Transformation ");
                    sb.append(transformation.key());
                    sb.append(" returned null after ");
                    sb.append(i);
                    sb.append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation key2 : list) {
                        sb.append(key2.key());
                        sb.append(10);
                    }
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new NullPointerException(sb.toString());
                        }
                    });
                    return null;
                } else if (transform == bitmap && bitmap.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                } else if (transform == bitmap || bitmap.isRecycled()) {
                    i++;
                    bitmap = transform;
                } else {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
            } catch (RuntimeException e) {
                Picasso.HANDLER.post(new Runnable() {
                    public void run() {
                        throw new RuntimeException("Transformation " + transformation.key() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return bitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x022e  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0296  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x029a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Bitmap transformResult(com.squareup.picasso.mishop.Request r33, android.graphics.Bitmap r34, int r35) {
        /*
            r0 = r33
            int r2 = r34.getWidth()
            int r3 = r34.getHeight()
            boolean r4 = r0.onlyScaleDown
            android.graphics.Matrix r10 = new android.graphics.Matrix
            r10.<init>()
            boolean r5 = r33.needsMatrixTransform()
            if (r5 != 0) goto L_0x001d
            if (r35 == 0) goto L_0x001a
            goto L_0x001d
        L_0x001a:
            r1 = r10
            goto L_0x0286
        L_0x001d:
            int r5 = r0.targetWidth
            int r7 = r0.targetHeight
            float r8 = r0.rotationDegrees
            r9 = 0
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 == 0) goto L_0x019a
            double r11 = (double) r8
            double r13 = java.lang.Math.toRadians(r11)
            double r13 = java.lang.Math.cos(r13)
            double r11 = java.lang.Math.toRadians(r11)
            double r11 = java.lang.Math.sin(r11)
            boolean r5 = r0.hasRotationPivot
            if (r5 == 0) goto L_0x0109
            float r5 = r0.rotationPivotX
            float r7 = r0.rotationPivotY
            r10.setRotate(r8, r5, r7)
            float r5 = r0.rotationPivotX
            double r7 = (double) r5
            r15 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r15 = r15 - r13
            java.lang.Double.isNaN(r7)
            double r7 = r7 * r15
            float r5 = r0.rotationPivotY
            r18 = r7
            double r6 = (double) r5
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r11
            double r7 = r18 + r6
            float r5 = r0.rotationPivotY
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r15
            float r9 = r0.rotationPivotX
            r20 = r3
            r21 = r4
            double r3 = (double) r9
            java.lang.Double.isNaN(r3)
            double r3 = r3 * r11
            double r5 = r5 - r3
            int r3 = r0.targetWidth
            double r3 = (double) r3
            java.lang.Double.isNaN(r3)
            double r3 = r3 * r13
            double r3 = r3 + r7
            int r9 = r0.targetWidth
            r22 = r2
            double r1 = (double) r9
            java.lang.Double.isNaN(r1)
            double r1 = r1 * r11
            double r1 = r1 + r5
            int r9 = r0.targetWidth
            r23 = r10
            double r9 = (double) r9
            java.lang.Double.isNaN(r9)
            double r9 = r9 * r13
            double r9 = r9 + r7
            int r15 = r0.targetHeight
            r24 = r1
            double r1 = (double) r15
            java.lang.Double.isNaN(r1)
            double r1 = r1 * r11
            double r9 = r9 - r1
            int r1 = r0.targetWidth
            double r1 = (double) r1
            java.lang.Double.isNaN(r1)
            double r1 = r1 * r11
            double r1 = r1 + r5
            int r15 = r0.targetHeight
            r26 = r9
            double r9 = (double) r15
            java.lang.Double.isNaN(r9)
            double r9 = r9 * r13
            double r1 = r1 + r9
            int r9 = r0.targetHeight
            double r9 = (double) r9
            java.lang.Double.isNaN(r9)
            double r9 = r9 * r11
            double r9 = r7 - r9
            int r11 = r0.targetHeight
            double r11 = (double) r11
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r13
            double r11 = r11 + r5
            double r13 = java.lang.Math.max(r7, r3)
            r28 = r11
            r11 = r26
            double r13 = java.lang.Math.max(r11, r13)
            double r13 = java.lang.Math.max(r9, r13)
            double r3 = java.lang.Math.min(r7, r3)
            double r3 = java.lang.Math.min(r11, r3)
            double r3 = java.lang.Math.min(r9, r3)
            r7 = r24
            double r9 = java.lang.Math.max(r5, r7)
            double r9 = java.lang.Math.max(r1, r9)
            r11 = r28
            double r9 = java.lang.Math.max(r11, r9)
            double r5 = java.lang.Math.min(r5, r7)
            double r1 = java.lang.Math.min(r1, r5)
            double r1 = java.lang.Math.min(r11, r1)
            double r13 = r13 - r3
            double r3 = java.lang.Math.floor(r13)
            int r5 = (int) r3
            double r9 = r9 - r1
            double r1 = java.lang.Math.floor(r9)
            int r7 = (int) r1
            r1 = r23
            goto L_0x01a1
        L_0x0109:
            r22 = r2
            r20 = r3
            r21 = r4
            r1 = r10
            r1.setRotate(r8)
            int r2 = r0.targetWidth
            double r2 = (double) r2
            java.lang.Double.isNaN(r2)
            double r2 = r2 * r13
            int r4 = r0.targetWidth
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r11
            int r6 = r0.targetWidth
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r13
            int r8 = r0.targetHeight
            double r8 = (double) r8
            java.lang.Double.isNaN(r8)
            double r8 = r8 * r11
            double r6 = r6 - r8
            int r8 = r0.targetWidth
            double r8 = (double) r8
            java.lang.Double.isNaN(r8)
            double r8 = r8 * r11
            int r10 = r0.targetHeight
            r30 = r4
            double r4 = (double) r10
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r13
            double r8 = r8 + r4
            int r4 = r0.targetHeight
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r11
            double r4 = -r4
            int r10 = r0.targetHeight
            double r10 = (double) r10
            java.lang.Double.isNaN(r10)
            double r10 = r10 * r13
            r12 = 0
            double r14 = java.lang.Math.max(r12, r2)
            double r14 = java.lang.Math.max(r6, r14)
            double r14 = java.lang.Math.max(r4, r14)
            double r2 = java.lang.Math.min(r12, r2)
            double r2 = java.lang.Math.min(r6, r2)
            double r2 = java.lang.Math.min(r4, r2)
            r4 = r30
            double r6 = java.lang.Math.max(r12, r4)
            double r6 = java.lang.Math.max(r8, r6)
            double r6 = java.lang.Math.max(r10, r6)
            double r4 = java.lang.Math.min(r12, r4)
            double r4 = java.lang.Math.min(r8, r4)
            double r4 = java.lang.Math.min(r10, r4)
            double r14 = r14 - r2
            double r2 = java.lang.Math.floor(r14)
            int r2 = (int) r2
            double r6 = r6 - r4
            double r3 = java.lang.Math.floor(r6)
            int r7 = (int) r3
            r5 = r2
            goto L_0x01a1
        L_0x019a:
            r22 = r2
            r20 = r3
            r21 = r4
            r1 = r10
        L_0x01a1:
            if (r35 == 0) goto L_0x01c7
            int r3 = getExifRotation(r35)
            int r2 = getExifTranslation(r35)
            if (r3 == 0) goto L_0x01be
            float r4 = (float) r3
            r1.preRotate(r4)
            r4 = 90
            if (r3 == r4) goto L_0x01b9
            r4 = 270(0x10e, float:3.78E-43)
            if (r3 != r4) goto L_0x01be
        L_0x01b9:
            r32 = r7
            r7 = r5
            r5 = r32
        L_0x01be:
            r3 = 1
            if (r2 == r3) goto L_0x01c7
            float r2 = (float) r2
            r3 = 1065353216(0x3f800000, float:1.0)
            r1.postScale(r2, r3)
        L_0x01c7:
            boolean r2 = r0.centerCrop
            if (r2 == 0) goto L_0x0237
            if (r5 == 0) goto L_0x01d5
            float r0 = (float) r5
            r2 = r22
            float r3 = (float) r2
            float r0 = r0 / r3
            r3 = r20
            goto L_0x01dc
        L_0x01d5:
            r2 = r22
            float r0 = (float) r7
            r3 = r20
            float r4 = (float) r3
            float r0 = r0 / r4
        L_0x01dc:
            if (r7 == 0) goto L_0x01e2
            float r4 = (float) r7
            float r6 = (float) r3
        L_0x01e0:
            float r4 = r4 / r6
            goto L_0x01e5
        L_0x01e2:
            float r4 = (float) r5
            float r6 = (float) r2
            goto L_0x01e0
        L_0x01e5:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0201
            float r6 = (float) r3
            float r4 = r4 / r0
            float r6 = r6 * r4
            double r8 = (double) r6
            double r8 = java.lang.Math.ceil(r8)
            int r4 = (int) r8
            int r6 = r3 - r4
            int r6 = r6 / 2
            float r8 = (float) r7
            float r9 = (float) r4
            float r8 = r8 / r9
            r9 = r2
            r10 = r4
            r4 = r21
        L_0x01fe:
            r17 = 0
            goto L_0x0228
        L_0x0201:
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0220
            float r6 = (float) r2
            float r0 = r0 / r4
            float r6 = r6 * r0
            double r8 = (double) r6
            double r8 = java.lang.Math.ceil(r8)
            int r0 = (int) r8
            int r6 = r2 - r0
            int r6 = r6 / 2
            float r8 = (float) r5
            float r9 = (float) r0
            float r8 = r8 / r9
            r9 = r0
            r10 = r3
            r17 = r6
            r0 = r8
            r6 = 0
            r8 = r4
            r4 = r21
            goto L_0x0228
        L_0x0220:
            r9 = r2
            r10 = r3
            r0 = r4
            r8 = r0
            r4 = r21
            r6 = 0
            goto L_0x01fe
        L_0x0228:
            boolean r2 = shouldResize(r4, r2, r3, r5, r7)
            if (r2 == 0) goto L_0x0231
            r1.preScale(r0, r8)
        L_0x0231:
            r7 = r6
            r8 = r9
            r9 = r10
            r6 = r17
            goto L_0x028a
        L_0x0237:
            r3 = r20
            r4 = r21
            r2 = r22
            boolean r0 = r0.centerInside
            if (r0 == 0) goto L_0x0263
            if (r5 == 0) goto L_0x0247
            float r0 = (float) r5
            float r6 = (float) r2
        L_0x0245:
            float r0 = r0 / r6
            goto L_0x024a
        L_0x0247:
            float r0 = (float) r7
            float r6 = (float) r3
            goto L_0x0245
        L_0x024a:
            if (r7 == 0) goto L_0x0250
            float r6 = (float) r7
            float r8 = (float) r3
        L_0x024e:
            float r6 = r6 / r8
            goto L_0x0253
        L_0x0250:
            float r6 = (float) r5
            float r8 = (float) r2
            goto L_0x024e
        L_0x0253:
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0258
            goto L_0x0259
        L_0x0258:
            r0 = r6
        L_0x0259:
            boolean r4 = shouldResize(r4, r2, r3, r5, r7)
            if (r4 == 0) goto L_0x0286
            r1.preScale(r0, r0)
            goto L_0x0286
        L_0x0263:
            if (r5 != 0) goto L_0x0267
            if (r7 == 0) goto L_0x0286
        L_0x0267:
            if (r5 != r2) goto L_0x026b
            if (r7 == r3) goto L_0x0286
        L_0x026b:
            if (r5 == 0) goto L_0x0271
            float r0 = (float) r5
            float r6 = (float) r2
        L_0x026f:
            float r0 = r0 / r6
            goto L_0x0274
        L_0x0271:
            float r0 = (float) r7
            float r6 = (float) r3
            goto L_0x026f
        L_0x0274:
            if (r7 == 0) goto L_0x027a
            float r6 = (float) r7
            float r8 = (float) r3
        L_0x0278:
            float r6 = r6 / r8
            goto L_0x027d
        L_0x027a:
            float r6 = (float) r5
            float r8 = (float) r2
            goto L_0x0278
        L_0x027d:
            boolean r4 = shouldResize(r4, r2, r3, r5, r7)
            if (r4 == 0) goto L_0x0286
            r1.preScale(r0, r6)
        L_0x0286:
            r8 = r2
            r9 = r3
            r6 = 0
            r7 = 0
        L_0x028a:
            r11 = 1
            r5 = r34
            r10 = r1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r5, r6, r7, r8, r9, r10, r11)
            r1 = r34
            if (r0 == r1) goto L_0x029a
            r34.recycle()
            goto L_0x029b
        L_0x029a:
            r0 = r1
        L_0x029b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.mishop.BitmapHunter.transformResult(com.squareup.picasso.mishop.Request, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}
