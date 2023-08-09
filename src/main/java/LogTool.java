/**
  Copyright (c) 2019 Wolfgang Hauptfleisch <dev@augmentedlogic.com>

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
 **/
package com.augmentedlogic.flere.toolkit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.util.Properties;


public class LogTool {


    private static final String DEFAULT_DATE_FORMAT = "dd-M-yyyy HH:mm:ss";
    private static final String DEFAULT_LOG_DIR = "." + File.separator;
    private static final String DEFAULT_LOG_FILE = "service";

    private String logfile = "service";
    private String tag = null;


    public LogTool()
    {


    }

    public LogTool(String logfile)
    {
        this.logfile = logfile;
    }

    public static void setLogDirProperty(String path)
    {
         if(!path.endsWith(File.separator)) {
            path = path + File.separator;
         }
         Properties props = System.getProperties();
         props.setProperty("flere.log.dir", path);
    }

    /**
     * get the point at which the log entry was written
     **/
    public static String getLogPoint()
    {
        return Thread.currentThread().getStackTrace()[2].getClassName() + File.separator + Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public LogTool tag(String tag)
    {
       this.tag = tag;
       return this;
    }


    /**
     *
     **/
    private void writeTo(String logfile, String tag, String msg)  {

        Properties systemProperties = System.getProperties();
        String date_format = systemProperties.getProperty("flere.toolkit.log.dateformat");
        if(date_format == null) {
            date_format = LogTool.DEFAULT_DATE_FORMAT;
        }
        String log_dir = systemProperties.getProperty("flere.log.dir");
        if(log_dir == null) {
            log_dir = LogTool.DEFAULT_LOG_DIR;
        }

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat(date_format);
        String DateToStr = format.format(curDate);

        String logmsg = DateToStr + " " + msg + "\n";
        if(tag != null) {
           logmsg = DateToStr + " [" + tag + "] " + msg + "\n";
        }

        String fileName = log_dir + File.separator + logfile + ".log";
        PrintWriter printWriter = null;
        File file = new File(fileName);

        try {
            if (!file.exists()) file.createNewFile();
            printWriter = new PrintWriter(new FileOutputStream(fileName, true));
            printWriter.write(logmsg);
        } catch (IOException e) {
            System.out.println("can't write Logfile");
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }


    /**
     *
     **/
    public void write(String msg)
    {
        this.writeTo(this.logfile, this.tag, msg);
    }

    public static void error(String tag, Exception emsg)  {

    }

    /**
     *
     **/
    public void error(Exception emsg)  {
        this.writeTo(this.logfile, this.tag, emsg.getMessage());
    }

}

