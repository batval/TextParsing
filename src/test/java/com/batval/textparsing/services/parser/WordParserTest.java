package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class WordParserTest {

    private List<String> componentExpectedList = new LinkedList<>();
    private List<Character> componentExpectedSymbolList = new LinkedList<>();
    private List<String> componentActualList = new LinkedList<>();
    private List<Character> componentActualSymbolList = new LinkedList<>();

    private String text = "A Spring Boot MVC, we first; here, spring-boot.";

    @Before
    public void beforeTest() {
        componentExpectedList.add("A");
        componentExpectedList.add("Spring");
        componentExpectedList.add("Boot");
        componentExpectedList.add("MVC");
        componentExpectedList.add("we");
        componentExpectedList.add("first");
        componentExpectedList.add("here");
        componentExpectedList.add("spring");
        componentExpectedList.add("boot");

        componentExpectedSymbolList.add(',');
        componentExpectedSymbolList.add(';');
        componentExpectedSymbolList.add(',');
        componentExpectedSymbolList.add('-');
        componentExpectedSymbolList.add('.');
    }

    @After
    public void afterTest() {
        componentExpectedList=null;
        componentActualList=null;
        componentExpectedSymbolList = null;
        componentActualSymbolList = null;
    }


    @Test
    public void parseWord() {

        String REGEX = "(\\w+)|(\\p{Punct})";
        Pattern pattern = Pattern.compile(REGEX);
        String word = "";
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                word = matcher.group(1);
                componentActualList.add(word);
            }
        }
        assertEquals(componentExpectedList, componentActualList);
    }

    @Test
    public void parseSymbol() {

        String REGEX = "(\\w+)|(\\p{Punct})";
        Pattern pattern = Pattern.compile(REGEX);
        Character symbol;
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(2) != null) {
                symbol =matcher.group(2).charAt(0);
                componentActualSymbolList.add(symbol);
            }
        }
        assertEquals(componentExpectedSymbolList, componentActualSymbolList);
    }

}