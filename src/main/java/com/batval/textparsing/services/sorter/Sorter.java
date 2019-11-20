package com.batval.textparsing.services.sorter;

import com.batval.textparsing.models.Component;

/**
 * A common interface for sored words.
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public interface Sorter {
    /**
     * Text parsing
     *
     * @param component - composite component
     */
    void sortWords(Component component);
}
