package com.zoe.jdeas.controller;

import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;
import com.zoe.jdeas.view.NewFile_Stage;
import javafx.scene.control.Tooltip;

import java.io.File;

/**
 * @author Zoe Lubanza
 */

public class HomeUI_Controller extends File_Controller
{
    private NewFile_Stage fileStage;

    /**
     *
     */
    public HomeUI_Controller()
    {
        fileStage = new NewFile_Stage();
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    public void initControllers(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        this.setButtonHover(homeUI,stage);
        this.closeApplication(homeUI,stage);
        this.minimizeApplication(homeUI,stage);
        this.maximizeOrMinimizeApplication(homeUI,stage);
        this.openRecentFile(homeUI, mainUI, stage);
        this.backButtonHandler(homeUI, mainUI);
        this.newButtonHandler(homeUI, mainUI, stage);
        this.openButtonHandler(homeUI, mainUI, stage);
        this.saveButtonHandler(homeUI, mainUI, stage);
        this.saveAsButtonHandler(homeUI, mainUI, stage);
        this.printButtonHandler(homeUI, mainUI, stage);
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     */
    private void backButtonHandler(HomeUI homeUI, MainUI mainUI)
    {
        homeUI.getBack().setOnMouseClicked(event -> hideHomepage(homeUI, mainUI));
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     */
    private void hideHomepage(HomeUI homeUI, MainUI mainUI)
    {
        homeUI.getContentPane().setVisible(false);
        getTextArea(mainUI.getSelectedTab()).requestFocus();
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void newButtonHandler(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getNew().setOnMouseClicked(event -> fileStage.newFileDialog(mainUI,homeUI,stage));
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void openButtonHandler(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getOpen().setOnMouseClicked(event -> super.openFile(homeUI, mainUI,stage));
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void saveButtonHandler(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getSave().setOnMouseClicked(event -> super.saveFile(homeUI, mainUI, stage));
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void saveAsButtonHandler(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getSaveAs().setOnMouseClicked(event -> super.saveFileAs(homeUI, mainUI, stage));
    }

    /**
     * 
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void printButtonHandler(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getPrint().setOnAction(event -> super.printFile(mainUI, stage));
    }

    /**
     * Close application
     * @param homeUI
     * @param stage
     */
    private void closeApplication(HomeUI homeUI, MainStage stage)
    {
        homeUI.getClosePane().setOnMouseClicked(event -> this.saveAndClose(stage));
    }

    /**
     *  Checks if files can be written into the recentFiles log and exit the program
     * @param stage
     */
    private void saveAndClose(MainStage stage){
        if(RECENT_FILES_PATH.length()>0) recordRecentFiles(RECENT_FILES_PATH.toString());
        stage.getMyStage().close();
    }

    /**
     * Minimize the application
     * @param homeUI
     * @param stage
     */
    private void minimizeApplication(HomeUI homeUI, MainStage stage)
    {
        homeUI.getMinPane().setOnMouseClicked(event -> stage.getMyStage().setIconified(true));
    }

    /**
     *  Hover for Close, Minimize and Maximize buttons
     * @param homeUI
     */
    @SuppressWarnings("Duplicates")
    private void setButtonHover(HomeUI homeUI, MainStage stage)
    {
        homeUI.getClosePane().setOnMouseEntered(event -> homeUI.getClose().setVisible(true));
        homeUI.getClosePane().setOnMouseExited(event -> homeUI.getClose().setVisible(false));
        homeUI.getMinPane().setOnMouseEntered(event -> homeUI.getMin().setVisible(true));
        homeUI.getMinPane().setOnMouseExited(event -> homeUI.getMin().setVisible(false));
        homeUI.getMaxPane().setOnMouseEntered(event -> {
            if(stage.getMyStage().isMaximized())
            {
                Tooltip maximize = new Tooltip("Restore");
                maximize.install(homeUI.getMaxPane(),maximize);
            }
            else {
                Tooltip maximize = new Tooltip("Maximize");
                maximize.install(homeUI.getMaxPane(),maximize);
            }
            homeUI.getMax().setVisible(true);
        });
        homeUI.getMaxPane().setOnMouseExited(event -> homeUI.getMax().setVisible(false));
    }

    /**
     *  Maximize the application
     * @param homeUI
     * @param stage
     */
    private void maximizeOrMinimizeApplication(HomeUI homeUI, MainStage stage)
    {
        homeUI.getMaxPane().setOnMouseClicked(event -> this.maxOrMin(stage));
    }

    /**
     * Decide whether to maximize or minimize the application stage
     * @param stage
     */
    private void maxOrMin(MainStage stage){
        if(stage.getMyStage().isMaximized()) stage.getMyStage().setMaximized(false);
        else stage.getMyStage().setMaximized(true);
    }

    /**
     * This method decides whether to open a recent file from the Recent file list when selected
     * @param mainUI
     * @param homeUI
     * @param stage
     */
    private void openSelectedItem(MainUI mainUI, HomeUI homeUI,MainStage stage)
    {
        String path = homeUI.getRecentListView().getSelectionModel().getSelectedItem().toString();

        File file = new File(path);

        if (file.exists()){
            mainUI.setFileName(file.getName());
            super.writeToTextArea(homeUI,mainUI,stage,path,file.getName());
        }
        else super.showAlert("File does not Exist in your computer");
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void openRecentFile(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        homeUI.getRecentListView().getSelectionModel().selectedItemProperty()
                .addListener(event -> openSelectedItem(mainUI,homeUI,stage));
    }

}