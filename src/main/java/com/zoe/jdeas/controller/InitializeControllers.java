package com.zoe.jdeas.controller;

import com.zoe.jdeas.view.splashscreen.Splash_Screen;
import com.zoe.jdeas.view.*;

import javafx.application.Application;

/**
 * @author Zoe Lubanza
 */

public class InitializeControllers
{
    private MainUI_Controller controller = new MainUI_Controller();
    private HomeUI_Controller homeUI_controller = new HomeUI_Controller();
    private FrameMotionController motionController = new FrameMotionController();
    private SplashScreenController splashScreenController = new SplashScreenController();

    public void allControllers(String argument,Splash_Screen splashScreen, MainUI frame, HomeUI homeUI, MainStage stage, Application application)
    {
        splashScreenController.displaySplashScreen(argument,splashScreen,stage,homeUI,frame);
        controller.initController(frame,homeUI,stage, application);
        homeUI_controller.initControllers(homeUI,frame,stage);
        motionController.mainUIMotion(frame,stage);
        motionController.homeUIMotion(homeUI,stage);
    }
}
