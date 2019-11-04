package com.batval.textparsing.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Composite implements Component {
    private CompositeTypes compositeTypes;
    private List<Component> componentList = new LinkedList<>();

    public Composite(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    @Override
    public CompositeTypes getCompositeTypes() {
        return compositeTypes;
    }

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

    public void add(Component component) {
        componentList.add(component);
    }

    public List<Component> getComponents() {
        return componentList;
    }

    public Component getChild(int i){
        return componentList.get(i);
    }
}