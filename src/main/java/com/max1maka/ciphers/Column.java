package com.max1maka.ciphers;

//столбцовый метод
public class Column {
    private final String text;
    private final String key;

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    public Column(String text, String key) {
        this.text = text;
        this.key = key;
    }

    //шифрование
    public String cryptText() {
        String key = getKey().toUpperCase();
        String text = getText().toUpperCase();

        int n = (text.length() % key.length() > 0) ? (text.length() / key.length() + 2) : text.length() / key.length() + 1;
        int m = key.length();
        char[][] matrix = new char[n][m];

        for (int j = 0; j < m; j++) {
            matrix[0][j] = key.charAt(j);
        }

        int k = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (k < text.length()) {
                    matrix[i][j] = text.charAt(k);
                    k++;
                }
            }
        }

        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < m; i++) {
                if (matrix[0][i] < matrix[0][i - 1]) {
                    for (int j = 0; j < n; j++) {
                        char tmp = matrix[j][i];
                        matrix[j][i] = matrix[j][i - 1];
                        matrix[j][i - 1] = tmp;
                    }
                    needIteration = true;
                }
            }
        }

        String finalText = "";

        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                finalText += matrix[i][j];
            }
        }
        return finalText;
    }

    //дешифрование
    public String deCryptText(){
        String key = getKey();
        String text = getText().toUpperCase();

        int symLeft = text.length() % key.length();

        int n = (text.length() % key.length() > 0) ? (text.length() / key.length() + 1) : text.length() / key.length();
        int m = key.length();

        char[][] matrix = new char[n][m];

        int lowest;
        int k = 0;

        int[] arr = new int[m];
        char[] temp = new char[m];
        for (int i = 0; i < m; i++) {
            arr[i] = i;
            temp[i] = key.charAt(i);
        }

        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < temp.length; i++) {
                if (temp[i] < temp[i - 1]) {
                    arr = swap(arr, i, i - 1);
                    temp = swap2(temp, i, i - 1);
                    needIteration = true;
                }
            }
        }

        int ind = 0;

        for (int j = 0; j < m; j++) {
            lowest = arr[ind];
            for (int i = 0; i < n; i++) {
                if (k != text.length()) {
                    if (i == n - 1 && lowest >= symLeft) {
                        break;
                    } else {
                        matrix[i][lowest] = text.charAt(k);
                        k++;
                    }
                }
            }
            ind++;
        }

        String finalText = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '\u0000')
                    finalText += matrix[i][j];
            }
        }
        return finalText;
    }


    private static int[] swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
        return array;
    }

    private static char[] swap2(char[] array, int ind1, int ind2) {
        char tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
        return array;
    }
}
