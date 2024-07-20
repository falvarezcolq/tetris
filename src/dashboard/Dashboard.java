
package dashboard;

import Models.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin@emapa.lan
 */
public class Dashboard extends Application{
    
    public static void main(String[] args) {
         launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    
}
