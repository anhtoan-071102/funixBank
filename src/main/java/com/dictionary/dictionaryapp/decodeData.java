/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dictionary.dictionaryapp;

/**
 *
 * @author ADMIN
 */
public class decodeData {

    public static int getDecimalValue(String s) {
        String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        int decValue = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int pos = base64.indexOf(s.charAt(i));
            decValue += (int) Math.pow(64, len - i - 1) * pos;
        }
        return decValue;
    }
}
