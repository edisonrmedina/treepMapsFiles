/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivosgrupo5;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fioye
 */
public class TreeNode<T> {
    private List<TreeMap<T>> children = new ArrayList<>();
    private T content;
    private long size = 0;
    private boolean visited=false;
    private boolean isDirectory;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public TreeNode(T content, long size) {
        this.content = content;
        this.size = size;
    }

    public void addChild(TreeMap child) {
        children.add(child);
    }

    public T getContent() {
        return this.content;
    }

    public long getSize() {
        return size;
    }
    public long sixe(){
        long totalSize = getSize( );
        if( isDirectory( ) ){
            List<TreeMap<T>> childrenList=getChildren();
            for (TreeMap<T> child:children)
                totalSize += child.getRoot().sixe( );}
        return totalSize;
    }

    public boolean isIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }
    
    public void setSize(long size) {
        this.size = size;
    }

    public void setChildren(List<TreeMap<T>> children) {
        this.children = children;
    }

    public void setContent(T content) {
        this.content = content;
    }
 
    public List<TreeMap<T>> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "children=" + children + ", content=" + content + ", size=" + size + '}';
    }

    private boolean isDirectory() {
        return getChildren()==null;
    }
    
    
    
}
