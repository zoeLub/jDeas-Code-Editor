package com.zoe.jdeas.controller;

import com.zoe.jdeas.model.ChangeStyle;
import com.zoe.jdeas.model.interfaces.HandleFile;
import com.zoe.jdeas.view.HomeUI;
import com.zoe.jdeas.view.MainStage;
import com.zoe.jdeas.view.MainUI;

import javafx.concurrent.Task;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Zoe Lubanza
 */

public abstract class File_Controller extends ChangeStyle implements HandleFile
{
    private String filePath;
    private boolean approveButton;
    private int max;
    private int min;
    private double percent;
    private boolean isRealExtension;
    private Task<Void> writeToTextAreaTask;

    /**
     *
     */
    public File_Controller()
    {
        filePath = null;
        approveButton = false;
        isRealExtension = false;
        max = 0;
        min = 0;
        percent = 0.0;
    }

    /**
     *
     */
    public void createTextArea(MainUI mainUI)
    {
        addTextArea(mainUI.getIndex(), new CodeArea());
    }

    /**
     *
     */
    public void addTextArea(MainUI mainUI, String fileName, MainStage stage)
    {
        mainUI.getTextAreaContainer().setCenter(new VirtualizedScrollPane<>(getTextArea(mainUI.getIndex())));

        getTextArea(mainUI.getIndex()).setParagraphGraphicFactory(LineNumberFactory.get(getTextArea(mainUI.getIndex())));

        getTextArea(mainUI.getIndex()).setId("codeArea");

        changeStyle(stage,getTextArea(mainUI.getIndex()),fileName);

        mainUI.getTabPane().getSelectionModel().select(mainUI.getIndex());

        getTextArea(mainUI.getIndex()).requestFocus();

        mainUI.setIndex(mainUI.getIndex()+1);
    }

    /**
     *
     * @param fileName
     */
    public void populateTabPane(String fileName, MainUI mainUI)
    {
        mainUI.setTextAreaContainer(new BorderPane());
        mainUI.getTextAreaContainer().setStyle("-fx-background-color: rgba(30,30,30,0.8);");

        addTab(mainUI.getIndex(),new Tab());
        getTab(mainUI.getIndex()).setText(fileName);
        getTab(mainUI.getIndex()).setContent(mainUI.getTextAreaContainer());

        mainUI.getTabPane().getTabs().add(getTab(mainUI.getIndex()));

        mainUI.setSelectedTab(mainUI.getTabPane().getSelectionModel().getSelectedIndex());

        addFile(mainUI.getIndex(),mainUI.getFile());
    }

    /**
     *
     * @param stage the main stage
     */
    private void showOpenDialog(MainStage stage, MainUI mainUI)
    {
        addFile(mainUI.getIndex(),mainUI.getFileChooser().showOpenDialog(stage.getMyStage()));

        if(getFiles(mainUI.getIndex()) != null)
            getFilePathAndName(mainUI);
        else clearFileList();
    }

    /**
     *
     * @param mainUI the main page
     */
    private void getFilePathAndName(MainUI mainUI)
    {
        approveButton = true;
        filePath = getFiles(mainUI.getIndex()).toString();
        mainUI.setFileName(getFiles(mainUI.getIndex()).getName());
    }

    /**
     *
     */
    private void clearFileList()
    {
        FILES.clear();
        approveButton = false;
    }

    /**
     *
     * @param path the path of the file to be opened
     * @return the total line number of the file to be opened
     */
    private void getTotalLineNumber(String path)
    {
        this.resetCounters();
        try {
            Files.lines(Paths.get(path)).forEach(line -> max++);
            isRealExtension = true;
        }catch (Exception e){
            if (isRealExtension)
                showErrorAndCloseProgram("jDeas was unable to read the file.\nFile could be corrupted.");
                else {
                    showAlert("jDeas could not open the file");
                    max = -1;
                }
        }
    }

    /**
     *
     */
    private void resetCounters()
    {
        max = 0;
        min = 0;
        percent = 0.0;
        isRealExtension = false;
    }

    /**
     *
     * @param path the path of the file to be opened
     * @return true if the total line number is <= 2000
     */
    private boolean canOpenFile(String path)
    {
        getTotalLineNumber(path);
        return isSupportedExtension(extensionOf(path))&& max<=2000;
    }

    /**
     * This method returns True if the Files arrayList is
     * Empty, and returns False otherwise... allowing us to know
     * if there is a file to be saved
     *
     * @return true or false
     */
    private boolean canSaveFile()
    {
        return !FILES.isEmpty();
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     * @param path
     * @param fileName
     */
    public void writeToTextArea(HomeUI homeUI,MainUI mainUI, MainStage stage, String path, String fileName)
    {
        this.getTotalLineNumber(path);
        if (max >=0)
        {
            this.populateTabPane(fileName,mainUI);
            this.createTextArea(mainUI);
            this.showProgress(stage);
            showNotification(stage,"Opening ","\""+mainUI.getFileName()+"\""+" Please Wait ...");
            this.writeToTextAreaTask(path, mainUI);
            this.writeToTextAreaTaskOnSucceeded(stage, homeUI, mainUI, path, fileName);
            this.bindProgress(stage.getProgressBar(),stage.getInfoMsg());
            startThread(writeToTextAreaTask);
        }
    }

    /**
     *
     * @param progressBar
     * @param label
     */
    public void bindProgress(ProgressBar progressBar, Label label)
    {
        progressBar.progressProperty().bind(writeToTextAreaTask.progressProperty());
        label.textProperty().bind(writeToTextAreaTask.messageProperty());
    }

    /**
     *
     * @param stage
     * @param homeUI
     * @param mainUI
     * @param path
     * @param fileName
     */
    private void writeToTextAreaTaskOnSucceeded(MainStage stage,HomeUI homeUI, MainUI mainUI, String path, String fileName)
    {
        writeToTextAreaTask.setOnSucceeded(event -> displayMainPage(stage, homeUI, mainUI, path, fileName));
    }

    /**
     *
     * @param stage
     * @param homeUI
     * @param mainUI
     * @param path
     * @param fileName
     * @param node
     */
    private void writeToTextAreaTaskOnSucceeded(MainStage stage,HomeUI homeUI, MainUI mainUI, String path, String fileName,Node node)
    {
        writeToTextAreaTask.setOnSucceeded(event -> displayMainPage(stage, homeUI, mainUI, path, fileName,node));
    }

    /**
     * this method will display the main page after a file has
     * been opened from the fileChooser
     * @param stage
     * @param homeUI
     * @param mainUI
     * @param path
     * @param fileName
     */
    private void displayMainPage(MainStage stage,HomeUI homeUI, MainUI mainUI, String path, String fileName)
    {
        setFiles(mainUI.getIndex(),new File(path));
        this.addTextArea(mainUI,fileName,stage);
        this.disposeHomeUI(homeUI);
        this.hideProgress(stage);
    }

    /**
     * This method will display the main page after the file has been opened
     * from Windows context menu
     * @param stage
     * @param homeUI
     * @param mainUI
     * @param path
     * @param fileName
     * @param node
     */
    private void displayMainPage(MainStage stage, HomeUI homeUI, MainUI mainUI, String path, String fileName, Node node)
    {
        setFiles(mainUI.getIndex(),new File(path));
        this.addTextArea(mainUI,fileName,stage);
        this.disposeHomeUI(homeUI);
        this.hideProgress(stage);
        node.setVisible(false);
        stage.getBorderPane().setVisible(true);
    }

    /**
     *
     * @param path
     * @param mainUI
     */
    private void writeToTextAreaTask(String path, MainUI mainUI)
    {
        writeToTextAreaTask = new Task<Void>() {
            @Override
            public Void call() throws Exception {

                Files.lines(Paths.get(path))
                        .forEach(l -> {
                            getTextArea(mainUI.getIndex()).appendText(l+"\n");
                            min++;

                            percent = (min*100)/max;
                            updateMessage((percent) + "%");
                            updateProgress(min, max);
                        });
                return  null;
            }
        };
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     * @param path
     * @param fileName
     */
    private void writeFileFromContextMenu(HomeUI homeUI,MainUI mainUI, MainStage stage, String path, String fileName,Node node,ProgressBar progressBar,Label label)
    {
        this.getTotalLineNumber(path);
        if (max==-1) showErrorAndCloseProgram("jDeas was unable to read the file.\nFile could be corrupted.");
        this.populateTabPane(fileName,mainUI);
        this.createTextArea(mainUI);
        this.writeToTextAreaTask(path, mainUI);
        this.writeToTextAreaTaskOnSucceeded(stage, homeUI, mainUI, path, fileName,node);
        this.bindProgress(progressBar,label);
        startThread(writeToTextAreaTask);
    }

    /**
     *
     * @param homeUI
     * @param mainUI
     * @param stage
     * @param path
     * @param fileName
     */
    public void openFileFromWindowsContextMenu(HomeUI homeUI,MainUI mainUI, MainStage stage, String path, String fileName,Node node,ProgressBar progressBar,Label label)
    {
        if (canOpenFile(path) && isSupportedExtension(fileName))
            writeFileFromContextMenu(homeUI, mainUI, stage, path, fileName,node,progressBar,label);
        else showErrorAndCloseProgram("File too big or not Supported by jDeas.\nNote that jDeas can only process a file with a maximum a 2000 lines yet.");
    }

    /**
     *
     * @param homeUI
     */
    private void disposeHomeUI(HomeUI homeUI)
    {
        homeUI.getContentPane().setVisible(false);
        homeUI.getBack().setVisible(true);
    }

    /**
     *  This method opens a file from fileChooser
     * @param homeUI
     * @param stage
     */
    public void openFile(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        this.showOpenDialog(stage,mainUI);

        if (approveButton)
            if(!canOpenFile(filePath)) this.showAlert("jDeas cannot process this File");
            else this.writeToTextArea(homeUI,mainUI,stage,filePath,mainUI.getFileName());
    }

    /**
     *
     * @param homeUI
     * @param stage
     */
    public void saveFile(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if(this.canSaveFile())
        {
            if(getFiles(mainUI.getSelectedTab()) == null) this.saveFileAs(homeUI,mainUI,stage);
            else{
                mainUI.setFileName(mainUI.getTabPane().getSelectionModel().getSelectedItem().getText());
                writeToFile(stage,homeUI,mainUI);
            }
        }
        else showAlert("Please Create or Open a File first ;-)");
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    public void printFile(MainUI mainUI, MainStage stage)
    {
        if(canSaveFile())
            this.printerJob(mainUI, stage);
        else showAlert("Nothing to Print. Create or Open a File first.");
    }

    /**
     *
     * @param mainUI
     * @param stage
     */
    private void printerJob(MainUI mainUI, MainStage stage)
    {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        TextFlow textFlow = new TextFlow(new Text(getTextArea(mainUI.getSelectedTab()).getText()));

        if(printerJob != null && printerJob.showPrintDialog(stage.getMyStage()))
        {
            PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
            textFlow.setMaxWidth(pageLayout.getPrintableWidth());

            if(printerJob.printPage(textFlow))
                printerJob.endJob();
        }
    }

    /**
     *
     * @param homeUI
     * @param stage
     */
    public void saveFileAs(HomeUI homeUI, MainUI mainUI, MainStage stage)
    {
        if(canSaveFile())
        {
            mainUI.setFileName(mainUI.getTabPane().getSelectionModel().getSelectedItem().getText());
            mainUI.getFileChooser().setInitialFileName(mainUI.getFileName());

            setFiles(mainUI.getSelectedTab(),mainUI.getFileChooser().showSaveDialog(stage.getMyStage()));

            if(getFiles(mainUI.getSelectedTab()) != null) this.writeToFile(stage,homeUI,mainUI);
        }
        else this.showAlert("Please Create or Open a File first ;-)");
    }

    /**
     * This method writes into the selected file
     * @param stage
     * @param homeUI
     * @param mainUI
     */
    private void writeToFile(MainStage stage, HomeUI homeUI, MainUI mainUI)
    {
        try {
            Files.write(Paths.get(getFiles(mainUI.getSelectedTab()).getPath()),
                    Arrays.asList(TEXTAREA.get(mainUI.getSelectedTab()).getText()), Charset.defaultCharset());
            this.addSavedFileToRecentFiles(getFiles(mainUI.getSelectedTab()).getAbsolutePath());
            showNotification(stage,mainUI.getFileName()," succefully saved !");
            this.disposeHomeUI(homeUI);
        }catch (IOException e){showAlert("Could not save the file");}
    }

    /**
     *
     * @param filePath
     */
    private void addSavedFileToRecentFiles(String filePath)
    {
        RECENT_FILES_PATH.append(filePath+"\n");
    }

}   // End of File_Controller class