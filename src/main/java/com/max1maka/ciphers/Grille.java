package com.max1maka.ciphers;

//метод поворачивающейся решетки
public class Grille {
    private String key;
    private String text;

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public Grille(String key, String text) {
        this.key = key;
        this.text = text;
    }

    //шифрование
    public String cryptText() {
        String text = getText();
        String key = getKey();

        String[] wordsTemp = key.split(" ");
        int[] keys = new int[wordsTemp.length];
        for (int i = 0; i < wordsTemp.length; i++) {
            keys[i] = Integer.parseInt(wordsTemp[i]);
        }

        int m = (int)Math.ceil(Math.sqrt(text.length()));
        int n = m;
        char[][] matrix = new char[n][m];
        int letter = 0;

        int c = 0;
        while (c < 4) {
            int k = -1;
            int curKey = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    k++;
                    if (curKey < keys.length && k == keys[curKey] && letter < text.length()) {
                        matrix[i][j] = text.charAt(letter);
                        curKey++;
                        letter++;
                    }
                }
            }
            matrix = turnToRight(matrix);
            c++;
        }

        String finalString = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][j] = '$';
                }
                if (matrix[i][j] != 0) {
                    finalString += matrix[i][j];
                }
            }
        }

       return finalString;
    }


    public char[][] turnToLeft(char[][] array) {
        char[][] resultArray = new char[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                resultArray[array[i].length - j - 1][i] = array[i][j];
            }
        }
        return resultArray;
    }

    public char[][] turnToRight(char[][] array) {
        char[][] resultArray = new char[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                resultArray[j][array.length - i - 1] = array[i][j];
            }
        }
        return resultArray;

    }

    //дешифрование
    public String deCryptText() {
        String text = getText();
        String key = getKey();

        String[] wordsTemp = key.split(" ");
        int[] keys = new int[wordsTemp.length];
        for (int i = 0; i < wordsTemp.length; i++) {
            keys[i] = Integer.parseInt(wordsTemp[i]);
        }

        int m = (int)Math.ceil(Math.sqrt(text.length()));
        int n = m;
        char[][] matrix = new char[n][m];
        int ind = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (ind != text.length() - 1) {
                    matrix[i][j] = text.charAt(ind);
                    ind++;
                }
            }
        }

        matrix = turnToLeft(matrix);
        matrix = turnToLeft(matrix);

        String finalString = "";
        int c = 0;
        while (c < 4) {
            int k = -1;
            int curKey = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    k++;
                    if (curKey < keys.length && k == keys[curKey]) {
                        finalString += matrix[i][j];
                        curKey++;
                    }
                }
            }
            matrix = turnToRight(matrix);
            c++;
        }

        return finalString.replaceAll("\\$", "");
    }
}
