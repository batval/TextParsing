package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;

/**
 * A common interface for parsing text.
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public interface Parser {

    /**
     * Text parsing
     *
     * @param text      - analyzing text
     * @param component - composite component
     */
    void parse(String text, Component component);
}
