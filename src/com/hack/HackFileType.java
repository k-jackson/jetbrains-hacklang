package com.hack;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import javax.swing.*;

public class HackFileType extends LanguageFileType {
    public static final HackFileType INSTANCE = new HackFileType();

    private HackFileType() {
        super(HackLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Hack File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Hack language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "hh";
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return HackIcons.FILE;
    }
}
