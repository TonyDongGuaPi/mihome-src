package com.xiaomi.smarthome.miio.camera.face.model;

import android.text.TextUtils;
import android.view.View;
import java.util.ArrayList;

public class FaceIdMetaResult {
    public static String face_tips_acquaintrance1;
    public static String face_tips_acquaintrance2;
    public static String face_tips_cry;
    public static String face_tips_stranger;
    public static String face_tips_stranger_acquaintrance;
    public static String face_tips_stranger_cry;
    public static String face_tips_stranger_min;
    public static String face_tips_strangers;
    public boolean babyCrying;
    public String deliverType;
    public String extraInfo;
    public FaceInfo[] faceInfoMetas;
    public String fileId;
    public boolean foundPeople;
    public boolean isDeliver;
    public int matchedCount = 0;
    public String tips;
    public String tipsMin;
    public int unMatchedCount = 0;

    public void calculateTips(View view, View view2) {
        if ((this.faceInfoMetas != null && this.faceInfoMetas.length != 0) || this.babyCrying) {
            if (TextUtils.isEmpty(this.tips) || this.faceInfoMetas == null) {
                ArrayList arrayList = new ArrayList();
                this.unMatchedCount = 0;
                this.matchedCount = 0;
                if (this.faceInfoMetas != null) {
                    int i = 0;
                    for (FaceInfo faceInfo : this.faceInfoMetas) {
                        if (i > 1) {
                            break;
                        }
                        i++;
                        if (!faceInfo.matched) {
                            this.unMatchedCount++;
                            if (view != null) {
                                view.setVisibility(0);
                            }
                        } else if (this.matchedCount <= 0 || !faceInfo.figureName.equals(arrayList.get(0))) {
                            this.matchedCount++;
                            arrayList.add(faceInfo.figureName);
                            if (view2 != null) {
                                view2.setVisibility(0);
                            }
                        }
                    }
                }
                if (this.unMatchedCount > 0) {
                    this.tips = face_tips_stranger_cry;
                    this.tipsMin = face_tips_stranger_min;
                } else {
                    this.tips = face_tips_cry;
                    if (this.matchedCount > 0) {
                        this.tipsMin = String.format(face_tips_acquaintrance1, new Object[]{arrayList.get(0)});
                    }
                }
                if (!this.babyCrying) {
                    switch (this.matchedCount) {
                        case 0:
                            if (this.unMatchedCount > 1) {
                                this.tips = String.format(face_tips_strangers, new Object[]{Integer.valueOf(this.unMatchedCount)});
                                return;
                            }
                            this.tips = String.format(face_tips_stranger, new Object[]{Integer.valueOf(this.unMatchedCount)});
                            return;
                        case 1:
                            if (this.unMatchedCount > 0) {
                                this.tips = String.format(face_tips_stranger_acquaintrance, new Object[]{arrayList.get(0)});
                                return;
                            }
                            this.tips = String.format(face_tips_acquaintrance1, new Object[]{arrayList.get(0)});
                            return;
                        case 2:
                            this.tips = String.format(face_tips_acquaintrance2, new Object[]{arrayList.get(0), arrayList.get(1)});
                            return;
                        default:
                            return;
                    }
                }
            } else {
                FaceInfo[] faceInfoArr = this.faceInfoMetas;
                int length = faceInfoArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    FaceInfo faceInfo2 = faceInfoArr[i2];
                    if (i3 <= 1) {
                        i3++;
                        if (faceInfo2.matched) {
                            if (view2 != null) {
                                view2.setVisibility(0);
                            }
                        } else if (view != null) {
                            view.setVisibility(0);
                        }
                        i2++;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
