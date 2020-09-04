package com.zoe.jdeas.model.interfaces;

import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;

import javafx.animation.*;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
/**
 * @author Zoe Lubanza
 */

public interface Utilities{
    String MY_EMAIL_ADDRESS = "mailto:zo.bouk@outlook.com";
    String[] FONT_FAMILY_NAMES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    String[] FONT_SIZES = {"12","14","18","20","22","24","30","34","36","42"};

    /**
     * Fill the comboBox with Font Families and Font Sizes
     * @param mainUI is the main page
     */
    @SuppressWarnings("unchecked")
    default void loadFonts(MainUI mainUI)
    {
        for(String s : FONT_FAMILY_NAMES) mainUI.getFontFamily().getItems().add(s);
        mainUI.getFontFamily().getSelectionModel().select("Monospaced");

        for(String s : FONT_SIZES) mainUI.getFontSize().getItems().add(s);
        mainUI.getFontSize().getSelectionModel().select(1);
    }

    /**
     *
     * @param node the node to be animated
     * @param seconds the duration of the animation
     * @param from the starting point of the transition
     * @param to the ending point of the transition
     * @param count the number of time the animation has to repeat
     */
    default void animateNode(Node node, double seconds, double from, double to, int count)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(seconds),node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.setCycleCount(count);
        fadeTransition.play();
    }

    /**
     *
     * @param stage is the main stage of the application
     * @param message0 message to be displayed
     * @param message1 message to be displayed (Continued..)
     */
    default void showNotification(MainStage stage, String message0, String message1)
    {
        stage.getNotificationLabel().setText(message0+message1);
        stage.getNotificationLabel().setVisible(true);
        this.animateNode(stage.getNotificationLabel(),10,1.0,0.0,0);
    }

    /**
     *
     * @param message is the message to be displayed to the user
     */
    default void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

    /**
     *
     * @param message
     */
    default void showErrorAndCloseProgram(String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * Setup the file chooser
     * @return fileChooser with extension filters
     */
    default FileChooser extensionFilters()
    {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files","*.java"),
                new FileChooser.ExtensionFilter("Html Files","*.html"),
                new FileChooser.ExtensionFilter("CSS Files","*.css"));

        return fileChooser;
    }

    /**
     *
     * @param fileName is the file to extract the extension from
     * @return the extension of a given fileName
     */
    default String extensionOf(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     *
     * @param fileName
     * @return
     */
    default boolean isSupportedExtension(String fileName)
    {
        return extensionOf(fileName).equals(".java")
                || extensionOf(fileName).equals(".html")
                || extensionOf(fileName).equals(".css")
                || extensionOf(fileName).equals(".txt");
    }

    /**
     *
     * @param stage is the main stage of the application
     */
    default void showProgress(MainStage stage)
    {
        stage.getInfoMsg().setVisible(true);
        stage.getProgressBar().setVisible(true);
    }

    /**
     *
     * @param stage is the main stage of the application
     */
    default void hideProgress(MainStage stage)
    {
        stage.getInfoMsg().setVisible(false);
        stage.getProgressBar().setVisible(false);
        stage.getNotificationLabel().setVisible(false);
    }

    /**
     *
     * @param task is a given task to be performed
     */
    default void startThread(Task task)
    {
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     *
     */
    default void denyFilePermission()
    {
        for(File file : allFiles())
        {
            file.setExecutable(false);
            file.setWritable(false);
        }
    }

    /**
     *
     * @return
     */
    default File[] allFiles()
    {
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        File[] file = new File[7];
        try {
            file[0] = new File(Paths.get(location.toURI()).resolve("../logs/recentFiles.log").normalize().toString());
            file[1] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/txt/quotes/quotes.txt").normalize().toString());
            file[2] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/txt/keyWords/java.txt").normalize().toString());
            file[3] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/txt/keyWords/html.txt").normalize().toString());
            file[4] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/stylesheets/darkTheme.css").normalize().toString());
            file[5] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/stylesheets/javaDark.css").normalize().toString());
            file[6] = new File(Paths.get(location.toURI()).resolve("../jDeas/files/stylesheets/htmlDark.css").normalize().toString());
        }catch (Exception e){showErrorAndCloseProgram("Failed to Load some System files, \nPlease reinstall jDeas.");}
        return file;
    }

    /**
     *
     */
    default void grantFilePermission()
    {
        for(File file : allFiles())
        {
            file.setExecutable(true);
            file.setWritable(true);
        }
    }
}