package org.reactnative.camera.tasks;

import android.os.AsyncTask;
import android.util.SparseArray;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.taobao.weex.common.Constants;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import org.reactnative.camera.utils.ImageDimensions;
import org.reactnative.facedetector.FaceDetectorUtils;
import org.reactnative.frame.RNFrameFactory;

public class TextRecognizerAsyncTask extends AsyncTask<Void, Void, SparseArray<TextBlock>> {

    /* renamed from: a  reason: collision with root package name */
    private TextRecognizerAsyncTaskDelegate f4161a;
    private ThemedReactContext b;
    private TextRecognizer c;
    private byte[] d;
    private int e;
    private int f;
    private int g;
    private ImageDimensions h;
    private double i;
    private double j;
    private int k;
    private int l;

    public TextRecognizerAsyncTask(TextRecognizerAsyncTaskDelegate textRecognizerAsyncTaskDelegate, ThemedReactContext themedReactContext, byte[] bArr, int i2, int i3, int i4, float f2, int i5, int i6, int i7, int i8, int i9) {
        this.f4161a = textRecognizerAsyncTaskDelegate;
        this.b = themedReactContext;
        this.d = bArr;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.h = new ImageDimensions(i2, i3, i4, i5);
        double d2 = (double) i6;
        double b2 = (double) (((float) this.h.b()) * f2);
        Double.isNaN(d2);
        Double.isNaN(b2);
        this.i = d2 / b2;
        double d3 = (double) i7;
        double c2 = (double) (((float) this.h.c()) * f2);
        Double.isNaN(d3);
        Double.isNaN(c2);
        this.j = d3 / c2;
        this.k = i8;
        this.l = i9;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SparseArray<TextBlock> doInBackground(Void... voidArr) {
        if (isCancelled() || this.f4161a == null) {
            return null;
        }
        this.c = new TextRecognizer.Builder(this.b).build();
        return this.c.detect(RNFrameFactory.a(this.d, this.e, this.f, this.g).a());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(SparseArray<TextBlock> sparseArray) {
        super.onPostExecute(sparseArray);
        if (this.c != null) {
            this.c.release();
        }
        if (sparseArray != null) {
            WritableArray createArray = Arguments.createArray();
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                WritableMap a2 = a((Text) sparseArray.valueAt(i2));
                if (this.h.e() == 1) {
                    a2 = a(a2);
                }
                createArray.pushMap(a2);
            }
            this.f4161a.onTextRecognized(createArray);
        }
        this.f4161a.onTextRecognizerTaskCompleted();
    }

    private WritableMap a(Text text) {
        String str;
        WritableMap createMap = Arguments.createMap();
        WritableArray createArray = Arguments.createArray();
        for (Text a2 : text.getComponents()) {
            createArray.pushMap(a(a2));
        }
        createMap.putArray("components", createArray);
        createMap.putString("value", text.getValue());
        int i2 = text.getBoundingBox().left;
        int i3 = text.getBoundingBox().top;
        if (text.getBoundingBox().left < this.e / 2) {
            i2 += this.k / 2;
        } else if (text.getBoundingBox().left > this.e / 2) {
            i2 -= this.k / 2;
        }
        if (text.getBoundingBox().height() < this.f / 2) {
            i3 += this.l / 2;
        } else if (text.getBoundingBox().height() > this.f / 2) {
            i3 -= this.l / 2;
        }
        WritableMap createMap2 = Arguments.createMap();
        double d2 = (double) i2;
        double d3 = this.i;
        Double.isNaN(d2);
        createMap2.putDouble("x", d2 * d3);
        double d4 = (double) i3;
        double d5 = this.j;
        Double.isNaN(d4);
        createMap2.putDouble(Constants.Name.Y, d4 * d5);
        WritableMap createMap3 = Arguments.createMap();
        double width = (double) text.getBoundingBox().width();
        double d6 = this.i;
        Double.isNaN(width);
        createMap3.putDouble("width", width * d6);
        double height = (double) text.getBoundingBox().height();
        double d7 = this.j;
        Double.isNaN(height);
        createMap3.putDouble("height", height * d7);
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putMap("origin", createMap2);
        createMap4.putMap("size", createMap3);
        createMap.putMap("bounds", createMap4);
        if (text instanceof TextBlock) {
            str = MiotApmTask.j;
        } else {
            str = text instanceof Line ? "line" : BindingXConstants.i;
        }
        createMap.putString("type", str);
        return createMap;
    }

    private WritableMap a(WritableMap writableMap) {
        ReadableMap map = writableMap.getMap("bounds");
        WritableMap a2 = FaceDetectorUtils.a(FaceDetectorUtils.a(map.getMap("origin"), this.h.b(), this.i), -map.getMap("size").getDouble("width"));
        WritableMap createMap = Arguments.createMap();
        createMap.merge(map);
        createMap.putMap("origin", a2);
        writableMap.putMap("bounds", createMap);
        ReadableArray array = writableMap.getArray("components");
        WritableArray createArray = Arguments.createArray();
        for (int i2 = 0; i2 < array.size(); i2++) {
            WritableMap createMap2 = Arguments.createMap();
            createMap2.merge(array.getMap(i2));
            a(createMap2);
            createArray.pushMap(createMap2);
        }
        writableMap.putArray("components", createArray);
        return writableMap;
    }
}
