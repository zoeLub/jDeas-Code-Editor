package  com.zoe.jdeas.controller;

import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;

import javafx.scene.input.MouseEvent;

/**
 * @author Zoe Lubanza
 */

public class FrameMotionController
{
    private Double Xcoord;
    private Double Ycoord;

    /**
     *
     * @param mainUI
     * @param stage
     */
    public void mainUIMotion(MainUI mainUI, MainStage stage)
    {
        mainUI.getTopPane().setOnMousePressed(event -> mousePressed(stage,event));
        mainUI.getTopPane().setOnMouseDragged(event -> mouseDragged(stage,event));
    }

    /**
     *
     * @param homeUI
     * @param stage
     */
    public void homeUIMotion(HomeUI homeUI, MainStage stage)
    {
        homeUI.getIconPane().setOnMousePressed(event -> mousePressed(stage,event));
        homeUI.getIconPane().setOnMouseDragged(event -> mouseDragged(stage,event));
    }

    /**
     *
     * @param stage
     * @param event
     */
    private void mousePressed(MainStage stage, MouseEvent event)
    {
        Xcoord = stage.getMyStage().getX() - event.getScreenX();
        Ycoord = stage.getMyStage().getY() - event.getScreenY();
    }

    /**
     *
     * @param stage
     * @param event
     */
    private void mouseDragged(MainStage stage, MouseEvent event)
    {
        stage.getMyStage().setX(event.getScreenX() + Xcoord);
        stage.getMyStage().setY(event.getScreenY() + Ycoord);
    }
}