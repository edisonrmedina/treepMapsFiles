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
    private String name;
    private float size = 0;
    private boolean visited=false;
    private boolean isDirectory;

    public boolean isVisited() {
        return visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public TreeNode(T content, float size, String name) {
        this.content = content;
        this.size = size;
        this.name=name;
    }

    public void addChild(TreeMap child) {
        children.add(child);
    }

    public T getContent() {
        return this.content;
    }

    public float getSize() {
        return size;
    }

    public boolean isIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }
    
    public void setSize(float size) {
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
