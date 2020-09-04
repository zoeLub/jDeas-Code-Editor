package com.zoe.jdeas.view;

import com.zoe.jdeas.controller.NewFileStage_Controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Zoe Lubanza
 */

public class NewFile_Stage
{
    private Stage myStage;
    private Scene myScene;
    private VBox mainPane;
    private FlowPane fileNamePane, radioButtonPane;
    private BorderPane cancelNewButton;
    private Label fileName;
    private TextField textField;
    private RadioButton txt,java,css,html;
    private Button cancel,New;
    private ToggleGroup toggleGroup;
    private NewFileStage_Controller controller;

    /**
     *
     */
    public NewFile_Stage()
    {
        controller = new NewFileStage_Controller();
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     * @param stage
     */
    public void newFileDialog(MainUI mainUI, HomeUI homeUI, MainStage stage)
    {
        this.populateGUI();
        controller.buttonHandler(this,stage,mainUI,homeUI);
        this.setupStage();
    }

    /**
     *
     */
    private void setupStage()
    {
        myScene = new Scene(mainPane,400,135);
        myScene.getStylesheets().add(getClass().getClassLoader().getResource("jDeas/files/stylesheets/darkTheme.css").toExternalForm());

        myStage = new Stage();
        myStage.setTitle("Create New File");
        myStage.setScene(myScene);
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icons/icon.png")));
        myStage.setResizable(false);
        myStage.showAndWait();
    }

    /**
     *
     */
    private void populateGUI()
    {
        fileName = new Label("File Name:");

        textField = new TextField();
        textField.setPrefWidth(290);

        txt = new RadioButton(".txt");
        txt.setUserData(".txt");
        txt.setId("radioBtns");

        java = new RadioButton(".java");
        java.setUserData(".java");
        java.setId("radioBtns");

        css = new RadioButton(".css");
        css.setUserData(".css");
        css.setId("radioBtns");

        html = new RadioButton(".html");
        html.setUserData(".html");
        html.setId("radioBtns");

        toggleGroup = new ToggleGroup();
        txt.setToggleGroup(toggleGroup);
        java.setToggleGroup(toggleGroup);
        css.setToggleGroup(toggleGroup);
        html.setToggleGroup(toggleGroup);

        cancel = new Button("Cancel");
        cancel.setPrefWidth(70);

        New = new Button("New");
        New.setPrefWidth(70);

        fileNamePane = new FlowPane();
        fileNamePane.setHgap(10);
        fileNamePane.setPadding(new Insets(10,10,0,10));

        radioButtonPane = new FlowPane();
        radioButtonPane.setVgap(10);
        radioButtonPane.setHgap(60);
        radioButtonPane.setPadding(new Insets(0,10,0,10));

        cancelNewButton = new BorderPane();
        cancelNewButton.setPadding(new Insets(5,15,10,15));
        cancelNewButton.setId("new-file-stage-button");

        mainPane = new VBox();
        mainPane.setId("panes");
        mainPane.setSpacing(20);

//        Adding
        fileNamePane.getChildren().addAll(fileName,textField);

        radioButtonPane.getChildren().addAll(txt,css,java,html);

        cancelNewButton.setLeft(cancel);
        cancelNewButton.setRight(New);

        mainPane.getChildren().addAll(fileNamePane,radioButtonPane,cancelNewButton);

    }

    /**
     *
     * @return
     */
    public Stage getMyStage()
    {
        return myStage;
    }

    /**
     *
     * @return
     */
    public TextField getTextField()
    {
        return textField;
    }

    /**
     *
     * @return
     */
    public ToggleGroup getToggleGroup()
    {
        return toggleGroup;
    }

    /**
     *
     * @return
     */
    public Button getCancel()
    {
        return cancel;
    }

    /**
     *
     * @return
     */
    public Button getNew()
    {
        return New;
    }
}
