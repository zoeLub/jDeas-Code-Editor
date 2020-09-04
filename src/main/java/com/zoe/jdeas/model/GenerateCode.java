package com.zoe.jdeas.model;

import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.MainUI;

/**
 * @author Zoe Lubanza
 */

public class GenerateCode implements HandleFile {

    /**
     *
     * @param fileName from the text field
     * @param mainUI the main page
     */
    public void generateCode(String fileName, MainUI mainUI)
    {
        if(extensionOf(fileName).equals(".java"))
            this.generateJavaCode(fileName, mainUI);
        else if (extensionOf(fileName).equals(".html"))
            this.generateHtmlCode(mainUI);
    }

    /**
     *
     * @param fileName from the text field
     * @param mainUI the main page
     */
    private void generateJavaCode(String fileName, MainUI mainUI)
    {
        getTextArea(mainUI.getIndex()).appendText(javaCode(fileName));
    }

    /**
     *
     * @param mainUI the main page
     */
    private void generateHtmlCode(MainUI mainUI)
    {
        getTextArea(mainUI.getIndex()).appendText(htmlCode());
    }

    /**
     *
     * @param fileName from the text field
     * @return the generated text
     */
    private static String javaCode(String fileName)
    {
        return  "public class "+fileName.replace(".java","")+" {\n"
                +"\n    public static void main(String[] args) {"
                +"\n        //Your code here"
                +"\n    }\n"
                +"}";
    }

    /**
     *
     * @return the generated text
     */
    private static String htmlCode()
    {
        return "<html>\n"
                +"\t<head>  </head>\n"
                +"\t<body>\n"
                +"\t</body>\n"
                +"</html>";
    }
}
