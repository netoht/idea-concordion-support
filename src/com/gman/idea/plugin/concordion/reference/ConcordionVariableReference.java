package com.gman.idea.plugin.concordion.reference;

import com.gman.idea.plugin.concordion.ConcordionVariableUsage;
import com.gman.idea.plugin.concordion.lang.psi.ConcordionVariable;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.gman.idea.plugin.concordion.ConcordionVariableUsage.findDeclaration;

public class ConcordionVariableReference extends AbstractConcordionReference<ConcordionVariable> {

    public ConcordionVariableReference(@NotNull ConcordionVariable owner) {
        super(owner, createRange(owner));
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ConcordionVariableUsage declaration = findDeclaration(owner);
        return declaration != null ? declaration.resolve() : null;
    }

    private static TextRange createRange(@NotNull ConcordionVariable owner) {
        return owner.getName() != null
                ? new TextRange(0, owner.getName().length() + 1)
                : new TextRange(0, owner.getTextLength());
    }
}