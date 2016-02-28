package com.hack;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import java.util.Map;

public class HackColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Identifiers", HackSyntaxHighlighterColors.IDENTIFIER),
            new AttributesDescriptor("Keywords", HackSyntaxHighlighterColors.KEYWORD),
            new AttributesDescriptor("Separators", HackSyntaxHighlighterColors.SEPARATOR),
            new AttributesDescriptor("Strings", HackSyntaxHighlighterColors.SQ_STRING),
            new AttributesDescriptor("Function Names", HackSyntaxHighlighterColors.FUNCTION_NAME),
            new AttributesDescriptor("Instance Fields", HackSyntaxHighlighterColors.MEMBER_NAME),
            new AttributesDescriptor("Local Variables", HackSyntaxHighlighterColors.VARIABLE_NAME),
            new AttributesDescriptor("Numbers", HackSyntaxHighlighterColors.NUMBER),
            new AttributesDescriptor("Semicolons", HackSyntaxHighlighterColors.SEMICOLON)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return HackIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new HackSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {

        return
            "<?hh // strict \n" +
            "namespace NS_anonymous_function_name;\n" +
            "class C {\n" +
            "    public function fname(): void { \n" +
            "        echo \"__FUNCTION__ = \" . __FUNCTION__ . \"\\n\";\n" +
            "        doThing(1);\n" +
            "        $x += $y == 3;\n" +
            "    }\n" +
            "}";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Hack";
    }
}
