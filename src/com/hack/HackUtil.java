package com.hack;

import com.hack.psi.HackClassMemberDeclaration;
import com.hack.psi.HackFile;
import com.hack.psi.HackFunctionDefinition;
import com.hack.psi.HackVarname;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.indexing.FileBasedIndex;
import java.util.*;

public class HackUtil {

    public static List<HackVarname> findVarnames(Project project, String key)
    {
        List<HackVarname> result = null;
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME
                , HackFileType.INSTANCE, GlobalSearchScope.allScope(project));

        for (VirtualFile virtualFile : virtualFiles) {
            PsiFile hackFile = PsiManager.getInstance(project).findFile(virtualFile);
            if (hackFile != null) {

                Collection<HackVarname> varnames = PsiTreeUtil.findChildrenOfType(hackFile, HackVarname.class);
                if (!varnames.isEmpty()) {
                    for (HackVarname varname : varnames) {

                        if (key.equals(varname.getName())) {
                            if (result == null) {
                                result = new ArrayList<HackVarname>();
                            }
                            result.add(varname);
                        }
                    }
                }
            }
        }

        return result != null ? result : Collections.<HackVarname>emptyList();
    }

    public static List<HackVarname> findVarnames(Project project)
    {
        List<HackVarname> result = new ArrayList<HackVarname>();
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance()
                .getContainingFiles(FileTypeIndex.NAME, HackFileType.INSTANCE, GlobalSearchScope.allScope(project));

        for (VirtualFile virtualFile : virtualFiles) {
            HackFile hackFile = (HackFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (hackFile != null) {
                HackVarname[] varnames = PsiTreeUtil.getChildrenOfType(hackFile, HackVarname.class);
                if (varnames != null) {
                    Collections.addAll(result, varnames);
                }
            }
        }

        return result;
    }


    public static List<HackVarname> findLocalVarnames(HackVarname var)
    {
        List<HackVarname> result = new ArrayList<HackVarname>();

        Condition<PsiElement> parentCheck = psiElement
                -> psiElement instanceof HackFunctionDefinition || psiElement instanceof HackClassMemberDeclaration;
        PsiElement parentFunction = PsiTreeUtil.findFirstParent(var, false, parentCheck);
        Collection<HackVarname> siblingVars = PsiTreeUtil.findChildrenOfType(parentFunction, HackVarname.class);

        for (HackVarname sibling : siblingVars) {
            if (var.getText().equals(sibling.getText())) {
                result.add(sibling);
            }
        }

        return result;
    }
}
