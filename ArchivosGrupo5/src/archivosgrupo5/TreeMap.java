
package archivosgrupo5;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 *
 * @author fioye
 */
public class TreeMap<T> {
    private TreeNode<T> root;

    public TreeMap(T content,float size,String name) {
        this.root=new TreeNode(content,size,name);
    }
    
    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
    public boolean isEmpty(){
        return this.root==null;
    }
    private boolean isLeaf(){
        return this.root.getChildren().isEmpty();
    }
    
    @Override
    public String toString() {
        return "TreeMap{" + "root=" + root + '}';
    }    

    public List<TreeMap<T>> recorrerEnAnchura(TreeMap<T> root){
        List<TreeMap<T>> recorrido=new LinkedList<>();
        Queue<TreeMap<T>> cola=new LinkedList<>();
        cola.add(root);
        root.root.setVisited(true);
        while(!cola.isEmpty()){
            TreeMap<T> nodo=cola.remove();
            recorrido.add(nodo);
            List<TreeMap<T>> hijos=nodo.getRoot().getChildren();
            for(TreeMap<T> hijo: hijos){
                if(!hijo.getRoot().isVisited()){
                    cola.add(hijo);
                    hijo.getRoot().setVisited(true);
                    if(hijo.isLeaf())
                        hijo.getRoot().setIsDirectory(false);
                }
            }
        }
        return recorrido;
    }
    
}
