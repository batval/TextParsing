package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class for parsing text into paragraphs. Implements an interface {@link Parser}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class ParagraphParser implements Parser {

    /**
     * Regular expression:
     * '.*?' matches any character (except for line terminators)
     * '*?' Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
     */
    private final String REGEX_LISTING = "(/-(.*?)-/)";
    /**
     * Regular expression:
     * '\p' matches the character p literally (case sensitive)
     * '{Space' matches the characters {Space literally (case sensitive)}{4} matches the character } literally (case sensitive)
     * {4} Quantifier — Matches exactly 4 times
     * OR
     * '\p' matches the character p literally (case sensitive)
     * {Space} matches the characters {Space} literally (case sensitive)
     * .*? matches any character (except for line terminators)
     * *? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
     * Match a single character present in the list below [.!?:]
     * '.!?:' matches a single character in the list .!?: (case sensitive)
     * '\n{2}' matches a line-feed (newline) character (ASCII 10)
     * {2} Quantifier — Matches exactly 2 times
     */
    private final String REGEX_PARAGRAPH = "((\\p{Space}{4}|\\p{Space})(.*?)([.!?:])\\n{2})|";
    /**
     * Regular expression:
     * '\p' matches the character p literally (case sensitive)
     * '{Space}' matches the characters {Space} literally (case sensitive)
     * Match a single character present in the list below [1-9]{1,2}
     * '{1,2}' Quantifier — Matches between 1 and 2 times, as many times as possible, giving back as needed (greedy)
     * '1-9' a single character in the range between 1 (index 49) and 9 (index 57) (case sensitive)
     * '\.' matches the character . literally (case sensitive)
     * '.*?' matches any character (except for line terminators)
     * '*?' Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
     * '\n' matches a line-feed (newline) character (ASCII 10)
     */
    private final String REGEX_CHAPTER_NAME = "(\\p{Space}[1-9]{1,2}\\..*?\\n)|";
    /**
     * Compound regular expression
     */
    private final String REGEX_FULL = REGEX_CHAPTER_NAME + REGEX_PARAGRAPH + REGEX_LISTING;
    /**
     * Sentence parser reference
     */
    private SentenceParser sentenceParser = null;
    /**
     * Regex constructor reference
     */
    private Pattern pattern = null;

    /**
     * Constructor - Paragraph parser
     * Enables DOTALL mode. In this mode, expression. matches any character, including the end of line character.
     * By default, this expression does not match end-of-line characters.
     *
     * @param sentenceParser - sentence parser
     */
    public ParagraphParser(SentenceParser sentenceParser) {
        this.sentenceParser = sentenceParser;
        this.pattern = Pattern.compile(REGEX_FULL, Pattern.DOTALL);
    }

    /**
     * Analyzing text and breaking it into chapter name or paragraph or listing
     *
     * @param text      - analyzing text
     * @param component - composite component
     */
    @Override
    public void parse(String text, Component component) {

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Word word = new Word(CompositeTypes.CHAPTER_NAME);
                word.setComponentContent(matcher.group(1));
                ((Composite) component).add(word);
            }
            if (matcher.group(2) != null) {
                Component paragraph = new Composite(CompositeTypes.PARAGRAPH);
                sentenceParser.parse(matcher.group(2), paragraph);
                ((Composite) component).add(paragraph);
            }
            if (matcher.group(6) != null) {
                Listing listing = new Listing(CompositeTypes.LISTING);
                listing.setComponentContent(matcher.group(6));
                ((Composite) component).add(listing);
            }
        }
    }
}
