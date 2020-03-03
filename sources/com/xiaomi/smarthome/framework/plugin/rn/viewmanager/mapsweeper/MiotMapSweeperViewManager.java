package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class MiotMapSweeperViewManager extends SimpleViewManager<ZoomMapSweeperView> {
    private static final int COMMAND_ADD_MAP_WITH_POINTS = 1;
    private static final int COMMAND_CLEAN_MAP_VIEW = 2;
    private static final int MSG_SWEEPER_MOVE = 1001;
    private Handler mHandler = null;
    /* access modifiers changed from: private */
    public ZoomMapSweeperView mZoomMapSweeperView;
    private int maxPointX = -1;
    private int maxPointY = -1;
    private int minPointX = -1;
    private int minPointY = -1;

    public String getName() {
        return "MHSweepingMap";
    }

    private void initHandler() {
        if (this.mHandler == null) {
            this.mHandler = new Handler() {
                public void handleMessage(Message message) {
                    if (message.what == 1001 && MiotMapSweeperViewManager.this.mZoomMapSweeperView != null) {
                        MsgObject msgObject = (MsgObject) message.obj;
                        MiotMapSweeperViewManager.this.mZoomMapSweeperView.getMapSweeperView().moveSweeper(msgObject.b, msgObject.f17622a);
                    }
                }
            };
        }
    }

    /* access modifiers changed from: protected */
    public ZoomMapSweeperView createViewInstance(ThemedReactContext themedReactContext) {
        this.mZoomMapSweeperView = new ZoomMapSweeperView(themedReactContext);
        initData();
        return this.mZoomMapSweeperView;
    }

    private void initData() {
        this.minPointX = -1;
        this.maxPointX = -1;
        this.minPointY = -1;
        this.maxPointY = -1;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("addMapWithPoints", 1, "cleanMapView", 2);
    }

    @ReactProp(customType = "Color", name = "wallColor")
    public void setWallColor(ZoomMapSweeperView zoomMapSweeperView, @Nullable Integer num) {
        zoomMapSweeperView.getMapSweeperView().setWallColor(num.intValue());
    }

    @ReactProp(customType = "Color", name = "floorColor")
    public void setFloorColor(ZoomMapSweeperView zoomMapSweeperView, @Nullable Integer num) {
        zoomMapSweeperView.getMapSweeperView().setFloorColor(num.intValue());
    }

    @ReactProp(customType = "Color", name = "lineColor")
    public void setLineColor(ZoomMapSweeperView zoomMapSweeperView, @Nullable Integer num) {
        zoomMapSweeperView.getMapSweeperView().setLineColor(num.intValue());
    }

    @ReactProp(name = "imageSources")
    public void setImageSources(ZoomMapSweeperView zoomMapSweeperView, ReadableArray readableArray) {
        ReadableArray readableArray2 = readableArray;
        if (readableArray2 != null && readableArray.size() != 0) {
            RnPluginLog.a("MiotMapSweeperViewManager-->setImages...");
            if (GlobalSetting.q || GlobalSetting.w) {
                RnPluginLog.a("MiotMapSweeperViewManager-->setImages..." + readableArray.toString());
            }
            int size = readableArray.size();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                ReadableMap map = readableArray2.getMap(i);
                Image image = new Image(MIOTUtils.e(map, "source"), MIOTUtils.e(map, "bgSource"), MIOTUtils.a(map, "name", ""), MIOTUtils.a(MIOTUtils.f(map, "position"), "x", -1), MIOTUtils.a(MIOTUtils.f(map, "position"), Constants.Name.Y, -1), MIOTUtils.a(map, ViewProps.ROTATION, 0), MIOTUtils.a(MIOTUtils.f(map, "size"), "w", -1), MIOTUtils.a(MIOTUtils.f(map, "size"), "h", -1));
                if (image.f17614a != null && image.f17614a.size() > 0 && !TextUtils.isEmpty(image.c)) {
                    arrayList.add(image);
                }
            }
            zoomMapSweeperView.getMapSweeperView().refreshCommonSweeperViews(arrayList);
        }
    }

    public void receiveCommand(ZoomMapSweeperView zoomMapSweeperView, int i, @Nullable ReadableArray readableArray) {
        RnPluginLog.a("MiotMapSweeperViewManager commandId=" + i + "   args=" + readableArray.toString());
        if (i == 1) {
            if (readableArray != null && readableArray.size() != 0) {
                addMapWithPoints(MIOTUtils.a(readableArray.getMap(0), "points", ""), MIOTUtils.a(readableArray.getMap(0), "name", ""), MIOTUtils.b(readableArray.getMap(0), "autoCenter"), MIOTUtils.b(readableArray.getMap(0), "scaleToFit"));
            } else {
                return;
            }
        } else if (i == 2) {
            cleanMapView(zoomMapSweeperView);
        }
        zoomMapSweeperView.getRootView().invalidate();
        zoomMapSweeperView.invalidate();
    }

    private void addMapWithPoints(String str, String str2, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str) && this.mZoomMapSweeperView != null) {
            String[] split = str.trim().split("\\s+");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < split.length; i += 3) {
                MapPoint mapPoint = new MapPoint(Integer.parseInt(split[i]), Integer.parseInt(split[i + 1]), Integer.parseInt(split[i + 2]));
                updataMinAndMaxPoint(mapPoint);
                if (mapPoint.c == 2 || mapPoint.c == 4) {
                    arrayList.add(mapPoint);
                } else if (mapPoint.c == 1 || mapPoint.c == 3) {
                    arrayList2.add(mapPoint);
                }
            }
            autoScallZoomMapSweeperView(z, z2);
            this.mZoomMapSweeperView.getMapSweeperView().addNewPointsToMapBackgroundView(arrayList, arrayList2);
            if (TextUtils.isEmpty(str2)) {
                this.mZoomMapSweeperView.getMapSweeperView().drawHistoryPoints(arrayList2);
            } else {
                this.mZoomMapSweeperView.getMapSweeperView().moveSweeper((List<MapPoint>) arrayList2, str2);
            }
        }
    }

    private void autoScallZoomMapSweeperView(boolean z, boolean z2) {
        float f;
        float f2;
        if (!z2 || !this.mZoomMapSweeperView.isScallEnable()) {
            RnPluginLog.a("MiotMapSweeperViewManager  scaleToFit=" + z2 + "  isScallEnable=" + this.mZoomMapSweeperView.isScallEnable());
            return;
        }
        int i = this.maxPointX - this.minPointX;
        int i2 = this.maxPointY - this.minPointY;
        int abs = Math.abs(Math.max(i, i2));
        if (abs == 0) {
            abs = 10;
        }
        int i3 = 256 / abs;
        if (i3 < 2) {
            i3 = 2;
        } else if (((float) i3) > 10.0f) {
            i3 = 10;
        }
        this.mZoomMapSweeperView.setMaxZoom((float) i3);
        if (z) {
            int i4 = i3 - 1;
            int i5 = this.minPointX + (i >> 1);
            int i6 = this.minPointY + (i2 >> 1);
            MapPoint a2 = MapPoint.a(new MapPoint(i5 - 128, i6 - 128, 0), this.mZoomMapSweeperView.getMapSweeperView().getMapPointScale());
            int left = this.mZoomMapSweeperView.getMapSweeperView().getLeft();
            int top = this.mZoomMapSweeperView.getMapSweeperView().getTop();
            if (left == 0) {
                f = (float) (this.mZoomMapSweeperView.getMapSweeperView().getViewWidth() / 2);
            } else {
                f = ((float) left) + ((float) (this.mZoomMapSweeperView.getMapSweeperView().getViewWidth() / 2));
            }
            if (top == 0) {
                f2 = (float) (this.mZoomMapSweeperView.getMapSweeperView().getViewHeight() / 2);
            } else {
                f2 = ((float) (this.mZoomMapSweeperView.getMapSweeperView().getViewHeight() / 2)) + ((float) top);
            }
            float f3 = (float) i4;
            float width = ((float) this.mZoomMapSweeperView.getWidth()) - ((((float) this.mZoomMapSweeperView.getWidth()) * 0.5f) / f3);
            float width2 = (((float) this.mZoomMapSweeperView.getWidth()) * 0.5f) / f3;
            if (f < width2 || f > width) {
                f = (width2 + width) / 2.0f;
            }
            float height = ((float) this.mZoomMapSweeperView.getHeight()) - ((((float) this.mZoomMapSweeperView.getHeight()) * 0.5f) / f3);
            float height2 = (((float) this.mZoomMapSweeperView.getHeight()) * 0.5f) / f3;
            if (f2 < height2 || f2 > height) {
                f2 = (height2 + height) / 2.0f;
            }
            float f4 = f + ((float) a2.f17616a);
            float f5 = f2 + ((float) a2.b);
            if (GlobalSetting.q || GlobalSetting.s) {
                RnPluginLog.a("MiotMapSweeperViewManager 移动中心位置  centerPointX=" + i5 + "  centerPointY=" + i6 + "  devidePoint.pointX=" + a2.f17616a + "  devidePoint.pointY=" + a2.b + "  tempCenterX=" + f4 + "  tempCenterY=" + f5 + "  checkBigX=" + width + "  checkSmallX=" + width2 + "  checkBigY=" + height + "  checkSmallY=" + height2);
            }
            this.mZoomMapSweeperView.zoomTo(f3, f4, f5);
        } else {
            this.mZoomMapSweeperView.zoomTo((float) (i3 - 1), this.mZoomMapSweeperView.zoomX, this.mZoomMapSweeperView.zoomY);
        }
        this.mZoomMapSweeperView.getMapSweeperView().startCommonSweeperViewAnimatorDelayed(1000);
        RnPluginLog.a("MiotMapSweeperViewManager  minPointX=" + this.minPointX + "  maxPointX=" + this.maxPointX + "  minPointY=" + this.minPointY + "  maxPointY=" + this.maxPointY + "  d=" + i3);
    }

    private void cleanMapView(ZoomMapSweeperView zoomMapSweeperView) {
        Image image = new Image((ReadableArray) null, (ReadableArray) null, "", 1, 1, 0, 1, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(image);
        zoomMapSweeperView.getMapSweeperView().refreshCommonSweeperViews(arrayList);
        zoomMapSweeperView.getMapSweeperView().cleanMapSweeperLines();
        zoomMapSweeperView.getMapSweeperView().cleanFloorAndSquare();
    }

    private void updataMinAndMaxPoint(MapPoint mapPoint) {
        if (this.minPointX == -1) {
            this.minPointX = mapPoint.f17616a;
        }
        if (this.minPointY == -1) {
            this.minPointY = mapPoint.b;
        }
        if (this.maxPointX == -1) {
            this.maxPointX = mapPoint.f17616a;
        }
        if (this.maxPointY == -1) {
            this.maxPointY = mapPoint.b;
        }
        if (mapPoint.f17616a < this.minPointX) {
            this.minPointX = mapPoint.f17616a;
        } else if (mapPoint.f17616a > this.maxPointX) {
            this.maxPointX = mapPoint.f17616a;
        }
        if (mapPoint.b < this.minPointY) {
            this.minPointY = mapPoint.b;
        } else if (mapPoint.b > this.maxPointY) {
            this.maxPointY = mapPoint.b;
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ZoomMapSweeperView zoomMapSweeperView) {
        super.onAfterUpdateTransaction(zoomMapSweeperView);
        zoomMapSweeperView.getRootView().invalidate();
        zoomMapSweeperView.invalidate();
        RnPluginLog.a("MiotMapSweeperViewManager-->onAfterUpdateTransaction...");
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        RnPluginLog.a("MiotMapSweeperViewManager-->onCatalystInstanceDestroy...");
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1001);
        }
        this.mZoomMapSweeperView = null;
    }

    private static class MsgObject {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f17622a;
        /* access modifiers changed from: private */
        public MapPoint b;

        public MsgObject(String str, MapPoint mapPoint) {
            this.f17622a = str;
            this.b = mapPoint;
        }
    }
}
