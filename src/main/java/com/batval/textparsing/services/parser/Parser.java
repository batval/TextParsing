package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.CompositeTypes;

public interface Parser {
void handleText(String text, Component component);
}
