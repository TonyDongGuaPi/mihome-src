package com.taobao.weex.ui.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.text.Layout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXText;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTextView extends View implements IRenderResult<WXText>, IRenderStatus<WXText>, IWXTextView, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean mIsLabelSet = false;
    private WeakReference<WXText> mWeakReference;
    private Layout textLayout;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(447869789552223808L, "com/taobao/weex/ui/view/WXTextView", 46);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void holdComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        holdComponent((WXText) wXComponent);
        $jacocoInit[44] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXTextView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDraw(canvas);
        $jacocoInit[1] = true;
        canvas.save();
        $jacocoInit[2] = true;
        Layout textLayout2 = getTextLayout();
        if (textLayout2 == null) {
            $jacocoInit[3] = true;
        } else {
            $jacocoInit[4] = true;
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            $jacocoInit[5] = true;
            textLayout2.draw(canvas);
            $jacocoInit[6] = true;
        }
        canvas.restore();
        $jacocoInit[7] = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            onTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
        return onTouchEvent;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[12] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[13] = true;
        return wXGesture;
    }

    public CharSequence getText() {
        boolean[] $jacocoInit = $jacocoInit();
        CharSequence text = this.textLayout.getText();
        $jacocoInit[14] = true;
        return text;
    }

    public Layout getTextLayout() {
        boolean[] $jacocoInit = $jacocoInit();
        Layout layout = this.textLayout;
        $jacocoInit[15] = true;
        return layout;
    }

    public void setTextLayout(Layout layout) {
        boolean[] $jacocoInit = $jacocoInit();
        this.textLayout = layout;
        if (layout == null) {
            $jacocoInit[16] = true;
        } else if (this.mIsLabelSet) {
            $jacocoInit[17] = true;
        } else {
            $jacocoInit[18] = true;
            setContentDescription(layout.getText());
            $jacocoInit[19] = true;
        }
        if (this.mWeakReference == null) {
            $jacocoInit[20] = true;
        } else {
            $jacocoInit[21] = true;
            WXText wXText = (WXText) this.mWeakReference.get();
            if (wXText == null) {
                $jacocoInit[22] = true;
            } else {
                $jacocoInit[23] = true;
                wXText.readyToRender();
                $jacocoInit[24] = true;
            }
        }
        $jacocoInit[25] = true;
    }

    public void setAriaLabel(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            this.mIsLabelSet = true;
            $jacocoInit[26] = true;
            setContentDescription(str);
            $jacocoInit[27] = true;
        } else {
            this.mIsLabelSet = false;
            if (this.textLayout == null) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
                setContentDescription(this.textLayout.getText());
                $jacocoInit[30] = true;
            }
        }
        $jacocoInit[31] = true;
    }

    public void setTextColor(@ColorInt int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Layout textLayout2 = getTextLayout();
        if (textLayout2 == null) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            textLayout2.getPaint().setColor(i);
            $jacocoInit[34] = true;
        }
        $jacocoInit[35] = true;
    }

    public void holdComponent(WXText wXText) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWeakReference = new WeakReference<>(wXText);
        $jacocoInit[36] = true;
    }

    @Nullable
    public WXText getComponent() {
        WXText wXText;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWeakReference != null) {
            wXText = (WXText) this.mWeakReference.get();
            $jacocoInit[37] = true;
        } else {
            wXText = null;
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
        return wXText;
    }

    public void enableCopy(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (z) {
            $jacocoInit[40] = true;
            setOnLongClickListener(new View.OnLongClickListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXTextView this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-1423780821213236134L, "com/taobao/weex/ui/view/WXTextView$1", 8);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public boolean onLongClick(View view) {
                    boolean[] $jacocoInit = $jacocoInit();
                    PopupMenu popupMenu = new PopupMenu(this.this$0.getContext(), this.this$0);
                    final String str = "Copy";
                    try {
                        $jacocoInit[1] = true;
                        String string = this.this$0.getContext().getResources().getString(17039361);
                        $jacocoInit[2] = true;
                        str = string;
                    } catch (Throwable unused) {
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                    popupMenu.getMenu().add(str);
                    $jacocoInit[5] = true;
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(this) {
                        private static transient /* synthetic */ boolean[] $jacocoData;
                        final /* synthetic */ AnonymousClass1 this$1;

                        private static /* synthetic */ boolean[] $jacocoInit() {
                            boolean[] zArr = $jacocoData;
                            if (zArr != null) {
                                return zArr;
                            }
                            boolean[] a2 = Offline.a(-517068991887884584L, "com/taobao/weex/ui/view/WXTextView$1$1", 9);
                            $jacocoData = a2;
                            return a2;
                        }

                        {
                            boolean[] $jacocoInit = $jacocoInit();
                            this.this$1 = r2;
                            $jacocoInit[0] = true;
                        }

                        public boolean onMenuItemClick(MenuItem menuItem) {
                            boolean[] $jacocoInit = $jacocoInit();
                            if (str.equals(menuItem.getTitle())) {
                                $jacocoInit[1] = true;
                                String charSequence = this.this$1.this$0.getText().toString();
                                $jacocoInit[2] = true;
                                ClipboardManager clipboardManager = (ClipboardManager) this.this$1.this$0.getContext().getSystemService(ShareChannel.d);
                                if (clipboardManager == null) {
                                    $jacocoInit[3] = true;
                                } else {
                                    $jacocoInit[4] = true;
                                    ClipData newPlainText = ClipData.newPlainText(charSequence, charSequence);
                                    $jacocoInit[5] = true;
                                    clipboardManager.setPrimaryClip(newPlainText);
                                    $jacocoInit[6] = true;
                                }
                                $jacocoInit[7] = true;
                                return true;
                            }
                            $jacocoInit[8] = true;
                            return false;
                        }
                    });
                    $jacocoInit[6] = true;
                    popupMenu.show();
                    $jacocoInit[7] = true;
                    return true;
                }
            });
            $jacocoInit[41] = true;
        } else {
            setOnLongClickListener((View.OnLongClickListener) null);
            $jacocoInit[42] = true;
        }
        $jacocoInit[43] = true;
    }
}
