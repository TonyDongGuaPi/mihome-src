package android.support.v8.renderscript;

import android.renderscript.BaseObj;
import android.renderscript.RSRuntimeException;
import android.renderscript.Sampler;
import android.support.v8.renderscript.Sampler;

class SamplerThunker extends Sampler {
    Sampler mN;

    protected SamplerThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    /* access modifiers changed from: package-private */
    public BaseObj getNObj() {
        return this.mN;
    }

    static Sampler.Value convertValue(Sampler.Value value) {
        switch (value) {
            case NEAREST:
                return Sampler.Value.NEAREST;
            case LINEAR:
                return Sampler.Value.LINEAR;
            case LINEAR_MIP_LINEAR:
                return Sampler.Value.LINEAR_MIP_LINEAR;
            case LINEAR_MIP_NEAREST:
                return Sampler.Value.LINEAR_MIP_NEAREST;
            case WRAP:
                return Sampler.Value.WRAP;
            case CLAMP:
                return Sampler.Value.CLAMP;
            case MIRRORED_REPEAT:
                return Sampler.Value.MIRRORED_REPEAT;
            default:
                return null;
        }
    }

    public static class Builder {
        float mAniso;
        Sampler.Value mMag = Sampler.Value.NEAREST;
        Sampler.Value mMin = Sampler.Value.NEAREST;
        RenderScriptThunker mRS;
        Sampler.Value mWrapR = Sampler.Value.WRAP;
        Sampler.Value mWrapS = Sampler.Value.WRAP;
        Sampler.Value mWrapT = Sampler.Value.WRAP;

        public Builder(RenderScriptThunker renderScriptThunker) {
            this.mRS = renderScriptThunker;
        }

        public void setMinification(Sampler.Value value) {
            if (value == Sampler.Value.NEAREST || value == Sampler.Value.LINEAR || value == Sampler.Value.LINEAR_MIP_LINEAR || value == Sampler.Value.LINEAR_MIP_NEAREST) {
                this.mMin = value;
                return;
            }
            throw new IllegalArgumentException("Invalid value");
        }

        public void setMagnification(Sampler.Value value) {
            if (value == Sampler.Value.NEAREST || value == Sampler.Value.LINEAR) {
                this.mMag = value;
                return;
            }
            throw new IllegalArgumentException("Invalid value");
        }

        public void setWrapS(Sampler.Value value) {
            if (value == Sampler.Value.WRAP || value == Sampler.Value.CLAMP || value == Sampler.Value.MIRRORED_REPEAT) {
                this.mWrapS = value;
                return;
            }
            throw new IllegalArgumentException("Invalid value");
        }

        public void setWrapT(Sampler.Value value) {
            if (value == Sampler.Value.WRAP || value == Sampler.Value.CLAMP || value == Sampler.Value.MIRRORED_REPEAT) {
                this.mWrapT = value;
                return;
            }
            throw new IllegalArgumentException("Invalid value");
        }

        public void setAnisotropy(float f) {
            if (f >= 0.0f) {
                this.mAniso = f;
                return;
            }
            throw new IllegalArgumentException("Invalid value");
        }

        public Sampler create() {
            this.mRS.validate();
            try {
                Sampler.Builder builder = new Sampler.Builder(this.mRS.mN);
                builder.setMinification(SamplerThunker.convertValue(this.mMin));
                builder.setMagnification(SamplerThunker.convertValue(this.mMag));
                builder.setWrapS(SamplerThunker.convertValue(this.mWrapS));
                builder.setWrapT(SamplerThunker.convertValue(this.mWrapT));
                builder.setAnisotropy(this.mAniso);
                android.renderscript.Sampler create = builder.create();
                SamplerThunker samplerThunker = new SamplerThunker(0, this.mRS);
                samplerThunker.mMin = this.mMin;
                samplerThunker.mMag = this.mMag;
                samplerThunker.mWrapS = this.mWrapS;
                samplerThunker.mWrapT = this.mWrapT;
                samplerThunker.mWrapR = this.mWrapR;
                samplerThunker.mAniso = this.mAniso;
                samplerThunker.mN = create;
                return samplerThunker;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }
    }
}
