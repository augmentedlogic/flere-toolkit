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
import java.util.*;


public class TaskTool
{


    public static String runc(String cmd) {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
        Process p;
        String result = null;
        try {
            p = pb.start();
            //long pid = pb.pid();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
            reader.lines().iterator().forEachRemaining(sj::add);
            result = sj.toString();
            int status = p.waitFor();
            //System.out.println(status);
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public class ReadStream implements Runnable {

        String name;
        InputStream is;
        Thread thread;

        public ReadStream(String name, InputStream is) {
            this.name = name;
            this.is = is;
        }

        public void start() {
            thread = new Thread (this);
            thread.start ();
        }

        public void run () {
            try {
                InputStreamReader isr = new InputStreamReader (is);
                BufferedReader br = new BufferedReader (isr);
                while (true) {
                    String s = br.readLine();
                    if (s == null) break;
                    //System.out.println ("[" + name + "] " + s);
                }
                //is.close ();
            } catch (Exception ex) {
                System.out.println ("Problem reading stream " + name + "... :" + ex);
                ex.printStackTrace ();
            }
        }
    }



}
