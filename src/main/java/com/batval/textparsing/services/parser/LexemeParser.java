package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;
import com.batval.textparsing.models.Listing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Parser {

    private final String REGEX = "((\\w+?-?\\w*?)[,.!?:\\s])|(#.*?-#)";
    private WordParser wordParser = null;

    public LexemeParser(WordParser wordParser) {
        this.wordParser = wordParser;
    }

    @Override
    public void handleText(String text, Component component) {

        Pattern lexeme = Pattern.compile(REGEX, Pattern.DOTALL);
        Matcher matcher = lexeme.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Component comp = new Composite(CompositeTypes.LEXEME);
                wordParser.handleText(matcher.group(1), comp);
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
