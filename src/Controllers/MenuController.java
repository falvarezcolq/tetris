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
import Controllers.LoginController; 
import Models.Almacen;
import Models.AuthUser;
import Models.LoginSubsidio;
import Models.Sucursal;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author admin@emapa.lan
 */
public class MenuController implements Initializable {

    public Button btnMenuVentas;
    public Button btnMenuBenef;
    public Button btnMenuReportes;
    public AuthUser authUser;
    public Almacen almacen;
    public Sucursal sucursal;
    public LoginSubsidio loginSubsidio;
    
    public Label lblSucursalNombre;
    public Label lblCajaNombre;
    public Label lblUsuarioNombre;
    public Label lblUsuarioSedemNombre;
    public Label lblNombre;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        authUser = LoginController.authUser;
        almacen = LoginController.almacen;
        sucursal = LoginController.sucursal;
        loginSubsidio = LoginController.loginSubsidio;
        showLabels();
        addListeners();
    }  
    
    private void addListeners(){
    
        btnMenuVentas.setOnAction(event -> onVentas());        
        btnMenuBenef.setOnAction(event -> onBeneficiarios());
        btnMenuReportes.setOnAction(event -> onReportes());
    }
    
    private void onVentas(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("VENTAS");
    }
    
    private void onBeneficiarios(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("BENEFICIARIOS");
    }
    
    private void onReportes(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set("REPORTES");
    }
    
    private void showLabels(){
        this.lblSucursalNombre.setText( "SUCURSAL: "+this.sucursal.nombre);
        this.lblCajaNombre.setText("CAJA: "+this.almacen.nombre);
        this.lblUsuarioNombre.setText( "USUARIO: "+this.authUser.usr_usuario);
        this.lblUsuarioSedemNombre.setText("USUARIO SEDEM: "+this.loginSubsidio.UsuarioNombre);
        
        this.lblNombre.setText("Nombre: "+this.authUser.name);
//        
    }
}
