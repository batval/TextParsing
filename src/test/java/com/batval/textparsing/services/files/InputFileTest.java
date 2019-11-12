package com.batval.textparsing.services.files;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class InputFileTest {

    @Test
    public void getText() throws IOException {
        InputFile inputFile = new InputFile();
        String text=null;
        text = inputFile.getText("src/main/resources/source_file.txt");
        Assert.assertNotNull(text);
    }
}