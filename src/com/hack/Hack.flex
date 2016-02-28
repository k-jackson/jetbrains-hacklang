package com.hack;
import com.intellij.lexer.*;
import com.intellij.psi.tree.IElementType;
import static com.hack.psi.HackTypes.*;
%%

%{
  public HackLexer() {
    this((java.io.Reader)null);
  }

  public static String hdnd_identifier = "";
%}

%public
%class HackLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%state DQ_STR, HDND_START, HD, ND, FUNC_NAME, MEMB_NAME, SCOPE_NAME

COMMENT = {TRADITIONALCOMMENT} | {ENDOFLINECOMMENT} | {DOCUMENTATIONCOMMENT}
TRADITIONALCOMMENT   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
INPUTCHARACTER = [^\r\n]
ENDOFLINECOMMENT     = ("//"|"#") {INPUTCHARACTER}* {EOL}?
DOCUMENTATIONCOMMENT = "/**" {COMMENTCONTENT} "*"+ "/"
COMMENTCONTENT       = ( [^*] | \*+ [^/*] )*
EOL=\r|\n|\r\n
LINE_WS=[\ \t\f]
WHITE_SPACE=({LINE_WS}|{EOL})+
NAME = {NAME_NONDIGIT}+ ({NAME_NONDIGIT}|{INTEGER})*
NAME_NONDIGIT = [a-zA-Z_]
INTEGER = 0 | [1-9][0-9]*
SQ_CHAR = ("\\" "'"|"\\" "\\")|("\\"? [^\\'])
ARRAY = ("[" ("'"|\") {NAME} ("'"|\") "]")
FLOAT_LITERAL = ("-")? (({FRACTIONAL_LITERAL} {EXPONENT_PART}?) | ({DIGIT_SEQ} {EXPONENT_PART}))
FRACTIONAL_LITERAL = ({DIGIT_SEQ}? "." {DIGIT_SEQ}) | ({DIGIT_SEQ} ".")
EXPONENT_PART = ("e"|"E") ("+"|"-")? {DIGIT_SEQ}
DIGIT_SEQ = [0-9]+
MAGIC_CONST = "__"("LINE"|"FILE"|"DIR"|"FUNCTION"|"CLASS"|"TRAIT"|"METHOD"|"NAMESPACE")"__"

%%
<YYINITIAL> {
  {WHITE_SPACE}"__construct"          { return CONSTRUCT; }
  {WHITE_SPACE}"__destruct"           { return DESTRUCT; }
  {WHITE_SPACE}        { return com.intellij.psi.TokenType.WHITE_SPACE; }
  "<?hh"               { return HH_OPENING_TAG; }
  {COMMENT}            { return COMMENT; }
  "$this"              { return THIS; }
  "$" {NAME}           { return VARIABLE_NAME; }
  ";"                  { return SEMICOLON; }
  "\\"                 { return B_SLASH; }
  "::"                 { yybegin(SCOPE_NAME); return SCOPE; }
  ":"                  { return COLON; }
  ","                  { return COMMA; }
  "..."                { return ELLIPSIS; }
  "."                  { return PERIOD; }
  "("                  { return L_PAREN; }
  ")"                  { return R_PAREN; }
  "{"                  { return L_BRACE; }
  "}"                  { return R_BRACE; }
  "["                  { return L_BRACKET; }
  "]"                  { return R_BRACKET; }
  "="                  { return EQ_ASSIGN; }
  "=="                 { return EQ; }
  "!="                 { return NEQ; }
  "==="                { return TEQ; }
  "!=="                { return NTEQ; }
  "'" {SQ_CHAR}* "'"   { return SQ_STRING; }
  \"                   { yybegin(DQ_STR); return D_QUOTE; }
  "function"           { yybegin(FUNC_NAME); return FUNCTION; }
  "class"              { return CLASS; }
  "use"                { return USE; }
  "namespace"          { return NAMESPACE; }
  "trait"              { return TRAIT; }
  "interface"          { return INTERFACE; }
  "if"                 { return IF; }
  "elseif"             { return ELSEIF; }
  "else"               { return ELSE; }
  "require_once"       { return REQUIRE_ONCE; }
  "require"            { return REQUIRE; }
  "enum"               { return ENUM; }
  "as"                 { return AS; }
  "async"              { return ASYNC; }
  "abstract"           { return ABSTRACT; }
  "final"              { return FINAL; }
  "extends"            { return EXTENDS; }
  "implements"         { return IMPLEMENTS; }
  "const"              { return CONST; }
  "public"             { return VPUBLIC; }
  "protected"          { return VPROTECTED; }
  "private"            { return VPRIVATE; }
  "static"             { return STATIC; }
  "type"               { return TYPE; }
  "newtype"            { return NEWTYPE; }
  "shape"              { return SHAPE; }
  "=>"                 { return KV_ARROW; }
  "array"              { return ARRAY; }
  "?"                  { return QUESTION_MARK; }
  "mixed"              { return MIXED; }
  "<<<"                { yybegin(HDND_START); return HDND_OPEN; }
  "->"                 { yybegin(MEMB_NAME); return ARROW; }
  "++"                 { return INCR; }
  "--"                 { return DECR; }
  "**"                 { return EXP; }
  "*"                  { return MULT; }
  "+"                  { return ADD; }
  "-"                  { return SUB; }
  "/"                  { return DIV; }
  "%"                  { return MOD; }
  "~"                  { return BIT_NOT; }
  "!"                  { return NOT; }
  "&"                  { return BIT_AND; }
  "|"                  { return BIT_OR; }
  "^"                  { return BIT_XOR; }
  "<="                 { return LT_EQ; }
  ">="                 { return GT_EQ; }
  "&&" | "and"         { return AND; }
  "||" | "or"          { return OR; }
  "**="                { return EXP_ASSIGN; }
  "*="                 { return MULT_ASSIGN; }
  "/="                 { return DIV_ASSIGN; }
  "%="                 { return MOD_ASSIGN; }
  "+="                 { return ADD_ASSIGN; }
  "-="                 { return SUB_ASSIGN; }
  ".="                 { return CONCAT_ASSIGN; }
  "<<="                { return BIT_SL_ASSIGN; }
  ">>="                { return BIT_SR_ASSIGN; }
  "&="                 { return BIT_AND_ASSIGN; }
  "|="                 { return BIT_OR_ASSIGN; }
  "^="                 { return BIT_XOR_ASSIGN; }
  "@"                  { return AT_SIGN; }
  "==>"                { return LAMBDA_ARROW; }
  "?->"                { yybegin(MEMB_NAME); return NULLSAFE_ARROW; }
  "<==>"               { return SPACESHIP; }
  "<"                  { return LT; }
  ">"                  { return GT; }
  "??"                 { return NULL_COALESCE; }
  "null" | "NULL"      { return NULL; }
  "true" | "TRUE"      { return TRUE; }
  "false" | "FALSE"    { return FALSE; }
  "invariant"          { return INVARIANT; }
  "exit"               { return EXIT; }
  "echo"               { return ECHO; }
  " list"              { return LIST; } // todo
  "tuple"              { return TUPLE; }
  "clone"              { return CLONE; }
  "new"                { return NEW; }
  "self"               { return SELF; }
  "parent"             { return PARENT; }
  "instanceof"         { return INSTANCEOF; }
  "await"              { return AWAIT; }
  "case"               { return CASE; }
  "default"            { return DEFAULT; }
  "switch"             { return SWITCH; }
  "while"              { return WHILE; }
  "do"                 { return DO; }
  "for"                { return FOR; }
  "foreach"            { return FOREACH; }
  "continue"           { return CONTINUE; }
  "break"              { return BREAK; }
  "return"             { return RETURN; }
  "throw"              { return THROW; }
  "try"                { return TRY; }
  "catch"              { return CATCH; }
  "finally"            { return FINALLY; }
  "int"                { return T_INT; }
  "num"                { return T_NUM; }
  "arraykey"           { return T_ARRAYKEY; }
  "string"             { return T_STRING; }
  "bool"               { return T_BOOL; }
  "float"              { return T_FLOAT; }
  "void"               { return T_VOID; }
  "resource"           { return T_RESOURCE; }
  "Map" | "ImmMap"     { return MAP; }
  "Set" | "ImmSet"     { return SET;}
  "Vector" |"ImmVector" { return ARRAY; }
  "Pair"               { return PAIR; }
  "INF"                { return INF; }
  "NAN"                { return NAN; }
  "yield"              { return YIELD; }
  {MAGIC_CONST}        { return MAGIC_CONST; }
  "T" [a-zA-Z0-9_]*    { return T_GENERIC; }
  {NAME}               { return NAME; }
  "0"("b"|"B")(0|1)+   { return BIN_LITERAL; }
  "0"[0-7]+            { return OCTAL_LITERAL; }
  ("0x"|"0X")[0-9a-fA-F]+ { return HEX_LITERAL; }
  {FLOAT_LITERAL}      { return FLOAT_LITERAL; }
  (("-")?[1-9][0-9]*)|"0" { return DECIMAL_LITERAL; }
}

<SCOPE_NAME> {
  "class"              { yybegin(YYINITIAL); return CLASS; }
  "Map" | "ImmMap"     { yybegin(YYINITIAL); return MAP; }
  "Set" | "ImmSet"     { yybegin(YYINITIAL); return SET;}
  "Vector" |"ImmVector" { yybegin(YYINITIAL); return ARRAY; }
  "$" {NAME}           { yybegin(YYINITIAL); return STATIC_VARIABLE_NAME; }
  {NAME} {WHITE_SPACE}? "(" { yypushback(1); yybegin(YYINITIAL); return FUNCTION_NAME; }
  {NAME}               { yybegin(YYINITIAL); return CONST_NAME;}
  {WHITE_SPACE}        { return com.intellij.psi.TokenType.WHITE_SPACE; }
}

<FUNC_NAME> {
    "__construct"      { yybegin(YYINITIAL); return CONSTRUCT; }
    "__destruct"       { yybegin(YYINITIAL); return DESTRUCT; }
    {NAME}             { yybegin(YYINITIAL); return FUNCTION_NAME; }
    "("                { yybegin(YYINITIAL); return L_PAREN; }
    {WHITE_SPACE}      { return com.intellij.psi.TokenType.WHITE_SPACE; }
}

<MEMB_NAME> {
    {NAME}             { yybegin(YYINITIAL); return MEMBER_NAME; }
}

<DQ_STR> {
  [^\"\\\$]+           { yybegin(DQ_STR); return DQ_STRING; }
  "$"{NAME}{ARRAY}*    { yybegin(DQ_STR); return DQ_VAR; }
  \\[\$\"efnrtv]       { yybegin(DQ_STR); return DQ_ESCAPE_SEQ; }
  //todo octals, hex, etc
  \"                   { yybegin(YYINITIAL); return D_QUOTE; }
  [^]                  { yybegin(DQ_STR); return DQ_STRING; }
}

<HDND_START> {
    {WHITE_SPACE}        { return com.intellij.psi.TokenType.WHITE_SPACE; }
    {NAME}               { hdnd_identifier = yytext().toString(); yybegin(HD); return HD_ID; }
    "'"{NAME}"'"         { hdnd_identifier = yytext().toString().replace("\'",""); yybegin(ND); return ND_ID; }
}

<HD> {
    {WHITE_SPACE}        { return com.intellij.psi.TokenType.WHITE_SPACE; }
    ^{NAME}";"$          { yypushback(1); if (yytext().toString().equals(hdnd_identifier)) { yybegin(YYINITIAL); return HD_ID; } else { yybegin(HD); return DQ_STRING; }}
    ^{NAME}$             { if (yytext().toString().equals(hdnd_identifier)) { yybegin(YYINITIAL); return HD_ID; } else { yybegin(HD); return DQ_STRING; }}
    [^\\\$\r\n]*         { yybegin(HD); return DQ_STRING; }
    "$"{NAME}{ARRAY}*    { yybegin(HD); return DQ_VAR; }
    "$"[^\r\n\$]*        { yybegin(HD); return DQ_STRING; }
    \\[\$\"efnrtv]       { yybegin(HD); return DQ_ESCAPE_SEQ; }
}

<ND> {
    {WHITE_SPACE}        { return com.intellij.psi.TokenType.WHITE_SPACE; }
    ^{NAME}";"$          { yypushback(1); if (yytext().toString().equals(hdnd_identifier)) { yybegin(YYINITIAL); return ND_ID; } else { yybegin(ND); return ND_CONTENTS; }}
    ^{NAME}$             { if (yytext().toString().equals(hdnd_identifier)) { yybegin(YYINITIAL); return ND_ID; } else { yybegin(ND); return ND_CONTENTS; }}
    ^[^\r\n]*            { yybegin(ND); return ND_CONTENTS; }
}

[^] { return com.intellij.psi.TokenType.BAD_CHARACTER; }