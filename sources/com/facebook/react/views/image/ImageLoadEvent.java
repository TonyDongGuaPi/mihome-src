package com.facebook.react.views.image;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final int mEventType;
    private final int mHeight;
    @Nullable
    private final String mImageError;
    @Nullable
    private final String mImageUri;
    private final int mWidth;

    public ImageLoadEvent(int i, int i2) {
        this(i, i2, (String) null);
    }

    public ImageLoadEvent(int i, int i2, boolean z, String str) {
        this(i, i2, (String) null, 0, 0, str);
    }

    public ImageLoadEvent(int i, int i2, String str) {
        this(i, i2, str, 0, 0, (String) null);
    }

    public ImageLoadEvent(int i, int i2, @Nullable String str, int i3, int i4) {
        this(i, i2, str, i3, i4, (String) null);
    }

    public ImageLoadEvent(int i, int i2, @Nullable String str, int i3, int i4, @Nullable String str2) {
        super(i);
        this.mEventType = i2;
        this.mImageUri = str;
        this.mWidth = i3;
        this.mHeight = i4;
        this.mImageError = str2;
    }

    public static String eventNameForType(int i) {
        switch (i) {
            case 1:
                return "topError";
            case 2:
                return "topLoad";
            case 3:
                return "topLoadEnd";
            case 4:
                return "topLoadStart";
            case 5:
                return "topProgress";
            default:
                throw new IllegalStateException("Invalid image event: " + Integer.toString(i));
        }
    }

    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap writableMap;
        if (this.mImageUri != null || this.mEventType == 2 || this.mEventType == 1) {
            writableMap = Arguments.createMap();
            if (this.mImageUri != null) {
                writableMap.putString("uri", this.mImageUri);
            }
            if (this.mEventType == 2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("width", (double) this.mWidth);
                createMap.putDouble("height", (double) this.mHeight);
                if (this.mImageUri != null) {
                    createMap.putString("url", this.mImageUri);
                }
                writableMap.putMap("source", createMap);
            } else if (this.mEventType == 1) {
                writableMap.putString("error", this.mImageError);
            }
        } else {
            writableMap = null;
        }
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), writableMap);
    }
}
