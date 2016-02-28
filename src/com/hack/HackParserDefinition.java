package com.hack;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.hack.parser.HackParser;
import com.hack.psi.HackFile;
import com.hack.psi.HackTypes;
import org.jetbrains.annotations.NotNull;

import static com.hack.psi.HackTypes.*;

public class HackParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(HackTypes.COMMENT);
    public static final TokenSet KEYWORDS = TokenSet.create(
        ABSTRACT, AS, ASYNC, BREAK, CASE, CATCH, CLASS, CLONE, CONST, CONTINUE, DEFAULT,
        DO, ECHO, ELSE, ELSEIF, ENUM, EXTENDS, FINAL, FINALLY, FOR, FOREACH, FUNCTION, IF, IMPLEMENTS, INSTANCEOF,
        INTERFACE, NAMESPACE, NEW, NEWTYPE, VPRIVATE, VPROTECTED, VPUBLIC, REQUIRE, PARENT, SELF,
        REQUIRE_ONCE, RETURN, SHAPE, STATIC, SWITCH, THROW, TRAIT, TRY, TUPLE, TYPE, USE, WHILE, YIELD, TRUE, FALSE
    );
    public static final TokenSet TYPES = TokenSet.create(
        T_BOOL, T_INT, T_FLOAT, T_NUM, T_STRING, T_ARRAYKEY, T_VOID, T_RESOURCE, T_GENERIC, ARRAY, MAP, SET, MIXED, PAIR, NULL
    );
    public static final TokenSet FUNCTION_NAMES = TokenSet.create(FUNCTION_NAME, CONSTRUCT, DESTRUCT);
    public static final TokenSet OPERATORS = TokenSet.create(
        L_BRACKET, R_BRACKET, L_PAREN, R_PAREN, L_BRACE, R_BRACE, PERIOD, ARROW, INCR, DECR, EXP, MULT, ADD, SUB, DIV,
        MOD, BIT_NOT, NOT, BIT_AND, BIT_OR , BIT_XOR, BIT_SL, BIT_SR, LT, GT, LT_EQ, GT_EQ, EQ, TEQ, NEQ, NTEQ,
        AND, OR, COLON, SEMICOLON, QUESTION_MARK, EQ_ASSIGN, EXP_ASSIGN , MULT_ASSIGN, DIV_ASSIGN, MOD_ASSIGN,
        ADD_ASSIGN, SUB_ASSIGN, CONCAT_ASSIGN, BIT_SL_ASSIGN, BIT_SR_ASSIGN, BIT_AND_ASSIGN, BIT_OR_ASSIGN,
        BIT_XOR_ASSIGN, COMMA, AT_SIGN, SCOPE, KV_ARROW, LAMBDA_ARROW, NULLSAFE_ARROW, SPACESHIP, NULL_COALESCE
    );
    public static final TokenSet NUMBERS = TokenSet.create(
            BIN_LITERAL, OCTAL_LITERAL, HEX_LITERAL, DECIMAL_LITERAL, FLOAT_LITERAL
    );

    public static final IFileElementType FILE = new IFileElementType(Language.<HackLanguage>findInstance(HackLanguage.class));


    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new HackLexerAdapter();
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new HackParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return HackTypes.Factory.createElement(node);
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new HackFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
