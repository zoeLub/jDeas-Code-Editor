package com.zoe.jdeas.model;

import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.MainStage;
import org.fxmisc.richtext.CodeArea;

/**
 * @author Zoe Lubanza
 */

public abstract class ChangeStyle implements HandleFile {

    private JavaHighlighter javaHighlighter;
    private HtmlHighlighter htmlHighlighter;
    private LoadStyles styles;

    /**
     *
     */
    public ChangeStyle()
    {
        javaHighlighter = new JavaHighlighter();
        htmlHighlighter = new HtmlHighlighter();
        styles = new LoadStyles();
    }

    /**
     *
     * @param stage
     * @param codeArea
     * @param fileName
     */
    public void changeStyle(MainStage stage, CodeArea codeArea, String fileName)
    {
        this.decideStyle(stage, codeArea, fileName);
    }

    /**
     *
     * @param stage
     * @param codeArea
     * @param fileName
     */
    private void decideStyle(MainStage stage, CodeArea codeArea, String fileName)
    {
        if (extensionOf(fileName).equals(".java"))
            setJavaHighlighter(stage, codeArea);
        else if (extensionOf(fileName).equals(".html"))
            setHtmlHighlighter(stage, codeArea);
        else setDefaultStyle(stage);
    }

    /**
     *
     * @param stage
     * @param codeArea
     */
    private void setJavaHighlighter(MainStage stage, CodeArea codeArea)
    {
        stage.getMyScene().getStylesheets().clear();
        stage.getMyScene().getStylesheets().add(styles.getDarkJava());
        javaHighlighter.applyJavaHighlighter(codeArea);
    }

    /**
     *
     * @param stage
     * @param codeArea
     */
    private void setHtmlHighlighter(MainStage stage, CodeArea codeArea)
    {
        stage.getMyScene().getStylesheets().clear();
        stage.getMyScene().getStylesheets().add(styles.getDarkHtml());
        htmlHighlighter.applyHtmlHighlighter(codeArea);
    }

    /**
     *
     * @param stage
     */
    private void setDefaultStyle(MainStage stage)
    {
        stage.getMyScene().getStylesheets().clear();
        stage.getMyScene().getStylesheets().add(styles.getDarkHtml());
    }

}