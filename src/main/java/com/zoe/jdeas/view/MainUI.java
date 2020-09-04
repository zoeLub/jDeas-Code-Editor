package com.zoe.jdeas.view;

import com.zoe.jdeas.model.interfaces.Utilities;
import com.zoe.jdeas.model.InitializeComponents;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.io.File;

/**
 * @author Zoe Lubanza
 */

public class MainUI extends InitializeComponents implements Utilities
{
    private File file;
    private int selectedTab;
    private int index;
    private String fileName;
    private FileChooser fileChooser;
    private BorderPane textAreaContainer;

    /**
     *
     */
    public MainUI()
    {
        textAreaContainer = new BorderPane();
        selectedTab = 0;
        index = 0;
        loadFonts(this);                //load the fonts to the ComboBox
        fileChooser = extensionFilters();       //setup the FileChooser extensions

        homeMenuItems();
        viewMenuItems();
        helpMenuItems();
        menus();
    }

    /**
     *
     */
    public void populateMainUI()
    {
        super.getTabPane().setId("myTab-pane");

        super.getContentPane().setId("mainUicontentPane");
        super.getMainPane1().setId("mainUicontentPane");
        super.getMainPane().setId("mainUicontentPane");
        super.getCenterPane().setId("mainUicontentPane");

        super.getConsole().setEditable(false);
        VirtualizedScrollPane scrollPane = new VirtualizedScrollPane<>(super.getConsole());

        super.getCloseConsole().setText("Close");
        super.getCloseConsole().setId("closeConsole");

        super.getConsoleContainer().setStyle("-fx-background-color: #2F2F30");
        AnchorPane.setLeftAnchor(scrollPane,.0);
        AnchorPane.setRightAnchor(scrollPane,.0);
        AnchorPane.setBottomAnchor(scrollPane,.0);
        AnchorPane.setTopAnchor(scrollPane,26.0);
        AnchorPane.setRightAnchor(super.getCloseConsole(),15.0);
        AnchorPane.setTopAnchor(super.getCloseConsole(),.0);

        super.getMenuBarPane().setId("menuBarPane");

        super.getMenuItemsPane().setPrefHeight(50);
        super.getMenuItemsPane().setPadding(new Insets(0,5,0,5));
        super.getMenuItemsPane().setId("menuItemsPane");

        super.getMenuBarPane().setTop(super.getMenuPane());

        super.getCenterPane().setCenter(super.getTabPane());

        super.getConsoleContainer().getChildren().addAll(scrollPane,super.getCloseConsole());

        super.getSplitPane().setOrientation(Orientation.VERTICAL);
        super.getSplitPane().getItems().add(super.getCenterPane());
        super.getMainPane1().setTop(super.getMenuBarPane());
        super.getMainPane1().setCenter(super.getSplitPane());
        super.getMainPane().setCenter(super.getMainPane1());
        super.getMainPane().setTop(topBar());

        super.getContentPane().setCenter(super.getMainPane());
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    private void homeMenuItems()
    {
        //        COMBOBOX
        super.getFontFamily().setPrefWidth(145);

        super.getFontSize().setPrefWidth(60);

        super.getJpaste().setText("Paste");
        super.getJpaste().setAlignment(Pos.BASELINE_LEFT);

        super.getJcopy().setText("Copy");
        super.getJcopy().setAlignment(Pos.BASELINE_LEFT);

        super.getJcut().setText("Cut");
        super.getJcut().setAlignment(Pos.BASELINE_LEFT);

        //        BUTTONS
        super.setUndo(new Button("",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/undo.png")))));
        super.getUndo().setId("undo-action");
        super.getUndo().setPrefWidth(15);
        super.setRedo(new Button("",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/redo.png")))));
        super.getRedo().setId("undo-action");
        super.getRedo().setPrefWidth(15);
        super.setCopy(new Button("",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/copy.png")))));
        super.getCopy().setId("action-buttons");
        super.setPaste(new Button("",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/paste.png")))));
        super.getPaste().setId("action-buttons");
        super.setCut(new Button("",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/cut.png")))));
        super.getCut().setId("action-buttons");

//        TEXTFIELDS
        super.getFindField().setPrefSize(150,18);
        super.getFindField().setDisable(true);

        super.getHomeMenuItems().setId("menuItems-pane");
        super.getHomeMenuItems().setPrefHeight(40);
        super.getHomeMenuItems().setHgap(4);
        super.getHomeMenuItems().setPadding(new Insets(5,5,5,5));

        super.getUndoRedoPane().setId("undoRedo-pane");
        super.getUndoRedoPane().setVgap(5);
        super.getUndoRedoPane().setPadding(new Insets(5,0,0,5));
        super.getUndoRedoPane().setPrefSize(50,30);

        super.getCopyPastePane().setId("copyPaste-pane");
        super.getCopyPastePane().setAlignment(Pos.CENTER);
        super.getCopyPastePane().setPrefSize(110,30);

        super.getCopyPane().setId("copy-paste-cut");
        super.getCopyPane().setSpacing(5);
        super.getCopyPane().setAlignment(Pos.CENTER);

        super.getPastePane().setId("copy-paste-cut");
        super.getPastePane().setSpacing(5);
        super.getPastePane().setAlignment(Pos.CENTER);

        super.getCutPane().setId("copy-paste-cut");
        super.getCutPane().setSpacing(5);
        super.getCutPane().setAlignment(Pos.CENTER);

        super.getFontPane().setId("font-pane");
        super.getFontPane().setHgap(3);
        super.getFontPane().setPadding(new Insets(5,0,0,0));
        super.getFontPane().setPrefSize(220,30);

        super.getFindPane().setId("find-pane");
        super.getFindPane().setPadding(new Insets(5,0,0,0));
        super.getFindPane().setPrefSize(300,30);

        super.getUndoRedoPane().getChildren().addAll(super.getUndo(),super.getRedo());

        super.getPastePane().getChildren().addAll(super.getPaste(),super.getJpaste());
        super.getCopyPane().getChildren().addAll(super.getCopy(),super.getJcopy());
        super.getCutPane().getChildren().addAll(super.getCut(),super.getJcut());

        super.getCopyPastePane().getChildren().addAll(super.getPastePane(),super.getCopyPane(),
                super.getCutPane());

        super.getFontPane().getChildren().addAll(super.getFontFamily(),super.getFontSize());

        super.getFindPane().getChildren().add(super.getFindField());

        super.getHomeMenuItems().getChildren().addAll(super.getUndoRedoPane()
                ,super.getCopyPastePane(),super.getFontPane(),super.getFindPane());
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    private void viewMenuItems()
    {
        super.setAllwaysOnTopIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/allwaysOnTop.png"))));
        super.setCastScreenIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/cast.png"))));
        super.setFullScreenIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/fScreen.png"))));

        super.getAllwaysOnTop().setText("AlwaysOnTop");
        super.getCastScreen().setText("Cast Screen");
        super.getFullScreen().setText("FullScreen");

        super.getAlwaysOnTopPane().setId("menuItems");
        super.getAlwaysOnTopPane().setSpacing(5);
        super.getAlwaysOnTopPane().setAlignment(Pos.CENTER);

        super.getCastScreenPane().setId("menuItems");
        super.getCastScreenPane().setSpacing(5);
        super.getCastScreenPane().setAlignment(Pos.CENTER);

        super.getFullScreenPane().setId("menuItems");
        super.getFullScreenPane().setSpacing(5);
        super.getFullScreenPane().setAlignment(Pos.CENTER);

        super.getViewMenuItems().setId("menuItems-pane");
        super.getViewMenuItems().setPrefHeight(40);
        super.getViewMenuItems().setHgap(0);
        super.getViewMenuItems().setPadding(new Insets(10,5,10,5));

//        Add super to panes
        super.getAlwaysOnTopPane().getChildren().addAll(super.getAllwaysOnTopIcon(),super.getAllwaysOnTop());

        super.getCastScreenPane().getChildren().addAll(super.getCastScreenIcon(),super.getCastScreen());

        super.getFullScreenPane().getChildren().addAll(super.getFullScreenIcon(),super.getFullScreen());

        super.getViewMenuItems().getChildren().addAll(super.getAlwaysOnTopPane(),super.getCastScreenPane(),
                super.getFullScreenPane());
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    private void helpMenuItems()
    {
        super.setAboutIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/about.png"))));
        super.setContactIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/contact.png"))));
        super.setUpdateIcon(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/update.png"))));

        super.getAbout().setText("About jDeas");

        super.getContact().setText("Contact Us");

        super.getUpdates().setText("Check for Updates");

        super.getAboutPane().setId("menuItems");
        super.getAboutPane().setAlignment(Pos.CENTER);

        super.getContactPane().setId("menuItems");
        super.getContactPane().setAlignment(Pos.CENTER);

        super.getUpdatePane().setId("menuItems");
        super.getUpdatePane().setAlignment(Pos.CENTER);

        super.getHelpMenuItems().setId("menuItems-pane");
        super.getHelpMenuItems().setPrefHeight(40);
        super.getHelpMenuItems().setHgap(2);
        super.getHelpMenuItems().setPadding(new Insets(10,5,10,5));

//        Add super to panes
        super.getIntroPane().getChildren().addAll(super.getIntroIcon(),super.getIntro());

        super.getAboutPane().getChildren().addAll(super.getAboutIcon(),super.getAbout());

        super.getContactPane().getChildren().addAll(super.getContactIcon(),super.getContact());

        super.getUpdatePane().getChildren().addAll(super.getUpdateIcon(),super.getUpdates());

        super.getHelpMenuItems().getChildren().addAll(super.getAboutPane(),
                super.getContactPane(),super.getUpdatePane());
    }

    /**
     *
     * @return
     */
    private void menus()
    {
        super.getMenuPane().setPrefHeight(20);

        super.getjFile().setText("File");
        super.getjFile().setId("myMenus");
        super.getjFile().setPrefSize(70,25);
        super.getjFile().setAlignment(Pos.CENTER);

        super.getjHome().setText("Home");
        super.getjHome().setId("myMenus");
        super.getjHome().setPrefSize(70,25);
        super.getjHome().setAlignment(Pos.CENTER);

        super.getjView().setText("View");
        super.getjView().setId("myMenus");
        super.getjView().setPrefSize(70,25);
        super.getjView().setAlignment(Pos.CENTER);

        super.getjHelp().setText("Help");
        super.getjHelp().setId("myMenus");
        super.getjHelp().setPrefSize(70,25);
        super.getjHelp().setAlignment(Pos.CENTER);

        super.getjRun().setText("Run");
        super.getjRun().setId("myMenus");
        super.getjRun().setPrefSize(70,25);
        super.getjRun().setAlignment(Pos.CENTER);

        super.getMenuPane().getChildren().addAll(super.getjFile(),super.getjHome(),super.getjView(),super.getjHelp(),super.getjRun());
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ConstantConditions")
    private BorderPane topBar()
    {
        super.getTopPane().setPrefHeight(25);
        super.getTopPane().setPadding(new Insets(3,10,0,5));
        super.getTopPane().setId("topPane");

        super.getLogoPane().setHgap(2);
        super.getLogoPane().setPrefSize(60,25);

        super.getIconPan().setAlignment(Pos.TOP_RIGHT);
        super.getIconPan().setPrefSize(100,25);
        super.getIconPan().setHgap(2);

        super.setLogo(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/icon2.png"))));

        super.setMin(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/min.png"))));
        super.setMinN(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png"))));
        super.getMin().setVisible(false);

        super.setMax(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/max.png"))));
        super.setMaxN(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png"))));
        super.getMax().setVisible(false);

        super.setClose(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/close.png"))));
        super.setCloseN(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png"))));

        super.getFrameTitle().setText("jDeas");
        super.getClose().setVisible(false);

        Tooltip minimize = new Tooltip("Minimize");
        minimize.install(super.getMinPane(),minimize);

        Tooltip maximize = new Tooltip("Maximize");
        maximize.install(super.getMaxPane(),maximize);

        Tooltip closeT = new Tooltip("Close");
        closeT.install(super.getClosePane(),closeT);

        super.getMinPane().getChildren().addAll(super.getMinN(),super.getMin());
        super.getMaxPane().getChildren().addAll(super.getMaxN(),super.getMax());
        super.getClosePane().getChildren().addAll(super.getCloseN(),super.getClose());

        super.getIconPan().getChildren().addAll(super.getMinPane(),super.getMaxPane(),super.getClosePane());

        super.getLogoPane().getChildren().addAll(super.getLogo(),super.getFrameTitle());

        super.getTopPane().setLeft(super.getLogoPane());
        super.getTopPane().setRight(super.getIconPan());

        return super.getTopPane();
    }

    /**
    *   THE FOLLOWINGS ARE THE GETTERS AND THE SETTERS
     */

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BorderPane getTextAreaContainer() {
        return textAreaContainer;
    }

    public void setTextAreaContainer(BorderPane textAreaContainer) {
        this.textAreaContainer = textAreaContainer;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

}// End of MainUI