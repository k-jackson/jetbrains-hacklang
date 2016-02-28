package com.hack;

import com.hack.psi.HackVarname;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HackReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public HackReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean b) {
        Project project = myElement.getProject();
        final List<HackVarname> varnames = HackUtil.findVarnames(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for (HackVarname varname : varnames) {
            results.add(new PsiElementResolveResult(varname));
        }

        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<HackVarname> varnames = HackUtil.findVarnames(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final HackVarname varname : varnames) {
            if (varname.getName() != null && varname.getName().length() > 0) { // todo?
                variants.add(
                        LookupElementBuilder.create(varname)
                        .withIcon(HackIcons.FILE)
                        .withTypeText(varname.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
