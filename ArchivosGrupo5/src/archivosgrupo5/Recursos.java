package archivosGrupo5;

import java.io.File;
import java.util.List;
import javafx.stage.DirectoryChooser;

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
            double tamañoRuta = tamañoNodo(Ruta)/1048576;
            
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
            double tamañoRuta = tamañoNodo(nuevaRuta)/1048576;
            
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
        System.out.println(concatenar + arbolito.getRaiz().getNombre() +"    tamaño: " + arbolito.getRaiz().getTamañoFichero()+"MB");
        
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
}
