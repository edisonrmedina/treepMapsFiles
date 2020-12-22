package archivosgrupo2;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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

}