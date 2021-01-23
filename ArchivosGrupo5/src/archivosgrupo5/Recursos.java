package archivosgrupo5;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class Recursos 
{
    //obtiene el tamaño de la ruta que lee (en bytes)--------------------------------------------------------------------------------------
    private static double tamañoNodo(String ruta)
    {
        File nodo = new File(ruta);
        
        if(nodo.isDirectory())
        {
            File[] datos = nodo.listFiles();
            double peso = 0;
            for(File arch: datos)
            {
                if(arch.isFile())
                {
                    peso = peso + arch.length();
                } 
                
                else
                {
                    peso = peso + tamañoNodo(arch.getAbsolutePath());
                }
            }
            return peso;
        }
        
        else 
        {
            return nodo.length();
        }
    }
    
    //nos da el arbol principal de directorios----------------------------------------------------------------------------------------
    private static TreeMap arbolPrincipal(String rutaPartida)
    {
        File archivoBase = new File(rutaPartida);
        
        if(archivoBase.exists() && archivoBase.isDirectory())
        {
            String Ruta = archivoBase.getAbsolutePath();
            String nombre = archivoBase.getName();
            double tamañoRuta = tamañoNodo(Ruta)/1073741824;
            
            TreeMap arbolBase = new TreeMap(Ruta, tamañoRuta, nombre);
            return crearArbolDirectorios(arbolBase);
        }
        
        else
        {
            System.out.println("La ruta especificada no existe");
            return null;
        }
    }
    
    //crea arboles dentro del arbol principal-------------------------------------------------------------------------------------------
    private static TreeMap crearArbolDirectorios(TreeMap arbolBase)
    {
        File ruta = new File(arbolBase.getRaiz().getRuta());
        File[] rutas = ruta.listFiles();
        
        for(File archivo: rutas)
        {
            String nuevaRuta = archivo.getAbsolutePath();
            String nombre = archivo.getName();
            double tamañoRuta = tamañoNodo(nuevaRuta)/1073741824;
            
            TreeMap nuevito = new TreeMap(nuevaRuta, tamañoRuta, nombre);
            arbolBase.añadirHijo(nuevito);
            
            if(archivo.isDirectory())
            {
                crearArbolDirectorios(nuevito);
            }
        }
        return arbolBase;
    }
    
    //Imprime el directorio de manera identada (demora según el directorio seleccionado)------------------------------------------------------   
    private static void imprimirElArbol(TreeMap arbolito, String concatenar)
    {
        System.out.println(concatenar + arbolito.getRaiz().getNombre() +"    tamaño: " + arbolito.getRaiz().getTamañoFichero()+"GB");
        
        if(arbolito.getRaiz().getHijos().size()>0)
        {
            List<TreeMap> hijos = arbolito.getRaiz().getHijos();
      
            hijos.forEach((hijo) -> 
            {
                imprimirElArbol(hijo, "     " + concatenar);
            });
        }
    }
    
    //permite abrir un archivo seleccionandolo------------------------------------------------------------------------------------------
    public static TreeMap abrirRuta()
    {
        DirectoryChooser directorio = new DirectoryChooser();
        File seleccionado = directorio.showDialog(null);
        
        TreeMap arbolito = arbolPrincipal(seleccionado.getAbsolutePath());
        imprimirElArbol(arbolito, "");
        return arbolito;
    }
    
    public static void guadarCaptura(Pane panel)
    {
        WritableImage captura = panel.getScene().snapshot(null);
        
        FileChooser archivoGuardar = new FileChooser();
        archivoGuardar.setTitle("Seleccione ruta para guardar su captura");
        archivoGuardar.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagen" ,"*.png"));
        
        File archivo = archivoGuardar.showSaveDialog(null);
        String ruta = archivo.getAbsolutePath();
        
        File imagen = new File(ruta);
        
        try
        {
            ImageIO.write(SwingFXUtils.fromFXImage(captura, null), "png", imagen);
            Desktop dt = Desktop.getDesktop();
            dt.open(imagen);
        }
        
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
