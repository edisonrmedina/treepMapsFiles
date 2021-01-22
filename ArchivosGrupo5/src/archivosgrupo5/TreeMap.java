package archivosgrupo5;

public class TreeMap {
    
    private TreeNode raiz;
    
    public TreeMap()
    {
        this.raiz = new TreeNode();
    }
    
    public TreeMap(String nombre)
    {
        this.raiz = new TreeNode(nombre);
    }
    
    public TreeMap(String ruta, double tamaño, String nombre)
    {
        this.raiz = new TreeNode(ruta, tamaño, nombre);
    }
    
    public void añadirHijo(TreeMap hijo)
    {
        this.raiz.añadirHijo(hijo);
    }

    public TreeNode getRaiz() 
    {
        return raiz;
    }

    public void setRaiz(TreeNode raiz) 
    {
        this.raiz = raiz;
    }
    
    public boolean tieneHijos()
    {
        return this.raiz.tieneHijos();
    }
}
