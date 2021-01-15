package archivosgrupo5;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ArchivosGrupo5 extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        Scene scene = new Scene(root, 960, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Visualizador de archivos -- TreeMap-- Grupo 5");
        primaryStage.show();
    }}
