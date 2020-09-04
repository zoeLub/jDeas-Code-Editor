package com.zoe.jdeas.model;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.fxmisc.richtext.CodeArea;

/**
 * This class initializes all the components used in MainUI class
 *
 * @author Zoe Lubanza
 */

public class InitializeComponents
{
    private SplitPane splitPane;
    private VBox copyPane, pastePane, cutPane;
    private VBox introPane, aboutPane,contactPane,updatePane;
    private VBox alwaysOnTopPane;
    private VBox castScreenPane;
    private VBox fullScreenPane;
    private BorderPane contentPane;
    private BorderPane mainPane;
    private BorderPane mainPane1;
    private BorderPane centerPane;
    private BorderPane menuBarPane;
    private BorderPane topPane;
    private FlowPane copyPastePane;
    private FlowPane homeMenuItems;
    private FlowPane viewMenuItems;
    private FlowPane helpMenuItems;
    private FlowPane undoRedoPane, fontPane, findPane;
    private FlowPane logoPane;
    private FlowPane iconPan;
    private FlowPane menuPane;
    private StackPane closePane;
    private StackPane minPane;
    private StackPane maxPane;
    private StackPane menuItemsPane;
    private AnchorPane consoleContainer;
    private Label frameTitle;
    private Label jFile;
    private Label jHome;
    private Label jView;
    private Label jHelp;
    private Label jRun;
    private Label jpaste, jcopy, jcut;
    private Label intro, about, contact, updates;
    private Label allwaysOnTop,castScreen,fullScreen;
    private Button redo,undo;
    private Button copy, paste, cut, closeConsole;
    private ComboBox<String> fontFamily;
    private ComboBox<String> fontSize;
    private TextField findField;
    private ImageView logo;
    private ImageView min;
    private ImageView minN;
    private ImageView max;
    private ImageView maxN;
    private ImageView close;
    private ImageView closeN;
    private ImageView introIcon, aboutIcon, contactIcon, updateIcon;
    private ImageView allwaysOnTopIcon,castScreenIcon,fullScreenIcon;
    private TabPane tabPane;
    private CodeArea console;

    /**
     *
     */
    public InitializeComponents()
    {
        splitPane = new SplitPane();
        copyPastePane = new FlowPane();
        homeMenuItems = new FlowPane();
        viewMenuItems = new FlowPane();
        helpMenuItems = new FlowPane();
        undoRedoPane = new FlowPane();
        fontPane = new FlowPane();
        findPane = new FlowPane();
        fontFamily = new ComboBox<>();
        fontSize = new ComboBox<>();
        findField = new TextField();
        introIcon = new ImageView();
        aboutIcon = new ImageView();
        contactIcon = new ImageView();
        updateIcon = new ImageView();
        allwaysOnTopIcon = new ImageView();
        castScreenIcon = new ImageView();
        fullScreenIcon = new ImageView();
        jpaste = new Label();
        jcopy = new Label();
        jcut = new Label();
        intro = new Label();
        about = new Label();
        contact = new Label();
        updates = new Label();
        allwaysOnTop = new Label();
        castScreen = new Label();
        fullScreen = new Label();
        redo = new Button();
        undo = new Button();
        copy = new Button();
        paste = new Button();
        cut = new Button();
        copyPane = new VBox();
        pastePane = new VBox();
        cutPane = new VBox();
        introPane = new VBox();
        aboutPane = new VBox();
        contactPane = new VBox();
        updatePane = new VBox();
        alwaysOnTopPane = new VBox();
        castScreenPane = new VBox();
        fullScreenPane = new VBox();
        contentPane = new BorderPane();
        mainPane = new BorderPane();
        mainPane1 = new BorderPane();
        centerPane = new BorderPane();
        menuBarPane = new BorderPane();
        topPane = new BorderPane();
        logoPane = new FlowPane();
        iconPan = new FlowPane();
        menuPane = new FlowPane();
        closePane = new StackPane();
        minPane = new StackPane();
        maxPane = new StackPane();
        menuItemsPane = new StackPane();
        consoleContainer = new AnchorPane();
        logo = new ImageView();
        min = new ImageView();
        minN = new ImageView();
        max = new ImageView();
        maxN = new ImageView();
        close = new ImageView();
        closeN = new ImageView();
        frameTitle = new Label();
        jFile = new Label();
        jHome = new Label();
        jView = new Label();
        jHelp = new Label();
        jRun = new Label();
        tabPane = new TabPane();
        console = new CodeArea();
        closeConsole = new Button();
    }

    public void setAboutIcon(ImageView aboutIcon) {
        this.aboutIcon = aboutIcon;
    }

    public void setAllwaysOnTopIcon(ImageView allwaysOnTopIcon) {
        this.allwaysOnTopIcon = allwaysOnTopIcon;
    }

    public void setCastScreenIcon(ImageView castScreenIcon) {
        this.castScreenIcon = castScreenIcon;
    }

    public void setClose(ImageView close) {
        this.close = close;
    }

    public void setCloseN(ImageView closeN) {
        this.closeN = closeN;
    }

    public void setContactIcon(ImageView contactIcon) {
        this.contactIcon = contactIcon;
    }

    public void setCopy(Button copy) {
        this.copy = copy;
    }

    public void setCut(Button cut) {
        this.cut = cut;
    }

    public void setFullScreenIcon(ImageView fullScreenIcon) {
        this.fullScreenIcon = fullScreenIcon;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    public void setMax(ImageView max) {
        this.max = max;
    }

    public void setMaxN(ImageView maxN) {
        this.maxN = maxN;
    }

    public void setMin(ImageView min) {
        this.min = min;
    }

    public void setMinN(ImageView minN) {
        this.minN = minN;
    }

    public void setPaste(Button paste) {
        this.paste = paste;
    }

    public void setRedo(Button redo) {
        this.redo = redo;
    }

    public void setUndo(Button undo) {
        this.undo = undo;
    }

    public ComboBox getFontFamily()
    {
        return fontFamily;
    }

    public ComboBox getFontSize()
    {
        return fontSize;
    }

    public VBox getAlwaysOnTopPane() {
        return alwaysOnTopPane;
    }

    public VBox getCastScreenPane() {
        return castScreenPane;
    }

    public VBox getAboutPane() {
        return aboutPane;
    }

    public VBox getContactPane() {
        return contactPane;
    }

    public VBox getIntroPane() {
        return introPane;
    }

    public VBox getUpdatePane() {
        return updatePane;
    }

    public VBox getFullScreenPane() {
        return fullScreenPane;
    }

    public VBox getCopyPane() {
        return copyPane;
    }

    public VBox getCutPane() {
        return cutPane;
    }

    public VBox getPastePane() {
        return pastePane;
    }

    public FlowPane getCopyPastePane() {
        return copyPastePane;
    }

    public FlowPane getHelpMenuItems() {
        return helpMenuItems;
    }

    public FlowPane getFindPane() {
        return findPane;
    }

    public FlowPane getFontPane() {
        return fontPane;
    }

    public FlowPane getHomeMenuItems() {
        return homeMenuItems;
    }

    public FlowPane getViewMenuItems() {
        return viewMenuItems;
    }

    public FlowPane getUndoRedoPane() {
        return undoRedoPane;
    }

    public Button getCopy() {
        return copy;
    }

    public Button getCut() {
        return cut;
    }

    public Button getPaste() {
        return paste;
    }

    public Button getRedo() {
        return redo;
    }

    public Button getUndo() {
        return undo;
    }

    public Label getAbout() {
        return about;
    }

    public Label getAllwaysOnTop() {
        return allwaysOnTop;
    }

    public Label getCastScreen() {
        return castScreen;
    }

    public Label getContact() {
        return contact;
    }

    public Label getFullScreen() {
        return fullScreen;
    }

    public Label getIntro() {
        return intro;
    }

    public Label getJcopy() {
        return jcopy;
    }

    public Label getJcut() {
        return jcut;
    }

    public Label getJpaste() {
        return jpaste;
    }

    public Label getUpdates() {
        return updates;
    }

    public TextField getFindField() {
        return findField;
    }

    public ImageView getAboutIcon() {
        return aboutIcon;
    }

    public ImageView getAllwaysOnTopIcon() {
        return allwaysOnTopIcon;
    }

    public ImageView getCastScreenIcon() {
        return castScreenIcon;
    }

    public ImageView getContactIcon() {
        return contactIcon;
    }

    public ImageView getFullScreenIcon() {
        return fullScreenIcon;
    }

    public ImageView getIntroIcon() {
        return introIcon;
    }

    public ImageView getUpdateIcon() {
        return updateIcon;
    }

    public FlowPane getIconPan() {
        return iconPan;
    }

    public FlowPane getLogoPane() {
        return logoPane;
    }

    public FlowPane getMenuPane() {
        return menuPane;
    }

    public ImageView getCloseN() {
        return closeN;
    }

    public ImageView getLogo() {
        return logo;
    }

    public ImageView getMaxN() {
        return maxN;
    }

    public ImageView getMinN() {
        return minN;
    }

    public void setUpdateIcon(ImageView updateIcon) {
        this.updateIcon = updateIcon;
    }

    public Label getFrameTitle() {
        return frameTitle;
    }

    public BorderPane getContentPane()
    {
        return contentPane;
    }

    public BorderPane getTopPane()
    {
        return topPane;
    }

    public BorderPane getCenterPane()
    {
        return centerPane;
    }

    public BorderPane getMenuBarPane()
    {
        return menuBarPane;
    }

    public StackPane getMenuItemsPane()
    {
        return menuItemsPane;
    }

    public StackPane getClosePane()
    {
        return closePane;
    }

    public StackPane getMinPane()
    {
        return minPane;
    }

    public StackPane getMaxPane()
    {
        return maxPane;
    }

    public BorderPane getMainPane1() {
        return mainPane1;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    //    THESE ARE LABEL GETTERS AND SETTERS
    public Label getjFile()
    {
        return jFile;
    }

    public Label getjHome()
    {
        return jHome;
    }

    public Label getjView()
    {
        return jView;
    }

    public Label getjHelp()
    {
        return jHelp;
    }

    //    IMAGE VIEW
    public ImageView getMin()
    {
        return min;
    }

    public ImageView getClose()
    {
        return close;
    }

    public ImageView getMax()
    {
        return max;
    }

    //    EditorView
    public TabPane getTabPane()
    {
        return tabPane;
    }

    //    LIST VIEW
    public AnchorPane getConsoleContainer() {
        return consoleContainer;
    }

    public Label getjRun() {
        return jRun;
    }

    public CodeArea getConsole() {
        return console;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public Button getCloseConsole() {
        return closeConsole;
    }
}