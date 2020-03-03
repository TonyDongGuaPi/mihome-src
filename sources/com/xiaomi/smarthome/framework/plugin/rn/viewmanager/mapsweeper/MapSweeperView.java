package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSweeperView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private Context f17617a;
    private MapBackgroundView b;
    private MapView c;
    private Map<String, CommonSweeperView> d;
    private Map<String, Image> e;
    private int f;
    private int g;
    private float h;
    private boolean i;
    private final Runnable j;

    private void b(Context context) {
    }

    public MapSweeperView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MapSweeperView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MapSweeperView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.d = new HashMap();
        this.e = new HashMap();
        this.h = 1.0f;
        this.i = true;
        this.j = new Runnable() {
            public void run() {
                MapSweeperView.this.measure(View.MeasureSpec.makeMeasureSpec(MapSweeperView.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(MapSweeperView.this.getHeight(), 1073741824));
                MapSweeperView.this.layout(MapSweeperView.this.getLeft(), MapSweeperView.this.getTop(), MapSweeperView.this.getRight(), MapSweeperView.this.getBottom());
            }
        };
        this.f17617a = context;
        a(context);
        b(context);
    }

    private void a(Context context) {
        this.b = new MapBackgroundView(context);
        this.c = new MapView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        addView(this.b, layoutParams);
        addView(this.c, layoutParams);
    }

    public void refreshCommonSweeperViews(List<Image> list) {
        if (list != null && list.size() != 0) {
            if (this.d == null) {
                this.d = new HashMap();
            }
            if (this.e == null) {
                this.e = new HashMap();
            }
            a();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.e.put(list.get(i2).c, list.get(i2));
            }
            for (Map.Entry next : this.e.entrySet()) {
                String str = (String) next.getKey();
                Image image = (Image) next.getValue();
                CommonSweeperView commonSweeperView = this.d.get(str);
                if (image != null) {
                    if (commonSweeperView == null) {
                        CommonSweeperView commonSweeperView2 = new CommonSweeperView(this.f17617a);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                        commonSweeperView2.initSweeperView(image);
                        addView(commonSweeperView2, layoutParams);
                        this.d.put(image.c, commonSweeperView2);
                        if (!this.i) {
                            a(commonSweeperView2, image);
                        }
                    } else {
                        commonSweeperView.updateSweeperView(image);
                        a(commonSweeperView, image);
                    }
                } else if (commonSweeperView != null) {
                    this.d.remove(str);
                    removeView(commonSweeperView);
                }
            }
        }
    }

    private void a(CommonSweeperView commonSweeperView, Image image) {
        if (image != null && commonSweeperView != null && image.d >= 0 && image.e >= 0) {
            MapPoint mapPoint = new MapPoint(image.d, image.e, 1);
            commonSweeperView.setCurrentPosition(image.d, image.e);
            MapPoint.a(mapPoint, this.h);
            commonSweeperView.smoothScrollTo(a(mapPoint.a()), a(mapPoint.b()));
        }
    }

    private void a() {
        for (Map.Entry<String, Image> key : this.e.entrySet()) {
            this.e.put((String) key.getKey(), (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.g, 1073741824);
        this.h = ((float) this.f) / 255.0f;
        super.onMeasure(makeMeasureSpec2, makeMeasureSpec);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.i) {
            this.i = false;
            for (Map.Entry next : this.e.entrySet()) {
                Image image = (Image) next.getValue();
                CommonSweeperView commonSweeperView = this.d.get((String) next.getKey());
                if (commonSweeperView != null) {
                    a(commonSweeperView, image);
                }
            }
        }
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.j);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.i("lvjie", "MapSweeperView-->mIsFirstInit=" + this.i + "   hasWindowFocus=" + z);
    }

    public void moveSweeper(MapPoint mapPoint) {
        if (mapPoint != null) {
            this.c.drawNewPoint(MapPoint.a(mapPoint, this.h));
        }
    }

    public void moveSweeper(MapPoint mapPoint, String str) {
        if (mapPoint != null) {
            CommonSweeperView commonSweeperView = this.d.get(str);
            if (commonSweeperView != null) {
                commonSweeperView.setCurrentPosition(mapPoint.f17616a, mapPoint.b);
            }
            MapPoint a2 = MapPoint.a(mapPoint, this.h);
            if (commonSweeperView != null) {
                commonSweeperView.smoothScrollTo(a(a2.a()), a(a2.b()));
            }
            this.c.drawNewPoint(a2);
        }
    }

    public void moveSweeper(List<MapPoint> list, String str) {
        if (list != null && list.size() != 0) {
            CommonSweeperView commonSweeperView = this.d.get(str);
            int size = list.size();
            if (commonSweeperView != null) {
                int i2 = size - 1;
                commonSweeperView.setCurrentPosition(list.get(i2).f17616a, list.get(i2).b);
            }
            List<MapPoint> a2 = MapPoint.a(list, this.h);
            this.c.drawHistoryPoints(a2);
            if (commonSweeperView != null) {
                int i3 = size - 1;
                commonSweeperView.smoothScrollTo(a(a2.get(i3).a()), a(a2.get(i3).b()));
            }
        }
    }

    public void addNewPointsToMapBackgroundView(List<MapPoint> list, List<MapPoint> list2) {
        this.b.addNewPointsToMapBackgroundView(MapPoint.a(list, this.h), MapPoint.a(list2, this.h));
    }

    private int a(int i2) {
        return (this.g >> 1) - i2;
    }

    public void drawHistoryPoints(List<MapPoint> list) {
        if (list != null && list.size() != 0) {
            this.c.drawHistoryPoints(MapPoint.a(list, this.h));
        }
    }

    public void cleanMapSweeperLines() {
        if (this.c != null) {
            this.c.cleanPath();
        }
    }

    public void cleanFloorAndSquare() {
        if (this.b != null) {
            this.b.cleanFloorAndSquare();
        }
    }

    public void setWallColor(int i2) {
        this.b.setSquareColor(i2);
    }

    public void setFloorColor(int i2) {
        this.b.setFloorColor(i2);
    }

    public void setLineColor(int i2) {
        this.c.setLineColor(i2);
    }

    public void setWidth(int i2) {
        this.g = i2;
    }

    public void setHeight(int i2) {
        this.f = i2;
    }

    public int getViewHeight() {
        return this.f;
    }

    public int getViewWidth() {
        return this.g;
    }

    public float getMapPointScale() {
        return this.h;
    }

    public MapPoint getImagePostion(String str) {
        CommonSweeperView commonSweeperView = this.d.get(str);
        if (commonSweeperView != null) {
            return commonSweeperView.getCurrentPosition();
        }
        return null;
    }

    public void updateViewByZoom(float f2) {
        if (this.d != null) {
            for (Map.Entry<String, CommonSweeperView> value : this.d.entrySet()) {
                CommonSweeperView commonSweeperView = (CommonSweeperView) value.getValue();
                if (commonSweeperView != null) {
                    commonSweeperView.setZoom(f2);
                }
            }
        }
    }

    public void startCommonSweeperViewAnimatorDelayed(long j2) {
        if (this.d != null) {
            for (Map.Entry<String, CommonSweeperView> value : this.d.entrySet()) {
                CommonSweeperView commonSweeperView = (CommonSweeperView) value.getValue();
                if (commonSweeperView != null) {
                    commonSweeperView.startSweeperCircularAnimatorDelayed(j2);
                }
            }
        }
    }
}
