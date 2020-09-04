package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.ChangeStyle;
import com.zoe.jdeas.model.GenerateQuotes;
import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.model.RunCode;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;

import javafx.application.Application;
import javafx.scene.control.Tooltip;

/**
 * @author Zoe Lubanza
 */

public class MainUI_Controller extends ChangeStyle implements HandleFile
{
    private boolean menuItemIsVisible;
    private MenuItems_Controller itemsController;
    private Font_Controller fontController;
    private GenerateQuotes quotes;
    private RunCode code;

    /**
     *
     */
    public MainUI_Controller()
    {
        menuItemIsVisible = false;
        itemsController = new MenuItems_Controller();
        fontController = new Font_Controller();
        quotes = new GenerateQuotes();
        code = new RunCode();
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     * @param stage
     * @param application
     */
    public void initController(MainUI mainUI, HomeUI homeUI, MainStage stage, Application application)
    {
        itemsController.initControllers(mainUI,fontController,stage,application);
        menuController(mainUI,homeUI,stage);
        setButtonHover(mainUI,stage);
        closeApplication(mainUI,stage);
        minimizeApplication(mainUI,stage);
        maximizeOrRestoreApplication(mainUI,stage);
        tabPaneHandler(mainUI,homeUI,stage);
        closeConsole(mainUI);
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     */
    private void fileMenu(MainUI mainUI, HomeUI homeUI)
    {
        mainUI.getjFile().setOnMouseClicked(event -> displayHomePage(homeUI));
    }

    /**
     *
     * @param homeUI
     */
    private void displayHomePage(HomeUI homeUI)
    {
        homeUI.getQuoteOfTheDay().setText(quotes.getQuote());
        this.animateNode(homeUI.getQuoteOfTheDay(),1.5,0.0,1.0,1);
        homeUI.getContentPane().setVisible(true);
    }

    /**
     *
     * @param mainUI
     */
    private void closeConsole(MainUI mainUI)
    {
        mainUI.getCloseConsole().setOnAction(event -> mainUI.getSplitPane().getItems().remove(1));
    }

    /**
     *
     * @param mainUI
     */
    private void homeMenu(MainUI mainUI)
    {
        mainUI.getjHome().setOnMouseClicked(event -> {
            if(!menuItemIsVisible)
            {
                mainUI.getMenuItemsPane().getChildren().add(mainUI.getHomeMenuItems());
                mainUI.getMenuBarPane().setBottom(mainUI.getMenuItemsPane());
                menuItemIsVisible = true;
            }
            else {
                mainUI.getMenuBarPane().setBottom(null);
                mainUI.getMenuItemsPane().getChildren().remove(0);
                menuItemIsVisible = false;
            }
        });
    }

    /**
     *
     * @param mainUI
     */
    private void viewMenu(MainUI mainUI)
    {
        mainUI.getjView().setOnMouseClicked(event -> {
            if(!menuItemIsVisible)
            {
                mainUI.getMenuItemsPane().getChildren().add(mainUI.getViewMenuItems());
                mainUI.getMenuBarPane().setBottom(mainUI.getMenuItemsPane());
                menuItemIsVisible = true;
            }
            else {
                mainUI.getMenuBarPane().setBottom(null);
                mainUI.getMenuItemsPane().getChildren().remove(0);
                menuItemIsVisible = false;
            }
        });
    }

    /**
     *
     * @param mainUI
     */
    private void helpMenu(MainUI mainUI)
    {
        mainUI.getjHelp().setOnMouseClicked(event -> {
            if(!menuItemIsVisible)
            {
                mainUI.getMenuItemsPane().getChildren().add(mainUI.getHelpMenuItems());
                mainUI.getMenuBarPane().setBottom(mainUI.getMenuItemsPane());
                menuItemIsVisible = true;
            }
            else {
                mainUI.getMenuBarPane().setBottom(null);
                mainUI.getMenuItemsPane().getChildren().remove(0);
                menuItemIsVisible = false;
            }
        });
    }

    /**
     *
     * @param mainUI
     */
    private void runMenu(HomeUI homeUI,MainUI mainUI, MainStage stage)
    {
        mainUI.getjRun().setOnMouseClicked(event -> code.run(homeUI, mainUI, stage));
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     */
    private void menuController(MainUI mainUI, HomeUI homeUI, MainStage stage)
    {
        this.fileMenu(mainUI, homeUI);
        this.homeMenu(mainUI);
        this.viewMenu(mainUI);
        this.helpMenu(mainUI);
        this.runMenu(homeUI, mainUI, stage);
    }

    /**
     * Close application
     * @param mainUI
     * @param stage
     */
    private void closeApplication(MainUI mainUI, MainStage stage)
    {
        mainUI.getClosePane().setOnMouseClicked(event -> this.saveAndClose(stage));
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
     * @param mainUI
     * @param stage
     */
    private void minimizeApplication(MainUI mainUI, MainStage stage)
    {
        mainUI.getMinPane().setOnMouseClicked(event -> stage.getMyStage().setIconified(true));
    }

    /**
     *  Hover for Close, Minimize and Maximize buttons
     * @param mainUI
     */
    @SuppressWarnings("Duplicates")
    private void setButtonHover(MainUI mainUI, MainStage stage)
    {
        mainUI.getClosePane().setOnMouseEntered(event -> mainUI.getClose().setVisible(true));
        mainUI.getClosePane().setOnMouseExited(event -> mainUI.getClose().setVisible(false));
        mainUI.getMinPane().setOnMouseEntered(event -> mainUI.getMin().setVisible(true));
        mainUI.getMinPane().setOnMouseExited(event -> mainUI.getMin().setVisible(false));
        mainUI.getMaxPane().setOnMouseEntered(event -> {
            if(stage.getMyStage().isMaximized())
            {
                Tooltip maximize = new Tooltip("Restore");
                maximize.install(mainUI.getMaxPane(),maximize);
            }
            else {
                Tooltip maximize = new Tooltip("Maximize");
                maximize.install(mainUI.getMaxPane(),maximize);
            }
            mainUI.getMax().setVisible(true);
        });
        mainUI.getMaxPane().setOnMouseExited(event -> mainUI.getMax().setVisible(false));
    }

    /**
     *  Maximize the application
     * @param mainUI
     * @param stage
     */
    private void maximizeOrRestoreApplication(MainUI mainUI, MainStage stage)
    {
        mainUI.getMaxPane().setOnMouseClicked(event -> this.maxOrMin(stage));
    }

    /**
     * Decide whether to maximaze or minimize the application stage
     * @param stage
     */
    private void maxOrMin(MainStage stage){
        if(stage.getMyStage().isMaximized()) stage.getMyStage().setMaximized(false);
        else stage.getMyStage().setMaximized(true);
    }

    /**
     *
     * @param mainUI
     * @param homeUI
     */
    private void tabPaneHandler(MainUI mainUI, HomeUI homeUI,MainStage stage)
    {
        mainUI.getTabPane().getSelectionModel().selectedIndexProperty().addListener( (observable, oldValue, newValue) -> {

            this.resetTabPane(homeUI,mainUI,newValue.intValue());

            mainUI.setSelectedTab( mainUI.getTabPane().getSelectionModel().getSelectedIndex());

            if (mainUI.getIndex()>=1)
                changeStyle(stage,getTextArea(mainUI.getSelectedTab()),getTab(mainUI.getSelectedTab()).getText());

            this.closeTabListener(mainUI, oldValue.intValue(),newValue.intValue());
        });
    }

    /**
     * This method checks if all tabs are closed and therefore
     * displays the Home screen or dashboard and clears
     * the arrayLists content in HandleFile.java
     *
     * @param homeUI Home screen or dashboard
     * @param selectedTab int value of selected tab
     */
    private void resetTabPane(HomeUI homeUI, MainUI mainUI, int selectedTab)
    {
        if(selectedTab == -1)
        {
            homeUI.getBack().setVisible(false);
            homeUI.getContentPane().setVisible(true);
            FILES.clear();
            TEXTAREA.clear();
            TABS.clear();
            mainUI.setIndex(0);
        }
    }

    /**
     *
     * @param mainUI
     * @param oldValue
     * @param newValue
     */
    private void closeTabListener(MainUI mainUI, int oldValue, int newValue)
    {
        if(newValue != -1)
            getTab(newValue).setOnClosed(event -> shiftContent(mainUI, oldValue, newValue));
    }

    /**
     * This method manages the closing of tabs
     * in a way that it shifts the content(File, TextArea, Tab) of the
     * upper Tabs to one tab back.
     * For example if the tabs 0,1,2,3 are opened, by closing Tab 1, This method
     * is responsible of shifting tab 2(and its content)to index 1
     * and tab 3(and its content) to index 2
     *
     * @param mainUI
     * @param oldValue int value of the old tab
     * @param newValue int value of the new tab
     */
    private void shiftContent(MainUI mainUI, int oldValue, int newValue)
    {
        int p = FILES.size();   //get size of the ArrayList

        for (int i = 0; i<p; i++)   //This loop will get us to the value of the tab that was closed(newValue)
        {
            if(i == newValue) //After that, we remove content of the closed tab
            {
                this.clearClosedTabContent(mainUI, newValue);

                for (int j = newValue ; j < p-1; j++) //This loop is the one shifting the content one tab back
                {
                    if(j+1 == p-1) break;
                    this.shiftContent(oldValue,j);
                }
                break;
            }
        }
    }

    /**
     *
     * @param oldValue
     * @param j
     */
    private void shiftContent(int oldValue, int j)
    {
        addFile(oldValue,getFiles(j+1));
        addTab(oldValue,getTab(j+1));
        addTextArea(oldValue, getTextArea(j+1));
    }

    /**
     *
     * @param mainUI
     * @param newValue
     */
    private void clearClosedTabContent(MainUI mainUI, int newValue)
    {
        removeFile(newValue);
        removeTextArea(newValue);
        removeTab(newValue);
        mainUI.setIndex(mainUI.getIndex()-1);
    }

}   // End of MainUI_Controller class