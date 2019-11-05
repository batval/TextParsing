package com.batval.textparsing.services.parser;

import com.batval.textparsing.models.Component;
import com.batval.textparsing.models.Composite;
import com.batval.textparsing.models.CompositeTypes;
import com.batval.textparsing.models.Listing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements Parser {

    //'\w+?' matches any word character (equal to [a-zA-Z0-9_]). +? Quantifier — Matches between one and unlimited times, as few times as possible, expanding as needed (lazy).
    //'-?'   matches the character - literally (case sensitive). ? Quantifier — Matches between zero and one times, as many times as possible, giving back as needed (greedy).
    //'\w*?' matches any word character (equal to [a-zA-Z0-9_]). *? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy).
    //Match a single character present in the list below [,.!?:\s]
    //,.!?: matches a single character in the list ,.!?: (case sensitive)
    //\s matches any whitespace character (equal to [\r\n\t\f\v ])
    //'|' - Or
    //# matches the character # literally (case sensitive)
    //.*? matches any character (except for line terminators)
    //*? Quantifier — Matches between zero and unlimited times, as few times as possible, expanding as needed (lazy)
    //-# matches the characters -# literally (case sensitive)
    private final String REGEX = "((\\w+?-?\\w*?)[,.!?:\\s])|(#.*?-#)";
    private WordParser wordParser = null;

    public LexemeParser(WordParser wordParser) {
        this.wordParser = wordParser;
    }

    @Override
    public void handleText(String text, Component component) {

        Pattern lexeme = Pattern.compile(REGEX, Pattern.DOTALL);
        Matcher matcher = lexeme.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                Component comp = new Composite(CompositeTypes.LEXEME);
                wordParser.handleText(matcher.group(1), comp);
                ((Composite) component).add(comp);
            }

            if (matcher.group(3) != null) {
                Listing innerCode = new Listing(CompositeTypes.INNER_CODE);
                innerCode.setComponentContent(matcher.group(3));
                ((Composite) component).add(innerCode);
            }
        }
    }
}
