package archivosgrupo5;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author LENOVO
 */
public class controladorArchivos {
      @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox mnBar;
    
    

    

    @FXML
    void initialize() {
        assert mnBar != null : "fx:id=\"mnBar\" was not injected: check your FXML file 'inicio.fxml'.";    
}
    public void abrirDirectorio(){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("src"));
        File selectedDir = dc.showDialog(null);
        
        File[] files = selectedDir.listFiles();
        
        
        
        System.out.println(selectedDir.getAbsolutePath());
    }
}
