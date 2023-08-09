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

import java.io.*;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import java.io.IOException;
import java.util.*;


public class FileTool
{

    public static String getBasename(String path)
    {
        File file_object = new File(path);
        return file_object.getName();
    }


    public static Boolean writeToFile(String filename , String data)
    {
        Boolean status = false;
        try {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(data);
            fileWriter.flush();
            fileWriter.close();
            status = true;
        } catch (IOException e) {
            status = false;
        }
        return status;
    }


    public static String readFromFile(String fileName) throws IOException
    {
        Objects.nonNull(fileName);
        String data = null;
        try {
            data = new String(readAllBytes(get(fileName)));
        } catch (IOException e) {
            LogTool.error(LogTool.getLogPoint(), e);
        }
        return data;
    }


    public static Boolean fileExists(String path)
    {
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }


    public static Boolean directoryExists(String path)
    {
        File f = new File(path);
        if(f.exists() && f.isDirectory()) {
            return true;
        }
        return false;
    }


    public static Boolean removeFile(String path)
    {
        File file = new File(path);
        if(file.delete()) {
            return true;
        }
        return false;
    }


    public static ArrayList<String> tailFile(String filename, int nlines) throws IOException
    {
        return tailFileProcessor(filename, nlines, false);
    }


    public static ArrayList<String> tailFileReverse(String filename, int nlines) throws IOException
    {
        return tailFileProcessor(filename, nlines, true);
    }


    private static ArrayList<String> tailFileProcessor(String filename, int nlines, Boolean reverse) throws IOException
    {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "r");
        long lof = randomAccessFile.length();
        long linesFromEnd = 1L;
        int counter = 0;
        long position = 0L;

        randomAccessFile.seek(lof - 1L);
        char current = (char) randomAccessFile.readByte();
        if(current == '\n') {
            nlines++;
        }

        while(linesFromEnd <= lof) {
            randomAccessFile.seek(lof - linesFromEnd);
            if(randomAccessFile.readByte() == '\n') {
                counter++;
            }
            if(counter == nlines) {
                position = randomAccessFile.getFilePointer();
                break;
            }
            linesFromEnd++;
        }
        randomAccessFile.seek(position);

        String line;
        ArrayList<String> lines = new ArrayList<String>();
        int nLine = 0;
        while((line = randomAccessFile.readLine()) != null) {
            lines.add(line);
        }
        if(reverse) {
            Collections.reverse(lines);
        }
        return lines;
    }


    public static ArrayList<String> listDirectories(String path)
    {
        ArrayList<String> dirs = new ArrayList<String>();
        File[] directories = new File(path).listFiles(File::isDirectory);
        for (File dir : directories) {
            dirs.add(dir.toString());
        }
        return dirs;
    }


    public static ArrayList<String> listFile(String path)
    {
        ArrayList<String> dirs = new ArrayList<String>();
        File[] directories = new File(path).listFiles(File::isFile);
        for (File dir : directories) {
            dirs.add(dir.toString());
        }
        return dirs;
    }

}

