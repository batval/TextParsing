package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing text into sentence. Implements an interface {@link Parser}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class SentenceParser implements Parser {
    /**
     * Regular expression:
     * '\s*' - matches any whitespace character (equal to [\r\n\t\f\v ]).
     * * Quantifier — Matches between zero and unlimited times, as many times as possible, giving back as needed (greedy).
     * Match a single character present in the list below [А-ЯA-Z]
     * А-Я a single character in the range between А (index 1040) and Я (index 1071) (case sensitive)
     * A-Z a single character in the range between A (index 65) and Z (index 90) (case sensitive)
     * * Quantifier — Matches between zero and unlimited times, as many times as possible, giving back as needed (greedy)
     * A repeated capturing group will only capture the last iteration. Put a capturing group around the repeated group to capture all iterations or use a non-capturing group instead if you're not interested in the data.
     * т matches the character т literally (case sensitive)
     * . matches any character (except for line terminators)
     * п matches the character п literally (case sensitive)
     * . matches any character (except for line terminators)
     * т matches the character т literally (case sensitive)
     * . matches any character (except for line terminators)
     * д matches the character д literally (case sensitive)
     * . matches any character (except for line terminators)
     * пр matches the characters пр literally (case sensitive)
     * . matches any character (except for line terminators)
     * etc matches the characters etc literally (case sensitive)
     * . matches any character (except for line terminators)
     * ?!. matches a single character in the list ?!. (case sensitive)
     * \( matches the character ( literally (case sensitive)
     * \( matches the character ( literally (case sensitive)
     * Match a single character not present in the list below [^\)]*
     * \) matches the character ) literally (case sensitive)
     * Match a single character present in the list below [.?!:]
     * .?!: matches a single character in the list .?!: (case sensitive)
     */
    private final String REGEX = "((\\s*)[А-ЯA-Z]((т.п.|т.д.|пр.|etc.)|[^?!.\\(]|\\([^\\)]*\\))*[.?!:])";
    /**
     * Lexeme parser reference
     */
    private LexemeParser lexemeParser = null;
    /**
     * Regex constructor reference
     */
    private Pattern pattern = null;

    /**
     * Constructor - Sentence parser
     * Enables DOTALL mode. In this mode, expression. matches any character, including the end of line character.
     * By default, this expression does not match end-of-line characters.
     *
     * @param lexemeParser - lexeme parser
     */
    public SentenceParser(LexemeParser lexemeParser) {
        this.lexemeParser = lexemeParser;
        this.pattern = Pattern.compile(REGEX, Pattern.DOTALL);
    }

    /**
     * Analyzing text and breaking it into sentence
     *
     * @param text      - analyzing text
     * @param component - composite component
     */
    @Override
    public void parse(String text, Component component) {

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Component comp = new Composite(CompositeTypes.SENTENCE);
            lexemeParser.parse(matcher.group(), comp);
            ((Composite) component).add(comp);
        }
    }
}
