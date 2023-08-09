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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class ConfigTool {

    /**
     * load settings from xml file
     *
     * @param filename path to the xml file
     */
    public void load(String filename)
    {
        try {
            File file = new File(filename);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Properties systemProperties = System.getProperties();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                systemProperties.setProperty(key, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProp(String key)
    {
        Properties systemProperties = System.getProperties();
        return systemProperties.getProperty(key);
    }

    /**
     * set a string value
     *
     * @param key name of the key
     * @param value assigned to that key
     */
    public static void setString(String key, String value)
    {
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty(key, value);
    }

    /**
     * set int value
     *
     * @param key name of the key
     * @param value assigned to that key
     */
    public static void setInt(String key, int value)
    {
        Properties systemProperties = System.getProperties();
        systemProperties.setProperty(key, String.valueOf(value));
    }


    /**
     * retrieve a string value
     *
     * @param key name of the key
     */
    public static String getString(String key)
    {
        Properties systemProperties = System.getProperties();
        return systemProperties.getProperty(key);
    }


    /**
     * retrieve int value
     *
     * @param key name of the key
     */
    public static Integer getInt(String key)
    {
        Properties systemProperties = System.getProperties();
        return Integer.parseInt(systemProperties.getProperty(key));
    }

    public static Double getDouble(String key)
    {
        Properties systemProperties = System.getProperties();
        return Double.parseDouble(systemProperties.getProperty(key));
    }


    /**
     * retrieve a string value with default
     *
     * @param key name of the key
     * @param default_value default value if value is null
     *
     */
    public static String getStringWithDefault(String key, String default_value)
    {
        String value = ConfigTool.getString(key);
        if(value == null) {
            value = default_value;
        }
        return value;
    }


    /**
     * retrieve int value with default
     *
     * @param key name of the key
     * @param default_value default value if value is null
     *
     */
    public static Integer getIntegerWithDefault(String key, Integer default_value)
    {
        return StringParser.parseIntegerWithDefault(ConfigTool.getProp(key), default_value);
    }

    /**
     * for compatibility
     **/
    public static Integer getIntWithDefault(String key, Integer default_value)
    {
        return ConfigTool.getIntegerWithDefault(key, default_value);
    }


    /**
     * @param key name of the key
     * @param default_value default value if value is null
     **/
    public static Double getDoubleWithDefault(String key, Double default_value)
    {
        return StringParser.parseDoubleWithDefault(key, default_value);
    }


}

