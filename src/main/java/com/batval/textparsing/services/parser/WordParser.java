package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements Parser {
    private final String REGEX = "(\\w+)|(\\p{Punct})";

    @Override
    public void handleText(String text, Component component) {

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Word word = new Word(CompositeTypes.WORD);
                word.setComponentContent(matcher.group(1));
                ((Composite) component).add(word);
            }
            if (matcher.group(2) != null) {
                Symbol symbol = new Symbol(CompositeTypes.SYMBOL);
                symbol.setComponentContent(matcher.group(2).charAt(0));
                ((Composite) component).add(symbol);
            }
        }
    }

}
