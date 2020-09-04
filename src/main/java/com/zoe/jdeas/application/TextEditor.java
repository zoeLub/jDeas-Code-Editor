package com.zoe.jdeas.application;

import com.zoe.jdeas.view.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Zoe Lubanza
 */

public class TextEditor extends Application
{
    private static String argument;
    @Override
    public void start(Stage primaryStage)
    {
        MainStage mainStage = new MainStage(argument,this);
        mainStage.setupStage();
    }

    public static void main(String[] args)
    {
        if(args.length>0) argument = args[0];
        launch(args);
    }

}//end of TextEditor class