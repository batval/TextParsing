package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;


public interface Parser {
void handleText(String text, Component component);
}
