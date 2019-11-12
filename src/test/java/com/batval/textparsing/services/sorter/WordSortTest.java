package com.batval.textparsing.services.sorter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class WordSortTest {

    private List<String> wordsToString = new ArrayList<>();
    private List<String> wordsToStringSorted = new ArrayList<>();


    @Before
    public void beforeTest() {
        wordsToString.add("started");
        wordsToString.add("with");
        wordsToString.add("is");
        wordsToString.add("an");
        wordsToString.add("a");
        wordsToString.add("in");
        wordsToString.add("spring");


        wordsToStringSorted.add("a");
        wordsToStringSorted.add("an");
        wordsToStringSorted.add("in");
        wordsToStringSorted.add("is");
        wordsToStringSorted.add("spring");
        wordsToStringSorted.add("started");
        wordsToStringSorted.add("with");
    }

    @After
    public void afterTest() {

    }


    @Test
    public void sortWords() {
        Character character;
        Collections.sort(wordsToString);
        character = wordsToString.get(0).charAt(0);
        for (String word : wordsToString) {
            if (character != word.charAt(0)) {
                System.out.print("\n  ".concat(word));
                character = word.charAt(0);
            } else {
                System.out.print("  ".concat(word));
            }
        }
        assertEquals(wordsToStringSorted,wordsToString);
    }
}