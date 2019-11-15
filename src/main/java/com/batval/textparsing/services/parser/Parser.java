package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;


public interface Parser {
void parse(String text, Component component);
}
