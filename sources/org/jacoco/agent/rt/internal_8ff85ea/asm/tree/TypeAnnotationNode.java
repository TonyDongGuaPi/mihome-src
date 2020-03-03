package org.jacoco.agent.rt.internal_8ff85ea.asm.tree;

import org.jacoco.agent.rt.internal_8ff85ea.asm.TypePath;

public class TypeAnnotationNode extends AnnotationNode {
    public int h;
    public TypePath i;

    public TypeAnnotationNode(int i2, TypePath typePath, String str) {
        this(327680, i2, typePath, str);
        if (getClass() != TypeAnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    public TypeAnnotationNode(int i2, int i3, TypePath typePath, String str) {
        super(i2, str);
        this.h = i3;
        this.i = typePath;
    }
}
