package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import java.io.Serializable;
import miuipub.reflect.Field;

public class JpegComponent implements Serializable {
    private static final long serialVersionUID = 61121257899091914L;
    private final int _componentId;
    private final int _quantizationTableNumber;
    private final int _samplingFactorByte;

    public JpegComponent(int i, int i2, int i3) {
        this._componentId = i;
        this._samplingFactorByte = i2;
        this._quantizationTableNumber = i3;
    }

    public int getComponentId() {
        return this._componentId;
    }

    @NotNull
    public String getComponentName() {
        switch (this._componentId) {
            case 1:
                return "Y";
            case 2:
                return "Cb";
            case 3:
                return "Cr";
            case 4:
                return Field.e;
            case 5:
                return "Q";
            default:
                return String.format("Unknown (%s)", new Object[]{Integer.valueOf(this._componentId)});
        }
    }

    public int getQuantizationTableNumber() {
        return this._quantizationTableNumber;
    }

    public int getHorizontalSamplingFactor() {
        return (this._samplingFactorByte >> 4) & 15;
    }

    public int getVerticalSamplingFactor() {
        return this._samplingFactorByte & 15;
    }

    @NotNull
    public String toString() {
        return String.format("Quantization table %d, Sampling factors %d horiz/%d vert", new Object[]{Integer.valueOf(this._quantizationTableNumber), Integer.valueOf(getHorizontalSamplingFactor()), Integer.valueOf(getVerticalSamplingFactor())});
    }
}
