package com.max1maka.ciphers;

import java.util.ArrayList;

//шифр цезаря
public class Cezar {
    private final String text;
    private final int key;

    public Cezar(String text, int key) {
        this.text = text;
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public int getKey() {
        return key;
    }

    //шифрование
    public String cryptText() {
        String str = getText().toUpperCase();
        int key = getKey();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(33); list.add(64);
        list.add(59); list.add(58);
        list.add(46); list.add(34);
        list.add(44); list.add(32);
        list.add(63); list.add(40);
        list.add(48); list.add(57);
        list.add(49); list.add(56);
        list.add(50); list.add(55);
        list.add(51); list.add(54);
        list.add(52); list.add(53);

        // 0 - eng, 1 - rus  1040 65
        int lang;
        int startPos;
        int shift;

        String finalString = "";
        for (int i = 0; i < str.length(); i++) {
            lang = (str.charAt(i) < 1040) ? 0 : 1;
            startPos = (lang == 0) ? 65 : 1040;
            shift = (lang == 0) ? 26 : 33;
            char newChar = str.charAt(i);
            if (!list.contains(((int)str.charAt(i)))){
                newChar = (str.charAt(i) + key > startPos + shift - 1) ? (char) (str.charAt(i) + key - shift) : (char) (str.charAt(i) + key);
            }
            finalString += newChar;
        }

        return finalString;
    }

    //дешифрование
    public String deCryptText() {
        String str = getText().toUpperCase();
        int key = getKey();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(33); list.add(64);
        list.add(59); list.add(58);
        list.add(46); list.add(34);
        list.add(44); list.add(32);
        list.add(63); list.add(40);
        list.add(48); list.add(57);
        list.add(49); list.add(56);
        list.add(50); list.add(55);
        list.add(51); list.add(54);
        list.add(52); list.add(53);

        // 0 - eng, 1 - rus  1040 65
        int lang;
        int startPos;
        int shift;


        String finalString = "";
        for (int i = 0; i < str.length(); i++) {
            lang = (str.charAt(i) < 1040) ? 0 : 1;
            startPos = (lang == 0) ? 65 : 1040;
            shift = (lang == 0) ? 26 : 33;
            char newChar = str.charAt(i);
            if (!list.contains(((int)str.charAt(i)))){
                newChar = (str.charAt(i) - key < startPos) ? (char) (str.charAt(i) - key + shift) : (char) (str.charAt(i) - key);
            }
            finalString += newChar;
        }

        return finalString;
    }
}
