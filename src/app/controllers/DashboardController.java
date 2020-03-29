/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controllers;

import app.config.Config;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rashi
 */
public class DashboardController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button btn_close;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_close_notification;
    @FXML
    private PasswordField pf_password;
    @FXML
    private VBox notification;
    
        Crud crud = new Crud();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(MouseEvent event) {
        crud.update(tf_name.getText(), tf_email.getText(), pf_password.getText());
    }

    @FXML
    private void delete(MouseEvent event) {
        
        crud.delete(Config.currentUser);
        try {
            Parent dashboard = FXMLLoader.load(getClass().getResource("/app/ui/app.fxml"));
            Scene dashScene = new Scene(dashboard);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(dashScene);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close(MouseEvent event) {
          Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeNotification(MouseEvent event) {   
        notification.setVisible(false);

    }
    
}
