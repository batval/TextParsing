package com.batval.textparsing.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Word implements Component {

    private String componentContent;
    private CompositeTypes compositeTypes;

    public Word(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }

    public String getComponentContent() {
        return componentContent;
    }

    @Override
    public CompositeTypes getCompositeTypes() {
        return compositeTypes;
    }

    @Override
    public String toString() {
        return componentContent;
    }

    @Override
    public void printing() {
         System.out.print(" ".concat(componentContent));

    }
}
