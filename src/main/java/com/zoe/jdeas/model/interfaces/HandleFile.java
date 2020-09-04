package com.zoe.jdeas.model.interfaces;

import javafx.scene.control.Tab;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Zoe Lubanza
 */

public interface HandleFile extends Utilities {

    List<File> FILES = new ArrayList<>();
    List<CodeArea> TEXTAREA = new ArrayList<>();
    List<Tab> TABS = new ArrayList<>();
    StringBuilder RECENT_FILES_PATH = new StringBuilder();

    /**
     *
     * @param index
     * @param tab
     */
    default void addTab(int index,Tab tab)
    {
        TABS.add(index,tab);
    }

    /**
     *
     * @param index
     */
    default void removeTab(int index)
    {
        TABS.remove(index);
    }

    /**
     *
     * @param index
     * @return
     */
    default Tab getTab(int index)
    {
        return TABS.get(index);
    }

    /**
     *
     * @param index
     */
    default void removeFile(int index)
    {
        FILES.remove(index);
    }

    /**
     *
     * @param index
     * @param files
     */
    default void setFiles(int index, File files)
    {
        FILES.set(index,files);
    }

    /**
     *
     * @param index
     * @return
     */
    default File getFiles(int index)
    {
        return FILES.get(index);
    }

    /**
     *
     * @param index
     * @param file
     */
    default void addFile(int index, File file)
    {
        FILES.add(index,file);
    }

    /**
     *
     * @param index
     * @return
     */
    default CodeArea getTextArea(int index)
    {
        return TEXTAREA.get(index);
    }

    /**
     *
     * @param index
     */
    default void removeTextArea(int index)
    {
        TEXTAREA.remove(index);
    }

    /**
     *
     * @param index
     * @param textArea
     */
    default void addTextArea(int index, CodeArea textArea)
    {
        TEXTAREA.add(index,textArea);
    }

    /**
     *
     * @param recentFilesPath
     */
    default void recordRecentFiles(String recentFilesPath)
    {
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        try {
            grantFilePermission();
            Files.write(Paths.get(location.toURI()).resolve("../classes/logs/recentFiles.log").normalize(),
                    Arrays.asList(recentFilesPath), Charset.defaultCharset());
        }catch(URISyntaxException | IOException e){showErrorAndCloseProgram("An important file is missing, please reinstall jDeas.");}
        denyFilePermission();
    }

}