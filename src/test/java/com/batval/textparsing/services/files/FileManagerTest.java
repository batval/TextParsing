package com.batval.textparsing.services.files;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class FileManagerTest {

    @Test
    public void getText() throws IOException {
        FileManager fileManager = new FileManager();
        String text=null;
        text = fileManager.getText("src/main/resources/source_file.txt");
        Assert.assertNotNull(text);
    }
}