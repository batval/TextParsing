package com.batval.textparsing.services.files;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for working with files. Reads the source text for further processing.
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class FileManager {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

    /**
     * Method for reading processed text
     *
     * @param fileAddress -source file and path to it
     * @return text read from source file
     * @throws IOException error reading file
     */
    public String getText(String fileAddress) throws IOException {
        String str = "";
        String text = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileAddress);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {

            str = bufferedReader.readLine();
            while (str != null) {
                text = text + str + "\n";
                str = bufferedReader.readLine();
            }
            if (text.isEmpty()) {
                logger.error("File is empty or error has occurred!");
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found!");
        }
        return text;
    }
}
