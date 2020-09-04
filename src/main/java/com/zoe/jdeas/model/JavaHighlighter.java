package com.zoe.jdeas.model;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zoe.jdeas.model.interfaces.Utilities;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;


/**
 * Copyright (c) 2013-2017, Tomas Mikula and contributors
 * All rights reserved.
 *
 * This class is responsible for java syntax highlighting
 *
 * The Original class can be found here:
 * https://github.com/FXMisc/RichTextFX/blob/master/richtextfx-demos/src/main/java/org/fxmisc/richtext/demo/JavaKeywordsDemo.java
 *
 * @author: Tomas Mikula and contributors. Modified by Zoe Lubanza
 */

public class JavaHighlighter implements Utilities
{
    private static String[] KEYWORDS;

    private static String KEYWORD_PATTERN;
    private static String PAREN_PATTERN;
    private static String BRACE_PATTERN ;
    private static String BRACKET_PATTERN;
    private static String SEMICOLON_PATTERN;
    private static String STRING_PATTERN;
    private static String COMMENT_PATTERN;
    private static Pattern PATTERN;
    private URL location = getClass().getProtectionDomain().getCodeSource().getLocation();

    public JavaHighlighter()
    {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            Files.lines(Paths.get(location.toURI()).resolve("../classes/jDeas/files/txt/keyWords/java.txt").normalize())
                    .forEach(l -> stringBuilder.append(l+"\n"));
        }catch (Exception e){this.showErrorAndCloseProgram("Failed to Load some System Files, \nPlease reinstall jDeas.");}

        KEYWORDS = stringBuilder.toString().split("\n");
        KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
        PAREN_PATTERN = "\\(|\\)";
        BRACE_PATTERN = "\\{|\\}";
        BRACKET_PATTERN = "\\[|\\]";
        SEMICOLON_PATTERN = "\\;";
        STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
        COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
        PATTERN = Pattern.compile(
                "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACE>" + BRACE_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
        );
    }

    /**
     *
     * @param codeArea
     */
    @SuppressWarnings("Duplicates")
    public void applyJavaHighlighter(CodeArea codeArea)
    {
        Subscription cleanupWhenNoLongerNeedIt = codeArea
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText())));

        final Pattern whiteSpace = Pattern.compile( "^\\s+" );

        codeArea.addEventHandler( KeyEvent.KEY_PRESSED, KE ->
        {
            if ( KE.getCode() == KeyCode.ENTER ) {
                int caretPosition = codeArea.getCaretPosition();
                int currentParagraph = codeArea.getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher( codeArea.getParagraph( currentParagraph-1 ).getSegments().get( 0 ) );
                if ( m0.find() ) Platform.runLater( () -> codeArea.insertText( caretPosition, m0.group() ) );
            }
        });
    }

    /**
     *
     * @param text
     * @return
     */
    private static StyleSpans<Collection<String>> computeHighlighting(String text)
    {
        Matcher matcher = PATTERN.matcher(text);

        int lastKwEnd = 0;

        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {

            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                    matcher.group("PAREN") != null ? "paren" :
                    matcher.group("BRACE") != null ? "brace" :
                    matcher.group("BRACKET") != null ? "bracket" :
                    matcher.group("SEMICOLON") != null ? "semicolon" :
                    matcher.group("STRING") != null ? "string" :
                    matcher.group("COMMENT") != null ? "comment" :
                    null; /* never happens */ assert styleClass != null;

            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);

        return spansBuilder.create();
    }
}