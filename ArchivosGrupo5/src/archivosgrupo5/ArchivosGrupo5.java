package archivosgrupo5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArchivosGrupo5 extends Application 
{
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setMaxHeight(600);
        stage.setMaxWidth(1200);
        stage.setScene(scene);
        stage.setTitle("TreeMaps");
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
