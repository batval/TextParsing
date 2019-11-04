package com.batval.textparsing.main;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;
import com.batval.textparsing.services.files.InputFile;
import com.batval.textparsing.services.parser.*;
import com.batval.textparsing.services.sorter.WordSort;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String FILE_ADDRESS = "src/main/resources/source_file.txt";

    public static void main(String[] args) throws IOException {
        logger.info("Start app...");

        InputFile inputFile = new InputFile();
        WordSort wordSort = new WordSort();


        Component component = new Composite(CompositeTypes.TEXT);

        WordParser wordParser = new WordParser();
        LexemeParser lexemeParser = new LexemeParser(wordParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        ChapterParser chapterParser = new ChapterParser(paragraphParser);
        chapterParser.handleText(inputFile.getText(FILE_ADDRESS), component);
        wordSort.sortWords(component);
        System.out.println();
        System.out.print("  ");
        logger.info("Rebuilt source to the console...");
        component.printing();
        System.out.println();
        logger.info("End app...");
    }
}
