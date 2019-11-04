package com.batval.textparsing.services.files;

import java.io.*;

public class InputFile {

    public String getText(String fileAddres) throws IOException {
        String str = "";
        String text = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileAddres);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {

            str = bufferedReader.readLine();
            while (str != null) {
                text = text + str + "\n";
                str = bufferedReader.readLine();
            }
            if (text.isEmpty()) {
                System.out.println("File is empty or error has occurred!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return text;
    }
}
