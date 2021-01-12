package archivosgrupo5;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
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
            TreeMap root = ArbolDirectorios.creaArbolDeDirectorios(selectedDir);
            
            //GraficadorArbol.graficar(root);
            //recorremos el 
             recorrerPostOrden(root);
            System.out.println(selectedDir.getAbsolutePath());
        }catch(NullPointerException ex){
                Alert altDer = new Alert(Alert.AlertType.INFORMATION);
                altDer.setTitle("Sin seleccion ");
                altDer.setHeaderText("No se ha seleccionado ninguna carpeta");
                altDer.show();
        }
           
        }
       public void visualizeButtonAction(TreeMap mapaArchivos) throws IOException {
        VBox container = new VBox();
        Pane SizeTotal = new Pane();

        HBox graphics = new HBox();
        graphics.setMaxWidth(960);
        graphics.setMaxHeight(650);

        Rectangle graphicSizeTotal = new Rectangle();
        graphicSizeTotal.setWidth(960);
        graphicSizeTotal.setHeight(25);
        graphicSizeTotal.setFill(Color.CORAL);
        graphicSizeTotal.setStroke(Color.WHITE);

        Label extensionSize = new Label();
        setLabelSize(extensionSize, mapaArchivos.getChildren().get(0).getSize());

        SizeTotal.getChildren().addAll(graphicSizeTotal, extensionSize);
        container.getChildren().addAll(SizeTotal, graphics);
        Painting(treeMap.getFirst(), graphics, 960.0, 650.0, "h");
        center.getChildren().addAll(container);
        save.setDisable(false);
    }

    public void Painting(Directory directory, Pane pane, double width, double height, String type) {
        LinkedList<Directory> selected = directory.getDirectorios();
        double size = directory.getSize();
        selected.forEach((f) -> {
            if (!f.isDirectory() && type.equals("h")) {
                double fact1 = width * (f.getSize() / size);
                double fact2 = height;
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                VBox temp = new VBox();
                temp.getChildren().addAll(shape);
                pane.getChildren().add(temp);
            } else if (!f.isDirectory() && type.equals("v")) {
                double fact1 = width;
                double fact2 = height * (f.getSize() / size);
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                HBox temp = new HBox();
                temp.getChildren().addAll(shape);
                pane.getChildren().add(temp);
            } else if (f.isDirectory() && type.equals("h")) {
                double size2 = f.getSize();
                VBox box = new VBox();
                box.setMaxWidth(width * (size2 / size));
                box.setMaxHeight(height);
                Painting(f, box, box.getMaxWidth(), box.getMaxHeight(), "v");
                pane.getChildren().add(box);
            } else if (f.isDirectory() && type.equals("v")) {
                double size2 = f.getSize();
                HBox box = new HBox();
                box.setMaxWidth(width);
                box.setMaxHeight(height * (size2 / size));
                Painting(f, box, box.getMaxWidth(), box.getMaxHeight(), "h");
                pane.getChildren().add(box);
            }
        });
    }
    public void salir(){
         Stage estageActual = (Stage) btnSalir.getScene().getWindow();
            estageActual.close();
    }
    }

