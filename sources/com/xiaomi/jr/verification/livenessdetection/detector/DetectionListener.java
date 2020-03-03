package com.xiaomi.jr.verification.livenessdetection.detector;

public interface DetectionListener {
    void onDetectionFailed(DetectionFailedType detectionFailedType);

    void onDetectionSuccess();

    void onFrameDetected(long j, DetectionFrame detectionFrame);
}
