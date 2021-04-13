package com.max1maka.ciphers;

//метож ЖД изгородь
public class IronFence {
    final private int key;
    final private String text;

    public IronFence(String text, int key) {
        this.key = key;
        this.text = text;
    }

    public int getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    //шифрование текста
    public String cryptText() {
        String text = getText();
        int n = getKey();
        int m = getText().length();
        char[][] matrix = new char[n][m];

        int i = 0; int k = 0;
        boolean isDown = false;

        for (int j = 0; j < m; j++) {
            matrix[i][j] = text.charAt(k);
            if (i == n - 1 || i == 0){
                isDown = !isDown;
            }

            if (isDown){
                i++;
            } else {
                i--;
            }
            k++;
        }

        String finalText = "";

        for (int i1 = 0; i1 < n; i1++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i1][j] != '\u0000'){
                    finalText += matrix[i1][j];
                }
            }
        }

        return finalText;
    }

    //дешифрование текста
    public String deCryptText() {
        String text = getText();
        int n = getKey();
        int m = getText().length();
        char[] finalText = new char[m];
        int i = 0;
        int j = 0;
        int indF, indT = 0;

        int c1 = 2 + 2 * (n - 2), c2 = 0;
        while (i < n){
            indF = j;
            boolean isC1Active = false;
            while (indF < m){
                finalText[indF] = text.charAt(indT);
                isC1Active = !isC1Active;
                if (isC1Active){
                    if (c1 != 0){
                        indF += c1;
                    } else {
                        indF += c2;
                    }
                } else {
                    if (c2 != 0){
                        indF += c2;
                    } else {
                        indF += c1;
                    }
                }
                indT++;
            }
            c1 -= 2;
            c2 += 2;
            i++;
            j++;
        }

        return String.valueOf(finalText);
    }
}
