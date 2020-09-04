package com.zoe.jdeas.view;

import com.zoe.jdeas.model.GenerateQuotes;
import com.zoe.jdeas.model.GreetUser;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import static javafx.scene.text.TextAlignment.CENTER;

/**
 * @author Zoe Lubanza
 */

public class HomeUI
{
    private BorderPane contentPane;
    private BorderPane recentPane;
    private BorderPane greetingPane;
    private StackPane listPane;
    private BorderPane recent;
    private Label recentText;
    private BorderPane greetingGroup;
    private BorderPane leftPane;
    private FlowPane iconPane;
    private StackPane closePane;
    private StackPane minPane;
    private StackPane maxPane;
    private GridPane leftPaneInside;
    private Button jRecent, jNew, jOpen, jSave, jSaveAs, jPrint, back;
    private Label greetingMsg;
    private Label quoteOfTheDay;
    private GreetUser msg;
    private ImageView img;
    private ImageView logo;
    private ImageView min;
    private ImageView minN;
    private ImageView max;
    private ImageView maxN;
    private ImageView close;
    private ImageView closeN;
    private ListView recentListView;
    private GenerateQuotes quotes;


    public HomeUI()
    {
        quotes = new GenerateQuotes();
        msg = new GreetUser();
    }

    /**
     *
     */
    public void initComponents()
    {
//        LABELS
        greetingMsg = new Label(msg.getGreetings());
        greetingMsg.setId("greetingMsg");

        quoteOfTheDay = new Label(quotes.getQuote());
        quoteOfTheDay.setStyle("-fx-font-style: italic; -fx-font-size: 12;");

//        IMAGES
        img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/Emoji.png")));

        logo = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/logo.png")));

        min = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/min.png")));
        minN = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png")));
        min.setVisible(false);

        max = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/max.png")));
        maxN = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png")));
        max.setVisible(false);

        close = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/close.png")));
        closeN = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/neutral.png")));
        close.setVisible(false);

//        BUTTONS
        back = new Button("Back",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/back.png"))));
        back.setId("home-buttons");
        back.setVisible(false);
        Tooltip tip0 = new Tooltip("Go Back");
        Tooltip.install(back,tip0);

        jRecent = new Button("Recent",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/recent.png"))));
        jRecent.setId("home-buttons");
        Tooltip tip1 = new Tooltip("Recent Files");
        Tooltip.install(jRecent,tip1);

        jNew = new Button("New",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/file1.png"))));
        jNew.setId("home-buttons");
        Tooltip tip2 = new Tooltip("Create New File");
        Tooltip.install(jNew,tip2);

        jOpen = new Button("Open",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/folder.png"))));
        jOpen.setId("home-buttons");
        Tooltip tip3 = new Tooltip("Open File");
        Tooltip.install(jOpen,tip3);

        jSave = new Button("Save",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/save.png"))));
        jSave.setId("home-buttons");
        Tooltip tip4 = new Tooltip("Save File");
        Tooltip.install(jSave,tip4);

        jSaveAs = new Button("Save As",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/saveAs.png"))));
        jSaveAs.setId("home-buttons");
        Tooltip tip5 = new Tooltip("Save File As");
        Tooltip.install(jSaveAs,tip5);

        jPrint = new Button("Print",new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/print.png"))));
        jPrint.setId("home-buttons");
        Tooltip tip6 = new Tooltip("Print File");
        Tooltip.install(jPrint,tip6);

//        LIST VIEW
        recentListView = new ListView();

//        PANELS
        contentPane = new BorderPane();
        contentPane.setId("homeUIcontentPane");

        leftPane = new BorderPane();
        leftPane.setPadding(new Insets(0,0,10,0));
        leftPane.setId("leftPane");
        BorderPane.setAlignment(logo, Pos.CENTER);

        recentPane = new BorderPane();
        recentPane.setId("recentPane");
        recentPane.setPadding(new Insets(10,5,10,5));

        leftPaneInside = new GridPane();
        leftPaneInside.setPrefWidth(140);
        leftPaneInside.setPadding(new Insets(10,10,10,0));
        leftPaneInside.setVgap(15);

        listPane = new StackPane();
        listPane.setId("listPane");

        recent = new BorderPane();

        recentText = new Label("No Recent File.\n\n Create a New File or Open a File.");
        recentText.setTextAlignment(CENTER);

        iconPane = new FlowPane();
        iconPane.setAlignment(Pos.TOP_RIGHT);
        iconPane.setId("iconPane");
        iconPane.setPrefHeight(25);
        iconPane.setPadding(new Insets(3,10,0,10));
        iconPane.setHgap(2);

        greetingPane =  new BorderPane();
        greetingPane.setId("greetingPane");
        greetingPane.setPadding(new Insets(0,0,0,0));

        greetingGroup = new BorderPane();
        BorderPane.setAlignment(img, Pos.CENTER);
        BorderPane.setAlignment(greetingMsg, Pos.CENTER);

        minPane = new StackPane();
        Tooltip minimize = new Tooltip("Minimize");
        minimize.install(minPane,minimize);

        maxPane = new StackPane();
        Tooltip maximize = new Tooltip("Maximize");
        maximize.install(maxPane,maximize);

        closePane = new StackPane();
        Tooltip closeT = new Tooltip("Close");
        closeT.install(closePane,closeT);


//        ADD COMPONENTS
        minPane.getChildren().addAll(minN,min);
        maxPane.getChildren().addAll(maxN,max);
        closePane.getChildren().addAll(closeN,close);

        iconPane.getChildren().addAll(minPane,maxPane,closePane);

        greetingGroup.setTop(greetingMsg);
        greetingGroup.setCenter(img);
        greetingGroup.setBottom(quoteOfTheDay);

        greetingPane.setPrefHeight(90);
        greetingPane.setCenter(greetingGroup);
        //greetingPane.setTop(iconPane);

        recent.setCenter(recentText);

        listPane.getChildren().addAll(recent,recentListView);

        recentPane.setCenter(listPane);
        recentPane.setTop(greetingPane);

        leftPaneInside.add(back,0,0);
        leftPaneInside.add(jRecent,0,1);
        leftPaneInside.add(jNew,0,2);
        leftPaneInside.add(jOpen,0,3);
        leftPaneInside.add(jSave,0,4);
        leftPaneInside.add(jSaveAs,0,5);
        leftPaneInside.add(jPrint,0,6);

        leftPane.setBottom(logo);
        leftPane.setCenter(leftPaneInside);

        contentPane.setLeft(leftPane);
        contentPane.setTop(iconPane);
        contentPane.setCenter(recentPane);
    }

    public BorderPane getContentPane()
    {
        return contentPane;
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

    public FlowPane getIconPane() {
        return iconPane;
    }

//    LABEL

    public Label getQuoteOfTheDay() {
        return quoteOfTheDay;
    }

    //  THESE ARE BUTTON GETTERS AND SETTERS

    public Button getNew()
    {
        return jNew;
    }

    public void setNew(Button U)
    {
        jNew = U;
    }

    public Button getOpen()
    {
        return jOpen;
    }

    public Button getSave()
    {
        return jSave;
    }

    public Button getSaveAs()
    {
        return jSaveAs;
    }

    public Button getPrint()
    {
        return jPrint;
    }

    public Button getBack() {
        return back;
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

//    LIST VIEW

    public ListView getRecentListView() {
        return recentListView;
    }
}// End of FaView