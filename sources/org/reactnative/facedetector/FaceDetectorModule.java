package org.reactnative.facedetector;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.mi.global.shop.model.Tags;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.reactnative.facedetector.tasks.FileFaceDetectionAsyncTask;

public class FaceDetectorModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNFaceDetector";
    private static ReactApplicationContext mScopedContext;

    public String getName() {
        return TAG;
    }

    public FaceDetectorModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        mScopedContext = reactApplicationContext;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("Mode", a());
                put("Landmarks", c());
                put("Classifications", b());
            }

            private Map<String, Object> a() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("fast", Integer.valueOf(RNFaceDetector.f));
                        put("accurate", Integer.valueOf(RNFaceDetector.e));
                    }
                });
            }

            private Map<String, Object> b() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put(Tags.BaiduLbs.ADDRTYPE, Integer.valueOf(RNFaceDetector.f4165a));
                        put("none", Integer.valueOf(RNFaceDetector.b));
                    }
                });
            }

            private Map<String, Object> c() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put(Tags.BaiduLbs.ADDRTYPE, Integer.valueOf(RNFaceDetector.c));
                        put("none", Integer.valueOf(RNFaceDetector.d));
                    }
                });
            }
        });
    }

    @ReactMethod
    public void detectFaces(ReadableMap readableMap, Promise promise) {
        new FileFaceDetectionAsyncTask(mScopedContext, readableMap, promise).execute(new Void[0]);
    }
}
