package com.zoe.jdeas.model;

import com.zoe.jdeas.model.interfaces.Utilities;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zoe Lubanza
 */

public class GenerateQuotes implements Utilities
{
    private String allLines;
    private String[] arrayOfQuotes;
    private String quote;
    private URL location = getClass().getProtectionDomain().getCodeSource().getLocation();

    /**
     * Constructor
     */
    public GenerateQuotes()
    {
        this.readFile();
    }

    /**
     * Read the file containing all the quotes
     */
    private void readFile()
    {
        try {
            allLines = Files.lines(Paths.get(location.toURI()).resolve("../classes/jDeas/files/txt/quotes/quotes.txt").normalize())
                    .collect(Collectors.joining("\n"));
        }catch (Exception e){this.showErrorAndCloseProgram("Failed to Load some System files, \nPlease reinstall jDeas.");}
        arrayOfQuotes = allLines.split("\n"); //pass each quote into an array of quotes

    }

    /**
     * @return a random quote from the array of quotes
     */
    public String getQuote()
    {
        new Random().ints(0, arrayOfQuotes.length)
                .limit(1)
                .forEach(randomNum -> quote = arrayOfQuotes[randomNum]);
        return quote;
    }
}