package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for parsing text into chapters. Implements an interface {@link Parser}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class ChapterParser implements Parser {

    /**
     * Regular expression:
     * '\p{Space}'- whitespace characters that are invisible but have a width
     * .*? matches any character (except for line terminators)
     * *? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
     * \n{3} matches a line-feed (newline) character (ASCII 10)
     * {3} Quantifier — Matches exactly 3 times
     */
    private final String REGEX = "\\p{Space}(.*?)\\n{3}";
    /**
     * Paragraph parser link
     */
    private ParagraphParser paragraphParser = null;
    /**
     * Regex constructor reference
     */
    private Pattern pattern = null;

    /**
     * Constructor - chapter parser
     * Enables DOTALL mode. In this mode, expression. matches any character, including the end of line character.
     * By default, this expression does not match end-of-line characters.
     *
     * @param paragraphParser - paragraph parser
     */
    public ChapterParser(ParagraphParser paragraphParser) {
        this.paragraphParser = paragraphParser;
        this.pattern = Pattern.compile(REGEX, Pattern.DOTALL);
    }

    /**
     * Analyzing text and breaking it into chapters
     *
     * @param text      - analyzing text
     * @param component - composite component
     */
    @Override
    public void parse(String text, Component component) {
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Component comp = new Composite(CompositeTypes.CHAPTER);
            paragraphParser.parse(matcher.group(), comp);
            ((Composite) component).add(comp);
        }
    }
}
