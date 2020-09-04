package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.MainUI;

/**
 * @author Zoe Lubanza
 */

public class Font_Controller implements HandleFile
{

 public void setFont(MainUI mainUI)
 {
     String fontFamily = mainUI.getFontFamily().getSelectionModel().getSelectedItem().toString();
     int fontSize = Integer.parseInt(mainUI.getFontSize().getSelectionModel().getSelectedItem().toString());

    getTextArea(mainUI.getSelectedTab()).setStyle("" +
            "-fx-font-size: "+fontSize+" ;" +
            "-fx-font-family: "+fontFamily+" ;");
 }

}//     End of Font_Controller class