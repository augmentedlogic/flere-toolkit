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
import java.io.*;
import java.time.*;
import java.text.SimpleDateFormat;

/**
 * various convenience methods to get list of days
 **/
public class DateTool {

    /**
     * get a list all days between two dates in the format yyyy-MM-dd
     **/
    public static ArrayList<String> getDaysBetween(String s , String e)
    {
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        ArrayList<String> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start.toString());
            start = start.plusDays(1);
        }

        return totalDates;
    }

    /**
     *  get today in the format yyyy-MM-dd
     **/
    public static String getToday()
    {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = format.format(today);
        return dateString;
    }

    /**
     *   get the day one calendar month ago in the format yyyy-MM-dd
     **/
    public static String getMonthBack(int dist)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, dist);
        Date result = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateString = format.format(result);
        return dateString;
    }

    /**
     * get all days of the last N days starting yesterday in format yyyy-MM-dd
     **/
    public static ArrayList<String> getDaysBefore(int days)
    {
        ArrayList<String> days_list = new ArrayList<String>();
        LocalDate today = LocalDate.now();
        for (int i = 1; i <= days; i++) {
            days_list.add(today.plusDays(i * -1 ).toString());
        }
        return days_list;
    }


}

