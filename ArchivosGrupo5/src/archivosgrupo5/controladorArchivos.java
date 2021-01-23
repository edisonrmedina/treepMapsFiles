package archivosgrupo5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
     @FXML
    private void salir(ActionEvent event) throws IOException
    {
        Stage pant = (Stage)bodega.getScene().getWindow();
        pant.close();
    }
    
    @FXML
    private void guardarCap(ActionEvent event)
    {
        try
        {
            Recursos.guadarCaptura(cuadro);
        }
        
        catch(NullPointerException e)
        {
            System.out.println(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cuadro.setMaxSize(1170, 530);
        bodega.setMaxSize(1170, 530);
        bodega.getChildren().add(cuadro);
    }    
}
