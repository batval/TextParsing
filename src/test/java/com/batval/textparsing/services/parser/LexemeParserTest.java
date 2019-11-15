package com.batval.textparsing.services.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class LexemeParserTest {

    private final String REGEX = "((\\w+?-?\\w*?)[,.!?:\\s])|(#.*?-#)";
    private List<String> lexemeListActual = new ArrayList<>();
    private List<String> lexemeListExpected = new ArrayList<>();

    private List<String> innerCodeListActual = new ArrayList<>();
    private List<String> innerCodeListExpected = new ArrayList<>();


    private String text = "You need to register a MultipartConfigElement class (which would be #<multipart-config>-# in #web.xml)-#.";

    @Before
    public void beforeTest() {
        lexemeListExpected.add("You ");
        lexemeListExpected.add("need ");
        lexemeListExpected.add("to ");
        lexemeListExpected.add("register ");
        lexemeListExpected.add("a ");
        lexemeListExpected.add("MultipartConfigElement ");
        lexemeListExpected.add("class ");
        lexemeListExpected.add("which ");
        lexemeListExpected.add("would ");
        lexemeListExpected.add("be ");
        lexemeListExpected.add("in ");

        innerCodeListExpected.add("#<multipart-config>-#");
        innerCodeListExpected.add("#web.xml)-#");

    }

    @After
    public void afterTest() {
        lexemeListActual=null;
        lexemeListExpected=null;
    }
    @Test
    public void parseLexeme() {
        Pattern pattern = Pattern.compile(REGEX,Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                lexemeListActual.add(matcher.group(1));
            }
        }
        assertEquals(lexemeListExpected,lexemeListActual);
    }


    @Test
    public void parseInnerCode() {
        Pattern pattern = Pattern.compile(REGEX,Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(3) != null) {
                innerCodeListActual.add(matcher.group(3));
            }
        }
        assertEquals(innerCodeListExpected,innerCodeListActual);
    }

}