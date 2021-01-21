

package archivosgrupo5;

import java.util.ArrayList;
import java.util.List;


public class TreeNode {
    private String ruta;
    private double tamañoFichero;
    private String nombre;
    private List<TreeMap> hijos;
    private boolean visitado;
    
    public TreeNode()
    {
        this.hijos = new ArrayList<>();
    }
    
    public TreeNode(String nombre)
    {
        this.ruta = nombre;
        this.visitado = false;
        this.hijos = new ArrayList<>();
    }
     
    public TreeNode(String ruta, double tamañoFichero, String nombre)
    {
        this.ruta = ruta;
        this.tamañoFichero = tamañoFichero;
        this.nombre = nombre;
        this.visitado = false;
        this.hijos = new ArrayList<>();
    }

    public String getRuta() 
    {
        return ruta;
    }

    public void setRuta(String ruta) 
    {
        this.ruta = ruta;
    }

    public double getTamañoFichero() 
    {
        return tamañoFichero;
    }

    public void setTamañoFichero(double tamañoFichero) 
    {
        this.tamañoFichero = tamañoFichero;
    }

    public List<TreeMap> getHijos() 
    {
        return hijos;
    }

    public void setHijos(List<TreeMap> hijos) 
    {
        this.hijos = hijos;
    }

    public boolean isVisitado() 
    {
        return visitado;
    }

    public void setVisitado(boolean visitado) 
    {
        this.visitado = visitado;
    }
    
    public void añadirHijo(TreeMap hijo)
    {
        this.hijos.add(hijo);
    }
    
      public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    
    public boolean tieneHijos()
    {
        return this.hijos.size()>0;
    }
}
