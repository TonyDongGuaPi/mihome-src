package android.support.v8.renderscript;

import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.support.v8.renderscript.Element;

class ElementThunker extends Element {
    Element mN;

    /* access modifiers changed from: package-private */
    public Element getNObj() {
        return this.mN;
    }

    public int getBytesSize() {
        try {
            return this.mN.getBytesSize();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public int getVectorSize() {
        try {
            return this.mN.getVectorSize();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    static Element.DataKind convertKind(Element.DataKind dataKind) {
        switch (dataKind) {
            case USER:
                return Element.DataKind.USER;
            case PIXEL_L:
                return Element.DataKind.PIXEL_L;
            case PIXEL_A:
                return Element.DataKind.PIXEL_A;
            case PIXEL_LA:
                return Element.DataKind.PIXEL_LA;
            case PIXEL_RGB:
                return Element.DataKind.PIXEL_RGB;
            case PIXEL_RGBA:
                return Element.DataKind.PIXEL_RGBA;
            case PIXEL_DEPTH:
                return Element.DataKind.PIXEL_DEPTH;
            case PIXEL_YUV:
                return Element.DataKind.PIXEL_YUV;
            default:
                return null;
        }
    }

    static Element.DataType convertType(Element.DataType dataType) {
        switch (dataType) {
            case NONE:
                return Element.DataType.NONE;
            case FLOAT_32:
                return Element.DataType.FLOAT_32;
            case FLOAT_64:
                return Element.DataType.FLOAT_64;
            case SIGNED_8:
                return Element.DataType.SIGNED_8;
            case SIGNED_16:
                return Element.DataType.SIGNED_16;
            case SIGNED_32:
                return Element.DataType.SIGNED_32;
            case SIGNED_64:
                return Element.DataType.SIGNED_64;
            case UNSIGNED_8:
                return Element.DataType.UNSIGNED_8;
            case UNSIGNED_16:
                return Element.DataType.UNSIGNED_16;
            case UNSIGNED_32:
                return Element.DataType.UNSIGNED_32;
            case UNSIGNED_64:
                return Element.DataType.UNSIGNED_64;
            case BOOLEAN:
                return Element.DataType.BOOLEAN;
            case MATRIX_4X4:
                return Element.DataType.MATRIX_4X4;
            case MATRIX_3X3:
                return Element.DataType.MATRIX_3X3;
            case MATRIX_2X2:
                return Element.DataType.MATRIX_2X2;
            case RS_ELEMENT:
                return Element.DataType.RS_ELEMENT;
            case RS_TYPE:
                return Element.DataType.RS_TYPE;
            case RS_ALLOCATION:
                return Element.DataType.RS_ALLOCATION;
            case RS_SAMPLER:
                return Element.DataType.RS_SAMPLER;
            case RS_SCRIPT:
                return Element.DataType.RS_SCRIPT;
            default:
                return null;
        }
    }

    public boolean isComplex() {
        try {
            return this.mN.isComplex();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public int getSubElementCount() {
        try {
            return this.mN.getSubElementCount();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Element getSubElement(int i) {
        try {
            return new ElementThunker(this.mRS, this.mN.getSubElement(i));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public String getSubElementName(int i) {
        try {
            return this.mN.getSubElementName(i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public int getSubElementArraySize(int i) {
        try {
            return this.mN.getSubElementArraySize(i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public int getSubElementOffsetBytes(int i) {
        try {
            return this.mN.getSubElementOffsetBytes(i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Element.DataType getDataType() {
        return this.mType;
    }

    public Element.DataKind getDataKind() {
        return this.mKind;
    }

    ElementThunker(RenderScript renderScript, android.renderscript.Element element) {
        super(0, renderScript);
        this.mN = element;
    }

    static Element create(RenderScript renderScript, Element.DataType dataType) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        android.renderscript.Element element = null;
        try {
            switch (dataType) {
                case FLOAT_32:
                    element = android.renderscript.Element.F32(renderScriptThunker.mN);
                    break;
                case FLOAT_64:
                    element = android.renderscript.Element.F64(renderScriptThunker.mN);
                    break;
                case SIGNED_8:
                    element = android.renderscript.Element.I8(renderScriptThunker.mN);
                    break;
                case SIGNED_16:
                    element = android.renderscript.Element.I16(renderScriptThunker.mN);
                    break;
                case SIGNED_32:
                    element = android.renderscript.Element.I32(renderScriptThunker.mN);
                    break;
                case SIGNED_64:
                    element = android.renderscript.Element.I64(renderScriptThunker.mN);
                    break;
                case UNSIGNED_8:
                    element = android.renderscript.Element.U8(renderScriptThunker.mN);
                    break;
                case UNSIGNED_16:
                    element = android.renderscript.Element.U16(renderScriptThunker.mN);
                    break;
                case UNSIGNED_32:
                    element = android.renderscript.Element.U32(renderScriptThunker.mN);
                    break;
                case UNSIGNED_64:
                    element = android.renderscript.Element.U64(renderScriptThunker.mN);
                    break;
                case BOOLEAN:
                    element = android.renderscript.Element.BOOLEAN(renderScriptThunker.mN);
                    break;
                case MATRIX_4X4:
                    element = android.renderscript.Element.MATRIX_4X4(renderScriptThunker.mN);
                    break;
                case MATRIX_3X3:
                    element = android.renderscript.Element.MATRIX_3X3(renderScriptThunker.mN);
                    break;
                case MATRIX_2X2:
                    element = android.renderscript.Element.MATRIX_2X2(renderScriptThunker.mN);
                    break;
                case RS_ELEMENT:
                    element = android.renderscript.Element.ELEMENT(renderScriptThunker.mN);
                    break;
                case RS_TYPE:
                    element = android.renderscript.Element.TYPE(renderScriptThunker.mN);
                    break;
                case RS_ALLOCATION:
                    element = android.renderscript.Element.ALLOCATION(renderScriptThunker.mN);
                    break;
                case RS_SAMPLER:
                    element = android.renderscript.Element.SAMPLER(renderScriptThunker.mN);
                    break;
                case RS_SCRIPT:
                    element = android.renderscript.Element.SCRIPT(renderScriptThunker.mN);
                    break;
            }
            return new ElementThunker(renderScript, element);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Element createVector(RenderScript renderScript, Element.DataType dataType, int i) {
        try {
            return new ElementThunker(renderScript, android.renderscript.Element.createVector(((RenderScriptThunker) renderScript).mN, convertType(dataType), i));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Element createPixel(RenderScript renderScript, Element.DataType dataType, Element.DataKind dataKind) {
        try {
            return new ElementThunker(renderScript, android.renderscript.Element.createPixel(((RenderScriptThunker) renderScript).mN, convertType(dataType), convertKind(dataKind)));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public boolean isCompatible(Element element) {
        try {
            return ((ElementThunker) element).mN.isCompatible(this.mN);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    static class BuilderThunker {
        Element.Builder mN;

        public BuilderThunker(RenderScript renderScript) {
            try {
                this.mN = new Element.Builder(((RenderScriptThunker) renderScript).mN);
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public void add(Element element, String str, int i) {
            try {
                this.mN.add(((ElementThunker) element).mN, str, i);
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public Element create(RenderScript renderScript) {
            try {
                return new ElementThunker(renderScript, this.mN.create());
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }
    }
}
