package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Parser {
    private final String REGEX ="((\\s*)[А-ЯA-Z]((т.п.|т.д.|пр.|etc.)|[^?!.\\(]|\\([^\\)]*\\))*[.?!:])";
private LexemeParser lexemeParser=null;

public SentenceParser(LexemeParser lexemeParser){
    this.lexemeParser = lexemeParser;
}

@Override
    public void handleText(String text, Component component){
    Pattern pattern = Pattern.compile(REGEX,Pattern.DOTALL);
    Matcher matcher =pattern.matcher(text);

while (matcher.find()){
Component comp= new Composite(CompositeTypes.SENTENCE);
lexemeParser.handleText(matcher.group(),comp);
    ((Composite) component).add(comp);
}
}
}
