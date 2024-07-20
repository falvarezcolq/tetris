/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Model;
import ServicioSeguridad.ResponseEntityOfDTOUsuariosut8TV34;
import ServicioSeguridad.State;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Utils.ApiEmapa;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

import Models.*;
import org.json.JSONArray;
/**
 * FXML Controller class
 *
 * @author admin@emapa.lan
 */
public class LoginController 
        implements Initializable
{

    
    public Button btnIngresar;
    public Label lblError;
    public TextField txtUsuario;
    public TextField txtPassword;

    public static AuthUser authUser; 
    public static Almacen almacen;
    public static Sucursal sucursal;
    public static LoginSubsidio loginSubsidio;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblError.setText("");
        txtUsuario.setText("AAA-1");
        txtPassword.setText("123456");
        btnIngresar.setOnAction( event -> onLogin());
    }
    
    private void onLogin(){ 
        
        if(valido()){
            if(userLogin(txtUsuario.getText(),txtPassword.getText())){
               Stage stage = (Stage) lblError.getScene().getWindow();
               Model.getInstance().getViewFactory().closeStage(stage);
               Model.getInstance().getViewFactory().showClientWindow();
           }
        }
    }
    
    
    private boolean valido(){
        
        if( txtUsuario.getText()!="" &&  txtPassword.getText() != ""){
            return true;
        }else{
            lblError.setText("No se permite campos vacios");
            return false;
        }
        
    }
    
    private boolean userLogin(String user, String pass){
           ApiEmapa api = new ApiEmapa();
           JSONObject json=null;
           try{
                HttpResponse response = api.login(user,pass);
                System.out.println("response: "+response);
                
                
                    
                if( response.getStatusLine().getStatusCode() == 200 ){
                    json = api.parseJsonObject(response);
                    System.out.println(json.getString("status"));                   
                    System.out.println("TEXT");

                    if( json.getString("status").equals("success")){
                        this.loadData(json);
                        
                        return true;
                    } else {
                        
                        lblError.setText("Error");
                    }
                         
                } else if(response.getStatusLine().getStatusCode()==401){
                    json = api.parseJsonObject(response);
                    
                    if(json.getString("status").equals("restringido")){
                        
                        lblError.setText(json.getString("message"));
                    } else {
                        lblError.setText("El usuario y contraseña son incorrectos");
                    }
                   
                
                }else{
                 lblError.setText("Error");
                }
              
               
                return false;
          } catch(IOException ex  ){
               System.out.print(ex);
               return false;
           }    
    }
    
    private void loadData(JSONObject json){
    
        authUser = new AuthUser();
        authUser.token = json.getString("token");
        JSONObject user = json.getJSONObject("user");
        authUser.email = user.getString("email");
        authUser.name = user.getString("name");
        authUser.user_id = user.getInt("id");
        authUser.usr_usuario = user.getString("usr_usuario");
        
        
        boolean hasPermissionSubsidio=false;
        
        
        if(json.has("permissions")){
           JSONArray permissions = json.getJSONArray("permissions");
         
           JSONObject permission ;
           for( int i = 0 ; i<permissions.length();i++){
              permission = permissions.getJSONObject(i);
              if(permission.has("name") && permission.getString("name").equals("SIESUBSIDIO")){
                  hasPermissionSubsidio = true;
                  break;
              }
           }
        }
        
        if(json.has("almacen")){
           JSONArray almacenes = json.getJSONArray("almacen");
         
           JSONObject almacenJson;
           for( int i = 0 ; i<almacenes.length();i++){
              almacenJson = almacenes.getJSONObject(i);
              if(almacenJson.has("loginsubsidio")){
                  JSONArray loginsubArray =  almacenJson.getJSONArray("loginsubsidio");
                  
                  if(loginsubArray.length()== 1){
                      JSONObject loginsubJson = loginsubArray.getJSONObject(0);
                      this.loadAuthUser(json);
                      this.loadAlmacen(almacenJson);
                      this.loadSucursal(almacenJson.getJSONObject("sucursal"));
                      this.loadLoginSubsidio(loginsubJson);
                      break;
                  }
                  
              }
           }
        }
        
        
    }

    
    private void loadAuthUser(JSONObject json){
    
        authUser = new AuthUser();
        authUser.token = json.getString("token");
        JSONObject user = json.getJSONObject("user");
        authUser.email = user.getString("email");
        authUser.name = user.getString("name");
        authUser.user_id = user.getInt("id");
        authUser.usr_usuario = user.getString("usr_usuario");
    }
    
    
    private void loadAlmacen(JSONObject json){
        almacen = new Almacen();
        almacen.id = json.getInt("id");
        almacen.nombre = json.getString("nombre");
        almacen.direccion = json.getString("direccion");
        almacen.telefono = json.getString("telefono");
        almacen.tipo = json.getString("tipo");
    }
    
    private void loadSucursal(JSONObject  json){
        sucursal = new Sucursal();
        sucursal.id = json.getInt("id");
        sucursal.id_sucursal= json.getInt("id_sucursal");
        sucursal.codigo = !json.isNull("codigo")  ? json.getInt("codigo"):0;
        sucursal.departamento_id = json.getInt("departamento_id");
        sucursal.provincia_id = json.getInt("provincia_id");
        sucursal.municipio_id = json.getInt("localidad_id");
        sucursal.localidad_id = json.getInt("localidad_id");
        sucursal.nombre = json.getString("nombre");
        sucursal.regional_precio_id = json.getInt("regional_precio_id"); 
    }
    
    private void loadLoginSubsidio(JSONObject json){
    
        loginSubsidio = new LoginSubsidio();
        loginSubsidio.id = json.getInt("id");
        loginSubsidio.IDGEN_INSTITUCION = json.getInt("IDGEN_INSTITUCION");
        loginSubsidio.IDGEN_INSTITUCIONSUCURSAL =  json.getInt("IDGEN_INSTITUCIONSUCURSAL");
        loginSubsidio.IDSEG_PERFIL = json.getInt("IDSEG_PERFIL");
        loginSubsidio.Institucion = json.getString("Institucion");
        loginSubsidio.NombreCompleto = json.getString("NombreCompleto");
        loginSubsidio.Sucursal = json.getString("Sucursal");
        loginSubsidio.Token = json.getString("Token");
        loginSubsidio.UsuarioNombre = json.getString("UsuarioNombre");
        
    }
    private static ResponseEntityOfDTOUsuariosut8TV34 autentificacionRegistradores(java.lang.String user, java.lang.String password) {
        ServicioSeguridad.ServicioSeguridad_Service service = new ServicioSeguridad.ServicioSeguridad_Service();
        ServicioSeguridad.ServicioSeguridad port = service.getBasicHttpBindingServicioSeguridad();
        return port.autentificacionRegistradores(user, password);
    }
    

    
}
