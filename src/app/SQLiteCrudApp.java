/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.sqlite.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rashi
 */
public class SQLiteCrudApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Connect.connect();
        Parent root = FXMLLoader.load(getClass().getResource("/app/ui/app.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
