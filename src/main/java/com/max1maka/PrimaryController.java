package com.max1maka;

import java.net.URL;
import java.util.ResourceBundle;

import com.max1maka.ciphers.Cezar;
import com.max1maka.ciphers.Column;
import com.max1maka.ciphers.Grille;
import com.max1maka.ciphers.IronFence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtInputText;

    @FXML
    private TextArea txtOutputText;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnDeCrypt;

    @FXML
    private CheckBox cbIronFence;

    @FXML
    private CheckBox cbColumn;

    @FXML
    private TextField txtIronFenceKey;

    @FXML
    private TextField txtColumnKey;

    @FXML
    private CheckBox cbCezar;

    @FXML
    private TextField txtCezarKey;

    @FXML
    private CheckBox cbRotate;

    @FXML
    private TextField txtRotateKey;

    private String inputText;
    private int ironFenceKey;
    private String columnKey;
    private int cezarKey;
    private String grilleKey;

    @FXML
    void initialize() {

        btnEncrypt.setOnAction(e -> {
            inputText = txtInputText.getText();

            if (cbIronFence.isSelected()){
                ironFenceKey = Integer.parseInt(txtIronFenceKey.getText());
                IronFence ironFence = new IronFence(inputText, ironFenceKey);
                inputText = ironFence.cryptText();
            }
            if (cbColumn.isSelected()){
                columnKey = txtColumnKey.getText();
                Column column = new Column(inputText, columnKey);
                inputText = column.cryptText();
            }
            if (cbRotate.isSelected()){
                grilleKey = txtRotateKey.getText();
                Grille grille = new Grille(grilleKey, inputText);
                inputText = grille.cryptText();
            }
            if (cbCezar.isSelected()){
                cezarKey = Integer.parseInt(txtCezarKey.getText());
                Cezar cezar = new Cezar(inputText, cezarKey);
                inputText = cezar.cryptText();
            }

            txtOutputText.setText(inputText);
        });

        btnDeCrypt.setOnAction(e -> {
            inputText = txtInputText.getText();

            if (cbCezar.isSelected()){
                cezarKey = Integer.parseInt(txtCezarKey.getText());
                Cezar cezar = new Cezar(inputText, cezarKey);
                inputText = cezar.deCryptText();
            }
            if (cbRotate.isSelected()){
                grilleKey = txtRotateKey.getText();
                Grille grille = new Grille(grilleKey, inputText);
                inputText = grille.deCryptText();
            }
            if (cbColumn.isSelected()){
                columnKey = txtColumnKey.getText();
                Column column = new Column(inputText, columnKey);
                inputText = column.deCryptText();
            }
            if (cbIronFence.isSelected()){
                ironFenceKey = Integer.parseInt(txtIronFenceKey.getText());
                IronFence ironFence = new IronFence(inputText, ironFenceKey);
                inputText = ironFence.deCryptText();
            }

            txtOutputText.setText(inputText);
        });

    }
}
