package com.batval.textparsing.services.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ChapterParserTest {

    private final String REGEX = "\\p{Space}(.*?)\\n{3}";

    private List<String> chapterListActual = new ArrayList<>();
    private List<String> chapterListExpected = new ArrayList<>();

    private String text = " 1. First chapter\n" +
            "   The initial application already contains a few classes to deal with storing and loading the uploaded files on disk; they’re all located in the hello.storage package. We will use those in our new FileUploadController.\n"+
            "\n" +
            "\n" +
            " 2. Second chapter\n" +
            "   To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!\n" +
            "\n" +
            "\n" +
            " 3. Third chapter\n" +
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
            "\n"+
            "\n";


    @Before
    public void beforeTest() {
        chapterListExpected.add(" 1. First chapter\n" +
                "   The initial application already contains a few classes to deal with storing and loading the uploaded files on disk; they’re all located in the hello.storage package. We will use those in our new FileUploadController.\n" +
                "\n" +
                "\n");
        chapterListExpected.add(" 2. Second chapter\n"+
                "   To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers, you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml). Thanks to Spring Boot, everything is auto-configured for you!\n"+
                "\n"+
                "\n");
        chapterListExpected.add(" 3. Third chapter\n" +
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
                "\n");
    }

    @After
    public void afterTest() {
        chapterListExpected = null;
        chapterListActual = null;
    }

    @Test
    public void parse() {
        Pattern pattern = Pattern.compile(REGEX,Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            chapterListActual.add(matcher.group());
        }
        assertEquals(chapterListExpected,chapterListActual);
    }
}