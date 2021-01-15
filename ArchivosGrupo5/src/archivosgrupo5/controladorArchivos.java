package archivosgrupo5;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
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
    
    @FXML
    private AnchorPane center;
    
    void initialize() {
        assert mnBar != null : "fx:id=\"mnBar\" was not injected: check your FXML file 'inicio.fxml'.";    
}
    @FXML
    public void abrirDirectorio(){
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("src"));
        File selectedDir = dc.showDialog(null);
        TreeMap arbol=null;
         try{
            File[] files = selectedDir.listFiles();
            arbol = ArbolDirectorios.creaArbolDeDirectorios(selectedDir);
//            System.out.println(arbol);
//            System.out.println(selectedDir.getAbsolutePath());
            
        }catch(NullPointerException ex){
                Alert altDer = new Alert(Alert.AlertType.INFORMATION);
                altDer.setTitle("Sin seleccion ");
                altDer.setHeaderText("No se ha seleccionado ninguna carpeta");
                altDer.show();
           
        }visualizeButtonAction(arbol);
    }
    public void setLabelSize(Label lb, double amount,String content) {
        lb.setStyle("-fx-font-weight: bold; -fx-font-size: 15");
        DecimalFormat two = new DecimalFormat("0.00");
        if (amount < 1024) {
            lb.setText("("+content + amount + " KB" + ")");
        } else if (amount > 1024 && amount < 1024 * 1024) {
            lb.setText("("+content + two.format(amount / 1024) + " MB" + ")");
        } else if (amount > 1024 * 1024 && amount < 1024 * 1024) {
            lb.setText("(" +content + two.format(amount / 1024 * 1024) + " GB" + ")");
        } else {
            lb.setText("(" +content + two.format(amount / 1024 * 1024 * 1024) + " TB" + ")");
        }
    }
       public void visualizeButtonAction(TreeMap arbolArchivos) {
        VBox container = new VBox();
        Pane tamanioTot = new Pane();

        HBox graphics = new HBox();
        graphics.setMaxWidth(960);
        graphics.setMaxHeight(650);

        Rectangle graphicSizeTotal = new Rectangle();
        graphicSizeTotal.setWidth(960);
        graphicSizeTotal.setHeight(25);
        graphicSizeTotal.setFill(Color.CORAL);
        graphicSizeTotal.setStroke(Color.WHITE);
        Label extensionSize = new Label();
        setLabelSize(extensionSize, arbolArchivos.getRoot().getSize(),(String)arbolArchivos.getRoot().getContent());
//        Label tamanio = new Label();
//        tamanio.setMinSize(minWidth, minHeight);
//        tamanio.setMaxSize(minWidth, minHeight);

        tamanioTot.getChildren().addAll(graphicSizeTotal, extensionSize);
        container.getChildren().addAll(tamanioTot, graphics);
        long totalSize=arbolArchivos.recorrerTOTALtamanio(arbolArchivos);
        int counter=2;
        Painting(arbolArchivos, graphics, 300.0, 200.0, counter,totalSize);
            
        
        center.getChildren().addAll(container);
//        save.setDisable(false);
    }
    public void Painting(TreeMap<String> map, Pane pane, double width, double height,int counter,long size) { 
      List<TreeMap<String>> arbolList=map.recorrerEnAnchura(map);
        for(TreeMap<String> nodo: arbolList){   
        if(nodo.getRoot().isIsDirectory()){
            if(counter % 2 == 0) {
                System.out.println("v"+nodo);
                double fact1 = width;
                double fact2 = height * (nodo.getRoot().sixe()/ size);
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                HBox temp = new HBox();
                
                temp.getChildren().addAll(shape);
                  Label extensionSize = new Label();
                setLabelSize(extensionSize, nodo.getRoot().sixe(),(String)nodo.getRoot().getContent());
                pane.getChildren().add(temp);
                counter++;
            }else {
                System.out.println("h"+nodo);
                double fact1 = width * (nodo.getRoot().sixe() / size);
                double fact2 = height;
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                VBox temp = new VBox();
                temp.getChildren().addAll(shape);
                  Label extensionSize = new Label();
                setLabelSize(extensionSize, nodo.getRoot().sixe(),(String)nodo.getRoot().getContent());
                pane.getChildren().add(temp);
                counter++;
            } }else{
            if (counter % 2 == 0) {
                System.out.println("Directorio v"+nodo);
                double size2 = nodo.getRoot().getSize();
                HBox box = new HBox();
                box.setMaxWidth(width);
                box.setMaxHeight(height * (size2 / size));
                Painting(nodo, box, box.getMaxWidth(), box.getMaxHeight(),counter+1,size );
                pane.getChildren().add(box);
                counter++;
            }
            else {
                System.out.println("Directorio h"+nodo);
                double size2 = nodo.getRoot().getSize();
                VBox box = new VBox();
                box.setMaxWidth(width * (size2 / size));
                box.setMaxHeight(height);
                Painting(nodo, box, box.getMaxWidth(), box.getMaxHeight(), counter+1,size);
                pane.getChildren().add(box);
                counter++;
            } 
        }}
        
    }
    
     public Color getRandomColor() {
        Random rd = new Random();
        float r = rd.nextFloat();
        float g = rd.nextFloat();
        float b = rd.nextFloat();
        Color randomColor = new Color(r, g, b, 1);
        return randomColor;
    }
       
       
    @FXML
    public void salir(){
         Stage estageActual = (Stage) btnSalir.getScene().getWindow();
            estageActual.close();
    }
    }

