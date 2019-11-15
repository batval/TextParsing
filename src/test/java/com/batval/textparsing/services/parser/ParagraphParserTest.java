package com.batval.textparsing.services.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ParagraphParserTest {

    private final String REGEX_LISTING = "(/-(.*?)-/)";
    private final String REGEX_PARAGRAPH = "((\\p{Space}{4}|\\p{Space})(.*?)([.!?:])\\n{2})|";
    private final String REGEX_CHAPTER_NAME = "(\\p{Space}[1-9]{1,2}\\..*?\\n)|";
    private final String REGEX_FULL = REGEX_CHAPTER_NAME + REGEX_PARAGRAPH + REGEX_LISTING;


    private List<String> chapterNameListActual = new ArrayList<>();
    private List<String> chapterNameListExpected = new ArrayList<>();

    private List<String> paragraphListActual = new ArrayList<>();
    private List<String> paragraphListExpected = new ArrayList<>();

    private List<String> listingListActual = new ArrayList<>();
    private List<String> listingListExpected = new ArrayList<>();

    private String text = " 1. Create an Application class\n" +
            "   The initial application already contains a few classes to deal with storing and loading the uploaded files on disk; they’re all located in the hello.storage package. We will use those in our new FileUploadController.\n"+
            "\n" +
            "   To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!\n" +
            "\n" +
            "   All you need to get started with this application is the following Application class.\n" +
            "\n" +
            "/-\n" +
            "src/main/java/hello/Application.java\n" +
            "package hello;\n" +
            "import org.springframework.boot.SpringApplication;\n" +
            "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
            "@SpringBootApplication\n" +
            "public class Application {\n" +
            "    public static void main(String[] args) {\n" +
            "        SpringApplication.run(Application.class, args);\n" +
            "    }\n" +
            "}\n" +
            "-/\n" +
            "\n" +
            "\n" ;

    @Before
    public void beforeTest() {
        chapterNameListExpected.add(" 1. Create an Application class\n");
        paragraphListExpected.add("   The initial application already contains a few classes to deal with storing and loading the uploaded files on disk; they’re all located in the hello.storage package. We will use those in our new FileUploadController.\n"+
                "\n");
        paragraphListExpected.add("   To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!\n" +
                        "\n");
        paragraphListExpected.add("   All you need to get started with this application is the following Application class.\n"+
                "\n");

        listingListExpected.add( "/-\n" +
                "src/main/java/hello/Application.java\n" +
                "package hello;\n" +
                "import org.springframework.boot.SpringApplication;\n" +
                "import org.springframework.boot.autoconfigure.SpringBootApplication;\n" +
                "@SpringBootApplication\n" +
                "public class Application {\n" +
                "    public static void main(String[] args) {\n" +
                "        SpringApplication.run(Application.class, args);\n" +
                "    }\n" +
                "}\n" +
                "-/");
    }

    @After
    public void afterTest() {
        chapterNameListActual = null;
        chapterNameListExpected = null;
        paragraphListActual=null;
        paragraphListExpected=null;
        listingListActual=null;
        listingListExpected=null;
    }

    @Test
    public void parseChapterName() {

        Pattern pattern = Pattern.compile(REGEX_FULL, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                chapterNameListActual.add(matcher.group(1));
            }
        }
        assertEquals(chapterNameListExpected, chapterNameListActual);
    }


    @Test
    public void parseParagraph() {

        Pattern pattern = Pattern.compile(REGEX_FULL, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(2) != null) {
                paragraphListActual.add(matcher.group(2));
            }
        }
        assertEquals(paragraphListExpected, paragraphListActual);
    }


    @Test
    public void parseListing() {

        Pattern pattern = Pattern.compile(REGEX_FULL, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(6) != null) {
                listingListActual.add(matcher.group(6));
            }
        }
        assertEquals(listingListExpected, listingListActual);
    }

}