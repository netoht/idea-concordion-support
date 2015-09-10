package com.gman.idea.plugin.concordion.formatter;

import com.gman.idea.plugin.concordion.lang.ConcordionLexerAdapter;
import com.gman.idea.plugin.concordion.lang.psi.ConcordionTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class ConcordionSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final TextAttributesKey[] STRING_LITERAL = new TextAttributesKey[]{
            DefaultLanguageHighlighterColors.STRING
    };
    private static final TextAttributesKey[] NUMBER_LITERAL = new TextAttributesKey[]{
            DefaultLanguageHighlighterColors.NUMBER
    };
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new ConcordionLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        //I really miss pattern matching here
        if (ConcordionTypes.STRING_LITERAL.equals(tokenType)) {
            return STRING_LITERAL;
        } else if (ConcordionTypes.DOUBLE_LITERAL.equals(tokenType)
                || ConcordionTypes.INTEGER_LITERAL.equals(tokenType)) {
            return NUMBER_LITERAL;
        } else {
            return EMPTY_KEYS;
        }
    }
}
