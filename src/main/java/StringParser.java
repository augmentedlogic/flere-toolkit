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
import java.lang.*;

public class StringParser
{


    private static Integer safeParseInteger(String s)
    {
        Integer return_value = null;
        if(s != null) {
            s = s.trim();
            if(s.matches("^\\d+$")) {
                return_value = Integer.parseInt(s);
            }

        }
        return return_value;
    }

    private static Double safeParseDouble(String s)
    {
        Double return_value = null;
        if(s != null) {
            s = s.trim();
            if(s.matches("^\\d+\\.\\d+$") || s.matches("\\d+")) {
                return_value = Double.parseDouble(s);
            }
        }
        return return_value;
    }


    public static Integer parseIntegerWithDefault(String s, Integer default_value)
    {
        Integer value = StringParser.safeParseInteger(s);
        if(value == null) {
            return default_value;
        }
        return value;
    }


    public static Double parseDoubleWithDefault(String s, Double default_value)
    {
        Double value = StringParser.safeParseDouble(s);
        if(value == null) {
            return default_value;
        }
        return value;
    }

}
