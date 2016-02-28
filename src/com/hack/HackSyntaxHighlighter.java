package com.hack;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.hack.psi.HackTypes;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

import static com.hack.HackSyntaxHighlighterColors.*;

public class HackSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final Map<IElementType, TextAttributesKey> TEXT_ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    static {
        fillMap(TEXT_ATTRIBUTES, NAME, HackTypes.NAME);
        fillMap(TEXT_ATTRIBUTES, CONST_NAME, HackTypes.CONST_NAME);
        fillMap(TEXT_ATTRIBUTES, MAGIC_CONST, HackTypes.MAGIC_CONST);
        fillMap(TEXT_ATTRIBUTES, SEMICOLON, HackTypes.SEMICOLON);
        fillMap(TEXT_ATTRIBUTES, COMMA, HackTypes.COMMA);
        fillMap(TEXT_ATTRIBUTES, VARIABLE_NAME, HackTypes.VARIABLE_NAME);
        fillMap(TEXT_ATTRIBUTES, STATIC_VARIABLE_NAME, HackTypes.STATIC_VARIABLE_NAME);
        fillMap(TEXT_ATTRIBUTES, THIS, HackTypes.THIS);
        fillMap(TEXT_ATTRIBUTES, MEMBER_NAME, HackTypes.MEMBER_NAME);
        fillMap(TEXT_ATTRIBUTES, SQ_STRING, HackTypes.SQ_STRING);
        fillMap(TEXT_ATTRIBUTES, DQ_STRING, HackTypes.DQ_STRING);
        fillMap(TEXT_ATTRIBUTES, DQ_ESCAPE_SEQ, HackTypes.DQ_ESCAPE_SEQ);
        fillMap(TEXT_ATTRIBUTES, DQ_VAR, HackTypes.DQ_VAR);
        fillMap(TEXT_ATTRIBUTES, D_QUOTE, HackTypes.D_QUOTE);
        fillMap(TEXT_ATTRIBUTES, OPENING_TAG, HackTypes.HH_OPENING_TAG);
        fillMap(TEXT_ATTRIBUTES, COMMENT, HackTypes.COMMENT);
        fillMap(TEXT_ATTRIBUTES, BAD_CHARACTER, TokenType.BAD_CHARACTER);
        fillMap(TEXT_ATTRIBUTES, HackParserDefinition.NUMBERS, NUMBER);
        fillMap(TEXT_ATTRIBUTES, HackParserDefinition.KEYWORDS, KEYWORD);
        fillMap(TEXT_ATTRIBUTES, HackParserDefinition.OPERATORS, OPERATOR);
        fillMap(TEXT_ATTRIBUTES, HackParserDefinition.TYPES, TYPES);
        fillMap(TEXT_ATTRIBUTES, HackParserDefinition.FUNCTION_NAMES, FUNCTION_NAME);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new HackLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(TEXT_ATTRIBUTES.get(tokenType));
    }
}
