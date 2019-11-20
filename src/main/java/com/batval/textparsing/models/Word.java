package com.batval.textparsing.models;

/**
 * A composite sheet is a word, a simple tree component that has no branches.
 * Implements an interface {@link Component}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class Word implements Component {
    /**
     * Component content
     */
    private String componentContent;
    /**
     * Type of composite
     */
    private CompositeTypes compositeTypes;

    /**
     * Constructor - creating a composite sheet is a word
     *
     * @param compositeTypes - a specific type of composite
     */
    public Word(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    /**
     * Determine the contents of the component {@link Word#componentContent}
     *
     * @param componentContent - content
     */
    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }

    /**
     * Method returns the contents of the component.
     *
     * @return component content.
     */
    public String getComponentContent() {
        return componentContent;
    }

    /**
     * Returns the type of  components.
     *
     * @return type of composite.
     */
    @Override
    public CompositeTypes getCompositeTypes() {
        return compositeTypes;
    }

    /**
     * Method override toString().
     *
     * @return component content.
     */
    @Override
    public String toString() {
        return componentContent;
    }

    /**
     * Printing components to the console.
     */
    @Override
    public void printing() {
        System.out.print(" ".concat(componentContent));

    }
}
