package com.batval.textparsing.models;


import java.util.LinkedList;
import java.util.List;

/**
 * A container is an integral component of a tree. It contains a set of child components.
 * These can be either simple leaf components or other container components.
 * Container methods redirect the main work to their child components.
 * Implements an interface {@link Component}
 *
 * @version 1.0
 * @autor Baturo Valery
 */


public class Composite implements Component {

    private CompositeTypes compositeTypes;

    private List<Component> componentList = new LinkedList<>();

    /**
     * Constructor - creating a new composite with a specific type
     *
     * @param compositeTypes - a specific type of composite
     */
    public Composite(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
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
        for (Component component : componentList) {

            if (CompositeTypes.PARAGRAPH.equals(component.getCompositeTypes()) |
                    CompositeTypes.LISTING.equals(component.getCompositeTypes()) |
                    CompositeTypes.CHAPTER_NAME.equals(component.getCompositeTypes())) {
                System.out.println();
            }
            component.printing();
        }
    }

    /**
     * Add components to the list.
     *
     * @param component - listed item
     */
    public void add(Component component) {
        componentList.add(component);
    }

    /**
     * Get the whole list of components.
     *
     * @return component list.
     */
    public List<Component> getComponents() {
        return componentList;
    }

    /**
     * Return component by number
     *
     * @param number - component number in the list
     * @return component of composite.
     */
    public Component getChild(int number) {
        return componentList.get(number);
    }


}