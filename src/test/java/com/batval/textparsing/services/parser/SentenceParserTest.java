package com.batval.textparsing.services.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class SentenceParserTest {


    private List<String> sentenceListActual = new ArrayList<>();
    private List<String> sentenceListExpected = new ArrayList<>();
    private final  String REGEX = "((\\s*)[А-ЯA-Z]((т.п.|т.д.|пр.|etc.)|[^?!.\\(]|\\([^\\)]*\\))*[.?!:])";

    private String text = "To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!";

    @Before
    public void beforeTest() {
        sentenceListExpected.add("To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies.");
        sentenceListExpected.add(" To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml).");
        sentenceListExpected.add(" Thanks to Spring Boot, everything is auto-configured for you!");

    }

    @After
    public void afterTest() {
        sentenceListActual=null;
        sentenceListExpected=null;
    }
    @Test
    public void parse() {


        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            sentenceListActual.add(matcher.group());
        }
        assertEquals(sentenceListExpected,sentenceListActual);
    }
}