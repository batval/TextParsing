package com.batval.textparsing.models;


public class Listing implements Component {

    private String componentContent;
    private CompositeTypes compositeTypes;

    public Listing(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }

    @Override
    public CompositeTypes getCompositeTypes() {
        return compositeTypes;
    }

    @Override
    public void printing() {
        System.out.print(componentContent);
    }
}
