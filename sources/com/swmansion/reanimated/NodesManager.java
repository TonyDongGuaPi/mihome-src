package com.swmansion.reanimated;

import android.util.SparseArray;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerReanimatedHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.swmansion.reanimated.nodes.AlwaysNode;
import com.swmansion.reanimated.nodes.BezierNode;
import com.swmansion.reanimated.nodes.BlockNode;
import com.swmansion.reanimated.nodes.CallFuncNode;
import com.swmansion.reanimated.nodes.ClockNode;
import com.swmansion.reanimated.nodes.ClockOpNode;
import com.swmansion.reanimated.nodes.ConcatNode;
import com.swmansion.reanimated.nodes.CondNode;
import com.swmansion.reanimated.nodes.DebugNode;
import com.swmansion.reanimated.nodes.EventNode;
import com.swmansion.reanimated.nodes.FunctionNode;
import com.swmansion.reanimated.nodes.JSCallNode;
import com.swmansion.reanimated.nodes.Node;
import com.swmansion.reanimated.nodes.NoopNode;
import com.swmansion.reanimated.nodes.OperatorNode;
import com.swmansion.reanimated.nodes.ParamNode;
import com.swmansion.reanimated.nodes.PropsNode;
import com.swmansion.reanimated.nodes.SetNode;
import com.swmansion.reanimated.nodes.StyleNode;
import com.swmansion.reanimated.nodes.TransformNode;
import com.swmansion.reanimated.nodes.ValueNode;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class NodesManager implements EventDispatcherListener {
    private static final Double e = Double.valueOf(0.0d);

    /* renamed from: a  reason: collision with root package name */
    public double f8898a;
    public final UpdateContext b;
    public Set<String> c = Collections.emptySet();
    public Set<String> d = Collections.emptySet();
    private final SparseArray<Node> f = new SparseArray<>();
    private final Map<String, EventNode> g = new HashMap();
    /* access modifiers changed from: private */
    public final UIImplementation h;
    private final DeviceEventManagerModule.RCTDeviceEventEmitter i;
    private final ReactChoreographer j;
    private final GuardedFrameCallback k;
    private final UIManagerModule.CustomEventNamesResolver l;
    private final AtomicBoolean m = new AtomicBoolean();
    private final NoopNode n;
    private final ReactContext o;
    /* access modifiers changed from: private */
    public final UIManagerModule p;
    private List<OnAnimationFrame> q = new ArrayList();
    private ConcurrentLinkedQueue<Event> r = new ConcurrentLinkedQueue<>();
    private boolean s;
    private Queue<NativeUpdateOperation> t = new LinkedList();

    public interface OnAnimationFrame {
        void a();
    }

    private final class NativeUpdateOperation {

        /* renamed from: a  reason: collision with root package name */
        public int f8901a;
        public WritableMap b;

        public NativeUpdateOperation(int i, WritableMap writableMap) {
            this.f8901a = i;
            this.b = writableMap;
        }
    }

    public NodesManager(ReactContext reactContext) {
        this.o = reactContext;
        this.p = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        this.b = new UpdateContext();
        this.h = this.p.getUIImplementation();
        this.l = this.p.getDirectEventNamesResolver();
        this.p.getEventDispatcher().addListener(this);
        this.i = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        this.j = ReactChoreographer.getInstance();
        this.k = new GuardedFrameCallback(reactContext) {
            /* access modifiers changed from: protected */
            public void doFrameGuarded(long j) {
                NodesManager.this.a(j);
            }
        };
        this.n = new NoopNode(this);
    }

    public void a() {
        if (this.m.get()) {
            e();
            this.m.set(true);
        }
    }

    public void b() {
        if (this.m.getAndSet(false)) {
            d();
        }
    }

    private void d() {
        if (!this.m.getAndSet(true)) {
            this.j.postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.k);
        }
    }

    private void e() {
        if (this.m.getAndSet(false)) {
            this.j.removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.k);
        }
    }

    /* access modifiers changed from: private */
    public void a(long j2) {
        double d2 = (double) j2;
        Double.isNaN(d2);
        this.f8898a = d2 / 1000000.0d;
        while (!this.r.isEmpty()) {
            a(this.r.poll());
        }
        if (!this.q.isEmpty()) {
            List<OnAnimationFrame> list = this.q;
            this.q = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                list.get(i2).a();
            }
        }
        if (this.s) {
            Node.runUpdates(this.b);
        }
        if (!this.t.isEmpty()) {
            final Queue<NativeUpdateOperation> queue = this.t;
            this.t = new LinkedList();
            this.o.runOnNativeModulesQueueThread(new GuardedRunnable(this.o) {
                public void runGuarded() {
                    boolean isOperationQueueEmpty = UIManagerReanimatedHelper.isOperationQueueEmpty(NodesManager.this.h);
                    while (!queue.isEmpty()) {
                        NativeUpdateOperation nativeUpdateOperation = (NativeUpdateOperation) queue.remove();
                        ReactShadowNode resolveShadowNode = NodesManager.this.h.resolveShadowNode(nativeUpdateOperation.f8901a);
                        if (resolveShadowNode != null) {
                            NodesManager.this.p.updateView(nativeUpdateOperation.f8901a, resolveShadowNode.getViewClass(), nativeUpdateOperation.b);
                        }
                    }
                    if (isOperationQueueEmpty) {
                        NodesManager.this.h.dispatchViewUpdates(-1);
                    }
                }
            });
        }
        this.m.set(false);
        this.s = false;
        if (!this.q.isEmpty() || !this.r.isEmpty()) {
            d();
        }
    }

    public Object a(int i2) {
        Node node = this.f.get(i2);
        if (node != null) {
            return node.value();
        }
        return e;
    }

    public <T extends Node> T a(int i2, Class<T> cls) {
        T t2 = (Node) this.f.get(i2);
        if (t2 == null) {
            if (cls == Node.class || cls == ValueNode.class) {
                return this.n;
            }
            throw new IllegalArgumentException("Requested node with id " + i2 + " of type " + cls + " cannot be found");
        } else if (cls.isInstance(t2)) {
            return t2;
        } else {
            throw new IllegalArgumentException("Node with id " + i2 + " is of incompatible type " + t2.getClass() + ", requested type was " + cls);
        }
    }

    public void a(int i2, ReadableMap readableMap) {
        Object obj;
        if (this.f.get(i2) == null) {
            String string = readableMap.getString("type");
            if ("props".equals(string)) {
                obj = new PropsNode(i2, readableMap, this, this.h);
            } else if ("style".equals(string)) {
                obj = new StyleNode(i2, readableMap, this);
            } else if ("transform".equals(string)) {
                obj = new TransformNode(i2, readableMap, this);
            } else if ("value".equals(string)) {
                obj = new ValueNode(i2, readableMap, this);
            } else if (MiotApmTask.j.equals(string)) {
                obj = new BlockNode(i2, readableMap, this);
            } else if ("cond".equals(string)) {
                obj = new CondNode(i2, readableMap, this);
            } else if ("op".equals(string)) {
                obj = new OperatorNode(i2, readableMap, this);
            } else if ("set".equals(string)) {
                obj = new SetNode(i2, readableMap, this);
            } else if ("debug".equals(string)) {
                obj = new DebugNode(i2, readableMap, this);
            } else if ("clock".equals(string)) {
                obj = new ClockNode(i2, readableMap, this);
            } else if ("clockStart".equals(string)) {
                obj = new ClockOpNode.ClockStartNode(i2, readableMap, this);
            } else if ("clockStop".equals(string)) {
                obj = new ClockOpNode.ClockStopNode(i2, readableMap, this);
            } else if ("clockTest".equals(string)) {
                obj = new ClockOpNode.ClockTestNode(i2, readableMap, this);
            } else if ("call".equals(string)) {
                obj = new JSCallNode(i2, readableMap, this);
            } else if ("bezier".equals(string)) {
                obj = new BezierNode(i2, readableMap, this);
            } else if ("event".equals(string)) {
                obj = new EventNode(i2, readableMap, this);
            } else if (ReactScrollViewHelper.OVER_SCROLL_ALWAYS.equals(string)) {
                obj = new AlwaysNode(i2, readableMap, this);
            } else if ("concat".equals(string)) {
                obj = new ConcatNode(i2, readableMap, this);
            } else if ("param".equals(string)) {
                obj = new ParamNode(i2, readableMap, this);
            } else if ("func".equals(string)) {
                obj = new FunctionNode(i2, readableMap, this);
            } else if ("callfunc".equals(string)) {
                obj = new CallFuncNode(i2, readableMap, this);
            } else {
                throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
            }
            this.f.put(i2, obj);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Animated node with ID " + i2 + " already exists");
    }

    public void b(int i2) {
        this.f.remove(i2);
    }

    public void a(int i2, int i3) {
        Node node = this.f.get(i2);
        if (node != null) {
            Node node2 = this.f.get(i3);
            if (node2 != null) {
                node.addChild(node2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with ID " + i3 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with ID " + i2 + " does not exists");
    }

    public void b(int i2, int i3) {
        Node node = this.f.get(i2);
        if (node != null) {
            Node node2 = this.f.get(i3);
            if (node2 != null) {
                node.removeChild(node2);
                return;
            }
            throw new JSApplicationIllegalArgumentException("Animated node with ID " + i3 + " does not exists");
        }
        throw new JSApplicationIllegalArgumentException("Animated node with ID " + i2 + " does not exists");
    }

    public void c(int i2, int i3) {
        Node node = this.f.get(i2);
        if (node == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with ID " + i2 + " does not exists");
        } else if (node instanceof PropsNode) {
            ((PropsNode) node).a(i3);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsNode.class.getName());
        }
    }

    public void d(int i2, int i3) {
        Node node = this.f.get(i2);
        if (node == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with ID " + i2 + " does not exists");
        } else if (node instanceof PropsNode) {
            ((PropsNode) node).b(i3);
        } else {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsNode.class.getName());
        }
    }

    public void a(int i2, WritableMap writableMap) {
        this.t.add(new NativeUpdateOperation(i2, writableMap));
    }

    public void a(int i2, String str, int i3) {
        String str2 = i2 + str;
        EventNode eventNode = (EventNode) this.f.get(i3);
        if (eventNode == null) {
            throw new JSApplicationIllegalArgumentException("Event node " + i3 + " does not exists");
        } else if (!this.g.containsKey(str2)) {
            this.g.put(str2, eventNode);
        } else {
            throw new JSApplicationIllegalArgumentException("Event handler already set for the given view and event type");
        }
    }

    public void b(int i2, String str, int i3) {
        this.g.remove(i2 + str);
    }

    public void a(Set<String> set, Set<String> set2) {
        this.d = set;
        this.c = set2;
    }

    public void a(int i2, Callback callback) {
        callback.invoke(this.f.get(i2).value());
    }

    public void c() {
        this.s = true;
        d();
    }

    public void a(OnAnimationFrame onAnimationFrame) {
        this.q.add(onAnimationFrame);
        d();
    }

    public void onEventDispatch(Event event) {
        if (UiThreadUtil.isOnUiThread()) {
            a(event);
            return;
        }
        this.r.offer(event);
        d();
    }

    private void a(Event event) {
        if (!this.g.isEmpty()) {
            String resolveCustomEventName = this.l.resolveCustomEventName(event.getEventName());
            int viewTag = event.getViewTag();
            EventNode eventNode = this.g.get(viewTag + resolveCustomEventName);
            if (eventNode != null) {
                event.dispatch(eventNode);
            }
        }
    }

    public void a(String str, WritableMap writableMap) {
        this.i.emit(str, writableMap);
    }
}
