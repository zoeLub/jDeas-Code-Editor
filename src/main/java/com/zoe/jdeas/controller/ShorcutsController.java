package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.RunCode;
import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;
import com.zoe.jdeas.view.NewFile_Stage;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author Zoe Lubanza
 */

public class ShorcutsController extends File_Controller implements HandleFile
{
    private NewFile_Stage newFileStage = new NewFile_Stage();
    private RunCode code = new RunCode();
    private int ftSize = 14;

    /**
     *
     * @param mainUI frame
     * @param homeUI welcome page
     * @param stage main stage
     */
    public void keyShortcuts(MainUI mainUI, HomeUI homeUI,MainStage stage)
    {
        stage.getMyScene().setOnKeyPressed(ke -> this.decideAction(ke,mainUI,homeUI,stage));
    }

    /**
     * Choose what action to perform based on the key combination pressed
     * @param ke
     * @param mainUI
     * @param homeUI
     * @param stage
     */
    private void decideAction(KeyEvent ke, MainUI mainUI, HomeUI homeUI,MainStage stage)
    {
        if(ke.isControlDown() && ke.getCode()==KeyCode.N)       //Create new File
            newFileStage.newFileDialog(mainUI, homeUI, stage);

        else if(ke.isControlDown() && ke.getCode()==KeyCode.O)    //Open File
            super.openFile(homeUI,mainUI,stage);

        else if(ke.isControlDown() && ke.getCode()==KeyCode.S)   //Save file
            super.saveFile(homeUI,mainUI,stage);

        else if(ke.isControlDown() && ke.getCode()==KeyCode.P)   //Save file
            super.printFile(mainUI,stage);

        else if (ke.isControlDown() && ke.getCode()==KeyCode.R)
            runCode(homeUI, mainUI, stage);

        else if(ke.getCode()==KeyCode.F11 && !stage.getMyStage().isFullScreen())   //Enable full screen
            enableFullScreen(mainUI,stage);

        else if(ke.getCode()==KeyCode.ESCAPE)   //exit full screen
            exitFullScreen(mainUI,stage);

        else if(ke.isControlDown() && ke.getCode()==KeyCode.L)       //Zoom in
            zoomIn(mainUI);

        else if(ke.isControlDown() && ke.getCode()==KeyCode.M)       //Zoom out
            zoomOut(mainUI);
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void runCode(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if(!homeUI.getContentPane().isVisible())
            code.run(homeUI, mainUI, stage);
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private static void exitFullScreen(MainUI mainUI, MainStage stage)
    {
        mainUI.getMainPane1().setTop(mainUI.getMenuBarPane());
        mainUI.getMainPane().setTop(mainUI.getTopPane());
        stage.getMyStage().setFullScreen(false);
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private static void enableFullScreen(MainUI mainUI, MainStage stage)
    {
        mainUI.getMainPane1().setTop(null);
        mainUI.getMainPane().setTop(null);
        stage.getMyStage().setFullScreen(true);
    }

    /**
     *
     * @param mainUI
     */
    private void zoomIn(MainUI mainUI)
    {
        String fontFamily = mainUI.getFontFamily().getSelectionModel().getSelectedItem().toString();

        if(getTextArea(mainUI.getSelectedTab()).isFocused())
        {
            ftSize = ftSize +2;

            getTextArea(mainUI.getSelectedTab()).setStyle("" +
                    "-fx-font-size: "+ftSize+" ;" +
                    "-fx-font-family: "+fontFamily+" ;");
        }
    }

    /**
     *
     * @param mainUI
     */
    private void zoomOut(MainUI mainUI)
    {
        String fontFamily = mainUI.getFontFamily().getSelectionModel().getSelectedItem().toString();

        if(getTextArea(mainUI.getSelectedTab()).isFocused())
            if(ftSize>=8)
            {
                ftSize = ftSize -2;
                getTextArea(mainUI.getSelectedTab()).setStyle("" +
                        "-fx-font-size: "+ftSize+" ;" +
                        "-fx-font-family: "+fontFamily+" ;");
            }
    }

}