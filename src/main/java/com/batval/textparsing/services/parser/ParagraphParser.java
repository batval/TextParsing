package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Parser {

    private final String REGEX_LISTING = "(/-(.*?)-/)";
    private final String REGEX_PARAGRAPH = "((\\p{Space}{4}|\\p{Space})(.*?)([.!?:])\\n{2})|";
    private final String REGEX_CHAPTER_NAME = "(\\p{Space}[1-9]{1,2}\\..*?\\n)|";
    private final String REGEX_FULL = REGEX_CHAPTER_NAME + REGEX_PARAGRAPH + REGEX_LISTING;

    private SentenceParser sentenceParser = null;

    public ParagraphParser(SentenceParser sentenceParser) {
        this.sentenceParser = sentenceParser;
    }

    @Override
    public void handleText(String text, Component component) {

        Pattern pattern = Pattern.compile(REGEX_FULL, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Word word = new Word(CompositeTypes.CHAPTER_NAME);
                word.setComponentContent(matcher.group(1));
                ((Composite) component).add(word);
            }
            if (matcher.group(2) != null) {
                Component paragraph = new Composite(CompositeTypes.PARAGRAPH);
                sentenceParser.handleText(matcher.group(2), paragraph);
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
