package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * @author Zoe Lubanza
 */

public class MenuItems_Controller implements HandleFile
{
    /**
     *
     * @param mainUI
     * @param fontController
     * @param stage
     * @param application
     */
    public void initControllers(MainUI mainUI, Font_Controller fontController, MainStage stage, Application application)
    {
        this.viewMenuItem(mainUI,stage);
        this.homeMenuItem(mainUI,fontController);
        this.helpMenuItem(mainUI,stage,application);
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private void alwaysOnTopButtonHandler(MainUI mainUI, MainStage stage)
    {
        mainUI.getAlwaysOnTopPane().setOnMouseClicked(event -> toggleAlwaysOnTop(stage));
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private void viewMenuItem(MainUI mainUI, MainStage stage)
    {
        this.alwaysOnTopButtonHandler(mainUI, stage);
        this.fullScreenButtonHandler(stage, mainUI);
        this.castScreenButtonHandler(mainUI,stage);
    }

    /**
     *
     * @param mainUI
     * @param fontController
     */
    private void homeMenuItem(MainUI mainUI, Font_Controller fontController)
    {
       this.changeFontFamily(mainUI, fontController);
       this.changeFontSize(mainUI, fontController);
       this.undoButtonHandler(mainUI);
       this.redoButtonHandler(mainUI);
       this.copyButtonHandler(mainUI);
       this.pasteButtonHandler(mainUI);
       this.cutButtonHandler(mainUI);
    }

    /**
     *
     * @param mainUI
     * @param fontController
     */
    private void changeFontFamily(MainUI mainUI, Font_Controller fontController)
    {
        mainUI.getFontFamily().valueProperty().addListener(event -> fontController.setFont(mainUI));
    }

    /**
     *
     * @param mainUI
     * @param fontController
     */
    private void changeFontSize(MainUI mainUI, Font_Controller fontController)
    {
        mainUI.getFontSize().valueProperty().addListener(event -> fontController.setFont(mainUI));
    }

    /**
     *
     * @param mainUI
     * @param stage
     * @param application
     */
    private void helpMenuItem(MainUI mainUI, MainStage stage, Application application)
    {
        this.aboutUsButtonHandler(mainUI);
        this.contactUsButtonHandler(mainUI, application);
        this.checkForUpdatesButtonHandler(mainUI, stage);
    }

    /**
     *
     * @param mainUI
     */
    private void aboutUsButtonHandler(MainUI mainUI)
    {
        mainUI.getAboutPane().setOnMouseClicked(event -> displayAboutUsStage());
    }

    /**
     *
     */
    @SuppressWarnings("ConstantConditions")
    private void displayAboutUsStage()
    {
        StackPane pane = new StackPane();
        ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/aboutUs.png")));
        pane.getChildren().add(imageView);
        Scene scene = new Scene(pane,584,346);
        scene.setFill(Color.TRANSPARENT);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        waitFor5SecondsAndCloseStage(stage);
    }

    /**
     *
     * @param stage
     */
    private void waitFor5SecondsAndCloseStage(Stage stage)
    {
        EventHandler onFinished = event -> stage.close();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),onFinished));
        timeline.play();
    }

    /**
     *
     * @param mainUI
     * @param application
     */
    private void contactUsButtonHandler(MainUI mainUI, Application application)
    {
        mainUI.getContactPane().setOnMouseClicked(event -> openMailService(application));
    }

    /**
     * this method open the mail service and add the developer's email address as recipient
     * @param application is the Main program
     */
    private void openMailService(Application application)
    {
        application.getHostServices().showDocument(MY_EMAIL_ADDRESS);
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private void castScreenButtonHandler(MainUI mainUI, MainStage stage)
    {
        mainUI.getCastScreenPane().setOnMouseClicked(event -> showNotification(stage,"Sorry, ","jDeas was unable to cast this screen"));
    }

    /**
     *
     * @param mainUI is the main page
     * @param stage the main stage
     */
    private void checkForUpdatesButtonHandler(MainUI mainUI,MainStage stage)
    {
        mainUI.getUpdatePane().setOnMouseClicked(event -> showNotification(stage, "No updates Available. ","Please check later"));
    }

    /**
     *
     * @param mainUI
     */
    private void undoButtonHandler(MainUI mainUI)
    {
        mainUI.getUndo().setOnAction(event -> undo(mainUI));
    }

    /**
     *
     * @param mainUI
     */
    private void redoButtonHandler(MainUI mainUI)
    {
        mainUI.getRedo().setOnAction(event -> redo(mainUI));
    }

    /**
     *
     * @param mainUI
     */
    private void copyButtonHandler(MainUI mainUI)
    {
        mainUI.getCopy().setOnAction(event -> copy(mainUI));
    }

    /**
     *
     * @param mainUI
     */
    private void pasteButtonHandler(MainUI mainUI)
    {
        mainUI.getPaste().setOnAction(event -> paste(mainUI));
    }

    /**
     *
     * @param mainUI
     */
    private void cutButtonHandler(MainUI mainUI)
    {
        mainUI.getCut().setOnAction(event -> cut(mainUI));
    }

    /**
     *
     * @param mainUI
     */
    private void undo(MainUI mainUI)
    {
        if(getTextArea(mainUI.getSelectedTab()).isUndoAvailable())
            getTextArea(mainUI.getSelectedTab()).undo();
    }

    /**
     *
     * @param mainUI
     */
    private void redo(MainUI mainUI)
    {
        if(getTextArea(mainUI.getSelectedTab()).isRedoAvailable())
            getTextArea(mainUI.getSelectedTab()).redo();
    }

    /**
     *
     * @param mainUI
     */
    private void paste(MainUI mainUI)
    {
        getTextArea(mainUI.getSelectedTab()).paste();
    }

    /**
     *
     * @param mainUI
     */
    private void copy(MainUI mainUI)
    {
        getTextArea(mainUI.getSelectedTab()).copy();
    }

    /**
     *
     * @param mainUI
     */
    private void cut(MainUI mainUI)
    {
        getTextArea(mainUI.getSelectedTab()).cut();
    }

    /**
     *
     * @param stage
     */
    private void toggleAlwaysOnTop(MainStage stage)
    {
        if (stage.getMyStage().isAlwaysOnTop()){
            stage.getMyStage().setAlwaysOnTop(false);
            showNotification(stage,"Always On Top ","Disabled");
        }
        else {
            stage.getMyStage().setAlwaysOnTop(true);
            showNotification(stage,"Always On Top ","Enabled");
        }
    }

    /**
     *
     * @param stage
     * @param mainUI
     */
    private void fullScreenButtonHandler(MainStage stage, MainUI mainUI)
    {
        mainUI.getFullScreenPane().setOnMouseClicked(event -> setFullScreen(mainUI, stage));
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private static void setFullScreen(MainUI mainUI, MainStage stage)
    {
        mainUI.getMainPane1().setTop(null);
        mainUI.getMainPane().setTop(null);
        stage.getMyStage().setFullScreen(true);
    }

}