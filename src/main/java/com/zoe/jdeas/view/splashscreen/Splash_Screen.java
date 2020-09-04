package com.zoe.jdeas.view.splashscreen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

 /**
  * @author Zoe Lubanza
  */

public class Splash_Screen
{
	private ImageView splashScreen;
	private ProgressBar progressBar;
	private AnchorPane anchorPane;
	private Label quote;

	/**
	 *
	 */
	public Splash_Screen()
	{
		initComponents();
	}

	/**
	 *
	 */
	@SuppressWarnings("ConstantConditions")
	private void initComponents()
	{
		progressBar = new ProgressBar();
		progressBar.setPrefSize(200,5);
		progressBar.setPadding(new Insets(0,0,0,0));
		progressBar.setLayoutX(160);
		progressBar.setLayoutY(380);

		quote = new Label("~ Creativity is just connecting things.");
		quote.setLayoutX(115);
		quote.setLayoutY(450);

		splashScreen = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("icons/splashScreen.png")));
		splashScreen.setLayoutX(108);
		splashScreen.setLayoutY(127);

		anchorPane = new AnchorPane();
		anchorPane.setPrefSize(584,346);
		anchorPane.setStyle("-fx-background-color: rgba(0,0,0,0.0);");
		anchorPane.getChildren().addAll(splashScreen,progressBar,quote);

	}//End of LoadingFrame constructor

	public AnchorPane getAnchorPane(){return anchorPane;}

	public ProgressBar getProgressBar()
	{
		return progressBar;
	}

	public Label getQuote() {
		return quote;
	}

}//End of LoadingFrame class