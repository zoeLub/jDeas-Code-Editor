package com.zoe.jdeas.view;

import com.zoe.jdeas.model.LoadStyles;
import com.zoe.jdeas.view.splashscreen.Splash_Screen;
import com.zoe.jdeas.controller.InitializeControllers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Zoe Lubanza
 */

public class MainStage extends Stage
{
    private Scene myScene;
    private Stage myStage;
    private StackPane mainPane;
    private StackPane stackPane;
    private BorderPane borderPane, bottomPane;
    private FlowPane flowPane;
    private MainUI mainUI;
    private HomeUI homeUI;
    private Splash_Screen splashScreen;
    private Label infoMsg, notificationLabel;
    private ProgressBar progressBar;
    private InitializeControllers controllers;
    private LoadStyles styles;

    /**
     *
     * @param application
     */
    public MainStage(String argument, Application application)
    {
        styles = new LoadStyles();
        controllers = new InitializeControllers();
        mainUI = new MainUI();
        homeUI = new HomeUI();
        splashScreen = new Splash_Screen();
        mainUI.populateMainUI();
        homeUI.initComponents();
        controllers.allControllers(argument,splashScreen,mainUI,homeUI,this, application);
    }

    /**
     *
     */
    public void setupStage()
    {
        myScene = new Scene(populateGUI(),800,600);
        myScene.setFill(Color.TRANSPARENT);
        myScene.getStylesheets().add(styles.getDarkTheme());

        myStage = new Stage();
        myStage.setTitle("jDeas");
        myStage.setScene(myScene);
        myStage.initStyle(StageStyle.UNDECORATED);
        myStage.initStyle(StageStyle.TRANSPARENT);
        myStage.getIcons().add(new Image(getClass().getClassLoader().getResource("icons/icon.png").toExternalForm()));
        myStage.show();
    }

    /**
     *
     * @return
     */
    private StackPane populateGUI()
    {
        mainPane = new StackPane();
        mainPane.setStyle("-fx-background-color: rgba(0,0,0,0.0);");
        mainPane.getChildren().addAll(mainUI.getContentPane(),homeUI.getContentPane());
        //mainPane.setPadding(new Insets(10,10,10,10));

        infoMsg = new Label("");
        infoMsg.setVisible(false);
        infoMsg.setId("notification");

        notificationLabel = new Label("");
        notificationLabel.setVisible(false);
        notificationLabel.setId("notification");

        progressBar = new ProgressBar();
        progressBar.setPrefHeight(10);
        progressBar.setVisible(false);

        flowPane = new FlowPane();
        flowPane.setAlignment(Pos.BOTTOM_RIGHT);
        flowPane.setHgap(5);
        flowPane.getChildren().addAll(progressBar,infoMsg);

        bottomPane = new BorderPane();
        bottomPane.setId("notification-bar");
        bottomPane.setLeft(notificationLabel);
        bottomPane.setRight(flowPane);


        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: rgba(0,0,0,0.0);");
        borderPane.setCenter(mainPane);
        borderPane.setBottom(bottomPane);
        borderPane.setVisible(false);

        stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: rgba(0,0,0,0.0);");
        stackPane.getChildren().addAll(splashScreen.getAnchorPane(),borderPane);

        return stackPane;
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
    public Scene getMyScene() {
        return myScene;
    }

    /**
     *
     * @return
     */
    public BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     *
     * @return
     */
    public Label getInfoMsg() {
        return infoMsg;
    }

    /**
     *
     * @return
     */
    public Label getNotificationLabel() {
        return notificationLabel;
    }

    /**
     *
     * @return
     */
    public ProgressBar getProgressBar() {
        return progressBar;
    }

}