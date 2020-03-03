package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptGroup;
import android.support.v8.renderscript.Script;

class ScriptGroupThunker extends ScriptGroup {
    ScriptGroup mN;

    /* access modifiers changed from: package-private */
    public ScriptGroup getNObj() {
        return this.mN;
    }

    ScriptGroupThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public void setInput(Script.KernelID kernelID, Allocation allocation) {
        try {
            this.mN.setInput(kernelID.mN, ((AllocationThunker) allocation).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setOutput(Script.KernelID kernelID, Allocation allocation) {
        try {
            this.mN.setOutput(kernelID.mN, ((AllocationThunker) allocation).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void execute() {
        try {
            this.mN.execute();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static final class Builder {
        ScriptGroup.Builder bN;
        RenderScript mRS;

        Builder(RenderScript renderScript) {
            RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
            this.mRS = renderScript;
            try {
                this.bN = new ScriptGroup.Builder(renderScriptThunker.mN);
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public Builder addKernel(Script.KernelID kernelID) {
            try {
                this.bN.addKernel(kernelID.mN);
                return this;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public Builder addConnection(Type type, Script.KernelID kernelID, Script.FieldID fieldID) {
            try {
                this.bN.addConnection(((TypeThunker) type).getNObj(), kernelID.mN, fieldID.mN);
                return this;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public Builder addConnection(Type type, Script.KernelID kernelID, Script.KernelID kernelID2) {
            try {
                this.bN.addConnection(((TypeThunker) type).getNObj(), kernelID.mN, kernelID2.mN);
                return this;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }

        public ScriptGroupThunker create() {
            ScriptGroupThunker scriptGroupThunker = new ScriptGroupThunker(0, this.mRS);
            try {
                scriptGroupThunker.mN = this.bN.create();
                return scriptGroupThunker;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }
    }
}
