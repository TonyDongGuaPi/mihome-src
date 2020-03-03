package com.taobao.weex.bridge;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.bridge.SimpleJSCallback;
import com.taobao.weex.performance.WXAnalyzerDataTransfer;
import com.taobao.weex.utils.WXReflectionUtils;
import java.lang.reflect.Type;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class NativeInvokeHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String mInstanceId;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8363158980632609994L, "com/taobao/weex/bridge/NativeInvokeHelper", 34);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ String access$000(NativeInvokeHelper nativeInvokeHelper) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = nativeInvokeHelper.mInstanceId;
        $jacocoInit[33] = true;
        return str;
    }

    public NativeInvokeHelper(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mInstanceId = str;
        $jacocoInit[0] = true;
    }

    public Object invoke(final Object obj, final Invoker invoker, JSONArray jSONArray) throws Exception {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
        Type[] parameterTypes = invoker.getParameterTypes();
        $jacocoInit[2] = true;
        final Object[] prepareArguments = prepareArguments(parameterTypes, jSONArray);
        $jacocoInit[3] = true;
        if (!WXAnalyzerDataTransfer.isInteractionLogOpen()) {
            $jacocoInit[4] = true;
        } else if (!(invoker instanceof MethodInvoker)) {
            $jacocoInit[5] = true;
        } else {
            int i = 0;
            $jacocoInit[6] = true;
            while (true) {
                if (i >= prepareArguments.length) {
                    $jacocoInit[7] = true;
                    break;
                } else if (prepareArguments[i] instanceof SimpleJSCallback) {
                    $jacocoInit[8] = true;
                    final String callbackId = ((SimpleJSCallback) prepareArguments[i]).getCallbackId();
                    $jacocoInit[9] = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[client][callNativeModuleStart],");
                    sb.append(this.mInstanceId);
                    sb.append(",");
                    MethodInvoker methodInvoker = (MethodInvoker) invoker;
                    sb.append(methodInvoker.mMethod.getDeclaringClass());
                    sb.append(",");
                    sb.append(methodInvoker.mMethod.getName());
                    sb.append(",");
                    sb.append(callbackId);
                    Log.d(WXAnalyzerDataTransfer.INTERACTION_TAG, sb.toString());
                    $jacocoInit[10] = true;
                    ((SimpleJSCallback) prepareArguments[i]).setInvokerCallback(new SimpleJSCallback.InvokerCallback(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ NativeInvokeHelper this$0;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(-2205449350364168260L, "com/taobao/weex/bridge/NativeInvokeHelper$1", 2);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$0 = r2;
                            $jacocoInit[0] = true;
                        }

                        public void onInvokeSuccess() {
                            boolean[] $jacocoInit = $jacocoInit();
                            Log.d(WXAnalyzerDataTransfer.INTERACTION_TAG, "[client][callNativeModuleEnd]," + NativeInvokeHelper.access$000(this.this$0) + "," + ((MethodInvoker) invoker).mMethod.getDeclaringClass() + "," + ((MethodInvoker) invoker).mMethod.getName() + "," + callbackId);
                            $jacocoInit[1] = true;
                        }
                    });
                    $jacocoInit[11] = true;
                    break;
                } else {
                    i++;
                    $jacocoInit[12] = true;
                }
            }
        }
        if (invoker.isRunOnUIThread()) {
            $jacocoInit[13] = true;
            WXSDKManager.getInstance().postOnUiThread(new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ NativeInvokeHelper this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-2990687153861950057L, "com/taobao/weex/bridge/NativeInvokeHelper$2", 7);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (invoker == null) {
                        $jacocoInit[1] = true;
                    } else {
                        try {
                            $jacocoInit[2] = true;
                            invoker.invoke(obj, prepareArguments);
                            $jacocoInit[3] = true;
                        } catch (Exception e) {
                            $jacocoInit[4] = true;
                            RuntimeException runtimeException = new RuntimeException(obj + "Invoker " + invoker.toString(), e);
                            $jacocoInit[5] = true;
                            throw runtimeException;
                        }
                    }
                    $jacocoInit[6] = true;
                }
            }, 0);
            $jacocoInit[15] = true;
            return null;
        }
        Object invoke = invoker.invoke(obj, prepareArguments);
        $jacocoInit[14] = true;
        return invoke;
    }

    /* access modifiers changed from: protected */
    public Object[] prepareArguments(Type[] typeArr, JSONArray jSONArray) throws Exception {
        boolean[] $jacocoInit = $jacocoInit();
        Object[] objArr = new Object[typeArr.length];
        $jacocoInit[16] = true;
        int i = 0;
        while (i < typeArr.length) {
            Class<JSCallback> cls = typeArr[i];
            $jacocoInit[17] = true;
            if (i >= jSONArray.size()) {
                $jacocoInit[18] = true;
                if (!cls.getClass().isPrimitive()) {
                    objArr[i] = null;
                    $jacocoInit[19] = true;
                } else {
                    Exception exc = new Exception("[prepareArguments] method argument list not match.");
                    $jacocoInit[20] = true;
                    throw exc;
                }
            } else {
                Object obj = jSONArray.get(i);
                if (cls == JSONObject.class) {
                    if (obj instanceof JSONObject) {
                        $jacocoInit[21] = true;
                    } else if (obj == null) {
                        $jacocoInit[22] = true;
                    } else if (!(obj instanceof String)) {
                        $jacocoInit[24] = true;
                    } else {
                        $jacocoInit[25] = true;
                        objArr[i] = JSON.parseObject(obj.toString());
                        $jacocoInit[26] = true;
                    }
                    objArr[i] = obj;
                    $jacocoInit[23] = true;
                } else if (JSCallback.class != cls) {
                    objArr[i] = WXReflectionUtils.parseArgument(cls, obj);
                    $jacocoInit[30] = true;
                } else if (obj instanceof String) {
                    $jacocoInit[27] = true;
                    objArr[i] = new SimpleJSCallback(this.mInstanceId, (String) obj);
                    $jacocoInit[28] = true;
                } else {
                    Exception exc2 = new Exception("Parameter type not match.");
                    $jacocoInit[29] = true;
                    throw exc2;
                }
            }
            i++;
            $jacocoInit[31] = true;
        }
        $jacocoInit[32] = true;
        return objArr;
    }
}
