package com.batval.textparsing.models;

/**
 * A component defines a common interface for simple and composite tree components.
 *
 * @version 1.0
 * @autor Baturo Valery
 */

public interface Component {

    /**
     * Printing components
     */
    void printing();

    /**
     * Returns the type of  components.
     */
    CompositeTypes getCompositeTypes();
}
