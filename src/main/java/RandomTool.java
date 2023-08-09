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
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.nio.charset.Charset;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * various convenience methods to generate random strings
 *
 **/
public class RandomTool
{

    private static char[] VALID_AN_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
    private static char[] VALID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879!£$%^&*()_+-={}[]:@~;'#<>?,./|".toCharArray();
    public static final int ANUM = 0;
    public static final int HEX = 1;
    public static final int BASE64 = 2;
    public static final int RAW = 3;
    public static final int CHARS = 4;

    /**
     *
     **/
    public static String getUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     *
     **/
    private static String getRandom(int length)
    {
        Random random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        String s = new String(salt, Charset.forName("UTF-8"));
        return s;
    }


    /**
     *
     **/
    private static String generate(int numChars, char[] set) {
        SecureRandom srand = new SecureRandom();
        Random rand = new Random();
        char[] buff = new char[numChars];

        for (int i = 0; i < numChars; ++i) {
            // reseeding rand
            if ((i % 10) == 0) {
                rand.setSeed(srand.nextLong());
            }
            buff[i] = set[rand.nextInt(set.length)];
        }

        return new String(buff);
    }

    /**
     * for backwards compatibility
     **/
    @Deprecated
        public static String getRandomAlphaNumericString(int numChars) {
            return generate(numChars, RandomTool.VALID_AN_CHARACTERS);
        }

    /**
     * generate a random String of a certain length
     *
     **/
    public static String getRandomString(int length, int string_type)
    {
        if(string_type == RandomTool.ANUM) {
            return RandomTool.generate(length, RandomTool.VALID_AN_CHARACTERS);
        } else if(string_type == RandomTool.CHARS) {
            return RandomTool.generate(length, RandomTool.VALID_CHARACTERS);
        } else if (string_type == RandomTool.HEX) {
            return TextTool.toHex(RandomTool.getRandom(length));
        } else if (string_type == RandomTool.BASE64) {
            return TextTool.toBase64(RandomTool.getRandom(length));
        } else if (string_type == RandomTool.RAW) {
            return RandomTool.getRandom(length);
        } else {
            return null;
        }
    }


}
