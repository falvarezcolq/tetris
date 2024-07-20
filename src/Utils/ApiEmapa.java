package Utils;
import static Utils.Rutas.URL_SERVICES;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.json.JSONObject;

import org.apache.http.NameValuePair;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import static java.util.stream.DoubleStream.builder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
//import static jdk.jshell.tool.JavaShellToolBuilder.builder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
//import principal.ListarRequerimientosFrm;
//import principal.LoginFrm;



public class ApiEmapa {
    public static JTable tabla;
    public static JTable tablaReq;
    public static JTable tablaTripulante;   
    public static JTable tablaRequisitos;  
//    Peticion_post postServicio=new Peticion_post();
//    Peticion_put putServicio=new Peticion_put();   
    public static String imagen_base;
    public static int tamano;
    public static String tipo_imagen_base;
    
    
    
    public HttpResponse login(String user,String pass) throws  IOException{
    
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL_SERVICES+"/api/login_aplicativo_movil");
//        httpGet.addHeader("token", LoginFrm.token);

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("usr_usuario", user));
        params.add(new BasicNameValuePair("password", pass));
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        
        
//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line ;  
//        int resultado=1;          
//        Object peticion = null;
//        
//
//        
//         while ((line = reader.readLine()) != null) {           
//            JSONObject jsonObjeto=new JSONObject(line);                          
//            peticion=jsonObjeto.get("status"); 
//            System.out.println(jsonObjeto);
//        } 
        
        return response;
    }
    
    
    public JSONObject parseJsonObject(HttpResponse response) throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line ;  
        JSONObject jsonObject=null;
        while ((line = reader.readLine()) != null) {           
            jsonObject=new JSONObject(line);                          
        }
        return jsonObject;
    }       
      
//    public int listarTramites() throws SQLException, IOException{   
//        
//        
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(URL_SERVICES+"/listarTramiteJava");
////        httpGet.addHeader("token", LoginFrm.token);
//        HttpResponse response = httpClient.execute(httpGet);
//        
//        
//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line;  
//        
//        
//        
//        int resultado=1;
//        
//         while ((line = reader.readLine()) != null) {           
//            JSONObject jsonObjeto=new JSONObject(line);            
//            JSONArray peticion = null;
//             try{                   
//                    peticion=jsonObjeto.getJSONArray("contenido");
//                } catch (JSONException e) {
//                    // El campo "contenido" no existe, maneja esta situación aquí
//                }          
//            if(peticion==null){
//                resultado=0;
//            }else if(peticion.length()>0){                
//                for (int i = 0; i < peticion.length(); i++) {
//                   JSONObject datos=peticion.getJSONObject(i);               
//                   String completo=datos.getString("nombres")+" "+datos.getString("primer_apellido")+" "+datos.getString("segundo_apellido");
//                   dfm.addRow(new Object[]{
//                       datos.getInt("id_proceso"),
//                       datos.getString("cod_inicio"),
//                       completo,
//                       datos.getString("nro_documento"),
//                       datos.getString("nacionalidad"),
//                       datos.getString("estado"),
//                       datos.getString("tramite")                        
//                   });                
//                } 
//                
//            }else{
//                JOptionPane.showMessageDialog(null, "No tiene registros", "Registro",JOptionPane.INFORMATION_MESSAGE);
//            }
//            
//                              
//        } 
//        return resultado;
//        
//        
//        
//        DefaultTableModel dfm=new DefaultTableModel();
//        tabla=CapturaFrm.jTableTramite;
//        tabla.setModel(dfm);           
//        dfm.setColumnIdentifiers(new Object[]{"N°","Cod. Inicio","Nombres","N° Documento","Nacionalidad","Estado","Trámite","Seleccionar"});     
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(5);//numero
//        tabla.getColumnModel().getColumn(1).setPreferredWidth(30);//cod Inicio
//        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);//nombre
//        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//documento
//        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);//nacionalidad
//        tabla.getColumnModel().getColumn(5).setPreferredWidth(30);//estado
//        tabla.getColumnModel().getColumn(6).setPreferredWidth(300); //tramite
//        tabla.getColumnModel().getColumn(7).setPreferredWidth(30); //seleccionar
//        addCheckbox(7, tabla);
//        while ((line = reader.readLine()) != null) {           
//            JSONObject jsonObjeto=new JSONObject(line);            
//            JSONArray peticion = null;
//             try{                   
//                    peticion=jsonObjeto.getJSONArray("contenido");
//                } catch (JSONException e) {
//                    // El campo "contenido" no existe, maneja esta situación aquí
//                }          
//            if(peticion==null){
//                resultado=0;
//            }else if(peticion.length()>0){                
//                for (int i = 0; i < peticion.length(); i++) {
//                   JSONObject datos=peticion.getJSONObject(i);               
//                   String completo=datos.getString("nombres")+" "+datos.getString("primer_apellido")+" "+datos.getString("segundo_apellido");
//                   dfm.addRow(new Object[]{
//                       datos.getInt("id_proceso"),
//                       datos.getString("cod_inicio"),
//                       completo,
//                       datos.getString("nro_documento"),
//                       datos.getString("nacionalidad"),
//                       datos.getString("estado"),
//                       datos.getString("tramite")                        
//                   });                
//                } 
//                
//            }else{
//                JOptionPane.showMessageDialog(null, "No tiene registros", "Registro",JOptionPane.INFORMATION_MESSAGE);
//            }
//            
//                              
//        } 
//        return resultado;
//    }
//        
// 
        
//    public boolean IsSelected(int row,int column,JTable table){
//        return table.getValueAt(row, column)!=null;
//    }
    
//    public void cambiarEstado(String estado){       
//      
//        try {
//            ArrayList<NameValuePair> body=new ArrayList<>();
//            body.add(new BasicNameValuePair("upload_imagen", estado));
//            String id_proceso=String.valueOf(seleccionarCod);
//            body.add(new BasicNameValuePair("id_proceso", id_proceso));
//            String result = putServicio.PUT("/actualizarEstadoProceso", body);
//            System.out.println("ms:"+ result);
//        } catch (IOException ex) {
//            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//               
//    }
        
//    public boolean insertarImagen(String imagen, String tipo_imagen){    
//         try {          
//            ArrayList<NameValuePair> body=new ArrayList<>();
//            body.add(new BasicNameValuePair("imagen", imagen));
//            String id_proceso=String.valueOf(seleccionarCod);
//            body.add(new BasicNameValuePair("id_proceso", id_proceso));
//            body.add(new BasicNameValuePair("par_tipo_imagen", tipo_imagen));           
//            String result = postServicio.POST("/registrarDocumentos", body);
//            System.out.println("ms:"+ result);
//            return true;       
//        } catch (IOException ex) {
//            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false; 
//    }   
    
//    public void listarDocumento(String id_proceso, String tipo_documento) throws IOException{        
//        HttpClient httpClient = HttpClients.createDefault();        
//        HttpGet httpGet = new HttpGet(URL_SERVICES+"/listarDocumentosJava/"+id_proceso+"/"+tipo_documento);      
//        httpGet.addHeader("token", LoginFrm.token);
//        try (CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet)) {           
//            String responseBody = EntityUtils.toString(response.getEntity());
//            JSONObject obj = new JSONObject(responseBody);
//            JSONArray arr = obj.getJSONArray("contenido");
//            tamano=arr.length();
//            ImageIcon imageIcon=null;
//            BufferedImage image=null;
//            for(int i = 0; i < tamano; i++ ){
//               // String id_proceso_base = arr.getJSONObject(i).getString("id_proceso");
//                tipo_imagen_base = arr.getJSONObject(i).getString("par_tipo_imagen");               
//                imagen_base = arr.getJSONObject(i).getString("imagen");                 
//                byte[] decodedBytes = Base64.getDecoder().decode(imagen_base);
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);                
//                image = ImageIO.read(inputStream);                             
//                if(tipo_imagen_base.equals("HPI")||tipo_imagen_base.equals("FOT")||tipo_imagen_base.equals("")){  
//                    imageIcon = new ImageIcon(image.getScaledInstance(Capturar0.getWidth(), Capturar0.getHeight(), Image.SCALE_DEFAULT));
//                    Capturar0.setIcon(imageIcon);
//                }
//                if(tipo_imagen_base.equals("HDII")){                   
//                    imageIcon = new ImageIcon(image.getScaledInstance(Capturar1.getWidth(), Capturar1.getHeight(), Image.SCALE_DEFAULT));
//                    Capturar1.setIcon(imageIcon);
//                }
//                if(tipo_imagen_base.equals("HDID")){                   
//                    imageIcon = new ImageIcon(image.getScaledInstance(Capturar2.getWidth(), Capturar2.getHeight(), Image.SCALE_DEFAULT));
//                    Capturar2.setIcon(imageIcon);
//                }
//                if(tipo_imagen_base.equals("HPD")){                   
//                    imageIcon = new ImageIcon(image.getScaledInstance(Capturar3.getWidth(), Capturar3.getHeight(), Image.SCALE_DEFAULT));
//                    Capturar3.setIcon(imageIcon);
//                }
//            }            
//        }
//    }
//    
//    public void listarRequisitos(String id_proceso) throws IOException{
//        HttpClient httpClient = HttpClients.createDefault();        
//        HttpGet httpGet = new HttpGet(URL_SERVICES+"/listarRequisitosJava/"+id_proceso);      
//        httpGet.addHeader("token", LoginFrm.token);
//        HttpResponse response = httpClient.execute(httpGet);
//       
//        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line;     
//        DefaultTableModel dfm=new DefaultTableModel();
//        tablaReq=ListarRequerimientosFrm.jTableRequisitos;
//        tablaReq.setModel(dfm);            
//        dfm.setColumnIdentifiers(new Object[]{"Cumplimiento","Requisito","Seleccionar"});    
//        tablaReq.getColumnModel().getColumn(0).setPreferredWidth(5);//numero        
//        tablaReq.getColumnModel().getColumn(1).setPreferredWidth(300);//cod Inicio
//        tablaReq.getColumnModel().getColumn(2).setPreferredWidth(6);        
//        addCheckbox(2, tablaReq);
//        while ((line = reader.readLine()) != null) {
//            JSONObject jsonObjeto=new JSONObject(line);   
//            JSONArray peticion=jsonObjeto.getJSONArray("contenido");            
//            for (int i = 0; i < peticion.length(); i++) {
//                JSONObject datos=peticion.getJSONObject(i);  
//                dfm.addRow(new Object[]{  
//                    datos.getInt("id_cumplimiento"),                           
//                    datos.getString("nombre_req")                      
//                });                
//            }                    
//        } 
//    }
//   
   
   

//    public void insertarImagenDocumento(int idCumplimiento, File rutaFile, String foto) {
//        try {
//            HttpClient httpClient = HttpClients.createDefault();
//            HttpPost httpPost = new HttpPost(URL_SERVICES+"/subirarchivo");
//            httpPost.addHeader("token", LoginFrm.token);          
//            String id_cumplimiento=String.valueOf(idCumplimiento);
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
//                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
//                    .addTextBody("id_cumplimiento", id_cumplimiento)
//                    .addPart("archivo",new ByteArrayBody(Files.readAllBytes(rutaFile.toPath()) , foto));
//            
//            HttpEntity entity = builder.build();   
//            httpPost.setEntity(entity);
//            HttpResponse response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("Código de respuesta: " + statusCode);
//        } catch (IOException ex) {
//            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

  
       
}
