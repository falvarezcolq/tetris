/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author admin@emapa.lan
 */
public class AdminController implements Initializable {
        
   public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal){
                case "BENEFICIARIOS": admin_parent.setCenter(Model.getInstance().getViewFactory().getBeneficiariosView());  break;             
                case "REPORTES" : admin_parent.setCenter(Model.getInstance().getViewFactory().getReportesView());     break;          
                default :admin_parent.setCenter(Model.getInstance().getViewFactory().getVentasSedemView());break;
            }
        }); 
    }
    
}
