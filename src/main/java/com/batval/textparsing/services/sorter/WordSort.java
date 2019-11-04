package com.batval.textparsing.services.sorter;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordSort implements Sorter {

    private static final Logger logger = LoggerFactory.getLogger(
            WordSort.class);

    private Character character;
    private ArrayList<Component> allWords = new ArrayList<>();

    public ArrayList<Component> returnAllWords(Component component) {
        Iterator<Component> componentIterator = ((Composite) component).getComponents().iterator();
        while (componentIterator.hasNext()) {
            Component nextComponent = componentIterator.next();
            if (nextComponent.getCompositeTypes().equals(CompositeTypes.WORD)) {
                allWords.add(nextComponent);
            } else {
                if (nextComponent instanceof Composite) {
                    returnAllWords(nextComponent);
                }
            }
        }
        return allWords;
    }

    @Override
    public void sortWords(Component component) {
        logger.info("Sort preparation...");

        returnAllWords(component);
            List<String> wordsToString = new ArrayList<>();
        for (Component word : allWords) {
            wordsToString.add(word.toString().toLowerCase());
        }

        Collections.sort(wordsToString);

        character = wordsToString.get(0).charAt(0);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/sort_text.txt"))) {


        for (String word : wordsToString) {
            if (character != word.charAt(0)) {
                System.out.print("\n  ".concat(word));
                bufferedWriter.write("\n  ".concat(word));
                character = word.charAt(0);
            } else {
                System.out.print("  ".concat(word));
                bufferedWriter.write("  ".concat(word));
            }
        }
        System.out.println();
            logger.info("End sort...");
        } catch (IOException e) {
            logger.error("Error sorting and displaying words.");
        }
    }
}
