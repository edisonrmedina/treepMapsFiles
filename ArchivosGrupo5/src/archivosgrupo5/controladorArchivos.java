package archivosgrupo5;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private AnchorPane arbolDibujo;
    
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
        List<TreeMap<String>> arbolList=arbolArchivos.recorrerEnAnchura(arbolArchivos);
        List<TreeMap<String>> listaDirectorios=new LinkedList<>();
        float totalDirectorios=0;
        for(TreeMap<String> arbol : arbolList){
            System.out.println(arbol);
            if(!arbol.getRoot().getChildren().isEmpty())
                listaDirectorios.add(arbol);
                totalDirectorios+=arbol.getRoot().getSize();
    }
        tamanioTot.getChildren().addAll(graphicSizeTotal, extensionSize);
        container.getChildren().addAll(tamanioTot, graphics);

        PaintingDir(listaDirectorios, graphics, 300.0, 200.0, "2",totalDirectorios);
            
        arbolDibujo.getChildren().addAll(container);
//        save.setDisable(false);
    }
    public void PaintingDir(List<TreeMap<String>> map, Pane pane, double width, double height,String counter,float size) { 
        for(TreeMap<String> nodo:map){  
            if (counter.equals("1")) {
                HBox box = new HBox();
                box.setMaxWidth(width);
                box.setMaxHeight(height*(nodo.getRoot().getSize()));
                System.out.println((nodo.getRoot().getSize()/size));
                box.setStyle("-fx-background-color: #FFFFFF;");
                PaintingDocs(nodo.getRoot().getChildren(), box, box.getMaxWidth(), box.getMaxHeight(),"1",nodo.getRoot().getSize() );
                pane.getChildren().addAll(box);
                counter="2";
            }
            else if (counter.equals("2")) {
                //System.out.println("Directorio h"+nodo);
                VBox box = new VBox();
                box.setMaxWidth(width*(nodo.getRoot().getSize()/size));
                System.out.println((nodo.getRoot().getSize()/size));
                box.setMaxHeight(height);
                box.setStyle("-fx-background-color: #FFFFFF;");
                PaintingDocs(nodo.getRoot().getChildren(), box, box.getMaxWidth(), box.getMaxHeight(), "2",nodo.getRoot().getSize());
                pane.getChildren().add(box);
                counter="1";
            } 
        }
        
    }
    public void PaintingDocs(List<TreeMap<String>> map, Pane pane, double width, double height,String counter,float size) { 
        for(TreeMap<String> nodo:map){  
            Pane tempPane=new Pane();
            if(counter.equals("1")&&nodo.getRoot().getChildren().isEmpty()) {
                //System.out.println("v"+nodo);
                double fact1 = width;
                double fact2 = height * ((nodo.getRoot().getSize())/ size);
                System.out.println((nodo.getRoot().getSize()/size));
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                HBox temp = new HBox();
                counter="2";
                
                Label extensionSize = new Label((String)nodo.getRoot().getName());
                tempPane.getChildren().addAll(shape,extensionSize);
                temp.getChildren().addAll(tempPane);
                  
                pane.getChildren().addAll(temp);
            }else if(counter.equals("2")&&nodo.getRoot().getChildren().isEmpty()) {
                //System.out.println("h"+nodo);
                double fact1 = width * ((nodo.getRoot().getSize()) / size);
                System.out.println((nodo.getRoot().getSize()/size));
                double fact2 = height;
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                VBox temp = new VBox();
                
               
                Label extensionSize = new Label((String)nodo.getRoot().getName());
                tempPane.getChildren().addAll(shape,extensionSize);
                temp.getChildren().addAll(tempPane);
                  
                pane.getChildren().addAll(temp);
                counter="1";

            
        }
        
    }}
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

