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

import java.util.Scanner;
import java.io.*;

public class ConsoleTool
{

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String LIGHT_RED = "\u001B[91m";
    public static final String LIGHT_GREEN = "\u001B[92m";
    public static final String LIGHT_YELLOW = "\u001B[93m";
    public static final String LIGHT_BLUE = "\u001B[94m";
    public static final String LIGHT_PURPLE = "\u001B[95m";
    public static final String LIGHT_MAGENTA = "\u001B[95m";
    public static final String LIGHT_CYAN = "\u001B[96m";


    public static void println(String msg, String color)
    {
        System.out.println(color + msg + ConsoleTool.RESET);
    }


    public static void print(String msg, String color)
    {
        System.out.print(color + msg + ConsoleTool.RESET);
    }


    private static String runYesNo(String question, Boolean default_answer)
    {

        Scanner in = new Scanner(System.in);
        if(default_answer) {
            System.out.print(question + " [Y/n]");
        } else {
            System.out.print(question + " [y/N]");
        }
        String ans = in.nextLine();
        return ans;
    }

    /**
     *  prompt for a yesy/no decision
     **/
    public static Boolean yesnoDialog(String question, Boolean default_answer)
    {
        Boolean answer = default_answer;
        Boolean valid = false;
        while(!valid) {
            String ans = runYesNo(question, default_answer);
            if (ans.toUpperCase().equals("Y")) {
                answer = true;
                valid = true;
            } else if (ans.toUpperCase().equals("N")) {
                answer = false;
                valid = true;
            }
        }
        return answer;
    }


    /**
     * prompt for text input
     **/
    public static String doTextPrompt(String message) throws Exception
    {
        String name = null;
        Console console = System.console();
        console.printf(message);
        try {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
            name = reader.readLine();
        } catch(Exception e) {
            throw e;
        }
        return name;
    }

    /**
     *  prompt for a password
     **/
    public static String doPasswordPrompt(String message)
    {
        Console console = System.console();
        if (console == null) {
            return null;
        }

        console.printf("Testing password%n");
        char[] passwordArray = console.readPassword(message);
        return new String(passwordArray);
    }


    public static String getCurrentUser()
    {
        return System.getProperty("user.name");
    }



    public static class Getopt
    {

        private String[] args = null;


        public static int getInt(String[] args, String key, int default_value)
        {
            int c = 0;
            for(String arg: args) {
                if(arg.equals(key)) {
                    return Integer.parseInt(args[c+1]);
                }
                c++;
            }

            return  default_value;
        }


        public static String getString(String[] args, String key, String default_param)
        {
            int c = 0;
            for(String arg: args) {
                if(arg.equals(key)) {
                    return args[c+1];
                }
                c++;
            }

            return default_param;
        }



        public static Boolean hasParam(String[] args, String key)
        {

            for(String arg: args) {
                if(arg.equals(key)) {
                    return true;
                }
            }

            return false;
        }


    }

}

