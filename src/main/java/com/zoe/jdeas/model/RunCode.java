package com.zoe.jdeas.model;

import com.zoe.jdeas.controller.File_Controller;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;
import javafx.concurrent.Task;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Zoe Lubanza
 */

public class RunCode extends File_Controller {
    private Process process;
    private String line;
    private int min =0;
    private int max = 0;
    private Task<Void> getErrorTask,getOutputTask,getLineNumTask;

    /**
     * This method decides how to run a file based on the file extension
     * @param mainUI the main page
     */
    public void run(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if (extensionOf(getTab(mainUI.getSelectedTab()).getText()).equals(".java"))
            this.runJavaCode(homeUI, mainUI, stage);
        else if (extensionOf(getTab(mainUI.getSelectedTab()).getText()).equals(".html"))
            this.runHtml(homeUI, mainUI, stage);
        else super.showAlert("Cannot run the selected file");
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void runJavaCode(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if(canRunFile(mainUI))
        {
            this.clearConsole(mainUI);
            this.removeConsole(mainUI);
            this.saveFile(homeUI, mainUI, stage);
            getLineNumTask = new Task<Void>() {
                @Override
                protected Void call(){
                    getMaxLines(mainUI);
                    return null;
                }
            };
            getLineNumTask.setOnSucceeded(event -> {
                runCommand(mainUI);
                getOutputOrErrorMessage(mainUI,stage);
            });

            this.startThread(getLineNumTask);
        }
        else super.showAlert("Please save the file first.\nPress CTRL+S to save.");
    }

    /**
     *
     * @param mainUI
     */
    private void runCommand(MainUI mainUI)
    {
        String changeDirectory = "cmd start cmd.exe /c cd "+getFiles(mainUI.getSelectedTab()).getParent();
        String compile = " && javac "+getFiles(mainUI.getSelectedTab()).getName();
        String run = " && java "+getFiles(mainUI.getSelectedTab()).getName().replace(".java","");
        String command = changeDirectory + compile + run;

        try {
            process = Runtime.getRuntime().exec(command);
        }catch (IOException e){}
    }

    /**
     *
     * @param mainUI
     */
    private void clearConsole(MainUI mainUI)
    {
        mainUI.getConsole().clear();
    }

    /**
     *
     * @param mainUI
     */
    private void displayConsole(MainUI mainUI)
    {
        if(mainUI.getSplitPane().getItems().size()<=1)
            mainUI.getSplitPane().getItems().add( mainUI.getConsoleContainer());
    }

    /**
     *
     * @param mainUI
     */
    private void removeConsole(MainUI mainUI)
    {
        if(mainUI.getSplitPane().getItems().size()>1)
            mainUI.getSplitPane().getItems().remove(1);
    }

    /**
     *
     * @param mainUI
     * @param line
     */
    private void writeToConsole(MainUI mainUI,String line)
    {
        mainUI.getConsole().appendText(line+"\n");
    }

    /**
     *
     * @param mainUI
     */
    private void getOutputOrErrorMessage(MainUI mainUI,MainStage stage)
    {
//        if(runJavaTask.isRunning()){
//            stopThread();
//            runJavaTask.cancel();
//        }

        getErrorTask = new Task<Void>() {
            @Override
            protected Void call(){

                try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream())))
                {
                    if(errorReader.readLine() != null)
                    {
                        showProgress(stage);
                        setErrorStyle(mainUI);
                        while ((line = errorReader.readLine()) != null)
                        {
                            writeToConsole(mainUI, line);          //display error message
                            min++;
                            updateProgress(min,max);
                        }
                    }else {
                        getErrorTask.cancel();
                        getOutput(mainUI,stage);   //display output
                    }

                }catch (IOException e){showAlert("Something went wrong, please try again");}

                return null;
            }
        };
        getErrorTask.setOnSucceeded(event -> {
            this.displayConsole(mainUI);
            this.hideProgress(stage);
        });
        stage.getProgressBar().progressProperty().bind(getErrorTask.progressProperty());
        startThread(getErrorTask);


    }

    /**
     *
     * @param mainUI
     */
    private void getMaxLines(MainUI mainUI)
    {
        max = 0;
        min = 0;
        runCommand(mainUI);

        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream())))
        {
            if(errorReader.readLine() != null)
            {
                while ((line = errorReader.readLine()) != null)
                    max++;
            }

            else {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
                {
                    while ((line = reader.readLine()) != null)
                        max++;
                }catch (IOException e){super.showAlert("Something went wrong, please try again");}
            }

        }catch (IOException e){super.showAlert("Something went wrong, please try again");}
    }

    /**
     *
     * @param mainUI
     */
    private void setErrorStyle(MainUI mainUI)
    {
        mainUI.getConsole().setId("error");
    }

    /**
     *
     * @param mainUI
     */
    private void setOutputStyle(MainUI mainUI)
    {
        mainUI.getConsole().setId("console");
    }

    /**
     *
     * @param mainUI
     */
    private void getOutput(MainUI mainUI, MainStage stage)
    {
//        if(runJavaTask.isRunning()){
//            this.stopThread();
//            runJavaTask.cancel();
//        }

        //getErrorTask.cancel();

        getOutputTask = new Task<Void>() {
            @Override
            protected Void call() {

                showProgress(stage);
                setOutputStyle(mainUI);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
                {
                    while ((line = reader.readLine()) != null)
                    {
                        writeToConsole(mainUI, line);
                        min++;
                        updateProgress(min,max);
                    }

                }catch (IOException e){showAlert("Something went wrong, please try again");}
                return null;
            }
        };
        getOutputTask.setOnSucceeded(event -> {
            this.displayConsole(mainUI);
            this.hideProgress(stage);
        });
        stage.getProgressBar().progressProperty().bind(getOutputTask.progressProperty());
        this.startThread(getOutputTask);

    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     */
    private void runHtml(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if(canRunFile(mainUI))
        {
            super.saveFile(homeUI, mainUI, stage);
            this.openFileInBrowser(mainUI);
        }
        else super.showAlert("Please save the file first");
    }

    /**
     * This method opens the absolute path of the active file
     * in the system's default web browser
     * @param mainUI
     */
    private void openFileInBrowser(MainUI mainUI)
    {
        try {
            Desktop.getDesktop().browse(getFiles(mainUI.getSelectedTab()).toURI());
        }catch (IOException e){super.showAlert("Could not run the file, Something went wrong.");}
    }

    /**
     * check if the file the user wants to run is saved first
     * @param mainUI
     * @return
     */
    private boolean canRunFile(MainUI mainUI)
    {
        return super.getFiles(mainUI.getSelectedTab()) != null;
    }

}
