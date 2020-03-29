/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.sqlite.Crud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rashi
 */
public class AppController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ImageView btn_close;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button btn_register;
    @FXML
    private Text txt_noitification;
    @FXML
    private ImageView btn_close_notification;
    @FXML
    private TextField tf_login_email;
    @FXML
    private PasswordField tf_login_password;
    @FXML
    private Button btn_login;
    @FXML
    private VBox notification;

    Crud crud = new Crud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crud.createTable();
        // TODO
    }

    @FXML
    private void closeApp(MouseEvent event) {

        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void hideNotification(MouseEvent event) {
        notification.setVisible(false);
    }

    @FXML
    private void register(MouseEvent event) {

        if (tf_name.getText().length() > 3
                || tf_email.getText().length() > 7
                || pf_password.getText().length() > 6) {
            crud.insert(tf_name.getText(), tf_email.getText(), pf_password.getText());

            try {

                Parent dashboard = FXMLLoader.load(getClass().getResource("/app/ui/dashboard.fxml"));
                Scene dashScene = new Scene(dashboard);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(dashScene);
            } catch (IOException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            notification.setVisible(true);
            txt_noitification.setText("Name Length: > 3 \n Email Length: > 7 \n Password length > 6");
        }

    }

    @FXML
    private void login(MouseEvent event) {
        if (tf_login_email.getText().length() > 7
                || tf_login_password.getText().length() > 6) {
            boolean status = crud.select(tf_login_email.getText(), tf_login_password.getText());
            try {
                if(status) {
                     Parent dashboard = FXMLLoader.load(getClass().getResource("/app/ui/dashboard.fxml"));
                Scene dashScene = new Scene(dashboard);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(dashScene);

                }else {
                    notification.setVisible(true);
                    txt_noitification.setText("Wrong username or password");
                }
               
            } catch (IOException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
