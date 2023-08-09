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

import java.util.*;
import java.math.*;

public class TextTool {


    public static String normalize(String s)
    {
        s = s.replace("\n", "").replace("\r", "");
        s = s.trim().replaceAll(" +", " ");
        s = s.replace("\t", "");
        return s;
    }

    // encodeBase64
    public static String toBase64(String s)
    {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }


    // decodeBase64
    public static String fromBase64(String s)
    {
        byte[] bytes = Base64.getDecoder().decode(s.getBytes());
        return new String(bytes);
    }

    // toHex
    public static String toHex(String s)
    {
        String hexs = null;
        try {
            hexs = String.format("%x", new BigInteger(1, s.getBytes("UTF-8")));
        } catch(Exception e) {
            LogTool.error(LogTool.getLogPoint(), e);
        }
        return hexs;
    }


}
