package com.hack.psi.impl;

import com.hack.psi.HackNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class HackNamedElementImpl extends ASTWrapperPsiElement implements HackNamedElement {

    public HackNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
