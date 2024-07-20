package Views;

//import com.jmc.mazebank.Controllers.Admin.AdminController;
//import com.jmc.mazebank.Controllers.Client.ClientController;
import Controllers.AdminController;
import Controllers.LoginSedemController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewFactory {
//    private AccountType loginAccountType;
    // Client Views
//    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
//    private AnchorPane dashboardView;
//    private AnchorPane transactionsView;
//    private AnchorPane accountsView;
    private AnchorPane ventasSedemView;
    private AnchorPane beneficiariosView;
    private AnchorPane reportesView;

    // Admin Views
//    private final ObjectProperty<AdminMenuOptions> selectedMenuItem;
    
    private final StringProperty selectedMenuItem;
//    private AnchorPane createClientView;
//    private AnchorPane clientsView;
//    private AnchorPane depositView;

    public ViewFactory(){
//        this.loginAccountType = AccountType.CLIENT;
//        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
//        this.selectedMenuItem = new SimpleObjectProperty<>();
        this.selectedMenuItem = new SimpleStringProperty("");
        
    }

    
    public StringProperty getSelectedMenuItem(){
        return this.selectedMenuItem;
    }
//    public AccountType getLoginAccountType() {
//        return loginAccountType;
//    }

//    public void setLoginAccountType(AccountType loginAccountType) {
//        this.loginAccountType = loginAccountType;
//    }

//    /*
//    * Client Views Section
//    * */
//    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
//        return clientSelectedMenuItem;
//    }

//    public AnchorPane getDashboardView() {
//        if (dashboardView == null){
//            try {
//                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return dashboardView;
//    }
    
    
      public AnchorPane getVentasSedemView(){
          if(ventasSedemView == null){
              try{
                  ventasSedemView = new FXMLLoader(getClass().getResource("/Resources/FXML/Main.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
          }
          return ventasSedemView;
      }
      
      public AnchorPane getBeneficiariosView(){
          if(beneficiariosView == null){    
              try{
                  beneficiariosView = new FXMLLoader(getClass().getResource("/Resources/FXML/Beneficiarios.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
          }
          return beneficiariosView;
      }
      
      
      
      public AnchorPane getReportesView(){
          if(reportesView == null){    
              try{
                  reportesView = new FXMLLoader(getClass().getResource("/Resources/FXML/Reportes.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
          }
          return reportesView;
      }
//
//    public AnchorPane getTransactionsView() {
//        if (transactionsView == null){
//            try {
//                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return transactionsView;
//    }
//
//    public AnchorPane getAccountsView() {
//        if (accountsView == null){
//            try {
//                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return accountsView;
//    }

    public void showClientWindow() {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/FXML/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);

    }
    
  
    
    /*
    * Admin Views Section
    * */
//    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
//        return adminSelectedMenuItem;
//    }

//    public AnchorPane getCreateClientView() {
//        if (createClientView == null){
//            try {
//                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return createClientView;
//    }
//
//    public AnchorPane getClientsView() {
//        if (clientsView == null) {
//            try {
//                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return clientsView;
//    }
//
//    public AnchorPane getDepositView() {
//        if (depositView == null){
//            try {
//                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return depositView;
//    }
//
//    public void showAdminWindow() {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
//        AdminController controller = new AdminController();
//        loader.setController(controller);
//        createStage(loader);
//    }
//
//
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/FXML/Login.fxml"));
        createStage(loader);
    }
    
    public void showLoginSedem(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/FXML/LoginSedem.fxml"));
//        LoginSedemController controller = new LoginSedemController();
//        loader.setController(controller);
        createStage(loader);
    }
    
    
//
//    public void showMessageWindow(String pAddress, String messageText) {
//        StackPane pane = new StackPane();
//        HBox hBox = new HBox(5);
//        hBox.setAlignment(Pos.CENTER);
//        Label sender = new Label(pAddress);
//        Label message = new Label(messageText);
//        hBox.getChildren().addAll(sender, message);
//        pane.getChildren().add(hBox);
//        Scene scene = new Scene(pane, 300, 100);
//        Stage stage = new Stage();
//        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
//        stage.setResizable(false);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setTitle("Message");
//        stage.setScene(scene);
//        stage.show();
//    }
//
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Resources/Images/shop.png"))));
//        stage.setResizable(false);
        stage.setTitle("SUBSIDIO EMAPA - SEDEM PRODUCTOS FRESCOS ");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
