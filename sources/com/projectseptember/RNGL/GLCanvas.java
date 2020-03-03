package com.projectseptember.RNGL;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.libra.Color;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLCanvas extends GLSurfaceView implements GLSurfaceView.Renderer, ReactPointerEventsView, Executor {

    /* renamed from: a  reason: collision with root package name */
    private ReactContext f8560a;
    private RNGLContext b;
    private boolean c = true;
    private boolean d = true;
    /* access modifiers changed from: private */
    public boolean e = false;
    private GLRenderData f;
    private int g;
    private int h;
    private boolean i;
    private GLData j;
    private List<Uri> k = new ArrayList();
    private List<Uri> l = new ArrayList();
    private Map<Uri, GLImage> m = new HashMap();
    private List<GLTexture> n = new ArrayList();
    private List<Bitmap> o = new ArrayList();
    private Map<Integer, GLShader> p;
    private Map<Integer, GLFBO> q;
    private ExecutorSupplier r;
    private final Queue<Runnable> s = new LinkedList();
    private List<CaptureConfig> t = new ArrayList();
    private float u;
    private float v;
    private PointerEvents w = PointerEvents.AUTO;

    public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
    }

    public GLCanvas(ThemedReactContext themedReactContext, ExecutorSupplier executorSupplier) {
        super(themedReactContext);
        this.f8560a = themedReactContext;
        this.r = executorSupplier;
        this.b = (RNGLContext) themedReactContext.getNativeModule(RNGLContext.class);
        setEGLContextClientVersion(2);
        DisplayMetrics displayMetrics = this.f8560a.getResources().getDisplayMetrics();
        this.v = displayMetrics.density;
        this.u = displayMetrics.density;
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(3);
        setZOrderOnTop(false);
        setRenderer(this);
        setRenderMode(0);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        syncContentBitmaps();
        requestRender();
    }

    public GLFBO getFBO(Integer num) {
        if (!this.q.containsKey(num)) {
            this.q.put(num, new GLFBO(this));
        }
        return this.q.get(num);
    }

    public GLShader getShader(Integer num) {
        if (!this.p.containsKey(num)) {
            GLShaderData shader = this.b.getShader(num);
            if (shader == null) {
                return null;
            }
            this.p.put(num, new GLShader(shader, num, this.b));
        }
        return this.p.get(num);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.q = new HashMap();
        this.p = new HashMap();
        this.m = new HashMap();
        this.n = new ArrayList();
        this.o = new ArrayList();
        this.f = null;
        requestSyncData();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        a(i2, i3, this.u);
    }

    public void onDrawFrame(GL10 gl10) {
        a(this.s);
        if (this.n.size() != this.h) {
            resizeUniformContentTextures(this.h);
        }
        if (!b()) {
            this.d = false;
            boolean z = this.e || this.i || this.h == 0;
            if (this.h > 0) {
                this.f8560a.runOnUiQueueThread(new Runnable() {
                    public void run() {
                        GLCanvas.this.syncContentBitmaps();
                        if (!GLCanvas.this.e) {
                            boolean unused = GLCanvas.this.e = true;
                            GLCanvas.this.requestRender();
                        }
                    }
                });
            }
            if (z) {
                e();
                this.e = false;
                if (this.t.size() > 0) {
                    a();
                }
            }
        } else if (this.d) {
            this.d = false;
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glClear(16384);
        }
    }

    private void a() {
        String str;
        String str2;
        Bitmap g2 = g();
        RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class);
        for (CaptureConfig next : this.t) {
            boolean equals = next.b.equals("png");
            boolean z = true;
            boolean z2 = !equals && (next.b.equals("jpg") || next.b.equals("jpeg"));
            boolean z3 = !equals && !z2 && next.b.equals("webm");
            boolean equals2 = next.f8559a.equals(ViewShot.Results.BASE_64);
            if (equals2 || !next.f8559a.equals("file")) {
                z = false;
            }
            String str3 = null;
            Bitmap.CompressFormat compressFormat = equals ? Bitmap.CompressFormat.PNG : z2 ? Bitmap.CompressFormat.JPEG : z3 ? Bitmap.CompressFormat.WEBP : null;
            int doubleValue = (int) (next.d.doubleValue() * 100.0d);
            if (compressFormat == null) {
                str = "Unsupported capture type '" + next.b + "'";
            } else {
                if (equals2) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        g2.compress(compressFormat, doubleValue, byteArrayOutputStream);
                        str2 = "data:image/png;base64," + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        str = "Could not capture as base64: " + e2.getMessage();
                    }
                } else if (z) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(next.c);
                        g2.compress(compressFormat, doubleValue, fileOutputStream);
                        fileOutputStream.close();
                        str2 = "file://" + next.c;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        str = "Could not write file: " + e3.getMessage();
                    }
                } else {
                    str = "Unsupported capture format '" + next.f8559a + "'";
                }
                str3 = str2;
                str = null;
            }
            WritableMap createMap = Arguments.createMap();
            createMap.putMap("config", next.a());
            if (str != null) {
                createMap.putString("error", str);
            }
            if (str3 != null) {
                createMap.putString("result", str3);
            }
            rCTEventEmitter.receiveEvent(getId(), "captureFrame", createMap);
        }
        this.t = new ArrayList();
    }

    private boolean b() {
        for (Uri contains : this.k) {
            if (!this.l.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public void setNbContentTextures(int i2) {
        this.h = i2;
        requestRender();
    }

    public void setRenderId(int i2) {
        if (this.h > 0) {
            if (!b()) {
                syncContentBitmaps();
            }
            requestRender();
        }
    }

    public void setBackgroundColor(int i2) {
        super.setBackgroundColor(16777215 & i2);
        if (i2 == 0) {
            getHolder().setFormat(-3);
        } else {
            getHolder().setFormat(3);
        }
        requestRender();
    }

    public void setAutoRedraw(boolean z) {
        this.i = z;
        setRenderMode(z ? 1 : 0);
    }

    public void setData(GLData gLData) {
        this.j = gLData;
        this.f = null;
        if (!b()) {
            syncContentBitmaps();
        }
        requestSyncData();
    }

    public void setImagesToPreload(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            arrayList.add(resolveSrc(readableArray.getMap(i2).getString("uri")));
        }
        this.k = arrayList;
        requestSyncData();
    }

    public void execute(Runnable runnable) {
        synchronized (this.s) {
            this.s.add(runnable);
            requestRender();
        }
    }

    private void a(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                queue.poll().run();
            }
        }
    }

    public void requestSyncData() {
        execute(new Runnable() {
            public void run() {
                try {
                    if (!GLCanvas.this.d()) {
                        GLCanvas.this.requestSyncData();
                    }
                } catch (GLShaderCompilationFailed unused) {
                }
            }
        });
    }

    public static Bitmap captureView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        }
        if (view.getDrawingCache() == null) {
            view.setDrawingCacheEnabled(true);
        }
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            Log.e(GLCanvasManager.REACT_CLASS, "view.getDrawingCache() is null. view=" + view);
            return Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
        }
        Matrix matrix = new Matrix();
        matrix.postScale(1.0f, -1.0f);
        return Bitmap.createBitmap(drawingCache, 0, 0, drawingCache.getWidth(), drawingCache.getHeight(), matrix, true);
    }

    public int syncContentBitmaps() {
        int i2;
        ArrayList arrayList = new ArrayList();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup == null) {
            i2 = 0;
        } else {
            i2 = viewGroup.getChildCount() - 1;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) childAt;
                if (viewGroup2.getChildCount() == 1) {
                    childAt = viewGroup2.getChildAt(0);
                }
            }
            arrayList.add(captureView(childAt));
        }
        this.o = arrayList;
        return i2;
    }

    public int syncContentTextures() {
        int min = Math.min(this.n.size(), this.o.size());
        for (int i2 = 0; i2 < min; i2++) {
            this.n.get(i2).a(this.o.get(i2));
        }
        return min;
    }

    public void resizeUniformContentTextures(int i2) {
        int size = this.n.size();
        if (size != i2) {
            if (i2 < size) {
                this.n = this.n.subList(0, i2);
                return;
            }
            for (int size2 = this.n.size(); size2 < i2; size2++) {
                this.n.add(new GLTexture(this));
            }
        }
    }

    private int c() {
        int i2 = 0;
        for (Uri contains : this.k) {
            if (this.l.contains(contains)) {
                i2++;
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void a(Uri uri) {
        this.l.add(uri);
        int c2 = c();
        int size = this.k.size();
        double d2 = (double) c2;
        double d3 = (double) size;
        Double.isNaN(d2);
        Double.isNaN(d3);
        a(d2 / d3, c2, size);
        this.c = true;
        requestSyncData();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000b, code lost:
        if (r1.getScheme() == null) goto L_0x000f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri resolveSrc(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x0017
            android.net.Uri r1 = android.net.Uri.parse(r4)     // Catch:{ Exception -> 0x000f }
            java.lang.String r2 = r1.getScheme()     // Catch:{ Exception -> 0x000e }
            if (r2 != 0) goto L_0x000e
            goto L_0x000f
        L_0x000e:
            r0 = r1
        L_0x000f:
            if (r0 != 0) goto L_0x0017
            com.facebook.react.bridge.ReactContext r0 = r3.f8560a
            android.net.Uri r0 = com.projectseptember.RNGL.GLImage.a(r0, r4)
        L_0x0017:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.projectseptember.RNGL.GLCanvas.resolveSrc(java.lang.String):android.net.Uri");
    }

    public Uri srcResource(ReadableMap readableMap) {
        boolean z = readableMap.hasKey("isStatic") && readableMap.getBoolean("isStatic");
        String string = readableMap.hasKey("path") ? readableMap.getString("path") : null;
        if (string == null || z) {
            string = readableMap.getString("uri");
        }
        return resolveSrc(string);
    }

    private GLRenderData a(GLData gLData, HashMap<Uri, GLImage> hashMap) {
        List<String> list;
        Map<Uri, GLImage> map;
        Map<Uri, GLImage> map2;
        Map<Uri, GLImage> map3;
        GLData gLData2 = gLData;
        HashMap<Uri, GLImage> hashMap2 = hashMap;
        Map<Uri, GLImage> map4 = this.m;
        GLShader shader = getShader(gLData2.f8564a);
        if (shader == null || !shader.h()) {
            return null;
        }
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        HashMap hashMap6 = new HashMap();
        HashMap hashMap7 = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (GLData a2 : gLData2.g) {
            GLRenderData a3 = a(a2, hashMap2);
            if (a3 == null) {
                return null;
            }
            arrayList.add(a3);
        }
        for (GLData a4 : gLData2.h) {
            GLRenderData a5 = a(a4, hashMap2);
            if (a5 == null) {
                return null;
            }
            arrayList2.add(a5);
        }
        Map<String, Integer> d2 = shader.d();
        List<String> f2 = shader.f();
        Map<String, Integer> e2 = shader.e();
        ReadableMapKeySetIterator keySetIterator = gLData2.b.keySetIterator();
        ArrayList arrayList3 = arrayList2;
        int i2 = 0;
        while (true) {
            ArrayList arrayList4 = arrayList;
            if (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                ReadableMapKeySetIterator readableMapKeySetIterator = keySetIterator;
                int intValue = d2.get(nextKey).intValue();
                Map<String, Integer> map5 = d2;
                int intValue2 = e2.get(nextKey).intValue();
                Map<String, Integer> map6 = e2;
                ReadableMap readableMap = gLData2.b;
                if (intValue == 35678 || intValue == 35680) {
                    Map<Uri, GLImage> map7 = map4;
                    list = f2;
                    int i3 = i2 + 1;
                    hashMap3.put(nextKey, Integer.valueOf(i2));
                    if (readableMap.isNull(nextKey)) {
                        GLTexture gLTexture = new GLTexture(this);
                        gLTexture.b();
                        hashMap7.put(nextKey, gLTexture);
                    } else {
                        try {
                            ReadableMap map8 = readableMap.getMap(nextKey);
                            String string = map8.getString("type");
                            if (string.equals("content")) {
                                int i4 = map8.getInt("id");
                                if (i4 >= this.n.size()) {
                                    resizeUniformContentTextures(i4 + 1);
                                }
                                hashMap7.put(nextKey, this.n.get(i4));
                            } else if (string.equals("fbo")) {
                                hashMap7.put(nextKey, getFBO(Integer.valueOf(map8.getInt("id"))).f8565a.get(0));
                            } else {
                                if (string.equals("uri")) {
                                    final Uri srcResource = srcResource(map8);
                                    if (srcResource == null) {
                                        shader.a("texture uniform '" + nextKey + "': Invalid uri format '" + map8 + "'");
                                    }
                                    HashMap<Uri, GLImage> hashMap8 = hashMap;
                                    GLImage gLImage = hashMap8.get(srcResource);
                                    if (gLImage == null) {
                                        map = map7;
                                        gLImage = map.get(srcResource);
                                        if (gLImage != null) {
                                            hashMap8.put(srcResource, gLImage);
                                        }
                                    } else {
                                        map = map7;
                                    }
                                    if (gLImage == null) {
                                        gLImage = new GLImage(this, this.r.forDecode(), new Runnable() {
                                            public void run() {
                                                GLCanvas.this.a(srcResource);
                                            }
                                        });
                                        gLImage.a(srcResource);
                                        hashMap8.put(srcResource, gLImage);
                                    }
                                    hashMap7.put(nextKey, gLImage.a());
                                } else {
                                    map = map7;
                                    HashMap<Uri, GLImage> hashMap9 = hashMap;
                                    shader.a("texture uniform '" + nextKey + "': Unexpected type '" + intValue + "'");
                                }
                                i2 = i3;
                            }
                        } catch (Exception unused) {
                            shader.a("texture uniform '" + nextKey + "': you cannot directly give require('./img.png') to gl-react, use resolveAssetSource(require('./img.png')) instead.");
                            return null;
                        }
                    }
                    map = map7;
                    HashMap<Uri, GLImage> hashMap10 = hashMap;
                    i2 = i3;
                } else {
                    if (intValue2 == 1) {
                        if (intValue == 5124) {
                            hashMap3.put(nextKey, Integer.valueOf(readableMap.getInt(nextKey)));
                        } else if (intValue != 5126) {
                            switch (intValue) {
                                case 35664:
                                case 35665:
                                case 35666:
                                case 35674:
                                case 35675:
                                case 35676:
                                    ReadableArray array = readableMap.getArray(nextKey);
                                    if (a(intValue) != array.size()) {
                                        shader.a("uniform '" + nextKey + "': Invalid array size: " + array.size() + ". Expected: " + a(intValue));
                                    }
                                    hashMap6.put(nextKey, a(array));
                                    break;
                                case 35667:
                                case 35668:
                                case 35669:
                                case 35671:
                                case 35672:
                                case 35673:
                                    ReadableArray array2 = readableMap.getArray(nextKey);
                                    if (a(intValue) != array2.size()) {
                                        shader.a("uniform '" + nextKey + "': Invalid array size: " + array2.size() + ". Expected: " + a(intValue));
                                    }
                                    hashMap5.put(nextKey, b(array2));
                                    break;
                                case 35670:
                                    hashMap3.put(nextKey, Integer.valueOf(readableMap.getBoolean(nextKey) ? 1 : 0));
                                    break;
                                default:
                                    shader.a("uniform '" + nextKey + "': type not supported: " + intValue);
                                    break;
                            }
                        } else {
                            hashMap4.put(nextKey, Float.valueOf((float) readableMap.getDouble(nextKey)));
                        }
                        map2 = map4;
                        list = f2;
                    } else {
                        ReadableArray array3 = readableMap.getArray(nextKey);
                        if (intValue2 != array3.size()) {
                            StringBuilder sb = new StringBuilder();
                            list = f2;
                            sb.append("uniform '");
                            sb.append(nextKey);
                            sb.append("': Invalid array size: ");
                            sb.append(array3.size());
                            sb.append(". Expected: ");
                            sb.append(intValue2);
                            shader.a(sb.toString());
                        } else {
                            list = f2;
                        }
                        int i5 = 0;
                        while (i5 < intValue2) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(nextKey);
                            int i6 = intValue2;
                            sb2.append(Operators.ARRAY_START_STR);
                            sb2.append(i5);
                            sb2.append(Operators.ARRAY_END_STR);
                            String sb3 = sb2.toString();
                            if (intValue == 5124) {
                                map3 = map4;
                                hashMap3.put(sb3, Integer.valueOf(array3.getInt(i5)));
                            } else if (intValue != 5126) {
                                switch (intValue) {
                                    case 35664:
                                    case 35665:
                                    case 35666:
                                    case 35674:
                                    case 35675:
                                    case 35676:
                                        map3 = map4;
                                        ReadableArray array4 = array3.getArray(i5);
                                        if (a(intValue) != array4.size()) {
                                            shader.a("uniform '" + sb3 + "': Invalid array size: " + array4.size() + ". Expected: " + a(intValue));
                                        }
                                        hashMap6.put(sb3, a(array4));
                                        break;
                                    case 35667:
                                    case 35668:
                                    case 35669:
                                    case 35671:
                                    case 35672:
                                    case 35673:
                                        map3 = map4;
                                        ReadableArray array5 = array3.getArray(i5);
                                        if (a(intValue) != array5.size()) {
                                            shader.a("uniform '" + sb3 + "': Invalid array size: " + array5.size() + ". Expected: " + a(intValue));
                                        }
                                        hashMap5.put(sb3, b(array5));
                                        break;
                                    case 35670:
                                        map3 = map4;
                                        hashMap3.put(sb3, Integer.valueOf(array3.getBoolean(i5) ? 1 : 0));
                                        break;
                                    default:
                                        StringBuilder sb4 = new StringBuilder();
                                        map3 = map4;
                                        sb4.append("uniform '");
                                        sb4.append(sb3);
                                        sb4.append("': type not supported: ");
                                        sb4.append(intValue);
                                        shader.a(sb4.toString());
                                        break;
                                }
                            } else {
                                map3 = map4;
                                hashMap4.put(sb3, Float.valueOf((float) array3.getDouble(i5)));
                            }
                            i5++;
                            intValue2 = i6;
                            map4 = map3;
                            HashMap<Uri, GLImage> hashMap11 = hashMap;
                        }
                        map2 = map4;
                    }
                    map = map2;
                    HashMap<Uri, GLImage> hashMap12 = hashMap;
                }
                map4 = map;
                arrayList = arrayList4;
                keySetIterator = readableMapKeySetIterator;
                d2 = map5;
                e2 = map6;
                f2 = list;
                gLData2 = gLData;
            } else {
                List<String> list2 = f2;
                Map<String, Integer> map9 = e2;
                int[] iArr = new int[1];
                GLES20.glGetIntegerv(34930, iArr, 0);
                if (i2 > iArr[0]) {
                    shader.a("Maximum number of texture reach. got " + i2 + " >= max " + iArr);
                }
                for (String next : list2) {
                    Map<String, Integer> map10 = map9;
                    int intValue3 = map10.get(next).intValue();
                    if (intValue3 != 1) {
                        for (int i7 = 0; i7 < intValue3; i7++) {
                            String str = next + Operators.ARRAY_START_STR + i7 + Operators.ARRAY_END_STR;
                            if (!hashMap4.containsKey(str) && !hashMap3.containsKey(str) && !hashMap6.containsKey(str) && !hashMap5.containsKey(str)) {
                                shader.a("All defined uniforms must be provided. Missing '" + str + "'");
                            }
                        }
                    } else if (!hashMap4.containsKey(next) && !hashMap3.containsKey(next) && !hashMap6.containsKey(next) && !hashMap5.containsKey(next)) {
                        shader.a("All defined uniforms must be provided. Missing '" + next + "'");
                    }
                    map9 = map10;
                }
                GLData gLData3 = gLData;
                return new GLRenderData(shader, hashMap3, hashMap4, hashMap5, hashMap6, hashMap7, Integer.valueOf((int) (gLData3.c.doubleValue() * gLData3.e.doubleValue())), Integer.valueOf((int) (gLData3.d.doubleValue() * gLData3.e.doubleValue())), gLData3.f, arrayList4, arrayList3);
            }
        }
    }

    private FloatBuffer a(ReadableArray readableArray) {
        int size = readableArray.size();
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        for (int i2 = 0; i2 < size; i2++) {
            asFloatBuffer.put((float) readableArray.getDouble(i2));
        }
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    private IntBuffer b(ReadableArray readableArray) {
        int size = readableArray.size();
        IntBuffer asIntBuffer = ByteBuffer.allocateDirect(size * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        for (int i2 = 0; i2 < size; i2++) {
            asIntBuffer.put(readableArray.getInt(i2));
        }
        asIntBuffer.position(0);
        return asIntBuffer;
    }

    private int a(int i2) {
        switch (i2) {
            case 35664:
            case 35667:
            case 35671:
                return 2;
            case 35665:
            case 35668:
            case 35672:
                return 3;
            case 35666:
            case 35669:
            case 35673:
            case 35674:
                return 4;
            case 35675:
                return 9;
            case 35676:
                return 16;
            default:
                throw new Error("Invalid array type: " + i2);
        }
    }

    /* access modifiers changed from: private */
    public boolean d() {
        if (this.j == null) {
            return true;
        }
        HashMap hashMap = new HashMap();
        GLRenderData a2 = a(this.j, hashMap);
        if (a2 == null) {
            return false;
        }
        Set<A> diff = diff(this.m.keySet(), this.m.keySet());
        this.m = hashMap;
        this.l.removeAll(diff);
        this.f = a2;
        return true;
    }

    private void a(GLRenderData gLRenderData) {
        int intValue = gLRenderData.g.intValue();
        int intValue2 = gLRenderData.h.intValue();
        for (GLRenderData a2 : gLRenderData.j) {
            a(a2);
        }
        for (GLRenderData a3 : gLRenderData.k) {
            a(a3);
        }
        if (gLRenderData.i.intValue() == -1) {
            GLES20.glBindFramebuffer(36160, this.g);
            GLES20.glViewport(0, 0, intValue, intValue2);
            GLES20.glBlendFunc(1, 771);
        } else {
            GLFBO fbo = getFBO(gLRenderData.i);
            fbo.a(intValue, intValue2);
            fbo.b();
            GLES20.glBlendFunc(770, 771);
        }
        gLRenderData.f8571a.a();
        for (String next : gLRenderData.f.keySet()) {
            gLRenderData.f.get(next).a(gLRenderData.b.get(next).intValue());
        }
        Map<String, Integer> d2 = gLRenderData.f8571a.d();
        for (String next2 : gLRenderData.b.keySet()) {
            gLRenderData.f8571a.a(next2, gLRenderData.b.get(next2));
        }
        for (String next3 : gLRenderData.c.keySet()) {
            gLRenderData.f8571a.a(next3, gLRenderData.c.get(next3));
        }
        for (String next4 : gLRenderData.e.keySet()) {
            gLRenderData.f8571a.a(next4, gLRenderData.e.get(next4), d2.get(next4).intValue());
        }
        for (String next5 : gLRenderData.d.keySet()) {
            gLRenderData.f8571a.a(next5, gLRenderData.d.get(next5), d2.get(next5).intValue());
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        GLES20.glDrawArrays(4, 0, 3);
    }

    private void e() {
        GLRenderData gLRenderData = this.f;
        if (gLRenderData != null) {
            syncContentTextures();
            int[] iArr = new int[1];
            GLES20.glGetIntegerv(36006, iArr, 0);
            this.g = iArr[0];
            GLES20.glEnable(Constants.TradeCode.BUSINESS_ORDER_SETTING_VER2);
            a(gLRenderData);
            GLES20.glDisable(Constants.TradeCode.BUSINESS_ORDER_SETTING_VER2);
            GLES20.glBindFramebuffer(36160, this.g);
            GLES20.glBindBuffer(34962, 0);
            if (this.c && !b()) {
                this.c = false;
                f();
            }
        }
    }

    private void a(double d2, int i2, int i3) {
        WritableMap createMap = Arguments.createMap();
        if (Double.isNaN(d2)) {
            d2 = 0.0d;
        }
        createMap.putDouble(NotificationCompat.CATEGORY_PROGRESS, d2);
        createMap.putInt("loaded", i2);
        createMap.putInt("total", i3);
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), NotificationCompat.CATEGORY_PROGRESS, createMap);
    }

    private void f() {
        ((RCTEventEmitter) ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "load", Arguments.createMap());
    }

    public void requestCaptureFrame(CaptureConfig captureConfig) {
        requestRender();
        for (CaptureConfig equals : this.t) {
            if (equals.equals(captureConfig)) {
                return;
            }
        }
        this.t.add(captureConfig);
    }

    private Bitmap g() {
        return a(0, 0, this.f.g.intValue(), this.f.h.intValue());
    }

    private Bitmap a(int i2, int i3, int i4, int i5) {
        int i6 = i4 * i5;
        int[] iArr = new int[i6];
        int[] iArr2 = new int[i6];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        try {
            GLES20.glReadPixels(i2, i3, i4, i5, 6408, FujifilmMakernoteDirectory.H, wrap);
            for (int i7 = 0; i7 < i5; i7++) {
                int i8 = i7 * i4;
                int i9 = ((i5 - i7) - 1) * i4;
                for (int i10 = 0; i10 < i4; i10++) {
                    int i11 = iArr[i8 + i10];
                    iArr2[i9 + i10] = (i11 & Color.g) | ((i11 << 16) & 16711680) | ((i11 >> 16) & 255);
                }
            }
            return Bitmap.createBitmap(iArr2, i4, i5, Bitmap.Config.ARGB_8888);
        } catch (GLException unused) {
            return null;
        }
    }

    public PointerEvents getPointerEvents() {
        return this.w;
    }

    /* access modifiers changed from: package-private */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.w = pointerEvents;
    }

    static <A> Set<A> diff(Set<A> set, Set<A> set2) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(set);
        hashSet.removeAll(set2);
        return hashSet;
    }

    public void setPixelRatio(float f2) {
        this.u = f2;
        a(getWidth(), getHeight(), f2);
    }

    private void a(int i2, int i3, float f2) {
        getHolder().setFixedSize((int) ((((float) i2) * f2) / this.v), (int) ((((float) i3) * f2) / this.v));
    }
}
