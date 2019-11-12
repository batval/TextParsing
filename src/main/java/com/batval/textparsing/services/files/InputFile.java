package com.batval.textparsing.services.files;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputFile {

    private static final Logger logger = LoggerFactory.getLogger(InputFile.class);

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
                System.out.println("File is empty or error has occurred!");
                logger.error("File is empty or error has occurred!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            logger.error("File not found!");
        }

        return text;
    }
}
