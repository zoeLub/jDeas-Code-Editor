package com.zoe.jdeas.model;

import com.zoe.jdeas.model.interfaces.Utilities;

/**
 * @author Zoe Lubanza
 */

public class LoadStyles implements Utilities {

    private String darkTheme;
    private String darkJava;
    private String darkHtml;

    /**
     *
     */
    @SuppressWarnings("ConstantConditions")
    public LoadStyles()
    {
        try{
            darkTheme = getClass().getClassLoader().getResource("jDeas/files/stylesheets/darkTheme.css").toString();
            darkJava = getClass().getClassLoader().getResource("jDeas/files/stylesheets/javaDark.css").toString();
            darkHtml = getClass().getClassLoader().getResource("jDeas/files/stylesheets/htmlDark.css").toString();
        }catch (Exception e){this.showErrorAndCloseProgram("Failed to Load some System files, \nPlease reinstall jDeas.");}
    }

    /**
     *
     * @return
     */
    public String getDarkTheme() {
        return darkTheme;
    }

    /**
     *
     * @return
     */
    public String getDarkJava() {
        return darkJava;
    }

    /**
     *
     * @return
     */
    public String getDarkHtml() {
        return darkHtml;
    }

}
