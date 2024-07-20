/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import le.Capture;
import le.MessageBox;
import le.Selection;

/**
 * FXML Controller class
 *
 * @author admin@emapa.lan
 */
public class MainController implements Initializable {

    public Button btnBuscarBenef;
    public TextField txtCI;
    public Button btnLectorHuella;
    public Label lblLector;
    
    
     private ReaderCollection m_collection;
    private Reader m_reader;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscarBenef.setOnAction(event -> onSearchBenef());
        btnLectorHuella.setOnAction(event->onSelect());
        
        try{
                m_collection = UareUGlobal.GetReaderCollection();
        }
        catch(UareUException e) {
                MessageBox.DpError("UareUGlobal.getReaderCollection()", e);
        }
        
    }    
    
    public void onSearchBenef(){
         // TODO add your handling code here:
        if(null==m_reader){
            MessageBox.Warning("Lector no seleccionado");
        }else{
            Capture.Run(m_reader,false);
        }
        
    }
    
    
    public void onSelect(){
    
         m_reader = Selection.Select(m_collection);     
         if(null!=m_reader){
             lblLector.setText(m_reader.GetDescription().id.product_name);
         }else{
             lblLector.setText("Seleccione lector");
         }
    }
    
}
