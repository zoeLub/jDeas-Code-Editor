package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.splashscreen.Splash_Screen;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;

import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Zoe Lubanza
 */

public class SplashScreenController extends File_Controller implements HandleFile
{
    private int lineNumber;
    private ShorcutsController shorcutsController;
    private Task<Void> loadingTask;
    private URL location = getClass().getProtectionDomain().getCodeSource().getLocation();

    /**
     * Constructor
     */
    public SplashScreenController()
    {
        lineNumber = 0;
        shorcutsController = new ShorcutsController();
    }

    /**
     *
     * @param splashScreen
     * @param stage
     * @param homeUI
     */
    public void displaySplashScreen(String argument,Splash_Screen splashScreen, MainStage stage, HomeUI homeUI,MainUI mainUI)
    {
        super.denyFilePermission();
        this.simulateProgress();
        this.taskSucceeded(argument, splashScreen, stage, homeUI,mainUI);
        splashScreen.getProgressBar().progressProperty().bind(loadingTask.progressProperty());
        this.startThread(loadingTask);
    }

    /**
     * check if the program is opening for the first time
     * @return true if the recentFiles log exists and is empty
     */
    private boolean firstTimeOpeningApp()
    {
        boolean firstTime = true;

        if (fileExists() && fileIsEmpty()) firstTime = true;
        else if (fileExists() && !fileIsEmpty()) firstTime = false;
        else showErrorAndCloseProgram("Failed to Load some System files, \nPlease reinstall jDeas.");

        return firstTime;
    }

    /**
     *
     * @return
     */
    private boolean fileExists(){
        boolean exist = false;
        try{exist = Files.exists(Paths.get(location.toURI()).resolve("../classes/logs/recentFiles.log").normalize());}
        catch (URISyntaxException e){this.showAlert("An important system exits is missing,\nPlease reinstall jDeas.");}
        return exist;
    }

    /**
     *
     * @return
     */
    private boolean fileIsEmpty()
    {
        try {
            Files.lines(Paths.get(location.toURI()).resolve("../classes/logs/recentFiles.log").normalize())
                    .forEach(line -> lineNumber++);
        }catch (URISyntaxException | IOException e){this.showAlert("jDeas could not access some system files\nError:"+e.getMessage());}

        return lineNumber<1;    //if the line number in the log file is less than one then the file is empty
    }

    /**
     *
     * @param splashScreen
     * @param stage
     * @param homeUI
     */
    private void loadAndShowRecentFiles(Splash_Screen splashScreen, MainStage stage, HomeUI homeUI)
    {
        try {
            Files.lines(Paths.get(location.toURI()).resolve("../classes/logs/recentFiles.log").normalize())
                    .distinct()
                    .forEach(line -> this.fillListView(line,homeUI));
        }catch (URISyntaxException | IOException e){this.showAlert("jDeas could not access some system files\nError:"+e.getMessage());}

        this.displayHomePage(splashScreen,stage,homeUI,true);
    }

    /**
     *
     * @param line
     * @param homeUI
     */
    @SuppressWarnings("unchecked")
    private void fillListView(String line, HomeUI homeUI)
    {
        if(new File(line).exists()){    //check if the path exists before filling the listView
            homeUI.getRecentListView().getItems().add(line);
            RECENT_FILES_PATH.append(line+"\n");
        }
    }

    /**
     *
     * @param splashScreen
     * @param mainStage
     * @param homeUI
     */
    private void displayHomePage(Splash_Screen splashScreen, MainStage mainStage, HomeUI homeUI, boolean state)
    {
        splashScreen.getAnchorPane().setVisible(false);
        homeUI.getRecentListView().setVisible(state);
        mainStage.getBorderPane().setVisible(true);
    }

    /**
     *
     * @param argument
     * @param splashScreen
     * @param stage
     * @param homeUI
     */
    private void decideAction(String argument, Splash_Screen splashScreen, MainStage stage, HomeUI homeUI,MainUI mainUI)
    {
        if(argument!=null)
            openFileFromWindowsContextMenu(homeUI,mainUI,stage,new File(argument).getPath(),new File(argument).getName(),
                    splashScreen.getAnchorPane(),splashScreen.getProgressBar(),splashScreen.getQuote());
        else if(firstTimeOpeningApp())
            this.displayHomePage(splashScreen,stage,homeUI,false);
        else if(!firstTimeOpeningApp())
            this.loadAndShowRecentFiles(splashScreen,stage,homeUI);
        this.loadShortcuts(mainUI,homeUI,stage);
    }

    /**
     *
     * @param argument
     * @param splashScreen
     * @param mainStage
     * @param homeUI
     */
    private void taskSucceeded(String argument, Splash_Screen splashScreen, MainStage mainStage, HomeUI homeUI, MainUI mainUI)
    {
        loadingTask.setOnSucceeded(event -> decideAction(argument, splashScreen, mainStage, homeUI, mainUI));
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     * @param stage
     */
    private void loadShortcuts(MainUI mainUI, HomeUI homeUI, MainStage stage)
    {
        shorcutsController.keyShortcuts(mainUI,homeUI,stage);
    }

    /**
     *
     */
    private void simulateProgress()
    {
        loadingTask = new Task<Void>() {
            @Override
            protected Void call(){
                final int Max = 1000;

                for (int i=0; i<Max; i++)
                {
                    try {
                        Thread.sleep(2);
                    }catch (InterruptedException e){showAlert(""+e.getMessage());}
                    updateProgress(i, Max);
                }
                return null;
            }
        };
    }

}