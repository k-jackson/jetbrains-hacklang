package com.hack;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class HackSyntaxHighlighterColors {

    public static final TextAttributesKey OPENING_TAG = createTextAttributesKey("HACK_OPENING_TAG", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("HACK_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey OPERATOR = createTextAttributesKey("HACK_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("HACK_IDENTIFIER", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey THIS = createTextAttributesKey("HACK_THIS", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey VARIABLE_NAME = createTextAttributesKey("HACK_VARIABLE_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey FUNCTION_NAME = createTextAttributesKey("HACK_FUNCTION_NAME", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey MEMBER_NAME = createTextAttributesKey("HACK_MEMBER_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("HACK_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey SQ_STRING = createTextAttributesKey("HACK_SQ_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey DQ_ESCAPE_SEQ = createTextAttributesKey("HACK_DQ_ESCAPE_SEQ", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
    public static final TextAttributesKey DQ_STRING = createTextAttributesKey("HACK_DQ_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey DQ_VAR = createTextAttributesKey("HACK_DQ_VAR", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey D_QUOTE = createTextAttributesKey("HACK_D_QUOTE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("HACK_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey SEMICOLON = createTextAttributesKey("HACK_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMA = createTextAttributesKey("HACK_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("HACK_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NAME = createTextAttributesKey("HACK_NAME", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey TYPES = createTextAttributesKey("HACK_TYPES", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey CONST_NAME = createTextAttributesKey("HACK_CONST_NAME", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey MAGIC_CONST = createTextAttributesKey("HACK_MAGIC_CONST", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey STATIC_VARIABLE_NAME = createTextAttributesKey("HACK_STATIC_VARIABLE_NAME", DefaultLanguageHighlighterColors.STATIC_FIELD);

    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("HACK_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
}
