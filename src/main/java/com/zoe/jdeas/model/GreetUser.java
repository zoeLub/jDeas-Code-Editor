package com.zoe.jdeas.model;

import java.text.SimpleDateFormat;
import java.util.Date;

 /**
  * @author Zoe Lubanza
  */

public class GreetUser
{
    private String message;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("kk");
    private int time = Integer.parseInt(dateFormat.format(date));

    /**
     *
     */
    public GreetUser()
    {
        if(time >= 17)message = "Good Evening ";
        else if(time >= 12)message = "Good Afternoon ";
        else message = "Good Morning ";
    }

    /**
     *
     * @return
     */
    public String getGreetings()
    {
        return message+System.getProperty("user.name");
    }

}//End GreetingMsg class