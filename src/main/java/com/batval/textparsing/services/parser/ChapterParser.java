package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChapterParser implements Parser {
    //'\p{Space}'- whitespace characters that are invisible but have a width
    //.*? matches any character (except for line terminators)
    //*? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
    //\n{3} matches a line-feed (newline) character (ASCII 10)
    //{3} Quantifier — Matches exactly 3 times
    private final String REGEX = "\\p{Space}(.*?)\\n{3}";
    private ParagraphParser paragraphParser = null;

    public ChapterParser(ParagraphParser paragraphParser) {
        this.paragraphParser = paragraphParser;
    }

    @Override
    public void handleText(String text, Component component) {
        Pattern pattern = Pattern.compile(REGEX, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Component comp = new Composite(CompositeTypes.CHAPTER);
            paragraphParser.handleText(matcher.group(), comp);
            ((Composite) component).add(comp);
        }
    }
}
