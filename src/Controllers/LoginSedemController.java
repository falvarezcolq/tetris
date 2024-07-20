/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin@emapa.lan
 */
public class LoginSedemController 
        implements Initializable
{

    
  
    public Button btnLoginSedem;
    public Label lblErrorS;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnLoginSedem.setOnAction( event -> onLoginSedem());
    }
    
    private void onLoginSedem(){
        Stage stage = (Stage) lblErrorS.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
    
}
