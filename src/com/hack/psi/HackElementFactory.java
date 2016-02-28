package com.hack.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.hack.HackFileType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class HackElementFactory {

    public static HackFile createFile(@NotNull Project project, @NotNull String text) {
        return (HackFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.hh", HackFileType.INSTANCE, text);
    }

    public static HackVarname createVarname(@NotNull Project project, @NotNull String name) {
        HackFile file = createFile(project, "<?hh function x() : void { " + name + " = 1; }");

        return PsiTreeUtil.findChildOfType(file, HackVarname.class);
    }
}
