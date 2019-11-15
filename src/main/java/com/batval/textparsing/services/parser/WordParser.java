package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements Parser {
    //'\w+' - matches any word character (equal to [a-zA-Z0-9_]).
    //+ Quantifier — Matches between one and unlimited times, as many times as possible, giving back as needed (greedy).
    //'|' - Or
    //'p{Punct}' - matches any kind of punctuation character.

    private final String REGEX = "(\\w+)|(\\p{Punct})";
private Pattern pattern = null;

    public WordParser() {
        this.pattern = Pattern.compile(REGEX);
    }


    @Override
    public void parse(String text, Component component) {


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
