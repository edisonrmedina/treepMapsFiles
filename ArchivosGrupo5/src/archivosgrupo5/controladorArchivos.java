package archivosgrupo5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class controladorArchivos implements Initializable 
{    
    private final HBox cuadro = new HBox();
    
    @FXML
    private StackPane bodega;
    
    @FXML
    private void abrirDirectorio(ActionEvent event) 
    {
        try
        {
            TreeMap principal = Recursos.abrirRuta();
            Complementos.dibujadorDeTreeMap(principal, this.cuadro);
        }
        
        catch(NullPointerException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cuadro.setMaxSize(1200, 700);
        bodega.setMaxSize(1200, 700);
        bodega.getChildren().add(cuadro);
    }    
}
