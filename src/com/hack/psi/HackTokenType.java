package com.hack.psi;

import com.intellij.psi.tree.IElementType;
import com.hack.HackLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HackTokenType extends IElementType {
    public HackTokenType(@NotNull @NonNls String debugName) {
        super(debugName, HackLanguage.INSTANCE);
    }
}
