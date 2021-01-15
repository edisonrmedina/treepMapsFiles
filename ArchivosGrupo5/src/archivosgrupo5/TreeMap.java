
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

    public TreeMap(T content,long size) {
        this.root=new TreeNode(content,size);
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
                }
            }
        }
        return recorrido;
    }
    
     public long recorrerTOTALtamanio(TreeMap<T> root){
        long total=0;
        Queue<TreeNode<T>> cola=new LinkedList<>();
        cola.add(root.getRoot());
        root.root.setVisited(true);
        while(!cola.isEmpty()){
            TreeNode<T> nodo=cola.remove();
            total=total+nodo.getSize();
            List<TreeMap<T>> hijos=nodo.getChildren();
            for(TreeMap<T> hijo: hijos){
                TreeNode<T> hijoSelect=hijo.getRoot();
                if(!hijoSelect.isVisited()){
                    cola.add(hijoSelect);
                    hijoSelect.setVisited(true);
                }
            }
        }
        return total;
    }
}
