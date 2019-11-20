package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;
import com.batval.textparsing.models.Listing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing text into lexemes. Implements an interface {@link Parser}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class LexemeParser implements Parser {

    /**
     * Regular expression:
     * '\w+?' matches any word character (equal to [a-zA-Z0-9_]). +? Quantifier — Matches between one and unlimited times, as few times as possible, expanding as needed (lazy).
     * '-?'   matches the character - literally (case sensitive). ? Quantifier — Matches between zero and one times, as many times as possible, giving back as needed (greedy).
     * '\w*?' matches any word character (equal to [a-zA-Z0-9_]). *? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy).
     * Match a single character present in the list below [,.!?:\s]
     * ,.!?: matches a single character in the list ,.!?: (case sensitive)
     * \s matches any whitespace character (equal to [\r\n\t\f\v ])
     * '|' - Or
     * '#' matches the character # literally (case sensitive)
     * '.*?' matches any character (except for line terminators)
     * '*?' Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
     * '-#' matches the characters -# literally (case sensitive)
     */
    private final String REGEX = "((\\w+?-?\\w*?)[,.!?:\\s])|(#.*?-#)";
    /**
     * Word parser reference
     */
    private WordParser wordParser = null;
    /**
     * Regex constructor reference
     */
    private Pattern pattern = null;

    /**
     * Constructor - Lexeme parser
     * Enables DOTALL mode. In this mode, expression. matches any character, including the end of line character.
     * By default, this expression does not match end-of-line characters.
     *
     * @param wordParser - word parser
     */
    public LexemeParser(WordParser wordParser) {
        this.wordParser = wordParser;
        this.pattern = Pattern.compile(REGEX, Pattern.DOTALL);
    }

    /**
     * Analyzing text and breaking it into lexeme or inner code (# -#)
     *
     * @param text      - analyzing text
     * @param component - composite component
     */
    @Override
    public void parse(String text, Component component) {

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Component comp = new Composite(CompositeTypes.LEXEME);
                wordParser.parse(matcher.group(1), comp);
                ((Composite) component).add(comp);
            }

            if (matcher.group(3) != null) {
                Listing innerCode = new Listing(CompositeTypes.INNER_CODE);
                innerCode.setComponentContent(matcher.group(3));
                ((Composite) component).add(innerCode);
            }
        }
    }
}
