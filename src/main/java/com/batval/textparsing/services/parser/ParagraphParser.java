package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements Parser {
    //.*? matches any character (except for line terminators)
    //*? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
    private final String REGEX_LISTING = "(/-(.*?)-/)";
    //\p matches the character p literally (case sensitive)
    //{Space matches the characters {Space literally (case sensitive)
    //}{4} matches the character } literally (case sensitive)
    //{4} Quantifier — Matches exactly 4 times
    //OR
    //\p matches the character p literally (case sensitive)
    //{Space} matches the characters {Space} literally (case sensitive)
    //.*? matches any character (except for line terminators)
    //*? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
    //Match a single character present in the list below [.!?:]
    //.!?: matches a single character in the list .!?: (case sensitive)
    //\n{2} matches a line-feed (newline) character (ASCII 10)
    //{2} Quantifier — Matches exactly 2 times
    private final String REGEX_PARAGRAPH = "((\\p{Space}{4}|\\p{Space})(.*?)([.!?:])\\n{2})|";
    //\p matches the character p literally (case sensitive)
    //{Space} matches the characters {Space} literally (case sensitive)
    //Match a single character present in the list below [1-9]{1,2}
    //{1,2} Quantifier — Matches between 1 and 2 times, as many times as possible, giving back as needed (greedy)
    //1-9 a single character in the range between 1 (index 49) and 9 (index 57) (case sensitive)
    //\. matches the character . literally (case sensitive)
    //.*? matches any character (except for line terminators)
    //*? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
    //\n matches a line-feed (newline) character (ASCII 10)
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
