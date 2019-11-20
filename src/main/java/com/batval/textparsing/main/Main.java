package com.batval.textparsing.main;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;
import com.batval.textparsing.services.files.FileManager;
import com.batval.textparsing.services.parser.*;
import com.batval.textparsing.services.sorter.WordSort;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main application launch class.
 *
 * @version 1.0
 * Text is processed, then the words are sorted and output to the console and to the file
 * Next, the restored text is displayed on the console
 * @autor Baturo Valery
 */

public class Main {
    /** Event Logger */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    /** File address */
    private static final String FILE_ADDRESS = "src/main/resources/source_file.txt";

    public static void main(String[] args) {
        logger.info("Start app...");

        try {

            FileManager fileManager = new FileManager();
            WordSort wordSort = new WordSort();

            Component component = new Composite(CompositeTypes.TEXT);

            WordParser wordParser = new WordParser();
            LexemeParser lexemeParser = new LexemeParser(wordParser);
            SentenceParser sentenceParser = new SentenceParser(lexemeParser);
            ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
            ChapterParser chapterParser = new ChapterParser(paragraphParser);
            chapterParser.parse(fileManager.getText(FILE_ADDRESS), component);
            wordSort.sortWords(component);
            System.out.println();
            logger.info("Rebuilt source to the console...");
            component.printing();
            System.out.println();
            logger.info("End app...");
        } catch (IOException ex) {
            logger.error("Information output error");
        }
    }
}
