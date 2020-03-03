package android.support.v8.renderscript;

import android.support.v8.renderscript.Script;
import android.support.v8.renderscript.ScriptGroupThunker;
import java.util.ArrayList;

public class ScriptGroup extends BaseObj {
    IO[] mInputs;
    IO[] mOutputs;

    static class IO {
        Allocation mAllocation;
        Script.KernelID mKID;

        IO(Script.KernelID kernelID) {
            this.mKID = kernelID;
        }
    }

    static class ConnectLine {
        Type mAllocationType;
        Script.KernelID mFrom;
        Script.FieldID mToF;
        Script.KernelID mToK;

        ConnectLine(Type type, Script.KernelID kernelID, Script.KernelID kernelID2) {
            this.mFrom = kernelID;
            this.mToK = kernelID2;
            this.mAllocationType = type;
        }

        ConnectLine(Type type, Script.KernelID kernelID, Script.FieldID fieldID) {
            this.mFrom = kernelID;
            this.mToF = fieldID;
            this.mAllocationType = type;
        }
    }

    static class Node {
        int dagNumber;
        ArrayList<ConnectLine> mInputs = new ArrayList<>();
        ArrayList<Script.KernelID> mKernels = new ArrayList<>();
        Node mNext;
        ArrayList<ConnectLine> mOutputs = new ArrayList<>();
        Script mScript;

        Node(Script script) {
            this.mScript = script;
        }
    }

    ScriptGroup(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public void setInput(Script.KernelID kernelID, Allocation allocation) {
        for (int i = 0; i < this.mInputs.length; i++) {
            if (this.mInputs[i].mKID == kernelID) {
                this.mInputs[i].mAllocation = allocation;
                this.mRS.nScriptGroupSetInput(getID(this.mRS), kernelID.getID(this.mRS), this.mRS.safeID(allocation));
                return;
            }
        }
        throw new RSIllegalArgumentException("Script not found");
    }

    public void setOutput(Script.KernelID kernelID, Allocation allocation) {
        for (int i = 0; i < this.mOutputs.length; i++) {
            if (this.mOutputs[i].mKID == kernelID) {
                this.mOutputs[i].mAllocation = allocation;
                this.mRS.nScriptGroupSetOutput(getID(this.mRS), kernelID.getID(this.mRS), this.mRS.safeID(allocation));
                return;
            }
        }
        throw new RSIllegalArgumentException("Script not found");
    }

    public void execute() {
        this.mRS.nScriptGroupExecute(getID(this.mRS));
    }

    public static final class Builder {
        private int mKernelCount;
        private ArrayList<ConnectLine> mLines = new ArrayList<>();
        private ArrayList<Node> mNodes = new ArrayList<>();
        private RenderScript mRS;
        private ScriptGroupThunker.Builder mT;

        public Builder(RenderScript renderScript) {
            if (RenderScript.isNative) {
                this.mT = new ScriptGroupThunker.Builder(renderScript);
            }
            this.mRS = renderScript;
        }

        private void validateCycle(Node node, Node node2) {
            for (int i = 0; i < node.mOutputs.size(); i++) {
                ConnectLine connectLine = node.mOutputs.get(i);
                if (connectLine.mToK != null) {
                    Node findNode = findNode(connectLine.mToK.mScript);
                    if (!findNode.equals(node2)) {
                        validateCycle(findNode, node2);
                    } else {
                        throw new RSInvalidStateException("Loops in group not allowed.");
                    }
                }
                if (connectLine.mToF != null) {
                    Node findNode2 = findNode(connectLine.mToF.mScript);
                    if (!findNode2.equals(node2)) {
                        validateCycle(findNode2, node2);
                    } else {
                        throw new RSInvalidStateException("Loops in group not allowed.");
                    }
                }
            }
        }

        private void mergeDAGs(int i, int i2) {
            for (int i3 = 0; i3 < this.mNodes.size(); i3++) {
                if (this.mNodes.get(i3).dagNumber == i2) {
                    this.mNodes.get(i3).dagNumber = i;
                }
            }
        }

        private void validateDAGRecurse(Node node, int i) {
            if (node.dagNumber == 0 || node.dagNumber == i) {
                node.dagNumber = i;
                for (int i2 = 0; i2 < node.mOutputs.size(); i2++) {
                    ConnectLine connectLine = node.mOutputs.get(i2);
                    if (connectLine.mToK != null) {
                        validateDAGRecurse(findNode(connectLine.mToK.mScript), i);
                    }
                    if (connectLine.mToF != null) {
                        validateDAGRecurse(findNode(connectLine.mToF.mScript), i);
                    }
                }
                return;
            }
            mergeDAGs(node.dagNumber, i);
        }

        private void validateDAG() {
            int i = 0;
            for (int i2 = 0; i2 < this.mNodes.size(); i2++) {
                Node node = this.mNodes.get(i2);
                if (node.mInputs.size() == 0) {
                    if (node.mOutputs.size() != 0 || this.mNodes.size() <= 1) {
                        validateDAGRecurse(node, i2 + 1);
                    } else {
                        throw new RSInvalidStateException("Groups cannot contain unconnected scripts");
                    }
                }
            }
            int i3 = this.mNodes.get(0).dagNumber;
            while (i < this.mNodes.size()) {
                if (this.mNodes.get(i).dagNumber == i3) {
                    i++;
                } else {
                    throw new RSInvalidStateException("Multiple DAGs in group not allowed.");
                }
            }
        }

        private Node findNode(Script script) {
            for (int i = 0; i < this.mNodes.size(); i++) {
                if (script == this.mNodes.get(i).mScript) {
                    return this.mNodes.get(i);
                }
            }
            return null;
        }

        private Node findNode(Script.KernelID kernelID) {
            for (int i = 0; i < this.mNodes.size(); i++) {
                Node node = this.mNodes.get(i);
                for (int i2 = 0; i2 < node.mKernels.size(); i2++) {
                    if (kernelID == node.mKernels.get(i2)) {
                        return node;
                    }
                }
            }
            return null;
        }

        public Builder addKernel(Script.KernelID kernelID) {
            if (this.mT != null) {
                this.mT.addKernel(kernelID);
                return this;
            } else if (this.mLines.size() != 0) {
                throw new RSInvalidStateException("Kernels may not be added once connections exist.");
            } else if (findNode(kernelID) != null) {
                return this;
            } else {
                this.mKernelCount++;
                Node findNode = findNode(kernelID.mScript);
                if (findNode == null) {
                    findNode = new Node(kernelID.mScript);
                    this.mNodes.add(findNode);
                }
                findNode.mKernels.add(kernelID);
                return this;
            }
        }

        public Builder addConnection(Type type, Script.KernelID kernelID, Script.FieldID fieldID) {
            if (this.mT != null) {
                this.mT.addConnection(type, kernelID, fieldID);
                return this;
            }
            Node findNode = findNode(kernelID);
            if (findNode != null) {
                Node findNode2 = findNode(fieldID.mScript);
                if (findNode2 != null) {
                    ConnectLine connectLine = new ConnectLine(type, kernelID, fieldID);
                    this.mLines.add(new ConnectLine(type, kernelID, fieldID));
                    findNode.mOutputs.add(connectLine);
                    findNode2.mInputs.add(connectLine);
                    validateCycle(findNode, findNode);
                    return this;
                }
                throw new RSInvalidStateException("To script not found.");
            }
            throw new RSInvalidStateException("From script not found.");
        }

        public Builder addConnection(Type type, Script.KernelID kernelID, Script.KernelID kernelID2) {
            if (this.mT != null) {
                this.mT.addConnection(type, kernelID, kernelID2);
                return this;
            }
            Node findNode = findNode(kernelID);
            if (findNode != null) {
                Node findNode2 = findNode(kernelID2);
                if (findNode2 != null) {
                    ConnectLine connectLine = new ConnectLine(type, kernelID, kernelID2);
                    this.mLines.add(new ConnectLine(type, kernelID, kernelID2));
                    findNode.mOutputs.add(connectLine);
                    findNode2.mInputs.add(connectLine);
                    validateCycle(findNode, findNode);
                    return this;
                }
                throw new RSInvalidStateException("To script not found.");
            }
            throw new RSInvalidStateException("From script not found.");
        }

        public ScriptGroup create() {
            if (this.mT != null) {
                return this.mT.create();
            }
            if (this.mNodes.size() != 0) {
                for (int i = 0; i < this.mNodes.size(); i++) {
                    this.mNodes.get(i).dagNumber = 0;
                }
                validateDAG();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                int[] iArr = new int[this.mKernelCount];
                int i2 = 0;
                int i3 = 0;
                while (i2 < this.mNodes.size()) {
                    Node node = this.mNodes.get(i2);
                    int i4 = i3;
                    int i5 = 0;
                    while (i5 < node.mKernels.size()) {
                        Script.KernelID kernelID = node.mKernels.get(i5);
                        int i6 = i4 + 1;
                        iArr[i4] = kernelID.getID(this.mRS);
                        boolean z = false;
                        for (int i7 = 0; i7 < node.mInputs.size(); i7++) {
                            if (node.mInputs.get(i7).mToK == kernelID) {
                                z = true;
                            }
                        }
                        boolean z2 = false;
                        for (int i8 = 0; i8 < node.mOutputs.size(); i8++) {
                            if (node.mOutputs.get(i8).mFrom == kernelID) {
                                z2 = true;
                            }
                        }
                        if (!z) {
                            arrayList.add(new IO(kernelID));
                        }
                        if (!z2) {
                            arrayList2.add(new IO(kernelID));
                        }
                        i5++;
                        i4 = i6;
                    }
                    i2++;
                    i3 = i4;
                }
                if (i3 == this.mKernelCount) {
                    int[] iArr2 = new int[this.mLines.size()];
                    int[] iArr3 = new int[this.mLines.size()];
                    int[] iArr4 = new int[this.mLines.size()];
                    int[] iArr5 = new int[this.mLines.size()];
                    for (int i9 = 0; i9 < this.mLines.size(); i9++) {
                        ConnectLine connectLine = this.mLines.get(i9);
                        iArr2[i9] = connectLine.mFrom.getID(this.mRS);
                        if (connectLine.mToK != null) {
                            iArr3[i9] = connectLine.mToK.getID(this.mRS);
                        }
                        if (connectLine.mToF != null) {
                            iArr4[i9] = connectLine.mToF.getID(this.mRS);
                        }
                        iArr5[i9] = connectLine.mAllocationType.getID(this.mRS);
                    }
                    int nScriptGroupCreate = this.mRS.nScriptGroupCreate(iArr, iArr2, iArr3, iArr4, iArr5);
                    if (nScriptGroupCreate != 0) {
                        ScriptGroup scriptGroup = new ScriptGroup(nScriptGroupCreate, this.mRS);
                        scriptGroup.mOutputs = new IO[arrayList2.size()];
                        for (int i10 = 0; i10 < arrayList2.size(); i10++) {
                            scriptGroup.mOutputs[i10] = (IO) arrayList2.get(i10);
                        }
                        scriptGroup.mInputs = new IO[arrayList.size()];
                        for (int i11 = 0; i11 < arrayList.size(); i11++) {
                            scriptGroup.mInputs[i11] = (IO) arrayList.get(i11);
                        }
                        return scriptGroup;
                    }
                    throw new RSRuntimeException("Object creation error, should not happen.");
                }
                throw new RSRuntimeException("Count mismatch, should not happen.");
            }
            throw new RSInvalidStateException("Empty script groups are not allowed");
        }
    }
}
