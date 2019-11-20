package com.batval.textparsing.models;

/**
 * A composite sheet is a symbol, a simple tree component that has no branches.
 * Implements an interface {@link Component}
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class Symbol implements Component {

    /**
     * Component content
     */
    private Character componentContent;
    /**
     * Type of composite
     */
    private CompositeTypes compositeTypes;

    /**
     * Constructor - creating a composite sheet is a symbol
     *
     * @param compositeTypes - a specific type of composite
     */
    public Symbol(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    /**
     * Determine the contents of the component {@link Symbol#componentContent}
     *
     * @param componentContent - content
     */
    public void setComponentContent(Character componentContent) {
        this.componentContent = componentContent;
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
     * Printing components to the console.
     */
    @Override
    public void printing() {
        System.out.print(componentContent);
    }

}
