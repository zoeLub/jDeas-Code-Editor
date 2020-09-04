package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.GenerateCode;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;
import com.zoe.jdeas.view.NewFile_Stage;

import javafx.scene.control.TextField;

/**
 * @author Zoe Lubanza
 */

public class NewFileStage_Controller extends File_Controller
{
    private GenerateCode code = new GenerateCode();
    /**
     *
     * @param homeUI
     */
    public void buttonHandler(NewFile_Stage fileStage, MainStage stage, MainUI mainUI, HomeUI homeUI)
    {
        this.cancelButtonHandler(fileStage);
        this.newButtonHandler(fileStage,homeUI,mainUI,stage);
    }

    /**
     *
     * @param fileStage
     */
    private void cancelButtonHandler(NewFile_Stage fileStage)
    {
        fileStage.getCancel().setOnMouseClicked(event -> fileStage.getMyStage().close() );
    }

    /**
     *
     * @param fileStage
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void newButtonHandler(NewFile_Stage fileStage, HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        fileStage.getNew().setOnMouseClicked(event -> {

            if (fileStage.getTextField().getText().isEmpty() || fileStage.getToggleGroup().getSelectedToggle()==null)
            {
                fileStage.getTextField().requestFocus();
                this.animateNode(fileStage.getTextField(), 0.1, 0.0,1.0,3);
            }
            else if(!isValidInput(fileStage.getTextField()))
            {
                this.animateNode(fileStage.getTextField(), 0.1, 0.0,1.0,3);
                super.showNotification(stage,"Invalid input."," No white space and special characters allowed");
            }
            else {
                fileStage.getMyStage().close();
                homeUI.getContentPane().setVisible(false);
                this.createNewFile(fileStage.getTextField().getText()+toggledBtn(fileStage),homeUI,mainUI,stage);
            }
        });
    }

    /**
     *
     * @param fileName
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void createNewFile(String fileName, HomeUI homeUI, MainUI mainUI,MainStage stage)
    {
        super.populateTabPane(fileName,mainUI);
        super.createTextArea(mainUI);
        code.generateCode(fileName,mainUI);
        super.addTextArea(mainUI,fileName,stage);
        homeUI.getBack().setVisible(true);
    }

    /**
     * Check if the characters entered in the textfield
     * are only letters and digits(No special characters)
     * @param textField
     * @return true if no white space or special character is entered
     */
    private boolean isValidInput(TextField textField)
    {
       return textField.getText().matches("[\\w]{1,30}");
    }

    /**
     *
     * @param newFileStage
     * @return
     */
    private String toggledBtn(NewFile_Stage newFileStage)
    {
        return newFileStage.getToggleGroup().getSelectedToggle().getUserData().toString();
    }

}// End of NewFileStage_Controller class
