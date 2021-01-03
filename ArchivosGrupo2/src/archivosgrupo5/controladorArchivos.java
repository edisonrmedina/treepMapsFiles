package archivosgrupo5;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class controladorArchivos {

    @FXML
    private VBox mnBar;
    
    @FXML
    private Button btnSalir;
    
    void initialize() {
        assert mnBar != null : "fx:id=\"mnBar\" was not injected: check your FXML file 'inicio.fxml'.";    
}
    @FXML
    public void abrirDirectorio(){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("src"));
        File selectedDir = dc.showDialog(null);
        
         try{
            File[] files = selectedDir.listFiles();
            System.out.println(selectedDir.getAbsolutePath());
        }catch(NullPointerException ex){
                Alert altDer = new Alert(Alert.AlertType.INFORMATION);
                altDer.setTitle("Sin seleccion ");
                altDer.setHeaderText("No se ha seleccionado ninguna carpeta");
                altDer.show();
        }
           
        }
    public void salir(){
         Stage estageActual = (Stage) btnSalir.getScene().getWindow();
            estageActual.close();
    }
    }

