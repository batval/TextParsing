package com.batval.textparsing.models;

public class Symbol implements Component {

    private Character componentContent;
    private CompositeTypes compositeTypes;

    public Symbol(CompositeTypes compositeTypes) {
        this.compositeTypes = compositeTypes;
    }

    public void setComponentContent(Character componentContent) {
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
