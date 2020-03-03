package org.wltea.analyzer.core;

import java.util.Stack;
import java.util.TreeSet;
import org.wltea.analyzer.core.QuickSortSet;

class IKArbitrator {
    IKArbitrator() {
    }

    /* access modifiers changed from: package-private */
    public void a(AnalyzeContext analyzeContext, boolean z) {
        QuickSortSet l = analyzeContext.l();
        LexemePath lexemePath = new LexemePath();
        for (Lexeme j = l.j(); j != null; j = l.j()) {
            if (!lexemePath.a(j)) {
                if (lexemePath.m() == 1 || !z) {
                    analyzeContext.a(lexemePath);
                } else {
                    analyzeContext.a(a(lexemePath.o(), lexemePath.e()));
                }
                lexemePath = new LexemePath();
                lexemePath.a(j);
            }
        }
        if (lexemePath.m() == 1 || !z) {
            analyzeContext.a(lexemePath);
        } else {
            analyzeContext.a(a(lexemePath.o(), lexemePath.e()));
        }
    }

    private LexemePath a(QuickSortSet.Cell cell, int i) {
        TreeSet treeSet = new TreeSet();
        LexemePath lexemePath = new LexemePath();
        Stack<QuickSortSet.Cell> a2 = a(cell, lexemePath);
        treeSet.add(lexemePath.h());
        while (!a2.isEmpty()) {
            QuickSortSet.Cell pop = a2.pop();
            a(pop.c(), lexemePath);
            a(pop, lexemePath);
            treeSet.add(lexemePath.h());
        }
        return (LexemePath) treeSet.first();
    }

    private Stack<QuickSortSet.Cell> a(QuickSortSet.Cell cell, LexemePath lexemePath) {
        Stack<QuickSortSet.Cell> stack = new Stack<>();
        while (cell != null && cell.c() != null) {
            if (!lexemePath.b(cell.c())) {
                stack.push(cell);
            }
            cell = cell.b();
        }
        return stack;
    }

    private void a(Lexeme lexeme, LexemePath lexemePath) {
        while (lexemePath.c(lexeme)) {
            lexemePath.a();
        }
    }
}
