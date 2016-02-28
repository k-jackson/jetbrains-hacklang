package com.hack.psi.impl;

import com.hack.HackUtil;
import com.hack.psi.*;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.hack.HackIcons;
import java.lang.String;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;

public class HackPsiImplUtil {

    public static String getName(HackVarname element) {
        return element.getText();
    }

    public static PsiElement setName(HackVarname element, String newName)
    {
        HackVarname variableName = HackElementFactory.createVarname(element.getProject(), newName);
        ASTNode newVarNode = variableName.getFirstChild().getNode();

        List<HackVarname> varnames = HackUtil.findLocalVarnames(element);

        for (HackVarname var : varnames) {
            ASTNode varNode = var.getNode().findChildByType(HackTypes.VARIABLE_NAME);
            if (varNode != null) {
                var.getNode().replaceChild(varNode, newVarNode.copyElement());
            }
        }

        return element;
    }

    public static PsiElement getNameIdentifier(HackVarname element)
    {
        ASTNode varNode = element.getNode().findChildByType(HackTypes.VARIABLE_NAME);

        if (varNode != null) {
            return varNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final HackVarname element)
    {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getText();
            }

            @Nullable
            @Override
            public String getLocationString() {
                PsiFile containingFile = element.getContainingFile();
                return containingFile == null ? null : containingFile.getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean b) {
                return HackIcons.FILE;
            }
        };
    }
}
